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

import pl.poznan.put.cs.net.testexample.model.DistributionService;
import pl.poznan.put.cs.net.testexample.repository.DistributionServiceRepository;

@RestController
@RequestMapping("/api/distribution-services")
public class DistributionServiceController {
	
	private final DistributionServiceRepository distributionServiceRepository;
	
	@Autowired
	public DistributionServiceController(
			DistributionServiceRepository distributionServiceRepository) {
		this.distributionServiceRepository = distributionServiceRepository;
	}
	
	@GetMapping
	public ResponseEntity<List<DistributionService>> getAllServices() {
		return ResponseEntity.ok(distributionServiceRepository.findAll());
	}
	
	@PostMapping
	public ResponseEntity<?> createService(@RequestBody @Valid DistributionService distributionService) {
		DistributionService created = distributionServiceRepository.save(distributionService);
		return ResponseEntity
				.created(MvcUriComponentsBuilder.fromController(getClass())
						.pathSegment(created.getId()).build().toUri()).build();
	}
}
