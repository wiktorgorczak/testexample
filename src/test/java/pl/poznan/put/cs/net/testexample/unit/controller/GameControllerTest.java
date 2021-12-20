package pl.poznan.put.cs.net.testexample.unit.controller;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import pl.poznan.put.cs.net.testexample.controller.GameController;
import pl.poznan.put.cs.net.testexample.model.DistributionService;
import pl.poznan.put.cs.net.testexample.model.Game;
import pl.poznan.put.cs.net.testexample.repository.GameRepository;

@WebMvcTest(GameController.class)
@ContextConfiguration
public class GameControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	GameRepository gameRepository;
	
	@Test
	public void getGame_returns404_whenThereIsNoSuchGame() throws Exception {
		when(gameRepository.findById(anyString())).thenReturn(Optional.empty());
		final String ID = "123123123";
		
		mockMvc.perform(MockMvcRequestBuilders.get("/api/games/" + ID))
			.andExpect(status().isNotFound());
		
		verify(gameRepository, times(1)).findById(anyString());
	}
	
	@Test
	public void getGame() throws Exception {
		DistributionService srv = new DistributionService();
		srv.setTitle("Steam");
		srv.setId("123");
		Game game = new Game();
		game.setDistributionService(srv);
		game.setTitle("Half-Life");
		
		when(gameRepository.findById("123")).thenReturn(Optional.of(game));
	}
}
