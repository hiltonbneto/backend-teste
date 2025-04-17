package com.teste.teste.ligas.repositorio;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teste.teste.ligas.orm.League;

public interface LeagueRepository extends JpaRepository<League, UUID> {
}