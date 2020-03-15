package com.imooc.oa.global;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request1 = (HttpServletRequest) request;
        HttpServletResponse response1 = (HttpServletResponse) response;
        Object employee = request1.getSession().getAttribute("employee");
        if (request1.getRequestURI().toLowerCase().indexOf("login") != -1 || employee != null){
            chain.doFilter(request1, response1);
        }else{
            response1.sendRedirect("/to_login");
        }
    }

    public void destroy() {
    }
}
