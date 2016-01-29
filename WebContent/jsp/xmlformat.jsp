<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<link href="/company/static/css/xmlformat.css" rel="stylesheet"
	type="text/css">
<title>XML格式化</title>
<style>
.msg {
	display: none;
	width: 713px;
	padding: 2px;
	overflow: hidden;
	background-color: #FFDFE2;
	color: rgb(105, 105, 105);
	vertical-align: middle;
	margin-top: 5px;
	margin-bottom: -5px;
	height: 22px;
}
</style>

<body>
	<div class="formatPanel">
		<div class="btnDiv">
			<a href='#' class='formatBtn'>格式化</a> 
		</div>

		<div class="msg">
			<span></span>
		</div>
		<div class="stack">
			<div class="layer_1">
				<div class="layer_2">
					<div class="layer_3">
						<textarea name="contents_req" id="contents_req" class="contents"
							wrap="off" spellcheck="false"></textarea>
					</div>
				</div>
			</div>
		</div>
	</div>