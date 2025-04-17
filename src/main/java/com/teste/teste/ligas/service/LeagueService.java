package com.teste.teste.ligas.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.stereotype.Service;

import com.teste.teste.ligas.dto.LeagueInput;
import com.teste.teste.ligas.orm.League;
import com.teste.teste.ligas.orm.Player;
import com.teste.teste.ligas.repositorio.LeagueRepository;
import com.teste.teste.ligas.repositorio.PlayerRepository;

@Service
public class LeagueService {

	private final LeagueRepository leagueRepository;
	private final PlayerRepository playerRepository;

	public LeagueService(LeagueRepository leagueRepository, PlayerRepository playerRepository) {
		this.leagueRepository = leagueRepository;
		this.playerRepository = playerRepository;
	}

	public League createLeague(LeagueInput dto) {
		League league = new League();
		league.setName(dto.getName());
		league.setDate(LocalDate.parse(dto.getDate(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		league.setNumberPlayer(Integer.parseInt(dto.getNumberPlayer()));
		league.setFormat(dto.getFormat());
		league.setStatus(dto.getStatus());

		List<Player> playerIds = dto.getPlayers().stream().map(p -> new Player(p.getId(), p.getName())).toList();

		List<Player> players = playerRepository.saveAll(playerIds);
		league.setPlayers(players);

		return leagueRepository.save(league);
	}
}