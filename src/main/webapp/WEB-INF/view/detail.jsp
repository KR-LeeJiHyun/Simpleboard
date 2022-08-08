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
            <div class="table-responsive">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>댓글 : </th>
                        </tr>
                    </thead>
                    <tr>
                        <td style="text-indent:0px;">누구인가? 2022-08-06 10:15</td>
                    </tr>
                    <tr>
                        <td style="text-indent:10px;">안녕하세요</td>
                    </tr>
                    <tr>
                        <td style="text-indent:10px;">┗궁예? 2022-08-06 10:18</td>
                    </tr>
                    <tr>
                        <td style="text-indent:20px;">반가워요</td>
                    </tr>
                    <tr>
                        <td style="text-indent:20px;">┗왕건? 2022-08-06 10:20</td>
                    </tr>
                    <tr>
                        <td style="text-indent:30px;">반갑습니다.</td>
                    </tr>
                    <tr>
                        <td style="text-indent:0px;">고려 2022-08-06 10:20</td>
                    </tr>
                    <tr>
                        <td style="text-indent:10px;">반갑습니다.</td>
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