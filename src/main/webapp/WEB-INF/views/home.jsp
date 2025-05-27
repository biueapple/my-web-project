<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><spring:message code="label.Main"/></title>
<style>
        /* Global Styles */
        body {
            font-family: Arial, sans-serif;
            background-color: #f7f7f7;
            margin: 0;
            padding: 0;
        }
        .container {
            width: 90%;
            max-width: 1200px;
            margin: 0 auto;
            padding: 20px;
        }
        /* Header Styles */
        header {
            background-color: #007BFF;
            color: #fff;
            padding: 20px 0;
            text-align: center;
        }
        header h1 {
            margin: 0;
            font-size: 2em;
        }
        /* Navigation Styles */
        nav {
            background-color: #fff;
            border-bottom: 2px solid #007BFF;
            margin: 20px 0;
            text-align: center;
            padding: 10px 0;
        }
        nav a {
            text-decoration: none;
            color: #007BFF;
            font-weight: bold;
            margin: 0 15px;
            padding: 5px 10px;
            border-radius: 4px;
            transition: background-color 0.3s, color 0.3s;
        }
        nav a:hover {
            background-color: #007BFF;
            color: #fff;
        }
        /* Main Content Styles */
        main {
            background-color: #fff;
            padding: 20px;
            border-radius: 4px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        main h3 {
            margin-top: 0;
        }
        /* Footer Styles */
        footer {
            text-align: center;
            margin-top: 20px;
            padding: 10px 0;
            color: #777;
        }
    </style>
</head>

<body>

<div class="container">
        <header>
            <h1><spring:message code="label.Main"/></h1>
        </header>
        <nav>
            <a href="<c:url value='/planeAdd'/>"><spring:message code="button.Addplane"/></a>
            <a href="<c:url value='/airplaneList'/>"><spring:message code="button.Listplane"/></a>
            <a href="<c:url value='/planeReservation'/>"><spring:message code="label.Reservation"/></a>
        </nav>
        <main>
            <h3>Home</h3>
            <p>현재 시간: ${now}입니다.</p>
        </main>
        <footer>
            &copy; 2025 Airplane Reservation. All Rights Reserved.
        </footer>
    </div>
    
</body>
</html>




