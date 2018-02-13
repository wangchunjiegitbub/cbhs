<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<table id="zgdm"></table>








<div id="zgdm_tool" style="padding:5px;">
	<div style="margig-bottom:5px;">
		
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onClick="zgdm_tool.editQd();">提交</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onClick="zgdm_tool.editQt();">撤销</a>
		<input id="dmhDate_ryqr" name="dmhDate_zgdm" type="text" class="easyui-datebox" required="required" value="true" data-options="
		onChange: function (n,o) {

			zgdm_tool.find();
			

		}"></input>
		<input type="radio"  onChange="zgdm_tool.find();" name="dmhTime_zgdm" checked="checked" value="1">白班
		<!-- <input type="radio" onChange="zgdm_tool.find();" name="infonumber_zgdm" value="15">15点 -->
		<input type="radio"  onChange="zgdm_tool.find();" name="dmhTime_zgdm" value="0">夜班
		<a href="#" class="easyui-linkbutton" iconCls="icon-page-find" plain="true" onClick="zgdm_tool.find();">查询</a>
	</div>
</div>


<form id="zgdm_add" style="margin:0;padding:5p 0 0 25px;color:#333;"> 
		<table id="zgdm_add_table" style="border-collapse:separate;border-spacing:10px;display:none;" >
    					<tr>
    						<td>工作日期</td>
    						<td><input id="rq_zgdm_add" name="rq_zgdm_add" class="easyui-datebox" data-options="required:'true',validType:'datevalue',value:'true'" style="width:150px;height:20px;"/></td>
    					</tr>
    					<tr>
    						<td>结账时间</td>
    						<td><input type="radio" name="number_zgdm_add" checked="checked" value="6"> 6点
								<!-- <input type="radio" name="number_zgdm_add" value="15">15点 -->
								<input type="radio" name="number_zgdm_add" value="18">18点
							</td>
    					</tr>
    					<tr>
    						<td>类别</td>
    						<td>
    							<input type="radio" name="zgdmType_add" checked="checked" value="1">甩车
								<input type="radio" name="zgdmType_add" value="0">挂车
							</td>
    					</tr>
    					<tr>
    						<td>车次</td>
    						<!-- <td><input type="text" name="zgdmCc_add" class="easyui-textbox" data-options="required:'true',validType:'account[2,10]'"  style="width:150px;height:20px;"/></td>
    						 -->
    						<td><input type="text" id="zgdmCc_add" name="zgdmCc_add" class="easyui-combobox"  
    					 data-options="valueField:'zgdmCc',
    					 required:'true',
    					 textField:'zgdmCc',
    					 url:'/dmh/getAllzgdmCc',
    					 filter: function(q, row){  
            				var opts = $(this).combobox('options');  
            				return row[opts.textField].indexOf(q) >-1;;  
        				},
        				onSelect:function(record) {  
            				$('zgdmCc_add').focus();  
        				}"   
    					 																									  
    					 style="width:150px;height:20px;"/></td>
    					</tr>
    					<tr>
    						<td>时间</td>
    						<td><input type="text" name="zgdmSj_add" class="easyui-datetimebox"  data-options="required:'true',showSeconds:false,value:'true',validType:'datetimevalue'"  style="width:150px;height:20px;"/></td>
    					</tr>
    					<tr>
    						<td>车数</td>
    						<td><input type="text" name="zgdmCs_add" class="easyui-textbox"  data-options="required:'true',validType:'intcount[4]'"  style="width:150px;height:20px;"/></td>
    					</tr>
    					<tr>
    						<td>中转</td>
    						<td><input type="text" name="zgdmZz_add" class="easyui-textbox"  data-options="required:'true',validType:'length[0,40]'"  style="width:150px;height:20px;"/></td>
    					</tr>
 		</table>
</form> 


<!-- 删除edit表的日期组件的 value:'true'属性   -->
<form id="zgdm_edit" style="margin:0;padding:5p 0 0 25px;color:#333;"> 
		<input type="hidden" name="zgdmId_edit" class="textbox" />
		<input type="hidden" name="zgdm_infoId_edit" class="textbox" />
		<table id="zgdm_edit_table" style="border-collapse:separate;border-spacing:10px;display:none;">
    					<tr>
    						<td>工作日期</td>
    						<td><input id="rq_zgdm_edit" name="rq_zgdm_edit" class="easyui-datebox" data-options="required:'true',validType:'datevalue'" style="width:150px;height:20px;" disabled/></td>
    					</tr>
    					<tr>
    						<td>结账时间</td>
    						<td><input type="radio" name="number_zgdm_edit" checked="checked" value="6" disabled>6点
								<!-- <input type="radio" name="number_zgdm_edit" value="15" disabled>15点 -->
								<input type="radio" name="number_zgdm_edit" value="18" disabled>18点
							</td>
    					</tr>
    					
    					<tr>
    						<td>类别</td>
    						<td>
    							<input type="radio" name="zgdmType_edit" checked="checked" value="1" disabled>甩车
								<input type="radio" name="zgdmType_edit" value="0" disabled>挂车
							</td>
    					</tr>
    					<tr>
    						<td>车次</td>
    						<!-- <td><input type="text" name="zgdmCc_edit" class="easyui-textbox" data-options="required:'true',validType:'account[2,10]'"  style="width:150px;height:20px;"/></td>
    					 -->
    						<td><input type="text" id="zgdmCc_edit" name="zgdmCc_edit" class="easyui-combobox"  
    					 data-options="valueField:'zgdmCc',
    					 required:'true',
    					 textField:'zgdmCc',
    					 url:'/dmh/getAllzgdmCc',
    					 filter: function(q, row){  
            				var opts = $(this).combobox('options');  
            				return row[opts.textField].indexOf(q) >-1;;  
        				},
        				onSelect:function(record) {  
            				$('zgdmCc_edit').focus();  
        				}"   
    					 																									  
    					 style="width:150px;height:20px;"/></td>
    					</tr>
    					<tr>
    						<td>时间</td>
    						<td><input type="text" name="zgdmSj_edit" class="easyui-datetimebox"  data-options="required:'true',showSeconds:false,value:'true',validType:'datetimevalue'"  style="width:150px;height:20px;"/></td>
    					</tr>
    					<tr>
    						<td>车数</td>
    						<td><input type="text" name="zgdmCs_edit" class="easyui-textbox"  data-options="required:'true',validType:'intcount[4]'"  style="width:150px;height:20px;"/></td>
    					</tr>
    					<tr>
    						<td>中转</td>
    						<td><input type="text" name="zgdmZz_edit" class="easyui-textbox"  data-options="required:'true',validType:'length[0,40]'"  style="width:150px;height:20px;"/></td>
    					</tr>
		</table>
</form> 

<script type="text/javascript"	src="<c:url value='/resources/js/zgdm.js'/>"></script>

	
