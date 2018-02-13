<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!-- release v4.3.6, copyright 2014 - 2017 Kartik Visweswaran -->
<html lang="en">
    <head>
        <meta charset="UTF-8"/>
        <title>文件上传</title>
        <link href="<c:url value='/resources/bootstrap/3.3.6/css/bootstrap.min.css'/>" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="<c:url value='/resources/upfile/css/default.css'/>">
        <link href="<c:url value='/resources/upfile/css/fileinput.css'/>" media="all" rel="stylesheet" type="text/css" />
        <script src="<c:url value='/resources/js/jquery-1.8.0.min.js'/>" type="text/javascript"></script>
        <script>window.jQuery || document.write('<script src="js/jquery-1.11.0.min.js"><\/script>')</script>
        <script src="<c:url value='/resources/upfile/js/fileinput.js'/>" type="text/javascript"></script>
        
        <script src="<c:url value='/resources/upfile/js/locales/zh.js'/>" type="text/javascript"></script>
       
        <script src="<c:url value='/resources/bootstrap/3.3.6/js/bootstrap.min.js'/>"></script>
    </head>
    <body onunload="alert('The onunload event was triggered');">
            <div class="htmleaf-container">
                
                <div class="container kv-main">
                    
                
                    
                    <hr>
                    
                    <form enctype="multipart/form-data">
                        <label>文电上传</label>
                        <input id="file-zh" name="file-zh[]" type="file" multiple>
                        
                    </form>
                    <hr>
                    <br>
                </div>
                
            </div>
    </body>
	<script>
	$("#file-zh").fileinput({
		              uploadUrl: 'uploadFile', // you must set a valid URL here else you will get an error
		              allowedFileExtensions : ['jpg','png','xls','xlsx','doc','docx'],
		              language: 'zh',
		              overwriteInitial: false,
		              maxFileSize: 0,
		              maxFilesNum: 10,
		              //allowedFileTypes: ['image', 'video', 'flash'],
		             slugCallback: function(filename) {
		                 return filename.replace('(', '_').replace(']', '_');
		             },
		             uploadExtraData: function(previewId, index) {   //额外参数的关键点
		                    var obj = {};
		                    obj.fodder = fodderType();
		                    obj.aaa = fodderType();
		                    console.log(obj);
		                    return obj;
		                }
		         });
		       //获得额外参数的方法
		         fodderType = function() {
		                 //return $("#fodderTypeSelect").val();
		                 return "123";
		             };	
		            
		             
		             
		             
		        
		                 
	</script>
</html>