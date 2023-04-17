package com.naver.webnovel.novel.novel;

import com.naver.webnovel.base.exception.BaseException;
import com.naver.webnovel.novel.novel.dto.CreateNovelRequest;
import com.naver.webnovel.novel.novel.dto.CreateNovelResponse;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/novel")
@RequiredArgsConstructor
public class NovelController {
    private final NovelProvider novelProvider;
    private final NovelService novelService;

    @PostMapping
    public ResponseEntity<CreateNovelResponse> createNovel(HttpServletRequest request,
                                                           @Valid @RequestBody CreateNovelRequest requestDto)
            throws BaseException {
        Long requestAuthorIdx = (Long) request.getAttribute("idx");
        CreateNovelResponse responseDto = novelService.createNovel(requestAuthorIdx, requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping("/{novelIdx}")
    public ResponseEntity<RetrieveNovelResponse> retrieveNovel(@PathVariable Long novelIdx) {
            throws BaseException {
            RetrieveNovelResponse responseDto = novelProvider.retrieveNovel(novelIdx);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        }
    }
