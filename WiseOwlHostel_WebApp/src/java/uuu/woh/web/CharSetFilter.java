/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uuu.woh.web;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 *
 * @author Sean
 */
//@WebFilter(filterName = "CharSetFilter", urlPatterns = {"*.jsp"})
public class CharSetFilter implements Filter {
    
    
    private FilterConfig filterConfig;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {        
        this.filterConfig = filterConfig;        
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String charset = filterConfig.getInitParameter("charset");
        if(charset==null) charset="UTF-8";
        
        //前置處理
        request.setCharacterEncoding(charset);
        request.getParameterNames();
        
        response.setCharacterEncoding(charset);
        response.getWriter();
        
        //交給下一棒
        chain.doFilter(request, response);
        
        //後續處理
    }
    
    @Override
    public void destroy() {        
        this.filterConfig = null;
    }
    
    
    
}
