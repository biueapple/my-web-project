<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비행기 좌석 선택</title>
<style>
    .seat {
        width: 40px;
        height: 40px;
        margin: 5px;
        background-color: #f2f2f2;
        border: 1px solid #ccc;
        border-radius: 4px;
        text-align: center;
        line-height: 40px;
        cursor: pointer;
        display: inline-block;
        user-select: none;
    }
    .seat.available {
        background-color: #4CAF50;
        color: white;
    }
    .seat.booked {
        background-color: #ccc;
        cursor: not-allowed;
        color: #666;
    }
    .seat.selected {
        background-color: #FF9800;
        color: white;
    }
    table {
        margin: 20px auto;
        border-collapse: collapse;
    }
    td {
        padding: 0;
        text-align: center;
        vertical-align: middle;
    }
    h2 {
        text-align: center;
        color: #333;
        margin-top: 40px;
    }
    button {
        display: block;
        margin: 30px auto;
        padding: 12px 30px;
        font-size: 16px;
        background-color: #007BFF;
        border: none;
        color: #fff;
        border-radius: 6px;
        cursor: pointer;
        transition: background-color 0.3s ease;
    }
    button:hover {
        background-color: #0056b3;
    }
</style>

<script>
    let selectedSeats = [];

    function selectSeat(seatId) {
        const seatElem = document.getElementById("seat_" + seatId);
        if (seatElem.classList.contains("booked")) return;

        const maxSeats = parseInt(document.getElementById("personCount").value);

        if (seatElem.classList.contains("selected")) {
            seatElem.classList.remove("selected");
            selectedSeats = selectedSeats.filter(id => id !== seatId);
        } else {
            if (selectedSeats.length >= maxSeats) {
                alert("최대 " + maxSeats + "개의 좌석만 선택할 수 있습니다.");
                return;
            }
            seatElem.classList.add("selected");
            selectedSeats.push(seatId);
        }

        document.getElementById("selectedSeatIds").value = selectedSeats.join(",");
    }

    function validateForm() {
        const maxSeats = parseInt(document.getElementById("personCount").value);
        if (selectedSeats.length !== maxSeats) {
            alert("총 " + maxSeats + "개의 좌석을 선택해야 합니다.");
            return false;
        }
        return true;
    }
</script>
</head>
<body>
    <h1 style="text-align:center;">비행기 좌석 선택</h1>

    <!-- 테스트용 인원 수 고정 -->
    <c:set var="personCount" value="${number_of_people}" />
    <c:set var="reserved" value="${reservedSeats}" />

    <form action="/airplane/reserveSeat" method="post" onsubmit="return validateForm();">
        <input type="hidden" id="personCount" value="${personCount}" />
        <input type="hidden" id="selectedSeatIds" name="seatIds" />

        <h2>First Class (총 좌석: ${original.first_seat})</h2>
        <table>
            <tr>
                <c:forEach var="i" begin="1" end="${original.first_seat}">
                    <c:set var="seatId" value="first_${i}" />
                    <td>
                        <c:choose>
                            <c:when test="${fn:contains(reserved, seatId)}">
                                <div id="seat_${seatId}" class="seat booked">F${i}</div>
                            </c:when>
                            <c:otherwise>
                                <div id="seat_${seatId}" class="seat available"
                                    onclick="selectSeat('${seatId}')">F${i}</div>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <c:if test="${i % 6 == 0}">
                        </tr><tr>
                    </c:if>
                </c:forEach>
            </tr>
        </table>

        <h2>Business Class (총 좌석: ${original.business_seat})</h2>
        <table>
            <tr>
                <c:forEach var="i" begin="1" end="${original.business_seat}">
                    <c:set var="seatId" value="business_${i}" />
                    <td>
                        <c:choose>
                            <c:when test="${fn:contains(reserved, seatId)}">
                                <div id="seat_${seatId}" class="seat booked">B${i}</div>
                            </c:when>
                            <c:otherwise>
                                <div id="seat_${seatId}" class="seat available"
                                    onclick="selectSeat('${seatId}')">B${i}</div>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <c:if test="${i % 6 == 0}">
                        </tr><tr>
                    </c:if>
                </c:forEach>
            </tr>
        </table>

        <h2>Economy Class (총 좌석: ${original.economy_seat})</h2>
        <table>
            <tr>
                <c:forEach var="i" begin="1" end="${original.economy_seat}">
                    <c:set var="seatId" value="economy_${i}" />
                    <td>
                        <c:choose>
                            <c:when test="${fn:contains(reserved, seatId)}">
                                <div id="seat_${seatId}" class="seat booked">E${i}</div>
                            </c:when>
                            <c:otherwise>
                                <div id="seat_${seatId}" class="seat available"
                                    onclick="selectSeat('${seatId}')">E${i}</div>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <c:if test="${i % 6 == 0}">
                        </tr><tr>
                    </c:if>
                </c:forEach>
            </tr>
        </table>

        <button type="submit">좌석 선택 완료</button>
    </form>
</body>
</html>
