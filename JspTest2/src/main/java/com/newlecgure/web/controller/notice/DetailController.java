package com.newlecgure.web.controller.notice;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.newlecgure.web.entity.Notice;
import com.newlecgure.web.service.NoticeService;

import sun.rmi.server.Dispatcher;
@WebServlet("/notice/detail3")
public class DetailController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse respone) throws ServletException, IOException {
		
		//id 값 이전페이지에서 받아야되서 list 수정해야함 제목부분 링크
		int id=Integer.parseInt(request.getParameter("id"));
		   
	     NoticeService service = new NoticeService();
	     Notice notice=service.getNotice(id);
		
	    request.setAttribute("n", notice);
	    //detail 3 에서 컨트룰러 분리됐으니 detail 3이랑 연결
	   request.getRequestDispatcher("/WEB-INF/view/notice/detail3.jsp").forward(request,respone);
	   
	    
	    
	}
}
