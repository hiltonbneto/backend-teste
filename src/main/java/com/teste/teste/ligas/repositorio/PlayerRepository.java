package com.teste.teste.ligas.repositorio;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teste.teste.ligas.orm.Player;

public interface PlayerRepository extends JpaRepository<Player, UUID> {
	
}
