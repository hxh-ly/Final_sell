<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%response.setHeader("X-Frame-Options", "SAMEORIGIN");%>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/static/css/layui.css">
</head>
<body>
<button type="button" class="layui-btn layui-btn-normal" onclick="clickMe(this)">添加</button>
<button type="button" class="layui-btn layui-btn-normal" onclick="window.location.href='${pageContext.request.contextPath}/goods/NavToQuery'">查询</button>
<table class="layui-table" lay-even lay-skin="nob" >
<colgroup>
    <col width="150">
    <col width="150">
    <col width="150">
    <col width="150">
    <col width="150">
    <col width="150">
</colgroup>
<thead>
<tr>
    <th>ID</th>
    <th>客户</th>
    <th>销售人员</th>
    <th>签到日期</th>
    <th>合同状态</th>
    <th>清单详情</th>
    <th>修改</th>
</tr>
</thead>
<tbody id="test1">
<c:forEach items="${requestScope.allContract }" var="ac">
    <tr>
        <td>${ac.id}</td>
        <td>${ac.client.getPhone()}</td>
        <td>${ac.staff.getPhone()}</td>
        <td>${ac.signdate}</td>
        <td>${ac.status}</td>
        <td><input type="button" class="layui-btn layui-btn-normal btn" onclick="window.location.href='${pageContext.request.contextPath}/contract/showOrder?id=${ac.id}'"  value="详情"/></td>
        <td><input type="button" class="layui-btn layui-btn-normal btn" onclick="window.location.href='${pageContext.request.contextPath}/contract/updateContract?id=${ac.id}'"  value="修改"/></td>
    </tr>

</c:forEach>
</tbody>
</table>
<script src="${pageContext.request.contextPath}/static/layui.js"></script>
</body>
</html>
