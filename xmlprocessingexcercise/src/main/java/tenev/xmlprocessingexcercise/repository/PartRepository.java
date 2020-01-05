package tenev.xmlprocessingexcercise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tenev.xmlprocessingexcercise.domain.entity.Part;

import java.util.List;

@Repository
public interface PartRepository extends JpaRepository<Part, Integer> {
    List<Part> findAllById(Integer id);
}
