<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<link href="/company/static/css/hessian.css" rel="stylesheet" type="text/css">
<title>Hessian接口</title>

<body>
	<div class="hessian_paras">
		<form>
			<span class="inp exeSpan"> <a href='#' class='exeBtn'>执 行</a><input id="sAction" name="sAction" type="hidden" value="hessianTest">
			</span> <span class="inp"> 接口URL <input id="hessian_url" name="hessian_url" type="text" maxlength="200" size="12" placeholder="hessian url" name="q" autocomplete="off">
			</span> <span class="inp"> Java类 <input id="hessian_interface" name="hessian_interface" type="text" maxlength="200" size="12" placeholder="java interface" name="q" autocomplete="off">
			</span> <span class="inp"> Java方法 <input id="hessian_method" name="hessian_method" type="text" maxlength="90" size="12" placeholder="method of the interface for hessian" name="q" autocomplete="off">
			</span>
			<div class="areatag" ><span >请求报文</span><a href="#" class="fm">F</a></div>
			<div class="stack ">
				<div class="layer_1">
					<div class="layer_2">
						<div class="layer_3">
							<textarea name="contents_req" id="contents_req" class="contents" wrap="off" spellcheck="false">${contents_req}</textarea>
						</div>
					</div>
				</div>
			</div>
			<div class="areatag"><span>返回报文</span></div>
			<div class="stack ">
				<div class="layer_1">
					<div class="layer_2">
						<div class="layer_3">
							<textarea name="contents_res" id="contents_res" class="contents"  wrap="off" spellcheck="false">${contents_res}</textarea>
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>