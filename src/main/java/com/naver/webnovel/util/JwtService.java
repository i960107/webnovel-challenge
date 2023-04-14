package com.naver.webnovel.util;

import com.naver.webnovel.base.authorization.Role;
import com.naver.webnovel.base.exception.BaseException;
import com.naver.webnovel.base.exception.BaseResponseStatus;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

    public String createAccessToken(Long idx, Role role) throws BaseException {
        Date now = new Date();
        Pair<String, Key> key = JwtKey.getRandomKey();
        return Jwts.builder()
                .setHeaderParam(JwsHeader.TYPE, "jwt")
                .setHeaderParam(JwsHeader.ALGORITHM, "HS256")
                .setHeaderParam(JwsHeader.KEY_ID, key.getFirst())
                .claim("idx", idx)
                .claim("role", role.name())
                .setIssuedAt(now)
                .setExpiration(new Date(System.currentTimeMillis() + JwtKey.EXPIRATION_TIME))
                .signWith(key.getSecond(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String getToken(HttpServletRequest request) {
        return request.getHeader(JwtKey.COOKIE_NAME);
    }

    public Map<String, Object> getUserInfo(String accessToken) throws BaseException {
        if (isTokenEmpty(accessToken)) {
            throw new BaseException(BaseResponseStatus.EMPTY_TOKEN);
        }
        Jws<Claims> claims = getClaimsFromJwtToken(accessToken);

        if (isTokenExpired(claims)) {
            throw new BaseException(BaseResponseStatus.EXPIRED_TOKEN);
        }
        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("idx", claims.getBody().get("idx", Long.class));
        userInfo.put("role", claims.getBody().get("role", Role.class));
        return userInfo;
    }

    private Jws<Claims> getClaimsFromJwtToken(String accessToken) throws BaseException {
        Jws<Claims> claims;
        try {
            claims = Jwts.parserBuilder()
                    .setSigningKeyResolver(SigningKeyResolver.instance)
                    .build().parseClaimsJws(accessToken);
        } catch (Exception e) {
            throw new BaseException(BaseResponseStatus.INVALID_TOKEN);
        }
        return claims;
    }


    private boolean isTokenExpired(Jws<Claims> claims) {
        return claims.getBody().getExpiration().before(new Date());
    }

    private boolean isTokenEmpty(String accessToken) {
        return accessToken == null || accessToken.length() == 0;
    }
}
