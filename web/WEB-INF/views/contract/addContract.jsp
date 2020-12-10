<%--
  Created by IntelliJ IDEA.
  User: 可爱的小栩
  Date: 2020/12/9
  Time: 12:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%response.setHeader("X-Frame-Options", "SAMEORIGIN");%>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/static/css/layui.css">
</head>
<body>
<form class="layui-form" action="">
    <div class="layui-form-item">
        <label class="layui-form-label">输入框</label>
        <div class="layui-input-block">
            <input type="text" name="title" required  lay-verify="required" placeholder="请输入标题" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">销售人员</label>
        <div class="layui-input-inline">
            <input type="password" name="password" required lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input">
        </div>
        <div class="layui-form-mid layui-word-aux">输入联系方式</div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">客户</label>
        <div class="layui-input-inline">
            <input type="password" name="password" required lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input">
        </div>
        <div class="layui-form-mid layui-word-aux">输入联系方式</div>
    </div>
    <input type="button" class="layui-btn layui-btn-normal btn" onclick="addContract()" data-id="${g.id}" value="添加清单"/>
    <input type="submit" class="layui-btn layui-btn-normal btn" value="确定录入"/>

</form>





<script src="${pageContext.request.contextPath}/static/layui.js"></script>
<script>
  function  addContract(){
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
</script>
</body>
</html>
