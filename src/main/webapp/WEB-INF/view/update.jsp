<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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

    <title>게시글 수정</title>
  </head>
  <body>
    
    <div class="content">
        <div class="container">
          <h2 class="mb-4">게시글 수정</h2>
        <form method="post">
            <div class="table-responsive">
                <table class="table table-striped custom-table">
                    <tbody>
                        <tr>
                            <th>제목</th>
                            <td>
                                <input type="text" name="title" required value="${post.title}"/>
                            </td>
                            <th>작성자</th>
                            <td>
                                <input type="text" name="writer" required value="${post.writer}" />
                            </td>
                        </tr>
                        <tr>
                            <th>내용</th>
                            <td colspan="4"><textarea name="content" required>${post.content}</textarea></td>
                        </tr>
                     	
                        <tr>
                        <th>Hashtag</th>
                        <td colspan="4" class='hashtag'>
                        	<c:forEach var="hashtag" items="${hashtags}">
                        		<span class="hashtag-text"><input type="text" name="hashtag" required value="${hashtag}"/>
            					<button class="btn_remove w-btn-outline w-btn-gray-outline">X</button></span>
                        	</c:forEach>
	                        <button type="button" class="w-btn-outline w-btn-gray-outline btn_add">추가</button>
                        </td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <div class="center">
            	<input class="hidden" name="id" value="${post.id}" readonly/>
            	비밀번호 : 
            	<input type="password" name="password">
                <input class="w-btn-outline w-btn-gray-outline" type="submit" value="수정" formaction="update"/>
                <input class="w-btn-outline w-btn-gray-outline" type="submit" value="삭제" formaction="delete"/>
                <a class="w-btn-outline w-btn-gray-outline" type="button" href="detail?id=${post.id}">취소</a>
            </div>
        </form>

        </div>
    </div>

    <script src="js/jquery-3.3.1.min.js"></script>
    <script src="js/popper.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/main.js"></script>
    <script src="js/hashtag.js"></script>
  </body>
</html>