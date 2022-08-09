<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!doctype html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link
	href="https://fonts.googleapis.com/css?family=Roboto:300,400&display=swap"
	rel="stylesheet">

<link rel="stylesheet" href="fonts/icomoon/style.css">

<link rel="stylesheet" href="css/owl.carousel.min.css">

<!-- Bootstrap CSS -->
<link rel="stylesheet" href="css/bootstrap.min.css">

<!-- Style -->
<link rel="stylesheet" href="css/style.css">

<title>게시글 목록</title>
</head>
<body>

	<div class="content">
		<div class="container">
			<h2 class="mb-4">게시판</h2>
			<div class="info">
				전체 게시글 수 : ${total_post_count} <br /> 전체 댓글 수 : ${total_comment_count}
				<button class="w-btn-outline w-btn-gray-outline reg"
					onclick="location.href='reg'">글쓰기</button>
			</div>
			<div class="table-responsive">
				<c:set var="now" value="<%=new java.util.Date()%>" />
				<fmt:parseNumber value="${now.time / (1000*60*60*24)}" integerOnly="true" var="today" />
				<table class="table table-striped custom-table">
					<thead>
						<tr>
							<th>번호</th>
							<th>제목</th>
							<th>작성자</th>
							<th>작성일시</th>
							<th>조회수</th>
							<th>좋아요</th>
						</tr>
					</thead>
					<tbody>
					<!--게시글 목록 출력-->
						<c:forEach var="post" items="${list}">
							<tr>
								<td>${post.id}</td>
								<fmt:parseNumber value="${post.regdate.time / (1000*60*60*24)}" integerOnly="true" var="chgDttm" />
								<td class="pl-0"><a href="detail?id=${post.id}" class="name">
									<!--3일 이내의 최신글 표시-->
									<c:if test="${today - chgDttm <= 3}">[New]</c:if>
									${post.title}(${post.cmt_count})
								</a></td>
								<td>${post.writer}</td>
								<td><fmt:formatDate value="${post.regdate}"
										pattern="yyyy-MM-dd" /></td>
								<td>${post.hit}</td>
								<td>${post.like}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<!--10개 묶음으로 페이지 나눌 때 사용-->
			<div class="center">
				<c:set var="p" value="${(param.page==null)?1:param.page}" />
				<!--페이지 시작 번호-->
				<c:set var="startNum" value="${p - (p - 1) % 5}" />
				<!--페이지 끝 번호-->
				<c:set var="endNum"
					value="${fn:substringBefore(Math.ceil(post_count/10), '.')}" />
				<ul>
					<!--이전 버튼-->
					<li><c:if test="${startNum-5>0}">
							<a type="button" href="?page=${startNum-5}&field=${param.f}&query=${param.q}">이전</a>
						</c:if> <c:if test="${startNum-5<=0}">
							<span type="button" onclick="alert('이전 페이지가 없습니다.');">이전</span>
						</c:if>
					</li>
					<!--페이지 번호 출력 마지막 번호를 넘지 않는 선에서-->
					<c:forEach var="i" begin="0" end="4">
						<c:if test="${startNum + i <= endNum }">
							<li><a class="${(p == i + startNum)?'active':''}"
								href="?page=${i+startNum}&field=${param.field}&query=${param.query}">${i+startNum}</a></li>
						</c:if>
					</c:forEach>
					<!--다음 버튼-->
					<li>
						<c:if test="${startNum+4<endNum}">
							<a type="button" href="?page=${startNum+5}&field=${param.f}&query=${param.q}">다음</a>
						</c:if> 
						<c:if test="${startNum+5>=endNum}">
							<span type="button" onclick="alert('다음 페이지가 없습니다.');">다음</span>
						</c:if>
					</li>
				</ul>
			</div>
			<div class="search">
				<form>
					<select name="field">
						<option ${(param.field=="title")?"selected":""} value="title" >제목</option>
						<option ${(param.field=="writer")?"selected":""} value="writer">작성자</option>
						<option ${(param.field=="hashtag")?"selected":""} value="hashtag">해시태그</option>
						<option ${(param.field=="content")?"selected":""} value="content">내용</option>
					</select> <input type="text" name="query" value="${param.query}" /> <input
						class="w-btn-outline w-btn-gray-outline" type="submit" value="검색" />
				</form>
			</div>
		</div>
	</div>

	<script src="js/jquery-3.3.1.min.js"></script>
	<script src="js/popper.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/main.js"></script>
</body>
</html>