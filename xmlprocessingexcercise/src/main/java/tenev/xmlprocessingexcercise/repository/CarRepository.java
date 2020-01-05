package tenev.xmlprocessingexcercise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tenev.xmlprocessingexcercise.domain.entity.Car;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {
    List<Car> findAllByMakeEqualsOrderByModelAscTravelledKmDesc(String make);
}
