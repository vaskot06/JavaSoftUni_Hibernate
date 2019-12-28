package tenev.jsonexercise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tenev.jsonexercise.domain.entity.User;
import tenev.jsonexercise.domain.view.UserView;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findAllByProductsSoldNotNullOrderByLastNameDescFirstNameDesc();

}
