package se.jensen.erik.cloudstoreproject.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;
import se.jensen.erik.cloudstoreproject.product.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
