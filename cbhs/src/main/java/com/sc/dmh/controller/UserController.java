package com.sc.dmh.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.sc.dmh.annotation.AuthPassport;
import com.sc.dmh.beans.CbhsUser;
import com.sc.dmh.beans.CbhsUserExample;

import com.sc.dmh.beans.CbhsUserExample.Criteria;
import com.sc.dmh.service.inter.UserServiceI;



@Controller
//@RequestMapping("/userController")
public class UserController {

	@Autowired
	private UserServiceI userService;
	
	

	public void setUserService(UserServiceI userService) {
		this.userService = userService;
	}
	
		
	
	//自定义权限验证注解加注解时表示有权限是才能访问
	@AuthPassport
	//请求的路径
	@RequestMapping("/index")
	public String showIndex( 
			HttpServletRequest request, 
			HttpServletResponse resp) 
			throws IOException {
			//返回的视图
		
			return "index";
		}
	
	
	//自定义权限验证注解加注解时表示有权限是才能访问
	@AuthPassport
	//请求的路径-退出方法
	@RequestMapping("/quit")
	public String quit( 
			HttpServletRequest request, 
			HttpServletResponse resp) 
			throws IOException {
			//Session失效，里面的所有东西都会清空了，同时也释放了资源。
			request.getSession().invalidate();
			//返回的视图
			return "login";
			}
	
	
	
	//登陆控制器
	@RequestMapping("/login")
	public String showUser(CbhsUser formUserdmh, 
			HttpServletRequest request, 
			HttpServletResponse resp) 
			throws IOException {//@PathVariable String id(路径变量如@RequestMapping("/{id}/showUser"))
		
		
		
		
//		----------------------------------------------------------------------------------
//		Cookie[] cookies = request.getCookies();//这样便可以获取一个cookie数组
//		for(Cookie cookie : cookies){
//		    System.out.println(cookie.getName());// get the cookie name
//		    System.out.println(cookie.getValue());; // get the cookie value
//		}
		
		
		/*
		 * 
		 * 2. 校验表单数据
		 * 3. 使用service查询，得到User
		 * 4. 查看用户是否存在，如果不存在：
		 *   * 保存错误信息：用户名或密码错误
		 *   * 保存用户数据：为了回显
		 *   * 转发到login.jsp
		 * 5. 如果存在，查看状态，如果状态为false：
		 *   * 保存错误信息：您没有激活
		 *   * 保存表单数据：为了回显
		 *   * 转发到login.jsp
		 * 6. 登录成功：
		 * 　　* 保存当前查询出的user到session中
		 *   * 保存当前用户的名称到cookie中，注意中文需要编码处理。
		 */
		/*
		 * 2. 校验
		 */
		Map<String,String> errors = validateLogin(formUserdmh);
		if(errors.size() > 0) {
			request.setAttribute("form", formUserdmh);
			request.setAttribute("errors", errors);
			//跳转到login.jsp页面
			return "login";
		}
		
		/*
		 * 3. 调用userService查询方法
		 */
		List<CbhsUser> uL=null;
		try {
			
			CbhsUserExample example = new CbhsUserExample();
			Criteria criteria = example.createCriteria();
			criteria.andUserNameEqualTo(formUserdmh.getUserName());
			criteria.andPasswordEqualTo(formUserdmh.getPassword());
			
			
			uL = userService.selectByExample(example);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(uL.size()<1){
			request.setAttribute("msg", "用户名或密码错误！");
			request.setAttribute("user", formUserdmh);
			return "login";
		}
		//保存用户到session中
		request.getSession().setAttribute("sessionUser", uL.get(0));
		// 获取用户名保存到cookie中
		String loginname = uL.get(0).getUserName();
		loginname = URLEncoder.encode(loginname, "utf-8");
		Cookie cookie = new Cookie("loginname", loginname);
		cookie.setMaxAge(60 * 60 * 24 * 30);//保存30天
		resp.addCookie(cookie);
		//request.
		
		
		return "index";
	}

	//校验表单
	private Map<String, String> validateLogin(CbhsUser formUser) {
		Map<String,String> errors = new HashMap<String,String>();
		/*
		 * 1. 校验登录名
		 */
		String loginname = formUser.getUserName();
		if(loginname == null || loginname.trim().isEmpty()) {
			errors.put("loginname", "用户名不能为空！");
		} else if(loginname.length() < 2 || loginname.length() > 20) {
			errors.put("loginname", "用户名长度必须在2~20之间！");
		} 
		
		
		/*
		 * 2. 校验登录密码
		 */
		String loginpass = formUser.getPassword();
		if(loginpass == null || loginpass.trim().isEmpty()) {
			errors.put("loginpass", "密码不能为空！");
		} else if(loginpass.length() < 3 || loginpass.length() > 20) {
			errors.put("loginpass", "密码长度必须在3~20之间！");
		}
	
		return errors;
		
	}
	
		//自定义权限验证注解加注解时表示有权限是才能访问
		@AuthPassport
		//请求的路径
		@RequestMapping("/userhsf")
		public String showUserPwd( 
				HttpServletRequest request, 
				HttpServletResponse resp) 
						throws IOException {
			//返回的视图
			
			return "user/userhsf";
		}
			
		
		// 自定义权限验证注解加注解时表示有权限是才能访问
				@AuthPassport
				// 请求的路径
				@RequestMapping("/getUser")
				public void ajaxGetBjh(HttpServletRequest request,
						HttpServletResponse resp) throws IOException {
					
					resp.setContentType("text/html;charset=utf-8");	
					//得到用户
					CbhsUser userSession = (CbhsUser) request.getSession().getAttribute("sessionUser");
					
					
					
					CbhsUser uL=null;
					
					try {
						//根据当前用户查找
						uL =  userService.selectByPrimaryKey(userSession.getUserId());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					//如何用户不存在返回0
					if(uL == null){
						
							resp.getWriter().write("0");
							return;
						
					}
					
					//用户存在返回用户json
					String json = JSON.toJSONString(uL);
					resp.getWriter().write(json);

				}
		
				// 自定义权限验证注解加注解时表示有权限是才能访问
				@AuthPassport
				// 修改密码
				@RequestMapping("/updateUser")
				public String bjhUpdate(CbhsUser formUser, String passwordNew1, String passwordNew2, HttpServletRequest request,
						HttpServletResponse resp) throws IOException {
					// @PathVariable
					// String
					// id(路径变量如@RequestMapping("/{id}/showUser"))
					/*
					 * 
					 * 2. 校验表单数据 3. 使用service查询，得到User 4. 查看用户是否存在，如果不存在： * 保存错误信息：用户名或密码错误
					 * * 保存用户数据：为了回显 * 转发到login.jsp 5. 如果存在，查看状态，如果状态为false： * 保存错误信息：您没有激活
					 * * 保存表单数据：为了回显 * 转发到login.jsp 6. 登录成功： 　　* 保存当前查询出的user到session中 *
					 * 保存当前用户的名称到cookie中，注意中文需要编码处理。
					 */
					/*
					 * 2. 校验
					 */

					/*
					 * 3. 调用userService查询方法
					 */
					if(formUser == null)return null;
					resp.setContentType("text/html;charset=utf-8");
					//两次输入密码不一致
					if(!passwordNew1.equals(passwordNew2)){
						resp.getWriter().write("-1");
						return null;
					}
					
					CbhsUser uL=null;
					
						try {
							//根据表单查找
							uL = userService.selectByPrimaryKey(formUser.getUserId());
						} catch (Exception e) {
							
							e.printStackTrace();
						}
					
					
					//如何用户不存在返回0
					if(uL == null){
						
							resp.getWriter().write("0");
							return null;
						
					}else
					//如果用户存在修改密码返回1
					{
						formUser.setPassword(passwordNew1);
						try {
							userService.updateByPrimaryKeySelective(formUser);
							uL = userService.selectByPrimaryKey(formUser.getUserId());
						} catch (Exception e) {
							
							e.printStackTrace();
						}
						
						//保存用户到session中
						request.getSession().setAttribute("sessionUser", uL);
						// 获取用户名保存到cookie中
						String loginname = uL.getUserName();
						loginname = URLEncoder.encode(loginname, "utf-8");
						Cookie cookie = new Cookie("loginname", loginname);
						cookie.setMaxAge(60 * 60 * 24 * 30);//保存30天
						resp.addCookie(cookie);
						resp.getWriter().write('1');
						
						
					}
					

					return null;

				}
		
				
				
				
				

				//登陆控制器
				@RequestMapping("/login1")
				public String showUser1(CbhsUser formUserdmh, 
						HttpServletRequest request, 
						HttpServletResponse resp) 
						throws IOException {//@PathVariable String id(路径变量如@RequestMapping("/{id}/showUser"))
					//解密后赋值
					System.out.println(formUserdmh.getPassword());
					formUserdmh.setPassword(new String(Base64.decodeBase64(formUserdmh.getPassword())));
					formUserdmh.setUserName(new String(Base64.decodeBase64(formUserdmh.getUserName())));
					
//					----------------------------------------------------------------------------------
//					Cookie[] cookies = request.getCookies();//这样便可以获取一个cookie数组
//					for(Cookie cookie : cookies){
//					    System.out.println(cookie.getName());// get the cookie name
//					    System.out.println(cookie.getValue());; // get the cookie value
//					}
					
					
					/*
					 * 
					 * 2. 校验表单数据
					 * 3. 使用service查询，得到User
					 * 4. 查看用户是否存在，如果不存在：
					 *   * 保存错误信息：用户名或密码错误
					 *   * 保存用户数据：为了回显
					 *   * 转发到login.jsp
					 * 5. 如果存在，查看状态，如果状态为false：
					 *   * 保存错误信息：您没有激活
					 *   * 保存表单数据：为了回显
					 *   * 转发到login.jsp
					 * 6. 登录成功：
					 * 　　* 保存当前查询出的user到session中
					 *   * 保存当前用户的名称到cookie中，注意中文需要编码处理。
					 */
					/*
					 * 2. 校验
					 */
					Map<String,String> errors = validateLogin(formUserdmh);
					if(errors.size() > 0) {
						request.setAttribute("form", formUserdmh);
						request.setAttribute("errors", errors);
						//跳转到login.jsp页面
						return "login";
					}
					
					/*
					 * 3. 调用userService查询方法
					 */
					List<CbhsUser> uL=null;
					try {
						
						CbhsUserExample example = new CbhsUserExample();
						Criteria criteria = example.createCriteria();
						criteria.andUserNameEqualTo(formUserdmh.getUserName());
						criteria.andPasswordEqualTo(formUserdmh.getPassword());
						
						
						uL = userService.selectByExample(example);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					if(uL.size()<1){
						request.setAttribute("msg", "用户名或密码错误！");
						request.setAttribute("user", formUserdmh);
						return "login";
					}
					//保存用户到session中
					request.getSession().setAttribute("sessionUser", uL.get(0));
					// 获取用户名保存到cookie中
					String loginname = uL.get(0).getUserName();
					loginname = URLEncoder.encode(loginname, "utf-8");
					Cookie cookie = new Cookie("loginname", loginname);
					cookie.setMaxAge(60 * 60 * 24 * 30);//保存30天
					resp.addCookie(cookie);
					//request.
					
					
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
