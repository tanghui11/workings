$().ready(function () {
    $('.summernote').summernote({
        height: 220,
        lang: 'zh-CN',
        callbacks: {
            onImageUpload: function (files) { //the onImageUpload API
                img = sendImg(files[0]);
            },
            onFileUpload: function (files) {
                file = sendFile(files[0]);
            }
        }
    });
    $('.summernote').summernote('code', decodeURIComponent($("#content").val()));
    loadType();
    validateRule();
    getTreeOrgIds();
    getTreeData();
    //getAllSelectNodes();
});

$.validator.setDefaults({
    submitHandler: function () {
        update();
    }
});
var treeOrgIds;
function getTreeOrgIds(){
    $.ajax({
        type: "GET",
        data: {
            "id": $('#id').val()
        },
        url: contextPath+"/oa/notify/getTreeOrgIds",
        success: function (data) {
            treeOrgIds=data;
            var text="";
            var orgs="";
            for(var i=0;i<data.length;i++){
                text += data[i].name + ",";
                orgs += data[i].id + ",";
            }
            $("#org").val(text);
            $("#orgIds").val(orgs);
        }
    });
}

function update() {
    var intro_sn = encodeURIComponent($("#intro_sn").summernote('code'));
    $("#content").val(intro_sn);
    $.ajax({
        cache: true,
        type: "POST",
        url: contextPath+"/oa/notify/update",
        data: $('#signupForm').serialize(),// 你的formid
        async: false,
        error: function (request) {
            parent.layer.alert("Connection error");
        },
        success: function (data) {
            if (data.code == 0) {
                parent.layer.msg("操作成功");
                parent.reLoad();
                var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
                parent.layer.close(index);
            } else {
                parent.layer.alert(data.msg)
            }
        }
    });
}

function validateRule() {
    var icon = "<i class='fa fa-times-circle'></i> ";
    $("#signupForm").validate({
        rules: {
            name: {
                required: true
            }
        },
        messages: {
            name: {
                required: icon + "请输入名字"
            }
        }
    })
}

function loadType() {
    var html = "";
    $.ajax({
        url: contextPath+'/common/sysDict/list/oa_notify_type',
        success: function (data) {
            // 加载数据
            for (var i = 0; i < data.length; i++) {
                html += '<option value="' + data[i].value + '">' + data[i].name + '</option>'
            }
            $(".chosen-select").append(html);
            $(".chosen-select").chosen({
                maxHeight: 200
            });
            $(".chosen-select").val($("#Ttype").val());
            $(".chosen-select").trigger("chosen:updated");
            // 点击事件
            $('.chosen-select').on('change', function (e, params) {

            });
        }
    });
}

function getTreeData() {
    $.ajax({
        type: "GET",
        data: {
            "orgid": $('#examTaskOrgid').val()
        },
        url: contextPath+"/system/org/treeAll",
        success: function (tree) {
            loadTree(tree);
        }
    });
}

function loadTree(tree) {
    $('#jstree').jstree({
        'core': {
            'data': tree
        },
        "checkbox": {
            "three_state": true,
        },
        "plugins": ["wholerow", "checkbox"]
    }).on('select_node.jstree', function (e, data) {

    }).on("loaded.jstree",function(e,data){
        for(var i=0;i<treeOrgIds.length;i++){
           if(treeOrgIds[i].type == "d"){
                $('#jstree').jstree("select_node", treeOrgIds[i].id,true)
           }
        }
    });
    $('#jstree').jstree().open_all();
}
function getAllSelectNodes() {
    selectOrgNames = [];
    var ref = $('#jstree').jstree(true); // 获得整个树
    if (ref == false) return;
    selectOrgIds = ref.get_selected(); // 获得所有选中节点的，返回值为数组
    $("#jstree").find(".jstree-undetermined").each(function (i, element) {
        selectOrgIds.push($(element).closest('.jstree-node').attr("id"));
    });
    for(var j=0;j<selectOrgIds.length;j++){
        selectOrgNames.push($('#jstree').jstree("get_node", selectOrgIds[j]).text);
    }
    $('#orgIds').val(selectOrgIds);
    $('#org').val(selectOrgNames);
}

//图片上传方法
function sendImg(file) {
    var data = new FormData();
    data.append("file", file);
    data.append("subPath", "notify");
    $.ajax({
        data: data,
        type: "POST",
        url: contextPath+"/system/summernote/upload",
        cache: false,
        contentType: false,
        processData: false,
		beforeSend: function(){
			layer.load();
		},
        success: function (data) {
            if (data.code == 0) {
                $(".summernote").summernote('insertImage', data.url, function ($image) {
                    $image.attr('src', data.url);
                });
            } else {
                parent.layer.alert(data.msg)
            }
        },
        complete: function(){
            layer.closeAll('loading'); //关闭loading
        }
    });
}

//文件上传方法
function sendFile(file) {
    var data = new FormData();
    data.append("file", file);
    data.append("subPath", "notify");
    $.ajax({
        data: data,
        type: "POST",
        url: contextPath+"/system/summernote/upload",
        cache: false,
        contentType: false,
        processData: false,
		beforeSend: function(){
			layer.load();
		},
        success: function (data) {
            if (data.code == 0) {
                $(".summernote").summernote('insertLink', data.url, data.name);
            } else {
                parent.layer.alert(data.msg)
            }
        },
        complete: function(){
            layer.closeAll('loading'); //关闭loading
        }
    });
}