<%@ page contentType="text/html;charset=UTF-8" language="java"
isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="ui_tb_h30">
	<div class="ui_flt" style="height: 30px; line-height: 30px;">
		共有
		<span class="ui_txt_bold04">${result.totalCount}</span>
		条记录，当前第
		<span class="ui_txt_bold04">${result.currentPage}/${result.totalPage}</span>
		页
	</div>
	<div class="ui_frt">
		<input type="button" value="首页" class="ui_input_btn01 btn_page" data-page="1"/>
		<input type="button" value="上一页" class="ui_input_btn01 btn_page" data-page="${result.prevPage}"/>
		<input type="button" value="下一页" class="ui_input_btn01 btn_page" data-page="${result.nextPage}"/>
		<input type="button" value="尾页" class="ui_input_btn01 btn_page"   data-page="${result.totalPage}"/>
		
		<select id="pageSize" name="pageSize" class="ui_select02">
			<option value="5">5</option>
			<option value="10">10</option>
			<option value="20">20</option>
		</select>

		<script type="text/javascript">
			//条数回显
			$("#pageSize option[value='${qo.pageSize}']").prop("selected", true);
		</script>

		转到第<input type="number" name="currentPage" style="width: 40px;" min="1" max="${result.totalPage}"
				  value="${qo.currentPage}" class="ui_input_txt01" style="width:50px;"/>
			 <input type="button" class="ui_input_btn01 btn_page" value="跳转"/>
	</div>
</div>