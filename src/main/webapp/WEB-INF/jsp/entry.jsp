<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>マイ家計簿</title>
</head>
<body>
<h1>支出記録</h1>
<c:forEach var="item" items="${errorList}">
    <p><c:out value="${item}" /></p>
</c:forEach>
<form action="PostTransactionServlet" method="POST">
支出用途：<input type="text" name="purpose"><br>
支出金額：<input type="number" name="money"><br>
支出日付：<input type="date" name="date"><br>
<input type="submit" value="記録">
<a href="TransactionServlet">やめる</a>
</form>
</body>
</html>