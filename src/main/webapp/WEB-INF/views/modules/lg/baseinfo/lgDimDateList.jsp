<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>基础信息-时间维度管理</title>
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
		<li class="active"><a href="${ctx}/lg/baseinfo/lgDimDate/">基础信息-时间维度列表</a></li>
		<shiro:hasPermission name="lg:baseinfo:lgDimDate:edit"><li><a href="${ctx}/lg/baseinfo/lgDimDate/form">基础信息-时间维度添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="lgDimDate" action="${ctx}/lg/baseinfo/lgDimDate/" method="post" class="breadcrumb form-search">
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
				<th>日期</th>
				<th>周ID</th>
				<th>周名称</th>
				<th>月ID</th>
				<th>月名称</th>
				<th>季度ID</th>
				<th>季度名称</th>
				<th>年ID</th>
				<th>年名称</th>
				<shiro:hasPermission name="lg:baseinfo:lgDimDate:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="lgDimDate">
			<tr>
				<td><a href="${ctx}/lg/baseinfo/lgDimDate/form?id=${lgDimDate.id}">
					<fmt:formatDate value="${lgDimDate.day}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</a></td>
				<td>
					${lgDimDate.weekId}
				</td>
				<td>
					${lgDimDate.weekName}
				</td>
				<td>
					${lgDimDate.monthId}
				</td>
				<td>
					${lgDimDate.monthName}
				</td>
				<td>
					${lgDimDate.seasonId}
				</td>
				<td>
					${lgDimDate.seasonName}
				</td>
				<td>
					${lgDimDate.yearId}
				</td>
				<td>
					${lgDimDate.yearName}
				</td>
				<shiro:hasPermission name="lg:baseinfo:lgDimDate:edit"><td>
    				<a href="${ctx}/lg/baseinfo/lgDimDate/form?id=${lgDimDate.id}">修改</a>
					<a href="${ctx}/lg/baseinfo/lgDimDate/delete?id=${lgDimDate.id}" onclick="return confirmx('确认要删除该基础信息-时间维度吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>