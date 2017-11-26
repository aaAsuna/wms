<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link href="/style/basic_layout.css" rel="stylesheet" type="text/css">
    <link href="/style/common_style.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="/js/jquery/jquery.js"></script>
    <script src="/js/plugins/artDialog/jquery.artDialog.js?skin=blue"></script>
    <script type="text/javascript" src="/js/plugins/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript" src="/js/plugins/form/jquery.form.min.js"></script>
    <script type="text/javascript" src="/js/commonAll.js"></script>
    <script type="text/javascript" src="/js/system/productStock.js"></script>
    <title>WMS-即时库存</title>
    <style>
        .alt td {
            background: black !important;
        }
    </style>
</head>
<body>
<form id="searchForm" action="#" method="post">
    <div id="container">
        <div class="ui_content">
            <div class="ui_text_indent">
                <div id="box_border">
                    <div id="box_top">搜索</div>
                     <div id="box_center">
                         商品名称或编号
                         <input type="text" class="ui_input_txt02" name="keyword" value="${qo.keyword}"/>
                         仓库
                         <select id="depotId" name="depotId" class="ui_select01">
                             <option value="-1">所有仓库</option>
                             <c:forEach items="${depots}" var="item">
                                 <option value="${item.id}">${item.name}</option>
                             </c:forEach>
                         </select>
                         商品品牌
                         <select id="brandId" name="brandId" class="ui_select01">
                             <option value="-1">所有品牌</option>
                             <c:forEach items="${brands}" var="item">
                                 <option value="${item.id}">${item.name}</option>
                             </c:forEach>
                         </select>
                         <script type="text/javascript">
                             $(function () {
                                 $("#depotId option[value=${qo.depotId}]").prop("selected", true);
                                 $("#brandId option[value=${qo.brandId}]").prop("selected", true);
                             });
                         </script>
                         阈值
                         <input type="text" class="ui_input_txt02" name="limitNumber" value="${qo.limitNumber}">
                     </div>
                    <div id="box_bottom">
                         <input type="button" value="查询" class="ui_input_btn01  btn_page" data-page="1"/>
                    </div>
                </div>
            </div>
        </div>
        <div class="ui_content">
            <div class="ui_tb">
                <table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
                    <tr>
                        <th width="30"><input type="checkbox" id="all"/></th>
                        <th>仓库</th>
                        <th>货品编码</th>
                        <th>货品名称</th>
                        <th>品牌</th>
                        <th>数量</th>
                        <th>成本</th>
                        <th>库存汇总</th>
                        <th></th>
                    </tr>
                    <tbody>
                    <c:forEach items="${result.data}" var="item">
                        <tr>
                            <td><input type="checkbox" name="IDCheck" class="acb" data-id="${item.id}"
                                       autocomplete="off"/></td>
                            <td>${item.depot.name}</td>
                            <td>${item.product.sn}</td>
                            <td>${item.product.name}</td>
                            <td>${item.product.brandName}</td>
                            <td>${item.storeNumber}</td>
                            <td>${item.price}</td>
                            <td>${item.amount}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <!-- 分页操作 -->
            <%@ include file="/WEB-INF/views/common/common_page.jsp" %>
        </div>
    </div>
</form>
</body>
</html>
    
