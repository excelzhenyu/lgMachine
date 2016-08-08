<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>GPS传感器模板明细管理</title>
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
		<li class="active"><a href="${ctx}/lg/lgGpsTemplateDetail/">控制器模板明细列表</a></li>
		<shiro:hasPermission name="lg:lgGpsTemplateDetail:edit"><li><a href="${ctx}/lg/lgGpsTemplateDetail/form?gpsTemplateId=${lgGpsTemplateDetail.gpsTemplateId}&gpsTemplateNo=${lgGpsTemplateDetail.gpsTemplateNo}">控制器模板明细添加</a></li></shiro:hasPermission>
	</ul>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th style="display:none">控制器模板编号</th>
				<th>传感器标识</th>
				<th>传感器名称</th>
				<th>传感器类型</th>
				<shiro:hasPermission name="lg:lgGpsTemplateDetail:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="lgGpsTemplateDetail">
			<tr>
				<td style="display:none">
					${lgGpsTemplateDetail.gpsTemplateNo}
				</td>
				<td>
					${lgGpsTemplateDetail.sensorMark}
				</td>
				<td>
					${lgGpsTemplateDetail.sensorName}
				</td>
				<td>
					${fns:getDictLabels(lgGpsTemplateDetail.sensorType, 'sensorType', lgGpsTemplateDetail.sensorType)}
				</td>
				<shiro:hasPermission name="lg:lgGpsTemplateDetail:edit"><td>
    				<a href="${ctx}/lg/lgGpsTemplateDetail/form?id=${lgGpsTemplateDetail.id}">修改</a>
					<a href="${ctx}/lg/lgGpsTemplateDetail/delete?id=${lgGpsTemplateDetail.id}" onclick="return confirmx('确认要删除该控制感器模板明细吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>