<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>试验机器选取管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				rules:{
					batchNumber:{required:true,remote:"${ctx}/lg/exptmachine/lgExptMachinePick/checkBatchNumber?id=${lgExptMachinePick.id}"}
				},
				messages:{
					batchNumber:{remote:"批次号不能重复"}
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
		<li><a href="${ctx}/lg/exptmachine/lgExptMachinePick/">试验机器选取列表</a></li>
		<li class="active"><a href="${ctx}/lg/exptmachine/lgExptMachinePick/form?id=${lgExptMachinePick.id}">试验机器选取<shiro:hasPermission name="lg:exptmachine:lgExptMachinePick:edit">${not empty lgExptMachinePick.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="lg:exptmachine:lgExptMachinePick:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="lgExptMachinePick" action="${ctx}/lg/exptmachine/lgExptMachinePick/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">试验批次号：</label>
			<div class="controls">
				<form:input path="batchNumber" htmlEscape="false" maxlength="20" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">试验目的：</label>
			<div class="controls">
				<form:textarea path="exptGoal" htmlEscape="false" rows="2" maxlength="255" class="input-xxlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">试验内容：</label>
			<div class="controls">
				<form:textarea path="exptContent" htmlEscape="false" rows="2" maxlength="255" class="input-xxlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">选取时间：</label>
			<div class="controls">
				<input name="pickTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${lgExptMachinePick.pickTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<!-- 
		<div class="control-group">
			<label class="control-label">选取人：</label>
			<div class="controls">
				<form:input path="pickStaff" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		-->
		<div class="control-group">
			<label class="control-label">有效码：</label>
			<div class="controls">
				<form:radiobuttons path="isEffective" items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false" class="" />
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">试验开始时间：</label>
			<div class="controls">
				<input name="exptBeginTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${lgExptMachinePick.exptBeginTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">试验结束时间：</label>
			<div class="controls">
				<input name="exptEndTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${lgExptMachinePick.exptEndTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="lg:exptmachine:lgExptMachinePick:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>