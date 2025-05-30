<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title><spring:message code="label.path"/></title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/flatpickr/dist/flatpickr.min.css">

    <style>
        body {
            background-color: #f8f9fa;
            padding-top: 50px;
        }
        .form-container {
            max-width: 500px;
            margin: auto;
            padding: 30px;
            background: white;
            border-radius: 10px;
            box-shadow: 0 0 15px rgba(0,0,0,0.1);
        }
        h3 {
            text-align: center;
            margin-bottom: 30px;
        }
    </style>
</head>
<body>

<div class="form-container">
    <h3>여행지를 선택하세요</h3>

    <form:form modelAttribute="airinfoDto" action="/airplane/Reservation" method="post">

        <!-- 출발지 -->
        <div class="mb-3">
            <label for="departure" class="form-label"><spring:message code="label.departure"/>:</label>
            <select name="departure" id="departure" class="form-select" required>
                <c:forEach var="airport" items="${airports}">
                    <option value="${airport.airportName}">${airport.airportName}</option>
                </c:forEach>
            </select>
            <form:errors path="departure" cssClass="text-danger" />
        </div>

        <!-- 도착지 -->
        <div class="mb-3">
            <label for="destination" class="form-label"><spring:message code="label.arrival"/>:</label>
            <select name="destination" id="destination" class="form-select" required>
                <c:forEach var="airport" items="${airports}">
                    <option value="${airport.airportName}">${airport.airportName}</option>
                </c:forEach>
            </select>
            <form:errors path="destination" cssClass="text-danger" />
            
        </div>

        <!-- 출발 날짜 선택 -->
        <div class="mb-3">
            <label for="flightDate" class="form-label"><spring:message code='label.DepartureDate'/></label>
            <input type="text" id="flightDate" name="departureDate" class="form-control" placeholder="날짜를 선택하세요" readonly required>
            <form:errors path="departureDate" cssClass="text-danger" />
        </div>

        <!-- 버튼 -->
        <div class="d-grid gap-2 d-md-flex justify-content-md-end">
            <button type="submit" class="btn btn-primary"><spring:message code='label.next'/></button>
            <button type="button" class="btn btn-outline-secondary" onclick="location.href='/airplane'"><spring:message code='label.cancellation'/></button>
        </div>

    </form:form>
</div>

<!-- ✅ Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<!-- ✅ Flatpickr JS -->
<script src="https://cdn.jsdelivr.net/npm/flatpickr"></script>
<!-- ✅ Flatpickr 한글 Locale -->
<script src="https://cdn.jsdelivr.net/npm/flatpickr/dist/l10n/ko.js"></script>
<!-- ✅ Flatpickr 초기화 -->
<script>
    flatpickr("#flightDate", {
        locale: "ko",            // 한글 출력
        dateFormat: "Y-m-d",     // 날짜 포맷: 2025-05-26
        minDate: "today",        // 오늘 이전 선택 불가
        allowInput: false        // 직접 입력 방지
    });
</script>

</body>
</html>
