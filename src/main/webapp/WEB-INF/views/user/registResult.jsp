<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입완료</title>
<style>
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

body {
  font-family: Arial, sans-serif;
}

form {
  margin: 0;
  padding: 0;
}

h2, p {
  margin: 0;
  padding: 0;
}

    .btn {
        display: inline-block;
        margin-top: 30px;
        padding: 12px 25px;
        font-size: 16px;
        color: #fff;
        background-color: #4CAF50;
        border: none;
        border-radius: 6px;
        text-decoration: none;
        transition: background-color 0.3s ease;
    }

    .btn:hover {
        background-color: #45a049;
    }
</style>
</head>
<body>

<%@ include file="/WEB-INF/views/header.jsp" %>

<h2>회원 가입</h2>
<p>[${result}] 가입이 완료되었습니다.</p>

  <a href="/airplane" class="btn">홈으로 이동</a>

</body>
</html>