<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- bootstrap文件上传页面 -->
<div id="div_info" style="overflow:hidden;"></div>

<table id="fileUp"></table>

<div id="fileUp_tool" style="padding:5px;">
	<div style="margig-bottom:5px;">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onClick="fileUp_tool.add();">上传</a>
		
		<a href="#" class="easyui-linkbutton" iconCls="icon-DeleteRed" plain="true" onClick="fileUp_tool.remove();">删除</a>
		<!-- <input id="dmhDate_fileUp" name="dmhDate_fileUp" type="text" class="easyui-datebox" required="required" value="true" data-options="
		onChange: function (n,o) {

			fileUp_tool.find();

		}"></input>
		<input type="radio"  onChange="fileUp_tool.find();" name="dmhTime_fileUp" checked="checked" value="1">白班
		
		<input type="radio"  onChange="fileUp_tool.find();" name="dmhTime_fileUp" value="0">夜班 -->
		<a href="#" class="easyui-linkbutton" iconCls="icon-page-find" plain="true" onClick="fileUp_tool.find();">查询</a>
	</div>
</div>


<form id="fileUp_add" style="margin:0;padding:5p 0 0 25px;color:#333;"> 
		<table id="fileUp_add_table" style="border-collapse:separate;border-spacing:10px;display:none;" >
    					<tr>
    						<td>日期</td>
    						<td><input id="fileUpDate_fileUp_add" name="fileUpDate_fileUp_add" class="easyui-datebox" data-options="required:'true',validType:'datevalue',value:'true'" style="width:150px;height:20px;"/></td>
    					</tr>
    					<tr>
    						<td>时间</td>
    						<td><input type="radio" name="fileUpTime_fileUp_add" checked="checked" value="1">白班
								<!-- <input type="radio" name="number_fileUp_add" value="15">15点 -->
								<input type="radio" name="fileUpTime_fileUp_add" value="0">夜班
							</td>
    					</tr>
    					<!-- <tr>
    						<td>类别</td>
    						<td>
    							<input type="radio" name="fileUpType_add" checked="checked" value="1">甩车
								<input type="radio" name="fileUpType_add" value="0">挂车
							</td>
    					</tr>
    					<tr>
    						<td>车次</td>
    						<td><input type="text" name="fileUpCc_add" class="easyui-textbox" data-options="required:'true',validType:'account[2,10]'"  style="width:150px;height:20px;"/></td>
    						
    						<td><input type="text" id="fileUpCc_add" name="fileUpCc_add" class="easyui-combobox"  
    					 data-options="valueField:'fileUpCc',
    					 required:'true',
    					 textField:'fileUpCc',
    					 url:'/fileUp/getAllfileUpCc',
    					 filter: function(q, row){  
            				var opts = $(this).combobox('options');  
            				return row[opts.textField].indexOf(q) >-1;;  
        				},
        				onSelect:function(record) {  
            				$('fileUpCc_add').focus();  
        				}"   
    					 																									  
    					 style="width:150px;height:20px;"/></td>
    					</tr>
    					<tr>
    						<td>时间</td>
    						<td><input type="text" name="fileUpSj_add" class="easyui-datetimebox"  data-options="required:'true',showSeconds:false,value:'true',validType:'datetimevalue'"  style="width:150px;height:20px;"/></td>
    					</tr>
    					<tr>
    						<td>车数</td>
    						<td><input type="text" name="fileUpCs_add" class="easyui-textbox"  data-options="required:'true',validType:'intcount[4]'"  style="width:150px;height:20px;"/></td>
    					</tr>
    					<tr>
    						<td>中转</td>
    						<td><input type="text" name="fileUpZz_add" class="easyui-textbox"  data-options="required:'true',validType:'length[0,40]'"  style="width:150px;height:20px;"/></td>
    					</tr> -->
 		</table>
</form> 


<!-- 删除edit表的日期组件的 value:'true'属性   -->
<form id="fileUp_edit" style="margin:0;padding:5p 0 0 25px;color:#333;"> 
		<input type="hidden" name="fileUpId_edit" class="textbox" />
		
		<table id="fileUp_edit_table" style="border-collapse:separate;border-spacing:10px;display:none;">
    					<tr>
    						<td>日期</td>
    						<td><input id="fileUpDate_fileUp_edit" name="fileUpDate_fileUp_edit" class="easyui-datebox" data-options="required:'true',validType:'datevalue',value:'true'" style="width:150px;height:20px;"/></td>
    					</tr>
    					<tr>
    						<td>时间</td>
    						<td><input type="radio" name="fileUpTime_fileUp_edit" checked="checked" value="1">白班
								<!-- <input type="radio" name="number_fileUp_add" value="15">15点 -->
								<input type="radio" name="fileUpTime_fileUp_edit" value="0">夜班
							</td>
    					</tr>
		</table>
</form> 

<script type="text/javascript"	src="<c:url value='/resources/js/fileUp.js'/>"></script>

	
