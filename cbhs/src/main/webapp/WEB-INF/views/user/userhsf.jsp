<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<div id="userdmh_tool" style="padding:5px;">
	<div style="margig-bottom:5px;">
		<!-- <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onClick="userdmh_tool.add();">添加</a> -->
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit"  onClick="user_tool.edit();">修改密码</a>
		<!-- <a href="#" class="easyui-linkbutton" iconCls="icon-DeleteRed" plain="true" onClick="userdmh_tool.remove();">删除</a>
		<input id="inforq" name="inforq_userdmh" type="text" class="easyui-datebox" required="required" value="true"></input>
		<input type="radio" name="infonumber_userdmh" checked="checked" value="6">6点
		<input type="radio" name="infonumber_userdmh" value="15">15点
		<input type="radio" name="infonumber_userdmh" value="18">18点
		<a href="#" class="easyui-linkbutton" iconCls="icon-page-find" plain="true" onClick="userdmh_tool.find();">查询</a> -->
	</div>
</div>

<!-- 
<form id="userdmh_add" style="margin:0;padding:5p 0 0 25px;color:#333;"> 
		<table id="userdmh_add_table" style="border-collapse:separate;border-spacing:10px;display:none;" >
    					<tr>
    						<td>用户名</td>
    						<td><input id="division_add" name="division_add" class="easyui-textbox" data-options="" style="width:150px;height:20px;"/></td>
    					</tr>
    					<tr>
    						<td>原密码</td>
    						<td><input id="pwd_add" name="pwd_add" class="easyui-textbox" data-options="" style="width:150px;height:20px;"/></td>
    					</tr>
    					<tr>
    						<td>新密码</td>
    						<td><input id="pwd_new1_add" name="pwd_new1_add" class="easyui-textbox" data-options="" style="width:150px;height:20px;"/></td>
    					</tr>
    					<tr>
    						<td>确认新密码</td>
    						<td><input id="pwd_new2_add" name="pwd_new2_add" class="easyui-textbox" data-options="" style="width:150px;height:20px;"/></td>
    					</tr>
    					
 		</table>
</form> 
 -->

<!-- 删除edit表的日期组件的 value:'true'属性   -->
<form id="userdmh_edit" style="margin:0;padding:5p 0 0 25px;color:#333;"> 
		<input type="hidden" name="userId_edit" class="textbox" />
		<table id="userdmh_edit_table" style="border-collapse:separate;border-spacing:10px;display:none;">
    					<tr>
    						<td>用户名</td>
    						<td><input id="division_edit" name="division_edit" class="easyui-textbox" data-options="disabled:'true'" style="width:150px;height:20px;"/></td>
    					</tr>
    					<tr>
    						<td>原密码</td>
    						<td><input id="pwd_edit" name="pwd_edit" class="easyui-textbox" data-options="required:'true',type:'password'" style="width:150px;height:20px;"/></td>
    					</tr>
    					<tr>
    						<td>新密码</td>
    						<td><input id="pwd_new1_edit" name="pwd_new1_edit" class="easyui-textbox" data-options="required:'true',type:'password',validType:'length[3,18]'" style="width:150px;height:20px;"/></td>
    					</tr>
    					<tr>
    						<td>确认新密码</td>
    						<td><input id="pwd_new2_edit" name="pwd_new2_edit" class="easyui-textbox" data-options="required:'true',type:'password',validType:'length[3,18]'" style="width:150px;height:20px;"/></td>
    					</tr>
 		</table>
</form> 

<script type="text/javascript"	src="<c:url value='/resources/js/user.js'/>"></script>

	
