



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
	$('#shsr').datagrid({    
	    url : '/cbhs/hsf/getSrAll',   
	    fit: true,//固定表头
	    striped : true,
	    rownumbers : true,
	    border : false,
	    toolbar: '#shsr_tool',
	    
//	    sortName : 'shsrType',
//	    sortOrder : 'asc', 
//	    remoteSort : false,
//	    rowStyler:function(index,row){
//			if (row.fpl>0.003){
//				return 'background-color:pink;color:blue;font-weight:bold;';
//			}
//		},
	    
	    columns:[[    
	        {
		         field:'shouruId',
		         title:'收入ID',
		         checkbox : true
		         //hidden : true 
	        }, 
	        {
		         field:'shouruDate',
		         title:'收入日期',
		         
		         width: '100px',
		    }, 
	        {
		         field:'srtype',
		         title:'收入项目',
		         
		         width: '100px',
		    }, 
	        {
		         field:'shouruMoney',
		         title:'收入金额',
		         
		         width: '100px',
		    }, 
	        {
		        field:'userXm',
		        title:'录入人',
		        width: '100px',
	        },   
	        {
		        field:'departmentName',
		        title:'部门',
		        width: '100px',
		     },
		     {
			    field:'shouruBeizu',
			    title:'备注',
			    width: '200px',
			  },
			  {
				    field:'shouruState',
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
//	       
	    ]],
	    queryParams: {
	    	
	    	beginDate : function (){
				var inforq = $.trim($('input[name="shsrDate_shsr_begin"]').val());
		        var date = new Date();
		        if( inforq ) inforq = formatDate(date);//如果info日期为true则设置为当天
		        return inforq;
			},
			endDate : function (){
				var inforq = $.trim($('input[name="shsrDate_shsr_end"]').val());
		        var date = new Date();
		        if( inforq ) inforq = formatDate(date);//如果info日期为true则设置为当天
		        return inforq;
			},
			depId : $.trim($('input[name="depId_shsr"]').val()),
		}

	 
	});  

	
	
	//添采购信息
	 $('#shsr_add').dialog({ 
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
				if ($('#shsr_add').form('validate')){
					$.ajax({ 
                            url : '/cbhs/hsf/addSr', 
                            type : 'POST', 
                            data :{ 
                            	    
                            	  shouruDate : $.trim($('input[name="shsr_date_add"]').val()),
                            	    srtypeId : $.trim($('input[name="shsr_typeid_add"]').val()),
                            	 shouruMoney : $.trim($('input[name="shsr_money_add"]').val()),
                            	 shouruBeizu : $.trim($('input[name="shsr_beizu_add"]').val()),
                            	   
                            	
                            	
                            	
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

    								$('#shsr_add').dialog('close').form('reset'); 
                                    $('#shsr').datagrid('reload'); 
                                }else if(data == -1) { 
                                	$.messager.alert('错误提示!!!', '不能创建！', 'error');
                                	$('#shsr_add').dialog('close').form('reset'); 
                                    $('#shsr').datagrid('reload'); 
                                   
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
        		$('#shsr_add').dialog('close').form('reset');
        	}
        	
        
        
        
        }]
    
	 }); 
	 
	 //修改甩挂信息
	 $('#shsr_edit').dialog({ 
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
				if ($('#shsr_edit').form('validate')){
					$.ajax({ 
                            url : '/cbhs/hsf/updateSr', 
                            type : 'POST', 
                            data :{ 
                            	shouruId : $.trim($('input[name="shouruId_edit"]').val()),
                            	shouruDate : $.trim($('input[name="shsr_date_edit"]').val()),
                                srtypeId : $.trim($('input[name="shsr_typeid_edit"]').val()), 
                            	shouruMoney : $.trim($('input[name="shsr_money_edit"]').val()), 
                            	shouruBeizu : $.trim($('input[name="shsr_beizu_edit"]').val()), 
                                
                                
                                
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
                                    $.messager.show({ 

                                        title : '提示', 
                                        msg : '信息修改成功！', 
                                    }); 

    								$('#shsr_edit').dialog('close').form('reset'); 
                                    $('#shsr').datagrid('reload'); 
                                }else if(data == -1) { 
                                	$.messager.alert('错误提示!!!', '不能修改！', 'error');
                                	$('#shsr_edit').dialog('close').form('reset'); 
                                    $('#shsr').datagrid('reload'); 
                                   
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
        		$('#shsr_edit').dialog('close').form('reset');
        	}
         }]
    
	 }); 

	 
	  
	 //border-collapse:   separate;   border-spacing:   10px; 
     shsr_tool = { 
    		
    		//点击查询
    		 find :function (){ 
    			
    		     $('#shsr').datagrid('loadData', {total:0,rows:[]});  
    			 $('#shsr').datagrid('load', {    
    				 	beginDate : function (){
    						var inforq = $.trim($('input[name="shsrDate_shsr_begin"]').val());
//    				        var date = new Date();
//    				        if( inforq ) inforq = formatDate(date);//如果info日期为true则设置为当天
    				        return inforq;
    					},
    					endDate : function (){
    						var inforq = $.trim($('input[name="shsrDate_shsr_end"]').val());
//    				        var date = new Date();
//    				        if( inforq ) inforq = formatDate(date);//如果info日期为true则设置为当天
    				        return inforq;
    					},
    					depId : $.trim($('input[name="depId_shsr"]').val()),
    			 });
            },
            //点击审核
            shEnter :function (){ 
                var rows = $('#shsr').datagrid('getSelections'); 
                if (rows.length >0) { 

                    $.messager.confirm('确定 ', '<font color="red">审核确认后不能撤销！！！</font>您要审核所选的<strong>' +  rows.length+ '</strong>条记录吗？',function (flag){ 
                        if (flag){ 
                            var ids = []; 
                            
                            for (var i = 0; i < rows.length; i ++) { ids.push(rows[i].shouruId); 
                            }
                            
                            $.ajax({ 
                               type : 'POST', 
                                url : '/cbhs/hsf/shSrEnter', 
                                data :{ 
                                    ids :ids.join(','), 
                                     
                                }, 
                                beforeSend :function (){ 
                                   $('#shsr').datagrid('loading'); 
                                }, 
                                success :function (data){ 
                                    if (data){ 
                                       $('#shsr').datagrid('loaded'); 
                                       $('#shsr').datagrid('reload'); 
                                       $('#shsr').datagrid('unselectAll'); 
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
    		//点击新增 粉票
    		add :function (){ 
        	    $('#shsr_add').dialog('open');
        	    $("#shsr_add_table").show();
        	    $.parser.parse('#shsr_add');//form显示后datebox控件不可用时添加这句扫描代码初始化datebox必须填加id否则发生不确定问题
        	    
        	  //不能用name加datebox只能用id加datebox
                var rq = $('input[name="shsrDate_shsr"]').val();//获取查询日期赋值给表单
                $('#shsr_date_add').datebox('setValue',rq);//如：$("input[name='mydate']").datebox('getValue')，会报TypeError: $.data(...) is undefined。
//                var number = $('input[name="shsrTime_shsr"]:checked').val();
//                $("input[name='shsrTime_shsr_add'][value=" + number + "]").attr("checked",true);
           }, 
           
           //点击删除
           remove :function (){ 
               var rows = $('#shsr').datagrid('getSelections'); 
               if (rows.length >0) { 

                   $.messager.confirm('确定 ', '您要删除所选的<strong>' +  rows.length+ '</strong>条记录吗？',function (flag){ 
                       if (flag){ 
                           var ids = []; 
                           
                           for (var i = 0; i < rows.length; i ++) { ids.push(rows[i].shouruId); 
                           }
                           
                           $.ajax({ 
                              type : 'POST', 
                               url : '/cbhs/hsf/deleteSr', 
                               data :{ 
                                   ids :ids.join(','), 
                                    
                               }, 
                               beforeSend :function (){ 
                                  $('#shsr').datagrid('loading'); 
                               }, 
                               success :function (data){ 
                                   if (data){ 
                                      $('#shsr').datagrid('loaded'); 
                                      $('#shsr').datagrid('reload'); 
                                      $('#shsr').datagrid('unselectAll'); 
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
           //点击修改
           edit :function (){
        	   $("#shsr_edit_table").show();
       	      // $('input[name="shsrSj_edit"]').css("width","150px");
        	   //!!!!渲染必须在这个位置否则获取datebox数据不能刷新!!!!!!!!!!!!!!!!!
        	   $.parser.parse('#shsr_edit');//form显示后datebox控件不可用时添加这句扫描代码初始化datebox
        	   var rows = $('#shsr').datagrid('getSelections');
        	   //console.log(rows);
        	   if(rows.length > 1){
        		   $.messager.alert('警告操作', '编辑记录只能选择一条数据！', 'warning');
        	   } else if(rows.length == 1) {
					$.ajax({ 
                        url : '/cbhs/hsf/getSr', 
                        type : 'POST', 
                        data :{ 
                        	shouruId : rows[0].shouruId
                        	
                        }, 
                        beforeSend :function (){ 
                            $.messager.progress({ 
                                text : '正在获取中...', 
                            }); 
                        }, 
                        success :function(data, response, status){ 
 							$.messager.progress('close'); 
                            if (data) { 
                            	var obj = $.parseJSON(data);
                            	//控制台打印:console.log(obj.shsrCc);
                            	
                            	//成功获取数据显示form表单页面
                            	$('#shsr_edit').form('load',{
                            		shouruId_edit : obj.shouruId,
                            		shsr_date_edit : obj.shouruDate,
                            		shsr_typeid_edit : obj.srtypeId,
                            		shsr_money_edit : obj.shouruMoney,
                            		shsr_beizu_edit : obj.shouruBeizu,
                                    
     
                           		
                            	}).dialog('open');
                            	
                            }else {
                            	$.messager.alert('获取失败', '未知错误导致失败！', 'warning');
                            }
                        } 
                    });
        	   } else if(rows.length == 0) {
        		   $.messager.alert('警告操作', '编辑记录至少选择一条数据！', 'warning');
        	   }
        	   
           },
      }; 

  
    
    


  	
});

