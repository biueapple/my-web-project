<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><spring:message code="label.Main" /></title>

<!-- Leaflet 지도 라이브러리 -->
<link rel="stylesheet" href="https://unpkg.com/leaflet/dist/leaflet.css" />
<script src="https://unpkg.com/leaflet/dist/leaflet.js"></script>

<style>
html, body {
	margin: 0;
	padding: 0;
	height: 100%;
	position: relative;
}

body::before {
	content: "";
	position: fixed;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	background-image:
		url('<c:url value="/images/patrick-tomasso-GOErUf5yNFA-unsplash.jpg" />');
	background-size: cover;
	background-position: center top;
	background-repeat: no-repeat;
	z-index: -1;
}

.container {
	width: 100%;
	max-width: 1200px;
	margin: 0 auto;
	padding: 0 20px;
}

nav {
	background-color: #fff;
	border-bottom: 2px solid #1a1a1a;
	padding: 10px 0;
	text-align: center;
	margin: 0;
}

nav a {
	text-decoration: none;
	color: #1a1a1a;
	font-weight: bold;
	margin: 0 15px;
	padding: 5px 10px;
	border-radius: 4px;
	transition: background-color 0.3s, color 0.3s;
}

main {
	width: 1160px;
  	height: 310px;
	background-color: rgba(255, 255, 255, 0.7);
	padding: 20px;
	border-radius: 4px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	margin-top: 20px;
}

.map-section {
	background-color: rgba(255, 255, 255, 0.7);
	padding: 20px;
	border-radius: 4px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	margin-top: 20px;
}

#world-map {
	width: 100%;
	height: 500px;
	border: 1px solid #ccc;
	border-radius: 6px;
}

footer {
	text-align: center;
	margin-top: 20px;
	padding: 10px 0;
	color: #777;
}

.main-content {
	display: flex;
	gap: 40px;
	align-items: flex-start;
	margin-top: 20px;
	flex-wrap: wrap;
}

.notice-board {
	flex: 1;
	min-width: 300px;
	max-height: 256px;
	overflow-y: auto;
	scrollbar-width: thin;
	scrollbar-color: rgba(0, 0, 0, 0.15) transparent;
}

.notice-board::-webkit-scrollbar {
	width: 6px;
	background: transparent;
}

.notice-board::-webkit-scrollbar-thumb {
	background: rgba(0, 0, 0, 0.15);
	border-radius: 4px;
}

.recent-slide-container {
	flex: 1;
	min-width: 420px;
	max-height: 256px;
	overflow-y: auto;
	scrollbar-width: thin;
	scrollbar-color: rgba(0, 0, 0, 0.15) transparent;
}

.recent-slide-container::-webkit-scrollbar {
	width: 6px;
	background: transparent;
}

.recent-slide-container::-webkit-scrollbar-thumb {
	background: rgba(0, 0, 0, 0.15);
	border-radius: 4px;
}

.recent-item {
	height: 80px;
	margin: 4px 0;
	padding: 8px 4px 8px 12px;
	background: rgba(255, 255, 255, 0.92);
	border-left: 5px solid #ff9900;
	border-radius: 6px;
	display: flex;
	flex-direction: column;
	justify-content: center;
	box-shadow: 0 2px 4px rgba(0, 0, 0, 0.08);
	font-size: 14px;
	text-align: left;
	color: #333;
}

.recent-item>div {
	margin-bottom: 4px;
}

.recent-item strong {
	color: #ff9900;
	font-weight: 600;
}

.arrow {
	margin: 0 4px;
}

.label-text {
	color: #000000;
	font-weight: 600;
}

.notice-item {
	background: rgba(255, 255, 255, 0.92);
	border-left: 5px solid #007bff;
	border-radius: 6px;
	margin-bottom: 10px;
	box-shadow: 0 2px 4px rgba(0, 0, 0, 0.08);
}

.notice-header {
	cursor: pointer;
	padding: 12px 16px;
	font-weight: 600;
	font-size: 15px;
	color: #333;
	transition: background-color 0.3s;
}

.notice-header:hover {
	background-color: #f0f8ff;
}

.notice-body {
	padding: 12px 16px;
	border-top: 1px solid #ddd;
	display: none;
	color: #555;
	font-size: 14px;
}
</style>
</head>

<body>

	<%@ include file="/WEB-INF/views/header.jsp"%>

	<div class="container">
		<main>
			<h3><spring:message code="label.Notice" /></h3>
			<div class="main-content">
				<div class="notice-board">
					<c:forEach var="boardIdDto" items="${noticeBoard}">
						<div class="notice-item">
							<div class="notice-header" onclick="toggleNotice(this)">
								[공지] ${boardIdDto.boardTitle}</div>
							<div class="notice-body">${boardIdDto.board}</div>
						</div>
					</c:forEach>
				</div>

				<c:if test="${not empty Recently}">
					<div class="recent-slide-container">
						<c:forEach var="plane" items="${Recently}">
							<div class="recent-item">
								<div>
									<strong>${plane.departureName}</strong> <span class="arrow">→</span>
									<strong>${plane.destinationName}</strong>
								</div>
								<div>
									<span class="label-text"><spring:message
											code="label.DepartureDate" /></span> <span class="label-text">${plane.formattedDate}</span>&nbsp;&nbsp;
									<span class="label-text"><spring:message
											code="label.Flight_time" /></span> <span class="label-text">${plane.formattedTime}</span>
								</div>
							</div>
						</c:forEach>
					</div>
				</c:if>
			</div>
		</main>

		<section class="map-section">
			<div id="world-map"></div>
		</section>

	</div>

	<script>
function toggleNotice(header) {
    const body = header.nextElementSibling;
    const isVisible = body.style.display === "block";
    document.querySelectorAll('.notice-body').forEach(el => el.style.display = "none");
    if (!isVisible) {
        body.style.display = "block";
    }
}
/* 지도 */
window.onload = function () {
	const bounds = [
	    [-90, -180],
	    [90, 180]
	  ];

	  const map = L.map('world-map', {
	    center: [37.4602, 126.4407],
	    zoom: 3,
	    minZoom: 2,
	    maxBounds: bounds,
	    maxBoundsViscosity: 1.0
	  });

    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
        attribution: '&copy; OpenStreetMap contributors'
    }).addTo(map);

    const incheon = [37.4602, 126.4407];
    L.marker(incheon).addTo(map).bindPopup("출발지: 인천국제공항");

    const destinations = [
        { name: "오사카-간사이", coords: [34.4342, 135.2329] },
        { name: "베이징", coords: [40.0801, 116.5846] },
        { name: "밴쿠버", coords: [49.1947, -123.1792] },
        { name: "로스앤젤레스", coords: [33.9416, -118.4085] },
        { name: "삿포로", coords: [42.7752, 141.6923] },
        { name: "방콕", coords: [13.6900, 100.7501] },
        { name: "다낭", coords: [16.0430, 108.1999] },
        { name: "파리", coords: [49.0097, 2.5479] },
        { name: "뉴욕", coords: [40.6413, -73.7781] }
    ];

    destinations.forEach(dest => {
        L.marker(dest.coords).addTo(map).bindPopup("도착지: " + dest.name);
    });
};
</script>

	<footer>&copy; 2025 Airplane Reservation. All Rights Reserved.</footer>

</body>
</html>
