package com.teste.teste.ligas.orm;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Player {

	@Id
	private UUID id; // Assumindo que o ID já vem do frontend e não é gerado no backend

	private String name;

	public Player() {
	}

	public Player(UUID id, String name) {
		this.id = id;
		this.name = name;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}