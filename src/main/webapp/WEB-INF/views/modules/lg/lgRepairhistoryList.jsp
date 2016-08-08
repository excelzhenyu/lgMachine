<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>报修记录管理</title>
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
		<li class="active"><a href="${ctx}/lg/lgRepairhistory/">报修记录列表</a></li>
		<shiro:hasPermission name="lg:lgRepairhistory:edit"><li><a href="${ctx}/lg/lgRepairhistory/form">报修记录添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="lgRepairhistory" action="${ctx}/lg/lgRepairhistory/" method="post" class="breadcrumb form-search">
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
				<th>报修时间</th>
				<th>报修单号</th>
				<th>报修人</th>
				<th>故障说明</th>
				<th>报修反馈</th>
				<shiro:hasPermission name="lg:lgRepairhistory:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="lgRepairhistory">
			<tr>
				<td><a href="${ctx}/lg/lgRepairhistory/form?id=${lgRepairhistory.id}">
					${lgRepairhistory.id}
				</a></td>
				<td>
					${lgRepairhistory.machineId}
				</td>
				<td>
					<fmt:formatDate value="${lgRepairhistory.repairTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${lgRepairhistory.orderNo}
				</td>
				<td>
					${lgRepairhistory.orderHuman}
				</td>
				<td>
					${lgRepairhistory.troubleInfo}
				</td>
				<td>
					${lgRepairhistory.feedBack}
				</td>
				<shiro:hasPermission name="lg:lgRepairhistory:edit"><td>
    				<a href="${ctx}/lg/lgRepairhistory/form?id=${lgRepairhistory.id}">修改</a>
					<a href="${ctx}/lg/lgRepairhistory/delete?id=${lgRepairhistory.id}" onclick="return confirmx('确认要删除该报修记录吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>