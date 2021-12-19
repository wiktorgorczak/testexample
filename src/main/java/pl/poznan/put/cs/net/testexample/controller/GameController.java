package pl.poznan.put.cs.net.testexample.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/games")
public class GameController {

	@GetMapping
	public ResponseEntity<List<?>> getAllGames() {
		return ResponseEntity.ok(List.of());
	}
}
