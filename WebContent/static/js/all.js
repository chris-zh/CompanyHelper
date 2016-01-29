$(function() {
	var userIP = $("#userIP").val();
	var ipTail = "";
	if(userIP && userIP != null && userIP != 'null'){
		ipTail = userIP.split('.')[3];
	}
	$.ajaxSetup({ // 解决ajax提交中文乱码问题
				contentType : "application/x-www-form-urlencoded; charset=UTF-8"
			});
	
	$("div.banner-wrapper").click(function(){
		var height = $(this).next().children("div.promotion-thumbnails").height() - 8;
		$(this).next().find("div.promotion-thumbnails div.dbconfig").height(height).toggle().siblings().toggle();
	});
	
	// 请假记录页面
	var holidayDetailClick = function() {
		$detaildiv = $(this).next(); //.parents("div.holidayshort")
		if($detaildiv.is(":visible")) {
			//$detaildiv.children("div:not([class~=hide])").find("div").slideUp(200);
			//$detaildiv.children("div:not([class~=hide])").slideUp(200);
			$detaildiv.slideUp(500);
		}else{
			//$detaildiv.children("div:not([class~=hide])").find("div").slideDown(200);
			//$detaildiv.children("div:not([class~=hide])").slideDown(200);
			$detaildiv.slideDown(500);
		}
		$clickBtn = $(this).find("div.detailBtnDiv a");
		if ($clickBtn.text() == "详情") {
			$clickBtn.text("隐藏");
		} else if ($clickBtn.text() == "隐藏") {
			$clickBtn.text("详情");
		}
	};
	//$("a.detailBtn").click(holidayDetailClick);
	$("div.holidayshort").click(holidayDetailClick);
	
	$(".toggleDiv").hover(
		function(){			
			if(!$(this).hasClass("selected"))
				$(this).addClass("hovered");
		},function(){	
				$(this).removeClass("hovered");
		}				  
	);
	$(".toggleDiv").click(function() {
		//$(this).css({"color" : "#097BAD"});
		//$(this).css({"background":"#EDE9E4"});
		$(this).addClass("selected");
		$(this).siblings().removeClass("selected");
		if ($(this).hasClass("unusedToggle")) {
			$(this).parent().siblings(".detailPanel.unusedPanel").removeClass("hide").show()
			.siblings(".detailPanel").addClass("hide").hide();
			//$(this).parent().siblings(".detailPanel:not(.unusedPanel)").hide();
		} else if ($(this).hasClass("usedToggle")) {
			$(this).parent().siblings(".detailPanel.usedPanel").removeClass("hide").show()
			.siblings(".detailPanel").addClass("hide").hide();
			//$(this).parent().siblings(".detailPanel:not(.usedPanel)").hide();
		} else if ($(this).hasClass("allToggle")) {
			$(this).parent().siblings(".detailPanel.allPanel").removeClass("hide").show()
			.siblings(".detailPanel").addClass("hide").hide();
			//$(this).parent().siblings(".detailPanel:not(.allPanel)").hide();
		}
	});
	
	// 通讯录页面
	// 显示员工详细
	var staffDetailClick = function() {
		var $detaildiv = $(this);
		if(!$(this).hasClass("namecard")){
			$detaildiv = $(this).parents("div.namecard");
		}
		var $detailBtn = $detaildiv.find(".staffDetailBtn");
		if ($detailBtn.text() == "详情") {
			$detaildiv.animate({"height":"235px"});
			// $detaildiv.find(".fvalue.mfvalue").animate({"margin-left":"99px"});
			// $detaildiv.find(".fvalue.mfvalue").css("margin-left","99px");
			$detaildiv.find(".padding").animate({"width":"153px"});
			$detaildiv.css("background-color","#E9F8FC");
			$detailBtn.text("隐藏");
		} else if ($detailBtn.text() == "隐藏") {
			$detaildiv.animate({"height":"30px"});
			// $detaildiv.find(".fvalue.mfvalue").animate({"margin-left":"-55px"});
			// $detaildiv.find(".fvalue.mfvalue").css("margin-left","-55px");
			$detaildiv.find(".padding").animate({"width":"0px"});
			//$detaildiv.animate({"background-color":"#F7F7F7"});
			$detailBtn.text("详情");
	}};
	//$("a.staffDetailBtn").click(staffDetailClick);
	$(".namecard .nameshort").click(staffDetailClick);
	
	// XML格式化页面
	var formatxml = function() {
		$(".msg").hide();
		$.post("xmlformat", {
			sAction : "formatXML",
			xmlPacket : $("#contents_req").val()
		}, function(data, status) {
			//console.log(data.reXML);
			//console.log(data.msg);
			if(data.reXML){
			$("#contents_req").val(data.reXML);
			}else if(data.msg){
				$(".msg").show();
				$(".msg span").text(data.msg);
			}
			//alert("Data: " + data + "\nStatus: " + status);
		}, "json");
	};
	
	$(".formatBtn").click(formatxml);
	
	// Hessian测试页面
	$(".fm").click(formatxml);
	var exeBtnAjax = function() {		
				$(".exeBtn").text("执行中...");	
				$(".exeBtn").unbind();	
				$.post("hessian", 
						$("div.hessian_paras form").serialize(), 
						function(data, status) {
							$("#contents_res").val(data);
							$(".exeBtn").bind("click",exeBtnAjax);
							$(".exeBtn").text("执 行");	
							//alert("Data: " + data + "\nStatus: " + status);
						});
	};
	$(".exeBtn").bind("click",exeBtnAjax);
	
	// 记事本页面
	var hoverNote = function(){
		$delImg = $(this).find(".deleteImg");
		if($delImg.height() == "12" ){
			$delImg.height("0px");
		}else{
			$delImg.height("12px");
		}
	};
	var focusNote = function(){
		if(!$(this).hasClass("highlightTitle")){
			//$(this).filter(":not(.addnote)").children("input").css({"background":"linear-gradient(to bottom, #ededed 5%, #C5ECE7 100%)","background":"-webkit-linear-gradient(top, #ededed 5%, #C5ECE7 100%)"});
			$(this).parents(".note:not(.addnote)").children("input").addClass("highlightTitle");
			$(this).parents(".note:not(.addnote)").siblings().children("input").removeClass("highlightTitle");
			//.css({"background":"linear-gradient(to bottom, #ededed 5%, #f5f5f5 100%)","background":"-webkit-linear-gradient(top, #ededed 5%, #f5f5f5 100%)"});
			
			// get note content via ajax
			$("#loading").show();
			$.post("notepad", 
					{
					noteId : $(this).parent().attr("id"),
					sAction : "noteContent"
					},  
					function(data, status) {
						$("#note_contents").val(data);
						$("#loading").hide();
						//alert("Data: " + data + "\nStatus: " + status);
						$("#ajaxstatus").remove();
				});	
		}
	};
	$(".note").hover(hoverNote,hoverNote);
	$(".note input.noteTitle").focus(focusNote);
	
	
	var clickDelImg =	function(){
		$(this).parents(".note").remove();
		$.post("notepad", 
				{
				noteId : $(this).parent().attr("id"),
				sAction : "noteDelete"
				},  
				function(data, status) {
					// if(data = "Y") delete complete
					// donothing
			});	
	};
	$(".deleteImg").click(clickDelImg);
	
	$(".addnote").click(
		function(){
			// 笔记ID生成规则， IP结尾加上当前毫秒时间
			if($(this).parent().children("div").size() < 20){// 最多20条笔记
				var curTime = new Date().getTime(); 
				var noteID = ipTail + '' + curTime;
				
				$newnote = $('<div class="note" id="' + noteID + '"><input class="noteTitle"  maxlength="8"  value="New" spellcheck="false"/> <img class="deleteImg" width="12px" height="12px" src="/company/static/img/delete1.png"/></div>');
				$(this).parent().children("div:last").after($newnote);			
				$newnote.hover(hoverNote,hoverNote);
				$newnote.children("input.noteTitle").focus(focusNote);
//				$newnote.children("input.noteTitle").keydown(saveNote);
				$newnote.find(".deleteImg").click(clickDelImg);
			}
		}
	);
	
	var saveNote = function(e) {
		//console.log(e);//打印event，观察各属性，代替查阅文档，调试用。
		if (e.keyCode == 83 && e.ctrlKey) {
			e.preventDefault();
			var title = $(".highlightTitle").val();
			$("#loading").show();
			$.post("notepad", 
					{
					noteId : $(".highlightTitle").parent().attr("id"),
					noteTitle : title,
					noteContent : $("#note_contents").val(),
					sAction : "saveNote"
					},  
					function(data, status) {
						$("#loading").hide();
				});	
		}
	};
	$(document).keydown(saveNote);
//	$("textarea#note_contents").keydown(saveNote);
//	$("input.noteTitle").keydown(saveNote);
	
	// 书签页面
//	$("li.thumbnail").hover(function(){$(this).toggleClass("highlight");},
//							function(){$(this).toggleClass("highlight");});
	
	// 日志页面
	$("select#log_file_name").change(function(){
			if($(this).val() == ""){
				$("#contents_res").val("");
			}else{
				window.location.href = "/company/log/" + $(this).val();
				/*
				$(this).after("<span id='ajaxstatus'>读取中...</span>");
				$.post("log", 
						{
						log_file_name : $(this).val(),
						sAction : "loadLog"
						},  
						function(data, status) {
							$("#contents_res").val(data);
							//alert("Data: " + data + "\nStatus: " + status);
							$("#ajaxstatus").remove();
					});	
				*/
			}
	});
});