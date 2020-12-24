<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%response.setHeader("X-Frame-Options", "SAMEORIGIN");%>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/static/css/layui.css">
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
</head>
<body>

<%--这里有给表单--%>
<form modelAttribute="client" onsubmit="return false" action="##" method="post">
    <security:csrfInput/>
    <div class="layui-form layui-card-header layuiadmin-card-header-auto">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">合同ID</label>
                <div class="layui-input-inline">
                    <input type="text" name="id" id="id" placeholder="请输入" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">客户号码</label>
                <div class="layui-input-inline">
                    <input type="text" name="cphone" id="cphone" placeholder="请输入" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">销售员号码</label>
                <div class="layui-input-inline">
                    <input type="text" name="sphone" id="sphone" placeholder="请输入" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">签约日期</label>
                <div class="layui-input-inline">
                    <div class="layui-input-inline">
                        <input type="text" name="signdate" id="signdate" placeholder="请输入" autocomplete="off"
                               class="layui-input">
                    </div>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">合同状态</label>
                <div class="layui-input-inline">
                    <div class="layui-input-inline">
                        <input type="text" name="status" id="status" placeholder="请输入" autocomplete="off"
                               class="layui-input">
                    </div>
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

<%--<table class="layui-table" lay-even lay-skin="nob" >
<colgroup>
    <col width="150">
    <col width="150">
    <col width="150">
    <col width="150">
    <col width="150">
    <col width="150">
</colgroup>
<thead>
<tr>
    <th>ID</th>
    <th>客户</th>
    <th>销售人员</th>
    <th>签到日期</th>
    <th>合同状态</th>
    <th>清单详情</th>
    <th>修改</th>
</tr>
</thead>
<tbody id="test1">
<c:forEach items="${requestScope.allContract }" var="ac">

    <tr>
        <td>${ac.id}</td>
        <td>${ac.client.getPhone()}</td>
        <td>${ac.staff.getPhone()}</td>
        <td>${ac.signdate}</td>
        <td>
            <c:choose>
                <c:when test="${ac.status==0}">
                    未履行
                </c:when>
                <c:when test="${ac.status==1}">
                    正履行
                </c:when>
                <c:otherwise>
                    履行完毕
                </c:otherwise>
            </c:choose>
        </td>
        <td><input type="button" class="layui-btn layui-btn-normal btn" onclick="window.location.href='${pageContext.request.contextPath}/contract/showOrder?cid=${ac.id}'"  value="详情"/></td>
        <td><c:if test="${ac.status==0}"><input type="button" class="layui-btn layui-btn-normal btn" onclick="window.location.href='${pageContext.request.contextPath}/contract/updateContract?id=${ac.id}'"  value="修改"/></c:if> </td>
    </tr>

</c:forEach>
</tbody>
</table>--%>

<table id="contract" lay-filter="test"></table>
<script type="text/html" id="buttonTpl">

    <input type="button" class="layui-btn layui-btn-danger layui-btn layui-btn-xs" value="详情" lay-event="detail">
    {{#  if(d.status==0){ }}
    <input type="button" class="layui-btn layui-btn-danger layui-btn layui-btn-xs" value="修改" lay-event="edit">
    {{#  } }}
</script>
<script type="text/html" id="isStatus">
    {{#  if(d.status==0){ }}
    <div>未履行</div>

    {{#  } else { }}
    <div>履行完毕</div>
    {{#  } }}
</script>
<script src="${pageContext.request.contextPath}/static/layui.js"></script>
<script>
    var tb = null
    layui.use(['table', 'form'], function () {
        let table = layui.table;
        let form = layui.form;
        tb = table.render({
            elem: '#contract'
            , url: '${pageContext.request.contextPath}/contract/contractList' //数据接口
            , page: true,//开启分页
            cols: [[ //表头
                {field: 'id', title: '合同ID', width: 120, sort: true, fixed: 'left'}
                , {field: 'd.client.phone', title: '客户电话', width: 120, templet: '<div>{{d.client.phone}}</div>'},
                , {field: 'd.staff.phone', title: '销售员电话', width: 120, templet: '<div>{{d.staff.phone}}</div>'}
                , {field: 'signdate', title: '签订日期', width: 200, sort: true}
                , {
                    field: 'status', title: '合同状态', width: 200, templet:
                        function (d) {
                            if (d.status == 0) {
                                return '<div>未履行</div>'
                            }
                            if (d.status == 1) {
                                return '<div>正履行</div>'
                            }
                            if (d.status == 2) {
                                return '<div>履行完毕</div>'
                            }

                        }
                }
                , {field: "update", title: "操作", templet: "#buttonTpl", width: 200}
            ]]
        });
        table.on('tool(test)', function (obj) {
            var data = obj.data;
            if (obj.event === 'detail') {
                //跳转到查看清单详情
                window.location.href = '${pageContext.request.contextPath}/contract/showOrder?cid=' + data.id
            } else if (obj.event === 'del') {
                layer.confirm('真的删除行么', function (index) {
                    obj.del();
                    layer.close(index);
                });
            } else if (obj.event === 'edit') {
                //弹窗修改框 data.id
                let cid = data.id
                layui.use(['layer', 'jquery'], function () {
                    let layer = layui.layer;
                    let $ = layui.jquery;
                    //修改合同信息
                    layer.open({
                        id: "updateContract",
                        type: 2,
                        title: '修改',
                        content: '${pageContext.request.contextPath}/contract/PopContract?cid=' + cid,
                        btn: ["提交", "取消"],
                        yes: function (index, layero) {
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
    let a = "${requestScope.msg}"
    console.log(a)
    layui.use('layer', function () {
        var layer = layui.layer;
        if (a == 0 && a != '')
            layer.msg("勿重复生成发货单");
        if (a == 1)
            layer.msg("生成发货单成功");
    });
</script>
<script>
    function sendClick() {
        layui.use(['table', 'form'], function () {
            let table = layui.table;
            var $ = layui.$;
            var header = $("meta[name='_csrf_header']").attr("content");
            var token = $("meta[name='_csrf']").attr("content");
            let id = $.trim($('.layui-input-inline #id').val());
            let status = $.trim($('.layui-input-inline #status').val());
            let cphone = $('.layui-input-inline #cphone').val();
            let sphone = $('.layui-input-inline #sphone').val();
            let signdate = $('#signdate').val();
            /*  $.ajax(
                  {
                      url:"
            ${pageContext.request.contextPath}/contract/queryContract",
                    type:"POST",
                    data:JSON.stringify({
                        "id":parseInt(id),
                        "status":parseInt(status),
                        "cphone":cphone!=''?cphone:null,
                        "sphone":sphone!=''?sphone:null,
                        "signdate":signdate!=''?signdate:null,

                    }),
                    contentType:'application/json;charset=UTF-8',
                    dataType:"json",
                    success:(res=>{
                        console.log(res)
                        console.log(res.data)
                        tb.reload()


                    }),
                    fail:(res=>{
                        alert("错误")
                    }),
                    beforeSend : function(xhr) {
                        xhr.setRequestHeader(header, token);
                    }
                }
            )*/
            table.reload('contract', {
                url: "${requestScope.request.contextPath }/contract/queryContract",
                where: {
                    id: id != '' ? id : null,
                    status: status != '' ? status : null
                    , cphone: cphone != '' ? cphone : null
                    , sphone: sphone != '' ? sphone : null
                    , signdate: signdate
                }
                , contentType: 'application/json'
                , method: 'POST'
            });

        })

    }

    function clickMe(e) {
        layui.use(['layer', 'jquery'], function () {
            let layer = layui.layer;
            let $ = layui.jquery;
            layer.open({
                id: "addClient",
                type: 2,
                title: '添加',
                content: '${pageContext.request.contextPath}/client/PopClient?flag=1',
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
