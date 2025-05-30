<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><spring:message code="label.login"/></title>
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

.error{
	color: red;
	font-weight: bold;
	font-size: 0.9em;
}
</style>
</head>
<body>

<%@ include file="/WEB-INF/views/header.jsp" %>

<h2><spring:message code="label.login"/></h2>
<form:form modelAttribute="loginRequestCommand" method="post">
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
		<spring:message code="label.remember"/>
		<form:checkbox path="remember"/>
	</p>
	<form:errors cssClass="error"/>
	<button type="submit"><spring:message code="label.login"/></button>
</form:form>
</body>
</html>



