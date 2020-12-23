<%--
  Created by IntelliJ IDEA.
  User: 可爱的小栩
  Date: 2020/12/23
  Time: 15:37
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
<table id="waitToStock" lay-filter="test"></table>
<script type="text/html" id="Stock">
    <input type="button" class="layui-btn layui-btn-danger layui-btn layui-btn-xs" value="进货" lay-event="stock" >
</script>
<script src="${pageContext.request.contextPath}/static/layui.js"></script>
<script>
    layui.use('table', function(){
        var table = layui.table;

        //第一个实例
        let tb=table.render({
            elem: '#waitToStock'
            ,url: '${pageContext.request.contextPath}/stock/showWaitToStock' //数据接口
            ,page: true //开启分页
            ,width:500
            ,cols: [[ //表头
                {field: 'id', title: 'ID', width:80, sort: true, fixed: 'left'}
                ,{field: 'goodsName', title: '货名', width:80}
                ,{field: 'num', title: '数量', width:80, sort: true}
                ,{field: 'do',title:'操作',templet:'#Stock'}
            ]]
        });
        table.on('tool(test)', function(obj){
            var data = obj.data;
           if(obj.event === 'stock'){
                //弹窗修改框 data.id
                //let cid=data.id
               console.log(data.id)
                layui.use(['layer','jquery'], function(){
                    let layer = layui.layer;
                    let $=layui.jquery;
                    layer.open({
                        id:"toStock",
                        type:2,
                        title:'进货',
                        content:'${pageContext.request.contextPath}/stock/ToPopStock?id='+data.id,
                        btn:["提交","取消"],
                        yes:function (index,layero){
                            var body = layer.getChildFrame('body', index);
                            var f = body.find(".layui-form");
                            f.submit();
                            layer.close(index);
                            setTimeout(function (){
                                tb.reload()},300);
                        },btn2: function (index,layero){
                            layer.close(index);
                        }

                    })
                });
            }
        });

    });

</script>
</body>
</html>