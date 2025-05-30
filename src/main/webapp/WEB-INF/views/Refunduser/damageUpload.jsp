<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>파손 보상 신청</h2>
	<h3>파손된 캐리어 사진을 업로드 해주세요</h3>


	<form method="post" action="/user/damage/upload"
		enctype="multipart/form-data" >
		<label>파손 사진 업로드</label>
		<input type="file" name="damagePhotos" multiple />
		<!-- <textarea name="description" placeholder="파손 내용 입력"></textarea>-->
		<button type="submit">보상신청</button>
	</form>

	<form action="<c:url value='/'/>" method="get"
		style="display: inline-block;">
		<button type="submit">이전으로</button>
	</form>
</body>
</html>