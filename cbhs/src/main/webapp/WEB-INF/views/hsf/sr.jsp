<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<table id="sr"></table>

<div id="sr_tool" style="padding:5px;">
	<div style="margig-bottom:5px;">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onClick="sr_tool.add();">添加收入</a>
		
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onClick="sr_tool.edit();">修改</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-DeleteRed" plain="true" onClick="sr_tool.remove();">删除</a>
		<input id="srDate_sr_begin" name="srDate_sr_begin" type="text" class="easyui-datebox" required="required" value="true" data-options="
		onChange: function (n,o) {

			sr_tool.find();

		}"></input>-
		<input id="srDate_sr_end" name="srDate_sr_end" type="text" class="easyui-datebox" required="required" value="true" data-options="
		onChange: function (n,o) {

			sr_tool.find();

		}"></input>
		
		
		<c:if test="${sessionScope.sessionUser.quanxian == 11 || sessionScope.sessionUser.quanxian == 12 }">
		
		
			<input type="text" id="depId_sr" name="depId_sr" class="easyui-combobox"  
	    					 data-options="valueField:'departmentId',
	    					 required:'true',
	    					 textField:'departmentName',
	    					 url:'/cbhs/hsf/getDepAllSelect',
	    					 filter: function(q, row){  
	            				var opts = $(this).combobox('options');  
	            				return row[opts.textField].indexOf(q) >-1;;  
	        				},
	        				onSelect:function(record) {  
	            				$('depId_sr').focus();  
	        				}"   
	    					 																									  
	    					 style="width:150px;height:20px;"/>
		</c:if>
		<!-- <input type="radio"  onChange="sr_tool.find();" name="srTime_sr" checked="checked" value="1">白班
		<input type="radio" onChange="sr_tool.find();" name="infonumber_sr" value="15">15点
		<input type="radio"  onChange="sr_tool.find();" name="srTime_sr" value="0">夜班 -->
		<a href="#" class="easyui-linkbutton" iconCls="icon-page-find" plain="true" onClick="sr_tool.find();">查询</a>
	</div>
</div>


<form id="sr_add" style="margin:0;padding:5p 0 0 25px;color:#333;"> 
		<table id="sr_add_table" style="border-collapse:separate;border-spacing:10px;display:none;" >
    					<tr>
    						<td>收入日期</td>
    						<td><input id="sr_date_add" name="sr_date_add" class="easyui-datebox" data-options="required:'true',validType:'datevalue',value:'true'" style="width:150px;height:20px;"/></td>
    					</tr>
    					<tr>
    						<td>收入类别</td>
    						<td><select id="sr_typeid_add" name="sr_typeid_add" class="easyui-combobox" style="width:150px"   
        								data-options="valueField:'srtypeId',textField:'srtype',url:'/cbhs/hsf/getSrtypeAll',required:'true', editable:false"></select></td>
    					</tr>
    					<tr>
    						<td>金额（元）</td>
    						<td><input type="text" name="sr_money_add" class="easyui-textbox"  data-options="required:'true',validType:['validateFloat','length[0,10]']"  style="width:150px;height:20px;"/></td>
    					</tr>
    					<tr>
    						<td>备注</td>
    						<td><input type="text" name="sr_beizu_add" class="easyui-textbox" data-options="required:'true',validType:'length[1,100]'"  style="width:150px;height:20px;"/></td>
    					</tr>
    					
    					
 		</table>
</form> 

<form id="sr_edit" style="margin:0;padding:5p 0 0 25px;color:#333;"> 
		
		<input type="hidden" name="shouruId_edit" class="textbox" />
		
		<table id="sr_edit_table" style="border-collapse:separate;border-spacing:10px;display:none;" >
    					<tr>
    						<td>收入日期</td>
    						<td><input id="sr_date_edit" name="sr_date_edit" class="easyui-datebox" data-options="required:'true',validType:'datevalue',value:'true'" style="width:150px;height:20px;"/></td>
    					</tr>
    					<tr>
    						<td>收入类别</td>
    						<td><select id="sr_typeid_edit" name="sr_typeid_edit" class="easyui-combobox" style="width:150px"   
        								data-options="valueField:'srtypeId',textField:'srtype',url:'/cbhs/hsf/getSrtypeAll',required:'true', editable:false"></select></td>
    					</tr>
    					<tr>
    						<td>金额（元）</td>
    						<td><input type="text" name="sr_money_edit" class="easyui-textbox"  data-options="required:'true',validType:['validateFloat','length[0,10]']"  style="width:150px;height:20px;"/></td>
    					</tr>
    					<tr>
    						<td>备注</td>
    						<td><input type="text" name="sr_beizu_edit" class="easyui-textbox" data-options="required:'true',validType:'length[1,100]'"  style="width:150px;height:20px;"/></td>
    					</tr>
    					
    					
 		</table>
</form> 



<script type="text/javascript"	src="<c:url value='/resources/js/sr.js'/>"></script>

	
