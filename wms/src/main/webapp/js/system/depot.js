$(function(){
    $("#editForm").ajaxForm(function(data){
        if(data.success){
            $.dialog({
                title: "温馨提示",
                content: "操作成功",
                icon: "face-smile",
                cancel: true,
                ok: function () {
                    window.location.href = "/depot/query.do";
                }
            });
        }
    });
});