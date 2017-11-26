$(function () {

    //显示选择货品列表框
    $("#edit_table_body").on("click", ".searchproduct", function () {
        var currentTr = $(this).closest("tr");
        $.dialog.open("/product/selectProductList.do", {
            title: "选择货品列表",
            width: 800,
            height: 520,
            close: function () {
                var ret = $.dialog.data("json");
                if (ret) {
                    currentTr.find("[tag=name]").val(ret["name"]);
                    currentTr.find("[tag=pid]").val(ret["id"]);
                    currentTr.find("[tag=costPrice]").val(ret["costPrice"]);

                    currentTr.find("[tag=brand]").html(ret["brandName"]);
                }
            }
        });
    }).on("change", "[tag=costPrice],[tag=number]", function () {
        //为金额与数量绑定 change 事件,计算金额小计
        //得到两个元素的值
        var currentTr = $(this).closest("tr");
        var price = currentTr.find("[tag=costPrice]").val() || 0;
        var number = currentTr.find("[tag=costPrice]").val() || 0;
        //设置金额小计
        currentTr.find("[tag=amount]").html((price * number).toFixed(2));
    }).on("click", ".removeItem", function () {
        //找到当前行,并删除
        var currentTr = $(this).closest("tr");
        //判断是否是最后一行
        if ($("#edit_table_body tr").size() > 1) {
            currentTr.remove();
        } else {
            //清空数据
            currentTr.find("[tag=name]").val("");
            currentTr.find("[tag=pid]").val("");
            currentTr.find("[tag=costPrice]").val("");
            currentTr.find("[tag=number]").val("");
            currentTr.find("[tag=remark]").val("");

            currentTr.find("[tag=brand]").html("");
            currentTr.find("[tag=amount]").html("");

        }
    });
    //点击添加明细按钮,追加一条明细
    $(".appendRow").click(function () {
        //找到tbody中的第一个tr
        var copy = $("#edit_table_body tr:first").clone(true);

        //清空数据
        copy.find("[tag=name]").val("");
        copy.find("[tag=pid]").val("");
        copy.find("[tag=costPrice]").val("");
        copy.find("[tag=number]").val("");
        copy.find("[tag=remark]").val("");

        copy.find("[tag=brand]").html("");
        copy.find("[tag=amount]").html("");

        //追加到tbody中
        copy.appendTo("#edit_table_body");
    });
    //提交之前修改明细的名称,添加上索引
    $("#editForm").submit(function () {
        //找到 tbody 中所有的行
        $.each($("#edit_table_body tr"), function (index, item) {
            //找到当前行中所有需要提交的元素,修改名称
            $(item).find("[tag=pid]").prop("name", "items[" + index + "].product.id");
            $(item).find("[tag=costPrice]").prop("name", "items[" + index + "].costPrice");
            $(item).find("[tag=number]").prop("name", "items[" + index + "].number");
            $(item).find("[tag=remark]").prop("name", "items[" + index + "].remark");
        });
    });

});

/** 确定审核的对话框*/
$(function () {
    $(".btn_audit").click(function () {
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
            if (data.success) {
                $.dialog({
                    title: "温馨提示",
                    content: "操作成功",
                    icon: "face-smile",
                    cancel: true,
                    ok: function () {
                        window.location.href = "/stockIncomeBill/query.do";
                    }
                });
            }
        });
    });


