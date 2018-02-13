



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
	$('#kpyl').datagrid({    
	    url : '/cbhs/rpf/getRpfAll',   
	    
	    striped : true,
	    rownumbers : true,
	    border : false,
	    toolbar: '#kpyl_tool',
	    
//	    sortName : 'kpylType',
//	    sortOrder : 'asc', 
//	    remoteSort : false,
	    rowStyler:function(index,row){
			if (row.fpl>0.003){
				return 'background-color:pink;color:blue;font-weight:bold;';
			}
		},
	    
	    columns:[[    
	        {
	         field:'rpfId',
	         title:'软票费ID',
	         checkbox : true
	         //hidden : true 
	        }, 
	        {
		         field:'cz',
		         title:'站名',
		         
		         width: '100px',
		        }, 
	        
	        {
	        field:'inDateS',
	        title:'日期',
	        width: '100px',
	        },    
//	        {
//	        field:'kpylTime',
//	        title:'时间',
//	        formatter: function(value,row,index){
//				if (row.kpylTime == 1){
//					return '<font color="blue">白班</font>';
//				} else {
//					return '夜班';
//				}
//			}
//	        },    
	        {
	        	field:'spNumber',
	        	title:'废票数量',
	        	width: '100px',
	        },    
	        {
	        	field:'fpNumber',
	        	title:'售票数量',
	        	width: '100px',
	        }, 
	        {
	        	field:'sumRpf',
	        	title:'合计数量',
	        	width: '100px',
	        	
	        },
	        {
	        	field:'fpl',
	        	title:'废票率',
	        	width: '100px',
//	        	formatter: function(value,row,index){
//					if ( value > 0.003 ){
//						return '<font color="blue">'+ value + '</font>';
//					} else {
//						return value;
//					}
//				}
	        	
	        },
	        {
	        	field:'fpCb',
	        	title:'废票成本（元）',
	        	width: '100px',
	        	
	        },
	        {
	        	field:'spCb',
	        	title:'售票成本（元）',
	        	width: '100px',
	        	
	        },
	        {
	        	field:'sjCb',
	        	title:'实际成本（元）',
	        	width: '100px',
	        	
	        },
	        
	    ]],
	    queryParams: {
	    	
	    	inDate : function (){
				var inforq = $.trim($('input[name="kpylDate_kpyl"]').val());
		        var date = new Date();
		        if( inforq ) inforq = formatDate(date);//如果info日期为true则设置为当天
		        return inforq;
			}
		}

	 
	});  

	
	
	//添加点名会
	 $('#kpyl_fenpiao_add').dialog({ 
		top : 150,
        width :350, 
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
				if ($('#kpyl_fenpiao_add').form('validate')){
					$.ajax({ 
                            url : '/cbhs/rpf/addKpylFp', 
                            type : 'POST', 
                            data :{ 
                            	fpNumber : $.trim($('input[name="kpyl_fenpiao_fp_add"]').val()),
                            	spNumber : $.trim($('input[name="kpyl_fenpiao_sp_add"]').val()),
                            	inDate : $.trim($('input[name="kpyl_fenpiao_date__add"]').val()),
//                                kpylCc : $.trim($('#kpylCc_add').combobox('getText')), 
//                                kpylSj : $.trim($('input[name="kpylSj_add"]').val()), 
//                                kpylCs : $.trim($('input[name="kpylCs_add"]').val()), 
//                                kpylZz : $.trim($('input[name="kpylZz_add"]').val()), 
//                                kpylType : $.trim($('input[name="kpylType_add"]:checked').val()), 
                                 
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

    								$('#kpyl_fenpiao_add').dialog('close').form('reset'); 
                                    $('#kpyl').datagrid('reload'); 
                                }else if(data == -1) { 
                                	$.messager.alert('错误提示!!!', '不能重复创建点名会！', 'error');
                                	$('#kpyl_fenpiao_add').dialog('close').form('reset'); 
                                    $('#kpyl').datagrid('reload'); 
                                   
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
        		$('#kpyl_fenpiao_add').dialog('close').form('reset');
        	}
        	
        
        
        
        }]
    
	 }); 
	 
	 //修改甩挂信息
	 $('#kpyl_edit').dialog({ 
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
				if ($('#kpyl_edit').form('validate')){
					$.ajax({ 
                            url : '/kpyl/updatekpyl', 
                            type : 'POST', 
                            data :{ 
                            	infoId : $.trim($('input[name="kpyl_infoId_edit"]').val()),
                            	kpylId : $.trim($('input[name="kpylId_edit"]').val()),
                                
                            	kpylCc : $.trim($('#kpylCc_edit').combobox('getText')), 
                                kpylSj : $.trim($('input[name="kpylSj_edit"]').val()), 
                                kpylCs : $.trim($('input[name="kpylCs_edit"]').val()), 
                                kpylZz : $.trim($('input[name="kpylZz_edit"]').val()), 
                                kpylType : $.trim($('input[name="kpylType_edit"]:checked').val()),
                                //kpylType : $.trim($('input[name="kpylType_edit"]').val()),//禁止修改甩挂类别
//                            	kpylCc : $.trim($('input[name="kpylCc_edit"]').val()), 
//                              kpylSj : $.trim($('input[name="kpylSj_edit"]').val()), 
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
                                        mkpyl : '甩挂信息修改成功！', 
                                    }); 

    								$('#kpyl_edit').dialog('close').form('reset'); 
                                    $('#kpyl').datagrid('reload'); 
                                }else if(data == -1) { 
                                	$.messager.alert('错误提示!!!', '报表已结账不能修改,必须修改时请联系调度员！', 'error');
                                	$('#kpyl_edit').dialog('close').form('reset'); 
                                    $('#kpyl').datagrid('reload'); 
                                   
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
        		$('#kpyl_edit').dialog('close').form('reset');
        	}
         }]
    
	 }); 

	 
	  
	 //border-collapse:   separate;   border-spacing:   10px; 
     kpyl_tool = { 
    		
    		//点击查询
    		 find :function (){ 
    			
    		     $('#kpyl').datagrid('loadData', {total:0,rows:[]});  
    			 $('#kpyl').datagrid('load', {    
    				 inDate : function (){
    						var inforq = $.trim($('input[name="kpylDate_kpyl"]').val());
    				        var date = new Date();
    				        if( inforq ) inforq = formatDate(date);//如果info日期为true则设置为当天
    				        return inforq;
    					} 
    			 });
            },  
    		//点击新增 粉票
    		addf :function (){ 
        	    $('#kpyl_fenpiao_add').dialog('open');
        	    $("#kpyl_fenpiao_add_table").show();
        	    $.parser.parse('#kpyl_fenpiao_add');//form显示后datebox控件不可用时添加这句扫描代码初始化datebox必须填加id否则发生不确定问题
        	    
        	  //不能用name加datebox只能用id加datebox
                var rq = $('input[name="kpylDate_kpyl"]').val();//获取查询日期赋值给表单
                $('#kpylDate_kpyl_add').datebox('setValue',rq);//如：$("input[name='mydate']").datebox('getValue')，会报TypeError: $.data(...) is undefined。
                var number = $('input[name="kpylTime_kpyl"]:checked').val();
                $("input[name='kpylTime_kpyl_add'][value=" + number + "]").attr("checked",true);
           }, 
           //点击新增 蓝票
	   		addl :function (){ 
	       	    $('#kpyl_fenpiao_add').dialog('open');
	       	    $("#kpyl_fenpiao_add_table").show();
	       	    $.parser.parse('#kpyl_fenpiao_add');//form显示后datebox控件不可用时添加这句扫描代码初始化datebox必须填加id否则发生不确定问题
	       	    
	       	  //不能用name加datebox只能用id加datebox
	               var rq = $('input[name="kpylDate_kpyl"]').val();//获取查询日期赋值给表单
	               $('#kpylDate_kpyl_add').datebox('setValue',rq);//如：$("input[name='mydate']").datebox('getValue')，会报TypeError: $.data(...) is undefined。
	               var number = $('input[name="kpylTime_kpyl"]:checked').val();
	               $("input[name='kpylTime_kpyl_add'][value=" + number + "]").attr("checked",true);
	          }, 
           //点击删除
           remove :function (){ 
               var rows = $('#kpyl').datagrid('getSelections'); 
               if (rows.length >0) { 

                   $.messager.confirm('确定 ', '您要删除所选的<strong>' +  rows.length+ '</strong>条记录吗？',function (flag){ 
                       if (flag){ 
                           var ids = []; 
                           
                           for (var i = 0; i < rows.length; i ++) { ids.push(rows[i].rpfId); 
                           }
                           
                           $.ajax({ 
                              type : 'POST', 
                               url : '/cbhs/rpf/deletekpyl', 
                               data :{ 
                                   ids :ids.join(','), 
                                    
                               }, 
                               beforeSend :function (){ 
                                  $('#kpyl').datagrid('loading'); 
                               }, 
                               success :function (data){ 
                                   if (data){ 
                                      $('#kpyl').datagrid('loaded'); 
                                      $('#kpyl').datagrid('reload'); 
                                      $('#kpyl').datagrid('unselectAll'); 
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
//        	   $("#kpyl_edit_table").show();
//       	      // $('input[name="kpylSj_edit"]').css("width","150px");
//        	   //!!!!渲染必须在这个位置否则获取datebox数据不能刷新!!!!!!!!!!!!!!!!!
//        	   $.parser.parse('#kpyl_edit');//form显示后datebox控件不可用时添加这句扫描代码初始化datebox
//        	   var rows = $('#kpyl').datagrid('getSelections');
//        	   //console.log(rows);
//        	   if(rows.length > 1){
//        		   $.messager.alert('警告操作', '编辑记录只能选择一条数据！', 'warning');
//        	   } else if(rows.length == 1) {
//					$.ajax({ 
//                        url : '/kpyl/getkpyl', 
//                        type : 'POST', 
//                        data :{ 
//                        	kpylId : rows[0].kpylId,
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
//                            	//控制台打印:console.log(obj.kpylCc);
//                            	
//                            	//成功获取数据显示form表单页面
//                            	$('#kpyl_edit').form('load',{
//                            		kpylId_edit : obj.kpyl.kpylId,
//                            		kpyl_infoId_edit : obj.kpyl.infoId,
//                            		
//                                    kpylCc_edit : obj.kpyl.kpylCc,
//                                    kpylSj_edit : obj.kpyl.kpylSjCharAll,
//                                    kpylCs_edit : obj.kpyl.kpylCs,
//                                    kpylZz_edit : obj.kpyl.kpylZz,
//                                    kpylType_edit : obj.kpyl.kpylType,//页面设置为不能修改
//     
////                            		kpylCc_edit : obj.kpyl.kpylCc,
////                            		kpylSj_edit : obj.kpyl.kpylSjCharAll,
////                            		
//                            		rq_kpyl_edit : obj.bInfo.rqChar,
//                            		number_kpyl_edit : obj.bInfo.number,
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

