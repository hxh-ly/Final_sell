<%--
  Created by IntelliJ IDEA.
  User: 可爱的小栩
  Date: 2020/11/30
  Time: 17:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%response.setHeader("X-Frame-Options", "SAMEORIGIN");%>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/static/css/layui.css">
</head>
<body>
<table class="layui-table" lay-even lay-skin="nob">
    <colgroup>
        <col width="150">
        <col width="150">
        <col width="150">
        <col width="150">
        <col width="150">
    </colgroup>
    <thead>
    <tr>
        <th>ID</th>
        <th>username</th>
        <th>password</th>
        <th>修改</th>
        <th>删除</th>
        <th>详情</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${requestScope.allUsers }" var="g">
        <tr>
            <td>${g.id}</td>
            <td>${g.username}</td>
            <td>${g.password}</td>
            <td><a href="${pageContext.request.contextPath }/goods/edit/${g.id}">update</a></td>
            <td><a href="${pageContext.request.contextPath }/goods/delete/${g.id} ">delete</a></td>
            <td><button  type="button" class="layui-btn layui-btn-normal">点击</button></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<%--<table border="1" cellspacing="0" cellpadding="3">
    <tr>
        <th>ID</th>
        <th>username</th>
        <th>password</th>
        <th>修改</th>
        <th>删除</th>
    </tr>
<c:forEach items="${requestScope.allUsers }" var="g">
    <tr>
        <td>${g.id}</td>
        <td>${g.username}</td>
        <td>${g.password}</td>
        <td><a href="${pageContext.request.contextPath }/goods/edit/${g.id}">update</a></td>
        <td><a href="${pageContext.request.contextPath }/goods/delete/${g.id} ">delete</a></td>
    </tr>
</c:forEach>
</table>--%>

</body>
</html>
