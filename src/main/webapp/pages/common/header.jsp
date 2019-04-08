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
<%
    String path = request.getContextPath();
    Integer serverPort = request.getServerPort();
    String basePath;
    if(serverPort==80){
        basePath = "//"+request.getServerName()+path+"/";
    }else{
       basePath = "//"+request.getServerName()+":"+request.getServerPort()+path+"/";
    }
%>
<c:set var="ctx" value="<%=basePath%>"/>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>WOWAYZY</title>
    <link rel="stylesheet" href="${ctx}js/layui/css/layui.css">
    <script src="${ctx}js/layui/layui.js"></script>
    <script src="${ctx}js/jq.min.js"></script>
    <style>
        .layui-upload-img{margin: 0 10px 10px 0;}
        .required{margin-left: 5px; margin-right:5px; color: red;}
    </style>
</head>
<body class="layui-layout-body">
    <div class="layui-layout layui-layout-admin">
        <div class="layui-header">
            <div class="layui-logo">WOWAYZY-玩儿</div>
            <ul class="layui-nav layui-layout-left">
                <li class="layui-nav-item"><a href="/workReport/index">LAYUI DEMO</a></li>
                <li class="layui-nav-item"><a href="/angJs/list">AngularJS</a></li>
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
                <li class="layui-nav-item"><div class="layui-nav-item" id="dateDom"></div></li>
                <li class="layui-nav-item">
                    <a href="javascript:;">
                        <img src="http://t.cn/RCzsdCq" class="layui-nav-img">
                        ${SESSION_CURRENT_USER.username}
                    </a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;">修改密码</a></dd>
                    </dl>
                </li>
            </ul>
        </div>
        <div class="layui-side layui-bg-black">
            <div class="layui-side-scroll">
                <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
                <ul class="layui-nav layui-nav-tree"  lay-filter="menu" id="menu">
                    <c:if test="${empty menuNum or menuNum == 1}">
                        <li class="layui-nav-item"><a href="/workReport/index">首页</a></li>
                        <li class="layui-nav-item"><a href="/industryCategory/list">行业管理</a></li>
                        <li class="layui-nav-item">
                            <a class="" href="javascript:;">分类管理</a>
                            <dl class="layui-nav-child">
                                <dd><a href="/class/list">一级分类管理</a></dd>
                                <dd><a href="/class/list?level=2">二级分类管理</a></dd>
                            </dl>
                        </li>
                        <li class="layui-nav-item"><a href="javascript:void(0);" onclick="checkCheckList();return false;">CHECKLIST管理</a></li>
                    </c:if>
                    <c:if test="${not empty menuNum or menuNum == 2}">
                        <li class="layui-nav-item"><a href="/angJs/list">实例</a></li>
                    </c:if>
                </ul>
            </div>
        </div>
        <script>
            $(function(){
                setInterval(function(){getDate()}, 1000);
                var href = window.location.href;
                $("#menu").find("a").each(function () {
                    if (this.href == href){
                        $(this).addClass("javascript:;");
                        $(this).parents("li").addClass("layui-nav-itemed");
                        if ($(this).parent().get(0).tagName == "DD"){
                            $(this).parent().addClass("layui-this");
                        }
                    }
                });
            });
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
            function checkCheckList() {
                var weekToday = new Date().getDay();
                if (weekToday==0||weekToday==6){
                    layer.alert("非工作日时段，不能导出checkList！",{icon:2},function(){
                        window.location.href="/checkList/list";
                    });
                    return false;
                }
                window.location.href="/checkList/list";
            }
        </script>