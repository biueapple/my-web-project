<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입</title>
</head>
<body>
<h2>예매 정보 확인</h2>
<h3>회원정보</h3>
	<table border="1">
		<tr>
			<th>이름</th>
			<th>성별</th>
			<th>출발지</th>
			<th>도착지</th>
			<th>좌석번호</th>
		</tr>

		<c:if test="${not empty user}">
			<tr>
				<td>${user.username}</td>
				<td>${user.gender}</td>
				<td>${user.depart}</td>
				<td>${user.arrive}</td>
				<td>${user.seat}</td>
			</tr>
		</c:if>
	
	</table>
	
<form action="<c:url value='/user/regist/refund'/>" method="post" style="display: inline-block;">
    <input type="hidden" name="username" value="${user.username}"/>
    <button type="submit" style="padding: 5px 10px; background-color: #2196F3; color: white;font-size: 13px; margin-right: 10px;">환불요청</button>
</form>

<form action="<c:url value='/'/>" method="get" style="display: inline-block;">
    <button type="submit" style="padding: 5px 10px;background-color: #2196F3; color: white;font-size: 13px;">홈으로</button>
</form>

		</body>
</html>