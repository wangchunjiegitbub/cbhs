



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
	$('#person').datagrid({    
	    url : '/cbhs/hsf/getPerson',   
	    
	    striped : true,
	    rownumbers : true,
	    border : false,
	    toolbar: '#person_tool',
	    
//	    sortName : 'personType',
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
				var inforq = $.trim($('input[name="person_time_begin"]').val());
		        var date = new Date();
		        if( inforq ) inforq = formatDate(date);//如果info日期为true则设置为当天
		        return inforq;
			},
			endDate : function (){
				var inforq = $.trim($('input[name="person_time_end"]').val());
		        var date = new Date();
		        if( inforq ) inforq = formatDate(date);//如果info日期为true则设置为当天
		        return inforq;
			},
			depId : $.trim($('input[name="depId_person"]').val()),
		}

	 
	});  

	
	
	//添采购信息
	 $('#person_add').dialog({ 
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
				if ($('#person_add').form('validate')){
					$.ajax({ 
                            url : '/cbhs/hsf/addPerson', 
                            type : 'POST', 
                            data :{ 
                            	  personTime : $.trim($('input[name="person_time_add"]').val()),
                            	    personIn : $.trim($('input[name="person_in_add"]').val()),
                            	   personOut : $.trim($('input[name="person_out_add"]').val()),
                            	  
                            	
                            	
//                                personCc : $.trim($('#personCc_add').combobox('getText')), 
//                                personSj : $.trim($('input[name="personSj_add"]').val()), 
//                                personCs : $.trim($('input[name="personCs_add"]').val()), 
//                                personZz : $.trim($('input[name="personZz_add"]').val()), 
//                                personType : $.trim($('input[name="personType_add"]:checked').val()), 
                                 
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

    								$('#person_add').dialog('close').form('reset'); 
                                    $('#person').datagrid('reload'); 
                                }else if(data == -1) { 
                                	$.messager.alert('错误提示!!!', '不能创建！', 'error');
                                	$('#person_add').dialog('close').form('reset'); 
                                    $('#person').datagrid('reload'); 
                                   
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
        		$('#person_add').dialog('close').form('reset');
        	}
        	
        
        
        
        }]
    
	 }); 
	 
	 //修改甩挂信息
	 $('#person_edit').dialog({ 
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
				if ($('#person_edit').form('validate')){
					$.ajax({ 
                            url : '/person/updateperson', 
                            type : 'POST', 
                            data :{ 
                            	infoId : $.trim($('input[name="person_infoId_edit"]').val()),
                            	personId : $.trim($('input[name="personId_edit"]').val()),
                                
                            	personCc : $.trim($('#personCc_edit').combobox('getText')), 
                                personSj : $.trim($('input[name="personSj_edit"]').val()), 
                                personCs : $.trim($('input[name="personCs_edit"]').val()), 
                                personZz : $.trim($('input[name="personZz_edit"]').val()), 
                                personType : $.trim($('input[name="personType_edit"]:checked').val()),
                                //personType : $.trim($('input[name="personType_edit"]').val()),//禁止修改甩挂类别
//                            	personCc : $.trim($('input[name="personCc_edit"]').val()), 
//                              personSj : $.trim($('input[name="personSj_edit"]').val()), 
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
                                    $.messager.personow({ 

                                        title : '提示', 
                                        mperson : '甩挂信息修改成功！', 
                                    }); 

    								$('#person_edit').dialog('close').form('reset'); 
                                    $('#person').datagrid('reload'); 
                                }else if(data == -1) { 
                                	$.messager.alert('错误提示!!!', '报表已结账不能修改,必须修改时请联系调度员！', 'error');
                                	$('#person_edit').dialog('close').form('reset'); 
                                    $('#person').datagrid('reload'); 
                                   
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
        		$('#person_edit').dialog('close').form('reset');
        	}
         }]
    
	 }); 

	 
	  
	 //border-collapse:   separate;   border-spacing:   10px; 
     person_tool = { 
    		
    		//点击查询
    		 find :function (){ 
    			
    		     $('#person').datagrid('loadData', {total:0,rows:[]});  
    			 $('#person').datagrid('load', {    
    				 beginDate : function (){
	    					var inforq = $.trim($('input[name="person_time_begin"]').val());
//	 				        var date = new Date();
//	 				        if( inforq ) inforq = formatDate(date);//如果info日期为true则设置为当天
	 				        return inforq;
    					}, 
	    			 endDate : function (){
	 					var inforq = $.trim($('input[name="person_time_end"]').val());
	//				        var date = new Date();
	//				        if( inforq ) inforq = formatDate(date);//如果info日期为true则设置为当天
					        return inforq;
						},
						depId : $.trim($('input[name="depId_person"]').val()),
	    			 });
            },  
    		//点击新增
    		add :function (){ 
        	    $('#person_add').dialog('open');
        	    $("#person_add_table").show();
        	    $.parser.parse('#person_add');//form显示后datebox控件不可用时添加这句扫描代码初始化datebox必须填加id否则发生不确定问题
        	    
        	  //不能用name加datebox只能用id加datebox
                var rq = $('input[name="person_time_end"]').val();//获取查询日期赋值给表单
                $('#person_time_add').datebox('setValue',rq);//如：$("input[name='mydate']").datebox('getValue')，会报TypeError: $.data(...) is undefined。
//                var number = $('input[name="personTime_person"]:checked').val();
//                $("input[name='personTime_person_add'][value=" + number + "]").attr("checked",true);
           }, 
           
           //点击删除
           remove :function (){ 
               var rows = $('#person').datagrid('getSelections'); 
               if (rows.length >0) { 

                   $.messager.confirm('确定 ', '您要删除所选的<strong>' +  rows.length+ '</strong>条记录吗？',function (flag){ 
                       if (flag){ 
                           var ids = []; 
                           
                           for (var i = 0; i < rows.length; i ++) { ids.push(rows[i].personId); 
                           }
                           
                           $.ajax({ 
                              type : 'POST', 
                               url : '/cbhs/hsf/deletePerson', 
                               data :{ 
                                   ids :ids.join(','), 
                                    
                               }, 
                               beforeSend :function (){ 
                                  $('#person').datagrid('loading'); 
                               }, 
                               success :function (data){ 
                                   if (data){ 
                                      $('#person').datagrid('loaded'); 
                                      $('#person').datagrid('reload'); 
                                      $('#person').datagrid('unselectAll'); 
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
//        	   $("#person_edit_table").personow();
//       	      // $('input[name="personSj_edit"]').css("width","150px");
//        	   //!!!!渲染必须在这个位置否则获取datebox数据不能刷新!!!!!!!!!!!!!!!!!
//        	   $.parser.parse('#person_edit');//form显示后datebox控件不可用时添加这句扫描代码初始化datebox
//        	   var rows = $('#person').datagrid('getSelections');
//        	   //console.log(rows);
//        	   if(rows.length > 1){
//        		   $.messager.alert('警告操作', '编辑记录只能选择一条数据！', 'warning');
//        	   } else if(rows.length == 1) {
//					$.ajax({ 
//                        url : '/person/getperson', 
//                        type : 'POST', 
//                        data :{ 
//                        	personId : rows[0].personId,
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
//                            	//控制台打印:console.log(obj.personCc);
//                            	
//                            	//成功获取数据显示form表单页面
//                            	$('#person_edit').form('load',{
//                            		personId_edit : obj.person.personId,
//                            		person_infoId_edit : obj.person.infoId,
//                            		
//                                    personCc_edit : obj.person.personCc,
//                                    personSj_edit : obj.person.personSjCharAll,
//                                    personCs_edit : obj.person.personCs,
//                                    personZz_edit : obj.person.personZz,
//                                    personType_edit : obj.person.personType,//页面设置为不能修改
//     
////                            		personCc_edit : obj.person.personCc,
////                            		personSj_edit : obj.person.personSjCharAll,
////                            		
//                            		rq_person_edit : obj.bInfo.rqChar,
//                            		number_person_edit : obj.bInfo.number,
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

