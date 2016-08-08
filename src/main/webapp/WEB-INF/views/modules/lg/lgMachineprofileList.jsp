<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>整机档案管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
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
		<li class="active"><a href="${ctx}/lg/lgMachineprofile/">整机档案列表</a></li>
		<shiro:hasPermission name="lg:lgMachineprofile:edit"><li><a href="${ctx}/lg/lgMachineprofile/form">整机档案添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="lgMachineprofile" action="${ctx}/lg/lgMachineprofile/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>整机编码：</label>
				<form:input path="code" htmlEscape="false" maxlength="40" class="input-medium"/>
			</li>
			<li><label>整机PIN码：</label>
				<form:input path="pinCode" htmlEscape="false" maxlength="40" class="input-medium"/>
			</li>
			<li><label>整机简称：</label>
				<form:input path="shortName" htmlEscape="false" maxlength="40" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>整机编码</th>
				<th>整机PIN码</th>
				<th >整机简称</th>
				<th>型号</th>
				<th>订货号</th>
				<th>整机类型</th>
				<th>合格证号</th>
				<th>整机状态</th>
				<th>生产日期</th>
				<th>服务档案号</th>
				<th>建档单位</th>
				<th>客户</th>
				<shiro:hasPermission name="lg:lgMachineprofile:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="lgMachineprofile">
			<tr>
				<td>
					${lgMachineprofile.code}
				</td>
				<td>${lgMachineprofile.pinCode }</td>
				<td><a href="${ctx}/lg/lgMachineprofile/form?id=${lgMachineprofile.id}">
				${lgMachineprofile.shortName }	</a></td>
				<td>${lgMachineprofile.modelNumber }</td>
				<td>${lgMachineprofile.orderNumber }</td>
				<td>${fns:getDictLabels(lgMachineprofile.machineType, 'machineType', lgMachineprofile.machineType)}</td>
				<td>${lgMachineprofile.certificationNumber }</td>
				<td>${fns:getDictLabels(lgMachineprofile.machineStatus, 'machineStatus', lgMachineprofile.machineStatus)}</td>
				<td ><fmt:formatDate value="${lgMachineprofile.productDate}" type="date" dateStyle="long"/></td>
				<td>${lgMachineprofile.serviceFileNumber}</td>
				<td>${lgMachineprofile.bookBuildingName}</td>
				<td>${lgMachineprofile.customerName }</td>
				<shiro:hasPermission name="lg:lgMachineprofile:edit"><td>
					<shiro:hasPermission name="lg:lgSensorlist:edit"><a href="${ctx}/lg/lgSensorlist/form?machineId=${lgMachineprofile.id}">添加传感器</a></shiro:hasPermission>
    				<a href="${ctx}/lg/lgMachineprofile/form?id=${lgMachineprofile.id}">修改</a>
					<a href="${ctx}/lg/lgMachineprofile/delete?id=${lgMachineprofile.id}" onclick="return confirmx('确认要删除该整机档案吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>