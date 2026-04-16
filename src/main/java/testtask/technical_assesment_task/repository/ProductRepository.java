package testtask.technical_assesment_task.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import testtask.technical_assesment_task.entity.Product;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @EntityGraph(attributePaths = {"producer", "attributes"})
    List<Product> findAll();

    @EntityGraph(attributePaths = {"producer", "attributes"})
    Optional<Product> findById(Long id);
}