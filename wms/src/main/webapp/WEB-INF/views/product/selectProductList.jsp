<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link href="/style/basic_layout.css" rel="stylesheet" type="text/css">
    <link href="/style/common_style.css" rel="stylesheet" type="text/css">
    <link href="/js/plugins/fancybox/jquery.fancybox.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="/js/jquery/jquery.js"></script>
    <script type="text/javascript" src="/js/plugins/fancybox/jquery.fancybox.js"></script>
    <script src="/js/plugins/artDialog/jquery.artDialog.js?skin=blue"></script>
    <script src="/js/plugins/artDialog/plugins/iframeTools.js"></script>
    <script type="text/javascript" src="/js/commonAll.js"></script>
    <title>WMS-商品管理</title>
    <style>
        .alt td {
            background: black !important;
        }
    </style>
    <script>
        $(function () {
            $(".pic").fancybox();
            $(".select").click(function () {
                $.dialog.data("json", $(this).data("json"));
                //关闭窗口
                $.dialog.close();
            });
        });
    </script>
</head>
<body>
<form id="searchForm" action="#" method="post">
    <div id="container">
        <div class="ui_content">
            <div class="ui_text_indent">
                <div id="box_border">
                    <div id="box_center">
                        关键字
                        <input name="keyword" value="${qo.keyword}" class="ui_input_txt02"/>
                        货品品牌
                        <select id="brandId" name="brandId" class="ui_select01 brand_Select">
                            <option value="-1">所有品牌</option>
                            <c:forEach items="${brands}" var="item">
                                <option value="${item.id}">${item.name}</option>
                            </c:forEach>
                        </select>
                        <script type="text/javascript">
                            $(function () {
                                $("#brandId option[value=${qo.brandId}]").prop("selected", true);
                            });
                        </script>
                            <input type="button" value="查询" class="btn_page" data-page="1">
                    </div>
                </div>
            </div>
        </div>
        <div class="ui_content">
            <div class="ui_tb">
                <table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
                    <tr>
                        <th width="30"><input type="checkbox" id="all"/></th>
                        <th>商品图片</th>
                        <th>商品品牌</th>
                        <th>商品编号</th>
                        <th>商品编号</th>
                        <th>成本价</th>
                        <th>销售价</th>
                        <th></th>
                    </tr>
                    <tbody id="edit_table_body">
                    <c:forEach items="${result.data}" var="item">
                        <tr>
                            <td><input type="checkbox" name="IDCheck" class="acb" data-id="${item.id}"/></td>
                            <td>
                                <a class="pic" href="${item.imagePath}" title="${item.brandName}">
                                    <img src="${item.smallImagePath}" class="list_img">
                                </a>
                            </td>
                            <td>${item.name}</td>
                            <td>${item.sn}</td>
                            <td>${item.brandName}</td>
                            <td>${item.costPrice}</td>
                            <td>${item.salePrice}</td>
                            <td>
                            <input type="button" value="选择货品" data-json='${item.jsonString}'
                                   class="ui_input_btn01 select">
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="ui_tb_h30">
                <%@ include file="/WEB-INF/views/common/common_page.jsp" %>
            </div>
        </div>
    </div>
</form>
</body>
</html>

