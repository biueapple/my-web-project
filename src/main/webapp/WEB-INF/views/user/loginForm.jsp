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
  background-color: #f5f5f5;
  min-height: 100vh;
}

.login-container {
  max-width: 400px;
  margin: 80px auto;
  background-color: #ffffff;
  padding: 30px 40px;
  border-radius: 10px;
  box-shadow: 0 8px 16px rgba(0,0,0,0.1);
}

.login-container h2 {
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
form input[type="password"] {
  padding: 10px;
  border-radius: 6px;
  border: 1px solid #ccc;
  font-size: 14px;
}

form input[type="checkbox"] {
  transform: scale(1.2);
  margin-top: 6px;
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

<div class="login-container">
  <h2><spring:message code="label.login"/></h2>

  <form:form modelAttribute="loginRequestCommand" method="post">
    <p>
      <label for="id"><spring:message code="label.id"/></label>
      <form:input path="id" id="id" />
      <form:errors path="id" cssClass="error"/>
    </p>

    <p>
      <label for="password"><spring:message code="label.password"/></label>
      <form:password path="password" id="password" />
      <form:errors path="password" cssClass="error"/>
    </p>

    <p>
      <label>
        <form:checkbox path="remember"/> <spring:message code="label.remember"/>
      </label>
    </p>

    <form:errors cssClass="error"/>

    <button type="submit"><spring:message code="label.login"/></button>
  </form:form>
</div>

</body>
</html>
