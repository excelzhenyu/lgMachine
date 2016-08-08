<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
  <title>机器运行概览</title>
  <meta name="decorator" content="default"/>
  <style type="text/css">
    .input-mysize {
      width: 200px;
    }
    .span-rightborder {
      border-right: 1px solid #f5f5f5;
      padding-right: 15px;
    }
    .padding-top-5 {
      padding-top: 5px
    }
  </style>
  <script type="text/javascript">
    $(document).ready(function() {
      renderWorkProvinceSelect();
      renderWorkStateSelect();
      renderMachineTypeSelect();
      renderProductTypeSelect();
      renderOrderNumberSelect();
      
      myChart1.setOption(option1);
      myChart2.setOption(option2);
      
      $('#yearGreater').val(5);
      
      renderThead();
      
      $('#queryBtn').click(function() {
        myChart1.clear();
        myChart1.setOption(option1);
        myChart2.clear();
        myChart2.setOption(option2);
        
        renderXAxis();
        renderChart();
      });
    });
    
    function page(n,s){
      $("#pageNo").val(n);
      $("#pageSize").val(s);
      $("#searchForm").submit();
          return false;
    }
    
    // 初始化工作地域选框
    function renderWorkProvinceSelect() {
      $.getJSON('${ctx}/lg/general/query/provinceList', function(data) {
        if (data) {
          $('#workProvinceBtn').popLayer({
            type: {name:'checkbox'},
            data: {data: data, label: 'cityName', value: 'cityName'},
            assignId: 'workProvinceMult'
          });
        }
      });
    }
    
    // 初始化工作工况选框
    function renderWorkStateSelect() {
      $.getJSON('${ctx}/lg/general/query/machineDimensionList', {type: 8}, function(data) {
        if (data) {
          $('#workStateBtn').popLayer({
            type: {name:'checkbox'},
            data: {data: data, label: 'dimensionName', value: 'id'},
            assignId: 'workStateMult'
          });
        }
      });
    }
    
    // 初始化主机类别选框
    function renderMachineTypeSelect() {
      $.getJSON('${ctx}/lg/general/query/dictList', {type: 'machineType'}, function(data) {
        if (data) {
          $('#machineTypeBtn').popLayer({
            type: {name:'checkbox'},
            data: {data: data, label: 'label', value: 'value'},
            assignId: 'machineTypeMult'
          });
        }
      });
    }
    
    // 初始化主机型号选框
    function renderProductTypeSelect() {
      $.getJSON('${ctx}/lg/general/query/productTypeList', function(data) {
        if (data) {
          $('#productTypeBtn').popLayer({
            type: {name:'checkbox'},
            data: {data: data},
            assignId: 'productTypeMult'
          });
        }
      });
    }
    
    // 初始化订货号选框
    function renderOrderNumberSelect() {
      $.getJSON('${ctx}/lg/general/query/orderNumberList', function(data) {
        if (data) {
          $('#orderNumberBtn').popLayer({
            type: {name:'checkbox'},
            data: {data: data, label: 'orderNumber', value: 'id'},
            assignId: 'orderNumberMult'
          });
        }
      });
    }
    
    // 渲染图表
    function renderChart() {
      $.getJSON('runningindex/workHourData', {
        yearGreater: $('#yearGreater').val(),
        workDurationGreater: $('#workDurationGreater').val(),
        machineTypeMult: $('#machineTypeMult').val(),
        productTypeMult: $('#productTypeMult').val(),
        orderNumberMult: $('#orderNumberMult').val(),
        workProvinceMult: $('#workProvinceMult').val(),
        workStateMult: $('#workStateMult').val()
      }, function(data) {
        if (data) {
          renderWorkHourChart(data);
          renderalivePersentChart(data);
          
          renderThead();
          renderTbody(data);
        }
      });
    }
    
    // 渲染工作时间分析图表
    function renderWorkHourChart(data) {
      var currYear = new Date().Format('yyyy');
      var supportYear = $('#yearGreater').val();
      
      var legend = [];
      var series = [];
      $.each(data, function(i, d) {
        var name = '型号-' + (d.productType || '') + 
                   ' 订货号-' + (d.orderNumber || '') + 
                   ' 工况-' + (d.workState || '');
        
        var data = [];
        
        for (var i = 0; i < supportYear; i++) {
          if (d[currYear - i]) {
            data.push(d[currYear - i].workHourSvg || 0);
          } else {
            data.push(0);
          }
        }
        
        legend.push(name);
        series.push({
          name: name,
          type: 'line',
          data: data
        });
      });
      
      myChart1.setOption({
        series: series
      });
    }
    
    // 渲染机器活跃分析图标
    function renderalivePersentChart(data) {
      var currYear = new Date().Format('yyyy');
      var supportYear = $('#yearGreater').val();
      
      var legend = [];
      var series = [];
      $.each(data, function(i, d) {
        var name = '型号-' + (d.productType || '') + 
                   ' 订货号-' + (d.orderNumber || '') + 
                   ' 工况-' + (d.workState || '');
        
        var data = [];
        
        for (var i = 0; i < supportYear; i++) {
          if (d[currYear - i]) {
            data.push((d[currYear - i].alivePersent || 0) * 100);
          } else {
            data.push(0);
          }
        }
        
        legend.push(name);
        series.push({
          name: name,
          type: 'line',
          data: data
        });
      });
      
      myChart2.setOption({
        series: series
      });
    }
    
    // 渲染表格头
    function renderThead() {
      var supportYear = $('#yearGreater').val();
      
      var theadStr1 = '';
      theadStr1 += '<tr>';
      theadStr1 += '<th rowspan="2">型号</th>';
      theadStr1 += '<th rowspan="2">订货号</th>';
      theadStr1 += '<th rowspan="2">工况</th>';
      
      var theadStr2 = '';
      theadStr2 += '<tr>';
      var currYear = new Date().Format('yyyy');
      for (var i = 0; i < supportYear; i++) {
        theadStr1 += '<th colspan="6">' + (currYear - i) + '年</th>';
        
        theadStr2 += '<th>机器总数</th>';
        theadStr2 += '<th>活跃机器数</th>';
        theadStr2 += '<th>活跃比</th>';
        theadStr2 += '<th>平均工作小时数</th>';
        theadStr2 += '<th>平局开机次数</th>';
        theadStr2 += '<th>报修比</th>';
      }
      theadStr1 += '</tr>';
      theadStr2 += '</tr>';
      
      $('thead').empty();
      $('thead').append(theadStr1 + theadStr2);
    }
    
    // 渲染表格内容
    function renderTbody(data) {
      var currYear = new Date().Format('yyyy');
      var supportYear = $('#yearGreater').val();
      var tbodyStr = '';
      
      $.each(data, function(i, d) {
        tbodyStr += '<tr>';
        tbodyStr += '<td>' + (d.productType || '') + '</td>';
        tbodyStr += '<td>' + (d.orderNumber || '') + '</td>';
        tbodyStr += '<td>' + (d.workState || '') + '</td>';
        
        for (var i = 0; i < supportYear; i++) {
          if (d[currYear - i]) {
            tbodyStr += '<td>' + d[currYear - i].machineCount || 0 + '</td>';
            tbodyStr += '<td>' + d[currYear - i].aliveCount || 0 + '</td>';
            tbodyStr += '<td>' + (d[currYear - i].alivePersent || 0) * 100 + '%</td>';
            tbodyStr += '<td>' + d[currYear - i].workHourSvg || 0 + '</td>';
            tbodyStr += '<td>' + d[currYear - i].powerOnSvg || 0 + '</td>';
          } else {
            tbodyStr += '<td>0</td>';
            tbodyStr += '<td>0</td>';
            tbodyStr += '<td>0</td>';
            tbodyStr += '<td>0</td>';
            tbodyStr += '<td>0</td>';
          }
          
          tbodyStr += '<td>0</td>';
        }
        
        tbodyStr += '</tr>';
      });
      
      $('tbody').empty();
      $('tbody').append(tbodyStr);
    }
    
    // 设置图标x轴数据
    function renderXAxis() {
      var supportYear = $('#yearGreater').val();
      var currYear = new Date().Format('yyyy');
      
      var xAxis = [];
      for (var i = 0; i < supportYear; i++) {
        xAxis.push(currYear - i + '年');
      }
      
      myChart1.setOption({
        xAxis: {
          data: xAxis
        }
      });
      myChart2.setOption({
        xAxis: {
          data: xAxis
        }
      });
    }
  </script>
</head>
<body>
  <div class="container-fluid">
    <div class="row-fluid">
      <div class="span2 span-rightborder">
        <form:form id="searchForm" modelAttribute="lgRunningIndexCond" action="${ctx}/lg/machinestatus/runningindex/" method="post" class="form-search .form-horizontal">
          <div class="control-group">
            <div class="controls padding-top-5">
              显示售后 <form:input id='yearGreater' path="yearGreater" htmlEscape="false" maxlength="255" class="span4"/> 年
            </div>
          </div>
          <div class="control-group">
            <div class="controls padding-top-5">
              活跃机器：<form:input id='workDurationGreater' path="workDurationGreater" htmlEscape="false" maxlength="255" class="span4"/>小时
              <span class="help-inline">每年度工作大于 xx 小时</span>
            </div>
          </div>
          <div class="control-group">
            <label class="control-label">主机类别：</label>
            <div class="controls">
              <form:hidden id="machineTypeMult" path="machineTypeMult"/>
              <button id="machineTypeBtn" type="button" class="btn btn-block" >主机类别</button>
            </div>
          </div>
          <div class="control-group">
            <label class="control-label">主机型号：</label>
            <div class="controls">
              <form:hidden id="productTypeMult" path="productTypeMult"/>
              <button id="productTypeBtn" type="button" class="btn btn-block" >主机型号</button>
            </div>
          </div>
          <div class="control-group">
            <label class="control-label">订货号：</label>
            <div class="controls">
              <form:hidden id="orderNumberMult" path="orderNumberMult"/>
              <button id="orderNumberBtn" type="button" class="btn btn-block" >订货号</button>
            </div>
          </div>
          <div class="control-group">
            <label class="control-label">地区列表：</label>
            <div class="controls">
              <form:hidden id="workProvinceMult" path="workProvinceMult"/>
              <button id="workProvinceBtn" type="button" class="btn btn-block" >选择地区</button>
            </div>
          </div>
          <div class="control-group">
            <label class="control-label">工作工况：</label>
            <div class="controls">
              <form:hidden id="workStateMult" path="workStateMult"/>
              <button id="workStateBtn" type="button" class="btn btn-block" >选择工作工况</button>
            </div>
          </div>
          <br />
          <div class="control-group">
            <div class="controls">
              <button id="queryBtn" type="button" class="btn btn-block btn-primary">趋势分析</button>
            </div>
          </div>
        </form:form>
      </div>
      <div class="span10">
        <div id='rightTop'>
            <div id='rightTop1' class='span6'></div>
            <div id='rightTop2' class='span6'></div>
        </div>
        <div id='rightBottom'>
            <table id="contentTable" class="table table-striped table-bordered table-condensed">
                <thead></thead>
                <tbody></tbody>
            </table>
        </div>
      </div>
    </div>
  </div>
  <script type="text/javascript">
  // 初始化右侧大小
  var height = document.body.scrollHeight;
  $('#rightTop').height(height * 0.5);
  $('#rightBottom').height(height * 0.5);
  $('#rightRight').height(height);
  var rightTopHeight = $('#rightTop').height();
  $('#rightTop1').height(rightTopHeight);
  $('#rightTop2').height(rightTopHeight);
  
  var myChart1 = echarts.init(document.getElementById('rightTop1'));
  var option1 = {
      title: {
          text: '机器运行趋势分析',
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
  
  var myChart2 = echarts.init(document.getElementById('rightTop2'));
  var option2 = {
      title: {
          text: '机器活跃报修分析',
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
  </script>
</body>
</html>