<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<style>
  header {
    font-family: Arial, sans-serif !important;
    font-size: 16px;
    background-color: #FF9800;
    color: #fff;
    padding: 20px 0 0 0;
    box-sizing: border-box;
  }

  .inner-header {
    max-width: 1200px;
    margin: 0 auto;
    padding: 0 20px;
    display: flex;
    align-items: center;
    justify-content: space-between;
    box-sizing: border-box;
    min-height: 60px;
  }

  header h1 {
    margin: 0;
    font-size: 32px !important;
    font-weight: normal;
    font-family: Arial, sans-serif !important;
    text-align: left;
    flex-shrink: 0;
    line-height: 1;
  }

  header h1 a {
    color: inherit;
    text-decoration: none;
    display: inline-block;
    line-height: 1;
  }

  .right-menu {
    display: flex;
    align-items: center;
    gap: 20px;
    white-space: nowrap;
  }

  .right-menu a {
    color: #fff;
    font-weight: bold;
    text-decoration: none;
    line-height: 1;
    padding: 2px 0;
  }

  .right-menu a:hover {
    text-decoration: underline;
  }

  .language-selector label {
    font-weight: bold;
    margin-right: 5px;
    color: #fff;
    white-space: nowrap;
    line-height: 1;
  }

  #languageSelect {
    padding: 5px 10px;
    border-radius: 4px;
    border: none;
    font-weight: bold;
    background-color: #ffffff;
    color: #1a1a1a;
    font-size: 14px;
    cursor: pointer;
    box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);
    transition: background-color 0.3s ease;
    line-height: 1;
  }

  #languageSelect:hover {
    background-color: #f0f0f0;
  }

  /* ✅ nav 스타일 추가 */
  nav {
    background-color: #fff;
    border-top: 2px solid #e0e0e0;
    border-bottom: 2px solid #1a1a1a;
    text-align: center;
    padding: 10px 0;
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

  nav a:hover {
    background-color: #FF9800;
    color: #ffffff;
  }
</style>

<header>
  <div class="inner-header">
    <h1>
      <a href="<c:url value='/' />">
        <spring:message code="label.Main" />
      </a>
    </h1>

    <div class="right-menu">
      <div class="language-selector">
        <label for="languageSelect">Language:</label>
        <select id="languageSelect" onchange="location.href='?lang=' + this.value;">
          <option value="ko_KR" ${param.lang == 'ko_KR' ? 'selected' : ''}>한국어</option>
          <option value="en_US" ${param.lang == 'en_US' ? 'selected' : ''}>English</option>
          <option value="ja_JP" ${param.lang == 'ja_JP' ? 'selected' : ''}>日本語</option>
        </select>
      </div>

      <c:choose>
        <c:when test="${empty sessionScope.loginUser}">
          <a href="<c:url value='/login' />"><spring:message code="label.login" /></a>
          <a href="<c:url value='/regist' />"><spring:message code="register.title" /></a>
        </c:when>
        <c:otherwise>
          <a href="<c:url value='/user/logout' />"><spring:message code="label.logout" /></a>
          <a href="<c:url value='/myPage' />">${sessionScope.username}</a>
        </c:otherwise>
      </c:choose>
    </div>
  </div>

  <nav>
    <a href="<c:url value='/Reservation'/>"><spring:message code="label.Reservation" /></a>
    <c:if test="${not empty sessionScope.loginUser}">
      <a href="<c:url value='/user/regist'/>"><spring:message code="label.BookingConfirmation" /></a>
    </c:if>
    <a href="<c:url value='/board'/>"><spring:message code="board.title" /></a>
  </nav>
</header>
