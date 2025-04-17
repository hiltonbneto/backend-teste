package com.teste.teste.ligas.orm;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

import com.teste.teste.ligas.dto.LeagueOutput;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity
public class League {

	@Id
	@GeneratedValue
	private UUID id;

	private String name;

	private LocalDate date;

	@Column(name = "number_player")
	private int numberPlayer;

	private String format;

	private String status;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "league_player", joinColumns = @JoinColumn(name = "league_id"), inverseJoinColumns = @JoinColumn(name = "player_id"))
	private List<Player> players;

	public League() {
	}

	public League(String name, LocalDate date, int numberPlayer, String format, String status, List<Player> players) {
		this.name = name;
		this.date = date;
		this.numberPlayer = numberPlayer;
		this.format = format;
		this.status = status;
		this.players = players;
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

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public int getNumberPlayer() {
		return numberPlayer;
	}

	public void setNumberPlayer(int numberPlayer) {
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

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public LeagueOutput toOutput() {
		return new LeagueOutput(id, name, date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
				String.valueOf(numberPlayer), format, status);
	}
}