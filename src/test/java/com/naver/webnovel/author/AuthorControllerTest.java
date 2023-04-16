package com.naver.webnovel.author;

import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.jayway.jsonpath.JsonPath;
import com.naver.webnovel.TestSupport;
import com.naver.webnovel.util.JwtKey;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.http.MediaType;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AuthorControllerTest extends TestSupport {
    private static String TEST_JSON_DIR = "/test-json/author/";
    private static String BASE_URL = "/api/authors";
    private static Long AUTHOR_IDX;
    private static Long USER_IDX;
    private static String AUTHOR_ACCESS_TOKEN;
    private static String USER_ACCESS_TOKEN;

    void userLogin(String filePath) throws Exception {
        mockMvc.perform(post("/api/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(readJson(filePath)))
                .andExpect(status().isOk())
                .andDo(result -> {
                    String string_json = result.getResponse().getContentAsString();
                    USER_IDX = JsonPath.parse(string_json).read("$.userIdx", Long.class);
                    USER_ACCESS_TOKEN = JsonPath.parse(string_json).read("$.userAccessToken", String.class);
                });
    }

    @Test
    void createAuthorTest() throws Exception {
        userLogin("/test-json/user/login-user-not-author.json");
        mockMvc.perform(
                        post(BASE_URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(readJson(TEST_JSON_DIR + "create-author.json"))
                                .header(JwtKey.COOKIE_NAME, USER_ACCESS_TOKEN))
                .andExpect(status().isCreated())
                .andDo(
                        restDocs.document(
                                requestHeaders(
                                        headerWithName(JwtKey.COOKIE_NAME).description("유저 인증을 위한 액세스 토큰")
                                ),
                                requestFields(
                                        fieldWithPath("userIdx").description("유저 식별자"),
                                        fieldWithPath("penname").description("필명")
                                ),
                                responseFields(
                                        fieldWithPath("authorIdx").description("작가 식별자"),
                                        fieldWithPath("userIdx").description("유저 식별자"),
                                        fieldWithPath("penname").description("필명"),
                                        fieldWithPath("createdAt").description("생성 일시"),
                                        fieldWithPath("status").description("상태(default ACTIVATED)")
                                )
                        )
                );
    }

    @Test
    void retrieveAuthorTest() throws Exception {

        userLogin("/test-json/user/login-user.json");

        authorLoginTest();

        mockMvc.perform(
                        get(BASE_URL + "/{authorIdx}", AUTHOR_IDX)
                                .header(JwtKey.COOKIE_NAME, AUTHOR_ACCESS_TOKEN))
                .andExpect(status().isOk())
                .andDo(
                        restDocs.document(
                                requestHeaders(
                                        headerWithName(JwtKey.COOKIE_NAME).description("작가 인증을 위한 액세스 토큰")
                                ),
                                pathParameters(
                                        parameterWithName("authorIdx").description("작가 식별자")
                                ),
                                responseFields(
                                        fieldWithPath("authorIdx").description("작가 식별자"),
                                        fieldWithPath("userIdx").description("유저 식별자"),
                                        fieldWithPath("penname").description("필명"),
                                        fieldWithPath("status").description("상태(default ACTIVATED)")
                                )
                        )
                );
    }

    @Test
    void authorLoginTest() throws Exception {
        userLogin("/test-json/user/login-user.json");

        mockMvc.perform(
                        post(BASE_URL + "/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(readJson(TEST_JSON_DIR + "login-author.json"))
                                .header(JwtKey.COOKIE_NAME, USER_ACCESS_TOKEN))
                .andExpect(status().isOk())
                .andDo(result -> {
                    String string_json = result.getResponse().getContentAsString();
                    AUTHOR_IDX = JsonPath.parse(string_json).read("$.authorIdx", Long.class);
                    AUTHOR_ACCESS_TOKEN = JsonPath.parse(string_json).read("$.authorAccessToken", String.class);
                })
                .andDo(
                        restDocs.document(
                                requestHeaders(
                                        headerWithName(JwtKey.COOKIE_NAME).description("유저 인증을 위한 액세스 토큰")
                                ),
                                requestFields(
                                        fieldWithPath("userIdx").description("유저 식별자"),
                                        fieldWithPath("authorIdx").description("작가 식별자")
                                ),
                                responseFields(
                                        fieldWithPath("authorIdx").description("작가 식별자"),
                                        fieldWithPath("authorAccessToken").description("작가 인증을 위한 액세스 토큰")
                                )
                        )
                );
    }


}