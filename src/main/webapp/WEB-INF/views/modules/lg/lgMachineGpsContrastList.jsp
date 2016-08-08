<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>整机与GPS设备对照管理</title>
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
		<li class="active"><a href="${ctx}/lg/lgMachineGpsContrast/">装机关系对照列表</a></li>
		<shiro:hasPermission name="lg:lgMachineGpsContrast:edit"><li><a href="${ctx}/lg/lgMachineGpsContrast/form">装机关系对照添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="lgMachineGpsContrast" action="${ctx}/lg/lgMachineGpsContrast/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>整机编码：</label>
				<form:input path="deviceNo" htmlEscape="false" maxlength="40" class="input-small"/>
			</li>
			<li><label>GPS设备编码：</label>
				<form:input path="gpsNo" htmlEscape="false" maxlength="40" class="input-small"/>
			</li>
			<li><label>控制器模板编号：</label>
				<form:input path="gpsTemplateNo" htmlEscape="false" maxlength="64" class="input-small"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>整机编码</th>
				<th>GPS设备编码</th>
				<th>控制器模板编号</th>
				<th>有效标志</th>
				<shiro:hasPermission name="lg:lgMachineGpsContrast:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="lgMachineGpsContrast">
			<tr>
				<td><a href="${ctx}/lg/lgMachineGpsContrast/form?id=${lgMachineGpsContrast.id}">
					${lgMachineGpsContrast.deviceNo}
				</a></td>
				<td>
					${lgMachineGpsContrast.gpsNo}
				</td>
				<td>
					${lgMachineGpsContrast.gpsTemplateNo}
				</td>
				<td>
					${fns:getDictLabels(lgMachineGpsContrast.isValid, 'yes_no', lgMachineGpsContrast.isValid)}
				</td>
				<shiro:hasPermission name="lg:lgMachineGpsContrast:edit"><td>
    				<a href="${ctx}/lg/lgMachineGpsContrast/form?id=${lgMachineGpsContrast.id}">修改</a>
					<a href="${ctx}/lg/lgMachineGpsContrast/delete?id=${lgMachineGpsContrast.id}" onclick="return confirmx('确认要删除该装机关系对照信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>