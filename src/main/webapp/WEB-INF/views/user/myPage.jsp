<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>마이페이지</title>
</head>
<body>
    
<!-- 회원정보 수정 -->
<form action="<c:url value='/editForm' />" method="get">
    <button type="submit">회원정보 수정</button>
</form>

<!-- 회원 탈퇴 -->
<form action="<c:url value='/userDelete' />" method="get">
    <button type="submit">회원 탈퇴</button>
</form>
</body>
</html>