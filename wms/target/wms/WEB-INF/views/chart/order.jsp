<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link href="/style/basic_layout.css" rel="stylesheet" type="text/css">
    <link href="/style/common_style.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="/js/jquery/jquery.js"></script>
    <script type="text/javascript" src="/js/commonAll.js"></script>
    <script type="text/javascript" src="/js/plugins/artDialog/artDialog.js?skin=blue"></script>
    <script type="text/javascript" src="/js/plugins/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript">
        $(function() {
            //控制查询日期控件
            $("[name=beginDate]").click(function () {
                WdatePicker({
                    maxDate: $("[name=endDate]").val()
                });
            });
            $("[name=endDate]").click(function () {
                WdatePicker({
                    minDate: $("[name=beginDate]").val()
                });
            });
        })
    </script>
    <title>WMS-采购订单管理</title>
    <style>
        .alt td {
            background: black !important;
        }
    </style>
</head>
<body>
<form id="searchForm" action="/chart/order.do" method="post">
    <div id="container">
        <div class="ui_content">
            <div class="ui_text_indent">
                <div id="box_border">
                    <div id="box_top">搜索</div>
                    <div id="box_center">
                        <fmt:formatDate value="${qo.beginDate}" pattern="yyyy-MM-dd" var="bd"/>
                        <fmt:formatDate value="${qo.endDate}" pattern="yyyy-MM-dd" var="ed"/>

                        业务时间
                        <input name="beginDate" value="${bd}" class="ui_input_txt02 Wdate beginDate"/>
                        ~
                        <input name="endDate" value="${ed}" class="ui_input_txt02 Wdate endDate"/>
                        货品
                        <input type="text" name="keyword" class="ui_input_txt02"/>
                        供应商
                        <select id="supplierId" name="supplierId" class="ui_select01">
                            <option value="-1">所有供应商</option>
                            <c:forEach items="${suppliers}" var="item">
                                <option value="${item.id}">${item.name}</option>
                            </c:forEach>
                        </select>
                        品牌
                        <select id="brandId" name="brandId" class="ui_select01">
                            <option value="-1">所有品牌</option>
                            <c:forEach items="${brands}" var="item">
                                <option value="${item.id}">${item.name}</option>
                            </c:forEach>
                        </select>
                        分组
                        <select id="groupBy" name="groupBy" class="ui_select01">
                            <c:forEach items="${groupTypes}" var="item">
                                <option value="${item.key}">${item.value}</option>
                            </c:forEach>
                        </select>
                        <script type="text/javascript">
                            $(function () {
                                $("#supplierId option[value=${qo.supplierId}]").prop("selected", true);
                                $("#brandId option[value=${qo.brandId}]").prop("selected", true);
                                $("#groupBy option[value='${qo.groupBy}']").prop("selected", true);
                            });
                        </script>
                        <input type="button" value="查询" class="ui_input_btn01 btn_page" data-page="1"/>
                    </div>
                </div>
            </div>
        </div>
        <div class="ui_content">
            <div class="ui_tb">
                <table class="table" cellspacing="0" cellpadding="0" width="100%"
                       align="center" border="0">
                    <tr>
                        <th>分组类型</th>
                        <th>采购总数量</th>
                        <th>采购总金额</th>
                    </tr>
                    <tbody>
                    <c:forEach items="${orderList}" var="item">
                        <tr class="data_tr">
                            <td>${item.groupType}</td>
                            <td>${item.totalNumber}</td>
                            <td>${item.totalAmount}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</form>
</body>
</html>

