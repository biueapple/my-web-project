<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><spring:message code="register.title" /></title>
<style>
body {
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
	margin: 0;
	background-color: #f9f9f9;
}

.container {
	width: 400px;
	background: white;
	padding: 30px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	border-radius: 10px;
}

h2 {
	text-align: center;
}

.error {
	color: red;
	font-weight: bold;
	font-size: 0.9em;
}
</style>
</head>
<body>
	<div class="container">
		<h2>
			<spring:message code="register.title" />
		</h2>
		<form:form modelAttribute="userRegisterRequest" method="post">

			<p>
				<spring:message code="label.id" />
				<form:input path="id" pattern="[a-z0-9]{4,20}"
					title="영문 소문자와 숫자만 입력하세요 (4~20자)" required="true"
					autocomplete="username" />
				<form:errors path="id" cssClass="error" />
			</p>

			<p>
				<spring:message code="label.password" />
				<form:password path="password" 
					pattern="(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,}"
					title="8자 이상, 영문, 숫자, 특수문자 최소 1개 포함" required="true"
					autocomplete="new-password" />
				<form:errors path="password" cssClass="error" />
			</p>

			<p>
				<spring:message code="label.passwordConfirm" />
				<form:password path="passwordConfirm" required="true"
					autocomplete="new-password" />
				<form:errors path="passwordConfirm" cssClass="error" />
			</p>

			<p>
				<spring:message code="label.name" />
				<form:input path="name" pattern="[가-힣a-zA-Z]+"
					title="이름은 한글 또는 영문만 입력해야 합니다." required="true" />
				<form:errors path="name" cssClass="error" />
			</p>

			<p>
				<spring:message code="label.gender" />
				<form:radiobuttons path="gender" items="${genderOptions}" />
				<form:errors path="gender" cssClass="error" />
			</p>

			<p>
				<spring:message code="label.age" />
				<form:input path="age" type="number" min="0" max="120" required="true" />
				<form:errors path="age" cssClass="error" />
			</p>

			<p>
				<spring:message code="label.phoneNumber" />
				<form:input path="phoneNumber" type="tel" 
					pattern="^01[016789]-?\d{3,4}-?\d{4}$" 
					title="올바른 핸드폰 번호 형식 입력 (예: 010-1234-5678)" required="true" />
				<form:errors path="phoneNumber" cssClass="error" />
			</p>

			<button type="submit">
				<spring:message code="button.submit" />
			</button>

		</form:form>
	</div>
</body>
</html>