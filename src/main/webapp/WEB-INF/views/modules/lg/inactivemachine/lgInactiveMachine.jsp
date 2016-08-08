<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
  <title>呆滞机智能调拨</title>
  <meta name="decorator" content="default"/>
  <%@include file="/WEB-INF/views/include/treeview.jsp" %> 
  <style type="text/css">
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
      renderDemandCitySelect();
      renderMachineTypeSelect();
      renderProductTypeSelect();
      renderOrderNumberSelect();
      
      myChart.setOption(option, true);
      renderThead();
      
      $('#inactiveMonth').val(6);
      $('input[name="orderType"][value="1"]').attr('checked', 'checked'); 
      
      $('#queryBtn').click(function() {
        renderChart();
      });
    });
    
    function page(n,s){
      $("#pageNo").val(n);
      $("#pageSize").val(s);
      $("#searchForm").submit();
          return false;
    }
    
    // 初始化城市选框
    function renderDemandCitySelect() {
      $('#demandCityBtn').popLayer({ 
        type: {name:'custom', content: '<div id="cityTree" class="ztree"></div>'}, 
        assignId: 'demandCity' 
      }); 
       
      $.getJSON('${ctx}/lg/general/query/systemCityList', function(data, status) { 
        if (status != 'success') { 
          console.log('systemCityList status: ' + status); 
          return; 
        }
        
        $('#cityTree').append('<button type="button" class="btn" onclick="$(\'[id$=_pop]\').hide();">确定</button>');
        
        var setting = { 
            check: { 
                enable: true, 
                chkStyle: "radio", 
                radioType: "all" 
            }, 
            view: { 
              dblClickExpand: false 
          }, 
          data: { 
              simpleData: { 
                  enable: true 
              } 
          }, 
          callback: { 
              onCheck: onCheck
          } 
        }; 
         
        var rootId = ''; 
        $.each(data, function(i, d) { 
          if (d.parentId == '0') { 
            rootId = d.id; 
            return false; 
          } 
        }); 
         
        var zNodes = []; 
        $.each(data, function(i, d) { 
          var obj = { 
            id: d.id,  
            pId: (d.parentId || 0), 
            name: (d.cityName || '城市列表'), 
            cityCode: (d.cityCode || 'cityCode'), 
            isort: (d.isort || 'isort') 
          }; 
          if (d.parentId == '0')  
            obj.open = true; 
           
          if (d.parentId == '0' || d.parentId == rootId) 
            obj.chkDisabled  = true; 
             
          zNodes.push(obj); 
        }); 
         
        $.fn.zTree.init($("#cityTree"), setting, zNodes); 
      });
    }
    
    function onCheck(e, treeId, treeNode) { 
      if(treeNode && treeNode.checked) {
        $('#demandCity').val(treeNode.id);
        $('#demandCityName').val(treeNode.name);
      }
      
      return false; 
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
      $.getJSON('data', {
        machineTypeMult: $('#machineTypeMult').val(),
        productTypeMult: $('#productTypeMult').val(),
        orderNumberMult: $('#orderNumberMult').val(),
        inactiveMonth: ($('#inactiveMonth').val() || 0),
        demandCity: $('#demandCity').val(), 
        orderType: $('input[name="orderType"]:checked').val() 
      }, function(data, status) {
        if (status != 'success') {
          console.log('status: ' + status);
          return;
        }
        
        myChart.setOption(option, true);
        
        var color = ['#a6c84c', '#ffa022', '#46bee9'];
        var arr = [];
        var arr2 = [];
        $.each(data.data, function(i, d) {
          var innerArr = [];
          innerArr.push({coord: [d.longitude, d.latitude], name: ''});
          innerArr.push({coord: [(data.demandCityLng || 0), (data.demandCityLat || 0)], name: ''});
          arr.push(innerArr);
          
          arr2.push([d.longitude, d.latitude, d.inactiveDays]);
        });
        
        var demandNum = ($('#demandNum').val() || 0);
        
        myChart.setOption({
          legend: {data: [$('#demandCityName').val(), '呆滞机']},
          series: [{
            name: '呆滞机',
            type: 'lines',
            zlevel: 1,
            effect: {
                show: true,
                period: 6,
                trailLength: 0.7,
                //color: '#fff',
                symbolSize: 3
            },
            lineStyle: {
                normal: {
                    color: color[0],
                    width: 0,
                    curveness: 0.2
                }
            },
            data: arr.slice(0, demandNum),
        },
        {
            name: '呆滞机',
            type: 'lines',
            zlevel: 2,
            effect: {
                show: true,
                period: 6,
                trailLength: 0,
                symbol: 'triangle',//planePath, 'circle', 'rect', 'roundRect', 'triangle', 'diamond', 'pin', 'arrow'
                symbolSize: 3
            },
            lineStyle: {
                normal: {
                    color: color[0],
                    width: 1,
                    opacity: 0.4,
                    curveness: 0.2
                }
            },
            data: arr.slice(0, demandNum),// convertData(item[1])
        },
        {
            name: $('#demandCityName').val(),
            type: 'effectScatter',
            coordinateSystem: 'geo',
            zlevel: 3,
            showEffectOn: 'render',
            rippleEffect: {
                brushType: 'stroke'
            },
            label: {
                normal: {
                    show: false,
                    position: 'right',
                    formatter: '{b}'
                }
            },
            symbolSize: 8,
            itemStyle: {
              normal: {
                color: 'rgba(37, 140, 249, 0.8)'
              }
            },
            data: [{value: [(data.demandCityLng || 0), (data.demandCityLat || 0)]}]
          },
          {
            name: '呆滞机',
            type: 'scatter',
            coordinateSystem: 'geo',
            zlevel: 3,
            rippleEffect: {
                brushType: 'stroke'
            },
            symbolSize: function (val) { 
              return val[2] / 80; 
            },
            label: {
                normal: {
                    show: false,
                    position: 'right',
                    formatter: '{b}'
                }
            },
            itemStyle: {
              normal: {
                color: color[0]
              }
            },
            data: arr2
          }]
        });  
        
        renderTbody(data.data);
      });
    }
    
    // 渲染表格头
    function renderThead() {
      var supportYear = $('#yearGreater').val();
      
      var theadStr = '';
      theadStr += '<tr>';
      theadStr += '<th>型号</th>';
      theadStr += '<th>订货号</th>';
      theadStr += '<th>呆滞数量</th>';
      theadStr += '<th>位置</th>';
      theadStr += '<th>距离(km)</th>';
      theadStr += '<th>经销商</th>';
      theadStr += '<th>生产日期</th>';
      theadStr += '<th>呆滞天数</th>';
      theadStr += '</tr>';
      
      $('thead').empty();
      $('thead').append(theadStr);
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
        tbodyStr += '<td>' + (d.inactiveCount || '') + '</td>';
        tbodyStr += '<td>' + (d.location || '') + '</td>';
        tbodyStr += '<td>' + (d.distance || '') + '</td>';
        tbodyStr += '<td>' + (d.saleUnit || '') + '</td>';
        tbodyStr += '<td>' + new Date(d.productDate || '').Format('yyyy-MM-dd') + '</td>';
        tbodyStr += '<td>' + (d.inactiveDays || '') + '</td>';
        
        tbodyStr += '</tr>';
      });
      
      $('tbody').empty();
      $('tbody').append(tbodyStr);
    }
  </script>
</head>
<body>
  <div class="container-fluid">
    <div class="row-fluid">
      <div class="span2 span-rightborder">
        <form:form id="searchForm" modelAttribute="lgInactiveMachineCond" action="${ctx}/lg/inactivemachine/inactivemachine/" method="post" class="form-search .form-horizontal">
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
            <div class="controls padding-top-5">
                <label class="control-label">
                    机器呆滞&nbsp;<form:input id='inactiveMonth' path="inactiveMonth" htmlEscape="false" maxlength="255" class="span4"/>&nbsp;月以上
                </label>
            </div>
          </div>
          <div class="control-group">
            <label class="control-label">需求城市：</label>
            <div class="controls">
              <form:hidden id="demandCity" path="demandCity"/>
              <input id="demandCityName" type='hidden' value='' />
              <button id="demandCityBtn" type="button" class="btn btn-block" >选择城市</button>
            </div>
          </div>
          <div class="control-group">
            <div class="controls padding-top-5">
                <label class="control-label">
                    需求数量：<form:input id='demandNum' path="demandNum" htmlEscape="false" maxlength="255" class="span4"/>
                </label> 
            </div>
          </div>
          <div class="control-group"> 
            <label class="control-label">排序规则：</label> 
            <div class="controls"> 
                <label class="inline">距离:&nbsp;<input name='orderType' type='radio' value='1' />升序 
                          <input name='orderType' type='radio' value='2'/>降序</label> 
                <label class="inline">呆滞天数:&nbsp;<input name='orderType' type='radio' value='3' />升序 
                          <input name='orderType' type='radio' value='4'/>降序</label> 
            </div> 
          </div>
          <br />
          <div class="control-group">
            <div class="controls">
              <button id="queryBtn" type="button" class="btn btn-block btn-primary">呆滞查询</button>
            </div>
          </div>
        </form:form>
      </div>
      <div class="span10">
        <div id='rightTop'>
        </div>
        <br />
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
  $('#rightTop').height(height * 0.6);
  $('#rightBottom').height(height * 0.4);
  $('#rightRight').height(height);
  
  var mapDiv = document.getElementById('rightTop');
  
  var option = {
    backgroundColor : '#2a333d',
    title : {
      text : '呆滞机位置',
      left : 'center',
      textStyle : {
        color : '#fff'
      }
    },
    tooltip: {
      trigger: 'item'
    },
    legend: {
        orient: 'vertical',
        x:'left',
        data: ['需求城市', '呆滞机'],
        textStyle : {
            color: '#fff'
        }
    },
    geo : {
      map : 'china',
      label : {
        emphasis : {
          show : false
        }
      },
      roam : true, //放大缩小
      itemStyle : {
        normal : {
          areaColor : '#404a59',
          borderColor : '#111'
        },
        emphasis : {
          areaColor : '#323c48'
        }
      }
    }
  };
  
  var myChart = echarts.init(mapDiv);
  </script>
</body>
</html>