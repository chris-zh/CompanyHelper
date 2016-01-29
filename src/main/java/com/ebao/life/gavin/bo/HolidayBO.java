package com.ebao.life.gavin.bo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.ebao.life.gavin.dao.HolidayDAO;
import com.ebao.life.gavin.util.ToolUtil;
import com.ebao.life.gavin.vo.HolidayVO;
import com.ebao.life.gavin.vo.StaffVO;


public class HolidayBO {
	private final HolidayDAO dao = new HolidayDAO();

	public Map<String, List<HolidayVO>> retriveHolidayByEmp(String empName) throws Exception {
		// TODO Auto-generated method stub
		return dao.retriveHolidayByEmp(empName);
	}
	
	public Map<String, List<HolidayVO>> retriveAllEmpHoliday() throws Exception {
		// TODO Auto-generated method stub
		return dao.retriveAllEmpHoliday();
	}

	/**
	 * @param voList 员工所有假期
	 * @return 员工假期总计array, array[0] 所有假期, array[1] 剩余假期
	 */
	public BigDecimal[]  getSumHoliday(List<HolidayVO> voList) {
		BigDecimal sumAll = BigDecimal.ZERO;
		BigDecimal sumUsed = BigDecimal.ZERO;
		for(HolidayVO vo:voList){
			if(vo.getHolidayLong() != null){
				sumAll = sumAll.add((vo.getHolidayLong().compareTo(BigDecimal.ZERO)>0)?vo.getHolidayLong():BigDecimal.ZERO);
				sumUsed = sumUsed.add(vo.getHolidayLong());
			}
		}
		BigDecimal[] reArr = {sumAll,sumUsed}; 
		return reArr;
	}

	/**
	 * 遍历HolidayVO, 去掉已使用的Holiday
	 * 
	 * @param empMap 
	 * @return
	 */
	public Map<String, List<HolidayVO>> sortEmpHoliday(Map<String,List<HolidayVO>> empMap) {
		Map<String, List<HolidayVO>> tmpMap = new LinkedHashMap<String, List<HolidayVO>>();
		for(String empName: empMap.keySet()){
			List<HolidayVO> voList = empMap.get(empName);
			List<HolidayVO> tmpVoList = new ArrayList<HolidayVO>();
			
			for(HolidayVO vo: voList){
				if(vo.getHolidayLong().compareTo(BigDecimal.ZERO) > 0){ //可用假期
					String holidayDate = vo.getHolidayDate();
					boolean flag = true;
					for(HolidayVO vo2: voList){
						if(vo2.getHolidayLong().compareTo(BigDecimal.ZERO)<0 && holidayDate.equals(vo2.getReferenceDate())){ //有被使用
							if(vo.getLeftHolidayLong().compareTo(BigDecimal.ZERO) <= 0){ //可能出现reference_date为相同的记录
								continue;
							}
							vo.setLeftHolidayLong(vo.getLeftHolidayLong().add(vo2.getHolidayLong()));
							vo.setUseDay(vo2.getHolidayDate());
							vo.setUseType(vo2.getHolidayType());
							vo.setUseLong(vo2.getHolidayLong().abs());
							tmpVoList.add(vo);
							flag = false;
						}
					}
					if(flag){
						tmpVoList.add(vo);
					}
				} else if(vo.getHolidayLong().compareTo(BigDecimal.ZERO) < 0){ //已用假期
					if(ToolUtil.isEmpty(vo.getReferenceDate())){ //使用了无调休的事假
						vo.setUseDay(vo.getHolidayDate());
						vo.setUseType(vo.getHolidayType());
						vo.setUseLong(vo.getHolidayLong());
						tmpVoList.add(vo);
					}
				}
			}
			
			tmpMap.put(empName, tmpVoList);
		}
		return tmpMap;
	}

	public Map<String, List<HolidayVO>> removeOutServiceEmp(Map<String, List<HolidayVO>> empHolidayMap, List<StaffVO> outStaffList) {
		for(StaffVO vo:outStaffList){
			empHolidayMap.remove(vo.getEmpName());
		}
		return empHolidayMap;
	}
	
	

}
