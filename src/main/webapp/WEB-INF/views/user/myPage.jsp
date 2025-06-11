<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
    <title><spring:message code="label.myPage" /></title>
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

.container {
    max-width: 600px;
    margin: 50px auto;
    padding: 30px;
    border: 1px solid #ddd;
    border-radius: 10px;
    text-align: left;
    background-color: #f9f9f9;
}

.user-info p {
    margin: 10px 0;
    font-size: 16px;
}

button {
    margin: 10px 5px;
    padding: 10px 20px;
    font-size: 14px;
    background-color: #007BFF;
    color: white;
    border: none;
    border-radius: 5px;
    cursor: pointer;
}

button:hover {
    background-color: #004488;
}

.delete-button {
    background-color: #dc3545;
    color: white;
}

.delete-button:hover {
    background-color: #c82333;
}
</style>
</head>
<body>

<%@ include file="/WEB-INF/views/header.jsp"%>

<c:if test="${not empty submit}">
    <div style="padding: 10px; background-color: #d4edda; color: #155724; border: 1px solid #c3e6cb; border-radius: 5px; margin-bottom: 20px;">
        <spring:message code="label.changePwSubmit" />
    </div>
</c:if>

<div class="container">
    <!-- 회원 정보 출력 -->
    <div class="user-info">
        <p><strong><spring:message code="label.id" /></strong> ${user.id}</p>
        <p><strong><spring:message code="label.name" /></strong> ${user.name}</p>
        <p><strong><spring:message code="label.gender" /></strong> ${user.gender}</p>
        <p><strong><spring:message code="label.age" /></strong> ${user.age}</p>
        <p><strong><spring:message code="label.phoneNumber" /></strong> ${user.phoneNumber}</p>
    </div>

    <hr>

    <!-- 회원정보 수정 -->
    <button onclick="location.href='changePassword';" ><spring:message code="label.ChangePassword" /></button>

    <!-- 회원 탈퇴 -->
    <button class="delete-button" onclick="location.href='userDelete';"><spring:message code="label.userDelete" /></button>
</div>

</body>
</html>
