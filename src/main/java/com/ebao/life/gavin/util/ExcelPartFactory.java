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
 *��������:
 *topLine ��һ��
 *title ����
 *value ���ֶ�
 *width ���
 *list ����Դ
 *
 *
 *
 *���sheet����
 */
public class ExcelPartFactory {
	private static final int SHEETNUM = 60000;// �嵥��¼����60000��ʱ����sheet��ʾ

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
			//��ӵ�һ��
			Label lColumn = new Label(0,0,firstLine[0],sCenterCaption);
			ws.mergeCells(0, 0, titles.length-1, 0);
			ws.addCell(lColumn);
			
			// �������
			Label lblColumn = null;
			int nRow = 1;// �б�
			int ct = 0; // �б�
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
				ct = 0; // ÿ�ζ�д���ݣ����Ὣ�б�����.
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

