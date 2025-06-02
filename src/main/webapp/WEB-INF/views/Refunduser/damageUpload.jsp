<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
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
<body>
<div class="main-content">
	<h2>파손 보상 신청</h2>
	<h3>파손된 캐리어 사진을 업로드 해주세요</h3>
	
	<div class="buttons-container">
	<form action="<c:url value='/user/damage/upload'/>" method="post"
		enctype="multipart/form-data" style="display: inline-block;" >
		<label>파손 사진 업로드</label>
		<input type="file" name="damagePhotos" multiple /><br>
		<!-- <textarea name="description" placeholder="파손 내용 입력"></textarea>-->
		<button type="submit">보상신청</button>
	</form>

	<form action="<c:url value='/'/>" method="get"
		style="display: inline-block;">
		<button type="submit"><spring:message code="button.return"/></button>
	</form>
	</div>
	</div>
</body>
</html>