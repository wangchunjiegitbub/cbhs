<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- -------------------------------------------------------------------------------------------------------------- -->


<div class="easyui-layout" fit="true">
			
			<div region="west" split="true" style="width:50%;">
				<table id="ryqr"></table>
			</div>
			<div region="center" split="true">
				<table id="ryqr_zgdm"></table>
			</div>
			
</div>

<!-- -------------------------------------------------------------------------------------------------------------- -->

<div id="ryqr_tool" style="padding:5px;">
	<div style="margig-bottom:5px;">
		<!-- <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onClick="ryqr_tool.add();">添加</a> -->
		<!-- <a href="#" class="easyui-linkbutton" iconCls="icon-DeleteRed" plain="true" onClick="ryqr_tool.remove();">删除</a> -->
		<input id="dmhDate_ryqr" name="dmhDate_ryqr" type="text" class="easyui-datebox" required="required" value="true" data-options="
		onChange: function (n,o) {

			ryqr_tool.find();
			ryqr_zgdm_tool.find();

		}"></input>
		<input type="radio"  onChange="ryqr_tool.find();ryqr_zgdm_tool.find();" name="dmhTime_ryqr" checked="checked" value="1">白班
		<!-- <input type="radio" onChange="ryqr_tool.find();" name="infonumber_ryqr" value="15">15点 -->
		<input type="radio"  onChange="ryqr_tool.find();ryqr_zgdm_tool.find();" name="dmhTime_ryqr" value="0">夜班
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onClick="ryqr_tool.confirm();">添加</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-page-find" plain="true" onClick="ryqr_tool.find();">本班</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-page-find" plain="true" onClick="ryqr_tool.findCzUserAll();">全站</a>
	</div>
</div>




<div id="ryqr_zgdm_tool" style="padding:5px;">
	<div style="margig-bottom:5px;">
		
		<a href="#" class="easyui-linkbutton" iconCls="icon-DeleteRed" plain="true" onClick="ryqr_tool.remove();">删除</a>
		
		<a href="#" class="easyui-linkbutton" iconCls="icon-page-find" plain="true" onClick="ryqr_zgdm_tool.find();">查询</a>
	</div>
</div>



<form id="ryqr_add" style="margin:0;padding:5p 0 0 25px;color:#333;"> 
		<table id="ryqr_add_table" style="border-collapse:separate;border-spacing:10px;display:none;" >
    					<tr>
    						<td>工作日期</td>
    						<td><input id="rq_ryqr_add" name="rq_ryqr_add" class="easyui-datebox" data-options="required:'true',validType:'datevalue',value:'true'" style="width:150px;height:20px;"/></td>
    					</tr>
    					<tr>
    						<td>结账时间</td>
    						<td><input type="radio" name="number_ryqr_add" checked="checked" value="6"> 6点
								<!-- <input type="radio" name="number_ryqr_add" value="15">15点 -->
								<input type="radio" name="number_ryqr_add" value="18">18点
							</td>
    					</tr>
    					<tr>
    						<td>类别</td>
    						<td>
    							<input type="radio" name="ryqrType_add" checked="checked" value="1">甩车
								<input type="radio" name="ryqrType_add" value="0">挂车
							</td>
    					</tr>
    					<tr>
    						<td>车次</td>
    						<!-- <td><input type="text" name="ryqrCc_add" class="easyui-textbox" data-options="required:'true',validType:'account[2,10]'"  style="width:150px;height:20px;"/></td>
    						 -->
    						<td><input type="text" id="ryqrCc_add" name="ryqrCc_add" class="easyui-combobox"  
    					 data-options="valueField:'ryqrCc',
    					 required:'true',
    					 textField:'ryqrCc',
    					 url:'/dmh/getAllryqrCc',
    					 filter: function(q, row){  
            				var opts = $(this).combobox('options');  
            				return row[opts.textField].indexOf(q) >-1;;  
        				},
        				onSelect:function(record) {  
            				$('ryqrCc_add').focus();  
        				}"   
    					 																									  
    					 style="width:150px;height:20px;"/></td>
    					</tr>
    					<tr>
    						<td>时间</td>
    						<td><input type="text" name="ryqrSj_add" class="easyui-datetimebox"  data-options="required:'true',showSeconds:false,value:'true',validType:'datetimevalue'"  style="width:150px;height:20px;"/></td>
    					</tr>
    					<tr>
    						<td>车数</td>
    						<td><input type="text" name="ryqrCs_add" class="easyui-textbox"  data-options="required:'true',validType:'intcount[4]'"  style="width:150px;height:20px;"/></td>
    					</tr>
    					<tr>
    						<td>中转</td>
    						<td><input type="text" name="ryqrZz_add" class="easyui-textbox"  data-options="required:'true',validType:'length[0,40]'"  style="width:150px;height:20px;"/></td>
    					</tr>
 		</table>
</form> 


<!-- 删除edit表的日期组件的 value:'true'属性   -->
<form id="ryqr_edit" style="margin:0;padding:5p 0 0 25px;color:#333;"> 
		<input type="hidden" name="ryqrId_edit" class="textbox" />
		<input type="hidden" name="ryqr_infoId_edit" class="textbox" />
		<table id="ryqr_edit_table" style="border-collapse:separate;border-spacing:10px;display:none;">
    					<tr>
    						<td>工作日期</td>
    						<td><input id="rq_ryqr_edit" name="rq_ryqr_edit" class="easyui-datebox" data-options="required:'true',validType:'datevalue'" style="width:150px;height:20px;" disabled/></td>
    					</tr>
    					<tr>
    						<td>结账时间</td>
    						<td><input type="radio" name="number_ryqr_edit" checked="checked" value="6" disabled>6点
								<!-- <input type="radio" name="number_ryqr_edit" value="15" disabled>15点 -->
								<input type="radio" name="number_ryqr_edit" value="18" disabled>18点
							</td>
    					</tr>
    					
    					<tr>
    						<td>类别</td>
    						<td>
    							<input type="radio" name="ryqrType_edit" checked="checked" value="1" disabled>甩车
								<input type="radio" name="ryqrType_edit" value="0" disabled>挂车
							</td>
    					</tr>
    					<tr>
    						<td>车次</td>
    						<!-- <td><input type="text" name="ryqrCc_edit" class="easyui-textbox" data-options="required:'true',validType:'account[2,10]'"  style="width:150px;height:20px;"/></td>
    					 -->
    						<td><input type="text" id="ryqrCc_edit" name="ryqrCc_edit" class="easyui-combobox"  
    					 data-options="valueField:'ryqrCc',
    					 required:'true',
    					 textField:'ryqrCc',
    					 url:'/dmh/getAllryqrCc',
    					 filter: function(q, row){  
            				var opts = $(this).combobox('options');  
            				return row[opts.textField].indexOf(q) >-1;;  
        				},
        				onSelect:function(record) {  
            				$('ryqrCc_edit').focus();  
        				}"   
    					 																									  
    					 style="width:150px;height:20px;"/></td>
    					</tr>
    					<tr>
    						<td>时间</td>
    						<td><input type="text" name="ryqrSj_edit" class="easyui-datetimebox"  data-options="required:'true',showSeconds:false,value:'true',validType:'datetimevalue'"  style="width:150px;height:20px;"/></td>
    					</tr>
    					<tr>
    						<td>车数</td>
    						<td><input type="text" name="ryqrCs_edit" class="easyui-textbox"  data-options="required:'true',validType:'intcount[4]'"  style="width:150px;height:20px;"/></td>
    					</tr>
    					<tr>
    						<td>中转</td>
    						<td><input type="text" name="ryqrZz_edit" class="easyui-textbox"  data-options="required:'true',validType:'length[0,40]'"  style="width:150px;height:20px;"/></td>
    					</tr>
		</table>
</form> 

<script type="text/javascript"	src="<c:url value='/resources/js/ryqr.js'/>"></script>

	
