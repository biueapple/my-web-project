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
/* 기존 스타일 그대로 유지 */
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

.main-content {
	padding: 30px 20px;
	text-align: center;
}

.main-content h2, .main-content h3 {
	color: #2c3e50;
	margin-bottom: 20px;
}

.reservation-table {
	margin: 0 auto 30px auto;
	border-collapse: collapse;
	width: 90%;
	max-width: 1000px;
	background-color: #ffffff;
	box-shadow: 0 2px 10px rgba(0, 0, 0, 0.05);
	border-radius: 8px;
	overflow: hidden;
}

.reservation-table thead {
	background-color: #FF9800;
	color: #ffffff;
}

.reservation-table th, .reservation-table td {
	padding: 12px 16px;
	text-align: center;
	border-bottom: 1px solid #e0e0e0;
	vertical-align: middle;
}

.reservation-table tbody tr:hover {
	background-color: #f8f9fa;
}

.buttons-container {
	display: flex;
	justify-content: space-between;
	align-items: center;
	max-width: 1000px;
	margin: 0 auto 20px auto;
	padding: 0 10px;
	box-sizing: border-box;
}

.left-buttons, .right-buttons {
	display: flex;
	gap: 10px;
	align-items: center;
}

button {
	background-color: #2980b9;
	color: #ffffff;
	border: none;
	padding: 10px 22px;
	border-radius: 6px;
	font-size: 15px;
	cursor: pointer;
	transition: background-color 0.3s ease;
	height: 40px;
	line-height: 20px;
	display: flex;
	align-items: center;
	justify-content: center;
}

button:hover {
	background-color: #1f5f85;
}

.message {
	text-align: center;
	margin-top: 20px;
	color: #d32f2f;
	font-weight: bold;
}
</style>
<script>
	function submitInsuranceForm(id) {
		document.getElementById("insuranceId").value = id;
		document.getElementById("insuranceForm").submit();
	}

	function submitDamageForm() {
		const checkedBoxes = document.querySelectorAll("input[name='ids']:checked");

		if (checkedBoxes.length === 0) {
			alert("피해보상을 신청할 예약을 선택해주세요.");
			return;
		}

		const form = document.getElementById("damageForm");
		form.innerHTML = '<input type="hidden" name="id" value="${user.userId}" />';

		checkedBoxes.forEach(box => {
			const hiddenInput = document.createElement("input");
			hiddenInput.type = "hidden";
			hiddenInput.name = "ids";
			hiddenInput.value = box.value;
			form.appendChild(hiddenInput);
		});

		form.submit();
	}
</script>
</head>
<body>

	<%@ include file="/WEB-INF/views/header.jsp"%>

	<div class="main-content">
		<h2>
			<spring:message code="label.Info.CheckReservationInformation" />
		</h2>
		<h3>
			<spring:message code="label.Info.MemberInformation" />
		</h3>

		<!-- 환불 요청 form -->
		<form id="refundForm" action="<c:url value='/user/regist/refund'/>"
			method="post">
			<table class="reservation-table">
				<thead>
					<tr>
						<th>선택</th>
						<th><spring:message code="label.Info.username" /></th>
						<th><spring:message code="label.Info.gender" /></th>
						<th><spring:message code="label.Info.Departure" /></th>
						<th><spring:message code="label.Info.Destination" /></th>
						<th><spring:message code="label.Info.SeatNumber" /></th>
						<th><spring:message code="label.InsuranceList" /></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="item" items="${list}">
						<c:if test="${item.state == '정상'}">
							<tr>
								<td><input type="checkbox" name="ids" value="${item.id}" /></td>
							
								<td>${item.userName}</td>
							
								<td>${item.gender}</td>
								<td>${item.depart}</td>
								<td>${item.arrive}</td>
								<td>${item.seat}</td>
								<td>
									<button type="button" style="position: relative; left: 35px;"
										onclick="submitInsuranceForm('${item.id}')">보험 확인</button>
								</td>
							</tr>
						</c:if>
					</c:forEach>
				</tbody>
			</table>

			
		</form>

		<!-- 버튼 영역 -->
		<div class="buttons-container">
			<div class="left-buttons">
				<!-- 환불 요청 버튼 -->
				<button type="submit" form="refundForm">
					<spring:message code="label.Info.RefundRequest" />
				</button>

				<!-- 피해보상 요청 버튼 -->
				<button type="button" onclick="submitDamageForm()">
					<spring:message code="label.damageRequest" />
				</button>
			</div>

			<div class="right-buttons">
				<!-- 홈으로 이동 -->
				<form action="<c:url value='/'/>" method="get">
					<button type="submit">
						<spring:message code="label.Home" />
					</button>
				</form>
			</div>
		</div>

	</div>

	<!-- 보험 확인용 숨은 form -->
	<form id="insuranceForm" action="<c:url value='/insurance'/>"
		method="post" style="display: none;">
		<input type="hidden" id="insuranceId" name="id" />
	</form>

	<!-- 피해보상 요청용 숨은 form -->
	<form id="damageForm" action="<c:url value='/user/damage'/>"
		method="post" style="display: none;">
		<input type="hidden" name="id" value="${user.userId}" />
	</form>

</body>
</html>