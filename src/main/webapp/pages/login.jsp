<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="content-type" content="text/html; charset=UTF-8">
  <meta charset="utf-8">
  <title>嘉数-工作总结管理</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
  <link rel="stylesheet" href="../js/layui/css/layui.css" media="all">
  <link id="layuicss-layuiAdmin" rel="stylesheet" href="../css/admin.css" media="all">
  <link rel="stylesheet" href="../css/login.css" media="all">
</head>
<body layadmin-themealias="default" class="layui-layout-body">
  <div id="LAY_app" class="layadmin-tabspage-none">
    <div class="layadmin-user-login layadmin-user-display-show" id="LAY-user-login" style="display: none;">
      <div class="layadmin-user-login-main">
        <div class="layadmin-user-login-box layadmin-user-login-header">
          <h2>嘉數資產</h2>
          <p>工作總結管理系統</p>
        </div>
        <div class="layadmin-user-login-box layadmin-user-login-body layui-form">
          <div class="layui-form-item">
            <label class="layadmin-user-login-icon layui-icon layui-icon-username" for="LAY-user-login-username"></label>
            <input name="loginname" id="LAY-user-login-username" lay-verify="username" placeholder="用户名" class="layui-input" type="text">
          </div>
          <div class="layui-form-item">
            <label class="layadmin-user-login-icon layui-icon layui-icon-password" for="LAY-user-login-password"></label>
            <input name="password" id="LAY-user-login-password" lay-verify="password" placeholder="密码" class="layui-input" type="password">
          </div>
          <div class="layui-form-item">
            <button class="layui-btn layui-btn-fluid" lay-submit="" lay-filter="LAY-user-login-submit">登 入</button>
          </div>
        </div>
      </div>
      <div class="layui-trans layadmin-user-login-footer">
        <p>
        </p>
      </div>
    </div>
    <script src="../js/jq.min.js"></script>
    <script src="../js/layui/layui.js"></script>
    <script>
        layui.use(['form','layer'], function(){
            var form = layui.form;
            var layer = layui.layer;
            form.on('submit(LAY-user-login-submit)', function(data){
                $.ajax({
                    url: '/login', //实际使用请改成服务端真实接口
                    data: data.field,
                    dataType:"json",
                    type:"post",
                    cache:false,
                    success: function(res){
                        if(res.code==1){
                            // layer.msg(res.msg, {offset: '15px',icon: 1}, function(){
                            //
                            // });
                            window.location.href = '/index';
                        }else{
                            layer.msg(res.msg, {offset: '15px',icon: 2,time: 2000});
                        }
                    }
                });
            });
            form.verify({
                username: function(value, item){ //value：表单的值、item：表单的DOM对象
                    if (value==null || value== "" || value==undefined){
                        return '用户名不能为空';
                    }
                    if(!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(value)){
                        return '用户名不能有特殊字符';
                    }
                    if(/(^\_)|(\__)|(\_+$)/.test(value)){
                        return '用户名首尾不能出现下划线\'_\'';
                    }
                    if(/^\d+\d+\d$/.test(value)){
                        return '用户名不能全为数字';
                    }
                },password: function(value, item){ //value：表单的值、item：表单的DOM对象
                    if (value==null || value== "" || value==undefined){
                        return '密码不能为空';
                    }
                    if(!new RegExp("^[\\S]{6,12}$").test(value)){
                        return '密码必须6到12位，且不能出现空格';
                    }
                }
            });
        });

    </script>
  </div>
  <div></div>
  <style id="LAY_layadmin_theme">.layui-side-menu,.layadmin-pagetabs .layui-tab-title li:after,.layadmin-pagetabs .layui-tab-title li.layui-this:after,.layui-layer-admin .layui-layer-title,.layadmin-side-shrink .layui-side-menu .layui-nav>.layui-nav-item>.layui-nav-child{background-color:#20222A !important;}.layui-nav-tree .layui-this,.layui-nav-tree .layui-this>a,.layui-nav-tree .layui-nav-child dd.layui-this,.layui-nav-tree .layui-nav-child dd.layui-this a{background-color:#009688 !important;}.layui-layout-admin .layui-logo{background-color:#20222A !important;}</style>
  <div class="layui-layer-move"></div>
</body>
</html>