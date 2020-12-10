<%--
  Created by IntelliJ IDEA.
  User: 可爱的小栩
  Date: 2020/12/7
  Time: 10:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%response.setHeader("X-Frame-Options", "SAMEORIGIN");%>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/static/css/layui.css">
</head>


<body>

<form:form  modelAttribute="addGood" id="testAdd" cssStyle="display: none"
            class="layui-form"  action="${requestScope.request.contextPath }/goods/add"
            method="post">
<div class="layui-form-item">
    <div class="layui-input-block">    <form:hidden path="id"/>
        <input type="hidden"  name="_method" value="put" autocomplete="off" class="layui-input" />
    </div>
    <label class="layui-form-label">Name:</label>
    <div class="layui-input-block"><form:input class="layui-input" path="goodsName"/></div>
    <label class="layui-form-label">Price:</label>
    <div class="layui-input-block"><form:input class="layui-input" path="price"/></div>
    <label class="layui-form-label">Amount:</label>
    <div class="layui-input-block"><form:input class="layui-input" path="amount"/></div>
</div>
    <input type="submit" value="submit" class="layui-btn layui-btn-normal">
</form:form>
<%--@elvariable id="addQuery" type="com"--%>

<button type="button" class="layui-btn layui-btn-normal" onclick="clickMe(this)">添加</button>
<button type="button" class="layui-btn layui-btn-normal" onclick="window.location.href='${pageContext.request.contextPath}/goods/NavToQuery'">查询</button>
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
        <th>ID</th>
        <th>商品名</th>
        <th>商品价格</th>
        <th>库存</th>
        <th>下架</th>
        <th>修改</th>
    </tr>
    </thead>
    <tbody id="test1">
    <c:forEach items="${requestScope.allGoods }" var="g">
        <tr>
            <td>${g.id}</td>
            <td>${g.goodsName}</td>
            <td>${g.price}</td>
            <td>${g.amount}</td>
            <td><input type="button" class="layui-btn layui-btn-normal btn" onclick="window.location.href='${pageContext.request.contextPath}/goods/delete?id=${g.id}'" data-id="${g.id}" value="下架"/></td>
            <td><input type="button" class="layui-btn layui-btn-normal btn" onclick="window.location.href='${pageContext.request.contextPath}/goods/updatePop?id=${g.id}'" data-id="${g.id}" value="修改"/></td>
        </tr>

    </c:forEach>
    </tbody>
</table>

<script src="${pageContext.request.contextPath}/static/layui.js"></script>
<script>

    function clickMe(e) {
        console.log(e.getAttribute("data-id"));
        let flag=e.getAttribute("data-id")
        layui.use(['layer','jquery'], function(){
            let layer = layui.layer;
            let $=layui.jquery;
            layer.open({
                type:1,
                title:'修改',
                content:$('#testAdd')

            })
        });
    }

    function clickToQuery(){
        layui.use(['layer','jquery'], function(){
            let layer = layui.layer;
            let $=layui.jquery;
            layer.open({
                type:2,
                title:'修改',
                content:'${pageContext.request.contextPath}/goods/NavAdd'

            })
        });
    }
</script>
</body>
</html>
