<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>Quartz Job 生成方案管理</title>
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
		<li class="active"><a href="${ctx}/workcenter/workcenterGenJob/">Quartz Job 生成方案列表</a></li>
		<shiro:hasPermission name="workcenter:workcenterGenJob:edit"><li><a href="${ctx}/workcenter/workcenterGenJob/form">Quartz Job 生成方案添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="workcenterGenJob" action="${ctx}/workcenter/workcenterGenJob/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>生成功能名：</label>
				<form:input path="functionName" htmlEscape="false" maxlength="500" class="input-small"/>
			</li>
			<li><label>Job 名称：</label>
				<form:input path="jobName" htmlEscape="false" maxlength="20" class="input-small"/>
			</li>
			<li><label>Job 组：</label>
				<form:input path="jobGroup" htmlEscape="false" maxlength="30" class="input-small"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>生成功能名</th>
				<th>Job 名</th>
				<th>Job 组</th>
				<th>是否是根节点</th>
				<th>Cron 表达式</th>
				<th>生成功能作者</th>
				<shiro:hasPermission name="workcenter:workcenterGenJob:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="workcenterGenJob">
			<tr>
				<td><a href="${ctx}/workcenter/workcenterGenJob/form?id=${workcenterGenJob.id}">
					${workcenterGenJob.functionName}
				</a></td>
				<td>
					${workcenterGenJob.jobName}
				</td>
				<td>
					${workcenterGenJob.jobGroup}
				</td>
				<td>
					${fns:getDictLabels(workcenterGenJob.rootJob, 'yes_no', workcenter.rootJob)}
				</td>
				<td>
					${workcenterGenJob.cronExpression}
				</td>
				<td>
					${workcenterGenJob.functionAuthor}
				</td>
				<shiro:hasPermission name="workcenter:workcenterGenJob:edit"><td>
    				<a href="${ctx}/workcenter/workcenterGenJob/form?id=${workcenterGenJob.id}">修改</a>
					<a href="${ctx}/workcenter/workcenterGenJob/delete?id=${workcenterGenJob.id}" onclick="return confirmx('确认要删除该Quartz Job 生成方案吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>