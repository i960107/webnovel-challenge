package com.naver.webnovel.util;

import com.naver.webnovel.base.exception.BaseException;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.util.Pair;

public class JwtKey {
    public static final int EXPIRATION_TIME = 1000 * 10 * 60;
    public static final String COOKIE_NAME = "JWT_AUTHENTICATION";
    @Autowired
    private static Environment env;
    private static final Map<String, Key> SECRET_KEY_SET;
    private static final String[] KID_SET;
    private static final Random random;

    static {
        SECRET_KEY_SET = new HashMap<>();
        List<Map<String, String>> properties = env.getProperty("security.secret.jwt.keys", List.class);
        for (Map<String, String> map : properties) {
            String keyId = map.get("keyId");
            Key secretKey = Keys.hmacShaKeyFor(map.get("secretKey").getBytes(StandardCharsets.UTF_8));
            SECRET_KEY_SET.put(keyId, secretKey);
        }
        KID_SET = SECRET_KEY_SET.keySet().toArray(new String[0]);
        random = new Random();
    }

    public static Key getKey(String kid) {
        Key key = SECRET_KEY_SET.getOrDefault(kid, null);
        return key;
    }

    public static Pair<String, Key> getRandomKey() throws BaseException {
        String keyId = KID_SET[random.nextInt(KID_SET.length)];
        return Pair.of(keyId, getKey(keyId));
    }
}
