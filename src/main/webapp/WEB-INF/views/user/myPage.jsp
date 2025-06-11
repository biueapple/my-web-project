<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
    <title><spring:message code="label.myPage" /></title>
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

.container {
    max-width: 600px;
    margin: 50px auto;
    padding: 30px;
    border: 1px solid #ddd;
    border-radius: 10px;
    text-align: left;
    background-color: #f9f9f9;
}

h2 {
	text-align: center;
}

.user-info p {
    margin: 10px 0;
    font-size: 16px;
}

.update-button {
    margin: 10px 5px;
    padding: 10px 20px;
    font-size: 14px;
    background-color: #007BFF;
    color: white;
    border: none;
    border-radius: 5px;
    cursor: pointer;
}

.update-button:hover {
    background-color: #004488;
}

.delete-button {
	margin: 10px 5px;
    padding: 10px 20px;
    font-size: 14px;
    background-color: #dc3545;
    color: white;
    border: none;
    border-radius: 5px;
    cursor: pointer;
}

.delete-button:hover {
    background-color: #c82333;
}
table {
    width: 100%;
    border-collapse: collapse;
    margin-top: 20px;
    background-color: #fff;
    box-shadow: 0 0 8px rgba(0,0,0,0.05);
    border-radius: 6px;
    overflow: hidden;
}

th, td {
    padding: 12px 16px;
    border-bottom: 1px solid #ddd;
    text-align: left;
    font-size: 15px;
}

th {
    background-color: #f0f4f8;
    color: #333;
    font-weight: 600;
}

tr:nth-child(even) {
    background-color: #fafafa;
}

tr:hover {
    background-color: #f2f9ff;
    transition: background-color 0.2s ease;
}

table button {
    background: none;
    border: none;
    padding: 0;
    font: inherit;
    color: #007bff;
    cursor: pointer;
}

table button:hover {
    color: #0056b3;
    font-weight: 600;
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

td.title-cell, th.title-cell {
  width: 250px;
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
</style>

</head>
<body>

<%@ include file="/WEB-INF/views/header.jsp"%>

<c:if test="${not empty submit}">
    <div style="padding: 10px; background-color: #d4edda; color: #155724; border: 1px solid #c3e6cb; border-radius: 5px; margin-bottom: 20px; text-align: center;">
        <spring:message code="label.changePwSubmit" />
    </div>
</c:if>

<div class="container">
	<h2><spring:message code="label.MyInformation" /></h2>
	<hr>
    <!-- 회원 정보 출력 -->
    <div class="user-info">
        <p><strong><spring:message code="label.id" /></strong> ${user.id}</p>
        <p><strong><spring:message code="label.name" /></strong> ${user.name}</p>
        <p><strong><spring:message code="label.gender" /></strong> ${user.gender}</p>
        <p><strong><spring:message code="label.age" /></strong> ${user.age}</p>
        <p><strong><spring:message code="label.phoneNumber" /></strong> ${user.phoneNumber}</p>
    </div>

    <hr>
	
	<h2><spring:message code="label.PostIWrote" /></h2>
    <hr>
    <c:if test="${empty boardList}">
    	<spring:message code="label.ThereAreNoPostsWritten" />
    </c:if>
    <c:if test="${not empty boardList}">
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
    	<c:forEach var="boardIdDto" items="${boardList}">
        <tr>
            <td>${boardIdDto.boardId}</td>
            <td class="title-cell">
              <form method="get" action="boardSelectOne" style="display:inline;">
                <input type="hidden" name="boardId" value="${boardIdDto.boardId}" />
                <button type="submit"  class="link-button" title="${boardIdDto.boardTitle}">
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
	</c:if>
	<hr>
	
    <!-- 회원정보 수정 -->
    <button class="update-button" onclick="location.href='changePassword';" ><spring:message code="label.ChangePassword" /></button>

    <!-- 회원 탈퇴 -->
    <button class="delete-button" onclick="location.href='userDelete';"><spring:message code="label.userDelete" /></button>
</div>

</body>
</html>
