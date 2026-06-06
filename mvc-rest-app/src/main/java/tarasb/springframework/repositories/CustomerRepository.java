package tarasb.springframework.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tarasb.springframework.domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
