<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<html>
<head>
	<title>试验机器工作概览</title>
	<meta name="decorator" content="default"/> 
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&amp;ak=ZUONbpqGBsYGXNIYHicvbAbM"></script>
	<script src="${ctxStatic}/echarts/extension/bmap.js"></script>
	<script src="${ctxStatic}/echarts/china.js" type="text/javascript"></script>
	
</head>
	<body  style="height:100%; margin: 0">
	<div id="mapContainer2" style="position:absolute;top:0;left:0;bottom:0;width:40%;height:100%;overflow-x:hidden;overflow-y:hidden;"></div>
	<div id="mapContainer1" style="position:absolute;top:0;left:40%;right:0;bottom:0;width:60%;height:100%;overflow-x:hidden;overflow-y:hidden;"></div>
	
	<script type="text/javascript">
		var main = $('#mapContainer2');
		var myChart2 = echarts.init(main[0]);
		var dom = document.getElementById("mapContainer1");
		var myChart = echarts.init(dom);
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
		$.getJSON("${ctx}/lg/exptmachine/lgExptMachineJob/getLineData", {deviceNo:'C2000B',lineDate:'2016-02-19'}, function(data) {
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
			        data: linesData,
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
			    var seriesDate = linesData;
			    var series2 =  [{
					type: 'lines',
					coordinateSystem: 'bmap',
					data: linesData,
					polyline: true,
					lineStyle: {
						normal: {
							color: 'purple',
							opacity: 0.6,
							width: 1
						}
					}
				}];
			    if(data.seriesType == "scatter") {
			    	series2 =  [{
						type: 'effectScatter',
						coordinateSystem: 'bmap',
						data: [{
					        "value": data.centerPosition
					    }],
						polyline: true,
						lineStyle: {
							normal: {
								color: 'purple',
								opacity: 0.6,
								width: 1
							}
						}
					}];
			    	
			    }
			    var option2 = {
						bmap: {
							center: data.centerPosition,
							zoom: 14,
							roam: true,
							mapStyle: {
								styleJson: [{
									'featureType': 'water',
									'elementType': 'all',
									'stylers': {
										'color': '#d1d1d1'
									}
								}, {
									'featureType': 'land',
									'elementType': 'all',
									'stylers': {
										'color': '#f3f3f3'
									}
								}, {
									'featureType': 'railway',
									'elementType': 'all',
									'stylers': {
										'visibility': 'off'
									}
								}, {
									'featureType': 'highway',
									'elementType': 'all',
									'stylers': {
										'color': '#fdfdfd'
									}
								}, {
									'featureType': 'highway',
									'elementType': 'labels',
									'stylers': {
										'visibility': 'off'
									}
								}, {
									'featureType': 'arterial',
									'elementType': 'geometry',
									'stylers': {
										'color': '#fefefe'
									}
								}, {
									'featureType': 'arterial',
									'elementType': 'geometry.fill',
									'stylers': {
										'color': '#fefefe'
									}
								}, {
									'featureType': 'poi',
									'elementType': 'all',
									'stylers': {
										'visibility': 'off'
									}
								}, {
									'featureType': 'green',
									'elementType': 'all',
									'stylers': {
										'visibility': 'off'
									}
								}, {
									'featureType': 'subway',
									'elementType': 'all',
									'stylers': {
										'visibility': 'off'
									}
								}, {
									'featureType': 'manmade',
									'elementType': 'all',
									'stylers': {
										'color': '#d1d1d1'
									}
								}, {
									'featureType': 'local',
									'elementType': 'all',
									'stylers': {
										'color': '#d1d1d1'
									}
								}, {
									'featureType': 'arterial',
									'elementType': 'labels',
									'stylers': {
										'visibility': 'off'
									}
								}, {
									'featureType': 'boundary',
									'elementType': 'all',
									'stylers': {
										'color': '#fefefe'
									}
								}, {
									'featureType': 'building',
									'elementType': 'all',
									'stylers': {
										'color': '#d1d1d1'
									}
								}, {
									'featureType': 'label',
									'elementType': 'labels.text.fill',
									'stylers': {
										'color': '#999999'
									}
								}]
							}
						}
					};
			    option2.series = series2;
			    myChart2.setOption(option2);
			}
			option.series = series;
			myChart = echarts.init(dom);
			myChart.setOption(option);
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
			myChart2.resize();
		}
	</script>
   </body>
</html>