<%--
  Created by IntelliJ IDEA.
  User: 可爱的小栩
  Date: 2020/12/7
  Time: 21:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
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
<div  id="layerJump">
<form:form  modelAttribute="goods" class="layui-form"  action="${requestScope.request.contextPath }/goods/update" method="post">
        <div class="layui-form-item">
            <div class="layui-input-block">
                <form:hidden path="id"/>
                <input type="hidden"  name="_method" value="put" autocomplete="off" class="layui-input" />
            </div>
            <label class="layui-form-label">商品名字</label>
            <div class="layui-input-block">
                <form:input type="text" path="goodsName" lay-verify="required"  autocomplete="off" class="layui-input"/>
            </div>
            <label class="layui-form-label">价格</label>
            <div class="layui-input-block">
                <form:input  type="text"  path="price" lay-verify="required" autocomplete="off" class="layui-input"/>
            </div>
            <label class="layui-form-label">数量</label>
            <div class="layui-input-block">
                <form:input type="text"  path="amount" lay-verify="required"  autocomplete="off" class="layui-input"/>
            </div>
        </div>
    <input type="submit" value="submit" class="layui-btn layui-btn-normal">
</form:form>
</div>
<script src="${pageContext.request.contextPath}/static/layui.js"></script>

</body>
</html>
