<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>机器开工柱图分布</title>
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
			<form:form id="drawForm" modelAttribute="lgMachineDistributionEntity" action="${ctx}/lg/distribution/lgMachineDistributionBar/getBarData" method="post" class="form-search .form-horizontal">
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
		
		var barChart;
		var cityName;
		var isProvince = false;
		//初始option数据
		var rightDom = document.getElementById('chart-panel');

		$(document).ready(function() {
			if(barChart) barChart.resize();
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
				cityName = '';
				$(this).ajaxSubmit({
		            dataType: "json",           
		            beforeSubmit: function(arr,$form,options){
		            },
		            success: function(data,status,xhr,$form){
		            	drawBar(data);
						isProvince = false;
						barOption.toolbox.feature.myBar.show = false;
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
			if(barChart) barChart.resize();
		}
		
		function onclickEvent() {
			barChart.on('click', function(params) {
				cityName = params.name;
				$("#location").val(cityName);
				isProvince = true;
				barOption.toolbox.feature.myBar.show = true;
				clickDrawBar();
			});
		}

        var barOption = {
      		title: {
		        text: '机器开工分布-全国',
		        left: 'center'
		    },
            tooltip: {
                trigger: 'axis',
                axisPointer: { // 坐标轴指示器，坐标轴触发有效
                    type: 'shadow' // 默认为直线，可选为：'line' | 'shadow'
                }
            },
            toolbox : {
	            itemSize: 25,
	            itemGap: 30,
	            right: '5%',
		        feature: {
		            myBar: {
		                show: false,
		                title: '机器开工分布-全国',
		                icon: 'image://${ctxStatic}/images/bar_chart.png',
		                onclick: function() {backChinaBar();}
		            }
		        }
		    },
            legend: {
                top: '5%',
            },
            grid: {
            	top: '10%',
                left: '3%',
                right: '3%',
                bottom: '3%',
                containLabel: true
            },
            xAxis: {
                type: 'category',
                axisLabel: {
                    interval: 0,
                    formatter:function(val){
                        return val.split("").join("\n");
                    }
                    //rotate: 90
                }
            },
            yAxis: {
                type: 'value'
            }
        };
        clickDrawBar();
		function clickDrawBar() {
			$.getJSON('${ctx}/lg/distribution/lgMachineDistributionBar/getBarData', {
				machineType : $('#machineType').val(),
				productType : $('#productType').val(),
				orderNumber : $('#orderNumber').val(),
				runMonth : $("#runMonth").val(),
				location : $("#location").val()
			}, function(data) {
				//console.log(JSON.stringify(data));
				drawBar(data);
				isProvince = false;
			});
		}
		function drawBar(data) {

			if(data) {
				var seriesMap = data.seriesMap;
				var xAxisData = data.xAxisData;
				var legendData = [];
				var series = [];
				$.each(seriesMap, function(key, seriesData) { 
					//console.log(seriesData); 
					legendData.push(key);
					series.push({
		                name: key,
		                type: 'bar',
		                stack: '总量',
		                label: {
		                    normal: {
		                        show: true,
		                        position: 'insideTop'
		                    }
		                },
		                barMinHeight:'15',
		                barMaxWidth:'50',
		                data:seriesData
		            });
				}); 

				//console.log(JSON.stringify(series));
				//console.log(legendData);
				barOption.series = series;
				barOption.legend.data = legendData;
				barOption.xAxis.data = xAxisData;
				barOption.title.text = '机器开工分布-' + (!cityName?'全国':cityName);
				barChart = initChart();
				barChart.setOption(barOption, true);
				if(!isProvince) {
					onclickEvent();
				}
			}
		}
		function backChinaBar() {
			$("#location").val('');
			barOption.toolbox.feature.myBar.show = false;

			$("#drawForm").submit();
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