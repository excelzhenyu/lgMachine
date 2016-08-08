<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>试验机工作时长分布</title>
	<meta name="decorator" content="default"/>
	<script src="echarts/echarts.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnImport").click(function(){
				$.jBox($("#importBox").html(), {title:"导入数据", buttons:{"关闭":true}, 
					bottomText:"导入文件不能超过5M，仅允许导入“xls”或“xlsx”格式文件！"});
			});
			
			var oto4Num =${lgWorktimeDistribution.oto4Num};
			var fourToEight=${lgWorktimeDistribution.fourToEight};
			var eightToTwelve=${lgWorktimeDistribution.eightToTwelve};
			var twelveToSixteen=${lgWorktimeDistribution.twelveToSixteen};
			var sixteenToTwenty=${lgWorktimeDistribution.sixteenToTwenty};
			var twentyTo24=${lgWorktimeDistribution.twentyTo24};
			// 基于准备好的dom，初始化echarts实例
	        var myChart = echarts.init(document.getElementById('chart'));
		
	        // 指定图表的配置项和数据
	        var option = {
	            title: {
	                text: '试验机工作时长分布'
	            },
	            tooltip: {},
	            legend: {
	                data:['机器数量']
	            },
	            xAxis: {
	                data: ["0-4小时","4-8小时","8-12小时","12-16小时","16-20小时","20-24小时"]
	            },
	            yAxis: {},
	            series: [{
	                name: '数量',
	                type: 'bar',
	                data: [oto4Num,fourToEight,eightToTwelve,twelveToSixteen,sixteenToTwenty,twentyTo24]
	            }]
	        };

	        // 使用刚指定的配置项和数据显示图表。
	        myChart.setOption(option);
		});
	
		
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="">试验机工作时长分布</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="lgWorktimeDistribution" action="${ctx}/lg/lgWorktimeDistribution/query" method="post" class="breadcrumb form-search" enctype="multipart/form-data" >
		<ul class="ul-form">
			<li><label>机器大类：</label>
				<form:checkboxes path="machineType" items="${fns:getDictList('machineType')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</li>
			<li><label>机器编号列表：</label>
				<input id="xlsFile" name="xlsFile" type="file" class="input-small"/>
			</li>
			<li><label>开始时间：</label>
				<input name="startDate" type="text" readonly="readonly" maxlength="20" class="input-small Wdate "
					value="<fmt:formatDate value="${lgWorktimeDistribution.startDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</li>
			<li><label>结束时间：</label>
				<input name="endDate" type="text" readonly="readonly" maxlength="20" class="input-small Wdate "
					value="<fmt:formatDate value="${lgWorktimeDistribution.endDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"  onclick=""/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>试验日期</th>
				<th>0-4小时</th>
				<th>4-8小时</th>
				<th>8-12小时</th>
				<th>12-16小时</th>
				<th>16-20小时</th>
				<th>20-24小时</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>
				<fmt:formatDate value="${lgWorktimeDistribution.startDate}" pattern="yyyy年MM月dd日" /> 
				至
				<fmt:formatDate value="${lgWorktimeDistribution.endDate}" pattern="yyyy年MM月dd日" /> 
				<td>${lgWorktimeDistribution.oto4Num}</td>
				<td>${lgWorktimeDistribution.fourToEight}</td>
				<td>${lgWorktimeDistribution.eightToTwelve}</td>
				<td>${lgWorktimeDistribution.twelveToSixteen}</td>
				<td>${lgWorktimeDistribution.sixteenToTwenty}</td>
				<td>${lgWorktimeDistribution.twentyTo24}</td>
			</tr>
		</tbody>
	</table>
	<div>
	<div class="span8" id="chart" style="width: 800px;height: 500px;">
	</div>
	<div class="span4">
			<c:forEach items="${lgWorktimeDistribution.machineNo}" var="no" varStatus="p">
           			 <ul>  
					<li><span>${no}</span></li>
         		   </ul>  
         	</c:forEach>
		</div>
	</div>
</body>
</html>