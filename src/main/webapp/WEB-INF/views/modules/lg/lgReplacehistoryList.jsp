<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>部件变更记录管理</title>
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
		<li class="active"><a href="${ctx}/lg/lgReplacehistory/">部件变更记录列表</a></li>
		<shiro:hasPermission name="lg:lgReplacehistory:edit"><li><a href="${ctx}/lg/lgReplacehistory/form">部件变更记录添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="lgReplacehistory" action="${ctx}/lg/lgReplacehistory/" method="post" class="breadcrumb form-search">
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
				<th>换件时间</th>
				<th>维修单号</th>
				<th>换件编码</th>
				<th>换件名称</th>
				<th>换件数量</th>
				<shiro:hasPermission name="lg:lgReplacehistory:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="lgReplacehistory">
			<tr>
				<td><a href="${ctx}/lg/lgReplacehistory/form?id=${lgReplacehistory.id}">
					${lgReplacehistory.id}
				</a></td>
				<td>
					${lgReplacehistory.machineId}
				</td>
				<td>
					<fmt:formatDate value="${lgReplacehistory.replaceTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${lgReplacehistory.orderNo}
				</td>
				<td>
					${lgReplacehistory.replaceCode}
				</td>
				<td>
					${lgReplacehistory.replaceName}
				</td>
				<td>
					${lgReplacehistory.replaceNums}
				</td>
				<shiro:hasPermission name="lg:lgReplacehistory:edit"><td>
    				<a href="${ctx}/lg/lgReplacehistory/form?id=${lgReplacehistory.id}">修改</a>
					<a href="${ctx}/lg/lgReplacehistory/delete?id=${lgReplacehistory.id}" onclick="return confirmx('确认要删除该部件变更记录吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>