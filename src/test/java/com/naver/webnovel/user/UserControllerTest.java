package com.naver.webnovel.user;

import static com.naver.webnovel.RestDocsConfiguration.field;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.naver.webnovel.TestSupport;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;


class UserControllerTest extends TestSupport {
    private static String TEST_JSON_DIR = "/test-json/user/";

    @Test
    void createUserTest() throws Exception {
        mockMvc.perform(
                        post("/api/users")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(readJson(TEST_JSON_DIR + "create-user.json"))
                ).andDo(x -> System.out.println(x.getResponse().getContentAsString()))
                .andDo(
                        restDocs.document(
                                requestFields(
                                        fieldWithPath("id").description("id").attributes(field("length", "10")),
                                        fieldWithPath("password").description("password")

                                                .attributes(field("length", "10")),
                                        fieldWithPath("nickname").description("nickname") .attributes(field("length", "10")),
                                        fieldWithPath("name").description("name") .attributes(field("length", "10")),
                                        fieldWithPath("phone").description("phone").attributes(field("length", "10")),
                                        fieldWithPath("birthDate").description("birthDate")
                                                .attributes(field("length", "10")),
                                        fieldWithPath("gender").description("gender").attributes(field("length", "10"))
                                )
                        )
                );
    }

}