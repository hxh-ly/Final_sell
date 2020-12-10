<%--
  Created by IntelliJ IDEA.
  User: 可爱的小栩
  Date: 2020/12/10
  Time: 11:19
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
<input type="button" class="layui-btn layui-btn-normal btn" onclick="window.location.href='${pageContext.request.contextPath}/contract/contractList'" data-id="${g.id}" value="返回"/>
<table class="layui-table" lay-even lay-skin="nob" >
    <colgroup>
        <col width="150">
        <col width="150">
        <col width="150">
        <col width="150">
    </colgroup>
    <thead>
    <tr>
        <th>清单ID</th>
        <th>清单状态</th>
        <th>清单物品</th>
        <th>good</th>
        <th>生成发货单</th>
        <th>修改清单信息</th>
    </tr>
    </thead>
    <tbody id="test1">
    <c:forEach items="${requestScope.ContainOrder }" var="co">
        <tr>
            <td>${co.id}</td>
            <td>1</td>
            <td>${co.goods[0].amount}</td>
            <td><input type="button" class="layui-btn layui-btn-normal btn" onclick="window.location.href='${pageContext.request.contextPath}/contract/delete?id=${ac.id}'"  value="货物"/></td>
            <td><input type="button" class="layui-btn layui-btn-normal btn" onclick="window.location.href='${pageContext.request.contextPath}/contract/delete?id=${ac.id}'" data-id="${g.id}" value="生成"/></td>
            <td><input type="button" class="layui-btn layui-btn-normal btn" onclick="window.location.href='${pageContext.request.contextPath}/contract/delete?id=${ac.id}'" data-id="${g.id}" value="update"/></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<script src="${pageContext.request.contextPath}/static/layui.js"></script>
</body>
</html>
