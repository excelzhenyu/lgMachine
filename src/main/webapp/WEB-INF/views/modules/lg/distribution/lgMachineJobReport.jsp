<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>机器工作报告</title>
<meta name="decorator" content="default" />
<script src="${ctxStatic}/echarts/theme/vintage.js"></script>
<link rel="stylesheet" href="${ctxStatic}/custom-css/drawLayout.css">
<script type="text/javascript">
	  $(document).ready(function() {
			$('#machineType').change(function() {
				changeProductTypeByMachineType($(this).find('option:selected').val());
				getMachineCodeList();
			});
  
			$('#productType').change(function() {
		     	changeOrderNumberByProductType($(this).find('option:selected').val());
				getMachineCodeList();
		    });
			$('#orderNumber').change(function() {
				getMachineCodeList();
		    });
			changeProductTypeByMachineType($(this).find('option:selected').val());
			changeOrderNumberByProductType($(this).find('option:selected').val());
			getMachineCodeList();
			
			$("#jobReportSubmit").click(function() {
				jobReportSubmit();
			});
			$("#machineSearchSubmit").click(function() {
				searchJobReportSubmit();
			});
			$(".mapClass").eq(0).click(function() {
				loadChinaMap();
			});
			
			loadChinaMap();
			$("#machineCodeList").change(function() {
				$("#machineSearch").empty();
				$("#machineSearch").append($("<option>").val('').text('全部'));
				$(this).find("option:selected").each(function() {
					$("#machineSearch").append($("<option>").val($(this).val()).text($(this).val()));
				});
				$('#machineSearch option:first').attr('selected','selected');
				$("#s2id_machineSearch span.select2-chosen").text('全部');
			});
	  }); 
	  	var flag = false; //标示是点击生成报告 还是 查询按钮
	 	window.onresize = function(){
			if(myChart) myChart.resize();
		}
		function refresh(){
			if (myChart && myChart.dispose) {
		        myChart.dispose();
		    }
			myChart = initChart();
			myChart.setOption(option);
		}
		function loadChinaMap() {
			$.get('${ctxStatic}/echarts/mapJson/china.json', function (chinaJson) {
				echarts.registerMap('china', chinaJson);
				myChart = initChart();
				myChart.setOption(option, true);
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
	    //获取机器列表
	    function getMachineCodeList() {
	    	$.getJSON('${ctx}/lg/distribution/lgMachineJobReport/getMachineCodeList',{
	    		machineType: $("#machineType").val(),
	    		modelNumber: $("#productType").val(),
	    		orderNumber: $("#orderNumber").val()
	    	}, function(data){
	    		if(data) {
	    			$('#machineCodeList').empty();
	   	          	$.each(data, function(i, d) {
	   	            	$('#machineCodeList').append($("<option>").val(d).text(d));
	   	         	});
	    		}
	    	});
	    }
	    function jobReportSubmit() {
	    	flag = false;
	    	getPonit();
	    	getJobReport();
	    }
	    function getPonit() {
	    	var runDateBegin = $("#runDateBegin").val();
	    	var runDateEnd = $("#runDateEnd").val();
	    	var deviceNos = '';
	    	var city = $("#city").val();
	    	$("#machineCodeList").find("option:selected").each(function() {
	    		deviceNos += $(this).val() + ',';
			});
 			if(!deviceNos) { showTip('请选择机器！', 'error',1500,0); return;};
 			if(!runDateBegin){ showTip('请选择起始开工日期！', 'error',1500,0);  return;}
 			if(!runDateEnd) { showTip('请选择结束开工日期！', 'error',1500,0);  return;};
	    	$.getJSON('${ctx}/lg/distribution/lgMachineJobReport/getMachineJobReportPoint',{
	    		runDateBegin: runDateBegin,
	    		runDateEnd: runDateEnd,
	    		deviceNos: deviceNos,
	    		city: city
	    	},function(data) {
	    		if(data) {
	    			var point = data;
    			 	var legendData = [];
                    var series = [];
                    point.forEach(function (item, i) {
                    	series.push({
    						name: item.device,
    						type : 'scatter',
    						coordinateSystem : 'geo',
    						data : item.mapPointList,
    						symbolSize : 10,
    						label: {
    						    normal: {
    						        formatter: '{b}',
    						        position: 'right',
    						        show: false
    						    },
    						    emphasis: {
    						        show: false
    						    }
    						}
    					});
    					legendData.push(item.device);
    				});
    				option.series = series;
    				option.legend.data = legendData;
    			    myChart.showLoading();
    		        setTimeout(refresh, 500);
	    		}
	    	});
	    }
	    function getJobReport() {
	    	var runDateBegin = $("#runDateBegin").val();
	    	var runDateEnd = $("#runDateEnd").val();
	    	var deviceNos = '';
	    	var city = $("#city").val();
	    	$("#machineCodeList").find("option:selected").each(function() {
	    		deviceNos += $(this).val() + ',';
			});
	    	if(flag) {
	    		var deviceNo = $("#machineSearch").val();
	    		deviceNos = deviceNo?deviceNo:deviceNos;
	    	} 
 			if(!runDateBegin) return;
 			if(!runDateEnd) return;
 			if(!deviceNos) return;
 			
	    	$.getJSON('${ctx}/lg/distribution/lgMachineJobReport/getMachineJobReport',{
	    		runDateBegin: runDateBegin,
	    		runDateEnd: runDateEnd,
	    		deviceNos: deviceNos,
	    		city: city,
	    		pageNo: $("#pageNo").val(),
	    		pageSize: $("#pageSize").val()
	    	},function(data) {
	    		if(data) {
	    			var page = data;
    				$("#contentTable tbody").empty();
	    			$.each(page.list, function(index, item) {
	    				$("#contentTable tbody").append("<tr>")
	    					.append($("<td>").text(item.deviceNo))
	    					.append($("<td>").text(item.modelNumber))
	    					.append($("<td>").text(item.orderNumber))
	    					.append($("<td>").text(item.workDate))
	    					.append($("<td>").text(item.oilAvg))
	    					.append($("<td>").text(item.sliceWorkDuration))
	    					.append($("<td>").text(item.runoffCount))
	    					.append($("<td>").text(item.city))
	    					.append($("<td>").text(item.received))
	    					.append($("<td>").text(item.onceRunDurationMax))
	    					.append($("<td>").text(item.onceRunDurationMIn))
	    					.append($("<td>").text(item.rotationlSpeedMax))
	    					.append($("<td>").text(item.alarmCount))
	    					.append($("<td>").text(item.jobWorkState))
	    					.append($("<td>").html("<a href='javascript:void(0);' onclick='openModal(\"" + item.deviceNo + "\",\"" + item.workDate + "\");'>查看</a>"))
	    					.append("</tr>");
	    			});
    			  	$(".pagination").html(page.html);
                    $("#pageNo").val(page.pageNo);
                    $("#pageSize").val(page.pageSize);
	    		}
	    	});
	    }
	    function searchJobReportSubmit() {
	    	flag = true;
	    	getJobReport();
	    }
	    function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			getJobReport();
        	return false;
        }
	    
	    function openModal(deviceNo, workDate) {
			var src = "${ctx}/lg/distribution/lgMachineJobReport/jobReportLine" + "?deviceNo=" + deviceNo + "&workDate=" + workDate; 
			$("#lineFrame").attr("src",src);  
			$('#myModal').modal('show');
		}
	</script>
	<style>
		.main-container {
		    overflow-y:hidden;
		}
		.right-container {
			overflow-y:auto;
		}
		.right-container .right-panel {
			position: absolute;
			top: 40px;
			right: 5px;
			bottom: 5px;
			left: 4px;
			margin: 0;
		}
		.ul-form {
			margin:0;
			padding:0;
			list-style-type:none;
		}
		.ul-form li {
			float: left;
			height: 35px;
    		line-height: 35px;
    		margin-right:20px;
		}
	</style>
</head>
<body>
	<!-- Modal -->
	<div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="width: 800px;height:450px;overflow-x:hidden;overflow-y:hidden;">
	    <iframe id="lineFrame" style="width:100%;height:100%;overflow-x:hidden;overflow-y:hidden;"></iframe>
	</div>
	<div class="main-container">
		<div class="left-container">
		<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12">
				<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
				<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>	
				<div class="control-group">
					<label class="control-label">主机类别：</label>
					<div class="controls">
						<select id="machineType" class="span12">
							<c:forEach items="${fns:getDictList('machineType')}" var="item">
								<option value="${item.value}">${item.label}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">主机型号：</label>
					<div class="controls">
						<select  id="productType" class="span12">
							<option value=""> 全部 </option>
						</select>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">订货号：</label>
					<div class="controls">
						<select id="orderNumber" class="span12">
							<option value=""> 全部 </option>
						</select>
					</div>
				</div>
				<div class="control-group">
                   	<label class="control-label">机器列表：</label>
					<div class="controls">
						<select id="machineCodeList" class="span12" multiple="multiple" placeholder="请选择机器" style="margin-bottom: 2px;">
						</select>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">工作地区：</label>
					<div class="controls">
						<select id="city" class="span12">
							<option value=""> 全部 </option>
							<c:forEach items="${fns:getLeafCityList()}" var="item">
								<option value="${item.cityName}">${item.cityName}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" >起始开工日期：</label>
					<div class="controls">
						<input id="runDateBegin" name="runDateBegin" type="text" readonly="readonly" maxlength="20" class="Wdate span12" style="margin-bottom: 0;"
							value="<fmt:formatDate value="${LgMachineJobReportData.runDateBegin}" pattern="yyyy-MM-dd"/>"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" >结束开工日期：</label>
					<div class="controls">
						<input id="runDateEnd" name="runDateEnd" type="text" readonly="readonly" maxlength="20" class="Wdate span12" style="margin-bottom: 18px;"
							value="<fmt:formatDate value="${LgMachineJobReportData.runDateEnd}" pattern="yyyy-MM-dd"/>"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
					</div>
				</div>
				
				<div class="control-group">
					<div class="controls">
						<input id="jobReportSubmit" class="btn btn-primary btn-block" type="button" value="生成工作报告"/>
					</div>
				</div>
				</div>
			</div>
			</div>
		</div>
		<div class="right-container">
			<div id="table-panel">
				<div class="tabbable">
				    <ul class="nav nav-tabs mapClass">
				    	<li class="active"><a href="#jobReportMap" data-toggle="tab">地图展示</a></li>
				        <li ><a href="#jobReportTable" data-toggle="tab">工作报告</a></li>
				    </ul>
				    <div class="tab-content">
				      <div class="tab-pane active" id="jobReportMap">
				            <div id="chart-panel"  class="right-panel"></div>
				        </div>
				        <div class="tab-pane " id="jobReportTable">
					        <div class="container-fluid">
					            <div class="row-fluid">
									<div class="span12">
										<ul class="ul-form">
											<li><label>机器编号：</label>
												<select id="machineSearch" class="input-small">
													<option value=""> 全部 </option>
												</select>
											</li>
											<li class="btns"><input id="machineSearchSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
											<li class="clearfix"></li>
										</ul>
										<table id="contentTable" class="table table-striped table-bordered table-condensed table-hover">
											<thead>
												<tr>
													<th> 机器编号 </th>
													<th> 机器型号 </th>
													<th> 订货号 </th>
													<th> 工作日期 </th>
													<th> 平均油耗 </th>
													<th> 工作时长 </th>
													<th> 开机次数 </th>
													<th> 工作区域 </th>
													<th> 回款逾期 </th>
													<th> 单次最大时长 </th>
													<th> 单次最小时长 </th>
													<th> 最高转速 </th>
													<th> 报警次数 </th>
													<th> 工作工况 </th>
													<th> 轨迹 </th>
												</tr>
											<tbody>
											
											</tbody>
										</table>
										<div class="pagination">
											<ul>
											<li class="disabled"><a href="javascript:">« 上一页</a></li>
											<li class="active"><a href="javascript:">1</a></li>
											<li class="disabled"><a href="javascript:">下一页 »</a></li>
											<li class="disabled controls"><a href="javascript:">当前 <input type="text" value="1" onkeypress="var e=window.event||this;var c=e.keyCode||e.which;if(c==13)page(this.value,30,'');" onclick="this.select();"/> / <input type="text" value="30" onkeypress="var e=window.event||this;var c=e.keyCode||e.which;if(c==13)page(1,this.value,'');" onclick="this.select();"/> 条，共 0 条</a></li>
											</ul>
											<div style="clear:both;"></div>
										</div> 	
									</div>
								</div>
							</div>
							
				        </div>
				      
				    </div>
				</div>
			</div>
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
		var rightDom = document.getElementById("chart-panel");
		var app = {};
		var myChart;

		var  option = {
           title: {
               text: '机器工作报告',
               subtext: '机器工作位置报告',
               left: 'center'
           },
           tooltip : {
               trigger: 'item',
               formatter: '试验机编号：{a}<br />{b} {c}'
           },
		   legend: {
		        orient: 'vertical',
		        y: 'top',
		        x:'left'
		   },
           geo: {
		        map: 'china',
		        label: {
		            emphasis: {
		                show: false
		            }
		        },
		        roam: true
		    }
       };
	</script>
	
</body>

</html>