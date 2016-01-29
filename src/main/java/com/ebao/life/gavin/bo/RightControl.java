package com.ebao.life.gavin.bo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ebao.life.gavin.dao.RightControlDAO;
import com.ebao.life.gavin.vo.ModuleVO;

public class RightControl {
	
	private final RightControlDAO dao = new RightControlDAO();
	
	
	public boolean hasRight(List<ModuleVO> allTitleList, String page) throws Exception {
		boolean flag = false;
		for(ModuleVO vo : allTitleList){
			if(page.equals(vo.getModuleName())){
				flag = true;
			}
		}
		
		return flag;
	}
	
	public List<ModuleVO> getTitleListByStaffCodeOrIp(String staffCodeOrIp) throws Exception{
		return dao.getTitleListByStaffCodeOrIp(staffCodeOrIp);
	}

	public List<ModuleVO> getFirstTitleList(List<ModuleVO> allTitleList){
		List<ModuleVO> firstTitleList = new ArrayList<ModuleVO>();
		
		for(ModuleVO vo : allTitleList){
			if("1".equals(vo.getModuleType())){
				firstTitleList.add(vo);
			}
		}
		
		return firstTitleList;
		
	}
	
	public Map<String,List<ModuleVO>> getSecondTitleMap(List<ModuleVO> allTitleList){
		Map<String,List<ModuleVO>> secondTitleMap = new HashMap<String,List<ModuleVO>>();
		for(ModuleVO vo : allTitleList){
			if("2".equals(vo.getModuleType())){
				List<ModuleVO> secondList = secondTitleMap.get(vo.getParentModule());
				if(secondList == null){
					secondList = new ArrayList<ModuleVO>();
					secondTitleMap.put(vo.getParentModule(), secondList);
				}
				secondList.add(vo);
			}
		}
		return secondTitleMap;
	}

}
