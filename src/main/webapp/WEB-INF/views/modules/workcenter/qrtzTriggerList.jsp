<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>Quartz触发器管理</title>
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
		<li class="active"><a href="${ctx}/workcenter/qrtzTrigger/">Quartz触发器列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="qrtzTrigger" action="${ctx}/workcenter/qrtzTrigger/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>触发器名称：</label>
				<form:input path="triggerName" htmlEscape="false" maxlength="40" class="input-small"/>
			</li>
			<li><label>触发器组：</label>
				<form:input path="triggerGroup" htmlEscape="false" maxlength="40" class="input-small"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>触发器名称</th>
				<th>触发器组</th>
				<th>描述</th>
				<th>cron表达式</th>
				<th>job名称</th>
				<th>job组</th>
				<th>下次触发时间</th>
				<th>上次触发时间</th>
				<th>开始时间</th>
				<th>结束时间</th>
				<th>状态</th>
				<shiro:hasPermission name="workcenter:qrtzTrigger:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="qrtzTrigger">
			<tr>
				<td>${qrtzTrigger.triggerName}</td>
				<td>${qrtzTrigger.triggerGroup}</td>
				<td>${qrtzTrigger.description}</td>
				<td>${qrtzTrigger.cronExpression}</td>
				<td>${qrtzTrigger.jobName}</td>
				<td>${qrtzTrigger.jobGroup}</td>
				<td><c:choose>
					<c:when test="${qrtzTrigger.nextFireTime == 0 || qrtzTrigger.nextFireTime == -1}">
						${qrtzTrigger.nextFireTime}
					</c:when>
					<c:otherwise>${fns:formatDateTime(qrtzTrigger.nextFireTime)}</c:otherwise>
				</c:choose></td>
				<td><c:choose>
					<c:when test="${qrtzTrigger.prevFireTime == 0 || qrtzTrigger.prevFireTime == -1}">
						${qrtzTrigger.prevFireTime}
					</c:when>
					<c:otherwise>${fns:formatDateTime(qrtzTrigger.prevFireTime)}</c:otherwise>
				</c:choose></td>
				<td><c:choose>
					<c:when test="${qrtzTrigger.startTime == 0 || qrtzTrigger.startTime == -1}">
						${qrtzTrigger.startTime}
					</c:when>
					<c:otherwise>${fns:formatDateTime(qrtzTrigger.startTime)}</c:otherwise>
				</c:choose></td>
				<td><c:choose>
					<c:when test="${qrtzTrigger.endTime == 0 || qrtzTrigger.endTime == -1}">
						${qrtzTrigger.endTime}
					</c:when>
					<c:otherwise>${fns:formatDateTime(qrtzTrigger.endTime)}</c:otherwise>
				</c:choose></td>
				<td>${qrtzTrigger.triggerState}</td>
				<shiro:hasPermission name="workcenter:qrtzTrigger:edit"><td>
	   				<a href="${ctx}/workcenter/qrtzTrigger/pauseTrigger?triggerName=${qrtzTrigger.triggerName}&triggerGroup=${qrtzTrigger.triggerGroup}">暂停</a>
					<a href="${ctx}/workcenter/qrtzTrigger/resumeTrigger?triggerName=${qrtzTrigger.triggerName}&triggerGroup=${qrtzTrigger.triggerGroup}">恢复</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>