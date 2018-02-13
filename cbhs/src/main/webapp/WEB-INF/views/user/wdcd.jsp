<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<table id="wdcd"></table>

<div id="wdcd_tool" style="padding:5px;">
	<div style="margig-bottom:5px;">
		<!-- <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onClick="wdcd_tool.add();">上传</a>
		
		<a href="#" class="easyui-linkbutton" iconCls="icon-DeleteRed" plain="true" onClick="wdcd_tool.remove();">删除</a> -->
		<input id="dmhDate_wdcd" name="dmhDate_wdcd" type="text" class="easyui-datebox" required="required" value="true" data-options="
		onChange: function (n,o) {

			wdcd_tool.find();

		}"></input>
		<input type="radio"  onChange="wdcd_tool.find();" name="dmhTime_wdcd" checked="checked" value="1">白班
		
		<input type="radio"  onChange="wdcd_tool.find();" name="dmhTime_wdcd" value="0">夜班 
		<a href="#" class="easyui-linkbutton" iconCls="icon-page-find" plain="true" onClick="wdcd_tool.find();">查询</a>
	</div>
</div>


<form id="wdcd_add" style="margin:0;padding:5p 0 0 25px;color:#333;"> 
		<table id="wdcd_add_table" style="border-collapse:separate;border-spacing:10px;display:none;" >
    					<tr>
    						<td>日期</td>
    						<td><input id="wdcdDate_wdcd_add" name="wdcdDate_wdcd_add" class="easyui-datebox" data-options="required:'true',validType:'datevalue',value:'true'" style="width:150px;height:20px;"/></td>
    					</tr>
    					<tr>
    						<td>时间</td>
    						<td><input type="radio" name="wdcdTime_wdcd_add" checked="checked" value="1">白班
								<!-- <input type="radio" name="number_wdcd_add" value="15">15点 -->
								<input type="radio" name="wdcdTime_wdcd_add" value="0">夜班
							</td>
    					</tr>
    					<!-- <tr>
    						<td>类别</td>
    						<td>
    							<input type="radio" name="wdcdType_add" checked="checked" value="1">甩车
								<input type="radio" name="wdcdType_add" value="0">挂车
							</td>
    					</tr>
    					<tr>
    						<td>车次</td>
    						<td><input type="text" name="wdcdCc_add" class="easyui-textbox" data-options="required:'true',validType:'account[2,10]'"  style="width:150px;height:20px;"/></td>
    						
    						<td><input type="text" id="wdcdCc_add" name="wdcdCc_add" class="easyui-combobox"  
    					 data-options="valueField:'wdcdCc',
    					 required:'true',
    					 textField:'wdcdCc',
    					 url:'/wdcd/getAllwdcdCc',
    					 filter: function(q, row){  
            				var opts = $(this).combobox('options');  
            				return row[opts.textField].indexOf(q) >-1;;  
        				},
        				onSelect:function(record) {  
            				$('wdcdCc_add').focus();  
        				}"   
    					 																									  
    					 style="width:150px;height:20px;"/></td>
    					</tr>
    					<tr>
    						<td>时间</td>
    						<td><input type="text" name="wdcdSj_add" class="easyui-datetimebox"  data-options="required:'true',showSeconds:false,value:'true',validType:'datetimevalue'"  style="width:150px;height:20px;"/></td>
    					</tr>
    					<tr>
    						<td>车数</td>
    						<td><input type="text" name="wdcdCs_add" class="easyui-textbox"  data-options="required:'true',validType:'intcount[4]'"  style="width:150px;height:20px;"/></td>
    					</tr>
    					<tr>
    						<td>中转</td>
    						<td><input type="text" name="wdcdZz_add" class="easyui-textbox"  data-options="required:'true',validType:'length[0,40]'"  style="width:150px;height:20px;"/></td>
    					</tr> -->
 		</table>
</form> 


<!-- 删除edit表的日期组件的 value:'true'属性   -->
<form id="wdcd_edit" style="margin:0;padding:5p 0 0 25px;color:#333;"> 
		<input type="hidden" name="wdcdId_edit" class="textbox" />
		
		<table id="wdcd_edit_table" style="border-collapse:separate;border-spacing:10px;display:none;">
    					<tr>
    						<td>日期</td>
    						<td><input id="wdcdDate_wdcd_edit" name="wdcdDate_wdcd_edit" class="easyui-datebox" data-options="required:'true',validType:'datevalue',value:'true'" style="width:150px;height:20px;"/></td>
    					</tr>
    					<tr>
    						<td>时间</td>
    						<td><input type="radio" name="wdcdTime_wdcd_edit" checked="checked" value="1">白班
								<!-- <input type="radio" name="number_wdcd_add" value="15">15点 -->
								<input type="radio" name="wdcdTime_wdcd_edit" value="0">夜班
							</td>
    					</tr>
		</table>
</form> 

<script type="text/javascript"	src="<c:url value='/resources/js/wdcd.js'/>"></script>

	
