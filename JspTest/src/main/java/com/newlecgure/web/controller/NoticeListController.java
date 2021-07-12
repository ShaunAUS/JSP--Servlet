package com.newlecgure.web.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.xdevapi.Statement;
import com.newlecgure.web.entity.Notice;
@WebServlet("/notice/list")
public class NoticeListController extends HttpServlet {
		@Override
		protected void doGet(HttpServletRequest request, HttpServletResponse respone) throws ServletException, IOException {
			
			List<Notice> list=new ArrayList<>();
			
			
			
			String sql = "SELECT * FROM newtable";
					try {
						Class.forName("com.mysql.jdbc.Driver");
						Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql/newlecture","shaun","951753");
						Statement stmt=con.createStatement();
						ResultSet rs=stmt.executeQuery(sql);
						
						
						//notice 객체 여러게 생성//list에 넣어준다//list에 넣고 request에 넣어준다
						 while(rs.next()){
							 int id=rs.getInt("ID");
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
							    
							    list.add(notice);
					 	} 
						
						
						    rs.close();
						    stmt.close();
						    con.close();
					}catch(ClassNotFoundException e) {
						e.printStackTrace();
					}catch(SQLException e) {
						e.printStackTrace();
					}
					
				    	request.setAttribute("list", list);
				    	request.getRequestDispatcher("/WEB-INF/view/notice/list.jsp").forward(request,respone);   
					    
		}
}
