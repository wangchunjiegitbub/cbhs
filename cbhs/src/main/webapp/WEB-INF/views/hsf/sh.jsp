<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<table id="sh"></table>

<div id="sh_tool" style="padding:5px;">
	<div style="margig-bottom:5px;">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onClick="sh_tool.add();">添加</a>
		
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onClick="sh_tool.edit();">修改</a>
		<!-- <a href="#" class="easyui-linkbutton" iconCls="icon-DeleteRed" plain="true" onClick="sh_tool.remove();">删除</a> -->
		<!-- <input id="shDate_sh" name="shDate_sh" type="text" class="easyui-datebox" required="required" value="true" data-options="
		onChange: function (n,o) {

			sh_tool.find();

		}"></input> -->
		<!-- <input type="radio"  onChange="sh_tool.find();" name="shTime_sh" checked="checked" value="1">白班
		<input type="radio" onChange="sh_tool.find();" name="infonumber_sh" value="15">15点
		<input type="radio"  onChange="sh_tool.find();" name="shTime_sh" value="0">夜班 -->
		<c:if test="${sessionScope.sessionUser.quanxian == 11 || sessionScope.sessionUser.quanxian == 12 }">
		
		
			<input type="text" id="depId_sh" name="depId_sh" class="easyui-combobox"  
	    					 data-options="valueField:'departmentId',
	    					 required:'true',
	    					 textField:'departmentName',
	    					 url:'/cbhs/hsf/getDepAllSelect',
	    					 filter: function(q, row){  
	            				var opts = $(this).combobox('options');  
	            				return row[opts.textField].indexOf(q) >-1;;  
	        				},
	        				onSelect:function(record) {  
	            				$('depId_sh').focus();  
	        				}"   
	    					 																									  
	    					 style="width:150px;height:20px;"/>
		</c:if>
		
		
		<a href="#" class="easyui-linkbutton" iconCls="icon-page-find" plain="true" onClick="sh_tool.find();">查询</a>
	</div>
</div>


<form id="sh_add" style="margin:0;padding:5p 0 0 25px;color:#333;"> 
		<table id="sh_add_table" style="border-collapse:separate;border-spacing:10px;display:none;" >
    					
    					<tr>
    						<td>商户名称</td>
    						<td><input type="text" name="sh_name_add" class="easyui-textbox"  data-options="required:'true',validType:'length[1,20]'"  style="width:150px;height:20px;"/></td>
    					</tr>
    					
    					<tr>
    						<td>身份证号</td>
    						<td><input type="text" name="sh_idnumber_add" class="easyui-textbox"  data-options="validType:'IdentityCodeValid[]'"  style="width:150px;height:20px;"/></td>
    					</tr>
    					
    					<!-- <tr>
    						<td>收据</td>
    						<td><input type="radio" name="sh_sj_add" value="1">有
								<input type="radio" name="sh_sj_add" value="0" checked="checked">无
								
							</td>
						</tr> -->
    					
 		</table>
</form> 



<!-- 删除edit表的日期组件的 value:'true'属性   -->
<form id="sh_edit" style="margin:0;padding:5p 0 0 25px;color:#333;"> 
    	<input type="hidden" name="shId_edit" class="textbox" />			
		<table id="sh_edit_table" style="border-collapse:separate;border-spacing:10px;display:none;" >
    					<tr>
    						<td>商户名称</td>
    						<td><input type="text" name="sh_name_edit" class="easyui-textbox"  data-options="required:'true',disabled:'true',validType:'length[1,20]'"  style="width:150px;height:20px;"/></td>
    					</tr>
    					
    					<tr>
    						<td>身份证号</td>
    						<td><input type="text" name="sh_idnumber_edit" class="easyui-textbox"  data-options="disabled:'true',validType:'IdentityCodeValid[]'"  style="width:150px;height:20px;"/></td>
    					</tr>
    					
    					<tr>
    						<td>是否显示</td>
    						<td><input type="radio" name="sh_activity_edit" value="1">是
								<input type="radio" name="sh_activity_edit" value="0" checked="checked">否
								
							</td>
						</tr>
    					
 		</table>
</form> 

<script type="text/javascript"	src="<c:url value='/resources/js/sh.js'/>"></script>

	
