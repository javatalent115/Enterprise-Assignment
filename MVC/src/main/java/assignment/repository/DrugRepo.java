package assignment.repository;

import assignment.entity.Drug;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DrugRepo extends JpaRepository<Drug, String>{
}
