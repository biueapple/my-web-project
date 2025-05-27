<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><spring:message code="label.Addplane"/></title>

<style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f7f7f7;
            margin: 0;
            padding: 20px;
        }
        .container {
            width: 400px;
            margin: 0 auto;
            background-color: #fff;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        h1 {
            text-align: center;
            margin-bottom: 20px;
            color: #333;
        }
        .form-group {
            margin-bottom: 15px;
        }
        label {
            display: block;
            font-weight: bold;
            margin-bottom: 8px;
            color: #555;
        }
        input[type="text"],
        input[type="number"],
        input[type="datetime-local"] {
            width: 100%;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
            box-sizing: border-box;
        }
        button {
            width: 100%;
            padding: 10px;
            border: none;
            border-radius: 4px;
            background-color: #007BFF;
            color: #fff;
            font-weight: bold;
            cursor: pointer;
        }
        button:hover {
            background-color: #0056b3;
        }
</style>


</head>
<body>

<div class="container">
    <h1><spring:message code="label.Addplane"/></h1>
    <form action="/airplane/planeReservation" method="post" onsubmit="return validateForm();">
    <div class="form-group">
            <label for="original_id">
                <spring:message code="label.Original_id"/> (Original ID)
            </label>
            <input type="number" id="original_id" name="original_id" required>
        </div>
        <div class="form-group">
            <label for="airport_id">
                <spring:message code="label.Airport_Id"/> (Airport ID)
            </label>
            <input type="number" id="airport_id" name="airportId" required>
        </div>
        <div class="form-group">
            <label for="plane_time">
                <spring:message code="label.Flight_time"/> (Plane Time)
            </label>
            <input type="datetime-local" id="plane_time" name="plane_time" required>
        </div>
        <div class="form-group">
            <label for="departure">
                <spring:message code="label.Departure"/> (Departure)
            </label>
            <input type="text" id="departure" name="departure" required>
        </div>
        <div class="form-group">
            <label for="destination">
                <spring:message code="label.Destination"/> (Destination)
            </label>
            <input type="text" id="destination" name="destination" required>
        </div>
        
        <button type="submit"><spring:message code="button.Add"/></button>
    </form>
</div>
<!-- 홈으로 돌아가는 버튼 -->
<div style="text-align: center;">
    <a href="<c:url value='/'/>" class="home-button">
        <spring:message code="button.Home"/>
    </a>
</div>

<script>
    function validateForm() {
        // 각 필드의 값을 가져옵니다.
        var airportId = document.getElementById("airport_id").value;
        var planeTime = document.getElementById("plane_time").value;
        var departure = document.getElementById("departure").value;
        var destination = document.getElementById("destination").value;
    
        // 하나라도 빈 값이 있는 경우
        if (airportId === "" || planeTime === "" || departure === "" || destination === "") {
            alert("모든 항목을 입력(선택)해 주세요.");
            return false;   // 폼 제출을 막음
        }
        return true;  // 모든 값이 있으면 제출
    }
</script>

</body>
</html>