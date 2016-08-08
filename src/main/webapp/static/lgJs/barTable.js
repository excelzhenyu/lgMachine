
var barTableNS = {
    init: function($this) { // 初始化
      var tableHtml = '';
      tableHtml += '<table class="time">'
        + '<thead>'
        + '<tr>'
        + '<td>'
        + '<div class="left">日期</div>'
        + '<div class="right">时间</div>'
        + '</td>'
        + '<td class="second">';
      for (var i = 1; i < 49; i++) {
        if ((i & 1) == 0) {
          tableHtml += '<div class="line large-lines">'
            + '<div class="number">'
            + i / 2
            + '</div>'
            + '</div>';
        } else {
          tableHtml = tableHtml + '<div class="line short-lines">'
            + '</div>';
        }
      }
      tableHtml += '</td>';
      tableHtml += '<td>';
      tableHtml += '累计工时';
      tableHtml += '</td>';
      tableHtml += '<tbody>';
      tableHtml += '</tbody>';
      tableHtml += '</tr>'
        + '</thead>';
      
      var colorBarHtml = '<div class="time-color" style="display: none;">'
        + '<ul class="color-ul">';
      for (var i = 0; i < 20; i++) {
        colorBarHtml += '<li class="color-li">';
        colorBarHtml += '<div class="color"></div>';
        colorBarHtml += '<div class="color-number">' + (i * 0.2).toFixed(1) + '</div>';
        colorBarHtml += '</li>';
      }
      colorBarHtml += '</ul></div>';
      
      $this.append(tableHtml);
      $this.append(colorBarHtml);
      
      return barTableNS;
    },
    render: function(result) {  // 渲染数据
      var beginDate = $('#beginDate').val()
      var endDate = $('#endDate').val()
      
      $('tbody').html('')
      $('thead').show()
      $('.time-color').show()
      var now = Date.now()
          // renderThead(result) //渲染表头
      barTableNS.renderRow(result) //渲染表格内容
      barTableNS.toolTip();
      var after = Date.now()
      console.log('渲染时间: ' + (after - now) + 'ms')
    },
    getMSTime: function(time) { //时间格式化 转化为ms
      var msTime
      var hour = time.split(':')[0]
      var minutes = time.split(':')[1]
      msTime = hour * 60 * 60 * 1000 + minutes * 60 * 1000
      return msTime
    },
    getTime: function(time) { //反序列化时间戳
      var times = new Date(time)
      var hour = times.getHours()
      var minutes = times.getMinutes()
      return hour + ':' + minutes
    },
    renderColumn: function(data, average) {
      if (data.length === 0) return
      var _width = $('thead td').eq(1).width();
      // console.log(_width)
      var beginTime = barTableNS.getMSTime(data.beginTime); //开始时间

      var endTime = barTableNS.getMSTime(data.endTime); //结束时间
      var spendTime = (endTime - beginTime) / (1000 * 60 * 60) //运行多少小时

      var leftTime = beginTime / (1000 * 60 * 60) //距离当天零点多长时间
      var spendDistance = (spendTime / 24) * _width //运行时区域宽度
      var leftDistance = (leftTime / 24) * _width //运行时区域开始距离每一天单元格最左侧的距离

      var background = barTableNS.getColor(data.average, average) //获取计算后的颜色值
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
    },
    colors: { //时间段分布颜色值
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
    },
    getColor: function(averageData, average) {
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
      var colorKeys = Object.keys(barTableNS.colors)
      colorKeys.forEach(function(item, index) {
        var num = +item // 转换key为数字
        if (diff === 0 && index === 0) {
          bgColor = barTableNS.colors[item];
        } else if ((diff <= num) && (diff > +colorKeys[index - 1])) {
          bgColor = barTableNS.colors[item]
        }
      })
      return bgColor
    },
    renderC: function(data, average) {  //渲染一列
      var result = data || [];
      var tdDiv = '';
      for (var i = 0; i < data.length; i++) {
          var areaDiv = barTableNS.renderColumn(data[i], average)
          tdDiv += areaDiv
      }
      return tdDiv
    },
    renderRow: function(data) { //渲染一行
      var result = data || [];
      var tr = '';
      for (var i = 0, len = result.length; i < len; i++) {
          tr += '<tr>'
          var date = result[i].date //日期
          var average = result[i].average
          tr += '<td>' + barTableNS.formateDate(date) + '</td>'; //第一列
          var datas = result[i].datas; //第二列所有数据
          tr += '<td>' + barTableNS.renderC(datas, average) + '</td>' //第二列
          tr += '<td>' + result[i].total + '</td>' // 第三列
          tr += '</tr>'
      }
      $('tbody').append(tr) //输出到页面显示
    },
    formateDate: function(date) {
      return date.replace(/\-/g, '/')
    },
    toolTip: function() {
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
};

