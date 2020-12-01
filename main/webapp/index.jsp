<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/WEB-INF/views/_menu.jsp"></jsp:include>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>101 роза</title>
</head>

<body>

<h1>Добро пожаловать</h1><br/>
<h2>Сервис составления букета цветов</h2>

<ul>
    <li><a href="${pageContext.request.contextPath}/login">Страница пользователя</a></li>
    <li><a href="${pageContext.request.contextPath}/flowerListMainPage">Ассортимент</a></li>
</ul>

</body>
</html>