package tenev.accountsys.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import tenev.accountsys.repositories.AccountRepository;
import tenev.accountsys.services.utils.AccountService;

import java.math.BigDecimal;

@Service
@Primary
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void withdrawMoney(BigDecimal money, Long id) {

        if (this.accountRepository.findById(id).get().getId() == id
                && this.accountRepository.findById(id).get().getUser().getUsername() != null
                && this.accountRepository.findById(id).get().getBalance().longValue() > 0
                && this.accountRepository.findById(id).get().getBalance().longValue() > money.longValue()) {

            this.accountRepository.findById(id).get()
                    .setBalance(this.accountRepository.findById(id).get().getBalance().subtract(money));

        }

    }

    @Override
    public void transferMoney(BigDecimal money, Long id) {

        if (this.accountRepository.findById(id).get().getUser().getId() == id
        && money.longValue() > 0){

            this.accountRepository.findById(id).get()
                    .setBalance(this.accountRepository.findById(id).get().getBalance().add(money));

        }

    }
}
