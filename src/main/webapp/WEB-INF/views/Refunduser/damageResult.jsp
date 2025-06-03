<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
	
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

form {
	margin: 0;
	padding: 0;
}

h2, p {
	margin: 0;
	padding: 0;
}

/* 본문 영역 스타일만 별도로 정의 */
.main-content {
	padding: 30px 20px;
	text-align: center;
}

.main-content h2, .main-content h3 {
	color: #2c3e50;
	margin-bottom: 20px;
}

.reservation-table {
	margin: 0 auto 30px auto;
	border-collapse: collapse;
	width: 90%;
	max-width: 1000px;
	background-color: #ffffff;
	box-shadow: 0 2px 10px rgba(0, 0, 0, 0.05);
	border-radius: 8px;
	overflow: hidden;
}

.reservation-table thead {
	background-color: #FF9800;
	color: #ffffff;
}

.reservation-table th, .reservation-table td {
	padding: 12px 16px;
	text-align: center;
	border-bottom: 1px solid #e0e0e0;
}

.reservation-table tbody tr:hover {
	background-color: #f8f9fa;
}

.buttons-container {
	display: flex;
	justify-content: space-between;
	align-items: center;
	max-width: 1000px;
	margin: 0 auto 20px auto;
	padding: 0 10px;
	box-sizing: border-box;
}


button {
	background-color: #2980b9;
	color: #ffffff;
	border: none;
	padding: 10px 22px;
	border-radius: 6px;
	font-size: 15px;
	cursor: pointer;
	transition: background-color 0.3s ease;
	height: 40px;
	line-height: 20px;
	display: flex;
	align-items: center;
	justify-content: center;
}

button:hover {
	background-color: #1f5f85;
}

.message {
	text-align: center;
	margin-top: 20px;
	color: #d32f2f;
	font-weight: bold;
}
</style>
</head>
<body>


	<p style="color: red; font-weight: bold;">${message}</p>
	<form action="<c:url value='/user/damage/home'/>" method="get"
		style="display: inline-block;"style="margin: 0; padding: 0;">
		<button type="submit"><spring:message code="label.Home" /></button>
	</form>
</body>
</html>