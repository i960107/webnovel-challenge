package com.naver.webnovel.user;

import com.naver.webnovel.base.Status;
import com.naver.webnovel.base.exception.BaseException;
import com.naver.webnovel.base.exception.BaseResponseStatus;
import com.naver.webnovel.user.dto.UserCreateRequest;
import com.naver.webnovel.user.dto.UserCreateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserCreateResponse createUser(UserCreateRequest requestDto) throws BaseException {
        if (userRepository.existsByIdAndStatus(requestDto.getId(), Status.ACTIVATED)) {
            throw new BaseException(BaseResponseStatus.DUPLICATE_ID);
        }

        if (userRepository.existsByPhoneAndStatus(requestDto.getPhone(), Status.ACTIVATED)) {
            throw new BaseException(BaseResponseStatus.DUPLICATE_PHONE);
        }

        User user = userRepository.save(requestDto.toEntity());

        return UserCreateResponse.fromEntity(user);
    }
}
