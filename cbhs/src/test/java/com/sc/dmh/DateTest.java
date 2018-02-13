package com.sc.dmh;



import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.sc.dmh.beans.CbhsUser;
import com.sc.dmh.beans.CbhsUserExample;
import com.sc.dmh.beans.CbhsUserExample.Criteria;
import com.sc.dmh.beans.DateMonthFirstLast;
import com.sc.dmh.beans.Rpf;
import com.sc.dmh.service.inter.UserServiceI;
import com.sc.dmh.util.DateStringUtil;




@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/root-context.xml","file:src/main/webapp/WEB-INF/spring/appServlet/spring-mybatis.xml"})   
public class DateTest {
	
	private static final Logger logger = Logger.getLogger(DateTest.class);
	@Autowired
	private UserServiceI userServiceI;
	
	public UserServiceI getUserServiceI() {
		return userServiceI;
	}

	public void setUserServiceI(UserServiceI userServiceI) {
		this.userServiceI = userServiceI;
	}

	@Test
	public void testGet() throws Exception {
		
		 CbhsUser user = userServiceI.selectByPrimaryKey(1);
		
		
		
		logger.info(JSON.toJSONStringWithDateFormat(user, "yyyy-MM-dd HH:mm:ss"));
	}
	
	@Test
	public void testGetpassusername() throws Exception {
		
		DateMonthFirstLast a = DateStringUtil.getDateFirstLast(new Date());
		
		logger.info(JSON.toJSONStringWithDateFormat(a, "yyyy-MM-dd HH:mm:ss"));
	}
	
	
//	 /** 
//     * @param args 
//     * @throws ParseException  
//     */  
//    public static void main(String[] args) throws ParseException {  
//        // TODO Auto-generated method stub  
//        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
//        Date d1=sdf.parse("2012-09-01");  
//        Date d2=sdf.parse("2012-09-30");  
//        System.out.println(daysBetween(d1,d2));
//  
//        System.out.println(daysBetween("2012-09-08 10:10:10","2012-09-15 00:00:00"));  
//    }  
	
	 /** 
	   * @param args 
	   * @throws ParseException  
	   */  
  public static void main(String[] args) throws ParseException {  
      // TODO Auto-generated method stub  
      String str = "~~";
     
      byte[] binaryData = null;
      binaryData = str.getBytes();
      System.out.println(Base64.encodeBase64URLSafeString(binaryData));
      System.out.println(new String(Base64.decodeBase64(Base64.encodeBase64URLSafeString(binaryData))));
      String strStringType="111111"; //创建一个字符串变量strStringType
      byte[] aaa;
      aaa = strStringType.getBytes();
	System.out.println(encode(aaa));
	
	System.out.println(new String(decode(encode(aaa))));
      
  }  
  
  /**  
   * 编码  
   * @param bstr  
   * @return String  
   */    
  public static String encode(byte[] bstr){    
  return new sun.misc.BASE64Encoder().encode(bstr);    
  }    
  /**  
   * 解码  
   * @param str  
   * @return string  
   */    
  public static byte[] decode(String str){    
  byte[] bt = null;    
  try {    
      sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();    
      bt = decoder.decodeBuffer( str );    
  } catch (IOException e) {    
      e.printStackTrace();    
  }    
  
      return bt;    
  }    
  
  /**
   * 字符串转解密
   * @param value
   * @return
   */
  public static String stringToAscii2(String value)  
  {  
	  char[] chrCharArray; //创建一个字符数组chrCharArray
      chrCharArray = value.toCharArray(); //将字符串变量转换为字符数组
//      value= String.valueOf(chrCharArray ); //将字符数组转换为字符串
      
      
      for(int i = 0;i<chrCharArray.length;i++){
    	 
    	  System.out.println((int)chrCharArray[i]);	 
    	  chrCharArray[i] = (char)(chrCharArray[i]+(i+1));
    	  System.out.println((int)chrCharArray[i]);
     }
      value= String.valueOf(chrCharArray ); //将字符数组转换为字符串
     System.out.println(value);
      return value;  

}
  
  /**
   * 字符串转换为Ascii
   * @param value
   * @return
   */
  public static String stringToAscii(String value)  
  {  
      StringBuffer sbu = new StringBuffer();  
      char[] chars = value.toCharArray();   
      for (int i = 0; i < chars.length; i++) {  
          if(i != chars.length - 1)  
          {  
              sbu.append((int)chars[i]).append(",");  
          }  
          else {  
              sbu.append((int)chars[i]);  
          }  
      }  
      return sbu.toString();  

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
	
	
}
