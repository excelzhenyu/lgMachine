<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>项目进度管理</title>
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
		<li class="active"><a href="${ctx}/test/pmK2dataprojectprogress/">项目进度列表</a></li>
		<shiro:hasPermission name="test:pmK2dataprojectprogress:edit"><li><a href="${ctx}/test/pmK2dataprojectprogress/form">项目进度添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="pmK2dataprojectprogress" action="${ctx}/test/pmK2dataprojectprogress/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>项目名称：</label>
				<form:select path="projectName" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('project_name')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>功能名称：</label>
				<form:input path="functionName" htmlEscape="false" maxlength="60" class="input-medium"/>
			</li>
			<li><label>开发人：</label>
				<form:select path="developer" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('developer_name')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>项目名称</th>
				<th>功能名称</th>
				<th>开发人</th>
				<th>完成时间</th>
				<th>是否测试</th>
				<th>是否提交GIT</th>
				<th>是否验收</th>
				<th>备注</th>
				<shiro:hasPermission name="test:pmK2dataprojectprogress:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="pmK2dataprojectprogress">
			<tr>
				<td><a href="${ctx}/test/pmK2dataprojectprogress/form?id=${pmK2dataprojectprogress.id}">
					${fns:getDictLabel(pmK2dataprojectprogress.projectName, 'project_name', '')}
				</a></td>
				<td>
					${pmK2dataprojectprogress.functionName}
				</td>
				<td>
					${fns:getDictLabel(pmK2dataprojectprogress.developer, 'developer_name', '')}
				</td>
				<td>
					<fmt:formatDate value="${pmK2dataprojectprogress.completionTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${fns:getDictLabel(pmK2dataprojectprogress.isTest, 'yes_no', '')}
				</td>
				<td>
					${fns:getDictLabel(pmK2dataprojectprogress.isPush, 'yes_no', '')}
				</td>
				<td>
					${fns:getDictLabel(pmK2dataprojectprogress.isAccept, 'yes_no', '')}
				</td>
				<td>
					${pmK2dataprojectprogress.remark}
				</td>
				<shiro:hasPermission name="test:pmK2dataprojectprogress:edit"><td>
    				<a href="${ctx}/test/pmK2dataprojectprogress/form?id=${pmK2dataprojectprogress.id}">修改</a>
					<a href="${ctx}/test/pmK2dataprojectprogress/delete?id=${pmK2dataprojectprogress.id}" onclick="return confirmx('确认要删除该项目进度吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>