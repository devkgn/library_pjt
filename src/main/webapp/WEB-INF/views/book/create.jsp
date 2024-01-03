<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>도서 등록</title>
<link href="<c:url value='/resources/css/include/common.css'/>" rel="stylesheet" type="text/css">
<link href="<c:url value='/resources/css/book/create.css'/>" rel="stylesheet" type="text/css">
</head>
<body>
	<jsp:include page="../include/header.jsp"/>
	<jsp:include page="../include/nav.jsp"/>
	<section>
		<div id="section_wrap">
			<div class="word">
				<h3>도서 등록</h3>
			</div><br>
			<div class="register_book_form">
				<form action="<c:url value='/book' />" name="register_book_form" method="post" enctype="multipart/form-data">	
					<input type="text" name="b_name" placeholder="도서 이름을 입력하세요."> <br>
					<input type="text" name="b_author" placeholder="저자를 입력하세요."> <br>
					<input type="text" name="b_publisher" placeholder="출판사를 입력하세요."> <br>
					<input type="text" name="b_publish_year" placeholder="출판연도를 입력하세요."> <br>
					<input type="file" name="file"><br>
					<input type="button" value="등록" onclick="registerBookForm();"> 
					<input type="reset"	value="취소">
				</form>
			</div>
		</div>
	</section>
	<script type="text/javascript">
		function registerBookForm() {
			let form = document.register_book_form;		
			if (form.b_name.value == '') {
				alert('도서 이름을 입력하세요.');
				form.b_name.focus();
			} else if (form.b_author.value == '') {
				alert('저자를 입력하세요.');
				form.b_author.focus();
			} else if (form.b_publisher.value == '') {
				alert('출판사를 입력하세요.');
				form.b_publisher.focus();
			} else if (form.b_publish_year.value == '') {
				alert('출판연도를 입력하세요.');
				form.b_publish_year.focus();
			} else if (form.file.value == '') {
				alert('이미지 파일을 선택하세요.');
				form.b_thumbnail.focus();	
			} else {
				form.submit();
			}
		}
	</script>
</body>
</html>