<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<title>欢迎登录家庭财务管理系统</title>
<link rel="stylesheet" href="css.css" type="text/css" media="screen">
<script language="JavaScript" src="js/js.js"></script>
</head>
<body style="background-color: #333333">
	<p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p>
	<form action="loginCL" method=post onsubmit="return check_input(this)">
		<table width="400" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#6685C5">
			<tr>
				<td bgcolor="#FFFFFF">
					<table width="400" border="0"
						align="center" cellpadding="0" cellspacing="0">
						<tr>
							<td colspan="3">
								<img src="images/login.gif" width="400" height="167">
							</td>
						</tr>
						<tr align="center">
							<td height="35" colspan="3">
								<div align="center">
									用户名：<input name=name class="s01" size=16 maxLength=20><br>
									密&nbsp;码：<input name=password type=password class="s01" size=16
										maxLength=20>
								</div>
							</td>
						</tr>
						<tr align="center">
							<td>
								&nbsp;
							</td>
						</tr>
						<tr align="center">
							<td>
								<div align="center">
									<input type=submit class="s02" value="登录">
									<input type=reset class="s02" value="取消">
								</div>
								<p>&nbsp;</p><br>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>