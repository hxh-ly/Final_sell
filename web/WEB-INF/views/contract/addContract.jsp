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
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta  name="_csrf_header" content="${_csrf.headerName}"/>
    <link rel="stylesheet" href="/static/css/layui.css">
    <title>Title</title>
</head>
<body>
<form  class="layui-form"  onsubmit="return false" action="##" method="post"> <!-- 提示：如果你不想用form，你可以换成div等任何一个普通元素 -->
<div class="layui-form-item">
    <div class="layui-input-block">

        <input type="hidden"  name="_method" id="id"  autocomplete="off" class="layui-input" />
    </div>
    <div class="layui-input-block">

        <input type="hidden"  name="_method1" id="status" value="0" autocomplete="off" class="layui-input" />
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">客户电话</label>
        <div class="layui-input-inline">
            <input name="clientPhone" id="clientPhone" type="text"  placeholder="请输入" autocomplete="off" class="layui-input" />
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">销售员电话</label>
        <div class="layui-input-inline">
            <input type="text" name="staffPhone" id="staffPhone" placeholder="请输入" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-inline">
        <label class="layui-form-label">签订日期</label>
        <div class="layui-input-inline">
            <input type="text" name="signDate" id="LAY-component-form-group-date" lay-verify="date" placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
    <div class="layui-inline">
        <label class="layui-form-label">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
        <div class="layui-input-inline">
            <input class="layui-btn layui-btn-normal btn"  type="button" onclick="toSendContract()" value="录入合同"/></div>
    </div>
    </div>
</div>
</form>

    <script src="${pageContext.request.contextPath}/static/layui.js"></script>
    <script>
        layui.use(['form','laydate'], function(){
            let form = layui.form;
            let laydate=layui.laydate;
            laydate.render({
                elem: '#LAY-component-form-group-date'
            });
            //各种基于事件的操作，下面会有进一步介绍

        });
    </script>
    <script>

       function toSendContract(){
           layui.use(['form','laydate'], function(){
               let form = layui.form;
               var $ = layui.$;
               var header = $("meta[name='_csrf_header']").attr("content");
               var token =$("meta[name='_csrf']").attr("content");
               let id = Math.floor(Math.random()*10000)
               let status = $.trim($('.layui-input-block #status').val());
               let clientPhone = $.trim($('.layui-input-inline #clientPhone').val());
               let staffPhone = $.trim($('.layui-input-inline #staffPhone').val());
               let signDate = $('.layui-input-inline #LAY-component-form-group-date').val();
               console.log("执行了")
               $.ajax(
                   {
                       url:"${pageContext.request.contextPath}/contract/addContract",
                       type:"POST",
                       data:JSON.stringify({
                           "id":id,
                           "status":0,
                           "client":{"id":null,"name":null,"phone":clientPhone,"location":null},
                           "staff":{"id":null,"name":null,"phone":staffPhone},
                           "signdate":signDate
                       }),
                       contentType:'application/json;charset=UTF-8',
                       dataType:"json",


                       success:(res=>{
                           console.log(res)
                           if(res.code==0){
                               alert("合同录入成功,请录入清单");
                               //window.location.reload();
                               window.location.href="${pageContext.request.contextPath}/contract/toContractList"
                           }

                       })
                   }
               )
           });
       }
    </script>
</body>
</html>
