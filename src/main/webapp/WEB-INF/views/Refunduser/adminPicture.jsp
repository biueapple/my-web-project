<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
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

/* 추가 스타일 */
.center-container {
	display: flex;
	flex-direction: column;
	align-items: center;
	gap: 20px; /* 이미지와 버튼 사이 간격 */
	margin-top: 30px;
	margin-bottom: 30px;
}

.center-container img {
	max-width: 300px;
	border: 2px solid #ccc;
	border-radius: 8px;
	box-shadow: 0 2px 6px rgba(0, 0, 0, 0.1);
}

.styled-button {
	padding: 10px 20px;
	font-size: 1rem;
	background-color: #007BFF; /* 파란색 계열 */
	color: white;
	border: none;
	border-radius: 5px;
	cursor: pointer;
	transition: background-color 0.3s ease;
}

.styled-button:hover {
	background-color: #0056b3;
}
</style>
</head>
<body>

	<%@ include file="/WEB-INF/views/header.jsp"%>

	<div class="center-container">
		<c:forEach var="p" items="${path}">
			<img src="<c:url value='/upload/${p.savepath}'/>" alt="damage photo" />
		</c:forEach>

		<button class="styled-button" onclick="location.href='/airplane';"><spring:message code="label.board.Home" /></button>
		<button class="styled-button" onclick="location.href='/airplane/user/regist';"><spring:message code="label.List" /></button>
	</div>

</body>
</html>
