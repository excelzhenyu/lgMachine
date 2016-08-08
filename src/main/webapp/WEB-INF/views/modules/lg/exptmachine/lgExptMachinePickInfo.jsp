<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>试验机器选取管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {	
			
			$("#btnImport").click(function(){
				$.jBox($("#importBox").html(), {title:"导入数据", buttons:{"关闭":true}, 
					bottomText:"导入文件不能超过5M，仅允许导入“xls”或“xlsx”格式文件！"});
			});
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
	<style>    
		html,body{
		    height: 100%;
		    width: 100%;
		}
		.controls {
			margin-top: 4px;
		}
	</style>
</head>
<body>
	<div id="importBox" class="hide">
		<form id="importForm" action="${ctx}/lg/exptmachine/lgExptMachinePick/import?id=${lgExptMachinePick.id}" method="post" enctype="multipart/form-data"
			class="form-search" style="padding-left:20px;text-align:center;" onsubmit="loading('正在导入，请稍等...');"><br/>
			<input id="uploadFile" name="file" type="file" value=""  style="width:330px"/><br/><br/>　　
			<input id="btnImportSubmit" class="btn btn-primary" type="submit" value="导 入"/>
			<a href="${ctx}/lg/exptmachine/lgExptMachinePick/import/template?id=${lgExptMachinePick.id}">下载模板</a>
		</form>
	</div>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/lg/exptmachine/lgExptMachinePick/">试验机器选取列表</a></li>
		<li  class="active"><a href="${ctx}/lg/exptmachine/lgExptMachinePick/info?id=${lgExptMachinePick.id}">试验机器选取明细</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="lgExptMachinePick" action="#" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="span6">
					<div class="control-group">
						<label class="control-label">试验批次号：</label>
						<div class="controls">
							${lgExptMachinePick.batchNumber}
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">试验开始时间：</label>
						<div class="controls">
							<fmt:formatDate value="${lgExptMachinePick.exptBeginTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
						</div>
					</div>
				</div>
				
				<div class="span6">
					<div class="control-group">
						<label class="control-label">选取人：</label>
						<div class="controls">
							${lgExptMachinePick.pickStaffName}
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">试验结束时间：</label>
						<div class="controls">
						<fmt:formatDate value="${lgExptMachinePick.exptEndTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
						</div>
					</div>	
				</div>
			</div>
			<div class="row-fluid">
				<div class="span12">
					<div class="control-group">
						<label class="control-label">试验目的：</label>
						<div class="controls">
							<form:textarea path="exptGoal" htmlEscape="false" rows="2" maxlength="255" class="input-xxlarge" readonly="true"/>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">试验内容：</label>
						<div class="controls">
							<form:textarea path="exptContent" htmlEscape="false" rows="2" maxlength="255" class="input-xxlarge" readonly="true"/>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">选取试验机:</label>
						<div class="controls">
							<a href="${ctx}/lg/exptmachine/lgExptMachinePick/infoPop?id=${lgExptMachinePick.id}" role="button" class="btn btn-primary">选取试验机</a> 
							<input id="btnImport" class="btn btn-primary" type="button" value="导入试验机"/></li>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">已选取试验机:</label>
						<div class="controls">
						
						<div class="row-fluid">
							<div class="span12">
							<table id="showTable" class="table table-striped table-bordered table-condensed table-hover">
							<thead>
								<tr>
									<th >整机编码</th>
									<th >型号</th>
									<th >订货号</th>
									<th >建档单位</th>
									<th >销售区域</th>
									<th >销售单位</th>
									<th >客户</th>
									<shiro:hasPermission name="lg:exptmachine:lgExptMachinePick:edit"><th width="6%">操作</th></shiro:hasPermission>
								</tr>
							</thead>
								<tbody>
									<c:forEach items="${page.list}" var="lgMachineprofile">
										<tr>
											<td >${lgMachineprofile.pinCode }</td>
											<td >${lgMachineprofile.modelNumber }</td>
											<td >${lgMachineprofile.orderNumber }</td>
											<td >${lgMachineprofile.bookBuildingId }</td>
											<td >${lgMachineprofile.saleAreaId }</td>
											<td >${lgMachineprofile.saleUnitId }</td>
											<td >${lgMachineprofile.customerId }</td>
											<shiro:hasPermission name="lg:exptmachine:lgExptMachinePick:edit"><td width="6%">
												<a href="${ctx}/lg/exptmachine/lgExptMachinePickDetail/deleteMachine?pickId=${lgExptMachinePick.id}&machineId=${lgMachineprofile.id}" onclick="return confirmx('确认要删除该试验机器选取明细吗？', this.href)">删除</a>
											</td></shiro:hasPermission>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
						</div>
							<div class="pagination">${page}</div>
						</div>
					</div>
				</div>
			</div>
		</div>	
	</form:form>
</body>
</html>