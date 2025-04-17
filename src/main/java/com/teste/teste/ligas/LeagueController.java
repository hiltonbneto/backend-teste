package com.teste.teste.ligas;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teste.teste.ligas.dto.LeagueDTO;
import com.teste.teste.ligas.orm.League;
import com.teste.teste.ligas.repositorio.LeagueRepository;
import com.teste.teste.ligas.service.LeagueService;

@RestController
@RequestMapping("/api/leagues")
public class LeagueController {

	private final LeagueService leagueService;

	private final LeagueRepository leagueRepository;

	public LeagueController(LeagueService leagueService, LeagueRepository leagueRepository) {
		this.leagueService = leagueService;
		this.leagueRepository = leagueRepository;
	}

	@PostMapping
	public ResponseEntity<League> createLeague(@RequestBody LeagueDTO leagueDTO) {
		League league = leagueService.createLeague(leagueDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(league);
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE+ ";charset=UTF-8")
	public ResponseEntity<List<League>> get() {
		return ResponseEntity.ok(leagueRepository.findAll());
	}
}