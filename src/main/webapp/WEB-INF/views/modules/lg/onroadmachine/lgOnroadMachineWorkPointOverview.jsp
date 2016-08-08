<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<html>
<head>
<title>试验机器工作概览</title>
<meta name="decorator" content="default" />
<link rel="stylesheet" href="${ctxStatic}/custom-css/drawLayout.css">
<link rel="stylesheet" href="${ctxStatic}/lgJs/progressBar.css">
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=n7YhXw9moZ650jtLcPn2hQjG8ANwebKu"></script>

<style>
.main-container {
    overflow-y: hidden;
}

.left-container {
    overflow-y: auto;
}

.right-container {
    position: absolute;
    right: 20%;
    width: 60%;
    height: 100%;
    padding: 0;
    padding-left: 5px;
    border: none;
    z-index: 30;
    overflow-y: hidden;
    /*background: #f3f3f3*/
}

.last-container {
    position: absolute;
    right: 0;
    bottom: 0;
    top: 0;
    width: 20%;
    padding: 5px;
    overflow-y: auto;
}

.ch_box {
    height: 120px;
    overflow-x: hidden;
    overflow-y: auto;
    border: 1px solid #EEE;
}

.ch_box ul {
    margin: 0;
    padding: 0;
}

.ch_box ul li {
    list-style-type: none;
}

.modal-body {
    min-height: 350px;
    max-height: 375px;
}

.modal.fade.in {
    position: fixed;
    margin: auto;
    left: 0;
    right: 0;
    top: 0;
}

#batchNumbers {
    display: none;
    margin-top: 35px;
}

.accordion {
    margin-bottom: 5px;
}
</style>

<script type="text/javascript">
    $(document).ready(function() {    
      initDeviceNoMult();
      initTransportUnitMult();
      initPurchaserUnitMult();

          $("#machineSelect").change(
              function() {
                var deviceNo = $(this).val() ? $(this).val() : $("#cacheMahine").val();
                var dateBegin = $("#dateBegin").val();
                var dateEnd = $("#dateEnd").val();
                if (!dateBegin || !dateEnd) {
                  return;
                }
                $.getJSON("datePointData", {
                  deviceNo : deviceNo,
                  dateBegin : dateBegin,
                  dateEnd : dateEnd
                }, function(data) {
                  var machineDetail = "";
                  $("#machineDetail").html(machineDetail);
                  data.forEach(function(item, i) {
                    item.mapPointList.forEach(function(subItem) {
                      var names = subItem.name.split("<br />");
                      var exptDate = "", address = "";

                      if (names.length > 1) {
                        exptDate = names[0];
                        address = names[1];
                      }
                      machineDetail += '<tr>' + '<td>' + item.device + '</td>' + '<td>' + exptDate
                          + '</td>' + '<td><a href="javascript:void(0);" title="' + address + '" '
                          + 'onclick="openModal(\'' + item.device + '\',\'' + exptDate + '\');" '
                          + '>轨迹</a>' + ' </td></tr>';
                    });
                    $("#machineDetail").html(machineDetail);
                  });
                });
              });
          
          $('#importForm').on("submit", function() {
            var fileName = $("#uploadFile").val();
            if (fileName && (fileName.indexOf('.xls') > -1 || fileName.indexOf('.xlsx') > -1)) {
              $(this).ajaxSubmit({
                dataType : "json",
                beforeSubmit : function(arr, $form, options) {
                },
                success : function(data, status, xhr, $form) {
                  if (data) {
                    setHtml(data);
                  }
                },
                error : function(xhr, status, error, $form) {
                  showTip('导入失败！', 'error', 1500, 0);
                },
                complete : function(xhr, status, $form) {
                }
              });
            } else {
              showTip('导入文件仅允许xls或xlsx格式！', 'error', 1500, 0);
            }
            return false;
          });
    });
    
  //初始化整机编号
  function initDeviceNoMult() {
    $.getJSON('${ctx}/lg/general/query/deviceNoList', function(data, status) {
      if (data) {
        $('#deviceNoMultBtn').popLayer({
          type : {name: 'checkbox'},
          data : {
            data : data,
            label : 'code',
            value : 'code'
          },
          assignId : 'deviceNoMult'
        });
      }
    });
  }

  // 初始化承运单位
  function initTransportUnitMult() {
    $.getJSON('${ctx}/lg/general/query/machineDimensionList', {
      type : 12
    }, function(data) {
      if (data) {
        $('#transportUnitMultBtn').popLayer({
          type : {name : 'checkbox'},
          data : {
            data : data,
            label : 'dimensionName',
            value : 'id'
          },
          assignId : 'transportUnitMult'
        });
      }
    });
  }

  // 初始化收货单位
  function initPurchaserUnitMult() {
    $.getJSON('${ctx}/lg/general/query/machineDimensionList', {
      type : 3
    }, function(data) {
      if (data) {
        $('#purchaserUnitMultBtn').popLayer({
          type : {name : 'checkbox'},
          data : {
            data : data,
            label : 'dimensionName',
            value : 'id'
          },
          assignId : 'purchaserUnitMult'
        });
      }
    });
  }

    function setHtml(data) {
      var machineListHtml = '';
      var machineSelectHtml = '<option value="">全部</option>';
      var cacheMahine = '';
      data.forEach(function(machine, index) {
            machineListHtml += '<li><label><input type="checkbox" name="device" value="' + machine + '" checked="checked" />'
                + machine + '</label></li>';
            machineSelectHtml += '<option value="' + machine + '">' + machine + '</option>';
            cacheMahine += machine + ',';
          });
      $("#machineArea").html(machineListHtml);
      $("#machineSelect").html(machineSelectHtml);
      $("#cacheMahine").val(cacheMahine);
      $("#importMachineCollapse").collapse('show');
    }

    function openModal(device, transportDate) {
      var src = "line" + "?deviceNo=" + device + "&transportDate=" + transportDate;
      $("#lineFrame").attr("src", src);
      $('#myModal').modal('show');
    }
  </script>
</head>
<body>
    <div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="width: 800px;height:450px;overflow-x:hidden;overflow-y:hidden;">
        <iframe id="lineFrame" style="width:100%;height:100%;overflow-x:hidden;overflow-y:hidden;"></iframe>
    </div>
    <div class="container-fluid">
        <div class="row-fluid">
            <div class="span2">
                <div class="control-group">
                    <label class="control-label">整机编号：</label>
                    <div class="controls">
                        <input type="hidden" id="deviceNoMult" />
                        <button id="deviceNoMultBtn" type="button" class="btn btn-block">选择整机编号</button>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">承运单位：</label>
                    <div class="controls">
                        <input type="hidden" id="transportUnitMult" />
                        <button id="transportUnitMultBtn" type="button"
                            class="btn btn-block">选择承运单位</button>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">收货单位：</label>
                    <div class="controls">
                        <input type="hidden" id="purchaserUnitMult" />
                        <button id="purchaserUnitMultBtn" type="button"
                            class="btn btn-block">选择收货单位</button>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">起始承运日期：</label>
                    <div class="controls">
                        <input id="transportDateBegin" name="transportDateBegin"
                            type="text" readonly="readonly" maxlength="20"
                            class="Wdate span12" placeholder="起始承运日期"
                            style="margin-bottom: 0;"
                            value="<fmt:formatDate value="${lgDeviceSliceStatics.resumeStart}" pattern="yyyy-MM-dd "/>"
                            onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">结束承运日期：</label>
                    <div class="controls">
                        <input id="transportDateEnd" name="transportDateEnd" type="text"
                            readonly="readonly" maxlength="20" class="Wdate span12"
                            placeholder="结束承运日期" style="margin-bottom: 0;"
                            value="<fmt:formatDate value="${lgDeviceSliceStatics.resumeStart}" pattern="yyyy-MM-dd "/>"
                            onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
                    </div>
                </div>
                <br />
                <div class="control-group">
                    <div class="controls">
                        <a href="javascript:getData();" role="button"
                            class="btn btn-primary btn-block">在途车辆位置</a>
                    </div>
                </div>
            </div>
            
            <div id="map" class="span8"></div>
            
            <div class="span2">
                <div class="control-group" style="clear: both; margin-top: 3px;">
                    <div class="controls">
                        <div class="accordion" id="postionDetailAccordion">
                            <div class="accordion-group">
                                <div class="accordion-heading">
                                    <a class="accordion-toggle collapsed" data-toggle="collapse"
                                        href="#postionDetailCollapse">在途车位置详情</a>
                                </div>
                                <div id="postionDetailCollapse" class="accordion-body collapse">
                                    <div class="accordion-inner" style="padding: 0;">
                                        <table class="table table-condensed table-striped table-hover">
                                            <tbody id="machineDetail">
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script type="text/javascript">
        var bodyHeight = document.body.scrollHeight;
        $('#map').height(bodyHeight);
        
        // 默认7天内时间
        $('#transportDateBegin').val((new Date(new Date() - 7*24*3600*1000)).Format('yyyy-MM-dd'));
        $('#transportDateEnd').val(new Date().Format('yyyy-MM-dd'));
        
        var markerMap = {}; // 存储地图标记
        
        // 信息窗口设置
        var opts = {
          width: 250,     // 信息窗口宽度
          title: "在途车辆详细信息" , // 信息窗口标题
          enableMessage: true,  //设置允许信息窗发送短息
          offset: new BMap.Size(-5, -10)
        };
        
        var map = new BMap.Map("map");            // 创建Map实例
        map.centerAndZoom('宝鸡',5);                 
        map.enableScrollWheelZoom();                 //启用滚轮放大缩小
        
        function getData() {
            $.getJSON('historyData', {
              deviceNoMult: $('#deviceNoMult').val(),
              transportUnitMult: $('#transportUnitMult').val(),
              purchaserUnitMult: $('#purchaserUnitMult').val(),
              transportDateBegin: $('#transportDateBegin').val(),
              transportDateEnd: $('#transportDateEnd').val()
            }, function(data, status) {
              if (!status == 'success') {
                console.log('getData() status: ' + status);
                return;
              }
              
              // 右侧数据
              var machineDetail = '';
              $.each(data.dataList, function(i, item) {
                machineDetail += '<tr>' 
                    + '<td><a href="javascript:void(0);" '
                    + 'onclick="openDetail(\'' + item.deviceNo +'\');" ' 
                    + '>' + item.deviceNo + '</a>'
                    + '<td>' + new Date(item.transportDate).Format('yyyy-MM-dd') + '</td>' 
                    + '<td><a href="javascript:void(0);" '
                    + 'onclick="openModal(\'' + item.deviceNo +'\',\'' + item.transportDate + '\');" ' 
                    + '>轨迹</a>'
                    + ' </td></tr>';
              });
              
              $("#machineDetail").empty();
              $("#machineDetail").append(machineDetail);
              $("#postionDetailCollapse").collapse('show');
              
              // 地图数据
              var maxLng = 0;
              var minLng = 9999;
              var maxLat = 0;
              var minLat = 9999;
              
              $.each(data.maxDateList, function(i, item) {
                var lng = item.longitude;
                var lat = item.latitudes;
                
                if (lng == 0 && lat == 0)
                  return true;
                
                if (lng > maxLng) {
                  maxLng = lng;
                } 
                if (lng < minLng) {
                  minLng = lng;
                }
                
                if (lat > maxLat) {
                  maxLat = lat;
                } 
                if (lat < minLat) {
                  minLat = lat;
                }
                
                var marker = new BMap.Marker(new BMap.Point(lng, lat));
                map.addOverlay(marker);
                
                addClickHandler(content(item.deviceNo, lng, lat), marker);
                
                markerMap[item.deviceNo] = marker;
              });
              
              var centerLng = (maxLng + minLng) / 2;
              var centerLat = (maxLat + minLat) / 2;
              
              if (minLng != 9999) {
                map.setCenter(new BMap.Point(centerLng, centerLat));
                var tempDis = (maxLng - minLng) > (maxLat - minLat) ? (maxLng - minLng) : (maxLat - minLat);
                map.setZoom(10);
              }
            });
        }
        
        // 详细信息弹框内容
        function content(deviceNo, lng, lat) {
          var html = '';
          $.ajaxSettings.async = false;
          $.getJSON('detail', {deviceNo: deviceNo, lng: lng, lat: lat}, function(data, status) {
            if (!status == 'success') {
              console.log('content() status: ' + status);
              return;
            }
            
            var detail = data.detail;
            
            html = '<div class="control-group" style="white-space:nowrap;">'
              + '<label class="control-label">运输进度：</label>'
              + '<div class="progress-bar blue shine" style="height: 20px;">'
              + '<span style="width: ' + data.present + '%"><font>' + data.present + '%</font></span>'
              + '</div></div><div class="control-group">'
              + '<label class="control-label">整机编号：</label>'
              + (detail.deviceNo || '')
              + '</div><div class="control-group">'
              + '<label class="control-label">型号：</label>'
              + (detail.productType || '')
              + '</div><div class="control-group">'
              + '<label class="control-label">订货号：</label>'
              + (detail.orderNumber || '')
              + '</div><div class="control-group">'
              + '<label class="control-label">承运日期：</label>'
              + (new Date(detail.transportDate).Format('yyyy-MM-dd'))
              + '</div><div class="control-group">'
              + '<label class="control-label">承运单位：</label>'
              + (detail.transportUnitName || '')
              + '</div><div class="control-group">'
              + '<label class="control-label">预计交车日期：</label>'
              + (new Date(detail.expectedDeliveryDate).Format('yyyy-MM-dd'))
              + '</div><div class="control-group">'
              + '<label class="control-label">承运人：</label>'
              + (detail.carrier || '')
              + '</div><div class="control-group">'
              + '<label class="control-label">联系方式：</label>'
              + (detail.carrierPhone || '')
              + '</div><div class="control-group">'
              + '<label class="control-label">运输车牌照号：</label>'
              + (detail.vehicleNo || '')
              + '</div><div class="control-group">'
              + '<label class="control-label">收货单位：</label>'
              + (detail.purchaserUnitName || '')
              + '</div><div class="control-group">'
              + '<label class="control-label">收货地址：</label>'
              + (detail.purchaserAddress || '')
              + '</div><div class="control-group">'
              + '<label class="control-label">收货人：</label>'
              + (detail.purchaser || '')
              + '</div><div class="control-group">'
              + '<label class="control-label">收货联系方式：</label>'
              + (detail.purchaserPhone || '')
              + '</div>';
            
            $.ajaxSettings.async = true;
          });
          return html;
        }
        
        // 弹出详细信息框
        function openDetail(deviceNo) {
          markerMap[deviceNo].dispatchEvent("click");
        }
        
        // 添加标记点击事件
        function addClickHandler(content,marker){
            marker.addEventListener("click", function(e) {
                openInfo(content,e);
            });
        }
        
        function openInfo(content,e){
            var p = e.target;
            var point = new BMap.Point(p.getPosition().lng, p.getPosition().lat);
            var infoWindow = new BMap.InfoWindow(content,opts);  // 创建信息窗口对象 
            map.openInfoWindow(infoWindow, point); //开启信息窗口
        }
    </script>
</body>
</html>