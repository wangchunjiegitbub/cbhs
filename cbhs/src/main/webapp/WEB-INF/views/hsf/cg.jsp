<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<table id="cg"></table>

<div id="cg_tool" style="padding:5px;">
	<div style="margig-bottom:5px;">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onClick="cg_tool.add();">添加采购</a>
		
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onClick="cg_tool.edit();">修改</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-DeleteRed" plain="true" onClick="cg_tool.remove();">删除</a>
		<input id="cgDate_cg_begin" name="cgDate_cg_begin" type="text" class="easyui-datebox" required="required" value="true" data-options="
		onChange: function (n,o) {

			cg_tool.find();

		}"></input>-
		<input id="cgDate_cg_end" name="cgDate_cg_end" type="text" class="easyui-datebox" required="required" value="true" data-options="
		onChange: function (n,o) {

			cg_tool.find();

		}"></input>
		<!-- <input type="radio"  onChange="cg_tool.find();" name="cgTime_cg" checked="checked" value="1">白班
		<input type="radio" onChange="cg_tool.find();" name="infonumber_cg" value="15">15点
		<input type="radio"  onChange="cg_tool.find();" name="cgTime_cg" value="0">夜班 -->
		<c:if test="${sessionScope.sessionUser.quanxian == 11 || sessionScope.sessionUser.quanxian == 12 }">
			<input type="text" id="depId_cg" name="depId_cg" class="easyui-combobox"  
	    					 data-options="valueField:'departmentId',
	    					 required:'true',
	    					 textField:'departmentName',
	    					 url:'/cbhs/hsf/getDepAllSelect',
	    					 filter: function(q, row){  
	            				var opts = $(this).combobox('options');  
	            				return row[opts.textField].indexOf(q) >-1;;  
	        				},
	        				onSelect:function(record) {  
	            				$('depId_cg').focus();  
	        				}"   
	    					 																									  
	    					 style="width:150px;height:20px;"/>
    	</c:if>
		<a href="#" class="easyui-linkbutton" iconCls="icon-page-find" plain="true" onClick="cg_tool.find();">查询</a>
	</div>
</div>


<form id="cg_add" style="margin:0;padding:5p 0 0 25px;color:#333;"> 
		<table id="cg_add_table" style="border-collapse:separate;border-spacing:10px;display:none;" >
    					<tr>
    						<td>采购日期</td>
    						<td><input id="cg_date_add" name="cg_date_add" class="easyui-datebox" data-options="required:'true',validType:'datevalue',value:'true'" style="width:150px;height:20px;"/></td>
    					</tr>
    					<tr>
    						<td>食材名称</td>
    						<td><select id="cg_scid_add" name="cg_scid_add" class="easyui-combobox" style="width:150px"   
        								data-options="valueField:'scId',textField:'scName',url:'/cbhs/hsf/getScAll',required:true, editable:false"></select></td>
    					</tr>
    					<tr>
    						<td>数量</td>
    						<td><input type="text" name="cg_sl_add" class="easyui-textbox"  data-options="required:'true',validType:['validateFloat','length[0,9]','zeroTest']"  style="width:150px;height:20px;"/></td>
    					</tr>
    					<tr>
    						<td>单位</td>
    						<td>
    							<select id="cg_dw_add" name="cg_dw_add" class="easyui-combobox" style="width:150px"   
        								data-options="required:true, editable:false">
        								<option value="斤">斤</option>
										<option value="升">升</option>
										<option value="克">克</option>
										<option value="个">个</option>
										<option value="罐">罐</option>
										
        						</select>
        					</td>
    					</tr>
    					<tr>
    						<td>金额（元）</td>
    						<td><input type="text" name="cg_money_add" class="easyui-textbox"  data-options="required:'true',validType:['validateFloat','length[0,9]']"  style="width:150px;height:20px;"/></td>
    					</tr>
    					<tr>
    						<td>商户</td>
    						<td><select id="cg_shid_add" name="cg_shid_add" class="easyui-combobox" style="width:150px"   
        								data-options="valueField:'shId',textField:'shName',url:'/cbhs/hsf/getShAll',required:true, editable:false"></select></td>
    					</tr>
    					<tr>
    						<td>收据</td>
    						<td><input type="radio" name="cg_sj_add" value="1">有
								<input type="radio" name="cg_sj_add" value="0" checked="checked">无
								
							</td>
						</tr>
    					<!-- 
    						<td>时间</td>
    						<td><input type="radio" name="cgTime_cg_add" checked="checked" value="1">白班
								<input type="radio" name="number_cg_add" value="15">15点
								<input type="radio" name="cgTime_cg_add" value="0">夜班
							</td>
    					 -->
    					<!-- <tr>
    						<td>类别</td>
    						<td>
    							<input type="radio" name="cgType_add" checked="checked" value="1">甩车
								<input type="radio" name="cgType_add" value="0">挂车
							</td>
    					</tr>
    					<tr>
    						<td>车次</td>
    						<td><input type="text" name="cgCc_add" class="easyui-textbox" data-options="required:'true',validType:'account[2,10]'"  style="width:150px;height:20px;"/></td>
    						
    						<td><input type="text" id="cgCc_add" name="cgCc_add" class="easyui-combobox"  
    					 data-options="valueField:'cgCc',
    					 required:'true',
    					 textField:'cgCc',
    					 url:'/cg/getAllcgCc',
    					 filter: function(q, row){  
            				var opts = $(this).combobox('options');  
            				return row[opts.textField].indexOf(q) >-1;;  
        				},
        				onSelect:function(record) {  
            				$('cgCc_add').focus();  
        				}"   
    					 																									  
    					 style="width:150px;height:20px;"/></td>
    					</tr>
    					<tr>
    						<td>时间</td>
    						<td><input type="text" name="cgSj_add" class="easyui-datetimebox"  data-options="required:'true',showSeconds:false,value:'true',validType:'datetimevalue'"  style="width:150px;height:20px;"/></td>
    					</tr>
    					<tr>
    						<td>车数</td>
    						<td><input type="text" name="cgCs_add" class="easyui-textbox"  data-options="required:'true',validType:'intcount[4]'"  style="width:150px;height:20px;"/></td>
    					</tr>
    					<tr>
    						<td>中转</td>
    						<td><input type="text" name="cgZz_add" class="easyui-textbox"  data-options="required:'true',validType:'length[0,40]'"  style="width:150px;height:20px;"/></td>
    					</tr> -->
 		</table>
</form> 



<!-- 删除edit表的日期组件的 value:'true'属性   -->
<form id="cg_edit" style="margin:0;padding:5p 0 0 25px;color:#333;"> 
		<input type="hidden" name="cg_id_edit" class="textbox" />				
		
		<table id="cg_edit_table" style="border-collapse:separate;border-spacing:10px;display:none;" >
    					<tr>
    						<td>采购日期</td>
    						<td><input id="cg_date_edit" name="cg_date_edit" class="easyui-datebox" data-options="required:'true',validType:'datevalue'" style="width:150px;height:20px;"/></td>
    					</tr>
    					<tr>
    						<td>食材名称</td>
    						<td><select id="cg_scid_edit" name="cg_scid_edit" class="easyui-combobox" style="width:150px"   
        								data-options="valueField:'scId',textField:'scName',url:'/cbhs/hsf/getScAll',required:true, editable:false"></select></td>
    					</tr>
    					<tr>
    						<td>数量</td>
    						<td><input type="text" name="cg_sl_edit" class="easyui-textbox"  data-options="required:'true',validType:['validateFloat','length[0,9]','zeroTest']"  style="width:150px;height:20px;"/></td>
    					</tr>
    					<tr>
    						<td>单位</td>
    						<td>
    							<select id="cg_dw_edit" name="cg_dw_edit" class="easyui-combobox" style="width:150px"   
        								data-options="required:true, editable:false">
        								<option value="斤">斤</option>
										<option value="升">升</option>
										<option value="克">克</option>
										<option value="个">个</option>
										<option value="罐">罐</option>
										
        						</select>
        					</td>
    					</tr>
    					<tr>
    						<td>金额（元）</td>
    						<td><input type="text" name="cg_money_edit" class="easyui-textbox"  data-options="required:'true',validType:['validateFloat','length[0,9]']"  style="width:150px;height:20px;"/></td>
    					</tr>
    					<tr>
    						<td>商户</td>
    						<td><select id="cg_shid_edit" name="cg_shid_edit" class="easyui-combobox" style="width:150px"   
        								data-options="valueField:'shId',textField:'shName',url:'/cbhs/hsf/getShAll',required:true, editable:false"></select></td>
    					</tr>
    					<tr>
    						<td>收据</td>
    						<td><input type="radio" name="cg_sj_edit" value="1">有
								<input type="radio" name="cg_sj_edit" value="0" checked="checked">无
								
							</td>
						</tr>
    					
 		</table>
</form> 

<script type="text/javascript"	src="<c:url value='/resources/js/cg.js'/>"></script>

	
