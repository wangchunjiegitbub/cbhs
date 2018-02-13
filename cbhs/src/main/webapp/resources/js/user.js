



$(function(){
	
	//日期转字符串函数
	var formatDate = function (date) {  
	    var y = date.getFullYear();  
	    var m = date.getMonth() + 1;  
	    m = m < 10 ? '0' + m : m;  
	    var d = date.getDate();  
	    d = d < 10 ? ('0' + d) : d;  
	    return y + '-' + m + '-' + d;  
	};
	
	//密码表
	$('#userdmh').datagrid({    
	    url : '',   
	    
	    striped : true,
	    rownumbers : true,
	    border : false,
	    toolbar: '#userdmh_tool',
	    
//	    columns:[[    
//	        {
//	         field:'infoId',
//	         title:'主表ID',
//	         hidden : true 
//	        }, 
//	        {
//	        field:'userdmhId',
//	        title:'自动编号',
//	        width:100,
//	        checkbox : true
//	        }, 
//	        {
//	        field:'userdmhCc',
//	        title:'车次',
//	        width:100
//	        },    
//	        {
//	        field:'userdmhSjCharShort',
//	        title:'计划时间',
//	        width:100,
//	        
//	        },
//	        {
//		        field:'userdmhWcsjCharShort',
//		        title:'完成时间',
//		        
//		        
//		    }, 
//	    ]],
//	    queryParams: {
//	    	number : $.trim($('input[name="infonumber_userdmh"]:checked').val()),
//			rq : function (){
//				var inforq = $.trim($('input[name="inforq_userdmh"]').val());
//		        var date = new Date();
//		        if( inforq ) inforq = formatDate(date);//如果info日期为true则设置为当天
//		        return inforq;
//			}
//		}

	 
	});  

	
	
//	//添加密码
//	 $('#userdmh_add').dialog({ 
//		top : 150,
//        width :350, 
//        title : '新增密码' ,
//        modal : true,
//		closed : true,
//        iconCls : 'icon-user', 
//        
//       
////        onBeforeOpen : function(){  
////        	
////        	//$('input[name="rq_userdmh_add"]').datebox('setValue', $('input[name="inforq_userdmh"]').val());
////        	alert($('input[name="rq_userdmh_add"]').val());
////        	alert($('input[name="inforq_userdmh"]').val());
////        	//var rq = $.trim($('input[name="inforq_userdmh"]').val());
////        	//console.log(rq instanceof Date);
////        	//console.log(rq instanceof String);
////        	$('input[name="rq_userdmh_add"]').datebox('setValue', '2016-10-10');
//////        	$('input[name="rq_userdmh_add"]').val(rq.toString());   
////        },  
//        buttons : [{
//        	text : '提交' ,
//        	iconCls : 'icon-door-in',
//			//点击提交时执行
//        	handler : function(){
//				//验证表单成执行
//				if ($('#userdmh_add').form('validate')){
//					$.ajax({ 
//                            url : '/dmh/addUserdmh', 
//                            type : 'POST', 
//                            data :{ 
//                            	number : $.trim($('input[name="number_userdmh_add"]:checked').val()),
//                            	rq : $.trim($('input[name="rq_userdmh_add"]').val() + ' 00:00'),
//                            	
//                                userdmhCc : $.trim($('input[name="userdmhCc"]').val()), 
//                                userdmhSj : $.trim($('input[name="userdmhSj"]').val()), 
//                                userdmhWcsj : $.trim($('input[name="userdmhWcsj"]').val()),
//                            }, 
//                            beforeSend :function (){ 
//                                $.messager.progress({ 
//
//                                    text : '正在提交密码中...', 
//                                }); 
//                            }, 
//                            success :function(data, response, status){ 
//                                
//								//alert(data);	
//								$.messager.progress('close'); 
//                                if (data >0) { 
//                                    $.messager.show({ 
//
//                                        title : '提示', 
//                                        msg : '密码添加成功！', 
//                                    }); 
//
//    								$('#userdmh_add').dialog('close').form('reset'); 
//                                    $('#userdmh').datagrid('reload'); 
//                                }else if(data == -1) { 
//                                	$.messager.alert('错误提示!!!', '报表已结账不能修改,必须修改时请联系调度员！', 'error');
//                                	$('#userdmh_add').dialog('close').form('reset'); 
//                                    $('#userdmh').datagrid('reload'); 
//                                   
//                                }else {
//                                	$.messager.alert('警告操作', '未知操作， 请重新提交！', 'warning');
//                                }
//                            } 
//                        }); 
//              	}
//        	},
//        	
//        	
//        
//        
//        },{
//        	
//        	text : '取消' ,
//			iconCls : 'icon-cancel',
//        	handler : function(){
//        		$('#userdmh_add').dialog('close').form('reset');
//        	}
//        	
//        
//        
//        
//        }]
//    
//	 }); 
	 
	 //修改密码
	 $('#userdmh_edit').dialog({ 
        width :350, 
        top : 150,
        title : '修改密码' ,
        modal : true,
		closed : true,
        iconCls : 'icon-edit', 
        buttons : [{
        	text : '提交' ,
        	iconCls : 'icon-door-in',
			//点击提交时执行
        	handler : function(){
        		//验证表单成执行
				if ($('#userdmh_edit').form('validate')){
					$.ajax({ 
                            url : '/cbhs/updateUser', 
                            type : 'POST', 
                            data :{ 
                            	
                            	userId : $.trim($('input[name="userId_edit"]').val()),
                                
                            	password : $.trim($('input[name="pwd_edit"]').val()), 
                            	passwordNew1 : $.trim($('input[name="pwd_new1_edit"]').val()),
                            	passwordNew2 : $.trim($('input[name="pwd_new2_edit"]').val()),
                            }, 
                            beforeSend :function (){ 
                                $.messager.progress({ 

                                    text : '正在修改密码中...', 
                                }); 
                            }, 
                            success :function(data, response, status){ 
                                
								//alert(data);	
								$.messager.progress('close'); 
                                if (data >0) { 
                                    $.messager.show({ 

                                        title : '提示', 
                                        msg : '密码修改成功！', 
                                    }); 

    								$('#userdmh_edit').dialog('close').form('reset'); 
                                    $('#userdmh').datagrid('reload'); 
                                }else if(data == -1) { 
                                	$.messager.alert('错误提示!!!', '新密码两次输入不一致不能修改！', 'error');
                                	//$('#userdmh_edit').dialog('close').form('reset'); 
                                    //$('#userdmh').datagrid('reload'); 
                                   
                                }else {
                                	$.messager.alert('警告操作', '原密码错误或未知操作, 请重新提交！', 'warning');
                                }
                            } 
                        }); 
              	}
        	},
        },{
        	
        	text : '取消' ,
			iconCls : 'icon-cancel',
        	handler : function(){
        		$('#userdmh_edit').dialog('close').form('reset');
        	}
         }]
    
	 }); 

	 
	  
	 //border-collapse:   separate;   border-spacing:   10px; 
     user_tool = { 
    		
//    		//点击查询
//    		 find :function (){ 
//    			 var infonumber = $.trim($('input[name="infonumber_userdmh"]:checked').val());
//    		     var inforq = $.trim($('input[name="inforq_userdmh"]').val());
//    		     $('#userdmh').datagrid('loadData', {total:0,rows:[]});  
//    			 $('#userdmh').datagrid('load', {    
//    				 number : infonumber,    
//    				 rq : inforq ,  
//    			 });
//            },  
//    		//点击新增 
//    		add :function (){ 
//        	    $('#userdmh_add').dialog('open');
//        	    $("#userdmh_add_table").show();
//        	    
//            	
//                $.parser.parse('#userdmh_add');//form显示后datebox控件不可用时添加这句扫描代码初始化datebox必须填加id否则发生不确定问题
//                //不能用name加datebox只能用id加datebox
//                var rq = $('input[name="inforq_userdmh"]').val();//获取查询日期赋值给表单
//                $('#rq_userdmh_add').datebox('setValue',rq);//如：$("input[name='mydate']").datebox('getValue')，会报TypeError: $.data(...) is undefined。
//                var number = $('input[name="infonumber_userdmh"]:checked').val();
//                $("input[name='number_userdmh_add'][value=" + number + "]").attr("checked",true);
//           }, 
//           //点击删除
//           remove :function (){ 
//               var rows = $('#userdmh').datagrid('getSelections'); 
//               if (rows.length >0) { 
//
//                   $.messager.confirm('确定 ', '您要删除所选的<strong>' +  rows.length+ '</strong>条记录吗？',function (flag){ 
//                       if (flag){ 
//                           var ids = []; 
//                           var infoIds = [];
//                           for (var i = 0; i < rows.length; i ++) { ids.push(rows[i].userdmhId); 
//                           }
//                           for (var i = 0; i < rows.length; i ++) { infoIds.push(rows[i].infoId); 
//                           }
//                           $.ajax({ 
//                              type : 'POST', 
//                               url : '/dmh/deleteUserdmh', 
//                               data :{ 
//                                   ids :ids.join(','), 
//                                   infoIds :infoIds.join(','), 
//                               }, 
//                               beforeSend :function (){ 
//                                  $('#userdmh').datagrid('loading'); 
//                               }, 
//                               success :function (data){ 
//                                   if (data){ 
//                                      $('#userdmh').datagrid('loaded'); 
//                                      $('#userdmh').datagrid('reload'); 
//                                      $('#userdmh').datagrid('unselectAll'); 
//                                      $.messager.show({ 
//
//                                          title : '提示', 
//                                          msg :data + '个密码被删除成功！', 
//                                       }); 
//                                   } 
//                               }, 
//                           }); 
//                       } 
//                   }); 
//               }else { 
//
//                   $.messager.alert('警告操作', '删除记录至少选定一条数据！', 'warning'); 
//               } 
//           }, 
           //点击修改
           edit :function (){
        	   $("#userdmh_edit_table").show();
       	      // $('input[name="userdmhSj_edit"]').css("width","150px");
        	   //!!!!渲染必须在这个位置否则获取datebox数据不能刷新!!!!!!!!!!!!!!!!!
        	   $.parser.parse('#userdmh_edit');//form显示后datebox控件不可用时添加这句扫描代码初始化datebox
        	   //var rows = $('#userdmh').datagrid('getSelections');
        	   //console.log(rows);
        	   
					$.ajax({ 
                        url : '/cbhs/getUser', 
                        type : 'POST', 
                        
                        beforeSend :function (){ 
                            $.messager.progress({ 
                                text : '正在获取中...', 
                            }); 
                        }, 
                        success :function(data, response, status){ 
 							$.messager.progress('close'); 
                            if (data) { 
                            	var obj = $.parseJSON(data);
                            	//控制台打印:console.log(obj.userdmhCc);
                            	
                            	//成功获取数据显示form表单页面
                            	$('#userdmh_edit').form('load',{
                            		userId_edit : obj.userId,
                            		division_edit : obj.userXm,
                            		
                            		
                            	}).dialog('open');
                            	
                            }else {
                            	$.messager.alert('获取失败', '未知错误导致失败！', 'warning');
                            }
                        } 
                    });
        	   
        	   
           },
      }; 

  
    



  	
});

