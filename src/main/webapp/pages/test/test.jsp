<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../common/header.jsp"%>
<div class="layui-body">
    <div class="layui-main" style="margin-top: 15px">
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" id="sub">立即提交</button>
            </div>
        </div>
    </div>
</div>
<div class="layui-footer">
    <!-- 底部固定区域 -->
</div>
</div>
<script>
    //JavaScript代码区域
    layui.use(['element','layedit','layer','laydate','upload'], function(){
        var layer = layui.layer;
    });
    //监听提交
    $("#sub").click(function(){
        layer.open({
            type: 2,
            skin: 'layui-layer-rim', //加上边框
            area: ['420px', '240px'], //宽高
            content: '/test/test1'
        });
    });
</script>
</body>
</html>