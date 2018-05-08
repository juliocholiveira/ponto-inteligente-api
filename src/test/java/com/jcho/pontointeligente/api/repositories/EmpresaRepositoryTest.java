package com.jcho.pontointeligente.api.repositories;

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

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class EmpresaRepositoryTest {
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	private static final String CNPJ = "5146365000100";
	
	@Before
	public void setUp() throws Exception{
		empresaRepository.save(obterDadosEmpresa());
	}
	
	@After
	public final void tearDown() {
		empresaRepository.deleteAll();
	}
	
	@Test
	public void testBuscarPorCnpj() {
		Empresa empresa = empresaRepository.findByCnpj(CNPJ);
		
		assertNotNull(empresa);
	}
	
	@Test
	public void testBuscarPorCnpjParaCnpjInvalido() {
		Empresa empresa = empresaRepository.findByCnpj("123456000100");
		
		assertNull(empresa);
	}
	
	public static Empresa obterDadosEmpresa() {
		// Criando empresa
		Empresa empresa = new Empresa();
		empresa.setCnpj(CNPJ);
		empresa.setRazaoSocial("BANCO DO BRASIL");
		
		return empresa;
	}

}
