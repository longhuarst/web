package com.clxs.web.filter;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

//
@WebFilter(filterName = "UserFilter", urlPatterns = "/*")
public class UserFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println(getClass().getSimpleName()+" - init();");

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println(getClass().getSimpleName()+" - doFilter();");

        filterChain.doFilter(servletRequest,servletResponse);//传给下一个？

    }

    @Override
    public void destroy() {
        System.out.println(getClass().getSimpleName()+" - destory();");
    }
}
