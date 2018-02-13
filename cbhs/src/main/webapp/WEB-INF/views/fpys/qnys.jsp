<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<table id="qnys"></table>

<div id="qnys_tool" style="padding:5px;">
	<div style="margig-bottom:5px;">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onClick="qnys_tool.addf();"><font color="red">新增预算</font></a>
		
		<!-- <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onClick="qnys_tool.edit();">修改</a> -->
		<a href="#" class="easyui-linkbutton" iconCls="icon-DeleteRed" plain="true" onClick="qnys_tool.remove();">删除</a>
		<input id="qnysDate_qnys" name="qnysDate_qnys" type="text" class="easyui-datebox" required="required" value="true" data-options="
		onChange: function (n,o) {

			qnys_tool.find();

		}"></input>
		<!-- <input type="radio"  onChange="qnys_tool.find();" name="qnysTime_qnys" checked="checked" value="1">白班
		<input type="radio" onChange="qnys_tool.find();" name="infonumber_qnys" value="15">15点
		<input type="radio"  onChange="qnys_tool.find();" name="qnysTime_qnys" value="0">夜班 -->
		<a href="#" class="easyui-linkbutton" iconCls="icon-page-find" plain="true" onClick="qnys_tool.find();">查询</a>
	</div>
</div>


<form id="qnys_add" style="margin:0;padding:5p 0 0 25px;color:#333;"> 
		<table id="qnys_add_table" style="border-collapse:separate;border-spacing:10px;display:none;" >
    					<tr>
    						<td>年份</td>
    						<td><input id="qnys_date__add" name="qnys_date__add" class="easyui-datebox" data-options="required:'true',validType:'datevalue',value:'true'" style="width:150px;height:20px;"/></td>
    					</tr>
    					<tr>
    						<td>费用科目</td>
    						<td><select id="cc" class="easyui-combotree" style="width:350px"   
        								data-options="url:'/cbhs/fpys/getKemuAll',required:true"></select></td>
    					</tr>
    					<tr>
    						<td>分劈科室</td>
    						<td><input type="text" name="qnys_add" class="easyui-textbox"  data-options="required:'true',validType:'intcount[10]'"  style="width:150px;height:20px;"/></td>
    					</tr>
    					<tr>
    						<td>分劈预算合计（元）</td>
    						<td><input type="text" name="qnys_add" class="easyui-textbox"  data-options="required:'true',validType:'intcount[10]'"  style="width:150px;height:20px;"/></td>
    					</tr>
    					<!-- <tr>
    						<td>时间</td>
    						<td><input type="radio" name="qnysTime_qnys_add" checked="checked" value="1">白班
								<input type="radio" name="number_qnys_add" value="15">15点
								<input type="radio" name="qnysTime_qnys_add" value="0">夜班
							</td>
    					</tr> -->
    					<!-- <tr>
    						<td>类别</td>
    						<td>
    							<input type="radio" name="qnysType_add" checked="checked" value="1">甩车
								<input type="radio" name="qnysType_add" value="0">挂车
							</td>
    					</tr>
    					<tr>
    						<td>车次</td>
    						<td><input type="text" name="qnysCc_add" class="easyui-textbox" data-options="required:'true',validType:'account[2,10]'"  style="width:150px;height:20px;"/></td>
    						
    						<td><input type="text" id="qnysCc_add" name="qnysCc_add" class="easyui-combobox"  
    					 data-options="valueField:'qnysCc',
    					 required:'true',
    					 textField:'qnysCc',
    					 url:'/qnys/getAllqnysCc',
    					 filter: function(q, row){  
            				var opts = $(this).combobox('options');  
            				return row[opts.textField].indexOf(q) >-1;;  
        				},
        				onSelect:function(record) {  
            				$('qnysCc_add').focus();  
        				}"   
    					 																									  
    					 style="width:150px;height:20px;"/></td>
    					</tr>
    					<tr>
    						<td>时间</td>
    						<td><input type="text" name="qnysSj_add" class="easyui-datetimebox"  data-options="required:'true',showSeconds:false,value:'true',validType:'datetimevalue'"  style="width:150px;height:20px;"/></td>
    					</tr>
    					<tr>
    						<td>车数</td>
    						<td><input type="text" name="qnysCs_add" class="easyui-textbox"  data-options="required:'true',validType:'intcount[4]'"  style="width:150px;height:20px;"/></td>
    					</tr>
    					<tr>
    						<td>中转</td>
    						<td><input type="text" name="qnysZz_add" class="easyui-textbox"  data-options="required:'true',validType:'length[0,40]'"  style="width:150px;height:20px;"/></td>
    					</tr> -->
 		</table>
</form> 



<!-- 删除edit表的日期组件的 value:'true'属性   -->
<form id="qnys_edit" style="margin:0;padding:5p 0 0 25px;color:#333;"> 
		<input type="hidden" name="qnysId_edit" class="textbox" />
		
		<table id="qnys_edit_table" style="border-collapse:separate;border-spacing:10px;display:none;">
    					<tr>
    						<td>日期</td>
    						<td><input id="qnysDate_qnys_edit" name="qnysDate_qnys_edit" class="easyui-datebox" data-options="required:'true',validType:'datevalue',value:'true'" style="width:150px;height:20px;"/></td>
    					</tr>
    					<tr>
    						<td>时间</td>
    						<td><input type="radio" name="qnysTime_qnys_edit" checked="checked" value="1">白班
								<!-- <input type="radio" name="number_qnys_add" value="15">15点 -->
								<input type="radio" name="qnysTime_qnys_edit" value="0">夜班
							</td>
    					</tr>
		</table>
</form> 

<script type="text/javascript"	src="<c:url value='/resources/js/qnys.js'/>"></script>

	
