<%@page import="java.util.UUID"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<title>WOWAYZY</title>
	<link rel="stylesheet" href="../../js/layui/css/layui.css">
	<script src="../../js/layui/layui.js"></script>
	<script src="../../js/jq.min.js"></script>
	<style>
		.layui-form-label {
			padding: 9px 7px;
			width: 63px;
		}
	</style>
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
	<div class="layui-main" style="margin-top: 15px">
		<div class="layui-form" action="" lay-filter="example">
			<c:if test="${not empty level and level eq 2}">
				<div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label">一级分类</label>
						<div class="layui-input-inline">
							<select name="pid" id="pid" lay-verify="required" lay-search>
								<option value="">请选择一级分类</option>
								<c:forEach var="item" items="${classes}">
									<option value="${item.id}" <c:if test="${not empty entity.pid and entity.pid eq item.id}">selected</c:if> >${item.classname}</option>
								</c:forEach>
							</select>
						</div>
					</div>
				</div>
			</c:if>
			<div class="layui-form-item">
				<div class="layui-inline">
					<label class="layui-form-label">分类名称</label>
					<div class="layui-input-inline">
						<input type="text" id="name" name="classname" lay-verify="required||name" autocomplete="off" class="layui-input">
					</div>
				</div>
			</div>
			<c:if test="${empty level or level eq 1}">
				<div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label">是否共享</label>
						<div class="layui-input-inline">
							<input type="checkbox" id="isOpen"  <c:if test="${not empty entity.isOpen and entity.isOpen==1}">checked=""</c:if> name="isOpen" lay-skin="switch" value="1" lay-filter="switchTest" lay-text="ON|OFF">
						</div>
					</div>
				</div>
			</c:if>
			<div class="layui-form-item">
				<div class="layui-inline">
					<label class="layui-form-label">排序</label>
					<div class="layui-input-inline">
						<input type="number" id="sort" name="sort" lay-verify="required||sort" autocomplete="off" class="layui-input">
					</div>
				</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-input-block">
					<input type="hidden" id="id" name="id">
					<input type="hidden" id="level" name="level">
					<button class="layui-btn" lay-submit="" lay-filter="demo1">立即提交</button>
				</div>
			</div>
		</div>
	</div>
</div>
<script>
    layui.use('element', function(){
        var element = layui.element;
    });
    layui.use(['form','layer'], function(){
        var form = layui.form;
        var layer = layui.layer;
        //监听提交
        form.on('submit(demo1)', function(data){
            $.ajax({
				url:"/class/save",
				data:data.field,
				dataType:"json",
				type:"post",
				success:function (data) {
				    var iconType = 1;
					if (data.code==0){
                        iconType = 2;
					}else{
                        parent.tableIns.reload();
					}
                    layer.alert(data.msg,{icon:iconType},function(){
                        parent.layer.closeAll();
					});
                }
			})
        });

        form.verify({
            name: function(value){
                if(value.length < 2){
                    return '行业名称至少得5个字符啊';
                }
                var msg = verifyName(value);
                if (msg != null && msg != "" && msg != undefined) {
                    return msg;
				}
            }
            ,sort: [/^([1-9]\d*|[0]{1,1})$/, '只能输入整数']
        });

		var id = "${entity.id}";
		if (id!=""){
            form.val('example', {
                "id": "${entity.id}",
                "classname": "${entity.classname}",
                "sort": "${entity.sort}",
				"level" : "${entity.level}",
				"pid" : "${entity.pid}"
            })
		}else{
            form.val('example', {
                "level" : "${level}"
            })
		}
    });

    function verifyName(value) {
        var msg = "";
        $.ajax({
            url:"/class/verify",
            data:{name:value,id:$("#id").val()},
            dataType:"json",
            async:false,
            type:"post",
            success:function (data) {
                if (data.code==0){
                    msg = data.msg;
                }
            }
        });
        return msg;
    }

</script>
</body>
</html>