<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.goodee.library.admin.AdminVo"%>
<link href="<c:url value='/resources/css/include/nav.css'/>" rel="stylesheet" type="text/css">
<nav>
	<div id="nav_wrap">
		<%
			AdminVo loginedAdminVo = (AdminVo) session.getAttribute("loginedAdminVo");
			if( loginedAdminVo == null){
		%>
		<div class="menu">
			<ul>
				<li>
					<a href="<c:url value='/admin/login'/>">로그인</a>
				</li>
				<li>
					<a href="<c:url value='/admin/create'/>">회원가입</a>
				</li>
			</ul>
		</div>
		<%}else{%>
		<div class="menu">
			<ul>
				<li>
					<a href="<c:url value='/admin/logout'/>">로그아웃</a>
				</li>
				<li>
					<a href="<c:url value='/admin/${loginedAdminVo.a_m_no}'/>">회원정보수정</a>
				</li>
				<li>
					<a href="<c:url value='/admin'/>">회원목록</a>
				</li>
				<li>
					<a href="<c:url value='/book/create'/>">도서등록</a>
				</li>
				<li>
					<a href="<c:url value='/book' />">도서목록</a>
				</li>
			</ul>
		</div>		
		<div class="search">
			<form action="<c:url value='/book'/>" name="search_book_form" method="get">
				<input type="text" name="b_name" placeholder="검색하고자 하는 도서 이름을 입력하세요.">
				<input type="submit" value="검색">
			</form>
		</div>
		<%}%>
	</div>
</nav> 