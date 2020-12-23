
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

            <input type="hidden" value="${requestScope.O_gid}" name="_method" id="id"  autocomplete="off" class="layui-input" />
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">数量</label>
            <div class="layui-input-inline">
                <input type="text" name="amount" id="amount" placeholder="请输入" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
                <div class="layui-input-inline " style="visibility: hidden">
                    <input  class="layui-btn layui-btn-normal btn" id="toUpdateOGoods" type="button" onclick="toSendContract()" value="添加货单货物"/></div>
            </div>
        </div>
    </div>
</form>

<script src="${pageContext.request.contextPath}/static/layui.js"></script>
<script>
    layui.use(['form','laydate'], function(){
        let form = layui.form;
        let laydate=layui.laydate;
    });
</script>
<script>

    function toSendContract(){
        layui.use(['form','laydate'], function(){
            let form = layui.form;
            var $ = layui.$;
            var header = $("meta[name='_csrf_header']").attr("content");
            var token =$("meta[name='_csrf']").attr("content");

            let amount = $.trim($('.layui-input-inline #amount').val());
            let O_gid = $.trim($('.layui-input-block #id').val());
            console.log(O_gid)
            $.ajax(
                {
                    url:"${pageContext.request.contextPath}/contract/updateOGoods",
                    data:JSON.stringify( {
                        "amount":parseInt(amount),
                        "oid":${requestScope.O_gid}
                    }),
                    type: "post",
                    dataType: "json",
                    contentType: "application/json",
                    success:(res=>{
                        console.log(res)
                        if(res.code==0){
                            alert("清单货品数量修改成功,请及时发货");

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
