<%--
  Created by IntelliJ IDEA.
  User: 可爱的小栩
  Date: 2020/12/10
  Time: 11:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%response.setHeader("X-Frame-Options", "SAMEORIGIN");%>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/static/css/layui.css">
</head>
<body>

<input type="button" class="layui-btn layui-btn-normal btn" onclick="window.location.href='${pageContext.request.contextPath}/contract/toContractList'"  value="返回"/>
<c:if test="${requestScope.contract.status==0}">
<input type="button" class="layui-btn layui-btn-normal btn" onclick="addOrder()"  value="添加清单"/>
</c:if>
<table class="layui-table" lay-even lay-skin="nob" >
    <colgroup>
        <col width="150">
        <col width="150">
        <col width="150">
        <col width="150">
    </colgroup>
    <thead>
    <tr>
        <th>清单ID</th>

        <th>清单物品</th>



    </tr>
    </thead>
    <tbody id="test1">
    <c:forEach items="${requestScope.ContainOrder }" var="co">
        <tr>
            <td>${co.id}</td>
            <td><input type="button" class="layui-btn layui-btn-normal btn" onclick="window.location.href='${pageContext.request.contextPath}/contract/showGoods?orderId=${co.id}&contractId=${requestScope.contractId}'"  value="查看"/></td>
            </tr>
    </c:forEach>
    </tbody>
</table>
<script src="${pageContext.request.contextPath}/static/layui.js"></script>
<script>
    function addOrder(){
        let cid=${requestScope.contractId}
            layui.use(['layer','jquery'], function(){
                let layer = layui.layer;
                let $=layui.jquery;
                //添加清单
                $.ajax({
                    url:'${pageContext.request.contextPath}/contract/addOrder?cid='+cid,
                    success:(res=>{
                        alert("添加清单成功，请录入清单物品")
                        window.location.reload()
                    })
                })
            });
    }
</script>
<script>
    let a="${requestScope.msg}"
    console.log(a)
    layui.use('layer', function(){
        var layer = layui.layer;
        if(a==2)
            layer.msg("库存不足 请生成进货单");

    });
</script>
</body>
</html>
