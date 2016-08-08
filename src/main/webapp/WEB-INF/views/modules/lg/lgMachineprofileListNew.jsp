<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>机器电子档案</title>
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
	<form:form id="searchForm" modelAttribute="lgMachineprofile" action="${ctx}/lg/lgMachineprofile/newList" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>整机编码：</label>
				<form:input  path="code" htmlEscape="false" maxlength="40" class="input-small"/>
			</li>
			<li><label>整机类型：</label>
				<form:select path="machineType" class="input-small">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('machineType')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>型号：</label>
				<form:select path="modelNumber" class="input-small  required" >
					<form:option value="" label=""/>
					<form:options items="${fns:getModelNumber()}"
						itemLabel="productType" itemValue="productType" htmlEscape="false" />
				</form:select>
			</li>
			<li><label>订货号：</label>
				<form:select path="orderNumber" class="input-small  required" >
					<form:option value="" label=""/>
					<form:options items="${fns:getOrderNumberList('')}"
						itemLabel="orderNumber" itemValue="orderNumber" htmlEscape="false" />
				</form:select>
			</li>
			<li><label>销售单位：</label>
				<form:select path="saleUnitId" class="input-small  required" >
					<form:option value="" label=""/>
					<form:options items="${fns:getMachineDimension(3)}"
						itemLabel="dimensionName" itemValue="id" htmlEscape="false" />
				</form:select>
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
				<th>整机PIN码</th>
				<th >整机简称</th>
				<th>型号</th>
				<th>订货号</th>
				<th>整机类型</th>
				<th>合格证号</th>
				<th>整机状态</th>
				<th>生产日期</th>
				<th>销售单位</th>
				<th>建档单位</th>
				<th>客户</th>
				<shiro:hasPermission name="lg:lgMachineprofile:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="lgMachineprofile">
			<tr>
				<td>
					${lgMachineprofile.code}
				</td>
				<td>${lgMachineprofile.pinCode }</td>
				<td><a href="${ctx}/lg/lgMachineprofile/form?id=${lgMachineprofile.id}">
				${lgMachineprofile.shortName }	</a></td>
				<td>${lgMachineprofile.modelNumber }</td>
				<td>${lgMachineprofile.orderNumber }</td>
				<td>${fns:getDictLabels(lgMachineprofile.machineType, 'machineType', lgMachineprofile.machineType)}</td>
				<td>${lgMachineprofile.certificationNumber }</td>
				<td>${fns:getDictLabels(lgMachineprofile.machineStatus, 'machineStatus', lgMachineprofile.machineStatus)}</td>
				<td ><fmt:formatDate value="${lgMachineprofile.productDate}" type="date" dateStyle="long"/></td>
				<td>${lgMachineprofile.saleUnitName}</td>
				<td>${lgMachineprofile.bookBuildingName}</td>
				<td>${lgMachineprofile.customerName }</td>
				<td>
					<a href="${ctx}/lg/lgMachineEvent/list?id=${lgMachineprofile.id}">概览</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>