<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>虚漏报方案配置管理</title>
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
		<li class="active"><a href="${ctx}/lg/distinguish/lgDistinguishmissandfalse/">虚漏报方案配置列表</a></li>
		<shiro:hasPermission name="lg:distinguish:lgDistinguishmissandfalse:edit"><li><a href="${ctx}/lg/distinguish/lgDistinguishmissandfalse/form">虚漏报方案配置添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="lgDistinguishmissandfalse" action="${ctx}/lg/distinguish/lgDistinguishmissandfalse/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>方案名称：</label>
				<form:input path="solutionName" htmlEscape="false" maxlength="40" class="input-small"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>类型</th>
				<th>方案名称</th>
				<th>有效标识</th>
				<th>备注</th>
				<shiro:hasPermission name="lg:distinguish:lgDistinguishmissandfalse:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="lgDistinguishmissandfalse">
			<tr>
				<td>
					${fns:getDictLabels(lgDistinguishmissandfalse.solutionType, 'solutionType', lgDistinguishmissandfalse.solutionType)}
				</td>
				<td>
					${lgDistinguishmissandfalse.solutionName}
				</td>
				<td>
					${fns:getDictLabels(lgDistinguishmissandfalse.isValid, 'yes_no', lgDistinguishmissandfalse.isValid)}
				</td>
				<td>
					${lgDistinguishmissandfalse.remark}
				</td>
				<shiro:hasPermission name="lg:distinguish:lgDistinguishmissandfalse:edit"><td>
    				<a href="${ctx}/lg/distinguish/lgDistinguishmissandfalse/form?id=${lgDistinguishmissandfalse.id}">修改</a>
					<a href="${ctx}/lg/distinguish/lgDistinguishmissandfalse/delete?id=${lgDistinguishmissandfalse.id}" onclick="return confirmx('确认要删除该虚漏报方案配置吗？', this.href)">删除</a>
					<a href="${ctx}/lg/distinguish/lgDistinguishmissandfalsedetail/list?distinguishId=${lgDistinguishmissandfalse.id}">详细</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>