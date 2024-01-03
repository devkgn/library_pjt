<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 찾기 성공</title>
<link href="<c:url value='/resources/css/include/common.css'/>" rel="stylesheet" type="text/css">
<link href="<c:url value='/resources/css/admin/find_password_result.css'/>" rel="stylesheet" type="text/css">
</head>
<body>
	<jsp:include page="../include/header.jsp"/>
	<jsp:include page="../include/nav.jsp"/>	
	<section>	
		<div id="section_wrap">	
			<div class="word">
				<h3>새 비밀번호가 이메일로 전송 됐습니다.</h3>
			</div>
			<div class="others">
				<a href="<c:url value='/admin/member/login' />">login</a>
			</div>
		</div>
	</section>
</body>
</html>