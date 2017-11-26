

//角色操作
$(function() {
    //全部从左移动到右
    $("#selectAll").click(function() {
        $(".all_roles option").appendTo($(".selected_roles"));
    });
    //把选中的从左移动到右
    $("#select").click(function() {
        $(".all_roles option:selected").appendTo($(".selected_roles"));
    });
    //全部从右移动到左
    $("#deselectAll").click(function() {
        $(".selected_roles option").appendTo($(".all_roles"));
    });
    //把选中的从右移动到左
    $("#deselect").click(function() {
        $(".selected_roles option:selected").appendTo($(".all_roles"));
    });
    //把已经分配的权限从所有的权限中移除
    var ids = $.map($(".selected_roles option"),function(item) {
        return item.value;
    });
    $.each($(".all_roles option"),function(index,item) {
        if($.inArray($(item).val(),ids) >= 0){
            $(item).remove();
        }
    });
});
//在表单提交之前,应该选中所有已经分配的权限选项和菜单选项
$(function() {
    $("#editForm").submit(function() {
        $(".selected_roles option").prop("selected", true);
    });
});
$(function(){
    /* 表单参数校验 */
    $("#editForm").validate({
        rules: {
            name: {
                required: true,
                minlength: 2
            },
            password: {
                required: true,
                minlength: 4
            },
            repassword: {
                equalTo: "#password",
                required: true
            },
            email: {
                required: true,
                email: true
            },
            age: {
                required: true,
                range: [18 , 60]
            }
        },
        messages: {
            name: {
                required: "用户名必须填写!",
                minlength: "用户名长度不能小于2位"
            },
            password: {
                required: "密码不能为空!",
                minlength: "密码长度不能小于4位"
            },
            repassword: {
                required: "验证密码不能为空!",
                equalTo: "验证密码不一致!"
            },
            email: {
                required: "邮箱不能为空!",
                email: "邮箱格式不正确!"
            },
            age: {
                range: "年龄必须在{0}到{1}之间"
            }
        }
    });
});


//将表单变成ajaxfrom
$(function(){
    $("#editForm").ajaxForm(function(data){
        if(data.success){
            $.dialog({
                title: "温馨提示",
                content: "操作成功",
                icon: "face-smile",
                cancel: true,
                ok: function () {
                    window.location.href = "/employee/query.do";
                }
            });
        }
    });
});
