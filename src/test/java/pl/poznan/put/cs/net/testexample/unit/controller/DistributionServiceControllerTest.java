package pl.poznan.put.cs.net.testexample.unit.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import pl.poznan.put.cs.net.testexample.controller.DistributionServiceController;
import pl.poznan.put.cs.net.testexample.model.DistributionService;
import pl.poznan.put.cs.net.testexample.repository.DistributionServiceRepository;

@WebMvcTest(DistributionServiceController.class)
@ContextConfiguration
public class DistributionServiceControllerTest {
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	private DistributionServiceRepository distributionServiceRepository;
	
	@Test
	public void getAllDistributionServices() throws Exception {
		DistributionService exampleDistributionService = new DistributionService();
		exampleDistributionService.setTitle("Steam");
		exampleDistributionService.setId("123");
		exampleDistributionService.setGames(List.of());
		
		final String EXPECTED_CONTENT = "[\n"
				+ "    {\n"
				+ "        \"id\": \"123\",\n"
				+ "        \"title\": \"Steam\",\n"
				+ "        \"games\": []\n"
				+ "    }\n"
				+ "]";
		
		when(distributionServiceRepository.findAll())
			.thenReturn(List.of(exampleDistributionService));
		
		mockMvc
			.perform(MockMvcRequestBuilders.get("/api/distribution-services/"))
			.andExpect(status().isOk())
			.andExpect(content().json(EXPECTED_CONTENT));
		
		verify(distributionServiceRepository, times(1)).findAll();
	}
	
	@Test
	public void getDistributionService_returns404_whenItDoesNotExist() throws Exception {
		when(distributionServiceRepository.findById("123"))
			.thenReturn(Optional.ofNullable(null));
		
		mockMvc
		.perform(MockMvcRequestBuilders.get("/api/distribution-services/123"))
		.andExpect(status().isNotFound());
		
		//verify is essential here!
//		verify(distributionServiceRepository, times(1)).findById("123");
	}
}
