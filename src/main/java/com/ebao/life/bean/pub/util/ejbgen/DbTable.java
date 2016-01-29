package com.ebao.life.bean.pub.util.ejbgen;

/**
 * Title:        ebao project
 * Description:  ebao java beans and servlets
 * Copyright:    Copyright (c) 2001
 * Company:      ebao
 * @author
 * @version
 */
import java.util.*;
public class DbTable {

  public DbTable() {
    mvFields=new Vector();
    mvUniIndex=new Vector();
  }
  private String msTableName=null;;
  private Vector mvFields=null;
  private Vector mvUniIndex=null;
  public void setTableName(String pTableName){
    msTableName=pTableName;
  }
  public void addUniIndex(UniqueIndex uIndex){
    Vector vIndex=uIndex.getColumns();
    if(vIndex!=null){
      boolean isPK=true;
      for(int i=0;i<vIndex.size();i++){
        if(!((DbField)vIndex.get(i)).isPrimarykey()){
          isPK=false;
        }
      }
      if(!isPK){
        mvUniIndex.add(uIndex);
      }
    }
  }
  public Vector getAllUniIndex(){
    return mvUniIndex;
  }
  public String getTableName(){
    return msTableName;
  }
  public String getConvertedJavaName(){
    return this.convertTableName(msTableName);
  }
  public Vector getNonPrimaryKeyField(){
    Vector v=new Vector();
    for (int i=0;i<mvFields.size();i++){
      if (!((DbField)mvFields.get(i)).isPrimarykey()){
        v.add(mvFields.get(i));
      }
    }
    return v;
  }
  public Vector getPrimaryKey(){
    Vector v=new Vector();
    for (int i=0;i<mvFields.size();i++){
      if (((DbField)mvFields.get(i)).isPrimarykey()){
        v.add(mvFields.get(i));
      }
    }
    return v;
  }
  public void addField(DbField pField){
    mvFields.addElement(pField);
  }
  public Vector getFields(){
    return mvFields;
  }
  public DbField getField(String sFieldName){
    DbField field=null;
    for(int i=0;i<mvFields.size();i++){
      if(((DbField)mvFields.get(i)).getFieldName().equals(sFieldName)){
        field=(DbField)mvFields.get(i);
      }
    }
    return field;
  }
  private  String convertTableName(String sName){
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
  boolean beStart=true;
  while(src.length()>0){
     tmp=src.substring(0,1);
     src=src.substring(1,src.length());
     if("T".equals(tmp.toUpperCase())){
       if(beStart){
         beStart=false;
         continue;//skip prefix of table name;eg T_customer;
       }
     }
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


}