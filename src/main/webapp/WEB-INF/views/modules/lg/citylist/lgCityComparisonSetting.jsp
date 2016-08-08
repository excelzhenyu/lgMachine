<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>城市地图对照管理</title>
	<meta name="decorator" content="default"/>
		<style> 
		html,body{
		    height: 100%;
		    width: 100%;
		}
		.span_height {
			overflow-x:hidden;
			/*overflow-y:hidden;*/
			border:2px solid #EEE;	
		}
		.row-fluid {
			margin-top:10px;
		}
		.row-fluid [class*="span"] {
			margin-left:10px;
			margin-right:0;
		}
		.container-fluid {
		    padding-right: 0;
		}
		.checked-bkcolor {
			background-color:#22cbaa;
		}
	</style>
	<script>
		var checkedSystemCityId = "", checkedMapCityId = "";
		var systemSelector, mapSelector;
		window.onresize = function() {
			$(".span_height").height(($("body").height()-250)*0.95);
		}
		$(document).ready(function() {	
			$(".span_height").height(($("body").height()-250)*0.95);
			clickTrEvent();
			$("#systemCityCode").keyup(function (e) {
			    if (e.keyCode == 13) {
			    	searchSystemCity();
			    }
			});
			$("#mapCityCode").keyup(function (e) {
			    if (e.keyCode == 13) {
			    	searchMapCity();
			    }
			});
		});
		//注册tr点击事件
		function clickTrEvent() {
			$("#contentTableSystemCity tbody tr").click(function() {
				$(this).addClass("success");
				$(this).siblings().removeClass("success");
				checkedSystemCityId = $(this).children('td').eq(0).text();
				systemSelector = $(this);
			});
			$("#contentTableMapCity tbody tr").click(function() {
				$(this).addClass("success");
				$(this).siblings().removeClass("success");
				checkedMapCityId = $(this).children('td').eq(0).text();
				mapSelector = $(this);
			});
		}
		//手工导入
		function manualImportData(){
			if(checkedMapCityId && checkedSystemCityId) {
				var data = {id:"",mapCityId:checkedMapCityId, systemCityId:checkedSystemCityId, isEffective:"1"}; 
	        	$.ajax({  
					type: "post",  
					data:data,  
					dataType:'json',
					url:"${ctx}/lg/citylist/lgCityComparison/manualSave",   
					success: function(json){ 
						if(json=="200" ){  
							systemSelector.hide();
							mapSelector.hide();
							systemSelector = mapSelector = checkedSystemCityId = checkedMapCityId =  "";
							showTip('导入成功!', 'info',1500,0);
						} else{  
							showTip('导入失败!', 'error',1500,0); 
						}  
	         		},  
	         		error: function(){  
	         			showTip('网络异常，导入失败!', 'error',1500,0);  
					}  
				});  
			} else {
				showTip('请选择业务系统城市和地图城市！', 'error',1500,0);
        	}
		}
		//一键导入
		function batchImportData(){
			confirmx('确定要一键导入名称相同的城市么？', "${ctx}/lg/citylist/lgCityComparison/batchImport", function(){});
		}
		//业务系统城市搜索
		function searchSystemCity() {
			var systemCityCode = $("#systemCityCode").val();
			/*
			if(systemCityCode == null || systemCityCode == "") {
				showTip('未输入搜索条件，请输入城市编码或名称', 'error',1500,0);  
				return false;
			}*/
			var data = {cityCode:systemCityCode}; 
        	$.ajax({  
				type: "post",  
				data:data,  
				dataType:'json',
				url:"${ctx}/lg/citylist/lgCityComparison/searchSystemCity",   
				success: function(json){ 
					if(json){  
						var cityListHtml = "";
						var cityList = json;
						for (var i in cityList) {
							cityListHtml += "<tr>";
							cityListHtml += "<td style=\"display:none\">" + cityList[i].id + "</td>";
							cityListHtml += "<td width=\"50%\">" + cityList[i].cityCode + "</td>";
							cityListHtml += "<td width=\"50%\">" + cityList[i].cityName + "</td>";
							cityListHtml += "</tr>";
						}
						$("#contentTableSystemCity tbody").html(cityListHtml);
						clickTrEvent();
					} 
         		},  
         		error: function(){  
         			showTip('网络异常，搜索失败!', 'error',1500,0);  
				}  
			});  
		}
		//地图城市搜索
		function searchMapCity() {
			var mapCityCode = $("#mapCityCode").val();
			/*
			if(mapCityCode == null || mapCityCode == "") {
				showTip('未输入搜索条件，请输入城市编码或名称', 'error',1500,0);  
				return false;
			}*/
			var data = {cityCode:mapCityCode}; 
        	$.ajax({  
				type: "post",  
				data:data,  
				dataType:'json',
				url:"${ctx}/lg/citylist/lgCityComparison/searchMapCity",   
				success: function(json){ 
					if(json){  
						var cityListHtml = "";
						var cityList = json;
						for (var i in cityList) {
							cityListHtml += "<tr>";
							cityListHtml += "<td style=\"display:none\">" + cityList[i].id + "</td>";
							cityListHtml += "<td width=\"50%\">" + cityList[i].cityCode + "</td>";
							cityListHtml += "<td width=\"50%\">" + cityList[i].cityName + "</td>";
							cityListHtml += "</tr>";
						}
						$("#contentTableMapCity tbody").html(cityListHtml);
						clickTrEvent();
					} 
         		},  
         		error: function(){  
         			showTip('网络异常，搜索失败!', 'error',1500,0);  
				}  
			});  
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li ><a href="${ctx}/lg/citylist/lgCityComparison/">业务销售城市与地图城市对照列表</a></li>
		<shiro:hasPermission name="lg:citylist:lgCityComparison:edit">
			<li class="active"><a href="${ctx}/lg/citylist/lgCityComparison/setting">业务销售城市与地图城市对照添加</a></li>
		</shiro:hasPermission>
	</ul>
	<div class="container-fluid">
	<sys:message content="${message}"/>		
	<div class="row-fluid">
		<div class="span6" align="center">
			<div class="row-fluid">
				<div class="span8" align="center"> <h4>尚未配置对照关系的业务系统城市列表</h4> </div>
				<div class="span4" align="center">
					<div class="input-append">
					 	<input class="span9" id="systemCityCode" type="text" placeholder="城市编号或名称">
					 	<button class="btn btn-primary" type="button" onClick="searchSystemCity();">搜索</button>
					</div>
				</div>
			</div>
			<table id="contentTableSystemCityHead" class="table table-striped table-bordered table-condensed table-hover" style="margin-top:10px;">
				<thead>
					<tr>
						<th width="50%">城市编码</th>
						<th width="50%">城市名称</th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
		</div>
		<div class="span6" align="center">
			<div class="row-fluid">
				<div class="span8" align="center"> <h4>尚未配置对照关系的地图城市列表</h4> </div>
				<div class="span4" align="center">
					<div class="input-append">
					 	<input class="span8" id="mapCityCode" type="text" placeholder="城市编号或名称">
					 	<button class="btn btn-primary" type="button" onClick="searchMapCity();">搜索</button>
					</div>
				</div>
			</div>
			<table id="contentTableMapCityHead" class="table table-striped table-bordered table-condensed table-hover" style="margin-top:10px;">
				<thead>
					<tr>
						<th width="50%">城市编码</th>
						<th width="50%">城市名称</th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>	
		</div>
	</div>
	<div class="row-fluid">
		<div class="span6 span_height">
			<table id="contentTableSystemCity"
					class="table table-bordered table-condensed table-hover">
					<tbody>
						<c:forEach items="${systemCityList}" var="systemCity">
							<tr>
								<td style="display:none">${systemCity.id}</td>
								<td width="50%">${systemCity.cityCode}</td>
								<td width="50%">${systemCity.cityName}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
		</div>
		<div class="span6 span_height">
			<table id="contentTableMapCity"
					class="table  table-bordered table-condensed table-hover">
					<tbody>
						<c:forEach items="${mapCityList}" var="mapCity">
							<tr>
								<td style="display:none">${mapCity.id}</td>
								<td width="50%">${mapCity.cityCode}</td>
								<td width="50%">${mapCity.cityName}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
		</div>
	</div>
	<div class="row-fluid">
		<div class="span2"> </div>
		<div class="span3">
			 <button class="btn btn-block btn-primary" type="button" onClick="batchImportData();">一键导入名称相同的城市</button>
		</div>
		<div class="span2"> </div>
		<div class="span3">
			<button class="btn btn-block btn-primary" type="button" onClick="manualImportData();">手工导入</button>
		</div>
		<div class="span2">  </div>
	</div>
</body>
</html>