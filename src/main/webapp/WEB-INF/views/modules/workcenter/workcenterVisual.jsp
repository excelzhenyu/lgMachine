<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>工作中心管理</title>
	<meta name="decorator" content="default"/>
	<style type="text/css">
	.workcenter {
		background-color: #fffefd;
	}
	.workcenter-block {
		margin: 5px;
		background-color: #ffffff;
		width: 100%;
		height: 400px;
		border-style: soild;
		border-width: 3px;
	}
	.btn {
		float: right;
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
</head>
<body>
	<sys:message content="${message}"/>
	
	<div class="form-actions">
		<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
	</div>
	<div class="container-fluid workcenter">
		<div class="row">
			<div id="job-graph" class="col-sm-1 col-xs-1 col-md-1 workcenter-block"></div>
		</div>
		<div class="row">
			<div id="job-graph2" class="col-sm-1 col-xs-1 col-md-1 workcenter-block"></div>
		</div>
	</div>
	
	<script type="text/javascript">
	var width = 2000;
	var height = 1000;
	var nodeWidth = width / 50;
	var nodeHeight = width / 50;

	var svg = d3.select("#job-graph")
				.append("svg")
				.attr("width",width)
				.attr("height",height);

	// 定义箭头
	var defs = svg.append("defs");

	var arrowMarker = defs.append("marker")
						.attr("id","arrow")
						.attr("markerUnits","strokeWidth")
						.attr("markerWidth","40")
						.attr("markerHeight","40")
						.attr("viewBox","0 0 40 40")
						.attr("refX","6")
						.attr("refY","6")
						.attr("orient","auto");

	var arrow_path = "M2,2 L10,6 L2,10 L6,6 L2,2";

	arrowMarker.append("path")
	            .attr("d",arrow_path)
	            .attr("fill","#000");
	
	$.getJSON('jobjson', {rootJobGroup: '${rootJobGroup}'}, function(root) {
		console.log(root);
		
		var nodes = root.nodes;
		var edges = root.links;
		
		var force = d3.layout.force()
				.nodes(nodes)		//指定节点数组
				.links(edges)		//指定连线数组
				.size([width,height])	//指定范围
				.linkDistance(150)	//指定连线长度
				.charge(-400);	//相互之间的作用力

		//添加连线
		var svg_edges = svg.selectAll("line")
							.data(edges)
							.enter()
							.append("line")
							.attr('marker-end', 'url(#arrow)')
							.style("stroke","#ccc")
							.style("stroke-width",1);
		
		var color = d3.scale.category20();
		
		//添加描述节点的文字
		var svg_texts = svg.selectAll("text")
							.data(nodes)
							.enter()
							.append("text")
							.style("fill", "black")
							.text(function(d){
								return d.name;
							});
		
		var drag = force.drag()
						.on('dragstart', function(d, i) {
							d.fixed = true;
						})
						.on('drag', function(d, i) {
		
						})
						.on('dragend', function(d, i) {
							var nameNGroup = d.name.split('.');
							
							$.post('updateAxisByNameNGroup',
									{name: nameNGroup[1], group: nameNGroup[0], xAxis: d.x, yAxis: d.y},
									function(date, status) {
										
									}); 
						});
		
		//添加节点
		var svg_nodes = svg.selectAll("rect")
							.data(nodes)
							.enter()
							.append("rect")
							.attr("width", nodeWidth)
							.attr("height", nodeHeight)
							.attr("opacity", 0.8)
							.style("fill",function(d,i){
								switch (d.status) {
									case 1:
										return 'steelBlue';
										break;
									case 2:
										return '#959595';
										break;
									case 9:
										return 'rgb(193, 0, 0)';
										break;
									default:
										return 'steelBlue';
		
								}
							})
							.call(drag);	//使得节点能够拖动
		
		force.on('start', function(d, i) {
			d.fixed = true;
		});
		
		force.on("tick", function(d, i){	//对于每一个时间间隔
			//更新连线坐标
			svg_edges.attr("x1",function(d){ return d.source.x; })
					.attr("y1",function(d){ return d.source.y; })
					.attr("x2",function(d){ return d.target.x - nodeWidth / 2; })
					.attr("y2",function(d){ return d.target.y; });
		
			//更新节点坐标
			svg_nodes.attr("x",function(d){ return d.x - nodeWidth / 2; })
					 .attr("y",function(d){ return d.y - nodeHeight / 2; })
					 .attr("rx",function(d){ return nodeWidth >> 2 ; })
					 .attr("ry",function(d){ return nodeWidth >> 2; });
		
		 	//更新文字坐标
			svg_texts.attr("x", function(d){ return d.x - d.name.length / 2; })
				.attr("y", function(d){ return d.y - nodeHeight / 2 - 4; });
		});
		
		force.start();	//开始作用
	});

	
	var myChart2 = echarts.init(document.getElementById('job-graph2'));

	var option2 = {
	    title: {
	        text: 'job <time></time>',
	        subtext: 'From ExcelHome'
	    },
	    tooltip : {
	        trigger: 'axis',
	        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
	            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
	        },
	        formatter: function (params) {
	            var tar = params[1];
	            return tar.name + '<br/>' + tar.seriesName + ' : ' + tar.value;
	        }
	    },
	    grid: {
	        left: '3%',
	        right: '4%',
	        containLabel: true
	    },
	    yAxis: {
	        type : 'category',
	        splitLine: {show: false},
	        data : ['job1','job2','job3','job4','job5','job6']
	    },
	    xAxis: {
	        type : 'time'
	    },
	    dataZoom: [
		    {
		        show: true,
		        start: 94,
		        end: 100,
		        handleSize: 8
		    },
		]
	};
	
	myChart2.setOption(option2);
	
	$.getJSON("jobexetimejson", {
		rootJobGroup: '${rootJobGroup}'
	}, function(data) {
		var seriesStr = new Array();
		var i = 0;
		for (var exeTime in data.exeTimeList) {
			var seriesObj = new Object();
			seriesObj.type = 'bar';
			seriesObj.stack = '执行时间';
			seriesObj.barMaxWidth = 30;
    		if (i % 2 == 0) {
    			var itemStyle = new Object();
    			var normal = new Object();
    			normal.barBorderColor = 'rgba(0,0,0,0)';
    			normal.color = 'rgba(0,0,0,0)';
    			
    			itemStyle.normal = normal;
    			itemStyle.emphasis = normal;
    			seriesObj.itemStyle = itemStyle;
    		} else {
				/* var label = new Object();
				var normal = new Object();
				normal.show = true;
				normal.position = 'inside';
				label.normal = normal;
				seriesObj.label = label; */
    		}
    		seriesObj.data = data.exeTimeList[exeTime];
    		
    		seriesStr[i] = seriesObj;
    		
    		i++;
    	}
		
		myChart2.setOption({
			yAxis: {
		        data : data.category
		    },
		    series: seriesStr
		});
	});
	</script>
</body>
</html>