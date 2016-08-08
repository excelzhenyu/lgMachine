<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>虚漏报方案配置详细管理</title>
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
		<li class="active"><a href="${ctx}/lg/distinguish/lgDistinguishmissandfalsedetail/list?distinguishId=${lgDistinguishmissandfalsedetail.distinguishId}">虚漏报方案配置详细列表</a></li>
		<li><a href="${ctx}/lg/distinguish/lgDistinguishmissandfalsedetail/form?distinguishId=${lgDistinguishmissandfalsedetail.distinguishId}">虚漏报方案配置详细添加</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="lgDistinguishmissandfalsedetail" action="${ctx}/lg/distinguish/lgDistinguishmissandfalsedetail/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>筛选条件</th>
				<th>选项</th>
				<th>小数类型值</th>
				<th style="display:none">整数类型值</th>
				<th>有效标识</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="lgDistinguishmissandfalsedetail">
			<tr>
				<td>
					${fns:getDictLabel(lgDistinguishmissandfalsedetail.condition, 'distinguishCondition', '')}
				</td>
				<td>
					${fns:getDictLabel(lgDistinguishmissandfalsedetail.option, 'distinguishOption', 'lgDistinguishmissandfalsedetail.option')}
				</td>
				<td>
					${lgDistinguishmissandfalsedetail.value1}
				</td>
				<td style="display:none">
					${lgDistinguishmissandfalsedetail.value2}
				</td>
				<td>
					${fns:getDictLabels(lgDistinguishmissandfalsedetail.isValid, 'yes_no', lgDistinguishmissandfalsedetail.isValid)}
				</td>
				<td>
    				<a href="${ctx}/lg/distinguish/lgDistinguishmissandfalsedetail/form?id=${lgDistinguishmissandfalsedetail.id}">修改</a>
					<a href="${ctx}/lg/distinguish/lgDistinguishmissandfalsedetail/delete?id=${lgDistinguishmissandfalsedetail.id}" onclick="return confirmx('确认要删除该虚漏报方案配置详细吗？', this.href)">删除</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>