package com.ebao.life.gavin.util;

import java.util.List;
import java.util.Map;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import com.ebao.life.gavin.vo.SheetVo;

/**
 * 
 * @author chris.zhang
 *
 *
 *
 *输入数据:
 *topLine 第一行
 *title 列名
 *value 列字段
 *width 宽度
 *list 数据源
 *
 *
 *
 *输出sheet对象
 */
public class ExcelPartFactory {
	private static final int SHEETNUM = 60000;// 清单记录超过60000条时，分sheet显示

	public static WritableWorkbook addSheet(SheetVo vo) throws Exception, WriteException{
	 WritableWorkbook wb = vo.getWb();
	 String[] firstLine = vo.getFirstLine();
	 String[] titles = vo.getTitles();
	 String[] columns = vo.getColumns();
	 int[] widths = vo.getWidths();
	 List list = vo.getList();
	 
	 
	 //start
		// Font: Arial, Bold, 10size
		WritableFont fmtCaption = new WritableFont(WritableFont.ARIAL);
		fmtCaption.setBoldStyle(WritableFont.BOLD);
		fmtCaption.setPointSize(WritableFont.DEFAULT_POINT_SIZE);
	    // CellFormat: for each Table Column Caption
		WritableCellFormat fmtCenterCaption = new WritableCellFormat(
				fmtCaption);
		fmtCenterCaption.setAlignment(Alignment.CENTRE);
		fmtCenterCaption.setBackground(Colour.GRAY_25);
		fmtCenterCaption.setBorder(Border.ALL, BorderLineStyle.THIN);
		WritableCellFormat sCenterCaption = new WritableCellFormat(
				fmtCaption);
		sCenterCaption.setAlignment(Alignment.CENTRE);
		sCenterCaption.setBackground(Colour.ICE_BLUE);
		sCenterCaption.setBorder(Border.ALL, BorderLineStyle.THIN);

		// CellFormat: for Table Data
		WritableCellFormat fmtCenterData = new WritableCellFormat();
		fmtCenterData.setAlignment(Alignment.CENTRE);
		fmtCenterData.setBorder(Border.ALL, BorderLineStyle.THIN);
		int listSize = list.size();
		int sheetCount = (listSize % SHEETNUM == 0 ? listSize / SHEETNUM
				: listSize / SHEETNUM + 1);
		for (int i = 1; i <= sheetCount; i++) {
			WritableSheet ws = wb.createSheet(vo.getSheetName(), vo.getSheetNum());
			for(int j=0;j<widths.length;j++){
				ws.setColumnView(j, widths[j]);
			}
			//添加第一行
			Label lColumn = new Label(0,0,firstLine[0],sCenterCaption);
			ws.mergeCells(0, 0, titles.length-1, 0);
			ws.addCell(lColumn);
			
			// 添加列名
			Label lblColumn = null;
			int nRow = 1;// 行标
			int ct = 0; // 列标
			for (int j = 0; j < titles.length; j++) {
				lblColumn = new Label(ct++, nRow, titles[j],
						fmtCenterCaption);
				ws.addCell(lblColumn);
			}
			nRow++;
			int jMin = (i - 1) * SHEETNUM;
			int jMax = (i * SHEETNUM < listSize) ? i * SHEETNUM : listSize;
			for (int j = jMin; j < jMax; j++) {
				Map<String, String> map= (Map<String, String>) list.get(j);
				ct = 0; // 每次读写数据，都会将列标重置.
				for (int k = 0; k < columns.length; k++) {
					ws.addCell(new Label(ct++, nRow, map
							.get(columns[k]) == null ? "" : map
							.get(columns[k]), fmtCenterData));
				}
				nRow++;
			}
		}
	 //end
	return wb;
		
	}
}

