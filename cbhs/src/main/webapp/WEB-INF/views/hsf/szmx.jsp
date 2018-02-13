<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<table id="szmx"></table>

<div id="szmx_tool" style="padding:5px;">
	<div style="margig-bottom:5px;">
		<form id="formQuery" method="post">
		<!-- <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onClick="szmx_tool.add();">添加采购</a>
		
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onClick="szmx_tool.edit();">修改</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-DeleteRed" plain="true" onClick="szmx_tool.remove();">删除</a> -->
		<input id="szmxDate_szmx" name="szmxDate_szmx" type="text" class="easyui-datebox" required="required" value="true" data-options="
		onChange: function (n,o) {

			szmx_tool.find();

		}"></input>
		<!-- <input type="radio"  onChange="szmx_tool.find();" name="szmxTime_szmx" checked="checked" value="1">白班
		<input type="radio" onChange="szmx_tool.find();" name="infonumber_szmx" value="15">15点
		<input type="radio"  onChange="szmx_tool.find();" name="szmxTime_szmx" value="0">夜班 -->
		<c:if test="${sessionScope.sessionUser.quanxian == 11 || sessionScope.sessionUser.quanxian == 12 }">
		
		
			<input type="text" id="depId_szmx" name="depId_szmx" class="easyui-combobox"  
	    					 data-options="valueField:'departmentId',
	    					 required:'true',
	    					 textField:'departmentName',
	    					 url:'/cbhs/hsf/getDepAllSelect',
	    					 filter: function(q, row){  
	            				var opts = $(this).combobox('options');  
	            				return row[opts.textField].indexOf(q) >-1;;  
	        				},
	        				onSelect:function(record) {  
	            				$('depId_szmx').focus();  
	        				}"   
	    					 																									  
	    					 style="width:150px;height:20px;"/>
		</c:if>
		<a href="#" class="easyui-linkbutton" iconCls="icon-page-find" plain="true" onClick="szmx_tool.find();">查询</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-save" plain="true" onClick="szmx_tool.exportData();">导出</a>
		
		</form>
	</div>
</div>


<form id="szmx_add" style="margin:0;padding:5p 0 0 25px;color:#333;"> 
		<table id="szmx_add_table" style="border-collapse:separate;border-spacing:10px;display:none;" >
    					<tr>
    						<td>采购日期</td>
    						<td><input id="szmx_date_add" name="szmx_date_add" class="easyui-datebox" data-options="required:'true',validType:'datevalue',value:'true'" style="width:150px;height:20px;"/></td>
    					</tr>
    					<tr>
    						<td>食材名称</td>
    						<td><select id="szmx_scid_add" name="szmx_scid_add" class="easyui-combobox" style="width:150px"   
        								data-options="valueField:'scId',textField:'scName',url:'/cbhs/hsf/getScAll',required:true, editable:false"></select></td>
    					</tr>
    					<tr>
    						<td>数量</td>
    						<td><input type="text" name="szmx_sl_add" class="easyui-textbox"  data-options="required:'true',validType:'intcount[8]'"  style="width:150px;height:20px;"/></td>
    					</tr>
    					<tr>
    						<td>金额（元）</td>
    						<td><input type="text" name="szmx_money_add" class="easyui-textbox"  data-options="required:'true',validType:['validateFloat','length[0,10]']"  style="width:150px;height:20px;"/></td>
    					</tr>
    					<tr>
    						<td>商户</td>
    						<td><select id="szmx_shid_add" name="szmx_shid_add" class="easyui-combobox" style="width:150px"   
        								data-options="valueField:'shId',textField:'shName',url:'/cbhs/hsf/getShAll',required:true, editable:false"></select></td>
    					</tr>
    					<tr>
    						<td>收据</td>
    						<td><input type="radio" name="szmx_sj_add" value="1">有
								<input type="radio" name="szmx_sj_add" value="0" checked="checked">无
								
							</td>
						</tr>
    					<!-- 
    						<td>时间</td>
    						<td><input type="radio" name="szmxTime_szmx_add" checked="checked" value="1">白班
								<input type="radio" name="number_szmx_add" value="15">15点
								<input type="radio" name="szmxTime_szmx_add" value="0">夜班
							</td>
    					 -->
    					<!-- <tr>
    						<td>类别</td>
    						<td>
    							<input type="radio" name="szmxType_add" checked="checked" value="1">甩车
								<input type="radio" name="szmxType_add" value="0">挂车
							</td>
    					</tr>
    					<tr>
    						<td>车次</td>
    						<td><input type="text" name="szmxCc_add" class="easyui-textbox" data-options="required:'true',validType:'account[2,10]'"  style="width:150px;height:20px;"/></td>
    						
    						<td><input type="text" id="szmxCc_add" name="szmxCc_add" class="easyui-combobox"  
    					 data-options="valueField:'szmxCc',
    					 required:'true',
    					 textField:'szmxCc',
    					 url:'/szmx/getAllszmxCc',
    					 filter: function(q, row){  
            				var opts = $(this).combobox('options');  
            				return row[opts.textField].indexOf(q) >-1;;  
        				},
        				onSelect:function(record) {  
            				$('szmxCc_add').focus();  
        				}"   
    					 																									  
    					 style="width:150px;height:20px;"/></td>
    					</tr>
    					<tr>
    						<td>时间</td>
    						<td><input type="text" name="szmxSj_add" class="easyui-datetimebox"  data-options="required:'true',showSeconds:false,value:'true',validType:'datetimevalue'"  style="width:150px;height:20px;"/></td>
    					</tr>
    					<tr>
    						<td>车数</td>
    						<td><input type="text" name="szmxCs_add" class="easyui-textbox"  data-options="required:'true',validType:'intcount[4]'"  style="width:150px;height:20px;"/></td>
    					</tr>
    					<tr>
    						<td>中转</td>
    						<td><input type="text" name="szmxZz_add" class="easyui-textbox"  data-options="required:'true',validType:'length[0,40]'"  style="width:150px;height:20px;"/></td>
    					</tr> -->
 		</table>
</form> 



<!-- 删除edit表的日期组件的 value:'true'属性   -->
<form id="szmx_edit" style="margin:0;padding:5p 0 0 25px;color:#333;"> 
		<input type="hidden" name="szmxId_edit" class="textbox" />
		
		<table id="szmx_edit_table" style="border-collapse:separate;border-spacing:10px;display:none;">
    					<tr>
    						<td>日期</td>
    						<td><input id="szmxDate_szmx_edit" name="szmxDate_szmx_edit" class="easyui-datebox" data-options="required:'true',validType:'datevalue',value:'true'" style="width:150px;height:20px;"/></td>
    					</tr>
    					<tr>
    						<td>时间</td>
    						<td><input type="radio" name="szmxTime_szmx_edit" checked="checked" value="1">白班
								<!-- <input type="radio" name="number_szmx_add" value="15">15点 -->
								<input type="radio" name="szmxTime_szmx_edit" value="0">夜班
							</td>
    					</tr>
		</table>
</form> 

<script type="text/javascript"	src="<c:url value='/resources/js/szmx.js'/>"></script>

	
