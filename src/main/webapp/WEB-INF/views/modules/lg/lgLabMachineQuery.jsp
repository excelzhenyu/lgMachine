<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>试验机查询</title>
	<meta name="decorator" content="default"/>
	<script src="echarts/echarts.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出用户数据吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/lg/lgLabMachineQuery/export");
						$("#searchForm").submit();
					}
				},{buttonsFocus:1});
				top.$('.jbox-body .jbox-icon').css('top','55px');
			});

			$("#conExport").click(function (){
				sensorModal();

			});

			$("#btnImport").click(function(){
				$.jBox($("#importBox").html(), {title:"导入数据", buttons:{"关闭":true},
					bottomText:"导入文件不能超过5M，仅允许导入“xls”或“xlsx”格式文件！"});
			});
		});

		function sensorModal(){
			if("${page.pageSize}"==null || "${page.pageSize}"==""){
				$.jBox.confirm("请先查询试验机数据后再进行导出配置");
			}else{
				var deviceNo = $('tbody').find('tr').eq(0).find('td').eq(0).text();
				$.getJSON('${ctx}/lg/lgLabMachineQuery/sensorModal',
				{deviceNo:deviceNo},
					function(data) {
						if (data) {
							$("#contentTable2 tbody").empty();
							$("#contentTable2 tbody").append(data.data.toString());
							$('#sensorModal').modal();
						}
				});
			}

		}

		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="">试验机查询</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="lgLabMachineQuery" action="${ctx}/lg/lgLabMachineQuery/query" method="post" class="breadcrumb form-search"  enctype="multipart/form-data">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>机器大类：</label>
				<form:checkboxes path="machineType" items="${fns:getDictList('machineType')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</li>
			<li><label>机器编号列表：</label>
			<form:input path="xlsFile" value="${lgLabMachineQuery.xlsFile}" type="file"/>
			</li>
		</ul>
		<ul class="ul-form">
			<li><label>试验批次号：</label>
				<form:select path="batchNumber" class="input-small">
					<form:option value="" label=""/>
					<form:options items="${fns:getBatchNumber()}" itemLabel="batchNumber" itemValue="batchNumber" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>试验日期：</label>
				<input name="startDate" type="text" readonly="readonly" maxlength="20" class="input-small Wdate"
					value="<fmt:formatDate value="${lgLabMachineQuery.startDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
				至
				<input name="endDate" type="text" readonly="readonly" maxlength="20" class="input-small Wdate "
					value="<fmt:formatDate value="${lgLabMachineQuery.endDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</li>
		</ul>
		<ul class="ul-form">
			<li>
			<label>每日开机时长大于</label>
			<form:input  path="workTimeDaily" htmlEscape="false" maxlength="40" class="input-small"/>
			小时
			</li>
			<li>
			<label>累计开机时长大于</label>
			<form:input  path="workTimeTotal" htmlEscape="false" maxlength="40" class="input-small"/>
			小时
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"  onclick=""/></li>
			<li class="btns"><input id="btnExport" class="btn btn-primary" type="button" value="生成报告"/></li>
			<li class="btns"><input id="conExport" class="btn btn-primary" type="button" value="导出配置"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>机器编号</th>
				<th>机器型号</th>
				<th>经销商</th>
				<th>工作日期</th>
				<th>工作时长</th>
				<th>1400至1800转速时长</th>
				<th>累计开机时长</th>
				<th>开机次数</th>
				<th>累计开机次数</th>
				<th>工作地域</th>
				<th>当日工作最大时长</th>
				<th>当日工作最小时长</th>
				<th>最高转速</th>
				<th>报警次数</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="lgLabMachineQuery">
			<tr>
				<td>${lgLabMachineQuery.deviceNo }</td>
				<td>${lgLabMachineQuery.modelNumber }</td>
				<td>${lgLabMachineQuery.saleUnit}</td>
				<td ><fmt:formatDate value="${lgLabMachineQuery.workDate}" type="date" dateStyle="long"/></td>
				<td>${lgLabMachineQuery.sliceWorkDuration }</td>
				<td>...</td>
				<td>${lgLabMachineQuery.runDurationTotal }</td>
				<td>${lgLabMachineQuery.runoffCount }</td>
				<td>${lgLabMachineQuery.runOffTotal }</td>
				<td>${lgLabMachineQuery.province }${lgLabMachineQuery.city }</td>
				<td>...</td>
				<td>...</td>
				<td>${lgLabMachineQuery.rotationlSpeedMax }</td>
				<td>${lgLabMachineQuery.alarmCount }</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>

	<!-- 传感器展示Modal -->
	<div id="sensorModal" class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div id="searchForm" class="breadcrumb form-search">
					<input id="pageNo" name="pageNo" type="hidden" value=""/>
					<input id="pageSize" name="pageSize" type="hidden" value=""/>
					<table id="contentTable2" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th>传感器标识：</th>
								<th>传感器名称</th>
							</tr>
						</thead>
						<tbody></tbody>
					</table>
					<input id='sensorExport' class='btn btn-primary' type='button' value='导出传感器数据'/>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
