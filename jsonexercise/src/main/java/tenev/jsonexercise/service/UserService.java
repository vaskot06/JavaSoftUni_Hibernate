package tenev.jsonexercise.service;

import tenev.jsonexercise.domain.dto.UserSeedDto;

public interface UserService {
    void seedUsers(UserSeedDto[] userSeedDto);
}
