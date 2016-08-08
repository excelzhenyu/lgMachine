<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>维修记录管理</title>
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
		<li class="active"><a href="${ctx}/lg/lgServicehistory/">维修记录列表</a></li>
		<shiro:hasPermission name="lg:lgServicehistory:edit"><li><a href="${ctx}/lg/lgServicehistory/form">维修记录添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="lgServicehistory" action="${ctx}/lg/lgServicehistory/" method="post" class="breadcrumb form-search">
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
				<th>维修时间</th>
				<th>维修单号</th>
				<th>维修人员</th>
				<th>维修说明</th>
				<shiro:hasPermission name="lg:lgServicehistory:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="lgServicehistory">
			<tr>
				<td><a href="${ctx}/lg/lgServicehistory/form?id=${lgServicehistory.id}">
					${lgServicehistory.id}
				</a></td>
				<td>
					${lgServicehistory.machineId}
				</td>
				<td>
					<fmt:formatDate value="${lgServicehistory.serviceTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${lgServicehistory.orderNo}
				</td>
				<td>
					${lgServicehistory.serviceHuman}
				</td>
				<td>
					${lgServicehistory.serviceInfo}
				</td>
				<shiro:hasPermission name="lg:lgServicehistory:edit"><td>
    				<a href="${ctx}/lg/lgServicehistory/form?id=${lgServicehistory.id}">修改</a>
					<a href="${ctx}/lg/lgServicehistory/delete?id=${lgServicehistory.id}" onclick="return confirmx('确认要删除该维修记录吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>