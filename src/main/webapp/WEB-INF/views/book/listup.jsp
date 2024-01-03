<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>도서 목록 조회(도서 검색)</title>
<link href="<c:url value='/resources/css/include/common.css'/>" rel="stylesheet" type="text/css">
<link href="<c:url value='/resources/css/book/listup.css'/>" rel="stylesheet" type="text/css">
<link href="<c:url value='/resources/css/include/paging.css'/>" rel="stylesheet" type="text/css">
</head>
<body>
	<jsp:include page="../include/header.jsp"/>
	<jsp:include page="../include/nav.jsp"/>	
	<section>
		<div id="section_wrap">
			<div class="word">
				<h3>도서 목록</h3>
			</div><br>
			<div class="book_list">
				<table>
					<thead>
						<tr>
							<th>도서명</th>
							<th>저자</th>
							<th>발행처</th>
							<th>발행연도</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="item" items="${bookVos}">
							<tr>
								<td>
									<a href="book/${item.b_no}">${item.b_name}</a>
								</td>
								<td>${item.b_author}</td>
								<td>${item.b_publisher}</td>
								<td>${item.b_publish_year}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</section>
	<div class="center">
	  <div class="pagination">
	  	<c:if test="${pagingVo.prev}">
	  		<a href="/book?page_no=${pagingVo.startPage-1}">&laquo;</a>
	  	</c:if>
	  	<c:forEach begin="${pagingVo.startPage}" end="${pagingVo.endPage}" var="idx">
	  		<a href="/book?page_no=${idx}" <c:out value="${pagingVo.page_no == idx ? 'class=active' : '' }"/>>${idx}</a>
	  	</c:forEach>
		<c:if test="${pagingVo.next && pagingVo.endPage > 0}">
		  <a href="/book?page_no=${pagingVo.endPage+1}">&raquo;</a>
		</c:if>
	  </div>
	</div>
</body>
</html>