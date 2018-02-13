<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<table id="person"></table>

<div id="person_tool" style="padding:5px;">
	<div style="margig-bottom:5px;">
		<c:if test="${sessionScope.sessionUser.quanxian == 11 || sessionScope.sessionUser.quanxian == 12 }">
			<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onClick="person_tool.shEnter();">审核</a>
		</c:if>
		<!-- <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onClick="person_tool.edit();">修改</a> -->
		<!-- <a href="#" class="easyui-linkbutton" iconCls="icon-DeleteRed" plain="true" onClick="person_tool.remove();">删除</a> -->
		<input id="person_time_begin" name="person_time_begin" type="text" class="easyui-datebox" required="required" value="true" data-options="
		onChange: function (n,o) {

			person_tool.find();

		}"></input>-
		<input id="person_time_end" name="person_time_end" type="text" class="easyui-datebox" required="required" value="true" data-options="
		onChange: function (n,o) {

			person_tool.find();

		}"></input>
		<!-- <input type="radio"  onChange="person_tool.find();" name="personTime_person" checked="checked" value="1">白班
		<input type="radio" onChange="person_tool.find();" name="infonumber_person" value="15">15点
		<input type="radio"  onChange="person_tool.find();" name="personTime_person" value="0">夜班 -->
		<c:if test="${sessionScope.sessionUser.quanxian == 11 || sessionScope.sessionUser.quanxian == 12 }">
		
		
			<input type="text" id="depId_person" name="depId_person" class="easyui-combobox"  
	    					 data-options="valueField:'departmentId',
	    					 required:'true',
	    					 textField:'departmentName',
	    					 url:'/cbhs/hsf/getDepAllSelect',
	    					 filter: function(q, row){  
	            				var opts = $(this).combobox('options');  
	            				return row[opts.textField].indexOf(q) >-1;;  
	        				},
	        				onSelect:function(record) {  
	            				$('depId_person').focus();  
	        				}"   
	    					 																									  
	    					 style="width:150px;height:20px;"/>
		</c:if>
		<a href="#" class="easyui-linkbutton" iconCls="icon-page-find" plain="true" onClick="person_tool.find();">查询</a>
	</div>
</div>


<form id="person_add" style="margin:0;padding:5p 0 0 25px;color:#333;"> 
		<table id="person_add_table" style="border-collapse:separate;border-spacing:10px;display:none;" >
    					
    					<tr>
    						<td>日期</td>
    						<td><input type="text" id="person_time_add" name="person_time_add" class="easyui-datebox"  data-options="required:'true'"  style="width:150px;height:20px;"/></td>
    					</tr>
    					
    					<tr>
    						<td>调入人数</td>
    						<td><input type="text" name="person_in_add" class="easyui-textbox"  data-options="required:'true',validType:'account[0,5]'"  style="width:150px;height:20px;"/></td>
    					</tr>
    					
    					<tr>
    						<td>调出人数</td>
    						<td><input type="text" name="person_out_add" class="easyui-textbox"  data-options="required:'true',validType:'account[0,5]'"  style="width:150px;height:20px;"/></td>
    					</tr>
    					<!-- <tr>
    						<td>收据</td>
    						<td><input type="radio" name="person_sj_add" value="1">有
								<input type="radio" name="person_sj_add" value="0" checked="checked">无
								
							</td>
						</tr> -->
    					<!-- 
    						<td>时间</td>
    						<td><input type="radio" name="personTime_person_add" checked="checked" value="1">白班
								<input type="radio" name="number_person_add" value="15">15点
								<input type="radio" name="personTime_person_add" value="0">夜班
							</td>
    					 -->
    					<!-- <tr>
    						<td>类别</td>
    						<td>
    							<input type="radio" name="personType_add" checked="checked" value="1">甩车
								<input type="radio" name="personType_add" value="0">挂车
							</td>
    					</tr>
    					<tr>
    						<td>车次</td>
    						<td><input type="text" name="personCc_add" class="easyui-textbox" data-options="required:'true',validType:'account[2,10]'"  style="width:150px;height:20px;"/></td>
    						
    						<td><input type="text" id="personCc_add" name="personCc_add" class="easyui-combobox"  
    					 data-options="valueField:'personCc',
    					 required:'true',
    					 textField:'personCc',
    					 url:'/person/getAllpersonCc',
    					 filter: function(q, row){  
            				var opts = $(this).combobox('options');  
            				return row[opts.textField].indexOf(q) >-1;;  
        				},
        				onSelect:function(record) {  
            				$('personCc_add').focus();  
        				}"   
    					 																									  
    					 style="width:150px;height:20px;"/></td>
    					</tr>
    					<tr>
    						<td>时间</td>
    						<td><input type="text" name="personSj_add" class="easyui-datetimebox"  data-options="required:'true',personowSeconds:false,value:'true',validType:'datetimevalue'"  style="width:150px;height:20px;"/></td>
    					</tr>
    					<tr>
    						<td>车数</td>
    						<td><input type="text" name="personCs_add" class="easyui-textbox"  data-options="required:'true',validType:'intcount[4]'"  style="width:150px;height:20px;"/></td>
    					</tr>
    					<tr>
    						<td>中转</td>
    						<td><input type="text" name="personZz_add" class="easyui-textbox"  data-options="required:'true',validType:'length[0,40]'"  style="width:150px;height:20px;"/></td>
    					</tr> -->
 		</table>
</form> 



<!-- 删除edit表的日期组件的 value:'true'属性   -->
<form id="person_edit" style="margin:0;padding:5p 0 0 25px;color:#333;"> 
		<input type="hidden" name="personId_edit" class="textbox" />
		
		<table id="person_edit_table" style="border-collapse:separate;border-spacing:10px;display:none;">
    					<tr>
    						<td>日期</td>
    						<td><input id="personDate_person_edit" name="personDate_person_edit" class="easyui-datebox" data-options="required:'true',validType:'datevalue',value:'true'" style="width:150px;height:20px;"/></td>
    					</tr>
    					<tr>
    						<td>时间</td>
    						<td><input type="radio" name="personTime_person_edit" checked="checked" value="1">白班
								<!-- <input type="radio" name="number_person_add" value="15">15点 -->
								<input type="radio" name="personTime_person_edit" value="0">夜班
							</td>
    					</tr>
		</table>
</form> 





<script type="text/javascript"	src="<c:url value='/resources/js/shPerson.js'/>"></script>

	
