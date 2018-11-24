<%@page import="java.util.UUID"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../common/header.jsp"%>
	<div class="layui-body">
		<div class="layui-main" style="margin-top: 15px">
			<div class="layui-form" action="" id="layui-form">
				<fieldset class="layui-elem-field">
					<legend>CHECKLIST管理</legend>
					<div class="layui-field-box">
						<div class="layui-field-title">
							<button class="layui-btn layui-btn-radius layui-btn-normal layui-btn-sm" id="exportExcel">
								<i class="layui-icon layui-icon-download-circle"></i>导出EXCEL</button>
						</div>
						<table class="layui-table" lay-filter="checkList" lay-size="sm" lay-skin="line">
							<colgroup>
								<col width="20%"><col width="20%"><col width="20%"><col width="20%"><col width="20%">
							</colgroup>
							<thead>
							<tr>
								<th align="center">Checklist</th>
								<th>关注点</th>
								<th>有，且影响大</th>
								<th>有，但影响小</th>
								<th>没有</th>
							</tr>
							</thead>
							<tbody class="layui-tbody">
								<c:forEach items="${listMap}" var="map" varStatus="mq">
									<c:forEach items="${map.value}" var="item" varStatus="iq">
										<tr class="${item.pid}_${item.id}">
											<c:if test="${iq.first}">
												<td rowspan="${map.value.size()}">${map.key.checkname}</td>
											</c:if>
											<td class="${item.pid}">${item.checkname}</td>
											<td>
												<input type="checkbox" class="switch" name="switch" lay-text="ON|OFF" lay-skin="switch" lay-filter="switch">
												<div></div>
											</td>
											<td>
												<input type="checkbox" class="switch" name="switch" lay-text="ON|OFF" lay-skin="switch" lay-filter="switch">
												<div></div>
											</td>
											<td>
												<input type="checkbox" class="switch" name="switch" lay-text="ON|OFF" lay-skin="switch" lay-filter="switch">
												<div></div>
											</td>
										</tr>
									</c:forEach>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</fieldset>
			</div>
		</div>
	</div>
	<div class="layui-footer"></div>
</div>
<script>
    layui.use(['element','layer','form'], function(){
        var element = layui.element;
        var layer = layui.layer;
        var form = layui.form;
        var $ = layui.$;

        $("#exportExcel").click(function () {
            var arr = new Array();
			$(".layui-tbody").find("tr").each(function () {
			    var check = "";
			    $(this).find("input[type='checkbox']").each(function () {
                    check += this.checked + ",";
                });
                arr.push({id:$(this).attr("class"),check:check});
            });
            var arrStr = JSON.stringify(arr);
            $.ajax({
                url:"/checkList/exportExcel",
                data:{arrStr:arrStr},
                dataType : "json",
                type : "post",
                success : function (result) {
                    if (result.code==1){
                        var index = layer.alert("保存成功，请下载文件。",{icon:1},function () {
                            window.open("/checkList/excelDown?excelName="+result.map.excelName);
                            layer.close(index)
                        });
                    }else{
                        layer.alert(result.msg, {icon:2});
                    }
                },
                error:function () {
                    layer.closeAll();
                    layer.alert("网络有误，请稍后重试或连续管理人员！",{icon:2})
                }
			});

        });
    });
</script>
</body>
</html>