<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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

<h2><spring:message code="board.selectTitle"/></h2>
	<p>
		<spring:message code="label.boardTitle"/>
		${boardIdDto.boardTitle}
	</p>
	<p>
		<spring:message code="label.userId"/>
		${boardIdDto.id}
	</p>
	<p>
		<spring:message code="label.board"/>
		${boardIdDto.board}
	</p>
	<p>
		<spring:message code="label.registDate"/>
		${boardIdDto.registDate}
	</p>
<button onclick="location.href='board';">목록</button>
<c:if test="${not empty notice}">
	<c:if test="${not empty admin}">
		<button onclick="location.href='noticeBoardUpdate?boardId=${boardIdDto.boardId}';">공지수정</button>
		<button onclick="location.href='noticeBoardDelete?boardId=${boardIdDto.boardId}';">공지삭제</button>
	</c:if>
</c:if>
<c:if test="${empty notice}">
	<button onclick="location.href='boardUpdate?boardId=${boardIdDto.boardId}';">수정</button>
	<button onclick="location.href='boardDelete?boardId=${boardIdDto.boardId}';">삭제</button><br>
	<c:if test="${not empty userNotMatchError}">
	${userNotMatchError}
	</c:if>
</c:if>
</body>
</html>