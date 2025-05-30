<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>파손 정보 업로드</h2>
	<h3>캐리어가 파손되셨습니까? 사진을 업로드하세요</h3>


	<form method="post" action="/user/regist/damage"
		enctype="multipart/form-data" style="display: inline-block;">
		<input type="file" name="damagePhotos" multiple />
		<textarea name="description" placeholder="파손 내용 입력"></textarea>
		<button type="submit">보상신청</button>
	</form>

	<form action="<c:url value='/'/>" method="get"
		style="display: inline-block;">
		<button type="submit">이전으로</button>
	</form>
</body>
</html>