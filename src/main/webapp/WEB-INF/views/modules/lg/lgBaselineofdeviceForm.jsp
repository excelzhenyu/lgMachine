<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>二级传感器分析管理</title>
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
		<li><a href="${ctx}/lg/lgBaselineofdevice/">二级传感器分析列表</a></li>
		<li class="active"><a href="${ctx}/lg/lgBaselineofdevice/form?id=${lgBaselineofdevice.id}">二级传感器分析<shiro:hasPermission name="lg:lgBaselineofdevice:edit">${not empty lgBaselineofdevice.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="lg:lgBaselineofdevice:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="lgBaselineofdevice" action="${ctx}/lg/lgBaselineofdevice/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">设备编码：</label>
			<div class="controls">
				<form:input path="deviceNo" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">sensorno：</label>
			<div class="controls">
				<form:input path="sensorNo" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">设备开机切片数目：</label>
			<div class="controls">
				<form:input path="sliceTotal" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">该传感器有回传值切片数目：</label>
			<div class="controls">
				<form:input path="sliceCount" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">平均值二级指标5分位（模拟量）：</label>
			<div class="controls">
				<form:input path="average5" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">平均值二级指标25分位（模拟量）：</label>
			<div class="controls">
				<form:input path="average25" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">平均值二级指标中分位（模拟量）：</label>
			<div class="controls">
				<form:input path="average50" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">平均值二级指标75分位（模拟量）：</label>
			<div class="controls">
				<form:input path="average75" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">平均值二级指标95分位（模拟量）：</label>
			<div class="controls">
				<form:input path="average95" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">平均值二级指标平均值（模拟量）：</label>
			<div class="controls">
				<form:input path="averageAvg" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">平均值二级指标最大值（模拟量）：</label>
			<div class="controls">
				<form:input path="averageMax" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">平均值二级指标最小值（模拟量）：</label>
			<div class="controls">
				<form:input path="averageMin" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">平均值二级指标标准差（模拟量）：</label>
			<div class="controls">
				<form:input path="averageStd" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">最大值二级指标5分位（模拟量）：</label>
			<div class="controls">
				<form:input path="max5" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">最大值二级指标25分位（模拟量）：</label>
			<div class="controls">
				<form:input path="max25" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">最大值二级指标中分位（模拟量）：</label>
			<div class="controls">
				<form:input path="max50" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">最大值二级指标75分位（模拟量）：</label>
			<div class="controls">
				<form:input path="max75" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">最大值二级指标95分位（模拟量）：</label>
			<div class="controls">
				<form:input path="max95" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">最大值二级指标平均值（模拟量）：</label>
			<div class="controls">
				<form:input path="maxAvg" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">最大值二级指标最大值（模拟量）：</label>
			<div class="controls">
				<form:input path="maxMax" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">最大值二级指标最小值（模拟量）：</label>
			<div class="controls">
				<form:input path="maxMin" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">最大值二级指标标准差（模拟量）：</label>
			<div class="controls">
				<form:input path="maxStd" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">最小值二级指标5分位（模拟量）：</label>
			<div class="controls">
				<form:input path="min5" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">最小值二级指标25分位（模拟量）：</label>
			<div class="controls">
				<form:input path="min25" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">最小值二级指标中分位（模拟量）：</label>
			<div class="controls">
				<form:input path="min50" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">最小值二级指标75分位（模拟量）：</label>
			<div class="controls">
				<form:input path="min75" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">最小值二级指标95分位（模拟量）：</label>
			<div class="controls">
				<form:input path="min95" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">最小值二级指标平均值（模拟量）：</label>
			<div class="controls">
				<form:input path="minAvg" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">最小值二级指标最大值（模拟量）：</label>
			<div class="controls">
				<form:input path="minMax" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">最小值二级指标最小值（模拟量）：</label>
			<div class="controls">
				<form:input path="minMin" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">最小值二级指标标准差（模拟量）：</label>
			<div class="controls">
				<form:input path="minStd" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">取值切换次数二级指标5分位（开关量）：</label>
			<div class="controls">
				<form:input path="countOfChange5" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">取值切换次数二级指标25分位（开关量）：</label>
			<div class="controls">
				<form:input path="countOfChange25" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">取值切换次数二级指标中分位（开关量）：</label>
			<div class="controls">
				<form:input path="countOfChange50" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">取值切换次数二级指标75分位（开关量）：</label>
			<div class="controls">
				<form:input path="countOfChange75" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">取值切换次数二级指标95分位（开关量）：</label>
			<div class="controls">
				<form:input path="countOfChange95" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">取值切换次数二级指标平均值（开关量）：</label>
			<div class="controls">
				<form:input path="countOfChangeAvg" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">取值切换次数二级指标最小值（开关量）：</label>
			<div class="controls">
				<form:input path="countOfChangeMin" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">取值切换次数二级指标最大值（开关量）：</label>
			<div class="controls">
				<form:input path="countOfChangeMax" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">取值切换次数二级指标标准差（开关量）：</label>
			<div class="controls">
				<form:input path="countOfChangeStd" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">高电平计数二级指标5分位（开关量）：</label>
			<div class="controls">
				<form:input path="countOfOne5" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">高电平计数二级指标25分位（开关量）：</label>
			<div class="controls">
				<form:input path="countOfOne25" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">高电平计数二级指标中分位（开关量）：</label>
			<div class="controls">
				<form:input path="countOfOne50" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">高电平计数二级指标75分位（开关量）：</label>
			<div class="controls">
				<form:input path="countOfOne75" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">高电平计数二级指标95分位（开关量）：</label>
			<div class="controls">
				<form:input path="countOfOne95" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">高电平计数二级指标平均值（开关量）：</label>
			<div class="controls">
				<form:input path="countOfOneAvg" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">高电平计数二级指标最大值（开关量）：</label>
			<div class="controls">
				<form:input path="countOfOneMax" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">高电平计数二级指标最小值（开关量）：</label>
			<div class="controls">
				<form:input path="countOfOneMin" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">高电平计数二级指标标准差（开关量）：</label>
			<div class="controls">
				<form:input path="countOfOneStd" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">低电平计数二级指标5分位（开关量）：</label>
			<div class="controls">
				<form:input path="countOfZero5" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">低电平计数二级指标25分位（开关量）：</label>
			<div class="controls">
				<form:input path="countOfZero25" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">低电平计数二级指标中分位（开关量）：</label>
			<div class="controls">
				<form:input path="countOfZero50" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">低电平计数二级指标75分位（开关量）：</label>
			<div class="controls">
				<form:input path="countOfZero75" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">低电平计数二级指标95分位（开关量）：</label>
			<div class="controls">
				<form:input path="countOfZero95" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">低电平计数二级指标平均值（开关量）：</label>
			<div class="controls">
				<form:input path="countOfZeroAvg" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">低电平计数二级指标最大值（开关量）：</label>
			<div class="controls">
				<form:input path="countOfZeroMax" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">低电平计数二级指标最小值（开关量）：</label>
			<div class="controls">
				<form:input path="countOfZeroMin" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">低电平计数二级指标标准差（开关量）：</label>
			<div class="controls">
				<form:input path="countOfZeroStd" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">数据点计数二级指标5分位（共用）：</label>
			<div class="controls">
				<form:input path="countOfSent5" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">数据点计数二级指标25分位（共用）：</label>
			<div class="controls">
				<form:input path="countOfSent25" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">数据点计数二级指标中分位（共用）：</label>
			<div class="controls">
				<form:input path="countOfSent50" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">数据点计数二级指标75分位（共用）：</label>
			<div class="controls">
				<form:input path="countOfSent75" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">数据点计数二级指标95分位（共用）：</label>
			<div class="controls">
				<form:input path="countOfSent95" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">数据点计数二级指标平均值（共用）：</label>
			<div class="controls">
				<form:input path="countOfSentAvg" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">数据点计数二级指标最大值（共用）：</label>
			<div class="controls">
				<form:input path="countOfSentMax" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">数据点计数二级指标最小值（共用）：</label>
			<div class="controls">
				<form:input path="countOfSentMin" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">数据点计数二级指标标准值（共用）：</label>
			<div class="controls">
				<form:input path="countOfSentStd" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">不同取值二级指标5分位（共用）：</label>
			<div class="controls">
				<form:input path="countOfSentDistinctParavalue5" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">不同取值二级指标25分位（共用）：</label>
			<div class="controls">
				<form:input path="countOfSentDistinctParavalue25" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">不同取值二级指标中分位（共用）：</label>
			<div class="controls">
				<form:input path="countOfSentDistinctParavalue50" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">不同取值二级指标75分位（共用）：</label>
			<div class="controls">
				<form:input path="countOfSentDistinctParavalue75" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">不同取值二级指标95分位（共用）：</label>
			<div class="controls">
				<form:input path="countOfSentDistinctParavalue95" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">不同取值二级指标平均值（共用）：</label>
			<div class="controls">
				<form:input path="countOfSentDistinctParavalueAvg" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">不同取值二级指标最大值（共用）：</label>
			<div class="controls">
				<form:input path="countOfSentDistinctParavalueMax" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">不同取值二级指标最小值（共用）：</label>
			<div class="controls">
				<form:input path="countOfSentDistinctParavalueMin" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">不同取值二级指标标准差（共用）：</label>
			<div class="controls">
				<form:input path="countOfSentDistinctParavalueStd" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">发送频率二级指标5分位（共用）：</label>
			<div class="controls">
				<form:input path="sentFrequence5" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">发送频率二级指标5分位（共用）：</label>
			<div class="controls">
				<form:input path="sentFrequence25" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">发送频率二级指标中分位（共用）：</label>
			<div class="controls">
				<form:input path="sentFrequence50" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">发送频率二级指标75分位（共用）：</label>
			<div class="controls">
				<form:input path="sentFrequence75" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">发送频率二级指标95分位（共用）：</label>
			<div class="controls">
				<form:input path="sentFrequence95" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">sent_frequence_avg：</label>
			<div class="controls">
				<form:input path="sentFrequenceAvg" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">发送频率二级指标最大值（共用）：</label>
			<div class="controls">
				<form:input path="sentFrequenceMax" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">发送频率二级指标最小值（共用）：</label>
			<div class="controls">
				<form:input path="sentFrequenceMin" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">发送频率二级指标标准差（共用）：</label>
			<div class="controls">
				<form:input path="sentFrequenceStd" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">梯度二级指标5分位（共用）：</label>
			<div class="controls">
				<form:input path="grad5" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">梯度二级指标25分位（共用）：</label>
			<div class="controls">
				<form:input path="grad25" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">梯度二级指标中分位（共用）：</label>
			<div class="controls">
				<form:input path="grad50" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">梯度二级指标75分位（共用）：</label>
			<div class="controls">
				<form:input path="grad75" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">梯度二级指标95分位（共用）：</label>
			<div class="controls">
				<form:input path="grad95" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">梯度二级指标平均值（共用）：</label>
			<div class="controls">
				<form:input path="gradAvg" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">梯度二级指标最大值（共用）：</label>
			<div class="controls">
				<form:input path="gradMax" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">梯度二级指标最小值（共用）：</label>
			<div class="controls">
				<form:input path="gradMin" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">梯度二级指标标准差（共用）：</label>
			<div class="controls">
				<form:input path="gradStd" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">标准差二级指标5分位（共用）：</label>
			<div class="controls">
				<form:input path="standard5" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">标准差二级指标25分位（共用）：</label>
			<div class="controls">
				<form:input path="standard25" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">标准差二级指标中分位（共用）：</label>
			<div class="controls">
				<form:input path="standard50" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">标准差二级指标75分位（共用）：</label>
			<div class="controls">
				<form:input path="standard75" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">标准差二级指标95分位（共用）：</label>
			<div class="controls">
				<form:input path="standard95" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">标准差二级指标平均值（共用）：</label>
			<div class="controls">
				<form:input path="standardAvg" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">标准差二级指标最大值（共用）：</label>
			<div class="controls">
				<form:input path="standardMax" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">标准差二级指标最小值（共用）：</label>
			<div class="controls">
				<form:input path="standardMin" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">标准差二级指标标准差（共用）：</label>
			<div class="controls">
				<form:input path="standardStd" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">方差二级指标5分位（共用）：</label>
			<div class="controls">
				<form:input path="variance5" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">方差二级指标25分位（共用）：</label>
			<div class="controls">
				<form:input path="variance25" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">方差二级指标中分位（共用）：</label>
			<div class="controls">
				<form:input path="variance50" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">方差二级指标75分位（共用）：</label>
			<div class="controls">
				<form:input path="variance75" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">方差二级指标95分位（共用）：</label>
			<div class="controls">
				<form:input path="variance95" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">方差二级指标平均值（共用）：</label>
			<div class="controls">
				<form:input path="varianceAvg" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">方差二级指标最大值（共用）：</label>
			<div class="controls">
				<form:input path="varianceMax" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">方差二级指标最小值（共用）：</label>
			<div class="controls">
				<form:input path="varianceMin" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">方差二级指标标准差（共用）：</label>
			<div class="controls">
				<form:input path="varianceStd" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="lg:lgBaselineofdevice:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>