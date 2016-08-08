<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>机器大事记管理</title>
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
		<li class="active"><a href="${ctx}/lg/lgMachineevent/">机器大事记列表</a></li>
		<shiro:hasPermission name="lg:lgMachineevent:edit"><li><a href="${ctx}/lg/lgMachineevent/form">机器大事记添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="lgMachineevent" action="${ctx}/lg/lgMachineevent/" method="post" class="breadcrumb form-search">
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
				<th>事件时间</th>
				<th>大事记</th>
				<th>是否有外部链接</th>
				<th>访问接口url</th>
				<th>事件描述</th>
				<th>停用标志</th>
				<shiro:hasPermission name="lg:lgMachineevent:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="lgMachineevent">
			<tr>
				<td><a href="${ctx}/lg/lgMachineevent/form?id=${lgMachineevent.id}">
					${lgMachineevent.id}
				</a></td>
				<td>
					${lgMachineevent.machineId}
				</td>
				<td>
					<fmt:formatDate value="${lgMachineevent.eventTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${lgMachineevent.eventInfo}
				</td>
				<td>
					${lgMachineevent.urlOrNot}
				</td>
				<td>
					${lgMachineevent.url}
				</td>
				<td>
					${lgMachineevent.eventDescription}
				</td>
				<td>
					${lgMachineevent.stopRemark}
				</td>
				<shiro:hasPermission name="lg:lgMachineevent:edit"><td>
    				<a href="${ctx}/lg/lgMachineevent/form?id=${lgMachineevent.id}">修改</a>
					<a href="${ctx}/lg/lgMachineevent/delete?id=${lgMachineevent.id}" onclick="return confirmx('确认要删除该机器大事记吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>