<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="_menu.jsp"></jsp:include>
<jsp:include page="_headerForUser.jsp"></jsp:include>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Error</title>
</head>

<body>

<h3>Ошибка при добавлении. Попробуйте снова</h3>
<h4>${errorString}</h4>

<a href="${pageContext.request.contextPath}/flowerRange">Вернуться к списку цветов</a>

</body>
</html>