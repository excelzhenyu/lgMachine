"use strict";

//===================================================
// 各种自定义的 js 公共组件
//
// $.fn.popLayer  checkbox 弹出层
// Date.prototype.Format  date 格式化
// hash(input) hash值
//
// @author lidong
//===================================================

/**
 * 弹出层插件，基于 JQuery 和 Bootstrap，自动生成各种弹出层，也可以自定义内容
 *
 * ${option} 参数:
 * option = {
 *   title: '',             标题
 *   type: {
 *     name: '',            类型 custom | checkbox | radio, 默认 custom    custom，自定义内容，checkbox，多选，radio，单选
 *     content: '',         custom 类型自定义的 html
 *     allChecked: boolean  checkbox 是否默认全选
 *   },
 *   data: {
 *     data: [],            自动生成弹出层内容的数据
 *     label: '',
 *     value: ''
 *   },
 *   maxWidth: number,      最大宽度, 默认276
 *   placement: 'right',    弹出框的位置 top | bottom | left | right ，默认 right
 *   assignId: ''           保存生成的 checkbox 的值的元素 ID
 * }
 */
var popLayer = {};

$.fn.popLayer = function(param) {
  var option = $.extend({}, {
    title: '',
    type: {
      name: 'custom',
      content: '',
      allChecked: false
    },
    data: {
      data: [],
      label: '',
      value: ''
    },
    maxWidth: 400,
    placement: 'right',
    assignId: 'popLayer' + hash(param.type.content || '')
  }, param);

  var parent = $(this);

  popLayer.assignId = option.assignId;

  var assignId = option.assignId;
  var placement = option.placement;
  var maxWidth = option.maxWidth;

  var checkboxName = assignId + '_cbn';
  var innerBtnId = assignId + '_innerBtn';
  var innerHtml = '';

  // 渲染内容
  switch(option.type.name) {
    case 'custom':
      innerHtml = option.type.content;
      break;
    case 'checkbox':
      var allCheck = '';
      if (option.type.allChecked)
        allCheck = 'checked';

      $.each(option.data.data, function(i, d) {
        var value = d[option.data.value] || d;
        var label = d[option.data.label] || d;

        var conChecked = false;
        if (option.type.allChecked) {
          conChecked = allCheck;
        } else {
          conChecked = $('#' + assignId).val().indexOf(value) != -1 ? 'checked' : '';
        }

        innerHtml += '<label class="checkbox inline">';
        innerHtml += ('<input name="' + checkboxName + '" type="checkbox" value="' + value + '"' + conChecked + '> ' + label);
        innerHtml += '</label>';
      });
      innerHtml += '</br>';
      innerHtml += '<label class="checkbox inline"><input id="' + assignId + '_selectAll" type="checkbox"' + allCheck + '">全选</label>';
      innerHtml += '<div style="float:\'right\'"><button id="' + innerBtnId + '" type="button" class="btn" "' + '">确定</button></div>';

      break;
    case 'radio':
      $.each(option.data.data, function(i, d) {
        var value = d[option.data.value] || d;
        var label = d[option.data.label] || d;

        var conChecked = $('#' + assignId).val().indexOf(value) != -1 ? 'checked' : '';

        innerHtml += '<label class="radio inline">';
        innerHtml += ('<input name="' + checkboxName + '" type="radio" value="' + value + '"' + conChecked + '> ' + label);
        innerHtml += '</label>';
      });
      innerHtml += '</br>';
      innerHtml += '<div style="float:\'right\'"><button id="' + innerBtnId + '" type="button" class="btn">确定</button></div>';

      break;
    default:
      innerHtml = option.type.content;
  };

  // 渲染弹出层
  var layerHtml = '<div id="' + assignId + '_pop" class="popover fade ' + placement + ' in" style="display: block;">'
    + '<div class="arrow"></div>'
    + '<h3 class="popover-title">' + (option.title || '') + '</h3>'
    + '<div class="popover-content">' + innerHtml + '</div>'
    + '</div>';

  parent.parent().remove('#' + assignId + '_pop');
  parent.parent().append(layerHtml);
  
  //$('body').remove('#' + assignId + '_pop');
  //$('body').append(layerHtml);

  $('#' + assignId + '_pop').hide();

  parent.click(function() {
    $('#' + assignId + '_pop').css({'max-width': maxWidth});

    if (!$('#' + assignId + '_pop').is(':visible')) { // 隐藏时:点击后隐藏其他弹出层并显示点击的弹出层
      $('[id$=_pop]').hide();
      $('#' + assignId + '_pop').show();
    } else {  // 显示时：点击后隐藏
      $('[id$=_pop]').hide();
    }
    
    var locationVal = popLayer.location(parent, assignId, placement);
    $('#' + assignId + '_pop').css({'top': locationVal.top, 
      'left': locationVal.left, 
      'z-index': 1000});
  });

  $('#' + assignId + '_selectAll').click(function() {
      popLayer.selectAllByCheckbox(assignId + '_selectAll', checkboxName);
  });

  $('#' + innerBtnId).click(function() {
      popLayer.popLayerBtnClick(checkboxName, assignId);
  });
  
  /*$(document).click(function(e){
    var target = e.target;
    var drag = $(".popover");
    var dragel = $(".popover")[0];
    if (dragel !== target && !$.contains(dragel, target)) {
      drag.hide();
    }
  });*/

  return this;
}

//定位
popLayer.location = function (parent, assignId, placement) {
  var top = parent.position().top;
  var left = parent.position().left;
  
  switch(placement) {
    case 'left':
      top = top - $('#' + assignId + '_pop').height() / 2 + parent.height() / 2;
      left = left - $('#' + assignId + '_pop').width() - 10;
      break;
    case 'top':
      top = top - $('#' + assignId + '_pop').height() - parent.height() / 2;
      left = left - $('#' + assignId + '_pop').width() / 2 + parent.width() / 2;
      break;
    case 'right':
      top = top - $('#' + assignId + '_pop').height() / 2 + parent.height() / 2;
      left = left + parent.width() + 10;
      break;
    case 'bottom':
      top = top + parent.height() + 15;
      left = left - $('#' + assignId + '_pop').width() / 2 + parent.width() / 2;
      break;
    default:
  }
  
  if (top < 0) top = 0;

  return {top: top, left: left};
}

//点击确定后，将选中的值加入隐藏域, 多个值间用 , 分割
popLayer.popLayerBtnClick = function (checkboxName, assignId) {
  var dataVal = '';
  $('input[name="' + checkboxName + '"]:checked').each(function(i, d) {
    if (i != 0)
      dataVal += ',';

    dataVal += $(this).val();
  });

  $('#' + assignId).val(dataVal);
  $('#' + assignId).change(); // 为了触发 onchange
  $('#' + assignId + '_pop').hide();
}

//全选或全不选
popLayer.selectAllByCheckbox = function (id, tarCheckboxName) {
  if($('#' + id).prop('checked') == true) {
    $('input[name="' + tarCheckboxName + '"]').prop("checked", true);
  } else {
    $('input[name="' + tarCheckboxName + '"]').prop("checked", false);
  }
}

/**
 * Data 格式化
 * (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423
 * (new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18
 */
Date.prototype.Format = function (fmt) {
  if (this == null || this == 'Invalid Date')
    return '';
  
  var o = {
    "M+": this.getMonth() + 1, //月份
    "d+": this.getDate(), //日
    "h+": this.getHours(), //小时
    "m+": this.getMinutes(), //分
    "s+": this.getSeconds(), //秒
    "q+": Math.floor((this.getMonth() + 3) / 3), //季度
    "S": this.getMilliseconds() //毫秒
  };
  if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
  for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
  return fmt;
}

/**
 * 求哈希值
 */
function hash(input){
    var I64BIT_TABLE = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_-'.split('');
    var hash = 5381;
    var i = input.length - 1;

    if(typeof input == 'string'){
        for (; i > -1; i--)
            hash += (hash << 5) + input.charCodeAt(i);
    } else {
        for (; i > -1; i--)
            hash += (hash << 5) + input[i];
    }
    var value = hash & 0x7FFFFFFF;

    var retValue = '';
    do {
        retValue += I64BIT_TABLE[value & 0x3F];
    } while (value >>= 6);

    return retValue;
}
