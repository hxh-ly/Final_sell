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
<iframe src="" frameborder="0" name="iframeContent" style="display: none;"></iframe>
<form:form  class="layui-form" target="iframeContent" modelAttribute="client" action="${requestScope.request.contextPath }/client/addClient" method="post"> <!-- 提示：如果你不想用form，你可以换成div等任何一个普通元素 -->
<div class="layui-form-item">
    <div class="layui-input-block">
        <form:hidden path="id"/>
        <input type="hidden"  name="_method" value="put" autocomplete="off" class="layui-input" />
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">客户名</label>
        <div class="layui-input-inline">
            <form:input path="name" type="text"  placeholder="请输入" autocomplete="off" class="layui-input" />
            <form:errors path="name"></form:errors>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">联系方式</label>
        <div class="layui-input-inline">
            <form:input type="text" path="phone" placeholder="请输入" autocomplete="off" class="layui-input"/>
        </div>
    </div>

    <div class="layui-form">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">发货地址</label>
                <div class="layui-input-inline">
                    <form:input type="text" path="location" placeholder="请输入" autocomplete="off" class="layui-input"/>
                </div>
            </div>

        </div>
        </form:form>
        <script src="${pageContext.request.contextPath}/static/layui.js"></script>
        <script>
            layui.use(['form','laydate'], function(){
                let form = layui.form;
                let $ =layui.$;
                $("iframe[name=iframeContent]").on("load", function() {
                    var responseText = $("iframe")[0].contentDocument.body.getElementsByTagName("pre")[0].innerHTML;
                    let parseJSON = $.parseJSON(responseText);

                    if(parseJSON.code===101)
                    alert("输入错误");
                    else{
                        let index = parent.layer.getFrameIndex(window.name); //获取窗口索引
                        parent.layer.close(index);
                        console.log("是否执行关闭后重载")
                        window.parent.reloadTable();
                    }
                    //以下就可以判断并处理返回值
                })
                //各种基于事件的操作，下面会有进一步介绍
            });



        </script>


</body>
</html>
