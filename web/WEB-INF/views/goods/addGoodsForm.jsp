<%--
  Created by IntelliJ IDEA.
  User: 可爱的小栩
  Date: 2020/12/18
  Time: 17:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%response.setHeader("X-Frame-Options", "SAMEORIGIN");%>
<html>
<head>
    <link rel="stylesheet" href="/static/css/layui.css">
    <title>Title</title>
</head>
<body>
<form:form  class="layui-form" modelAttribute="goods" action="${requestScope.request.contextPath }/goods/addGoods" method="post"> <!-- 提示：如果你不想用form，你可以换成div等任何一个普通元素 -->
<div class="layui-form-item">
    <div class="layui-input-block">
        <form:hidden path="id"/>
        <input type="hidden"  name="_method" value="put" autocomplete="off" class="layui-input" />
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">商品名</label>
        <div class="layui-input-inline">
            <input name="goodsName" type="text"  placeholder="请输入" autocomplete="off" class="layui-input" />
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">价格</label>
        <div class="layui-input-inline">
            <input type="text" name="price" placeholder="请输入" autocomplete="off" class="layui-input">
        </div>
    </div>


        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">库存</label>
                <div class="layui-input-inline">
                    <input type="text" name="amount" placeholder="请输入" autocomplete="off" class="layui-input">
                </div>
            </div>

        </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <slabel class="layui-form-label">类别</slabel>
            <div class="layui-input-inline">
                <input type="text" name="category" placeholder="请输入" autocomplete="off" class="layui-input">
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
