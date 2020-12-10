<%--
  Created by IntelliJ IDEA.
  User: 可爱的小栩
  Date: 2020/12/9
  Time: 11:34
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
    <%--@elvariable id="addQuery" type=""--%>
    <form:form  modelAttribute="addQuery" id="testQuery"
                class="layui-form"  action="${requestScope.request.contextPath }/goods/query"
                method="post">
        <div class="layui-form-item">
            <div class="layui-input-block">
                <label class="layui-form-label">Id:</label>  <div class="layui-input-block"><form:input class="layui-input" path="id"/></div>

            <label class="layui-form-label">Name:</label>
            <div class="layui-input-block"><form:input class="layui-input" path="goodsName"/></div>
            <label class="layui-form-label">Price:</label>
            <div class="layui-input-block"><form:input class="layui-input" path="price"/></div>
            <label class="layui-form-label">Amount:</label>
            <div class="layui-input-block"><form:input class="layui-input" path="amount"/></div>
        </div>
        <input type="submit" value="submit" class="layui-btn layui-btn-normal">
    </form:form>

</div>
<script src="${pageContext.request.contextPath}/static/layui.js"></script>

</body>
</html>
