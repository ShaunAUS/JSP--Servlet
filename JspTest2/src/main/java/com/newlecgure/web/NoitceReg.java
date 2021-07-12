package com.newlecgure.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.Out;

@WebServlet("/notice-reg")
public class NoitceReg extends HttpServlet {
	
		@Override
		protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
			resp.setCharacterEncoding("UTF-8");
			resp.setContentType("text/html; charset=UTF-8");
			
			
			PrintWriter out =resp.getWriter();
			
			
			String title=req.getParameter("title");
			String content=req.getParameter("content");
			
			out.print(title);
			out.print(content);
			
		
				
			
		}
		
}
