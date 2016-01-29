<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<link href="/company/static/css/bookmark.css" rel="stylesheet"type="text/css">
<link href="/company/static/css/pure-min.css"  rel="stylesheet" type="text/css">
<style>
.button-left {
    margin-left: 30%;
}
.button-line {
    float: left;
    margin-left: 30px;
}

.button-group-left {
    margin-left: 20%;
    margin-top: 10%;
}

</style>
<%
%>
<title>CQ导入</title>

<div>
<div>
	<div class="button-group-left">
  	<button class="pure-button pure-button-primary button-line  button-left" onclick="sr()"><span class="button_desc">SR模板</span></button>
    <button class="pure-button pure-button-primary button-line" onclick="desgin()"><span class="button_desc">Design模板</span></button>
    <button class="pure-button pure-button-primary button-line" onclick="coding()"><span class="button_desc">Coding模板</span></button>
    </div>
</div>
</div>
<script>
$.post("importcq", 
		{
		sAction:"init"
		},  
		function(data, status) {
			
	},"json"
		);	
function sr(){
	var url = "importcq?sAction=sr";
	window.open(url);
}
function export_data(){
	var url = "overtime?sAction=export";
	window.open(url);
}
function mailToBoss(){
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
function mailToStaff(){
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
</script>