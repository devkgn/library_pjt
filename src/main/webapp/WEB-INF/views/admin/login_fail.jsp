<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 실패</title>
<link href="<c:url value='/resources/css/include/common.css'/>" rel="stylesheet" type="text/css">
<link href="<c:url value='/resources/css/admin/login_result.css'/>" rel="stylesheet" type="text/css">
</head>
<body>
	<jsp:include page="../include/header.jsp"/>
	<jsp:include page="../include/nav.jsp"/>	
	<section>
		<div id="section_wrap">
			<div class="word">
				<h3>로그인 실패!!</h3>
				<span>(아이디 혹은 비밀번호를 확인해보세요.)</span>
			</div>
			<div class="others">
				<a href="<c:url value='/admin/create' />">회원가입</a>
				<a href="<c:url value='/admin/login' />">로그인</a>
			</div>
		</div>
	</section>
</body>
</html>