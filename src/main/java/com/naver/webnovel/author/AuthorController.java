package com.naver.webnovel.author;

import com.naver.webnovel.author.dto.AuthorCreateRequest;
import com.naver.webnovel.author.dto.AuthorCreateResponse;
import com.naver.webnovel.author.dto.AuthorLoginRequest;
import com.naver.webnovel.author.dto.AuthorLoginResponse;
import com.naver.webnovel.author.dto.RetrieveAuthorResponse;
import com.naver.webnovel.base.authorization.AuthorOnly;
import com.naver.webnovel.base.authorization.UserOnly;
import com.naver.webnovel.base.exception.BaseException;
import java.net.URI;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/authors")
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorProvider authorProvider;
    private final AuthorService authorService;

    @GetMapping("/{authorIdx}")
    @AuthorOnly
    public ResponseEntity<RetrieveAuthorResponse> retrieveAuthor(@PathVariable Long authorIdx) throws BaseException {
        RetrieveAuthorResponse responseDto = authorService.retrieveAuthor(authorIdx);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PostMapping("/login")
    @UserOnly
    public ResponseEntity<AuthorLoginResponse> login(@Valid @RequestBody AuthorLoginRequest requestDto)
            throws BaseException {
        AuthorLoginResponse responseDto = authorProvider.login(requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PostMapping
    @UserOnly
    public ResponseEntity<AuthorCreateResponse> createAuthor(@Valid @RequestBody AuthorCreateRequest requestDto)
            throws BaseException {
        AuthorCreateResponse responseDto = authorService.createAuthor(requestDto);

        HttpHeaders header = new HttpHeaders();

        URI uri = UriComponentsBuilder.fromUriString("/authors" + responseDto.getAuthorIdx()).build().toUri();
        header.setLocation(uri);

        return new ResponseEntity<>(responseDto, header, HttpStatus.CREATED);
    }
}
