$(function () {
    //显示选择货品列表框
    $("#edit_table_body").on("click",".searchproduct",function() {
        var currrentTr = $(this).closest("tr");
        $.dialog.open("/product/selectProductList.do", {
            title: "选择商品列表",
            width: 800,
            height: 520,
            close: function() {
                var ret = $.dialog.data("json");
                if(ret) {
                    currrentTr.find("[tag=name]").val(ret.name);
                    currrentTr.find("[tag=id]").val(ret.id);
                    currrentTr.find("[tag=costPrice]").val(ret.costPrice);

                    currrentTr.find("[tag=brand]").html(ret.brandName);
                }
            }
        })

    }).on("click",".removeItem",function() {
        var currentTr = $(this).closest("tr");
        if($("#edit_table_body tr").size() > 1) {
            currentTr.remove();
        } else {
            //清空数据
            currrentTr.find("[tag=name]").val("");
            currrentTr.find("[tag=id]").val("");
            currrentTr.find("[tag=costPrice]").val("");
            currrentTr.find("[tag=brand]").html("");
        }
    }).on("change","[tag=costPrice],[tag=number]",function() {
        var currrentTr = $(this).closest("tr");
        var price = currrentTr.find("[tag=costPrice]").val();
        var number = currrentTr.find("[tag=number]").val();
        //计算金额小计
        currrentTr.find("[tag=amount]").html((price * number).toFixed(2));
    });



        //点击添加明细按钮,追加一条明细
        $(".appendRow").click(function() {
            //找到tbody中的第一个tr
            var copyTr = $("#edit_table_body tr:first").clone();
            //清空数据
            copyTr.find("[tag=name]").val("");
            copyTr.find("[tag=id]").val("");
            copyTr.find("[tag=costPrice]").val("");
            copyTr.find("[tag=brand]").html("");
            //追加到tbody中
            copyTr.appendTo($("#edit_table_body"));
        });
        //提交之前修改明细的名称,添加上索引
        $("#editForm").submit(function () {
            //找到 tbody 中所有的行
            $.each($("#edit_table_body tr"),function(index,item) {
                //找到当前行中所有需要提交的元素,修改名称
                $(item).find("[tag=pid]").prop("name", "items[" + index + "].product.id");
                $(item).find("[tag=costPrice]").prop("name", "items[" + index + "].costPrice");
                $(item).find("[tag=number]").prop("name", "items[" + index + "].number");
                $(item).find("[tag=remark]").prop("name", "items[" + index + "].remark");
            });

        });


});

/** 确定审核的对话框*/
$(function() {
    $(".btn_audit").click(function() {
        var url = $(this).data("url");
        $.dialog({
            title: "温馨提示",
            content: "您确定要审核吗？",
            icon: "face-smile",
            cancel: true,
            ok: function () {
                $.get(url, function (data) {
                    console.log(data)
                    if (data.success) {
                        var sdg = $.dialog({
                            title: "温馨提示",
                            icon: "face-smile"
                        });
                        sdg.content(data.msg).button({
                            name: "确定",
                            callback: function () {
                                window.location.reload();
                            }
                        });
                    } else {
                        var fdg = $.dialog({
                            title: "温馨提示",
                            icon: "face-sad"
                        });
                        fdg.content(data.msg).button({
                            name: "确定",
                            callback: true
                        });
                    }
                }, "json");
            }
        });
    });
});

//将表单变成ajaxfrom
$(function () {
    $("#editForm").ajaxForm(function (data) {
        var dg = $.dialog({
            title: "温馨提示",
            content: "",
            icon: "face-smile"
        })
        if (data.success) {
            dg.content(data.msg).button({
                name : "确定",
                callback : function() {
                    window.location.href = "/orderBill/query.do"
                }
            })
        } else{
           dg.content(data.msg).button({
               name: "知道了"
           })
        }

    });
});


