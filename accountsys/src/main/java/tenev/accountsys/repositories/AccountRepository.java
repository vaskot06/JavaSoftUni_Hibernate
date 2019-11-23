package tenev.accountsys.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tenev.accountsys.entities.Account;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {
//
//    Account getAccountByOwner(String owner);
//
//    Account getAccount();
//
//    Account getAccountById();

}
