<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<html>
<head>
<title>在途车辆轨迹</title>
<meta name="decorator" content="default" />
<script src="http://cdn.bootcss.com/echarts/3.2.2/extension/bmap.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=n7YhXw9moZ650jtLcPn2hQjG8ANwebKu"></script>
</head>
<body style="height: 100%; margin: 0">
	<div id="mapContainer" style="width: 800px; height: 450px; overflow-x: hidden; overflow-y: hidden;"></div>
	<script type="text/javascript">
    var dom = document.getElementById("mapContainer");
    var myChart = echarts.init(dom);
    var app = {};
    
    option = {
      bmap : {
        center : [ 120.13066322374, 30.240018034923 ],
        zoom : 5,
        roam : true,
        mapStyle : {
          styleJson : [ {
            'featureType' : 'water',
            'elementType' : 'all',
            'stylers' : {
              'color' : '#d1d1d1'
            }
          }, {
            'featureType' : 'land',
            'elementType' : 'all',
            'stylers' : {
              'color' : '#f3f3f3'
            }
          }, {
            'featureType' : 'railway',
            'elementType' : 'all',
            'stylers' : {
              'visibility' : 'off'
            }
          }, {
            'featureType' : 'highway',
            'elementType' : 'all',
            'stylers' : {
              'color' : '#fdfdfd'
            }
          }, {
            'featureType' : 'highway',
            'elementType' : 'labels',
            'stylers' : {
              'visibility' : 'off'
            }
          }, {
            'featureType' : 'arterial',
            'elementType' : 'geometry',
            'stylers' : {
              'color' : '#fefefe'
            }
          }, {
            'featureType' : 'arterial',
            'elementType' : 'geometry.fill',
            'stylers' : {
              'color' : '#fefefe'
            }
          }, {
            'featureType' : 'poi',
            'elementType' : 'all',
            'stylers' : {
              'visibility' : 'off'
            }
          }, {
            'featureType' : 'green',
            'elementType' : 'all',
            'stylers' : {
              'visibility' : 'off'
            }
          }, {
            'featureType' : 'subway',
            'elementType' : 'all',
            'stylers' : {
              'visibility' : 'off'
            }
          }, {
            'featureType' : 'manmade',
            'elementType' : 'all',
            'stylers' : {
              'color' : '#d1d1d1'
            }
          }, {
            'featureType' : 'local',
            'elementType' : 'all',
            'stylers' : {
              'color' : '#d1d1d1'
            }
          }, {
            'featureType' : 'arterial',
            'elementType' : 'labels',
            'stylers' : {
              'visibility' : 'off'
            }
          }, {
            'featureType' : 'boundary',
            'elementType' : 'all',
            'stylers' : {
              'color' : '#fefefe'
            }
          }, {
            'featureType' : 'building',
            'elementType' : 'all',
            'stylers' : {
              'color' : '#d1d1d1'
            }
          }, {
            'featureType' : 'label',
            'elementType' : 'labels.text.fill',
            'stylers' : {
              'color' : '#999999'
            }
          } ]
        }
      }
    };

    myChart.setOption(option);
    window.onresize = function() {
      myChart.resize();
    };
    
    $.getJSON('lineData', {deviceNo: '${deviceNo}', transportDate: '${transportDate}'}, function(data, status) {
      if (status != 'success') {
        console.log('lineData status: ' + status);
        return;
      }
      
      var mapData = [];
      var linesData = [];
      var pointData = [];
      var maxLat = 0;
      var minLat = 9999;
      var maxLng = 0;
      var minLng = 9999;
      
      var j = 0;
      $.each(data, function(i, item) {
        if (item.longitude == 0)
          return true;
        
        var longitude = item.longitude;
        var latitudes = item.latitudes;
        
        if (longitude > maxLng) {
          maxLng = longitude;
        } 
        if (longitude < minLng) {
          minLng = longitude;
        }
        
        if (latitudes > maxLat) {
          maxLat = latitudes;
        } 
        if (latitudes < minLat) {
          minLat = latitudes;
        }
        
        var innerArr = [longitude, latitudes];
        linesData.push({coord: innerArr});
        pointData.push(innerArr);
        
        mapData.push(linesData);
        
        if (j != 0) {
          linesData = [linesData[1]];   // 新建数组，避免引用
        } 
        
        j++;
      });
      
      if (mapData[0] && mapData[0].length <= 1)
        return true;
      
      var series = [];
      series.push({
        name : '',
        type : 'lines',
        coordinateSystem : 'bmap',
        zlevel : 1,
        effect : {
          show : true,
          period : 6,
          trailLength : 0.7,
          //color: '#fff',
          symbolSize : 3
        },
        lineStyle : {
          normal : {
            color : 'rgba(37, 140, 249, 0.8)',
            width : 0,
            curveness : 0.2
          }
        },
        data : mapData
      }, {
        name : '',
        type : 'lines',
        coordinateSystem : 'bmap',
        zlevel : 2,
        effect : {
          show : true,
          period : 6,
          trailLength : 0,
          symbol : 'triangle',//planePath, 'circle', 'rect', 'roundRect', 'triangle', 'diamond', 'pin', 'arrow'
          symbolSize : 3
        },
        lineStyle : {
          normal : {
            color : 'rgba(37, 140, 249, 0.8)',
            width : 1,
            opacity : 0.4,
            curveness : 0.2
          }
        },
        data : mapData
      }, {
        name : '',
        type : 'scatter',
        coordinateSystem : 'bmap',
        zlevel : 3,
        rippleEffect : {
          brushType : 'stroke'
        },
        label : {
          normal : {
            show : false,
            position : 'right',
            formatter : '{b}'
          }
        },
        symbolSize : 6,
        itemStyle : {
          normal : {
            color : 'rgba(37, 140, 249, 0.8)'
          }
        },
        data : pointData
      });

      myChart.setOption({
        series : series
      });

      // 缩放地图
      var bmap = myChart.getModel().getComponent('bmap').getBMap();
      bmap.setCenter(new BMap.Point((maxLng + minLng) / 2, (maxLat + minLat) / 2));
      
      var tempDis = (maxLng - minLng) > (maxLat - minLat) ? (maxLng - minLng) : (maxLat - minLat);
      bmap.setZoom(14 / tempDis);
    });
  </script>
</body>
</html>