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

import org.apache.log4j.Logger;
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
import com.sc.dmh.beans.TabHsfJiecun;
import com.sc.dmh.beans.TabHsfPerson;
import com.sc.dmh.beans.TabHsfPersonExample;
import com.sc.dmh.beans.TabHsfShouru;
import com.sc.dmh.beans.TabHsfShouruExample;
import com.sc.dmh.beans.ViewCg;
import com.sc.dmh.beans.ViewShouru;
import com.sc.dmh.beans.ViewShouruExample;
import com.sc.dmh.beans.ViewShouruExample.Criteria;
import com.sc.dmh.service.inter.HsfJcServiceI;
import com.sc.dmh.service.inter.HsfShouruServiceI;

import com.sc.dmh.service.inter.UserServiceI;
import com.sc.dmh.service.inter.ViewShouruServiceI;
import com.sc.dmh.util.DateStringUtil;
import com.sc.dmh.util.MyBeanUtils;
import com.sc.dmh.util.TreeHelper;



@Controller
@RequestMapping("/hsf")
public class SrController {

	private static final Logger logger = Logger.getLogger(SrController.class);
	
	@Autowired
	private UserServiceI userService;
	
	@Autowired
	private HsfShouruServiceI hsfSrService;
	
	@Autowired
	private ViewShouruServiceI viewSrService;
	
	@Autowired
	private HsfJcServiceI hsfJcServiceI;
	
	

	public void setHsfJcServiceI(HsfJcServiceI hsfJcServiceI) {
		this.hsfJcServiceI = hsfJcServiceI;
	}

	public void setViewShouruService(ViewShouruServiceI viewSrService) {
		this.viewSrService = viewSrService;
	}

	public void setHsfShouruService(HsfShouruServiceI hsfSrService) {
		this.hsfSrService = hsfSrService;
	}

	public void setUserService(UserServiceI userService) {
		this.userService = userService;
	}

	
	
	// 自定义权限验证注解加注解时表示有权限是才能访问
			@AuthPassport
			// 审核人员变化情况
			@RequestMapping("/shSrEnter")
			public String shSrEnter(String ids, HttpServletRequest request,
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
					TabHsfShouruExample example = new TabHsfShouruExample();
					com.sc.dmh.beans.TabHsfShouruExample.Criteria criteria = example.createCriteria();
					
					//设置查询条件添加日期条件
					criteria.andShouruIdIn(idList);
					TabHsfShouru record = new TabHsfShouru();
					record.setShouruState(1);
					//								
					 delNumb = hsfSrService.updateByExampleSelective(record, example);
					
				} catch (Exception e) {
					
					e.printStackTrace();
				}
				
				resp.getWriter().write( delNumb + "");

					
				

				return null;

			}
			
	
	
	
	//自定义权限验证注解加注解时表示有权限是才能访问
	@AuthPassport
	//请求的路径
	@RequestMapping("/sr")
	public String showKpyl( 
			HttpServletRequest request, 
			HttpServletResponse resp) 
			throws IOException {
			//返回的视图
		
			return "hsf/sr";
		}
	
	
	//自定义权限验证注解加注解时表示有权限是才能访问
		@AuthPassport
		//请求的路径
		@RequestMapping("/shSr")
		public String shSr( 
				HttpServletRequest request, 
				HttpServletResponse resp) 
				throws IOException {
				//返回的视图
			
				return "hsf/shSr";
			}
	
			// 自定义权限验证注解加注解时表示有权限是才能访问
			@AuthPassport
			// 添加客票管理中的客票用量中的粉票
			@RequestMapping("/addSr")
			public String fpAdd(TabHsfShouru formHsfShouru, HttpServletRequest request,
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
					formHsfShouru.setUserId(userSession.getUserId());
					//设置部门
					formHsfShouru.setDepId(userSession.getBumenId());
				}
			
				
				
				List<TabHsfJiecun> jcList = hsfJcServiceI.selectLastByDepId(Long.valueOf(userSession.getBumenId().toString()));
				
				//结存不为空并且-结账时间不在人员添加日期之前
				if( !jcList.isEmpty() && !jcList.get(0).getJiecunMonth().before(formHsfShouru.getShouruDate())){
					
					//返回-1代表已经结账不能修改人员信息，必须修改时先删除结账信息再添加人员添加完成后再次结账。
					resp.getWriter().write("-1");
					return null;
				}
				
				
				
				
				
				
					try {
						
						//添加粉票记录
						//int i = rpfService.insertSelective(formRpfFp);
						
						int i  = hsfSrService.insertSelective(formHsfShouru);
						
						resp.getWriter().write(i + "");
						
					} catch (Exception e) {
						
						e.printStackTrace();
					} 
				
					
					
					
					
					return null;
				} 
						
			
						// 自定义权限验证注解加注解时表示有权限是才能访问
						@AuthPassport
						// 获取所有
						@RequestMapping("/getSrAll")
						public String getSrAll(Integer depId, Date beginDate, Date endDate, HttpServletRequest request,
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
									
									ViewShouruExample example = new ViewShouruExample();
									Criteria cri = example.createCriteria();
									
									
									//设置查询条件添加车站id为登录用户部门id
									
									if(depId==null){
										depId=userSession.getBumenId();
								    }
									if(depId==58 || depId==59){
										//id57取全部 56财务科
									}else{
										//设置查询条件添加车站id为登录用户部门id
										cri.andDepIdEqualTo(depId);
										
									}
									
									
									
//									if(depId==null){
//										cri.andDepIdEqualTo(userSession.getBumenId());
//									}else{
//										cri.andDepIdEqualTo(depId);
//									}
									//设置查询条件添加日期条件
									
									//cri.andShouruDateEqualTo(formViewShouru.getShouruDate());
									cri.andShouruDateBetween(beginDate, endDate);
									//查询当月当前车站售票记录
									List<ViewShouru> srAll = viewSrService.selectByExample(example);
									
									ViewShouru sumSr = new ViewShouru();
									
									if(srAll.size()>0){
										
										for(ViewShouru sr : srAll){
											sumSr.setShouruMoney(sumSr.getShouruMoney().add(sr.getShouruMoney()));
										}
										sumSr.setSrtype("合计");
										srAll.add(sumSr);
									}
									
									
//									//复制软票费列表到页面列表
//									if(rpfAll.size()>0){
//										
//										for(TabHsfShouru t : rpfAll){
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
									String json = "{\"total\":" + srAll.size() + " , \"rows\":"
											+ JSON.toJSONStringWithDateFormat(srAll, "yyyy-MM-dd") + "}";
									
									
									resp.getWriter().write(json);
									
									
								} catch (Exception e) {
									
									e.printStackTrace();
								} 
							
								
								
								
								
								return null;
							} 

			
	
						// 自定义权限验证注解加注解时表示有权限是才能访问
						@AuthPassport
						// 修改班计划
						@RequestMapping("/deleteSr")
						public String dxcDelete(String ids, HttpServletRequest request,
								HttpServletResponse resp) throws IOException {
							
							//统一设置编码
							resp.setContentType("text/html;charset=utf-8");
							CbhsUser userSession = (CbhsUser) request.getSession().getAttribute("sessionUser");
							
							
							if(ids == null) return "";
							
							//转换参数为list
							String[] idsArr = ids.split(",");
							
							Integer [] num=new Integer[idsArr.length];
							
							for(int i=0;i<num.length;i++){
						            num[i]=Integer.parseInt(idsArr[i]);
						    }
							
							
							List<Integer> idList = java.util.Arrays.asList(num);
							
							
							
							TabHsfShouruExample example = new TabHsfShouruExample();
							com.sc.dmh.beans.TabHsfShouruExample.Criteria criteria = example.createCriteria();
							
							//设置查询条件添加日期条件
							criteria.andShouruIdIn(idList);
							
							List<TabHsfShouru> hsfSrList = hsfSrService.selectByExample(example);
							
							//获取结账最后记录
							List<TabHsfJiecun> jcList = hsfJcServiceI.selectLastByDepId(Long.valueOf(userSession.getBumenId().toString()));
							
							
							//结存不为空并且-删除的人员列表不为空
							if( !jcList.isEmpty() && !hsfSrList.isEmpty()){
								
								
								for(TabHsfShouru sr : hsfSrList){
									//结账时间不在删除人员日期之前
									if(!jcList.get(0).getJiecunMonth().before(sr.getShouruDate())){
										//返回-1代表已经结账不能修改人员信息，必须修改时先删除结账信息再添加人员添加完成后再次结账。
										logger.debug(sr.getShouruDate());
										resp.getWriter().write("-1");
										return null;
									}
								}
								
								
								
							}
							
							
							
							
							
							
							int delNumb = 0;
							try {
								
//								
								 delNumb = hsfSrService.deleteByExample(example);
								
							} catch (Exception e) {
								
								e.printStackTrace();
							}
							
							resp.getWriter().write( delNumb + "");

								
							

							return null;

						}
						
						// 自定义权限验证注解加注解时表示有权限是才能访问
						@AuthPassport
						// 添加客票管理中的客票用量中的粉票
						@RequestMapping("/getSr")
						public String getSr(TabHsfShouru formHsfShouru, HttpServletRequest request,
								HttpServletResponse resp) throws IOException {
							
							resp.setContentType("text/html;charset=utf-8");
							
						if(formHsfShouru == null)return null;
							
							
								try {
									
									
									
									TabHsfShouru i  = hsfSrService.selectByPrimaryKey((long)formHsfShouru.getShouruId());
									
									resp.getWriter().write(JSON.toJSONStringWithDateFormat(i, "yyyy-MM-dd"));
									
								} catch (Exception e) {
									
									e.printStackTrace();
								} 
							
								
								
								
								
								return null;
							} 		
						
						
						// 自定义权限验证注解加注解时表示有权限是才能访问
						@AuthPassport
						// 添加客票管理中的客票用量中的粉票
						@RequestMapping("/updateSr")
						public String updateSr(TabHsfShouru formHsfShouru, HttpServletRequest request,
								HttpServletResponse resp) throws IOException {
							
							resp.setContentType("text/html;charset=utf-8");
							
						
							if(formHsfShouru == null)return null;
							
								try {
									
									
									
									int i  = hsfSrService.updateByPrimaryKeySelective(formHsfShouru);
									
									resp.getWriter().write(JSON.toJSONStringWithDateFormat(i, "yyyy-MM-dd"));
									
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
