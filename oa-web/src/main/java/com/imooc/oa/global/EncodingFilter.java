package com.imooc.oa.global;

import javax.servlet.*;
import java.io.IOException;

public class EncodingFilter implements Filter {

    private String encoding = "utf-8";

    public void init(FilterConfig filterConfig) throws ServletException {
        if(filterConfig.getInitParameter("encoding") != null){
            encoding = filterConfig.getInitParameter("encoding");
        }
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding(encoding);
        response.setCharacterEncoding(encoding);
        chain.doFilter(request,response);
    }

    public void destroy() {
    }
}
