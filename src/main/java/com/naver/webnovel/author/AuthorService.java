package com.naver.webnovel.author;

import com.naver.webnovel.author.dto.AuthorCreateRequest;
import com.naver.webnovel.author.dto.AuthorCreateResponse;
import com.naver.webnovel.author.dto.RetrieveAuthorResponse;
import com.naver.webnovel.base.Status;
import com.naver.webnovel.base.exception.BaseException;
import com.naver.webnovel.base.exception.BaseResponseStatus;
import com.naver.webnovel.user.User;
import com.naver.webnovel.user.UserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthorService {
    private final AuthorRepository authorRepository;
    private final UserRepository userRepository;

    public RetrieveAuthorResponse retrieveAuthor(Long authorIdx) throws BaseException {
        Optional<Author> author = authorRepository.findByIdxAndStatus(authorIdx, Status.ACTIVATED);
        if (author.isEmpty()) {
            throw new BaseException(BaseResponseStatus.INVALID_AUTHOR);
        }
        return RetrieveAuthorResponse.fromEntity(author.get());
    }

    public AuthorCreateResponse createAuthor(AuthorCreateRequest requestDto) throws BaseException {
        Optional<User> user = userRepository.findByIdxAndStatus(requestDto.getUserIdx(), Status.ACTIVATED);
        if (user.isEmpty()) {
            throw new BaseException(BaseResponseStatus.INVALID_USER);
        }

        if (user.get().getAuthor() != null) {
            throw new BaseException(BaseResponseStatus.ALREADY_CREATED_AUTHOR);
        }

        Author author = requestDto.toEntity(user.get());
        authorRepository.save(author);
        return AuthorCreateResponse.fromEntity(author);
    }
}
