<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 목록</title>
<link href="<c:url value='/resources/css/include/common.css'/>" rel="stylesheet" type="text/css">
<link href="<c:url value='/resources/css/admin/listup.css'/>" rel="stylesheet" type="text/css">
</head>
<body>
	<jsp:include page="../include/header.jsp"/>
	<jsp:include page="../include/nav.jsp"/>	
	<section>
		<div id="section_wrap">
			<div class="word">
				<h3>회원 목록</h3>
			</div>
			<br>
			<div class="admin_list">
				<table>
					<thead>
						<tr>
							<th>계정</th>
							<th>이름</th>
							<th>성별</th>
							<th>메일</th>
							<th>연락처</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="item" items="${adminVos}">
							<tr>
								<td>${item.a_m_id}</td>
								<td>${item.a_m_name}</td>
								<td>${item.a_m_gender}</td>
								<td>${item.a_m_mail}</td>
								<td>${item.a_m_phone}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</section>
</body>
</html>