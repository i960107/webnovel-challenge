package com.naver.webnovel;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.snippet.Attributes.Attribute;

@TestConfiguration
public class RestDocsConfiguration {

    @Bean
    public RestDocumentationResultHandler write() {
        return MockMvcRestDocumentation.document(
                "{class-name}/{method_name}"
//                Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
//                Preprocessors.preprocessResponse(Preprocessors.prettyPrint())
        );
    }

    public static final Attribute field(
            final String key,
            final String value
    ) {
        return new Attribute(key, value);
    }
}
