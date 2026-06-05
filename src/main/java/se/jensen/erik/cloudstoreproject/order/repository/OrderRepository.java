package se.jensen.erik.cloudstoreproject.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.jensen.erik.cloudstoreproject.order.model.CustomerOrder;

import java.util.List;

public interface OrderRepository extends JpaRepository <CustomerOrder, Long> {

    List<CustomerOrder> findByUserEmail(String userEmail);
}
