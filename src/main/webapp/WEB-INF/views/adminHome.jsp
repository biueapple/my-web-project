<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><spring:message code="label.Admin" /></title>
<style>
/* Global Styles */
body {
	font-family: Arial, sans-serif;
	background-color: #f7f7f7;
	margin: 0;
	padding: 0;
}

.container {
	width: 90%;
	max-width: 1200px;
	margin: 0 auto;
	padding: 20px;
}
/* Header Styles */
header {
	background-color: #007BFF;
	color: #fff;
	padding: 20px 0;
}

.inner-header {
	max-width: 1200px;
	margin: 0 auto;
	display: flex;
	align-items: center;
	justify-content: space-between;
}

header h1 {
	margin: 0;
	font-size: 2em;
	text-align: left;
	flex-shrink: 0;
}

/* 오른쪽 메뉴 영역 (로그아웃 + 언어선택) */
.top-right-auth {
	display: flex;
	align-items: center;
	gap: 20px;
	font-weight: bold;
	color: #fff;
}

.top-right-auth a {
	color: #fff;
	text-decoration: none;
	font-weight: bold;
}

.top-right-auth a:hover {
	text-decoration: underline;
}

/* 언어 선택 박스 스타일 */
.language-selector {
	display: flex;
	align-items: center;
	gap: 5px;
}

.language-selector label {
	color: #fff;
}

#languageSelect {
	padding: 5px 10px;
	border-radius: 4px;
	border: none;
	font-weight: bold;
	background-color: #ffffff;
	color: #007BFF;
	font-size: 14px;
	cursor: pointer;
	box-shadow: 0 2px 5px rgba(0,0,0,0.2);
	transition: background-color 0.3s ease;
}

#languageSelect:hover {
	background-color: #f0f0f0;
}

/* Navigation Styles */
nav {
	background-color: #fff;
	border-bottom: 2px solid #007BFF;
	margin: 20px 0;
	text-align: center;
	padding: 10px 0;
}

nav a {
	text-decoration: none;
	color: #007BFF;
	font-weight: bold;
	margin: 0 15px;
	padding: 5px 10px;
	border-radius: 4px;
	transition: background-color 0.3s, color 0.3s;
}

nav a:hover {
	background-color: #007BFF;
	color: #fff;
}
/* Main Content Styles */
main {
	background-color: #fff;
	padding: 20px;
	border-radius: 4px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

main h3 {
	margin-top: 0;
}
/* Footer Styles */
footer {
	text-align: center;
	margin-top: 20px;
	padding: 10px 0;
	color: #777;
}
</style>
</head>

<body>

	<div class="container">
		<header>
			<div class="inner-header">
				<h1>
					관리자 홈
				</h1>
				<div class="top-right-auth">
					<div class="language-selector">
						<label for="languageSelect">Language:</label>
						<select id="languageSelect" onchange="changeLanguage(this.value)">
							<option value="ko_KR" ${param.lang == 'ko_KR' ? 'selected' : ''}>한글</option>
							<option value="en_US" ${param.lang == 'en_US' ? 'selected' : ''}>English</option>
						</select>
					</div>
					<a href="<c:url value='/user/logout'/>"><spring:message code="label.logout" /></a>
				</div>
			</div>
		</header>

		<nav>
				<a href="<c:url value='/planeAdd'/>"><spring:message code="button.Addplane" /></a>
				<a href="<c:url value='/airplaneList'/>"><spring:message code="button.Listplane" /></a>
				<a href="<c:url value='/planeReservation'/>"><spring:message code="label.AirReservation" /></a>
				<a href="<c:url value='/Reservation'/>"><spring:message code="label.Reservation" /></a>
				<a href="<c:url value='/user/regist'/>"><spring:message code="label.BookingConfirmation" /></a>
				<a href="<c:url value='/board'/>"><spring:message code="board.title" /></a>
		</nav>
		<main>
			<h3><spring:message code="label.Admin" /></h3>

		</main>
		<footer> &copy; 2025 Airplane Reservation. All Rights
			Reserved. </footer>
	</div>

<script>
	function changeLanguage(lang) {
		const url = new URL(window.location.href);
		url.searchParams.set('lang', lang);
		window.location.href = url.toString();
	}
</script>

</body>
</html>
