<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2019/4/11
  Time: 22:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
${demo.name}
${demo.age}
<br/>

坐标点：<input name="nodePoints" id="nodePoints" ><br/>
省：<select name="mapprovince" id="mapprovince" onchange="search(this)"></select><br/>
市：<select name="mapcity" id="mapcity" onchange="search(this)"></select><br/>
区：<select name="mapdistrict" id="mapdistrict" onchange="search(this)"></select><br/>
<input type="button" onclick="openEditor()" value="开启编辑">&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" onclick="closeEditor()" value="关闭编辑"><br/>
<div id="mapContainer" style="width:1200px; height: 480px;"></div>
</body>
<script src="../../js/jq.min.js"></script>
<!--<script type="text/javascript" src="https://webapi.amap.com/maps?v=1.4.14&key=d71b63ade11020736e2b13d3ef3aa3bb&plugin=AMap.ToolBar,AMap.DistrictSearch"></script>-->
<script type="text/javascript" src="https://webapi.amap.com/maps?v=1.4.14&key=e154b7157fd92ad5cacdb677f4d1a0cd&plugin=AMap.DistrictSearch"></script>
<script>


    var s = {source:1,
        reason:1,
        type:1,
        phone:18241891872};
    var par = JSON.stringify(s);
    $.ajax({
        url: 'http://localhost:8081/api/userBlack/save.json', //实际使用请改成服务端真实接口
        data: par,
        dataType:"json",
        contentType:"application/json",
        type:"post",
        cache:false,
        success: function(res){
            console.log(res)
        }
    });

    var map;
    var district,editorTool;
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
        console.log("status:" + status);
        console.log("result:" + result.toString());
        if(status=='complete'){
            getData(result.districtList[0]);
        }
    });

    function search(obj){
        var option = $(obj).find("option:selected");
        var keyword = option.html().trim(); //关键字
        var adcode = option.attr('adcode');
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
                console.log("status:"+status);
                console.log("result:"+result);
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
            $('#map' + subList[0].level).html(html);
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
                map:map,
                path: arr,
                strokeColor: "#0000ff",
                strokeOpacity: 1,
                strokeWeight: 1,
                fillColor: "#f5deb3",
                fillOpacity: 0.5
            });
            map.setFitView();//地图自适应
            // 添加编辑控件
        }
    }

    function clearNode(){
        //清除地图上所有覆盖物
        map.clearMap();
        map.setFitView();//地图自适应
        point="";
        $("#nodePoints").val(point);
    }

    function openEditor(){
        map.plugin(["AMap.PolyEditor"], function() {
            editorTool = new AMap.PolyEditor(map, polygon);
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
        });
    }

    function closeEditor(){
        editorTool.close();
    }

</script>
</body>
</html>
