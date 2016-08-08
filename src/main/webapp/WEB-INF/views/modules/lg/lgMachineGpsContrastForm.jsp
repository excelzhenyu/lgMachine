<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>整机与GPS设备对照管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				
				rules:{
					deviceNo:{remote:"${ctx}/lg/lgMachineGpsContrast/checkDeviceNo?id=${lgMachineGpsContrast.id}"},
					gpsNo:{required:true,remote:"${ctx}/lg/lgMachineGpsContrast/checkGpsNo?id=${lgMachineGpsContrast.id}"}
				},
				messages:{
					deviceNo:{remote:"此设备已经添加模板，不能重复添加"},
					gpsNo:{remote:"GPS设备编号重复"}
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
		<li><a href="${ctx}/lg/lgMachineGpsContrast/">整机与GPS设备对照列表</a></li>
		<li class="active"><a href="${ctx}/lg/lgMachineGpsContrast/form?id=${lgMachineGpsContrast.id}">整机与GPS设备对照<shiro:hasPermission name="lg:lgMachineGpsContrast:edit">${not empty lgMachineGpsContrast.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="lg:lgMachineGpsContrast:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="lgMachineGpsContrast" action="${ctx}/lg/lgMachineGpsContrast/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">整机编码：</label>
			<div class="controls">
				<form:select path="deviceNo" class="input-medium  required" >
					<form:options items="${fns:getMachineInfo()}"
						itemLabel="code" itemValue="code" htmlEscape="false" />
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">GPS设备编码：</label>
			<div class="controls">
				<form:input path="gpsNo" htmlEscape="false" maxlength="40" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">GPS传感器模板编号：</label>
			<div class="controls">
				<form:select path="gpsTemplateNo" class="input-medium  required" >
					<form:options items="${fns:getGpsTemplateInfo()}"
						itemLabel="gpsTemplateName" itemValue="gpsTemplateNo" htmlEscape="false" />
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">有效标志：</label>
			<div class="controls">
				 <form:radiobuttons path="isValid" items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false" />
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="lg:lgMachineGpsContrast:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>