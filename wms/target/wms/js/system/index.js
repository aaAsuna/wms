//加载当前日期
function loadDate(){
	var time = new Date();
	var myYear = time.getFullYear();
	var myMonth = time.getMonth() + 1;
	var myDay = time.getDate();
	if (myMonth < 10) {
		myMonth = "0" + myMonth;
	}
	document.getElementById("day_day").innerHTML = myYear + "." + myMonth + "."	+ myDay;
}

/**
 * 隐藏或者显示侧边栏
 * 
 **/
function switchSysBar(flag){
	var side = $('#side');
    var left_menu_cnt = $('#left_menu_cnt');
	if( flag==true ){	// flag==true
		left_menu_cnt.show(500, 'linear');
		side.css({width:'280px'});
		$('#top_nav').css({width:'77%', left:'304px'});
    	$('#main').css({left:'280px'});
	}else{
        if ( left_menu_cnt.is(":visible") ) {
			left_menu_cnt.hide(10, 'linear');
			side.css({width:'60px'});
        	$('#top_nav').css({width:'100%', left:'60px', 'padding-left':'28px'});
        	$('#main').css({left:'60px'});
        	$("#show_hide_btn").find('img').attr('src', 'images/common/nav_show.png');
        } else {
			left_menu_cnt.show(500, 'linear');
			side.css({width:'280px'});
			$('#top_nav').css({width:'77%', left:'304px', 'padding-left':'0px'});
        	$('#main').css({left:'280px'});
        	$("#show_hide_btn").find('img').attr('src', 'images/common/nav_hide.png');
        }
	}
}
// =====================================
//定义为全局变量
var setting = {
	data : {
		simpleData:{
			enable:true
		}
	},
	callback:{
		onClick:function(event,treeId,treeNode){
			if(treeNode.action){
				$("#rightMain").prop("src","/"+treeNode.action+".do");
				$("#here_area").html("当前位置:"+treeNode.getParentNode().name+"&nbsp;>&nbsp;" + treeNode.name);
			}
		}
	},
	async: {
		enable: true,
		url: "/systemMenu/loadMenusByParentSn.do",
		autoParam: ["sn=parentSn"]
	}

};

var zNodes = {
	business : [
		{id : 1,pId: 0,name : "业务模块",sn:"business",isParent:true,open:true}
	],
	systemManage : [
		{id : 2,pId:0,name : "系统模块",sn:"system",isParent:true,open:true}
	],
	charts : [
		{id : 3,pId:0,name : "报表模块",sn:"chart",isParent:true,open:true}
	]
};

function loadMenu(sn) {
	$.fn.zTree.init($("#dleft_tab1"), setting, zNodes[sn]);
}

// =====================================
$(function() {
	// 加载菜单树
	loadMenu("business");
	// 加载日期
	loadDate();
	// ======================================
	// 切换菜单按钮样式和标题
	$("#TabPage2 li").click(function() {
		// 删除其他样式
		$.each($("#TabPage2 li"),
			function(index, item) {
				$(item).prop("class", "").children().prop(
					"src","/images/common/" + ($(item).index() + 1) + ".jpg");
			});
		$(this).prop("class", "selected");// 设置选中状态
		var currentIndex = $(this).index() + 1;// 当前选择器第index+1个元素
		$(this).children("img").prop("src","/images/common/" + currentIndex + "_hover.jpg");// 修改图片
		// 切换标题
		$("#nav_module img").prop("src","/images/common/module_" + currentIndex + ".png");
		//点击之后加载其他菜单
		loadMenu($(this).data("rootmenu"));
	});
	// ======================================
	// 显示隐藏侧边栏
	$("#show_hide_btn").click(function() {
		switchSysBar();
	});
});