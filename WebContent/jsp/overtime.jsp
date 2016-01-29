<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<link href="/company/static/css/bookmark.css" rel="stylesheet"
	type="text/css">
<style>
.ipt {
    width: 50%;
    /* float: left; */
    margin-top: 10px;
    height: 22px;
    /* color: blue; */
}
.desc {
    width: 30%;
    display: block;
    float: left;
    color: #57A1D3;
}
.version {
    /* clear: both; */
    /* width: 100%; */
    overflow: hidden;
}
.ver {
    text-align: left;
}
.ver_value {
    color: #B91B2A;
}
.button_desc {
    color: #3377AA;
    font-family: punctuation, "PingFangSC-Regular", "Microsoft Yahei", "sans-serif";
}
.overtime_button {
    float: left;
    width: 10%;
    margin: 10px;
    display: block;
}
.pair_button {
    margin-left: 30%;
}
.data{
	color:#B91B2A;
}
.main_send {
    width: 20%;
}
</style>
<%
%>
<title>加班请假</title>

<div>
<div>
  <div class="version" style=""><span class="desc ver">当前版本周 : <span class="data" id="version"></span> 第<span class="data" id="week"></span>周</span></span></div>
  <div class="ipt"><span class="desc">姓名</span>
  <input type="text" name="name" id= "name"></div>
  <div class="ipt"><span class="desc">请假原因</span>
  <input type="text" name="reason" id = "reason"></div>
  <div class="pair_button">
  <button class="overtime_button" onclick="save()"><span class="button_desc">添加</span></button>
  </div>
  <div class="pair_button">
    <button class="overtime_button" onclick="export_data()"><span class="button_desc">导出</span></button>
  </div>
  <div class="pair_button">
    <button class="overtime_button main_send" onclick="mailToBoss()"><span class="button_desc">发送给韩董</span></button>
 </div>
 <div class="pair_button">
    <button class="overtime_button main_send" onclick="mailToStaff()"><span class="button_desc">发送给核心</span></button>
 </div>
</div>
</div>
<script language="javascript">
$.post("overtime", 
		{
		sAction:"init"
		},  
		function(data, status) {
			$("#loading").hide();
			$("#version").text(data.version);
			$("#week").text(data.week);
	},"json"
		);	
function save(){
	$.post("overtime", 
			{
			name : $("#name").val(),
			reason : $("#reason").val(),
			sAction:"save"
			},  
			function(data, status) {
				$("#loading").hide();
				alert(data);
		});	
}
function export_data(){
	var url = "overtime?sAction=export";
	window.open(url);
}
function mailToBoss(){
	if(confirm("约吗？")){
		$.post("overtime", 
				{
				name : $("#name").val(),
				reason : $("#reason").val(),
				sAction:"mailToBoss"
				},  
				function(data, status) {
					$("#loading").hide();
					alert(data);
			});	
	}
}
function mailToStaff(){
	if(confirm("约吗？")){
	$.post("overtime", 
			{
			name : $("#name").val(),
			reason : $("#reason").val(),
			sAction:"mailToStaff"
			},  
			function(data, status) {
				$("#loading").hide();
				alert(data);
		});	
	}
}
</script>