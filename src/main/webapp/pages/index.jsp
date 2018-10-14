<%@page import="java.util.UUID"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="common/header.jsp"%>
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

				<div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label">一级分类</label>
						<div class="layui-input-inline">
							<select name="interest" lay-filter="aihao">
								<option value=""></option>
								<option value="0">写作</option>
								<option value="1" selected="">阅读</option>
								<option value="2">游戏</option>
								<option value="3">音乐</option>
								<option value="4">旅行</option>
							</select>
						</div>
					</div>
					<div class="layui-inline">
						<label class="layui-form-label">二级分类</label>
						<div class="layui-input-inline">
							<select name="interest" lay-filter="aihao">
								<option value=""></option>
								<option value="0">写作</option>
								<option value="1" selected="">阅读</option>
								<option value="2">游戏</option>
								<option value="3">音乐</option>
								<option value="4">旅行</option>
							</select>
						</div>
					</div>
				</div>
				<div class="layui-form-item layui-form-text">
                  <label class="layui-form-label">编辑器</label>
                  <div class="layui-input-block">
                    <textarea class="layui-textarea layui-hide" name="content" lay-verify="content" id="LAY_demo_editor"></textarea>
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
							</div>
						</div>
					</div>
				</div>

				<div class="layui-form-item">
					<div class="layui-input-block">
						<button class="layui-btn" lay-submit="" lay-filter="demo1">立即提交</button>
						<button type="reset" class="layui-btn layui-btn-primary">重置</button>
					</div>
				</div>
			</form>
		</div>
	</div>
	<div class="layui-footer">
		<!-- 底部固定区域 -->
		© layui.com - 底部固定区域
	</div>
</div>
<script>
    //JavaScript代码区域
    layui.use('element', function(){
        var element = layui.element;
    });
    layui.use('form', function(){
        var form = layui.form;

        //监听提交
        form.on('submit(formDemo)', function(data){
            layer.msg(JSON.stringify(data.field));
            return false;
        });
    });
    layui.use('laydate', function(){
        var laydate = layui.laydate;

        //执行一个laydate实例
        laydate.render({
            elem: '#date' //指定元素
        });
    });
    layui.use('layedit', function(){
        var layedit = layui.layedit;
        layedit.build('LAY_demo_editor'); //建立编辑器
    });

    layui.use('upload', function(){
        var $ = layui.jquery
            ,upload = layui.upload;

        //普通图片上传
        var uploadInst = upload.render({
            elem: '#test1'
            ,url: '/upload/'
            ,before: function(obj){
                //预读本地文件示例，不支持ie8
                obj.preview(function(index, file, result){
                    $('#demo1').attr('src', result); //图片链接（base64）
                });
            }
            ,done: function(res){
                //如果上传失败
                if(res.code > 0){
                    return layer.msg('上传失败');
                }
                //上传成功
            }
            ,error: function(){
                //演示失败状态，并实现重传
                var demoText1 = $('#demoText1');
                demoText1.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
                demoText1.find('.demo-reload').on('click', function(){
                    uploadInst.upload();
                });
            }
        });
        var uploadInst = upload.render({
            elem: '#test2'
            ,url: '/upload/'
            ,before: function(obj){
                //预读本地文件示例，不支持ie8
                obj.preview(function(index, file, result){
                    $('#demo2').attr('src', result); //图片链接（base64）
                });
            }
            ,done: function(res){
                //如果上传失败
                if(res.code > 0){
                    return layer.msg('上传失败');
                }
                //上传成功
            }
            ,error: function(){
                //演示失败状态，并实现重传
                var demoText2 = $('#demoText2');
                demoText2.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
                demoText2.find('.demo-reload').on('click', function(){
                    uploadInst.upload();
                });
            }
        });
        var uploadInst = upload.render({
            elem: '#test3'
            ,url: '/upload/'
            ,before: function(obj){
                //预读本地文件示例，不支持ie8
                obj.preview(function(index, file, result){
                    $('#demo3').attr('src', result); //图片链接（base64）
                });
            }
            ,done: function(res){
                //如果上传失败
                if(res.code > 0){
                    return layer.msg('上传失败');
                }
                //上传成功
            }
            ,error: function(){
                //演示失败状态，并实现重传
                var demoText3 = $('#demoText3');
                demoText3.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
                demoText3.find('.demo-reload').on('click', function(){
                    uploadInst.upload();
                });
            }
        });
        var uploadInst = upload.render({
            elem: '#test4'
            ,url: '/upload/'
            ,before: function(obj){
                //预读本地文件示例，不支持ie8
                obj.preview(function(index, file, result){
                    $('#demo4').attr('src', result); //图片链接（base64）
                });
            }
            ,done: function(res){
                //如果上传失败
                if(res.code > 0){
                    return layer.msg('上传失败');
                }
                //上传成功
            }
            ,error: function(){
                //演示失败状态，并实现重传
                var demoText4 = $('#demoText1');
                demoText4.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
                demoText4.find('.demo-reload').on('click', function(){
                    uploadInst.upload();
                });
            }
        });
    });
</script>
</body>
</html>