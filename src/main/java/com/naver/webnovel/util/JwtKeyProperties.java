package com.naver.webnovel.util;

import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties("security.secret.jwt.keys")
public class JwtKeyProperties {
    private Map<String, String> keys;

    @Getter
    @Setter
    public static class KeyInfo {
        private String id;
        private String secretKey;
    }
}
