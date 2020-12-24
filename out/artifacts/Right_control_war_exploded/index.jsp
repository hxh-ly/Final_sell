<%--
  Created by IntelliJ IDEA.
  User: 可爱的小栩
  Date: 2020/11/28
  Time: 21:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security"  %>
<html>
  <head>
    <title>$Title$</title>
    <link rel="stylesheet" href="/static/css/layui.css">
  </head>
 <%-- <body>
  aaaaaaaa
      用户名:<security:authentication property="name"/>
  <form method="post" action="${pageContext.request.contextPath}/logout">
    <security:csrfInput/>
    <input type="submit" value="注销">
  </form>
  </body>
</html>--%>
  <body class="layui-layout-body">
  <div class="layui-layout layui-layout-admin">
    <div class="layui-header">
      <div class="layui-logo" style="font-size: 26px">销售管理系统</div>
      <ul class="layui-nav layui-layout-right">
        <li class="layui-nav-item">
          <!--传入用户名-->
          <!--                <p th:text="${userName}"></p>-->
        </li>
        <li class="layui-nav-item"><form style="display: inline-block" method="post" action="${pageContext.request.contextPath}/logout"><security:csrfInput/>
          <input type="submit" value="注销"></form></li>
      </ul>
    </div>

    <div class="layui-side layui-bg-black">
      <div class="layui-side-scroll">
        <!-- 左侧垂直导航区域-->
        <ul class="layui-nav layui-nav-tree" lay-filter="test">
          <security:authorize access="hasAnyRole('ROLE_CONTRACT')">
          <li class="layui-nav-item">
            <a href="javascript:;">管理合同信息</a>
            <dl class="layui-nav-child">
              <dd><a href="javascript:;"
                     data-id="1"
                     data-title="查看合同"
                     data-url="${pageContext.request.contextPath}/contract/toContractList" class="site-demo-active"
                     data-type="tabAddContract">查看合同</a>
              </dd>
              <dd><a href="javascript:;"
                     data-id="2"
                     data-title="添加合同"
                     data-url="${pageContext.request.contextPath}/contract/ToaddContract" class="site-demo-active"
                     data-type="tabAddContract">添加合同</a>
              </dd>
            </dl>
          </li>
          </security:authorize>
          <security:authorize access="hasAnyRole('ROLE_CLIENT')">
            <li class="layui-nav-item">
            <a href="javascript:;" >管理客户信息</a>
            <dl class="layui-nav-child">
              <dd><a href="javascript:;"data-id="50"
                     class="site-demo-active"      data-title="查看客户信息" data-type="ShowClient" data-url="${pageContext.request.contextPath}/client/toShowList">客户信息</a></dd>
            </dl>
          </li>
          </security:authorize>
          <security:authorize access="hasAnyRole('ROLE_STAFF')">
          <li class="layui-nav-item">
            <a href="javascript:;">管理销售人员信息</a>
            <dl class="layui-nav-child">
              <dd><a href="javascript:;"data-id="51"
                     class="site-demo-active"      data-title="查看销售人员信息" data-type="ShowStaff" data-url="${pageContext.request.contextPath}/staff/toShowList">销售员信息</a></dd>
            </dl>
          </li>
          </security:authorize>
          <security:authorize access="hasAnyRole('ROLE_WAREHOUSE')">
          <li class="layui-nav-item">
            <a href="javascript:;">管理商品信息</a>
            <dl class="layui-nav-child">
              <dd><a data-id="5" class="site-demo-active" data-title="管理商品信息" data-type="ShowGoods" data-url="${pageContext.request.contextPath}/goods/toShowList">管理商品信息</a></dd>
              <dd><a data-id="6" class="site-demo-active" data-title="管理发货信息" data-type="GoodsDelivery" data-url="${pageContext.request.contextPath}/delivery/deliveryList">管理发货信息</a></dd>
              <dd><a data-id="7" class="site-demo-active" data-title="管理进货信息" data-type="ShowStock" data-url="${pageContext.request.contextPath}/stock/toShowStockList">进货记录</a></dd>
              <dd><a data-id="8" class="site-demo-active" data-title="管理待进货信息" data-type="WaittoStock" data-url="${pageContext.request.contextPath}/stock/goWaitToStock">管理待进货信息</a></dd>
            </dl>
          </li>
          </security:authorize>

          <security:authorize access="hasAnyRole('ROLE_CLIENT')">
            <li class="layui-nav-item">
              <a href="javascript:;">统计信息</a>
              <dl class="layui-nav-child">
                <dd><a data-id="9" class="site-demo-active" data-title="客户统计" data-type="ShowGoods" data-url="${pageContext.request.contextPath}/goods/toShowList">客户统计</a></dd>
                <dd><a data-id="10" class="site-demo-active" data-title="销售统计" data-type="GoodsDelivery" data-url="${pageContext.request.contextPath}/delivery/deliveryList">销售统计</a></dd>
                <dd><a data-id="11" class="site-demo-active" data-title="商品种类统计" data-type="ShowStock" data-url="${pageContext.request.contextPath}/statistics/goodsType">商品种类统计</a></dd>
              </dl>
            </li>
          </security:authorize>

        </ul>
      </div>
    </div>

    <!--tab标签-->
    <div class="layui-tab" lay-filter="demo" lay-allowclose="true"
         style="margin-left: 200px;">
      <ul class="layui-tab-title"></ul>
      <div class="layui-tab-content"></div>
    </div>


  </div>
  <script src="/static/layui.js"></script>
  <script>
    //定义模块
    layui.define(function(exports){
      exports('demo',function(){
        alert('hello define!');
      })
    })


  </script>
  <script>

    layui.use(['element', 'layer', 'jquery','demo'], function () {
      var element = layui.element;
      // var layer = layui.layer;
      var $ = layui.$;

      // 配置tab实践在下面无法获取到菜单元素
      $('.site-demo-active').on('click', function () {
        var dataid = $(this);
        //这时会判断右侧.layui-tab-title属性下的有lay-id属性的li的数目，即已经打开的tab项数目
        if ($(".layui-tab-title li[lay-id]").length <= 0) {
          //如果比零小，则直接打开新的tab项
          active.tabAdd(dataid.attr("data-url"), dataid.attr("data-id"), dataid.attr("data-title"));
        } else {
          //否则判断该tab项是否以及存在
          var isData = false; //初始化一个标志，为false说明未打开该tab项 为true则说明已有
          $.each($(".layui-tab-title li[lay-id]"), function () {
            //如果点击左侧菜单栏所传入的id 在右侧tab项中的lay-id属性可以找到，则说明该tab项已经打开
            if ($(this).attr("lay-id") == dataid.attr("data-id")) {
              isData = true;
            }
          })
          if (isData == false) {
            //标志为false 新增一个tab项
            active.tabAdd(dataid.attr("data-url"), dataid.attr("data-id"), dataid.attr("data-title"));
          }
        }
        //最后不管是否新增tab，最后都转到要打开的选项页面上
        active.tabChange(dataid.attr("data-id"));
      });

      var active = {
        //在这里给active绑定几项事件，后面可通过active调用这些事件
        tabAdd: function (url, id, name) {
          //新增一个Tab项 传入三个参数，分别对应其标题，tab页面的地址，还有一个规定的id，是标签中data-id的属性值
          //关于tabAdd的方法所传入的参数可看layui的开发文档中基础方法部分
          element.tabAdd('demo', {
            title: name,
            content: '<iframe data-frameid="' + id + '" scrolling="yes" frameborder="0" src="' + url + '" style="width:100%;height:550px;"></iframe>',
            id: id //规定好的id
          })
          //FrameWH();  //计算ifram层的大小
        },
        tabChange: function (id) {
          //切换到指定Tab项
          element.tabChange('demo', id); //根据传入的id传入到指定的tab项
        },
        tabDelete: function (id) {
          element.tabDelete("demo", id);//删除
        }
      };

      function FrameWH() {
        var h = $(window).height();
        $("iframe").css("height", h + "px");
      }
    });
  </script>


  </body>
</html>