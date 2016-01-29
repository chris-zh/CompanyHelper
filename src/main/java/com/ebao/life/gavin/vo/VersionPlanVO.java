package com.ebao.life.gavin.vo;

import java.util.Date;
/**
 * ∂‘”¶±Ìebao_version_plan
 * @author chris.zhang
 *
 */
public class VersionPlanVO {
	private long planId;
	private String versionNumber;
	private Date requestEndTime;
	private Date versionConfirmTime;
	private Date devStartTime;
	private Date devEndTime;
	private Date testStartTime;
	private Date testEndTime;
	private Date releaseTime;
	public long getYear() {
		return year;
	}
	public void setYear(long year) {
		this.year = year;
	}
	private long devDays;
	private long testDays;
	private long year;
	public String getVersionNumber() {
		return versionNumber;
	}
	public void setVersionNumber(String versionNumber) {
		this.versionNumber = versionNumber;
	}
	public long getPlanId() {
		return planId;
	}
	public void setPlanId(long planId) {
		this.planId = planId;
	}
	public Date getRequestEndTime() {
		return requestEndTime;
	}
	public void setRequestEndTime(Date requestEndTime) {
		this.requestEndTime = requestEndTime;
	}
	public Date getVersionConfirmTime() {
		return versionConfirmTime;
	}
	public void setVersionConfirmTime(Date versionConfirmTime) {
		this.versionConfirmTime = versionConfirmTime;
	}
	public Date getDevStartTime() {
		return devStartTime;
	}
	public void setDevStartTime(Date devStartTime) {
		this.devStartTime = devStartTime;
	}
	public Date getDevEndTime() {
		return devEndTime;
	}
	public void setDevEndTime(Date devEndTime) {
		this.devEndTime = devEndTime;
	}
	public Date getTestStartTime() {
		return testStartTime;
	}
	public void setTestStartTime(Date testStartTime) {
		this.testStartTime = testStartTime;
	}
	public Date getTestEndTime() {
		return testEndTime;
	}
	public void setTestEndTime(Date testEndTime) {
		this.testEndTime = testEndTime;
	}
	public Date getReleaseTime() {
		return releaseTime;
	}
	public void setReleaseTime(Date releaseTime) {
		this.releaseTime = releaseTime;
	}
	public long getDevDays() {
		return devDays;
	}
	public void setDevDays(long devDays) {
		this.devDays = devDays;
	}
	public long getTestDays() {
		return testDays;
	}
	public void setTestDays(long testDays) {
		this.testDays = testDays;
	}

}
