<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="_menu.jsp"></jsp:include>
<jsp:include page="_headerForUser.jsp"></jsp:include>

<html>
<head>
    <title>Заказ</title>

</head>
<body>

<h3>Ваш заказ принят! Ожидайте звонка оператора!</h3>

<a href="${pageContext.request.contextPath}/login">Вернуться на главную страницу пользователя</a><br/>

</body>
</html>
