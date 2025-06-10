<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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

.error {
	color: red;
	display: block;
	margin-top: 5px;
	font-size: 0.9em;
}

/* 추가된 스타일 */
.container {
	max-width: 500px;
	margin: 50px auto;
	padding: 30px;
	border: 1px solid #ddd;
	border-radius: 10px;
	background-color: #f9f9f9;
}

.title {
	text-align: center;
	font-size: 24px;
	font-weight: bold;
	margin-bottom: 20px;
	line-height: 1.4;
}

form div {
	margin-bottom: 15px;
	text-align: left;
}


input[type="password"] {
	width: 100%;
	padding: 8px;
	border: 1px solid #ccc;
	border-radius: 4px;
}

input[type="submit"] {
	display: block;
	width: 100%;
	padding: 10px;
	background-color: #4CAF50;
	color: white;
	border: none;
	border-radius: 5px;
	font-size: 16px;
	cursor: pointer;
}

input[type="submit"]:hover {
	background-color: #45a049;
}
</style>
</head>
<body>

<%@ include file="/WEB-INF/views/header.jsp"%>

<div class="container">
	<h2 class="title"><spring:message code="label.ChangePassword" /></h2>

	<form:form modelAttribute="passwordChangeForm" method="post" action="${pageContext.request.contextPath}/changePassword">
	    <div>
	        <label><spring:message code="label.CurrentPassword" /></label>
	        <form:password path="currentPassword" required="required"/>
	        <form:errors path="currentPassword" cssClass="error"/>
	    </div>
	    <div>
	        <label><spring:message code="label.NewPassword" /></label>
	        <form:password path="newPassword" required="required"/>
	        <form:errors path="newPassword" cssClass="error"/>
	    </div>
	    <div>
	        <label><spring:message code="label.NewPasswordCheck" /></label>
	        <form:password path="newPasswordConfirm" required="required"/>
	        <form:errors path="newPasswordConfirm" cssClass="error"/>
	    </div>
	    <input type="submit" value="<spring:message code='label.ChangePassword' />"/>
	</form:form>
</div>

</body>
</html>
