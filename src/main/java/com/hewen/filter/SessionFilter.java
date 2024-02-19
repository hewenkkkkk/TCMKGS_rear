package com.hewen.filter;

import com.hewen.entity.component.RedisUtils;
import com.hewen.entity.constants.Constants;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class SessionFilter extends OncePerRequestFilter {

    @Resource
    private RedisUtils redisUtils;

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // 尝试从请求中获取SESSIONID的Cookie
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("SESSIONID".equals(cookie.getName())) {
                    // 延长会话在Redis中的过期时间
                    redisUtils.extendSessionExpiration(cookie.getValue(), Constants.TIMEOUT_MINUTES);
                }
            }
        }

        // 继续过滤器链
        filterChain.doFilter(request, response);
    }
}
