package com.naver.webnovel.author;

import com.naver.webnovel.author.dto.AuthorLoginRequest;
import com.naver.webnovel.author.dto.AuthorLoginResponse;
import com.naver.webnovel.base.Status;
import com.naver.webnovel.base.authorization.Role;
import com.naver.webnovel.base.exception.BaseException;
import com.naver.webnovel.base.exception.BaseResponseStatus;
import com.naver.webnovel.user.UserRepository;
import com.naver.webnovel.util.JwtService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthorProvider {
    private final AuthorRepository authorRepository;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    public AuthorLoginResponse login(AuthorLoginRequest requestDto) throws BaseException {
        Optional<Long> authorIdx = userRepository.findIdxByIdxAndAuthorIdxAndStatus(
                requestDto.getAuthorIdx()
                , requestDto.getUserIdx()
                , Status.ACTIVATED);

        if (authorIdx.isEmpty()) {
            throw new BaseException(BaseResponseStatus.INVALID_AUTHOR);
        }

        String accessToken = jwtService.createAccessToken(authorIdx.get(), Role.AUTHOR);
        return new AuthorLoginResponse(authorIdx.get(), accessToken);
    }
}
