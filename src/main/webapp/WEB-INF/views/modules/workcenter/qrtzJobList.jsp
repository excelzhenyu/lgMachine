<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>Quartz Job管理</title>
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
		<li class="active"><a href="${ctx}/workcenter/qrtzJob/">Quartz Job列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="qrtzJob" action="${ctx}/workcenter/qrtzJob/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>Job 名称：</label>
				<form:input path="jobName" htmlEscape="false" maxlength="40" class="input-small"/>
			</li>
			<li><label>Job 组：</label>
				<form:input path="jobGroup" htmlEscape="false" maxlength="40" class="input-small"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>job名称</th>
				<th>job组</th>
				<th>描述</th>
				<th>全类名</th>
				<th>是否持久化</th>
				<th>是否更新数据</th>
				<th>非并发执行</th>
				<th>是否可恢复</th>
				<shiro:hasPermission name="workcenter:qrtzJob:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="qrtzJob">
			<tr>
				<td>${qrtzJob.jobName}</td>
				<td>${qrtzJob.jobGroup}</td>
				<td>${qrtzJob.description}</td>
				<td>${qrtzJob.jobClassName}</td>
				<td>${fns:getDictLabels(qrtzJob.isDurable, 'yes_no', qrtzJob.isDurable)}</td>
				<td>${fns:getDictLabels(qrtzJob.isUpdateData, 'yes_no', qrtzJob.isUpdateData)}</td>
				<td>${fns:getDictLabels(qrtzJob.isNonconcurrent, 'yes_no', qrtzJob.isNonconcurrent)}</td>
				<td>${fns:getDictLabels(qrtzJob.requestsRecovery, 'yes_no', qrtzJob.requestsRecovery)}</td>
				<shiro:hasPermission name="workcenter:qrtzJob:edit"><td>
	   				<a href="${ctx}/workcenter/qrtzJob/triggerJob?jobName=${qrtzJob.jobName}&jobGroup=${qrtzJob.jobGroup}">触发</a>
					<a href="${ctx}/workcenter/qrtzJob/deleteJob?jobName=${qrtzJob.jobName}&jobGroup=${qrtzJob.jobGroup}">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>