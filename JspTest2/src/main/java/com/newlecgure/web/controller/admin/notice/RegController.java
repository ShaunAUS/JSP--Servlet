package com.newlecgure.web.controller.admin.notice;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.mysql.cj.x.protobuf.MysqlxCrud.Collection;
import com.newlecgure.web.entity.Notice;
import com.newlecgure.web.service.NoticeService;
//일반 인코딩 방식은 쿼리문 한문장으로 가지만 멀티파트는 나눠서 간다
//일반=url 인코딩방식=문자열만보낼수잇음 파일 x, 그래서 멀티파트로 바꿔야함
//멀티파트로 인코딩하면 서버쪽에서도 멀티파트로 받아야한다
@MultipartConfig(
		
		fileSizeThreshold=1024*1024, //파일 크기가 이걸 넘으면 location 하드디스크에 저장하자
		
		maxFileSize=1024*1024*50, //파일 하나 최대크기
		maxRequestSize=1024*1024*50*5//파일 여러개 최대크기
		
		
		)
@WebServlet("/admin/board/notice/reg")

public class RegController extends HttpServlet {

	//글쓰기 // 등록
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/view/adimn/board/notice/reg.jsp")
		.forward(request,response);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//기본값 줘야되는지 생각
			
			String title=request.getParameter("title");
			String content=request.getParameter("content");
			//바로공개 체크하면 true , 아니면 null// isOpen을 통해 pub이 0 or 1로바뀐다//pub 공개=1 공개x=0
			String isOpen=request.getParameter("open");
			
			
			
			//0.Contrlloer 애노테이션 선언, jsp 에 multipart 선언
			//1.파일 JSP에서 받고, inputstream 준비 
			//2.파일 읽기  
			//3.저장위치====1(절대경로 구하기), +(\) + (파일이름) 
			//3.  2번은 outputstream 에 넣어주기
			
			
			// 상대경로 /upload -> 절대경로: c:temp upload    =자바 파일저장= 절대경로만!!
			
			
			
			Collection<part> parts=request.getParts();//파일 여러게 받기// 두개이상이면 name으로 받지 않ㅇ는다
			StringBuilder builder= new StringBuilder();
			for(Part p : parts) {
				if(p.getName().equals("file")) continue;
				if(p.getSize()==0) continue; //멀티파일 빈파일 왓을때
				
				
				//Part filePart=request.getPart("file");  //파일하나 받는
				Part filePart=p;
				//파일명 얻기
				String fileName = filePart.getSubmittedFileName();
				//파일이름1,파일이름2
				builder.append(fileName);
				builder.append(",");
				
				InputStream fis=filePart.getInputStream();
				
			
			
			
			
			//저장위치//절대경로구하기
			String realPath=request.getServletContext().getRealPath("/member/upload"); //절대경로 얻기  
			System.out.println(realPath);
			
			
			
			//realpath 있나확인  있으면 mkdir= /upload 생성,  mkdirs= /mmember/upload 부모까지 생성
			File path=new File(realPath);
			if(!path.exists()) {
				path.mkdirs();
			}
			
			//fileName 동일한 이름 있나 확인
			
			
			
			// realpath+\+filename
			//filepath=실제 종합주소 ,    (realpath) =주소 + (pathSeparator)=\ + (getSubmittedFileName)파일이름
			String filePath=realPath+File.pathSeparator+fileName;
			//파일 출력
			FileOutputStream fos=new FileOutputStream(filePath);
			
			
			byte[] buf=new byte[1024];
			int size=0;
			
			//int b=fis.read();//byte단위로 읽지만 다읽으면 -1정수반환==read 는 int형반환
			//1024 한바가지 .. 마지막은 다 안채워질수도 있다
			while((size=fis.read(buf))!=-1) {
				//0~ size 채워지만큼 출력한다
				fos.write(buf,0,size);
			}
			
			
			
			
			
			
			
			fos.close();
			fis.close();
			}
			
			//두번쨰인자는 포함 x   7 이면 6까지
			builder.delete(builder.length()-1, builder.length());
			
			
			boolean pub= false;
			if(isOpen!=null) {
				pub=true;
			}
			
			Notice notice=new Notice();
			notice.setContent(content);
			notice.setTitle(title);
			notice.setPub(pub);
			notice.setWriterId("newlec");
			
			notice.setFiles(builder.toString());
			
			
			//글 등록 메서드
			NoticeService service=new NoticeService();
			service.insertNotice(notice);
			//등록 db에하고 다시 목록화면으로 온다
			//밑에처럼 경로 안정하면 @webservlet 부분 reg대신 list들어간다
			response.sendRedirect("list");
		
		
			response.setCharacterEncoding("UTF-8");  //출력값 한글
			response.setContentType("text/html; charset=UTF-8");
		
			
	}
}
