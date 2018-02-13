<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<table id="bx"></table>

<div id="bx_tool" style="padding:5px;">
	<div style="margig-bottom:5px;">
		<!-- <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onClick="bx_tool.add();">添加采购</a> -->
		
		<!-- <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onClick="bx_tool.edit();">修改</a> -->
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onClick="bx_tool.remove();">报销</a>
		<input id="bxDate_bx" name="bxDate_bx" type="text" class="easyui-datebox" required="required" value="true" data-options="
		onChange: function (n,o) {

			bx_tool.find();

		}"></input>
		<select id="bx_scid" name="bx_scid" class="easyui-combobox" style="width:70px"   
        								data-options="valueField:'scId',textField:'scName',url:'/cbhs/hsf/getScAll', editable:false">
        	
        </select>
        <select id="bx_shid" name="bx_shid" class="easyui-combobox" style="width:150px"   
        								data-options="valueField:'shId',textField:'shName',url:'/cbhs/hsf/getShAll', editable:false"></select>
        <select id="bx_sj" name="bx_sj" class="easyui-combobox" style="width:70px"   
        								data-options="editable:false">
        	<option ></option>
        	<option value="1">有收据</option>
			<option value="0">无收据</option>
        </select>
		<!-- <input type="radio"  onChange="bx_tool.find();" name="bxTime_bx" checked="checked" value="1">白班
		<input type="radio" onChange="bx_tool.find();" name="infonumber_bx" value="15">15点
		<input type="radio"  onChange="bx_tool.find();" name="bxTime_bx" value="0">夜班 -->
		
		<c:if test="${sessionScope.sessionUser.quanxian == 11 || sessionScope.sessionUser.quanxian == 12 }">
		
		
			<input type="text" id="depId_bx" name="depId_bx" class="easyui-combobox"  
	    					 data-options="valueField:'departmentId',
	    					 required:'true',
	    					 textField:'departmentName',
	    					 url:'/cbhs/hsf/getDepAllSelect',
	    					 filter: function(q, row){  
	            				var opts = $(this).combobox('options');  
	            				return row[opts.textField].indexOf(q) >-1;;  
	        				},
	        				onSelect:function(record) {  
	            				$('depId_bx').focus();  
	        				}"   
	    					 																									  
	    					 style="width:150px;height:20px;"/>
		</c:if>
		
		
		<a href="#" class="easyui-linkbutton" iconCls="icon-page-find" plain="true" onClick="bx_tool.find();">查询</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-page-find" plain="true" onClick="bx_tool.delAll();">清空</a>
	</div>
</div>


<form id="bx_add" style="margin:0;padding:5p 0 0 25px;color:#333;"> 
		<table id="bx_add_table" style="border-collapse:separate;border-spacing:10px;display:none;" >
    					<tr>
    						<td>采购日期</td>
    						<td><input id="bx_date_add" name="bx_date_add" class="easyui-datebox" data-options="required:'true',validType:'datevalue',value:'true'" style="width:150px;height:20px;"/></td>
    					</tr>
    					<tr>
    						<td>食材名称</td>
    						<td><select id="bx_scid_add" name="bx_scid_add" class="easyui-combobox" style="width:150px"   
        								data-options="valueField:'scId',textField:'scName',url:'/cbhs/hsf/getScAll',required:true, editable:false"></select></td>
    					</tr>
    					<tr>
    						<td>数量</td>
    						<td><input type="text" name="bx_sl_add" class="easyui-textbox"  data-options="required:'true',validType:'intcount[8]'"  style="width:150px;height:20px;"/></td>
    					</tr>
    					<tr>
    						<td>金额（元）</td>
    						<td><input type="text" name="bx_money_add" class="easyui-textbox"  data-options="required:'true',validType:['validateFloat','length[0,10]']"  style="width:150px;height:20px;"/></td>
    					</tr>
    					<tr>
    						<td>商户</td>
    						<td><select id="bx_shid_add" name="bx_shid_add" class="easyui-combobox" style="width:150px"   
        								data-options="valueField:'shId',textField:'shName',url:'/cbhs/hsf/getShAll',required:true, editable:false"></select></td>
    					</tr>
    					<tr>
    						<td>收据</td>
    						<td><input type="radio" name="bx_sj_add" value="1">有
								<input type="radio" name="bx_sj_add" value="0" checked="checked">无
								
							</td>
						</tr>
    					<!-- 
    						<td>时间</td>
    						<td><input type="radio" name="bxTime_bx_add" checked="checked" value="1">白班
								<input type="radio" name="number_bx_add" value="15">15点
								<input type="radio" name="bxTime_bx_add" value="0">夜班
							</td>
    					 -->
    					<!-- <tr>
    						<td>类别</td>
    						<td>
    							<input type="radio" name="bxType_add" checked="checked" value="1">甩车
								<input type="radio" name="bxType_add" value="0">挂车
							</td>
    					</tr>
    					<tr>
    						<td>车次</td>
    						<td><input type="text" name="bxCc_add" class="easyui-textbox" data-options="required:'true',validType:'account[2,10]'"  style="width:150px;height:20px;"/></td>
    						
    						<td><input type="text" id="bxCc_add" name="bxCc_add" class="easyui-combobox"  
    					 data-options="valueField:'bxCc',
    					 required:'true',
    					 textField:'bxCc',
    					 url:'/bx/getAllbxCc',
    					 filter: function(q, row){  
            				var opts = $(this).combobox('options');  
            				return row[opts.textField].indexOf(q) >-1;;  
        				},
        				onSelect:function(record) {  
            				$('bxCc_add').focus();  
        				}"   
    					 																									  
    					 style="width:150px;height:20px;"/></td>
    					</tr>
    					<tr>
    						<td>时间</td>
    						<td><input type="text" name="bxSj_add" class="easyui-datetimebox"  data-options="required:'true',showSeconds:false,value:'true',validType:'datetimevalue'"  style="width:150px;height:20px;"/></td>
    					</tr>
    					<tr>
    						<td>车数</td>
    						<td><input type="text" name="bxCs_add" class="easyui-textbox"  data-options="required:'true',validType:'intcount[4]'"  style="width:150px;height:20px;"/></td>
    					</tr>
    					<tr>
    						<td>中转</td>
    						<td><input type="text" name="bxZz_add" class="easyui-textbox"  data-options="required:'true',validType:'length[0,40]'"  style="width:150px;height:20px;"/></td>
    					</tr> -->
 		</table>
</form> 



<!-- 删除edit表的日期组件的 value:'true'属性   -->
<form id="bx_edit" style="margin:0;padding:5p 0 0 25px;color:#333;"> 
		<input type="hidden" name="bxId_edit" class="textbox" />
		
		<table id="bx_edit_table" style="border-collapse:separate;border-spacing:10px;display:none;">
    					<tr>
    						<td>日期</td>
    						<td><input id="bxDate_bx_edit" name="bxDate_bx_edit" class="easyui-datebox" data-options="required:'true',validType:'datevalue',value:'true'" style="width:150px;height:20px;"/></td>
    					</tr>
    					<tr>
    						<td>时间</td>
    						<td><input type="radio" name="bxTime_bx_edit" checked="checked" value="1">白班
								<!-- <input type="radio" name="number_bx_add" value="15">15点 -->
								<input type="radio" name="bxTime_bx_edit" value="0">夜班
							</td>
    					</tr>
		</table>
</form> 

<script type="text/javascript"	src="<c:url value='/resources/js/bx.js'/>"></script>

	
