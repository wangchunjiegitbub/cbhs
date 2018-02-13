



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
	$('#bx').datagrid({    
	    url : '/cbhs/hsf/getCgAllNotBx',   
	    
	    striped : true,
	    rownumbers : true,
	    border : false,
	    toolbar: '#bx_tool',
	    
//	    sortName : 'bxType',
//	    sortOrder : 'asc', 
//	    remoteSort : false,
//	    rowStyler:function(index,row){
//			if (row.fpl>0.003){
//				return 'background-color:pink;color:blue;font-weight:bold;';
//			}
//		},
	    
	    columns:[[    
	        {
	         field:'cgId',
	         title:'采购ID',
	         checkbox : true
	         //hidden : true 
	        }, 
	        {
		         field:'cgDate',
		         title:'采购日期',
		         
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
		        field:'cgBak1',
		        title:'单位',
		        width: '100px',
		        },  
//	        {
//	        field:'cgTime',
//	        title:'时间',
//	        formatter: function(value,row,index){
//				if (row.cgTime == 1){
//					return '<font color="blue">白班</font>';
//				} else {
//					return '夜班';
//				}
//			}
//	        },    
	        {
	        	field:'cgMoney',
	        	title:'金额',
	        	width: '100px',
	        }, 
	        {
	        	field:'cgDj',
	        	title:'单价',
	        	width: '100px',
	        	formatter: function(value,row,index){
						//四舍五入保留两位
	        			if(row.cgSl>0){
	        				return (row.cgMoney/row.cgSl).toFixed(2);
	        			}else{
	        				return "";
	        			}
	        				
					
				}
	        },  
	        {
	        	field:'shName',
	        	title:'商户',
	        	width: '100px',
	        },  
	        {
	        	field:'cgSj',
	        	title:'收据',
	        	width: '100px',
	        	formatter: function(value,row,index){
					if (value == 1){
						return '有';
					} else if(value == 0) {
						return '<font color="red">无</font>';
					}else{
						return "";
					}
				}
	        }, 
	        {
	        	field:'cgBx',
	        	title:'报销',
	        	width: '100px',
	        	formatter: function(value,row,index){
					if (value == 1){
						return '已报销';
					} else if(value == 0) {
						return '<font color="red">未报销</font>';
					} else {
						return "";
					}
				}
	        	
	        },
//	       
	        
	    ]],
	    queryParams: {
	    	
	    	depId : $.trim($('input[name="depId_bx"]').val()),
		}

	 
	});  

	
	
	//添采购信息
	 $('#bx_add').dialog({ 
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
				if ($('#bx_add').form('validate')){
					$.ajax({ 
                            url : '/cbhs/hsf/addBx', 
                            type : 'POST', 
                            data :{ 
                            	  bxDate : $.trim($('input[name="bx_date_add"]').val()),
                            	  bxScid : $.trim($('input[name="bx_scid_add"]').val()),
                            	    bxSl : $.trim($('input[name="bx_sl_add"]').val()),
                            	 bxMoney : $.trim($('input[name="bx_money_add"]').val()),
                            	  bxShid : $.trim($('input[name="bx_shid_add"]').val()),
                            	    bxSj : $.trim($('input[name="bx_sj_add"]:checked').val()),
                            	
                            	
                            	
//                                bxCc : $.trim($('#bxCc_add').combobox('getText')), 
//                                bxSj : $.trim($('input[name="bxSj_add"]').val()), 
//                                bxCs : $.trim($('input[name="bxCs_add"]').val()), 
//                                bxZz : $.trim($('input[name="bxZz_add"]').val()), 
//                                bxType : $.trim($('input[name="bxType_add"]:checked').val()), 
                                 
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

    								$('#bx_add').dialog('close').form('reset'); 
                                    $('#bx').datagrid('reload'); 
                                }else if(data == -1) { 
                                	$.messager.alert('错误提示!!!', '不能创建！', 'error');
                                	$('#bx_add').dialog('close').form('reset'); 
                                    $('#bx').datagrid('reload'); 
                                   
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
        		$('#bx_add').dialog('close').form('reset');
        	}
        	
        
        
        
        }]
    
	 }); 
	 
	 //修改甩挂信息
	 $('#bx_edit').dialog({ 
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
				if ($('#bx_edit').form('validate')){
					$.ajax({ 
                            url : '/bx/updatebx', 
                            type : 'POST', 
                            data :{ 
                            	infoId : $.trim($('input[name="bx_infoId_edit"]').val()),
                            	bxId : $.trim($('input[name="bxId_edit"]').val()),
                                
                            	bxCc : $.trim($('#bxCc_edit').combobox('getText')), 
                                bxSj : $.trim($('input[name="bxSj_edit"]').val()), 
                                bxCs : $.trim($('input[name="bxCs_edit"]').val()), 
                                bxZz : $.trim($('input[name="bxZz_edit"]').val()), 
                                bxType : $.trim($('input[name="bxType_edit"]:checked').val()),
                                //bxType : $.trim($('input[name="bxType_edit"]').val()),//禁止修改甩挂类别
//                            	bxCc : $.trim($('input[name="bxCc_edit"]').val()), 
//                              bxSj : $.trim($('input[name="bxSj_edit"]').val()), 
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
                                        mbx : '甩挂信息修改成功！', 
                                    }); 

    								$('#bx_edit').dialog('close').form('reset'); 
                                    $('#bx').datagrid('reload'); 
                                }else if(data == -1) { 
                                	$.messager.alert('错误提示!!!', '报表已结账不能修改,必须修改时请联系调度员！', 'error');
                                	$('#bx_edit').dialog('close').form('reset'); 
                                    $('#bx').datagrid('reload'); 
                                   
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
        		$('#bx_edit').dialog('close').form('reset');
        	}
         }]
    
	 }); 

	 
	  
	 //border-collapse:   separate;   border-spacing:   10px; 
     bx_tool = { 
    		//清空选择的数据
    		 delAll :function (){
    			 $('#bx_scid').combobox('unselect',$('#bx_scid').combobox('getValue')); 
    			 $('#bx_shid').combobox('unselect',$('#bx_shid').combobox('getValue'));
    			 $('#bx_sj').combobox('unselect',$('#bx_sj').combobox('getValue'));
    		 },
    		//点击查询
    		 find :function (){ 
    			 
				 $('#bx').datagrid('loadData', {total:0,rows:[]});  
    			 $('#bx').datagrid('load', {    
    				 bxDate : function (){
    						var inforq = $.trim($('input[name="bxDate_bx"]').val());
//    				        var date = new Date();
//    				        if( inforq ) inforq = formatDate(date);//如果info日期为true则设置为当天
    				        return inforq;
    					} ,
    				cgScid : $.trim($('input[name="bx_scid"]').val()),
    				cgShid : $.trim($('input[name="bx_shid"]').val()),
    				  cgSj : $.trim($('input[name="bx_sj"]').val()),
    				  depId : $.trim($('input[name="depId_bx"]').val()),
    			 });
            },  
    		//点击新增 粉票
    		add :function (){ 
        	    $('#bx_add').dialog('open');
        	    $("#bx_add_table").show();
        	    $.parser.parse('#bx_add');//form显示后datebox控件不可用时添加这句扫描代码初始化datebox必须填加id否则发生不确定问题
        	    
        	  //不能用name加datebox只能用id加datebox
                var rq = $('input[name="bxDate_bx"]').val();//获取查询日期赋值给表单
                $('#bx_date_add').datebox('setValue',rq);//如：$("input[name='mydate']").datebox('getValue')，会报TypeError: $.data(...) is undefined。
//                var number = $('input[name="bxTime_bx"]:checked').val();
//                $("input[name='bxTime_bx_add'][value=" + number + "]").attr("checked",true);
           }, 
           
           //点击删除
           remove :function (){ 
               var rows = $('#bx').datagrid('getSelections'); 
               if (rows.length >0) { 

                   $.messager.confirm('确定 ', '<font color="red">报销后不能撤销！！！</font>您要报销所选的<strong>' +  rows.length+ '</strong>条记录吗？',function (flag){ 
                       if (flag){ 
                           var ids = []; 
                           
                           for (var i = 0; i < rows.length; i ++) { ids.push(rows[i].cgId); 
                           }
                           
                           $.ajax({ 
                              type : 'POST', 
                               url : '/cbhs/hsf/cgBx', 
                               data :{ 
                                   ids :ids.join(','), 
                                    
                               }, 
                               beforeSend :function (){ 
                                  $('#bx').datagrid('loading'); 
                               }, 
                               success :function (data){ 
                                   if (data){ 
                                      $('#bx').datagrid('loaded'); 
                                      $('#bx').datagrid('reload'); 
                                      $('#bx').datagrid('unselectAll'); 
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
//           //点击修改
//           edit :function (){
//        	   $("#bx_edit_table").show();
//       	      // $('input[name="bxSj_edit"]').css("width","150px");
//        	   //!!!!渲染必须在这个位置否则获取datebox数据不能刷新!!!!!!!!!!!!!!!!!
//        	   $.parser.parse('#bx_edit');//form显示后datebox控件不可用时添加这句扫描代码初始化datebox
//        	   var rows = $('#bx').datagrid('getSelections');
//        	   //console.log(rows);
//        	   if(rows.length > 1){
//        		   $.messager.alert('警告操作', '编辑记录只能选择一条数据！', 'warning');
//        	   } else if(rows.length == 1) {
//					$.ajax({ 
//                        url : '/bx/getbx', 
//                        type : 'POST', 
//                        data :{ 
//                        	bxId : rows[0].bxId,
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
//                            	//控制台打印:console.log(obj.bxCc);
//                            	
//                            	//成功获取数据显示form表单页面
//                            	$('#bx_edit').form('load',{
//                            		bxId_edit : obj.bx.bxId,
//                            		bx_infoId_edit : obj.bx.infoId,
//                            		
//                                    bxCc_edit : obj.bx.bxCc,
//                                    bxSj_edit : obj.bx.bxSjCharAll,
//                                    bxCs_edit : obj.bx.bxCs,
//                                    bxZz_edit : obj.bx.bxZz,
//                                    bxType_edit : obj.bx.bxType,//页面设置为不能修改
//     
////                            		bxCc_edit : obj.bx.bxCc,
////                            		bxSj_edit : obj.bx.bxSjCharAll,
////                            		
//                            		rq_bx_edit : obj.bInfo.rqChar,
//                            		number_bx_edit : obj.bInfo.number,
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

