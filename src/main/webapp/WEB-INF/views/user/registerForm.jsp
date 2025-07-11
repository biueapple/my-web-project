<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><spring:message code="register.title" /></title>
<style>
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

body {
  font-family: Arial, sans-serif;
  background-color: #f5f5f5;
  min-height: 100vh;
}

.register-container {
  max-width: 500px;
  margin: 60px auto;
  background-color: #ffffff;
  padding: 30px 40px;
  border-radius: 10px;
  box-shadow: 0 8px 16px rgba(0,0,0,0.1);
}

.register-container h2 {
  margin-bottom: 25px;
  text-align: center;
  color: #333;
}

form p {
  margin-bottom: 18px;
  display: flex;
  flex-direction: column;
}

form label {
  margin-bottom: 5px;
  font-weight: bold;
  color: #333;
}

form input[type="text"],
form input[type="password"],
form input[type="number"] {
  padding: 10px;
  border-radius: 6px;
  border: 1px solid #ccc;
  font-size: 14px;
}

form input[type="radio"] {
  margin-right: 8px;
}

.radio-group {
  display: flex;
  gap: 15px;
  align-items: center;
  padding-top: 5px;
}

button[type="submit"] {
  width: 100%;
  padding: 12px;
  background-color: #FF9800;
  border: none;
  border-radius: 6px;
  color: white;
  font-weight: bold;
  font-size: 16px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

button[type="submit"]:hover {
  background-color: #e68900;
}

.error {
  color: red;
  font-weight: bold;
  font-size: 0.85em;
  margin-top: 5px;
}
</style>
</head>
<body>

<%@ include file="/WEB-INF/views/header.jsp" %>

<div class="register-container">
	<h2><spring:message code="register.title"/></h2>

	<form:form modelAttribute="userRegisterRequest" method="post">
		
		<p>
			<label for="id"><spring:message code="label.id"/></label>
			<form:input path="id" id="id"/>
			<form:errors path="id" cssClass="error"/>
		</p>

		<p>
			<label for="password"><spring:message code="label.password"/></label>
			<form:password path="password" id="password"/>
			<form:errors path="password" cssClass="error"/>
		</p>

		<p>
			<label for="passwordConfirm"><spring:message code="label.passwordConfirm"/></label>
			<form:password path="passwordConfirm" id="passwordConfirm"/>
			<form:errors path="passwordConfirm" cssClass="error"/>
		</p>

		<p>
			<label for="name"><spring:message code="label.name"/></label>
			<form:input path="name" id="name"/>
			<form:errors path="name" cssClass="error"/>
		</p>

		<p>
			<label><spring:message code="label.gender"/></label>
			<div class="radio-group">
				<form:radiobuttons path="gender" items="${genderOptions}" />
			</div>
			<form:errors path="gender" cssClass="error"/>
		</p>

		<p>
			<label for="age"><spring:message code="label.age"/></label>
			<form:input path="age" id="age" type="number"/>
			<form:errors path="age" cssClass="error"/>
		</p>

		<p>
			<label for="phoneNumber"><spring:message code="label.phoneNumber"/></label>
			<form:input path="phoneNumber" id="phoneNumber"
			oninput="this.value = this.value.replace(/[^0-9-]/g, '')"
	            placeholder="010-1234-5678"/>
			<form:errors path="phoneNumber" cssClass="error"/>
		</p>

			<button type="submit">
				<spring:message code="button.submit" />
			</button>
	
	</form:form>
	
</body>
</html>
