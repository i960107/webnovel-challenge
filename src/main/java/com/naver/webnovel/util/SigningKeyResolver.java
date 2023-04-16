package com.naver.webnovel.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.SigningKeyResolverAdapter;
import java.security.Key;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SigningKeyResolver extends SigningKeyResolverAdapter {
    private final JwtKey jwtKey;

    @Override
    public Key resolveSigningKey(JwsHeader jwsHeader, Claims claims) {
        String kid = jwsHeader.getKeyId();
        if (kid == null) {
            return null;
        }
        return jwtKey.getKey(kid);
    }
}
