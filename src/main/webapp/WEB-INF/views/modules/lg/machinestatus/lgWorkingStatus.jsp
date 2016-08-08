<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
  <title>开机情况管理</title>
  <meta name="decorator" content="default"/>
  <style type="text/css">
    .span-rightborder {
      border-right: 1px solid #f5f5f5;
      padding-right: 15px;
    }
  </style>
  <script type="text/javascript">
    $(document).ready(function() {
      selectSaleYearId();
      selectCustomerId();
      
      $('#typeSelect').change(function() {
        refreshMap();
      });
    });
    function page(n,s){
      $("#pageNo").val(n);
      $("#pageSize").val(s);
      $("#searchForm").submit();
          return false;
        }
    
    // 选择销售年度
    function selectSaleYearId() {
      $.getJSON('${ctx}/lg/general/query/saleYearList', {yearGreaterOE: (new Date).getFullYear()}, function(data) {
        if (data) {
          $('#saleYearBtn').popLayer({
            type: {name:'checkbox'},
            maxWidth: 300,
            data: {data: data, label: 'yearName', value: 'yearId'},
            assignId: 'saleYear'
          });
        }
      });
    }
    
    // 选择经销商
    function selectCustomerId() {
      $.getJSON('${ctx}/lg/general/query/machineDimensionList', {type: 3}, function(data) {
        if (data) {
          $('#customerBtn').popLayer({
            type: {name:'checkbox'},
            data: {data: data, label: 'dimensionName', value: 'id'},
            assignId: 'customerId'
          });
        }
      });
    }
  </script>
</head>
<body>
  <div class="container-fluid">
    <div class="row-fluid">
      <div class="span2 span-rightborder">
        <form:form id="searchForm" modelAttribute="lgWorkingStatusCond" action="${ctx}/lg/machinestatus/workingstatus/" method="post" class="form-search .form-horizontal">
          <div class="control-group">
            <label class="control-label" for="inputEmail">主机类别：</label>
            <div class="controls">
              <form:select id="machineTypeSelect" path="machineType" class="span12">
                <form:option value="" label="全部"/>
                <form:options items="${fns:getDictList('machineType')}" itemLabel="label" itemValue="value" htmlEscape="false" />
              </form:select>
            </div>
          </div>
          <div class="control-group">
            <label class="control-label" for="inputEmail">展示方式：</label>
            <div class="controls">
              <form:select id="typeSelect" path="type" class="span12">
                <form:option value="0" label="实时"/>
                <form:option value="1" label="24小时轮播"/>
              </form:select>
            </div>
          </div>
          <div class="control-group">
            <label class="control-label" for="inputPassword">经销商列表：</label>
            <div class="controls">
              <form:hidden id="customerId" path="customerId"/>
              <button id="customerBtn" type="button" class="btn btn-block" >选择经销商</button>
            </div>
          </div>
          <div class="control-group">
            <label class="control-label" for="inputPassword">销售年度列表：</label>
            <div class="controls">
              <form:hidden id="saleYear" path="saleYear"/>
              <button id="saleYearBtn" type="button" class="btn btn-block" >选择销售年度</button>
            </div>
          </div>
          <br />
          <div class="control-group">
            <div class="controls">
              <button id="btn" type="button" class="btn btn-block" onclick="refreshMap()">开机实况</button>
            </div>
          </div>
        </form:form>
      </div>
      <div id="mapDiv" class="span10">
        <!--Body content-->
      </div>
    </div>
  </div>
  
  <script type="text/javascript">
    $('#mapDiv').height(document.body.scrollHeight);
    
    var locationNameMap = {
      '安徽': 'anhui',
      '澳门': 'aomen',
      '北京': 'beijing',
      '重庆': 'chongqing',
      '福建': 'fujian',
      '甘肃': 'gansu',
      '广东': 'guangdong',
      '广西': 'guangxi',
      '贵州': 'guizhou',
      '海南': 'hainan',
      '河北': 'hebei',
      '黑龙江': 'heilongjiang',
      '河南': 'henan',
      '湖北': 'hubei',
      '湖南': 'hunan',
      '江苏': 'jiangsu',
      '江西': 'jiangxi',
      '吉林': 'jilin',
      '辽宁': 'liaoning',
      '内蒙古': 'neimenggu',
      '宁夏': 'ningxia',
      '青海': 'qinghai',
      '山东': 'shandong',
      '上海': 'shanghai',
      '山西': 'shanxi',
      '四川': 'sichuan',
      '天津': 'tianjin',
      '香港': 'xianggang',
      '新疆': 'xinjiang',
      '西藏': 'xizang',
      '云南': 'yunnan',
      '浙江': 'zhejiang'
    };
    
    var mapDiv = document.getElementById('mapDiv');
    
    var mapOption = {
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
    };

    var option = {
      backgroundColor : '#2a333d',
      title : {
        text : '机器开机实况',
        subtext : '机器开机实况',
        left : 'center',
        textStyle : {
          color : '#fff'
        }
      },
      tooltip: {
        trigger: 'item',
        formatter: function(param) {
          console.log(param);
        }
      },
      legend: {
          orient: 'vertical',
          x:'left',
          data: [],
          textStyle : {
              color: '#fff'
          }
      },
      geo : mapOption
    };
    
    var myChart = echarts.init(mapDiv);
    
    // 刷新 echarts 地图数据
    function refreshMap() {
      var location = '';
      
      myChart.on('click', function(params) {
        if (params.seriesType == 'scatter') {  // 点点进入机器电子档案
          addTabPage('机器电子档案', '${ctx}/lg/lgMachineEvent/list?code=' + params.value[2]);
        } else if (params.componentType = 'geo') {  // 点省进入省地图
          location = params.name;
          $.get('${ctxStatic}/echarts/mapJson/' + locationNameMap[location] + '.json', function (locationJson) {
            echarts.registerMap(location, locationJson);
            getMapData(location);

            myChart.on('click', function(params) {
              if (params.seriesType == 'scatter') {  // 点点进入机器电子档案
                addTabPage('机器电子档案', '${ctx}/lg/lgMachineEvent/list?code=' + params.value[2]);
              }
            });
          });
        }
      });
      
      getMapData(location);
    }
    
    function randomData() {
      return Math.round(Math.random()*1000);
  }
    
    // 请求地图数据
    function getMapData(location) {
      $.getJSON('statusMapData', {
          machineType : $('#machineTypeSelect').find("option:selected").val(),
          type : $('#typeSelect').find("option:selected").val(),
          customerId : $('#customerId').val(),
          saleYear : $('#saleYear').val(),
          province : location
        }, function(jdata, status) {
          if (status != 'success') {
            console.log('status: ' + status);
            return;
          }
          
          if ($('#typeSelect').find("option:selected").val() == '0') {  // 实时
            var arr = [];
            $.each(jdata.seriesData, function(i, d) {
              var obj = {
                  name: i,
                  type: 'scatter',
                  coordinateSystem: 'geo',
                  itemStyle: {
                    normal : {
                      shadowBlur : 2,
                      color : 'rgba(37, 140, 249, 0.8)'
                    }
                  },
                  data: d
              };
  
              arr.push(obj);
            });
            
            /* mapOption.type = 'map';
            mapOption.mapType = 'shanxi';
            mapOption.data = [
             {name: '北京',value: randomData() },
             {name: '天津',value: randomData() },
             {name: '上海',value: randomData() },
             {name: '重庆',value: randomData() },
             {name: '河北',value: randomData() },
             {name: '河南',value: randomData() },
             {name: '云南',value: randomData() },
             {name: '辽宁',value: randomData() },
             {name: '黑龙江',value: randomData() },
             {name: '湖南',value: randomData() },
             {name: '安徽',value: randomData() },
             {name: '山东',value: randomData() },
             {name: '新疆',value: randomData() },
             {name: '江苏',value: randomData() },
             {name: '浙江',value: randomData() },
             {name: '江西',value: randomData() },
             {name: '湖北',value: randomData() },
             {name: '广西',value: randomData() },
             {name: '甘肃',value: randomData() },
             {name: '山西',value: randomData() },
             {name: '内蒙古',value: randomData() },
             {name: '陕西',value: randomData() },
             {name: '吉林',value: randomData() },
             {name: '福建',value: randomData() },
             {name: '贵州',value: randomData() },
             {name: '广东',value: randomData() },
             {name: '青海',value: randomData() },
             {name: '西藏',value: randomData() },
             {name: '四川',value: randomData() },
             {name: '宁夏',value: randomData() },
             {name: '海南',value: randomData() },
             {name: '台湾',value: randomData() },
             {name: '香港',value: randomData() },
             {name: '澳门',value: randomData() }
         ];
            arr.push(mapOption); */
            
            myChart.setOption(option, true);
            myChart.setOption({
              legend: {data: jdata.legendData},
              geo: {map: location == '' ? 'china' : location},
              series: arr
            });
          } else {  // 24小时轮播
            var baseOption = {
              backgroundColor : '#2a333d',
              title : {
                text : '机器开机实况',
                subtext : '机器开机实况',
                left : 'center',
                textStyle : {
                  color : '#fff'
                }
              },
              tooltip: {
                trigger: 'item',
                formatter: function(param) {
                  console.log(param);
                }
              },
              legend: {
                  orient: 'vertical',
                  x:'left',
                  data: jdata.legendData,
                  textStyle : {
                      color: '#fff'
                  }
              },
              timeline: {
                axisType: 'category',
                autoPlay: true,
                playInterval: 1000,
                data: ['00:00', '01:00', '02:00', '03:00', '04:00', '05:00', '06:00', 
                       '07:00', '08:00', '09:00', '10:00', '11:00', '12:00', '13:00',
                       '14:00', '15:00', '16:00', '17:00', '18:00', '19:00', '20:00', 
                       '21:00', '22:00', '23:00']
              },
              geo : mapOption
            };
            baseOption.geo.map = location == '' ? 'china' : location;
            
            var optionsData = [];
            $.each(jdata.seriesData24, function(i, d) {
              var iObj = {};
              $.each(d, function(ii, id) {
                iObj.series = [];
                iObj.series.push({
                  name: ii,
                  type: 'scatter',
                  coordinateSystem: 'geo',
                  symbolSize: 8,
                  itemStyle: {
                    normal : {
                      shadowBlur : 2,
                      shadowColor : 'rgba(37, 140, 249, 0.8)',
                      color : 'rgba(37, 140, 249, 0.8)'
                    }
                  }, 
                  data: id
                });
              });
              
              optionsData.push(iObj);
            });
            
            myChart.setOption({
              baseOption: baseOption,
              options: optionsData
            }, true);
          }
          
          // 进入省后添加返回按钮
          if (location != '') {
            myChart.setOption({
              toolbox: {
                top: 'center',
                right: 5,
                itemSize: 30,
                feature: {
                  myTool1: {
                    show: true,
                    title: '返回',
                    icon: 'path://M432.45,595.444c0,2.177-4.661,6.82-11.305,6.82c-6.475,0-11.306-4.567-11.306-6.82s4.852-6.812,11.306-6.812C427.841,588.632,432.452,593.191,432.45,595.444L432.45,595.444z M421.155,589.876c-3.009,0-5.448,2.495-5.448,5.572s2.439,5.572,5.448,5.572c3.01,0,5.449-2.495,5.449-5.572C426.604,592.371,424.165,589.876,421.155,589.876L421.155,589.876z M421.146,591.891c-1.916,0-3.47,1.589-3.47,3.549c0,1.959,1.554,3.548,3.47,3.548s3.469-1.589,3.469-3.548C424.614,593.479,423.062,591.891,421.146,591.891L421.146,591.891zM421.146,591.891',
                    onclick: function() {
                      refreshMap();
                    }
                  }
                }
              }
            });
          }
      });
    }

    refreshMap();
  </script>
</body>
</html>