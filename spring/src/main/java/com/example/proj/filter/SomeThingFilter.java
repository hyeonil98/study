package com.example.proj.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

public class SomeThingFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // text를 요청하는 request라면 응답의 Content-Type을 "text/plat"으로 만듬
        if (request.getAttribute("giveMe").equals("text")) {
            response.setContentType("text/plain");
        }

        // 책임 연쇄 패턴에 따라 다음 필터를 실행하기 위해 필터 체인의 doFilter 호출
        chain.doFilter(request, response);
    }
}
