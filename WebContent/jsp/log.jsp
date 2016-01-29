<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<title>日志管理</title>

<style>
	.selectDiv {
		margin-top: 15px;
    	height: 20px;
	}
	.selectDiv span{
		display: block;
	    padding-left: 250px;
	    float: left;
	}
	.selectArea {
		height: 20px;
    	float: left;
	}
	.selectArea select{
		width: 150px;
	}
	.stack {
		height: 728px;
	}
                .stack .layer_1 .layer_2 .layer_3 .contents {
					height: 730px;
                }
</style>

<body>
	<div class="formatPanel">
			<div class="selectDiv">
				<span>
					日志文件名：
				</span>
				<div class="selectArea">
					<select name="log_file_name" id="log_file_name">
						<option value="">&nbsp;</option>
						<%
							String[] filenames = (String[])request.getAttribute("filenames");
							String selectedFilename = (String)request.getAttribute("selectedFilename");
							String logContent = (String)request.getAttribute("logContent");
							if(filenames != null){
								for(String filename: filenames){
									if(!filename.contains("-")){
										String selected = filename.equals(selectedFilename)?"selected":"";
						%>
									<option value="<%=filename%>" <%=selected%>><%=filename%></option>
						<%
								
									}
								}
							}
						%>
					</select>
				</div>
			</div>
			<div class="stack">
				<div class="layer_1">
					<div class="layer_2">
						<div class="layer_3">
							<textarea name="contents_res" id="contents_res" class="contents" wrap="off" spellcheck="false"><%=logContent%></textarea>
						</div>
					</div>
				</div>
			</div>
	</div>