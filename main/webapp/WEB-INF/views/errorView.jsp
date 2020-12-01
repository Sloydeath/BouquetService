<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Ошибка</title>
</head>

<body>

<h3>Ошибка! Вы ввели неверные данные</h3>
<h4>${errorString}</h4>

<a href="${pageContext.request.contextPath}/login">Вернуться на главную страницу пользователя</a><br>
<a href="${pageContext.request.contextPath}/flowerRange">К списку цветов</a>

</body>
</html>