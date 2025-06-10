<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>비밀번호 변경</title>
    <style>
        .error {
            color: red;
        }
    </style>
</head>
<body>
<h2>비밀번호 변경</h2>

<form:form modelAttribute="passwordChangeForm" method="post" action="${pageContext.request.contextPath}/changePassword">
    <div>
        <label>현재 비밀번호:</label>
        <form:password path="currentPassword" required="required"/>
        <form:errors path="currentPassword" cssClass="error"/>
    </div>
    <div>
        <label>새 비밀번호:</label>
        <form:password path="newPassword" required="required"/>
        <form:errors path="newPassword" cssClass="error"/>
    </div>
    <div>
        <label>새 비밀번호 확인:</label>
        <form:password path="newPasswordConfirm" required="required"/>
        <form:errors path="newPasswordConfirm" cssClass="error"/>
    </div>
    <input type="submit" value="비밀번호 변경"/>
</form:form>

</body>
</html>