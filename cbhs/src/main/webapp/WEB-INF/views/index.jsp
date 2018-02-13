<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="copyright" content="All Rights Reserved, Copyright (C) 2013, Wuyeguo, Ltd." />
<title>沈阳车务段成本核算管理系统</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/easyui/1.4.5/themes/default/easyui.css'/>" />
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/wu.css'/>" />
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/icon.css'/>" />
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/IconExtension.css'/>" />
<script type="text/javascript" src="<c:url value='/resources/js/jquery-1.8.0.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/easyui/1.4.5/jquery.easyui.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/easyui/1.4.5/locale/easyui-lang-zh_CN.js'/>"></script>
<script type="text/javascript"	src="<c:url value='/resources/js/validator.js'/>"></script>




</head>
<body class="easyui-layout">
	<!-- begin of header -->
	<div class="wu-header" data-options="region:'north',border:false,split:true">
    	<div class="wu-header-left">
        	<h1 style="font-family:微软雅黑;">沈阳车务段成本核算管理系统</h1>
        </div>
        <div class="wu-header-right">
        	<p><strong class="easyui-tooltip" >${sessionUser.userXm}</strong>，欢迎您！</p>
            <p><a href="<c:url value='/quit'/>">安全退出</a></p>
        </div>
    </div>
    <!-- end of header -->
    <!-- begin of sidebar -->
	<div class="wu-sidebar" data-options="region:'west',split:true,border:true,title:'导航菜单'"> 
    	<div class="easyui-accordion" data-options="border:false,fit:true"> 
         
          <c:if test="${sessionScope.sessionUser.quanxian == 5 || sessionScope.sessionUser.quanxian > 100 }">
          
          
		          <div title="软票费" data-options="iconCls:'icon-cart'" style="padding:5px;">  	
		    			<ul class="easyui-tree wu-side-tree">
		                	
		                	<li iconCls="icon-user-key"><a href="javascript:void(0)" data-icon="icon-user-key" data-link="<c:url value='/rpf/kpyl'/>" iframe="0">客票用量</a></li>
		                	<!-- <li iconCls="icon-user-key"><a href="javascript:void(0)" data-icon="icon-user-key" data-link="<c:url value='/ryqr'/>" iframe="0">人员确认</a></li>
		                    <li iconCls="icon-user-key"><a href="javascript:void(0)" data-icon="icon-user-key" data-link="<c:url value='/fileUp'/>" iframe="0">文电上传</a></li>
		                	 --><!-- <li iconCls="icon-chart-organisation"><a href="javascript:void(0)" data-icon="icon-chart-organisation" data-link="layout-3.html" iframe="0">导航标题</a></li>
		                    <li iconCls="icon-users"><a href="javascript:void(0)" data-icon="icon-users" data-link="temp/layout-3.html" iframe="0">导航标题</a></li>
		                    <li iconCls="icon-user-group"><a href="javascript:void(0)" data-icon="icon-user-group" data-link="temp/layout-3.html" iframe="0">导航标题</a></li>
		                    <li iconCls="icon-book"><a href="javascript:void(0)" data-icon="icon-book" data-link="temp/layout-3.html" iframe="0">导航标题</a></li>
		                    <li iconCls="icon-cog"><a href="javascript:void(0)" data-icon="icon-cog" data-link="temp/layout-3.html" iframe="0">导航标题</a></li>
		                    <li iconCls="icon-application-osx-error"><a href="javascript:void(0)" data-icon="icon-application-osx-error" data-link="temp/layout-3.html" iframe="0">导航标题</a></li>
		                 -->
		                 </ul>
		            </div>
            
	            <div title="分劈预算" data-options="iconCls:'icon-cart'" style="padding:5px;">  	
	    			<ul class="easyui-tree wu-side-tree">
	                	
	                	<li iconCls="icon-user-key"><a href="javascript:void(0)" data-icon="icon-user-key" data-link="<c:url value='/fpys/qnys'/>" iframe="0">全年预算</a></li>
	                	<!-- <li iconCls="icon-user-key"><a href="javascript:void(0)" data-icon="icon-user-key" data-link="<c:url value='/ryqr'/>" iframe="0">人员确认</a></li>
	                    <li iconCls="icon-user-key"><a href="javascript:void(0)" data-icon="icon-user-key" data-link="<c:url value='/fileUp'/>" iframe="0">文电上传</a></li>
	                	 --><!-- <li iconCls="icon-chart-organisation"><a href="javascript:void(0)" data-icon="icon-chart-organisation" data-link="layout-3.html" iframe="0">导航标题</a></li>
	                    <li iconCls="icon-users"><a href="javascript:void(0)" data-icon="icon-users" data-link="temp/layout-3.html" iframe="0">导航标题</a></li>
	                    <li iconCls="icon-user-group"><a href="javascript:void(0)" data-icon="icon-user-group" data-link="temp/layout-3.html" iframe="0">导航标题</a></li>
	                    <li iconCls="icon-book"><a href="javascript:void(0)" data-icon="icon-book" data-link="temp/layout-3.html" iframe="0">导航标题</a></li>
	                    <li iconCls="icon-cog"><a href="javascript:void(0)" data-icon="icon-cog" data-link="temp/layout-3.html" iframe="0">导航标题</a></li>
	                    <li iconCls="icon-application-osx-error"><a href="javascript:void(0)" data-icon="icon-application-osx-error" data-link="temp/layout-3.html" iframe="0">导航标题</a></li>
	                 -->
	                 </ul>
	            </div>
            </c:if>
            <c:if test="${sessionScope.sessionUser.quanxian == 6 || sessionScope.sessionUser.quanxian > 100 || sessionScope.sessionUser.quanxian == 11 }">
	            <div title="伙食费管理" data-options="iconCls:'icon-cart'" style="padding:5px;">  	
	    			<ul class="easyui-tree wu-side-tree">
	                	
	                	
	                	<li iconCls="icon-user-key"><a href="javascript:void(0)" data-icon="icon-user-key" data-link="<c:url value='/hsf/cg'/>" iframe="0">原料采购</a></li>
	                	
	                	<li iconCls="icon-user-key"><a href="javascript:void(0)" data-icon="icon-user-key" data-link="<c:url value='/hsf/sh'/>" iframe="0">商户管理</a></li>
	                	<li iconCls="icon-user-key"><a href="javascript:void(0)" data-icon="icon-user-key" data-link="<c:url value='/hsf/person'/>" iframe="0">人员管理</a></li>
	                	<li iconCls="icon-user-key"><a href="javascript:void(0)" data-icon="icon-user-key" data-link="<c:url value='/hsf/sr'/>" iframe="0">收入管理</a></li>
	                	<li iconCls="icon-user-key"><a href="javascript:void(0)" data-icon="icon-user-key" data-link="<c:url value='/hsf/szmx'/>" iframe="0">收支明细</a></li>
	                	
	                	
	                	<c:if test="${sessionScope.sessionUser.quanxian == 11 || sessionScope.sessionUser.quanxian == 12 }">
		                	<li iconCls="icon-user-key"><a href="javascript:void(0)" data-icon="icon-user-key" data-link="<c:url value='/hsf/bx'/>" iframe="0">审核报销</a></li>
		                	<li iconCls="icon-user-key"><a href="javascript:void(0)" data-icon="icon-user-key" data-link="<c:url value='/hsf/shPerson'/>" iframe="0">审核人员</a></li>
		                	<li iconCls="icon-user-key"><a href="javascript:void(0)" data-icon="icon-user-key" data-link="<c:url value='/hsf/shSr'/>" iframe="0">审核收入</a></li>
		                </c:if>	
	                	
	                	<li iconCls="icon-user-key"><a href="javascript:void(0)" data-icon="icon-user-key" data-link="<c:url value='/hsf/jz'/>" iframe="0">结账</a></li>
	                	<li iconCls="icon-user-key"><a href="javascript:void(0)" data-icon="icon-user-key" data-link="<c:url value='/hsf/groupSum'/>" iframe="0">汇总查询</a></li>
	                	
	                	<!-- <li iconCls="icon-user-key"><a href="javascript:void(0)" data-icon="icon-user-key" data-link="<c:url value='/ryqr'/>" iframe="0">人员确认</a></li>
	                    <li iconCls="icon-user-key"><a href="javascript:void(0)" data-icon="icon-user-key" data-link="<c:url value='/fileUp'/>" iframe="0">文电上传</a></li>
	                	 --><!-- <li iconCls="icon-chart-organisation"><a href="javascript:void(0)" data-icon="icon-chart-organisation" data-link="layout-3.html" iframe="0">导航标题</a></li>
	                    <li iconCls="icon-users"><a href="javascript:void(0)" data-icon="icon-users" data-link="temp/layout-3.html" iframe="0">导航标题</a></li>
	                    <li iconCls="icon-user-group"><a href="javascript:void(0)" data-icon="icon-user-group" data-link="temp/layout-3.html" iframe="0">导航标题</a></li>
	                    <li iconCls="icon-book"><a href="javascript:void(0)" data-icon="icon-book" data-link="temp/layout-3.html" iframe="0">导航标题</a></li>
	                    <li iconCls="icon-cog"><a href="javascript:void(0)" data-icon="icon-cog" data-link="temp/layout-3.html" iframe="0">导航标题</a></li>
	                    <li iconCls="icon-application-osx-error"><a href="javascript:void(0)" data-icon="icon-application-osx-error" data-link="temp/layout-3.html" iframe="0">导航标题</a></li>
	                 -->
	                 </ul>
	                 
	                <!-- <ul class="easyui-tree">
                    <li>
                        <span>Folder</span>
                        <ul>
                            <li>
                                <span>Sub Folder 1</span>
                               <ul>
                                    <li>
                                        <span><a target="mainFrame" href="http://www.baidu.com">审核博客</a></span>
                                   </li>
                                    <li>
                                        <span><a href="#">File 12</a></span>
                                    </li>
                                    <li>
                                        <span>File 13</span>
                                    </li>
                                </ul>
                            </li>
                            <li>
                                <span>File 2</span>
                            </li>
                            <li>
                                <span>File 3</span>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <span><a href="#">File21</a></span>
                    </li>
                </ul> -->
	                 
	                 
	                 
	            </div>
	          
          
          
          
          </c:if>
        	<%-- <div title="点名流程" data-options="iconCls:'icon-database-go'" style="padding:5px;">  	
    			<ul class="easyui-tree wu-side-tree">

                	<li iconCls="icon-users"><a href="javascript:void(0)" data-icon="icon-users" data-link="<c:url value='/zgdm'/>" iframe="0">职工点名</a></li>
                    <li iconCls="icon-text_smallcaps"><a href="javascript:void(0)" data-icon="icon-text_smallcaps" data-link="<c:url value='/wdcd'/>" iframe="0">文电传达</a></li>
                    <li iconCls="icon-cog"><a href="javascript:void(0)" data-icon="icon-cog" data-link="<c:url value='/cjYczz'/>" iframe="0">班前试问</a></li>
                    <li iconCls="icon-delivery"><a href="javascript:void(0)" data-icon="icon-delivery" data-link="<c:url value='/dfc'/>" iframe="0">工作总结</a></li>
                    <li iconCls="icon-package-go-in"><a href="javascript:void(0)" data-icon="icon-package-go-in" data-link="<c:url value='/lcInfo'/>" iframe="0">安全预想</a></li>
                  
                   
                    
                                  
                </ul>
            </div> --%>
		
		
		
		 	
		
		
		
           <!--  <div title="打印结账" data-options="iconCls:'icon-printer'" style="padding:5px;">  	
    			<ul class="easyui-tree wu-side-tree">
                	<li iconCls="icon-printer-go"><a href="javascript:void(0)" data-icon="icon-printer-go" data-link="<c:url value='/tab15'/>" iframe="1">打印日况表</a></li>
                	<li iconCls="icon-printer-start"><a href="javascript:void(0)" data-icon="icon-printer-start" data-link="<c:url value='/tabzx'/>" iframe="1">打印装卸表</a></li>
                    <li iconCls="icon-lock-edit"><a href="javascript:void(0)" data-icon="icon-lock-edit" data-link="<c:url value='/bInfo'/>" iframe="0">结账</a></li>
                   </ul>
            </div> -->
            <div title="系统管理" data-options="iconCls:'icon-cart'" style="padding:5px;">  	
    			<ul class="easyui-tree wu-side-tree">
                	<li iconCls="icon-user-key"><a href="javascript:void(0)" data-icon="icon-user-key" data-link="<c:url value='/userhsf'/>" iframe="0">修改密码</a></li>
                    
                	<!-- <li iconCls="icon-chart-organisation"><a href="javascript:void(0)" data-icon="icon-chart-organisation" data-link="layout-3.html" iframe="0">导航标题</a></li>
                    <li iconCls="icon-users"><a href="javascript:void(0)" data-icon="icon-users" data-link="temp/layout-3.html" iframe="0">导航标题</a></li>
                    <li iconCls="icon-user-group"><a href="javascript:void(0)" data-icon="icon-user-group" data-link="temp/layout-3.html" iframe="0">导航标题</a></li>
                    <li iconCls="icon-book"><a href="javascript:void(0)" data-icon="icon-book" data-link="temp/layout-3.html" iframe="0">导航标题</a></li>
                    <li iconCls="icon-cog"><a href="javascript:void(0)" data-icon="icon-cog" data-link="temp/layout-3.html" iframe="0">导航标题</a></li>
                    <li iconCls="icon-application-osx-error"><a href="javascript:void(0)" data-icon="icon-application-osx-error" data-link="temp/layout-3.html" iframe="0">导航标题</a></li>
                 -->
                 </ul>
            </div>
            <div title="软件下载" data-options="iconCls:'icon-bricks'" style="padding:5px;">  	
    			<ul class="easyui-tree wu-side-tree">
                	<li iconCls="icon-Firefox"><a href="<c:url value='/resources/firefox/Firefox-full-latest.exe'/>" data-icon="icon-Firefox" >火狐浏览器</a></li>
                	<li iconCls="icon-Chrome"><a href="<c:url value='/resources/chrome/ChromeStandaloneSetup.exe'/>" data-icon="icon-Chrome" >谷歌浏览器</a></li>
                    <%-- <li iconCls="icon-download"><a href="javascript:void(0)" data-icon="icon-download" data-link="<c:url value='/resources/grinstall/grbsctl6.exe'/>" iframe="1">打印控件</a></li> --%>
                </ul>
            </div>
            
           
        </div>
    </div>	
    <!-- end of sidebar -->    
    <!-- begin of main -->
     <div class="wu-main" data-options="region:'center'">
        <div id="wu-tabs" class="easyui-tabs" data-options="border:false,fit:true">  
             <div title="首页" data-options="href:'<c:url value='/resources/temp/layout-1.jsp'/>',closable:false,iconCls:'icon-tip',cls:'pd3'"></div>
         </div>
    </div> 
    <!-- end of main --> 
    <!-- begin of footer -->
	<!-- <div class="wu-footer" data-options="region:'south',border:true,split:true">
    	&copy; 2013 Wu All Rights Reserved
    </div> -->
    <!-- end of footer -->  
    <script type="text/javascript">
		$(function(){
			$('.wu-side-tree a').bind("click",function(){
				var title = $(this).text();
				var url = $(this).attr('data-link');
				var iconCls = $(this).attr('data-icon');
				var iframe = $(this).attr('iframe')==1?true:false;
				addTab(title,url,iconCls,iframe);
			});	
			
		})
		window.onload=function(){ 
			$.ajax({ 
                url : '/cbhs/hsf/getJzState', 
                type : 'POST', 
                data : "", 
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
                            msg : '<font color="blue">采购报销、收入、人员调动已经全部审核通过请及时结账！</font>', 
                          //不关闭
                            timeout : 0,
                            height : 130,

                        }); 

						 
                    }
                } 
            }); 
		} 
		/**
		* Name 载入树形菜单 
		
		$('#wu-side-tree').tree({
			url:'temp/menu.php',
			cache:false,
			onClick:function(node){
				var url = node.attributes['url'];
				if(url==null || url == ""){
					return false;
				}
				else{
					addTab(node.text, url, '', node.attributes['iframe']);
				}
			}
		});
		*/
		
		/**
		* Name 选项卡初始化
		*/
		 $('#wu-tabs').tabs({
			tools:[{
				iconCls:'icon-reload',
				border:false,
				handler:function(){
					$('#mlInfoAll-datagrid').datagrid('reload');
				}
			}]
		}); 
			
		/**
		* Name 添加菜单选项
		* Param title 名称
		* Param href 链接
		* Param iconCls 图标样式
		* Param iframe 链接跳转方式（true为iframe，false为href）
		*/	
		function addTab(title, href, iconCls, iframe){
			var tabPanel = $('#wu-tabs');
			if(!tabPanel.tabs('exists',title)){
				var content = '<iframe scrolling="auto" frameborder="0"  src="'+ href +'" style="width:100%;height:99%;"></iframe>';
				if(iframe){
					tabPanel.tabs('add',{
						title:title,
						content:content,
						iconCls:iconCls,
						fit:true,
						cls:'pd3',
						closable:true
					});
				}
				else{
					tabPanel.tabs('add',{
						title:title,
						href:href,
						iconCls:iconCls,
						fit:true,
						cls:'pd3',
						closable:true
					});
				}
			}
			else
			{
				tabPanel.tabs('select',title);
			}
		}
		/**
		* Name 移除菜单选项
		*/
		function removeTab(){
			var tabPanel = $('#wu-tabs');
			var tab = tabPanel.tabs('getSelected');
			if (tab){
				var index = tabPanel.tabs('getTabIndex', tab);
				tabPanel.tabs('close', index);
			}
		}
		
		/**
		*打开新窗口
		*/
		function openFirstWin(url, title, width, height) {
			
			var hostString = window.location.hostname;
			var portString = window.location.port;
			
			url = 'http://'+hostString+':'+portString+'/dmh/upFile';
			
			var strWidth;
			var strHeight;
			if (width == null)
				strWidth = 800;
			else
				strWidth = width;
			if (height == null)
				strHeight = 520;
			else
				strHeight = height;
			if (url != null) {
				var content = '<iframe name=\"first\" scrolling=\"auto\" frameborder=\"0\" src=\"' + url + '\" style=\"width:100%\;height:100%\;\"></iframe>';
				//var content = createIFrame(url);
				parent.$('#div_info').window({
					close: false,
					modal: true,
					draggable: true,
					title: title,
					animate: true,
					content: content,
					minimizable: false,
					width: strWidth,
					height: strHeight,
					top: (parent.$(parent.window).height() - height) * 0.5,
					left: (parent.$(window).width() + 200 - strWidth) * 0.5,
					onBeforeClose:function(){//窗口关闭时执行刷新
						//parent.$('#datagrid').datagrid('reload');
						parent.$('#fileUp').datagrid('reload');
						}
				});
			}
			return false;
		}

	</script>
</body>
</html>
