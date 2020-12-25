<%--
  Created by IntelliJ IDEA.
  User: 可爱的小栩
  Date: 2020/12/10
  Time: 21:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%response.setHeader("X-Frame-Options", "SAMEORIGIN");%>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/static/css/layui.css">
</head>
<body>
<input type="button" class="layui-btn layui-btn-normal btn"
       onclick="window.location.href='${pageContext.request.contextPath}/contract/showOrder?cid=${requestScope.back}'"
       data-id="${g.id}" value="返回"/>
<c:if test="${requestScope.contract.status==0}">
    <input type="button" class="layui-btn layui-btn-normal btn" onclick="addGoodsInOrder()" value="添加货单"/>
</c:if>
<table class="layui-table" lay-even lay-skin="nob">
    <colgroup>
        <col width="150">
        <col width="150">
        <col width="150">
        <col width="150">
        <col width="150">
    </colgroup>
    <thead>
    <tr>
        <th>货单ID</th>
        <th>商品名称</th>
        <th>价格</th>
        <th>数量</th>
        <th>是否发货</th>
        <%--<th>修改清单信息</th>--%>

        <th>生成发货单</th>
        <th>生成进货单</th>
    </tr>
    </thead>
    <tbody id="test1">
    <c:forEach items="${requestScope.GoodsInOrder }" var="gi">
        <tr>
                <%--    //id不是goodsId  而是订单下的货单id--%>
            <td>${gi.id}</td>
            <td>${gi.goodsName}</td>
            <td>${gi.price}</td>
            <td>${gi.amount}</td>
            <td>
                <c:choose>
                    <c:when test="${gi.isSend==0}">未发货</c:when>
                    <c:when test="${gi.isSend==1}">已发货</c:when>
                </c:choose>
            </td>

            <%--<td><c:if test="${requestScope.contract.status==0}"><input type="button"
                                                                       class="layui-btn layui-btn-normal btn"
                                                                       onclick="PopUpdateO_gid(this)" data-id="${gi.id}"
                                                                       value="update"/></c:if></td>--%>


            <td><c:if test="${gi.isGenerate==0}"><input type="button" class="layui-btn layui-btn-danger btn"
                                                        onclick="window.location.href='${pageContext.request.contextPath}/contract/newReceipt?goodsName=${gi.goodsName}&orderId=${requestScope.orderId}&contractId=${contract.id}&need=${gi.amount}'"
                                                        value="生成"/></c:if></td>
            <td><c:if test="${gi.isSend==0}"><input type="button" id="toNewStock" class="layui-btn layui-btn-danger btn"
                                                    onclick="newStock(this)" data-id="${gi.id}" value="进货单"/></c:if>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<script src="${pageContext.request.contextPath}/static/layui.js"></script>
<script>
    let a = "${requestScope.msg}"
    console.log(a)
    layui.use('layer', function () {
        let layer = layui.layer;
        if (a == 0 && a !== "")
            layer.msg("请勿重复生成发货单");
        if (a == 1)
            layer.msg("生成发货单成功");
        if (a == 2)
            layer.msg("库存不足 请生成进货单");

    });
</script>
<script>
    // window.location.href='${pageContext.request.contextPath}/order/addOrder?cid=${requestScope.back}&oid=${requestScope.orderId}'
    function addGoodsInOrder() {
        layui.use(['layer', 'jquery'], function () {
            let layer = layui.layer;
            let $ = layui.jquery;
            layer.open({
                id: "addGoods",
                type: 2,
                title: '添加',
                content: '${pageContext.request.contextPath}/contract/PopGoodsAdd?cid=${requestScope.back}&oid=${requestScope.orderId}',
                btn: ["提交", "取消"],
                yes: function (index, layero) {
                    console.log("aaa")
                    var body = layer.getChildFrame('body', index);
                    var f = body.find("#toAddGoods");
                    f.click();
                    layer.close(index);
                    setTimeout(function () {
                        window.location.reload()
                    }, 300);
                }, btn2: function (index, layero) {
                    layer.close(index);
                }

            })
        });
    }

    function PopUpdateO_gid(e) {
        O_gid = e.getAttribute("data-id");
        layui.use(['layer', 'jquery'], function () {
            let layer = layui.layer;
            let $ = layui.jquery;
            layer.open({
                id: "updateO_gids",
                type: 2,
                title: '添加',
                content: '${pageContext.request.contextPath}/contract/PopUpdateOgoods?O_gid=' + O_gid,
                btn: ["提交", "取消"],
                yes: function (index, layero) {
                    var body = layer.getChildFrame('body', index);
                    var f = body.find("#toUpdateOGoods");
                    f.click();
                    layer.close(index);
                    setTimeout(function () {
                        window.location.reload()
                    }, 300);
                }, btn2: function (index, layero) {
                    layer.close(index);
                }

            })
        });
    }

    //生成进货单
    function newStock(e) {
        let O_gid = e.getAttribute("data-id");
        layui.use('layer', function () {
            $ = layui.$;
            $.ajax({
                url: '${pageContext.request.contextPath}/contract/newAStock?O_gid=' + O_gid,
                success: (res => {
                    if (res.code == 0) {
                        alert("生成进货单成功")
                        //隐藏按钮
                        $('#toNewStock').attr("disabled", "disabled")
                        window.location.reload()
                    } else if (res.code == 101) {
                        alert("生成进货单失败")
                    }
                })
            })
        })

    }
</script>
</body>
</html>
