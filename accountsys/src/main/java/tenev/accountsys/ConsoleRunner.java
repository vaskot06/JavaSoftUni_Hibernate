package tenev.accountsys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import tenev.accountsys.entities.Account;
import tenev.accountsys.entities.User;
import tenev.accountsys.services.AccountServiceImpl;
import tenev.accountsys.services.UserServiceImpl;
import tenev.accountsys.services.utils.AccountService;
import tenev.accountsys.services.utils.UserService;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
@Component
public class ConsoleRunner implements CommandLineRunner {


    private UserServiceImpl userService;
    private AccountServiceImpl accountService;

    @Autowired
    public ConsoleRunner(UserServiceImpl userService, AccountServiceImpl accountService) {
        this.userService = userService;
        this.accountService = accountService;
    }

    @Override
    public void run(String... args) throws Exception {

        User example = new User();
        example.setUsername("Pesho");
        example.setAge(25);


        Account account = new Account();
        account.setBalance(BigDecimal.valueOf(25000));

        Set<Account> userAccounts = new HashSet<>();
        userAccounts.add(account);

        account.setUser(example);

        example.setAccount(userAccounts);

        userService.registerUser(example);


   //     accountService.withdrawMoney(BigDecimal.valueOf(20000), user.getId());


    }
}
