package com.jcho.pontointeligente.api.repositories;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.jcho.pontointeligente.api.entities.Empresa;
import com.jcho.pontointeligente.api.entities.Funcionario;
import com.jcho.pontointeligente.api.entities.Lancamento;
import com.jcho.pontointeligente.api.enums.TipoEnum;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class LancamentoRepositoryTest {

	@Autowired
	private EmpresaRepository empresaRepository;

	@Autowired
	private FuncionarioRepository funcionarioRepository;

	@Autowired
	private LancamentoRepository lancamentoRepository;

	private Funcionario funcionario;

	@Before
	public void setUp() throws Exception {
		// Salvando Empresa
		Empresa empresa = empresaRepository.save(EmpresaRepositoryTest.obterDadosEmpresa());

		// Salvando funcionario
		funcionario = funcionarioRepository.save(FuncionarioRepositoryTest.obterDadosFuncionario(empresa));
		
		// Salvando Lancamentos
		lancamentoRepository.save(obterDadosLancamentos(funcionario, TipoEnum.INICIO_TRABALHO));
		lancamentoRepository.save(obterDadosLancamentos(funcionario, TipoEnum.TERMINO_TRABALHO));

	}
	
	@After
	public void tearDown() throws Exception{
		this.empresaRepository.deleteAll();
	}
	
	@Test
	public void testBuscarLancamentosPorFuncionario() {
		List<Lancamento> lancamentos = lancamentoRepository.findByFuncionarioId(funcionario.getId());
		
		assertEquals(2, lancamentos.size());
	}
	
	@Test
	public void testBuscarLancamentosPorFuncionarioPaginado() {
		PageRequest pageRequest = new PageRequest(0, 10);
		Page<Lancamento> lancamentos = lancamentoRepository.findByFuncionarioId(funcionario.getId(), pageRequest);
		
		assertEquals(2, lancamentos.getTotalElements());
	}

	public static Lancamento obterDadosLancamentos(Funcionario funcionario, TipoEnum tipo) {
		Lancamento lancamento = new Lancamento();
		lancamento.setData(new Date());
		lancamento.setTipo(tipo);
		lancamento.setFuncionario(funcionario);
		return lancamento;
	}

}
