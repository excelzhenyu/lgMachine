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
		$('input[id=uploadFile]').change(function() {  
			var fileNameValue = $(this).val();
			if(fileNameValue) {
				fileNameValue = fileNameValue.split(/[\\/]/).pop();
			}
			$("#promptId").html('<strong>已选文件：</strong>' + fileNameValue);
		});  
		$("#clearMachine").click(function(event) {
			event.stopPropagation(); //阻止事件冒泡到父元素，阻止任何父事件处理程序被执行
			$("#machineArea").html('');
			$("#machineSelect").empty();
			$("#machineSelect").append($("<option>").val('').text('全部'));
		});
		
		$("#machineSelect").change(function() {
			var deviceNo = $(this).val()?$(this).val():$("#cacheMahine").val();
			var dateBegin = $("#dateBegin").val();
			var dateEnd = $("#dateEnd").val();
			if(!dateBegin || !dateEnd) {
				return;
			}
			$.getJSON("${ctx}/lg/exptmachine/lgExptMachineWorkOverview/datePointData", { deviceNo: deviceNo, dateBegin: dateBegin, dateEnd: dateEnd}, function(data) {
				var machineDetail = "";
				$("#machineDetail").html(machineDetail);
				data.forEach(function (item, i) {
					item.mapPointList.forEach(function(subItem) {
						var names = subItem.name.split("<br />");
						var exptDate = "", address = "";

						if(names.length > 1) {
							exptDate = names[0];
							address = names[1];
						}
						machineDetail += '<tr>' 
						+ '<td>' + item.device + '</td>' 
						+ '<td>' + exptDate + '</td>' 
						+ '<td><a href="javascript:void(0);" title="' + address +'" '
						+ 'onclick="openModal(\'' + item.device +'\',\'' + exptDate + '\');" ' 
						+ '>轨迹</a>'
						+ ' </td></tr>';
					});
				$("#machineDetail").html(machineDetail);
			});
		});
	});
		$("#batchNumberSelect").change(function() {
			var pickIds = '';
			$("#batchNumberSelect").find("option:selected").each(function() {
				pickIds += $(this).val() + ',';
			});
			if(!pickIds) {
				$("#machineArea").html('');
				return;
			}
			$.getJSON("${ctx}/lg/exptmachine/lgExptMachineWorkOverview/getMachineCodeByPickId", { pickIds: pickIds}, function(data) {
				if(data) {
            		setHtml(data);
				}
			});
		});
		$('#importForm').on("submit",function() {
			var fileName = $("#uploadFile").val();
			if(fileName && (fileName.indexOf('.xls') > -1 || fileName.indexOf('.xlsx') > -1)){
				$(this).ajaxSubmit({
		            dataType: "json",           
		            beforeSubmit: function(arr,$form,options){
		            },
		            success: function(data,status,xhr,$form){
		            	if (data) {
		            		setHtml(data);
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
			$('#importForm .control-group').eq(0).find('select').change(function() {
				var selectValue = $(this).val()
				var controlDiv = $('#importForm .control-group')
				if (selectValue == 1) {
					$('#batchNumbers').hide()
					controlDiv.eq(1).show()
					controlDiv.eq(2).show()
					$("#machineArea").html('');
				} else if(selectValue == 2) {
					controlDiv.eq(1).hide()
					controlDiv.eq(2).hide()
					$('#batchNumbers').show()
					$("#machineArea").html('');
				}
			})
			
		
	});
	function setHtml(data) {
		var machineListHtml = '';
    	var machineSelectHtml = '<option value="">全部</option>';
    	var cacheMahine = '';
    	data.forEach(function(machine, index) {
    		machineListHtml += '<li><label><input type="checkbox" name="device" value="' + machine + '" checked="checked" />' + machine+  '</label></li>';
    		machineSelectHtml += '<option value="' + machine + '">' + machine + '</option>';
    		cacheMahine += machine + ',';
    	});
    	$("#machineArea").html(machineListHtml);
    	$("#machineSelect").html(machineSelectHtml);
		$("#cacheMahine").val(cacheMahine);
    	$("#importMachineCollapse").collapse('show');
	}
	function openModal(device, exptDate) {
		var src = "${ctx}/lg/exptmachine/lgExptMachineWorkOverview/line" + "?deviceNo=" + device + "&exptDate=" + exptDate; 
		$("#lineFrame").attr("src",src);  
		$('#myModal').modal('show');
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
		    padding-left: 5px;
		    border: none;
		    z-index: 30;
		    overflow-y:hidden;
		    /*background: #f3f3f3*/
		}
		
		.last-container {
		    position: absolute;
		    right: 0;
		    bottom: 0;
		    top: 0;
		    width: 20%;
		    padding: 5px;
		    overflow-y: auto;
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
		#batchNumbers {
			display: none;
			margin-top: 35px;
		}
		.accordion {
		    margin-bottom: 5px;
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
                    <form id="importForm" action="${ctx}/lg/exptmachine/lgExptMachineWorkOverview/import" method="post" enctype="multipart/form-data" style="margin:0;">
                        <input id="uploadFile" name="file" type="file" value="" style="display:none" />
                        <input id="selectedMahine" value="" type="hidden" />
                        <input id="cacheMahine" value="" type="hidden" />
						<div class="control-group" style="margin-top: 5px;">
							<label class="control-label">数据来源：</label>
							<div class="controls">
								<select path="dataType" class="span12">
									<option value="1" selected="selected">从文件导入</options>
									<option value="2">从批次号选择</option>
								</select>
							</div>
						</div>
                        <div class="control-group" style="clear: both;margin-top: 30px;">
                            <div class="controls">
                                <div class="alert alert-info alert-dismissable">
                                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                                    <span id="promptId">
                                    <a href="${ctx}/lg/exptmachine/lgExptMachineWorkOverview/import/template"><strong>下载导入文件模板</strong></a>
                                    </span>
                                </div>
                            </div>
                        </div>

                        <div class="control-group">
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
							<select id="batchNumberSelect" class="span12" multiple="multiple" placeholder="请选择试验机批次号" style="margin-bottom: 18px;">
								<c:forEach items="${fns:getBatchNumber()}" var="item">
									<option value="${item.id}">${item.batchNumber}</option>
								</c:forEach>
							</select>
						</div>
					</div>
                    <div class="control-group" style="clear: both;">
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
                            value="<fmt:formatDate value="${pmK2dataprojectprogress.completionTime}" pattern="yyyy-MM-dd"/>" 
                            onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label">结束试验日期：</label>
                        <div class="controls">
                            <input id="dateEnd" name="dateEnd" type="text" readonly="readonly" maxlength="20" class="Wdate span12" 
                            placeholder="结束试验日期" style="margin-bottom: 0;"
                            value="<fmt:formatDate value="${pmK2dataprojectprogress.completionTime}" pattern="yyyy-MM-dd"/>" 
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
    </div>
    <div class="last-container">
        <div class="container-fluid">
            <div class="row-fluid">
                <div class="span12">
                    <div class="control-group" >
                        <label class="control-label">按试验机筛选：</label>
                        <div class="controls">
                            <select id="machineSelect" class="span12" >
                            	<option value="">全部</option>
                            </select>
                        </div>
                    </div>
                    <div class="control-group" style="clear: both;margin-top: 30px;">
                        <div class="controls">
                            <div class="accordion" id="postionDetailAccordion">
                                <div class="accordion-group">
                                    <div class="accordion-heading">
                                        <a class="accordion-toggle collapsed" data-toggle="collapse" href="#postionDetailCollapse">试验机位置详情</a>
                                    </div>
                                    <div id="postionDetailCollapse" class="accordion-body collapse">
                                        <div class="accordion-inner" style="padding: 0;">
                                            <table class="table table-condensed table-striped table-hover">
                                                <tbody id="machineDetail">
                                                </tbody>
                                            </table>
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
</div>

	<script type="text/javascript">
		window.onresize = function(){
			myChart.resize();
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
		var app = {};
		var myChart;

		var  option = {
           title: {
               text: '试验机器位置',
               subtext: '试验机器位置',
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
		$.get('${ctxStatic}/echarts/mapJson/china.json', function (chinaJson) {
			echarts.registerMap('china', chinaJson);
			myChart = initChart();//echarts.init(rightDom);
			myChart.setOption(option, true);
		});


		function getPoint() {
			var deviceNo = '';//"C30090,C2000B,";
			var dateBegin = $("#dateBegin").val();
			var dateEnd = $("#dateEnd").val();
			$("input[name='device']").each(function(){
				if ('checked' == $(this).attr("checked")) {
					deviceNo += $(this).val()+",";
				}
			});
			if(!deviceNo) {
				showTip('请导入并选择试验机', 'error',1500,0);
				return;
			} 
			if(!dateBegin) {
				showTip('请选择起始试验日期', 'error',1500,0);
				return;
			} 
			if(!dateEnd) {
				showTip('请选择结束试验日期', 'error',1500,0);
				return;
			} 
			$.getJSON("${ctx}/lg/exptmachine/lgExptMachineWorkOverview/datePointData", { deviceNo: deviceNo, dateBegin: dateBegin, dateEnd: dateEnd}, function(data) {
				console.log(JSON.stringify(data));
				var series = [];
				var legendData = [];
				var machineDetail = "";
				$("#machineDetail").html(machineDetail);
				data.forEach(function (item, i) {
					item.mapPointList.forEach(function(subItem) {
						var names = subItem.name.split("<br />");
						var exptDate = "", address = "";

						if(names.length > 1) {
							exptDate = names[0];
							address = names[1];
						}
						machineDetail += '<tr>' 
						+ '<td>' + item.device + '</td>' 
						+ '<td>' + exptDate + '</td>' 
						+ '<td><a href="javascript:void(0);" title="' + address +'" '
						+ 'onclick="openModal(\'' + item.device +'\',\'' + exptDate + '\');" ' 
						+ '>轨迹</a>'
						+ ' </td></tr>';
					});
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
				$("#machineDetail").html(machineDetail);
				option.series = series;
				option.legend.data = legendData;
			    myChart.showLoading();
		        setTimeout(refresh, 500);
            	$("#postionDetailCollapse").collapse('show');
			});
		}
		function refresh(){
			if (myChart && myChart.dispose) {
		        myChart.dispose();
		    }
			myChart = initChart();
			myChart.setOption(option);
		}

	</script>
   </body>
</html>