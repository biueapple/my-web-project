<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

body {
  font-family: Arial, sans-serif;
}

/* 본문을 감싸는 컨테이너 */
.content-wrapper {
  max-width: 480px;
  margin: 40px auto;
  padding: 20px;
  text-align: center;
}

/* 메시지 박스 */
.message {
  font-size: 1rem;
  background-color: #fafafa;
  color: #b00020;
  border: 2px solid #ddd;
  border-radius: 8px;
  padding: 20px;
  margin-bottom: 30px;
  line-height: 1.5;
}

/* 버튼 스타일 */
button {
  background-color: #1a73e8;
  color: white;
  border: none;
  padding: 14px 32px;
  border-radius: 6px;
  font-weight: 600;
  font-size: 1rem;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

button:hover {
  background-color: #155ab6;
}

form {
  margin: 0;
  padding: 0;
}

h2, p {
  margin: 0;
  padding: 0;
}
</style>
</head>
<body>

<%@ include file="/WEB-INF/views/header.jsp" %>

<div class="content-wrapper">
	<p class="message">${refund}</p>
	<button onclick="location.href='/airplane';"><spring:message code="label.Home" /></button>
	<button onclick="location.href='/airplane/user/regist';"><spring:message code="label.List" /></button>
</div>

</body>
</html>
