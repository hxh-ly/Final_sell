<%--
  Created by IntelliJ IDEA.
  User: 可爱的小栩
  Date: 2020/12/14
  Time: 15:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%response.setHeader("X-Frame-Options", "SAMEORIGIN");%>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/static/css/layui.css">
</head>


<body style="overflow-y:scroll ">


<%--@elvariable id="addQuery" type="com"--%>

<button type="button" class="layui-btn layui-btn-normal" onclick="clickMe(this)">添加</button>
<button type="button" class="layui-btn layui-btn-normal" onclick="window.location.href='${pageContext.request.contextPath}/goods/NavToQuery'">查询</button>
<table class="layui-table" lay-even lay-skin="nob" >
    <colgroup>
        <col width="150">
        <col width="150">
        <col width="150">
        <col width="150">
        <col width="150">
    </colgroup>
    <thead>
    <tr>
        <th>发货单ID</th>
        <th>发货商品</th>
        <th>发货数量</th>
        <th>库存数量</th>
        <th>发货地址</th>
        <th>物流状态</th>
        <th>操作</th>
        <th>进货</th>
    </tr>
    </thead>
    <tbody id="test1">
    <c:forEach items="${requestScope.deliveryInfo }" var="delivery">
        <tr>
            <td>${delivery.id}</td>
            <td>${delivery.name}</td>
            <td>${delivery.dnumber}</td>
            <td>${delivery.quantity}</td>
            <td>${delivery.location}</td>
            <td>
                <c:choose>
                    <c:when test="${delivery.dstatus==0}">
                        未发货
                    </c:when>
                    <c:when test="${delivery.dstatus==1}">
                        已发货
                    </c:when>

                </c:choose>



            </td>
            <td><c:if test="${delivery.dstatus==0}"><input type="button" class="layui-btn layui-btn-normal btn" onclick="window.location.href='${pageContext.request.contextPath}/delivery/doDelivery?eid=${delivery.id}'"  value="发货"/></c:if></td>
            <td><input type="button" class="layui-btn layui-btn-normal btn" onclick="openStock(this)" data-id="${delivery.id}"  value="进货"/></td>
        </tr>

    </c:forEach>
    </tbody>
</table>

<script src="${pageContext.request.contextPath}/static/layui.js"></script>
<script>
    function openStock(e){
        let eid=e.getAttribute("data-id");
        console.log(eid)
        layui.use('layer', function(){

            let layer = layui.layer;
            layer.open({
                id:"LAY_addStock",
                type: 2,
                content: "${pageContext.request.contextPath}/delivery/addStock?eid="+eid  //这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content: ['http://sentsin.com', 'no']
                ,
                btn:['提交','取消'],
                area:['300px','300px'],
                yes:function (index,layero){
                    console.log("aaa")
                    var body = layer.getChildFrame('body', index);
                    var f = body.find(".layui-form");
                    f.submit();
                    layer.close(index);
                    setTimeout(function (){location.reload();},500);
                },btn2: function (index,layero){
                    layer.close(index);
                }

            });
        });
    }
    function clickMe(e) {
        console.log(e.getAttribute("data-id"));
        let flag=e.getAttribute("data-id")
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

    function clickToQuery(){
        layui.use(['layer','jquery'], function(){
            let layer = layui.layer;
            let $=layui.jquery;
            layer.open({
                type:2,
                title:'修改',
                content:'${pageContext.request.contextPath}/goods/NavAdd',


            })
        });
    }
    let msg="${requestScope.msg}"
    console.log(msg)
    layui.use('layer', function(){
        let layer = layui.layer;
        if(msg==0&&msg!=="")
            layer.msg("库存不足,请进货");
        if(msg==1)
            layer.msg("发货成功");
        if(msg==2)
            layer.msg("发货成功,库存为0请进货");
        if(msg==3)
            layer.msg("进货失败");
        if(msg==4)
            layer.msg("进货成功");

    });

</script>
</body>
</html>