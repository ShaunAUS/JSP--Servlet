package com.newlecgure.web.controller.admin.notice;


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
@WebServlet("/admin/board/notice/list")
public class ListController extends HttpServlet {
	
	
		@Override
		protected void doPost(HttpServletRequest request, HttpServletResponse respone) throws ServletException, IOException {
		
				//키가 다같은데 값이 다르다 =배열, getParameteterVlaues
			String[] openIds=request.getParameterValues("open-id");
			String[] delIds=request.getParameterValues("del-id");
			String cmd = request.getParameter("cmd");
			
			switch(cmd) {
			case "일괄공개":
				for(String openId:openIds) {
					System.out.printf("open id:$s\n",openId);
				}
				
			case "일괄삭제":	
				
				//삭제 번호들 string배열로 받았지만 다시 int형으로 변환
				NoticeService service=new NoticeService();
				int[] ids =new int[delIds.length];
				for(int i =0;i<delIds.length;i++) {
					ids[i]=Integer.parseInt(delIds[i]);
				}
				//service 에서 호출
				int result=service.deleteNoticeAll(ids);
			}
			//삭제나 공개 끝난뒤 다시 페이지호출
			//밑에 doGet이 호출된다 =목록페이지 
			respone.sendRedirect("list");
		}
		
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
			List<Notice> list=service.getNoticeList(field,query,page);	
			int count=service.getNoticeCount(field,query);
			
			
			request.setAttribute("list", list);  //검색결과 클라한테 보여준다 
			request.setAttribute("count",count);
			
			request.getRequestDispatcher("/WEB-INF/view/admin/board/notice/list.jsp").forward(request,respone);   
					    
		}
}
