<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><spring:message code="board.title"/></title>
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
</style>
</head>
<body>

<%@ include file="/WEB-INF/views/header.jsp" %>

<h1><spring:message code="board.title"/></h1>
<table border="1">
    <tr>
        <th><spring:message code="label.boardId"/></th>
        <th><spring:message code="label.boardTitle"/></th>
        <th><spring:message code="label.userId"/></th>
        <th><spring:message code="label.registDate"/></th>
    </tr>
    <c:forEach var="boardIdDto" items="${list}">
        <tr>
            <td>${boardIdDto.boardId}</td>
            <td><a href="boardSelectOne?boardId=${boardIdDto.boardId}">${boardIdDto.boardTitle}</a></td>
            <td>${boardIdDto.id}</td>
            <td>${boardIdDto.registDate}</td>
        </tr>
    </c:forEach>
</table>
<button onclick="location.href='boardInsert';">글작성</button>
<button onclick="location.href='/airplane';">홈으로</button>
</body>
</html>