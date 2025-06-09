<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>${user.name}님의 마이페이지</h2>

    <!-- 정보 수정 폼 -->
    <form action="${pageContext.request.contextPath}/myPage" method="post">
        <label>전화번호: <input type="text" name="phoneNumber" value="${user.phoneNumber}" /></label><br>
        <label>비밀번호: <input type="text" name="password" value="${user.password}" /></label><br>
        <label>새 비밀번호: <input type="text" name="password" value="${user.password}" /></label><br>
        <label>새 비밀번호 확인: <input type="text" name="password" value="${user.password}" /></label><br>
        <input type="submit" value="정보 수정" />
        
    </form>
	<a href="myPage" class="btn">취소</a>
</body>
</html>