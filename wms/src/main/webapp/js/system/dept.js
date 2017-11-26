$(function() {
    $("#dept_datagrid").datagrid({
        columns:[[
            {
                field: 'id',
                hidden : true
            },{
                field : 'name',
                title : '部门名',
                width : 100,
                align: 'center'
            },{
                field : 'sn',
                title : '部门编号',
                width : 100,
                align: 'center'
            }
        ]],
        url : 'http://localhost:8080/department/list.do',
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
    $('#dept_dialog').dialog({
        width: 270,
        height: 230,
        buttons: '#dept_btns',
        closed: true//默认关闭
    })
});
function add() {
    $("#dept_dialog").dialog("setTitle", "新增员工");

    $('#dept_dialog').dialog("open")
}

function edit() {
    //判断是否选中数据
    var row = $("#dept_datagrid").datagrid("getSelected");
    if(!row) {
        //弹出提示框
        $.messager.alert('温馨提示','请选中一条数据!','warning');
        return;
    }

    //给员工添加"dept.id"的属性
    row["dept.id"] = row.dept.id;

    //回显操作,读取记录填充到表单中
    //基于同名匹配的原则
    $('#dept_form').form('load',row);

    //设置标题
    $("#dept_dialog").dialog("setTitle", "编辑员工");
    //打开弹出框
    $("#dept_dialog").dialog("open");

}
function remove() {
    var row = $("#dept_datagrid").datagrid("getSelected");
    if(!row) {
        //弹出提示框
        $.messager.alert('温馨提示','请选中一条数据!','warning');
        return;
    }

    //发送删除请求
    $.get("dept_delete.json",{id:row.id},function(data) {
        if(data.success) {
            //弹出提示
            $.messager.alert('温馨提示',data.msg,"info",function(){
                //重新加载页面
                $("#dept_datagrid").datagrid("reload");
            });

        }
    })
}

function save() {
    var url = 'dept_save.json';

    //判断是否有id
    if($("[name=id]").val()) {
        url = 'dept_update.json';
    }

    //提交表单
    $("#dept_form").form("submit", {
        url:url,
        success:function(data){
            //转换成json对象
            data = $.parseJSON(data);
            if(data.success){
                //弹出提示
                $.messager.alert('温馨提示', data.msg,"info",function() {
                    //关闭弹出框
                    $("#dept_dialog").dialog("close");
                    //重新加载页面
                    $("#dept_datagrid").datagrid("reload");
                });
            }else{
                $.messager.alert('温馨提示',data.msg,'error');
            }
        }
    })

}
function cancel(){
    $("#dept_dialog").dialog("close");
}

function searchEmployee() {
    //easyui的组件都使用easyui的方法来获取值
    var keyword = $("#keyword").textbox("getValue");
    var cc = $("#cc").combobox("getValue");

    //使用easyui的方式可以帮你自动封装到数据表格中
    $("#dept_datagrid").datagrid("load",{
        keyword : keyword,
        cc : cc
    });
}
