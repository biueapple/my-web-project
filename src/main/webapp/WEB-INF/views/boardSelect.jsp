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
		<spring:message code="label.registDate"/>
		${boardIdDto.formattedRegistDateOne}
	</p>
	<p>
		<spring:message code="label.board"/>
		${boardIdDto.board}
	</p>
<button onclick="location.href='board';"><spring:message code="label.List" /></button>
<c:if test="${not empty notice}">
	<c:if test="${not empty admin}">
		<button onclick="location.href='noticeBoardUpdate?boardId=${boardIdDto.boardId}';"><spring:message code="label.NoticeModification" /></button>
		<button onclick="location.href='noticeBoardDelete?boardId=${boardIdDto.boardId}';"><spring:message code="label.DeleteNotice" /></button>
	</c:if>
</c:if>
<c:if test="${empty notice}">
	<c:if test="${not empty matchUser}">
	<button onclick="location.href='boardUpdate?boardId=${boardIdDto.boardId}';"><spring:message code="label.correction" /></button>
	<button onclick="location.href='boardDelete?boardId=${boardIdDto.boardId}';"><spring:message code="label.DeleteText" /></button><br>
	</c:if>
</c:if>
</body>
</html>