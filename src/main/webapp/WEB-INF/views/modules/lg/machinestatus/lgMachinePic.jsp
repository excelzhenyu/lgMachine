<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
  <title>机器画像</title>
  <meta name="decorator" content="default"/>
  <script src="${ctxStatic}/lgJs/barTable.js" type="text/javascript"></script>
  <link href="${ctxStatic}/lgJs/barTable.css" rel="stylesheet" />
  <style type="text/css">
    .input-mysize {
      width: 200px;
    }
    .span-rightborder {
      border-right: 1px solid #f5f5f5;
      padding-right: 15px;
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
        
      barTableNS.init($('#rightTop'));
      myChart.setOption(option);
        
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
      
      $.getJSON('', {}, function(data, status) {
        
      }); 
      
      barTableNS.render(result);
      // toolTip() //绑定tooltip
    }
    //当页面大小该表时重新计算，重新渲染切片的宽度
    /* $(window).resize(function() {
			$('tbody').html('')
			renderRow(result)
		}) */
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
              <button id="machinePicBtn" type="button" class="btn btn-block btn-primary">机器画像</button>
            </div>
          </div>
        </form:form>
      </div>
      <div id='rightDiv' class="span10">
        <div id='rightTop'></div>
        <div id='rightBottom'></div>
      </div>
    </div>
  </div>
  
  <script type="text/javascript">
  var height = document.body.scrollHeight;
  //$('#rightTop').height(height * 0.5);
  $('#rightBottom').height(height * 0.5);
  
  var myChart = echarts.init(document.getElementById('rightBottom'));
  
  var option = {
      title: {
          text: '日累计工作时间趋势分析',
          x: 'center'
      },
      tooltip: {
          trigger: 'axis'
      },
      legend: {
          data:['日累计工作时间'],
          left: 'left'
      },
      xAxis:  {
          type: 'category',
          boundaryGap: false,
          data: []
      },
      yAxis: {
          type: 'value'
      }
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
  
  for (var i = 0; i < 20; i++) {
      result.push({
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
      });
  }
  </script>
</body>
</html>