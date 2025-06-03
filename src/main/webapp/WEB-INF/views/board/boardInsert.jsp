<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><spring:message code="board.insertTitle"/></title>
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

<h2><spring:message code="board.insertTitle"/></h2>
	<form:form modelAttribute="board" method="post">
		<form:hidden path="userId" value="${userId}"/>
		<p>
			<spring:message code="label.boardTitle"/>
			<form:input path="boardTitle"/>
			<form:errors path="boardTitle" cssClass="error"/>
		</p>
		<p>
			<spring:message code="label.board"/>
			<form:input path="board"/>
		</p>
		<button type="submit">
			<spring:message code="label.submit"/>
		</button>
		<button type="button" onclick="location.href='board';">
			<spring:message code="label.cancellation" />
		</button>
	</form:form>
</body>
</html>