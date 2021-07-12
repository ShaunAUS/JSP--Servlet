
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
    
    
    
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>업로드 테스트</title>
</head>
<body>
<form action="fileOK.jsp" method="post" enctype="multipart/form-data">
<input type="file" name="upload">
<input type="submit" value="업로드">
</form>
</body>
</html>

