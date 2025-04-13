package com.teste.teste.login.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teste.teste.login.orm.UsuarioEntity;

public interface UsuarioRepositorioJpa extends JpaRepository<UsuarioEntity, Long> {

	Optional<UsuarioEntity> findByEmail(String email);

}
