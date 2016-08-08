<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>保养记录管理</title>
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
		<li class="active"><a href="${ctx}/lg/lgUpkeephistory/">保养记录列表</a></li>
		<shiro:hasPermission name="lg:lgUpkeephistory:edit"><li><a href="${ctx}/lg/lgUpkeephistory/form">保养记录添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="lgUpkeephistory" action="${ctx}/lg/lgUpkeephistory/" method="post" class="breadcrumb form-search">
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
				<th>保养时间</th>
				<th>保养单号</th>
				<th>保养人</th>
				<th>保养说明</th>
				<shiro:hasPermission name="lg:lgUpkeephistory:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="lgUpkeephistory">
			<tr>
				<td><a href="${ctx}/lg/lgUpkeephistory/form?id=${lgUpkeephistory.id}">
					${lgUpkeephistory.id}
				</a></td>
				<td>
					${lgUpkeephistory.machineId}
				</td>
				<td>
					<fmt:formatDate value="${lgUpkeephistory.upkeepTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${lgUpkeephistory.orderNo}
				</td>
				<td>
					${lgUpkeephistory.orderHuman}
				</td>
				<td>
					${lgUpkeephistory.upkeepInfo}
				</td>
				<shiro:hasPermission name="lg:lgUpkeephistory:edit"><td>
    				<a href="${ctx}/lg/lgUpkeephistory/form?id=${lgUpkeephistory.id}">修改</a>
					<a href="${ctx}/lg/lgUpkeephistory/delete?id=${lgUpkeephistory.id}" onclick="return confirmx('确认要删除该保养记录吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>