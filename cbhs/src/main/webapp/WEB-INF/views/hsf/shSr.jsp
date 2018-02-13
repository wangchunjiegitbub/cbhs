<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<table id="shsr"></table>

<div id="shsr_tool" style="padding:5px;">
	<div style="margig-bottom:5px;">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onClick="shsr_tool.shEnter();">审核</a>
		
		<!-- 
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onClick="shsr_tool.edit();">修改</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-DeleteRed" plain="true" onClick="shsr_tool.remove();">删除</a>
		 -->
		<input id="shsrDate_shsr_begin" name="shsrDate_shsr_begin" type="text" class="easyui-datebox" required="required" value="true" data-options="
		onChange: function (n,o) {

			shsr_tool.find();

		}"></input>-
		<input id="shsrDate_shsr_end" name="shsrDate_shsr_end" type="text" class="easyui-datebox" required="required" value="true" data-options="
		onChange: function (n,o) {

			shsr_tool.find();

		}"></input>
		
		
		<c:if test="${sessionScope.sessionUser.quanxian == 11 || sessionScope.sessionUser.quanxian == 12 }">
		
		
			<input type="text" id="depId_shsr" name="depId_shsr" class="easyui-combobox"  
	    					 data-options="valueField:'departmentId',
	    					 required:'true',
	    					 textField:'departmentName',
	    					 url:'/cbhs/hsf/getDepAllSelect',
	    					 filter: function(q, row){  
	            				var opts = $(this).combobox('options');  
	            				return row[opts.textField].indexOf(q) >-1;;  
	        				},
	        				onSelect:function(record) {  
	            				$('depId_shsr').focus();  
	        				}"   
	    					 																									  
	    					 style="width:150px;height:20px;"/>
		</c:if>
		<!-- <input type="radio"  onChange="shsr_tool.find();" name="shsrTime_shsr" checked="checked" value="1">白班
		<input type="radio" onChange="shsr_tool.find();" name="infonumber_shsr" value="15">15点
		<input type="radio"  onChange="shsr_tool.find();" name="shsrTime_shsr" value="0">夜班 -->
		<a href="#" class="easyui-linkbutton" iconCls="icon-page-find" plain="true" onClick="shsr_tool.find();">查询</a>
	</div>
</div>


<form id="shsr_add" style="margin:0;padding:5p 0 0 25px;color:#333;"> 
		<table id="shsr_add_table" style="border-collapse:separate;border-spacing:10px;display:none;" >
    					<tr>
    						<td>收入日期</td>
    						<td><input id="shsr_date_add" name="shsr_date_add" class="easyui-datebox" data-options="required:'true',validType:'datevalue',value:'true'" style="width:150px;height:20px;"/></td>
    					</tr>
    					<tr>
    						<td>收入类别</td>
    						<td><select id="shsr_typeid_add" name="shsr_typeid_add" class="easyui-combobox" style="width:150px"   
        								data-options="valueField:'srtypeId',textField:'srtype',url:'/cbhs/hsf/getSrtypeAll',required:'true', editable:false"></select></td>
    					</tr>
    					<tr>
    						<td>金额（元）</td>
    						<td><input type="text" name="shsr_money_add" class="easyui-textbox"  data-options="required:'true',validType:['validateFloat','length[0,10]']"  style="width:150px;height:20px;"/></td>
    					</tr>
    					<tr>
    						<td>备注</td>
    						<td><input type="text" name="shsr_beizu_add" class="easyui-textbox" data-options="required:'true',validType:'length[1,100]'"  style="width:150px;height:20px;"/></td>
    					</tr>
    					
    					
 		</table>
</form> 

<form id="shsr_edit" style="margin:0;padding:5p 0 0 25px;color:#333;"> 
		
		<input type="hidden" name="shouruId_edit" class="textbox" />
		
		<table id="shsr_edit_table" style="border-collapse:separate;border-spacing:10px;display:none;" >
    					<tr>
    						<td>收入日期</td>
    						<td><input id="shsr_date_edit" name="shsr_date_edit" class="easyui-datebox" data-options="required:'true',validType:'datevalue',value:'true'" style="width:150px;height:20px;"/></td>
    					</tr>
    					<tr>
    						<td>收入类别</td>
    						<td><select id="shsr_typeid_edit" name="shsr_typeid_edit" class="easyui-combobox" style="width:150px"   
        								data-options="valueField:'srtypeId',textField:'srtype',url:'/cbhs/hsf/getSrtypeAll',required:'true', editable:false"></select></td>
    					</tr>
    					<tr>
    						<td>金额（元）</td>
    						<td><input type="text" name="shsr_money_edit" class="easyui-textbox"  data-options="required:'true',validType:['validateFloat','length[0,10]']"  style="width:150px;height:20px;"/></td>
    					</tr>
    					<tr>
    						<td>备注</td>
    						<td><input type="text" name="shsr_beizu_edit" class="easyui-textbox" data-options="required:'true',validType:'length[1,100]'"  style="width:150px;height:20px;"/></td>
    					</tr>
    					
    					
 		</table>
</form> 



<script type="text/javascript"	src="<c:url value='/resources/js/shSr.js'/>"></script>

	
