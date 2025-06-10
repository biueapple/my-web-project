<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>마이페이지</title>
</head>
<body>
    <hr>

    <!-- 회원 정보 출력 -->
    <p><strong>아이디:</strong> ${user.id}</p>
    <p><strong>이름:</strong> ${user.name}</p>
    <p><strong>성별:</strong> ${user.gender}</p>
    <p><strong>나이:</strong> ${user.age}</p>
    <p><strong>전화번호:</strong> ${user.phoneNumber}</p>

    <hr>
<!-- 회원정보 수정 -->
<form action="<c:url value='/changePassword' />" method="get">
    <button type="submit">회원정보 수정</button>
</form>

<!-- 회원 탈퇴 -->
<form action="<c:url value='/userDelete' />" method="get">
    <button type="submit">회원 탈퇴</button>
</form>
</body>
</html>