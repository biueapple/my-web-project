<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    /* 사용 가능한 좌석 */
    .seat.available {
        background-color: #4CAF50;
        color: white;
    }
    /* 이미 예약된 좌석 */
    .seat.booked {
        background-color: #ccc;
        cursor: not-allowed;
        color: #666;
    }
    /* 선택된 좌석 */
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
    function selectSeat(seatId) {
        // 이미 예약된 좌석은 선택 불가
        var seatElem = document.getElementById("seat_" + seatId);
        if(seatElem.classList.contains("booked")) return;

        // 기존 선택 좌석 해제
        var prevSelected = document.querySelector(".seat.selected");
        if(prevSelected){
            prevSelected.classList.remove("selected");
        }

        // 새 좌석 선택
        seatElem.classList.add("selected");

        // 선택된 좌석 ID를 숨은 필드에 저장
        document.getElementById("selectedSeatId").value = seatId;
    }

    // 제출 시 좌석 선택 여부 확인
    function validateForm() {
        var selected = document.getElementById("selectedSeatId").value;
        if(selected === "") {
            alert("좌석을 선택해 주세요.");
            return false;
        }
        return true;
    }
</script>
</head>
<body>
    <h1 style="text-align:center;">비행기 좌석 선택</h1>

    <form action="/airplane/reserveSeat" method="post" onsubmit="return validateForm();">
        <!-- 선택된 좌석 ID 저장용 -->
        <input type="hidden" id="selectedSeatId" name="seatId" value="">

        <h2>First Class (총 좌석: ${original.first_seat})</h2>
        <table>
            <tr>
            <c:forEach var="i" begin="1" end="${original.first_seat}">
                <td>
                    <div id="seat_first_${i}" 
                         class="seat available" 
                         onclick="selectSeat('first_${i}')">
                        F${i}
                    </div>
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
                <td>
                    <div id="seat_business_${i}" 
                         class="seat available" 
                         onclick="selectSeat('business_${i}')">
                        B${i}
                    </div>
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
                <td>
                    <div id="seat_economy_${i}" 
                         class="seat available" 
                         onclick="selectSeat('economy_${i}')">
                        E${i}
                    </div>
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
