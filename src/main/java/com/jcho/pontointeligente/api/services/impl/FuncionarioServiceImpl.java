package com.jcho.pontointeligente.api.services.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jcho.pontointeligente.api.entities.Funcionario;
import com.jcho.pontointeligente.api.repositories.FuncionarioRepository;
import com.jcho.pontointeligente.api.services.FuncionarioService;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {
	
	private static final Logger log = LoggerFactory.getLogger(FuncionarioServiceImpl.class);
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;

	@Override
	public Funcionario persistir(Funcionario funcionario) {
		log.info("Persistindo funcionario {}", funcionario);
		return funcionarioRepository.save(funcionario);
	}

	@Override
	public Optional<Funcionario> buscaPorCpf(String cpf) {
		log.info("Buscando funcionário pelo CPF {}", cpf);
		return Optional.ofNullable(funcionarioRepository.findByCpf(cpf));
	}

	@Override
	public Optional<Funcionario> buscaPorEmail(String email) {
		log.info("Buscando funcionário por email {}", email);
		return Optional.ofNullable(funcionarioRepository.findByEmail(email));
	}

	@Override
	public Optional<Funcionario> buscaPorId(Long id) {
		log.info("Buscando funcionário por id {}", id);
		return Optional.ofNullable(funcionarioRepository.findOne(id));
	}

}
