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
        <label>이름: <input type="text" name="name" value="${user.name}" /></label><br>
        <label>나이: <input type="text" name="age" value="${user.age}" /></label><br>
        <label>전화번호: <input type="text" name="phoneNumber" value="${user.phoneNumber}" /></label><br>
        <input type="submit" value="정보 수정" />
    </form>

    <hr>

    <!-- 회원 정보 출력 -->
    <p><strong>아이디:</strong> ${user.id}</p>
    <p><strong>이름:</strong> ${user.name}</p>
    <p><strong>성별:</strong> ${user.gender}</p>
    <p><strong>나이:</strong> ${user.age}</p>
    <p><strong>전화번호:</strong> ${user.phoneNumber}</p>

    <hr>
</body>
</html>