<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>家庭财务管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<meta http-equiv="refresh" content="60*30">
<link rel="stylesheet" href="css/css.css" type="text/css" media="screen">
<script type="text/javascript" src="laydate/laydate.js"></script>
<script language="JavaScript">
<!--
function change(obj,i) {
he=parseInt(obj.style.height);
if (he>=80&&he<=400)
   obj.style.height=he+i+'px';
else 
   obj.style.height='80px';
}

function chk(theForm){
if (theForm.classid.value == ""){
                alert("请选择部门!");
                theForm.classid.focus();
                return (false);
        } 
if (theForm.addtime.value == ""){
                alert("请输入日期!");
                theForm.addtime.focus();
                return (false);
        } 
if (theForm.money.value == ""){
                alert("请输入金额!");
                theForm.money.focus();
                return (false);
        } 
if (theForm.project.value == ""){
                alert("请输入项目名称!");
                theForm.project.focus();
                return (false);
        } 
}
//-->
</script>
</head>

<body text="#000000">
	<p>&nbsp;</p><br><br><br>
	<form action="addConsume" method="post" onSubmit="return chk(this)">
		<table cellpadding="2" cellspacing="1" border="0" width="400" height="400" class="tableBorder" align=center>
			<tr>
				<th height=25 colspan=5 align="center">新增消费记录</th>
			</tr>
			<tr>
				<td>姓名:<input type="text" name="name" /></td>
			</tr>
			<tr>
				<td class=forumrow>日期：  
					<input onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})"
					type="button" value="请选择日期" name="time" />
				</td>
			</tr>
			<tr>
				<td class=forumrow>
					金额：<input name="cost" type="text" size="10" maxlength="10"> (<span class="red">元</span>)
				</td>
			</tr>
			<tr>
				<td class=forumrow>
				用途：<select name="purpose">
						<option value="shopping">购物</option>
						<option value="book">购书</option>
						<option value="communication">话费</option>
						<option value="dog">狗粮</option>
						<option value="food">吃饭</option>
						<option value="traffic">交通</option>
						<option value="party">聚餐</option>
						<option value="treat">请客</option>
						<option value="ticket">车票</option>
					</select>
				</td>
			</tr>
			<tr>
				<td class=forumrow>
					消费地点：<input type="text" name="place" />
				</td>
			</tr>
			<tr>
				<td class=forumrow>
					消费途径：<input name="costWay" type="text" id="project" size="50" maxlength="50">
				</td>
			</tr>
			<tr>
				<td rowspan="2" class=forumrow>
					具体说明：<textarea name="description" cols="50" rows="8" id="message"></textarea>
				</td>
			</tr>
			<tr>
				<td colspan="3" class=forumrow>&nbsp;</td>
				<td width="38%" class=forumrow><a
					href="javascript:change(document.all.message,-50)"><img
						src="images/minus.gif" alt="缩小文本框" width="20" height="20"
						border="0"></a> <a
					href="javascript:change(document.all.message,50)"><img
						src="images/plus.gif" alt="放大文本框" width="20" height="20"
						border="0"></a></td>
			</tr>
			<tr>
				<td colspan="5" align="center" class=forumrow><input
					type="submit" name="add" value="添 加"></td>
			</tr>
		</table>
	</form>
</html>