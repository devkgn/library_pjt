<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 찾기</title>
<link href="<c:url value='/resources/css/include/common.css'/>" rel="stylesheet" type="text/css">
<link href="<c:url value='/resources/css/admin/find_password_form.css'/>" rel="stylesheet" type="text/css">
</head>
<body>
	<jsp:include page="../include/header.jsp"/>
	<jsp:include page="../include/nav.jsp"/>
	<section>
		<div id="section_wrap">
			<div class="word">
				<h3>비밀번호 찾기</h3><br>
				<p>(메일 주소로 새로운 비밀번호를 보내드립니다.)</p><br>
			</div>
			<div class="find_password_form">
				<form action="<c:url value='/admin/findPassword' />" name="find_password_form" method="post">
					<input type="text" name="a_m_id" placeholder="아이디를 입력하세요."> <br>
					<input type="text" name="a_m_name" placeholder="이름을 입력하세요."> <br>
					<input type="text" name="a_m_mail" placeholder="메일주소를 입력하세요."> <br>
					<input type="button" value="비밀번호 찾기" onclick="findPassword();"> 
					<input type="reset" value="초기화">
				</form>
			</div>
			<div class="create_account_login">
				<a href="<c:url value='/admin/create' />">회원가입</a>
				<a href="<c:url value='/admin/login' />">로그인</a>
			</div>
		</div>
	</section>	
	<script type="text/javascript">
		function findPassword() {
			let form = document.find_password_form;
			if (form.a_m_id.value == '') {
				alert('아이디를 입력하세요.');
				form.a_m_id.focus();	
			} else if (form.a_m_name.value == '') {
				alert('이름을 입력하세요.');
				form.a_m_name.focus();
			} else if (form.a_m_mail.value == '') {
				alert('메일주소를 입력하세요.');
				form.a_m_mail.focus();
			} else {
				form.submit();
			}
		}
	</script>
</body>
</html>