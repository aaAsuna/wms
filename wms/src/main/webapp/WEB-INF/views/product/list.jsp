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
    <script src="/js/plugins/artDialog/jquery.artDialog.js?skin=blue"></script>
    <script type="text/javascript" src="/js/plugins/fancybox/jquery.fancybox.js"></script>
    <script type="text/javascript" src="/js/commonAll.js"></script>
    <title>WMS-商品管理</title>
    <style>
        .alt td {
            background: black !important;
        }
    </style>
    <script>
        $(function() {
            $(".pic").fancybox();
        });
    </script>
</head>
<body>
<form id="searchForm" action="/product/query.do" method="post">
    <div id="container">
        <div class="ui_content">
            <div class="ui_text_indent">
                <div id="box_border">
                    <div id="box_top">搜索</div>
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
                            <input type="button" value="查询" class="  btn_page" data-page="1"/>
                        </div>
                        <div id="box_bottom">
                            <input type="button" value="批量删除" class="ui_input_btn01 btn_batchDelete"
                                   data-url="/product/batchDelete.do"/>
                            <input type="button" value="新增" class="ui_input_btn01 btn_redirect"
                                   data-url="/product/input.do"/>
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
                            <th>商品名称</th>
                            <th>商品编码</th>
                            <th>商品品牌</th>
                            <th>成本价格</th>
                            <th>销售价格</th>
                            <th></th>
                        </tr>
                        <tbody>
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
                                    <a href="javascript:;" class="btn_redirect"
                                       data-url="/product/input.do?id=${item.id}">编辑</a>
                                    <a href="javascript:;" class="btn_delete"
                                       data-url="/product/delete.do?id=${item.id}&imagePath=${item.imagePath}">删除</a>
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

