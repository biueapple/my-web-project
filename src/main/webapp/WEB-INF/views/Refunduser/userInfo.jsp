<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입</title>
<style>
body {
	background-color: #f0f8ff;
	color: #333;
	text-align: center;
}

table {
	margin: 20px auto;
	border-collapse: collapse;
	width: 80%;
	max-width: 600px;
	box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
	border-radius: 8px;
	overflow: hidden;
}

button {
	padding: 8px 16px;
	background-color: #2196F3;
	color: white;
	border: none;
	border-radius: 4px;
	cursor: pointer;
	margin: 10px;
}
</style>
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
		<c:forEach var="RefundUser" items="${list}">
			<tr>
				<td>${RefundUser.id}</td>
				<td>${RefundUser.gender}</td>
				<td>${RefundUser.depart}</td>
				<td>${RefundUser.arrive}</td>
				<td>${RefundUser.seat}</td>
			</tr>
		</c:forEach>


	</table>


	<form action="<c:url value='/user/regist/refund'/>" method="post"
		style="display: inline-block;">
		<input type="hidden" name="id" value="${user.userId}" />
		<button type="submit">환불요청</button>
	</form>

	<form action="<c:url value='/'/>" method="get"
		style="display: inline-block;">
		<button type="submit">이전으로</button>
	</form>
	<c:if test="${not empty message}">
		<p style="color: red; font-weight: bold;">${message}</p>
	</c:if>

</body>
</html>