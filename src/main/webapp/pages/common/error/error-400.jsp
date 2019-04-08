<%@page import="java.util.UUID"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>WOWAYZY-400</title>
    <link rel="stylesheet" href="../../../js/layui/css/layui.css">
    <link rel="stylesheet" href="../../../css/admin.css">
    <script src="../../../js/layui/layui.js"></script>
    <script src="../../../js/jq.min.js"></script>
</head>
<div class="layui-fluid">
    <div class="layadmin-tips">
        <i class="layui-icon" face=""></i>
        <div class="layui-text" style="font-size: 20px;">
            <div class="layui-collapse" lay-filter="test">
                <div class="layui-colla-item">
                    400
                </div>
            </div>
        </div>

    </div>
</div>

<script>
    layui.use('element', function(){
        var element = layui.element;
    });
</script>
</body>
</html>