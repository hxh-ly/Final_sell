<%--
  Created by IntelliJ IDEA.
  User: 可爱的小栩
  Date: 2020/12/24
  Time: 11:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="${pageContext.request.contextPath}/static/echarts.min.js"></script>
</head>
<body>

<div id="main" style="width: 600px;height:400px;"></div>
<script src="${pageContext.request.contextPath}/static/layui.js"></script>
<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    /*ajax 获取商品种类 X轴
    * y轴是种类卖的数量
    *
    *
    *
    *
    *
    *
    * */
    layui.use(['element', 'layer'], function () {
        let $ = layui.$;
    $.ajax({
        url:'${pageContext.request.contextPath}/statistics/getGoodsSell',
        success:(res=>{
            console.log(res)
            //渲染
            var myChart = echarts.init(document.getElementById('main'));
            // 指定图表的配置项和数据
            var option = {
                text:"货物类销售数量统计",
                color: ['#3398DB'],
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                        type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                    }
                },
                grid: {
                    left: '3%',
                    right: '4%',
                    bottom: '3%',
                    containLabel: true
                },
                xAxis: [
                    {
                        type: 'category',
                        data: res.data.xGoodsType,
                        axisTick: {
                            alignWithLabel: true
                        }
                    }
                ],
                yAxis: [
                    {
                        type: 'value'
                    }
                ],
                series: [
                    {
                        name: '直接访问',
                        type: 'bar',
                        barWidth: '60%',
                        data: res.data.yGoodsType
                    }
                ]
            };

            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
        })
    })
    })
</script>
</body>
</html>
