<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><spring:message code="label.InsuranceList" /></title>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f7f7f7;
        margin: 0;
        padding: 0;
    }
    h1 {
        text-align: center;
        color: #333;
    }
    
	header h1 a {
  		color: white !important;
	}
    
    table {
        margin: 0 auto;
        width: 80%;
        border-collapse: collapse;
        background-color: #fff;
        box-shadow: 0 0 5px rgba(0,0,0,0.1);
    }
    th, td {
        padding: 10px;
        text-align: center;
        border: 1px solid #ddd;
    }
    th {
        background-color: #eee;
    }
    .submit-btn {
        display: block;
        margin: 20px auto;
        padding: 10px 20px;
        background-color: #007BFF;
        color: white;
        border: none;
        border-radius: 5px;
        cursor: pointer;
    }
    .submit-btn:disabled {
        background-color: #aaa;
        cursor: not-allowed;
    }
</style>
</head>
<body>

<%@ include file="/WEB-INF/views/header.jsp" %>

<h1><spring:message code="label.InsuranceList" /></h1>

<form id="insuranceForm" action=<c:url value='/insuranceSubmit'/> method="post">
    <table>
        <thead>
            <tr>
                <th><spring:message code="label.Choice" /></th>
                <th><spring:message code="label.Name" /></th>
                <th><spring:message code="label.Price" /></th>
                <th><spring:message code="label.Compensation" /></th>
                <th><spring:message code="label.Content" /></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="insurance" items="${insuranceList}">
                <tr>
                    <td><input type="radio" name="insuranceId" value="${insurance.id}" onclick="enableSubmitButton()"></td>
                    <td>${insurance.insuranceName}</td>
                    <td>${insurance.price}</td>
                    <td>${insurance.compensation}%</td>
                    <td>${insurance.content}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <input type="submit" id="submitButton" value="<spring:message code='button.Next' />" disabled class="submit-btn">
    
    <input type="hidden" name= "id" value="${id}">
</form>

<script>
    function enableSubmitButton() {
        document.getElementById("submitButton").disabled = false;
    }

    document.getElementById("insuranceForm").addEventListener("submit", function(e) {
        const radios = document.getElementsByName("insuranceId");
        let isSelected = false;
        for (let i = 0; i < radios.length; i++) {
            if (radios[i].checked) {
                isSelected = true;
                break;
            }
        }
        if (!isSelected) {
            alert("보험을 선택해주세요.");
            e.preventDefault();
        }
    });
</script>

</body>
</html>
