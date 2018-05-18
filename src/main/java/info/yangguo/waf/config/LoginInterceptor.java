package info.yangguo.waf.config;

import info.yangguo.waf.exception.UnauthorizedException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object object) throws UnauthorizedException {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("WAFTOKEN".equals(cookie.getName()) && ContextHolder.getSessions().containsKey(cookie.getValue())) {
                    return true;
                }
            }
        }
        throw new UnauthorizedException();
    }
}