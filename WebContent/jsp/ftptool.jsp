<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<title>代码部署工具</title>

<style>
div.tool-body{
    padding: 15px 30px 15px 30px;
    text-align: left;
    font-family: 微软雅黑, 'Microsoft YaHei', Monaco, 'Helvetica Neue', Helvetica, Arial, sans-serif;
    /* background-color: #fafafa; */
    border: #F4F4F4 solid 1px;
    margin-bottom: 10px;
}

h1.tool-heading {
    color: #5F5F5F;
    font-size: 21px;
    line-height: 1.1;
    margin: 0;
    padding: 0 30px 0 0;
    font-weight: normal;
    text-shadow: 1px 0 1px #FFF;
}

h1 small {
    font-size: 75%;
    font-weight: 400;
    line-height: 1;
    color: #777;
    text-shadow: 1px 0 1px #FFF;
}

.tool-intro {
    margin: 5px 0 0;
    font-size: 12px;
    color: #8d8d8d;
}

.downloadBtn{
	margin-left: 38px;
    padding: 3px 12px 1px 12px;
}

div.default-panel {
    text-align: left;
    background-color: #fafafa;
    border: 1px solid #F4F4F4;
    margin-bottom: 10px;
    /* margin: 0px 20px 10px 20px; */
    width: 95%;
    -webkit-box-sizing: border-box;
       -moz-box-sizing: border-box;
            box-sizing: border-box;
}

.panel-head {
    border-bottom: 1px solid #DEDEDE;
    padding: 0px 10px 0px 10px;
}

.panel-head h2 {
    margin: 0;
    padding: 0;
    font-size: 18px;
    line-height: 30px;
    font-weight: normal;
    color: #428bca;
}

.panel-head h4 {
    margin: 0;
    padding: 0;
    font-size: 14px;
    line-height: 30px;
    font-weight: normal;
    color: #428bca;
}

.panel-head .comment-user{
    color: #555555;
    line-height: 30px;
}

.panel-head .comment-date{
    color: #868585;
    font-size: 12px;
}

.panel-head .comment-version{
    float: right;
    font-size: 12px;
    color: #868585;
    line-height: 30px;
}

.panel-content {
    padding: 10px;
    color: #5F5F5F;
    overflow: hidden;
    clear: both;
}

.comment-area{
	display: block;
    overflow: hidden;
    width: 670px;
    height: 85px;
    padding: 6px 12px;
    font-size: 14px;
    line-height: 1.42857143;
    color: #555;
    background-color: #fff;
    background-image: none;
    border: 1px solid #ccc;
    border-radius: 4px;
    -webkit-box-shadow: inset 0 1px 1px rgba(0,0,0,.075);
    box-shadow: inset 0 1px 1px rgba(0,0,0,.075);
    -webkit-transition: border-color ease-in-out .15s,-webkit-box-shadow ease-in-out .15s;
    -o-transition: border-color ease-in-out .15s,box-shadow ease-in-out .15s;
    transition: border-color ease-in-out .15s,box-shadow ease-in-out .15s;
}

a.commentBtn {
    float: right;
    padding: 2px 5px;
    font-size: 14px;
    margin: 5px 5px 0px 0px;
}

</style>

	<div class="tool-body">
		<h1 class="tool-heading">
			代码部署工具 <small>2.0.0</small>
			<input type="hidden" id="version" value="2.0.0">
			<a href="javascript:void(0);" class="btn downloadBtn">下载</a> 
		</h1>
		<div class="tool-intro">
			<span>20次下载，467个评论，8.07M，2015-01-23更新</span>
		</div>
	</div>

	<div class="default-panel">
		<div class="panel-head">
			<h2>工具简介</h2>
		</div>
		<div class="panel-content">
			1. 通过提供的文件地址，工具将本地文件(class,jsp等)上传到对应服务器上，完成代码部署<br> 
			2. 不再需要使用FTP工具翻找文件夹来上传文件到服务器了<br> 
			3. 当前支持主干开发，BETA开发，Release开发环境 <br>
			4. 支持交叉部署，如主干开发的本地代码部署到BETA开发环境<br>
			5. 支持多个文件同时上传<br>
			6. 自动保存上次成功上传的文件列表<br>
			7. 文件列表支持行注释（-- 和 //）与块注释（/* */），不需要上传的文件可通过注释排除			
		</div>
	</div>
	<div class="default-panel">
		<div class="panel-head">
			<h2>评论(285条)</h2>
		</div>
		
		<div class="panel-content">
			<textarea id="comment-content" maxlength="250" class="comment-area" rows="3"></textarea>		
			<a href="javascript:void(0);" class="btn commentBtn">提交评论</a>
		</div>
	</div>
	
	<div class="default-panel">
		<div class="panel-head">
			<h4>
				<span class="comment-user">10.7.219.132 (刘珍伟)</span>
				<span class="comment-date">2015-11-23</span>
				<span class="comment-version"> 版本：2.0.0 </span>
			</h4>
		</div>
		<div class="panel-content">
			开发人员六六六	
		</div>
	</div>
	
	<div class="default-panel">
		<div class="panel-head box">
			<h4>
				<span class="comment-user">10.7.219.132</span>
				<span class="comment-date">2015-11-23</span>
				<span class="comment-version"> 版本：2.0.0 </span>
			</h4>
		</div>
		<div class="panel-content">
			1. 通过提供的文件地址，工具将本地文件(class,jsp等)上传到对应服务器上，完成代码部署
			2. 不再需要使用FTP工具翻找文件夹来上传文件到服务器了
			3. 当前支持主干开发，BETA开发，Release开发环境 
			4. 通过选择可以将本地的代码上传到不同的服务器上，如主干开发的本地代码部署到BETA开发环境
			5. 支持多个文件同时上传
			6. 自动保存上次成功上传的文件列表
			7. 文件列表支持注释功能，不需要上传的文件可通过注释排除
		</div>
	</div>