package com.teste.teste.ligas.dto;

import java.util.UUID;

public class LeagueOutput {
	private UUID id;
	private String name;
	private String date;
	private String numberPlayer;
	private String format;
	private String status;

	public LeagueOutput() {}
	
	public LeagueOutput(UUID id, String name, String date, String numberPlayer, String format, String status) {
		super();
		this.id = id;
		this.name = name;
		this.date = date;
		this.numberPlayer = numberPlayer;
		this.format = format;
		this.status = status;
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getNumberPlayer() {
		return numberPlayer;
	}

	public void setNumberPlayer(String numberPlayer) {
		this.numberPlayer = numberPlayer;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}