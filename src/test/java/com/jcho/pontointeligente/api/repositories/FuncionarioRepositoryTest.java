package com.jcho.pontointeligente.api.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.jcho.pontointeligente.api.entities.Empresa;
import com.jcho.pontointeligente.api.entities.Funcionario;
import com.jcho.pontointeligente.api.enums.PerfilEnum;
import com.jcho.pontointeligente.api.utils.PasswordUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class FuncionarioRepositoryTest {

	@Autowired
	private EmpresaRepository empresaRepository;

	@Autowired
	private FuncionarioRepository funcionarioRepository;

	private static final String EMAIL = "julio@gmail.com";
	private static final String CPF = "03471477470";

	@Before
	public void setUp() throws Exception {

		// Salvando Empresa
		Empresa empresa = empresaRepository.save(EmpresaRepositoryTest.obterDadosEmpresa());

		// Salvando funcionario
		funcionarioRepository.save(obterDadosFuncionario(empresa));

	}

	@After
	public final void tearDown() {
		this.empresaRepository.deleteAll();
	}

	@Test
	public void testBuscarPorEmail() {
		Funcionario funcionario = funcionarioRepository.findByEmail(EMAIL);

		assertEquals(EMAIL, funcionario.getEmail());
	}

	@Test
	public void testBuscarPorCpf() {
		Funcionario funcionario = funcionarioRepository.findByCpf(CPF);

		assertEquals(CPF, funcionario.getCpf());
	}

	@Test
	public void testBuscarPorEmailECpf() {
		Funcionario funcionario = funcionarioRepository.findByCpfOrEmail(CPF, EMAIL);

		assertNotNull(funcionario);
	}

	@Test
	public void testBuscarPorEmailOuCpfParaEmailInvalido() {
		Funcionario funcionario = funcionarioRepository.findByCpfOrEmail(CPF, "julio2@gmail.com");

		assertNotNull(funcionario);
	}

	@Test
	public void testBuscarPorEmailOuCpfParaCpfInvalido() {
		Funcionario funcionario = funcionarioRepository.findByCpfOrEmail("12345678900", EMAIL);

		assertNotNull(funcionario);
	}

	@Test
	public void testBuscarPorEmailOuCpfParaEmailECpfInvalidos() {
		Funcionario funcionario = funcionarioRepository.findByCpfOrEmail("12345678900", "julio2@gmail.com");

		assertNull(funcionario);
	}

	public static Funcionario obterDadosFuncionario(Empresa empresa) {
		// Criando funcionario
		Funcionario funcionario = new Funcionario();
		funcionario.setCpf(CPF);
		funcionario.setEmail(EMAIL);
		funcionario.setEmpresa(empresa);
		funcionario.setNome("JÚLIO CÉSAR HENRIQUE DE OLIVEIRA");
		funcionario.setPerfil(PerfilEnum.ROLE_ADMIN);
		funcionario.setSenha(PasswordUtils.geraBCrypt("123456"));
		
		return funcionario;
	}

}
