<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.math.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.ebao.life.gavin.vo.NoteVO"%>

<link href="/company/static/css/notepad.css" rel="stylesheet"
	type="text/css">
<title>记事本</title>

<style>

#loading {
	display:none;
	position: absolute;
	top: 2px;
	right: 2px;
	width: 14px;
	height: 7px;
	background: url('/company/static/img/loading.gif') -1px -4px no-repeat transparent;
	border: 1px solid #9ba0a5;
	opacity: 0.4;
	-moz-opacity: 0.4;
	filter: alpha(opacity=40);
	-webkit-transition: opacity 1s linear;
	-moz-transition: opacity 1s linear;
	transition: opacity 1s linear;
	-webkit-border-radius: 1px;
	-moz-border-radius: 1px;
	border-radius: 1px;
}

</style>

<div class="notepadPanel">
	<div class="titleDiv">
		<div class="note addnote">
			<a href='javascript:void(0);' class='noteTitle'>+</a>
		</div>
		<%
			List<NoteVO> noteList = (List<NoteVO>) request.getAttribute("noteList");
			if (noteList == null) noteList = new ArrayList<NoteVO>();
			for (NoteVO note : noteList) {
		%>
			<div class="note" id="<%=note.getNoteId()%>">
				<input class='noteTitle' maxlength="8" spellcheck="false" value="<%=note.getNoteTitle()%>"/> <img
					class="deleteImg" width="12px" height="12px"
					src="/company/static/img/delete1.png" />
			</div>
		<%
			}
		%>
	</div>
	<div class="stack">
		<div class="layer_1">
			<div class="layer_2">
				<div class="layer_3">
					<textarea name="note_contents" id="note_contents"
						placeholder="需Ctrl+S手动保存" class="contents" wrap="off"
						spellcheck="false"></textarea>
				</div>
			</div>
		</div>
	</div>
	<div id="loading" style="/* display:none; */"></div>
</div>