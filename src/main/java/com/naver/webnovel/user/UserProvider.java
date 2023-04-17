package com.naver.webnovel.user;

import com.naver.webnovel.base.Status;
import com.naver.webnovel.base.authorization.Role;
import com.naver.webnovel.base.exception.BaseException;
import com.naver.webnovel.base.exception.BaseResponseStatus;
import com.naver.webnovel.user.dto.UserLoginRequest;
import com.naver.webnovel.user.dto.UserLoginResponse;
import com.naver.webnovel.util.JwtService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserProvider {
    private final UserRepository userRepository;
    private final JwtService jwtService;

    public UserRetrieveResponse retrieveUser(Long idx) throws BaseException {
        Optional<User> user = userRepository.findByIdxAndStatus(idx, Status.ACTIVATED);
        if (user.isEmpty()) {
            throw new BaseException(BaseResponseStatus.INVALID_USER);
        }
        return UserRetrieveResponse.fromEntity(user.get());
    }

    public UserLoginResponse login(UserLoginRequest requestDto) throws BaseException {
        Optional<Long> idx = userRepository.findIdxByIdAndPasswordAndStatus(requestDto.getId(),
                requestDto.getPassword(), Status.ACTIVATED);

        if (idx.isEmpty()) {
            throw new BaseException(BaseResponseStatus.INVALID_USER);
        }

        String accessToken = jwtService.createAccessToken(idx.get(), Role.USER);
        return new UserLoginResponse(idx.get(), accessToken);
    }
}
