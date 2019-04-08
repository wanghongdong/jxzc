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
						<label class="layui-form-label"><span class="required">*</span>坐标集</label>
						<div class="layui-input-inline">
							<input name="nodePoints" id="nodePoints" class="layui-input" lay-verify="required" lay-search=""></input>
						</div>
					</div>
				</div>
				<div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label"><span class="required">*</span>省</label>
						<div class="layui-input-inline">
							<select name="mapprovince" id="mapprovince" onchange="search(this)"></select>
						</div>
					</div>
					<div class="layui-inline">
						<label class="layui-form-label"><span class="required">*</span>市</label>
						<div class="layui-input-inline">
							<select name="mapcity" id="mapcity" onchange="search(this)"></select>
						</div>
					</div>
				</div>
				<div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label"><span class="required">*</span>区</label>
						<div class="layui-input-inline">
							<select name="mapdistrict" id="mapdistrict" onchange="search(this)"></select>
						</div>
					</div>
				</div>
				<div id="mapContainer" class="map" style="width:1200px; height: 600px;"></div>
				<div class="layui-form-item">
					<div class="layui-input-block">
						<button class="layui-btn" lay-submit="" lay-filter="submitForm">立即提交</button>
						<button type="index.jsp" class="layui-btn layui-btn-primary">重置</button>
					</div>
				</div>
			</form>
		</div>
	</div>
	<div class="layui-footer">
		<!-- 底部固定区域 -->
	</div>
</div>
<script type="text/javascript" src="https://webapi.amap.com/maps?v=1.3&key=db4b48fb15aecc4e23ffd395910dd5a6&plugin=AMap.ToolBar,AMap.DistrictSearch,AMap.PolyEditor"></script>
<script>
    //JavaScript代码区域
    layui.use(['element','form','layer'], function(){
        var element = layui.element;
        var form = layui.form;
		var layer = layui.layer;
    });

	var map;
	var district;
	var provinceSelect = $("#mapprovince");
	var citySelect = $("#mapcity");
	var districtSelect = $("#mapdistrict")
	var point = "",polygon = "";

	map = new AMap.Map('mapContainer', {
		resizeEnable: true,
		zoom: 8
	});

	//注意：需要使用插件同步下发功能才能这样直接使用
	district = new AMap.DistrictSearch({
		subdistrict: 1,   //  显示下级行政区级数，0：不返回下级行政区,1：返回下一级行政区,2：返回下两级行政区,3：返回下三级行政区 默认值：1
		//关键字对应的行政区级别或商圈，可选值： (country：国家) (province：省/直辖市) (city：市) (district：区/县) (biz_area：商圈)
		level: "province",
		showbiz:false  //是否显示商圈，默认值true
	});

	district.search('中国', function(status, result) {
		if(status=='complete'){
			getData(result.districtList[0]);
		}
	});

	function search(obj){
		var option = $(obj).find("option:selected");
		var keyword = option.html().trim(); //关键字
		var adcode = option.prop('adcode');
		//清除数据
		clearNode();
		if(keyword=='--请选择--'){
			if($(obj).is("#mapprovince")){
				citySelect.html("");
				districtSelect.html("");
			}
			if($(obj).is("#mapcity")){
				districtSelect.html("");
			}
		}else{
			district.setLevel(option.val()); //行政区级别
			district.setExtensions('all');   //返回行政区划边点坐标
			//按照adcode进行查询可以保证数据返回的唯一性
			district.search(adcode, function(status, result) {
				if(status === 'complete'){
					getData(result.districtList[0]);
				}
			});
		}
	}

	function getData(data) {
		var bounds = data.boundaries;	//行政区的边界坐标集合
		var subList = data.districtList;   //下级行政区信息列表
		var level = data.level;	//行政区划级别

		getPoint(bounds);
		if (subList) {
			var html = "<option>--请选择--</option>"
			subList.forEach(function(item){
				html += "<option value=\""+ item.level +"\" center=\""+ item.center +"\" adcode=\""+ item.adcode +"\">"+ item.name +"</option>"
			})
			$('#map' + subList[0].level).append(html);
		}
	}

	function getPoint(bounds){
		if (bounds){
			point = "";
			bounds.forEach(function(item, index){
				item.forEach(function(obj){
					point += obj.lng + "," + obj.lat+";";
				});
				point += item[0].lng + "," + item[0].lat+";";
			});
			$("#nodePoints").val(point);
			draw(bounds);
		}
	}

	function draw(arr){
		if (arr!=null || arr !='' || arr!=undefined){
			polygon = new AMap.Polygon({
				path: arr,
				strokeColor: "#0000ff",
				strokeOpacity: 1,
				strokeWeight: 1,
				fillColor: "#f5deb3",
				fillOpacity: 0.5
			});
			map.add(polygon);
			map.setFitView([ polygon ]);//地图自适应
			// 添加编辑控件
			var editorTool = new AMap.PolyEditor(map, polygon);
			editorTool.open(); //开启编辑   close()  关闭编辑
			//绑定事件.在结束编辑的时候,获取多边形对象.
			AMap.event.addListener(editorTool, "adjust", function(e) {
				var pathPoint = "";	//每次都要重置一次.
				var pointAll = e.target.getPath();
				pointAll.forEach(function(item){
					pathPoint += item.lng + "," + item.lat+";";
				})
				pathPoint += pointAll[0].lng + "," + pointAll[0].lat+";";
				$("#nodePoints").val(pathPoint);
			});
		}
	}

	function clearNode(){
		//清除地图上所有覆盖物
		map.clearMap();
		point="";
		$("#nodePoints").val(point);
	}

</script>
</body>
</html>