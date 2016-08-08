<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>整机传感器信息管理</title>
<meta name="decorator" content="default" />
<link rel="stylesheet" type="text/css" href="${ctxStatic}/timeline/elements-all.css">
<link rel="stylesheet" type="text/css" href="${ctxStatic}/timeline/colors.css">
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=n7YhXw9moZ650jtLcPn2hQjG8ANwebKu">
//v2.0版本的引用方式：src="http://api.map.baidu.com/api?v=2.0&ak=您的密钥"
//v1.4版本及以前版本的引用方式：src="http://api.map.baidu.com/api?v=1.4&key=您的密钥&callback=initialize"
</script>
<style type="text/css">
.my ul {
	width: 100%;
}

.my li {
	width: 30%; /*如果显示三列 则width改为70px*/
	float: left;
	display: block;
}

.carousel-indicators {
	bottom: -12px;
	left: 105px;
	top: auto;
	right: auto;
}

.carousel-indicators li {
	width: 8px;
	height: 8px;
	border: 1px solid #bdc3c7;
	border-radius: 8px;
	cursor: pointer;
	/* 	background: rgba(0, 0, 0, 0); */
}

.carousel-indicators .active {
	background: rgba(189, 195, 199, .5)
}
</style>
	<script type="text/javascript">
	$(document).ready(function() {
	});
	function page(n,s){
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchForm").submit();
    	return false;
    }
	</script>
<script src="echarts/echarts.min.js"></script>
<script type="text/javascript">
	$(function(){
		var map = new BMap.Map("mapDiv");          // 创建地图实例
		var point = new BMap.Point(parseFloat(118.437627), parseFloat(34.991502));
	//	var point = new BMap.Point(118.437627, 34.991502);  // 创建点坐标
		map.centerAndZoom(point, 15);                 // 初始化地图，设置中心点坐标和地图级别
		map.setMinZoom(7);
		map.setMaxZoom(16);
		map.addOverlay(new BMap.Marker(point));
		map.enableScrollWheelZoom();
		map.panBy(140,120);                                //为了点居中而设置的地图偏移量
		});

</script>
</head>
<body>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span6" style="height: 50%;">
				<div class="row-fluid">
					<div class="span6"
						style="vertical-align: middle; margin: 18px auto auto auto;">
						 <div id="machine-carousel" class="carousel slide"
							data-ride="carousel">
							<ol class="carousel-indicators">
								<li data-target="#machine-carousel" data-slide-to="0"
									class="active"></li>
								<li data-target="#machine-carousel" data-slide-to="1"></li>
							</ol>
							<div class="carousel-inner">
								<div class="item active">
									<img style="width: 300px; height: 200px; cursor: pointer;"
										data-toggle="modal" data-target="#myModal"
										src="http://product.21-sun.com/uploadfiles//image/2014/08/20140814173416_816.jpg" />
								</div>
								<div class="item">
									 <div id="mapDiv" style="width:300px;height:200px;">
        								<!--Body content-->
     								 </div>
								</div>

							</div>
						</div>

					</div>
					<!-- <div class="span1"></div> -->
					<div class="span6"
						style="vertical-align: middle; margin: 50px auto auto 5px;">
						<p>
							编码：<span>${lgMachineprofile.code}</span>
						</p>
						<p>
							型号：<span>${lgMachineprofile.modelNumber}</span>
						</p>
						<p>
							名称：<span>${lgMachineprofile.name }</span>
						</p>
						<p>
							订货号：<span>${lgMachineprofile.orderNumber }</span>
						</p>
						<p>
							合格证号：<span>${lgMachineprofile.certificationNumber }</span>
						</p>
					</div>
				</div>
			</div>
			<!-- 传感器列表 -->
			<div class="span6 my">
			<a href="${ctx}/lg/lgMachineprofile/newList"><input id="back" class="btn btn-primary" type="button" value="返回列表" style="float:right;"/></a>
			<a href=""><input id="backMap" class="btn btn-primary" type="button" value="返回地图" style="float:right;"/></a>
			<div id="sensorDiv"  style="margin: 10px auto auto auto;border: 1px solid #ddd;border-radius: 3px;float:right;width:100%;">
				<div style="clear: both;margin-top: 10px;margin-bottom: 10px;">
					<div style="text-align: center;">传感器列表</div>
				</div>
				<div style="overflow: hidden;margin-bottom:5px;">
				<c:forEach items="${sensorList}" var="sensor" varStatus="p">
					<c:if test="${p.count eq 1 || (p.count-1) % 5 eq 0}">
						<ul>
					</c:if>
					<li><span><a href="${ctx}/lg/lgMachineEvent/sensorChart?deviceNo=${lgMachineprofile.code}">${sensor.sensorName}</a></span></li>
					<c:if test="${p.count % 5 eq 0 || p.count % 5 eq 5}">
						</ul>
					</c:if>
				</c:forEach>
				</div>

			</div>
		</div>
		</div>
	</div>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12">
				<div class="tabbable" id="tabs-490524">
					<ul class="nav nav-tabs">
						<li class="active"><a href="#panel-1" data-toggle="tab">机器大事记</a>
						</li>
						<li><a href="#panel-2" data-toggle="tab">报修记录</a></li>
						<li><a href="#panel-3" data-toggle="tab">保养记录</a></li>
						<li><a href="#panel-4" data-toggle="tab">维修记录</a></li>
						<li><a href="#panel-7" data-toggle="tab">巡检整改</a></li>
						<li><a href="#panel-5" data-toggle="tab">部件变更</a></li>
						<li><a href="#panel-6" data-toggle="tab">机器简述</a></li>
					</ul>
					<div class="tab-content">
						<div class="tab-pane active" id="panel-1">
							<!-- 时间轴 -->
							<div class="example-box-wrapper">
								<!-- 以下是时间轴  icon timeline组件  -->
								<div class="timeline-scroll">
									<div class="timeline-box timeline-horizontal"
										>
										<c:forEach items="${eventList}" var="lgMachineevent" varStatus="p">
										<c:if test="${p.count % 2 ne 0 }">

											<div class="tl-row">
											<div class="tl-item">
												<c:choose>
													<c:when test="${lgMachineevent.eventType eq 1}"><div class="tl-icon bg-red" style="bottom: -35px;"></c:when>
													<c:when test="${lgMachineevent.eventType eq 2}"><div class="tl-icon bg-black" style="bottom: -35px;"></c:when>
													<c:when test="${lgMachineevent.eventType eq 3}"><div class="tl-icon bg-blue" style="bottom: -35px;"></c:when>
													<c:when test="${lgMachineevent.eventType eq 4}"><div class="tl-icon bg-purple" style="bottom: -35px;"></c:when>
													<c:when test="${lgMachineevent.eventType eq 5}"><div class="tl-icon bg-green" style="bottom: -35px;"></c:when>
												</c:choose>
													<i class="glyph-icon icon-star"></i>
												</div>
												<div class="tl-panel"><fmt:formatDate
													value="${lgMachineevent.eventTime}"
													pattern="yyyy/MM/dd" /></div>
												<div class="popover top">
													<div class="arrow"></div>
													<div class="popover-content">
														<h3 class="tl-title"></h3>
														<p class="tl-content">${lgMachineevent.eventDescription}</p>
														<div class="tl-time">
															<i class="glyph-icon icon-clock-o"></i>
															<fmt:formatDate value="${lgMachineevent.eventTime}" pattern="yyyy年MM月dd日" />
														</div>
													</div>
												</div>
											</div>
											</div>
										</c:if>

										<c:if test="${p.count % 2 eq 0 }">
										<div class="tl-row">
											<div class="tl-item float-right">
												<c:choose>
													<c:when test="${lgMachineevent.eventType eq 1}"><div class="tl-icon bg-red" style="bottom: -35px;"></c:when>
													<c:when test="${lgMachineevent.eventType eq 2}"><div class="tl-icon bg-black" style="bottom: -35px;"></c:when>
													<c:when test="${lgMachineevent.eventType eq 3}"><div class="tl-icon bg-blue" style="bottom: -35px;"></c:when>
													<c:when test="${lgMachineevent.eventType eq 4}"><div class="tl-icon bg-purple" style="bottom: -35px;"></c:when>
													<c:when test="${lgMachineevent.eventType eq 5}"><div class="tl-icon bg-green" style="bottom: -35px;"></c:when>
												</c:choose>
													<i class="glyph-icon icon-star"></i>
												</div>
												<div class="tl-panel">
												<fmt:formatDate value="${lgMachineevent.eventTime}" pattern="yyyy/MM/dd" />
												</div>
												<div class="popover bottom">
													<div class="arrow"></div>
													<div class="popover-content">
														<h3 class="tl-title"></h3>
														<p class="tl-content">${lgMachineevent.eventDescription }</p>
														<div class="tl-time">
															<i class="glyph-icon icon-clock-o"></i>
															<fmt:formatDate value="${lgMachineevent.eventTime}" pattern="yyyy年MM月dd日" />
														</div>
													</div>
												</div>
											</div>
										</div>
										</c:if>
										</c:forEach>
									</div>
								</div>
							</div>
						</div>
						<div class="tab-pane" id="panel-2">
							<table id="contentTable"
								class="table table-striped table-bordered table-condensed">
								<thead>
									<tr>
										<th>报修时间</th>
										<th>报修单号</th>
										<th>报修人</th>
										<th>故障说明</th>
										<th>报修反馈</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${repairList}" var="lgRepairhistory">
										<tr>
											<td><fmt:formatDate
													value="${lgRepairhistory.repairTime}"
													pattern="yyyy-MM-dd HH:mm:ss" /></td>
											<td>${lgRepairhistory.orderNo}</td>
											<td>${lgRepairhistory.orderHuman}</td>
											<td>${lgRepairhistory.troubleInfo}</td>
											<td>${lgRepairhistory.feedBack}</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
						<div class="tab-pane" id="panel-3">
							<table id="contentTable"
								class="table table-striped table-bordered table-condensed">
								<thead>
									<tr>
										<th>保养时间</th>
										<th>保养单号</th>
										<th>保养人</th>
										<th>保养说明</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${upkeepList}" var="lgUpkeephistory">
										<tr>
											<td><fmt:formatDate
													value="${lgUpkeephistory.upkeepTime}"
													pattern="yyyy-MM-dd HH:mm:ss" /></td>
											<td>${lgUpkeephistory.orderNo}</td>
											<td>${lgUpkeephistory.orderHuman}</td>
											<td>${lgUpkeephistory.upkeepInfo}</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
						<div class="tab-pane" id="panel-4">
							<table id="contentTable"
								class="table table-striped table-bordered table-condensed">
								<thead>
									<tr>
										<th>维修时间</th>
										<th>维修单号</th>
										<th>维修人员</th>
										<th>维修说明</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${serviceList}" var="lgServicehistory">
										<tr>
											<td><fmt:formatDate
													value="${lgServicehistory.serviceTime}"
													pattern="yyyy-MM-dd HH:mm:ss" /></td>
											<td>${lgServicehistory.orderNo}</td>
											<td>${lgServicehistory.serviceHuman}</td>
											<td>${lgServicehistory.serviceInfo}</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
						<div class="tab-pane" id="panel-5">
							<table id="contentTable"
								class="table table-striped table-bordered table-condensed">
								<thead>
									<tr>
										<th>换件时间</th>
										<th>维修单号</th>
										<th>换件编码</th>
										<th>换件名称</th>
										<th>换件数量</th>
										<shiro:hasPermission name="lg:lgReplacehistory:edit">
											<th>操作</th>
										</shiro:hasPermission>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${replaceList}" var="lgReplacehistory">
										<tr>
											<td><fmt:formatDate
													value="${lgReplacehistory.replaceTime}"
													pattern="yyyy-MM-dd HH:mm:ss" /></td>
											<td>${lgReplacehistory.orderNo}</td>
											<td>${lgReplacehistory.replaceCode}</td>
											<td>${lgReplacehistory.replaceName}</td>
											<td>${lgReplacehistory.replaceNums}</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
						<div class="tab-pane" id="panel-6">
							<p>${lgMachineinfo.machineInfo}</p>
						</div>
						<div class="tab-pane" id="panel-7">
							<table id="contentTable"
								class="table table-striped table-bordered table-condensed">
								<thead>
									<tr>
										<th>巡检整改编号</th>
										<th>点修说明</th>
										<th>单据类型</th>
										<th>操作人</th>
										<th>巡检时间</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${inspectionList}" var="lgInspection">
										<tr>
											<td>${lgInspection.inspectionNo}</td>
											<td>${lgInspection.info}</td>
											<td>${lgInspection.type}</td>
											<td>${lgInspection.orderHuman}</td>
											<td><fmt:formatDate
													value="${lgInspection.inspectionTime}"
													pattern="yyyy-MM-dd HH:mm:ss" /></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
<div class="modal fade" id="myModal"  class="modal fade bs-example-modal-lg" style="display: none;" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">详细档案</h4>
      </div>
      <div class="modal-body">
      	<p>
      			<span>整机编号：${lgMachineprofile.code}</span>
        		<span>整机PIN码：${lgMachineprofile.pinCode }</span>
        </p>
        <p>
        		<span>整机简称：${lgMachineprofile.shortName }</span>
        		<span>整机全称：${lgMachineprofile.name}</span></p>
        <p>
        		<span>型号：${lgMachineprofile.modelNumber }</span>
        		<span>订货号：${lgMachineprofile.orderNumber }</span>
        </p>
        <p>
        		<span>整机类型：${fns:getDictLabels(lgMachineprofile.machineType, 'machineType', lgMachineprofile.machineType)}</span>
        		<span>合格证号：${lgMachineprofile.certificationNumber }</span>
        </p>
        <p>
        		<span>整机状态：${fns:getDictLabels(lgMachineprofile.machineStatus, 'machineStatus', lgMachineprofile.machineStatus)}</span>
        		<span>状态说明：${lgMachineprofile.statusDetail }</span>
        </p>
        <p>
        		<span>批次号：${lgMachineprofile.batchNumber }</span>
        		<span>服务档案号：${lgMachineprofile.serviceFileNumber }</span>
        </p>
        <p>
        		<span>生产日期：<fmt:formatDate value="${lgMachineprofile.productDate}" type="date" dateStyle="long"/></span>
        		<span>档案建立日期：<fmt:formatDate value="${lgMachineprofile.fileCreatedate}" type="date" dateStyle="long"/></span>
        </p>
         <p>
        		<span>建档单位：${lgMachineprofile.bookBuildingName }</span>
        		<span>销售区域：${lgMachineprofile.saleAreaId}</span>
        </p>
         <p>
        		<span>销售形式：${lgMachineprofile.saleType }</span>
        		<span>销售单位：${lgMachineprofile.saleUnitName }</span>
        </p>
         <p>
        		<span>销售日期：<fmt:formatDate value="${lgMachineprofile.saleDate}" type="date" dateStyle="long"/></span>
        		<span>客户：${lgMachineprofile.customerName}</span>
        </p>
         <p>
        		<span>生产工厂：${lgMachineprofile.productFactoryId }</span>
        		<span>生产类型：${fns:getDictLabels(lgMachineprofile.productType, 'productType', lgMachineprofile.productType)}</span>
        </p>

      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
	<script type="text/javascript">
		$(document).ready(function() {
        var timeline_item = $('.tl-row').length;
        var _width = $('.tl-row').width();
        var style;
        if(timeline_item > 0){
        	style = '<style>\n'
                +  '.timeline-horizontal.timeline-box:before {'
                + 'width:' + (_width * timeline_item + 20) + 'px'
                + '}\n</style>';
        } else {
        	$('.timeline-scroll').css({'width': '100%'})
        	$('.timeline-horizontal').css({'height':'0'})
        }

        if(timeline_item > 5) {
        	$('.timeline-scroll').css({
           		'width': (_width * timeline_item) + 'px'
           	})
        }

          $('head').append(style);
      })

	</script>
</body>
</html>
