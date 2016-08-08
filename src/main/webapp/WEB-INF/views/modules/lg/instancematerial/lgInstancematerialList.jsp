<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>实例物料管理</title>
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
		<li class="active"><a href="${ctx}/lg/instancematerial/lgInstancematerial/">实例物料列表</a></li>
		<shiro:hasPermission name="instancematerial:lgInstancematerial:edit"><li><a href="${ctx}/lg/instancematerial/lgInstancematerial/form">实例物料添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="lgInstancematerial" action="${ctx}/lg/instancematerial/lgInstancematerial/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>中性物料ID：</label>
				<form:input path="ledgerid" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>物料PIN码：</label>
				<form:input path="materialpin" htmlEscape="false" maxlength="40" class="input-medium"/>
			</li>
			<li><label>批次号：</label>
				<form:input path="materialnum" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>供应商ID：</label>
				<form:input path="supplierid" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>生产日期：</label>
				<input name="beginProducetime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${lgInstancematerial.beginProducetime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input name="endProducetime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${lgInstancematerial.endProducetime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
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
				<th>中性物料</th>
				<th>物料PIN码</th>
				<th>批次号</th>
				<th>供应商</th>
				<th>生产日期</th>
				<th>入库日期</th>
				<th>供应商物料编码</th>
				<th>领料日期</th>
				<th>报废日期</th>
				<shiro:hasPermission name="instancematerial:lgInstancematerial:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="lgInstancematerial">
			<tr>
				<td><a href="${ctx}/lg/instancematerial/lgInstancematerial/form?id=${lgInstancematerial.id}">
					${lgInstancematerial.ledgerName}
				</a></td>
				<td>
					${lgInstancematerial.materialpin}
				</td>
				<td>
					${lgInstancematerial.materialnum}
				</td>
				<td>
					${lgInstancematerial.supplierName}
				</td>
				<td>
					<fmt:formatDate value="${lgInstancematerial.producetime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${lgInstancematerial.inboundtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${lgInstancematerial.suppliermaterialcode}
				</td>
				<td>
					<fmt:formatDate value="${lgInstancematerial.recivetime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${lgInstancematerial.deadtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="instancematerial:lgInstancematerial:edit"><td>
    				<a href="${ctx}/lg/instancematerial/lgInstancematerial/form?id=${lgInstancematerial.id}">修改</a>
					<a href="${ctx}/lg/instancematerial/lgInstancematerial/delete?id=${lgInstancematerial.id}" onclick="return confirmx('确认要删除该实例物料吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>