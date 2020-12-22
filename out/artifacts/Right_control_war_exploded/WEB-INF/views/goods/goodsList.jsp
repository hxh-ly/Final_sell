<%--
  Created by IntelliJ IDEA.
  User: 可爱的小栩
  Date: 2020/12/19
  Time: 23:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%response.setHeader("X-Frame-Options", "SAMEORIGIN");%>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/static/css/layui.css">
</head>
<body>
<input type="button" class="layui-btn layui-btn-normal btn" onclick="clickMe(this)" value="增加"/>

<form modelAttribute="goods" onsubmit="return false" action="##" method="post">
    <security:csrfInput/>
    <div class="layui-form layui-card-header layuiadmin-card-header-auto">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">货品ID</label>
                <div class="layui-input-inline">
                    <input type="text" name="id" id="id" placeholder="请输入" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">货品名</label>
                <div class="layui-input-inline">
                    <input type="text" name="goodsName" id="goodsName" placeholder="请输入" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">价格</label>
                <div class="layui-input-inline">
                    <input type="text" name="price" id="price" placeholder="请输入" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">库存</label>
                <div class="layui-input-inline">
                    <input type="text" name="amount" id="amount" placeholder="请输入" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">类别</label>
                <div class="layui-input-inline">
                    <input type="text" name="category" id="category" placeholder="请输入" autocomplete="off" class="layui-input">
                </div>
            </div>

            <div class="layui-inline">
                <input type="button" onclick="sendClick()" class="layui-btn layui-btn-normal btn" value="查询"/>
            </div>

        </div>
    </div>
</form>
<br>
<br>
<br>
<div class="layui-fluid">
    <div class="layui-card">


        <div class="layui-card-body">
            <div style="padding-bottom: 10px;">

            </div>
            <table id="goods" lay-filter="test"></table>
            <script type="text/html" id="buttonTpl">
                <input type="button" class="layui-btn layui-btn-danger layui-btn layui-btn-xs" value="修改"
                       lay-event="edit">
                <input type="button" class="layui-btn layui-btn-success layui-btn layui-btn-xs" value="下架"
                       lay-event="del">
            </script>
        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/static/layui.js"></script>
<script>

    var tb = null
    //初始化加载
    layui.use(['table', 'form'], function () {
        let table = layui.table;
        let form = layui.form;
        $=layui.$;
        tb = table.render({
            elem: '#goods'
            , url: '${pageContext.request.contextPath}/goods/showGoods' //数据接口
            , page: true //开启分页

            , cols: [[ //表头
                {field: 'id', title: 'ID', width: 80, sort: true, fixed: 'left'}
                , {field: 'goodsName', title: '商品名', width: 120}
                , {field: 'price', title: '价格', width: 120}
                , {field: "amount", title: "库存",  width: 120}
                , {field: "category", title: "类别",  width: 120}
                , {field: "do", title: "操作", templet: "#buttonTpl", width: 240}
            ]]
        });
        table.on('tool(test)', function (obj) {
            var data = obj.data;
            if (obj.event === 'detail') {
                layer.msg('ID：' + data.id + ' 的查看操作');
            } else if (obj.event === 'del') {
                layer.confirm('真的删除么', function (index) {
                    //ajax
                    $.ajax({
                        url:'${pageContext.request.contextPath}/goods/deleteGoods?id='+data.id,
                        success:res=>{
                            tb.reload()
                        }
                    })
                    layer.close(index);
                });
            } else if (obj.event === 'edit') {
                //弹窗修改框 data.id
                let cid = data.id
                layui.use(['layer', 'jquery'], function () {
                    let layer = layui.layer;
                    let $ = layui.jquery;
                    layer.open({
                        id: "updateGoods",
                        type: 2,
                        title: '修改',
                        content: '${pageContext.request.contextPath}/goods/PopGoods?flag=0&id=' + cid,
                        btn: ["提交", "取消"],
                        yes: function (index, layero) {
                            console.log("aaab")
                            var body = layer.getChildFrame('body', index);
                            var f = body.find(".layui-form");
                            f.submit();
                            layer.close(index);
                            setTimeout(function () {
                                tb.reload()
                            }, 300);
                        }, btn2: function (index, layero) {
                            layer.close(index);
                        }

                    })
                });
            }
        });
    });
</script>
<script>
    /* function  clickToUpdate(){
         layui.use(['table','form'], function(){
             let table=layui.table
      let checkStatus = table.checkStatus('client')
            , checkData = checkStatus.data;
             console.log(checkData)
     })
     }*/
    //查询重载
    function sendClick() {
        layui.use(['table', 'form'], function () {
            let table = layui.table;
            var $ = layui.$;
            let id = $.trim($('.layui-input-inline #id').val());
            let goodsName = $.trim($('.layui-input-inline #goodsName').val());
            let price = $.trim($('.layui-input-inline #price').val());
            let amount = $.trim($('.layui-input-inline #amount').val());
            let category = $('.layui-input-inline #category').val();

            table.reload('goods', {
                url: "${requestScope.request.contextPath }/goods/SearchGoods",
                where: {
                    id: id,
                    goodsName: goodsName,
                    price:price,
                    amount:amount,
                    category:category

                }
            });
            /* table.render({
                 elem: '#client',
                 url: '
            ${requestScope.request.contextPath }/client/SearchClient',
            contentType:'application/json',
            where:{
                "client":{"id":"1","name":" ","phone":"131","location":" "}
            }
        })*/
        })
    }

    //添加pop出
    function clickMe(e) {
        layui.use(['layer', 'jquery'], function () {
            let layer = layui.layer;
            let $ = layui.jquery;
            layer.open({
                id: "addGoods",
                type: 2,
                title: '添加',
                content: '${pageContext.request.contextPath}/goods/PopGoods?flag=1',
                btn: ["提交", "取消"],
                yes: function (index, layero) {
                    console.log("aaa")
                    var body = layer.getChildFrame('body', index);
                    var f = body.find(".layui-form");
                    f.submit();
                    layer.close(index);
                    setTimeout(function () {
                        tb.reload()
                    }, 300);
                }, btn2: function (index, layero) {
                    layer.close(index);
                }

            })
        });
    }
</script>
</body>
</html>
