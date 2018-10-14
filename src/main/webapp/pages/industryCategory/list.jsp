<%@page import="java.util.UUID"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../common/header.jsp"%>
	<div class="layui-body">
		<div class="layui-main" style="margin-top: 15px">
			<form class="layui-form" action="">
				<div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label">验证日期</label>
						<div class="layui-input-inline">
							<input type="text" name="date" id="date" lay-verify="date" placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input">
						</div>
					</div>
					<div class="layui-inline">
						<label class="layui-form-label">行业类别</label>
						<div class="layui-input-inline">
							<select name="modules" lay-verify="required" lay-search="">
								<option value="">直接选择或搜索选择</option>
								<option value="1">layer</option>
								<option value="2">form</option>
								<option value="3">layim</option>
								<option value="4">element</option>
								<option value="5">laytpl</option>
								<option value="6">upload</option>
								<option value="7">laydate</option>
								<option value="8">laypage</option>
								<option value="9">flow</option>
								<option value="10">util</option>
								<option value="11">code</option>
								<option value="12">tree</option>
								<option value="13">layedit</option>
								<option value="14">nav</option>
								<option value="15">tab</option>
								<option value="16">table</option>
								<option value="17">select</option>
								<option value="18">checkbox</option>
								<option value="19">switch</option>
								<option value="20">radio</option>
							</select>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
	<div class="layui-footer">
		© layui.com - 底部固定区域
	</div>
</div>
<script>
    //JavaScript代码区域
    layui.use('element', function(){
        var element = layui.element;
    });
</script>
</body>
</html>