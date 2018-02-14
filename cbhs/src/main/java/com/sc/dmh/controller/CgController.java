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
import com.sc.dmh.beans.DateMonthFirstLast;


import com.sc.dmh.beans.TabHsfCg;
import com.sc.dmh.beans.TabHsfCgExample;

import com.sc.dmh.beans.ViewCg;
import com.sc.dmh.beans.ViewCgExample;
import com.sc.dmh.beans.ViewCgExample.Criteria;
import com.sc.dmh.service.inter.HsfCgServiceI;
import com.sc.dmh.service.inter.HsfJcServiceI;
import com.sc.dmh.service.inter.UserServiceI;
import com.sc.dmh.service.inter.ViewCgServiceI;
import com.sc.dmh.util.DateStringUtil;
import com.sc.dmh.util.MyBeanUtils;
import com.sc.dmh.util.TreeHelper;



@Controller
@RequestMapping("/hsf")
public class CgController {

	
	
	@Autowired
	private UserServiceI userService;
	
	@Autowired
	private HsfCgServiceI hsfCgService;
	
	@Autowired
	private ViewCgServiceI viewCgService;
	
	@Autowired
	private HsfCgServiceI cgService;
	
	@Autowired
	private HsfJcServiceI hsfJcServiceI;

	
	
	public void setHsfJcServiceI(HsfJcServiceI hsfJcServiceI) {
		this.hsfJcServiceI = hsfJcServiceI;
	}

	public void setCgService(HsfCgServiceI cgService) {
		this.cgService = cgService;
	}

	public void setViewCgService(ViewCgServiceI viewCgService) {
		this.viewCgService = viewCgService;
	}

	public void setHsfCgService(HsfCgServiceI hsfCgService) {
		this.hsfCgService = hsfCgService;
	}

	public void setUserService(UserServiceI userService) {
		this.userService = userService;
	}

	//自定义权限验证注解加注解时表示有权限是才能访问
	@AuthPassport
	//请求的路径
	@RequestMapping("/cg")
	public String showKpyl( 
			HttpServletRequest request, 
			HttpServletResponse resp) 
			throws IOException {
			//返回的视图
		
			return "hsf/cg";
		}
	
	
			// 自定义权限验证注解加注解时表示有权限是才能访问
			@AuthPassport
			// 添加客票管理中的客票用量中的粉票
			@RequestMapping("/addCg")
			public String fpAdd(TabHsfCg formHsfCg, HttpServletRequest request,
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
				resp.setContentType("text/html;charset=utf-8");
				//创建点名会对象
				//TabRpf formRpfFp = new TabRpf();
				//得到用户
				CbhsUser userSession = (CbhsUser) request.getSession().getAttribute("sessionUser");
				//
				if(userSession != null ){
					//设置用户	
					formHsfCg.setCgUserid(userSession.getUserId());
					//设置部门
					formHsfCg.setCgDepid(userSession.getBumenId());
				}
			
				
				
					try {
						
						//添加粉票记录
						//int i = rpfService.insertSelective(formRpfFp);
						
						int i  = hsfCgService.insertSelective(formHsfCg);
						
						resp.getWriter().write(i + "");
						
					} catch (Exception e) {
						
						e.printStackTrace();
					} 
				
					
					
					
					
					return null;
				} 
						
			
						// 自定义权限验证注解加注解时表示有权限是才能访问
						@AuthPassport
						// 获取所有
						@RequestMapping("/getCgAll")
						public String getCgAll(Date beginDate,Date endDate,Integer depId, HttpServletRequest request,
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
							resp.setContentType("text/html;charset=utf-8");
							//创建点名会对象
							//TabRpf formRpfFp = new TabRpf();
						    //得到用户
							CbhsUser userSession = (CbhsUser) request.getSession().getAttribute("sessionUser");
							//
//							if(userSession != null ){
//								//获取用户部门设置到车站id		
//								formRpfFp.setCzId(userSession.getBumenId());
//								
//							}
						
							
							
								try {
									//DateMonthFirstLast a = DateStringUtil.getDateFirstLast(formRpfFp.getInDate());
									
									ViewCgExample example = new ViewCgExample();
									Criteria cri = example.createCriteria();
									
//									System.out.println(depId);
									
									//设置查询条件添加车站id为登录用户部门id
									if(depId==null){
										cri.andCgDepidEqualTo(userSession.getBumenId());
									}else{
										cri.andCgDepidEqualTo(depId);
									}
									
									
									//设置查询条件添加日期条件
									
									cri.andCgDateBetween(beginDate, endDate);
//									
									//查询
									List<ViewCg> cgAll = viewCgService.selectByExample(example);
									
									ViewCg sumCg = new ViewCg();
									
									if(cgAll.size()>0){
										
										for(ViewCg cg : cgAll){
											sumCg.setCgMoney(sumCg.getCgMoney().add(cg.getCgMoney()));
										}
										sumCg.setScName("合计");
										cgAll.add(sumCg);
									}

									
									
									
									// {"total":10 , "rows":[{},{}]}
									String json = "{\"total\":" + cgAll.size() + " , \"rows\":"
											+ JSON.toJSONStringWithDateFormat(cgAll, "yyyy-MM-dd") + "}";
									
									
									resp.getWriter().write(json);
									
									
								} catch (Exception e) {
									
									e.printStackTrace();
								} 
							
								
								
								
								
								return null;
							} 

			
	
						// 自定义权限验证注解加注解时表示有权限是才能访问
						@AuthPassport
						// 修改班计划
						@RequestMapping("/deleteCg")
						public String deleteCg(String ids, HttpServletRequest request,
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
								TabHsfCgExample example = new TabHsfCgExample();
								com.sc.dmh.beans.TabHsfCgExample.Criteria criteria = example.createCriteria();
								
								//设置查询条件添加日期条件
								criteria.andCgIdIn(idList);
//								
								 delNumb = hsfCgService.deleteByExample(example);
								
							} catch (Exception e) {
								
								e.printStackTrace();
							}
							
							resp.getWriter().write( delNumb + "");

								
							

							return null;

						}
				
						
						
						
						// 自定义权限验证注解加注解时表示有权限是才能访问
						@AuthPassport
						// 获取所有
						@RequestMapping("/getCg")
						public String getCg(Integer formCgId, HttpServletRequest request,
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
							resp.setContentType("text/html;charset=utf-8");
							//创建点名会对象
							//TabRpf formRpfFp = new TabRpf();
						    //得到用户
							CbhsUser userSession = (CbhsUser) request.getSession().getAttribute("sessionUser");
							//
//							if(userSession != null ){
//								//获取用户部门设置到车站id		
//								formRpfFp.setCzId(userSession.getBumenId());
//								
//							}
						
							
							
								try {
									//DateMonthFirstLast a = DateStringUtil.getDateFirstLast(formRpfFp.getInDate());
//									
//									ViewCgExample example = new ViewCgExample();
//									Criteria cri = example.createCriteria();
//									
//									//设置查询条件添加车站id为登录用户部门id
//									cri.andCgDepidEqualTo(userSession.getBumenId());
//									//设置查询条件添加日期条件
//									
//									cri.andCgDateBetween(beginDate, endDate);
//									
									//查询当月当前车站售票记录
									TabHsfCg cg = cgService.selectByPrimaryKey((long)formCgId);
									
									
									
									
//									//复制软票费列表到页面列表
//									if(rpfAll.size()>0){
//										
//										for(TabHsfCg t : rpfAll){
//											if(t.getKemuParentid() == 999999999 ){
//												list.add(TreeHelper.getComboTree(t.getKemuId().toString(),null, t.getKemuName()));
//											}else{
//												list.add(TreeHelper.getComboTree(t.getKemuId().toString(),t.getKemuParentid().toString(), t.getKemuName()));
//											}
//										}
//										
//									}
//									
//									
//									List<ComboTree> l= TreeHelper.makeTree(list);
//									
//									
//									
									
									
									
									// {"total":10 , "rows":[{},{}]}
									String json =  JSON.toJSONStringWithDateFormat(cg, "yyyy-MM-dd");
									
									
									resp.getWriter().write(json);
									
									
								} catch (Exception e) {
									
									e.printStackTrace();
								} 
							
								
								
								
								
								return null;
							} 		
						
						
						
						// 自定义权限验证注解加注解时表示有权限是才能访问
						@AuthPassport
						// 添加客票管理中的客票用量中的粉票
						@RequestMapping("/updateCg")
						public String updateCg(TabHsfCg formHsfCg, HttpServletRequest request,
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
							resp.setContentType("text/html;charset=utf-8");
							//创建点名会对象
							//TabRpf formRpfFp = new TabRpf();
							//得到用户
//							CbhsUser userSession = (CbhsUser) request.getSession().getAttribute("sessionUser");
//							//
//							if(userSession != null ){
//								//设置用户	
//								formHsfCg.setCgUserid(userSession.getUserId());
//								//设置部门
//								formHsfCg.setCgDepid(userSession.getBumenId());
//							}
						
							
							
								try {
									
									//添加粉票记录
									//int i = rpfService.insertSelective(formRpfFp);
									
									int i  = hsfCgService.updateByPrimaryKeySelective(formHsfCg);
									
									resp.getWriter().write(i + "");
									
								} catch (Exception e) {
									
									e.printStackTrace();
								} 
							
								
								
								
								
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
