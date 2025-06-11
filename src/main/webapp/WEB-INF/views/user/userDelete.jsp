<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><spring:message code="label.userDelete" /></title>
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
    font-weight: bold;
    font-size: 0.9em;
}

.container {
    max-width: 400px;
    margin: 50px auto;
    padding: 30px;
    border: 1px solid #ddd;
    border-radius: 10px;
    background-color: #f9f9f9;
    text-align: center;
}

input[type="password"] {
    width: 100%;
    padding: 10px;
    margin-top: 5px;
    margin-bottom: 10px;
    border: 1px solid #ccc;
    border-radius: 4px;
}

input[type="submit"] {
    padding: 10px 20px;
    width: 100%;
    background-color: #f44336;
    color: white;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    font-size: 15px;
}

input[type="submit"]:hover {
    background-color: #d32f2f;
}
</style>
</head>
<body>

<%@ include file="/WEB-INF/views/header.jsp"%>

<div class="container">
    <h2><spring:message code="label.userDelete" /></h2>
    
    <c:if test="${not empty error}">
   		<div style="color:red; margin-bottom:10px;">
     	   탈퇴 실패
    	</div>
	</c:if>

    <form:form modelAttribute="userDeleteCommand"
               action="${pageContext.request.contextPath}/delete" method="post">
        <label for="password"><spring:message code="label.CurrentPassword" /></label>
        <form:password path="password" id="password"/>
        <form:errors path="password" cssClass="error" />
        <br/>
        <input type="submit" value="<spring:message code='label.userDelete' />" />
    </form:form>
</div>

</body>
</html>
