package com.hewen.Interceptor;

import com.hewen.entity.component.RedisUtils;
import com.hewen.entity.po.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Component
public class RememberMeInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 尝试从请求中获取REMEMBERME的Cookie
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("REMEMBERME".equals(cookie.getName())) {
                    String rememberToken = cookie.getValue();
                    UserInfo userInfo = (UserInfo) redisUtils.get("rememberMe:" + rememberToken);
                    if (userInfo != null) {
                        // 用户存在，自动登录逻辑
                        // 例如，将用户信息设置到会话中
                        String sessionId = UUID.randomUUID().toString();
                        redisUtils.setex(sessionId, userInfo, 30 * 60); // 30分钟
                        Cookie sessionCookie = new Cookie("sessionId", sessionId);
                        response.addCookie(sessionCookie);
                        return true;
                    }
                }
            }
        }
        return true;
    }
}
