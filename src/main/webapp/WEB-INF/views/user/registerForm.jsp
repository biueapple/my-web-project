<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><spring:message code="register.title"/></title>
<style>
.error {
	color: red;
	font-weight: bold;
	font-size: 0.9em;
}
</style>
</head>
<body>
	<h2><spring:message code="register.title"/></h2>
	<form:form modelAttribute="userRegisterRequest" method="post">
		
		<p>
			<spring:message code="label.id"/>
			<form:input path="id"/>
			<form:errors path="id" cssClass="error"/>
		</p>

		<p>
			<spring:message code="label.password"/>
			<form:password path="password"/>
			<form:errors path="password" cssClass="error"/>
		</p>

		<p>
			<spring:message code="label.passwordConfirm"/>
			<form:password path="passwordConfirm"/>
			<form:errors path="passwordConfirm" cssClass="error"/>
		</p>

		<p>
			<spring:message code="label.name"/>
			<form:input path="name"/>
			<form:errors path="name" cssClass="error"/>
		</p>

		<p>
			<spring:message code="label.gender"/>
			<form:radiobuttons path="gender" items="${genderOptions}"/>
			<form:errors path="gender" cssClass="error"/>
		</p>

		<p>
			<spring:message code="label.age"/>
			<form:input path="age" type="number"/>
			<form:errors path="age" cssClass="error"/>
		</p>

		<p>
			<spring:message code="label.phoneNumber"/>
			<form:input path="phoneNumber"/>
			<form:errors path="phoneNumber" cssClass="error"/>
		</p>

		<button type="submit">
			<spring:message code="button.submit"/>
		</button>

	</form:form>
</body>
</html>