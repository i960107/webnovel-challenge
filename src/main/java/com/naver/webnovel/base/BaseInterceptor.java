package com.naver.webnovel.base;

import com.naver.webnovel.base.authorization.AuthorOnly;
import com.naver.webnovel.base.authorization.Role;
import com.naver.webnovel.base.authorization.UserOnly;
import com.naver.webnovel.base.exception.BaseException;
import com.naver.webnovel.base.exception.BaseResponseStatus;
import com.naver.webnovel.util.JwtService;
import java.lang.reflect.Method;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;

@Component
@RequiredArgsConstructor
public class BaseInterceptor implements HandlerInterceptor {
    private final JwtService jwtService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        if (method.getAnnotation(AuthorOnly.class) != null) {
            checkAuthorization(request, Role.AUTHOR);
        } else if (method.getAnnotation(UserOnly.class) != null) {
            checkAuthorization(request, Role.USER);
        }
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    private void checkAuthorization(HttpServletRequest request, Role expectedRole) throws BaseException {
        String accessToken = jwtService.getToken(request);
        Map<String, Object> info = jwtService.getUserInfo(accessToken);
        if (info.get("role") != expectedRole) {
            throw new BaseException(BaseResponseStatus.UNAUTHORIZED);
        }
        String pathVariableIdx = ((Map) request.getAttribute(
                HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE)).get("idx").toString();

        if (info.get("idx") != pathVariableIdx) {
            throw new BaseException(BaseResponseStatus.BAD_REQUEST);
        }

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
