
var prefix = contextPath+"/region/regionRegion"
$(function() {
    validateRule();
    loadTree();
    //load();
    //计算机构树div高度
    $("#jstree").height($(document).height() - 120);
});

/*function load() {
	$('#exampleTable')
			.bootstrapTable(
					{
						method : 'get', // 服务器数据的请求方式 get or post
						url : prefix + "/list", // 服务器数据的加载地址
					//	showRefresh : true,
					//	showToggle : true,
					//	showColumns : true,
						iconSize : 'outline',
						toolbar : '#exampleToolbar',
						striped : true, // 设置为true会有隔行变色效果
						dataType : "json", // 服务器返回的数据类型
						pagination : true, // 设置为true会在底部显示分页条
						// queryParamsType : "limit",
						// //设置为limit则会发送符合RESTFull格式的参数
						singleSelect : false, // 设置为true将禁止多选
						// contentType : "application/x-www-form-urlencoded",
						// //发送到服务器的数据编码类型
						pageSize: parent.pageSize, // 如果设置了分页，每页数据条数
						pageNumber : 1, // 如果设置了分布，首页页码
						//search : true, // 是否显示搜索框
						showColumns : false, // 是否显示内容下拉框（选择显示的列）
						sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
						queryParams : function(params) {
							return {
								//说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
								limit: params.limit,
								offset:params.offset
					           // name:$('#searchName').val(),
					           // username:$('#searchName').val()
							};
						},
						// //请求服务器数据时，你可以通过重写参数的方式添加一些额外的参数，例如 toolbar 中的参数 如果
						// queryParamsType = 'limit' ,返回参数必须包含
						// limit, offset, search, sort, order 否则, 需要包含:
						// pageSize, pageNumber, searchText, sortName,
						// sortOrder.
						// 返回false将会终止请求
						columns : [
								{
									checkbox : true
								},
									{
									field : 'id', 
									title : '编号' 
								},
										{
									field : 'parentid', 
									title : '父编号' 
								},
										{
									field : 'code', 
									title : '代码' 
								},
										{
									field : 'type', 
									title : '类别' 
								},
										{
									field : 'parentName', 
									title : '' 
								},
										{
									field : 'name', 
									title : '名称' 
								},
										{
									field : 'pinyin', 
									title : '拼音' 
								},
										{
									field : 'linkman', 
									title : '联系人' 
								},
										{
									field : 'address', 
									title : '联系地址' 
								},
										{
									field : 'postCode', 
									title : '邮编' 
								},
										{
									field : 'phone', 
									title : '固定电话' 
								},
										{
									field : 'mphone', 
									title : '移动电话' 
								},
										{
									field : 'fax', 
									title : '传真' 
								},
										{
									field : 'photoFlag', 
									title : '是否能照相' 
								},
										{
									field : 'examTransfer', 
									title : '考区转移' 
								},
										{
									field : 'roomSize', 
									title : '考场规格' 
								},
										{
									field : 'examMsg', 
									title : '考试提示' 
								},
										{
									field : 'seq', 
									title : '考场编排序号' 
								},
										{
									field : 'operator', 
									title : '操作员' 
								},
										{
									field : 'updateDate', 
									title : '操作日期' 
								},
											{
									field : 'email', 
									title : '邮箱' 
								},
									{
									title : '操作',
									field : 'id',
									align : 'center',
									formatter : function(value, row, index) {
										var e = '<a class="btn btn-primary btn-sm '+s_edit_h+'" href="#" mce_href="#" title="编辑" onclick="edit(\''
												+ row.id
												+ '\')"><i class="fa fa-edit"></i></a> ';
										var d = '<a class="btn btn-warning btn-sm '+s_remove_h+'" href="#" title="删除"  mce_href="#" onclick="remove(\''
												+ row.id
												+ '\')"><i class="fa fa-remove"></i></a> ';
										var f = '<a class="btn btn-success btn-sm" href="#" title="备用"  mce_href="#" onclick="resetPwd(\''
												+ row.id
												+ '\')"><i class="fa fa-key"></i></a> ';
										return e + d ;
									}
								} ]
					});
}
function reLoad() {
	$('#exampleTable').bootstrapTable('refresh');
}
function add() {

    location.href =  prefix + '/add';
}
function edit(id) {

    location.href =  prefix + '/edit/' + id ;
}
function remove(id) {
	layer.confirm('确定要删除选中的记录？', {
		btn : [ '确定', '取消' ]
	}, function() {
		$.ajax({
			url : prefix+"/remove",
			type : "post",
			data : {
				'id' : id
			},
			success : function(r) {
				if (r.code==0) {
					layer.msg(r.msg);
					reLoad();
				}else{
					layer.msg(r.msg);
				}
			}
		});
	})
}

function resetPwd(id) {
}
function batchRemove() {
	var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
	if (rows.length == 0) {
		layer.msg("请选择要删除的数据");
		return;
	}
	layer.confirm("确认要删除选中的'" + rows.length + "'条数据吗?", {
		btn : [ '确定', '取消' ]
	// 按钮
	}, function() {
		var ids = new Array();
		// 遍历所有选择的行数据，取每条数据对应的ID
		$.each(rows, function(i, row) {
			ids[i] = row['id'];
		});
		$.ajax({
			type : 'POST',
			data : {
				"ids" : ids
			},
			url : prefix + '/batchRemove',
			success : function(r) {
				if (r.code == 0) {
					layer.msg(r.msg);
					reLoad();
				} else {
					layer.msg(r.msg);
				}
			}
		});
	}, function() {

	});
}*/

function getTreeData() {
    $.ajax({
        type: "GET",
        url: contextPath+ "/common/tree/regionRegionTree",
        success: function (tree) {
            //console.log(tree);
            loadTree(tree);
        }
    });
}
function loadTree() {
    $("#jstree").jstree({
        'core': {
            "data" : {
                "dataType" : 'json',
                "opts":{
                    method: "POST"
                },
                "url" : function(node){
                    return contextPath+ '/common/tree/regionRegionTree';
                },
                "data" : function(node){
                    if(node.id !="#"){
                        return {"id":-1 , "parentid":node.id};
                    }else{
                        return {"id" : $('#orgid').val(),"parentid":-1};
                    }
                }
            }
        }
    });
}
//存个变量
var daima;
$("#parentid").val($('#orgid').val());
$('#jstree').on("changed.jstree", function (e, data) {
    if (data.selected == -1) {
        $('#orgid').val('0');
        //load();
    } else {
        $('#orgid').val(data.selected[0]);
        $('#orgType').val(data.node.original.attributes.type);
        // load();
        $.ajax({
            cache : true,
            type : "get",
            url : contextPath+ "/region/regionRegion/edit/"+$('#orgid').val(),
            //data : $('#signupForm').serialize(),// 你的formid
            async : false,
            error : function(request) {
                parent.layer.alert("Connection error");
            },
            success : function(data) {
                daima = data.code;
                //console.log(data);
                $("#code").val(data.code);
                $("#address").val(data.address);
                $("#enabledFlag").val(data.enabledFlag);
                $("#examMsg").val(data.examMsg);
                $("#examTransfer").val(data.examTransfer);
                $("#fax").val(data.fax);
                $("#id").val(data.id);
                $("#linkman").val(data.linkman);
                $("#mphone").val(data.mphone);
                $("#name").val(data.name);
                $("#operator").val(data.operator);
                $("#parentid").val(data.parentid);
                $("#phone").val(data.phone);
                $("#photoFlag").val(data.photoFlag);
                $("#pinyin").val(data.pinyin);
                $("#postCode").val(data.postCode);
                $("#roomSize").val(data.roomSize);
                $("#seq").val(data.seq);
                $("#type").val(data.type);
                $("#updateDate").val(data.updateDate);
                $("#email").val(data.email);
                //$("#parentid").val(data.parentid);
            }
        });

    }
});


//
$.validator.setDefaults({
    submitHandler : function() {
        if(daima == $("#code").val()){
            layer.msg("代码不能重复！");
        }else{
            $("#parentid").val($("#orgid").val());
            save();
        }
        //console.log($("#id").val())

        //console.log($("#parentid").val())



    }
});
function save() {
	countDown("submission","提交");
	$.ajax({
		cache : true,
		type : "POST",
		url :contextPath+  "/region/regionRegion/save",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
				location.reload();

			} else {
				parent.layer.alert(data.msg)
			}

		}
	});

}
//修改
function xiugai(){
    $.ajax({
        cache : true,
        type : "POST",
        url :contextPath+  "/region/regionRegion/update",
        data : $('#signupForm').serialize(),// 你的formid
        async : false,
        error : function(request) {
            parent.layer.alert("Connection error");
        },
        success : function(data) {
            if (data.code == 0) {
                parent.layer.msg("操作成功");
                // self.location=document.referrer;
            } else {
                parent.layer.alert(data.msg)
            }

        }
    });
}
function remove(){
    $.ajax({
        cache : true,
        type : "POST",
        url :contextPath+  "/region/regionRegion/remove",
        data :{"id":$('#orgid').val()} ,// 你的formid
        async : false,
        error : function(request) {
            parent.layer.alert("Connection error");
        },
        success : function(data) {
            if (data.code == 0) {
                parent.layer.msg("操作成功");
                // self.location=document.referrer;
                //location.reload();
            } else {
                parent.layer.alert(data.msg)
            }

        }
    });
}

//删除


function validateRule() {
    var icon = "<i class='fa fa-times-circle'></i> ";
    $("#signupForm").validate({
        rules : {
            parentid : {
                required : true
            },
            code : {
                required : true,
                maxlength: 20
            },
            type : {
                required : true,
                maxlength: 1
            },
            name : {
                required : true,
                maxlength: 30
            },
            pinyin : {
                maxlength: 30
            },
            linkman : {
                maxlength: 15
            },
            address : {
                maxlength: 65
            },
            postCode : {
                isZipCode:true
            },
            phone : {
                isTel:true
            },
            mphone : {
                isPhone:true
            },
            fax : {
                fax:true
            },
            photoFlag : {
                required : true
            },
            examTransfer : {
                maxlength: 18
            },
            roomSize : {
                number:true
            },
            examMsg : {
                maxlength: 333
            },
            seq : {
                maxlength: 2
            },
        },
        messages : {
            parentid : {
                required : icon + "请输入父编号"
            },
            code : {
                required : icon + "请输入代码",
                maxlength: icon + "最多输入20个字符"
            },
            type : {
                required : icon + "请输入类别",
                maxlength: icon + "最多输入1个字符"
            },
            name : {
                required : icon + "请输入名称",
                maxlength: icon + "最多输入30个字符"
            },
            pinyin : {
                maxlength: icon + "最多输入30个字符"
            },
            linkman : {
                maxlength: icon + "最多输入15个字符"
            },
            address : {
                maxlength: icon + "最多输入65个字符"
            },
            postCode : {
                isZipCode:icon + "请正确填写您的邮政编码"
            },
            Phone : {
                isTel: icon + "请填写正确的座机号码"
            },
            mphone : {
                isPhone: icon + "请填写正确的11位手机号"
            },
            fax : {
                fax: icon + "传真格式如：0371-68787027"
            },
            photoFlag : {
                required : icon + "请选择是或否"
            },
            examTransfer : {
                maxlength: icon + "最多输入18个字符"
            },
            roomSize : {
                number: icon + "请输入正确的考场规格,如:0"
            },
            examMsg : {
                maxlength: icon + "最多输入333个字符"
            },
            seq : {
                maxlength: icon + "最多输入2个字符,如:00"
            }
        }
    })
}

//导入
function importData() {
    layer.open({
        type : 2,
        title : '区县信息维护导入',
        shadeClose : false, // 点击遮罩关闭层
        area : [ '600px', '190px' ],
        content : prefix + '/QXimportData' // iframe的url
    });
}