package com.ebao.life.gavin.util;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

public class CsvWriter {

	/** CSV�ļ��зָ��� */
	private static final String CSV_COLUMN_SEPARATOR = ", ";

	/** CSV�ļ��зָ��� */
	private static final String CSV_RN = "\r\n";
	
	private static final String CSV_ENCODING = "GB2312";

	/**
	 * 
	 * ��������������Ķ�Ӧ��csv����
	 * */
	public static String formatCsvData(List<Map<String, Object>> data,
			String displayColNames, String matchColNames) {

		StringBuffer buf = new StringBuffer();

		String[] displayColNamesArr = null;
		String[] matchColNamesMapArr = null;

		displayColNamesArr = displayColNames.split(",");
		matchColNamesMapArr = matchColNames.split(",");

		// �����ͷ
		for (int i = 0; i < displayColNamesArr.length; i++) {
			String colValue = displayColNamesArr[i] = "\""+displayColNamesArr[i]+"\"";
			buf.append(colValue).append(CSV_COLUMN_SEPARATOR);
		}
		if(CSV_COLUMN_SEPARATOR.equals(buf.substring(buf.length()-CSV_COLUMN_SEPARATOR.length()))){
			buf = new StringBuffer(buf.substring(0, buf.length() - CSV_COLUMN_SEPARATOR.length()));
		}
		buf.append(CSV_RN);

		if (null != data) {
			// �������
			for (int i = 0; i < data.size(); i++) {

				for (int j = 0; j < matchColNamesMapArr.length; j++) {
					Object colValue = data.get(i).get(matchColNamesMapArr[j]);
					colValue = "\""+colValue+"\"";
					buf.append(colValue).append(
							CSV_COLUMN_SEPARATOR);
				}
				if(CSV_COLUMN_SEPARATOR.equals(buf.substring(buf.length()-CSV_COLUMN_SEPARATOR.length()))){
					buf = new StringBuffer(buf.substring(0, buf.length() - CSV_COLUMN_SEPARATOR.length()));
				}
				buf.append(CSV_RN);
			}
		}
		return buf.toString();
	}
 	public static void exportCsv(String fileName, String content,
			HttpServletResponse response) throws IOException {

		// �����ļ���׺
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhh24mmss");
		String fn = fileName;

		// ��ȡ�ַ�����
		String csvEncoding = CSV_ENCODING;
//		response.setContentType("aplication/vnd.ms-excel;charset=UTF-8");
//		response.addHeader("Content-Disposition", "inline; filename="+ fileName);
		// ������Ӧ
		response.setCharacterEncoding(csvEncoding);
		response.setContentType("text/csv; charset=" + csvEncoding);
		response.setHeader("Pragma", "public");
		response.setHeader("Cache-Control", "max-age=30");
		response.setHeader("Content-Disposition", "attachment; filename="
				+ new String(fn.getBytes(), csvEncoding));

		// д����Ӧ
		OutputStream os = response.getOutputStream();
		os.write(content.getBytes("GBK"));
		os.flush();
		os.close();
	}
 	
 	public static void main(String[] args) {
		String buf = "2,3,4,";
		buf = buf.substring(buf.length()-1);
		System.out.println(buf);
	}

}