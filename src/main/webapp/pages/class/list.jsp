<%@page import="java.util.UUID"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../common/header.jsp"%>
	<div class="layui-body">
		<div class="layui-main" style="margin-top: 15px">
			<fieldset class="layui-elem-field">
				<legend><c:if test="${not empty level and level eq 2}">二级</c:if><c:if test="${empty level or level eq 1}">一级</c:if>分类管理</legend>
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
		<button class="layui-btn layui-btn-sm" lay-event="add" id="addPage">新增</button>
	</div>
</script>
<script type="text/html" id="barDemo">
	<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
	<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
<script>
    var tableIns = null;
    var level = "${level}" == "" ? 1 : parseInt("${level}");
        layui.use(['table','layer'], function(){
        var table = layui.table;
        var layer = layui.layer;
        tableIns = table.render({
            title:"行业类别",
            method:"post",
            elem: '#tableList',
            url:'/class/list',
            toolbar: '#toolbarDemo',
			defaultToolbar: [],
            where:{level:level},
            cols: [[
				<c:if test="${not empty level and level eq 2}">
                	{field:'pid', title: '一级类别名称',templet:function (d) {
						return findClassName(d.pid);
                    }},
				</c:if>
                {field:'classname', title: '类别名称'},
				{field:'pisOpen', title: '是否共享', sort: true, templet:function (d) {
					if (d.level == 2){
					    if(d.pisOpen==1){
                            return "<span style='color: limegreen;'>是</span>";
						}else{
                        	return "<span style='color: red;'>否</span>";
						}
					}else {
                        if(d.isOpen==1){
                            return "<span style='color: limegreen;'>是</span>";
                        }else{
                        	return "<span style='color: red;'>否</span>";
                        }
					}
				}},
				{field:'sort', title: '排序', sort: true},
				{field:'right', title: '操作', toolbar: '#barDemo'}
            ]]
            ,page: true
        });

        //头工具栏事件
        table.on('toolbar(tableList)', function(obj){
            //选中状态
            if (obj.event="add"){
				layer.open({
					type: 2 ,
					title: level+'级分类管理',
					area: ['400px', '300px'],
					shade: 0,
					content: '/class/edit?level='+level
				})
			}
        });
		//监听行工具事件
        table.on('tool(tableList)', function(obj){
            var data = obj.data;
            if(obj.event === 'del'){
                layer.confirm('真的删除此数据？',{icon:3}, function(index){
                    $.ajax({
                        url:"/class/del",
                        data:{id:data.id},
                        dataType:"json",
                        type:"post",
                        success:function (result) {
                            var iconType = 1;
                            if (result.code==0){
                                iconType = 2;
                            }
                            layer.alert(result.msg,{icon:iconType});
                            tableIns.reload();
                        }
					});
                    obj.del();
                    layer.close(index);
                });
            } else if(obj.event === 'edit'){
                layer.open({
                    type: 2 ,
                    title: level+'级分类管理',
                    area: ['400px', '300px'],
                    shade: 0,
                    content: '/class/edit?id='+data.id+'&level='+level
                })
            }
        });
    });

    function findClassName(pid){
        var classname = "";
        $.ajax({
            url:"/class/edit",
            data:{id:pid},
            dataType:"json",
            type:"post",
			async : false,
            success:function (result) {
                if (result.code == 1){
                    classname = result.map.entity.classname;
				}
            }
		});
        return classname;
	}

    //JavaScript代码区域
    layui.use('element', function(){
        var element = layui.element;
    });
</script>
</body>
</html>