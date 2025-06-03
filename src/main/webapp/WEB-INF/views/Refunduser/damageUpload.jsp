<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파손 보상 신청</title>
<style>
* {
	margin: 0;
	padding: 0;
	box-sizing: border-box;
}

body {
	font-family: Arial, sans-serif;
	background-color: #f4f4f4;
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
}

.main-content {
	background: #fff;
	padding: 40px 30px;
	border-radius: 10px;
	box-shadow: 0 4px 12px rgba(0,0,0,0.1);
	width: 100%;
	max-width: 600px;
	text-align: center;
}

.main-content h2, .main-content h3 {
	color: #2c3e50;
	margin-bottom: 20px;
}

form {
	display: flex;
	flex-direction: column;
	align-items: center;
	gap: 15px;
}

label {
	font-weight: bold;
	color: #333;
}

input[type="file"] {
	margin-top: 5px;
}

.buttons-container {
	display: flex;
	justify-content: space-around;
	margin-top: 30px;
	gap: 20px;
	flex-wrap: wrap;
}

button {
	background-color: #2980b9;
	color: #ffffff;
	border: none;
	padding: 10px 24px;
	border-radius: 6px;
	font-size: 15px;
	cursor: pointer;
	transition: background-color 0.3s ease;
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
	<h2>파손 보상 신청</h2>
	<h3>파손된 캐리어 사진을 업로드 해주세요</h3>

	<form action="<c:url value='/user/damage/upload'/>" method="post" enctype="multipart/form-data">
		<label for="damagePhotos">파손 사진 업로드</label>
		<input type="file" id="damagePhotos" name="damagePhotos" multiple />
		
		<div class="buttons-container">
			<button type="submit">보상신청</button>
			<form action="<c:url value='/'/>" method="get">
				<button type="submit"><spring:message code="button.return"/></button>
			</form>
		</div>
	</form>
</div>

</body>
</html>
