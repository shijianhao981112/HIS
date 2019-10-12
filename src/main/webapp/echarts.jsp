<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
    <div id="main" style="width: 600px;height:400px;"></div>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/dist/Js/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/dist/Js/echarts.min.js"></script>
<script type="text/javascript">
	// 页面加载元后执行的动作
	$(document).ready (function(){
		// ajax发出请求,获得数据,利用echarts做图表
		$.ajax({
			url:"${pageContext.request.contextPath}/showEcharts2.do",
			type:"GET",
			success:function(data){
				// 基于准备好的dom，初始化echarts实例
		        var myChart = echarts.init(document.getElementById('main'));

		        // 指定图表的配置项和数据
		        var  option1 = {
		            title: {
		                text: 'ECharts 入门示例'
		            },
		            tooltip: {},
		            legend: {
		                data:['销量']
		            },
		            xAxis: {
		                data: ["星期一","星期二","星期三","星期四","星期五","星期六","星期日"]
		            },
		            yAxis: {},
		            series: [{
		                name: '销量',
		                type: 'bar',
		                data: data
		            }]
		        }; 
				var option2 = {
					    title: {
					        text: '折线图堆叠'
					    },
					    tooltip: {
					        trigger: 'axis'
					    },
					    legend: {
					        data:['骨科','妇科','急诊科','外科']
					    },
					    grid: {
					        left: '3%',
					        right: '4%',
					        bottom: '3%',
					        containLabel: true
					    },
					    toolbox: {
					        feature: {
					            saveAsImage: {}
					        }
					    },
					    xAxis: {
					        type: 'category',
					        boundaryGap: false,
					        data: ['周一','周二','周三','周四','周五','周六','周日']
					    },
					    yAxis: {
					        type: 'value'
					    },
					    series: [
					        {
					            name:'骨科',
					            type:'line',
					           // stack: '总量',
					            data:data[0]
					        },
					        {
					            name:'妇科',
					            type:'line',
					            //stack: '总量',
					            data:data[1]
					        },
					        {
					            name:'急诊科',
					            type:'line',
					           // stack: '总量',
					            data:data[2]
					        },
					        {
					            name:'外科',
					            type:'line',
					          //  stack: '总量',
					            data:data[3]
					       
					        }
					    ]
					};

		        
		        // 使用刚指定的配置项和数据显示图表。
		        myChart.setOption(option2);
				
			}
		});
		
	});

</script>


</html>