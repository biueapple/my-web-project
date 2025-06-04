<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><spring:message code="label.damageResultTitle" /></title>
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

img {
	width: 200px;
	height: auto;
	margin: 10px;
	border-radius: 4px;
	box-shadow: 0 0 5px rgba(0,0,0,0.1);
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
	margin: 20px auto;
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

	<div class="main-content">
		<c:if test="${not empty message}">
			<p class="message">${message}</p>
		</c:if>

		<form action="<c:url value='/user/damage/home'/>" method="get">
			<button type="submit">
				<spring:message code="label.Home" />
			</button>
		</form>

		<c:if test="${not empty savepath}">
			<h3><spring:message code="label.uploadedPhotos" /></h3>
			<c:forEach var="path" items="${savepath}">
				<img src="${path}" alt="uploaded photo" />
			</c:forEach>
		</c:if>
	</div>

</body>
</html>
