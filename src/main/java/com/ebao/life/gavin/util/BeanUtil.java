package com.ebao.life.gavin.util;

//created by liwei.zhang
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import com.ebao.life.bean.pub.common.Tools;
import com.ebao.life.bean.pub.util.ejbgen.DbField;

public class BeanUtil {
  /**
   *<p>Description£ºgenerate a new instance of designated bean class with values from source bean object</p>
   *@param	beanClass - designated bean class
   *@param	srcBean - source bean object
   *@return	a new instance of designated bean class
   */
  public static Object getBean(Class beanClass, Object srcBean) {
    try {
      return copyObject(beanClass.newInstance(), srcBean);
    }
    catch (Exception ex) {
      return null;
    }
  }


  public static Object copyObject(Object destObj, Object srcObj) {
    return BeanUtil.copyObject(destObj, srcObj, true);
  }

  public static Object copyObject(Object destObj, Object srcObj, boolean nullUpdate) {
    try {
      if (srcObj == null || destObj == null) {
        return null;
      }

      PropertyDescriptor[] beanProps = Introspector.getBeanInfo(destObj.getClass()).getPropertyDescriptors();
      String propName = null;
      Object propValue = null;

      for (int i = 0; i < beanProps.length; i++) {
        propName = beanProps[i].getName();
        try {
          propValue = getPropertyValue(srcObj, propName);
        }
        catch (NoSuchFieldException ex) {
          continue;
        }

        if (!nullUpdate && propValue == null) {
          continue;
        }
        try {
          setPropertyValue(destObj, propName, propValue);
        }
        catch (Exception ex) {
          continue;
        }
      }
      return destObj;
    }
    catch (Exception ex) {
      return null;
    }
  }

  /**
   *<p>Description£ºgenerate a new instance of designated bean class with values from a HTTP request</p>
   *@param	beanClass - designated bean class
   *@param	request - a HTTP request from with the bean will be generated
   *@return	a new instance of designated bean class
   */
  public static Object getBean(Class beanClass, HttpServletRequest request) {
    return getBean(beanClass, request, true);
  }

  public static Object getBean(Class beanClass, HttpServletRequest request, boolean toGBK) {
    try {
      return copyObject(beanClass.newInstance(), request, toGBK);
    }
    catch (Exception ex) {
      return null;
    }
  }

  public static Object copyObject(Object destObj, HttpServletRequest request) {
    return copyObject(destObj, request, true);
  }

  

  
  /**
   *<p>Description£ºgenerate a set of new instances of designated bean class with values from source bean objects</p>
   *@param	beanClass - designated bean class
   *@param	srcBeans - source bean objects
   *@return	a set of new instances of designated bean class
   */
  public static ArrayList getBeans(Class beanClass, Collection srcBeans) {
    ArrayList newBeans = new ArrayList(srcBeans.size());
    Iterator srcBeanList = srcBeans.iterator();
    Object newBean = null;
    while (srcBeanList.hasNext()) {
      newBean = BeanUtil.getBean(beanClass, srcBeanList.next());
      if (newBean != null) {
        newBeans.add(newBean);
      }
    }
    return newBeans;
  }

  /**
   *<p>Description£ºgenerate a set of new instances of designated bean class with values from a result set</p>
   *@param	beanClass - designated bean class
   *@param	rs - a database result set
   *@return	a set of new instances of designated bean class
   */
  public static ArrayList getBeans(Class beanClass, ResultSet rs) {
    return getBeans(beanClass, rs, null);
  }

  public static ArrayList getBeans(Class beanClass, ResultSet rs, HashMap nameMappings) {
    try {
      ResultSetMetaData rsmd = rs.getMetaData();
      ArrayList newBeans = new ArrayList();
      String sFieldName = null;
      Object fieldValue = null;
      Object newBean = null;

      while (rs.next()) {
        newBean = beanClass.newInstance();
        for (int i = 1; i <= rsmd.getColumnCount(); i++) {
          sFieldName = rsmd.getColumnName(i);

          fieldValue = rs.getObject(sFieldName);
          if (fieldValue == null) {
            continue;
          }

          sFieldName = DbField.convertFieldName(sFieldName);
          if (nameMappings != null) {
            String mappedName = (String) nameMappings.get(sFieldName);
            sFieldName = mappedName == null ? sFieldName : mappedName;
          }

          try {
            setPropertyValue(newBean, sFieldName, fieldValue);
          }
          catch (Exception ex) {
            continue;
          }
        }
        newBeans.add(newBean);
      }
      return newBeans;
    }
    catch (Exception ex) {
      return null;
    }
  }

  public static void setPropertyValue(Object bean, String propName,
                                      Object propValue)
      throws Exception {
    int delimiterPos = propName.indexOf(".");
    if (delimiterPos == -1) {
      Method writeMethod = getPropertyWriteMethod(bean.getClass(), propName);
      if (writeMethod !=  null &&
          Modifier.isPublic(writeMethod.getModifiers())) {

        Class[] paramTypes = writeMethod.getParameterTypes();
        Object[] args = new Object[] {convert(paramTypes[0], propValue)};
        writeMethod.invoke(bean, args);
      }
    }
    else {
      String memberName = propName.substring(0, delimiterPos);
      Object memberObj = getPropertyValue(bean, memberName);
      if (memberObj != null) {
        memberObj = memberObj.getClass().newInstance();
        setPropertyValue(bean, memberName, memberObj);
      }
      propName = propName.substring(delimiterPos + 1, propName.length());
      setPropertyValue(memberObj, propName, propValue);
    }
  }

  public static Object getPropertyValue(Object bean, String propName)
      throws NoSuchFieldException {
    if (bean == null) {
      return null;
    }

    int delimiterPos = propName.indexOf(".");

    try {
      if (delimiterPos != -1) {
        String memberName = propName.substring(0, delimiterPos);
        Object memberObj = getPropertyValue(bean, memberName);
        if (memberObj != null) {
          return getPropertyValue(memberObj, propName.substring(delimiterPos + 1, propName.length()));
        }
        else {
          return null;
        }
      }
      else {
        Method readMethod = getPropertyReadMethod(bean.getClass(), propName);
        if (readMethod == null || !Modifier.isPublic(readMethod.getModifiers())) {
          throw new NoSuchFieldException();
        }
        return readMethod.invoke(bean, null);
      }
    }
    catch (NoSuchFieldException ex) {
      throw ex;
    }
    catch (Exception ex) {
      return null;
    }
  }

  public static Object convert(Class classToConvert, Object source) {
    if (source == null) {
      return null;
    }

    try {
      if (classToConvert.isInstance(source)) {
        return source;
      }
//      if (classToConvert.equals(source.getClass())) {
//        return source;
//      }

      if (classToConvert.equals(String.class)) {
        return source.toString();
      }
      if (classToConvert.equals(Integer.class)) {
        return new Integer(Tools.toInteger(source.toString()));
      }
      if (classToConvert.equals(Long.class)) {
        return new Long(Tools.toLong(source.toString()));
      }
      if (classToConvert.equals(Float.class)) {
        return new Float(Tools.toFloat(source.toString()));
      }
      if (classToConvert.equals(Double.class)) {
        return new Double(Tools.toDouble(source.toString()));
      }
      if (classToConvert.equals(int.class)) {
        return new Integer(Tools.toInteger(source.toString()));
      }
      if (classToConvert.equals(long.class)) {
        return new Long(Tools.toLong(source.toString()));
      }
      if (classToConvert.equals(float.class)) {
        return new Float(Tools.toFloat(source.toString()));
      }
      if (classToConvert.equals(double.class)) {
        return new Double(Tools.toDouble(source.toString()));
      }
      if (classToConvert.equals(BigDecimal.class)) {
        return Tools.getBigDecimal(source.toString());
      }
      if (classToConvert.equals(Timestamp.class)) {
        return Tools.getTimestamp(source.toString());
      }
      if (classToConvert.equals(java.util.Date.class)) {
        return Tools.toDate(source.toString());
      }
      if (classToConvert.equals(boolean.class)) {
        return new Boolean("true".equals(source.toString())?true:false);
      }
      if (classToConvert.equals(String[].class)) {
        String[] returnValue = {source.toString()};
        return returnValue;
      }
      if (classToConvert.equals(int[].class)) {
        int[] returnValue = null;
        if (source.getClass().equals(String[].class)) {
          int len = ((String[])source).length;
          returnValue = new int[len];
          for (int i=0;i<len;i++) {
            returnValue[i] = Tools.toInteger(((String[])source)[i]);
          }
        }
        return returnValue;
      }
      if (classToConvert.equals(long[].class)) {
        long[] returnValue = null;
        if (source.getClass().equals(String[].class)) {
          int len = ((String[])source).length;
          returnValue = new long[len];
          for (int i=0;i<len;i++) {
            returnValue[i] = Tools.toLong(((String[])source)[i]);
          }
        }
        return returnValue;
      }
      if (classToConvert.equals(float[].class)) {
        float[] returnValue = null;
        if (source.getClass().equals(String[].class)) {
          int len = ((String[])source).length;
          returnValue = new float[len];
          for (int i=0;i<len;i++) {
            returnValue[i] = Tools.toFloat(((String[])source)[i]);
          }
        }
        return returnValue;
      }
      if (classToConvert.equals(double[].class)) {
        double[] returnValue = null;
        if (source.getClass().equals(String[].class)) {
          int len = ((String[])source).length;
          returnValue = new double[len];
          for (int i=0;i<len;i++) {
            returnValue[i] = Tools.toDouble(((String[])source)[i]);
          }
        }
        return returnValue;
      }
      if (classToConvert.equals(Integer[].class)) {
        Integer[] returnValue = null;
        if (source.getClass().equals(String[].class)) {
          int len = ((String[])source).length;
          returnValue = new Integer[len];
          for (int i=0;i<len;i++) {
            returnValue[i] = new Integer(Tools.toInteger(((String[])source)[i]));
          }
        }
        return returnValue;
      }
      if (classToConvert.equals(Long[].class)) {
        Long[] returnValue = null;
        if (source.getClass().equals(String[].class)) {
          int len = ((String[])source).length;
          returnValue = new Long[len];
          for (int i=0;i<len;i++) {
            returnValue[i] = new Long(Tools.toLong(((String[])source)[i]));
          }
        }
        return returnValue;
      }
      if (classToConvert.equals(Float[].class)) {
        Float[] returnValue = null;
        if (source.getClass().equals(String[].class)) {
          int len = ((String[])source).length;
          returnValue = new Float[len];
          for (int i=0;i<len;i++) {
            returnValue[i] = new Float(Tools.toFloat(((String[])source)[i]));
          }
        }
        return returnValue;
      }
      if (classToConvert.equals(Double[].class)) {
        Double[] returnValue = null;
        if (source.getClass().equals(String[].class)) {
          int len = ((String[])source).length;
          returnValue = new Double[len];
          for (int i=0;i<len;i++) {
            returnValue[i] = new Double(Tools.toDouble(((String[])source)[i]));
          }
        }
        return returnValue;
      }
      if (classToConvert.equals(java.util.Date[].class)) {
        java.util.Date[] returnValue = null;
        if (source.getClass().equals(String[].class)) {
          int len = ((String[])source).length;
          returnValue = new java.util.Date[len];
          for (int i=0;i<len;i++) {
            returnValue[i] = Tools.toDate(((String[])source)[i]);
          }
        }
        return returnValue;
      }
      if (classToConvert.equals(BigDecimal[].class)) {
        BigDecimal[] returnValue = null;
        if (source.getClass().equals(String[].class)) {
          int len = ((String[])source).length;
          returnValue = new BigDecimal[len];
          for (int i=0;i<len;i++) {
            returnValue[i] = Tools.getBigDecimal(((String[])source)[i]);
          }
        }
        return returnValue;
      }
    } catch (Exception ex) {
      return null;
    }
    return null;
  }

  public static void isoToGBK(Object srcBean) {
    try {
      PropertyDescriptor[] srcProps =
          Introspector.getBeanInfo(srcBean.getClass()).getPropertyDescriptors();
      PropertyDescriptor aProperty = null;
      String propValue = null;
      Object[] params = new Object[1];

      for (int i = 0; i < srcProps.length; i++) {
        aProperty = srcProps[i];
        if (aProperty.getPropertyType().equals(String.class)) {
          propValue = (String) aProperty.getReadMethod().invoke(srcBean, null);
          propValue = Tools.toGB(propValue);
          params[0] = propValue;
          aProperty.getWriteMethod().invoke(srcBean, params);
        }
      }
    }
    catch (Exception ex) {
    }
  }

  public static Method getPropertyReadMethod(Class beanClass, String propName) {
    Method method = null;
    try{
      propName = propName.substring(0, 1).toUpperCase() +
                 propName.substring(1, propName.length());
      try{
        method = beanClass.getMethod("get" + propName, null);
      }catch(NoSuchMethodException nsex){
        method = beanClass.getMethod("is" + propName, null);
      }
    }catch(NoSuchMethodException ex){
      method = null;
    }
    return method;
  }

  public static Method getPropertyWriteMethod(Class beanClass, String propName) {
    Method readMethod = getPropertyReadMethod(beanClass, propName);
    if (readMethod == null) {
      return null;
    }

    try {
      Class[] argTypes = {readMethod.getReturnType()};
      propName = propName.substring(0, 1).toUpperCase() +
                 propName.substring(1, propName.length());
      return beanClass.getMethod("set" + propName, argTypes);
    }
    catch (Exception ex) {
      return null;
    }
  }

  public static void main(String[] argv) throws Exception{

  }
  
  public static String getRequestURL(HttpServletRequest request)throws Exception{
	    Enumeration paramNames = request.getParameterNames();
	    String propName = null;
	    Object propValue = null;
	    String[] paramValues = null;
	    String url = request.getRequestURI()+"?";
	    boolean toGBK = true;
	    while (paramNames.hasMoreElements()) {
	      propName = (String) paramNames.nextElement();
	      paramValues = request.getParameterValues(propName);
	      switch (paramValues.length) {
	        case 0:
	          propValue = null;
	          break;
	        case 1:
	          propValue = paramValues[0];
	          if (toGBK) {
	            propValue = Tools.toGB(propValue.toString());
	          }
	          break;
	        default:
	          propValue = paramValues;
	      }
	      url+=propName+"="+propValue+"&";
	    }
	    url = url.substring(0,url.length()-1);
	    return url;
  }

}