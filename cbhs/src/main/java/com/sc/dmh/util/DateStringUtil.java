package com.sc.dmh.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.sc.dmh.beans.DateMonthFirstLast;

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
	     * Timestamp按格式转换成Date 
	     *  
	     * @param timestamp 
	     * @param format 
	     * @return Date 
	     * @throws ParseException 
	     */  
		   public static DateMonthFirstLast getDateFirstLast(Date Date) throws ParseException {
			    
			    DateMonthFirstLast dateMonthFirstLast = new DateMonthFirstLast();
			    Calendar cale = null;
			    cale = Calendar.getInstance();
			    
			   
			    
			    cale.setTime(Date);
			    
		      
		        // 获取前月的第一天
		      
		        cale.add(Calendar.MONTH, 0);
		        cale.set(Calendar.DAY_OF_MONTH, 1);
		        dateMonthFirstLast.setFirstday(cale.getTime());
		        // 获取前月的最后一天
		        
		        cale.add(Calendar.MONTH, 1);
		        cale.set(Calendar.DAY_OF_MONTH, 0);
		        dateMonthFirstLast.setLastday(cale.getTime());
		           
			   
		        
		        return dateMonthFirstLast;
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
						  //退出本次循环,判断属性类型为long
						  if(field[j].getType().getName().equals("long"))continue;
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
								SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.US);
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
						//退出本次循环,判断属性类型为long
						  if(field[j].getType().getName().equals("long"))continue;
								
								
							 
						 
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
	
	
	/**
	* Object 转换成 int
	*
	* @param s
	* @return int
	*/
	public static int parseInt(Object s) {
	if (s != null && s.toString().length() > 0) {
	try {
	return Integer.parseInt(s.toString());
	} catch (Exception e) {
	return 0;
	}
	} else {
	return 0;
	}
	}

	/**
	* Object 转换成 String
	*
	* @param s
	* @return String
	*/
	public static String parseString(Object s) {
	if (null != s) {
	return s.toString();
	} else {
	return "";
	}
	}
	
	/**
	 * java中遍历一个对象的所有属性 判断是否为null
	 * @param model
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws SecurityException 
	 * @throws NoSuchFieldException 
	 */
	public static int getObjectIsNull(Object model) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException, SecurityException{
        int i = 0;
		Field[] field = model.getClass().getDeclaredFields();        //获取实体类的所有属性，返回Field数组  
        for(int j=0 ; j<field.length ; j++){     //遍历所有属性
                String name = field[j].getName();    //获取属性的名字
                if(name.equals("cheZhan")){//车站属性
                	
                	name = name.substring(0,1).toUpperCase()+name.substring(1); //将属性的首字符大写，方便构造get，set方法
                    String type = field[j].getGenericType().toString();    //获取属性的类型
                    if(type.equals("class java.lang.String")){   //如果type是类类型，则前面包含"class "，后面跟类名
                        Method m = model.getClass().getMethod("get"+name);
                        
                        String value = (String) m.invoke(model);    //调用getter方法获取属性值
                        
                        if( "调\n度\n员".equals(value)){
                        	break;//车站为调动员时退出for循环
                        }
                       
                        
                    }
                    continue;//车站属性不是调度员进入下次循环
                	
                }
                //System.out.println("attribute name:"+name);     
                name = name.substring(0,1).toUpperCase()+name.substring(1); //将属性的首字符大写，方便构造get，set方法
                String type = field[j].getGenericType().toString();    //获取属性的类型
                if(type.equals("class java.lang.String")){   //如果type是类类型，则前面包含"class "，后面跟类名
                    Method m = model.getClass().getMethod("get"+name);
                    
                    String value = (String) m.invoke(model);    //调用getter方法获取属性值
                    
                    if(value != null && value != "") i++;//数据不为空i+1
                   
                    
                }
          
            }
        return i;
    }
	
	
	
	/**  
     * 计算两个日期之间相差的天数  
     * @param smdate 较小的时间 
     * @param bdate  较大的时间 
     * @return 相差天数 
     * @throws ParseException  
     */    
    public static int daysBetween(Date smdate,Date bdate) throws ParseException    
    {    
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
        smdate=sdf.parse(sdf.format(smdate));  
        bdate=sdf.parse(sdf.format(bdate));  
        Calendar cal = Calendar.getInstance();    
        cal.setTime(smdate);    
        long time1 = cal.getTimeInMillis();                 
        cal.setTime(bdate);    
        long time2 = cal.getTimeInMillis();         
        long between_days=(time2-time1)/(1000*3600*24);  
            
       return Integer.parseInt(String.valueOf(between_days));           
    }    
      
/** 
*字符串的日期格式的计算 
*/  
    public static int daysBetween(String smdate,String bdate) throws ParseException{  
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
        Calendar cal = Calendar.getInstance();    
        cal.setTime(sdf.parse(smdate));    
        long time1 = cal.getTimeInMillis();                 
        cal.setTime(sdf.parse(bdate));    
        long time2 = cal.getTimeInMillis();         
        long between_days=(time2-time1)/(1000*3600*24);  
            
       return Integer.parseInt(String.valueOf(between_days));     
    }  
    
    /**
     * 日期加一天方法
     * @param date
     * @return
     */
    public static Date addDateOneDay(Date date) {  
        if (null == date) {  
            return date;  
        }  
        Calendar c = Calendar.getInstance();  
        c.setTime(date);   //设置当前日期  
        c.add(Calendar.DATE, 1); //日期加1天  
//     c.add(Calendar.DATE, -1); //日期减1天  
        date = c.getTime();  
        return date;  
    }  
    
    /**
     * 返回MMDD日期形式
     * @param date
     * @return
     */
    public static String getDateMMdd(Date date)
    {
        if( null == date ) return "";
        return new SimpleDateFormat("MM-dd").format(date);
    }	
  

}
