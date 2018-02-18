



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
	$('#szmx').datagrid({    
	    url : '/cbhs/hsf/getSzmxAll',   
	    fit: true,//固定表头
	    striped : true,
	    rownumbers : true,
	    border : false,
	    toolbar: '#szmx_tool',
	    
//	    sortName : 'szmxType',
//	    sortOrder : 'asc', 
//	    remoteSort : false,
//	    rowStyler:function(index,row){
//			if (row.fpl>0.003){
//				return 'background-color:pink;color:blue;font-weight:bold;';
//			}
//		},
	    
	    columns:[[    
	       
	        {
		         field:'szmxDate',
		         title:'日期',
		         
		         width: '100px',
		    }, 
	        {
		         field:'zhaiyao',
		         title:'摘要',
		         
		         width: '100px',
		    }, 
	        
	        {
	        field:'dinge',
	        title:'定额',
	        width: '100px',
	        },    
//	        {
//	        field:'szmxTime',
//	        title:'时间',
//	        formatter: function(value,row,index){
//				if (row.szmxTime == 1){
//					return '<font color="blue">白班</font>';
//				} else {
//					return '夜班';
//				}
//			}
//	        },    
	        {
	        	field:'shouru',
	        	title:'收入',
	        	width: '100px',
	        	 formatter: function(value,row,index){
	 				if (value == 0){
	 					return '';
	 				} else{
	 					return value
	 				}
	 				
	 			}
	        },  
	        {
	        	field:'zhichu',
	        	
	        	title:'采购金额（支出）',
	        	width: '100px',
	        	 formatter: function(value,row,index){
		 				if (value == 0){
		 					return '';
		 				} else{
		 					return value
		 				}
	        	 }
	        },  
	        {
	        	field:'jieyu',
	        	title:'结余',
	        	width: '100px',
	        	
	        }, 
	        {
	        	field:'beizhu',
	        	title:'备注',
	        	width: '100px',
	        	
	        	
	        },
	        {
	        	
	        	field:'rs',
	        	title:'人员变动情况',
	        	width: '100px',
	        }, 
//	       
	        
	    ]],
	    queryParams: {
	    	
	    	formEndDate : function (){
				var inforq = $.trim($('input[name="szmxDate_szmx"]').val());
		        var date = new Date();
		        if( inforq ) inforq = formatDate(date);//如果info日期为true则设置为当天
		        return inforq;
			},
			depId : $.trim($('input[name="depId_szmx"]').val()),
		}

	 
	});  

	
	
	//添采购信息
	 $('#szmx_add').dialog({ 
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
				if ($('#szmx_add').form('validate')){
					$.ajax({ 
                            url : '/cbhs/hsf/addSzmx', 
                            type : 'POST', 
                            data :{ 
                            	  szmxDate : $.trim($('input[name="szmx_date_add"]').val()),
                            	  szmxScid : $.trim($('input[name="szmx_scid_add"]').val()),
                            	    szmxSl : $.trim($('input[name="szmx_sl_add"]').val()),
                            	 szmxMoney : $.trim($('input[name="szmx_money_add"]').val()),
                            	  szmxShid : $.trim($('input[name="szmx_shid_add"]').val()),
                            	    szmxSj : $.trim($('input[name="szmx_sj_add"]:checked').val()),
                            	
                            	
                            	
//                                szmxCc : $.trim($('#szmxCc_add').combobox('getText')), 
//                                szmxSj : $.trim($('input[name="szmxSj_add"]').val()), 
//                                szmxCs : $.trim($('input[name="szmxCs_add"]').val()), 
//                                szmxZz : $.trim($('input[name="szmxZz_add"]').val()), 
//                                szmxType : $.trim($('input[name="szmxType_add"]:checked').val()), 
                                 
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

    								$('#szmx_add').dialog('close').form('reset'); 
                                    $('#szmx').datagrid('reload'); 
                                }else if(data == -1) { 
                                	$.messager.alert('错误提示!!!', '不能创建！', 'error');
                                	$('#szmx_add').dialog('close').form('reset'); 
                                    $('#szmx').datagrid('reload'); 
                                   
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
        		$('#szmx_add').dialog('close').form('reset');
        	}
        	
        
        
        
        }]
    
	 }); 
	 
	 //修改甩挂信息
	 $('#szmx_edit').dialog({ 
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
				if ($('#szmx_edit').form('validate')){
					$.ajax({ 
                            url : '/szmx/updateszmx', 
                            type : 'POST', 
                            data :{ 
                            	infoId : $.trim($('input[name="szmx_infoId_edit"]').val()),
                            	szmxId : $.trim($('input[name="szmxId_edit"]').val()),
                                
                            	szmxCc : $.trim($('#szmxCc_edit').combobox('getText')), 
                                szmxSj : $.trim($('input[name="szmxSj_edit"]').val()), 
                                szmxCs : $.trim($('input[name="szmxCs_edit"]').val()), 
                                szmxZz : $.trim($('input[name="szmxZz_edit"]').val()), 
                                szmxType : $.trim($('input[name="szmxType_edit"]:checked').val()),
                                //szmxType : $.trim($('input[name="szmxType_edit"]').val()),//禁止修改甩挂类别
//                            	szmxCc : $.trim($('input[name="szmxCc_edit"]').val()), 
//                              szmxSj : $.trim($('input[name="szmxSj_edit"]').val()), 
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
                                        mszmx : '甩挂信息修改成功！', 
                                    }); 

    								$('#szmx_edit').dialog('close').form('reset'); 
                                    $('#szmx').datagrid('reload'); 
                                }else if(data == -1) { 
                                	$.messager.alert('错误提示!!!', '报表已结账不能修改,必须修改时请联系调度员！', 'error');
                                	$('#szmx_edit').dialog('close').form('reset'); 
                                    $('#szmx').datagrid('reload'); 
                                   
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
        		$('#szmx_edit').dialog('close').form('reset');
        	}
         }]
    
	 }); 

	 
	  
	 //border-collapse:   separate;   border-spacing:   10px; 
     szmx_tool = { 
    		
    		//点击查询
    		 find :function (){ 
    			
    		     $('#szmx').datagrid('loadData', {total:0,rows:[]});  
    			 $('#szmx').datagrid('load', {    
    				 formEndDate : function (){
    						var inforq = $.trim($('input[name="szmxDate_szmx"]').val());
//    				        var date = new Date();
//    				        if( inforq ) inforq = formatDate(date);//如果info日期为true则设置为当天
    				        return inforq;
    					},
    					depId : $.trim($('input[name="depId_szmx"]').val()),
    			 });
            },  
    		//点击新增 粉票
    		add :function (){ 
        	    $('#szmx_add').dialog('open');
        	    $("#szmx_add_table").show();
        	    $.parser.parse('#szmx_add');//form显示后datebox控件不可用时添加这句扫描代码初始化datebox必须填加id否则发生不确定问题
        	    
        	  //不能用name加datebox只能用id加datebox
                var rq = $('input[name="szmxDate_szmx"]').val();//获取查询日期赋值给表单
                $('#szmx_date_add').datebox('setValue',rq);//如：$("input[name='mydate']").datebox('getValue')，会报TypeError: $.data(...) is undefined。
//                var number = $('input[name="szmxTime_szmx"]:checked').val();
//                $("input[name='szmxTime_szmx_add'][value=" + number + "]").attr("checked",true);
           }, 
           
           //点击删除
           remove :function (){ 
               var rows = $('#szmx').datagrid('getSelections'); 
               if (rows.length >0) { 

                   $.messager.confirm('确定 ', '您要删除所选的<strong>' +  rows.length+ '</strong>条记录吗？',function (flag){ 
                       if (flag){ 
                           var ids = []; 
                           
                           for (var i = 0; i < rows.length; i ++) { ids.push(rows[i].szmxId); 
                           }
                           
                           $.ajax({ 
                              type : 'POST', 
                               url : '/cbhs/hsf/deleteSzmx', 
                               data :{ 
                                   ids :ids.join(','), 
                                    
                               }, 
                               beforeSend :function (){ 
                                  $('#szmx').datagrid('loading'); 
                               }, 
                               success :function (data){ 
                                   if (data){ 
                                      $('#szmx').datagrid('loaded'); 
                                      $('#szmx').datagrid('reload'); 
                                      $('#szmx').datagrid('unselectAll'); 
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
           exportData :function (){
        	    
        	   
        	    var formEndDate = $.trim($('input[name="szmxDate_szmx"]').val());
        	    var depId = $.trim($('input[name="depId_szmx"]').val());
        	    $('#formQuery').form('submit',{
        	         url: '/cbhs/hsf/downloadSzmx?formEndDate=' + formEndDate + '&depId=' + depId
        	    })
        	},
//           //点击修改
//           edit :function (){
//        	   $("#szmx_edit_table").show();
//       	      // $('input[name="szmxSj_edit"]').css("width","150px");
//        	   //!!!!渲染必须在这个位置否则获取datebox数据不能刷新!!!!!!!!!!!!!!!!!
//        	   $.parser.parse('#szmx_edit');//form显示后datebox控件不可用时添加这句扫描代码初始化datebox
//        	   var rows = $('#szmx').datagrid('getSelections');
//        	   //console.log(rows);
//        	   if(rows.length > 1){
//        		   $.messager.alert('警告操作', '编辑记录只能选择一条数据！', 'warning');
//        	   } else if(rows.length == 1) {
//					$.ajax({ 
//                        url : '/szmx/getszmx', 
//                        type : 'POST', 
//                        data :{ 
//                        	szmxId : rows[0].szmxId,
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
//                            	//控制台打印:console.log(obj.szmxCc);
//                            	
//                            	//成功获取数据显示form表单页面
//                            	$('#szmx_edit').form('load',{
//                            		szmxId_edit : obj.szmx.szmxId,
//                            		szmx_infoId_edit : obj.szmx.infoId,
//                            		
//                                    szmxCc_edit : obj.szmx.szmxCc,
//                                    szmxSj_edit : obj.szmx.szmxSjCharAll,
//                                    szmxCs_edit : obj.szmx.szmxCs,
//                                    szmxZz_edit : obj.szmx.szmxZz,
//                                    szmxType_edit : obj.szmx.szmxType,//页面设置为不能修改
//     
////                            		szmxCc_edit : obj.szmx.szmxCc,
////                            		szmxSj_edit : obj.szmx.szmxSjCharAll,
////                            		
//                            		rq_szmx_edit : obj.bInfo.rqChar,
//                            		number_szmx_edit : obj.bInfo.number,
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

