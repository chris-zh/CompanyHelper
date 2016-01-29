<%@ page import="java.math.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.ebao.life.gavin.vo.HolidayVO"%>
<%@ page import="com.ebao.life.gavin.util.ToolUtil"%>
<%@ page isELIgnored="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="/company/static/css/holiday.css" rel="stylesheet" type="text/css">
<title>请假记录</title>

<%
	String displayClass = "hide"; 
	String btnTxt = "详情";
	Map<String, List<HolidayVO>> empHolidayMap = (Map<String, List<HolidayVO>>) request.getAttribute("empHolidayMap");
	Map<String, BigDecimal[]> sumHolidayMap = (Map<String, BigDecimal[]>) request.getAttribute("sumHolidayMap");

	if(empHolidayMap == null) empHolidayMap = new HashMap<String, List<HolidayVO>>();
	if(sumHolidayMap == null) sumHolidayMap = new HashMap<String, BigDecimal[]>();
	
	if(empHolidayMap.size() == 1){  //只有一个人的数据时默认显示详情
			displayClass = "";
			btnTxt="隐藏";
	}
	for (String key : empHolidayMap.keySet()) {
		List<HolidayVO> holidayVOList = empHolidayMap.get(key);
%>

		<div class="holidayshort">
			<div class="empname">
				<span><%=key%></span>
			</div>
			<div class='detailBtnDiv'>
				<a href='javascript:void(0);' class='detailBtn'><%=btnTxt%></a>
			</div>
			<div class="holidayNum">
				<span>总<%=ToolUtil.format(sumHolidayMap.get(key)[0])%>天
				</span>&nbsp;&nbsp;<span>剩<%=ToolUtil.format(sumHolidayMap.get(key)[1])%>天
				</span>
			</div>
		</div>
		<div class="holidaydetail <%=displayClass%>">
			<div class="holidayToggle">
				<div class="toggleDiv unusedToggle selected">
					<span>可用假期</span> 
				</div>
				<div class="toggleDiv usedToggle">
					<span>已用假期</span>
				</div>
				<div class="toggleDiv allToggle">
					<span>所有假期</span>
				</div>
			</div>
			
			<!-- 可用 start -->	
			<div class="detailPanel unusedPanel">
				<%
				for (HolidayVO vo : holidayVOList) {
					if (vo.getLeftHolidayLong().compareTo(BigDecimal.ZERO) != 0) {
						String useClass = vo.getLeftHolidayLong().compareTo(BigDecimal.ZERO)>0?"unused":"used";
				%>
						<div class="<%=useClass%>">
							<span class="sp1"><%=vo.getHolidayDate()%> <%=vo.getHolidayType()%><%=ToolUtil.format(vo.getHolidayLong())%>天</span>
							<span class="sp2">
								<%if(vo.getLeftHolidayLong().compareTo(vo.getHolidayLong()) != 0){ %>
									<%=vo.getUseDay()%> <%=vo.getUseType()%><%=ToolUtil.format(vo.getUseLong())%>天
								<%}%>&nbsp;
							</span> 
							<span class="sp3">剩<%=ToolUtil.format(vo.getLeftHolidayLong())%>天</span>
						</div>
				<%
					}
		
				}
				%>
				<div class="sumHoliday">
					<span class="sp1">总计:&nbsp;<%=ToolUtil.format(sumHolidayMap.get(key)[1])%>天</span> 
					<span class="sp2">&nbsp;</span> 
					<span class="sp3">&nbsp;</span>
				</div>
			</div>
			<!-- 可用 end -->
		
			<!-- 已用 start -->
			<div class="detailPanel usedPanel" style="display:none;">
				<% 
				for (HolidayVO vo : holidayVOList) {
					if (vo.getLeftHolidayLong().compareTo(BigDecimal.ZERO) <= 0) {
				%>
						<div class="used">
							<span class="sp1"><%=vo.getHolidayDate()%> <%=vo.getHolidayType()%><%=ToolUtil.format(vo.getHolidayLong())%>天</span>
							<span class="sp2"><%=vo.getUseDay()%> <%=vo.getUseType()%><%=ToolUtil.format(vo.getUseLong())%>天</span>
							<span class="sp3">剩<%=vo.getLeftHolidayLong().compareTo(BigDecimal.ZERO) == 0?0:ToolUtil.format(vo.getLeftHolidayLong())%>天
							</span>
						</div>
				<%
					}	
				}
				%>
				<div class="sumHoliday">
					<span class="sp1">总计:&nbsp;<%=ToolUtil.format((sumHolidayMap.get(key)[0].subtract(sumHolidayMap.get(key)[1])))%>天</span> 
					<span class="sp2">&nbsp;</span> 
					<span class="sp3">&nbsp;</span>
				</div>
			</div>
			<!-- 已用end -->
		
			<!-- 所有 start -->
			<div class="detailPanel allPanel" style="display:none;">
				<%// 所有假期panel
				for (HolidayVO vo : holidayVOList) {
					if (vo.getLeftHolidayLong().compareTo(BigDecimal.ZERO) <= 0) {
				%>
						<div class="used">
							<span class="sp1"><%=vo.getHolidayDate()%> <%=vo.getHolidayType()%><%=ToolUtil.format(vo.getHolidayLong())%>天</span>
							<span class="sp2"><%=vo.getUseDay()%> <%=vo.getUseType()%><%=ToolUtil.format(vo.getUseLong())%>天</span>
							<span class="sp3">剩<%=vo.getLeftHolidayLong().compareTo(BigDecimal.ZERO) == 0?0:ToolUtil.format(vo.getLeftHolidayLong())%>天
							</span>
						</div>
				<%
					}
				%>
				<%
					if (vo.getLeftHolidayLong().compareTo(BigDecimal.ZERO) > 0) {
				%>
						<div class="unused">
							<span class="sp1"><%=vo.getHolidayDate()%> <%=vo.getHolidayType()%><%=ToolUtil.format(vo.getHolidayLong())%>天</span>
							<span class="sp2">
								<%if(vo.getLeftHolidayLong().compareTo(vo.getHolidayLong()) != 0){ %>
									<%=vo.getUseDay()%> <%=vo.getUseType()%><%=ToolUtil.format(vo.getUseLong())%>天
								<%} else{%>
									&nbsp;
								<%}%>
							</span> 
							<span class="sp3">剩<%=ToolUtil.format(vo.getLeftHolidayLong())%>天</span>
						</div>
				<%
					}
				}
				%>
				<div class="sumHoliday">
					<span class="sp1">总计:&nbsp;<%=ToolUtil.format(sumHolidayMap.get(key)[0])%>天</span> 
					<span class="sp2">&nbsp;</span> 
					<span class="sp3">&nbsp;</span>
				</div>
			</div>
			<!-- 所有 end -->
		</div>

<%
	}
%>