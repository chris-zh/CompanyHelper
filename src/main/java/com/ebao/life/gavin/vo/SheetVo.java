package com.ebao.life.gavin.vo;

import java.util.List;

import jxl.write.WritableWorkbook;

public class SheetVo {
	private WritableWorkbook wb;
	private String[] firstLine;
	private String[] titles;
	private String[] columns;
	private int[] widths;
	private List list;
	private String sheetName;
	private int sheetNum;
	/**
	 * @return the titles
	 */
	public String[] getTitles() {
		return titles;
	}
	/**
	 * @param titles the titles to set
	 */
	public void setTitles(String[] titles) {
		this.titles = titles;
	}
	/**
	 * @return the columns
	 */
	public String[] getColumns() {
		return columns;
	}
	/**
	 * @param columns the columns to set
	 */
	public void setColumns(String[] columns) {
		this.columns = columns;
	}
	/**
	 * @return the widths
	 */
	public int[] getWidths() {
		return widths;
	}
	/**
	 * @param widths the widths to set
	 */
	public void setWidths(int[] widths) {
		this.widths = widths;
	}
	/**
	 * @return the list
	 */
	public List getList() {
		return list;
	}
	/**
	 * @param list the list to set
	 */
	public void setList(List list) {
		this.list = list;
	}
	/**
	 * @return the wb
	 */
	public WritableWorkbook getWb() {
		return wb;
	}
	/**
	 * @param wb the wb to set
	 */
	public void setWb(WritableWorkbook wb) {
		this.wb = wb;
	}
	/**
	 * @return the firstLine
	 */
	public String[] getFirstLine() {
		return firstLine;
	}
	/**
	 * @param firstLine the firstLine to set
	 */
	public void setFirstLine(String[] firstLine) {
		this.firstLine = firstLine;
	}
	/**
	 * @return the sheetName
	 */
	public String getSheetName() {
		return sheetName;
	}
	/**
	 * @param sheetName the sheetName to set
	 */
	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}
	/**
	 * @return the sheetNum
	 */
	public int getSheetNum() {
		return sheetNum;
	}
	/**
	 * @param sheetNum the sheetNum to set
	 */
	public void setSheetNum(int sheetNum) {
		this.sheetNum = sheetNum;
	}

}
