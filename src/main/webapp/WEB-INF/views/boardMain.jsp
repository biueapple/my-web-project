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

.deleted-post {
	color: gray;
	font-style: italic;
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
    <c:forEach var="boardIdDto" items="${noticeBoardList}">
        <tr>
            <td>${boardIdDto.boardId}</td>
            <td><a href="noticeBoardSelectOne?boardId=${boardIdDto.boardId}" class="${boardIdDto.state == '삭제' ? 'deleted-post' : ''}">
            	[공지] ${boardIdDto.boardTitle}
            </a></td>
            <td>${boardIdDto.id}</td>
            <td>${boardIdDto.formattedRegistDate}</td>
        <tr>
    </c:forEach>
    <c:forEach var="boardIdDto" items="${boardList}">
        <tr>
            <td>${boardIdDto.boardId}</td>
            <td><a href="boardSelectOne?boardId=${boardIdDto.boardId}" class="${boardIdDto.state == '삭제' ? 'deleted-post' : ''}">
            	${boardIdDto.boardTitle}
            </a></td>
            <td>${boardIdDto.id}</td>
            <td>${boardIdDto.formattedRegistDate}</td>
        </tr>
    </c:forEach>
</table>
<c:if test="${not empty admin}">
	<button onclick="location.href='noticeBoardInsert';">공지작성</button>
</c:if>
<button onclick="location.href='boardInsert';"><spring:message code="label.Writing" /></button>
<button onclick="location.href='/airplane';"><spring:message code="label.board.Home" /></button>
</body>
</html>