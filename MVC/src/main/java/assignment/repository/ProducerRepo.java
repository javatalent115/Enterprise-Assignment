package assignment.repository;

import assignment.entity.Producer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProducerRepo extends JpaRepository<Producer, String>{
}
