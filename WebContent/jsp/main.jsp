
<%@ page import="java.util.*"%>
<%@ page import="com.ebao.life.gavin.vo.ModuleVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="shortcut icon" href="/company/favicon.ico" type="image/x-icon">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="/company/static/js/jquery.js"></script>
<script src="/company/static/js/all.js" type="text/javascript"></script>

<link href="/company/static/css/main.css" rel="stylesheet" type="text/css">
<script>
	
</script>
<style>
</style>
</head>
<body>
	<input type="hidden" id="userIP" name="userIP" value='<%=(String)request.getAttribute("userIP")%>'/>
	<div class="wrapper">
		<div class="navbar">
			<div class="username">
				<div align="left">
					<span>${empName}</span>
				</div>
			</div>
		</div>
<div class="navsecond">
			<!-- 
			<div class="mod maintitle1">
				<h2 class="section-title">
					<a href="javascript:void(0);">信息</a>
				</h2>
				<div class="side-links nav-anon">
					<ul>
						<li><a href="bookmark" id="bookmark">书签</a></li>
						<li><a href="contact" id="contact">通讯录</a></li>
						<li><a href="holiday" id="holiday">请假记录</a></li>
						<li><a href="vote" id="vote">投票</a></li>
						<li><a href="billboard" id="billboard">公告</a></li>
						<li><a href="msgboard" id="msgboard">留言板</a></li>
						<li><a href="overtime" id="overtime">加班请假</a></li>
						<li><a href="importcq" id="importcq">CQ模板</a></li>
					</ul>
				</div>
			</div>

			<div class="mod maintitle2">
				<h2 class="section-title">
					<a href="javascript:void(0);">工具</a>
				</h2>
				<div class="side-links nav-anon">
					<ul>
						<li><a href="xmlformat" id="xmlformat">XML格式化</a></li>
						<li><a href="notepad" id="notepad">记事本</a></li>
					</ul>
				</div>
			</div>

			<div class="mod maintitle3">
				<h2 class="section-title">
					<a href="javascript:void(0);">开发</a>
				</h2>
				<div class="side-links nav-anon">
					<ul>
						<li><a href="hessian" id="hessian">Hessian测试</a></li>
						<li><a href="ftptool" id="ftptool">代码部署</a></li>
						<li><a href="checktool" id="checktool">上线包校对</a></li>
					</ul>
				</div>
			</div>
			
			 -->
			 <%
			 	List<ModuleVO> firstTitleList = (List<ModuleVO>)request.getAttribute("firstTitleList");
			 	Map<String,List<ModuleVO>> secondTitleMap = (Map<String,List<ModuleVO>>)request.getAttribute("secondTitleMap");
			 	for(int i = 0; i < firstTitleList.size(); i++){
			 		ModuleVO firstTitle = firstTitleList.get(i);
			 		List<ModuleVO> secondTitleList = secondTitleMap.get(firstTitle.getModuleId());
			 		if(secondTitleList != null && secondTitleList.size() > 0){
			 %>
			 		<div class="mod maintitle<%=i%>">
						<h2 class="section-title">
							<a href="javascript:void(0);"><%=firstTitle.getModuleDesc() %></a>
						</h2>
						<div class="side-links nav-anon">
							<ul>
								<%for(ModuleVO secondTitle: secondTitleList){ %>
									<li><a href="/company/<%=secondTitle.getModuleName() %>" id="<%=secondTitle.getModuleName()%>"><%=secondTitle.getModuleDesc()%></a></li>
								<%} %>
							</ul>
						</div>
					</div>
			 <%
			 		}
			 	}
			 %>

		</div>
		<div class="content">
			<%
				String topage = (String)request.getAttribute("page");
				String pageJsp = topage + ".jsp";
			%>
   			<jsp:include page="<%=pageJsp%>" flush="true"/>
   			<script>
   				$(function(){
   					var selectedPage = '<%=topage%>';
   					$('#'+selectedPage).css("border-bottom","1px dotted");
   				});
   			
   			</script>
   			
		</div>
		
		<div class="footer">
			<div class="copyright">
				<div align="right">
					<span style="font-size: 11px;">©created by <a href="mailto:ebao.liuzw@waibao.cntaiping.com">zhenwei.liu</a></span>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
