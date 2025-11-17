package com.itheima.controller;

import com.itheima.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 会话跟踪演示
 */
@Slf4j
@RestController
public class SessionController {

    //设置cookie
    @GetMapping("/c1")
    public Result c1(HttpServletResponse response){
        log.info("响应Cookie");
        response.addCookie(new Cookie("username", "jinyong"));
        return Result.success();
    }

    //获取cookie
    @GetMapping("/c2")
    public Result c2(HttpServletRequest request){
        log.info("响应Cookie");
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if(cookie.getName().equals("username")){
                System.out.println("username: " + cookie.getValue());
            }
        }
        return Result.success();
    }




    //设置session
    @GetMapping("/s1")
    public Result s1(HttpSession session){
        log.info("请求...");
        session.setAttribute("session_username", "huangpu01");
        session.setAttribute("session_flag", "666");

        System.out.println(session.hashCode());
        return Result.success();
    }


    //获取session
    @GetMapping("/s2")
    public Result s2(HttpServletRequest request){
        log.info("响应....");

        HttpSession session = request.getSession();
        Object session_username = session.getAttribute("session_username");
        Object session_flag = session.getAttribute("session_flag");
        log.info("{},{}", session_username, session_flag);

        System.out.println(session.hashCode());
        return Result.success();
    }

}
