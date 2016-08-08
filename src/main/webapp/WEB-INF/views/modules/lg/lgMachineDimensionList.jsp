<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>整机维度管理</title>
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
		<li class="active"><a href="${ctx}/lg/lgMachineDimension/">整机维度列表</a></li>
		<shiro:hasPermission name="lg:lgMachineDimension:edit"><li><a href="${ctx}/lg/lgMachineDimension/form">整机维度添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="lgMachineDimension" action="${ctx}/lg/lgMachineDimension/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>维度类别：</label>
				<form:select path="dimensionType" class="input-small">
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('dimensionType')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>维度编号：</label>
				<form:input path="dimensionCode" htmlEscape="false" maxlength="20" class="input-small"/>
			</li>
			<li><label>维度名称：</label>
				<form:input path="dimensionName" htmlEscape="false" maxlength="120" class="input-small"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>维度类别</th>
				<th>维度编号</th>
				<th>维度名称</th>
				<shiro:hasPermission name="lg:lgMachineDimension:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="lgMachineDimension">
			<tr>
				<td><a href="${ctx}/lg/lgMachineDimension/form?id=${lgMachineDimension.id}">
					${fns:getDictLabel(lgMachineDimension.dimensionType, 'dimensionType', '')}
				</a></td>
				<td>
					${lgMachineDimension.dimensionCode}
				</td>
				<td>
					${lgMachineDimension.dimensionName}
				</td>
				<shiro:hasPermission name="lg:lgMachineDimension:edit"><td>
    				<a href="${ctx}/lg/lgMachineDimension/form?id=${lgMachineDimension.id}">修改</a>
					<a href="${ctx}/lg/lgMachineDimension/delete?id=${lgMachineDimension.id}" onclick="return confirmx('确认要删除该整机维度吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>