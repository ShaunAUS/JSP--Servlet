package com.newlecgure.web.controller;

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

import sun.rmi.server.Dispatcher;
@WebServlet("/notice/detail3")
public class NoticeDetailController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse respone) throws ServletException, IOException {
		
		//id 값 이전페이지에서 받아야되서 list 수정해야함 제목부분 링크
		int id=Integer.parseInt(request.getParameter("id"));
		   
	    String sql = "SELECT * FROM newtable WHERE ID=?";
	    		
	    try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql/newlecture","shaun","951753");
			PreparedStatement stmt=con.prepareStatement(sql);
			
			stmt.setInt(1,id);
			
			ResultSet rs=stmt.executeQuery();
			
			rs.next();
			
			// close 되기전에 값을 넣어놓는다
		    String title= rs.getString("TITLE");
		    String writerId=rs.getString("WRITER_ID"); 
		    Date regdate=rs.getDate("REGDATE"); 
		    String hit=rs.getString("HIT"); 
		    String files=rs.getString("FILES"); 
		    String content=rs.getString("CONTENT");
		    
		    Notice notice=new Notice(
		    		id,
		    		title,
		    		writerId,
		    		regdate,
		    		hit,
		    		files,
		    		content
		    		);
		    
		    request.setAttribute("n", notice);
		    
		    //try 지역변수라 여기다가 써야댐
			/*
			 * request.setAttribute("title",title );
			 * request.setAttribute("writerId",writerId);
			 * request.setAttribute("regdate",regdate); request.setAttribute("hit",hit);
			 * request.setAttribute("files",files); request.setAttribute("content",content);
			 */
		    
		    	rs.close();
		    	stmt.close();
		    	con.close();
		} catch (ClassNotFoundException e) {
		
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	    
	    //detail 3 에서 컨트룰러 분리됐으니 detail 3이랑 연결
	   request.getRequestDispatcher("/WEB-INF/view/notice/detail3.jsp").forward(request,respone);
	   
	    
	    
	}
}
