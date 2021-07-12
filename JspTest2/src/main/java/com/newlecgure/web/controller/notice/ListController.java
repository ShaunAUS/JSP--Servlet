package com.newlecgure.web.controller.notice;

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
import com.newlecgure.web.service.NoticeService;
@WebServlet("/notice/list")
public class ListController extends HttpServlet {
		@Override
		protected void doGet(HttpServletRequest request, HttpServletResponse respone) throws ServletException, IOException {
			
			
			//list?f=title&q=a    <=검색은 필수가 아니라 옵션, null이 올수도있다
			
			
			String field_= request.getParameter("f");
			String query_ =request.getParameter("q");
			
			String page_=request.getParameter("p");
			
			//null을 받을수있는 정수형 = String
			
			
			
			// 아무값 입력 안하면 ""들어가서 오류남
			//""처리해줘야 기본값 title,"" 들어가서 전체검색 리스트나옴
			
			String field= "title"; //field가 null이면 기본값
			if(field_!=null && !field_.equals(""))
				field=field_;
			
			String query="";   //query 가 null이면 기본값 
			if(query_!=null && !query_.equals(""))
				query=query_;
			
			
			int page=1;      
			if(page_!=null&& !page_.equals(""))
				page=Integer.parseInt(page_);
			
			
			
			
			//사용자의 입출력만 담당한다 =controller // 그외에 기능 들은 NoticeService 에 
			NoticeService service= new NoticeService();
			//목록 보여준다.첫페이지
			//NoticeView = 댓글기능 추가된 list페이지, sql에서 view페이지 만든다
			List<NoticeView> list=service.getNoticePubList(field,query,page);	
			int count=service.getNoticeCount(field,query);
			
			
			request.setAttribute("list", list);  //검색결과 클라한테 보여준다 
			request.setAttribute("count",count);
			
			request.getRequestDispatcher("/WEB-INF/view/notice/list.jsp").forward(request,respone);   
					    
		}
}
