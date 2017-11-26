$(function() {
    $("#emp_datagrid").datagrid({
        columns:[[
            {
                field: 'id',
                hidden : true
            },{
                field : 'name',
                title : '姓名',
                width : 100,
                align: 'center'
            },{
                field : 'age',
                title : '年龄',
                width : 100
            },{
                field : 'inputTime',
                title : '入职时间',
                width : 100
            },{
                field : 'dept',
                title : '部门',
                width : 100
            },{
                field : 'state',
                title : '状态',
                width : 100,
                //formatter可以对内容做格式化操作,修改的内容
                formatter : function(value, row, index) {
                    return value?"在职":"离职"
                },
                //styler只能修改单元格格式
                styler : function (value, row, index) {
                    if(!value) {
                        return 'backgroundColor:#ffee00;color:red;';
                    }
                }
            }
        ]],
        url : 'employee.json',
        fitColumns : true,
        toolbar : '#toolbar',
        singleSelect : true,
        striped : true,//斑马线效果
        loadMsg : "拼命加载中...",
        pagination : true,//显示分页条
        rownumbers : true, //显示行号数
        pageSize : 10,//初始化页面每页显示条数
        pageList:[10,50,100],//初始化页面大小选择列表.
        queryParams: {
            name : 'easyui'
        },
        scrollbarSize : 0,//设置滚动条的宽度
        toolbar : '#toolbar',
        onClickRow:function(index, row) {
            if(!row.state){
                //把编辑按钮禁用掉
                $("#edit").linkbutton("disable");
            } else {
                //重新启动按钮
                $("#edit").linkbutton("enable");
            }
        }
    });
    //表单弹出框
    $('#emp_dialog').dialog({
        width: 270,
        height: 230,
        buttons: '#emp_btns',
        closed: true//默认关闭
    })
});

