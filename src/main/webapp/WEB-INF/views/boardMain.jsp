<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><spring:message code="board.title"/></title>
</head>
<body>
<h1><spring:message code="board.title"/></h1>
<table border="1">
    <tr>
        <th><spring:message code="label.boardId"/></th>
        <th><spring:message code="label.boardTitle"/></th>
        <th><spring:message code="label.userId"/></th>
        <th><spring:message code="label.registDate"/></th>
    </tr>
    <c:forEach var="board" items="${list}">
        <tr>
            <td>${board.boardId}</td>
            <td><a href="boardSelectOne?boardId=${board.boardId}">${board.boardTitle}</a></td>
            <td>${board.userId}</td>
            <td>${board.registDate}</td>
        </tr>
    </c:forEach>
</table>
<button onclick="location.href='boardInsert';">글작성</button>
</body>
</html>