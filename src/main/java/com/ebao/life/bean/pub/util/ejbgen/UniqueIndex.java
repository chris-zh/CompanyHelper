package com.ebao.life.bean.pub.util.ejbgen;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author
 * @version 1.0
 */
import java.util.*;
public class UniqueIndex {

  public UniqueIndex() {
  }
  private Vector vColumns=null;
  public void setColumns(Vector pColumns){
    vColumns=pColumns;
  }
  public Vector getColumns(){
    return vColumns;
  }
  public String getPartMethodName() throws Exception{
      String s="";
      for(int j=0;j<vColumns.size();j++){
        DbField field=(DbField)vColumns.get(j);
        if(j>0){
          s=s+"And";
        }
        s=s+field.getConvertFieldName();
      }
      return s;
  }
  public String getMethodDeclarePara() throws Exception{
      String s="(";
      for(int j=0;j<vColumns.size();j++){
        DbField field=(DbField)vColumns.get(j);
        if(j>0){
          s=s+",";
        }
        s=s+field.getJavaDataType()+" "+field.getJavaParaVariableName();
      }
      s=s+")";
      return s;
  }
  public String getMethodCallPara() throws Exception{
      String s="(";
      for(int j=0;j<vColumns.size();j++){
        DbField field=(DbField)vColumns.get(j);
        if(j>0){
          s=s+",";
        }
        s=s+field.getJavaParaVariableName();
      }
      s=s+")";
      return s;
  }
}