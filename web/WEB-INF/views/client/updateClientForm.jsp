
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%response.setHeader("X-Frame-Options", "SAMEORIGIN");%>
<html>
<head>
    <link rel="stylesheet" href="/static/css/layui.css">
    <title>Title</title>
</head>
<body>
<form:form  class="layui-form" modelAttribute="client" action="${requestScope.request.contextPath }/client/addClient" method="post"> <!-- 提示：如果你不想用form，你可以换成div等任何一个普通元素 -->
<div class="layui-form-item">
    <div class="layui-form-item">
        <label class="layui-form-label">客户名</label>
        <div class="layui-input-inline">
            <form:input path="id" type="text"  placeholder="请输入" autocomplete="off" class="layui-input" />
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">客户名</label>
        <div class="layui-input-inline">
            <form:input path="name" type="text"  placeholder="请输入" autocomplete="off" class="layui-input" />
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">联系方式</label>
        <div class="layui-input-inline">
            <input type="text" name="phone" placeholder="请输入" autocomplete="off" class="layui-input">
        </div>
    </div>

    <div class="layui-form">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">发货地址</label>
                <div class="layui-input-inline">
                    <input type="text" name="location" placeholder="请输入" autocomplete="off" class="layui-input">
                </div>
            </div>

        </div>
        </form:form>
        <script src="${pageContext.request.contextPath}/static/layui.js"></script>
        <script>
            layui.use(['form','laydate'], function(){
                let form = layui.form;

                //各种基于事件的操作，下面会有进一步介绍
            });
        </script>
</body>
</html>
