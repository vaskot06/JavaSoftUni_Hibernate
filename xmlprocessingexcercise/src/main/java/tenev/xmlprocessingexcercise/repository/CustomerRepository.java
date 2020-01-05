package tenev.xmlprocessingexcercise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tenev.xmlprocessingexcercise.domain.entity.Customer;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    @Query(value = "select c from Customer as c order by c.dateOfBirth asc, c.youngDriver desc")
    List<Customer> findAll();

}
