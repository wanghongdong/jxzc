<%@page import="java.util.UUID"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../common/header.jsp"%>
	<div class="layui-body">
		<div class="layui-main" style="margin-top: 15px">
			<fieldset class="layui-elem-field">
				<legend>CHECKLIST管理</legend>
				<div class="layui-field-box">
					<table class="layui-table" lay-data="{url:'/', id:'test3'}" lay-filter="checkList">
						<thead>
							<tr>
								<th lay-data="{type:'checkbox'}">ID</th>
								<th lay-data="{field:'id', width:80, sort: true}">ID</th>
								<th lay-data="{field:'username', width:120, sort: true, edit: 'text'}">用户名</th>
								<th lay-data="{field:'email', edit: 'text', minWidth: 150}">邮箱</th>
								<th lay-data="{field:'sex', width:80, edit: 'text'}">性别</th>
								<th lay-data="{field:'city', edit: 'text', minWidth: 100}">城市</th>
								<th lay-data="{field:'experience', sort: true, edit: 'text'}">积分</th>
							</tr>
						</thead>
					</table>
				</div>
			</fieldset>
		</div>
	</div>
	<div class="layui-footer"></div>
</div>
<script>
    layui.use(['table','layer','element'], function(){
        var table = layui.table;
        var element = layui.element;
        //监听单元格编辑
        table.on('edit(checkList)', function(obj){
            var value = obj.value //得到修改后的值
                ,data = obj.data //得到所在行所有键值
                ,field = obj.field; //得到字段
            layer.msg('[ID: '+ data.id +'] ' + field + ' 字段更改为：'+ value);
        });
    });
</script>
</body>
</html>