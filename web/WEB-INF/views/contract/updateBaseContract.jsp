
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

            <input type="hidden" value="${requestScope.contract.id}"  id="id"  autocomplete="off" class="layui-input" />
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">客户电话</label>
            <div class="layui-input-inline">
                <input type="text" name="cphone" id="cphone" value="${requestScope.contract.client.phone}" placeholder="请输入" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">销售员电话</label>
            <div class="layui-input-inline">
                <input type="text" name="sphone" id="sphone" value="${requestScope.contract.staff.phone}"  placeholder="请输入" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">签订日期</label>
            <div class="layui-input-inline">
                <input type="text" name="signdate" id="signdate" value="${requestScope.contract.signdate}" lay-verify="date" placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
                <div class="layui-input-inline " style="visibility: hidden">
                    <input  class="layui-btn layui-btn-normal btn" id="toUpdateBase" type="button" onclick="toSendContract()" value="添加货单货物"/></div>
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
            elem: '#signdate'
        });
    });
</script>
<script>

    function toSendContract(){
        layui.use(['form','laydate'], function(){
            let form = layui.form;
            var $ = layui.$;
            var header = $("meta[name='_csrf_header']").attr("content");
            var token =$("meta[name='_csrf']").attr("content");

            let id = $.trim($('.layui-input-block #id').val());
            let cphone = $.trim($('.layui-input-inline #cphone').val());
            let sphone = $.trim($('.layui-input-inline #sphone').val());
            let signdate= $.trim($('.layui-input-inline #signdate').val());
            $.ajax(
                {
                    url:"${pageContext.request.contextPath}/contract/updateBaseInfo",
                    data:JSON.stringify( {
                        "id":parseInt(id),
                        "signdate":signdate,
                        "cphone":cphone,
                        "sphone":sphone
                    }),
                    type: "post",
                    dataType: "json",
                    contentType: "application/json",
                    success:(res=>{
                        console.log(res)
                        if(res.code==0){
                            alert("清单基本修改成功");

                        }
                        if(res.code==101){
                            alert("修改失败···")
                        }

                    })
                }
            )
        });
    }
</script>
</body>
</html>
