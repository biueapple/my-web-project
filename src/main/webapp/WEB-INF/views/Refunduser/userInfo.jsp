<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><spring:message code="label.Main" /></title>
<style>
  /* 기본 초기화 */
  * {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
  }

  body {
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    background-color: #f7f9fc;
    color: #333;
    text-align: center;
    padding: 40px 15px 60px 15px;
  }

  /* 헤더 영역 여백 유지 */
  header {
    margin-bottom: 30px;
  }

  h2, h3 {
    margin-bottom: 18px;
    color: #2c3e50;
    font-weight: 700;
  }

  /* 테이블 스타일 */
  table {
    margin: 0 auto 40px auto;
    width: 90%;
    max-width: 900px;
    border-collapse: separate;
    border-spacing: 0;
    background: #fff;
    border-radius: 12px;
    box-shadow: 0 8px 25px rgba(0,0,0,0.1);
    overflow: hidden;
    font-size: 0.95rem;
  }

  thead tr {
    background-color: #2c3e50;
    color: #fff;
    text-transform: uppercase;
    font-weight: 700;
  }

  th, td {
    padding: 14px 20px;
    border-bottom: 1px solid #eaeaea;
    text-align: center;
  }

  tbody tr:hover {
    background-color: #f1f5f9;
  }

  /* 체크박스 칸 고정 너비 */
  th:first-child, td:first-child {
    width: 60px;
  }

  /* 버튼 공통 스타일 */
  button {
    background-color: #2980b9;
    color: white;
    border: none;
    padding: 12px 28px;
    border-radius: 30px;
    font-size: 1rem;
    cursor: pointer;
    margin: 0 8px 20px 8px;
    transition: background-color 0.25s ease;
    box-shadow: 0 3px 6px rgba(41, 128, 185, 0.4);
  }

  button:hover {
    background-color: #1c5980;
  }

  /* form 구분 (inline-block 유지) */
  form {
    display: inline-block;
  }

  /* 에러 메시지 */
  p[style*="color: red"] {
    font-weight: 700;
    margin-top: 25px;
    font-size: 1.1rem;
    color: #d32f2f;
  }
</style>
</head>
<body>
	<%@ include file="/WEB-INF/views/header.jsp" %>

	<h2><spring:message code="label.Info.CheckReservationInformation" /></h2>
	<h3><spring:message code="label.Info.MemberInformation" /></h3>

	<form action="<c:url value='/user/regist/refund'/>" method="post">
		<table>
			<thead>
				<tr>
					<th>선택</th>
					<th><spring:message code="label.Info.username" /></th>
					<th><spring:message code="label.Info.gender" /></th>
					<th><spring:message code="label.Info.Departure" /></th>
					<th><spring:message code="label.Info.Destination" /></th>
					<th><spring:message code="label.Info.SeatNumber" /></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="RefundUser" items="${list}">
					<c:if test="${RefundUser.state=='정상'}">
						<tr>
							<td><input type="checkbox" name="ids" value="${RefundUser.id}"/></td>
							<td>${RefundUser.userId}</td>
							<td>${RefundUser.gender}</td>
							<td>${RefundUser.depart}</td>
							<td>${RefundUser.arrive}</td>
							<td>${RefundUser.seat}</td>
						</tr>
					</c:if>
				</c:forEach>
			</tbody>
		</table>

		<input type="hidden" name="id" value="${user.userId}" />
		<button type="submit"><spring:message code="label.Info.RefundRequest" /></button>
	</form>
	

	<form action="<c:url value='/'/>" method="get" style="display: inline-block;">
		<button type="submit"><spring:message code="label.Home" /></button>
	</form>

	<form action="<c:url value='/user/damage'/>" method="get" style="display: inline-block;">
		<input type="hidden" name="id" value="${user.userId}" />
		<button type="submit">보상요청</button>
	</form>

	<c:if test="${not empty message}">
		<p style="color: red; font-weight: bold;">${message}</p>
	</c:if>
</body>
</html>
