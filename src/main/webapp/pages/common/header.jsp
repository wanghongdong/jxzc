<%--
  Created by IntelliJ IDEA.
  User: whd
  Date: 2018/9/26
  Time: 23:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>layout 后台大布局 - Layui</title>
    <link rel="stylesheet" href="../js/layui/css/layui.css">
    <script src="../js/layui/layui.js"></script>
    <script src="../js/jq.min.js"></script>
    <style>
        .layui-upload-img{margin: 0 10px 10px 0;}
    </style>
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">嘉数-工作总结管理</div>
        <ul class="layui-nav layui-layout-left">
            <%--<li class="layui-nav-item"><a href="">控制台</a></li>--%>
            <%--<li class="layui-nav-item"><a href="">商品管理</a></li>--%>
            <%--<li class="layui-nav-item"><a href="">用户</a></li>--%>
            <%--<li class="layui-nav-item">--%>
                <%--<a href="javascript:;">其它系统</a>--%>
                <%--<dl class="layui-nav-child">--%>
                    <%--<dd><a href="">邮件管理</a></dd>--%>
                    <%--<dd><a href="">消息管理</a></dd>--%>
                    <%--<dd><a href="">授权管理</a></dd>--%>
                <%--</dl>--%>
            <%--</li>--%>
        </ul>
        <ul class="layui-nav layui-layout-right">
            <%--<li class="layui-nav-item">--%>
                <%--<a href="javascript:;">--%>
                    <%--<img src="http://t.cn/RCzsdCq" class="layui-nav-img">--%>
                    <%--贤心--%>
                <%--</a>--%>
                <%--<dl class="layui-nav-child">--%>
                    <%--<dd><a href="">基本资料</a></dd>--%>
                    <%--<dd><a href="">安全设置</a></dd>--%>
                <%--</dl>--%>
            <%--</li>--%>
            <div class="layui-nav-item" id="dateDom"></div>
        </ul>
    </div>
    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree"  lay-filter="test">
                <li class="layui-nav-item"><a href="/index">首页</a></li>
                <li class="layui-nav-item"><a href="/industryCategory/list">行业管理</a></li>
                <li class="layui-nav-item">
                    <a class="" href="javascript:;">分类管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;">一级分类管理</a></dd>
                        <dd><a href="javascript:;">二级分类管理</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item"><a href="">checkList管理</a></li>
            </ul>
        </div>
    </div>
    <script>
        $(function(){
            setInterval(function(){getDate()}, 1000);
        })
        function getDate() {
            var today = new Date();
            var date = today.getFullYear() + "-" + twoDigits(today.getMonth() + 1) + "-" + twoDigits(today.getDate()) + " ";
            var week = " 星期" + "日一二三四五六 ".charAt(today.getDay());
            var time = twoDigits(today.getHours()) + ": " + twoDigits(today.getMinutes()) + ": " + twoDigits(today.getSeconds());
            $("#dateDom").html(date +" "+time);
        }
        function twoDigits(val) {
            if (val < 10)
                return "0" + val;
            return val;
        }
    </script>