package se.jensen.erik.cloudstoreproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.jensen.erik.cloudstoreproject.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
