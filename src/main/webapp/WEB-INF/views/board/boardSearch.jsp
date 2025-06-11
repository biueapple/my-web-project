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

td.title-cell, th.title-cell {
  width: 300px;
  max-width: 300px;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}

.title-cell button {
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  display: inline-block;
  max-width: 100%;
}

.board-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin: 40px 0 15px;
}

.board-header h1 {
  margin: 0;
  font-weight: 700;
}

.search-form {
  display: flex;
  align-items: center;
  gap: 8px;
  background-color: transparent;  /* 큰 테두리 없애기 */
  padding: 0;
  border: none;  /* 큰 테두리 제거 */
  box-shadow: none;
}

.search-select,
.search-input {
  padding: 6px 12px;
  font-size: 14px;
  border: 1px solid #ccc;  /* 테두리 살리기 */
  border-radius: 4px;
  outline: none;
  transition: border-color 0.3s ease;
}

.search-select:focus,
.search-input:focus {
  border-color: #0d6efd;  /* 포커스 시 파란색 테두리 */
  box-shadow: 0 0 5px rgba(13, 110, 253, 0.5);
}

.search-input {
  width: 180px;
}

.button-search {
  background-color: #0d6efd;
  color: white;
  border: none;
  padding: 6px 14px;
  font-size: 13px;
  font-weight: 600;
  cursor: pointer;
  border-radius: 4px;
  transition: background-color 0.2s ease;
}

.button-search:hover {
  background-color: #004488;
}
</style>
</head>
<body>

<%@ include file="/WEB-INF/views/header.jsp" %>
<div class="board-container">
<div class="board-header">
  <h1><spring:message code="board.title"/></h1>
  <form action="<c:url value='/searchBoard'/>" method="post" class="search-form">
    <select name="searchType" class="search-select">
      <option value="boardTitle">제목</option>
      <option value="board">내용</option>
      <option value="id">작성자</option>
    </select>
    <input type="search" name="keyword" placeholder="검색어 입력" class="search-input">
    <button type="submit" class="button-search">검색</button>
  </form>
</div>
<table>
    <thead>
    <tr>
        <th><spring:message code="label.boardId"/></th>
        <th class="title-cell"><spring:message code="label.boardTitle"/></th>
        <th><spring:message code="label.userId"/></th>
        <th><spring:message code="label.registDate"/></th>
    </tr>
    </thead>
    <tbody>
     <c:forEach var="boardIdDto" items="${noticeList}">
        <tr>
            <td>${boardIdDto.boardId}</td>
            <td class="title-cell">
              <form method="get" action="noticeBoardSelectOne" style="display:inline;">
                <input type="hidden" name="boardId" value="${boardIdDto.boardId}" />
                <button type="submit" class="link-button ${boardIdDto.state == '삭제' ? 'deleted-post' : ''}" title="${boardIdDto.boardTitle}">
                  <spring:message code="board.notification"/> ${boardIdDto.boardTitle}
                </button>
              </form>
            </td>
            <td>${boardIdDto.id}</td>
            <td>${boardIdDto.formattedRegistDate}</td>
        </tr>
    </c:forEach>
    <c:forEach var="boardIdDto" items="${list}">
        <tr>
            <td>${boardIdDto.boardId}</td>
            <td class="title-cell">
              <form method="get" action="boardSelectOne" style="display:inline;">
                <input type="hidden" name="boardId" value="${boardIdDto.boardId}" />
                <button type="submit" class="link-button" title="${boardIdDto.boardTitle}">
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
	<button onclick="location.href='noticeBoardInsert';"><spring:message code="board.CreateNotice"/></button>
</c:if>
<button onclick="location.href='boardInsert';"><spring:message code="label.Writing" /></button>
<button onclick="location.href='/airplane';"><spring:message code="label.board.Home" /></button>
</div>
</body>
</html>
