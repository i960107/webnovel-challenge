package com.naver.webnovel.user;

import com.naver.webnovel.base.authorization.AuthorOnly;
import com.naver.webnovel.base.authorization.UserOnly;
import com.naver.webnovel.base.exception.BaseException;
import com.naver.webnovel.user.dto.UserCreateRequest;
import com.naver.webnovel.user.dto.UserCreateResponse;
import com.naver.webnovel.user.dto.UserLoginRequest;
import com.naver.webnovel.user.dto.UserLoginResponse;
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
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserProvider userProvider;

    @PostMapping
    public ResponseEntity<UserCreateResponse> createUser(@Valid @RequestBody UserCreateRequest requestDto)
            throws BaseException {
        UserCreateResponse responseDto = userService.createUser(requestDto);

        HttpHeaders header = new HttpHeaders();

        URI uri = UriComponentsBuilder.fromUriString("/users" + responseDto.getIdx()).build().toUri();
        header.setLocation(uri);

        return new ResponseEntity<>(responseDto, header, HttpStatus.CREATED);
    }

    @PostMapping("login")
    public ResponseEntity<UserLoginResponse> login(@Valid @RequestBody UserLoginRequest requestDto)
            throws BaseException {
        UserLoginResponse responseDto = userProvider.login(requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/{userIdx}")
    @UserOnly
    public ResponseEntity<UserRetrieveResponse> getUser(@PathVariable Long userIdx) throws BaseException {
        UserRetrieveResponse responseDto = userProvider.retrieveUser(userIdx);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

}
