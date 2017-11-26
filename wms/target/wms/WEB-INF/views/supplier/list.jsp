<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link href="/style/basic_layout.css" rel="stylesheet" type="text/css">
    <link href="/style/common_style.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="/js/jquery/jquery.js"></script>
    <script src="/js/plugins/artDialog/jquery.artDialog.js?skin=blue"></script>
    <script type="text/javascript" src="/js/commonAll.js"></script>
    <title>WMS-供应商管理</title>
    <style>
        .alt td {
            background: black !important;
        }
    </style>
</head>
<body>
<form id="searchForm" action="/supplier/query.do" method="post">
    <div id="container">
        <div class="ui_content">
            <div class="ui_text_indent">
                <div id="box_border">
                        <div id="box_top"></div>
                    <div id="box_bottom">
                        <input type="button" value="新增" class="ui_input_btn01 btn_redirect" data-url="/supplier/input.do"/>
                    </div>
                </div>
            </div>
        </div>
        <div class="ui_content">
            <div class="ui_tb">
                <table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
                    <tr>
                        <th width="30"><input type="checkbox" id="all"/></th>
                        <th>编号</th>
                        <th>供应商名称</th>
                        <th>供应商电话</th>
                        <th>供应商地址</th>
                        <th></th>
                    </tr>
                    <tbody>
                        <c:forEach items="${result.data}" var="item">
                            <tr>
                                <td><input type="checkbox" name="IDCheck" class="acb" data-id="${item.id}"/></td>
                                <td>${item.id}</td>
                                <td>${item.name}</td>
                                <td>${item.phone}</td>
                                <td>${item.address}</td>
                                <td>
                                    <a href="javascript:;" class="btn_redirect" data-url="/supplier/input.do?id=${item.id}">编辑</a>
                                    <a href="javascript:;" class="btn_delete" data-url="/supplier/delete.do?id=${item.id}">删除</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="ui_tb_h30">
                <%@ include file="/WEB-INF/views/common/common_page.jsp"%>
            </div>
        </div>
    </div>
</form>
</body>
</html>

