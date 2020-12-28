<%--
  Created by IntelliJ IDEA.
  User: 可爱的小栩
  Date: 2020/12/17
  Time: 22:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%response.setHeader("X-Frame-Options", "SAMEORIGIN");%>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/static/css/layui.css">
</head>
<body>
<table id="demo" lay-filter="test"></table>
<script src="${pageContext.request.contextPath}/static/layui.js"></script>
<script>
    layui.use('table', function(){
        var table = layui.table;

        //第一个实例
        table.render({
            elem: '#demo'
            ,url: '${pageContext.request.contextPath}/stock/showStock' //数据接口
            ,page: true //开启分页
            ,width:500
            ,cols: [[ //表头
                {field: 'id', title: 'ID', width:80, sort: true, fixed: 'left'}
                ,{field: 'goodsName', title: '货名', width:80}
                ,{field: 'num', title: '数量', width:80, sort: true}
                ,{field: 'deliverDate', title: '进货日期', width:160}
                ,{field: 'userName', title: '进货人员', width: 160}

            ]]
        });

    });
</script>
</body>
</html>
