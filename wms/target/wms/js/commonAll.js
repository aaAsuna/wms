//禁用jQuery对数组参数做的序列化操作,对数组参数名带上[]
jQuery.ajaxSettings.traditional = true;
/** table鼠标悬停换色* */
$(function() {
	// 如果鼠标移到行上时，执行函数
	$(".table tr").mouseover(function() {
		$(this).css({background : "#CDDAEB"});
		$(this).children('td').each(function(index, ele){
			$(ele).css({color: "#1D1E21"});
		});
	}).mouseout(function() {
		$(this).css({background : "#FFF"});
		$(this).children('td').each(function(index, ele){
			$(ele).css({color: "#909090"});
		});
	});
});
/** 翻页操作*/
$(function() {
	//翻页
	$(".btn_page").click(function() {
		//获取翻页页数
		var PageNo = $(this).data("page") || $(":input[name=currentPage]").val();
		//跳转页面
		$(":input[name=currentPage]").val(PageNo);
		$("#searchForm").submit();
	});
	// 设置每页显示多少条数据:改变pageSize
	$("#pageSize").change(function() {
		//提交表单,跳转到第一页
		$(":input[name=currentPage]").val(1);
		$("#searchForm").submit();
	});
});
/** 点击,跳转到指定URL*/
$(function() {
	$(".btn_redirect").click(function() {
		window.location.href = $(this).data("url");
	});
});
/** 批量删除操作*/
$(function() {
	//全选框
	$("#all").change(function () {
		$(":input[name=IDCheck]").prop("checked", this.checked);
	});
	//批量删除
	$(".btn_batchDelete").click(function () {
		var deleteUrl = $(this).data("url");
		//弹出对话框确定是否需要删除
		$.dialog({
			title: "温馨提示",
			content: "确定删除该数据吗?",
			ok: function () {
				//初始化一个窗口
				var dg = $.dialog({
					title: "温馨提示",
					content: "",
					icon: "face-smile",
					ok: true
				});
				//获取所有选中的id
				var ids = $.map($(":input[name='IDCheck']:checked"), function (item) {
					return $(item).data("id");
				});
				// 发送ajax请求
				$.get(deleteUrl, {ids: ids}, function (data) {
					if (data.success) {
						dg.content(data.msg);
						dg.button({
							name: "确定",
							callback: function () {
								window.location.reload();
							}
						});
					} else {
								dg.title("温馨提示"),
									dg.content(data.msg),
									dg.ok(true);
					}
				});
			},
			cancel: true
		});
	});
});
/** 确定删除的对话框*/
$(function() {
	$(".btn_delete").click(function() {
		var deleteUrl = $(this).data("url");
		//弹出对话框确定是否需要删除
		$.dialog({
			title: "温馨提示",
			content: "确定删除该数据吗?",
			ok: function() {
					var dg = $.dialog({
						title: "温馨提示",
						content: "",
						icon: "face-smile",
						ok: true
					});
				// 发送ajax请求
				$.get(deleteUrl, function(data) {
					if(data.success){
						dg.content(data.msg);
						dg.button({
							name:"确定",
							callback: function() {
								window.location.reload();
							}});
					}else {
							dg.title("温馨提示"),
							dg.content(data.msg),
							dg.ok(true);
					}

				});
			},
			cancel: true
		});
	});
});