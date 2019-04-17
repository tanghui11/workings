$(function () {
    //初始化插件
    initPlugin();
});

/**
 * 页面加载和异步页面加载时，初始化插件
 */
function initPlugin() {
    //radio默认选中第一个
    $('input:radio').each(function () {
        var $name = $(this).attr('name');
        if (!$('input:radio[name=' + $name + ']').is(':checked')) {
            $('input:radio[name=' + $name + ']').eq(0).attr("checked", true);
        }
    });

    //datetimepicker
    $('input[data-toggle="datetimepicker"]').each(function () {
        var $options = $(this).data();
        if (!$options.hasOwnProperty('format')) $options.format = 'yyyy-mm-dd';
        if (!$options.hasOwnProperty("autoclose")) $options.autoclose = true;
        if (!$options.hasOwnProperty("today")) {
            $options.todayBtn = true;
        } else {
            $options.todayBtn = $options.today;
        }
        if (!$options.hasOwnProperty("language")) $options.language = "zh-CN";
        if (!$options.hasOwnProperty("min")) {
            $options.minView = 2;
        } else {
            $options.minView = $options.min;
        }
        if (!$options.hasOwnProperty("start")) {
            $options.startView = 2;
        } else {
            $options.startView = $options.start;
        }
        if (!$options.hasOwnProperty("step")) {
            $options.minuteStep = 5;
        } else {
            $options.minuteStep = $options.step;
        }
        $(this).datetimepicker($options).on('changeDate', function (e) {
            $(this).focus();
        });
        $(this).attr("readonly", "readonly");
    });

    //textarea禁止横向拉伸
    $(".form-group textarea").css("resize", "vertical");

}


/* ========================================================================
 * @description ECharts
 * @author 张海 <izhangh@outlook.com>
 * @Blog http://www.izhangh.cn
 * ======================================================================== */
var $echarts = $('div[data-toggle="echarts"]')
$echarts.each(function () {
    var eChart = echarts.init($(this).get(0));
    eChart.showLoading();
    $.get($(this).data('url'), function (data) {
        eChart.hideLoading();
        eChart.setOption(data);
    }, 'json')
})

/* ========================================================================
 * @description gridstack
 * @author 张海 <izhangh@outlook.com>
 * @Blog http://www.izhangh.cn
 * ======================================================================== */
var $gridstack = $('div[data-toggle="gridstack"]')

$gridstack.each(function () {
    var param = $(this).data('param'),
        datagrid = $("#datagrid" + $(this).attr('id')).val(),
        gridstack = $(this);
    try {
        datagrid = eval('(' + datagrid + ')');
    } catch (err) {
        datagrid = "";
    }
    //配置项
    $(this).removeAttr('data-param').gridstack({
        float: false,
        width: 12,
        cellHeight: 100,
        // disableResize: false,
        // disableDrag: false,
        handle: 'grid-stack-item-content .panel-heading',
        handleClass: 'grid-stack-item-content .panel-heading',
    });
    //生成gridstack
    var grid = $(gridstack).data('gridstack');
    grid.removeAll();
    //循环生成每一个grid
    _.each(GridStackUI.Utils.sort(datagrid), function (node) {
        var uuid = generateUUID();
        var ajaxData = {title: '加载中...', icon: 'spinner'};
        //创建
        createGrid(gridstack, grid, uuid, node, ajaxData, false);
        //加载
        loadContent(gridstack, uuid);
    }, this);
    //设置配置
    $('#gridstackSetting' + gridstack.attr('id')).click(function () {
        //关闭配置
        if ($('#gridstackSettingBox' + gridstack.attr('id')).is(':visible')) {
            $(this).html("<i class='fa fa-gear'></i> 配置");
            $('.editGriddata').hide();
            $('#gridstackSettingBox' + gridstack.attr('id')).hide();
            $('#gridstackAddWidget' + gridstack.attr('id')).hide();
        } else {    //开启配置
            $(this).html("<i class='fa fa-times'></i> 取消配置");
            $('.editGriddata').show();
            $('#gridstackSettingBox' + gridstack.attr('id')).show();
            $('#gridstackAddWidget' + gridstack.attr('id')).show();
            freshSettingData(gridstack);
            //添加新grid
            //创建 新的 grid
            $('#gridstackAddWidget' + gridstack.attr('id')).on('click', function () {
                var uuid = generateUUID();
                var node = {
                    x: 0,
                    y: 0,
                    width: 12,
                    height: 2,
                    data: {}
                };
                createGrid(gridstack, grid, uuid, node, '', true);
            })
            //监听grid变化
            gridstack.change(function (event, ui) {
                var gridContainer = this;
                var element = event.target;
                freshSettingData(gridstack);
            });
        }
    })
})

/**
 * 创建grid
 * @param grid
 * @param uuid
 * @param node
 * @param ajaxData
 * @param autoPosition
 */
function createGrid(gridstack, grid, uuid, node, ajaxData, autoPosition) {
    if (typeof node.data != "object") {
        node.data = eval("(" + node.data + ")")
    }
    if (ajaxData == '') ajaxData = {title: '新增Grid' + ($('.grid-stack-item').length + 1), icon: 'area-chart'};
    var html = '<div data-item="' + uuid + '">';
    html += "<div class='grid-stack-item-content clearfix' data-content='" + uuid + "' data-option='" + JSON.stringify(node.data) + "'>";
    html += '<div class="editGriddata">';
    html += '<span class="remove" data-remove="' + uuid + '"><i class="fa fa-trash-o"></i></span>';
    html += '<button type="button" class="btn btn-blue pull-right" id="setting' + uuid + '"><i class="fa fa-gear"></i> 配置</button>';
    html += '<input type="hidden" name="' + uuid + '" value="' + node.data + '" />';
    html += '</div>';
    html += '<div class="panel panel-default" style="height:100%;overflow-y:auto;overflow-x:hidden;margin-bottom:0;" data-panel="' + uuid + '" style="height: 100%;margin-bottom:0;">';
    html += '<div class="panel-heading"style="z-index:2;position: absolute;top: 0px;left: 0;width: 100%;border: 1px solid #ddd;">';
    html += '<h3 class="panel-title"><i class="fa fa-' + ajaxData['icon'] + '"></i> ' + ajaxData['title'] + '</h3>';
    html += '</div>';
    html += '<div class="panel-body" style="z-index:1;width:100%;height:100%;padding-top: 50px;"></div>';
    html += '</div>';
    html += '</div>';
    html += '</div>';
    grid.addWidget($(html), node.x, node.y, node.width, node.height, autoPosition);
    if (autoPosition) $('.editGriddata').show();
    //删除处理
    $('.grid-stack-item-content[data-content="' + uuid + '"]').find('.editGriddata .remove').click(function () {
        layer.confirm('确定要删除吗？', {
            btn: ['确定', '取消']
        }, function () {
            grid.removeWidget($('.grid-stack-item[data-item="' + uuid + '"]'));
            freshSettingData(gridstack); //更新 grid 数据
            layer.msg("操作成功");
        })
    })
    //配置处理
    $('#setting' + uuid).click(function () {
        var url = $(gridstack).data('seturl') + '?uuid=' + uuid;
        var data = $('.grid-stack-item-content[data-content="' + uuid + '"]').data('option');
        if (typeof data != "object" && data != 'undefined') {
            data = eval("(" + data + ")");
        }
        url += '&' + $.param(data);
        layer.open({
            type: 2,
            title: '配置',
            maxmin: true,
            shadeClose: false, // 点击遮罩关闭层
            area: ['800px', '520px'],
            content: url // iframe的url
        });
    })
}

function settingReturn(uuid) {
    var input = $('.grid-stack-item-content[data-content="' + uuid + '"]').find('.editGriddata input');
    var gridstack = $(input).closest("div[data-toggle='gridstack']");
    $('.grid-stack-item-content[data-content="' + uuid + '"]').data('option', $(input).val());
    loadContent(gridstack, uuid);
    freshSettingData(gridstack);
}


/**
 * 加载内容
 * @param uuid
 */
function loadContent(gridstack, uuid) {
    var data = $('.grid-stack-item-content[data-content="' + uuid + '"]').data('option');
    if (typeof data != "object") {
        data = eval("(" + $('.grid-stack-item-content[data-content="' + uuid + '"]').data('option') + ")");
    }
    $.ajax({
        url: $(gridstack).data('url'),
        type: 'GET',
        data: data,
        dataType: 'json',
        success: function (ajaxData) {
            if (ajaxData['status'] == '200') {
                $('.panel[data-panel="' + uuid + '"] .panel-heading .panel-title').html('<i class="fa fa-' + ajaxData['icon'] + '"></i> ' + ajaxData['title']);
                if (ajaxData.type == 'block') {
                    $('.panel[data-panel="' + uuid + '"] .panel-body').html(block(ajaxData));
                } else if (ajaxData.type == 'table') {
                    $('.panel[data-panel="' + uuid + '"] .panel-body').html(table(ajaxData));
                } else if (ajaxData.type == 'info') {
                    $('.panel[data-panel="' + uuid + '"] .panel-body').html(info(ajaxData));
                } else {
                    var eChart = echarts.init($('.panel[data-panel="' + uuid + '"] .panel-body').get(0));
                    eChart.setOption(ajaxData.data);
                    $('.panel[data-panel="' + uuid + '"] .panel-body').resize(function () {
                        eChart.resize();
                    });
                }
            } else if (ajaxData['status'] == '300') {
                $('.panel[data-panel="' + uuid + '"] .panel-heading .panel-title').html('<i class="fa fa-times"></i> ' + ajaxData.info);
                $('.panel[data-panel="' + uuid + '"] .panel-body').html('');
            }
        },
        error: function (e) {
            $('.panel[data-panel="' + uuid + '"] .panel-heading .panel-title').html('<i class="fa fa-times"></i> ' + e.status + e.statusText + ' 页面不存在');
            $('.panel[data-panel="' + uuid + '"] .panel-body').html('');
        }
    });
}

/**
 * 更新数据
 */
function freshSettingData(gridstack) {
    $('#gridstackSettingBoxFormatData' + gridstack.attr('id')).text(JSON.stringify(getGridData(gridstack), null, '    '));
    $('#gridstackSettingBoxData' + gridstack.attr('id')).val(JSON.stringify(getGridData(gridstack), null, ''));
}

/**
 * 获取grids数据
 * @returns {*}
 */
function getGridData(gridstack) {
    var serialized_data = _.map($(gridstack).find('.grid-stack-item:visible'), function (el) {
        var el = $(el);
        var node = el.data('_gridstack_node');
        var data = el.find('.grid-stack-item-content').data('option');
        if (typeof el.find('.grid-stack-item-content').data('option') != "object") {
            data = eval("(" + el.find('.grid-stack-item-content').data('option') + ")");
        }
        return {
            x: node.x,
            y: node.y,
            width: node.width,
            height: node.height,
            data: data
        };
    }, this);
    // return JSON.stringify(serialized_data);
    return serialized_data;
}

/**
 * 生成唯一ID
 * @returns {string}
 */
function generateUUID() {
    var d = new Date().getTime();
    var uuid = 'xxxxxxxxxxxx4xxxyxxxxxxxxxxxxxxx'.replace(/[xy]/g, function (c) {
        var r = (d + Math.random() * 16) % 16 | 0;
        d = Math.floor(d / 16);
        return (c == 'x' ? r : (r & 0x3 | 0x8)).toString(16);
    });
    return 'data' + uuid;
}

/**
 * 生成block
 * @param ajaxData
 * @returns {string}
 */
function block(ajaxData) {
    var blockNum;
    var mb = '';
    if (ajaxData.data.length > 4) {
        blockNum = 3;
        mb = 'margin-bottom:10px;';
    } else {
        blockNum = 12 / ajaxData.data.length;
    }
    var html = '<div class="row" style="margin-left: -10px;margin-right:-10px;">';
    for (var i = 0; i < ajaxData.data.length; i++) {
        html += '<div class="col-sm-' + blockNum + ' col-md-' + blockNum + '" style="padding-left:8px;padding-right:8px;' + mb + '">';
        var href = '#';
        ajaxData.data[i].url = '#';
        if (ajaxData.data[i].url != '') href = 'href="' + ajaxData.data[i].url + '" data-toggle="navtab" data-id="' + ajaxData.data[i].id + '" data-title="' + ajaxData.data[i].title + '"';
        html += '<a ' + href + ' class="infobox bk-' + ajaxData.data[i].color + '">';
        html += '<div class="icon"><i class="fa fa-' + ajaxData.data[i].icon + '"></i></div>';
        html += '<div class="item">' + ajaxData.data[i].number + '</div> <div class="title">' + ajaxData.data[i].title + '</div>';
        html += '<ul class="list"> ';
        for (var j = 0; j < ajaxData.data[i].item.length; j++) {
            html += '<li> <p>' + ajaxData.data[i]['item'][j].title + '</p> <p> ' + ajaxData.data[i]['item'][j].data + '</p></li>';
        }
        html += '</ul>';
        html += '</a>';
        html += '</div>';
    }
    html += '</div>';
    return html;
}

/**
 * 生成table
 * @param ajaxData
 * @returns {string}
 */
function table(ajaxData) {
    html = '<table class="table table-striped table-bordered table-hover">';
    for (var i = 0; i < ajaxData.data.length; i++) {
        if (i == 0) {
            html += '<tr>';
            for (var j = 0; j < ajaxData.data[i].length; j++) {
                html += '<th>' + ajaxData.data[i][j].name + '</th>'
            }
            html += '</tr>';
        } else {
            html += '<tr>';
            for (var j = 0; j < ajaxData.data[i].length; j++) {
                html += '<td>' + ajaxData.data[i][j].name + '</td>'
            }
            html += '</tr>';
        }
    }
    html += '</table>';
    return html;
}

/**
 * 生成block
 * @param ajaxData
 * @returns {string}
 */
function info(ajaxData) {
    return '<div style="text-indent: 2em;">' + ajaxData.data + '</div>';
}