<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link href="/style/basic_layout.css" rel="stylesheet" type="text/css">
    <link href="/style/common_style.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" type="text/css" href="/js/plugins/jquery-easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="/js/plugins/jquery-easyui/themes/icon.css">
    <script type="text/javascript" src="/js/plugins/jquery-easyui/jquery.min.js"></script>
    <script type="text/javascript" src="/js/plugins/jquery-easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/js/plugins/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="/js/system/dept.js"></script>
    <title>WMS-部门管理</title>
    <style>
        .alt td {
            background: black !important;
        }
    </style>
</head>
<body>
<table id="dept_datagrid"></table>


<div id="toolbar">
    <a class="easyui-linkbutton" href="#" iconCls="icon-add" plain="true" onclick="add()">新增</a>
    <a class="easyui-linkbutton" href="#" iconCls="icon-edit" plain="true" onclick="edit()">编辑</a>
    <a class="easyui-linkbutton" href="#" iconCls="icon-remove" plain="true" onclick="remove()">删除</a>
    <a class="easyui-linkbutton" href="#" iconCls="icon-reload" plain="true" onclick="reload()">刷新</a>
    <input id="keyword" type="text" name="keyword" class="easyui-textbox"/>
    <select id="cc" class="easyui-combobox" name="dept" style="width:200px;">
        <option value="aa">aitem1</option>
        <option>bitem2</option>
        <option>bitem3</option>
        <option>bitem4</option>
        <option>bitem5</option>
    </select>

    <a class="easyui-linkbutton" href="#" iconCls="icon-search" plain="true" onclick="searchEmployee()">查询</a>
</div>

<div id="dept_dialog">
    <form id="dept_form" action="#">
        <table>
            <input type="hidden" name="id">
            <tr>
                <td>部门名称:	</td>
                <td><input type="text" name="name" class="easyui-textbox"/></td>
            </tr>
            <tr>
                <td>部门编号:	</td>
                <td><input type="text" name="sn" class="easyui-textbox" /></td>
            </tr>
        </table>
    </form>
</div>

<div id="dept_btns">
    <a class="easyui-linkbutton" id="save" href="#" iconCls="icon-save" plain="true" onclick="save()" >保存</a>
    <a class="easyui-linkbutton" id="cancel" href="#" iconCls="icon-cancel" plain="true" onclick="cancel()">关闭</a>
</div>
</body>
</html>
    
