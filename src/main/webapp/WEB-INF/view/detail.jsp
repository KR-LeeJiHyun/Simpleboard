<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!doctype html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400&display=swap" rel="stylesheet">

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
                            <td colspan="6"><textarea class="readonly" name="content" readonly> ${post.content}</textarea></td>
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
                <a class="w-btn-outline w-btn-gray-outline" type="button" href="update?id=${post.id}">수정</a>
            </div>
        </form>

        <div class="comment">
        	<form method="get" action="comment">
        		<input class="hidden" readonly type=number name="post_id" value="${post.id}"/>
        		<input class="hidden" readonly type=number name="group_id" value="0"/>
        		<input class="hidden" readonly type=number name="depth" value="0"/>
        		<input class="hidden" readonly type=number name="order" value="0"/>
        		작성자 : <input type="text" class="writer" name="writer" required />
        		내용 : <input type="text" name="content" required />
        		비밀번호 : <input type="password" name="password" required />
        		<input class="w-btn-outline w-btn-gray-outline" type="submit" value="등록"/>
        	</form>
            <div class="table-responsive">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>댓글 : </th>
                        </tr>
                    </thead>
                    <c:forEach var="comment" items="${list}">
                    	<tr>
                        	<td style="text-indent:${comment.depth * 10}px;">${comment.writer} <fmt:formatDate pattern="yyyy-MM-dd hh:mm:ss" value="${comment.regdate}"/></td>
                    	</tr>
                    	<tr>
                       		<td style="text-indent:${comment.depth * 10 + 10}px;">${comment.content}</td>
                    	</tr>
                    </c:forEach>
                    
                    <tr>
                        <td style="text-indent:0px;">누구인가? 2022-08-06 10:15</td>
                    </tr>
                    <tr>
                        <td style="text-indent:10px;">안녕하세요</td>
                    </tr>
                </table>
        </div>

        </div>
    </div>

    <script src="js/jquery-3.3.1.min.js"></script>
    <script src="js/popper.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/main.js"></script>
  </body>
</html>