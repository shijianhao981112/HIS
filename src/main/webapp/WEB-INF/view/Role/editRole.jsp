<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title></title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/dist/Css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/dist/Css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/dist/Css/style.css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/dist/Js/jquery.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/dist/Js/jquery.sorted.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/dist/Js/bootstrap.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/dist/Js/ckform.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/dist/Js/common.js"></script>
<!-- <script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js">
</script> -->

<script type="text/javascript">

$(function(){  
 
var group = ${roles};  
 
  //选中每个value与数组中value相同的checkbox  
 
 $.each(group, function(i,item){  
 
   $("input[name=group][value="+item+"]").attr("checked","checked");        <span>          </span>  
 
 });  
 
});

</script>


    <style type="text/css">
        body {
            padding-bottom: 40px;
        }
        .sidebar-nav {
            padding: 9px 0;
        }

        @media (max-width: 980px) {
            /* Enable use of floated navbar text */
            .navbar-text.pull-right {
                float: none;
                padding-left: 5px;
                padding-right: 5px;
            }
        }


    </style>
</head>
<body>
<form action="${pageContext.request.contextPath}/Role/index" method="post" class="definewidth m20" >
<table class="table table-bordered table-hover definewidth m10">
    <tr>
        <td width="10%" class="tableleft">角色名称</td>
        <td><input type="text" name="title" value="${roles.role_name }"/></td>
    </tr>
    <tr>
        <td class="tableleft">状态</td>
        <td>
            <input type="radio" name="statuss" value="1"  /> 启用
           <input type="radio" name="statuss" value="0"  /> 禁用
        </td>
    </tr>
    <tr>
        <td class="tableleft">权限</td>
        <td>
			<ul><label class='checkbox inline'><input type='checkbox'  name='group' value='1' />挂号信息管理</label></ul> 
            <ul><label class='checkbox inline'><input type='checkbox'   name='group' value='2' />门诊医生管理</label></ul> 
            <ul><label class='checkbox inline'><input type='checkbox'   name='group' value='3' />药品管理</label></ul> 
            <ul><label class='checkbox inline'><input type='checkbox'   name='group' value='4' />住院办理</label></ul> 
            <ul><label class='checkbox inline'><input type='checkbox'   name='group' value='5' />收费项目登记</label></ul> 
            <ul><label class='checkbox inline'><input type='checkbox'   name='group' value='6' />在院发药</label></ul> 
            <ul><label class='checkbox inline'><input type='checkbox'   name='group' value='7' />住院结算</label></ul> 
            <ul><label class='checkbox inline'><input type='checkbox'   name='group' value='8' />月营业额统计</label></ul> 
            <ul><label class='checkbox inline'><input type='checkbox' name='group' value='9' />年营业额统计</label></ul> 
            <ul><label class='checkbox inline'><input type='checkbox' name='group' value='10' />用户管理</label></ul> 
            <ul><label class='checkbox inline'><input type='checkbox' name='group' value='11' />角色管理</label></ul> 
            <ul><label class='checkbox inline'><input type='checkbox' name='group' value='12' />资源管理</label></ul> 
		</td>
    </tr>
    <tr>
        <td class="tableleft"></td>
        <td>
            <button type="submit" class="btn btn-primary" type="button">更新</button> &nbsp;&nbsp;<button type="button" class="btn btn-success" name="backid" id="backid">返回列表</button>
        </td>
    </tr>
</table>
</form>
                                                                                            
</body>
</html>
<script>
    $(function () {
        $(':checkbox[name="group[]"]').click(function () {
            $(':checkbox', $(this).closest('li')).prop('checked', this.checked);
        });

		$('#backid').click(function(){
				window.location.href="index.html";
		 });

    });
</script>