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

    <title>게시글 목록</title>
  </head>
  <body>
  

  <div class="content">
    <div class="container">
      <h2 class="mb-4">게시판</h2>
      <div class="info">
        전체 게시글 수 : 2<br/>
        전체 댓글 수 : 0 
        <button class="w-btn-outline w-btn-gray-outline reg" onclick="location.href='reg'">글쓰기</button>
      </div>
      <div class="table-responsive">
        <table class="table table-striped custom-table">
          <thead>
            <tr>
              <th>번호</th>
              <th>제목</th>
              <th>작성자</th>
              <th>작성일시</th>
              <th>조회수</th>
              <th >좋아요</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>2</td>
              <td class="pl-0">
                  <a href="detail" class="name">Web Designer dsklfjklsdjalksjdflksj lvkmdfalknmlgv(0)</a>
              </td>
              <td>James Yates</td>
              <td>2022-08-06</td>
              <td>100</td>
              <td>10</td>
            </tr> 
            <tr>
              <td>1</td>
              <td class="pl-0">
                  <a href="detail" class="name">Graphic Designer dsklfjklsdjalksjdflksj lvkmdfalknmlgv(0)</a>
              </td>
              <td>Float</td>
              <td>2022-08-01</td>
              <td>23</td>
              <td>1</td>
            </tr>           
          </tbody>
        </table>
      </div>
    <div>       
        <ul>
          <li><div class="active">1</div></li>
          <li><div>2</div></li>
          <li><div>3</div></li>
          <li><div>4</div></li>
          <li><div>5</div></li>
      </ul>
    </div>
    <div class="search">
      <form>
        <select name="field">
          <option value="title">제목</option>
          <option value="writer">작성자</option>
          <option value="hashtag">해시태그</option>
          <option value="content">내용</option>
        </select>
        <input type="text" name="q" value/>
        <input class="w-btn-outline w-btn-gray-outline" type="submit" value="검색"/>
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