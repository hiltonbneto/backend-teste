package com.teste.teste.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teste.teste.login.dto.LoginInput;
import com.teste.teste.login.dto.LoginOutput;
import com.teste.teste.login.dto.NovoUsuarioInput;
import com.teste.teste.login.service.LoginService;

@RestController
@RequestMapping("/auth")
public class LoginController {

	@Autowired
	private LoginService service;

	@PostMapping("/register")
	public ResponseEntity<LoginOutput> register(@RequestBody NovoUsuarioInput input) {
		return ResponseEntity.ok(service.cadastrar(input));
	}

	@PostMapping("/login")
	public ResponseEntity<LoginOutput> login(@RequestBody LoginInput input) {
		return ResponseEntity.ok(service.login(input));
	}

}
