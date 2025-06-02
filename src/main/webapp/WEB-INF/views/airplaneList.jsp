<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><spring:message code="button.Listplane"/></title>
<style>
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

body {
  font-family: Arial, sans-serif;
}

form {
  margin: 0;
  padding: 0;
}

h2, p {
  margin: 0;
  padding: 0;
}
</style>
    
</head>
<body>

<%@ include file="/WEB-INF/views/header.jsp" %>

<h1><spring:message code="label.Listplane"/></h1>
    
    <form id="planeForm" action="/airplane/airplaneList" method="post">
        <table>
            <thead>
                <tr>
                    <th>선택</th>
                    <th>ID</th>
                    <th>Departure</th>
                    <th>Destination</th>
                    <th>Plane Time</th>
                    <th>Price</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="plane" items="${list}">
                    <tr>
                        <!-- name이 동일하면 반드시 하나만 선택됩니다. -->
                        <td>
                            <input type="radio" name="id" value="${plane.id}" onclick="enableNextButton()">
                        </td>
                        <td>${plane.id}</td>
                        <td>${plane.departure}</td>
                        <td>${plane.destination}</td>
                        <td>${plane.plane_time}</td>
                        <td>${plane.price}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <!-- 초기에는 비활성화 되어 있음 -->
        <input type="submit" id="nextButton" value="<spring:message code='button.Next'/>" disabled class="submit-btn">
    </form>
    
	<!-- 홈으로 돌아가는 버튼 -->
    <div style="text-align: center;">
        <a href="<c:url value='/'/>" class="home-button"><spring:message code="button.Home"/></a>
    </div>
    
    <script>
        // 라디오 버튼 선택 시 "다음" 버튼 활성화 함수
        function enableNextButton() {
            document.getElementById("nextButton").disabled = false;
        }

        // 폼 제출 전에 반드시 하나의 라디오가 선택되었는지 확인
        document.getElementById("planeForm").addEventListener("submit", function(e) {
            var radios = document.getElementsByName("id");
            var isChecked = false;
            for (var i = 0; i < radios.length; i++) {
                if (radios[i].checked) {
                    isChecked = true;
                    break;
                }
            }
            if (!isChecked) {
                alert("하나의 비행기를 선택해 주세요.");
                e.preventDefault();
            }
        });
    </script>

</body>
</html>