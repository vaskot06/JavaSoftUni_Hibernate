package tenev.accountsys.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tenev.accountsys.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User getUserByUsername(String username);

}
