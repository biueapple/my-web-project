<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><spring:message code="label.Main" /></title>
<style>
html, body {
	margin: 0;
	padding: 0;
	height: 100%;
	position: relative;
}

body::before {
	content: "";
	position: fixed;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	background-image: url('<c:url value="/images/patrick-tomasso-GOErUf5yNFA-unsplash.jpg" />');
	background-size: cover;
	background-position: center top;
	background-repeat: no-repeat;
	z-index: -1;
}

.container {
	width: 100%;
	max-width: 1200px;
	margin: 0 auto;
	padding: 0 20px;
}

nav {
	background-color: #fff;
	border-bottom: 2px solid #1a1a1a;
	padding: 10px 0;
	text-align: center;
	margin: 0;
}

nav a {
	text-decoration: none;
	color: #1a1a1a;
	font-weight: bold;
	margin: 0 15px;
	padding: 5px 10px;
	border-radius: 4px;
	transition: background-color 0.3s, color 0.3s;
}

main {
	background-color: rgba(255, 255, 255, 0.7);
	padding: 20px;
	border-radius: 4px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	margin-top: 20px;
}

footer {
	text-align: center;
	margin-top: 20px;
	padding: 10px 0;
	color: #777;
}

.recent-slide-container {
	width: 420px;
	height: 256px;
	overflow-y: auto;
	margin-top: 20px;
	margin-left: auto;
	margin-right: 0;

	scrollbar-width: thin;
	scrollbar-color: rgba(0,0,0,0.15) transparent;
}

.recent-slide-container::-webkit-scrollbar {
	width: 6px;
	background: transparent;
}

.recent-slide-container::-webkit-scrollbar-thumb {
	background: rgba(0, 0, 0, 0.15);
	border-radius: 4px;
}

.recent-item {
	height: 80px;
	margin: 4px 0;
	padding: 8px 4px 8px 12px; /* ✅ 오른쪽 여백 줄임 */
	box-sizing: border-box;
	background: rgba(255, 255, 255, 0.92);
	border-left: 5px solid #ff9900; /* 주황색 */
	border-radius: 6px;
	display: flex;
	flex-direction: column;
	justify-content: center;
	box-shadow: 0 2px 4px rgba(0, 0, 0, 0.08);
	font-size: 14px;
	text-align: left;
	color: #333;
}

.recent-item > div {
	margin-bottom: 4px;
}

.recent-item strong {
	color: #ff9900;
	font-weight: 600;
}

.arrow {
	margin: 0 4px;
}

.label-text {
	color: #000000;
	font-weight: 600;
}

.notice-board {
	margin-top: 20px;
}

.notice-title {
	font-size: 18px;
	font-weight: bold;
	margin-bottom: 10px;
	color: #1a1a1a;
}

.notice-item {
	background: rgba(255, 255, 255, 0.92);
	border-left: 3px solid #007bff;
	border-radius: 4px;
	margin-bottom: 6px;
	box-shadow: 0 1px 2px rgba(0, 0, 0, 0.05);
	overflow: hidden;
}

.notice-item:hover {
	background-color: #f0f8ff;
}

.notice-item a {
	text-decoration: none;
	color: #333;
	font-weight: 600;
	font-size: 15px;
	display: block;
	transition: color 0.3s;
}

.notice-item a:hover {
	color: #007bff;
}
.main-content {
	display: flex;
	gap: 40px; /* 좌우 공간 */
	align-items: flex-start;
	margin-top: 20px;
	flex-wrap: wrap;
}

.notice-board {
	flex: 1;
	min-width: 300px;
}

.recent-slide-container {
	flex: 1;
	min-width: 420px;
}
.notice-item {
	background: rgba(255, 255, 255, 0.92);
	border-left: 5px solid #007bff;
	border-radius: 6px;
	margin-bottom: 10px;
	box-shadow: 0 2px 4px rgba(0, 0, 0, 0.08);
	overflow: hidden;
}

.notice-header {
	cursor: pointer;
	padding: 12px 16px;
	font-weight: 600;
	font-size: 15px;
	color: #333;
	transition: background-color 0.3s;
}

.notice-header:hover {
	background-color: #f0f8ff;
}

.notice-body {
	padding: 12px 16px;
	border-top: 1px solid #ddd;
	display: none;
	color: #555;
	font-size: 14px;
}
</style>
</head>

<script>
function toggleNotice(header) {
	const body = header.nextElementSibling;
	const isVisible = body.style.display === "block";
	// 닫기
	document.querySelectorAll('.notice-body').forEach(el => el.style.display = "none");
	// 클릭한 항목만 열기
	if (!isVisible) {
		body.style.display = "block";
	}
}
</script>
<body>

<%@ include file="/WEB-INF/views/header.jsp" %>

	<div class="container">
		<main>
			<h3>공지사항</h3>
			<div class="main-content">
				<div class="notice-board">
					<c:forEach var="boardIdDto" items="${noticeBoard}">
						<div class="notice-item">
							<div class="notice-header" onclick="toggleNotice(this)">
								[공지] ${boardIdDto.boardTitle}
							</div>
							<div class="notice-body">${boardIdDto.board}</div>
						</div>
					</c:forEach>
				</div>

				<c:if test="${not empty Recently}">
					<div class="recent-slide-container">
						<c:forEach var="plane" items="${Recently}">
							<div class="recent-item">
								<div>
									<strong>${plane.departureName}</strong>
									<span class="arrow">→</span>
									<strong>${plane.destinationName}</strong>
								</div>
								<div>
									<span class="label-text"><spring:message code="label.DepartureDate" /></span> <span class="label-text">${plane.formattedDate}</span>&nbsp;&nbsp;
									<span class="label-text"><spring:message code="label.Flight_time" /></span> <span class="label-text">${plane.formattedTime}</span>
								</div>
							</div>
						</c:forEach>
					</div>
				</c:if>
			</div>
		</main>

		<footer>&copy; 2025 Airplane Reservation. All Rights
			Reserved.</footer>
	</div>

</body>
</html>
