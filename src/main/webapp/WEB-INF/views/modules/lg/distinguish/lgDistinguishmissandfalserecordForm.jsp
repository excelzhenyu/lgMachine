<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>机器虚报漏报记录管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
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
		<li><a href="${ctx}/lg/distinguish/lgDistinguishmissandfalserecord/">机器虚报漏报记录列表</a></li>
		<li class="active"><a href="${ctx}/lg/distinguish/lgDistinguishmissandfalserecord/form?id=${lgDistinguishmissandfalserecord.id}">机器虚报漏报记录<shiro:hasPermission name="lg:distinguish:lgDistinguishmissandfalserecord:edit">${not empty lgDistinguishmissandfalserecord.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="lg:distinguish:lgDistinguishmissandfalserecord:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="lgDistinguishmissandfalserecord" action="${ctx}/lg/distinguish/lgDistinguishmissandfalserecord/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">虚漏报配置ID：</label>
			<div class="controls">
				<form:input path="distinguishId" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">类型（1：虚报 2：漏报）：</label>
			<div class="controls">
				<form:input path="solutionType" htmlEscape="false" maxlength="1" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">机器编码：</label>
			<div class="controls">
				<form:input path="deviceNo" htmlEscape="false" maxlength="20" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">机器类型：</label>
			<div class="controls">
				<form:input path="machineType" htmlEscape="false" maxlength="1" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">机器型号：</label>
			<div class="controls">
				<form:input path="productType" htmlEscape="false" maxlength="20" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">订货号：</label>
			<div class="controls">
				<form:input path="orderNo" htmlEscape="false" maxlength="20" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">经销商ID：</label>
			<div class="controls">
				<form:input path="saleId" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">经销商名称：</label>
			<div class="controls">
				<form:input path="saleName" htmlEscape="false" maxlength="20" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">简介：</label>
			<div class="controls">
				<form:input path="info" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">确认日期：</label>
			<div class="controls">
				<input name="confirmDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${lgDistinguishmissandfalserecord.confirmDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">确认状态（1：未处理 2：误报 3：人工已处理）：</label>
			<div class="controls">
				<form:input path="confirmState" htmlEscape="false" maxlength="1" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">处理人：</label>
			<div class="controls">
				<form:input path="operator" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">处理日期：</label>
			<div class="controls">
				<input name="operateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${lgDistinguishmissandfalserecord.operateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">处理说明：</label>
			<div class="controls">
				<form:input path="operateInfo" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="lg:distinguish:lgDistinguishmissandfalserecord:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>