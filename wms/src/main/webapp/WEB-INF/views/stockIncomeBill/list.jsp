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
    <script type="text/javascript" src="/js/system/stockIncomeBill.js"></script>
    <title>WMS-采购入库单</title>
    <style>
        .alt td {
            background: black !important;
        }
    </style>
    <script>
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
</head>
<body>
<form id="searchForm" action="#" method="post">
    <div id="container">
        <div class="ui_content">
            <div class="ui_text_indent">
                <div id="box_border">
                    <div id="box_top">搜索</div>
                     <div id="box_center">
                         开始时间
                         <fmt:formatDate value="${qo.beginDate}" pattern="yyyy-MM-dd" var="fbeginDate"/>
                         <fmt:formatDate value="${qo.endDate}" pattern="yyyy-MM-dd" var="fendDate"/>
                         <input name="beginDate" value="${fbeginDate}" class="ui_input_txt02 Wdate"/>
                         <input name="endDate" value="${fendDate}" class="ui_input_txt02 Wdate"/>
                         仓库
                         <select id="depotId" name="depotId" class="ui_select01">
                             <option value="-1">所有仓库</option>
                             <c:forEach items="${depots}" var="item">
                                 <option value="${item.id}">${item.name}</option>
                             </c:forEach>
                         </select>
                         状态
                         <select id="status" name="status" class="ui_select01 depotSelect">
                             <option value="-1">所有状态</option>
                             <option value="0">未审核</option>
                             <option value="1">已审核</option>
                         </select>
                         <script type="text/javascript">
                             $(function () {
                                 $("#depots option[value=${qo.depotId}]").prop("selected", true);
                                 $("#status option[value=${qo.status}]").prop("selected", true);
                             });
                         </script>
                         <input type="button" value="查询" class="  btn_page" data-page="1"/>
                     </div>
                    <div id="box_bottom">
                        <input type="button" value="新增" class="ui_input_btn01 btn_redirect"
                               data-url="/stockIncomeBill/input.do"/>
                    </div>
                </div>
            </div>
        </div>
        <div class="ui_content">
            <div class="ui_tb">
                <table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
                    <tr>
                        <th width="30"><input type="checkbox" id="all"/></th>
                        <th>入库单编号</th>
                        <th>业务时间</th>
                        <th>仓库</th>
                        <th>总金额</th>
                        <th>总数量</th>
                        <th>录入人</th>
                        <th>审核人</th>
                        <th>状态</th>
                        <th></th>
                    </tr>
                    <tbody>
                    <c:forEach items="${result.data}" var="item">
                        <tr>
                            <td><input type="checkbox" name="IDCheck" class="acb" data-id="${item.id}"
                                       autocomplete="off"/></td>
                            <td>${item.sn}</td>
                            <td><f:formatDate value="${item.vdate}" pattern="yyyy-MM-dd"/></td>
                            <td>${item.depot.name}</td>
                            <td>${item.totalAmount}</td>
                            <td>${item.totalNumber}</td>
                            <td>${item.inputUser.name}</td>
                            <td>${item.auditor.name}</td>
                            <td>
                                <span style="color: ${item.status == 0? "green" : "red"}">${item.displayStatus}</span>
                            </td>
                            <td>
                                <c:if test="${item.status == 0}">
                                    <a href="javascript:;" class="btn_audit"
                                       data-url="/stockIncomeBill/audit.do?id=${item.id}">审核</a>
                                    <a href="javascript:;" class="btn_redirect"
                                       data-url="/stockIncomeBill/input.do?id=${item.id}">编辑</a>
                                    <a href="javascript:;" class="btn_delete"
                                       data-url="/stockIncomeBill/delete.do?id=${item.id}">删除 </a>
                                </c:if>
                                <c:if test="${item.status == 1}">
                                    <a href="/stockIncomeBill/view.do?id=${item.id}">查看</a>
                                </c:if>
                            </td>

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
    
