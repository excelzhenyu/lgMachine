<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>整机传感器信息管理</title>
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
		<li class="active"><a href="${ctx}/lg/lgSensorlist/">整机传感器信息列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="lgSensorlist" action="${ctx}/lg/lgSensorlist/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>传感器PIN码：</label>
				<form:input path="sensorPIN" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>传感器名称：</label>
				<form:input path="sensorName" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>传感器编码</th>
				<th>传感器PIN码</th>
				<th>传感器名称</th>
				<th>整机名称</th>
				<th>整机PIN码</th>
				<th>停用标志</th>
				<th>传感器类型</th>
				<th>启用时间</th>
				<th>停用时间</th>
				<shiro:hasPermission name="lg:lgSensorlist:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="lgSensorlist">
			<tr>
				<td>
					${lgSensorlist.sensorCode}
				</td>
				<td>
					${lgSensorlist.sensorPIN}
				</td>
				<td>
					<a href="${ctx}/lg/lgSensorlist/form?id=${lgSensorlist.id}">
					${lgSensorlist.sensorName}
				</a>
				</td>
				<td>
					${lgSensorlist.name}
				</td>
				<td>
					${lgSensorlist.pinCode}
				</td>
				<td>
					${fns:getDictLabels(lgSensorlist.stopMark, 'stopMark', lgSensorlist.stopMark)}
				</td>
				<td>
					${fns:getDictLabels(lgSensorlist.sensorType, 'sensorType', lgSensorlist.sensorType)}
				</td>
				<td>
					<fmt:formatDate value="${lgSensorlist.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${lgSensorlist.stopTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="lg:lgSensorlist:edit"><td>
    				<a href="${ctx}/lg/lgSensorlist/form?id=${lgSensorlist.id}">修改</a>
					<a href="${ctx}/lg/lgSensorlist/delete?id=${lgSensorlist.id}" onclick="return confirmx('确认要删除该整机传感器信息吗？此操作不可逆。', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>