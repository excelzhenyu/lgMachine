<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>整机类别管理</title>
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
		<li class="active"><a href="${ctx}/lg/lgMachineType/">主机型号列表</a></li>
		<shiro:hasPermission name="lg:lgMachineType:edit"><li><a href="${ctx}/lg/lgMachineType/form">主机型号添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="lgMachineType" action="${ctx}/lg/lgMachineType/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>机器类型：</label>
				<form:select path="machineType" class="input-small">
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('machineType')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>产品型号：</label>
				<form:input path="productType" htmlEscape="false" maxlength="20" class="input-small"/>
			</li>
			<li><label>订货号：</label>
				<form:input path="orderNumber" htmlEscape="false" maxlength="20" class="input-small"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>机器类型</th>
				<th>产品型号</th>
				<th>订货号</th>
				<shiro:hasPermission name="lg:lgMachineType:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="lgMachineType">
			<tr>
				<td><a href="${ctx}/lg/lgMachineType/form?id=${lgMachineType.id}">
					${fns:getDictLabel(lgMachineType.machineType, 'machineType', '')}
				</a></td>
				<td>
					${lgMachineType.productType}
				</td>
				<td>
					${lgMachineType.orderNumber}
				</td>
				<shiro:hasPermission name="lg:lgMachineType:edit"><td>
    				<a href="${ctx}/lg/lgMachineType/form?id=${lgMachineType.id}">修改</a>
					<a href="${ctx}/lg/lgMachineType/delete?id=${lgMachineType.id}" onclick="return confirmx('确认要删除该整机类别吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>