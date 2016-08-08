<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>实例物料管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$.validator.addMethod("checkMaterialPin", function() {
				var flag = 1;
				$.ajax({
					type:"POST",  
					url:'${ctx}/lg/instancematerial/lgInstancematerial/checkMaterialPin',
					async:false,
					data:{
						'ledgerid': $("#ledgeridSelect option:selected").val(),
						'materialpin': $("#materialpinInput").val()
					},
					success: function(msg){
					     if(msg == 'false'){
					         flag = 0;
					     }  
					}  
				})
				
				if(flag == 0){
					return false;
				}else{  
					return true;
				}
			}, "此中性物料下的实例物料PIN码已存在")
			
			//$("#name").focus();
			$("#inputForm").validate({
				rules: {
					materialpin: {checkMaterialPin: true}
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
		<li><a href="${ctx}/lg/instancematerial/lgInstancematerial/">实例物料列表</a></li>
		<li class="active"><a href="${ctx}/lg/instancematerial/lgInstancematerial/form?id=${lgInstancematerial.id}">实例物料<shiro:hasPermission name="instancematerial:lgInstancematerial:edit">${not empty lgInstancematerial.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="instancematerial:lgInstancematerial:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="lgInstancematerial" action="${ctx}/lg/instancematerial/lgInstancematerial/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">中性物料ID：</label>
			<div class="controls">
				<form:select id="ledgeridSelect" path="ledgerid" class="input-xlarge required">
					<form:option value="" label=""/>
					<form:options items="${fns:getAllTepMaterialledger()}" itemLabel="materialName" itemValue="id" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">物料PIN码：</label>
			<div class="controls">
				<form:input id="materialpinInput" path="materialpin" htmlEscape="false" maxlength="40" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">批次号：</label>
			<div class="controls">
				<form:input path="materialnum" htmlEscape="false" maxlength="20" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">供应商ID：</label>
			<div class="controls">
				<form:select path="supplierid" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getMachineDimension(6)}" itemLabel="dimensionName" itemValue="id" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">生产日期：</label>
			<div class="controls">
				<input name="producetime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${lgInstancematerial.producetime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">入库日期：</label>
			<div class="controls">
				<input name="inboundtime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${lgInstancematerial.inboundtime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">供应商物料编码：</label>
			<div class="controls">
				<form:input path="suppliermaterialcode" htmlEscape="false" maxlength="40" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">领料日期：</label>
			<div class="controls">
				<input name="recivetime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${lgInstancematerial.recivetime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">报废日期：</label>
			<div class="controls">
				<input name="deadtime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${lgInstancematerial.deadtime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="instancematerial:lgInstancematerial:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>