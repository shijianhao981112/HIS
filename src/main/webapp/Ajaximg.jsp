<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 
	通过new Form表单对象,从表单对象中获得上传的文件对象
	将这个对象通过ajax发送到后台
 -->
<!-- 文件的上传 -->
<form action="#" id="formId">
	<input type="file" name="img"><br>
	<input type="button" value="上传" onclick="doUpload();">
</form>
<div >
	<img id="i1" alt="" width="500px" height="500px"  src="" >
</div>

</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/dist/Js/jquery.js"></script>
<script type="text/javascript">
	function doUpload(){
		// 创建Form表单对象
		var formData = new FormData($("#formId")[0]);
		// alert("xx");
		// ajax 发送请求,返回图片地址,赋值给img标签
		$.ajax({
			url:"/A_Hospital/upload.do",
			type:"POST",
			data:formData,
			
			async:false,
			contentType:false,
			processData:false,
			
			success:function(data){
				 alert(data.obj);
				$("#i1").attr("src","${pageContext.request.contextPath}"+data.obj);
			}
		});
	}

</script>
</html>