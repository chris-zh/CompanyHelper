package com.ebao.life.bean.pub.common;

/**
 * Title:        基于Web的寿险作业系统
 * Description:  Webbased Life Insurance System
 * Java source file
 * Copyright:    Copyright (c) 2001
 * Company:      ebao
 * @author
 * @version
 * $Log: Tools.java,v $
 * Revision 1.103  2007/12/06 09:00:35  weblogic
 * *** empty log message ***
 *
 * Revision 1.97  2007/12/06 02:56:35  haoxu
 * *** empty log message ***
 *
 * Revision 1.96  2007/10/10 06:38:44  janewang
 * <No Comment Entered>
 *
 * Revision 1.95  2007/10/09 09:03:22  janewang
 * <No Comment Entered>
 *
 * Revision 1.94  2005/01/28 08:21:07  weblogic
 * *** empty log message ***
 *
 * Revision 1.94  2005/01/28 07:42:59  jerry.min
 * <No Comment Entered>
 *
 * Revision 1.93  2004/09/21 03:06:31  flora.tang
 * <No Comment Entered>
 *
 * Revision 1.91  2004/06/25 03:53:18  tairs.zhang
 * 修改writeBlob()方法，改为从sequence中取blob_id的值。
 *
 * Revision 1.90  2004/03/24 03:53:21  chris.yang
 * modify getNewLinedText:delete "\r" before "\n" when read the string from page
 *
 * Revision 1.89  2004/03/22 09:20:48  chris.yang
 * fix null and index out of range error.
 *
 * Revision 1.88  2004/03/22 07:00:06  chris.yang
 * change getNewLinedText the process body and fix "double the last char when extend the maxlength"
 *
 * Revision 1.87  2004/02/16 10:48:43  meyer.lu
 * add trace log in getNewLinedText()
 *
 * Revision 1.86  2004/02/16 07:01:38  meyer.lu
 * getNewLinedText()
 * fix the bug of lost the last char
 *
 * Revision 1.85  2004/02/12 15:08:29  meyer.lu
 * modify getNewLinedText()
 * add indent
 *
 * Revision 1.84  2004/02/12 14:29:24  meyer.lu
 * modify getNewLinedText()
 *
 * Revision 1.83  2004/02/12 14:17:35  meyer.lu
 * add method:getNewLinedText()
 *
 * Revision 1.82  2003/11/14 09:15:48  eric.zhang
 * 返回字符窜的字节长度
 *
 * Revision 1.81  2003/11/14 09:13:57  eric.zhang
 * 返回字符窜的字节长度
 *
 * Revision 1.80  2003/09/19 10:14:15  xiaoyuan.he
 * 修改getMonthAmount和getDayAmount方法
 *
 * Revision 1.79  2003/09/01 08:50:18  yongguo.fan
 * fixed bug to processStrings()
 *
 * Revision 1.78  2003/07/16 13:55:28  yongguo.fan
 * Modify Date2String(date)
 * // if(date==null) return null
 *
 * Revision 1.77  2003/05/30 04:23:08  kevin.qiu
 * 新增解压缩输入数据流
 *
 * Revision 1.76  2003/05/30 04:15:03  kevin.qiu
 * 新增使用InputStream写CLOB字段，注意使用了Weblogic的CLOB实现
 *
 * Revision 1.75  2003/05/28 11:43:15  yongguo.fan
 * <No Comment Entered>
 *
 * Revision 1.74  2003/05/28 11:39:55  yongguo.fan
 * <No Comment Entered>
 *
 * Revision 1.73  2003/05/28 11:22:04  yongguo.fan
 * 将ZIP格式文件解压到指定文件
 *
 * Revision 1.72  2003/04/09 03:20:07  xiaoyuan.he
 * 修改processStrings方法
 *
 * Revision 1.71  2003/04/03 13:33:46  tairs.zhang
 * 优化beforFilling()和afterFilling()方法，使之可以处理为null的源串
 *
 * Revision 1.70  2003/04/03 10:46:28  tairs.zhang
 * <No Comment Entered>
 *
 * Revision 1.69  2003/03/31 10:42:10  yongguo.fan
 * 新增: beforFilling(),afterFilling()
 *
 * Revision 1.68  2003/02/19 03:53:31  jason.shi
 * 恢复了fromGB方法
 *
 * Revision 1.67  2003/02/19 03:50:35  jason.shi
 * <No Comment Entered>
 *
 * Revision 1.66  2003/02/18 06:22:36  jason.shi
 * 取消toGB和fromGB方法中的转码工作，改由通过修改web应用配置文件完成
 *
 * Revision 1.65  2003/02/14 04:33:01  jason.shi
 * 新增方法filterOracleSqlString
 *
 * Revision 1.64  2003/01/27 08:33:31  diego.ding
 * <No Comment Entered>
 *
 * Revision 1.63  2003/01/09 04:08:02  yongguo.fan
 * processStrings()中加入对'"','&'的处理
 *
 * Revision 1.62  2003/01/03 06:25:39  charles.chang
 * <No Comment Entered>
 *
 * Revision 1.61  2002/12/23 09:01:59  jack.huang
 * <No Comment Entered>
 *
 * Revision 1.60  2002/12/18 01:51:46  jack.huang
 * Add msNull(Object obj) Method
 *
 * Revision 1.59  2002/11/26 07:44:01  charles.chang
 * <No Comment Entered>
 *
 * Revision 1.58  2002/11/21 07:45:14  kevin.qiu
 * 新增 getDBTime() : 取当前数据库的系统时间
 *
 * Revision 1.57  2002/11/20 09:49:21  diego.ding
 * <No Comment Entered>
 *
 * Revision 1.56  2002/11/01 03:37:34  yun.xia
 * modify clear mothod from private to public
 *
 * Revision 1.55  2002/10/31 10:42:43  jason.shi
 * 增加unicodeToGBK
 *
 * Revision 1.54  2002/10/17 06:37:49  charles.chang
 * <No Comment Entered>
 *
 * Revision 1.53  2002/10/17 02:39:11  charles.chang
 * dateTOstring(long)
 *
 * Revision 1.52  2002/10/15 03:37:32  jason.shi
 * 将getFill15方法移到Sequence.java中
 *
 * Revision 1.51  2002/10/15 03:25:43  jason.shi
 * 对错误处理做了一些修改
 *
 * Revision 1.50  2002/09/28 12:45:24  jason.shi
 * 增加了GBK到UTF8转换
 *
 * Revision 1.49  2002/09/02 07:22:07  jason.shi
 * 合并冲突
 *
 * Revision 1.48  2002/08/30 08:31:23  wei.ma
 * 对号码加校验位后生成最终15位号码
 *
 * Revision 1.47  2002/08/30 02:25:09  wei.ma
 * 把格式进行调整，用TAB符
 *
 * Revision 1.46  2002/08/30 02:23:47  wei.ma
 * <No Comment Entered>
 *
 * Revision 1.43.4.1  2002/08/26 09:08:21  wencai.yan
 * no message
 *
 * Revision 1.45  2002/08/22 05:55:52  jiankang.song
 * <No Comment Entered>
 *
 * Revision 1.44  2002/08/20 03:30:00  jason.shi
 * 将GBK调整为UTF8,同时合并了太平的一个方法
 *
 */

//import oracle.sql.*;
import java.io.*;
import java.math.*;
import java.sql.*;
import java.text.*;
import java.util.*;
import java.util.Date;
import java.util.zip.*;

import com.ebao.life.gavin.util.DBManager;
import com.ebao.life.gavin.util.EnvEnum;
import com.ebao.life.gavin.util.PooledConnection;

public class Tools {

  //日期格式
  private static SimpleDateFormat dateFormat1 = new SimpleDateFormat(
      "yyyy-MM-dd");
  private static SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyyMMdd");

  private static final int nMaxBufferSize = 15 * 1024;
  private static final int MAX_BUFFER_SIZE = 2 * 1024;

  //18位身份证号码检查用
  public static String getId18CheckCode(String sPre17ID) throws Exception {
    int[] nCertiCheckW = {
        7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1};
    int nCount = 0;
    int nIdNum = 0;
    if ( (sPre17ID == null) || (sPre17ID.length() != 17)) {
      throw new Exception("Invalid certi code length");
    }
    for (int i = 0; i < 17; i++) {
      char c = sPre17ID.charAt(i);
      if ( (c <= '9') || (c >= '0')) {
        nIdNum = c - '0';
      }
      else {
        throw new Exception("Invalid Certi Code char");
      }
      nCount += nIdNum * nCertiCheckW[i];
    }
    nCount = nCount % 11;
    switch (nCount) {
      case 0:
        return "1";
      case 1:
        return "0";
      case 2:
        return "X";
      case 3:
        return "9";
      case 4:
        return "8";
      case 5:
        return "7";
      case 6:
        return "6";
      case 7:
        return "5";
      case 8:
        return "4";
      case 9:
        return "3";
      case 10:
        return "2";
      default:
        return "";
    }
  }

  public static void writeClob(char[] buf, int nDataLength, String sTableName,
          String stField_name,
          String stClause) throws Exception {
	writeClobUseProcedure(buf, nDataLength, sTableName, stField_name, stClause);
	}
  
  /**
   * 调用存储过程写CLOB数据
   * @param buf
   * @param nDataLength
   * @param sTableName
   * @param stField_name
   * @param stClause
   */
  public static void writeClobUseProcedure(char[] buf, int nDataLength,
                                           String sTableName,
                                           String stField_name, String stClause) throws
      Exception {
    String sql = null;
//String sql1="SELECT photo from t_agent where agent_id=261 for update";
    String sql1 = "SELECT " + stField_name + "  FROM " + sTableName + " WHERE " +
        stClause
        + " FOR UPDATE";
    Connection conn = null;
    CallableStatement stmt = null;
	PooledConnection poolconn = null;
    try {
      poolconn = DBManager.getConnection(EnvEnum.tpdev);
      conn = poolconn.getConnection();

      sql = "update " + sTableName + " set " + stField_name +
          "=EMPTY_CLOB() WHERE " + stClause;
      Statement stmt1 = conn.createStatement();
      stmt1.execute(sql);
      stmt1.close();
      int iTimes = (int) (nDataLength / (MAX_BUFFER_SIZE));
      int iRead;


      char[] buf2 = new char[MAX_BUFFER_SIZE];
      sql = ("begin p_write_clob(?,?,?); end;");
      stmt = conn.prepareCall(sql);
      stmt.setString(1, sql1);
      for (int i = 0; i < iTimes; i++) {
        //iRead=fin.read(buf2,i*31*1024,31*1024);
        //out.println(String.valueOf(iRead));
        for (int j = 0; j < MAX_BUFFER_SIZE; j++) {
          buf2[j] = buf[i * MAX_BUFFER_SIZE + j];
        }

        stmt.setInt(2, MAX_BUFFER_SIZE);
        stmt.setString(3, new String(buf2));
        stmt.executeUpdate();
      }
      int iRemain = nDataLength - (int) iTimes * MAX_BUFFER_SIZE;
      if (iRemain>0) {
	      char[] buf3 = new char[iRemain];
	      for (int j = 0; j < iRemain; j++) {
	        buf3[j] = buf[iTimes * MAX_BUFFER_SIZE + j];
	      }
	      stmt.setInt(2, iRemain);
	      stmt.setString(3, new String(buf3));
	      stmt.executeUpdate();
      }
      stmt.close();
    }
    catch (Exception e) {
      //e.printStackTrace();
      throw e;
    }
    finally {
      poolconn.close(); // 必须关闭，否则连接池会爆
      //db.close();
    }
  } //end of method writeClob()
  
      /*******************************************************************************
   *Name:toGB
   *
   *Function:Covert String to Unicode Code,and convert null to empty string
   *
   *Author:Jason Shi
   *
   *Create Date:2000/6/6
   *
   *Parameter:
   *        none
   *
   *Relate table:none
   *
   *Return value:
   *        return Unicode Code String  if success
   *        return "" if fail or inStr is null
       ******************************************************************************/
  public static String toGB(String inStr) {
    try {

      if (inStr == null)
        return "";
//Convert String
//        return inStr;
//        return (new String(inStr.getBytes ("8859_1"),"gb2312"));
      //if("Linux".equals( System.getProperty("os.name"))){
//      if(true){
//        return (new String(inStr.getBytes ("ISO8859_1"),"UTF8"));
//      }
//      else{
      return inStr;
//      }
    }
    catch (Exception e) {
      return "";
    }
  }

  public static String fromGB(String inStr) {
    try {

      if (inStr == null)
        return "";
//Convert String
// if OS is NT then return inStr
//        return inStr;
//      return inStr;
      return (new String(inStr.getBytes("UTF8"), "8859_1"));
    }
    catch (Exception e) {
      return "";
    }
  }

  public static String fromGB(byte[] inBytes) {
    try {

      if (inBytes == null)
        return "";
//Convert String
// if OS is NT then return inStr

//if OS is Linux then return below
      return (new String(inBytes, "8859_1"));

    }
    catch (Exception e) {
      return "";
    }
  }

  public static long toLong(String inStr) {
    try {
      if (inStr == null) {
        return 0;
      }
      else {
        return Long.valueOf(inStr).longValue();
      }
    }
    catch (Exception e) {
      return 0;
    }
  }

  public static int toInteger(String inStr) {
    try {
      if (inStr == null) {
        return 0;
      }
      else {
//return Integer.valueOf(valueOf(inStr)).intValue();
        return new Integer(inStr).intValue();
      }
    }
    catch (Exception e) {
      return 0;
    }
  }

  public static double toDouble(String inStr) {
    try {
      if (inStr == null) {
        return 0;
      }
      else {
        return Double.valueOf(inStr).doubleValue();
      }
    }
    catch (Exception e) {
      return 0;
    }
  }

  public static float toFloat(String inStr) {
    try {
      if (inStr == null) {
        return 0;
      }
      else {
        return Float.valueOf(inStr).floatValue();
      }
    }
    catch (Exception e) {
      return 0;
    }
  }

  public static String msNull(String inStr) {
    if (inStr == null) {
      return "";
    }
    else {
      return inStr;
    }
  }

  public static Object msNull(Object inStr) {
    if (inStr == null) {
      return "";
    }
    else {
      return inStr;
    }
  }


//根据输入的String返回BigDecimal，或者若String非数字串，返回null
  public static BigDecimal getBigDecimal(String str) {
    BigDecimal bd = null;
    if (str == null)
      return null;
    try {
      bd = new BigDecimal(str);
    }
    catch (Exception e) {
      return null;
    }
    return bd;
  }

//根据年，月，日，转化为Timestamp类型
  public static Timestamp getTimestamp(String sDate) {
    Timestamp ts = null;
    if (sDate == null || "".equals(sDate))
      return null;
    /*xi.zhu_begin_20111108-太平中间件升级项目:兼容sDate='2011-11-7'这种字符串类型，正确的应是'2011-11-07'*/
    if(sDate.length() < 10){ 
    	int iDate[] = {4,2,2};
    	String rDate = "";
    	String[] tDate = sDate.split("-");
    	for(int i=0; i<tDate.length; i++){
    		while(tDate[i].length()<iDate[i]){
    			tDate[i] = "0" + tDate[i];
    		}
    		if(i==0){
    			rDate += tDate[i];
    		}else{
    			rDate = rDate + "-" + tDate[i];
    		}
    	}
    	ts = Timestamp.valueOf(rDate + " 00:00:00.000000000");
    }else{
    	ts = Timestamp.valueOf(sDate + " 00:00:00.000000000");
    }
    /*xi.zhu_end_20111108-太平中间件升级项目:兼容sDate='2011-11-7'这种字符串类型，正确的应是'2011-11-07'*/
    
    return ts;
  }

  public static Timestamp getEndTimestamp(String sDate) {
    Timestamp ts = null;
    if (sDate == null || "".equals(sDate))
      return null;
    ts = Timestamp.valueOf(sDate + " 23:59:59.999999999");
    return ts;
  }

// following date involved static mothods by rock yang, 2001-7
// NOTE: year and month have literal meaning. e.g. 2000-7-1, year = 2001, month = 7, date = 1
  public static String Date2String(java.util.Date date) {
    if (date == null)
      return null;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    return simpleDateFormat.format(date);
  }

  public static java.util.Date toDate(String sDate) throws java.text.
      ParseException {
    java.util.Date result = null;
    if (sDate == null)
      result = null;
    else if (sDate.length() == 10 && sDate.indexOf("-") == 4) {
      result = dateFormat1.parse(sDate);
    }
    else if (sDate.length() == 8) {
      result = dateFormat2.parse(sDate);
    }
    return result;
  }

//在URL后面添加一个标记，保证用户提交的URL是由服务器生成的
//例如 在客户端显示一个车险定损的图片,以 showimage?img_id=123
//方式定义URL,用户可能修改img_id来看到不该看到的图片
//因此对该URL做标记，在showimage程序中检查标记是否与URL符合
//相关的方法有
//    encryptURL
//    checkEncryptURL

  public static Timestamp addDate(Timestamp oldDate, int addDays) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(new Date(oldDate.getTime()));
    calendar.add(calendar.DATE, addDays);
    return new Timestamp(calendar.getTime().getTime());
  }

  public static Timestamp addMonth(Timestamp oldDate, int addMonths) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(new Date(oldDate.getTime()));
    calendar.add(calendar.MONTH, addMonths);
    return new Timestamp(calendar.getTime().getTime());
  }

  public static Timestamp addYear(Timestamp oldDate, int addYears) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(new Date(oldDate.getTime()));
    calendar.add(calendar.YEAR, addYears);
    return new Timestamp(calendar.getTime().getTime());
  }

  public static java.util.Date toDate(int year, int month, int date) {
    return toDate(year, month, date, 0, 0, 0);
  }

  public static java.util.Date toDate(int year, int month, int date, int hrs,
                                      int min, int sec) {
    Calendar calendar = Calendar.getInstance();
    calendar.set(year, month - 1, date, hrs, min, sec);
    return calendar.getTime();
  }

  public static int getYear(java.util.Date date) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    return calendar.get(Calendar.YEAR);
  }

  public static int getYear(long date) {
    return getYear(new java.util.Date(date));
  }

  public static int getMonth(java.util.Date date) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    return calendar.get(Calendar.MONTH) + 1;
  }

  public static int getMonth(long date) {
    return getMonth(new java.util.Date(date));
  }

  public static int getDate(java.util.Date date) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    return calendar.get(Calendar.DAY_OF_MONTH);
  }

  public static int getDate(long date) {
    return getDate(new java.util.Date(date));
  }

  public static int getHours(java.util.Date date) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    return calendar.get(Calendar.HOUR);
  }

  public static int getMinutes(java.util.Date date) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    return calendar.get(Calendar.MINUTE);
  }

  public static int getSeconds(java.util.Date date) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    return calendar.get(Calendar.SECOND);
  }

  public static String getChineseDate(java.util.Date d) {
    if (d == null) {
      return " 年 月 日";
    }
    else {
      return "" + getYear(d) + "年" + getMonth(d) + "月" + getDate(d) + "日";
    }
  }

  public static String toString(Object obj) {
    if (obj == null) {
      return "";
    }
    else if (obj instanceof Date) {
      return Date2String( (Date) obj);
    }
    else {
      return obj.toString();
    }
  }

  /*----------------------------------------------------------------------
   Function name:	convertBirthDateToAge(Dabe birthDate,Date nowDate)
   Description: covert birthdate to age
   Input:		birthday, the current date you want to compare
   Output:     age
   Author:		Richard Zhang, Rodolf Li
   Date:		Aug 15,2001
   -----------------------------------------------------------------------*/
  public static int convertBirthDateToAge(Date birthDate, Date nowDate) {
    int nAge = 0;
    nAge = nowDate.getYear() - birthDate.getYear();
    if (nowDate.getMonth() * 100 + nowDate.getDate()
        < birthDate.getMonth() * 100 + birthDate.getDate()) {
      nAge--;
    }
    return nAge;
  }

  /*----------------------------------------------------------------------
   Function name:	getDouble()
   Description: covert double to double
   Input:	    ORIGIN double, count, bDischarge
   Output:     final double
   Author:		Richard Zhang
   Date:		Aug 15,2001
   -----------------------------------------------------------------------*/
  public static double getDoubleDischargeTail(double dOrigin, int nCount) {
    return getDouble(dOrigin, nCount, true);
  }

  public static double getDoubleNotDischargeTail(double dOrigin, int nCount) {
    return getDouble(dOrigin, nCount, false);
  }

  public static double getDouble(double dOrigin, int nCount, boolean bDischarge) {
    long lTemp = (long) Math.pow(10, nCount);
    if (bDischarge == true)
      return (long) (dOrigin * lTemp) / (double) lTemp;
    else
      return Math.round(dOrigin * lTemp) / (double) lTemp;
  }
  
  public static BigDecimal getDoubleDischargeTail(BigDecimal dOrigin, int nCount) {
	    return getDouble(dOrigin, nCount, true);
  }

  public static BigDecimal getDoubleNotDischargeTail(BigDecimal dOrigin, int nCount) {
    return getDouble(dOrigin, nCount, false);
  }

  public static BigDecimal getDouble(BigDecimal dOrigin, int nCount, boolean bDischarge) {
	BigDecimal lTemp = BigDecimal.valueOf(Math.pow(10, nCount));
	BigDecimal ret = null;
    if (bDischarge == true) {
      ret = BigDecimal.valueOf((long) (dOrigin.multiply(lTemp).doubleValue())).divide(lTemp).setScale(2, BigDecimal.ROUND_HALF_UP);
      if(ret.doubleValue()==0) {
    	  ret = new BigDecimal("0");
      }
      return ret;
    } else {
	  ret = BigDecimal.valueOf(Math.round(dOrigin.multiply(lTemp).doubleValue())).divide(lTemp).setScale(2, BigDecimal.ROUND_HALF_UP);
      if(ret.doubleValue()==0) {
   	    ret = new BigDecimal("0");
      }
      return ret;
    }
  }

  /*----------------------------------------------------------------------
   Function name:	getMonthAmount()
   Description: get two date's between month
   Input:	    Date startDate,Date endDate
   Output:     int month_amount
   Author:		Richard Zhang
   Date:		Sept 2,2001
   -----------------------------------------------------------------------*/
  public static int getMonthAmount(Date startDate, Date endDate) {
    int nYear = 0;
    int nMonth = 0;
    int nDay = 0;
    int nMonthAmount = 0;
    Calendar cldStart = Calendar.getInstance();
    Calendar cldEnd = Calendar.getInstance();

    cldStart.setTime(startDate);
    cldEnd.setTime(endDate);

    nYear = cldEnd.get(cldEnd.YEAR) - cldStart.get(cldStart.YEAR);
    nMonth = cldEnd.get(cldEnd.MONTH) - cldStart.get(cldStart.MONTH);
    nDay = cldEnd.get(cldEnd.DATE) - cldStart.get(cldStart.DATE);

    if (nDay < 0)
      nMonthAmount = nYear * 12 + nMonth - 1;
    else
      nMonthAmount = nYear * 12 + nMonth;
    return nMonthAmount;
  }

  public static int getDayAmount(Date startDate, Date endDate) {
    int dayAmount = 0;
//    Calendar cldStart = Calendar.getInstance();
//    Calendar cldEnd   = Calendar.getInstance();
//
//    cldStart.setTime(startDate);
//    cldEnd.setTime(endDate);
//
//    int nStart = cldStart.get(cldStart.DAY_OF_YEAR);
//    int nEnd = cldEnd.get(cldEnd.DAY_OF_YEAR);
//    if (nEnd - nStart > 0)
//      dayAmount = nEnd - nStart;
//    else
//      dayAmount = 365 - (nEnd - nStart);

    return (int) ( (endDate.getTime() - startDate.getTime()) /
                  (1000 * 60 * 60 * 24));
  }

  /*----------------------------------------------------------------------
   Function name:	doubleToStr()
   Description: 解决科学计数法问题
   Input:	    double
   Output:     该double对应的字符串
   Author:		ernest
   Date:		Sept 2,2001
   ----------------------------------------------------------------------*/
  public static String doubleToStr(double d) {
    String str = NumberFormat.getInstance().format(d);
    String str2 = "";
    int i = 0;
    while ( (i >= 0) && str.length() > 0) {
      i = str.indexOf(",");
      if (i == -1) {
        str2 += str;
        break;
      }
      else {
        str2 += str.substring(0, i);
        str = str.substring(i + 1, str.length());
      }
    }
    return str2;
  }

// <Add by=xiayun>
  /**
   * 将空字符串转化为null
   */
  static public String emptyStringToNull(String value) {
    if (value == null)
      return null;
    value = value.trim();
    if (value.length() == 0)
      return null;
    return value;
  }

  /**
   * 将日期格式转换为yyyy-mm-dd格式的String
   * @parameter division分割符
   */
  static public String dateToString(Date date, String division) {
    if (date == null)
      return null;
    if (division == null)
      division = "";
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    int year = cal.get(Calendar.YEAR);
    int month = cal.get(Calendar.MONTH) + 1;
    int day = cal.get(Calendar.DAY_OF_MONTH);
    String result = "" + year;
    result += division;
    if (month < 10)
      result += "0";
    result += month;
    result += division;
    if (day < 10)
      result += "0";
    result += day;
    return result;
  }

  /**
   * 将日期格式转换为yyyy-mm-dd格式的String
   * @parameter division分割符
   */
  static public String dateToString(Timestamp date, String division) {
    if (date == null)
      return null;
    return dateToString(new Date(date.getTime()), division);
  }


// </Add>
  /**
   * collection 对象格式转换为XML文本格式
   * @param list
   * @return
   */
  public static String generateXML(Collection list) {
    StringBuffer xml = new StringBuffer();
    Iterator iter = null;
    if (list != null) {
      iter = list.iterator();
      while (iter.hasNext()) {
        HashMap row = (HashMap) iter.next();
        if (row != null) {
          Set set = row.keySet();
          Iterator cols = set.iterator();
          xml.append("<row>");
          while (cols.hasNext()) {
            String colName = (String) cols.next();
            String colValue = (String) row.get(colName);
            xml.append("<" + colName + ">" + colValue + "</" + colName + ">");
          } // while 2
          xml.append("</row>\n");
        } // if 2
      } // while 1
    } // if 1
    return xml.toString();
  }

  /**
   * 生成javascript的Array
   * @param sValue
   */
  static public String generateArray(String[][] sValue) {
    if (sValue == null) {
      sValue = new String[0][0];
    }

    StringBuffer buff = new StringBuffer(4096);
    buff.append("new Array(");
    String[] ss;
    String s;
    for (int i = 0; i < sValue.length; i++) {
      ss = sValue[i];
      if (ss == null) {
        ss = new String[0];
      }
      if (i > 0) {
        buff.append(",");
      }
      buff.append("new Array(");
      for (int j = 0; j < ss.length; j++) {
        if (j > 0) {
          buff.append(",");
        }
        s = ss[j];
        if (s == null) {
          s = "&nbsp;";
        }
        else if ("".equals(s.trim())) {
          s = "&nbsp;";
        }
        buff.append("\"" + convertString(s) + "\"");
      }
      buff.append(")");
    }
    buff.append(")");
    return buff.toString();
  }

  private static String convertString(String sSource) {
    if (sSource == null) {
      return null;
    }
    StringBuffer buff = new StringBuffer(1024);
    int n = sSource.length();
    char c;
    for (int i = 0; i < n; i++) {
      c = sSource.charAt(i);
      if (c == '"') {
        buff.append('\\');
        buff.append(c);
      }
      else if (c == '\\') {
        buff.append('\\');
        buff.append(c);
      }
      else if (c == '\r') {
        buff.append("\\r");
      }
      else if (c == '\n') {
        buff.append("\\n");
      }
      else {
        buff.append(c);
      }
    }
    return buff.toString();
  }

  /**
   * 判断一个数是否是0
   * @param value 要判断的数
   * @param digits 判断到小数后的位数
   * @return 是否是0
   */
  public static boolean isZero(double value, int digits) {
    return getDouble(value, digits, false) == 0;
  }

  /**
   * 获取obj的String表达,当obj为null时，返回defaultValue
   * @param obj
   * @param defaultValue
   * @return
   */
  public static String getObjectString(Object obj, String defaultValue) {
    if (obj == null) {
      return defaultValue;
    }
    else {
      return obj.toString();
    }
  }

      /*******************************************************************************
   *Name:processStrings
   *
   *Function:to replace all string \r\n to <br>, space to &nbsp, < to &lt, and
   * > to &gt for html displying
   *
   *Author:Minghua wang
   *
   *Create Date:July 5,2000
   *
   *Parameter:
   *    String str
   *
   *Return value:
   *        return replaced string
       ******************************************************************************/
  public static String processStrings(String str) {
    if (str != null) {
      str = replaceString("&", "&amp;", str);
      str = replaceString(" ", "&nbsp;", str);
      str = replaceString("<", "&lt;", str);
      str = replaceString(">", "&gt;", str);
      str = replaceString("\r\n", "<br>", str);
      str = replaceString("\"", "&quot;", str);

      return (str);
    }
    else
      return (str);
  } //end of method processStrings()

  /**
   * 将日期格式转换为yyyy-mm-dd格式的String
   * @parameter division分割符
   */

// this method to replace space to &nbsp  and newline carriage return to <br> ;
      /*******************************************************************************
   *Name:replaceString
   *
   *Function:replace the olsStr to newStr in a string wholeStr
   *
   *Author:Minghua Wang
   *
   *Create Date:July 5, 2000
   *
   *Parameter:
   *        String oldStr , String newStr, String wholeStr
   *
   *Return value:
   *        return the replaced string
       ******************************************************************************/
  public static String replaceString(String oldStr, String newStr,
                                     String wholeStr) {
    if (wholeStr == null)
      return "";

    if (oldStr == null)
      return wholeStr;
    if (newStr == null)
      return wholeStr;
// canceled by rodolf
    /*
//change by jason shi for avoid dead loop
     if(newStr.indexOf(oldStr)>=0){
     return wholeStr;
     }
     */
    int start, end;
    StringBuffer result = new StringBuffer();
    result = result.append(wholeStr);
// updated by rodolf
    start = 0;

    while (wholeStr.indexOf(oldStr, start) > -1) {
      start = wholeStr.indexOf(oldStr, start);
      end = start + oldStr.length();
      result.replace(start, end, newStr);
      wholeStr = result.toString();
      start += newStr.length();
    }
    return wholeStr;
  }

  /**
   * 四舍五入取digits位小数
   * @param value
   * @param digits 小数位数
   * @return String
   */
  public static String roundString(double value, int digits) {
    String format = "#";
    if (digits > 0) {
      format += ".";
    }
    for (int i = 0; i < digits; i++) {
      format += "#";
    }
    DecimalFormat numberFormatter = new DecimalFormat(format);
    return numberFormatter.format(value);
  }





  public static String getChineseDate(long d) {
    return getChineseDate(new java.util.Date(d));
  }

  /**
   * 将GBK编码的字符串转化为Unicode的字符串
   * @param pGbkString
   * @return
   */
  public static String gbkToUnicode(String pGbkString) {
    String sResult = null;
    try {
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      OutputStreamWriter os = new OutputStreamWriter(baos, "UTF-8");
      os.write(pGbkString);
      os.close();
      sResult = baos.toString("UTF-8");
    }
    catch (Exception e) {
      //e.printStackTrace();
    }
    return sResult;
  }

  /**
   * 将Unicode编码的字符串转化为GBK的字符串
   * @param pGbkString
   * @return
   */
  public static String unicodekToGBK(String pUnicodeString) {
    String sResult = null;
    try {
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      OutputStreamWriter os = new OutputStreamWriter(baos, "GBK");
      os.write(pUnicodeString);
      os.close();
      sResult = baos.toString("GBK");
    }
    catch (Exception e) {
      //e.printStackTrace();
    }
    return sResult;
  }

  /**
   * 将GBK编码的字符串转化为Unicode的字符串
   * @param pGbkString
   * @return
   */
  public static char[] gbkToUnicode(char[] pGbkString) {
    char[] sResult = null;
    try {
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      OutputStreamWriter os = new OutputStreamWriter(baos, "UTF-8");
      os.write(pGbkString);
      os.close();
      sResult = baos.toString("UTF-8").toCharArray();
    }
    catch (Exception e) {
      //e.printStackTrace();

    }
    return sResult;
  }

  /**
   * 将double转化为String
   * @param d double数据
   * @param limit 小数位数
   * @param hasComma 生成的String是否使用逗号(,)分隔
   * @param bDischarge 是否不采用四舍五入。=true 直接去为尾
   * @return 转化后的String
   */
  public static String doubleToStr(double d, int limit, boolean hasComma,
                                   boolean bDischarge) {
    d = getDouble(d, limit, bDischarge);
    String sFormat = "#";
    if (hasComma) {
      sFormat += ",";
    }
    sFormat += "##0";
    if (limit > 0) {
      sFormat += ".";
      for (int i = 0; i < limit; i++) {
        sFormat += "0";
      }
    }
    DecimalFormat numberFormatter = new DecimalFormat(sFormat);
    return numberFormatter.format(d);
  }



  /**
   * 填充指定长度的字符至源字符串前
   * 该方法支持传入中文
   * @param sourceValue 源字串
   * @param length 格式长度
   * @param c 填充字符
   */
  public static String beforFilling(String sourceValue, int length, char c) {
    StringBuffer fill = new StringBuffer(1024);
    int currentLength = 0;
    if (sourceValue != null)
      currentLength = getByteLength(sourceValue);
    for (int i = 0; i < length - currentLength; i++)
      fill.append(c);
    return sourceValue == null ? fill.toString() :
        fill.toString() + sourceValue;
  }

  /**
   * 填充指定长度的字符至源字符串尾
   * 该方法支持传入中文
   * @param sourceValue 源字串
   * @param length 格式长度
   * @param c 填充字符
   */
  public static String afterFilling(String value, int length, char c) {
    StringBuffer fill = new StringBuffer(1024);
    int currentLength = 0;
    if (value != null)

      //返回字符窜的字节长度
      //  currentLength = value.length();
      currentLength = getByteLength(value);
    for (int i = 0; i < length - currentLength; i++)
      fill.append(c);
    return value == null ? fill.toString() : value + fill.toString();
  }

  /**
   * 返回字符窜的字节长度
   * @param s 源字串
   */
  public static int getByteLength(String s) {
    int length = 0;
    try {
      byte[] bytes = s.getBytes("US-ASCII");
      for (int i = 0; i < bytes.length; i++) {
        length += bytes[i] == 63 ? 2 : 1;
      }
    }
    catch (UnsupportedEncodingException ex) {
    }

    return length;
  }



  /**
   * 将传入字符串转为按指定lineCharSize(ASCII标准)作换行处理的字符串,
   * 支持中文，换行符'\n'
   *
   * <br>meyer.lu 2004-02-16
   *   fix the bug of lost the last char
   * <br>chris.yang 2004-03-22
   *   change getNewLinedText the process body and fix "double the last char when extend the maxlength"
   * @author meyer.lu 2004-02-12
   * @param originalText
   * @param lineCharSize 每行字符个数（ASCII标准）
   * @param indent 换行的缩进字符串
   * @return
   */
  public static String getNewLinedText(String originalText, int lineCharSize, String indent) {
    //if not ended by \n then add one \n for gen all the text(if no \n then the last line will not gen)

    boolean needAddReturnChar = false;

    if(originalText == null)
      return "";

    if(originalText!=null && originalText.length()>0 && "\n".charAt(0)!=originalText.charAt(originalText.length()-1))
      needAddReturnChar = true;


    if(needAddReturnChar)
      originalText += "\n";


    StringBuffer newLinedText = new StringBuffer(originalText.length() + 10);
      StringBuffer tempLineText = new StringBuffer();
      String currentLineText = "";

      int tempLineSize = 0;
      char ch;
      for (int i = 0; i < originalText.length(); i++) {
        //current char
        ch = originalText.charAt(i);

        //pass the "\r" when read it from page before the "\n"
        if(ch=="\r".charAt(0))
          continue;

        //old text
        currentLineText =new String(tempLineText.toString());

        //new text
        tempLineText.append(ch);

        //new text length
        tempLineSize = getByteLength(tempLineText.toString());

        if (tempLineSize <= lineCharSize) {
          //add current char and in then max length
          if (ch != "\n".charAt(0)) {
            //not "\n" do nothing

          }
          else {
            //when meet '\n', '\n' is before the end of the line

            if (tempLineText.length() > 0) {
              newLinedText.append(indent).append(tempLineText); //new line
            }

            tempLineText.delete(0, tempLineText.length()); //reset tempLineText

          }

        }
        else {
          if (ch != "\n".charAt(0)) {
            //not "\n"
            //use currentLineText to generate new line
            newLinedText.append(indent).append(currentLineText).append("\n"); //new line
            tempLineText.delete(0, tempLineText.length()); //reset tempLineText
            //add current char into next line
            tempLineText.append(ch);
          }
          else {
            //when '\n' is right the end of the line
            newLinedText.append(indent).append(tempLineText); //new line, include "\n"
            tempLineText.delete(0, tempLineText.length()); //reset tempLineText
          }

        }

      }

       //delete the add"\n"
       if(needAddReturnChar)
         newLinedText.delete(newLinedText.length()-1,newLinedText.length());

      return newLinedText.toString();
    }

  /**
   * 过滤字符串中的特殊字符，for oracle
   * @param pOrgString
   * @return
   */
  public static String filterOracleSqlString(String pOrgString) {
    String sResult = pOrgString;
    if (sResult == null) {
      sResult = "";
    }
    sResult = Tools.replaceString("'", "''", sResult);
    return sResult;
  }

  /**
   * 将ZIP格式文件解压到指定文件
   * @param zip
   * @param unzip
   * @throws Exception
   */
  public static void decompresser(File zip, File unzip) throws Exception {
    OutputStream out = null;
    FileInputStream in = new FileInputStream(zip);
    ZipInputStream zis = new ZipInputStream(new BufferedInputStream(in));
    ZipEntry entry;
    byte[] data = new byte[2048];
    int count;
    int BUFFER = 2048;
    FileOutputStream fos = new FileOutputStream(unzip);
    if ( (entry = zis.getNextEntry()) != null) {
      BufferedOutputStream dest = new BufferedOutputStream(fos, BUFFER);
      while ( (count = zis.read(data, 0, BUFFER)) != -1) {
        dest.write(data, 0, count);
      }
      dest.flush();
      dest.close();
    }
    fos.flush();
    fos.close();
    zis.close();
    in.close();
  }
  
}
