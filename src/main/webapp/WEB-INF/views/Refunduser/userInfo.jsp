<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
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
	<h2><spring:message code="label.Info.CheckReservationInformation"/></h2>
	<h3><spring:message code="label.Info.MemberInformation"/></h3>
	<table border="1">
		<tr>
			<th><spring:message code="label.Info.username"/></th>
			<th><spring:message code="label.Info.gender"/></th>
			<th><spring:message code="label.Info.Departure"/></th>
			<th><spring:message code="label.Info.Destination"/></th>
			<th><spring:message code="label.Info.SeatNumber"/></th>
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
		<button type="submit"><spring:message code="label.Info.RefundRequest"/></button>
	</form>

	<form action="<c:url value='/'/>" method="get"
		style="display: inline-block;">
		<button type="submit"><spring:message code="label.Home"/></button>
	</form>
		<form action="<c:url value='/user/damage/upload'/>" method="get"
		style="display: inline-block;">
		<input type="hidden" name="id" value="${user.userId}" />
		<button type="submit">보상요청</button>
	</form>
	<c:if test="${not empty message}">
		<p style="color: red; font-weight: bold;">${message}</p>
	</c:if>

</body>
</html>