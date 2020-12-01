<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="_menu.jsp"></jsp:include>

<!DOCTYPE html>
<html>
<head>
    <title>Registration error</title>
</head>
<body>
<h1>Такой пользователь уже зарегистрирован!</h1><br/>
<h2>Введите другой email</h2>
<a href="<c:url value="/registration"/>">Перейти к регистрации</a>
</body>
</html>
