package com.ebao.life.gavin.servlet;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ebao.life.gavin.bo.HolidayBO;
import com.ebao.life.gavin.bo.StaffBO;
import com.ebao.life.gavin.vo.HolidayVO;
import com.ebao.life.gavin.vo.StaffVO;

@WebServlet("/holiday")
public class HolidayServlet extends RootServlet {
	private static final long serialVersionUID = 1L;
	private final HolidayBO holidayBO = new HolidayBO();

	private final StaffBO staffBO = new StaffBO();

	public void actionRequest(HttpServletRequest request, HttpServletResponse response, String action) throws Exception {

	}

	public void pageRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String admin = request.getParameter("admin");
		String empName = (String) request.getAttribute("empName");
		Map<String, BigDecimal[]> sumHolidayMap = new HashMap<String, BigDecimal[]>();
		// if(配置IP权限，部分IP可获得所有人员)
		// Map<String,List<HolidayVO>> empHolidayMap =
		// holidayBO.retriveAllEmpHoliday();
		Map<String, List<HolidayVO>> empHolidayMap = new HashMap<String, List<HolidayVO>>();
		if ("liuzhenwei".equals(admin)) {
			empHolidayMap = holidayBO.retriveAllEmpHoliday();
		} else {
			empHolidayMap = holidayBO.retriveHolidayByEmp(empName);
		}
		for (String emp : empHolidayMap.keySet()) { // 兼容多个员工
			BigDecimal[] sumHoliday = holidayBO.getSumHoliday(empHolidayMap.get(emp));
			sumHolidayMap.put(emp, sumHoliday);
		}

		List<StaffVO> outStaffList = staffBO.retriveOutServiceStaffs();
		empHolidayMap = holidayBO.removeOutServiceEmp(empHolidayMap, outStaffList);
		empHolidayMap = holidayBO.sortEmpHoliday(empHolidayMap); // 去掉已使用

		request.setAttribute("empHolidayMap", empHolidayMap);
		request.setAttribute("sumHolidayMap", sumHolidayMap);

	}
	
}
