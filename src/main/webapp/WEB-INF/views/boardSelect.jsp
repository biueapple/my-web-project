<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><spring:message code="board.title"/></title>
</head>
<body>
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
<button onclick="location.href='boardUpdate?boardId=${boardIdDto.boardId}';">수정</button>
<button onclick="location.href='boardDelete?boardId=${boardIdDto.boardId}';">삭제</button><br>
<c:if test="${not empty userNotMatchError}">
	${userNotMatchError}
</c:if>
</body>
</html>