package com.naver.webnovel.user;

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
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.http.MediaType;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserControllerTest extends TestSupport {
    private static String TEST_JSON_DIR = "/test-json/user/";
    private static String BASE_URL = "/api/users";
    private static String ACCESS_TOKEN;
    private static Long USER_IDX;

    @Test
    @Order(1)
    void createUserTest() throws Exception {
        mockMvc.perform(
                        post(BASE_URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(readJson(TEST_JSON_DIR + "create-user.json")))
                .andExpect(status().isCreated())
                .andDo(
                        restDocs.document(
                                requestFields(
                                        fieldWithPath("id").description("아이디(숫자, 영문자 소문자, 8 ~ 12자리)"),
                                        fieldWithPath("password").description("SHA256으로 암호화한 비밀번호"),
                                        fieldWithPath("nickname").description("닉네임(숫자, 영문자 소문자, _, 4~ 10자리)"),
                                        fieldWithPath("name").description("이름"),
                                        fieldWithPath("phone").description("휴대폰 번호(숫자만 11 ~ 15 자리)"),
                                        fieldWithPath("birthDate").description("생년월일(yyyy-MM-dd)"),
                                        fieldWithPath("gender").description("성별(MALE|FEMALE)")
                                ),
                                responseFields(
                                        fieldWithPath("idx").description("식별자"),
                                        fieldWithPath("id").description("아이디(숫자, 영문자 소문자, 8 ~ 12자리)"),
                                        fieldWithPath("nickname").description("닉네임(숫자, 영문자 소문자, _, 4~ 10자리)"),
                                        fieldWithPath("name").description("이름"),
                                        fieldWithPath("phone").description("휴대폰 번호(숫자만 11 ~ 15 자리)"),
                                        fieldWithPath("birthDate").description("생년월일(yyyy-MM-dd)"),
                                        fieldWithPath("gender").description("성별(MALE|FEMALE)"),
                                        fieldWithPath("cash").description("보유한 캐시(default 0)"),
                                        fieldWithPath("keepTicket").description("보유한 소장권 수(default 0)"),
                                        fieldWithPath("createdAt").description("생성일시"),
                                        fieldWithPath("status").description("상태(default ACTIVATED)")
                                )
                        )
                );
    }

    @Test
    @Order(3)
    void retrieveUserTest() throws Exception {
        mockMvc.perform(
                        get(BASE_URL + "/{userIdx}", USER_IDX)
                                .header(JwtKey.COOKIE_NAME, ACCESS_TOKEN))
                .andExpect(status().isOk())
                .andDo(
                        restDocs.document(
                                requestHeaders(
                                        headerWithName(JwtKey.COOKIE_NAME).description("인증을 위한 유저 액세스 토큰")
                                ),
                                pathParameters(
                                        parameterWithName("userIdx").description("유저 식별자")
                                ),
                                responseFields(
                                        fieldWithPath("idx").description("식별자"),
                                        fieldWithPath("id").description("아이디(숫자, 영문자 소문자, 8 ~ 12자리)"),
                                        fieldWithPath("nickname").description("닉네임(숫자, 영문자 소문자, _, 4~ 10자리)"),
                                        fieldWithPath("name").description("이름"),
                                        fieldWithPath("phone").description("휴대폰 번호(숫자만 11 ~ 15 자리)"),
                                        fieldWithPath("birthDate").description("생년월일(yyyy-MM-dd)"),
                                        fieldWithPath("gender").description("성별(MALE|FEMALE)"),
                                        fieldWithPath("authorIdx").optional().description("작가 식별자(작가 권한 있는 경우)"),
                                        fieldWithPath("cash").description("보유한 캐시(default 0)"),
                                        fieldWithPath("keepTicket").description("보유한 소장권 수(default 0)"),
                                        fieldWithPath("status").description("상태(default ACTIVATED)")
                                )
                        )
                );
    }

    @Test
    @Order(2)
    void loginTest() throws Exception {
        mockMvc.perform(
                        post(BASE_URL + "/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(readJson(TEST_JSON_DIR + "login-user.json")))
                .andExpect(status().isOk())
                .andDo(result -> {
                    String string_json = result.getResponse().getContentAsString();
                    USER_IDX = JsonPath.parse(string_json).read("$.userIdx", Long.class);
                    ACCESS_TOKEN = JsonPath.parse(string_json).read("$.accessToken", String.class);
                })
                .andDo(
                        restDocs.document(
                                requestFields(
                                        fieldWithPath("id").description("아이디(숫자, 영문자 소문자, 8 ~ 12자리)"),
                                        fieldWithPath("password").description("SHA256으로 암호화한 비밀번호")
                                ),
                                responseFields(
                                        fieldWithPath("userIdx").description("유저 식별자"),
                                        fieldWithPath("userAccessToken").description("유저 인증을 위한 액세스 토큰")
                                )
                        )
                );
    }
}