<%@page import="java.util.UUID"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../common/header.jsp"%>
	<div class="layui-body">
		<div class="layui-main" style="margin-top: 15px">
			<form class="layui-form" action="">
				<div class="layui-form-item">
					<table class="layui-hide" id="test" lay-filter="test"></table>
				</div>
			</form>
		</div>
	</div>
	<div class="layui-footer">
	</div>
</div>
<script type="text/html" id="barDemo">
	<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
	<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
<script>
    layui.use('table', function(){
        var table = layui.table;
        table.render({
            title:"行业类别",
            method:"post",
            elem: '#test'
            ,url:'/industryCategory/list'
			,defaultToolbar:['exports']
            ,cols: [[
                {field:'name', width:80, title: '行业名称', edit: 'text'}
                ,{field:'isOpen', width:80, title: '是否共享', sort: true, edit: 'text'}
                ,{field:'sort', width:80, title: '排序', sort: true, edit: 'text'}
                ,{field:'right', width:135, title: '操作', toolbar: '#barDemo'}
            ]]
            ,page: true
        });

        table.on('tool(test)', function(obj){
            var data = obj.data;
            //console.log(obj)
            if(obj.event === 'del'){
                layer.confirm('真的删除行么', function(index){
                    obj.del();
                    layer.close(index);
                });
            } else if(obj.event === 'edit'){
                layer.prompt({
                    formType: 2
                    ,value: data.email
                }, function(value, index){
                    obj.update({
                        email: value
                    });
                    layer.close(index);
                });
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