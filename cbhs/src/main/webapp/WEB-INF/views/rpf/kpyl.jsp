<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<table id="kpyl"></table>

<div id="kpyl_tool" style="padding:5px;">
	<div style="margig-bottom:5px;">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onClick="kpyl_tool.addf();"><font color="red">新增粉票用量</font></a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onClick="kpyl_tool.addl();"><font color="blue">新增蓝票用量</font></a>
		<!-- <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onClick="kpyl_tool.edit();">修改</a> -->
		<a href="#" class="easyui-linkbutton" iconCls="icon-DeleteRed" plain="true" onClick="kpyl_tool.remove();">删除</a>
		<input id="kpylDate_kpyl" name="kpylDate_kpyl" type="text" class="easyui-datebox" required="required" value="true" data-options="
		onChange: function (n,o) {

			kpyl_tool.find();

		}"></input>
		<!-- <input type="radio"  onChange="kpyl_tool.find();" name="kpylTime_kpyl" checked="checked" value="1">白班
		<input type="radio" onChange="kpyl_tool.find();" name="infonumber_kpyl" value="15">15点
		<input type="radio"  onChange="kpyl_tool.find();" name="kpylTime_kpyl" value="0">夜班 -->
		<a href="#" class="easyui-linkbutton" iconCls="icon-page-find" plain="true" onClick="kpyl_tool.find();">查询</a>
	</div>
</div>


<form id="kpyl_fenpiao_add" style="margin:0;padding:5p 0 0 25px;color:#333;"> 
		<table id="kpyl_fenpiao_add_table" style="border-collapse:separate;border-spacing:10px;display:none;" >
    					<tr>
    						<td>统计月份</td>
    						<td><input id="kpyl_fenpiao_date__add" name="kpyl_fenpiao_date__add" class="easyui-datebox" data-options="required:'true',validType:'datevalue',value:'true'" style="width:150px;height:20px;"/></td>
    					</tr>
    					<tr>
    						<td>售票张数</td>
    						<td><input type="text" name="kpyl_fenpiao_sp_add" class="easyui-textbox"  data-options="required:'true',validType:'intcount[10]'"  style="width:150px;height:20px;"/></td>
    					</tr>
    					<tr>
    						<td>废票张数</td>
    						<td><input type="text" name="kpyl_fenpiao_fp_add" class="easyui-textbox"  data-options="required:'true',validType:'intcount[10]'"  style="width:150px;height:20px;"/></td>
    					</tr>
    					<!-- <tr>
    						<td>时间</td>
    						<td><input type="radio" name="kpylTime_kpyl_fenpiao_add" checked="checked" value="1">白班
								<input type="radio" name="number_kpyl_fenpiao_add" value="15">15点
								<input type="radio" name="kpylTime_kpyl_fenpiao_add" value="0">夜班
							</td>
    					</tr> -->
    					<!-- <tr>
    						<td>类别</td>
    						<td>
    							<input type="radio" name="kpylType_add" checked="checked" value="1">甩车
								<input type="radio" name="kpylType_add" value="0">挂车
							</td>
    					</tr>
    					<tr>
    						<td>车次</td>
    						<td><input type="text" name="kpylCc_add" class="easyui-textbox" data-options="required:'true',validType:'account[2,10]'"  style="width:150px;height:20px;"/></td>
    						
    						<td><input type="text" id="kpylCc_add" name="kpylCc_add" class="easyui-combobox"  
    					 data-options="valueField:'kpylCc',
    					 required:'true',
    					 textField:'kpylCc',
    					 url:'/kpyl/getAllkpylCc',
    					 filter: function(q, row){  
            				var opts = $(this).combobox('options');  
            				return row[opts.textField].indexOf(q) >-1;;  
        				},
        				onSelect:function(record) {  
            				$('kpylCc_add').focus();  
        				}"   
    					 																									  
    					 style="width:150px;height:20px;"/></td>
    					</tr>
    					<tr>
    						<td>时间</td>
    						<td><input type="text" name="kpylSj_add" class="easyui-datetimebox"  data-options="required:'true',showSeconds:false,value:'true',validType:'datetimevalue'"  style="width:150px;height:20px;"/></td>
    					</tr>
    					<tr>
    						<td>车数</td>
    						<td><input type="text" name="kpylCs_add" class="easyui-textbox"  data-options="required:'true',validType:'intcount[4]'"  style="width:150px;height:20px;"/></td>
    					</tr>
    					<tr>
    						<td>中转</td>
    						<td><input type="text" name="kpylZz_add" class="easyui-textbox"  data-options="required:'true',validType:'length[0,40]'"  style="width:150px;height:20px;"/></td>
    					</tr> -->
 		</table>
</form> 

<form id="kpyl_lanpiao_add" style="margin:0;padding:5p 0 0 25px;color:#333;"> 
		<table id="kpyl_lanpiao_add_table" style="border-collapse:separate;border-spacing:10px;display:none;" >
    					<tr>
    						<td>日期</td>
    						<td><input id="kpyl_lanpiao_date_add" name="kpyl_lanpiao_date_add" class="easyui-datebox" data-options="required:'true',validType:'datevalue',value:'true'" style="width:150px;height:20px;"/></td>
    					</tr>
    					<tr>
    						<td>售票张数</td>
    						<td><input type="text" name="kpyl_lanpiao_sp_add" class="easyui-textbox"  data-options="required:'true',validType:'intcount[4]'"  style="width:150px;height:20px;"/></td>
    					</tr>
    					<tr>
    						<td>废票张数</td>
    						<td><input type="text" name="kpyl_lanpiao_fp_add" class="easyui-textbox"  data-options="required:'true',validType:'intcount[4]'"  style="width:150px;height:20px;"/></td>
    					</tr>
    					<!-- <tr>
    						<td>时间</td>
    						<td><input type="radio" name="kpylTime_kpyl_add" checked="checked" value="1">白班
								<input type="radio" name="number_kpyl_add" value="15">15点
								<input type="radio" name="kpylTime_kpyl_add" value="0">夜班
							</td>
    					</tr> -->
    					<!-- <tr>
    						<td>类别</td>
    						<td>
    							<input type="radio" name="kpylType_add" checked="checked" value="1">甩车
								<input type="radio" name="kpylType_add" value="0">挂车
							</td>
    					</tr>
    					<tr>
    						<td>车次</td>
    						<td><input type="text" name="kpylCc_add" class="easyui-textbox" data-options="required:'true',validType:'account[2,10]'"  style="width:150px;height:20px;"/></td>
    						
    						<td><input type="text" id="kpylCc_add" name="kpylCc_add" class="easyui-combobox"  
    					 data-options="valueField:'kpylCc',
    					 required:'true',
    					 textField:'kpylCc',
    					 url:'/kpyl/getAllkpylCc',
    					 filter: function(q, row){  
            				var opts = $(this).combobox('options');  
            				return row[opts.textField].indexOf(q) >-1;;  
        				},
        				onSelect:function(record) {  
            				$('kpylCc_add').focus();  
        				}"   
    					 																									  
    					 style="width:150px;height:20px;"/></td>
    					</tr>
    					<tr>
    						<td>时间</td>
    						<td><input type="text" name="kpylSj_add" class="easyui-datetimebox"  data-options="required:'true',showSeconds:false,value:'true',validType:'datetimevalue'"  style="width:150px;height:20px;"/></td>
    					</tr>
    					<tr>
    						<td>车数</td>
    						<td><input type="text" name="kpylCs_add" class="easyui-textbox"  data-options="required:'true',validType:'intcount[4]'"  style="width:150px;height:20px;"/></td>
    					</tr>
    					<tr>
    						<td>中转</td>
    						<td><input type="text" name="kpylZz_add" class="easyui-textbox"  data-options="required:'true',validType:'length[0,40]'"  style="width:150px;height:20px;"/></td>
    					</tr> -->
 		</table>
</form> 


<!-- 删除edit表的日期组件的 value:'true'属性   -->
<form id="kpyl_edit" style="margin:0;padding:5p 0 0 25px;color:#333;"> 
		<input type="hidden" name="kpylId_edit" class="textbox" />
		
		<table id="kpyl_edit_table" style="border-collapse:separate;border-spacing:10px;display:none;">
    					<tr>
    						<td>日期</td>
    						<td><input id="kpylDate_kpyl_edit" name="kpylDate_kpyl_edit" class="easyui-datebox" data-options="required:'true',validType:'datevalue',value:'true'" style="width:150px;height:20px;"/></td>
    					</tr>
    					<tr>
    						<td>时间</td>
    						<td><input type="radio" name="kpylTime_kpyl_edit" checked="checked" value="1">白班
								<!-- <input type="radio" name="number_kpyl_add" value="15">15点 -->
								<input type="radio" name="kpylTime_kpyl_edit" value="0">夜班
							</td>
    					</tr>
		</table>
</form> 

<script type="text/javascript"	src="<c:url value='/resources/js/kpyl.js'/>"></script>

	
