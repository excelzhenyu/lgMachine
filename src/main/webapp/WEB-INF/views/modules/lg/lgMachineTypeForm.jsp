<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>整机类别管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				rules:{
					orderNumber:{required:true,remote:"${ctx}/lg/lgMachineType/checkOrderNumber?id=${lgMachineType.id}"}
				},
				messages:{
					orderNumber:{remote:"订货号不能重复"}
				},
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
	
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/lg/lgMachineType/">整机类别列表</a></li>
		<li class="active"><a href="${ctx}/lg/lgMachineType/form?id=${lgMachineType.id}">整机类别<shiro:hasPermission name="lg:lgMachineType:edit">${not empty lgMachineType.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="lg:lgMachineType:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="lgMachineType" action="${ctx}/lg/lgMachineType/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">机器类型：</label>
			<div class="controls">
				<form:select path="machineType" class="input-xlarge required">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('machineType')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">产品型号：</label>
			<div class="controls">
				<form:input path="productType" htmlEscape="false" maxlength="20" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">订货号：</label>
			<div class="controls">
				<form:input path="orderNumber" htmlEscape="false" maxlength="20" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="lg:lgMachineType:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>