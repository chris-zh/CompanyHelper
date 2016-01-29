package com.ebao.life.gavin.vo;

public class StaffVO {
	/*
	 * EMP_ID, EMP_NAME, ROLE, STAFF_CODE, STAFF_TYPE, PROF_LEVEL, REPORTO,
	 * CHARGE_MODULE, TP_CARD_CODE, EXT, MOBILE, EMAIL, WORK_DATE, IN_EBAO_DATE,
	 * IN_TP_DATE, LEFT_DATE, IS_INVER, IS_SA, GENDER, SEAT_NO, IS_CORE,
	 * LS_WORK_DATE, IP, ID_CARD, NOTEBOOK_PC_MODEL, NOTEBOOK_PC_NUM,
	 * HOST_PC_NUM, SCREEN_PC_NUM, BAOXIAO, DESCRIPT
	 */

	private String empId;
	private String empName;
	private String role;
	private String staffCode;
	private String staffType;
	private String profLevel;
	private String reporto;
	private String charge_module;
	private String tpCardCode;
	private String ext;
	private String mobile;
	private String email;
	private String workDate;
	private String inEbaoDate;
	private String inTpDate;
	private String leftDate;
	private String isInver;
	private String isSa;
	private String gender;
	private String seatNo;
	private String isCore;
	private String lsWorkDate;
	private String ip;
	private String idCard;
	private String notebookPc_model;
	private String notebookPcNum;
	private String hostPcNum;
	private String screenPcNum;
	private String baoxiao;
	private String descript;

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getStaffCode() {
		return staffCode;
	}

	public void setStaffCode(String staffCode) {
		this.staffCode = staffCode;
	}

	public String getStaffType() {
		return staffType;
	}

	public void setStaffType(String staffType) {
		this.staffType = staffType;
	}

	public String getProfLevel() {
		return profLevel;
	}

	public void setProfLevel(String profLevel) {
		this.profLevel = profLevel;
	}

	public String getReporto() {
		return reporto;
	}

	public void setReporto(String reporto) {
		this.reporto = reporto;
	}

	public String getCharge_module() {
		return charge_module;
	}

	public void setCharge_module(String charge_module) {
		this.charge_module = charge_module;
	}

	public String getTpCardCode() {
		return tpCardCode;
	}

	public void setTpCardCode(String tpCardCode) {
		this.tpCardCode = tpCardCode;
	}

	public String getExt() {
		return ext==null?"":ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWorkDate() {
		return workDate;
	}

	public void setWorkDate(String workDate) {
		this.workDate = workDate;
	}

	public String getInEbaoDate() {
		return inEbaoDate;
	}

	public void setInEbaoDate(String inEbaoDate) {
		this.inEbaoDate = inEbaoDate;
	}

	public String getInTpDate() {
		return inTpDate;
	}

	public void setInTpDate(String inTpDate) {
		this.inTpDate = inTpDate;
	}

	public String getLeftDate() {
		return leftDate;
	}

	public void setLeftDate(String leftDate) {
		this.leftDate = leftDate;
	}

	public String getIsInver() {
		return isInver;
	}

	public void setIsInver(String isInver) {
		this.isInver = isInver;
	}

	public String getIsSa() {
		return isSa;
	}

	public void setIsSa(String isSa) {
		this.isSa = isSa;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getSeatNo() {
		return seatNo;
	}

	public void setSeatNo(String seatNo) {
		this.seatNo = seatNo;
	}

	public String getIsCore() {
		return isCore;
	}

	public void setIsCore(String isCore) {
		this.isCore = isCore;
	}

	public String getLsWorkDate() {
		return lsWorkDate;
	}

	public void setLsWorkDate(String lsWorkDate) {
		this.lsWorkDate = lsWorkDate;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getNotebookPc_model() {
		return notebookPc_model;
	}

	public void setNotebookPc_model(String notebookPc_model) {
		this.notebookPc_model = notebookPc_model;
	}

	public String getNotebookPcNum() {
		return notebookPcNum;
	}

	public void setNotebookPcNum(String notebookPcNum) {
		this.notebookPcNum = notebookPcNum;
	}

	public String getHostPcNum() {
		return hostPcNum;
	}

	public void setHostPcNum(String hostPcNum) {
		this.hostPcNum = hostPcNum;
	}

	public String getScreenPcNum() {
		return screenPcNum;
	}

	public void setScreenPcNum(String screenPcNum) {
		this.screenPcNum = screenPcNum;
	}

	public String getBaoxiao() {
		return baoxiao;
	}

	public void setBaoxiao(String baoxiao) {
		this.baoxiao = baoxiao;
	}

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();

		sb.append("\n");
		sb.append("StaffVO.getEmpId():" + this.getEmpId());
		sb.append("\n");
		sb.append("StaffVO.getEmpName():" + this.getEmpName());
		sb.append("\n");
		sb.append("StaffVO.getRole():" + this.getRole());
		sb.append("\n");
		sb.append("StaffVO.getStaffCode():" + this.getStaffCode());
		sb.append("\n");
		sb.append("StaffVO.getStaffType():" + this.getStaffType());
		sb.append("\n");
		sb.append("StaffVO.getProfLevel():" + this.getProfLevel());
		sb.append("\n");
		sb.append("StaffVO.getReporto():" + this.getReporto());
		sb.append("\n");
		sb.append("StaffVO.getCharge_module():" + this.getCharge_module());
		sb.append("\n");
		sb.append("StaffVO.getTpCardCode():" + this.getTpCardCode());
		sb.append("\n");
		sb.append("StaffVO.getExt():" + this.getExt());
		sb.append("\n");
		sb.append("StaffVO.getMobile():" + this.getMobile());
		sb.append("\n");
		sb.append("StaffVO.getEmail():" + this.getEmail());
		sb.append("\n");
		sb.append("StaffVO.getWorkDate():" + this.getWorkDate());
		sb.append("\n");
		sb.append("StaffVO.getInEbaoDate():" + this.getInEbaoDate());
		sb.append("\n");
		sb.append("StaffVO.getInTpDate():" + this.getInTpDate());
		sb.append("\n");
		sb.append("StaffVO.getLeftDate():" + this.getLeftDate());
		sb.append("\n");
		sb.append("StaffVO.getIsInver():" + this.getIsInver());
		sb.append("\n");
		sb.append("StaffVO.getIsSa():" + this.getIsSa());
		sb.append("\n");
		sb.append("StaffVO.getGender():" + this.getGender());
		sb.append("\n");
		sb.append("StaffVO.getSeatNo():" + this.getSeatNo());
		sb.append("\n");
		sb.append("StaffVO.getIsCore():" + this.getIsCore());
		sb.append("\n");
		sb.append("StaffVO.getLsWorkDate():" + this.getLsWorkDate());
		sb.append("\n");
		sb.append("StaffVO.getIdCard():" + this.getIdCard());
		sb.append("\n");
		sb.append("StaffVO.getNotebookPc_model():" + this.getNotebookPc_model());
		sb.append("\n");
		sb.append("StaffVO.getNotebookPcNum():" + this.getNotebookPcNum());
		sb.append("\n");
		sb.append("StaffVO.getHostPcNum():" + this.getHostPcNum());
		sb.append("\n");
		sb.append("StaffVO.getScreenPcNum():" + this.getScreenPcNum());
		sb.append("\n");
		sb.append("StaffVO.getBaoxiao():" + this.getBaoxiao());
		sb.append("\n");
		sb.append("StaffVO.getDescript():" + this.getDescript());
		sb.append("\n");
		return sb.toString();
	}

}
