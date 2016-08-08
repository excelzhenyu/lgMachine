<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<html>
<head>
	<title>试验机器工作概览</title>
	<meta name="decorator" content="default"/> 
	<script src="${ctxStatic}/echarts/theme/vintage.js"></script>
	<link rel="stylesheet" href="${ctxStatic}/custom-css/drawLayout.css">
	
	<script type="text/javascript">
	$(document).ready(function() {	
		$("#deviceCode").keyup(function (e) {
		    if (e.keyCode == 13) {
		    	gotoProfile();
		    }
		});
		$('input[id=uploadFile]').change(function() {  
			var fileNameValue = $(this).val();
			if(fileNameValue) {
				fileNameValue = fileNameValue.split(/[\\/]/).pop();
			}
			$("#promptId").html('<strong>已选文件：</strong>' + fileNameValue);
		});  
		//清除试验机列表
		$("#clearMachine").click(function(event) {
			event.stopPropagation(); //阻止事件冒泡到父元素，阻止任何父事件处理程序被执行
			$("#machineArea").empty();
			$("#machineSelect").empty();
			$("#machineSelect").append($("<option>").val('').text('全部'));
		});

		//选择批次号
		$("#batchNumberSelect").change(function() {
			genScreen();
		});
		genScreen();
		//上传文件
		$('#importForm').on("submit",function() {
			var fileName = $("#uploadFile").val();
			if(fileName && (fileName.indexOf('.xls') > -1 || fileName.indexOf('.xlsx') > -1)){
				$(this).ajaxSubmit({
		            dataType: "json",           
		            beforeSubmit: function(arr,$form,options){
		            },
		            success: function(data,status,xhr,$form){
		            	if (data) {
		            		setMachine(data);
		                	$("#importMachineCollapse").collapse('show');
		                }
		            },
		            error: function(xhr, status, error, $form){
						showTip('导入失败！', 'error',1500,0);
		            },
		            complete: function(xhr, status, $form){
		            }
		        });
			} else {
				showTip('导入文件仅允许xls或xlsx格式！', 'error',1500,0);
			}
		    return false; 
	    });
		//数据来源切换
		$('#importForm .control-group').eq(0).find('select').change(function() {
			var selectValue = $(this).val()
			var controlDiv = $('#importForm .control-group')
			if (selectValue == 1) {
				$('#batchNumbers').hide();
				controlDiv.eq(1).show();
				controlDiv.eq(2).show();
				$("#machineControlGroup").show();

				$("#machineArea").html('');
			} else if(selectValue == 2) {
				controlDiv.eq(1).hide();
				controlDiv.eq(2).hide();
				$("#machineControlGroup").hide();
				$('#batchNumbers').show();
				$("#machineArea").html('');
			}
		});
		
		$("#machineSelect").change(function() {
			filterMachine($(this));
		});
		
		$(".mapClass li").eq(1).click(function() {
			$('#bar-panel').css({'position': 'absolute'});
			setBar();
		});
		$('.mapClass li').eq(0).click(function() {
			$('#bar-panel').css({'position': 'relative'});
		});
	});
	
	function filterMachine(selector) {
		
		//var deviceNo = $(this).val()?$(this).val():$("#cacheMahine").val();
		var deviceNo = selector.val()?selector.val():$("#cacheMahine").val();
		var dateBegin = $("#dateBegin").val();
		var dateEnd = $("#dateEnd").val();
		
		$.getJSON("${ctx}/lg/exptmachine/lgExptMachineJob/filterMachine", { deviceNo: deviceNo, dateBegin: dateBegin, dateEnd: dateEnd}, function(data) {
			$("#machineDetail").empty();
			$.each(data, function(index, subItem) {
				var ahref = '<a href="javascript:void(0);" title="' + subItem.address +'" '
				+ 'onclick="openModal(\'' + subItem.name +'\',\'' + subItem.workDate + '\');" ' 
				+ '>轨迹</a>';
				$("#machineDetail").append(
					$('<tr>').append($("<td>").text(subItem.name))
					.append($("<td>").text(subItem.workDate))
					.append($("<td>").html(ahref)));
			});
			
		});
	}
	//设置试验机筛选
	function setMachine(data) {
    	//缓存试验机
    	var cacheMahine = '';
		$("#machineArea").empty();
    	$("#machineSelect").empty();
		$("#machineSelect").append($("<option>").val('').text('全部'));
		$("#deviceCode").empty();
		$("#deviceCode").append($("<option>").val('').text('选择试验机'));

    	$.each(data, function(index, machine) {
			var lilabel = '<label><input type="checkbox" name="device" value="' + machine + '" checked="checked" />' + machine+  '</label>';
			$("#machineSelect").append($("<option>").val(machine).text(machine));
			$("#machineArea").append($('<li>').html(lilabel));
			$("#deviceCode").append($("<option>").val(machine).text(machine));
    		cacheMahine += machine + ',';
    	});
		$("#cacheMahine").val(cacheMahine);
	}
	//轨迹模态框
	function openModal(device, exptDate) {
		var src = "${ctx}/lg/exptmachine/lgExptMachineJob/line" + "?deviceNo=" + device + "&exptDate=" + exptDate; 
		$("#lineFrame").attr("src",src);  
		$('#myModal').modal('show');
	}
	//生成试验机筛选列表
	function genScreen() {
		var batchNumber = '';
		$("#batchNumberSelect").find("option:selected").each(function() {
			batchNumber += $(this).val() + ',';
		});
		$.getJSON("${ctx}/lg/exptmachine/lgExptMachineJob/getMachineCodeByBatchNumber", { batchNumber: batchNumber}, function(data) {
			if(data) {
				setMachine(data);
			}
		});
	}
	</script>
	<style> 
		.main-container {
		    overflow-y:hidden;
		}
		.left-container {
		    overflow-y:auto;
		}
		
		.right-container {
		    position: absolute;
		    right: 20%;
		    width: 60%;
		    height: 100%;
		    padding: 0;
		    border: none;
		    overflow-y:hidden;
		}
		
		.last-container {
		    position: absolute;
		    right: 0;
		    bottom: 0;
		    top: 0;
		    width: 20%;
		    overflow-y: auto;
		}
		.last-container .right-bar-panel {
			/**position: absolute;*/
			top: 50px;
			right: 0;
			bottom: 0;
			left: 0;
			margin: 0;
			padding:10px 10px 10px 10px;
		}
		
		.ch_box {
			height:120px;
			overflow-x:hidden;
			overflow-y: auto; 
			border: 1px solid #EEE;
		}
		.ch_box ul {
			margin: 0;
			padding: 0;
		}
		.ch_box ul li {
			list-style-type:none;
		}
		
		.modal-body {
			min-height: 350px;
		    max-height: 375px;
		}
		.modal.fade.in {
		    position:fixed;
		    margin:auto;
		    left:0; 
		    right:0; 
		    top:0;
		}
		
		#machineControlGroup {
			display: none;
		}
		.accordion {
		    margin-bottom: 5px;
		}
		
		.searchdiv {
		    position: absolute;
		    top: 10px;
		    right: 100px;
		}
		.tab-content > .tab-pane,
		.pill-content > .pill-pane {
			display: block;     
			height: 0;          
			overflow-y: hidden; 
		}
		.tab-content > .active,
		.pill-content > .active {
			height: auto;
		}
	</style>
</head>
<body  style="height: 100%; margin: 0">

<!-- Modal -->
<div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="width: 800px;height:450px;overflow-x:hidden;overflow-y:hidden;">
    <iframe id="lineFrame" style="width:100%;height:100%;overflow-x:hidden;overflow-y:hidden;"></iframe>
</div>
<div class="main-container">
    <div class="left-container">
        <div class="container-fluid">
            <div class="row-fluid">
                <div class="span12">
                    <form id="importForm" action="${ctx}/lg/exptmachine/lgExptMachineJob/import" method="post" enctype="multipart/form-data" style="margin:0;">
                        <input id="uploadFile" name="file" type="file" value="" style="display:none" />
                        <input id="selectedMahine" value="" type="hidden" />
                        <input id="cacheMahine" value="" type="hidden" />
                        
						<div class="control-group" style="margin-top: 5px;">
							<label class="control-label">数据来源：</label>
							<div class="controls">
								<select path="dataType" class="span12">
									<option value="2"  selected="selected">从批次号选择</option>
									<option value="1">从文件导入</options>
									
								</select>
							</div>
						</div>
                        <div class="control-group" style="clear: both;margin-top: 30px;display: none;">
                            <div class="controls">
                                <div class="alert alert-info alert-dismissable">
                                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                                    <span id="promptId">
                                    <a href="${ctx}/lg/exptmachine/lgExptMachineJob/import/template"><strong>下载导入文件模板</strong></a>
                                    </span>
                                </div>
                            </div>
                        </div>

                        <div class="control-group" style="display: none;">
                            <div class="controls">
                                <div class="row-fluid">
                                    <div class="span6">
                                        <button class="btn btn-block" type="button" onclick="$('input[id=uploadFile]').click();">选择文件</button>
                                    </div>
                                    <div class="span6">
                                        <button class="btn btn-primary btn-block" id="btnImportSubmit" type="submit"> 导入 </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                    <div class="control-group" id="batchNumbers">
                    	<label class="control-label">选择批次号：</label>
						<div class="controls">
							<select id="batchNumberSelect" class="span12" multiple="multiple" placeholder="全部试验批次号" style="margin-bottom: 10px;">
								<c:forEach items="${fns:getBatchNumber()}" var="item">
									<option value="${item.batchNumber}">${item.batchNumber}</option>
								</c:forEach>
							</select>
						</div>
					</div>
                    <div class="control-group" style="clear: both;margin-top:5px;" id="machineControlGroup">
                        <div class="controls">
                            <div class="accordion" id="importMachineAccordion">
                                <div class="accordion-group">
                                    <div class="accordion-heading">
                                        <a class="accordion-toggle collapsed" data-toggle="collapse" href="#importMachineCollapse">试验机列表
                                        	<button class="btn-primary" id="clearMachine" style="float:right;">清空</button>
                                        </a>
                                    </div>
                                    <div id="importMachineCollapse" class="accordion-body collapse">
                                        <div class="accordion-inner ch_box">
                                            <ul id="machineArea"> </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="control-group">
                       	<label class="control-label">起始试验日期：</label>
                        <div class="controls">
                            <input id="dateBegin" name="dateBegin" type="text" readonly="readonly" maxlength="20" class="Wdate span12" 
                            placeholder="起始试验日期" style="margin-bottom: 0;"
                            value="<fmt:formatDate value="${dateBegin}" pattern="yyyy-MM-dd"/>" 
                            onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label">结束试验日期：</label>
                        <div class="controls">
                            <input id="dateEnd" name="dateEnd" type="text" readonly="readonly" maxlength="20" class="Wdate span12" 
                            placeholder="结束试验日期" style="margin-bottom: 18px;"
                            value="<fmt:formatDate value="${dateEnd}" pattern="yyyy-MM-dd"/>" 
                            onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
                        </div>
                    </div>

                    <div class="control-group">
                        <div class="controls">
                            <a href="javascript:getPoint();" role="button" class="btn btn-primary btn-block">查看试验机位置</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    <div class="right-container">
        <div id="chart-panel" class="right-panel"></div>
        <div class="searchdiv"> 
	        <select id="deviceCode" class="span12" style="width:100px;height:15px;">
	        	<option value="">选择试验机</option>
			</select>
        	<!-- <input id="deviceCode" type="text" style="width:80px;height:15px;" placeholder="输入试验机"/>   -->
        </div>
    </div>
	<div class="last-container">
	<div class="tabbable" >
		<ul class="nav nav-tabs mapClass">
			<li  class="active"><a href="#trackTab" data-toggle="tab">工作轨迹</a></li>
			<li><a href="#barTab" data-toggle="tab">工作时长</a></li>
		</ul>
		<div class="tab-content">
			<div class="tab-pane active" id="trackTab">
				<div class="container-fluid" style="margin:0;padding:0 10px 0 10px;">
					<div class="row-fluid">
						<div class="span12">
							<div class="control-group">
								<label class="control-label">按试验机筛选：</label>
								<div class="controls">
									<select id="machineSelect" class="span12">
										<option value="">全部</option>
									</select>
								</div>
							</div>
							<div class="control-group" style="clear: both;margin-top: 30px;">
								<label class="control-label">试验机位置详情</label>
								<div class="controls">
									<table class="table table-striped table-bordered table-hover table-condensed">
										<thead>
											<tr><th>试验机</th><th>日期</th><th>查看</th></tr>
										</thead>
										<tbody id="machineDetail">
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="tab-pane " id="barTab">
				<div id="bar-panel" class="right-bar-panel"></div>
			</div>
		</div>
	</div>
	</div>

</div>
	<script type="text/javascript">
		window.onresize = function(){
			myChart.resize();
			barChart.resize();
		}
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
		var myChart;
		
		var exptData;
		var saleData;
		
		var  option = {
           title: {
               text: '试验机器位置',
               subtext: '试验机器位置',
               left: 'center'
           },
           tooltip : {
               trigger: 'item'/*,
               formatter: '批次号：{a}<br />试验机编号:{b} {c}'*/
           },
           toolbox : {
	            itemSize: 15,
	            itemGap: 15,
	            right: 5,
		        feature: {
	        		mySearch: {
		                show: true,
		                title: '搜索',
		                icon: 'image://${ctxStatic}/images/search_24.png',
		                onclick: function() {
		                	gotoProfile();
		                }
		            },
		            myShow: {
		                show: true,
		                title: '点我显示销售机',
		                icon: 'image://${ctxStatic}/images/unchecked_checkbox.png',
		                onclick: function() {
		                	if(option.toolbox.feature.myShow.icon.indexOf("unchecked") == -1) {
			                	option.toolbox.feature.myShow.icon = 'image://${ctxStatic}/images/unchecked_checkbox.png';
			                	option.toolbox.feature.myShow.title = '点我显示销售机';
			            		if(exptData) {
				            		draw(exptData);
			            		} else {
				            		getPoint();
			            		}
		                	} else {
			                	option.toolbox.feature.myShow.icon = 'image://${ctxStatic}/images/checked_checkbox.png';
				                option.toolbox.feature.myShow.title = '点我隐藏销售机';
				                if(saleData) {
				            		draw(exptData, saleData);
				                } else {
				                	getScatterSale();
				                }
		                	}
		                }
		            },
		            myGoto: {
		                show: true,
		                title: '机器电子档案概览',
		                icon: 'image://${ctxStatic}/images/home_chart.png',
		                onclick: function() {
		                	addTabPage('机器电子档案', '${ctx}/lg/exptmachine/lgExptMachineJob/gotoMachineProfile');
		                }
		            }
		           
		        }
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
		$.get('${ctxStatic}/echarts/mapJson/china.json', function (chinaJson) {
			echarts.registerMap('china', chinaJson);
			myChart = initChart();
			myChart.setOption(option, true);
		});
		
		
		function onclickEvent() {
			myChart.on('click', function(params) {
				if (params.seriesType == 'scatter') {  // 点点进入机器电子档案
		 			addTabPage('机器电子档案', '${ctx}/lg/lgMachineEvent/list?code=' + params.name);
				} 
			});
		}
		//批次号分组
		function getBatchPoint() {
			var batchNumber = '';
			var dateBegin = $("#dateBegin").val();
			var dateEnd = $("#dateEnd").val();
			$("#batchNumberSelect").find("option:selected").each(function() {
				batchNumber += $(this).val() + ',';
			});
			$.getJSON("${ctx}/lg/exptmachine/lgExptMachineJob/getScatterData", {batchNumber: batchNumber, dateBegin: dateBegin, dateEnd: dateEnd}, function(data) {
				exptData = data;
				if(data) draw(data);
			});
		}
		//试验机分组
		function getDevicePoint() {
			var deviceNo = '';//"C30090,C2000B,";
			var dateBegin = $("#dateBegin").val();
			var dateEnd = $("#dateEnd").val();
			$("input[name='device']").each(function(){
				if ('checked' == $(this).attr("checked")) {
					deviceNo += $(this).val()+",";
				}
			});
			
			$.getJSON("${ctx}/lg/exptmachine/lgExptMachineJob/getPointData", { deviceNo: deviceNo, dateBegin: dateBegin, dateEnd: dateEnd}, function(data) {
				exptData = data;
				if(data) draw(data);
			});
		}
		//销售机
		function getScatterSale() {
			var dateBegin = $("#dateBegin").val();
			var dateEnd = $("#dateEnd").val();
			$.getJSON("${ctx}/lg/exptmachine/lgExptMachineJob/getScatterSale", { dateBegin: dateBegin, dateEnd: dateEnd}, function(data) {
				saleData = data;
				if(data) draw(exptData,data);
			});
		}
		//跳转到机器电子档案
		function gotoProfile() {
			var code = $("#deviceCode").val();
			if(!code) {
				 $("#deviceCode").focus();return;
			}
			addTabPage('机器电子档案', '${ctx}/lg/lgMachineEvent/list?code=' + code);
		}
		function draw(data1, data2) {
			var series = [];
			var legendData = [];
			
			$("#machineDetail").empty();

			if(data1) {
				$.each(data1, function (i, item) {
					$.each(item.mapPointList, function(index, subItem) {
	
						var ahref = '<a href="javascript:void(0);" title="' + subItem.address +'" '
						+ 'onclick="openModal(\'' + subItem.name +'\',\'' + subItem.workDate + '\');" ' 
						+ '>轨迹</a>';
						$("#machineDetail").append(
							$('<tr>').append($("<td>").text(subItem.name))
							.append($("<td>").text(subItem.workDate))
							.append($("<td>").html(ahref)));
					});
	
					series.push({
						name: item.seriesName,
						type : 'scatter',
						coordinateSystem : 'geo',
						data : item.mapPointList,
						symbolSize : 8,
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
					legendData.push(item.seriesName);
				});
			}
			if(data2) {
				$.each(data2, function (i, item) {
					series.push({
						name: item.seriesName,
						type : 'scatter',
						coordinateSystem : 'geo',
						data : item.mapPointList,
						symbolSize : 8,
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
					legendData.push(item.seriesName);
				});
			}
			option.series = series;
			option.legend.data = legendData;
		    myChart.showLoading();
	        setTimeout(refresh, 500);
        	//$("#postionDetailCollapse").collapse('show');
        	
    		filterMachine($("#machineSelect"));
		}
		function getPoint() {
			var dataType = $('#importForm .control-group').eq(0).find('select').val();
			option.toolbox.feature.myShow.icon = 'image://${ctxStatic}/images/unchecked_checkbox.png';
        	option.toolbox.feature.myShow.title = '点我显示销售机';
			if(dataType == 1) {
				getDevicePoint();
			} else if(dataType == 2) {
				getBatchPoint();
			}
			setBar();
		}
		function refresh(){
			if (myChart && myChart.dispose) {
		        myChart.dispose();
		    }
			myChart = initChart();
			myChart.setOption(option);
			onclickEvent();
		}
		function setBar() {
			barChart = echarts.init(barDom);
			barChart.setOption(barOption);
		}
		getPoint();
		
		var barDom = document.getElementById("bar-panel");
		var barChart = echarts.init(barDom);

		var barOption = {
			    color: ['#3398DB'],
			    tooltip : {
			        trigger: 'axis',
			        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
			            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
			        }
			    },
			    grid: {
			        left: '1%',
			        right: '5%',
			        bottom: '3%',
			        containLabel: true
			    },
			    yAxis : [
			        {
			            type : 'category',
			            data:['试验机'],
			            axisTick: {
			                alignWithLabel: true
			            }
			        }
			    ],
			    xAxis : [
			        {
			            type : 'value'
			        }
			    ],
			    series : [
			        {
			            name:'工作时长',
			            type:'bar',
			            data: [0],
			            barMinHeight:'15',
		                barMaxWidth:'50',
			        }
			    ]
		};
		function setBar() {
			var batchNumber = '';
			var deviceNo = '';
			var dateBegin = $("#dateBegin").val();
			var dateEnd = $("#dateEnd").val();
			
			
			var dataType = $('#importForm .control-group').eq(0).find('select').val();
			var getUrl = "";//"${ctx}/lg/exptmachine/lgExptMachineJob/getExptMachineRunDuration";
			var params = {};
			if(dataType == 1) {
				$("input[name='device']").each(function(){
					if ('checked' == $(this).attr("checked")) {
						deviceNo += $(this).val()+",";
					}
				});
				getUrl = "${ctx}/lg/exptmachine/lgExptMachineJob/getExptMachineRunDuration2";
				params ={ deviceNo: deviceNo, dateBegin: dateBegin, dateEnd: dateEnd};
			} else if(dataType == 2) {
				$("#batchNumberSelect").find("option:selected").each(function() {
					batchNumber += $(this).val() + ',';
				});
				getUrl = "${ctx}/lg/exptmachine/lgExptMachineJob/getExptMachineRunDuration";
				params = {batchNumber: batchNumber, dateBegin: dateBegin, dateEnd: dateEnd};
			}
			$.getJSON(getUrl, params , function(data) {
				console.log(JSON.stringify(data));
				if(data) {
					var series = [
    			        {
    			            name:'时长分布',
    			            type:'bar',
    			            barMinHeight:'15',
    		                barMaxWidth:'50',
    		                label : {
    							normal: {
    		                        show : true,
    		                        position: 'insideRight'
    							}
    	                    },
    			            data: data.runDurationList
    			        }
    			    ];
					var  yAxis = [
				        {
				            type : 'category',
				            data : data.xAxisList
				        }
				    ];
					barOption.yAxis = yAxis;
					barOption.series = series;
					barChart = echarts.init(barDom);
					barChart.setOption(barOption);
				}
			});
		}
		//setBar();
	</script>
   </body>
</html>