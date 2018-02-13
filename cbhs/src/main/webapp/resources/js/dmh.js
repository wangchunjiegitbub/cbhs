



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
	$('#dmh').datagrid({    
	    url : '/dmh/getDmhAll',   
	    
	    striped : true,
	    rownumbers : true,
	    border : false,
	    toolbar: '#dmh_tool',
	    
//	    sortName : 'dmhType',
//	    sortOrder : 'asc', 
//	    remoteSort : false,
	    
	    columns:[[    
	        {
	         field:'dmhId',
	         title:'点名会ID',
	         checkbox : true
	         //hidden : true 
	        }, 
	        
	        {
	        field:'dmhDateChar',
	        title:'日期',
	        },    
	        {
	        field:'dmhTime',
	        title:'时间',
	        formatter: function(value,row,index){
				if (row.dmhTime == 1){
					return '<font color="blue">白班</font>';
				} else {
					return '夜班';
				}
			}
	        },    
	        {
	        	field:'bumen',
	        	title:'部门',
	        },    
	        {
	        	field:'userXm',
	        	title:'姓名',
	        },    
	        
	    ]],
	    queryParams: {
	    	dmhTime : $.trim($('input[name="dmhTime_dmh"]:checked').val()),
	    	dmhDate : function (){
				var inforq = $.trim($('input[name="dmhDate_dmh"]').val());
		        var date = new Date();
		        if( inforq ) inforq = formatDate(date);//如果info日期为true则设置为当天
		        return inforq;
			}
		}

	 
	});  

	
	
	//添加点名会
	 $('#dmh_add').dialog({ 
		top : 150,
        width :350, 
        title : '创建点名会' ,
        modal : true,
		closed : true,
        iconCls : 'icon-add', 
        buttons : [{
        	text : '提交' ,
        	iconCls : 'icon-door-in',
			//点击提交时执行
        	handler : function(){
				//验证表单成执行
				if ($('#dmh_add').form('validate')){
					$.ajax({ 
                            url : '/dmh/addDmh', 
                            type : 'POST', 
                            data :{ 
                            	dmhTime : $.trim($('input[name="dmhTime_dmh_add"]:checked').val()),
                            	dmhDate : $.trim($('input[name="dmhDate_dmh_add"]').val()),
                            	
//                                dmhCc : $.trim($('#dmhCc_add').combobox('getText')), 
//                                dmhSj : $.trim($('input[name="dmhSj_add"]').val()), 
//                                dmhCs : $.trim($('input[name="dmhCs_add"]').val()), 
//                                dmhZz : $.trim($('input[name="dmhZz_add"]').val()), 
//                                dmhType : $.trim($('input[name="dmhType_add"]:checked').val()), 
                                 
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
                                        msg : '会议创建成功！', 
                                    }); 

    								$('#dmh_add').dialog('close').form('reset'); 
                                    $('#dmh').datagrid('reload'); 
                                }else if(data == -1) { 
                                	$.messager.alert('错误提示!!!', '不能重复创建点名会！', 'error');
                                	$('#dmh_add').dialog('close').form('reset'); 
                                    $('#dmh').datagrid('reload'); 
                                   
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
        		$('#dmh_add').dialog('close').form('reset');
        	}
        	
        
        
        
        }]
    
	 }); 
	 
	 //修改甩挂信息
	 $('#dmh_edit').dialog({ 
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
				if ($('#dmh_edit').form('validate')){
					$.ajax({ 
                            url : '/dmh/updatedmh', 
                            type : 'POST', 
                            data :{ 
                            	infoId : $.trim($('input[name="dmh_infoId_edit"]').val()),
                            	dmhId : $.trim($('input[name="dmhId_edit"]').val()),
                                
                            	dmhCc : $.trim($('#dmhCc_edit').combobox('getText')), 
                                dmhSj : $.trim($('input[name="dmhSj_edit"]').val()), 
                                dmhCs : $.trim($('input[name="dmhCs_edit"]').val()), 
                                dmhZz : $.trim($('input[name="dmhZz_edit"]').val()), 
                                dmhType : $.trim($('input[name="dmhType_edit"]:checked').val()),
                                //dmhType : $.trim($('input[name="dmhType_edit"]').val()),//禁止修改甩挂类别
//                            	dmhCc : $.trim($('input[name="dmhCc_edit"]').val()), 
//                              dmhSj : $.trim($('input[name="dmhSj_edit"]').val()), 
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
                                        mdmh : '甩挂信息修改成功！', 
                                    }); 

    								$('#dmh_edit').dialog('close').form('reset'); 
                                    $('#dmh').datagrid('reload'); 
                                }else if(data == -1) { 
                                	$.messager.alert('错误提示!!!', '报表已结账不能修改,必须修改时请联系调度员！', 'error');
                                	$('#dmh_edit').dialog('close').form('reset'); 
                                    $('#dmh').datagrid('reload'); 
                                   
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
        		$('#dmh_edit').dialog('close').form('reset');
        	}
         }]
    
	 }); 

	 
	  
	 //border-collapse:   separate;   border-spacing:   10px; 
     dmh_tool = { 
    		
    		//点击查询
    		 find :function (){ 
    			 var dmhTime = $.trim($('input[name="dmhTime_dmh"]:checked').val());
    		     var dmhDate = $.trim($('input[name="dmhDate_dmh"]').val());
    		     $('#dmh').datagrid('loadData', {total:0,rows:[]});  
    			 $('#dmh').datagrid('load', {    
    				 dmhTime : dmhTime,    
    				 dmhDate : dmhDate,  
    			 });
            },  
    		//点击新增 
    		add :function (){ 
        	    $('#dmh_add').dialog('open');
        	    $("#dmh_add_table").show();
        	    $.parser.parse('#dmh_add');//form显示后datebox控件不可用时添加这句扫描代码初始化datebox必须填加id否则发生不确定问题
        	    
        	  //不能用name加datebox只能用id加datebox
                var rq = $('input[name="dmhDate_dmh"]').val();//获取查询日期赋值给表单
                $('#dmhDate_dmh_add').datebox('setValue',rq);//如：$("input[name='mydate']").datebox('getValue')，会报TypeError: $.data(...) is undefined。
                var number = $('input[name="dmhTime_dmh"]:checked').val();
                $("input[name='dmhTime_dmh_add'][value=" + number + "]").attr("checked",true);
           }, 
//           //点击删除
//           remove :function (){ 
//               var rows = $('#dmh').datagrid('getSelections'); 
//               if (rows.length >0) { 
//
//                   $.messager.confirm('确定 ', '您要删除所选的<strong>' +  rows.length+ '</strong>条记录吗？',function (flag){ 
//                       if (flag){ 
//                           var ids = []; 
//                           var infoIds = [];
//                           for (var i = 0; i < rows.length; i ++) { ids.push(rows[i].dmhId); 
//                           }
//                           for (var i = 0; i < rows.length; i ++) { infoIds.push(rows[i].infoId); 
//                           }
//                           $.ajax({ 
//                              type : 'POST', 
//                               url : '/dmh/deletedmh', 
//                               data :{ 
//                                   ids :ids.join(','), 
//                                   infoIds :infoIds.join(','), 
//                               }, 
//                               beforeSend :function (){ 
//                                  $('#dmh').datagrid('loading'); 
//                               }, 
//                               success :function (data){ 
//                                   if (data){ 
//                                      $('#dmh').datagrid('loaded'); 
//                                      $('#dmh').datagrid('reload'); 
//                                      $('#dmh').datagrid('unselectAll'); 
//                                      $.messager.show({ 
//
//                                          title : '提示', 
//                                          mdmh :data + '个甩挂信息被删除成功！', 
//                                       }); 
//                                   } 
//                               }, 
//                           }); 
//                       } 
//                   }); 
//               }else { 
//
//                   $.messager.alert('警告操作', '删除记录至少选定一条数据！', 'warning'); 
//               } 
//           }, 
//           //点击修改
//           edit :function (){
//        	   $("#dmh_edit_table").show();
//       	      // $('input[name="dmhSj_edit"]').css("width","150px");
//        	   //!!!!渲染必须在这个位置否则获取datebox数据不能刷新!!!!!!!!!!!!!!!!!
//        	   $.parser.parse('#dmh_edit');//form显示后datebox控件不可用时添加这句扫描代码初始化datebox
//        	   var rows = $('#dmh').datagrid('getSelections');
//        	   //console.log(rows);
//        	   if(rows.length > 1){
//        		   $.messager.alert('警告操作', '编辑记录只能选择一条数据！', 'warning');
//        	   } else if(rows.length == 1) {
//					$.ajax({ 
//                        url : '/dmh/getdmh', 
//                        type : 'POST', 
//                        data :{ 
//                        	dmhId : rows[0].dmhId,
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
//                            	//控制台打印:console.log(obj.dmhCc);
//                            	
//                            	//成功获取数据显示form表单页面
//                            	$('#dmh_edit').form('load',{
//                            		dmhId_edit : obj.dmh.dmhId,
//                            		dmh_infoId_edit : obj.dmh.infoId,
//                            		
//                                    dmhCc_edit : obj.dmh.dmhCc,
//                                    dmhSj_edit : obj.dmh.dmhSjCharAll,
//                                    dmhCs_edit : obj.dmh.dmhCs,
//                                    dmhZz_edit : obj.dmh.dmhZz,
//                                    dmhType_edit : obj.dmh.dmhType,//页面设置为不能修改
//     
////                            		dmhCc_edit : obj.dmh.dmhCc,
////                            		dmhSj_edit : obj.dmh.dmhSjCharAll,
////                            		
//                            		rq_dmh_edit : obj.bInfo.rqChar,
//                            		number_dmh_edit : obj.bInfo.number,
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

