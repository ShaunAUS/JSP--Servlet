package com.newlecgure.web.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mysql.cj.xdevapi.Statement;
import com.newlecgure.web.controller.notice.NoticeView;
import com.newlecgure.web.entity.Notice;

public class NoticeService {
	
		//일괄삭제 // 선택여러개 하고
		public int removeNoticeAll(int[] ids){
			return 0;
		}
		public int pubNoticeAll(int[] ids){
			return 0;
		}
		
		// 인서트,업데이트,델리트 반환값= 처리가완료된후 완료된게 레코드에 몇개 영향을 줬는가
		//인서트된게 있으면 1반환 아니면 0
		//공지등록 요청//글 등록
		public int insertNotice(Notice notice){
			
			
			int result=0;
			
			
			String params="";
			
			
			String sql= "INSERT INTO NOTICE(TITLE,CONTENT,WRITER_ID,PUB,FILES) VALUES(?,?,?,?,?)";
					
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql/newlecture","shaun","951753");
				PreparedStatement stmt=con.prepareStatement(sql);
				//notice 값은 notice.id 이런식이 아니고 getter,setter을 통해 얻는다
				stmt.setString(1,notice.getTitle());
				stmt.setString(2, notice.getContent());
				stmt.setString(3, notice.getWriterId());
				stmt.setBoolean(4,notice.getPub());
				stmt.setString(5,notice.getFiles());
				
				
				result=stmt.executeUpdate();  
				
				
				    stmt.close();
				    con.close();
			}catch(ClassNotFoundException e) {
				e.printStackTrace();
			}catch(SQLException e) {
				e.printStackTrace();
			}
			return result; 
		}
		//삭제된게 잇으면 1반환 아니면 0
		//공지삭제 요청 // 디테일 페이지에서 삭제
		public int deleteNotice(int id){
			return 0;
		}
		//공지수정 요청//디테일 페이지에서 수정
		public int updateNotice(Notice notice){
			return 0;
		}
		
		
		//가장최근 글목록
		List<Notice> getNoticeNewestList(){
			return null;
		}
	
		
		public List<NoticeView> getNoticeViewList(){
			return getNoticeList("title","",1);
		}
		
		public List<Notice> getNoticeList(int page){
			return getNoticeList("title","",page);
		}
		
		// 페이징= 원하는 개수만큼 글목록 볼수있다
		
		
		//검색해서  검색한거 몇페이지 있는지까지
		public List<Notice> getNoticeList(String field/*TITLE, WRITER_ID*/, String query/*A*/,int page ){
			
			List<Notice> list=new ArrayList<>();
			
			//field 랑 qurery 를 sql문에 박아 넣어야 함 그래야 검색됌
			
			//사용자가 입력하면 sql입력문에 들어가서 db에서 정보꺼내온다
			String sql="SELECT * FROM (" +
			"  SELECT ROWNUM NUM, N.*"+
			"  FROM (SELECT * FROM NOTICE_View WHERE "+field+" LIKE ? ORDER BY REGDATE DESC)N" +
			") " +
			"WHERE NUM BETWEEN ? AND ?";
			
			// TITLE=?로하고 setString(1.title) 하면 title ->'title'로 들어가서 컬럼 검색이 안됌
			//TITLE = 'A'  -> TITLE LIE '%A%'    A라는걸 지정해서 찾는게 아니라 /A가포함된 타이틀 찾는것
			//TITLE ='%%'  검색어가 없으면 모든걸 검색한다
			
			//필터링은 가장 안쪽이 좋다
			
			//1,11,21,31, -> an= 1+(page-1)*10
			//10,20,30,40, ->page*10
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql/newlecture","shaun","951753");
				PreparedStatement stmt=con.prepareStatement(sql);
				
				
				stmt.setString(1,"%+query+%" );
				stmt.setInt(2,1+(page-1)*10 );
				stmt.setInt(3, page*10);
				
				ResultSet rs=stmt.executeQuery();  //쿼리문 실행
				
				
				//notice 객체 여러게 생성//list에 넣어준다//list에 넣고 request에 넣어준다
				 while(rs.next()){
					 int id=rs.getInt("ID");
					 String title= rs.getString("TITLE");
					    String writerId=rs.getString("WRITER_ID"); 
					    Date regdate=rs.getDate("REGDATE"); 
					    String hit=rs.getString("HIT"); 
					    String files=rs.getString("FILES"); 
					    String content=rs.getString("CONTENT");
					
					    Notice notice=new Notice(	//검색결과 notice에 넣기
					    		id,
					    		title, 
					    		writerId,
					    		regdate,
					    		hit,
					    		files,
					    		content
					    		);
					    
					    list.add(notice);    //검색결과를 list에 넣기 =>request ->클라 화면 출력
			 	} 
				
				
				    rs.close();
				    stmt.close();
				    con.close();
			}catch(ClassNotFoundException e) {
				e.printStackTrace();
			}catch(SQLException e) {
				e.printStackTrace();
			}
			
			
			
			
			return list;
		}
		
		
		
		
		
		//페이지가 페이지중 몇번쨰인지
		public int getNoticeCount() {
			
			return getNoticeCount("title","");
		}
		
		
		//검색 했을떄 검색결과 몇페이지중 몇페이지
		public int getNoticeCount(String field, String query) {
			
			int count=0;
			
			 //count(id)  부를떄 컬럼명 일치 x  = count 별명지어준다
			String sql="SELECT COUNT(ID) COUNT FROM (" +
					"  SELECT ROWNUM NUM, N.*"+
					"  FROM (SELECT * FROM NOTICE WHERE "+field+" LIKE ? ORDER BY REGDATE DESC)N" +
					") ";
					
			
		
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql/newlecture","shaun","951753");
				PreparedStatement stmt=con.prepareStatement(sql);
				
				
				stmt.setString(1,"%"+query+"%" );
				
				
				ResultSet rs=stmt.executeQuery();  //검색결과
				 
				
				if(rs.next())
					//다른 정보 필요없고 count만 필요
					count=rs.getInt("count");
			 	
								
				    rs.close();
				    stmt.close();
				    con.close();
			}catch(ClassNotFoundException e) {
				e.printStackTrace();
			}catch(SQLException e) {
				e.printStackTrace();
			}
			
			
				return count;
		}

		//--------------------------------------detail 영역------------------------------------------------
		
		public Notice getNotice(int id) {
			
			Notice notice = null;
			
			String sql= "SELECT * FROM NOTICE WHERE ID =?";
	
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql/newlecture","shaun","951753");
				PreparedStatement stmt=con.prepareStatement(sql);
				
				
				stmt.setInt(1,id );
				
				
				ResultSet rs=stmt.executeQuery();  
				
				//while-> if
				 if(rs.next()){
					 int nid=rs.getInt("ID");
					 String title= rs.getString("TITLE");
					    String writerId=rs.getString("WRITER_ID"); 
					    Date regdate=rs.getDate("REGDATE"); 
					    String hit=rs.getString("HIT"); 
					    String files=rs.getString("FILES"); 
					    String content=rs.getString("CONTENT");
					
					    notice=new Notice(
					    		nid,
					    		title,
					    		writerId,
					    		regdate,
					    		hit,
					    		files,
					    		content
					    		);
					    
					    
			 	} 
				
				
				    rs.close();
				    stmt.close();
				    con.close();
			}catch(ClassNotFoundException e) {
				e.printStackTrace();
			}catch(SQLException e) {
				e.printStackTrace();
			}
			
			
			
			return notice;
		}
		
		public List<NoticeView> getNoticePubList(String field, String query, int page) {
			
		}
		
		
		//현재 ID 를 주면서 다음 ID를구해 다음글 알아야댐
		public Notice getNextNoitce(int id) {
			
			Notice notice = null;
			
			String sql= "SELECT * FROM NOTICE " +
					"WHERE ID = ( " +
					"  SELECT ID FROM NOTICE "  +
					"  WHERE REGDATE>(SELECT REGDATE FROM NOTICE WHERE ID=?)" +
					"  AND RONUM=1";
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql/newlecture","shaun","951753");
				PreparedStatement stmt=con.prepareStatement(sql);
				
				
				stmt.setInt(1,id );
				
				
				ResultSet rs=stmt.executeQuery();  
				
				 if(rs.next()){
					 int nid=rs.getInt("ID");
					 String title= rs.getString("TITLE");
					    String writerId=rs.getString("WRITER_ID"); 
					    Date regdate=rs.getDate("REGDATE"); 
					    String hit=rs.getString("HIT"); 
					    String files=rs.getString("FILES"); 
					    String content=rs.getString("CONTENT");
					
					    notice=new Notice(
					    		nid,
					    		title,
					    		writerId,
					    		regdate,
					    		hit,
					    		files,
					    		content
					    		);
					    
					    
			 	} 
				
				
				    rs.close();
				    stmt.close();
				    con.close();
			}catch(ClassNotFoundException e) {
				e.printStackTrace();
			}catch(SQLException e) {
				e.printStackTrace();
			}
			
			return notice;
			
			
		}
		public Notice getPrevNoitce(int id) {
			
			Notice notice = null;
			
			String sql= "SELECT * FROM NOTICE " +
					"WHERE ID = ( " +
					"  SELECT ID FROM NOTICE "  +
					"  WHERE REGDATE>(SELECT REGDATE FROM NOTICE WHERE ID=?)" +
					"  AND RONUM=1";
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql/newlecture","shaun","951753");
				PreparedStatement stmt=con.prepareStatement(sql);
				
				
				stmt.setInt(1,id );
				
				
				ResultSet rs=stmt.executeQuery();  
				
				 if(rs.next()){
					 int nid=rs.getInt("ID");
					 String title= rs.getString("TITLE");
					    String writerId=rs.getString("WRITER_ID"); 
					    Date regdate=rs.getDate("REGDATE"); 
					    String hit=rs.getString("HIT"); 
					    String files=rs.getString("FILES"); 
					    String content=rs.getString("CONTENT");
					
					    notice=new Notice(
					    		nid,
					    		title,
					    		writerId,
					    		regdate,
					    		hit,
					    		files,
					    		content
					    		);
					    
					    
			 	} 
				
				
				    rs.close();
				    stmt.close();
				    con.close();
			}catch(ClassNotFoundException e) {
				e.printStackTrace();
			}catch(SQLException e) {
				e.printStackTrace();
			}
			return notice;
		}
		//일괄삭제
		//ids== 삭제 숫자들 string->int
		public int deleteNoticeAll(int[] ids) {
			
			int result=0;
			
			
			String params="";
			
			// ""+3= "3";
			for(int i=0; i<ids.length; i++) {
				params+=ids[i];
				//마지막이 아니냐 조건절,//sql쿼리문  ,  생성//"3"+","
				if(i<ids.length) {
					params += ",";
				}
			}
			//sql 이런식으로 나와야함
			//"DELETE NOTICE WHERE ID IN(1,2,3)";
			String sql= "DELETE NOTICE WHERE ID IN("+params+")";
					
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql/newlecture","shaun","951753");
				Statement stmt=con.createStatement();
				
		
				result=stmt.executeUpdate();  
				
				
				    stmt.close();
				    con.close();
			}catch(ClassNotFoundException e) {
				e.printStackTrace();
			}catch(SQLException e) {
				e.printStackTrace();
			}
			return result;
		}
}
