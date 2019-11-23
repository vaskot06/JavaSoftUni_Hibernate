package tenev.accountsys.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import tenev.accountsys.entities.User;
import tenev.accountsys.repositories.UserRepository;
import tenev.accountsys.services.utils.UserService;


@Service
@Primary
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void registerUser(User user) {

        if (this.userRepository.existsById(user.getId())){
            throw new IllegalArgumentException("This user already exists");
        }else {
            this.userRepository.saveAndFlush(user);
        }
    }
}
