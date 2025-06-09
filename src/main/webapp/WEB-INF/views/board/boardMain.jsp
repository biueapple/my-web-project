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
	background-color: #f9f9f9;
	color: #333;
}

h1 {
  margin: 40px 0 15px;
  text-align: left;
  font-weight: 700;
}

table {
  width: 100%;
  border-collapse: collapse;
  margin-top: 16px;
  background-color: #fff;
  border: 1px solid #ddd;
  box-shadow: 0 0 6px rgba(0,0,0,0.05);
}

th, td {
  padding: 12px 16px;
  border: 1px solid #ddd;
  text-align: left;
  vertical-align: middle;
  font-size: 15px;
}

th {
  background-color: #f0f4f8;
  color: #555;
  font-weight: 600;
}

tr:nth-child(even) {
  background-color: #fafafa;
}

tr:hover {
  background-color: #e6f0ff;
}

.link-button {
  background: none;
  border: none;
  padding: 0;
  margin: 0;
  font: inherit;
  color: #000000;
  cursor: pointer;
  text-align: left;
  position: relative;
  transition: color 0.3s ease;
  outline: none;
}

.link-button::after {
  content: '';
  position: absolute;
  left: 0;
  bottom: -2px;
  width: 0;
  height: 2px;
  background-color: #ff6600;
  transition: width 0.3s ease;
}

.link-button:hover,
.link-button:focus {
  color: #ff6600;
  font-weight: 600;
  background: none;
}

.link-button:hover::after,
.link-button:focus::after {
  width: 100%;
}


button {
  background-color: #0d6efd;
  color: white;
  border: none;
  padding: 10px 20px;
  margin: 24px 10px 20px 0;
  border-radius: 4px;
  font-size: 14px;
  font-weight: 500;
  cursor: pointer;
  transition: background-color 0.2s ease;
}

button:hover {
  background-color: #004488;
}

.board-container {
  max-width: 900px;
  margin: 0 auto 40px;
  padding: 0 20px;
  background-color: #fff;
  border-radius: 8px;
  box-shadow: 0 0 10px rgba(0,0,0,0.05);
}
th:first-child,
td:first-child {
  width: 80px;
  text-align: center;
}

th:nth-child(3),
td:nth-child(3) {
  width: 100px;
  white-space: nowrap;
  text-align: center;
}

th:nth-child(4),
td:nth-child(4) {
  width: 130px;
  white-space: nowrap;
  text-align: center;
}

.deleted-post {
  color: #999;
  font-style: italic;
}
</style>
</head>
<body>

<%@ include file="/WEB-INF/views/header.jsp" %>
<div class="board-container">
<h1><spring:message code="board.title"/></h1>
<table>
    <thead>
    <tr>
        <th><spring:message code="label.boardId"/></th>
        <th><spring:message code="label.boardTitle"/></th>
        <th><spring:message code="label.userId"/></th>
        <th><spring:message code="label.registDate"/></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="boardIdDto" items="${noticeBoardList}">
        <tr>
            <td>${boardIdDto.boardId}</td>
            <td>
              <form method="get" action="noticeBoardSelectOne" style="display:inline;">
                <input type="hidden" name="boardId" value="${boardIdDto.boardId}" />
                <button type="submit" class="link-button ${boardIdDto.state == '삭제' ? 'deleted-post' : ''}">
                  [공지] ${boardIdDto.boardTitle}
                </button>
              </form>
            </td>
            <td>${boardIdDto.id}</td>
            <td>${boardIdDto.formattedRegistDate}</td>
        </tr>
    </c:forEach>
    <c:forEach var="boardIdDto" items="${boardList}">
        <tr>
            <td>${boardIdDto.boardId}</td>
            <td>
              <form method="get" action="boardSelectOne" style="display:inline;">
                <input type="hidden" name="boardId" value="${boardIdDto.boardId}" />
                <button type="submit" class="link-button ${boardIdDto.state == '삭제' ? 'deleted-post' : ''}">
                  ${boardIdDto.boardTitle}
                </button>
              </form>
            </td>
            <td>${boardIdDto.id}</td>
            <td>${boardIdDto.formattedRegistDate}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<c:if test="${not empty admin}">
	<button onclick="location.href='noticeBoardInsert';">공지작성</button>
</c:if>
<button onclick="location.href='boardInsert';"><spring:message code="label.Writing" /></button>
<button onclick="location.href='/airplane';"><spring:message code="label.board.Home" /></button>
</div>
</body>
</html>
