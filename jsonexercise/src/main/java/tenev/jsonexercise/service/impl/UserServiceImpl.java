package tenev.jsonexercise.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tenev.jsonexercise.domain.dto.UserSeedDto;
import tenev.jsonexercise.domain.entity.Category;
import tenev.jsonexercise.domain.entity.User;
import tenev.jsonexercise.repository.UserRepository;
import tenev.jsonexercise.service.UserService;
import tenev.jsonexercise.util.ValidatorUtil;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {


    private final ValidatorUtil validatorUtil;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(ValidatorUtil validatorUtil, ModelMapper modelMapper, UserRepository userRepository) {
        this.validatorUtil = validatorUtil;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    @Override
    public void seedUsers(UserSeedDto[] userSeedDto) {

        for (UserSeedDto seedDto : userSeedDto) {

            boolean userFound = false;
            boolean userNull = false;

            if (this.userRepository.count() > 3) {
                Set<User> friends = seedDto.getFriends();

                for (int i = 0; i < 3; i++) {

                    if (friends == null) {
                        friends = new HashSet<>();
                    }
                    User friend = randomUser();

                    if (friend == null) {
                        userNull = true;
                    }

                    if (!friends.isEmpty()) {
                        for (User userSet : friends) {
                            if (!userNull){
                                if (userSet.getLastName().equals(friend.getLastName())) {
                                    userFound= true;
                                    break;
                                }
                            }
                        }
                    }

                    if (!userFound && !userNull){
                        friends.add(friend);
                    }
                }
                seedDto.setFriends(friends);
            }

            if (!this.validatorUtil.isValid(seedDto)) {
                this.validatorUtil.violations(seedDto).forEach(v -> System.out.println(v.getMessage()));
                continue;
            }

            User user = this.modelMapper.map(seedDto, User.class);
            this.userRepository.saveAndFlush(user);
        }
    }

    public User randomUser() {
        Random random = new Random();

        int id = random.nextInt((int) (this.userRepository.count() - 1) + 1);
        User user = this.userRepository.findById(id).orElse(null);
        return user;
    }
}
