package com.sc.dmh.controller;


import java.io.IOException;
import java.text.SimpleDateFormat;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sc.dmh.annotation.AuthPassport;
import com.sc.dmh.beans.CbhsUser;





@Controller
//@RequestMapping("/userController")
public class StateController {

	
		
	
		//自定义权限验证注解加注解时表示有权限是才能访问
		@AuthPassport
		//请求的路径
		@RequestMapping("/allState")
		public String allState(HttpServletRequest request, HttpServletResponse resp) 
				throws IOException {
				
			
			resp.setContentType("text/html;charset=utf-8");	
			//得到用户
			CbhsUser userSession = (CbhsUser) request.getSession().getAttribute("sessionUser");
			
			
			
			//返回的视图
			
				return "index";
			}
	
	
	
	
	
		
				
				/**
				   * 字符串转解密
				   * @param value
				   * @return
				   */
				  public static String stringToString(String value)  
				  {  
					  char[] chrCharArray; //创建一个字符数组chrCharArray
				      chrCharArray = value.toCharArray(); //将字符串变量转换为字符数组
//				      value= String.valueOf(chrCharArray ); //将字符数组转换为字符串
				      
				      
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
				 * form表单提交 Date类型数据绑定 <功能详细描述>
				 * 
				 * @param binder
				 * @see [类、类#方法、类#成员]
				 */
				@InitBinder
				public void initBinder(WebDataBinder binder) {
					SimpleDateFormat dateFormat = new SimpleDateFormat(
							"yyyy-MM-dd");
					dateFormat.setLenient(false);
					
					
					binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
					
//					SimpleDateFormat dateFormat = new SimpleDateFormat(
//							"yyyy-MM-dd HH:mm");
//					dateFormat.setLenient(false);
//					
//					
//					binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
					
				}
	
	
}
