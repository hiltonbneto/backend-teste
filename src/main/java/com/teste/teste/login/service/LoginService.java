package com.teste.teste.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.teste.teste.aplicacao.security.JwtUtil;
import com.teste.teste.login.dto.LoginInput;
import com.teste.teste.login.dto.LoginOutput;
import com.teste.teste.login.dto.NovoUsuarioInput;
import com.teste.teste.login.orm.UsuarioEntity;
import com.teste.teste.login.repositorio.UsuarioRepositorioJpa;

@Service
public class LoginService {

	@Autowired
	private UsuarioRepositorioJpa usuarioRepositorioJpa;

	private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	public LoginOutput cadastrar(final NovoUsuarioInput input) {
		if (usuarioRepositorioJpa.findByEmail(input.email()).isPresent()) {
			throw new RuntimeException("Usuário já existe");
		}

		String hashedPassword = passwordEncoder.encode(input.senha());
		UsuarioEntity usuarioEntity = new UsuarioEntity();
		usuarioEntity.setEmail(input.email());
		usuarioEntity.setNome(input.nome());
		usuarioEntity.setSenha(hashedPassword);
		usuarioRepositorioJpa.save(usuarioEntity);
		return login(new LoginInput(input.email(), input.senha()));
	}

	public LoginOutput login(final LoginInput input) {
		UsuarioEntity usuarioEntity = usuarioRepositorioJpa.findByEmail(input.email())
				.orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

		if (!passwordEncoder.matches(input.senha(), usuarioEntity.getSenha())) {
			throw new RuntimeException("Senha inválida");
		}

		String token = JwtUtil.generateToken(input.email()); // JWT aqui

		return new LoginOutput(token, usuarioEntity.getNome());
	}

}
