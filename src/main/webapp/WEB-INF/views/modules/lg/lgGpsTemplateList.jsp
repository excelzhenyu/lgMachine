<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>GPS传感器模板管理</title>
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
		<li class="active"><a href="${ctx}/lg/lgGpsTemplate/">控制器模板列表</a></li>
		<shiro:hasPermission name="lg:lgGpsTemplate:edit"><li><a href="${ctx}/lg/lgGpsTemplate/form">控制器模板添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="lgGpsTemplate" action="${ctx}/lg/lgGpsTemplate/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>控制器模板编号：</label>
				<form:input path="gpsTemplateNo" htmlEscape="false" maxlength="40" class="input-small"/>
			</li>
			<li><label>模版名称：</label>
				<form:input path="gpsTemplateName" htmlEscape="false" maxlength="30" class="input-small"/>
			</li>
			<li><label>启用时间：</label>
				<input name="startTime" type="text" readonly="readonly" maxlength="20" class="input-small Wdate"
					value="<fmt:formatDate value="${lgGpsTemplate.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>停用时间：</label>
				<input name="stopTime" type="text" readonly="readonly" maxlength="20" class="input-small Wdate"
					value="<fmt:formatDate value="${lgGpsTemplate.stopTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>控制器模板编号</th>
				<th>模版名称</th>
				<th>停用标志</th>
				<th>创建时间</th>
				<th>停用时间</th>
				<shiro:hasPermission name="lg:lgGpsTemplate:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="lgGpsTemplate">
			<tr>
				<td><a href="${ctx}/lg/lgGpsTemplate/form?id=${lgGpsTemplate.id}">
					${lgGpsTemplate.gpsTemplateNo}
				</a></td>
				<td>
					${lgGpsTemplate.gpsTemplateName}
				</td>
				<td>
					${fns:getDictLabels(lgGpsTemplate.stopMark, 'stopMark', lgGpsTemplate.stopMark)}
			     </td>
				<td>
					<fmt:formatDate value="${lgGpsTemplate.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${lgGpsTemplate.stopTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>

				<shiro:hasPermission name="lg:lgGpsTemplate:edit"><td>
    				<a href="${ctx}/lg/lgGpsTemplate/form?id=${lgGpsTemplate.id}">修改</a>
					<a href="${ctx}/lg/lgGpsTemplate/delete?id=${lgGpsTemplate.id}" onclick="return confirmx('确认要删除控制器模板吗?删除该传感器模板会同时删除模板中传感器信息！', this.href)">删除</a>
					<a href="${ctx}/lg/lgGpsTemplateDetail/list?gpsTemplateId=${lgGpsTemplate.id}&gpsTemplateNo=${lgGpsTemplate.gpsTemplateNo}">详细</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>