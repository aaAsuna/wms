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
    <script type="text/javascript" src="/js/plugins/artDialog/jquery.artDialog.js?skin=blue"></script>
    <script type="text/javascript" src="/js/plugins/artDialog/plugins/iframeTools.js"></script>
    <script type="text/javascript" src="/js/plugins/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript">
        $(function () {
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
            //查看图表
            $(".btn_bar").click(function () {
                var param = $("#searchForm").serialize();
                var url = "/chart/saleByBar.do?" + param;
                $.dialog.open(url, {
                    id: 'ooxx',
                    title: '销售报表柱状图',
                    width: 850,
                    height: 650,
                    close: function () {//子窗口关闭事件
                    }
                });
            });
            //显示餅图
            $(".btn_pie").click(function () {
                var param = $("#searchForm").serialize();
                var url = "/chart/saleByPie.do?" + param;
                $.dialog.open(url, {
                    id: 'ooxx',
                    title: '销售报表餅图',
                    width: 850,
                    height: 650,
                    close: function () {//子窗口关闭事件
                    }
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
<form id="searchForm" action="/chart/sale.do" method="post">
    <div id="container">
        <div class="ui_content">
            <div class="ui_text_indent">
                <div id="box_bsale">
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
                        客户
                        <select id="clientId" name="clientId" class="ui_select01">
                            <option value="-1">所有客户</option>
                            <c:forEach items="${clients}" var="item">
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
                                $("#clientId option[value=${qo.clientId}]").prop("selected", true);
                                $("#brandId option[value=${qo.brandId}]").prop("selected", true);
                                $("#groupBy option[value='${qo.groupBy}']").prop("selected", true);
                            });
                        </script>
                        <input type="button" value="查询" class="ui_input_btn01 btn_page" data-page="1"/>
                    </div>
                    <div id="box_bottom">
                        <div style="align-content: right;padding-right: 50px">
                            <input type="button" value="柱状报表" class="left2right btn_bar">
                            <input type="button" value="饼状报表" class="left2right btn_pie">
                        </div>
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
                        <th>销售总数量</th>
                        <th>销售总金额</th>
                        <th>毛利润</th>
                    </tr>
                    <tbody>
                    <c:forEach items="${saleList}" var="item">
                        <tr class="data_tr">
                            <td>${item.groupType}</td>
                            <td>${item.totalNumber}</td>
                            <td>${item.totalAmount}</td>
                            <td>${item.grossProfit}</td>
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

