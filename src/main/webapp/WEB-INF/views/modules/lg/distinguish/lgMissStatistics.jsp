<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
	<head>
		<title>虚漏报方案配置详细管理</title>
		<meta name="decorator" content="default"/>
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
				var myChart1 = echarts.init(document.getElementById('chart1'));
				var myChart2 = echarts.init(document.getElementById('chart2'));
				var option1 = {
					title: {
						text: '挖掘机待确认台数',
						x: 'left'
					},
					tooltip: {
						trigger: 'item',
						formatter: "{a} <br/>{b} : {c} ({d}%)"
					},
					legend: {
						orient: 'vertical',
						left: 'right',
						data: []
					},
					series: [
						{
							type: 'pie',
							radius: '55%',
							center: [
								'50%', '60%'
							],
							data: [],
							itemStyle: {
								emphasis: {
									shadowBlur: 10,
									shadowOffsetX: 0,
									shadowColor: 'rgba(0, 0, 0, 0.5)'
								}
							}
						}
					]
				};

				var option2 = {
					title: {
						text: '装载机待确认台数',
						x: 'left'
					},
					tooltip: {
						trigger: 'item',
						formatter: "{a} <br/>{b} : {c} ({d}%)"
					},
					legend: {
						orient: 'vertical',
						left: 'right',
						data: ['LG952', 'LG978']
					},
					series: [
						{
							type: 'pie',
							radius: '55%',
							center: [
								'50%', '60%'
							],
							data: [
								{
									value: 335,
									name: 'LG952'
								}, {
									value: 135,
									name: 'LG978'
								}
							],
							itemStyle: {
								emphasis: {
									shadowBlur: 10,
									shadowOffsetX: 0,
									shadowColor: 'rgba(0, 0, 0, 0.5)'
								}
							}
						}
					]
				};

				if ($("#contentTable1 tbody tr").length > 0) {
					$.getJSON("${ctx}/lg/lgMissAndFalseStatistics/chartData", {solutionType:2}, function (data) {
						var series1 = [];
						var series2 = [];
						if (data) {
							series1.push({
								type: 'pie',
								radius: '55%',
								center: [
									'50%', '60%'
								],
								data: data.wjjNumberData,
								itemStyle: {
									emphasis: {
										shadowBlur: 10,
										shadowOffsetX: 0,
										shadowColor: 'rgba(0, 0, 0, 0.5)'
									}
								}
							});
							series2.push({
								type: 'pie',
								radius: '55%',
								center: [
									'50%', '60%'
								],
								data: data.zzjNumberData,
								itemStyle: {
									emphasis: {
										shadowBlur: 10,
										shadowOffsetX: 0,
										shadowColor: 'rgba(0, 0, 0, 0.5)'
									}
								}
							});

							option1.legend.data = data.wjjModelNumber;
							option1.series = series1;
							option2.legend.data = data.zzjModelNumber;
							option2.series = series2;
							myChart1.setOption(option1);
							myChart2.setOption(option2);
						}
					});
				}
				
				
				var myChart3 = echarts.init(document.getElementById('chart3'));
				var myChart4 = echarts.init(document.getElementById('chart4'));
				var option3 = {
					title: {
						text: '挖掘机待确认台数',
						x: 'left'
					},
					tooltip: {
						trigger: 'item',
						formatter: "{a} <br/>{b} : {c} ({d}%)"
					},
					legend: {
						orient: 'vertical',
						left: 'right',
						data: []
					},
					series: [
						{
							type: 'pie',
							radius: '55%',
							center: [
								'50%', '60%'
							],
							data: [],
							itemStyle: {
								emphasis: {
									shadowBlur: 10,
									shadowOffsetX: 0,
									shadowColor: 'rgba(0, 0, 0, 0.5)'
								}
							}
						}
					]
				};
				var option4 = {
					title: {
						text: '装载机待确认台数',
						x: 'left'
					},
					tooltip: {
						trigger: 'item',
						formatter: "{a} <br/>{b} : {c} ({d}%)"
					},
					legend: {
						orient: 'vertical',
						left: 'right',
						data: []
					},
					series: [
						{
							type: 'pie',
							radius: '55%',
							center: [
								'50%', '60%'
							],
							data: [],
							itemStyle: {
								emphasis: {
									shadowBlur: 10,
									shadowOffsetX: 0,
									shadowColor: 'rgba(0, 0, 0, 0.5)'
								}
							}
						}
					]
				};
				$.getJSON('${ctx}/lg/lgMissAndFalseStatistics/chartData2', {solutionType:1}, function (data) {
					var series3 = [];
					var series4 = [];
					if (data) {
						series3.push({
							type: 'pie',
							radius: '55%',
							center: [
								'50%', '60%'
							],
							data: data.wjjNumberData,
							itemStyle: {
								emphasis: {
									shadowBlur: 10,
									shadowOffsetX: 0,
									shadowColor: 'rgba(0, 0, 0, 0.5)'
								}
							}
						});
						series4.push({
							type: 'pie',
							radius: '55%',
							center: [
								'50%', '60%'
							],
							data: data.zzjNumberData,
							itemStyle: {
								emphasis: {
									shadowBlur: 10,
									shadowOffsetX: 0,
									shadowColor: 'rgba(0, 0, 0, 0.5)'
								}
							}
						});

						option3.legend.data = data.wjjModelNumber;
						option3.series = series3;
						option4.legend.data = data.zzjModelNumber;
						option4.series = series4;
						myChart3.setOption(option3);
						myChart4.setOption(option4);
					}
				});

			});
			function page(n, s) {
				$("#pageNo").val(n);
				$("#pageSize").val(s);
				$("#searchForm").submit();
				return false;
			}
		</script>
		<script type="text/javascript">
			$(function () {
				$("[name='modelNumber']").click(function () {
					var modelNumber = $(this).find('a').text();
					$.getJSON('${ctx}/lg/lgMissAndFalseStatistics/detailModal', {
						modelNumber:modelNumber,solutionType:2
					}, function (data) {
						if (data) {
							$("#contentTable2 tbody").empty();
							$("#contentTable2 tbody").append(data.data.toString());
							$('#detailModal').modal();
						}
					});
				});

				$.getJSON('${ctx}/lg/lgMissAndFalseStatistics/contentTable2', {solutionType:2}, function (data) {
					if (data) {
						$("#contentTable3 tbody").empty();
						$("#contentTable3 tbody").append(data.data.toString());
						$("[name='saleName']").click(function () {
							var saleName = $(this).find('a').text();
							$.getJSON('${ctx}/lg/lgMissAndFalseStatistics/saleModal', {
								saleName: saleName,solutionType:2
							}, function (data) {
								if (data) {
									$("#contentTable4 tbody").empty();
									$("#contentTable4 tbody").append(data.data.toString());
									$('#saleModal').modal();
								}
							});
						});
					}
				});

			

			});
		</script>
	</head>
	<body style="width:100%;height:100%">
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="span2 span-rightborder">
					<form:form id="searchForm" modelAttribute="lgFalseStatistics" action="${ctx}/lg/lgMissAndFalseStatistics/query" method="post" class="form-search .form-horizontal">
						<div class="control-group">
							<div class="controls">
								<p>截止日期：</p>
								<input
									name="startDate"
									type="text"
									readonly="readonly"
									maxlength="20"
									class="input-mysize Wdate span12"
									value="<fmt:formatDate value="${lgFalseStatistics.startDate}" pattern="yyyy-MM-dd"/>"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
							</div>
						</div>
						<div class="control-group" style="display: none">
							<div class="controls">
								<form:input path="solutionType" value='2' htmlEscape="false" maxlength="64" class="input-xlarge "/>
							</div>
						</div>
						<input id="btnSubmit" class="btn btn-primary btn-block" type="submit" value="查询"/>
					</form:form>
				</div>
				<div id="divRight" class="span10">
					<div class="tabbable" id="tabs-490524">
						<ul class="nav nav-tabs">
							<li class="active">
								<a href="#panel-1" data-toggle="tab">型号漏报统计</a>
							</li>
							<li>
								<a id="tab2" href="#panel-2" data-toggle="tab">经销商漏报统计</a>
							</li>
						</ul>
						<div class="tab-content">
							<div class="tab-pane active" id="panel-1">
								<table id="contentTable1" class="table table-striped table-bordered table-condensed">
									<thead>
										<tr>
											<th>机器类别</th>
											<th>机器型号</th>
											<th>虚报台数</th>
											<th>确认台数</th>
											<th>误计台数</th>
											<th>待确认台数</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${wjjListModel}" var="lgFalseStatistics">
											<tr>
												<td>${fns:getDictLabels(lgFalseStatistics.machineType, 'machineType', lgFalseStatistics.machineType)}</td>
												<td name="modelNumber">
													<a>${lgFalseStatistics.modelNumber}</a>
												</td>
												<td>${lgFalseStatistics.falseNumbers }</td>
												<td>${lgFalseStatistics.confirmNumbers }</td>
												<td>${lgFalseStatistics.errorNumbers }</td>
												<td>${lgFalseStatistics.unconfirmNumbers}</td>
											</tr>
										</c:forEach>
										<c:if test="${wjjListModel!=null and wjjListModel.size()!=0}">
											<tr>
												<td>小计</td>
												<td ></td>
												<td>${wjjNumbersModel.wjjFalseNumbers }</td>
												<td>${wjjNumbersModel.wjjConfirmNumbers }</td>
												<td>${wjjNumbersModel.wjjErrorNumbers }</td>
												<td>${wjjNumbersModel.wjjUnconfirmNumbers}</td>
											</tr>
										</c:if>
										<c:forEach items="${zzjListModel}" var="lgFalseStatistics">
											<tr>
												<td>${fns:getDictLabels(lgFalseStatistics.machineType, 'machineType', lgFalseStatistics.machineType)}</td>
												<td name="modelNumber">
													<a>${lgFalseStatistics.modelNumber}</a>
												</td>
												<td>${lgFalseStatistics.falseNumbers }</td>
												<td>${lgFalseStatistics.confirmNumbers }</td>
												<td>${lgFalseStatistics.errorNumbers }</td>
												<td>${lgFalseStatistics.unconfirmNumbers}</td>
											</tr>
										</c:forEach>
										<c:if test="${zzjListModel!=null and zzjListModel.size()!=0}">
											<tr>
												<td>小计</td>
												<td ></td>
												<td>${zzjNumbersModel.zzjFalseNumbers }</td>
												<td>${zzjNumbersModel.zzjConfirmNumbers }</td>
												<td>${zzjNumbersModel.zzjErrorNumbers }</td>
												<td>${zzjNumbersModel.zzjUnconfirmNumbers}</td>
											</tr>
										</c:if>
										<c:if test="${totalNumbersModel!=null}">
											<tr>
												<td>合计</td>
												<td ></td>
												<td>${totalNumbersModel.totalFalseNumbers }</td>
												<td>${totalNumbersModel.totalConfirmNumbers }</td>
												<td>${totalNumbersModel.totalErrorNumbers }</td>
												<td>${totalNumbersModel.totalUnconfirmNumbers}</td>
											</tr>
										</c:if>
									</tbody>
								</table>

								<div id="chart1" class="span5" style="height:350px;"></div>
								<div id="chart2" class="span5" style="height:350px;"></div>
							</div>
							<div class="tab-pane" id="panel-2">
								<table id="contentTable3" class="table table-striped table-bordered table-condensed">
									<thead>
										<tr>
											<th>机器类别</th>
											<th>经销商</th>
											<th>虚报台数</th>
											<th>确认台数</th>
											<th>误计台数</th>
											<th>待确认台数</th>
										</tr>
									</thead>
									<tbody></tbody>
								</table>

								<div id="chart3" class="span5" style="height:350px;"></div>
								<div id="chart4" class="span5" style="height:350px;"></div>
							</div>
						</div>
					</div>
				</div>
				<!-- 传感器展示Modal -->
				<div id="detailModal" class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
					<div class="modal-dialog modal-lg">
						<div class="modal-content">
							<table id="contentTable2" class="table table-striped table-bordered table-condensed">
								<thead>
									<tr>
										<th>整机编号：</th>
										<th>型号：</th>
										<th>经销商：</th>
										<th>订货号：</th>
										<th>状态：</th>
										<th>确认人：</th>
										<th>确认时间：</th>
									</tr>
								</thead>
								<tbody></tbody>
							</table>
						</div>
					</div>
				</div>
				<div id="saleModal" class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
					<div class="modal-dialog modal-lg">
						<div class="modal-content">
							<table id="contentTable4" class="table table-striped table-bordered table-condensed">
								<thead>
									<tr>
										<th>整机编号：</th>
										<th>型号：</th>
										<th>经销商：</th>
										<th>订货号：</th>
										<th>状态：</th>
										<th>确认人：</th>
										<th>确认时间：</th>
									</tr>
								</thead>
								<tbody></tbody>
							</table>
						</div>
					</div>
				</div>
			</body>
		</html>
