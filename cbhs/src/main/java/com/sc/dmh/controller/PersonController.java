package com.sc.dmh.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;







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
import com.sc.dmh.beans.ComboTree;
import com.sc.dmh.beans.TabHsfCg;
import com.sc.dmh.beans.TabHsfCgExample;
import com.sc.dmh.beans.TabHsfPerson;
import com.sc.dmh.beans.TabHsfPersonExample;
import com.sc.dmh.beans.ViewPerson;
import com.sc.dmh.beans.ViewPersonExample;
import com.sc.dmh.beans.ViewPersonExample.Criteria;
import com.sc.dmh.service.inter.HsfPersonServiceI;

import com.sc.dmh.service.inter.UserServiceI;
import com.sc.dmh.service.inter.ViewPersonServiceI;




@Controller
@RequestMapping("/hsf")
public class PersonController {

	
	
	@Autowired
	private UserServiceI userService;
	
	@Autowired
	private HsfPersonServiceI hsfPersonService;
	
	@Autowired
	private ViewPersonServiceI viewPersonService;
	
	

	public void setViewPersonService(ViewPersonServiceI viewPersonService) {
		this.viewPersonService = viewPersonService;
	}

	public void setHsfPersonService(HsfPersonServiceI hsfPersonService) {
		this.hsfPersonService = hsfPersonService;
	}

	public void setUserService(UserServiceI userService) {
		this.userService = userService;
	}

	//自定义权限验证注解加注解时表示有权限是才能访问
	@AuthPassport
	//请求的路径
	@RequestMapping("/person")
	public String showKpyl( 
			HttpServletRequest request, 
			HttpServletResponse resp) 
			throws IOException {
			//返回的视图
		
			return "hsf/person";
		}
	
	
	//自定义权限验证注解加注解时表示有权限是才能访问
		@AuthPassport
		//请求的路径
		@RequestMapping("/shPerson")
		public String shPerson( 
				HttpServletRequest request, 
				HttpServletResponse resp) 
				throws IOException {
				//返回的视图
			
				return "hsf/shPerson";
			}
	
	
		
		// 自定义权限验证注解加注解时表示有权限是才能访问
		@AuthPassport
		// 审核人员变化情况
		@RequestMapping("/shPersonEnter")
		public String shPersonEnter(String ids, HttpServletRequest request,
				HttpServletResponse resp) throws IOException {
			
			//统一设置编码
			resp.setContentType("text/html;charset=utf-8");
			if(ids == null) return "";
			
			//转换参数为list
			String[] idsArr = ids.split(",");
			
			Integer [] num=new Integer[idsArr.length];
			
			for(int i=0;i<num.length;i++){
		            num[i]=Integer.parseInt(idsArr[i]);
		    }
			
			
			List<Integer> idList = java.util.Arrays.asList(num);
			
			
			
			int delNumb = 0;
			try {
				TabHsfPersonExample example = new TabHsfPersonExample();
				com.sc.dmh.beans.TabHsfPersonExample.Criteria criteria = example.createCriteria();
				
				//设置查询条件添加日期条件
				criteria.andPersonIdIn(idList);
				TabHsfPerson record = new TabHsfPerson();
				record.setPersonState(1);
				//								
				 delNumb = hsfPersonService.updateByExampleSelective(record, example);
				
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			
			resp.getWriter().write( delNumb + "");

				
			

			return null;

		}
		
		
		
		
		
		
		
		
		
		
		
		
		
			// 自定义权限验证注解加注解时表示有权限是才能访问
			@AuthPassport
			// 添加客票管理中的客票用量中的粉票
			@RequestMapping("/addPerson")
			public String fpAdd(TabHsfPerson formPerson, HttpServletRequest request,
					HttpServletResponse resp) throws IOException {
				// @PathVariable
				// String
				// id(路径变量如@RequestMapping("/{id}/personowUser"))
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
				resp.setContentType("text/html;charset=utf-8");
				//创建点名会对象
				//TabRpf formRpfFp = new TabRpf();
				//得到用户
				CbhsUser userSession = (CbhsUser) request.getSession().getAttribute("sessionUser");
				//
				if(userSession != null ){
					//获取用户部门设置到车站id		
					formPerson.setDepId(userSession.getBumenId());
					//设置用户id
					formPerson.setUserId(userSession.getUserId());
					
					
				}
			
				
				
					try {
						
						//添加粉票记录
						int i = hsfPersonService.insertSelective(formPerson);
						
						resp.getWriter().write(i + "");
					} catch (Exception e) {
						
						e.printStackTrace();
					} 
				
					
					
					
					
					return null;
				} 
						
			
						// 自定义权限验证注解加注解时表示有权限是才能访问
						@AuthPassport
						// 获取所有
						@RequestMapping("/getPersonAll")
						public String getPersonAll(HttpServletRequest request,
								HttpServletResponse resp) throws IOException {
							// @PathVariable
							// String
							// id(路径变量如@RequestMapping("/{id}/personowUser"))
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
							resp.setContentType("text/html;charset=utf-8");
							//创建点名会对象
							//TabRpf formRpfFp = new TabRpf();
							//得到用户
							CbhsUser userSession = (CbhsUser) request.getSession().getAttribute("sessionUser");
							
							if(userSession == null ){
								//获取用户部门设置到车站id		
								return null;
								
							}
						
							
							
								try {
									//DateMonthFirstLast a = DateStringUtil.getDateFirstLast(formRpfFp.getInDate());
									
//									TabHsfPersonExample example = new TabHsfPersonExample();
//									Criteria criteria = example.createCriteria();
									
									//设置查询条件添加车站id为登录用户部门id
									//criteria.andPersonDepidEqualTo(userSession.getBumenId());
									//设置查询条件添加日期条件
									
									
//									
									//查询当月当前车站售票记录
//									List<TabHsfPerson> rpfAll = hsfPersonService.selectByExample(example);
									
									
									
									
//								
									
									
									
									// {"total":10 , "rows":[{},{}]}
//									String json = "{\"total\":" + rpfAll.size() + " , \"rows\":"
//											+ JSON.toJSONString(rpfAll) + "}";
									
									
//									resp.getWriter().write(JSON.toJSONString(rpfAll));
									
									
								} catch (Exception e) {
									
									e.printStackTrace();
								} 
							
								
								
								
								
								return null;
							} 
						
						// 自定义权限验证注解加注解时表示有权限是才能访问
						@AuthPassport
						// 获取所有
						@RequestMapping("/getPerson")
						public String getPerson(Date beginDate, Date endDate,Integer depId,  HttpServletRequest request,
								HttpServletResponse resp) throws IOException {
							// @PathVariable
							// String
							// id(路径变量如@RequestMapping("/{id}/personowUser"))
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
							resp.setContentType("text/html;charset=utf-8");
							//创建点名会对象
							//TabRpf formRpfFp = new TabRpf();
							//得到用户
							CbhsUser userSession = (CbhsUser) request.getSession().getAttribute("sessionUser");
							
							if(userSession == null ){
								//获取用户部门设置到车站id		
								return null;
								
							}
						
							
							
								try {
									//DateMonthFirstLast a = DateStringUtil.getDateFirstLast(formRpfFp.getInDate());
									
									ViewPersonExample example = new ViewPersonExample();
									  Criteria c = example.createCriteria();
									
																		
									    if(depId==null){
											depId=userSession.getBumenId();
									    }
										if(depId==58 || depId==59){
											//id57取全部 56财务科
										}else{
											//设置查询条件添加车站id为登录用户部门id
											c.andDepIdEqualTo(depId);
											
										}
									
									
									//设置查询条件添加日期条件
									
									c.andPersonTimeBetween(beginDate, endDate);
//									
									//查询当月当前车站售票记录
									List<ViewPerson> rpfAll = viewPersonService.selectByExample(example);
									
									
									
									
//								
									
									
									
//									// {"total":10 , "rows":[{},{}]}
									String json = "{\"total\":" + rpfAll.size() + " , \"rows\":"
											+ JSON.toJSONStringWithDateFormat(rpfAll, "yyyy-MM-dd") + "}";
//									
									
									resp.getWriter().write(json);
									
									
								} catch (Exception e) {
									
									e.printStackTrace();
								} 
							
								
								
								
								
								return null;
							} 

			
	
						// 自定义权限验证注解加注解时表示有权限是才能访问
						@AuthPassport
						// 修改班计划
						@RequestMapping("/deletePerson")
						public String dxcDelete(String ids, HttpServletRequest request,
								HttpServletResponse resp) throws IOException {
							
							//统一设置编码
							resp.setContentType("text/html;charset=utf-8");
							if(ids == null) return "";
							
							//转换参数为list
							String[] idsArr = ids.split(",");
							
							Integer [] num=new Integer[idsArr.length];
							
							for(int i=0;i<num.length;i++){
						            num[i]=Integer.parseInt(idsArr[i]);
						    }
							
							
							List<Integer> idList = java.util.Arrays.asList(num);
							
							
							
							int delNumb = 0;
							try {
								
								
								 TabHsfPersonExample example = new TabHsfPersonExample();
								 com.sc.dmh.beans.TabHsfPersonExample.Criteria c = example.createCriteria();
								 c.andPersonIdIn(idList);
								 
								delNumb = hsfPersonService.deleteByExample(example );
								
							} catch (Exception e) {
								
								e.printStackTrace();
							}
							
							resp.getWriter().write( delNumb + "");

								
							

							return null;

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
