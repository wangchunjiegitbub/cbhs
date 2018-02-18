



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
	$('#groupSum').datagrid({    
	    url : '/cbhs/hsf/getGroupSumAll',   
	    fit: true,//固定表头
	    striped : true,
	    rownumbers : true,
	    border : false,
	    toolbar: '#groupSum_tool',
	    
//	    sortName : 'groupSumType',
//	    sortOrder : 'asc', 
//	    remoteSort : false,
//	    rowStyler:function(index,row){
//			if (row.fpl>0.003){
//				return 'background-color:pink;color:blue;font-weight:bold;';
//			}
//		},
	    
	    columns:[[    
	        {
	         field:'groupSumId',
	         title:'采购ID',
	         checkbox : true
	         //hidden : true 
	        }, 
	        {
		         field:'shName',
		         title:'商户',
		         
		         width: '100px',
		    }, 
	        {
		         field:'scName',
		         title:'食材名称',
		         
		         width: '100px',
		    }, 
		    {
		         field:'cgSl',
		         title:'数量',
		         
		         width: '100px',
		    }, 
		    {
		         field:'dj',
		         title:'单价',
		         
		         width: '100px',
		    }, 
	        
	        {
	        field:'cgMoney',
	        title:'金额',
	        width: '100px',
	        },   
	        {
		        field:'departmentName',
		        title:'部门',
		        width: '100px',
		        },   
//	        {
//	        field:'groupSumTime',
//	        title:'时间',
//	        formatter: function(value,row,index){
//				if (row.groupSumTime == 1){
//					return '<font color="blue">白班</font>';
//				} else {
//					return '夜班';
//				}
//			}
//	        },    
//	        {
//	        	field:'groupSumMoney',
//	        	title:'金额',
//	        	width: '100px',
//	        },  
//	        {
//	        	field:'groupSumDj',
//	        	title:'单价',
//	        	width: '100px',
//	        	formatter: function(value,row,index){
//						//四舍五入保留两位
//						return (row.groupSumMoney/row.groupSumSl).toFixed(2);
//					
//				}
//	        },  
//	        {
//	        	field:'shName',
//	        	title:'商户',
//	        	width: '100px',
//	        },  
//	        {
//	        	field:'groupSumSj',
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
//	        	field:'groupSumBx',
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
	    	
	    	cgDate : function (){
				var inforq = $.trim($('input[name="groupSumDate_groupSum"]').val());
		        var date = new Date();
		        if( inforq ) inforq = formatDate(date);//如果info日期为true则设置为当天
		        return inforq;
			},
			depId : $.trim($('input[name="depId_groupSum"]').val()),
		}

	 
	});  

	
	
	//添采购信息
	 $('#groupSum_add').dialog({ 
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
				if ($('#groupSum_add').form('validate')){
					$.ajax({ 
                            url : '/cbhs/hsf/addGroupSum', 
                            type : 'POST', 
                            data :{ 
                            	  groupSumDate : $.trim($('input[name="groupSum_date_add"]').val()),
                            	  groupSumScid : $.trim($('input[name="groupSum_scid_add"]').val()),
                            	    groupSumSl : $.trim($('input[name="groupSum_sl_add"]').val()),
                            	 groupSumMoney : $.trim($('input[name="groupSum_money_add"]').val()),
                            	  groupSumShid : $.trim($('input[name="groupSum_shid_add"]').val()),
                            	    groupSumSj : $.trim($('input[name="groupSum_sj_add"]:checked').val()),
                            	  groupSumBak1 : $.trim($('input[name="groupSum_dw_add"]').val()),
                            	
                            	
//                                groupSumCc : $.trim($('#groupSumCc_add').combobox('getText')), 
//                                groupSumSj : $.trim($('input[name="groupSumSj_add"]').val()), 
//                                groupSumCs : $.trim($('input[name="groupSumCs_add"]').val()), 
//                                groupSumZz : $.trim($('input[name="groupSumZz_add"]').val()), 
//                                groupSumType : $.trim($('input[name="groupSumType_add"]:checked').val()), 
                                 
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

    								$('#groupSum_add').dialog('close').form('reset'); 
                                    $('#groupSum').datagrid('reload'); 
                                }else if(data == -1) { 
                                	$.messager.alert('错误提示!!!', '不能创建！', 'error');
                                	$('#groupSum_add').dialog('close').form('reset'); 
                                    $('#groupSum').datagrid('reload'); 
                                   
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
        		$('#groupSum_add').dialog('close').form('reset');
        	}
        	
        
        
        
        }]
    
	 }); 
	 
	 //修改甩挂信息
	 $('#groupSum_edit').dialog({ 
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
				if ($('#groupSum_edit').form('validate')){
					$.ajax({ 
                            url : '/groupSum/updategroupSum', 
                            type : 'POST', 
                            data :{ 
                            	infoId : $.trim($('input[name="groupSum_infoId_edit"]').val()),
                            	groupSumId : $.trim($('input[name="groupSumId_edit"]').val()),
                                
                            	groupSumCc : $.trim($('#groupSumCc_edit').combobox('getText')), 
                                groupSumSj : $.trim($('input[name="groupSumSj_edit"]').val()), 
                                groupSumCs : $.trim($('input[name="groupSumCs_edit"]').val()), 
                                groupSumZz : $.trim($('input[name="groupSumZz_edit"]').val()), 
                                groupSumType : $.trim($('input[name="groupSumType_edit"]:checked').val()),
                                //groupSumType : $.trim($('input[name="groupSumType_edit"]').val()),//禁止修改甩挂类别
//                            	groupSumCc : $.trim($('input[name="groupSumCc_edit"]').val()), 
//                              groupSumSj : $.trim($('input[name="groupSumSj_edit"]').val()), 
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
                                        mgroupSum : '甩挂信息修改成功！', 
                                    }); 

    								$('#groupSum_edit').dialog('close').form('reset'); 
                                    $('#groupSum').datagrid('reload'); 
                                }else if(data == -1) { 
                                	$.messager.alert('错误提示!!!', '报表已结账不能修改,必须修改时请联系调度员！', 'error');
                                	$('#groupSum_edit').dialog('close').form('reset'); 
                                    $('#groupSum').datagrid('reload'); 
                                   
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
        		$('#groupSum_edit').dialog('close').form('reset');
        	}
         }]
    
	 }); 

	 
	  
	 //border-collapse:   separate;   border-spacing:   10px; 
     groupSum_tool = { 
    		//清空选择的数据
    		 delAll :function (){
    			 $('#groupSum_shid').combobox('unselect',$('#groupSum_shid').combobox('getValue')); 
//    			 $('#bx_shid').combobox('unselect',$('#bx_shid').combobox('getValue'));
//    			 $('#bx_sj').combobox('unselect',$('#bx_sj').combobox('getValue'));
    		 },
    		//点击查询
    		 find :function (){ 
    			
    		     $('#groupSum').datagrid('loadData', {total:0,rows:[]});  
    			 $('#groupSum').datagrid('load', {    
    				 cgDate : function (){
    						var inforq = $.trim($('input[name="groupSumDate_groupSum"]').val());
//    				        var date = new Date();
//    				        if( inforq ) inforq = formatDate(date);//如果info日期为true则设置为当天
    				        return inforq;
    					},
    				 cgShid : $.trim($('input[name="groupSum_shid"]').val()),
    				 depId : $.trim($('input[name="depId_groupSum"]').val()),
    			 });
            },  
    		//点击新增 粉票
    		add :function (){ 
        	    $('#groupSum_add').dialog('open');
        	    $("#groupSum_add_table").show();
        	    $.parser.parse('#groupSum_add');//form显示后datebox控件不可用时添加这句扫描代码初始化datebox必须填加id否则发生不确定问题
        	    
        	  //不能用name加datebox只能用id加datebox
                var rq = $('input[name="groupSumDate_groupSum"]').val();//获取查询日期赋值给表单
                $('#groupSum_date_add').datebox('setValue',rq);//如：$("input[name='mydate']").datebox('getValue')，会报TypeError: $.data(...) is undefined。
//                var number = $('input[name="groupSumTime_groupSum"]:checked').val();
//                $("input[name='groupSumTime_groupSum_add'][value=" + number + "]").attr("checked",true);
           }, 
           
           //点击删除
           remove :function (){ 
               var rows = $('#groupSum').datagrid('getSelections'); 
               if (rows.length >0) { 

                   $.messager.confirm('确定 ', '您要删除所选的<strong>' +  rows.length+ '</strong>条记录吗？',function (flag){ 
                       if (flag){ 
                           var ids = []; 
                           
                           for (var i = 0; i < rows.length; i ++) { ids.push(rows[i].groupSumId); 
                           }
                           
                           $.ajax({ 
                              type : 'POST', 
                               url : '/cbhs/hsf/deleteGroupSum', 
                               data :{ 
                                   ids :ids.join(','), 
                                    
                               }, 
                               beforeSend :function (){ 
                                  $('#groupSum').datagrid('loading'); 
                               }, 
                               success :function (data){ 
                                   if (data){ 
                                      $('#groupSum').datagrid('loaded'); 
                                      $('#groupSum').datagrid('reload'); 
                                      $('#groupSum').datagrid('unselectAll'); 
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
        	var cgDate =  $.trim($('input[name="groupSumDate_groupSum"]').val());
//			       
			var  cgShid = $.trim($('input[name="groupSum_shid"]').val());
			var  depId = $.trim($('input[name="depId_groupSum"]').val());
			
        	   
       	    
       	    $('#formQueryGroup').form('submit',{
       	         url: '/cbhs/hsf/downloadGroupSum?cgDate=' + cgDate + '&cgShid=' + cgShid  + '&depId=' + depId
       	    })
       	},
//           //点击修改
//           edit :function (){
//        	   $("#groupSum_edit_table").show();
//       	      // $('input[name="groupSumSj_edit"]').css("width","150px");
//        	   //!!!!渲染必须在这个位置否则获取datebox数据不能刷新!!!!!!!!!!!!!!!!!
//        	   $.parser.parse('#groupSum_edit');//form显示后datebox控件不可用时添加这句扫描代码初始化datebox
//        	   var rows = $('#groupSum').datagrid('getSelections');
//        	   //console.log(rows);
//        	   if(rows.length > 1){
//        		   $.messager.alert('警告操作', '编辑记录只能选择一条数据！', 'warning');
//        	   } else if(rows.length == 1) {
//					$.ajax({ 
//                        url : '/groupSum/getgroupSum', 
//                        type : 'POST', 
//                        data :{ 
//                        	groupSumId : rows[0].groupSumId,
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
//                            	//控制台打印:console.log(obj.groupSumCc);
//                            	
//                            	//成功获取数据显示form表单页面
//                            	$('#groupSum_edit').form('load',{
//                            		groupSumId_edit : obj.groupSum.groupSumId,
//                            		groupSum_infoId_edit : obj.groupSum.infoId,
//                            		
//                                    groupSumCc_edit : obj.groupSum.groupSumCc,
//                                    groupSumSj_edit : obj.groupSum.groupSumSjCharAll,
//                                    groupSumCs_edit : obj.groupSum.groupSumCs,
//                                    groupSumZz_edit : obj.groupSum.groupSumZz,
//                                    groupSumType_edit : obj.groupSum.groupSumType,//页面设置为不能修改
//     
////                            		groupSumCc_edit : obj.groupSum.groupSumCc,
////                            		groupSumSj_edit : obj.groupSum.groupSumSjCharAll,
////                            		
//                            		rq_groupSum_edit : obj.bInfo.rqChar,
//                            		number_groupSum_edit : obj.bInfo.number,
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

