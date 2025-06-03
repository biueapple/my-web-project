<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:forEach var="path" items="${photopaths}">
<img src="/${path}" alt="damage photo" style="max-width:300px;"/>
</c:forEach>

</body>
</html>