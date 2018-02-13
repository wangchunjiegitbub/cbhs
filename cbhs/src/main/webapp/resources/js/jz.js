



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
	$('#jz').datagrid({    
	    url : '/cbhs/hsf/getJzAll',   
	    
	    striped : true,
	    rownumbers : true,
	    border : false,
	    toolbar: '#jz_tool',
	    
//	    sortName : 'jzType',
//	    sortOrder : 'asc', 
//	    remoteSort : false,
//	    rowStyler:function(index,row){
//			if (row.fpl>0.003){
//				return 'background-color:pink;color:blue;font-weight:bold;';
//			}
//		},
	    
	    columns:[[    
	        {
	         field:'jiecunId',
	         title:'结存ID',
	         checkbox : true
	         //hidden : true 
	        }, 
	        {
		         field:'jiecunMonth',
		         title:'结存日期',
		         
		         width: '100px',
		    }, 
	        {
		         field:'jiecunRs',
		         title:'人员数量',
		         
		         width: '100px',
		    }, 
	        
	        {
	        field:'jiecunMoney',
	        title:'结存金额',
	        width: '100px',
	        },    
//	        {
//	        field:'jzTime',
//	        title:'时间',
//	        formatter: function(value,row,index){
//				if (row.jzTime == 1){
//					return '<font color="blue">白班</font>';
//				} else {
//					return '夜班';
//				}
//			}
//	        },    
//	        {
//	        	field:'jzMoney',
//	        	title:'金额',
//	        	width: '100px',
//	        },  
//	        {
//	        	field:'shName',
//	        	title:'商户',
//	        	width: '100px',
//	        },  
//	        {
//	        	field:'jzSj',
//	        	title:'收据',
//	        	width: '100px',
//	        	formatter: function(value,row,index){
//					if (value == 1){
//						return '有';
//					} else {
//						return '<font color="red">无</font>';
//					}
//				}
//	        }, 
//	        {
//	        	field:'jzBx',
//	        	title:'报销',
//	        	width: '100px',
//	        	formatter: function(value,row,index){
//					if (value == 1){
//						return '已报销';
//					} else {
//						return '<font color="red">未报销</font>';
//					}
//				}
//	        	
//	        },
//	       
	        
	    ]],
	    queryParams: {
	    	
	    	jiecunMonth : function (){
				var inforq = $.trim($('input[name="jzDate_jz"]').val());
		        var date = new Date();
		        if( inforq ) inforq = formatDate(date);//如果info日期为true则设置为当天
		        return inforq;
			},
			depId : $.trim($('input[name="depId_jz"]').val()),
		}

	 
	});  

	
	
	//添采购信息
	 $('#jz_add').dialog({ 
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
				if ($('#jz_add').form('validate')){
					$.ajax({ 
                            url : '/cbhs/hsf/addJz', 
                            type : 'POST', 
                            data :{ 
                            	  jzDate : $.trim($('input[name="jz_date_add"]').val()),
                            	  jzScid : $.trim($('input[name="jz_scid_add"]').val()),
                            	    jzSl : $.trim($('input[name="jz_sl_add"]').val()),
                            	 jzMoney : $.trim($('input[name="jz_money_add"]').val()),
                            	  jzShid : $.trim($('input[name="jz_shid_add"]').val()),
                            	    jzSj : $.trim($('input[name="jz_sj_add"]:checked').val()),
                            	
                            	
                            	
//                                jzCc : $.trim($('#jzCc_add').combobox('getText')), 
//                                jzSj : $.trim($('input[name="jzSj_add"]').val()), 
//                                jzCs : $.trim($('input[name="jzCs_add"]').val()), 
//                                jzZz : $.trim($('input[name="jzZz_add"]').val()), 
//                                jzType : $.trim($('input[name="jzType_add"]:checked').val()), 
                                 
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

    								$('#jz_add').dialog('close').form('reset'); 
                                    $('#jz').datagrid('reload'); 
                                }else if(data == -1) { 
                                	$.messager.alert('错误提示!!!', '不能创建！', 'error');
                                	$('#jz_add').dialog('close').form('reset'); 
                                    $('#jz').datagrid('reload'); 
                                   
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
        		$('#jz_add').dialog('close').form('reset');
        	}
        	
        
        
        
        }]
    
	 }); 
	 
	 //修改甩挂信息
	 $('#jz_edit').dialog({ 
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
				if ($('#jz_edit').form('validate')){
					$.ajax({ 
                            url : '/jz/updatejz', 
                            type : 'POST', 
                            data :{ 
                            	infoId : $.trim($('input[name="jz_infoId_edit"]').val()),
                            	jzId : $.trim($('input[name="jzId_edit"]').val()),
                                
                            	jzCc : $.trim($('#jzCc_edit').combobox('getText')), 
                                jzSj : $.trim($('input[name="jzSj_edit"]').val()), 
                                jzCs : $.trim($('input[name="jzCs_edit"]').val()), 
                                jzZz : $.trim($('input[name="jzZz_edit"]').val()), 
                                jzType : $.trim($('input[name="jzType_edit"]:checked').val()),
                                //jzType : $.trim($('input[name="jzType_edit"]').val()),//禁止修改甩挂类别
//                            	jzCc : $.trim($('input[name="jzCc_edit"]').val()), 
//                              jzSj : $.trim($('input[name="jzSj_edit"]').val()), 
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
                                        mjz : '甩挂信息修改成功！', 
                                    }); 

    								$('#jz_edit').dialog('close').form('reset'); 
                                    $('#jz').datagrid('reload'); 
                                }else if(data == -1) { 
                                	$.messager.alert('错误提示!!!', '报表已结账不能修改,必须修改时请联系调度员！', 'error');
                                	$('#jz_edit').dialog('close').form('reset'); 
                                    $('#jz').datagrid('reload'); 
                                   
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
        		$('#jz_edit').dialog('close').form('reset');
        	}
         }]
    
	 }); 

	 
	  
	 //border-collapse:   separate;   border-spacing:   10px; 
     jz_tool = { 
    		
    		//点击查询
    		 find :function (){ 
    			
    		     $('#jz').datagrid('loadData', {total:0,rows:[]});  
    			 $('#jz').datagrid('load', {    
    				 jzDate : function (){
    						var inforq = $.trim($('input[name="jzDate_jz"]').val());
//    				        var date = new Date();
//    				        if( inforq ) inforq = formatDate(date);//如果info日期为true则设置为当天
    				        return inforq;
    					},
    				depId : $.trim($('input[name="depId_jz"]').val()),
    			 });
            },  
    		//点击新增 粉票
    		add :function (){ 
    			$.messager.confirm('确定 ', '<font color="red">结账后将不能修改数据！！！您要结账吗？</font>',function (flag){ 
                    if (flag){ 
                                            
                        $.ajax({ 
                           type : 'POST', 
                            url : '/cbhs/hsf/addJz', 
                            data :{ 
                            	jiecunMonth : function (){
            						var inforq = $.trim($('input[name="jzDate_jz"]').val());
//            				        var date = new Date();
//            				        if( inforq ) inforq = formatDate(date);//如果info日期为true则设置为当天
            				        return inforq;
            					}, 
                                 
                            }, 
                            beforeSend :function (){ 
                               $('#jz').datagrid('loading'); 
                            }, 
                            success :function (data){ 
                                if (data){ 
                                   $('#jz').datagrid('loaded'); 
                                   $('#jz').datagrid('reload'); 
                                   $('#jz').datagrid('unselectAll'); 
                                   $.messager.show({ 

                                       title : '提示', 
                                       msg : data + '结账成功！', 
                                    }); 
                                } 
                            }, 
                        }); 
                    } 
                }); 
           }, 
           
           //点击删除
           remove :function (){ 
               var rows = $('#jz').datagrid('getSelections'); 
               if (rows.length >0) { 

                   $.messager.confirm('确定 ', '您要删除所选的<strong>' +  rows.length+ '</strong>条记录吗？',function (flag){ 
                       if (flag){ 
                           var ids = []; 
                           
                           for (var i = 0; i < rows.length; i ++) { ids.push(rows[i].jiecunId); 
                           }
                           
                           $.ajax({ 
                              type : 'POST', 
                               url : '/cbhs/hsf/deleteJz', 
                               data :{ 
                                   ids :ids.join(','), 
                                    
                               }, 
                               beforeSend :function (){ 
                                  $('#jz').datagrid('loading'); 
                               }, 
                               success :function (data){ 
                                   if (data){ 
                                      $('#jz').datagrid('loaded'); 
                                      $('#jz').datagrid('reload'); 
                                      $('#jz').datagrid('unselectAll'); 
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
//           //点击修改
//           edit :function (){
//        	   $("#jz_edit_table").show();
//       	      // $('input[name="jzSj_edit"]').css("width","150px");
//        	   //!!!!渲染必须在这个位置否则获取datebox数据不能刷新!!!!!!!!!!!!!!!!!
//        	   $.parser.parse('#jz_edit');//form显示后datebox控件不可用时添加这句扫描代码初始化datebox
//        	   var rows = $('#jz').datagrid('getSelections');
//        	   //console.log(rows);
//        	   if(rows.length > 1){
//        		   $.messager.alert('警告操作', '编辑记录只能选择一条数据！', 'warning');
//        	   } else if(rows.length == 1) {
//					$.ajax({ 
//                        url : '/jz/getjz', 
//                        type : 'POST', 
//                        data :{ 
//                        	jzId : rows[0].jzId,
//                        	infoId : rows[0].infoId,
//                        }, 
//                        beforeSend :function (){ 
//                            $.messager.progress({ 
//                                text : '正在获取中...', 
//                            }); 
//                        }, 
//                        success :function(data, response, status){ 
// 							$.messager.progress('close'); 
//                            if (data) { 
//                            	var obj = $.parseJSON(data);
//                            	//控制台打印:console.log(obj.jzCc);
//                            	
//                            	//成功获取数据显示form表单页面
//                            	$('#jz_edit').form('load',{
//                            		jzId_edit : obj.jz.jzId,
//                            		jz_infoId_edit : obj.jz.infoId,
//                            		
//                                    jzCc_edit : obj.jz.jzCc,
//                                    jzSj_edit : obj.jz.jzSjCharAll,
//                                    jzCs_edit : obj.jz.jzCs,
//                                    jzZz_edit : obj.jz.jzZz,
//                                    jzType_edit : obj.jz.jzType,//页面设置为不能修改
//     
////                            		jzCc_edit : obj.jz.jzCc,
////                            		jzSj_edit : obj.jz.jzSjCharAll,
////                            		
//                            		rq_jz_edit : obj.bInfo.rqChar,
//                            		number_jz_edit : obj.bInfo.number,
//                            	}).dialog('open');
//                            	
//                            }else {
//                            	$.messager.alert('获取失败', '未知错误导致失败！', 'warning');
//                            }
//                        } 
//                    });
//        	   } else if(rows.length == 0) {
//        		   $.messager.alert('警告操作', '编辑记录至少选择一条数据！', 'warning');
//        	   }
//        	   
//           },
      }; 

  
    
    


  	
});

