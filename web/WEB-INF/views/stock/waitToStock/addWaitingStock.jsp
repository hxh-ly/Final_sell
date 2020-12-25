<%--
  Created by IntelliJ IDEA.
  User: 可爱的小栩
  Date: 2020/12/16
  Time: 21:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
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
<%--<div  id="layerJump">
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
</div>--%>
<form:form  class="layui-form" modelAttribute="waitToStock" action="${requestScope.request.contextPath }/delivery/goodsStock" method="post"> <!-- 提示：如果你不想用form，你可以换成div等任何一个普通元素 -->
<div class="layui-form-item">
    <div class="layui-input-block">
        <form:hidden path="id"/>

    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">商品</label>
        <div class="layui-input-inline">
            <form:input path="goodsName" type="text"  placeholder="请输入" autocomplete="off" class="layui-input" />
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">数量</label>
        <div class="layui-input-inline">
            <form:input type="text" path="num" placeholder="请输入" autocomplete="off" class="layui-input"/>
        </div>
    </div>

    <div class="layui-form">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">进货时间</label>
                <div class="layui-input-inline">
                    <input type="text" name="deliverDate" id="deliverDate" lay-verify="date" placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input">
                </div>
            </div>

        </div>
        <div class="layui-form">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">进货经手人</label>
                    <div class="layui-input-inline">
                        <input type="text" readonly="readonly" name="userName" value="<security:authentication property="principal.username"/> " autocomplete="off" class="layui-input">
                    </div>
                </div>

            </div>
        </div>
        <!-- 更多表单结构排版请移步文档左侧【页面元素-表单】一项阅览 -->
        </form:form>
        <script src="${pageContext.request.contextPath}/static/layui.js"></script>
        <script>
            layui.use(['form','laydate'], function(){
                let form = layui.form;
                let laydate=layui.laydate;
                laydate.render({
                    elem: '#deliverDate'
                });
                //各种基于事件的操作，下面会有进一步介绍
            });
        </script>
</body>
</html>