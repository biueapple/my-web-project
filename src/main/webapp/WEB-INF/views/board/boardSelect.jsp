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
.detail-row {
  display: flex;
  margin-bottom: 16px;
}

.detail-label {
  width: 120px;
  font-weight: 600;
  color: #555;
}

.detail-value {
  flex: 1;
  color: #222;
}

.detail-content {
  margin-top: 20px;
}

.content-box {
  white-space: pre-wrap;
  border: 1px solid #ccc;
  border-radius: 6px;
  padding: 0px;
  background-color: #fcfcfc;
  font-size: 16px;
  line-height: 1.3;
  color: #333;
  min-height: 200px;
  word-break: break-word;
}

.button-box {
  margin-top: 30px;
  text-align: left;
}

.detail-value {
  flex: 1;
  color: #222;
  word-break: break-word;
  white-space: normal;
}
</style>
</head>
<body>

<%@ include file="/WEB-INF/views/header.jsp" %>

<div class="center-wrapper">
  <h2><spring:message code="board.selectTitle"/></h2>
  
  <div class="detail-row">
    <span class="detail-label"><spring:message code="label.boardTitle"/></span>
    <span class="detail-value">${boardIdDto.boardTitle}</span>
  </div>
  
  <div class="detail-row">
    <span class="detail-label"><spring:message code="label.userId"/></span>
    <span class="detail-value">${boardIdDto.id}</span>
  </div>
  
  <div class="detail-row">
    <span class="detail-label"><spring:message code="label.registDate"/></span>
    <span class="detail-value">${boardIdDto.formattedRegistDateOne}</span>
  </div>
  
  <div class="detail-content">
    <span class="detail-label"><spring:message code="label.board"/></span>
    <div class="content-box">
      ${boardIdDto.board}
    </div>
  </div>

  <div class="button-box">
    <button onclick="location.href='board';"><spring:message code="label.List" /></button>
    
    <c:if test="${not empty notice}">
      <c:if test="${not empty admin}">
        <button onclick="location.href='noticeBoardUpdate?boardId=${boardIdDto.boardId}';">
          <spring:message code="label.NoticeModification" />
        </button>
        <button onclick="location.href='noticeBoardDelete?boardId=${boardIdDto.boardId}';">
          <spring:message code="label.DeleteNotice" />
        </button>
      </c:if>
    </c:if>
    
    <c:if test="${empty notice}">
      <c:if test="${not empty matchUser}">
        <button onclick="location.href='boardUpdate?boardId=${boardIdDto.boardId}';">
          <spring:message code="label.correction" />
        </button>
        <button onclick="location.href='boardDelete?boardId=${boardIdDto.boardId}';">
          <spring:message code="label.DeleteText" />
        </button>
      </c:if>
    </c:if>
  </div>
</div>
</body>
</html>