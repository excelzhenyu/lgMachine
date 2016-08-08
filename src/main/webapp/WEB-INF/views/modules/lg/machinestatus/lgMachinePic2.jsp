<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
  <title>机器画像</title>
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
  }
  thead td {
    border: 1px solid #ccc;
    width: 14.5%;
    text-align: center;
    height: 34px;
    /*background: #E4E4E4;*/
  }
  

  .second {
    width: 70.3125%;
    border-right: 0;
    border-left: 0;
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
    background-image: linear-gradient(to right top, transparent calc(50% - 1px), #DEDEDE, transparent calc(50% + 1px));
  }
  tbody tr {
    border: 1px solid #ccc;
  }
  thead .left, thead .right {
    height: 50px;
    position: absolute;
  }
  thead .left {
    left: 2px;
  }
  thead .right {
    top: 2px;
    right: 6px;
  }
  td {
    position: relative;
  }
  tr td:nth-of-type(1), tr td:nth-of-type(3) {
    border: 1px solid #ccc;
    text-align: center;
  }
  .map-tooltip {
    position: absolute;
    top: 10px;
    left: 25px;
    background: #FFFFFF;
    width: 200px;
    height: 100px;
    border-radius: 3px;
    border: 1px solid #ccc;
    z-index: 100;
    padding-left: 10px;
    /*padding-top: 10px;*/
  }
  .tooltips {
    height: auto;
    bottom: 1px;
    top: 0;
    margin-top: 1px;
    position: absolute;
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
  .line {
    position: absolute;
    border-right: 1px solid #A4A4A4;
    width: 2.08%;
    bottom: 0;
  }
  .short-lines {
    height: calc(30%);
  }
  .large-lines {
    height: calc(60%);
  }
  .second div:nth-of-type(1) {
        left: 0%;
  }

    .second div:nth-of-type(2) {
      left: 2.08333%;
    }
    
    .second div:nth-of-type(3) {
      left: 4.16667%;
    }
    
    .second div:nth-of-type(4) {
      left: 6.25%;
    }
    
    .second div:nth-of-type(5) {
      left: 8.33333%;
    }
    
    .second div:nth-of-type(6) {
      left: 10.41667%;
    }
    
    .second div:nth-of-type(7) {
      left: 12.5%;
    }
    
    .second div:nth-of-type(8) {
      left: 14.58333%;
    }
    
    .second div:nth-of-type(9) {
      left: 16.66667%;
    }
    
    .second div:nth-of-type(10) {
      left: 18.75%;
    }
    
    .second div:nth-of-type(11) {
      left: 20.83333%;
    }
    
    .second div:nth-of-type(12) {
      left: 22.91667%;
    }
    
    .second div:nth-of-type(13) {
      left: 25%;
    }
    
    .second div:nth-of-type(14) {
      left: 27.08333%;
    }
    
    .second div:nth-of-type(15) {
      left: 29.16667%;
    }
    
    .second div:nth-of-type(16) {
      left: 31.25%;
    }
    
    .second div:nth-of-type(17) {
      left: 33.33333%;
    }
    
    .second div:nth-of-type(18) {
      left: 35.41667%;
    }
    
    .second div:nth-of-type(19) {
      left: 37.5%;
    }
    
    .second div:nth-of-type(20) {
      left: 39.58333%;
    }
    
    .second div:nth-of-type(21) {
      left: 41.66667%;
    }
    
    .second div:nth-of-type(22) {
      left: 43.75%;
    }
    
    .second div:nth-of-type(23) {
      left: 45.83333%;
    }
    
    .second div:nth-of-type(24) {
      left: 47.91667%;
    }
    
    .second div:nth-of-type(25) {
      left: 50%;
    }
    
    .second div:nth-of-type(26) {
      left: 52.08333%;
    }
    
    .second div:nth-of-type(27) {
      left: 54.16667%;
    }
    
    .second div:nth-of-type(28) {
      left: 56.25%;
    }
    
    .second div:nth-of-type(29) {
      left: 58.33333%;
    }
    
    .second div:nth-of-type(30) {
      left: 60.41667%;
    }
    
    .second div:nth-of-type(31) {
      left: 62.5%;
    }
    
    .second div:nth-of-type(32) {
      left: 64.58333%;
    }
    
    .second div:nth-of-type(33) {
      left: 66.66667%;
    }
    
    .second div:nth-of-type(34) {
      left: 68.75%;
    }
    
    .second div:nth-of-type(35) {
      left: 70.83333%;
    }
    
    .second div:nth-of-type(36) {
      left: 72.91667%;
    }
    
    .second div:nth-of-type(37) {
      left: 75%;
    }
    
    .second div:nth-of-type(38) {
      left: 77.08333%;
    }
    
    .second div:nth-of-type(39) {
      left: 79.16667%;
    }
    
    .second div:nth-of-type(40) {
      left: 81.25%;
    }
    
    .second div:nth-of-type(41) {
      left: 83.33333%;
    }
    
    .second div:nth-of-type(42) {
      left: 85.41667%;
    }
    
    .second div:nth-of-type(43) {
      left: 87.5%;
    }
    
    .second div:nth-of-type(44) {
      left: 89.58333%;
    }
    
    .second div:nth-of-type(45) {
      left: 91.66667%;
    }
    
    .second div:nth-of-type(46) {
      left: 93.75%;
    }
    
    .second div:nth-of-type(47) {
      left: 95.83333%;
    }
    
    .second div:nth-of-type(48) {
      left: 97.91667%;
    }
  
  .number {
    position: absolute;
    top: -15px;
    right: 2px;
    color: #A4A4A4;
  }
  .color-ul {
    list-style: none;
    width: 100%;
    text-align: center;
    margin: 0;
  }
  .color-ul li {
    display: inline-block;
    width: 4.2%;
    height: 40px;
    margin-bottom: 20px;
    padding-top: 10px;
  }
    ul.color-ul {
    font-size: 0px;
  }

  ul.color-ul li {
    font-size: 14px;
  }
  
  .color {
    height: 15px;
  }
  .time-color {
    width: 100%;
    margin: 0 auto;
  }
  .color-li {
    position: relative;
  }
  .color-number {
    position: absolute;
    width: 100%;
    height: auto;
    left: 0;
    text-align: center;
  }
  .color-li:nth-of-type(1) .color {
    background: #949FFC
  }
  .color-li:nth-of-type(2) .color {
    background: #95AFFB
  }
  .color-li:nth-of-type(3) .color {
    background: #97C2FC
  }
  .color-li:nth-of-type(4) .color {
    background: #96DEFC
  }
  .color-li:nth-of-type(5) .color {
    background: #AFEAFD
  }
  .color-li:nth-of-type(6) .color {
    background: #AEF6FB
  }
  .color-li:nth-of-type(7) .color {
    background: #AFF5E9
  }
  .color-li:nth-of-type(8) .color {
    background: #ADF2DD
  }
  .color-li:nth-of-type(9) .color {
    background: #C6F6DF
  }
  .color-li:nth-of-type(10) .color {
    background: #C6F6D2
  }
  .color-li:nth-of-type(11) .color {
    background: #C7F4C8
  }
  .color-li:nth-of-type(12) .color {
    background: #D0F4C6
  }
  .color-li:nth-of-type(13) .color {
    background: #DBF6C9
  }
  .color-li:nth-of-type(14) .color {
    background: #DBF4B1
  }
  .color-li:nth-of-type(15) .color {
    background: #ECF6B0
  }
  .color-li:nth-of-type(16) .color {
    background: #FEF5B1
  }
  .color-li:nth-of-type(17) .color {
    background: #FDE9B2
  }
  .color-li:nth-of-type(18) .color {
    background: #FECE9B
  }
  .color-li:nth-of-type(19) .color {
    background: #FDC299
  }
  .color-li:nth-of-type(20) .color {
    background: #FBB197
  }
  .color-li:nth-of-type(21) .color {
    background: #FDA094
  }
  * {
    box-sizing: border-box;
  }
  #rightTop {
    margin-top: 5px;
    margin-bottom: 20px;
  } 
  
  #rightBottom {
    clear: both;
  }
  </style>
  <script type="text/javascript">
    $(function() {
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
      
      // 根据主机改变画像源的值
      $('#deviceNoMult').change(function() {
        $.getJSON('${ctx}/lg/general/query/sensorNameList', {deviceNo: $(this).val()}, function(data) {
          if (data) {
            $('#sensorNameSelect').empty();
            $('#sensorNameSelect').append($("<option>").val('').text('全部'));
            $.each(data, function(i, d) {
              $('#sensorNameSelect').append($("<option>").val(d.id).text(d.sensorName));
            });
            $('#sensorNameSelect').prop('selectedIndex', 0);
          }
        });
      });

      selectProfile();
      selectdealerIdMult();
      selectProvinceId();
      
      $('#machinePicBtn').on('click', function() {
        $('#deviceNoWarn').hide();
        if (!$('#deviceNoMult').val()) {
          $('#deviceNoWarn').show();
          return;
        }
        
        myChart.clear();
        myChart.setOption(option);
        myChartRightRight.clear();
        myChartRightRight.setOption(option2);
         
        renderRightTop();
        renderRightBottom();
      });
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
            type: {name:'radio'},
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

    function renderRightBottom() {
      $.getJSON('seriesData', {
        machineType: $('#selectMachineType').find('option:selected').val(),
        productType: $('#selectProductType').find('option:selected').val(),
        orderNumber: $('#selectOrderNumber').find('option:selected').val(),
        orderNumber: $('#sensorNameSelect').find('option:selected').val(),
        deviceNoMult: $('#deviceNoMult').val(),
        dealerIdMult: $('#dealerIdMult').val(),
        workProvinceMult: $('#workProvinceMult').val(),
        saleProvinceMult: $('#saleProvinceMult').val(),
        resumeStart: $('#resumeStart').val(),
        resumeStop: $('#resumeStop').val()
      }, function(data) {
        var xAxisData = [];
        var seriesData = [11, 11, 15, 13, 12, 13, 10];
        
        // 根据查询条件计算x坐标轴数据
        var xStart = $('#resumeStart').val().replace(/-/g,"/");
        var xEnd = ($('#resumeStop').val() || new Date().Format('yyyy-MM-dd')).replace(/-/g,"/");
        
        var betweenDays = new Date(xEnd).getDate() - new Date(xStart).getDate();
        
        for (var i = 0; i < betweenDays + 1; i++) {
          var dateTemp = new Date(xStart);
          xAxisData.push(new Date(dateTemp.setDate(dateTemp.getDate() + i)).Format('yyyy-MM-dd'));
        }
        
        if (data) {
          
        }
        
        myChart.setOption({
          xAxis: {
            data: xAxisData
          },
          series: [
              {
                  name: '日累计工作时间',
                  type: 'line',
                  data: seriesData,
                  markLine: {
                    data: [
                        {type: 'average', name: '平均值'}
                    ]
                  },
                  itemStyle: {
                    normal: {color: '#2ec7c9'}
                  },
                  lineStyle: {
                    normal: {color: '#2ec7c9'}
                  }
              }
          ]
        });
      });
    }

    function renderRightTop() {
      var beginDate = $('#beginDate').val()
      var endDate = $('#endDate').val()
          //此处做前端的查询条件校验
          // if (!beginDate) {
          //  var errorlabel = $('#beginDate').next('label')
          //  errorlabel.html('请输入开始日期')
          //  errorlabel.show()
          //  return false
          // }

      $('tbody').html('')
      $('thead').show()
      $('.time-color').show()
      var now = Date.now()
          // renderThead(result) //渲染表头
      renderRow(result) //渲染表格内容
      var after = Date.now()
      console.log('渲染时间: ' + (after - now) + 'ms')
      // toolTip() //绑定tooltip
    }
  </script>
</head>
<body>
  <div class="container-fluid">
    <div class="row-fluid">
      <div class="span2 span-rightborder">
        <form:form id="searchForm" modelAttribute="lgDeviceSliceStatics" action="${ctx}/lg/machinestatus/machinepic/" method="post" class="form-search .form-horizontal">
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
            <label class="control-label">选择主机：</label>
            <span class="help-inline"><font color="red">*</font> </span>
            <div class="controls">
              <div id='deviceNoWarn' class="alert alert-error" style="display: none">
                必须选择一个主机
              </div>
              <form:hidden id="deviceNoMult" path="deviceNoMult"/>
              <button id="deviceNoMultBtn" type="button" class="btn btn-block" >选择主机</button>
            </div>
          </div>
          <div class="control-group">
            <label class="control-label">画像源：</label>
            <div class="controls">
              <form:select id="sensorNameSelect" path="productType" class="span12">
              </form:select>
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
                value="<fmt:formatDate value="${lgDeviceSliceStatics.resumeStart}" pattern="yyyy-MM-dd"/>"
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
              <button id="machinePicBtn" type="button" class="btn btn-block" onclick="">机器画像</button>
            </div>
          </div>
        </form:form>
      </div>
      <div id='rightDiv' class="span6">
        <div id='rightTop'>
            <table class="time">
		      <thead style="display:none;">
		        <tr>
		          <td>
		            <div class="left">日期</div>
		            <div class="right">时间</div>
		          </td>
		          <td class="second">
		            <div class="line short-lines">
		
		            </div>
		            <div class="line large-lines">
		              <div class="number">
		                1
		              </div>
		            </div>
		            <div class="line short-lines">
		
		            </div>
		            <div class="line large-lines">
		              <div class="number">
		                2
		              </div>
		            </div>
		            <div class="line short-lines">
		
		            </div>
		            <div class="line large-lines">
		              <div class="number">
		                3
		              </div>
		            </div>
		            <div class="line short-lines">
		
		            </div>
		            <div class="line large-lines">
		              <div class="number">
		                4
		              </div>
		            </div>
		            <div class="line short-lines">
		
		            </div>
		            <div class="line large-lines">
		              <div class="number">
		                5
		              </div>
		            </div>
		            <div class="line short-lines">
		
		            </div>
		            <div class="line large-lines">
		              <div class="number">
		                6
		              </div>
		            </div>
		            <div class="line short-lines">
		
		            </div>
		            <div class="line large-lines">
		              <div class="number">
		                7
		              </div>
		            </div>
		            <div class="line short-lines">
		
		            </div>
		            <div class="line large-lines">
		              <div class="number">
		                8
		              </div>
		            </div>
		            <div class="line short-lines">
		
		            </div>
		            <div class="line large-lines">
		              <div class="number">
		                9
		              </div>
		            </div>
		
		            <div class="line short-lines">
		
		            </div>
		            <div class="line large-lines">
		              <div class="number">
		                10
		              </div>
		            </div>
		            <div class="line short-lines">
		
		            </div>
		            <div class="line large-lines">
		              <div class="number">
		                11
		              </div>
		            </div>
		            <div class="line short-lines">
		
		            </div>
		            <div class="line large-lines">
		              <div class="number">
		                12
		              </div>
		            </div>
		            <div class="line short-lines">
		
		            </div>
		            <div class="line large-lines">
		              <div class="number">
		                13
		              </div>
		            </div>
		            <div class="line short-lines">
		
		            </div>
		            <div class="line large-lines">
		              <div class="number">
		                14
		              </div>
		            </div>
		            <div class="line short-lines">
		
		            </div>
		            <div class="line large-lines">
		              <div class="number">
		                15
		              </div>
		            </div>
		            <div class="line short-lines">
		
		            </div>
		            <div class="line large-lines">
		              <div class="number">
		                16
		              </div>
		            </div>
		            <div class="line short-lines">
		
		            </div>
		            <div class="line large-lines">
		              <div class="number">
		                17
		              </div>
		            </div>
		            <div class="line short-lines">
		
		            </div>
		            <div class="line large-lines">
		              <div class="number">
		                18
		              </div>
		            </div>
		            <div class="line short-lines">
		
		            </div>
		            <div class="line large-lines">
		              <div class="number">
		                19
		              </div>
		            </div>
		            <div class="line short-lines">
		
		            </div>
		            <div class="line large-lines">
		              <div class="number">
		                20
		              </div>
		            </div>
		            <div class="line short-lines">
		
		            </div>
		            <div class="line large-lines">
		              <div class="number">
		                21
		              </div>
		            </div>
		            <div class="line short-lines">
		
		            </div>
		            <div class="line large-lines">
		              <div class="number">
		                22
		              </div>
		            </div>
		            <div class="line short-lines">
		
		            </div>
		            <div class="line large-lines">
		              <div class="number">
		                23
		              </div>
		            </div>
		            <div class="line short-lines">
		
		            </div>
		            <div class="line large-lines">
		              <div class="number">
		                24
		              </div>
		            </div>
		          </td>
		          <td>
		            累计工时
		          </td>
		        </tr>
		      </thead>
		      <tbody>
		
		      </tbody>
		    </table>
		
		
		
		    <div class="time-color" style="display: none;">
		      <ul class="color-ul">
		        <li class="color-li">
		          <div class="color"></div>
		          <div class="color-number">0</div>
		        </li>
		        <li class="color-li">
		          <div class="color"></div>
		          <div class="color-number">0.2</div>
		        </li>
		        <li class="color-li">
		          <div class="color"></div>
		          <div class="color-number">0.4</div>
		        </li>
		        <li class="color-li">
		          <div class="color"></div>
		          <div class="color-number">0.6</div>
		        </li>
		        <li class="color-li">
		          <div class="color"></div>
		          <div class="color-number">0.8</div>
		        </li>
		        <li class="color-li">
		          <div class="color"></div>
		          <div class="color-number">1.2</div>
		        </li>
		        <li class="color-li">
		          <div class="color"></div>
		          <div class="color-number">1.4</div>
		        </li>
		        <li class="color-li">
		          <div class="color"></div>
		          <div class="color-number">1.6</div>
		        </li>
		        <li class="color-li">
		          <div class="color"></div>
		          <div class="color-number">1.8</div>
		        </li>
		        <li class="color-li">
		          <div class="color"></div>
		          <div class="color-number">2.0</div>
		        </li>
		
		        <li class="color-li">
		          <div class="color"></div>
		          <div class="color-number">2.2</div>
		        </li>
		        <li class="color-li">
		          <div class="color"></div>
		          <div class="color-number">2.4</div>
		        </li>
		        <li class="color-li">
		          <div class="color"></div>
		          <div class="color-number">2.6</div>
		        </li>
		        <li class="color-li">
		          <div class="color"></div>
		          <div class="color-number">2.8</div>
		        </li>
		        <li class="color-li">
		          <div class="color"></div>
		          <div class="color-number">3.0</div>
		        </li>
		        <li class="color-li">
		          <div class="color"></div>
		          <div class="color-number">3.2</div>
		        </li>
		        <li class="color-li">
		          <div class="color"></div>
		          <div class="color-number">3.4</div>
		        </li>
		        <li class="color-li">
		          <div class="color"></div>
		          <div class="color-number">3.6</div>
		        </li>
		        <li class="color-li">
		          <div class="color"></div>
		          <div class="color-number">3.8</div>
		        </li>
		        <li class="color-li">
		          <div class="color"></div>
		          <div class="color-number">4.0</div>
		        </li>
		
		      </ul>
		    </div>
        </div>
        <div id='rightBottom'></div>
      </div>
      <div id='rightRight' class='span4'></div>
    </div>
  </div>
  
  <script type="text/javascript">
  var height = document.body.scrollHeight;
  $('#rightTop').height(height * 0.5);
  $('#rightBottom').height(height * 0.5);
  $('#rightRight').height(height);
  
  var myChart = echarts.init(document.getElementById('rightBottom'));
  var option = {
      backgroundColor: '#1b1b1b',
      tooltip : {
          formatter: "{a} <br/>{c} {b}"
      },
      toolbox: {
          show : true,
          feature : {
              mark : {show: true},
              restore : {show: true},
              saveAsImage : {show: true}
          }
      },
      series : [
          {
              name:'速度',
              type:'gauge',
              min:0,
              max:220,
              splitNumber:11,
              radius: '50%',
              axisLine: {            // 坐标轴线
                  lineStyle: {       // 属性lineStyle控制线条样式
                      color: [[0.09, 'lime'],[0.82, '#1e90ff'],[1, '#ff4500']],
                      width: 3,
                      shadowColor : '#fff', //默认透明
                      shadowBlur: 10
                  }
              },
              axisLabel: {            // 坐标轴小标记
                  textStyle: {       // 属性lineStyle控制线条样式
                      fontWeight: 'bolder',
                      color: '#fff',
                      shadowColor : '#fff', //默认透明
                      shadowBlur: 10
                  }
              },
              axisTick: {            // 坐标轴小标记
                  length :15,        // 属性length控制线长
                  lineStyle: {       // 属性lineStyle控制线条样式
                      color: 'auto',
                      shadowColor : '#fff', //默认透明
                      shadowBlur: 10
                  }
              },
              splitLine: {           // 分隔线
                  length :25,         // 属性length控制线长
                  lineStyle: {       // 属性lineStyle（详见lineStyle）控制线条样式
                      width:3,
                      color: '#fff',
                      shadowColor : '#fff', //默认透明
                      shadowBlur: 10
                  }
              },
              pointer: {           // 分隔线
                  shadowColor : '#fff', //默认透明
                  shadowBlur: 5
              },
              title : {
                  textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                      fontWeight: 'bolder',
                      fontSize: 20,
                      fontStyle: 'italic',
                      color: '#fff',
                      shadowColor : '#fff', //默认透明
                      shadowBlur: 10
                  }
              },
              detail : {
                  backgroundColor: 'rgba(30,144,255,0.8)',
                  borderWidth: 1,
                  borderColor: '#fff',
                  shadowColor : '#fff', //默认透明
                  shadowBlur: 5,
                  offsetCenter: [0, '50%'],       // x, y，单位px
                  textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                      fontWeight: 'bolder',
                      color: '#fff'
                  }
              },
              data:[{value: 40, name: 'km/h'}]
          },
          {
              name:'转速',
              type:'gauge',
              center : ['25%', '55%'],    // 默认全局居中
              radius : '30%',
              min:0,
              max:7,
              endAngle:45,
              splitNumber:7,
              axisLine: {            // 坐标轴线
                  lineStyle: {       // 属性lineStyle控制线条样式
                      color: [[0.29, 'lime'],[0.86, '#1e90ff'],[1, '#ff4500']],
                      width: 2,
                      shadowColor : '#fff', //默认透明
                      shadowBlur: 10
                  }
              },
              axisLabel: {            // 坐标轴小标记
                  textStyle: {       // 属性lineStyle控制线条样式
                      fontWeight: 'bolder',
                      color: '#fff',
                      shadowColor : '#fff', //默认透明
                      shadowBlur: 10
                  }
              },
              axisTick: {            // 坐标轴小标记
                  length :12,        // 属性length控制线长
                  lineStyle: {       // 属性lineStyle控制线条样式
                      color: 'auto',
                      shadowColor : '#fff', //默认透明
                      shadowBlur: 10
                  }
              },
              splitLine: {           // 分隔线
                  length :20,         // 属性length控制线长
                  lineStyle: {       // 属性lineStyle（详见lineStyle）控制线条样式
                      width:3,
                      color: '#fff',
                      shadowColor : '#fff', //默认透明
                      shadowBlur: 10
                  }
              },
              pointer: {
                  width:5,
                  shadowColor : '#fff', //默认透明
                  shadowBlur: 5
              },
              title : {
                  offsetCenter: [0, '-30%'],       // x, y，单位px
                  textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                      fontWeight: 'bolder',
                      fontStyle: 'italic',
                      color: '#fff',
                      shadowColor : '#fff', //默认透明
                      shadowBlur: 10
                  }
              },
              detail : {
                  //backgroundColor: 'rgba(30,144,255,0.8)',
                 // borderWidth: 1,
                  borderColor: '#fff',
                  shadowColor : '#fff', //默认透明
                  shadowBlur: 5,
                  width: 80,
                  height:30,
                  offsetCenter: [25, '20%'],       // x, y，单位px
                  textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                      fontWeight: 'bolder',
                      color: '#fff'
                  }
              },
              data:[{value: 1.5, name: 'x1000 r/min'}]
          },
          {
              name:'油表',
              type:'gauge',
              center : ['75%', '50%'],    // 默认全局居中
              radius : '30%',
              min:0,
              max:2,
              startAngle:135,
              endAngle:45,
              splitNumber:2,
              axisLine: {            // 坐标轴线
                  lineStyle: {       // 属性lineStyle控制线条样式
                      color: [[0.2, 'lime'],[0.8, '#1e90ff'],[1, '#ff4500']],
                      width: 2,
                      shadowColor : '#fff', //默认透明
                      shadowBlur: 10
                  }
              },
              axisTick: {            // 坐标轴小标记
                  length :12,        // 属性length控制线长
                  lineStyle: {       // 属性lineStyle控制线条样式
                      color: 'auto',
                      shadowColor : '#fff', //默认透明
                      shadowBlur: 10
                  }
              },
              axisLabel: {
                  textStyle: {       // 属性lineStyle控制线条样式
                      fontWeight: 'bolder',
                      color: '#fff',
                      shadowColor : '#fff', //默认透明
                      shadowBlur: 10
                  },
                  formatter:function(v){
                      switch (v + '') {
                          case '0' : return 'E';
                          case '1' : return 'Gas';
                          case '2' : return 'F';
                      }
                  }
              },
              splitLine: {           // 分隔线
                  length :15,         // 属性length控制线长
                  lineStyle: {       // 属性lineStyle（详见lineStyle）控制线条样式
                      width:3,
                      color: '#fff',
                      shadowColor : '#fff', //默认透明
                      shadowBlur: 10
                  }
              },
              pointer: {
                  width:2,
                  shadowColor : '#fff', //默认透明
                  shadowBlur: 5
              },
              title : {
                  show: false
              },
              detail : {
                  show: false
              },
              data:[{value: 0.5, name: 'gas'}]
          },
          {
              name:'水表',
              type:'gauge',
              center : ['75%', '50%'],    // 默认全局居中
              radius : '30%',
              min:0,
              max:2,
              startAngle:315,
              endAngle:225,
              splitNumber:2,
              axisLine: {            // 坐标轴线
                  lineStyle: {       // 属性lineStyle控制线条样式
                      color: [[0.2, 'lime'],[0.8, '#1e90ff'],[1, '#ff4500']],
                      width: 2,
                      shadowColor : '#fff', //默认透明
                      shadowBlur: 10
                  }
              },
              axisTick: {            // 坐标轴小标记
                  show: false
              },
              axisLabel: {
                  textStyle: {       // 属性lineStyle控制线条样式
                      fontWeight: 'bolder',
                      color: '#fff',
                      shadowColor : '#fff', //默认透明
                      shadowBlur: 10
                  },
                  formatter:function(v){
                      switch (v + '') {
                          case '0' : return 'H';
                          case '1' : return 'Water';
                          case '2' : return 'C';
                      }
                  }
              },
              splitLine: {           // 分隔线
                  length :15,         // 属性length控制线长
                  lineStyle: {       // 属性lineStyle（详见lineStyle）控制线条样式
                      width:3,
                      color: '#fff',
                      shadowColor : '#fff', //默认透明
                      shadowBlur: 10
                  }
              },
              pointer: {
                  width:2,
                  shadowColor : '#fff', //默认透明
                  shadowBlur: 5
              },
              title : {
                  show: false
              },
              detail : {
                  show: false
              },
              data:[{value: 0.5, name: 'gas'}]
          }
      ]
  };
  
  var myChartRightRight = echarts.init(document.getElementById('rightRight'));
  
  var geoCoordMap = {
      '上海': [121.4648,31.2891],
      '东莞': [113.8953,22.901],
      '东营': [118.7073,37.5513],
      '中山': [113.4229,22.478],
      '临汾': [111.4783,36.1615],
      '临沂': [118.3118,35.2936],
      '丹东': [124.541,40.4242],
      '丽水': [119.5642,28.1854],
      '乌鲁木齐': [87.9236,43.5883],
      '佛山': [112.8955,23.1097],
      '保定': [115.0488,39.0948],
      '兰州': [103.5901,36.3043],
      '包头': [110.3467,41.4899],
      '北京': [116.4551,40.2539],
      '北海': [109.314,21.6211],
      '南京': [118.8062,31.9208],
      '南宁': [108.479,23.1152],
      '南昌': [116.0046,28.6633],
      '南通': [121.1023,32.1625],
      '厦门': [118.1689,24.6478],
      '台州': [121.1353,28.6688],
      '合肥': [117.29,32.0581],
      '呼和浩特': [111.4124,40.4901],
      '咸阳': [108.4131,34.8706],
      '哈尔滨': [127.9688,45.368],
      '唐山': [118.4766,39.6826],
      '嘉兴': [120.9155,30.6354],
      '大同': [113.7854,39.8035],
      '大连': [122.2229,39.4409],
      '天津': [117.4219,39.4189],
      '太原': [112.3352,37.9413],
      '威海': [121.9482,37.1393],
      '宁波': [121.5967,29.6466],
      '宝鸡': [107.1826,34.3433],
      '宿迁': [118.5535,33.7775],
      '常州': [119.4543,31.5582],
      '广州': [113.5107,23.2196],
      '廊坊': [116.521,39.0509],
      '延安': [109.1052,36.4252],
      '张家口': [115.1477,40.8527],
      '徐州': [117.5208,34.3268],
      '德州': [116.6858,37.2107],
      '惠州': [114.6204,23.1647],
      '成都': [103.9526,30.7617],
      '扬州': [119.4653,32.8162],
      '承德': [117.5757,41.4075],
      '拉萨': [91.1865,30.1465],
      '无锡': [120.3442,31.5527],
      '日照': [119.2786,35.5023],
      '昆明': [102.9199,25.4663],
      '杭州': [119.5313,29.8773],
      '枣庄': [117.323,34.8926],
      '柳州': [109.3799,24.9774],
      '株洲': [113.5327,27.0319],
      '武汉': [114.3896,30.6628],
      '汕头': [117.1692,23.3405],
      '江门': [112.6318,22.1484],
      '沈阳': [123.1238,42.1216],
      '沧州': [116.8286,38.2104],
      '河源': [114.917,23.9722],
      '泉州': [118.3228,25.1147],
      '泰安': [117.0264,36.0516],
      '泰州': [120.0586,32.5525],
      '济南': [117.1582,36.8701],
      '济宁': [116.8286,35.3375],
      '海口': [110.3893,19.8516],
      '淄博': [118.0371,36.6064],
      '淮安': [118.927,33.4039],
      '深圳': [114.5435,22.5439],
      '清远': [112.9175,24.3292],
      '温州': [120.498,27.8119],
      '渭南': [109.7864,35.0299],
      '湖州': [119.8608,30.7782],
      '湘潭': [112.5439,27.7075],
      '滨州': [117.8174,37.4963],
      '潍坊': [119.0918,36.524],
      '烟台': [120.7397,37.5128],
      '玉溪': [101.9312,23.8898],
      '珠海': [113.7305,22.1155],
      '盐城': [120.2234,33.5577],
      '盘锦': [121.9482,41.0449],
      '石家庄': [114.4995,38.1006],
      '福州': [119.4543,25.9222],
      '秦皇岛': [119.2126,40.0232],
      '绍兴': [120.564,29.7565],
      '聊城': [115.9167,36.4032],
      '肇庆': [112.1265,23.5822],
      '舟山': [122.2559,30.2234],
      '苏州': [120.6519,31.3989],
      '莱芜': [117.6526,36.2714],
      '菏泽': [115.6201,35.2057],
      '营口': [122.4316,40.4297],
      '葫芦岛': [120.1575,40.578],
      '衡水': [115.8838,37.7161],
      '衢州': [118.6853,28.8666],
      '西宁': [101.4038,36.8207],
      '西安': [109.1162,34.2004],
      '贵阳': [106.6992,26.7682],
      '连云港': [119.1248,34.552],
      '邢台': [114.8071,37.2821],
      '邯郸': [114.4775,36.535],
      '郑州': [113.4668,34.6234],
      '鄂尔多斯': [108.9734,39.2487],
      '重庆': [107.7539,30.1904],
      '金华': [120.0037,29.1028],
      '铜川': [109.0393,35.1947],
      '银川': [106.3586,38.1775],
      '镇江': [119.4763,31.9702],
      '长春': [125.8154,44.2584],
      '长沙': [113.0823,28.2568],
      '长治': [112.8625,36.4746],
      '阳泉': [113.4778,38.0951],
      '青岛': [120.4651,36.3373],
      '韶关': [113.7964,24.7028]
  };

  var BJData = [
      [{name:'北京'}, {name:'上海',value:95}],
      [{name:'北京'}, {name:'广州',value:90}],
      [{name:'北京'}, {name:'大连',value:80}],
      [{name:'北京'}, {name:'南宁',value:70}],
      [{name:'北京'}, {name:'南昌',value:60}],
      [{name:'北京'}, {name:'拉萨',value:50}],
      [{name:'北京'}, {name:'长春',value:40}],
      [{name:'北京'}, {name:'包头',value:30}],
      [{name:'北京'}, {name:'重庆',value:20}],
      [{name:'北京'}, {name:'常州',value:10}]
  ];

  var SHData = [
      [{name:'上海'},{name:'包头',value:95}],
      [{name:'上海'},{name:'昆明',value:90}],
      [{name:'上海'},{name:'广州',value:80}],
      [{name:'上海'},{name:'郑州',value:70}],
      [{name:'上海'},{name:'长春',value:60}],
      [{name:'上海'},{name:'重庆',value:50}],
      [{name:'上海'},{name:'长沙',value:40}],
      [{name:'上海'},{name:'北京',value:30}],
      [{name:'上海'},{name:'丹东',value:20}],
      [{name:'上海'},{name:'大连',value:10}]
  ];

  var GZData = [
      [{name:'广州'},{name:'福州',value:95}],
      [{name:'广州'},{name:'太原',value:90}],
      [{name:'广州'},{name:'长春',value:80}],
      [{name:'广州'},{name:'重庆',value:70}],
      [{name:'广州'},{name:'西安',value:60}],
      [{name:'广州'},{name:'成都',value:50}],
      [{name:'广州'},{name:'常州',value:40}],
      [{name:'广州'},{name:'北京',value:30}],
      [{name:'广州'},{name:'北海',value:20}],
      [{name:'广州'},{name:'海口',value:10}]
  ];

  var planePath = 'path://M1705.06,1318.313v-89.254l-319.9-221.799l0.073-208.063c0.521-84.662-26.629-121.796-63.961-121.491c-37.332-0.305-64.482,36.829-63.961,121.491l0.073,208.063l-319.9,221.799v89.254l330.343-157.288l12.238,241.308l-134.449,92.931l0.531,42.034l175.125-42.917l175.125,42.917l0.531-42.034l-134.449-92.931l12.238-241.308L1705.06,1318.313z';

  var convertData = function (data) {
      var res = [];
      for (var i = 0; i < data.length; i++) {
          var dataItem = data[i];
          var fromCoord = geoCoordMap[dataItem[0].name];
          var toCoord = geoCoordMap[dataItem[1].name];
          if (fromCoord && toCoord) {
              res.push({
                  fromName: dataItem[0].name,
                  toName: dataItem[1].name,
                  coords: [fromCoord, toCoord]
              });
          }
      }
      return res;
  };

  var color = ['#a6c84c', '#ffa022', '#46bee9'];
  var series = [];
  [['北京', BJData], ['上海', SHData], ['广州', GZData]].forEach(function (item, i) {
      series.push({
          name: item[0] + ' Top10',
          type: 'lines',
          zlevel: 1,
          effect: {
              show: true,
              period: 6,
              trailLength: 0.7,
              color: '#fff',
              symbolSize: 3
          },
          lineStyle: {
              normal: {
                  color: color[i],
                  width: 0,
                  curveness: 0.2
              }
          },
          data: convertData(item[1])
      },
      {
          name: item[0] + ' Top10',
          type: 'lines',
          zlevel: 2,
          effect: {
              show: true,
              period: 6,
              trailLength: 0,
              symbol: planePath,
              symbolSize: 15
          },
          lineStyle: {
              normal: {
                  color: color[i],
                  width: 1,
                  opacity: 0.4,
                  curveness: 0.2
              }
          },
          data: convertData(item[1])
      },
      {
          name: item[0] + ' Top10',
          type: 'effectScatter',
          coordinateSystem: 'geo',
          zlevel: 2,
          rippleEffect: {
              brushType: 'stroke'
          },
          label: {
              normal: {
                  show: true,
                  position: 'right',
                  formatter: '{b}'
              }
          },
          symbolSize: function (val) {
              return val[2] / 8;
          },
          itemStyle: {
              normal: {
                  color: color[i]
              }
          },
          data: item[1].map(function (dataItem) {
              return {
                  name: dataItem[1].name,
                  value: geoCoordMap[dataItem[1].name].concat([dataItem[1].value])
              };
          })
      });
  });

  var option2 = {
      backgroundColor: '#404a59',
      title : {
          text: '模拟迁徙',
          subtext: '数据纯属虚构',
          left: 'center',
          textStyle : {
              color: '#fff'
          }
      },
      tooltip : {
          trigger: 'item'
      },
      legend: {
          orient: 'vertical',
          top: 'bottom',
          left: 'right',
          data:['北京 Top10', '上海 Top10', '广州 Top10'],
          textStyle: {
              color: '#fff'
          },
          selectedMode: 'single'
      },
      geo: {
          map: 'china',
          label: {
              emphasis: {
                  show: false
              }
          },
          roam: true,
          itemStyle: {
              normal: {
                  areaColor: '#323c48',
                  borderColor: '#404a59'
              },
              emphasis: {
                  areaColor: '#2a333d'
              }
          }
      },
      series: series
  };
  
  //时间暂时未设置成时间戳
  var result = [{
          date: '2016-06-01',
          datas: [{
              beginTime: '02:20',
              endTime: '06:30',
              average: 1.1
          }, {
              beginTime: '08:13',
              endTime: '11:23',
              average: 1.3
          }, {
              beginTime: '13:11',
              endTime: '17:56',
              average: 0.9
          }],
          average: 1.5,
          total: 10
      }, {
          date: '2016-06-02',
          datas: [{
              beginTime: '01:17',
              endTime: '05:52',
              average: 2.1
          }, {
              beginTime: '08:29',
              endTime: '11:53',
              average: 2.3
          }, {
              beginTime: '13:30',
              endTime: '18:56',
              average: 3.8
          }],
          average: 1.6,
          total: 15
      }, {
          date: '2016-06-03',
          datas: [{
              beginTime: '05:11',
              endTime: '09:20',
              average: 1.9
          }, {
              beginTime: '9:29',
              endTime: '11:59',
              average: 3.1
          }, {
              beginTime: '13:53',
              endTime: '19:59',
              average: 1.6
          }, {
              beginTime: '21:21',
              endTime: '23:20',
              average: 3.9
          }],
          average: 1.0,
          total: 13
      }

  ]

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

  function renderColumn(data, average) {
      if (data.length === 0) return
      var _width = $('thead td').eq(1).width();
      // console.log(_width)
      var beginTime = getMSTime(data.beginTime); //开始时间

      var endTime = getMSTime(data.endTime); //结束时间
      var spendTime = (endTime - beginTime) / (1000 * 60 * 60) //运行多少小时

      var leftTime = beginTime / (1000 * 60 * 60) //距离当天零点多长时间
      var spendDistance = (spendTime / 24) * _width //运行时区域宽度
      var leftDistance = (leftTime / 24) * _width //运行时区域开始距离每一天单元格最左侧的距离

      var background = getColor(data.average, average) //获取计算后的颜色值
      var tooltipData = {
          beginTime: data.beginTime,
          endTime: data.endTime,
          average: data.average,

      }
      tooltipData = JSON.stringify(tooltipData)

      var areaDiv = '<div class="tooltips" style="background:' +
          background + ';width:' + spendDistance + 'px;left:' +
          leftDistance + 'px;"' +
          ' data-tooltips=\'' + tooltipData +
          '\'></div>';
      return areaDiv;
  }

  //时间段分布颜色值
  var colors = {
      '0': '#949FFC',
      '0.2': '#95AFFB',
      '0.4': '#97C2FC',
      '0.6': '#96DEFC',
      '0.8': '#AFEAFD',
      '1.0': '#AEF6FB',
      '1.2': '#AFF5E9',
      '1.4': '#ADF2DD',
      '1.6': '#C6F6DF',
      '1.8': '#C6F6D2',
      '2.0': '#C7F4C8',
      '2.2': '#D0F4C6',
      '2.4': '#DBF6C9',
      '2.6': '#DBF4B1',
      '2.8': '#ECF6B0',
      '3.0': '#FEF5B1',
      '3.2': '#FDE9B2',
      '3.4': '#FECE9B',
      '3.6': '#FDC299',
      '3.8': '#FBB197',
      '4.0': '#FDA094'
  }

  function getColor(averageData, average) {
      var diff = Math.abs(averageData - average) //计算当前数值与平均值的差 取绝对值
      if (!Object.keys) {
      Object.keys = function(o) {
          if (o !== Object(o))
              throw new TypeError('Object.keys called on a non-object');
          var k = [],
              p;
          for (p in o)
              if (Object.prototype.hasOwnProperty.call(o, p)) k.push(p);
          return k;
      }
    }
    var bgColor = '#ccc'
    var colorKeys = Object.keys(colors)
    colorKeys.forEach(function(item, index) {
      var num = +item // 转换key为数字
      if (diff === 0 && index === 0) {
        bgColor = colors[item];
      } else if ((diff <= num) && (diff > +colorKeys[index - 1])) {
        bgColor = colors[item]
      }
    })
    return bgColor
  }


  //渲染一列
  function render(data, average) {
      var result = data || [];
      var tdDiv = '';
      for (var i = 0; i < data.length; i++) {
          var areaDiv = renderColumn(data[i], average)
          tdDiv += areaDiv
      }
      return tdDiv
  }

  //渲染一行
  function renderRow(data) {

      var result = data || [];
      var tr = '';
      for (var i = 0, len = result.length; i < len; i++) {
          tr += '<tr>'
          var date = result[i].date //日期
          var average = result[i].average
          tr += '<td>' + formateDate(date) + '</td>'; //第一列
          var datas = result[i].datas; //第二列所有数据
          tr += '<td>' + render(datas, average) + '</td>' //第二列
          tr += '<td>' + result[i].total + '</td>' // 第三列
          tr += '</tr>'
      }
      $('tbody').append(tr) //输出到页面显示
  }


  function formateDate(date) {
      return date.replace(/\-/g, '/')
  }
  
  //tooltip
  function toolTip() {
      $('.tooltips').hover(function() {
          var data = $(this).attr('data-tooltips')
          var result = JSON.parse(data) //每一块区域的数据
          var div = '<div class="map-tooltip">' +
              '<br>开始时间: &nbsp;' + result.beginTime +
              '<br>结束时间: &nbsp;' + result.endTime +
              '</div>';
          $(this).append(div)
      }, function() {
          $(this).find('div').remove()
      })
  }
  </script>
</body>
</html>