package com.sc.dmh.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

public class DateStringUtil {
	
    /** 
     * Timestamp按格式转换成Date 
     *  
     * @param timestamp 
     * @param format 
     * @return Date 
     */  
	   public static Date toDate(String sDate, String sFmt) {
	        Date dt = null;
	        
	        try {
	            dt = new SimpleDateFormat(sFmt).parse(sDate);
	        } catch (ParseException e) {
	            return dt;
	        }
	        
	        return dt;
	    }
	   
	   /**
	    * 组合list,属性为key属性值累加为value的map
	    * @param t
	    * @return
	    */
	   public static Map<String, String> getFieldMap(List<?> t){
			  //判断数据是否为空为空返回null
			  if(t.size()<1)return null;
			  //得到对象的Class
			  Class<?> demo=t.get(0).getClass();
			  //取得本类的全部属性
			  Field[] field = demo.getDeclaredFields();
			  //创建返回Map
			  Map<String,String> returnMap = new HashMap<String,String>();
			  //循环全部对象
			  for(int i=0;i<t.size();i++){
				  //循环全部属性
				  for(int j=0; j<field.length;j++){
					  try {
						  	//得到属性的值
						  	String fieldValue = BeanUtils.getProperty(t.get(i),field[j].getName());
						  	//如果属性的值为null并且属性类型是String或Integer则修改为空串
						  	
						  	if(fieldValue == null && (field[j].getType().getName().equals("java.lang.String") || field[j].getType().getName().equals("java.lang.Integer"))) fieldValue = "";
						  	// 判断属性类型为java.util.Date
							if (field[j].getType().getName().equals("java.util.Date")) {
								//类型为Date属性值为null并且map中存在
								if(fieldValue == null && returnMap.containsKey(field[j].getName())){
									//获取以属性名为key的value
									String str = returnMap.get(field[j].getName());
									str = str + "\n";
									//累加后添加到map
									returnMap.put(field[j].getName(), str);
									//退出本次循环
									continue;
								}
								//类型为Date属性值为null并且map中不存在
								if(fieldValue == null && !returnMap.containsKey(field[j].getName())){
									//添加到map
									returnMap.put(field[j].getName(), "\n");
									//退出本次循环
									continue;
								}
								//构造SimpleDateFormat对象时使用两个参数的构造函数,将Locale指定为US,
								SimpleDateFormat sdf = new SimpleDateFormat("E MMM dd hh:mm:ss z yyyy", Locale.US);
								try {
									//转换String属性值为Date类型
									Date date = sdf.parse(fieldValue);
									//修改输出模式
									sdf.applyPattern("HH:mm");
									
									// map中已经存在累加
									if (returnMap.containsKey(field[j].getName())) {
										//获取以属性名为key的value
										String str = returnMap.get(field[j].getName());
										str = str
												+ "\n"
												+ sdf.format(date);
										//累加后添加到map
										returnMap.put(field[j].getName(), str);

									} else {// map中不存在直接put
										//添加到map
										returnMap.put(field[j].getName(), "\n"	+ sdf.format(date));
									}
								} catch (ParseException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							} else {// 其他类型
								
								// map中已经存在累加
								if (returnMap.containsKey(field[j].getName())) {
									//获取以属性名为key的value
									String str = returnMap.get(field[j].getName());
									str = str
											+ "\n"
											+ fieldValue;
									//累加后添加到map
									returnMap.put(field[j].getName(), str);
								} else {// map中不存在直接put
									//添加到map
									returnMap.put(field[j].getName(), "\n"	+ fieldValue); 
								}
							}
						  
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (NoSuchMethodException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				  }
			  }
			  
			  return returnMap;
		  }
	   /**
	    * 组合list为String,属性累加为一行,
	    * @param 
	    * @return
	    */
	   public static String getFieldToString(List<?> t){
			  //判断数据是否为空为空返回null
			  if(t.size()<1)return null;
			  //得到对象的Class
			  Class<?> demo=t.get(0).getClass();
			  //取得本类的全部属性
			  Field[] field = demo.getDeclaredFields();
			  //创建返回Map
			  String returnStr = new String();
			  //循环全部对象
			  for(int i=0;i<t.size();i++){
				  //每个对象一行,每行前加换行
				  returnStr=returnStr+"\n";
				  //循环全部属性
				  for(int j=0; j<field.length;j++){
					  try {
						  	//得到属性的值
						  	String fieldValue = BeanUtils.getProperty(t.get(i),field[j].getName());
						  	//如果属性的值为null并且属性类型是String或Integer则修改为空串
						  	
						  	if(fieldValue == null && (field[j].getType().getName().equals("java.lang.String") || field[j].getType().getName().equals("java.lang.Integer"))) fieldValue = "";
						  	// 判断属性类型为java.util.Date
							if (field[j].getType().getName().equals("java.util.Date")) {
								//退出本次循环
								continue;
								
							} else {// 其他类型
								//检查属性名是否有Id有跳出
								if(field[j].getName().indexOf("Id")>=0)continue;//退出本次循环
								returnStr=returnStr + fieldValue;
//								//String中不存在 
//								if (returnStr == null || returnStr.length() <= 0) {
//									//获取以属性名为key的value
//									String str = returnStr.get(field[j].getName());
//									str = str
//											+ "\n"
//											+ fieldValue;
//									//累加后添加到map
//									returnStr.put(field[j].getName(), str);
//								} else {// String中已经存在累加
//									//添加到String
//									returnStr.put(field[j].getName(), "\n"	+ fieldValue); 
//								}
							}
						  
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (NoSuchMethodException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				  }
			  }
			  
			  return returnStr;
		  }

	public static Date getSystemDate() {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ");
		try {
			Date date = sdf.parse(new SimpleDateFormat("yyyy-MM-dd").format(new Date(System.currentTimeMillis())));
			return date;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
  

}
