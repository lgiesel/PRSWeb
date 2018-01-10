package prs.domain.status;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface StatusRepository extends CrudRepository<Status, Integer> {
	
	Status findStatusByDescription(String statusDesc);

}
