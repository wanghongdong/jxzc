<%@page import="java.util.UUID"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../common/header.jsp"%>
	<div class="layui-body">
		<div class="layui-main" style="margin-top: 15px">
			<fieldset class="layui-elem-field">
				<legend>easyExcel</legend>
				<div class="layui-field-box">
					<table class="layui-hide" id="tableList" lay-filter="tableList"></table>
				</div>
			</fieldset>
		</div>
	</div>
	<div class="layui-footer"></div>
</div>
<script type="text/html" id="toolbarDemo">
	<div class="layui-btn-container">
		<button class="layui-btn layui-btn-sm" lay-event="add" id="addPage">导出</button>
	</div>
</script>
<script>
    var tableIns = null;
        layui.use(['table','layer'], function(){
        var table = layui.table;
        var layer = layui.layer;
        tableIns = table.render({
            title:"行业类别",
            method:"post",
            elem: '#tableList',
            url:'/easyExcel/list',
            toolbar:'#toolbarDemo',
			defaultToolbar: [],
            cols: [[
                {field:'name', title: '行业名称'}
                ,{field:'isOpen', title: '是否共享', sort: true, templet:function (d) {
					if (d.isOpen==1) return "<span style='color: limegreen;'>是</span>";
					else return "<span style='color: red;'>否</span>";
                }}
                ,{field:'sort', title: '排序', sort: true}
                ,{field:'right', title: '操作', toolbar: '#barDemo'}
            ]]
            ,page: true
        });

        //头工具栏事件
        table.on('toolbar(tableList)', function(obj){
            //选中状态
            if (obj.event="add"){
				window.location.href = "/easyExcel/export";
			}
        });
    });
    //JavaScript代码区域
    layui.use('element', function(){
        var element = layui.element;
    });
</script>
</body>
</html>