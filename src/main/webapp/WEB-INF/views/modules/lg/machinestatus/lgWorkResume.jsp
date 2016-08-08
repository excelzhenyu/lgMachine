<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
  <title>工作履历分析管理</title>
  <meta name="decorator" content="default"/>
  <style type="text/css">
    .input-mysize {
      width: 200px;
    }
    .span-rightborder {
      border-right: 1px solid #f5f5f5;
      padding-right: 15px;
    }
    table {
      border-collapse: collapse;
      border: 1px solid #ccc;
      width: 100%;
      min-width: 100%;
    }

    thead td {
      border: 1px solid #ccc;
      text-align: center;
      height: 36px;
    }

    thead td:nth-of-type(1)::after {
      content: '';
      position: absolute;
      width: 100%;
      height: 100%;
      z-index: -2;
      display: block;
      top: 0;
      left: 0;
      background-image: linear-gradient(to right top,  transparent calc(50% - 1px), #DEDEDE, transparent calc(50% + 1px));

    }
    tbody tr {
      border: 1px solid #ccc;
    }

    thead .left,
    thead .right {
      /*height: 50px;*/
      position: absolute;
    }

    thead .left {
      left: 5px;
    }

    thead .right {
      top: 2px;
      right: 5px;
    }


    td {
      position: relative;
      min-width:  100px;
    }

    tr td:nth-of-type(1) {
      border: 1px solid #ccc;
      text-align: center;
    }

    .map-tooltip {
      position: absolute;
      top: 10px;
      left: 25px;
      background: #FFFFFF;
      width: 200px;
      /* height: 100px; */
      border-radius: 5px;
      border: 1px solid #ccc;
      z-index: 100;
      padding-left: 10px;
      /*padding-top: 10px;*/
    }

    .tooltips {
      height: 100%;
      margin-top: 1px;
      margin-bottom: 1px;
    }
    .container {
      border: 1px solid #ccc;
      padding: 5px;
      margin-bottom: 10px;
    }
    .error {
      color: red;
      display: none;
    }
  </style>
  <script type="text/javascript">
    $(document).ready(function() {
      if ($('#resumeStart').val() == '')
        $('#resumeStart').val(getAllowMinDate().Format('yyyy-MM-dd'));  // 开工日期默认本月第一天
        
      // 设置结束日期默认值
      if ($('#resumeStop').val() == '') {
        var today = new Date();
        if ((new Date()).getDate() == 1) {  // 如果是每月第一天则默认当天信息
          $('#resumeStop').val(today.Format('yyyy-MM-dd'));
        } else {    // 否则默认昨天日期
          $('#resumeStop').val((new Date(today - 24*60*60*1000)).Format('yyyy-MM-dd'));
        }
      }
        
      $('#selectMachineType').change(function() {
        changeProductTypeByMachineType($(this).find('option:selected').val());
      });
      
      $('#selectProductType').change(function() {
        changeOrderNumberByProductType($(this).find('option:selected').val());
      });
      
      selectProfile();
      selectdealerIdMult();
      selectProvinceId();
    });
    
    function page(n,s){
      $("#pageNo").val(n);
      $("#pageSize").val(s);
      $("#searchForm").submit();
          return false;
        }
    
    // 获取允许输入的最小时间
    function getAllowMinDate() {
      var date = new Date();
      date.setDate(1);
      return date;
    }
    
    // 根据主机类别改变主机型号下拉框
    function changeProductTypeByMachineType(vMachineType) {
      $.getJSON('${ctx}/lg/general/query/productTypeList', {machineType: vMachineType}, function(data) {
        if (data) {
          $('#selectProductType').empty();
          $('#selectProductType').append($("<option>").val('').text('全部'));
          $.each(data, function(i, d) {
            $('#selectProductType').append($("<option>").val(d).text(d));
          });
          $('#selectProductType').prop('selectedIndex', 0);
        }
      });
    }
    
    // 根据主机型号改变订货好下拉框
    function changeOrderNumberByProductType(vProductType) {
      $.getJSON('${ctx}/lg/general/query/orderNumberList', {productType: vProductType}, function(data) {
        if (data) {
          $('#selectOrderNumber').empty();
          $('#selectOrderNumber').append($("<option>").val('').text('全部'));
          $.each(data, function(i, d) {
            $('#selectOrderNumber').append($("<option>").val(d.orderNumber).text(d.orderNumber));
          });
          $('#selectOrderNumber').prop('selectedIndex', 0);
        }
      });
    }
    
    // 选择主机
    function selectProfile() {
      $.getJSON('${ctx}/lg/general/query/deviceNoList', function(data) {
        if (data) {
          $('#deviceNoMultBtn').popLayer({
            type: {name:'checkbox'},
            data: {data: data},
            assignId: 'deviceNoMult'
          });
        }
      });
    }
    
    // 选择经销商
    function selectdealerIdMult() {
      $.getJSON('${ctx}/lg/general/query/machineDimensionList', {type: 3}, function(data) {
        if (data) {
          $('#dealerIdMultBtn').popLayer({
            type: {name:'checkbox'},
            data: {data: data, label: 'dimensionName', value: 'id'},
            assignId: 'dealerIdMult'
          });
        }
      });
    }
    
    // 选择工作地域
    function selectProvinceId() {
      $.getJSON('${ctx}/lg/general/query/provinceList', function(data) {
        if (data) {
          $('#workProvinceMultBtn').popLayer({
            type: {name:'checkbox'},
            data: {data: data, label: 'cityName', value: 'id'},
            assignId: 'workProvinceMult'
          });
          
          $('#saleProvinceMultBtn').popLayer({
            type: {name:'checkbox'},
            data: {data: data, label: 'cityName', value: 'id'},
            assignId: 'saleProvinceMult'
          });
        }
      });
    }

    /* 履历图用到的 js begin */
    //时间格式化 转化为ms
	function getMSTime(time) {
	    var msTime
	        // try {
	        //   var timeStamp = new Date(time) //时间戳
	        //   var hour = timeStamp.getHours();
	        //   var minutes = timeStamp.getMinutes();
	        //   var seconds = timeStamp.getSeconds();
	        //   var ms = timeStamp.getMilliseconds();
	        //   msTime = hour * 60 * 60 * 1000 + minutes * 60 * 1000 + seconds * 1000 + ms
	        // } catch (e) {
	        //   var hour = time.split(':')[0]
	        //   var minutes = time.split(':')[1]
	        //   msTime = hour * 60 * 60 * 1000 + minutes * 60 * 1000
	        // }
	    var hour = time.split(':')[0]
	    var minutes = time.split(':')[1]
	    msTime = hour * 60 * 60 * 1000 + minutes * 60 * 1000
	    return msTime
	}
	
	//反序列化时间戳
	function getTime(time) {
	    var times = new Date(time)
	    var hour = times.getHours()
	    var minutes = times.getMinutes()
	    return hour + ':' + minutes
	}
	
	function getRandomcolor(colors) {
	    colors = colors || []
	    var num = ~~(Math.random() * 5)
	    return colors[num] || '#ccc'
	}
	/*
	 * ${data} 机器运行数据
	 * ${machine} 机器编号
	 * ${date} 运行日期
	 */
	function renderColumn(data, machine, date) {
	    // console.log(data)
	    if (data.length === 0) return
	    var _width = $('td').width();
	    var beginTime = getMSTime(data.beginTime); //开始时间
	
	    var endTime = getMSTime(data.endTime); //结束时间
	    var spendTime = (endTime - beginTime) / (1000 * 60 * 60) //运行多少小时
	
	    var leftTime = beginTime / (1000 * 60 * 60) //距离当天零点多长时间
	    var spendDistance = (spendTime / 24) * _width //运行时区域宽度
	    var leftDistance = (leftTime / 24) * _width //运行时区域开始距离每一天单元格最左侧的距离
	        // console.log(leftDistance)
	        //var colors = ['#FA9182', '#B1F6FB', '#C7F7DF', '#EFF5B3', '#E1F3B7']
	    var colors = []
	    var background = getRandomcolor(colors)
	    var tooltipData = {
	        machine_number: machine,
	        address: data.address,
	        beginTime: data.beginTime,
	        endTime: data.endTime,
	        total: data.total,
	        warn: data.warn
	    }
	    tooltipData = JSON.stringify(tooltipData)
	
	    var areaDiv = '<div class="tooltips" style="position: absolute;top:0;background:' +
	        background + ';width:' + spendDistance + 'px;left:' +
	        leftDistance + 'px;"' +
	        ' data-tooltips=\'' + tooltipData +
	        '\'></div>';
	    return areaDiv;
	}
	
	
	//渲染一列
	function render(data, machine, date) {
	    var result = data || [];
	    var tdDiv = '';
	    for (var i = 0; i < data.length; i++) {
	        var areaDiv = renderColumn(data[i], machine, date)
	        tdDiv += areaDiv
	    }
	    return tdDiv
	}
	
	//渲染一行
	function renderRow(data, beginDate, endDate) {
	    var dates = getRequestDates(beginDate, endDate) //获取查询的所有日期
	    var result = data || [];
	    var tr = '';
	    for (var i = 0, len = result.length; i < len; i++) {
	        tr += '<tr>'
	        var machine_number = result[i].machine_number //机器主机编号
	        tr += '<td>' + machine_number + '</td>'; //第一列
	        var datas = result[i].datas; //同一个主机编号的所有数据(相当于一行的所有数据)
	        var hasData = false
	        for (var k = 0; k < dates.length; k++) {
	            for (var j= 0; j < datas.length; j++) {
	                if(datas[j].date == dates[k]) { //判断当前日期是否有数据
	                    hasData = true
	                    tr += '<td>' + render(datas[j].data, machine_number, datas[j].date) + '</td>'
	                    break;
	                } else {
	                    hasData = false
	                }
	            }
	            if(!hasData) {//如果当前日期没有数据，渲染为空
	                tr += '<td></td>'
	            }
	        }
	        tr += '</tr>'
	    }
	    $('tbody').append(tr) //输出到页面显示
	}
	
	//动态显示表格头部日期
	function renderThead(beginDate, endDate) {
	    var head = $('#map thead')
	    var dates = getRequestDates(beginDate, endDate)
	
	    var content = '<tr><td><div class="left">主机编号</div><div class="right">日期</div></td>';
	    dates.map(function(data) {
	        content += '<td>' + data + '</td>'
	    })
	    content += '</tr>'
	    head.append(content)
	}
	
	//获取前端查询的所有日期，过滤起始日期到终止日期的所有天数
	function getRequestDates(beginDate, endDate) {
	    var begeinDate = Date.parse(beginDate) //开始日期
	    var endDate = Date.parse(endDate) //结束日期
	    var dates = []
	    for (var i = begeinDate; i <= endDate; i+= (24 * 60 * 60 * 1000)) {
	        var date = new Date(i)
	        var year = date.getFullYear()
	        var month = date.getMonth() < 10 ? ('0' + (date.getMonth() + 1)) : (date.getMonth() + 1)
	        var day = date.getDate() < 10 ? ('0' + date.getDate()) : date.getDate()
	        dates.push(year + '-' + month + '-' + day)
	    }
	    return dates
	}
	
	
	//获取后台返回数据的所有日期
	function getDates(data) {
	    var datas = data[0].datas // 获取第一个的数据 因为每一个数据项的个数是一样的，即使某天机器未启动，应该返回data=[]
	    var dates = [] //所有日期的集合
	    datas.map(function(data) {
	        var date = data.date.replace(/\-/g, '/')
	        dates.push(date)
	    })
	    return dates
	}
	
	//tooltip
	function toolTip() {
	    $('.tooltips').hover(function() {
	        var data = $(this).attr('data-tooltips')
	        var result = JSON.parse(data) //每一块区域的数据
	        var div = '<div class="map-tooltip">' +
	            '设备编号: &nbsp;' + result.machine_number +
	            '<br>地址: &nbsp;' + result.address +
	            '<br>开始时间: &nbsp;' + result.beginTime +
	            '<br>结束时间: &nbsp;' + result.endTime +
	            '<br>总开工数: &nbsp;' + result.total +
	            '<br>异常报警: &nbsp;' + result.warn +
	            '</div>';
	        var that = $(this)
	        $(this).append(div)
	        var tooltipDiv = that.find('.map-tooltip')
	        var _width = tooltipDiv.width()
	        var _parent = that.parent()
	        if (that.parent().parent().find('td:last').is(_parent)) {
	            tooltipDiv.css({'left': '-' + _width + 'px'})
	        }
	    }, function() {
	        $(this).find('div').remove()
	    })
	}
	
	$(function() {
    $(window).resize(function() {
        $('tbody').html('')
        $('thead').html('')
        var beginDate = $('#resumeStart').val()
        var endDate = $('#resumeStop').val()
        renderThead(beginDate, endDate) //渲染表头
        renderRow(result, beginDate, endDate) //渲染表格内容
    })
})
     
     var result = '';
    // 渲染工作履历图
    function renderResumePic() {
      $.getJSON('getResumeData', {
        machineType: $('#selectMachineType').find('option:selected').val(),
        productType: $('#selectProductType').find('option:selected').val(),
        orderNumber: $('#selectOrderNumber').find('option:selected').val(),
        deviceNoMult: $('#deviceNoMult').val(),
        dealerIdMult: $('#dealerIdMult').val(),
        workProvinceMult: $('#workProvinceMult').val(),
        saleProvinceMult: $('#saleProvinceMult').val(),
        resumeStart: $('#resumeStart').val(),
        resumeStop: $('#resumeStop').val()
      }, function(jsonData) {
        if (jsonData) {
          result = jsonData;
          
          var beginDate = $('#resumeStart').val()
          var endDate = $('#resumeStop').val()
  
          $('tbody').html('')
          $('thead').html('')
          var now = Date.now()
          renderThead(beginDate, endDate) //渲染表头
          renderRow(jsonData, beginDate, endDate) //渲染表格内容
          var after = Date.now()
          console.log('渲染时间: ' + (after - now) + 'ms')
          toolTip() //绑定tooltip
        }
      });
    }
    /* 履历图用到的 js end */
  </script>
</head>
<body>
  <div class="container-fluid">
    <div class="row-fluid">
      <div class="span2 span-rightborder">
        <form:form id="searchForm" modelAttribute="lgDeviceSliceStatics" action="${ctx}/lg/machinestatus/workresume/" method="post" class="form-search .form-horizontal">
          <div class="control-group">
            <label class="control-label">主机类别：</label>
            <div class="controls">
              <form:select id="selectMachineType" path="machineType" class="span12">
                <form:option value="" label="全部"/>
                <form:options items="${fns:getDictList('machineType')}" itemLabel="label" itemValue="value" htmlEscape="false" />
              </form:select>
            </div>
          </div>
          <div class="control-group">
            <label class="control-label">主机型号：</label>
            <div class="controls">
              <form:select id="selectProductType" path="productType" class="span12">
                <form:option value="" label="全部"/>
                <form:options items="${fns:getProductTypeList('')}" htmlEscape="false" />
              </form:select>
            </div>
          </div>
          <div class="control-group">
            <label class="control-label">订货号：</label>
            <div class="controls">
              <form:select id="selectOrderNumber" path="orderNumber" class="span12">
                <form:option value="" label="全部"/>
                <form:options items="${fns:getOrderNumberList('')}" itemLabel="orderNumber" itemValue="orderNumber" htmlEscape="false" />
              </form:select>
            </div>
          </div>
          <div class="control-group">
            <label class="control-label">主机列表：</label>
            <div class="controls">
              <form:hidden id="deviceNoMult" path="deviceNoMult"/>
              <button id="deviceNoMultBtn" type="button" class="btn btn-block" >选择主机</button>
            </div>
          </div>
          <div class="control-group">
            <label class="control-label">经销商列表：</label>
            <div class="controls">
              <form:hidden id="dealerIdMult" path="dealerIdMult"/>
              <button id="dealerIdMultBtn" type="button" class="btn btn-block" >选择经销商</button>
            </div>
          </div>
          <div class="control-group">
            <label class="control-label">工作地域列表：</label>
            <div class="controls">
              <form:hidden id="workProvinceMult" path="workProvinceMult"/>
              <button id="workProvinceMultBtn" type="button" class="btn btn-block" >选择工作地域</button>
            </div>
          </div>
          <div class="control-group">
            <label class="control-label">销售地域列表：</label>
            <div class="controls">
              <form:hidden id="saleProvinceMult" path="saleProvinceMult"/>
              <button id="saleProvinceMultBtn" type="button" class="btn btn-block" >选择销售地域</button>
            </div>
          </div>
          <div class="control-group">
            <label class="control-label">开工日期：</label>
            <div class="controls">
              <input id="resumeStart" name="resumeStart" type="text" readonly="readonly" maxlength="20" class="input-mysize Wdate span12"
                value="<fmt:formatDate value="${lgDeviceSliceStatics.resumeStop}" pattern="yyyy-MM-dd"/>"
                onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
            </div>
          </div>
          <div class="control-group">
            <label class="control-label">结束日期：</label>
            <div class="controls">
              <input id="resumeStop" name="resumeStop" type="text" readonly="readonly" maxlength="20" class="input-mysize Wdate span12" 
                value="<fmt:formatDate value="${lgDeviceSliceStatics.resumeStop}" pattern="yyyy-MM-dd"/>"
                onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
            </div>
          </div>
          <br />
          <div class="control-group">
            <div class="controls">
              <button type="button" class="btn btn-block btn-primary" onclick="renderResumePic()">工作履历分析</button>
            </div>
          </div>
        </form:form>
      </div>
      <div class="span10">
        <table id="map">
            <thead>
            </thead>
            <tbody>
            </tbody>
          </table>
      </div>
    </div>
  </div>
</body>
</html>