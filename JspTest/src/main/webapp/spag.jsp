<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <!-- controller 영역 -->
    <%
    
    
 
    %>
    
    <!-- controller 영역 -->
    
    
     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<% 
pageContext.setAttribute("aa","hello");  /* getSession,getrequest,getrespone 가능 =페이지객체 = EL사용가능*/
                                         
%>
<body>
		<%=request.getAttribute("result") %> 입니다	 <!-- //view 영역  //request 공용저장소에서 result값 꺼내온다 result키값으로 -->
		<%-- 
		<!-- experssion language EL -->
		${result}  <br>
		${names[0]}<br>
		${notice.title}<br>
		${empty parm.n?'값이비어있습니다:parm.n }<br> --%>
</body>
</html>