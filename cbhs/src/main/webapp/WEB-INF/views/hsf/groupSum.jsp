<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<table id="groupSum"></table>

<div id="groupSum_tool" style="padding:5px;">
	<div style="margig-bottom:5px;">
		<form id="formQueryGroup" method="post">
			<!-- <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onClick="groupSum_tool.add();">添加采购</a>
			
			<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onClick="groupSum_tool.edit();">修改</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-DeleteRed" plain="true" onClick="groupSum_tool.remove();">删除</a> -->
			<input id="groupSumDate_groupSum" name="groupSumDate_groupSum" type="text" class="easyui-datebox" required="required" value="true" data-options="
			onChange: function (n,o) {
	
				groupSum_tool.find();
	
			}"></input>
			<select id="groupSum_shid" name="groupSum_shid" class="easyui-combobox" style="width:150px"   
	        								data-options="valueField:'shId',textField:'shName',url:'/cbhs/hsf/getShAll', editable:false"></select>
			<!-- <input type="radio"  onChange="groupSum_tool.find();" name="groupSumTime_groupSum" checked="checked" value="1">白班
			<input type="radio" onChange="groupSum_tool.find();" name="infonumber_groupSum" value="15">15点
			<input type="radio"  onChange="groupSum_tool.find();" name="groupSumTime_groupSum" value="0">夜班 -->
			<c:if test="${sessionScope.sessionUser.quanxian == 11 || sessionScope.sessionUser.quanxian == 12 }">
		
		
				<input type="text" id="depId_groupSum" name="depId_groupSum" class="easyui-combobox"  
		    					 data-options="valueField:'departmentId',
		    					 required:'true',
		    					 textField:'departmentName',
		    					 url:'/cbhs/hsf/getDepAllSelect',
		    					 filter: function(q, row){  
		            				var opts = $(this).combobox('options');  
		            				return row[opts.textField].indexOf(q) >-1;;  
		        				},
		        				onSelect:function(record) {  
		            				$('depId_groupSum').focus();  
		        				}"   
		    					 																									  
		    					 style="width:150px;height:20px;"/>
			</c:if>
		
			<a href="#" class="easyui-linkbutton" iconCls="icon-page-find" plain="true" onClick="groupSum_tool.delAll();">清空</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-page-find" plain="true" onClick="groupSum_tool.find();">查询</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-save" plain="true" onClick="groupSum_tool.exportData();">导出</a>
		</form>
	</div>
</div>


<form id="groupSum_add" style="margin:0;padding:5p 0 0 25px;color:#333;"> 
		<table id="groupSum_add_table" style="border-collapse:separate;border-spacing:10px;display:none;" >
    					<tr>
    						<td>采购日期</td>
    						<td><input id="groupSum_date_add" name="groupSum_date_add" class="easyui-datebox" data-options="required:'true',validType:'datevalue',value:'true'" style="width:150px;height:20px;"/></td>
    					</tr>
    					<tr>
    						<td>食材名称</td>
    						<td><select id="groupSum_scid_add" name="groupSum_scid_add" class="easyui-combobox" style="width:150px"   
        								data-options="valueField:'scId',textField:'scName',url:'/cbhs/hsf/getScAll',required:true, editable:false"></select></td>
    					</tr>
    					<tr>
    						<td>数量</td>
    						<td><input type="text" name="groupSum_sl_add" class="easyui-textbox"  data-options="required:'true',validType:'intcount[8]'"  style="width:150px;height:20px;"/></td>
    					</tr>
    					<tr>
    						<td>单位</td>
    						<td>
    							<select id="groupSum_dw_add" name="groupSum_dw_add" class="easyui-combobox" style="width:150px"   
        								data-options="required:true, editable:false">
        								<option value="斤">斤</option>
										<option value="公斤">公斤</option>
										<option value="袋">袋</option>
										<option value="升">升</option>
										
		
        						</select>
        					</td>
    					</tr>
    					<tr>
    						<td>金额（元）</td>
    						<td><input type="text" name="groupSum_money_add" class="easyui-textbox"  data-options="required:'true',validType:['validateFloat','length[0,10]']"  style="width:150px;height:20px;"/></td>
    					</tr>
    					<tr>
    						<td>商户</td>
    						<td><select id="groupSum_shid_add" name="groupSum_shid_add" class="easyui-combobox" style="width:150px"   
        								data-options="valueField:'shId',textField:'shName',url:'/cbhs/hsf/getShAll',required:true, editable:false"></select></td>
    					</tr>
    					<tr>
    						<td>收据</td>
    						<td><input type="radio" name="groupSum_sj_add" value="1">有
								<input type="radio" name="groupSum_sj_add" value="0" checked="checked">无
								
							</td>
						</tr>
    					<!-- 
    						<td>时间</td>
    						<td><input type="radio" name="groupSumTime_groupSum_add" checked="checked" value="1">白班
								<input type="radio" name="number_groupSum_add" value="15">15点
								<input type="radio" name="groupSumTime_groupSum_add" value="0">夜班
							</td>
    					 -->
    					<!-- <tr>
    						<td>类别</td>
    						<td>
    							<input type="radio" name="groupSumType_add" checked="checked" value="1">甩车
								<input type="radio" name="groupSumType_add" value="0">挂车
							</td>
    					</tr>
    					<tr>
    						<td>车次</td>
    						<td><input type="text" name="groupSumCc_add" class="easyui-textbox" data-options="required:'true',validType:'account[2,10]'"  style="width:150px;height:20px;"/></td>
    						
    						<td><input type="text" id="groupSumCc_add" name="groupSumCc_add" class="easyui-combobox"  
    					 data-options="valueField:'groupSumCc',
    					 required:'true',
    					 textField:'groupSumCc',
    					 url:'/groupSum/getAllgroupSumCc',
    					 filter: function(q, row){  
            				var opts = $(this).combobox('options');  
            				return row[opts.textField].indexOf(q) >-1;;  
        				},
        				onSelect:function(record) {  
            				$('groupSumCc_add').focus();  
        				}"   
    					 																									  
    					 style="width:150px;height:20px;"/></td>
    					</tr>
    					<tr>
    						<td>时间</td>
    						<td><input type="text" name="groupSumSj_add" class="easyui-datetimebox"  data-options="required:'true',showSeconds:false,value:'true',validType:'datetimevalue'"  style="width:150px;height:20px;"/></td>
    					</tr>
    					<tr>
    						<td>车数</td>
    						<td><input type="text" name="groupSumCs_add" class="easyui-textbox"  data-options="required:'true',validType:'intcount[4]'"  style="width:150px;height:20px;"/></td>
    					</tr>
    					<tr>
    						<td>中转</td>
    						<td><input type="text" name="groupSumZz_add" class="easyui-textbox"  data-options="required:'true',validType:'length[0,40]'"  style="width:150px;height:20px;"/></td>
    					</tr> -->
 		</table>
</form> 



<!-- 删除edit表的日期组件的 value:'true'属性   -->
<form id="groupSum_edit" style="margin:0;padding:5p 0 0 25px;color:#333;"> 
		<input type="hidden" name="groupSumId_edit" class="textbox" />
		
		<table id="groupSum_edit_table" style="border-collapse:separate;border-spacing:10px;display:none;">
    					<tr>
    						<td>日期</td>
    						<td><input id="groupSumDate_groupSum_edit" name="groupSumDate_groupSum_edit" class="easyui-datebox" data-options="required:'true',validType:'datevalue',value:'true'" style="width:150px;height:20px;"/></td>
    					</tr>
    					<tr>
    						<td>时间</td>
    						<td><input type="radio" name="groupSumTime_groupSum_edit" checked="checked" value="1">白班
								<!-- <input type="radio" name="number_groupSum_add" value="15">15点 -->
								<input type="radio" name="groupSumTime_groupSum_edit" value="0">夜班
							</td>
    					</tr>
		</table>
</form> 

<script type="text/javascript"	src="<c:url value='/resources/js/groupSum.js'/>"></script>

	
