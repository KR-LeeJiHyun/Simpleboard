<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

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

<title>게시글</title>
</head>
<body>

	<div class="content">
		<div class="container">
			<h2 class="mb-4">게시글</h2>
			<button class="w-btn-outline w-btn-gray-outline reg"
				onclick="location.href='index'">목록</button>
			<form method="post">
				<div class="table-responsive">
					<table class="table table-striped custom-table">
						<tbody>
							<tr>
								<th>제목</th>
								<td colspan="6">${post.title}</td>
							</tr>

							<tr>
								<th>번호</th>
								<td name="id" value="${post.id}">${post.id}</td>
								<th>조회수</th>
								<td>${post.hit}</td>
								<th>좋아요</th>
								<td><a href="like?id=${post.id}">${post.like}</a></td>
							</tr>

							<tr>
								<th>작성자</th>
								<td>${post.writer}</td>
								<th>작성일시</th>
								<td>${post.regdate}</td>
								<th>싫어요</th>
								<td><a href="unlike?id=${post.id}">${post.unlike}</a></td>
							</tr>

							<tr>
								<td colspan="6"><textarea class="readonly" name="content"
										readonly> ${post.content}</textarea></td>
							</tr>
							<tr>
								<th>해시태그</th>
								<td colspan="6">
									<c:forEach var="hashtag" items="${hashtags}">
										<span>#${hashtag} </span>
									</c:forEach>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="center">
					<a class="w-btn-outline w-btn-gray-outline" type="button"
						href="update?id=${post.id}">수정</a>
				</div>
			</form>

			<div class="comment">
				<div class="table-responsive">
					<table class="table table-striped">
						<thead>
							<tr>
								<th>댓글 :</th>
								<td>
									<form method="get" action="comment">
										<input class="hidden" readonly type=number name="id" value="${comment.id}" />
										<input class="hidden" readonly type=number name="post_id" value="${post.id}" /> 
										작성자 : <input type="text" class="writer" name="writer" required /> 
										내용 : <input class="comment_content" type="text" name="content" required /> 
										비밀번호 : <input type="password" name="password" required /> 
										<input class="w-btn-outline w-btn-gray-outline" type="submit" value="등록" />
									</form>
								</td>
							</tr>
						</thead>
						<c:forEach var="comment" items="${list}">
							<tr>
								<td colspan=2>
									${comment.writer} <fmt:formatDate pattern="yyyy-MM-dd hh:mm:ss" value="${comment.regdate}" />
								</td>
							</tr>
							<tr>
								<c:if test="${!empty comment.content}">
								<td style="text-indent: 10px;" colspan=2>
									${comment.content}
									<button class="w-btn-outline w-btn-gray-outline comment_update">수정</button>
								</td>
								<td class="hidden" style="text-indent: 10px;" colspan=2>
									<form>
										<input class="hidden" readonly type=number name="id" value="${comment.id}" /> 
										<input class="hidden" readonly type=number name="post_id" value="${post.id}" /> 
										작성자 : <input type="text" class="writer" name="writer" value="${comment.writer}" required /> 
										내용 : <input class="comment_content" type="text" name="content" value="${comment.content}" required /> 
										비밀번호 : <input type="password" name="password" required /> 
										<input class="w-btn-outline w-btn-gray-outline" type="submit" value="수정" formaction="updateComment"/>
										<input class="w-btn-outline w-btn-gray-outline" type="submit" value="삭제" formaction="deleteComment"/>
										<button type="button" class="w-btn-outline w-btn-gray-outline comment_cancel">취소</button>
									</form>
								</td>
								</c:if>
								<c:if test="${empty comment.content}">
								<td style="text-indent: 10px;" colspan=2>
									작성자에 요청으로 인해 삭제된 댓글입니다.
								</td>
								</c:if>
							</tr>
						</c:forEach>
					</table>
				</div>
				<c:if test="${page+1<=last_page}">
					<button class="w-btn-outline w-btn-gray-outline reg" onclick="location.href='detail?id=${post.id}&page=${page + 1}'">더보기</button>
				</c:if>
				<c:if test="${last_page<page + 1}">
					<span class="w-btn-outline w-btn-gray-outline reg" type="button" onclick="alert('더이상 댓글이 없습니다.');">더보기</span>
				</c:if>
			</div>
		</div>

		<script src="js/jquery-3.3.1.min.js"></script>
		<script src="js/popper.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
		<script src="js/main.js"></script>
		<script src="js/comment.js"></script>
</body>
</html>