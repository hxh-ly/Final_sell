
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
            <label class="layui-form-label">商品名</label>
            <div class="layui-input-inline">
                <input name="goodsName" id="goodsName" type="text"  placeholder="请输入" autocomplete="off" class="layui-input" />
            </div>
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
                <div class="layui-input-inline">
                    <input class="layui-btn layui-btn-normal btn" id="toAddGoods" type="button" onclick="toSendContract()" value="添加货单货物"/></div>
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

            let goodsName = $.trim($('.layui-input-inline #goodsName').val());
            let amount = $.trim($('.layui-input-inline #amount').val());
            console.log(amount)
            $.ajax(
                {
                    url:"${pageContext.request.contextPath}/contract/GoodsAddInO",
                    data:JSON.stringify( {
                        "goodsName":goodsName,
                        "amount":parseInt(amount),
                        "oid":${requestScope.oid}
                    }),
                    type: "post",
                    dataType: "json",
                    contentType: "application/json",
                    success:(res=>{
                        console.log(res)
                        if(res.code==0){
                            alert("清单货物填入成功,请及时发货");

                            }

                    })
                }
            )
        });
    }
</script>
</body>
</html>
