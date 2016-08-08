<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>整机档案管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {	
			//$("#name").focus();
			$("#inputForm").validate({
				rules:{
					code:{required:true,remote:"${ctx}/lg/lgMachineprofile/checkCode?id=${lgMachineprofile.id}"},
					pinCode:{remote:"${ctx}/lg/lgMachineprofile/checkPinCode?id=${lgMachineprofile.id}"}
				},
				messages:{
					code:{remote:"整机编号已经存在"},
					pinCode:{remote:"整机PIN码已经存在"}
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
				
		 function setfiletext(file){
			if(file.value.indexOf('.jpg') > -1 || file.value.indexOf('.png') > -1){
			   	var filesell = $("#myBlogImage").val();
			   	var filesellv = filesell.split("\\");
			   	$("#filesel").val(filesellv[filesellv.length-1]);
			}else{
				alert("只能上传.jpg .png格式的文件，请重新选择");
			}
		   }	
		 
		 function openImg(){
//			 var src = ${ctxStatic}+$("imgPath").val();
//			 alert(src);
//			window.open(src);
			var src = "${ctxUpload}/6.jpg";
			var newWindow = window.open('');
		    newWindow.document.write('<img src="'+src+'"/>');//换成你需要的地址
			//+$("dlcpathlable").html();
			alert(src);
		 }
	</script>
	<script type="text/javascript">
	function ajaxFileUpload() {
		//开始上传文件时显示一个图片,文件上传完成将图片隐藏
		//$("#loading").ajaxStart(function(){$(this).show();}).ajaxComplete(function(){$(this).hide();});
		//执行上传文件操作的函数
		var file = document.getElementById("myBlogImage");
		if(file.value.indexOf('.jpg') > -1 || file.value.indexOf('.png') > -1){
			$.ajaxFileUpload({
				//处理文件上传操作的服务器端地址(可以传参数,已亲测可用)
				url : '${ctx}/lg/lgMachineprofile/uploaderajax',
				secureuri : false, //是否启用安全提交,默认为false 
				fileElementId : 'myBlogImage', //文件选择框的id属性
				dataType : 'text', //服务器返回的格式,可以是json或xml等
				success : function(data, status) { //服务器响应成功时的处理函数
					data = jQuery.parseJSON(jQuery(data).text());
					if (data.files.length > 0) {
						$('#imgUrl').val(data.files[0].fileName);
						$('#dlcpathlable').html(data.files[0].fileName);
					}
					
				},
				error : function(data, status, e) { //服务器响应失败时的处理函数
					console.log('上传失败处理');

				}
			});
		}else{
			alert("只能上传.jpg .png格式的图片，请重新选择");
		}
	}	
</script>
<style>
	
</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/lg/lgMachineprofile/">整机档案列表</a></li>
		<li class="active"><a href="${ctx}/lg/lgMachineprofile/form?id=${lgMachineprofile.id}">整机档案<shiro:hasPermission name="lg:lgMachineprofile:edit">${not empty lgMachineprofile.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="lg:lgMachineprofile:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>		
	<form:form id="inputForm" modelAttribute="lgMachineprofile" action="${ctx}/lg/lgMachineprofile/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<div class="row">
			<div class="span7">
			<div class="control-group">
			<label class="control-label">整机编码：</label>
			<div class="controls">
				<form:input path="code" htmlEscape="false" maxlength="40" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">整机PIN码：</label>
			<div class="controls">
				<form:input path="pinCode" htmlEscape="false" maxlength="40" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">整机简称：</label>
			<div class="controls">
				<form:input path="shortName" htmlEscape="false" maxlength="40" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">整机全称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="120" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">英文名称：</label>
			<div class="controls">
				<form:input path="enName" htmlEscape="false" maxlength="40" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">型号：</label>
			<div class="controls">
				<form:input path="modelNumber" htmlEscape="false" maxlength="20" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">订货号：</label>
			<div class="controls">
				<form:input path="orderNumber" htmlEscape="false" maxlength="20" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">整机类型：</label>
			<div class="controls">
				<form:select path="machineType" class="input-medium  required" >
					<form:options items="${fns:getDictList('machineType')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</div>
			</div>
		<div class="control-group">
			<label class="control-label">合格证号：</label>
			<div class="controls">
				<form:input path="certificationNumber" htmlEscape="false" maxlength="30" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">整机状态：</label>
			<div class="controls">
				<form:select path="machineStatus" class="input-medium  required" >
					<form:options items="${fns:getDictList('machineStatus')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">状态说明：</label>
			<div class="controls">
				<form:input path="statusDetail" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">批次号：</label>
			<div class="controls">
				<form:input path="batchNumber" htmlEscape="false" maxlength="20" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">服务档案号：</label>
			<div class="controls">
				<form:input path="serviceFileNumber" htmlEscape="false" maxlength="20" class="input-xlarge "/>
			</div>
		</div>
			</div>
			
     		<div class="span7">
     		<div class="control-group">
			<label class="control-label">生产日期：</label>
			<div class="controls">
				<input name="productDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${lgMachineprofile.productDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">档案建立日期：</label>
			<div class="controls">
				<input name="fileCreatedate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${lgMachineprofile.fileCreatedate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">建档单位：</label>
			<div class="controls">
				<form:select path="bookBuildingId" class="input-medium  required" >
					<form:options items="${fns:getMachineDimension(1)}"
						itemLabel="dimensionName" itemValue="id" htmlEscape="false" />
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">销售区域：</label>
			<div class="controls">
				<form:select path="saleAreaId" class="input-medium  required" >
					<form:options items="${fns:getMachineDimension(2)}"
						itemLabel="dimensionName" itemValue="id" htmlEscape="false" />
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">销售形式：</label>
			<div class="controls">
				<form:input path="saleType" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">销售单位：</label>
			<div class="controls">
				<form:select path="saleUnitId" class="input-medium  required" >
					<form:options items="${fns:getMachineDimension(3)}"
						itemLabel="dimensionName" itemValue="id" htmlEscape="false" />
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">销售日期：</label>
			<div class="controls">
				<input name="saleDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${lgMachineprofile.saleDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">客户：</label>
			<div class="controls">
				<form:select path="customerId" class="input-medium  required" >
					<form:options items="${fns:getMachineDimension(4)}"
						itemLabel="dimensionName" itemValue="id" htmlEscape="false" />
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">生产工厂：</label>
			<div class="controls">
				<form:select path="productFactoryId" class="input-medium  required" >
					<form:options items="${fns:getMachineDimension(5)}"
						itemLabel="dimensionName" itemValue="id" htmlEscape="false" />
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">整机生产类型：</label>
			<div class="controls">
				<form:select path="productType" class="input-medium  required" >
					<form:options items="${fns:getDictList('productType')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否安装智能终端：</label>
			<div class="controls">
 			 <form:radiobuttons path="smartTerminalOrNot" items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">整机传输特征码：</label>
			<div class="controls">
				<form:input path="transmitCode" htmlEscape="false" maxlength="30" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">停止服务标志： </label>
			<div class="controls">
					<form:radiobuttons path="stopServiceMark" items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</div>
			</div>

     		</div>
		</div>		
		<div class="control-group">
			<label class="control-label">上传路径：</label>
			<div class="controls">
				<span class="btn" >选择文件</span>
				<input type="text" value="未选择文件" id="filesel" class="filetext" disabled="disabled">
				<input type="file" id="myBlogImage" accept="image/png,image/jpeg" name="myfiles" class="filehide" onchange="setfiletext(this)" style="cursor:pointer;left: 100px;">
				<input type="button" value="上传" onclick="ajaxFileUpload()" class="btn">
				<form:hidden path="imgUrl" htmlEscape="false" maxlength="200" class="input-xlarge "/>
				<label id="dlcpathlable"  onclick='openImg()'>${lgMachineprofile.imgUrl}</label>
			</div>
		</div>	
		<div class="form-actions">
			<shiro:hasPermission name="lg:lgMachineprofile:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>