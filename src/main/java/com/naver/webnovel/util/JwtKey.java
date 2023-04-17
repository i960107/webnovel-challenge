package com.naver.webnovel.util;

import com.naver.webnovel.base.exception.BaseException;
import com.naver.webnovel.base.exception.BaseResponseStatus;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.annotation.PostConstruct;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

@Component
public class JwtKey implements EnvironmentAware {
    public static final int EXPIRATION_TIME = 1000 * 60 * 60 * 2;
    public static final String COOKIE_NAME = "JWT_AUTHENTICATION";
    private Environment env;
    private final Map<String, Key> SECRET_KEY_SET;
    private final List<String> KID_SET;
    private final Random random;

    public JwtKey(JwtKeyProperties keys) {
        SECRET_KEY_SET = new HashMap<>();
        KID_SET = new ArrayList<>();
        random = new Random();
    }

    @PostConstruct
    private void init() throws BaseException {
        String KEY_STORE = "security.secret.jwt.keys.";
        Integer count = env.getProperty(KEY_STORE + "key_count", Integer.class);
        if (count == null) {
            throw new BaseException(BaseResponseStatus.FAIL_TO_LOAD_JWT_KEY);
        }

        for (int i = 1; i <= count; i++) {
            String keyId = "key" + String.valueOf(i);
            Key secretKey = Keys.hmacShaKeyFor(env.getProperty(KEY_STORE + keyId).getBytes(StandardCharsets.UTF_8));
            SECRET_KEY_SET.put(keyId, secretKey);
        }
        KID_SET.addAll(SECRET_KEY_SET.keySet());
    }

    public Key getKey(String kid) {
        Key key = SECRET_KEY_SET.getOrDefault(kid, null);
        return key;
    }

    public Pair<String, Key> getRandomKey() throws BaseException {
        if (KID_SET.isEmpty()) {
            throw new BaseException(BaseResponseStatus.FAIL_TO_CREATE_ACCESS_TOKEN);
        }
        String keyId = KID_SET.get(random.nextInt(KID_SET.size()));
        return Pair.of(keyId, getKey(keyId));
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.env = environment;
    }
}
