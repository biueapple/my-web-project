<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><spring:message code="register.title" /></title>
<style>
body {
	background-color: #f0f8ff;
	color: #333;
	text-align: center;
}

.error {
	color: red;
	font-weight: bold;
	font-size: 0.9em;
}

button {
	padding: 8px 16px;
	background-color: #2196F3;
	color: white;
	border: none;
	border-radius: 4px;
	cursor: pointer;
	margin: 10px;
}
</style>
</head>
<body>


	<%-- <form:form modelAttribute="userRegisterRequest" method="post">
		<p>
			<spring:message code="label.username" />
			<form:input path="userId" />
			<form:errors path="userId" cssClass="error" />
		</p>

		<p>
			<spring:message code="label.birthDate" />
			<form:input path="birthDate" type="date" />
			<form:errors path="birthDate" cssClass="error" />
		</p>
		<p>
			<spring:message code="label.gender" />
			<form:radiobuttons path="gender" items="${genderOptions}" />
			<form:errors path="gender" cssClass="error" />
		</p>
		<p>
			<spring:message code="label.country" />
			<form:select path="country" items="${countryOptions}" />
			<form:errors path="country" cssClass="error" />
		</p>

		<button type="submit">
			<spring:message code="Confirmbutton.submit" />
			 --%>
			
		</button>
			<button type="button" onclick="history.back()">이전으로</button>

		

	<c:if test="${not empty message}">
		<p style="color: red; font-weight: bold;">${message}</p>
	</c:if>
</body>
</html>


