<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>机器简述管理</title>
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
		<li class="active"><a href="${ctx}/lg/lgMachineinfo/">机器简述列表</a></li>
		<shiro:hasPermission name="lg:lgMachineinfo:edit"><li><a href="${ctx}/lg/lgMachineinfo/form">机器简述添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="lgMachineinfo" action="${ctx}/lg/lgMachineinfo/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>UUID</th>
				<th>整机ID</th>
				<th>机器简述</th>
				<shiro:hasPermission name="lg:lgMachineinfo:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="lgMachineinfo">
			<tr>
				<td><a href="${ctx}/lg/lgMachineinfo/form?id=${lgMachineinfo.id}">
					${lgMachineinfo.id}
				</a></td>
				<td>
					${lgMachineinfo.machineId}
				</td>
				<td>
					${lgMachineinfo.machineInfo}
				</td>
				<shiro:hasPermission name="lg:lgMachineinfo:edit"><td>
    				<a href="${ctx}/lg/lgMachineinfo/form?id=${lgMachineinfo.id}">修改</a>
					<a href="${ctx}/lg/lgMachineinfo/delete?id=${lgMachineinfo.id}" onclick="return confirmx('确认要删除该机器简述吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>