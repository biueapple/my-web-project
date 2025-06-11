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

.center-container {
	display: flex;
	flex-direction: column;
	align-items: center;
	justify-content: center;
	margin-top: 50px;
	text-align: center;
}

.message-box {
	color: red;
	font-size: 18px;
	margin-bottom: 20px;
}

.styled-button {
	background-color: #007BFF;
	color: white;
	border: none;
	padding: 10px 20px;
	font-size: 16px;
	border-radius: 5px;
	cursor: pointer;
	transition: background-color 0.3s;
}

.styled-button:hover {
	background-color: #0056b3;
}
</style>
</head>
<body>

	<%@ include file="/WEB-INF/views/header.jsp"%>



	<div class="center-container">
		<div class="message-box">${message}</div>
		
		<button class="styled-button" onclick="location.href='/airplane';">
			<spring:message code="label.board.Home" />
		</button>
<br/>


		<!-- 이전 -->
		<button class="styled-button"
			onclick="location.href='/airplane/user/regist';"><spring:message code="label.List" /></button>
	</div>
</body>
</html>
