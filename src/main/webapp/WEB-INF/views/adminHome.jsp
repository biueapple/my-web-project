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


/* Navigation Styles */
nav {
	background-color: #fff;
	border-bottom: 2px solid #1a1a1a;
	margin: 20px 0;
	text-align: center;
	padding: 10px 0;
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
	<%@ include file="/WEB-INF/views/adminHeader.jsp" %>
	
	<div class="container">
		
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
