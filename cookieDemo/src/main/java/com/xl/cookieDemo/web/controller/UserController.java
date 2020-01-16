package com.xl.cookieDemo.web.controller;

import com.xl.cookieDemo.entity.User;
import com.xl.cookieDemo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api")
@Slf4j
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable Long id) {
        log.info("process=get-user, user_id={}", id);
        User user = userService.getUserById(id);
        return user;
    }

    @GetMapping("/cookie/set")
    public String setCookie(HttpServletResponse response) {
        Cookie cookie = new Cookie("sessionId","CookieTestInfo");
        response.addCookie(cookie);
        return "添加cookies信息成功";
    }

    @GetMapping("/cookie/get1")
    public String getCookie1(HttpServletRequest request) {
        StringBuffer sb = new StringBuffer();

        Cookie[] cookies =  request.getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies){
                sb.append(cookie.getName());
                sb.append("=");
                sb.append(cookie.getValue());
                sb.append("; ");
            }
        }

        return sb.toString();
    }

    @GetMapping("cookie/get2")
    public String getCookie2(@CookieValue("sessionId") String sessionId) {
        return sessionId;
    }
}
