package com.newlecgure.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import com.sun.net.httpserver.Filter.Chain;

@WebFilter("/*")   //필터 매핑
public class CharacterEncodingFilter implements Filter {

	@Override     //서블릿처럼  어노테이션 or web.xml 에서매핑 가능
	public void doFilter(ServletRequest request, ServletResponse respone, FilterChain chain)
			throws IOException, ServletException {
		
		
			request.setCharacterEncoding("UTF-8");    //클라가 서버한테 보낼떄 UTF8
			//System.out.println("hello filter");//필터 시작전
			chain.doFilter(request,respone);
			//System.out.println("hello filter"); //필터후 서브릿후
			
	}

}
