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
  background-color: #f9f9f9;
  color: #333;
  padding: 0;
}

.center-wrapper {
  max-width: 600px;
  margin: 40px auto 0;
  padding: 24px 30px;
  background-color: #fff;
  border-radius: 8px;
  box-shadow: 0 0 10px rgba(0,0,0,0.05);
  
}

.center-wrapper h2 {
  margin-bottom: 24px;
  font-weight: 700;
  color: #222;
  text-align: left;
}

.center-wrapper p {
  margin-bottom: 16px;
  font-size: 15px;
  color: #444;
}

.center-wrapper label {
  display: block;
  margin-bottom: 6px;
  font-weight: 600;
  color: #555;
}

.center-wrapper input[type="text"],
.center-wrapper input[type="hidden"],
.center-wrapper input[type="password"],
.center-wrapper input[type="email"],
.center-wrapper input[type="number"] {
  width: 100%;
  padding: 10px 14px;
  border: 1px solid #ddd;
  border-radius: 4px;
  font-size: 14px;
  box-sizing: border-box;
  transition: border-color 0.3s ease;
}

.center-wrapper input[type="text"]:focus,
.center-wrapper input[type="number"]:focus,
.center-wrapper input[type="email"]:focus,
.center-wrapper input[type="password"]:focus {
  outline: none;
  border-color: #0d6efd;
  box-shadow: 0 0 6px rgba(13,110,253,0.3);
}

.error {
  color: #d9534f;
  font-size: 13px;
  margin-top: 4px;
  display: block;
}

.center-wrapper button {
  background-color: #0d6efd;
  color: white;
  border: none;
  padding: 10px 20px;
  margin-top: 24px;
  margin-right: 10px;
  border-radius: 4px;
  font-size: 14px;
  font-weight: 500;
  cursor: pointer;
  transition: background-color 0.2s ease;
}

.center-wrapper button:hover {
  background-color: #004488;
}

.center-wrapper button[type="button"] {
  background-color: #6c757d;
}

.center-wrapper button[type="button"]:hover {
  background-color: #565e64;
}
.center-wrapper .textarea-board {
  width: 100%;
  height: 300px;
  padding: 10px 14px;
  font-size: 14px;
  border: 1px solid #ddd;
  border-radius: 4px;
  resize: none;
  box-sizing: border-box;
  transition: border-color 0.3s ease;
}

.center-wrapper .textarea-board:focus {
  outline: none;
  border-color: #0d6efd;
  box-shadow: 0 0 6px rgba(13,110,253,0.3);
}
</style>
</head>
<body>

<%@ include file="/WEB-INF/views/header.jsp" %>

<div class="center-wrapper">
<h2><spring:message code="board.insertTitle"/></h2>
	<form:form modelAttribute="boardDto" method="post">
		<form:hidden path="userId" value="${userId}"/>
		<p>
			<spring:message code="label.boardTitle"/>
			<form:input path="boardTitle"/>
			<form:errors path="boardTitle" cssClass="error"/>
		</p>
		<p>
			<spring:message code="label.board"/>
			<form:textarea path="board" cssClass="textarea-board"/>
		</p>
		<button type="submit">
			<spring:message code="label.submit"/>
		</button>
		<button type="button" onclick="location.href='board';">
			<spring:message code="label.cancellation" />
		</button>
	</form:form>
</div>
</body>
</html>