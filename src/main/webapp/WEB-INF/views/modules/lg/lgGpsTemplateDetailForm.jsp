<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>控制器模板明细管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				
				rules:{
					sensorMark:{remote:"${ctx}/lg/lgGpsTemplateDetail/checkSensorMark?id=${lgGpsTemplateDetail.id}&gpsTemplateNo=${lgGpsTemplateDetail.gpsTemplateNo}"}
				},
				messages:{
					sensorMark:{remote:"传感器标识已经存在"}
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
		<li><a href="${ctx}/lg/lgGpsTemplateDetail/">控制器模板明细列表</a></li>
		<li class="active"><a href="${ctx}/lg/lgGpsTemplateDetail/form?id=${lgGpsTemplateDetail.id}">控制器模板明细<shiro:hasPermission name="lg:lgGpsTemplateDetail:edit">${not empty lgGpsTemplateDetail.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="lg:lgGpsTemplateDetail:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="lgGpsTemplateDetail" action="${ctx}/lg/lgGpsTemplateDetail/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group" style="display: none">
			<label class="control-label">控制器模板ID：</label>
			<div class="controls">
				<form:input path="gpsTemplateId" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group" style="display:none">
			<label class="control-label">控制器模板编号：</label>
			<div class="controls">
				<form:input path="gpsTemplateNo" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">传感器标识：</label>
			<div class="controls">
				<form:input path="sensorMark" htmlEscape="false" maxlength="30" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">传感器名称：</label>
			<div class="controls">
				<form:input path="sensorName" htmlEscape="false" maxlength="40" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">传感器类型：</label>
			<div class="controls">
				<form:select path="sensorType" class="input-medium  required" >
					<form:options items="${fns:getDictList('sensorType')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="lg:lgGpsTemplateDetail:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>