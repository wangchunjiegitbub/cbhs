



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
	$('#cg').datagrid({    
	    url : '/cbhs/hsf/getCgAll',   
	    fit: true,//固定表头
	    striped : true,
	    rownumbers : true,
	    border : false,
	    toolbar: '#cg_tool',
	    
//	    sortName : 'cgType',
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
					if(typeof(row.cgSl) == "undefined" ){
						return "";
					}else{
						//四舍五入保留两位
						return (row.cgMoney/row.cgSl).toFixed(2);
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
					} else {
						return '<font color="red">无</font>';
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
					} else {
						return '<font color="red">未报销</font>';
					}
				}
	        	
	        },
//	       
	        
	    ]],
	    queryParams: {
	    	
	    	beginDate : function (){
				var inforq = $.trim($('input[name="cgDate_cg_begin"]').val());
		        var date = new Date();
		        if( inforq ) inforq = formatDate(date);//如果info日期为true则设置为当天
		        return inforq;
			},
			endDate : function (){
				var inforq = $.trim($('input[name="cgDate_cg_end"]').val());
		        var date = new Date();
		        if( inforq ) inforq = formatDate(date);//如果info日期为true则设置为当天
		        return inforq;
			},
			depId : $.trim($('input[name="depId_cg"]').val()),
			
		}

	 
	});  

	
	
	//添采购信息
	 $('#cg_add').dialog({ 
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
				if ($('#cg_add').form('validate')){
					$.ajax({ 
                            url : '/cbhs/hsf/addCg', 
                            type : 'POST', 
                            data :{ 
                            	  cgDate : $.trim($('input[name="cg_date_add"]').val()),
                            	  cgScid : $.trim($('input[name="cg_scid_add"]').val()),
                            	    cgSl : $.trim($('input[name="cg_sl_add"]').val()),
                            	 cgMoney : $.trim($('input[name="cg_money_add"]').val()),
                            	  cgShid : $.trim($('input[name="cg_shid_add"]').val()),
                            	    cgSj : $.trim($('input[name="cg_sj_add"]:checked').val()),
                            	  cgBak1 : $.trim($('input[name="cg_dw_add"]').val()),
                            	
                            	
//                                cgCc : $.trim($('#cgCc_add').combobox('getText')), 
//                                cgSj : $.trim($('input[name="cgSj_add"]').val()), 
//                                cgCs : $.trim($('input[name="cgCs_add"]').val()), 
//                                cgZz : $.trim($('input[name="cgZz_add"]').val()), 
//                                cgType : $.trim($('input[name="cgType_add"]:checked').val()), 
                                 
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

    								$('#cg_add').dialog('close').form('reset'); 
                                    $('#cg').datagrid('reload'); 
                                }else if(data == -1) { 
                                	$.messager.alert('错误提示!!!', '不能创建！', 'error');
                                	$('#cg_add').dialog('close').form('reset'); 
                                    $('#cg').datagrid('reload'); 
                                   
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
        		$('#cg_add').dialog('close').form('reset');
        	}
        	
        
        
        
        }]
    
	 }); 
	 
	 //修改甩挂信息
	 $('#cg_edit').dialog({ 
        width :350, 
        top : 150,
        title : '修改信息' ,
        modal : true,
		closed : true,
        iconCls : 'icon-edit', 
        buttons : [{
        	text : '提交' ,
        	iconCls : 'icon-door-in',
			//点击提交时执行
        	handler : function(){
        		//验证表单成执行
				if ($('#cg_edit').form('validate')){
					$.ajax({ 
                            url : '/cbhs/hsf/updateCg', 
                            type : 'POST', 
                            data :{ 
                            	cgId : $.trim($('input[name="cg_id_edit"]').val()),
                              cgDate : $.trim($('input[name="cg_date_edit"]').val()),
                          	  cgScid : $.trim($('input[name="cg_scid_edit"]').val()),
                          	    cgSl : $.trim($('input[name="cg_sl_edit"]').val()),
                          	 cgMoney : $.trim($('input[name="cg_money_edit"]').val()),
                          	  cgShid : $.trim($('input[name="cg_shid_edit"]').val()),
                          	    cgSj : $.trim($('input[name="cg_sj_edit"]:checked').val()),
                          	  cgBak1 : $.trim($('input[name="cg_dw_edit"]').val()), 
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

    								$('#cg_edit').dialog('close').form('reset'); 
                                    $('#cg').datagrid('reload'); 
                                }else if(data == -1) { 
                                	$.messager.alert('错误提示!!!', '不能修改信息！', 'error');
                                	$('#cg_edit').dialog('close').form('reset'); 
                                    $('#cg').datagrid('reload'); 
                                   
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
        		$('#cg_edit').dialog('close').form('reset');
        	}
         }]
    
	 }); 

	 
	  
	 //border-collapse:   separate;   border-spacing:   10px; 
     cg_tool = { 
    		
    		//点击查询
    		 find :function (){ 
    			
    		     $('#cg').datagrid('loadData', {total:0,rows:[]});  
    			 $('#cg').datagrid('load', {    
    				 beginDate : function (){
    						var inforq = $.trim($('input[name="cgDate_cg_begin"]').val());
//    				        var date = new Date();
//    				        if( inforq ) inforq = formatDate(date);//如果info日期为true则设置为当天
    				        return inforq;
    					} ,
    					endDate : function (){
    						var inforq = $.trim($('input[name="cgDate_cg_end"]').val());
//    				        var date = new Date();
//    				        if( inforq ) inforq = formatDate(date);//如果info日期为true则设置为当天
    				        return inforq;
    					},
    					depId : $.trim($('input[name="depId_cg"]').val()),
    			 });
            },  
    		//点击新增 粉票
    		add :function (){ 
        	    $('#cg_add').dialog('open');
        	    $("#cg_add_table").show();
        	    $.parser.parse('#cg_add');//form显示后datebox控件不可用时添加这句扫描代码初始化datebox必须填加id否则发生不确定问题
        	    
        	  //不能用name加datebox只能用id加datebox
                var rq = $('input[name="cgDate_cg_end"]').val();//获取查询日期赋值给表单
                $('#cg_date_add').datebox('setValue',rq);//如：$("input[name='mydate']").datebox('getValue')，会报TypeError: $.data(...) is undefined。
//                var number = $('input[name="cgTime_cg"]:checked').val();
//                $("input[name='cgTime_cg_add'][value=" + number + "]").attr("checked",true);
           }, 
           
           //点击删除
           remove :function (){ 
               var rows = $('#cg').datagrid('getSelections'); 
               if (rows.length >0) { 

                   $.messager.confirm('确定 ', '您要删除所选的<strong>' +  rows.length+ '</strong>条记录吗？',function (flag){ 
                       if (flag){ 
                           var ids = []; 
                           
                           for (var i = 0; i < rows.length; i ++) { ids.push(rows[i].cgId); 
                           }
                           
                           $.ajax({ 
                              type : 'POST', 
                               url : '/cbhs/hsf/deleteCg', 
                               data :{ 
                                   ids :ids.join(','), 
                                    
                               }, 
                               beforeSend :function (){ 
                                  $('#cg').datagrid('loading'); 
                               }, 
                               success :function (data){ 
                                   if (data){ 
                                      $('#cg').datagrid('loaded'); 
                                      $('#cg').datagrid('reload'); 
                                      $('#cg').datagrid('unselectAll'); 
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
        	   $("#cg_edit_table").show();
       	      // $('input[name="cgSj_edit"]').css("width","150px");
        	   //!!!!渲染必须在这个位置否则获取datebox数据不能刷新!!!!!!!!!!!!!!!!!
        	   $.parser.parse('#cg_edit');//form显示后datebox控件不可用时添加这句扫描代码初始化datebox
        	   var rows = $('#cg').datagrid('getSelections');
        	   //console.log(rows);
        	   if(rows.length > 1){
        		   $.messager.alert('警告操作', '编辑记录只能选择一条数据！', 'warning');
        	   } else if(rows.length == 1) {
					$.ajax({ 
                        url : '/cbhs/hsf/getCg', 
                        type : 'POST', 
                        data :{ 
                        	formCgId : rows[0].cgId,
                        	
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
                            	//控制台打印:console.log(obj.cgCc);
                            	
                            	//成功获取数据显示form表单页面
                            	$('#cg_edit').form('load',{
                            		cg_id_edit : obj.cgId,
                            		cg_date_edit : obj.cgDate,
                            		
                            		cg_scid_edit : obj.cgScid,
                            		cg_sl_edit : obj.cgSl,
                            		cg_dw_edit : obj.cgBak1,
                            		cg_money_edit : obj.cgMoney,
                            		cg_shid_edit : obj.cgShid,//页面设置为不能修改
     
                            		cg_sj_edit : obj.cgSj,
                            		
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

