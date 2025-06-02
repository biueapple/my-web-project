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
.error {
    color: red;
    font-weight: bold;
    font-size: 0.9em;
}
</style>
</head>
<body>

<h2><spring:message code="label.userDelete" /></h2>

<!-- 회원 탈퇴 폼 -->
<form:form modelAttribute="userDeleteCommand"
           action="${pageContext.request.contextPath}/delete"
           method="post">
    비밀번호: <form:password path="password"/>
    <form:errors path="password" cssClass="error" />
    <br/>
    <input type="submit" value="회원 탈퇴" />
</form:form>

</body>
</html>