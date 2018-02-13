<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<table id="jz"></table>

<div id="jz_tool" style="padding:5px;">
	<div style="margig-bottom:5px;">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onClick="jz_tool.add();">新增结账</a>
		
		<!-- <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onClick="jz_tool.edit();">修改</a> -->
		<c:if test="${sessionScope.sessionUser.quanxian == 11 || sessionScope.sessionUser.quanxian == 12 }">
			<a href="#" class="easyui-linkbutton" iconCls="icon-DeleteRed" plain="true" onClick="jz_tool.remove();">删除</a>
		</c:if>
		<input id="jzDate_jz" name="jzDate_jz" type="text" class="easyui-datebox" required="required" value="true" data-options="
		onChange: function (n,o) {

			jz_tool.find();

		}"></input>
		<!-- <input type="radio"  onChange="jz_tool.find();" name="jzTime_jz" checked="checked" value="1">白班
		<input type="radio" onChange="jz_tool.find();" name="infonumber_jz" value="15">15点
		<input type="radio"  onChange="jz_tool.find();" name="jzTime_jz" value="0">夜班 -->
		<c:if test="${sessionScope.sessionUser.quanxian == 11 || sessionScope.sessionUser.quanxian == 12 }">
		
		
			<input type="text" id="depId_jz" name="depId_jz" class="easyui-combobox"  
	    					 data-options="valueField:'departmentId',
	    					 required:'true',
	    					 textField:'departmentName',
	    					 url:'/cbhs/hsf/getDepAllSelect',
	    					 filter: function(q, row){  
	            				var opts = $(this).combobox('options');  
	            				return row[opts.textField].indexOf(q) >-1;;  
	        				},
	        				onSelect:function(record) {  
	            				$('depId_jz').focus();  
	        				}"   
	    					 																									  
	    					 style="width:150px;height:20px;"/>
		</c:if>
		
		<a href="#" class="easyui-linkbutton" iconCls="icon-page-find" plain="true" onClick="jz_tool.find();">查询</a>
	</div>
</div>


<form id="jz_add" style="margin:0;padding:5p 0 0 25px;color:#333;"> 
		<table id="jz_add_table" style="border-collapse:separate;border-spacing:10px;display:none;" >
    					<tr>
    						<td>结账日期</td>
    						<td><input id="jz_date_add" name="jz_date_add" class="easyui-datebox" data-options="required:'true',validType:'datevalue',value:'true'" style="width:150px;height:20px;"/></td>
    					</tr>
    					<tr>
    						<td>食材名称</td>
    						<td><select id="jz_scid_add" name="jz_scid_add" class="easyui-combobox" style="width:150px"   
        								data-options="valueField:'scId',textField:'scName',url:'/cbhs/hsf/getScAll',required:true, editable:false"></select></td>
    					</tr>
    					<tr>
    						<td>数量</td>
    						<td><input type="text" name="jz_sl_add" class="easyui-textbox"  data-options="required:'true',validType:'intcount[8]'"  style="width:150px;height:20px;"/></td>
    					</tr>
    					<tr>
    						<td>金额（元）</td>
    						<td><input type="text" name="jz_money_add" class="easyui-textbox"  data-options="required:'true',validType:['validateFloat','length[0,10]']"  style="width:150px;height:20px;"/></td>
    					</tr>
    					<tr>
    						<td>商户</td>
    						<td><select id="jz_shid_add" name="jz_shid_add" class="easyui-combobox" style="width:150px"   
        								data-options="valueField:'shId',textField:'shName',url:'/cbhs/hsf/getShAll',required:true, editable:false"></select></td>
    					</tr>
    					<tr>
    						<td>收据</td>
    						<td><input type="radio" name="jz_sj_add" value="1">有
								<input type="radio" name="jz_sj_add" value="0" checked="checked">无
								
							</td>
						</tr>
    					<!-- 
    						<td>时间</td>
    						<td><input type="radio" name="jzTime_jz_add" checked="checked" value="1">白班
								<input type="radio" name="number_jz_add" value="15">15点
								<input type="radio" name="jzTime_jz_add" value="0">夜班
							</td>
    					 -->
    					<!-- <tr>
    						<td>类别</td>
    						<td>
    							<input type="radio" name="jzType_add" checked="checked" value="1">甩车
								<input type="radio" name="jzType_add" value="0">挂车
							</td>
    					</tr>
    					<tr>
    						<td>车次</td>
    						<td><input type="text" name="jzCc_add" class="easyui-textbox" data-options="required:'true',validType:'account[2,10]'"  style="width:150px;height:20px;"/></td>
    						
    						<td><input type="text" id="jzCc_add" name="jzCc_add" class="easyui-combobox"  
    					 data-options="valueField:'jzCc',
    					 required:'true',
    					 textField:'jzCc',
    					 url:'/jz/getAlljzCc',
    					 filter: function(q, row){  
            				var opts = $(this).combobox('options');  
            				return row[opts.textField].indexOf(q) >-1;;  
        				},
        				onSelect:function(record) {  
            				$('jzCc_add').focus();  
        				}"   
    					 																									  
    					 style="width:150px;height:20px;"/></td>
    					</tr>
    					<tr>
    						<td>时间</td>
    						<td><input type="text" name="jzSj_add" class="easyui-datetimebox"  data-options="required:'true',showSeconds:false,value:'true',validType:'datetimevalue'"  style="width:150px;height:20px;"/></td>
    					</tr>
    					<tr>
    						<td>车数</td>
    						<td><input type="text" name="jzCs_add" class="easyui-textbox"  data-options="required:'true',validType:'intcount[4]'"  style="width:150px;height:20px;"/></td>
    					</tr>
    					<tr>
    						<td>中转</td>
    						<td><input type="text" name="jzZz_add" class="easyui-textbox"  data-options="required:'true',validType:'length[0,40]'"  style="width:150px;height:20px;"/></td>
    					</tr> -->
 		</table>
</form> 



<!-- 删除edit表的日期组件的 value:'true'属性   -->
<form id="jz_edit" style="margin:0;padding:5p 0 0 25px;color:#333;"> 
		<input type="hidden" name="jzId_edit" class="textbox" />
		
		<table id="jz_edit_table" style="border-collapse:separate;border-spacing:10px;display:none;">
    					<tr>
    						<td>日期</td>
    						<td><input id="jzDate_jz_edit" name="jzDate_jz_edit" class="easyui-datebox" data-options="required:'true',validType:'datevalue',value:'true'" style="width:150px;height:20px;"/></td>
    					</tr>
    					<tr>
    						<td>时间</td>
    						<td><input type="radio" name="jzTime_jz_edit" checked="checked" value="1">白班
								<!-- <input type="radio" name="number_jz_add" value="15">15点 -->
								<input type="radio" name="jzTime_jz_edit" value="0">夜班
							</td>
    					</tr>
		</table>
</form> 

<script type="text/javascript"	src="<c:url value='/resources/js/jz.js'/>"></script>

	
