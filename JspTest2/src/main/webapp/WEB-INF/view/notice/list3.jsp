
<%@page import="com.newlecgure.web.entity.Notice"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<!DOCTYPE html>
<html>

<head>
    <title>코딩 전문가를 만들기 위한 온라인 강의 시스템</title>
    <meta charset="UTF-8">
    <title>공지사항목록</title>
    
    <link href="/css/customer/layout.css" type="text/css" rel="stylesheet" />
    <style>
    
        #visual .content-container{	
            height:inherit;
            display:flex; 
            align-items: center;
            
            background: url("../../images/customer/visual.png") no-repeat center;
        }
    </style>
</head>

<body>
    <!-- header 부분 -->

    <header id="header">
        
        <div class="content-container">
            <!-- ---------------------------<header>--------------------------------------- -->

            <h1 id="logo">
                <a href="/index.html">
                    <img src="/images/logo.png" alt="뉴렉처 온라인" />

                </a>
            </h1>

            <section>
                <h1 class="hidden">헤더</h1>

                <nav id="main-menu">
                    <h1>메인메뉴</h1>
                    <ul>
                        <li><a href="/guide">학습가이드</a></li>

                        <li><a href="/course">강좌선택</a></li>
                        <li><a href="/answeris/index">AnswerIs</a></li>
                    </ul>
                </nav>

                <div class="sub-menu">

                    <section id="search-form">
                        <h1>강좌검색 폼</h1>
                        <form action="/course">
                            <fieldset>
                                <legend>과정검색필드</legend>
                                <label>과정검색</label>
                                <input type="text" name="q" value="" />
                                <input type="submit" value="검색" />
                            </fieldset>
                        </form>
                    </section>

                    <nav id="acount-menu">
                        <h1 class="hidden">회원메뉴</h1>
                        <ul>
                            <li><a href="/index.html">HOME</a></li>
                            <li><a href="/member/login.html">로그인</a></li>
                            <li><a href="/member/agree.html">회원가입</a></li>
                        </ul>
                    </nav>

                    <nav id="member-menu" class="linear-layout">
                        <h1 class="hidden">고객메뉴</h1>
                        <ul class="linear-layout">
                            <li><a href="/member/home"><img src="/images/txt-mypage.png" alt="마이페이지" /></a></li>
                            <li><a href="/notice/list.html"><img src="/images/txt-customer.png" alt="고객센터" /></a></li>
                        </ul>
                    </nav>

                </div>
            </section>

        </div>
        
    </header>

	<!-- --------------------------- <visual> --------------------------------------- -->
	<!-- visual 부분 -->
	
	<div id="visual">
		<div class="content-container"></div>
	</div>
	<!-- --------------------------- <body> --------------------------------------- -->
	<div id="body">
		<div class="content-container clearfix">

			<!-- --------------------------- aside --------------------------------------- -->
			<!-- aside 부분 -->


			<aside class="aside">
				<h1>고객센터</h1>

				<nav class="menu text-menu first margin-top">
					<h1>고객센터메뉴</h1>
					<ul>
						<li><a class="current"  href="/customer/notice">공지사항</a></li>
						<li><a class=""  href="/customer/faq">자주하는 질문</a></li>
						<li><a class="" href="/customer/question">수강문의</a></li>
						<li><a class="" href="/customer/event">이벤트</a></li>
						
					</ul>
				</nav>


	<nav class="menu">
		<h1>협력업체</h1>
		<ul>
			<li><a target="_blank" href="http://www.notepubs.com"><img src="/images/notepubs.png" alt="노트펍스" /></a></li>
			<li><a target="_blank" href="http://www.namoolab.com"><img src="/images/namoolab.png" alt="나무랩연구소" /></a></li>
						
		</ul>
	</nav>
					
			</aside>
			<!-- --------------------------- main --------------------------------------- -->



		<main class="main">
			<h2 class="main title">공지사항</h2>
			
			<div class="breadcrumb">
				<h3 class="hidden">경로</h3>
				<ul>
					<li>home</li>
					<li>고객센터</li>
					<li>공지사항</li>
				</ul>
			</div>
		<!-- 	------------------------검색창------------------------ -->
			<div class="search-form margin-top first align-right">
				<h3 class="hidden">공지사항 검색폼</h3>
				<form class="table-form">
					<fieldset>
						<legend class="hidden">공지사항 검색 필드</legend>
						<label class="hidden">검색분류</label>
						<select name="f">                       <!-- list?f="title"&q=a -->
							<option  ${(param.f=="title")?"selected":""}value="title">제목</option>
							<option  ${(param.f=="writer_id")?"selected":""}value="writer_id">작성자</option>
						</select> 
						<label class="hidden">검색어</label>
						<input type="text" name="q" value="${param.q}"/>
						<input class="btn btn-search" type="submit" value="검색" />
					</fieldset>
				</form>
			</div>
			
			<div class="notice margin-top">
				<h3 class="hidden">공지사항 목록</h3>
				<table class="table">
					<thead>
						<tr>
							<th class="w60">번호</th>
							<th class="expand">제목</th>
							<th class="w100">작성자</th>
							<th class="w100">작성일</th>
							<th class="w60">조회수</th>
						</tr>
					</thead>
					<tbody>
					
					
					<%-- <!-- 향상된 for문은 지역변수만 올수있다 그래서 request 에 넣은거 꺼내서 다시선언 -->
					<%
					List<Notice>list = (List<Notice>)request.getAttribute("list");
					
					//EL은 pageContext,request,session,application 저장소에 넣은것만 쓸수있다.
					//그래서 다시 pageContext 에 넣어준다
					
					
					for(Notice n:list){
						 pageContext.setAttribute("n",n);
						
						%> --%>
				
					
					
					
					
					 ㄴ
					<!-- items= 저장소에있는거 담을수있다. items 꺼내서 그릇= var 하나씩 꺼낸다 /  -->
					<!-- varStatus= 태그부분에서 속성 쓸수잇다. varStatus="st"  (st.current/st.indx/st.first/st.last....) -->
					<c:forEach var="n" items="${list}" >
					<tr>
						<td>${n.id}</td>
						<td class="title indent text-align-left"><a href="detail?id=${n.id}"></a>${n.title}</td><span>${n.cmtId}</span>
						<td>${n.writerId}</td>
						<td><fmt:formatDate pattern="yyyy-MM-dd hh:mm:ss" value="${n.regdate}"/></td>
						<td><fmt:formatNumber value= "${n.hit}"></fmt:formatNumber></td>
					</tr>
					</c:forEach>
				<%-- 	<%} %> --%>
				
					
					</tbody>
				</table>
			</div>
				<c:set var="page" value="${pram.p==null}?1:param.p"/>
				<c:set var="startNum" value="${page-(page-1)%5 }"  /> <!-- page에 따라/ 맨처음숫자 정함
			 -->	
			 				<!-- 정적함수는 EL에서 사용가능/ 두번쨰 인수(구분자)기준으로 앞에숫자출력 -->
			 	<c:set var="LastNum" value="${fn:substringBefore(Math.ceil(count/10),'.' }" />
					
			<div class="indexer margin-top align-right">
				<h3 class="hidden">현재 페이지</h3>			<!-- null이거나, ""면 참반환 ,param.p 는 필수가아니라 옵션이라 처음은 p값이 없어서 null임
 -->				<div><span class="text-orange text-strong">${(empty param.p)?1:param.p }</span> / ${lastNum}pages</div>
			</div>

			<div class="margin-top align-center pager">	
		
	<div>
		
	<!-- 	/12345/67891011  한턴씩 바뀜
 --> 		
		<c:if test="${startNum>1}">
			<a class="btn btn-prev" href="?p=${startNum-1}&t=&q=" >이전</a>
		</c:if>
		<c:if test="${startNum<=1}">
			<span class="btn btn-prev" onclick="alert('이전 페이지가 없습니다.');">이전</span>
		</c:if>	
	
	</div>
	
	
	<ul class="-list- center">
	
		<c:set var="page" value="${(empty param.p)?1:param.p }"/>
		<c:forEach var="i" begin="0" end="4">
		<!-- 6/6 -->
		<c:if test="${(startNum+i)<=lastNum }">
											<!-- ""안에 ""못씀 ""안에는 ''로써야댐	 -->				
		<!-- p= 필수가 아니라 옵션이라 처음에는 null 들어간다. -->
		<li><a class="-text- ${(page==(startNum+i))?'orange':''} bold" href="?p=${startNum+i}&f=${param.f}&q=${param.q}" >${startNum+i}</a></li>
		</c:if>
		</c:forEach>		
	</ul>
	<div>
			<!-- 조건에 맞으면 출력 아니면 x -->
			<!-- EL 로 연산자처리해야함 JSTL 연산자처리 없음 -->
			<c:if test="${startNum+4<LastNum}">
				<a href="?p=${startNum+5}&t=&q=" class="btn btn-next" >다음</span></a>	
			</c:if>
			<c:if test="${startNum+4>=LastNum}">
				<span class="btn btn-next" onclick="alert('다음 페이지가 없습니다.');">다음</span>
			</c:if>	
	</div>
	
			</div>
		</main>
		
			
		</div>
	</div>

    <!-- ------------------- <footer> --------------------------------------- -->



        <footer id="footer">
            <div class="content-container">
                <h2 id="footer-logo"><img src="/images/logo-footer.png" alt="회사정보"></h2>
    
                <div id="company-info">
                    <dl>
                        <dt>주소:</dt>
                        <dd>서울특별시 </dd>
                        <dt>관리자메일:</dt>
                        <dd>admin@newlecture.com</dd>
                    </dl>
                    <dl>
                        <dt>사업자 등록번호:</dt>
                        <dd>111-11-11111</dd>
                        <dt>통신 판매업:</dt>
                        <dd>신고제 1111 호</dd>
                    </dl>
                    <dl>
                        <dt>상호:</dt>
                        <dd>뉴렉처</dd>
                        <dt>대표:</dt>
                        <dd>홍길동</dd>
                        <dt>전화번호:</dt>
                        <dd>111-1111-1111</dd>
                    </dl>
                    <div id="copyright" class="margin-top">Copyright ⓒ newlecture.com 2012-2014 All Right Reserved.
                        Contact admin@newlecture.com for more information</div>
                </div>
            </div>
        </footer>
    </body>
   
    </html>