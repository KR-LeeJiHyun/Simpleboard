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

    <title>게시글</title>
  </head>
  <body>
    
    <div class="content">
        <div class="container">
          <h2 class="mb-4">게시글</h2>
        <form method="post">
            <div class="table-responsive">
                <table class="table table-striped custom-table">
                    <tbody>
                        <tr>
                            <th>제목</th>
                            <td colspan="10">Web Designer dsklfjklsdjalksjdflksj</td>
                        </tr>

                        <tr>
                            <th>번호</th>
                            <td name="id" value="2">2</td>
                            <th>조회수</th>
                            <td>100</td>
                            <th>좋아요</th>
                            <td>10</td>
                        </tr>
                        
                        <tr>
                            <th>작성자</th>
                            <td colspan="2">James Yates</td>
                            <th>작성일시</th>
                            <td colspan="2">2022-08-06</td>
                        </tr>
                        
                        <tr>
                            <td colspan="10"><textarea name="content" readonly> hi</textarea></td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <div class="center">
                <div>비밀번호 : <input type="password" name="password" /></div>
                <a class="w-btn-outline w-btn-gray-outline" type="button" href="reg">수정</a>
                <a class="w-btn-outline w-btn-gray-outline" type="button" href="index">삭제</a>
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