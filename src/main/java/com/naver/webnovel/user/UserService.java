package com.naver.webnovel.user;

import com.naver.webnovel.user.dto.UserCreateRequest;
import com.naver.webnovel.user.dto.UserCreateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserCreateResponse createUser(UserCreateRequest dto) {
        userRepository.save();
    }
}
