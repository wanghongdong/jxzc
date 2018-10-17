<%--
  Created by IntelliJ IDEA.
  User: whd
  Date: 2018/9/27
  Time: 0:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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