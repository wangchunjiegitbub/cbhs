package com.sc.dmh.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.ParseException;
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

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
import com.sc.dmh.beans.HsfSzmx;
import com.sc.dmh.beans.TabDepartment;
import com.sc.dmh.beans.TabHsfCg;
import com.sc.dmh.beans.TabHsfCgExample;
import com.sc.dmh.beans.TabHsfJiecun;
import com.sc.dmh.beans.TabHsfSh;
import com.sc.dmh.beans.TabHsfShExample;
import com.sc.dmh.beans.ViewCg;
import com.sc.dmh.beans.ViewCgExample;
import com.sc.dmh.beans.ViewCgExample.Criteria;
import com.sc.dmh.service.inter.DepartmentServiceI;
import com.sc.dmh.service.inter.HsfCgServiceI;
import com.sc.dmh.service.inter.HsfJcServiceI;
import com.sc.dmh.service.inter.HsfShServiceI;
import com.sc.dmh.service.inter.UserServiceI;
import com.sc.dmh.service.inter.ViewCgServiceI;
import com.sc.dmh.util.DateStringUtil;
import com.sc.dmh.util.ExcelUtil;
import com.sc.dmh.util.MyBeanUtils;
import com.sc.dmh.util.NumberToCN;
import com.sc.dmh.util.TreeHelper;



@Controller
@RequestMapping("/hsf")
public class GroupSumController {

	
	
	@Autowired
	private UserServiceI userService;
	
	@Autowired
	private HsfCgServiceI hsfCgService;
	
	@Autowired
	private ViewCgServiceI viewCgService;
	
	@Autowired
	private HsfJcServiceI hsfJcServiceI;
	
	@Autowired
	private DepartmentServiceI depServiceI;
	
	@Autowired
	private HsfShServiceI ShServiceI;
	
	

	public void setShServiceI(HsfShServiceI shServiceI) {
		ShServiceI = shServiceI;
	}

	public void setDepServiceI(DepartmentServiceI depServiceI) {
		this.depServiceI = depServiceI;
	}

	public void setHsfJcServiceI(HsfJcServiceI hsfJcServiceI) {
		this.hsfJcServiceI = hsfJcServiceI;
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
	@RequestMapping("/groupSum")
	public String showKpyl( 
			HttpServletRequest request, 
			HttpServletResponse resp) 
			throws IOException {
			//返回的视图
		
			return "hsf/groupSum";
		}
	
	
//			
						// 自定义权限验证注解加注解时表示有权限是才能访问
						@AuthPassport
						// 获取所有
						@RequestMapping("/getGroupSumAll")
						public String getGroupSumAll(Integer depId, ViewCg formViewCg, Date beginDate, HttpServletRequest request,
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
						
							
							//设置查询条件添加车站id为登录用户部门id
							
							
							if(depId==null){
								depId =userSession.getBumenId();
								
							}
							
							//根据部门id获取最后结存记录
							List<TabHsfJiecun> hsfJc = hsfJcServiceI.selectLastByDepId((long) depId);
							//设置开始日期为结存记录日期
							if(beginDate == null && hsfJc.get(0).getJiecunMonth() != null) beginDate = DateStringUtil.addDateOneDay(hsfJc.get(0).getJiecunMonth());
							
							
								try {
									//DateMonthFirstLast a = DateStringUtil.getDateFirstLast(formRpfFp.getInDate());
									
									ViewCgExample example = new ViewCgExample();
									Criteria cri = example.createCriteria();
									
									//设置查询条件添加车站id为登录用户部门id
									cri.andCgDepidEqualTo(depId);
									//设置查询条件添加日期条件
									
									cri.andCgDateBetween(beginDate, formViewCg.getCgDate());
									
									//设置查询商户id
									if(formViewCg.getCgShid() != null)cri.andCgShidEqualTo(formViewCg.getCgShid());
//									
									//条件查询
									List<ViewCg> cgAll = viewCgService.selectSumByExample(example);
									
									//查询结果大于0
									if(cgAll.size()>0){
										
										//采购合计
										ViewCg cgSum = new ViewCg();
										//计算合计
										for(ViewCg cg : cgAll){
											cgSum.setCgMoney(cg.getCgMoney().add(cgSum.getCgMoney()));
										}
										cgSum.setScName("合计");
										cgAll.add(cgSum);
										
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
						@RequestMapping("/downloadGroupSum")    
					    public ResponseEntity<byte[]> download(Integer depId, ViewCg formViewCg, Date beginDate, HttpServletRequest request,
								HttpServletResponse resp) throws IOException {   
					    	
					    	
					    	
//					    	resp.setContentType("text/html;charset=utf-8");
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
							
							if(depId==null){
								depId =userSession.getBumenId();
								
							}
						
							//根据部门id获取最后结存记录
							List<TabHsfJiecun> hsfJc = hsfJcServiceI.selectLastByDepId((long) depId);
							//如果记录为0退出
//							if(hsfJc.isEmpty()) return null;
							//设置开始日期为结存记录日期
							if(beginDate == null && hsfJc.get(0).getJiecunMonth() != null) beginDate = DateStringUtil.addDateOneDay(hsfJc.get(0).getJiecunMonth());
							
							
							//按照部门查询商户
							TabHsfShExample exampleSh = new TabHsfShExample();
							com.sc.dmh.beans.TabHsfShExample.Criteria cSh = exampleSh.createCriteria();
							cSh.andShDepidEqualTo( depId);
							
							List<TabHsfSh> shList = ShServiceI.selectByExample(exampleSh);
							
							
							
								try {
									ExcelUtil excel = new ExcelUtil();
									File file = new File("d:/data2.xls");
									
									String tempFilePath = "D:/wjzfzmd.xls";
									OutputStream os = new FileOutputStream(file);
							         
								  
								    //部门名称
							        TabDepartment dep = depServiceI.selectByPrimaryKey((long)depId);
									
									//DateMonthFirstLast a = DateStringUtil.getDateFirstLast(formRpfFp.getInDate());
									//有数据商户统计
							        int j = 0;
							        for(int i = 0;i<shList.size();i++){
										
									
												ViewCgExample example = new ViewCgExample();
												Criteria cri = example.createCriteria();
												
												//设置查询条件添加车站id为登录用户部门id
												cri.andCgDepidEqualTo(depId);
												//设置查询条件添加日期条件
												
												cri.andCgDateBetween(beginDate, formViewCg.getCgDate());
												
												//设置查询商户id
												cri.andCgShidEqualTo(shList.get(i).getShId());
			//									
												//条件查询
												List<ViewCg> cgAll = viewCgService.selectSumByExample(example);
												//无结果跳出本次循环
												if(cgAll.size()<1) continue;
			//									
											        
											       
											        Map<String, Object> dataMap = new HashMap<String, Object>();
											        
											        
						//					        //合计添加后移除合计数
						//					        hsfSzmxList.remove(hsfSzmxList.size()-1);
											        List<Map<Integer, Object>> datalist = new ArrayList<Map<Integer,Object>>();
											        Map<Integer, Object> data = new HashMap<Integer,Object>();
											        BigDecimal sum=new BigDecimal("0");
											        sum.setScale(2);
											        for(ViewCg cg : cgAll){
											        	data = new HashMap<Integer,Object>();
											        	//得到日期字符串2015-02-03
											        	data.put(1, cg.getScName());
												        data.put(4, cg.getCgSl().toString());
												        data.put(5, cg.getDj());
												        
												        data.put(6, cg.getCgMoney().toString());
												       
												        //data.put(3, szmx.getBeizhu());
												        datalist.add(data);
												        //计算商户合计数
												        sum = sum.add(cg.getCgMoney());
											        }
											        //部门名称b2改b3
											        dataMap.put("B3", dep.getDepartmentName());
													//商户合计金额 "（小写）¥" + 
											        dataMap.put("H26",sum.toString());
													//商户合计汉字C24改c26
											        dataMap.put("C26", NumberToCN.number2CNMontrayUnit(sum));
													
											        //					       
													excel.writeData(tempFilePath, dataMap, j);
											        
													excel.writeSheetName(tempFilePath, shList.get(i).getShName(), j);
											        
											       
											        String[] heads = new String[]{"A4","B4","E4","F4","G4","H4"};   //必须为列表头部所有位置集合，   输出 数据单元格样式和头部单元格样式保持一致
											        excel.writeDateList(tempFilePath,heads,datalist,j);
											        //有数据商户记录加一
											        j++;	 
											        
											        
										
									
							}
									//写到输出流并移除资源
							        excel.writeAndClose(tempFilePath, os);
							          
							        os.flush();
									os.close();
					    	
					    	
					        String path="D:\\data2.xls";  
					        File file1=new File(path);  
					        HttpHeaders headers = new HttpHeaders();    
					        //文件名部门名称+时间+xls
					        String fileName=new String((dep.getDepartmentName() + DateStringUtil.getDateMMdd(new Date()) + ".xls").getBytes("UTF-8"),"iso-8859-1");//为了解决中文名称乱码问题  
					        headers.setContentDispositionFormData("attachment", fileName);   
					        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);   
					        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),    
					                                          headers, HttpStatus.CREATED);    
					    
								
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
