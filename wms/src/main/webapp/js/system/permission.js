/** 重新加载权限*/
$(function() {
   $(".btn_load").click(function() {
       var url = $(this).data("url");
       $.dialog({
           title: "温馨提示",
           content: "亲,重新加载权限可能需要耗费很长的时间,你确定加载吗?",
           icon:"face-smile",
           ok: function(){
               var dialog = $.dialog();//初始化对话框
               //通过ajax发送请求
               $.get(url,function(data){
                   var msg;
                   if (data.success) {
                       msg = "权限加载成功!";
                   } else {
                       msg = "对不起,您没有权限!";
                   }
                   dialog.title("温馨提示").content(msg).button({
                       name:"确定",
                       callback:function(){
                           window.location.reload();
                       }
                   });
               });
           },
           cancel:true
       });
   });
});