<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:if test="${empty userId}">
    <p style="color:red; font-weight:bold;">
        ${refund}
    </p>
</c:if>

<form action="<c:url value='/'/>" method="get">
    <button type="submit"><spring:message code="label.Home"/></button>
</form>
</body>
</html>