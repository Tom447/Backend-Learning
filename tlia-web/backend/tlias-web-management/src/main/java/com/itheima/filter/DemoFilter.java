package com.itheima.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

//@WebFilter(urlPatterns = "/*") //拦截所有
public class DemoFilter implements Filter {

    @Override //web服务器启动后, Filter实例创建之后, 自动执行, 只执行一次
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init .......");
    }

    @Override //每次拦截到请求后都会执行, 会执行多次
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("DemoFilter 拦截到了请求 ..... 前");

        //放行
        filterChain.doFilter(request, response);

        System.out.println("DemoFilter 拦截到了请求 ..... 后");
    }

    @Override //web服务器关闭时执行, 只执行一次
    public void destroy() {
        System.out.println("destroy .......");
    }

}
