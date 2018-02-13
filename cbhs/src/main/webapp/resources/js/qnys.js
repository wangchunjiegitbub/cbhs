



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
	$('#qnys').datagrid({    
	    url : '/fpys/qnys/getQnysAll',   
	    
	    striped : true,
	    rownumbers : true,
	    border : false,
	    toolbar: '#qnys_tool',
	    
//	    sortName : 'qnysType',
//	    sortOrder : 'asc', 
//	    remoteSort : false,
	    rowStyler:function(index,row){
			if (row.fpl>0.003){
				return 'background-color:pink;color:blue;font-weight:bold;';
			}
		},
	    
	    columns:[[    
	        {
	         field:'cbfpId',
	         title:'成本分劈ID',
	         checkbox : true
	         //hidden : true 
	        }, 
	        {
		         field:'kemuName',
		         title:'费用科目',
		         
		         width: '100px',
		        }, 
	        
	        {
	        field:'departmentName',
	        title:'分劈科室',
	        width: '100px',
	        },    
//	        {
//	        field:'qnysTime',
//	        title:'时间',
//	        formatter: function(value,row,index){
//				if (row.qnysTime == 1){
//					return '<font color="blue">白班</font>';
//				} else {
//					return '夜班';
//				}
//			}
//	        },    
	        {
	        	field:'kemuFpxm',
	        	title:'分劈项目',
	        	width: '100px',
	        },    
	        {
	        	field:'userZw',
	        	title:'科目负责人',
	        	width: '100px',
	        }, 
	        {
	        	field:'kemuSum',
	        	title:'分劈预算合计',
	        	width: '100px',
	        	
	        },
//	       
	        
	    ]],
	    queryParams: {
	    	
	    	inDate : function (){
				var inforq = $.trim($('input[name="qnysDate_qnys"]').val());
		        var date = new Date();
		        if( inforq ) inforq = formatDate(date);//如果info日期为true则设置为当天
		        return inforq;
			}
		}

	 
	});  

	
	
	//添加点名会
	 $('#qnys_add').dialog({ 
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
				if ($('#qnys_add').form('validate')){
					$.ajax({ 
                            url : '/fpys/qnys/addQnys', 
                            type : 'POST', 
                            data :{ 
                            	fpNumber : $.trim($('input[name="qnys_fp_add"]').val()),
                            	spNumber : $.trim($('input[name="qnys_sp_add"]').val()),
                            	inDate : $.trim($('input[name="qnys_date__add"]').val()),
//                                qnysCc : $.trim($('#qnysCc_add').combobox('getText')), 
//                                qnysSj : $.trim($('input[name="qnysSj_add"]').val()), 
//                                qnysCs : $.trim($('input[name="qnysCs_add"]').val()), 
//                                qnysZz : $.trim($('input[name="qnysZz_add"]').val()), 
//                                qnysType : $.trim($('input[name="qnysType_add"]:checked').val()), 
                                 
                            }, 
                            beforeSend :function (){ 
                                $.messager.progress({ 

                                    text : '正在提交甩挂信息...', 
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

    								$('#qnys_add').dialog('close').form('reset'); 
                                    $('#qnys').datagrid('reload'); 
                                }else if(data == -1) { 
                                	$.messager.alert('错误提示!!!', '不能重复创建点名会！', 'error');
                                	$('#qnys_add').dialog('close').form('reset'); 
                                    $('#qnys').datagrid('reload'); 
                                   
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
        		$('#qnys_add').dialog('close').form('reset');
        	}
        	
        
        
        
        }]
    
	 }); 
	 
	 //修改甩挂信息
	 $('#qnys_edit').dialog({ 
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
				if ($('#qnys_edit').form('validate')){
					$.ajax({ 
                            url : '/qnys/updateqnys', 
                            type : 'POST', 
                            data :{ 
                            	infoId : $.trim($('input[name="qnys_infoId_edit"]').val()),
                            	qnysId : $.trim($('input[name="qnysId_edit"]').val()),
                                
                            	qnysCc : $.trim($('#qnysCc_edit').combobox('getText')), 
                                qnysSj : $.trim($('input[name="qnysSj_edit"]').val()), 
                                qnysCs : $.trim($('input[name="qnysCs_edit"]').val()), 
                                qnysZz : $.trim($('input[name="qnysZz_edit"]').val()), 
                                qnysType : $.trim($('input[name="qnysType_edit"]:checked').val()),
                                //qnysType : $.trim($('input[name="qnysType_edit"]').val()),//禁止修改甩挂类别
//                            	qnysCc : $.trim($('input[name="qnysCc_edit"]').val()), 
//                              qnysSj : $.trim($('input[name="qnysSj_edit"]').val()), 
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
                                        mqnys : '甩挂信息修改成功！', 
                                    }); 

    								$('#qnys_edit').dialog('close').form('reset'); 
                                    $('#qnys').datagrid('reload'); 
                                }else if(data == -1) { 
                                	$.messager.alert('错误提示!!!', '报表已结账不能修改,必须修改时请联系调度员！', 'error');
                                	$('#qnys_edit').dialog('close').form('reset'); 
                                    $('#qnys').datagrid('reload'); 
                                   
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
        		$('#qnys_edit').dialog('close').form('reset');
        	}
         }]
    
	 }); 

	 
	  
	 //border-collapse:   separate;   border-spacing:   10px; 
     qnys_tool = { 
    		
    		//点击查询
    		 find :function (){ 
    			
    		     $('#qnys').datagrid('loadData', {total:0,rows:[]});  
    			 $('#qnys').datagrid('load', {    
    				 inDate : function (){
    						var inforq = $.trim($('input[name="qnysDate_qnys"]').val());
//    				        var date = new Date();
//    				        if( inforq ) inforq = formatDate(date);//如果info日期为true则设置为当天
    				        return inforq;
    					} 
    			 });
            },  
    		//点击新增 粉票
    		addf :function (){ 
        	    $('#qnys_add').dialog('open');
        	    $("#qnys_add_table").show();
        	    $.parser.parse('#qnys_add');//form显示后datebox控件不可用时添加这句扫描代码初始化datebox必须填加id否则发生不确定问题
        	    
        	  //不能用name加datebox只能用id加datebox
                var rq = $('input[name="qnysDate_qnys"]').val();//获取查询日期赋值给表单
                $('#qnysDate_qnys_add').datebox('setValue',rq);//如：$("input[name='mydate']").datebox('getValue')，会报TypeError: $.data(...) is undefined。
                var number = $('input[name="qnysTime_qnys"]:checked').val();
                $("input[name='qnysTime_qnys_add'][value=" + number + "]").attr("checked",true);
           }, 
           //点击新增 蓝票
	   		addl :function (){ 
	       	    $('#qnys_add').dialog('open');
	       	    $("#qnys_add_table").show();
	       	    $.parser.parse('#qnys_add');//form显示后datebox控件不可用时添加这句扫描代码初始化datebox必须填加id否则发生不确定问题
	       	    
	       	  //不能用name加datebox只能用id加datebox
	               var rq = $('input[name="qnysDate_qnys"]').val();//获取查询日期赋值给表单
	               $('#qnysDate_qnys_add').datebox('setValue',rq);//如：$("input[name='mydate']").datebox('getValue')，会报TypeError: $.data(...) is undefined。
	               var number = $('input[name="qnysTime_qnys"]:checked').val();
	               $("input[name='qnysTime_qnys_add'][value=" + number + "]").attr("checked",true);
	          }, 
           //点击删除
           remove :function (){ 
               var rows = $('#qnys').datagrid('getSelections'); 
               if (rows.length >0) { 

                   $.messager.confirm('确定 ', '您要删除所选的<strong>' +  rows.length+ '</strong>条记录吗？',function (flag){ 
                       if (flag){ 
                           var ids = []; 
                           
                           for (var i = 0; i < rows.length; i ++) { ids.push(rows[i].qnysId); 
                           }
                           
                           $.ajax({ 
                              type : 'POST', 
                               url : '/fpys/qnys/deleteqnys', 
                               data :{ 
                                   ids :ids.join(','), 
                                    
                               }, 
                               beforeSend :function (){ 
                                  $('#qnys').datagrid('loading'); 
                               }, 
                               success :function (data){ 
                                   if (data){ 
                                      $('#qnys').datagrid('loaded'); 
                                      $('#qnys').datagrid('reload'); 
                                      $('#qnys').datagrid('unselectAll'); 
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
//        	   $("#qnys_edit_table").show();
//       	      // $('input[name="qnysSj_edit"]').css("width","150px");
//        	   //!!!!渲染必须在这个位置否则获取datebox数据不能刷新!!!!!!!!!!!!!!!!!
//        	   $.parser.parse('#qnys_edit');//form显示后datebox控件不可用时添加这句扫描代码初始化datebox
//        	   var rows = $('#qnys').datagrid('getSelections');
//        	   //console.log(rows);
//        	   if(rows.length > 1){
//        		   $.messager.alert('警告操作', '编辑记录只能选择一条数据！', 'warning');
//        	   } else if(rows.length == 1) {
//					$.ajax({ 
//                        url : '/qnys/getqnys', 
//                        type : 'POST', 
//                        data :{ 
//                        	qnysId : rows[0].qnysId,
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
//                            	//控制台打印:console.log(obj.qnysCc);
//                            	
//                            	//成功获取数据显示form表单页面
//                            	$('#qnys_edit').form('load',{
//                            		qnysId_edit : obj.qnys.qnysId,
//                            		qnys_infoId_edit : obj.qnys.infoId,
//                            		
//                                    qnysCc_edit : obj.qnys.qnysCc,
//                                    qnysSj_edit : obj.qnys.qnysSjCharAll,
//                                    qnysCs_edit : obj.qnys.qnysCs,
//                                    qnysZz_edit : obj.qnys.qnysZz,
//                                    qnysType_edit : obj.qnys.qnysType,//页面设置为不能修改
//     
////                            		qnysCc_edit : obj.qnys.qnysCc,
////                            		qnysSj_edit : obj.qnys.qnysSjCharAll,
////                            		
//                            		rq_qnys_edit : obj.bInfo.rqChar,
//                            		number_qnys_edit : obj.bInfo.number,
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

