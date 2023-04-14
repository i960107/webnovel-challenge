package com.naver.webnovel.user;

import com.naver.webnovel.user.dto.UserCreateRequest;
import com.naver.webnovel.user.dto.UserCreateResponse;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserProvider userProvider;

    @PostMapping
    public ResponseEntity<UserCreateResponse> createUser(@RequestBody UserCreateRequest requestDto) {
        UserCreateResponse responseDto = userService.createUser(requestDto);

        HttpHeaders header = new HttpHeaders();

        URI uri = UriComponentsBuilder.fromUriString("/users" + responseDto.getIdx()).build().toUri();
        header.setLocation(uri);

        return new ResponseEntity<UserCreateResponse>(responseDto, header, HttpStatus.CREATED);
    }
}
