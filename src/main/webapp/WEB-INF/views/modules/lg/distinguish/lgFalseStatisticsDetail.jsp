<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>虚漏报方案配置详细管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			var height = document.body.scrollHeight;
			$("#chart1").height(height*0.5);
			$("#chart2").height(height*0.5);
			$("#chart3").height(height*0.5);
			$("#chart4").height(height*0.5);
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
	<script type="text/javascript">
	$(function(){
		 var myChart = echarts.init(document.getElementById('chart4'));
			var data = [
			            {name: '', value: 279}
			       ];
			       var geoCoordMap = {
			           '':[118.437627,34.991502]
			       };

			       var convertData = function (data) {
			           var res = [];
			           for (var i = 0; i < data.length; i++) {
			               var geoCoord = geoCoordMap[data[i].name];
			               if (geoCoord) {
			                   res.push({
			                       name: data[i].name,
			                       value: geoCoord.concat(data[i].value)
			                   });
			               }
			           }
			           return res;
			       };

			       option = {
			           backgroundColor: '#404a59',
			           title: {
			               text: '轨迹追踪',
			               left: 'left',
			               textStyle: {
			                   color: '#fff'
			               }
			           },
			           tooltip : {
			               trigger: 'item'
			           },
			           legend: {
			               orient: 'vertical',
			               y: 'bottom',
			               x:'right',
			               data:['pm2.5'],
			               textStyle: {
			                   color: '#fff'
			               }
			           },
			           geo: {
			               map: 'china',
			               label: {
			                   emphasis: {
			                       show: false
			                   }
			               },
			               roam: true,
			               itemStyle: {
			                   normal: {
			                       areaColor: '#323c48',
			                       borderColor: '#111'
			                   },
			                   emphasis: {
			                       areaColor: '#2a333d'
			                   }
			               }
			           },
			           series : [
			               {
			                   name: '2016/6/12',
			                   type: 'effectScatter',
			                   coordinateSystem: 'geo',
			                   data: convertData(data),
			                   symbolSize: 10,
			                   showEffectOn: 'render',
			                   rippleEffect: {
			                       brushType: 'stroke'
			                   },
			                   hoverAnimation: true,
			                   label: {
			                       normal: {
			                           formatter: '{b}',
			                           position: 'right',
			                           show: true
			                       }
			                   },
			                   itemStyle: {
			                       normal: {
			                           color: '#f4e925',
			                           shadowBlur: 10,
			                           shadowColor: '#333'
			                       }
			                   },
			                   zlevel: 1
			               }
			           ]
			       };
			       myChart.setOption(option);

	});
</script>
<script type="text/javascript">
	$(function(){
		var option1 = {
    tooltip: {
        trigger: 'axis',
        position: function (pt) {
            return [pt[0], '10%'];
        }
    },
    title: {
        left: 'left',
        text: '油耗分析',
    },
    legend: {
        top: 'bottom',
        data:['意向']
    },
    toolbox: {
        feature: {
            dataZoom: {
                yAxisIndex: 'none'
            },
            restore: {},
            saveAsImage: {}
        }
    },
    xAxis: {
        type: 'category',
        boundaryGap: false,
        data: []
    },
    yAxis: {
        type: 'value',
        boundaryGap: [0, '100%']
    },
    dataZoom: [{
        type: 'slider',
        start: 0,
        end: 10
    }, {
        start: 0,
        end: 10,
        handleIcon: 'M10.7,11.9v-1.3H9.3v1.3c-4.9,0.3-8.8,4.4-8.8,9.4c0,5,3.9,9.1,8.8,9.4v1.3h1.3v-1.3c4.9-0.3,8.8-4.4,8.8-9.4C19.5,16.3,15.6,12.2,10.7,11.9z M13.3,24.4H6.7V23h6.6V24.4z M13.3,19.6H6.7v-1.4h6.6V19.6z',
        handleSize: '80%',
        handleStyle: {
            color: '#fff',
            shadowBlur: 3,
            shadowColor: 'rgba(0, 0, 0, 0.6)',
            shadowOffsetX: 2,
            shadowOffsetY: 2
        }
    }],
    series: [
        {
            name:'油耗',
            type:'line',
            smooth:true,
            symbol: 'none',
            sampling: 'average',
            itemStyle: {
                normal: {
                    color: 'rgb(255, 70, 131)'
                }
            },
            areaStyle: {
                normal: {
                    color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                        offset: 0,
                        color: 'rgb(255, 158, 68)'
                    }, {
                        offset: 1,
                        color: 'rgb(255, 70, 131)'
                    }])
                }
            },
            data: []
        }
    ]
	};
	var myChart3 = echarts.init(document.getElementById('chart3'));
	var deviceNo = "${deviceNo}";
	$.getJSON("${ctx}/lg/lgMissAndFalseStatistics/oilSumChart",{deviceNo:deviceNo},function(data){

		if(data){
			var series = [];
			var xAxis = [];
			series.push({
				name:'油耗',
				type:'line',
				smooth:true,
				symbol: 'none',
				sampling: 'average',
				itemStyle: {
						normal: {
								color: 'rgb(255, 70, 131)'
						}
				},
				areaStyle: {
						normal: {
								color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
										offset: 0,
										color: 'rgb(255, 158, 68)'
								}, {
										offset: 1,
										color: 'rgb(255, 70, 131)'
								}])
						}
				},
				data: data.oilSumData
			});
			xAxis.push({
				type: 'category',
        boundaryGap: false,
        data: data.oilSumDate
			});
			option1.series=series;
			option1.xAxis=xAxis;
			myChart3.setOption(option1);
		}

	});
	});
</script>
</head>
<body>
	<div class=".container-fluid">
		<div class="row-fluid">
			<div id="chart1" class="span6"><p style="color: #003D79;font-size: large;">机器状态摘要</p>
			<br>
			<br>
			<ul style="font-size: medium;">
			<li>机器编号：<a href="${ctx}/lg/lgMachineEvent/list?id=${machineId}">${deviceNo}</a></li>
			<c:forEach items="${detail.eventList}" var="d">
				<li>${d}</li>
			</c:forEach>
			<li>总运行时间：${detail.runDurationTotal}小时</li>
			<li>开关机次数：${detail.runOffTotal}次</li>
			<li>最长开机时间：${detail.runDurationMax}小时</li>
			<li>最高发动机转速：${detail.rotationlSpeedMax}RPM</li>
			<li>最高行驶速度：40km/h</li>
			</ul>
			</div>
			<div id="chart2" class="span6">

			</div>
		</div>
		<div class="row-fluid">
			<div id="chart3" class="span6"></div>
			<div id="chart4" class="span6"></div>
		</div>
	</div>
</body>
</html>
