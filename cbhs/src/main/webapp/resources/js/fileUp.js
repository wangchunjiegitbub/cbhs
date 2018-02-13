



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
	$('#fileUp').datagrid({    
	    url : '/dmh/getFileUpAll',   
	    
	    fit : true,
	    striped : true,
	    rownumbers : true,
	    border : false,
	    toolbar: '#fileUp_tool',
	    
	    pagination: true,
	    pageNumber:1,						//初始化页码
	    pageSize:15, 						//设置默认分页大小
	    pageList:[10,15,20,30,40,50], 		//设置分页大小
	    
	    
//	    sortName : 'fileUpType',
//	    sortOrder : 'asc', 
//	    remoteSort : false,
	    
	    columns:[[    
	        {
	         field:'fileId',
	         title:'文件ID',
	         checkbox : true
	         //hidden : true 
	        }, 
	        
	        {
	        field:'fileName',
	        title:'文件名称',
//	        formatter: function(value,row,index){
//	         	 return '<a href="http://localhost:8080/dmh/download">查看</a>  '	
//	        }  	
	        },    
	        {
	        field:'fileUptimeCharAll',
	        title:'上传时间',
	        
	        },    
	        {
	        	field:'fileSize',
	        	title:'文件大小',
	        },    
	        
	        
	    ]],
	    queryParams: {
	    	dmhTim : $.trim($('input[name="dmhTime_fileUp"]:checked').val()),
	    	dmhDate : function (){
				var inforq = $.trim($('input[name="dmhDate_fileUp"]').val());
		        var date = new Date();
		        if( inforq ) inforq = formatDate(date);//如果info日期为true则设置为当天
		        return inforq;
			}
		}

	 
	});  

	
	
	
	 
	
	 
	  
	 //border-collapse:   separate;   border-spacing:   10px; 
     fileUp_tool = { 
    		
    		//点击查询
    		 find :function (){ 
    			
    		     $('#fileUp').datagrid('loadData', {total:0,rows:[]});  
    		     $('#fileUp').datagrid('reload');  
    			 
            },
            //点击删除已经确认的人员
            remove :function (){ 
            var rows = $('#fileUp').datagrid('getSelections'); 
            if (rows.length >0) { 

                $.messager.confirm('确定 ', '您要删除所选的<strong>' +  rows.length+ '</strong>条记录吗？',function (flag){ 
                    if (flag){ 
                        var fileIds = []; 
                       
                        for (var i = 0; i < rows.length; i ++) { fileIds.push(rows[i].fileId); 
                        }
                        
                        $.ajax({ 
                           type : 'POST', 
                            url : '/dmh/deleteFile', 
                            data :{ 
                            	fileIds : fileIds.join(','), 
                         	   	
                                 
                            }, 
                            beforeSend :function (){ 
                               $('#fileUp').datagrid('loading'); 
                            }, 
                            success :function (data){ 
                         	   $('#fileUp').datagrid('loadData', {total:0,rows:[]}); 
                               $('#fileUp').datagrid('reload');
                               $('#fileUp').datagrid('unselectAll');
                         	   
                         	   if (data){ 
                                   if(data == 0){
	                                   $.messager.show({ 
	
	                                       title : '提示', 
	                                       msg :'上传时间超过1小时不能删除！', 
	                                    }); 
                                   }else{
                                	   $.messager.show({ 
                                			
	                                       title : '提示', 
	                                       msg :data + '个记录被删除成功！', 
	                                    }); 
                                   }
                                } 
                            }, 
                        }); 
                    } 
                }); 
            }else { 

                $.messager.alert('警告操作', '删除记录至少选定一条数据！', 'warning'); 
            } 
        }, 
    		//点击新增 
    		add :function (){ 
    			openFirstWin('http://10.39.91.16','文件上传','800','520');
    			
//    			var hostString = window.location.hostname;
//    			var portString = window.location.port;
//    			
//    			window.open('http://'+hostString+':'+portString+'/dmh/upFile');
    			
//    			$('#fileUp_add').dialog('open');
//        	    $("#fileUp_add_table").show();
//        	    $.parser.parse('#fileUp_add');//form显示后datebox控件不可用时添加这句扫描代码初始化datebox必须填加id否则发生不确定问题
//        	    
//        	  //不能用name加datebox只能用id加datebox
//                var rq = $('input[name="fileUpDate_fileUp"]').val();//获取查询日期赋值给表单
//                $('#fileUpDate_fileUp_add').datebox('setValue',rq);//如：$("input[name='mydate']").datebox('getValue')，会报TypeError: $.data(...) is undefined。
//                var number = $('input[name="fileUpTime_fileUp"]:checked').val();
//                $("input[name='fileUpTime_fileUp_add'][value=" + number + "]").attr("checked",true);
           }, 
//           
//           //点击修改
//           edit :function (){
//        	   $("#fileUp_edit_table").show();
//       	      // $('input[name="fileUpSj_edit"]').css("width","150px");
//        	   //!!!!渲染必须在这个位置否则获取datebox数据不能刷新!!!!!!!!!!!!!!!!!
//        	   $.parser.parse('#fileUp_edit');//form显示后datebox控件不可用时添加这句扫描代码初始化datebox
//        	   var rows = $('#fileUp').datagrid('getSelections');
//        	   //console.log(rows);
//        	   if(rows.length > 1){
//        		   $.messager.alert('警告操作', '编辑记录只能选择一条数据！', 'warning');
//        	   } else if(rows.length == 1) {
//					$.ajax({ 
//                        url : '/fileUp/getfileUp', 
//                        type : 'POST', 
//                        data :{ 
//                        	fileUpId : rows[0].fileUpId,
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
//                            	//控制台打印:console.log(obj.fileUpCc);
//                            	
//                            	//成功获取数据显示form表单页面
//                            	$('#fileUp_edit').form('load',{
//                            		fileUpId_edit : obj.fileUp.fileUpId,
//                            		fileUp_infoId_edit : obj.fileUp.infoId,
//                            		
//                                    fileUpCc_edit : obj.fileUp.fileUpCc,
//                                    fileUpSj_edit : obj.fileUp.fileUpSjCharAll,
//                                    fileUpCs_edit : obj.fileUp.fileUpCs,
//                                    fileUpZz_edit : obj.fileUp.fileUpZz,
//                                    fileUpType_edit : obj.fileUp.fileUpType,//页面设置为不能修改
//     
////                            		fileUpCc_edit : obj.fileUp.fileUpCc,
////                            		fileUpSj_edit : obj.fileUp.fileUpSjCharAll,
////                            		
//                            		rq_fileUp_edit : obj.bInfo.rqChar,
//                            		number_fileUp_edit : obj.bInfo.number,
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

