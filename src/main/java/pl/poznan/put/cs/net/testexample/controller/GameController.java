package pl.poznan.put.cs.net.testexample.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import pl.poznan.put.cs.net.testexample.model.Game;
import pl.poznan.put.cs.net.testexample.repository.GameRepository;

@RestController
@RequestMapping("/api/games")
public class GameController {

	private final GameRepository gameRepository;
	
	@Autowired
	public GameController(GameRepository gameRepository) {
		this.gameRepository = gameRepository;
	}
	
	@GetMapping
	public ResponseEntity<List<Game>> getAllGames() {
		return ResponseEntity.ok(gameRepository.findAll());
	}
	
	@PostMapping
	public ResponseEntity<?> addGame(@RequestBody @Valid Game game) {
		Game created = gameRepository.save(game);
		return ResponseEntity
				.created(MvcUriComponentsBuilder.fromController(getClass())
						.pathSegment(created.getId()).build().toUri())
				.build();
	}
	
}
