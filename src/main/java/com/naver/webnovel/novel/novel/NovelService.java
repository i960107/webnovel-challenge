package com.naver.webnovel.novel.novel;

import com.naver.webnovel.author.Author;
import com.naver.webnovel.author.AuthorRepository;
import com.naver.webnovel.base.Status;
import com.naver.webnovel.base.exception.BaseException;
import com.naver.webnovel.base.exception.BaseResponseStatus;
import com.naver.webnovel.novel.novel.dto.CreateNovelRequest;
import com.naver.webnovel.novel.novel.dto.CreateNovelResponse;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class NovelService {
    private final NovelRepository novelRepository;
    private final AuthorRepository authorRepository;

    public CreateNovelResponse createNovel(Long requestAuthorIdx, CreateNovelRequest requestDto) throws BaseException {
        if (requestAuthorIdx != requestDto.getAuthorIdx()) {
            throw new BaseException(BaseResponseStatus.UNAUTHORIZED);
        }

        Optional<Author> author = authorRepository.findByIdxAndStatus(requestDto.getAuthorIdx(), Status.ACTIVATED);
        if (author.isEmpty()) {
            throw new BaseException(BaseResponseStatus.INVALID_AUTHOR);
        }

        Novel novel = requestDto.toEntity(author.get());
        novelRepository.save(novel);
        return CreateNovelResponse.fromEntity(novel);
    }

}
