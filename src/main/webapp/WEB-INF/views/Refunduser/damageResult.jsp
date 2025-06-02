<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


	<p style="color: red; font-weight: bold;">${message}</p>
	<form action="<c:url value='/user/damage/home'/>" method="get"
		style="display: inline-block;">
		<button type="submit">홈으로</button>
	</form>
</body>
</html>