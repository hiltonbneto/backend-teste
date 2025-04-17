package com.teste.teste.ligas.dto;

import java.util.List;

public class LeagueDTO {
	private String name;
	private String date; // formato "dd/MM/yyyy"
	private String numberPlayer;
	private String format;
	private String status;
	private List<PlayerDTO> players;

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

	public List<PlayerDTO> getPlayers() {
		return players;
	}

	public void setPlayers(List<PlayerDTO> players) {
		this.players = players;
	}
}