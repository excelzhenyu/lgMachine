<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>机器开工热度分布</title>
<meta name="decorator" content="default" />
<script src="${ctxStatic}/echarts/theme/vintage.js"></script>
<link rel="stylesheet" href="${ctxStatic}/custom-css/drawLayout.css">
</head>
<body>
	<div class="main-container">
		<div class="left-container">
		<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12">
			<form:form id="drawForm" modelAttribute="lgMachineDistributionEntity" action="${ctx}/lg/distribution/lgMachineDistribution/getMapData" method="post" class="form-search .form-horizontal">
					<form:hidden path="location"/>
					<div class="control-group">
						<label class="control-label">主机类别：</label>
						<div class="controls">
							<form:select path="machineType" class="span12">
								<form:options items="${fns:getDictList('machineType')}" itemLabel="label" itemValue="value" htmlEscape="false" />
							</form:select>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">主机型号：</label>
						<div class="controls">
							<form:select  path="productType" class="span12">
								<form:option value="" label="全部"/>
								<!--<form:options items="${fns:getProductTypeList('')}" htmlEscape="false" />-->
							</form:select>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">订货号：</label>
						<div class="controls">
							<form:select path="orderNumber" class="span12">
								<form:option value="" label="全部"/>
								<!--<form:options items="${fns:getOrderNumberList('')}" itemLabel="orderNumber" itemValue="orderNumber" htmlEscape="false" />-->
							</form:select>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">热度类型：</label>
						<div class="controls">
							<form:select path="densityType" class="span12">
								<form:options items="${fns:getDictList('densityType')}" itemLabel="label" itemValue="value" htmlEscape="false" />
							</form:select>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" >开工月份：</label>
						<div class="controls">
							<input id="runMonth" name="runMonth" type="text" readonly="readonly" maxlength="20" class="Wdate span12"
								value="<fmt:formatDate value="${lgMachineDistributionEntity.runMonth}" pattern="yyyy-MM"/>"
								onclick="WdatePicker({dateFmt:'yyyy-MM',isShowClear:false});"/>
						</div>
					</div>
					</br>
					<div class="control-group">
						<div class="controls">
							<input id="btnSubmit" class="btn btn-primary btn-block" type="submit" value="开工热度分布"/>
						</div>
					</div>
				</form:form>
				</div>
			</div>
			</div>
		</div>
		<!-- <div class="middle-container"> </div> -->
		<div class="right-container">
			<div id="chart-panel" class="right-panel"></div>
		</div>
	</div>

	<script type="text/javascript">
		var themName = [
			'dark',
			'infographic',
			'macarons',
			'roma',
			'shine',
			'vintage'
		];
		function initChart() {
			 return echarts.init(rightDom, themName[5]);
		}
		var mapCity = {
				'安徽':'anhui',
				'澳门':'aomen',
				'北京':'beijing',
				'中国':'china',
				'重庆':'chongqing',
				'福建':'fujian',
				'甘肃':'gansu',
				'广东':'guangdong',
				'广西':'guangxi',
				'贵州':'guizhou',
				'海南':'hainan',
				'河北':'hebei',
				'黑龙江':'heilongjiang',
				'河南':'henan',
				'湖北':'hubei',
				'湖南':'hunan',
				'江苏':'jiangsu',
				'江西':'jiangxi',
				'吉林':'jilin',
				'辽宁':'liaoning',
				'内蒙古':'neimenggu',
				'宁夏':'ningxia',
				'青海':'qinghai',
				'山东':'shandong',
				'上海':'shanghai',
				'山西':'shanxi',
				'陕西':'shanxi1',
				'四川':'sichuan',
				'台湾':'taiwan',
				'天津':'tianjin',
				'香港':'xianggang',
				'新疆':'xinjiang',
				'西藏':'xizang',
				'云南':'yunnan',
				'浙江':'zhejiang'
		};
		var rightDom;
		var chinaChart;
		var cityName;
		var cityEnName;
		var initFlag = true;
		var isBar = false;
		//初始option数据
		var chinaOption = {
			    title: {
			        text: '机器开工热度分布',
			        subtext: '开工热度分布',
			        left: 'center'
			    },
			    tooltip: {
			        trigger: 'item'
			    },
			    visualMap: {
			    	type: 'continuous',
			        min: 0,
			        max: 5000,
			        left: 'left',
			        top: 'bottom',
			        calculable: true,
			        //seriesIndex: [0],
	           		//color: ['orangered','yellow','lightskyblue'],
		        	text: ['高','低']
			    },
			    toolbox : {
		            itemSize: 25,
		            itemGap: 30,
		            right: '5%',
			        feature: {
			            myBar: {
			                show: false,
			                title: '最近13个月机器数量柱状图',
			                icon: 'image://${ctxStatic}/images/bar_chart.png',
			                onclick: function() {createProvinceBar();}
			            },
			            myMap: {
			                show: false,
			                title: '切换为省视图',
			                icon: 'image://${ctxStatic}/images/location_chart.png',
			                onclick: function() {drawProvinceMap();}
			            },
			            myTableBar: {
			                show: false,
			                title: '省内各地区柱状图',
			                icon: 'image://${ctxStatic}/images/bar2_chart.png',
			                onclick: function() {createTableBar();}
			            },
			            myChinaMap: {
			                show: false,
			                title: '切换为全国视图',
			                icon: 'image://${ctxStatic}/images/home_chart.png',
			                onclick: function() {drawChinaMap();}
			            }
			        }
			    },
			    series: [
			        {
			            name: '',
			            type: 'map',
			            map: 'china',
			            roam: true,
			            showLegendSymbol:false,
			            label: {
			                normal: {
			                    show: true
			                },
			                emphasis: {
			                    show: true
			                }
			            },
			            data:[]
			        }
			    ]
			};
		
		  $(document).ready(function() {
			  
			$('#machineType').change(function() {
				changeProductTypeByMachineType($(this).find('option:selected').val());
			});
    
			$('#productType').change(function() {
		      changeOrderNumberByProductType($(this).find('option:selected').val());
		    });
			changeProductTypeByMachineType($(this).find('option:selected').val());
			changeOrderNumberByProductType($(this).find('option:selected').val());

			$("#drawForm").on('submit', function() {
				$("#location").val('');

				$(this).ajaxSubmit({
		            dataType: "json",           
		            beforeSubmit: function(arr,$form,options){
		            },
		            success: function(data,status,xhr,$form){
		            	if(data) {
		            		//console.log(JSON.stringify(data));
		            		
			            	chinaOption.series[0].data = data.dataList;
			            	chinaOption.series[0].name = data.seriesName;
			            	chinaOption.visualMap.max = data.maxValue == 0?5000:data.maxValue;
			            	chinaOption.visualMap.seriesIndex = [ 0 ];
							chinaOption.series[0].label.normal.show = true;
			            	if(!initFlag) {
			            		var sr = [];
			            		sr.push(chinaOption.series[0]);
			            		chinaOption.series = sr;
			            		if(isBar) {
			            			delete chinaOption.legend;
									delete chinaOption.grid;
			            			delete chinaOption.xAxis;
									delete chinaOption.yAxis;
			            		}
				            	delete chinaOption.series[0].left; 
								delete chinaOption.series[0].top; 
								delete chinaOption.series[0].width;
								
								chinaOption.toolbox.feature.myBar.show = false;
								chinaOption.toolbox.feature.myTableBar.show = false;
								chinaOption.toolbox.feature.myMap.show = false;
								chinaOption.toolbox.feature.myChinaMap.show = false;
			            	} else {
				            	initFlag = false;
			            	}
							chinaChart = initChart();
							chinaChart.setOption(chinaOption, true);
							onclickEvent();
		            	}
		            },
		            error: function(xhr, status, error, $form){
						showTip('数据加载失败！', 'error',1500,0);
		            },
		            complete: function(xhr, status, $form){
		            }
		        });
			    return false;  //require
			});
		});
		
		window.onresize = function(){
			if(chinaChart) chinaChart.resize();
		}
		
		$.get('${ctxStatic}/echarts/mapJson/china.json', function (chinaJson) {
			echarts.registerMap('china', chinaJson);
			rightDom = document.getElementById('chart-panel');
			chinaChart = initChart();
		    chinaChart.setOption(chinaOption, true);
		    onclickEvent();
		});
		
		function onclickEvent() {
			chinaChart.on('click', function(params) {
					if(params.seriesIndex == 0) {
						cityName = params.name;
						cityEnName = mapCity[cityName];
						if (cityEnName) {
							$("#location").val(cityName);
							drawProvinceMap();
						}
					}
				});
			}
		function drawProvinceMap() {

			$.getJSON('${ctx}/lg/distribution/lgMachineDistribution/getMapData', {
				machineType : $('#machineType').val(),
				productType : $('#productType').val(),
				orderNumber : $('#orderNumber').val(),
				densityType : $('#densityType').val(),
				runMonth : $("#runMonth").val(),
				location : $("#location").val()
			}, function(data) {
				$.get('${ctxStatic}/echarts/mapJson/' + cityEnName + '.json', function(chinaJson) {
					echarts.registerMap(cityEnName, chinaJson);
					//点击省地图后下钻到省视图，国家地图放置在左上角
					chinaOption.series[0].left = 'left';
					chinaOption.series[0].top = 'top';
					chinaOption.series[0].width = '30%';
					chinaOption.series[0].label.normal.show = false;
					createProvinceMap(data);
					onclickEvent();
				});
			});
		}
		function createProvinceMap(data) {
			var provinceSeries = {
				type : 'map',
				roam : true,
				left : 'right',
				top : 'center',
				showLegendSymbol : false,
				label : {
					normal : {
						show : true
					},
					emphasis : {
						show : true
					}
				},
				data: []
			};
			/*while (chinaOption.series.length > 1) {
				chinaOption.series.pop();
			}*/
			var sr = [];
    		sr.push(chinaOption.series[0]);
    		chinaOption.series = sr;
    		
			provinceSeries.map = cityEnName;
			provinceSeries.data = data.dataList;
			provinceSeries.name = data.seriesName;
			chinaOption.series.push(provinceSeries);

			chinaOption.visualMap.seriesIndex = [ 0, 1 ];
			if(isBar) {
				delete chinaOption.xAxis;
				delete chinaOption.yAxis;
				delete chinaOption.grid;
				isBar = false;
			} 
			chinaOption.toolbox.feature.myBar.show = true;
			chinaOption.toolbox.feature.myMap.show = false;
			chinaOption.toolbox.feature.myTableBar.show = true;
			chinaOption.toolbox.feature.myChinaMap.show = true;

			chinaChart = initChart();
			chinaChart.setOption(chinaOption, true);
		}
		function createProvinceBar() {
			$.getJSON('${ctx}/lg/distribution/lgMachineDistribution/getMapBarData', {
				machineType : $('#machineType').val(),
				productType : $('#productType').val(),
				orderNumber : $('#orderNumber').val(),
				densityType : $('#densityType').val(),
				runMonth : $("#runMonth").val(),
				location : $("#location").val()
			}, function(data) {
				isBar = true;
				
				var sr = [];
        		sr.push(chinaOption.series[0]);
        		chinaOption.series = sr;
        		/*
				while (chinaOption.series.length > 1) {
					chinaOption.series.pop();
				}*/
				var barSeries = {
					name : '最近24个月机器数量',
					type : 'bar',
					//data :  [320, 332, 301, 334, 390, 330, 320, 320, 332, 301, 334, 390, 330, 320,280],
					data: data.seriesData,
					itemStyle : {
						normal : {
							color: function(params) {
								var colorList = [
									'#61a0a8', '#d48265'
								];
								return colorList[params.dataIndex%2]
							}
						}
					}, 
					barMinHeight:'15',
	                barMaxWidth:'50',
					label : {
						normal: {
	                        show : true,
	                        position: 'top'
						}
                    }
				};
				var barGrid =  {
		                left: '10%',
		                right: '5%',
		                bottom: '10%',
		                top: '40%',
		                containLabel: true
				};
			
				var barxAxis = [ {
					type : 'category',
					name: '月份',
					min: 'dataMin',
					axisLabel: {
						interval: 0,
			            rotate: 30
			        },
					//data :  ['2016-04','2015-05','2015-06','2015-07','2015-08','2015-09','2015-10','2015-11','2015-12','2016-01','2016-02','2016-03','2016-04','2016-05','2016-06']
					data : data.xAxisData
				}];
				var baryAxis = [{
					name: '最近24个月机器数量',
					type : 'value'
				} ];
				chinaOption.series.push(barSeries);
				chinaOption.grid = barGrid;
				chinaOption.xAxis = barxAxis;
				chinaOption.yAxis = baryAxis;
				chinaOption.visualMap.seriesIndex = [ 0 ];
				chinaOption.toolbox.feature.myBar.show = false;
				chinaOption.toolbox.feature.myMap.show = true;
				//chinaOption.toolbox.feature.myTableBar.show = true;
				//chinaOption.toolbox.feature.myChinaMap.show = true;
				chinaChart = initChart();
				chinaChart.setOption(chinaOption, true);
				onclickEvent();
			});
		}

		function drawChinaMap() {
			$("#btnSubmit").submit();
		}
		function createTableBar() {
			$.getJSON('${ctx}/lg/distribution/lgMachineDistribution/getMapCompareBarData', {
				machineType : $('#machineType').val(),
				productType : $('#productType').val(),
				orderNumber : $('#orderNumber').val(),
				densityType : $('#densityType').val(),
				runMonth : $("#runMonth").val(),
				location : $("#location").val()
			}, function(data) {
				isBar = true;
				var sr = [];
        		sr.push(chinaOption.series[0]);
        		chinaOption.series = sr;
			  
				var tableBarLegend = {
					left: '10%',
					bottom: '5%',
					orient: 'vertical',
					data : data.barLegendList//[ '主机数量', '省内平均时长', '地区平均时长' ]
				};
				var tableBarGrid = {
					left : '40%',
					right : '5%',
					bottom : '5%',
					top:'15%',
					containLabel : true
				};
				var tableBarxAxis = [{
					type : 'value'
				}];
				var tableBaryAxis = [{
					type : 'category',
					data : data.xAxisData
				}];
				var tableBarSeries = [ {
					name : data.barLegendList[0],
					type : 'bar',
					stack : '总量',
					barMinHeight:'20',
					barMaxWidth:'50',
					label : {
						normal : {
							show : true,
							position : 'insideRight'
						}
					},
					data : data.machineSriesData
				}, {
					name : data.barLegendList[1],
					type : 'bar',
					stack : '总量',
					barMinHeight:'20',
					barMaxWidth:'50',
					label : {
						normal : {
							show : true,
							position : 'insideRight'
						}
					},
					data : data.provinceSeriesData
				}, {
					name : data.barLegendList[2],
					type : 'bar',
					stack : '总量',
					barMinHeight:'20',
					barMaxWidth:'50',
					label : {
						normal : {
							show : true,
							position : 'insideRight'
						}
					},
					data : data.citySeriesData
				} ];
				chinaOption.legend = tableBarLegend;
				chinaOption.grid = tableBarGrid;
				chinaOption.xAxis = tableBarxAxis;
				chinaOption.yAxis = tableBaryAxis;
				tableBarSeries.forEach(function(item, index) {
					chinaOption.series.push(item);
				})
				chinaOption.visualMap.seriesIndex = [ 0 ];
	
				chinaChart = initChart();
				chinaChart.setOption(chinaOption, true);
				onclickEvent();
			});
		}

		// 根据主机类别改变主机型号下拉框
	    function changeProductTypeByMachineType(vMachineType) {
	      $.getJSON('${ctx}/lg/general/query/productTypeList', {machineType: vMachineType}, function(data) {
	        if (data) {
	          $('#productType').empty();
	          $('#productType').append($("<option>").val('').text('全部'));
	          $.each(data, function(i, d) {
	            $('#productType').append($("<option>").val(d).text(d));
	          });
	          $('#productType').prop('selectedIndex', 0);
	        }
	      });
	    }
	    
	    // 根据主机型号改变订货好下拉框
	    function changeOrderNumberByProductType(vProductType) {
	      $.getJSON('${ctx}/lg/general/query/orderNumberList', {productType: vProductType}, function(data) {
	        if (data) {
	          $('#orderNumber').empty();
	          $('#orderNumber').append($("<option>").val('').text('全部'));
	          $.each(data, function(i, d) {
	            $('#orderNumber').append($("<option>").val(d.orderNumber).text(d.orderNumber));
	          });
	          $('#orderNumber').prop('selectedIndex', 0);
	        }
	      });
	    }
	</script>
</body>

</html>