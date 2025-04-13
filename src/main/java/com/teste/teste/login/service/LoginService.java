package com.teste.teste.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.teste.teste.aplicacao.security.JwtUtil;
import com.teste.teste.login.dto.LoginInput;
import com.teste.teste.login.dto.LoginOutput;
import com.teste.teste.login.dto.TokenOutput;
import com.teste.teste.login.dto.UsuarioInput;
import com.teste.teste.login.dto.UsuarioOutput;
import com.teste.teste.login.orm.UsuarioEntity;
import com.teste.teste.login.repositorio.UsuarioRepositorioJpa;

@Service
public class LoginService {

	@Autowired
	private UsuarioRepositorioJpa usuarioRepositorioJpa;

	private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	public UsuarioOutput cadastrar(final UsuarioInput input) {
		if (usuarioRepositorioJpa.findByEmail(input.email()).isPresent()) {
			throw new RuntimeException("Usuário já existe");
		}

		String hashedPassword = passwordEncoder.encode(input.senha());
		UsuarioEntity usuarioEntity = new UsuarioEntity();
		usuarioEntity.setEmail(input.email());
		usuarioEntity.setNome(input.nome());
		usuarioEntity.setSenha(hashedPassword);
		usuarioRepositorioJpa.save(usuarioEntity);
		return usuarioEntity.toUsuarioOutput();
	}

	public LoginOutput login(final LoginInput input) {
		UsuarioEntity usuarioEntity = usuarioRepositorioJpa.findByEmail(input.email())
				.orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

		if (!passwordEncoder.matches(input.senha(), usuarioEntity.getSenha())) {
			throw new RuntimeException("Senha inválida");
		}

		String token = JwtUtil.generateAccessToken(input.email()); // JWT aqui
		String refreshToken = JwtUtil.generateRefreshToken(input.email());

		return new LoginOutput(usuarioEntity.getNome(), new TokenOutput(token, refreshToken));
	}

	public TokenOutput refreshAccessToken(String refreshToken) {
		if (!JwtUtil.isTokenValid(refreshToken)) {
			throw new RuntimeException("Refresh token inválido ou expirado");
		}

		String username = JwtUtil.extractUsername(refreshToken);

		return new TokenOutput(JwtUtil.generateAccessToken(username), JwtUtil.generateRefreshToken(username));
	}

}
