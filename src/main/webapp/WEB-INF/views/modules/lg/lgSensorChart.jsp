<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
	<head>
		<title>传感器数据图表展示</title>
		<meta name="decorator" content="default"/>
		<link rel="stylesheet" type="text/css" href="${ctxStatic}/timeline/elements-all.css">
			<link rel="stylesheet" type="text/css" href="${ctxStatic}/timeline/colors.css">
				<script src="http://cdn.hcharts.cn/highcharts/highcharts.js"></script>
				<style type="text/css">
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
				#btnSubmit {
					margin-top: 10px;
				}
				</style>
				<script type="text/javascript">
					$(document).ready(function () {
						$("#startDate").val("2016-01-01");
						$("#endDate").val("2016-06-01");
						$("#startDateBottom").val("2016-01-01");
						$("#endDateBottom").val("2016-06-01");
						var height = document.body.scrollHeight;
						$("#divRightTop").height(height * 0.8);
						$("#divRightBottom").height(height * 0.8);
					});
					function page(n, s) {
						$("#pageNo").val(n);
						$("#pageSize").val(s);
						$("#searchForm").submit();
						return false;
					}
				</script>
				<script>
					$(function () {

						var deviceNo = "${deviceNo}";
						$.getJSON("${ctx}/lg/lgMachineEvent/sensorList", {
							deviceNo: deviceNo
						}, function (data) {
							if (data) {
								$("#sensorList").append(data.data.toString());
								draw();
								$("#btnSetOption").click(function () {
									draw();
								});
							}
						});

						$.getJSON("${ctx}/lg/lgMachineEvent/sensorListBottom", {
							deviceNo: deviceNo
						}, function (data) {
							if (data) {
								$("#sensorListBottom").append(data.data.toString());
								drawBottom();
								$("#btnSetOptionBottom").click(function () {
									drawBottom();
								});
							}
						});

					});

					function draw() {
						var sensorMark = new Array();
						$('input[name="sensor"]:checked').each(function () {
							sensorMark.push($(this).val());
						});
						if (sensorMark.length == 0) {
							showTip('请选择需要查看的传感器数据', 'error', 1500, 0);
						} else {
							setChartOption(sensorMark);
						}
					}

					function drawBottom() {
						var sensorMark = new Array();
						$('input[name="sensorBottom"]:checked').each(function () {
							sensorMark.push($(this).val());
						});
						if (sensorMark.length == 0) {
							showTip('请选择需要查看的传感器数据', 'error', 1500, 0);
						}
						if (sensorMark.length != 2) {
							showTip('请选择两个传感器进行数据对比', 'error', 1500, 0);
						} else {
							setOptionBottom(sensorMark);
						}
					}

					function setChartOption(sensorMark) {
						var startDate = $("#startDate").val();
						var endDate = $("#endDate").val();
						$.ajax({
							type: "post",
							url: "${ctx}/lg/lgMachineEvent/getChartJson",
							dataType: "json",
							data: {
								sensorMark: sensorMark,
								startDate: startDate,
								endDate: endDate
							},
							success: function (data) {
								var option = {
									title: {
										text: '传感器数据分析折线图'
									},
									tooltip: {
										trigger: 'axis'
									},
									legend: {
										data: []
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
										data: []
									},
									yAxis: [
										{
											type: 'value'
										}, {
											type: 'value'
										}
									],
									dataZoom: [
										{
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
										}
									],
									series: [
										{
											name: '邮件营销',
											type: 'line',
											stack: '总量',
											data: [
												120,
												132,
												101,
												134,
												90,
												230,
												210
											]
										}
									]
								};
								var myChart = echarts.init(document.getElementById('divRightTop'));
								var series = [];
								var xAxis = [];
								for (var i = 0; i < data.length; i++) {
									if (data[i].sensorName == 'start_times' || data[i].sensorName == 'total_duration' || data[i].sensorName == 'engin_rotate' || data[i].sensorName == 'gps_speed') {
										series.push({name: data[i].sensorName, type: 'line', data: data[i].valueList, yAxisIndex: '1'});
									} else {
										series.push({name: data[i].sensorName, type: 'line', data: data[i].valueList});
									}

								}

								xAxis.push({type: 'category', boundaryGap: false, data: data[0].isoList});
								option.series = series;
								option.xAxis = xAxis;
								myChart.setOption(option);
							}
						});
					};
					function setOptionBottom(sensorMark) {
						var startDate = $("#startDateBottom").val();
						var endDate = $("#endDateBottom").val();
						$.ajax({
							type: "post",
							url: "${ctx}/lg/lgMachineEvent/getChartJsonBottom",
							dataType: "json",
							data: {
								sensorMark: sensorMark,
								startDate: startDate,
								endDate: endDate
							},
							success: function (data) {
								var optionBottom = {
									title: {
										text: '传感数据对比散点图'
									},
									tooltip: {
										trigger: 'axis'
									},
									grid: {
										left: '3%',
										right: '7%',
										bottom: '3%',
										containLabel: true
									},
									toolbox: {
										feature: {
											dataZoom: {},
											brush: {
												type: ['rect', 'polygon', 'clear']
											}
										}
									},
									brush: {},
									legend: {},
									xAxis: [
										{
											type: 'value',
											scale: true,
											axisLabel: {
												formatter: '{value}'
											},
											splitLine: {
												show: false
											}
										}
									],
									yAxis: [
										{
											type: 'value',
											scale: true,
											axisLabel: {
												formatter: '{value}'
											},
											splitLine: {
												show: false
											}
										}
									],
									series: [
										{
											name: '',
											type: 'scatter',
											data: []
										}
									]
								};
								var myChartBottom = echarts.init(document.getElementById('divRightBottom'));
								var xyValueList = data[0].xyValueList;
								optionBottom.series[0].data = xyValueList;
								myChartBottom.setOption(optionBottom);
							}
						});
					};
				</script>
			</head>
			<body>
				<div class="container-fluid">
					<div class="row-fluid">
						<div class="span12">
							<div class="tabbable" id="tabs-490524">
								<ul class="nav nav-tabs">
									<li class="active">
										<a href="#panel-1" data-toggle="tab">折线展示</a>
									</li>
									<li>
										<a href="#panel-2" data-toggle="tab">多传感器组合柱图</a>
									</li>
									<li>
										<a href="#panel-3" data-toggle="tab">月历切片展示</a>
									</li>
									<li>
										<a href="#panel-4" data-toggle="tab">散点图关系展示</a>
									</li>
								</ul>
								<div class="tab-content">
									<div class="tab-pane active" id="panel-1">
										<div>
											<div class=".container-fluid">
												<div class="row-fluid">
													<div class="span2 span-rightborder">
														<ul id="sensorList">
															<li>
																<lable>开始时间：</label>
															</li>
															<li>
																<input id="startDate" type="text" readonly="readonly" maxlength="20" class="input-small Wdate" value="" pattern="yyyy-MM-dd" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
															</li>
															<li>
																<lable>结束时间：</label>
															</li>
															<li>
																<input id="endDate" type="text" readonly="readonly" maxlength="20" class="input-small Wdate" value="" pattern="yyyy-MM-dd" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
															</li>
															<ul></div>
															<div id="divRightTop" class="span10"></div>
														</div>
													</div>
												</div>
											</div>
											<div class="tab-pane" id="panel-2"></div>
											<div class="tab-pane" id="panel-3"></div>
											<div class="tab-pane" id="panel-4">
												<div>
													<div class=".container-fluid">
														<div class="row-fluid">
															<div class="span2 span-rightborder">
																<ul id="sensorListBottom">
																	<li>
																		<lable>开始时间：</label>
																	</li>
																	<li>
																		<input id="startDateBottom" type="text" readonly="readonly" maxlength="20" class="input-small Wdate" value="" pattern="yyyy-MM-dd" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
																	</li>
																	<li>
																		<lable>结束时间：</label>
																	</li>
																	<li>
																		<input id="endDateBottom" type="text" readonly="readonly" maxlength="20" class="input-small Wdate" value="" pattern="yyyy-MM-dd" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
																	</li>
																	<ul></div>
																	<div id="divRightBottom" class="span10"></div>
																</div>
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</body>
						</html>
