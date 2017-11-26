$(function() {
    //============================================================
    //权限操作
    //全部从左移动到右
    $("#selectAll").click(function() {
        $(".all_permissions option").appendTo($(".selected_permissions"));
    });
    //把选中的从左移动到右
    $("#select").click(function() {
        $(".all_permissions option:selected").appendTo($(".selected_permissions"));
    });
    //全部从右移动到左
    $("#deselectAll").click(function() {
        $(".selected_permissions option").appendTo($(".all_permissions"));
    });
    //把选中的从右移动到左
    $("#deselect").click(function() {
        $(".selected_permissions option:selected").appendTo($(".all_permissions"));
    });
    //===============================================================
    //把已经分配的权限从所有的权限中移除
    var permsIds = $.map($(".selected_permissions option"),function(item) {
        return item.value;
    });
    $.each($(".all_permissions option"),function(index,item) {
       if($.inArray($(item).val(),permsIds) >= 0){
            $(item).remove();
       }
    });
    //把已经菜单的权限从所有的菜单中移除
    //=================================================================
    var muneIds = $.map($(".selected_menus option"),function(item) {
        return item.value;
    });
    $.each($(".all_menus option"),function(index,item) {
        if($.inArray($(item).val(),muneIds) >= 0){
            $(item).remove();
        }
    });

    //=================================================================
    //菜单操作
    //全部从左移动到右
    $("#mselectAll").click(function() {
        $(".all_menus option").appendTo($(".selected_menus"));
    });
    //把选中的从左移动到右
    $("#mselect").click(function() {
        $(".all_menus option:selected").appendTo($(".selected_menus"));
    });
    //全部从右移动到左
    $("#mdeselectAll").click(function() {
        $(".selected_menus option").appendTo($(".all_menus"));
    });
    //把选中的从右移动到左
    $("#mdeselect").click(function() {
        $(".selected_menus option:selected").appendTo($(".all_menus"));
    });
    //把已经分配的权限从所有的权限中移除
    var ids = $.map($(".selected_menus option"),function(item) {
        return item.value;
    });
    $.each($(".all_menus option"),function(index,item) {
        if($.inArray($(item).val(),ids) >= 0){
            $(item).remove();
        }
    });
});
//在表单提交之前,应该选中所有已经分配的权限选项和菜单选项
$(function() {
   $("#editForm").submit(function() {
       $(".selected_permissions option").prop("selected", true);
       $(".selected_menus option").prop("selected", true);
   });
});


//把表单变成ajax From
$(function(){
    $("#editForm").ajaxForm(function(data){
        if(data.success){
            $.dialog({
                title: "温馨提示",
                content: "操作成功",
                icon: "face-smile",
                cancel: true,
                ok: function () {
                    window.location.href = "/role/query.do";
                }
            });
        }
    });
});
