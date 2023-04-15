package com.naver.webnovel.user;

import com.naver.webnovel.base.exception.BaseException;
import com.naver.webnovel.user.dto.UserCreateRequest;
import com.naver.webnovel.user.dto.UserCreateResponse;
import java.net.URI;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping
    public ResponseEntity<UserCreateResponse> createUser(@Valid @RequestBody UserCreateRequest requestDto)
            throws BaseException {
        UserCreateResponse responseDto = userService.createUser(requestDto);

        HttpHeaders header = new HttpHeaders();

        URI uri = UriComponentsBuilder.fromUriString("/users" + responseDto.getIdx()).build().toUri();
        header.setLocation(uri);

        return new ResponseEntity<>(responseDto, header, HttpStatus.CREATED);
    }
}
