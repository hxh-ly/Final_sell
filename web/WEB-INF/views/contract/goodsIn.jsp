<%--
  Created by IntelliJ IDEA.
  User: 可爱的小栩
  Date: 2020/12/10
  Time: 21:00
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
<input type="button" class="layui-btn layui-btn-normal btn" onclick="window.location.href='${pageContext.request.contextPath}/contract/showOrder?cid=${requestScope.back}'" data-id="${g.id}" value="返回"/>
<table class="layui-table" lay-even lay-skin="nob" >
    <colgroup>
        <col width="150">
        <col width="150">
        <col width="150">
        <col width="150">
        <col width="150">
    </colgroup>
    <thead>
    <tr>
        <th>商品ID</th>
        <th>商品名称</th>
        <th>价格</th>
        <th>数量</th>
        <th>修改清单信息</th>
        <th>生成发货单</th>
        <th>生成进货单</th>
    </tr>
    </thead>
    <tbody id="test1">
    <c:forEach items="${requestScope.GoodsInOrder }" var="gi">
        <tr>
            <td>${gi.id}</td>
            <td>${gi.goodsName}</td>
            <td>${gi.price}</td>
            <td>${gi.amount}</td>
            <td><c:if test="${requestScope.contract.status==0}"><input type="button" class="layui-btn layui-btn-normal btn" onclick="window.location.href='${pageContext.request.contextPath}/contract/update?id=${gi.id}'"  value="update"/></c:if></td>
            <td><input type="button" class="layui-btn layui-btn-danger btn" onclick="window.location.href='${pageContext.request.contextPath}/contract/newReceipt?goodId=${gi.id}&orderId=${requestScope.orderId}&contractId=${contract.id}'"  value="生成"/></td>
            <td><input type="button" class="layui-btn layui-btn-danger btn" onclick="window.location.href='${pageContext.request.contextPath}/contract'"  value="进货单"/></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<script src="${pageContext.request.contextPath}/static/layui.js"></script>
<script>
    let a="${requestScope.msg}"
    console.log(a)
    layui.use('layer', function(){
        var layer = layui.layer;
        if(a==0&&a!="")
            layer.msg("请勿重复生成发货单");
        if(a==1)
            layer.msg("生成发货单成功");
        if(a==2)
            layer.msg("库存不足 请生成进货单");

    });
</script>
</body>
</html>
