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
						<label class="layui-form-label"><span class="required">*</span>日期</label>
						<div class="layui-input-inline">
							<input type="text" name="workTime" id="workTime" lay-verify="required|date" readonly="readonly" placeholder="日期选择" autocomplete="off" class="layui-input">
						</div>
					</div>
					<div class="layui-inline">
						<label class="layui-form-label"><span class="required">*</span>行业类别</label>
						<div class="layui-input-inline">
							<select name="industrycategory" id="industrycategory" lay-verify="required" lay-search="">
								<option value="">直接选择或搜索选择</option>
								<c:forEach items="${categoryList}" var="item">
									<option value="${item.id}">${item.name}</option>
								</c:forEach>
							</select>
						</div>
					</div>
				</div>

				<div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label"><span class="required">*</span>一级分类</label>
						<div class="layui-input-inline">
							<select name="oneclass" id="oneclass" lay-filter="oneclass" lay-verify="required">
								<option value=""></option>
								<c:forEach items="${oneClasses}" var="item">
									<option value="${item.id}">${item.classname}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="layui-inline">
						<label class="layui-form-label"><span class="required">*</span>二级分类</label>
						<div class="layui-input-inline">
							<select name="twoclass" id="twoclass" lay-filter="twoclass" lay-verify="required">
								<option value=""></option>
							</select>
						</div>
					</div>
				</div>
				<div class="layui-form-item layui-form-text">
                  <label class="layui-form-label">相关事件</label>
                  <div class="layui-input-block">
                    <textarea class="layui-textarea layui-hide" name="relatedEvents" placeholder="最多输入500个字符" lay-verify="" id="LAY_relatedEvents_editor"></textarea>
                  </div>
                </div>
                <div class="layui-form-item layui-form-text">
                    <label class="layui-form-label">个人点评</label>
                    <div class="layui-input-block">
                        <textarea class="layui-textarea layui-hide" name="personalComments" placeholder="最多输入500个字符" lay-verify="" id="LAY_personalComments_editor"></textarea>
                    </div>
                </div>
				<div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label">条目一</label>
						<div class="layui-input-inline">
							<div class="layui-upload">
								<button type="button" class="layui-btn test-upload" id="test1">上传图片</button>
								<div class="layui-upload-list">
									<img class="layui-upload-img" id="demo1" height="140%" width="140%">
									<p id="demoText1"></p>
								</div>
                                <input type="hidden" name="file1" id="file1">
                            </div>
						</div>
					</div>
				</div>
				<div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label">条目二</label>
						<div class="layui-input-inline">
							<div class="layui-upload">
								<button type="button" class="layui-btn test-upload" id="test2">上传图片</button>
								<div class="layui-upload-list">
									<img class="layui-upload-img" id="demo2" height="140%" width="140%">
									<p id="demoText2"></p>
								</div>
                                <input type="hidden" name="file2" id="file2">
							</div>
						</div>
					</div>
				</div>
				<div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label">条目三</label>
						<div class="layui-input-inline">
							<div class="layui-upload">
								<button type="button" class="layui-btn test-upload" id="test3">上传图片</button>
								<div class="layui-upload-list">
									<img class="layui-upload-img" id="demo3" height="140%" width="140%">
									<p id="demoText3"></p>
								</div>
                                <input type="hidden" name="file3" id="file3">
							</div>
						</div>
					</div>
				</div>
				<div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label">条目四</label>
						<div class="layui-input-inline">
							<div class="layui-upload">
								<button type="button" class="layui-btn test-upload" id="test4">上传图片</button>
								<div class="layui-upload-list">
									<img class="layui-upload-img" id="demo4" height="140%" width="140%">
									<p id="demoText4"></p>
								</div>
                                <input type="hidden" name="file4" id="file4">
							</div>
						</div>
					</div>
				</div>
				<div class="layui-form-item">
					<div class="layui-input-block">
						<button class="layui-btn" lay-submit="" lay-filter="submitForm">立即提交</button>
						<button type="reset" class="layui-btn layui-btn-primary">重置</button>
					</div>
				</div>
			</form>
		</div>
	</div>
	<div class="layui-footer">
		<!-- 底部固定区域 -->
	</div>
</div>
<script>
    //JavaScript代码区域
    layui.use(['element','form','layedit','layer','laydate','upload'], function(){
        var element = layui.element;
        var layer = layui.layer;
        var form = layui.form;
        var layedit = layui.layedit;
        var laydate = layui.laydate;
        //执行一个laydate实例
        laydate.render({
            elem: '#workTime' //指定元素
            ,calendar: true
            ,value: new Date()
            ,isInitValue: true
        });
        var relatedEventsIndex = layedit.build('LAY_relatedEvents_editor',{tool: []}); //建立编辑器
        var personalCommentsIndex = layedit.build('LAY_personalComments_editor',{tool: []}); //建立编辑器
        form.on('select(oneclass)', function(data){
            var onClassId = data.value;
            if (onClassId!=null && onClassId!="" && onClassId!=undefined){
                var loadIndex = layer.load(2);
                $.ajax({
                    url:"/workReport/queryTwoClasses",
                    data:{pid:onClassId},
                    dataType : "json",
                    type : "post",
                    async : false,
                    success : function (result) {
                        if (result.code==1){
                            var html = "<option value=\"\"></option>";
                            result.map.classes.forEach(function (item,index) {
                                html += "<option value=\""+ item.id +"\">"+ item.classname +"</option>";
                            });
                            $("#twoclass").html(html)
                            form.render('select');
                            layer.closeAll();
                        }else{
                            layer.closeAll();
                            layer.alert("未查询到数据，请稍后重试或连续管理人员！",{icon:2})
                        }
                    },
                    error:function () {
                        layer.closeAll();
                        layer.alert("网络有误，请稍后重试或连续管理人员！",{icon:2})
                    }
                })
            }
        });
        var $ = layui.jquery
            ,upload = layui.upload;
        //普通图片上传
        var uploadInst1 = upload.render({
            elem: '#test1'
            ,url: '/file'
            ,accept : "images"
            ,acceptMime: 'image/*'
            ,size: 2*1024
            ,before: function(obj){
                //预读本地文件示例，不支持ie8
                obj.preview(function(index, file, result){
                    $('#demo1').attr('src', result); //图片链接（base64）
                });
            }
            ,done: function(res, index, upload){
                //如果上传失败
                if(res.code == 0){
                    return layer.msg('上传失败',{icon:2});
                }else{
                    var id = res.map.id;
                    $("#file1").val(id);
                }
            }
            ,error: function(){
                //演示失败状态，并实现重传
                var demoText1 = $('#demoText1');
                demoText1.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
                demoText1.find('.demo-reload').on('click', function(){
                    uploadInst1.upload();
                });
            }
        });
        var uploadInst2 = upload.render({
            elem: '#test2'
            ,url: '/file'
            ,accept : "images"
            ,acceptMime: 'image/*'
            ,size: 2*1024
            ,before: function(obj){
                //预读本地文件示例，不支持ie8
                obj.preview(function(index, file, result){
                    $('#demo2').attr('src', result); //图片链接（base64）
                });
            }
            ,done: function(res){
                //如果上传失败
                if(res.code == 0){
                    return layer.msg('上传失败',{icon:2});
                }else{
                    var id = res.map.id;
                    $("#file2").val(id);
                }
                //上传成功
            }
            ,error: function(){
                //演示失败状态，并实现重传
                var demoText2 = $('#demoText2');
                demoText2.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
                demoText2.find('.demo-reload').on('click', function(){
                    uploadInst2.upload();
                });
            }
        });
        var uploadInst3 = upload.render({
            elem: '#test3'
            ,url: '/file'
            ,accept : "images"
            ,acceptMime: 'image/*'
            ,size: 2*1024
            ,before: function(obj){
                //预读本地文件示例，不支持ie8
                obj.preview(function(index, file, result){
                    $('#demo3').attr('src', result); //图片链接（base64）
                });
            }
            ,done: function(res){
                //如果上传失败
                if(res.code == 0){
                    return layer.msg('上传失败',{icon:2});
                }else{
                    var id = res.map.id;
                    $("#file3").val(id);
                }
                //上传成功
            }
            ,error: function(){
                //演示失败状态，并实现重传
                var demoText3 = $('#demoText3');
                demoText3.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
                demoText3.find('.demo-reload').on('click', function(){
                    uploadInst3.upload();
                });
            }
        });
        var uploadInst4 = upload.render({
            elem: '#test4'
            ,url: '/file'
            ,accept : "images"
            ,acceptMime: 'image/*'
            ,size: 2*1024
            ,before: function(obj){
                //预读本地文件示例，不支持ie8
                obj.preview(function(index, file, result){
                    $('#demo4').attr('src', result); //图片链接（base64）
                });
            }
            ,done: function(res){
                //如果上传失败
                if(res.code == 0){
                    return layer.msg('上传失败',{icon:2});
                }else{
                    var id = res.map.id;
                    $("#file4").val(id);
                }
                //上传成功
            }
            ,error: function(){
                //演示失败状态，并实现重传
                var demoText4 = $('#demoText4');
                demoText4.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
                demoText4.find('.demo-reload').on('click', function(){
                    uploadInst4.upload();
                });
            }
        });
        //监听提交
        form.on('submit(submitForm)', function(data){
            var relatedEvents = layedit.getText(relatedEventsIndex);
            var personalComments = layedit.getText(personalCommentsIndex);
            data.field.relatedEvents = relatedEvents;
            data.field.personalComments = personalComments;
            if(relatedEvents!=null && relatedEvents!="" && relatedEvents!=undefined && relatedEvents.length>500){
				layer.alert("相关事件最多输入500个字符！",{icon:2});
				return false;
			}
            if(personalComments!=null && personalComments!="" && personalComments!=undefined && personalComments.length>500){
                layer.alert("个人点评最多输入500个字符！",{icon:2});
                return false;
            }
            // data.field.relatedEvents = layedit.getContent(relatedEventsIndex);
            // data.field.personalComments = layedit.getContent(personalCommentsIndex);
            var index = layer.load(1);
            $.ajax({
				url:"/workReport/saveReport",
                data:{report:JSON.stringify(data.field)},
                dataType:"json",
                type:"post",
				success:function (res) {
                    layer.close(index);
					if (res.code==1){
                        layer.alert("保存成功，请下载文件。",{icon:1},function () {
                            window.open("/workReport/reportExcelDown?excelName="+res.map.excelName);
                            window.location.href="/workReport/index";
                            // $.get("/reportExcelDown?excelName="+res.map.excelName, function(result){
                            //     window.location.href="/index";
                            // });
                        });
					}else{
                        layer.alert(res.msg, {icon:2});
					}
                },error:function (res,msg,error) {
                    layer.close(index);
					layer.alert("网络出错，code："+res.code,{icon:2});
                }
			});
            return false;
        });
    });
</script>
</body>
</html>