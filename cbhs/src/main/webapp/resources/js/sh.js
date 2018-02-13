



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
	$('#sh').datagrid({    
	    url : '/cbhs/hsf/getSh',   
	    
	    striped : true,
	    rownumbers : true,
	    border : false,
	    toolbar: '#sh_tool',
	    
//	    sortName : 'shType',
//	    sortOrder : 'asc', 
//	    remoteSort : false,
//	    rowStyler:function(index,row){
//			if (row.fpl>0.003){
//				return 'background-color:pink;color:blue;font-weight:bold;';
//			}
//		},
	    
	    columns:[[    
	        {
	         field:'shId',
	         title:'商户ID',
	         checkbox : true
	         //hidden : true 
	        }, 
	        {
		         field:'shName',
		         title:'商户名称',
		         
		         width: '100px',
		    }, 
	        {
		         field:'shActivity',
		         title:'显示状态',
		         
		         width: '100px',
		         formatter: function(value,row,index){
						if (value == 1){
							return '<font color="blue">是</font>';
						} else {
							return '<font color="gray">否</font>';
						}
					}
		    }, 
	        
	    ]],
	    queryParams: {
	    	
	    	depId : $.trim($('input[name="depId_sh"]').val()),
		}

	 
	});  

	
	
	//添采购信息
	 $('#sh_add').dialog({ 
		top : 150,
        width :500,
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
				if ($('#sh_add').form('validate')){
					$.ajax({ 
                            url : '/cbhs/hsf/addSh', 
                            type : 'POST', 
                            data :{ 
                            	      shName : $.trim($('input[name="sh_name_add"]').val()),
                            	  shIdnumber : $.trim($('input[name="sh_idnumber_add"]').val()),
                            	  
                            	
                            	
//                                shCc : $.trim($('#shCc_add').combobox('getText')), 
//                                shSj : $.trim($('input[name="shSj_add"]').val()), 
//                                shCs : $.trim($('input[name="shCs_add"]').val()), 
//                                shZz : $.trim($('input[name="shZz_add"]').val()), 
//                                shType : $.trim($('input[name="shType_add"]:checked').val()), 
                                 
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

    								$('#sh_add').dialog('close').form('reset'); 
                                    $('#sh').datagrid('reload'); 
                                }else if(data == -1) { 
                                	$.messager.alert('错误提示!!!', '不能创建！', 'error');
                                	$('#sh_add').dialog('close').form('reset'); 
                                    $('#sh').datagrid('reload'); 
                                   
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
        		$('#sh_add').dialog('close').form('reset');
        	}
        	
        
        
        
        }]
    
	 }); 
	 
	 //修改甩挂信息
	 $('#sh_edit').dialog({ 
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
				if ($('#sh_edit').form('validate')){
					$.ajax({ 
                            url : '/cbhs/hsf/updatesh', 
                            type : 'POST', 
                            data :{ 
                            	//shName : $.trim($('input[name="sh_name_edit"]').val()),
                          	  //shIdnumber : $.trim($('input[name="sh_idnumber_edit"]').val()),
                            	    shId : $.trim($('input[name="shId_edit"]').val()),
                          	  shActivity : $.trim($('input[name="sh_activity_edit"]:checked').val()),
                                //shType : $.trim($('input[name="shType_edit"]').val()),//禁止修改甩挂类别
//                            	shCc : $.trim($('input[name="shCc_edit"]').val()), 
//                              shSj : $.trim($('input[name="shId_edit"]').val()), 
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
                                        msh : '甩挂信息修改成功！', 
                                    }); 

    								$('#sh_edit').dialog('close').form('reset'); 
                                    $('#sh').datagrid('reload'); 
                                }else if(data == -1) { 
                                	$.messager.alert('错误提示!!!', '报表已结账不能修改,必须修改时请联系调度员！', 'error');
                                	$('#sh_edit').dialog('close').form('reset'); 
                                    $('#sh').datagrid('reload'); 
                                   
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
        		$('#sh_edit').dialog('close').form('reset');
        	}
         }]
    
	 }); 

	 
	  
	 //border-collapse:   separate;   border-spacing:   10px; 
     sh_tool = { 
    		
    		//点击查询
    		 find :function (){ 
    			
    		     $('#sh').datagrid('loadData', {total:0,rows:[]});  
    			 $('#sh').datagrid('load', {    
    				 inDate : function (){
    						var inforq = $.trim($('input[name="shDate_sh"]').val());
    				        var date = new Date();
    				        if( inforq ) inforq = formatDate(date);//如果info日期为true则设置为当天
    				        return inforq;
    					} ,
    					depId : $.trim($('input[name="depId_sh"]').val()),
    			 });
            },  
    		//点击新增
    		add :function (){ 
        	    $('#sh_add').dialog('open');
        	    $("#sh_add_table").show();
        	    $.parser.parse('#sh_add');//form显示后datebox控件不可用时添加这句扫描代码初始化datebox必须填加id否则发生不确定问题
        	    
        	  //不能用name加datebox只能用id加datebox
//                var rq = $('input[name="shDate_sh"]').val();//获取查询日期赋值给表单
//                $('#sh_date_add').datebox('setValue',rq);//如：$("input[name='mydate']").datebox('getValue')，会报TypeError: $.data(...) is undefined。
//                var number = $('input[name="shTime_sh"]:checked').val();
//                $("input[name='shTime_sh_add'][value=" + number + "]").attr("checked",true);
           }, 
           
           //点击删除
           remove :function (){ 
               var rows = $('#sh').datagrid('getSelections'); 
               if (rows.length >0) { 

                   $.messager.confirm('确定 ', '您要删除所选的<strong>' +  rows.length+ '</strong>条记录吗？',function (flag){ 
                       if (flag){ 
                           var ids = []; 
                           
                           for (var i = 0; i < rows.length; i ++) { ids.push(rows[i].shId); 
                           }
                           
                           $.ajax({ 
                              type : 'POST', 
                               url : '/cbhs/hsf/deleteSh', 
                               data :{ 
                                   ids :ids.join(','), 
                                    
                               }, 
                               beforeSend :function (){ 
                                  $('#sh').datagrid('loading'); 
                               }, 
                               success :function (data){ 
                                   if (data){ 
                                      $('#sh').datagrid('loaded'); 
                                      $('#sh').datagrid('reload'); 
                                      $('#sh').datagrid('unselectAll'); 
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
        	   $("#sh_edit_table").show();
       	      // $('input[name="shSj_edit"]').css("width","150px");
        	   //!!!!渲染必须在这个位置否则获取datebox数据不能刷新!!!!!!!!!!!!!!!!!
        	   $.parser.parse('#sh_edit');//form显示后datebox控件不可用时添加这句扫描代码初始化datebox
        	   var rows = $('#sh').datagrid('getSelections');
        	   //console.log(rows);
        	   if(rows.length > 1){
        		   $.messager.alert('警告操作', '编辑记录只能选择一条数据！', 'warning');
        	   } else if(rows.length == 1) {
					$.ajax({ 
                        url : '/cbhs/hsf/getShOne', 
                        type : 'POST', 
                        data :{ 
                        	shId : rows[0].shId,
//                        	infoId : rows[0].infoId,
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
                            	//控制台打印:console.log(obj.shCc);
                            	
                            	//成功获取数据显示form表单页面
                            	$('#sh_edit').form('load',{
                            		shId_edit : obj.shId,
                            		sh_name_edit : obj.shName,
                            		
                            		sh_idnumber_edit : obj.shIdnumber,
                            		sh_activity_edit : obj.shActivity,
     
//                            		shCc_edit : obj.sh.shCc,
//                            		shSj_edit : obj.sh.shSjCharAll,
//                            		
//                            		rq_sh_edit : obj.bInfo.rqChar,
//                            		number_sh_edit : obj.bInfo.number,
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

