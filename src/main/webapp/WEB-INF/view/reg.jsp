<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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

    <title>게시글 등록</title>
  </head>
  <body>
    
    <div class="content">
        <div class="container">
          <h2 class="mb-4">게시글 등록</h2>
        <form method="post">
            <div class="table-responsive">
                <table class="table table-striped custom-table">
                    <tbody>
                        <tr>
                            <th>제목</th>
                            <td>
                                <input type="text" name="title" />
                            </td>
                            <th>작성자</th>
                            <td>
                                <input type="text" name="writer" />
                            </td>
                            <th>비밀번호</th>
                            <td>
                                <input type="password" name="password" />
                            </td>
                        </tr>
                        
                        <tr>
                            <td colspan="6"><textarea name="content"></textarea></td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <div class="center">
                <input class="w-btn-outline w-btn-gray-outline" type="submit" value="등록" />
                <a class="w-btn-outline w-btn-gray-outline" type="button" href="index">취소</a>
            </div>
        </form>

        </div>
    </div>

    <script src="js/jquery-3.3.1.min.js"></script>
    <script src="js/popper.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/main.js"></script>
  </body>
</html>