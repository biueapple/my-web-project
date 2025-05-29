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
	text-align: center;
}

header h1 {
	margin: 0;
	font-size: 2em;
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
					<spring:message code="label.Main" />
				</h1>
				<div class="top-right-auth">
					<c:if test="${empty sessionScope.loginUser}">
						<a href="<c:url value='/login'/>"><spring:message
								code="label.login" /></a>
						<a href="<c:url value='/regist'/>"><spring:message
								code="register.title" /></a>
					</c:if>
					<c:if test="${not empty sessionScope.loginUser}">
						<a href="<c:url value='/user/logout'/>"><spring:message
								code="label.logout" /></a>
					</c:if>
				</div>
			</div>
			<div style="text-align: right; margin-top: 10px;">
				<a href="?lang=ko" style="margin-right: 10px;">한글</a> 
				<a href="?lang=en">영어</a>
			</div>
		</header>

		<nav>
			<c:if test="${empty sessionScope.loginUser}">
				<a href="board">게시판</a>
				<a href="<c:url value='/Reservation'/>"><spring:message
						code="label.Reservation" /></a>
			</c:if>
			<c:if test="${not empty sessionScope.loginUser}">
				<a href="<c:url value='/planeAdd'/>"><spring:message
						code="button.Addplane" /></a>
				<a href="<c:url value='/airplaneList'/>"><spring:message
						code="button.Listplane" /></a>
				<a href="<c:url value='/planeReservation'/>"><spring:message
						code="label.Reservation" /></a>
				<a href="<c:url value='/Reservation'/>"><spring:message
						code="label.Reservation" /></a>
				<a href="<c:url value='/user/regist'/>"><spring:message
						code="label.BookingConfirmation" /></a>
				<a href="board"><spring:message code="board.title" /></a>
			</c:if>
		</nav>
		<main>
			<h3>Home</h3>

		</main>
		<footer> &copy; 2025 Airplane Reservation. All Rights
			Reserved. </footer>
	</div>

</body>
</html>




