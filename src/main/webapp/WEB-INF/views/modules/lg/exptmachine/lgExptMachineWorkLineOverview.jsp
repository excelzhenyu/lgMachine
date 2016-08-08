<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<html>
<head>
	<title>试验机器工作概览</title>
	<meta name="decorator" content="default"/> 
</head>
	<body  style="height: 100%; margin: 0">
	<div id="mapContainer" style="width: 800px;height:450px;overflow-x:hidden;overflow-y:hidden;"></div>
	<script type="text/javascript">
		var dom = document.getElementById("mapContainer");
		var myChart = echarts.init(dom);
		var app = {};
		//var json = '[[{"name":"北京","coord":[116.4551,40.2539]},{"name":"济南","coord":[117.1582,36.8701]}],[{"name":"济南","coord":[117.1582,36.8701]},{"name":"上海","coord":[121.4648,31.2891]}],[{"name":"上海","coord":[121.4648,31.2891]},{"name":"广州","coord":[113.5107,23.2196]}]]';
		//var hahaData = JSON.parse(json);
		var color = ['#a6c84c', '#ffa022', '#46bee9'];
		var option = {
		    backgroundColor: '#404a59',
		    title : {
		        text: '机器轨迹图',
		        subtext: '试验机器轨迹图',
		        left: 'center',
		        textStyle : {
		            color: '#fff'
		        }
		    },
		    tooltip : {
		    	//show: false,
		        trigger: 'item',
		        position: ['1%', '80%']

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
		    }
		};
		var deviceNo = '${deviceNo}';//'C30090';
		var lineDate = '${exptDate}';//'2016-01-02';

		$.getJSON("${ctx}/lg/exptmachine/lgExptMachineWorkOverview/dateLineData", { deviceNo: deviceNo, lineDate: lineDate}, function(data) {
			var series = [];
			if(data && data.mapLineList) {
				var linesData = data.mapLineList;
				option.geo.center = data.centerPosition;
				option.geo.zoom = data.zoomValue;
			    series.push({
			        name: data.device,
			        type: 'lines',
			        zlevel: 1,
			        effect: {
			            show: true,
			            period: 6,
			            trailLength: 0.7,
			            //color: '#fff',
			            symbolSize: 3
			        },
			        lineStyle: {
			            normal: {
			                color: color[0],
			                width: 0,
			                curveness: 0.2
			            }
			        },
			        data: linesData,
			    },
			    {
			        name: data.device,
			        type: 'lines',
			        zlevel: 2,
			        effect: {
			            show: true,
			            period: 6,
			            trailLength: 0,
			            symbol: 'triangle',//planePath, 'circle', 'rect', 'roundRect', 'triangle', 'diamond', 'pin', 'arrow'
			            symbolSize: 3
			        },
			        lineStyle: {
			            normal: {
			                color: color[0],
			                width: 1,
			                opacity: 0.4,
			                curveness: 0.2
			            }
			        },
			        data: linesData,// convertData(item[1])
			    },
			    {
			    	name: data.device,
			        type: 'scatter',
			        coordinateSystem: 'geo',
			        zlevel: 3,
			        rippleEffect: {
			            brushType: 'stroke'
			        },
			        label: {
			            normal: {
			                show: false,
			                position: 'right',
			                formatter: '{b}'
			            }
			        },
			        symbolSize: 5,
			        itemStyle: {
			            normal: {
			                color: color[0]
			            }
			        },
			        data: linesData.map(function (item, i) {
			            return {
			            	name: item[0].name,
				            value: item[0].coord
			            }
			        })
			    });
			}
			option.series = series;
			myChart.showLoading();
		    setTimeout(refresh, 500);
		});
		function refresh(){
			if (myChart && myChart.dispose) {
		        myChart.dispose();
		    }
			myChart = echarts.init(dom);
			myChart.setOption(option);
		}
		myChart.setOption(option);
		window.onresize = function(){
			myChart.resize();
		}

       </script>
   </body>
</html>