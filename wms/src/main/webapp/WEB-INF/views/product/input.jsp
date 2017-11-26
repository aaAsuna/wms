<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<title>信息管理系统</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link href="/style/basic_layout.css" rel="stylesheet" type="text/css">
	<link href="/style/common_style.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="/js/jquery/jquery.js"></script>
	<script src="/js/plugins/artDialog/jquery.artDialog.js?skin=blue"></script>
	<script type="text/javascript" src="/js/plugins/form/jquery.form.min.js"></script>
	<script src="/js/plugins/artDialog/plugins/iframeTools.js"></script>
	<script type="text/javascript" src="/js/commonAll.js"></script>
	<script type="text/javascript" src="/js/system/product.js"></script>
</head>
<body>
<form action="/product/saveOrUpdate.do" method="post" id="editForm" enctype="multipart/form-data">
	<input type="hidden" name="id" value="${product.id}"/>
	<div id="container">
		<div id="nav_links">
			<span style="color: #1A5CC6;">商品编辑</span>
			<div id="page_close">
				<a>
					<img src="/images/common/page_close.png" width="20" height="20" style="vertical-align: text-top;"/>
				</a>
			</div>
		</div>
		<div class="ui_content">
			<table cellspacing="0" cellpadding="0" width="100%" align="left" border="0">
				<tr>
					<td class="ui_text_rt" width="140">商品编码</td>
					<td class="ui_text_lt">
						<input type="text" name="sn" value="${product.sn}" class="ui_input_txt02"/>
					</td>
				</tr>
				<tr>
					<td class="ui_text_rt" width="140">商品名称</td>
					<td class="ui_text_lt">
						<input type="text" name="name" value="${product.name}"  class="ui_input_txt02"/>
					</td>
				</tr>
				<tr>
					<td class="ui_text_rt" width="140">成本价格</td>
					<td class="ui_text_lt">
						<input type="text" name="costPrice" value="${product.costPrice}"  class="ui_input_txt02"/>
					</td>
				</tr>
				<tr>
					<td class="ui_text_rt" width="140">销售价格</td>
					<td class="ui_text_lt">
						<input type="text" name="salePrice" value="${product.salePrice}"  class="ui_input_txt02"/>
					</td>
				</tr>
				<tr>
					<td class="ui_text_rt" width="140">商品品牌</td>
					<td class="ui_text_lt">
						<select id="brandId" name="brandId" class="ui_select01 brandSelect">
							<c:forEach items="${brands}" var="item">
								<option value="${item.id}">${item.name}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td class="ui_text_rt" width="140">商品图片</td>
					<td class="ui_text_lt">
						<input type="file" name="pic" class="ui_file">
						<c:if test="${not empty product.imagePath}">
							<img src="${product.imagePath}" class="list_img_min"/>
							<input type="hidden" name="imagePath" value="${product.imagePath}">
						</c:if>
					</td>
				</tr>
				<script type="text/javascript">
					//回显部门
					$("#brandId option[value=${product.brandId}]").prop("selected",true);
				</script>
				<tr>
					<td class="ui_text_rt" width="140">货品描述</td>
					<td class="ui_text_lt">
						<textarea name="intro" class="ui_input_txtarea">${product.intro}</textarea>
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td class="ui_text_lt">
						&nbsp;<input type="submit" value="确定保存" class="ui_input_btn01"/>
						&nbsp;<input id="cancelbutton" type="button" value="重置" class="ui_input_btn01"/>
					</td>
				</tr>
			</table>
		</div>
	</div>
</form>
</body>
</html>