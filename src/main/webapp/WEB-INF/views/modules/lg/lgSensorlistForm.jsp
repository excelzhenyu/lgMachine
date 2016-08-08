<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>整机传感器信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			var text = document.getElementById('li2').innerText;
			if(text=="整机传感器信息添加"){
				$("#sensorCode").val('');
				$("#sensorPIN").val('');
				$("#sensorName").val('');
				$("#startTime").val('');
				$("#stopTime").val('');
			}else{
				var submitButton = document.getElementById('btnSubmit');
				submitButton.value="保 存";
				var cancleButton = document.getElementById('btnCancel');
				cancleButton.value="取 消";
				cancleButton.onclick=function(){
					history.go(-1);
				}
			}

			$("#inputForm").validate({
				
				rules:{
					sensorCode:{remote:"${ctx}/lg/lgSensorlist/checkSensorCode?id=${lgSensorlist.id}"},
					sensorPIN:{remote:"${ctx}/lg/lgSensorlist/checkSensorPin?id=${lgSensorlist.id}"}
				},
				messages:{
					sensorCode:{remote:"传感器编号已经存在"},
					sensorPIN:{remote:"传感器PIN码已经存在"}
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
		<li><a href="${ctx}/lg/lgSensorlist/">整机传感器信息列表</a></li>
		<li class="active"><a id="li2" href="${ctx}/lg/lgSensorlist/form?id=${lgSensorlist.id}">整机传感器信息<shiro:hasPermission name="lg:lgSensorlist:edit">${not empty lgSensorlist.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="lg:lgSensorlist:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="lgSensorlist" action="${ctx}/lg/lgSensorlist/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group" style="display:none">
			<label class="control-label">整机ID：</label>
			<div class="controls">
				<form:input path="machineId" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">传感器编码：</label>
			<div class="controls">
				<form:input id="sensorCode" path="sensorCode" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">传感器PIN码：</label>
			<div class="controls">
				<form:input id="sensorPIN" path="sensorPIN" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">传感器名称：</label>
			<div class="controls">
				<form:input id="sensorName" path="sensorName" htmlEscape="false" maxlength="64" class="input-xlarge "/>
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
		<div class="control-group">
			<label class="control-label">启用时间：</label>
			<div class="controls">
				<input id="startTime" name="startTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${lgSensorlist.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">停用时间：</label>
			<div class="controls">
				<input id="stopTime" name="stopTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${lgSensorlist.stopTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="lg:lgSensorlist:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="继续保存" />&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" onclick="location='${ctx}/lg/lgMachineprofile/list'"  value="完 成"></input>
		</div>
	</form:form>
</body>
</html>