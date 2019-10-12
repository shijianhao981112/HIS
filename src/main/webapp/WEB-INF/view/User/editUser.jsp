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
<form action="${pageContext.request.contextPath}/User/editUser" method="post" class="definewidth m20">
<input type="hidden" name="id" value="${user.id}" />
    <table class="table table-bordered table-hover definewidth m10">
        <tr>
            <td width="10%" class="tableleft">登录名</td>
            <td>${user.user_name }</td>
        </tr>
        <tr>
            <td class="tableleft">密码</td>
            <td><input type="text" name="password" value="${user.password }"/></td>
        </tr>
        <tr>
            <td class="tableleft">真实姓名</td>
            <td><input type="text" name="real_name" value="${user.real_name }"/></td>
        </tr>
        <tr>
            <td class="tableleft">邮箱</td>
            <td><input type="text" name="email" value="${user.email }"/></td>
        </tr>
        <tr>
            <td class="tableleft">状态</td>
            <td>
                <input type="radio" name="status" value="1" checked/> 启用
              <input type="radio" name="status" value="0" /> 禁用
            </td>
        </tr>
        <tr>
            <td class="tableleft">角色</td>
            <td>
            	<select name="role_id">
        			<option value="" selected>--请选择--
        			<option value="1" >管理员
        			<option value="2">院长
            		<option value="3">医生护士人员
       			 </select>
        	</td>
        </tr>
        <tr>
            <td class="tableleft"></td>
            <td>
                <button type="submit" class="btn btn-primary" type="button">更新</button>&nbsp;&nbsp;
                <button type="button" class="btn btn-success" name="backid" id="backid">返回列表</button>
            </td>
        </tr>
    </table>
</form>
                                                                                            
</body>
</html>
<script>
    $(function () {       
		$('#backid').click(function(){
				window.location.href="index.html";
		 });
    });
</script>