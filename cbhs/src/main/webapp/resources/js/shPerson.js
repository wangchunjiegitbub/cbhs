



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
	
	//甩挂信息表
	$('#shperson').datagrid({    
	    url : '/cbhs/hsf/getPerson',   
	    fit: true,//固定表头
	    striped : true,
	    rownumbers : true,
	    border : false,
	    toolbar: '#shperson_tool',
	    
//	    sortName : 'shpersonType',
//	    sortOrder : 'asc', 
//	    remoteSort : false,
//	    rowStyler:function(index,row){
//			if (row.fpl>0.003){
//				return 'background-color:pink;color:blue;font-weight:bold;';
//			}
//		},
	    
	    columns:[[    
	        {
	         field:'personId',
	         title:'商户ID',
	         checkbox : true
	         //hidden : true 
	        }, 
	        {
		         field:'departmentName',
		         title:'部门',
		         
		         width: '100px',
		    }, 
	        {
		         field:'personTime',
		         title:'日期',
		         
		         width: '100px',
		    }, 
	        {
		         field:'personIn',
		         title:'调入人数',
		         
		         width: '100px',
		    }, 
	        {
		         field:'personOut',
		         title:'调出人数',
		         
		         width: '100px',
//		         formatter: function(value,row,index){
//						if (value == 1){
//							return '<font color="blue">是</font>';
//						} else {
//							return '<font color="gray">否</font>';
//						}
//					}
		    }, 
		    {
		         field:'userXm',
		         title:'操作人',
		         
		         width: '100px',
		    }, 
		    {
		         field:'personState',
		         title:'状态',
		         
		         width: '100px',
		         formatter: function(value,row,index){
						if (value == 1){
							return '<font color="blue">已审核</font>';
						} else {
							return '<font color="red">未审核</font>';
						}
					}
		    }, 
	        
	    ]],
	    queryParams: {
	    	
	    	beginDate : function (){
				var inforq = $.trim($('input[name="shperson_time_begin"]').val());
		        var date = new Date();
		        if( inforq ) inforq = formatDate(date);//如果info日期为true则设置为当天
		        return inforq;
			},
			endDate : function (){
				var inforq = $.trim($('input[name="shperson_time_end"]').val());
		        var date = new Date();
		        if( inforq ) inforq = formatDate(date);//如果info日期为true则设置为当天
		        return inforq;
			},
			depId : $.trim($('input[name="depId_shperson"]').val()),
		}

	 
	});  

	
	
	//添采购信息
	 $('#shperson_add').dialog({ 
		top : 150,
        width :300,
        //height : 500,
        title : '新增' ,
        modal : true,
		closed : true,
        iconCls : 'icon-add', 
        buttons : [{
        	text : '提交' ,
        	iconCls : 'icon-door-in',
			//点击提交时执行
        	handler : function(){
				//验证表单成执行
				if ($('#shperson_add').form('validate')){
					$.ajax({ 
                            url : '/cbhs/hsf/addPerson', 
                            type : 'POST', 
                            data :{ 
                            	  personTime : $.trim($('input[name="shperson_time_add"]').val()),
                            	    personIn : $.trim($('input[name="shperson_in_add"]').val()),
                            	   personOut : $.trim($('input[name="shperson_out_add"]').val()),
                            	  
                            	
                            	
//                               
                            }, 
                            beforeSend :function (){ 
                                $.messager.progress({ 

                                    text : '正在提交信息...', 
                                }); 
                            }, 
                            success :function(data, response, status){ 
                                
								//alert(data);	
								$.messager.progress('close'); 
                                if (data >0) { 
                                    $.messager.show({ 

                                        title : '提示', 
                                        msg : '添加成功！', 
                                    }); 

    								$('#shperson_add').dialog('close').form('reset'); 
                                    $('#shperson').datagrid('reload'); 
                                }else if(data == -1) { 
                                	$.messager.alert('错误提示!!!', '不能创建！', 'error');
                                	$('#shperson_add').dialog('close').form('reset'); 
                                    $('#shperson').datagrid('reload'); 
                                   
                                }else {
                                	$.messager.alert('警告操作', '未知操作， 请重新提交！', 'warning');
                                }
                            } 
                        }); 
              	}
        	},
        	
        	
        
        
        },{
        	
        	text : '取消' ,
			iconCls : 'icon-cancel',
        	handler : function(){
        		$('#shperson_add').dialog('close').form('reset');
        	}
        	
        
        
        
        }]
    
	 }); 
	 
	 //修改甩挂信息
	 $('#shperson_edit').dialog({ 
        width :350, 
        top : 150,
        title : '修改甩挂信息' ,
        modal : true,
		closed : true,
        iconCls : 'icon-edit', 
        buttons : [{
        	text : '提交' ,
        	iconCls : 'icon-door-in',
			//点击提交时执行
        	handler : function(){
        		//验证表单成执行
				if ($('#shperson_edit').form('validate')){
					$.ajax({ 
                            url : '/shperson/updateshperson', 
                            type : 'POST', 
                            data :{ 
                            	infoId : $.trim($('input[name="shperson_infoId_edit"]').val()),
                            	personId : $.trim($('input[name="shpersonId_edit"]').val()),
                                
                            	personCc : $.trim($('#shpersonCc_edit').combobox('getText')), 
                                personSj : $.trim($('input[name="shpersonSj_edit"]').val()), 
                                personCs : $.trim($('input[name="shpersonCs_edit"]').val()), 
                                personZz : $.trim($('input[name="shpersonZz_edit"]').val()), 
                                personType : $.trim($('input[name="shpersonType_edit"]:checked').val()),
                                
                            }, 
                            beforeSend :function (){ 
                                $.messager.progress({ 

                                    text : '正在修改甩挂信息中...', 
                                }); 
                            }, 
                            success :function(data, response, status){ 
                                
								//alert(data);	
								$.messager.progress('close'); 
                                if (data >0) { 
                                    $.messager.shpersonow({ 

                                        title : '提示', 
                                        mshperson : '甩挂信息修改成功！', 
                                    }); 

    								$('#shperson_edit').dialog('close').form('reset'); 
                                    $('#shperson').datagrid('reload'); 
                                }else if(data == -1) { 
                                	$.messager.alert('错误提示!!!', '报表已结账不能修改,必须修改时请联系调度员！', 'error');
                                	$('#shperson_edit').dialog('close').form('reset'); 
                                    $('#shperson').datagrid('reload'); 
                                   
                                }else {
                                	$.messager.alert('警告操作', '未知操作或没有修改数据， 请重新提交！', 'warning');
                                }
                            } 
                        }); 
              	}
        	},
        },{
        	
        	text : '取消' ,
			iconCls : 'icon-cancel',
        	handler : function(){
        		$('#shperson_edit').dialog('close').form('reset');
        	}
         }]
    
	 }); 

	 
	  
	 //border-collapse:   separate;   border-spacing:   10px; 
     shperson_tool = { 
    		
    		//点击查询
    		 find :function (){ 
    			
    		     $('#shperson').datagrid('loadData', {total:0,rows:[]});  
    			 $('#shperson').datagrid('load', {    
    				 beginDate : function (){
	    					var inforq = $.trim($('input[name="shperson_time_begin"]').val());
//	 				        var date = new Date();
//	 				        if( inforq ) inforq = formatDate(date);//如果info日期为true则设置为当天
	 				        return inforq;
    					}, 
	    			 endDate : function (){
	 					var inforq = $.trim($('input[name="shperson_time_end"]').val());
	//				        var date = new Date();
	//				        if( inforq ) inforq = formatDate(date);//如果info日期为true则设置为当天
					        return inforq;
						},
						depId : $.trim($('input[name="depId_shperson"]').val()),
	    			 });
            },  
    		//点击新增
    		add :function (){ 
        	    $('#shperson_add').dialog('open');
        	    $("#shperson_add_table").show();
        	    $.parser.parse('#shperson_add');//form显示后datebox控件不可用时添加这句扫描代码初始化datebox必须填加id否则发生不确定问题
        	    
        	  //不能用name加datebox只能用id加datebox
                var rq = $('input[name="shperson_time_end"]').val();//获取查询日期赋值给表单
                $('#shperson_time_add').datebox('setValue',rq);//如：$("input[name='mydate']").datebox('getValue')，会报TypeError: $.data(...) is undefined。
//                var number = $('input[name="shpersonTime_shperson"]:checked').val();
//                $("input[name='shpersonTime_shperson_add'][value=" + number + "]").attr("checked",true);
           }, 
           
           //点击删除
           remove :function (){ 
               var rows = $('#shperson').datagrid('getSelections'); 
               if (rows.length >0) { 

                   $.messager.confirm('确定 ', '您要删除所选的<strong>' +  rows.length+ '</strong>条记录吗？',function (flag){ 
                       if (flag){ 
                           var ids = []; 
                           
                           for (var i = 0; i < rows.length; i ++) { ids.push(rows[i].shpersonId); 
                           }
                           
                           $.ajax({ 
                              type : 'POST', 
                               url : '/cbhs/hsf/deletePerson', 
                               data :{ 
                                   ids :ids.join(','), 
                                    
                               }, 
                               beforeSend :function (){ 
                                  $('#shperson').datagrid('loading'); 
                               }, 
                               success :function (data){ 
                                   if (data){ 
                                      $('#shperson').datagrid('loaded'); 
                                      $('#shperson').datagrid('reload'); 
                                      $('#shperson').datagrid('unselectAll'); 
                                      $.messager.show({ 

                                          title : '提示', 
                                          msg : data + '个信息被删除成功！', 
                                       }); 
                                   } 
                               }, 
                           }); 
                       } 
                   }); 
               }else { 

                   $.messager.alert('警告操作', '删除记录至少选定一条数据！', 'warning'); 
               } 
           }, 
         //点击删除
           shEnter :function (){ 
               var rows = $('#shperson').datagrid('getSelections'); 
               if (rows.length >0) { 

                   $.messager.confirm('确定 ', '<font color="red">审核确认后不能撤销！！！</font>您要审核所选的<strong>' +  rows.length+ '</strong>条记录吗？',function (flag){ 
                       if (flag){ 
                           var ids = []; 
                           
                           for (var i = 0; i < rows.length; i ++) { ids.push(rows[i].shpersonId); 
                           }
                           
                           $.ajax({ 
                              type : 'POST', 
                               url : '/cbhs/hsf/shPersonEnter', 
                               data :{ 
                                   ids :ids.join(','), 
                                    
                               }, 
                               beforeSend :function (){ 
                                  $('#shperson').datagrid('loading'); 
                               }, 
                               success :function (data){ 
                                   if (data){ 
                                      $('#shperson').datagrid('loaded'); 
                                      $('#shperson').datagrid('reload'); 
                                      $('#shperson').datagrid('unselectAll'); 
                                      $.messager.show({ 

                                          title : '提示', 
                                          msg : data + '提交成功！', 
                                       }); 
                                   } 
                               }, 
                           }); 
                       } 
                   }); 
               }else { 

                   $.messager.alert('警告操作', '请至少选定一条数据！', 'warning'); 
               } 
           }, 
//           
      }; 

  
    
    


  	
});

