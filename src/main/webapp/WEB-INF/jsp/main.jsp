<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>	マイ家計簿</title>
</head>
<body>
<h1>マイ家計簿</h1>
<c:forEach var="item" items="${errorList}">
    <p><c:out value="${item}" /></p>
</c:forEach>
<a href="PostTransactionServlet">記録をつける</a>
<a href="Logout">ログアウト</a>
<table border="1">
    <tr>
        <th>用途</th>
        <th>金額</th>
        <th>日付</th>
        <th>削除</th>
    </tr>
    <c:forEach var="item" items="${transactionList}">
        <tr>
            <td><c:out value="${item.purpose}"/></td>
            <td><c:out value="${item.money}"/></td>
            <td><c:out value="${item.date}"/></td>
            <td>
                <form action="TransactionServlet" method="POST">
                    <input type="hidden" name="id" value="${item.id}">
                    <button type="submit" name="delete">削除</button>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>