<%@ page import="java.math.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.ebao.life.gavin.vo.StaffVO"%>
<%@ page isELIgnored="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="/company/static/css/contact.css" rel="stylesheet" type="text/css" />
<title>通讯录</title>
<style>
.sortPanel {
    overflow: hidden;
    width: 681px;
}

.sortBy {
    float: left;
    width: 338px;
    padding: 2px 0px;
    border-left: 1px solid #F4F4F4;
    border-right: 1px solid #F4F4F4;
    background-color: #EAEAEA;
    cursor: pointer;
}

</style>

<script>
	$(
		function(){
			$(".sortBy").click(
					function(){
						var sortBy = $(this).hasClass("sortByCode")?"code":"name";
						var token = "∧"==$(this).children("span.sortToken").text()?"desc":"asc";
						window.location.href = "/company/contact?sortBy="+sortBy+"&token="+token;
					});
		}	
	);

</script>
<%
	boolean isSortByName = "name".equals(request.getParameter("sortBy"));
	String token = "desc".equals(request.getParameter("token"))?"∨":"∧";
%>

<div class="sortPanel">
  <div class="sortBy sortByName"><span>按姓名排序 </span><span class="sortToken"><%=isSortByName?token:"" %></span></div>
  <div class="sortBy sortByCode"><span>按工号排序 </span><span class="sortToken"><%=isSortByName?"":token %></span></div>
</div>
<%
	List<StaffVO> staffList =  (List<StaffVO>) request.getAttribute("staffList");
	if(staffList == null) staffList = new ArrayList<StaffVO>();
	for (StaffVO staff : staffList) {
%>

		<div class="namecard">
			<div class="nameshort">
				<div class="empname">
					<span><%=staff.getEmpName()%></span>
				</div>
				<div class='detailBtnDiv'>
					<a href='javascript:void(0);' class='staffDetailBtn'>详情</a>
				</div>
				<div class="mobile">
					<span class="field mfield">手机</span>
					<div class="padding">&nbsp;</div>
					<span class="fvalue mfvalue"><%=staff.getMobile() %></span>
				</div>
			</div>
			<div class="staffDetail">
				<span class="field">性别</span> <span class="fvalue"><%=staff.getGender() %>&nbsp;</span>
				<span class="field">员工号</span> <span class="fvalue"><%=staff.getStaffCode() %>&nbsp;</span>
				<span class="field">岗位</span> <span class="fvalue"><%=staff.getRole() %>&nbsp;</span>
				<span class="field">IP</span> <span class="fvalue"><%=staff.getIp() %>&nbsp;</span>
				<span class="field">分机号</span> <span class="fvalue"><%=staff.getExt() %>&nbsp;</span>
				<span class="field">座位号</span> <span class="fvalue"><%=staff.getSeatNo() %>&nbsp;</span>
				<span class="field">邮箱</span> <span class="fvalue"><a href="mailto:<%=staff.getEmail() %>"><%=staff.getEmail() %></a>&nbsp;</span>
				<span class="field">开始工作时间</span><span class="fvalue"><%=staff.getWorkDate() %>&nbsp;</span>
				<span class="field">加入易保时间</span><span class="fvalue"><%=staff.getInEbaoDate() %>&nbsp;</span>
				<span class="field">进入太平时间</span><span class="fvalue"><%=staff.getInTpDate() %>&nbsp;</span>
			</div>
		</div>
<%
	}
%>