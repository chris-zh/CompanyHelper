package com.ebao.life.gavin.vo;

import java.math.BigDecimal;
import java.text.NumberFormat;

public class HolidayVO {

	
	/*
		EMP_NAME
		HOLIDAY_DATE
		HOLIDAY_TYPE
		REFERENCE_DATE
		HOLIDAY_LONG
		INSERT_TIME
		COMMENTS
	 */

	private String empName;
	private String holidayDate;
	private String holidayType;
	private String referenceDate;
	private BigDecimal holidayLong;
	private String insertTime;
	private String comments;
	
	private BigDecimal leftHolidayLong = BigDecimal.ZERO;
	private String useType;		//该假期被使用类型
	private BigDecimal useLong;	//该假期被使用长度
	private String useDay;		//该假期被使用日期

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getHolidayDate() {
		return holidayDate;
	}

	public void setHolidayDate(String holidayDate) {
		this.holidayDate = holidayDate;
	}

	public String getHolidayType() {
		return holidayType;
	}

	public void setHolidayType(String holidayType) {
		this.holidayType = holidayType;
	}

	public String getReferenceDate() {
		return referenceDate;
	}

	public void setReferenceDate(String referenceDate) {
		this.referenceDate = referenceDate;
	}

	public BigDecimal getHolidayLong() {
		return holidayLong;
	}

	public void setHolidayLong(BigDecimal holidayLong) {
		this.leftHolidayLong = holidayLong;
		this.holidayLong = holidayLong;
	}

	public String getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(String insertTime) {
		this.insertTime = insertTime;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public BigDecimal getLeftHolidayLong() {
		return leftHolidayLong;
	}

	public void setLeftHolidayLong(BigDecimal leftHolidayLong) {
		this.leftHolidayLong = leftHolidayLong;
	}

	public String getUseDay() {
		return useDay;
	}

	public void setUseDay(String useDay) {
		this.useDay = useDay;
	}

	public String getUseType() {
		return useType;
	}

	public void setUseType(String useType) {
		this.useType = useType;
	}

	public BigDecimal getUseLong() {
		return useLong;
	}

	public void setUseLong(BigDecimal useLong) {
		this.useLong = useLong;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();

		sb.append("\n");
		sb.append("HolidayVO.getEmpName():" + this.getEmpName());
		sb.append("\n");
		sb.append("HolidayVO.getHolidayDate():" + this.getHolidayDate());
		sb.append("\n");
		sb.append("HolidayVO.getHolidayType():" + this.getHolidayType());
		sb.append("\n");
		sb.append("HolidayVO.getReferenceDate():" + this.getReferenceDate());
		sb.append("\n");
		sb.append("HolidayVO.getHolidayLong():" + this.getHolidayLong());
		sb.append("\n");
		sb.append("HolidayVO.getInsertTime():" + this.getInsertTime());
		sb.append("\n");
		sb.append("HolidayVO.getComments():" + this.getComments());
		sb.append("\n");
		return sb.toString();
	}
	
	
}
