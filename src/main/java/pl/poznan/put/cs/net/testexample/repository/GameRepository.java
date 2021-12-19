package pl.poznan.put.cs.net.testexample.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import pl.poznan.put.cs.net.testexample.model.Game;

public interface GameRepository extends CrudRepository<Game, String> {
	
	@Override
	List<Game> findAll();
}
