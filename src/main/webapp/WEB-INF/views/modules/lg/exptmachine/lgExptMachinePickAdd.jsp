<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>试验机器选取管理</title>
	<meta name="decorator" content="default"/>
	<script src="${ctxStatic}/custom-js/linkedList-0.1.js" type="text/javascript"></script>
	
	<script type="text/javascript">
		//创建链表
		var cacheList = new LinkedList();
		
		window.onresize = function() {
			$(".span_height").height($("body").height()-$(".ch_box").height()-$(".pagination").height()-180);
		}
		$(document).ready(function() {	
			$(".span_height").height($("body").height()-$(".ch_box").height()-$(".pagination").height()-180);
			
			$("#checkall").change(function(){  
				if($(this).attr('checked')) {
					$("#contentTable tbody tr td input[type=checkbox]").attr('checked', true);
				} else {
					$("#contentTable tbody tr td input[type=checkbox]").attr('checked', false);				
				}
			})  

			//$("#name").focus();
			$("#inputForm").validate({
				ignore:"", //放开忽略hidden验证，默认是不验证hidden表单数据
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

			$("#machineType").change(function() {
				queryProductList();	
			});
			
			$("#dimensionType").change(function() {
				queryDimensionList();
				$("#saleAreaLlb").text($(this).find("option:selected").text()+"：");
			});
			queryProductList();
			queryDimensionList();
			addTrClickEvent();
			//查询
			$('#searchForm').on("submit",function() {
			        $(this).ajaxSubmit({
		                dataType: "json",           
		                /*target: '#contentTable',       */   
		                beforeSubmit: function(arr,$form,options){
		                },
		                success: function(data,status,xhr,$form){
		                    var p_html = "";
		                    if(data != null && data.list != null) {
			                	for (var i in data.list) {
		                    		p_html += "<tr>"
		                    		+"<td width='2%'><input type='checkbox' value='" + data.list[i].id + "'/></td>"
		 		                    +"<td width='14%'>" + ((typeof(data.list[i].pinCode) == "undefined")?" ":(data.list[i].pinCode)) + "</td>"
		 		                    +"<td width='14%'>" + ((typeof(data.list[i].modelNumber) == "undefined")?" ":(data.list[i].modelNumber)) + "</td>"
		 		                    +"<td width='14%'>" + ((typeof(data.list[i].orderNumber) == "undefined")?" ":(data.list[i].orderNumber)) + "</td>"
		 		                    +"<td width='14%'>" + ((typeof(data.list[i].bookBuildingId) == "undefined")?" ":(data.list[i].bookBuildingId)) + "</td>"
		 		                    +"<td width='14%'>" + ((typeof(data.list[i].saleAreaId) == "undefined")?" ":(data.list[i].saleAreaId)) + "</td>"
		 		                    +"<td width='14%'>" + ((typeof(data.list[i].saleUnitId) == "undefined")?" ":(data.list[i].saleUnitId)) + "</td>"
		 		                    +"<td width='14%'>" + ((typeof(data.list[i].customerId) == "undefined")?" ":(data.list[i].customerId)) + "</td>"
		 		                    +"</tr>";	
		                    	 }
		                    } else {
		                    	p_html = "<td colspan=\"8\"><h5  style=\"padding: 0 0 0 10px;\">没有符合条件的记录!!!</h5></td>";
		                    }
		                    $("#contentTable tbody").html(p_html);
		                    $(".pagination").html(data.html);
		                    $("#pageNo").val(data.pageNo);
		                    $("#pageSize").val(data.pageSize);
		                    addTrClickEvent();
		                },
		                error: function(xhr, status, error, $form){
		                },
		                complete: function(xhr, status, $form){
		                }
		            }
			     );
			        return false; 
			    });
		});
		//获取产品型号列表
		function queryProductList() {
			machineType = $("#machineType").val();
			if(machineType == null || machineType == "") {
				return;
			}	
			$.ajax({
				type:"POST",
				url:"${ctx}/lg/exptmachine/lgExptMachinePick/productTypeList",
				dataType:"json",	
				data: {
					machineType:machineType
				},
				success:function(data) {
					var p_html = "";
					$.each(data, function(index, item) {
						p_html += "<li><label><input type='checkbox' name='queryMap.modelNumberList' value='" + item + "'/>"+ item+"</label></li>";
					});
					$("#productType").html(p_html);
				}
			});
		}
		//获取整机维度列表
		function queryDimensionList() {
			dimensionType = $("#dimensionType").val();
			if(dimensionType == null || dimensionType == "") {
				return;
			}
			$.ajax({
				type:"POST",
				url:"${ctx}/lg/exptmachine/lgExptMachinePick/dimensionList",
				dataType:"json",	
				data: {
					dimensionType:dimensionType
				},
				success:function(data) {
					var p_html = "";
					$.each(data, function(index, item) {
						p_html += "<li><label><input type='checkbox' name='saleAreaIdList' value='" + item.id + "'/>"+item.dimensionName+"</label></li>";
					});
					$("#dimensionArea").html(p_html);
				}
			});
		}
		//添加tr单击事件
		function addTrClickEvent() {
			$("#contentTable tbody tr").click(function() {
				var selector = $(this).children('td').eq(0).children('input[type=checkbox]');
				selector.attr('checked')?selector.attr('checked', false):selector.attr('checked', true);
				if(selector.attr('checked')) {
					cacheList.addLast(selector.val());
				} else {
					cacheList.remove(selector.val());
				}
			});
			$("#contentTable tbody tr td input[type=checkbox]").click(function(event) {
				event.stopPropagation(); //阻止事件冒泡到父元素，阻止任何父事件处理程序被执行
				var selector = $(this);
				if(selector.attr('checked')) {
					cacheList.addLast(selector.val());
				} else {
					cacheList.remove(selector.val());
				}
			});
		}
		function page(n,s){
			//cacheUserSelect();
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		//缓存用户某一分页的选择，防止用户点击下一页时丢失选择
		function cacheUserSelect() {
			var sel = "";
			$("#contentTable tbody tr td input[type=checkbox]:checked").each(function(){ 
				cacheList.addLast($(this).val());
			});
		}
		
		function saveMachine() {
			var machineIds = "";
			$("#contentTable tbody tr td input[type=checkbox]:checked").each(function(){ 
				machineIds += $(this).val() + ",";
			});
			$("#machineIds").val(machineIds);
			if(machineIds == null || machineIds == "") {
				showTip('请选择试验机器！', 'error',1500,0);
				return;
			} 
			$("#inputForm").submit();
		} 
	</script>
	<style> 
		.ch_box {
			height:120px;
			overflow-x:hidden;
			overflow-y: auto; 
			background-color:#FFF;
		}
		.ch_box ul li {
			list-style-type:none;
		}
		    
		html,body{
		    height: 100%;
		    width: 100%;
		}
		.span_height {
			overflow-x:hidden;
			/*overflow-y:hidden;*/
			border:2px solid #EEE;	
		}

	</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/lg/exptmachine/lgExptMachinePick/info?id=${lgExptMachinePick.id}">试验机器选取明细</a></li>
		<li class="active"><a href="${ctx}/lg/exptmachine/lgExptMachinePick/add?id=${lgExptMachinePick.id}">选取试验机器</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="machineType" action="${ctx}/lg/exptmachine/lgExptMachinePick/queryList" method="post" class="breadcrumb form-search">
	<input id="pickId" name="pickId"  value="${lgExptMachinePick.id}" type="hidden"/>
	
	<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">
			<div class="row-fluid">
				<div class="span5">
					<label class="control-label">机器类型：</label>
					<select id="machineType" name="machineType" class="input-small">
						<c:forEach items="${fns:getDictList('machineType')}" var="machineType">
								<option value="${machineType.value}">${machineType.label}</option>
						</c:forEach>
					</select>
				</div>
				<div class="span5">
					<select id="dimensionType" class="input-small">
						<c:forEach items="${fns:getDictList('dimensionType')}" var="dimensionType">
							<c:if test="${dimensionType.value == '2'}">
								<option value="${dimensionType.value}">${dimensionType.label}</option>
							</c:if>
						</c:forEach>
					</select>
				</div>
				<div class="span2">
					
				</div>
			</div>
			<div class="row-fluid">
				<div class="span5">
					<label class="control-label">产品型号：</label>
					<div class="ch_box">
						<ul id="productType">
						</ul>
					</div>
				</div>
				<div class="span5">
					<label class="control-label" id="saleAreaLlb">销售区域：</label>
					<div class="ch_box">
						<ul id="dimensionArea">
						</ul>
					</div>
				</div>
				<div class="span2">
					<div style="border-top: none;">
						<input id="searchSubmit" class="btn btn-primary btn-block" type="submit" value="查 询" />
						<input id="pickSubmit" class="btn btn-primary btn-block" type="button" onclick="saveMachine()" value="保 存" />
						<input id="btnCancel" class="btn btn-block" type="button" value="返 回" onclick="history.go(-1)"/>
					</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	</div>
	</form:form>
	
	<form:form id="inputForm" modelAttribute="lgExptMachinePickDetail" action="${ctx}/lg/exptmachine/lgExptMachinePickDetail/saveBatch" method="post" class="form-horizontal">
		<input id="machineIds" name="machineIds" class="input-medium"  type="hidden" value=""/>
		<input id="pickId" name="pickId" class="input-medium"  type="hidden" value="${lgExptMachinePick.id}"/>
	</form:form>
	<div class="container-fluid " >
			<div class="row-fluid">
				<div class="span12">
					<table class="table table-striped table-bordered table-condensed table-hover">
						<thead>
							<tr>
								<th width="2%"><input type='checkbox' id="checkall"/> </th>
								<th width="14%">整机编码</th>
								<th width="14%">型号</th>
								<th width="14%">订货号</th>
								<th width="14%">建档单位</th>
								<th width="14%">销售区域</th>
								<th width="14%">销售单位</th>
								<th width="14%">客户</th>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
				</div>
			</div>
			<div class="row-fluid">
				<div class="span12 span_height">
					<table id="contentTable" class="table table-striped table-bordered table-condensed table-hover">
						<tbody>
							<td colspan="8"><h5  style="padding: 0 0 0 10px;">查询结果为当前批次未选取过的试验机器，若已选取，则此处不予展示!!!</h5></td>
						</tbody>
					</table>
				</div>
			</div>
			<div class="pagination">
				<ul>
				<li class="disabled"><a href="javascript:">« 上一页</a></li>
				<li class="active"><a href="javascript:">1</a></li>
				<li class="disabled"><a href="javascript:">下一页 »</a></li>
				<li class="disabled controls"><a href="javascript:">当前 <input type="text" value="1" onkeypress="var e=window.event||this;var c=e.keyCode||e.which;if(c==13)page(this.value,30,'');" onclick="this.select();"/> / <input type="text" value="30" onkeypress="var e=window.event||this;var c=e.keyCode||e.which;if(c==13)page(1,this.value,'');" onclick="this.select();"/> 条，共 0 条</a></li>
				</ul>
				<div style="clear:both;"></div>
			</div> 	
		</div>
	</div>
	</div>
</body>
</html>