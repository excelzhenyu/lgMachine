<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>机器虚报漏报记录管理</title>
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
		<li class="active"><a href="${ctx}/lg/distinguish/lgDistinguishmissandfalserecord/">机器虚报漏报记录列表</a></li>
		<shiro:hasPermission name="lg:distinguish:lgDistinguishmissandfalserecord:edit"><li><a href="${ctx}/lg/distinguish/lgDistinguishmissandfalserecord/form">机器虚报漏报记录添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="lgDistinguishmissandfalserecord" action="${ctx}/lg/distinguish/lgDistinguishmissandfalserecord/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>订货号：</label>
				<form:input path="orderNo" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>经销商名称：</label>
				<form:input path="saleName" htmlEscape="false" maxlength="20" class="input-medium"/>
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
				<th>机器编码</th>
				<th>机器类型</th>
				<th>机器型号</th>
				<th>订货号</th>
				<th>经销商名称</th>
				<th>确认日期</th>
				<th>确认状态</th>
				<th>处理人</th>
				<th>处理日期</th>
				<th>处理说明</th>
				<shiro:hasPermission name="lg:distinguish:lgDistinguishmissandfalserecord:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="lgDistinguishmissandfalserecord">
			<tr>
				<td><c:if test="${lgDistinguishmissandfalserecord.solutionType=='1'}">
						虚报
						</c:if>
						<c:if test="${lgDistinguishmissandfalserecord.solutionType=='2'}">
						漏报
						</c:if>
				</td>
				<td>
					${lgDistinguishmissandfalserecord.deviceNo}
				</td>
				<td>
					${fns:getDictLabels(lgDistinguishmissandfalserecord.machineType, 'machineType', lgDistinguishmissandfalserecord.machineType)}
				</td>
				<td>
					${lgDistinguishmissandfalserecord.productType}
				</td>
				<td>
					${lgDistinguishmissandfalserecord.orderNo}
				</td>
				<td>
					${lgDistinguishmissandfalserecord.saleName}
				</td>
				<td>
					<fmt:formatDate value="${lgDistinguishmissandfalserecord.confirmDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					<c:if test="${lgDistinguishmissandfalserecord.confirmState=='1'}">
						未处理
					</c:if>
					<c:if test="${lgDistinguishmissandfalserecord.confirmState=='2'}">
						误报
					</c:if>
					<c:if test="${lgDistinguishmissandfalserecord.confirmState=='3'}">
						人工已处理
					</c:if>
				</td>
				<td>
					${lgDistinguishmissandfalserecord.operator}
				</td>
				<td>
					<fmt:formatDate value="${lgDistinguishmissandfalserecord.operateDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${lgDistinguishmissandfalserecord.operateInfo}
				</td>
				<shiro:hasPermission name="lg:distinguish:lgDistinguishmissandfalserecord:edit"><td>
    				<a href="${ctx}/lg/distinguish/lgDistinguishmissandfalserecord/form?id=${lgDistinguishmissandfalserecord.id}">修改</a>
					<a href="${ctx}/lg/distinguish/lgDistinguishmissandfalserecord/delete?id=${lgDistinguishmissandfalserecord.id}" onclick="return confirmx('确认要删除该机器虚报漏报记录吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>