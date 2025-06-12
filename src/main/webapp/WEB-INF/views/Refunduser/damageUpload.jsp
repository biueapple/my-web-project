<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><spring:message code="label.DamageCompensation" /></title>
<style>
* {
	margin: 0;
	padding: 0;
	box-sizing: border-box;
}

body {
	font-family: Arial, sans-serif;
	background-color: #f4f4f4;
	margin: 0;
	padding: 0;
}

.page-wrapper {
	display: flex;
	justify-content: center;
	align-items: center;
	height: calc(100vh - 300px);
	padding-top: 10px;
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

input[type="file"] {
    font-family: Arial, sans-serif;
    font-size: 14px;
    color: #333;
    background-color: #f8f8f8;
    padding: 6px;
    border: 1px solid #ccc;
    border-radius: 4px;
}
</style>
</head>
<body>

<%@ include file="/WEB-INF/views/header.jsp" %>

<div class="page-wrapper">
	<div class="main-content">
		<h2><spring:message code="label.DamageCompensation" /></h2>
		<h3><spring:message code="label.UploadPhoto" /></h3>

		<form action="<c:url value='/user/damage/upload'/>" method="post" enctype="multipart/form-data">
			<input type="file" id="damagePhotos" name="damagePhotos" multiple />

			<div class="buttons-container">
				<button type="submit"><spring:message code="label.application" /></button>
				<button type="button" onclick="location.href='/airplane/user/regist';"><spring:message code="label.List" /></button>
			</div>
		</form>
	</div>
</div>

</body>
</html>
