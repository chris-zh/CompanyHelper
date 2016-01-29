package com.ebao.life.bean.pub.util.ejbgen;

/**
 * Title:        ebao project
 * Description:  ebao java beans and servlets
 * Copyright:    Copyright (c) 2001
 * Company:      ebao
 * @author
 * @version
 */
import java.math.*;
import com.ebao.life.bean.pub.common.*;
import java.sql.Timestamp;
public class DbField {


  private String msFieldName=null;
  private int mnDataType =-1;
  private boolean mbPrimaryKey=false;
  private String msDefaultValue=null;
  final static int typeUnknown=-1;
  final static int typeString=0;
  final static int typeLong=1;
  final static int typeDouble=2;
  final static int typeDate=3;
  final static int typeBlob=4;
  final static int typeClob=5;

  public boolean isTimeStamp(){
    return "TIME_STAMP".equalsIgnoreCase(msFieldName);
  }
  public void setPrimaryKey(boolean bePrimaryKey){
    mbPrimaryKey=bePrimaryKey;
  }
  public boolean isPrimarykey(){
    return mbPrimaryKey;
  }
  public String getFieldName(){
    return msFieldName;
  }
  public DbField(String pFieldName,String pDataType,int pDecDigits) throws Exception {
    mnDataType=mapSqlDataType(pDataType,pDecDigits);
    msFieldName=pFieldName;
  }
  public String getJavaObjectType(){
         switch(mnDataType){
            case typeString:
              return "String";
            case typeDouble:
              return "BigDecimal";
//            case typeLong:
//              return "Long";
            case typeLong:
              if(mbPrimaryKey){
                return "Long";
              }
              else{
                return "BigDecimal";
              }
            case typeDate:
              return "Timestamp";
            case typeBlob:
              return "BLOB";
            case typeClob:
              return "CLOB";
            default:
              return null;
         }
  }
  public String getJavaDataType(){
         switch(mnDataType){
            case typeString:
              return "String";
            case typeDouble:
              return "BigDecimal";
//            case typeLong:
//              return "long";
            case typeLong:
              if(mbPrimaryKey){
                return "long";
              }
              else{
                return "BigDecimal";
              }
            case typeDate:
              return "Timestamp";
            case typeBlob:
            case typeClob:
              return "byte[]";
            default:
              return null;
         }
  }
   private String getDataTypePrefix(int nDataType){
//         System.out.println(nDataType+":"+nDecDigits+":"+mapSqlDataType(sTypeName,nDataType,nDecDigits));
         switch(nDataType){
            case typeString:
              return "str";
            case typeDouble:
              return "dec";
//            case typeLong:
//              return "lng";
            case typeLong:
              if(mbPrimaryKey){
                return "lng";
              }
              else{
                return "dec";
              }
            case typeDate:
              return "date";
            case typeBlob:
            case typeClob:
              return "blob";
            default:
              return null;
         }
 }

  public String getJavaMemberVariableName() throws Exception{
    return "m"+this.getDataTypePrefix(mnDataType)+this.convertFieldName(msFieldName);
  }
  public String getJavaParaVariableName() throws Exception{
    return "p"+this.convertFieldName(msFieldName);
  }
  public String getJavaGetMethodName() throws Exception{
    return "get"+this.convertFieldName(msFieldName);
  }
  public String getJavaSetMethodName() throws Exception{
    return "set"+this.convertFieldName(msFieldName);
  }
  public String getConvertFieldName() throws Exception{
    return this.convertFieldName(msFieldName);
  }
  public String getDefaultValue() throws Exception{
      return msDefaultValue;
  }
  private int mapSqlDataType(String sTypeName,int nDecDigits) throws Exception{
  if(sTypeName==null){
    return typeUnknown;
  }
  String sUcaseType=sTypeName.toUpperCase();
  try{
    if("NUMBER".equals(sTypeName.toUpperCase())){
        if (nDecDigits>0){
          return typeDouble;
        }
        else{
          return typeLong;
        }
    }
    if("DATE".equals(sUcaseType)){
      return typeDate;
    }
    if("CHAR".equals(sUcaseType)||"NCHAR".equals(sUcaseType)||"VARCHAR".equals(sUcaseType)||"NVARCHAR".equals(sUcaseType)||"NVARCHAR2".equals(sUcaseType)||"VARCHAR2".equals(sUcaseType)){
      return typeString;
    }
    if("BLOB".equals(sUcaseType)){
      return typeBlob;
    }
   return typeUnknown;
  }
  catch(Exception e){
    e.printStackTrace();
    throw e;
    //return typeUnknown;
  }
}

  //frank.xing 2002-10-16  from private to public static
  //private  String convertFieldName(String sName){
   public static String convertFieldName(String sName){
     if(sName==null){
        return null;
     }
     if(sName.length()<=1){
        return sName.toUpperCase();
     }
     String tmp;
     String src;
     String dst="";
     src=sName;
     boolean beFirst=true;
     boolean beUpCase=true;
     while(src.length()>0){
        tmp=src.substring(0,1);
        src=src.substring(1,src.length());
/*
        if("T".equals(tmp.toUpperCase())){
          if(beFirst){
            continue;//skip prefix of table name;eg T_customer;
          }
        }
*/
        if("_".equals(tmp)){
           beUpCase=true;
           continue;
        }
        if(beUpCase){
          dst+=tmp.toUpperCase();
        }
        else{
          dst+=tmp.toLowerCase();
        }
        beUpCase=false;
        beFirst=false;
     }
     return dst;
   }
 public void setDefaultValue(String sDefault){
    String sVal=null;
    if(sDefault!=null){
      sDefault=sDefault.trim();
    }
    switch(mnDataType){
        case typeString:
            if(sDefault==null){
                sVal="null";
            }
            else {
                sDefault=sDefault.replace('"',' ');
                sDefault=sDefault.replace('\'',' ');
                sDefault=sDefault.replace('(',' ');
                sDefault=sDefault.replace(')',' ');
                sDefault=sDefault.trim();
                if("null".equals(sDefault)){
                  sDefault=null;
                  sVal="null";
                }
                else{
                  sVal="\""+sDefault+"\"";
                }
            }
            break;
        case typeDouble:
            if(sDefault==null){
                sVal="null";
            }
            else if("".equals(sDefault)){
                sVal="null";
            }
            else if("null".equals(sDefault)){
                sVal="null";
            }
            else if("NULL".equals(sDefault)){
                sVal="null";
            }
            else{
              sVal="new BigDecimal("+Double.parseDouble (sDefault.trim())+")";
            }
            break;
//        case typeLong:
//            sVal=String.valueOf(Long.parseLong (sDefault.trim()));
//            break;
        case typeLong:
              if(mbPrimaryKey){
                if(sDefault==null){
                    sVal="0";
                }
                else if("".equals(sDefault)){
                    sVal="0";
                }
                else if("null".equals(sDefault)){
                    sVal="0";
                }
                else if("NULL".equals(sDefault)){
                    sVal="0";
                }
                else{
                  sVal=String.valueOf(Long.parseLong (sDefault.trim()));
                }
              }
              else{
                if(sDefault==null){
                    sVal="null";
                }
                else if("".equals(sDefault)){
                    sVal="null";
                }
                else if("null".equals(sDefault)){
                    sVal="null";
                }
                else if("NULL".equals(sDefault)){
                    sVal="null";
                }
                else{
                  sVal="BigDecimal.valueOf("+Long.parseLong(sDefault.trim())+"L)";
                }
              }

            break;
        case typeDate:
            if(sDefault==null){
                sVal="null";
            }
            else if(sDefault.toUpperCase().indexOf("SYSDATE")>=0){
                sVal="new Timestamp((new Date()).getTime())";
            }
            else{
                sVal="null";
            }
            break;
        case typeBlob:
            sVal="null";
            break;
        case typeClob:
            sVal="null";
            break;
        default:
            sVal="ERROR!";
            break;
    }
    msDefaultValue= sVal;

  }

}