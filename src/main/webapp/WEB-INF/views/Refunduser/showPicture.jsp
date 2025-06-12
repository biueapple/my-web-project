<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><spring:message code="label.Main" /></title>
<style>
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

body {
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
}

.content-wrapper {
  max-width: 480px;
  margin: 60px auto;
  padding: 30px 25px;
  text-align: center;
}

/* 메시지 박스 */
.message {
  font-size: 1rem;
  background-color: #fafafa;
  color: #333;
  border: 2px solid #ddd;
  border-radius: 8px;
  padding: 20px;
  margin-bottom: 30px;
  line-height: 1.5;
}

/* 버튼 스타일 */
button {
  background-color: #1976d2;
  color: #ffffff;
  border: none;
  padding: 12px 28px;
  border-radius: 6px;
  font-size: 1rem;
  font-weight: 600;
  cursor: pointer;
  transition: background-color 0.3s ease;
  margin: 8px;
}

button:hover {
  background-color: #1565c0;
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

<%@ include file="/WEB-INF/views/header.jsp"%>

<div class="content-wrapper">
	<p class="message">${message}</p>
	<button class="styled-button" onclick="location.href='/airplane';"><spring:message code="label.Home" /></button>
	<button class="styled-button" onclick="location.href='/airplane/user/regist';"><spring:message code="label.List" /></button>
</div>
</body>
</html>
