<%--
  Created by IntelliJ IDEA.
  User: 可爱的小栩
  Date: 2020/11/29
  Time: 21:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%response.setHeader("X-Frame-Options", "SAMEORIGIN");%>
<html>
<head>
    <meta charset="utf-8">
    <title>Layui</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="/static/css/layui.css">
</head>
<body class="layui-layout-body">
<button type="button" class="layui-btn layui-btn-normal">添加</button>
<table id="demo" lay-filter="test"></table>

<script src="static/layui.js"></script>
<script >
    layui.use('table', function(){
        var table = layui.table;

        //第一个实例
        table.render({
            elem: '#demo'
            ,height: 312
            ,url: 'https://www.layui.com/demo/table/user' //数据接口
            ,page: true //开启分页
            ,parseData:function (res){
                console.log(res)
                return{
                    "code": res.status, //解析接口状态
                    "msg": res.message, //解析提示文本
                    "count": res.total, //解析数据长度
                    "data": res.data.item //解析数据列表
                }
            },
            done: function(res, curr, count){
                //如果是异步请求数据方式，res即为你接口返回的信息。
                //如果是直接赋值的方式，res即为：{data: [], count: 99} data为当前页数据、count为数据总长度
                console.log(res);

                //得到当前页码
                console.log(curr);

                //得到数据总量
                console.log(count);
            }

            ,cols: [[ //表头
                {field: 'id', title: 'ID', width:80, sort: true, fixed: 'left'}
                ,{field: 'username', title: '用户名', width:80}
                ,{field: 'sex', title: '性别', width:80, sort: true}
                ,{field: 'city', title: '城市', width:80}
                ,{field: 'sign', title: '签名', width: 177}
                ,{field: 'experience', title: '积分', width: 80, sort: true}
                ,{field: 'score', title: '评分', width: 80, sort: true}
                ,{field: 'classify', title: '职业', width: 80}
                ,{field: 'wealth', title: '财富', width: 135, sort: true}
            ]]
        });

    });
</script>

</body>

</html>