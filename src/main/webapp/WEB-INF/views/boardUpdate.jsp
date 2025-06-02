<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><spring:message code="board.updateTitle"/></title>
</head>
<body>
<h2><spring:message code="board.updateTitle"/></h2>
	<form:form modelAttribute="board" method="post">
		<form:hidden path="boardId"/>
		<p>
			<spring:message code="label.boardTitle"/>
			<form:input path="boardTitle"/>
			<form:errors path="boardTitle"/>
		</p>
		<p>
			<spring:message code="label.board"/>
			<form:input path="board"/>
		</p>
		<button type="submit">
			<spring:message code="label.update"/>
		</button>
	</form:form>
</body>
</html>