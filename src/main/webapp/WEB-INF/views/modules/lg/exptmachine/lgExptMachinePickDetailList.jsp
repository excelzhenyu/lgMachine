<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>试验机器选取明细管理</title>
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
		<li class="active"><a href="${ctx}/lg/exptmachine/lgExptMachinePickDetail/">试验机器选取明细列表</a></li>
		<shiro:hasPermission name="lg:exptmachine:lgExptMachinePickDetail:edit"><li><a href="${ctx}/lg/exptmachine/lgExptMachinePickDetail/form">试验机器选取明细添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="lgExptMachinePickDetail" action="${ctx}/lg/exptmachine/lgExptMachinePickDetail/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>试验机选取ID：</label>
				<form:input path="pickId" htmlEscape="false" maxlength="64" class="input-small"/>
			</li>
			<li><label>整机ID：</label>
				<form:input path="machineId" htmlEscape="false" maxlength="64" class="input-small"/>
			</li>
			<li><label>备注：</label>
				<form:input path="remarks" htmlEscape="false" maxlength="255" class="input-small"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>试验机选取ID</th>
				<th>整机ID</th>
				<th>备注</th>
				<shiro:hasPermission name="lg:exptmachine:lgExptMachinePickDetail:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="lgExptMachinePickDetail">
			<tr>
				<td><a href="${ctx}/lg/exptmachine/lgExptMachinePickDetail/form?id=${lgExptMachinePickDetail.id}">
					${lgExptMachinePickDetail.pickId}
				</a></td>
				<td>
					${lgExptMachinePickDetail.machineId}
				</td>
				<td>
					${lgExptMachinePickDetail.remarks}
				</td>
				<shiro:hasPermission name="lg:exptmachine:lgExptMachinePickDetail:edit"><td>
    				<a href="${ctx}/lg/exptmachine/lgExptMachinePickDetail/form?id=${lgExptMachinePickDetail.id}">修改</a>
					<a href="${ctx}/lg/exptmachine/lgExptMachinePickDetail/delete?id=${lgExptMachinePickDetail.id}" onclick="return confirmx('确认要删除该试验机器选取明细吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>