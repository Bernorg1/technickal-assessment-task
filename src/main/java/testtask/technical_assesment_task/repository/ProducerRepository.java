package testtask.technical_assesment_task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import testtask.technical_assesment_task.entity.Producer;

@Repository
public interface ProducerRepository extends JpaRepository<Producer, Long> {
}

