<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<table id="dmh"></table>

<div id="dmh_tool" style="padding:5px;">
	<div style="margig-bottom:5px;">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onClick="dmh_tool.add();">创建会议</a>
		<!-- <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onClick="dmh_tool.edit();">修改</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-DeleteRed" plain="true" onClick="dmh_tool.remove();">删除</a> -->
		<input id="dmhDate_dmh" name="dmhDate_dmh" type="text" class="easyui-datebox" required="required" value="true" data-options="
		onChange: function (n,o) {

			dmh_tool.find();

		}"></input>
		<input type="radio"  onChange="dmh_tool.find();" name="dmhTime_dmh" checked="checked" value="1">白班
		<!-- <input type="radio" onChange="dmh_tool.find();" name="infonumber_dmh" value="15">15点 -->
		<input type="radio"  onChange="dmh_tool.find();" name="dmhTime_dmh" value="0">夜班
		<a href="#" class="easyui-linkbutton" iconCls="icon-page-find" plain="true" onClick="dmh_tool.find();">查询</a>
	</div>
</div>


<form id="dmh_add" style="margin:0;padding:5p 0 0 25px;color:#333;"> 
		<table id="dmh_add_table" style="border-collapse:separate;border-spacing:10px;display:none;" >
    					<tr>
    						<td>日期</td>
    						<td><input id="dmhDate_dmh_add" name="dmhDate_dmh_add" class="easyui-datebox" data-options="required:'true',validType:'datevalue',value:'true'" style="width:150px;height:20px;"/></td>
    					</tr>
    					<tr>
    						<td>时间</td>
    						<td><input type="radio" name="dmhTime_dmh_add" checked="checked" value="1">白班
								<!-- <input type="radio" name="number_dmh_add" value="15">15点 -->
								<input type="radio" name="dmhTime_dmh_add" value="0">夜班
							</td>
    					</tr>
    					<!-- <tr>
    						<td>类别</td>
    						<td>
    							<input type="radio" name="dmhType_add" checked="checked" value="1">甩车
								<input type="radio" name="dmhType_add" value="0">挂车
							</td>
    					</tr>
    					<tr>
    						<td>车次</td>
    						<td><input type="text" name="dmhCc_add" class="easyui-textbox" data-options="required:'true',validType:'account[2,10]'"  style="width:150px;height:20px;"/></td>
    						
    						<td><input type="text" id="dmhCc_add" name="dmhCc_add" class="easyui-combobox"  
    					 data-options="valueField:'dmhCc',
    					 required:'true',
    					 textField:'dmhCc',
    					 url:'/dmh/getAlldmhCc',
    					 filter: function(q, row){  
            				var opts = $(this).combobox('options');  
            				return row[opts.textField].indexOf(q) >-1;;  
        				},
        				onSelect:function(record) {  
            				$('dmhCc_add').focus();  
        				}"   
    					 																									  
    					 style="width:150px;height:20px;"/></td>
    					</tr>
    					<tr>
    						<td>时间</td>
    						<td><input type="text" name="dmhSj_add" class="easyui-datetimebox"  data-options="required:'true',showSeconds:false,value:'true',validType:'datetimevalue'"  style="width:150px;height:20px;"/></td>
    					</tr>
    					<tr>
    						<td>车数</td>
    						<td><input type="text" name="dmhCs_add" class="easyui-textbox"  data-options="required:'true',validType:'intcount[4]'"  style="width:150px;height:20px;"/></td>
    					</tr>
    					<tr>
    						<td>中转</td>
    						<td><input type="text" name="dmhZz_add" class="easyui-textbox"  data-options="required:'true',validType:'length[0,40]'"  style="width:150px;height:20px;"/></td>
    					</tr> -->
 		</table>
</form> 


<!-- 删除edit表的日期组件的 value:'true'属性   -->
<form id="dmh_edit" style="margin:0;padding:5p 0 0 25px;color:#333;"> 
		<input type="hidden" name="dmhId_edit" class="textbox" />
		
		<table id="dmh_edit_table" style="border-collapse:separate;border-spacing:10px;display:none;">
    					<tr>
    						<td>日期</td>
    						<td><input id="dmhDate_dmh_edit" name="dmhDate_dmh_edit" class="easyui-datebox" data-options="required:'true',validType:'datevalue',value:'true'" style="width:150px;height:20px;"/></td>
    					</tr>
    					<tr>
    						<td>时间</td>
    						<td><input type="radio" name="dmhTime_dmh_edit" checked="checked" value="1">白班
								<!-- <input type="radio" name="number_dmh_add" value="15">15点 -->
								<input type="radio" name="dmhTime_dmh_edit" value="0">夜班
							</td>
    					</tr>
		</table>
</form> 

<script type="text/javascript"	src="<c:url value='/resources/js/dmh.js'/>"></script>

	
