package pl.poznan.put.cs.net.testexample.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import pl.poznan.put.cs.net.testexample.model.DistributionService;

public interface DistributionServiceRepository 
	extends CrudRepository<DistributionService, String> {
	Optional<DistributionService> findByTitle(String title);
	
	@Override
	List<DistributionService> findAll();
}
