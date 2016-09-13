<%@ page contentType="text/html;charset=gb2312" %>
<%@ include file="incdb.jsp"%>
<%@ include file="sessionadmin.jsp"%>
<html>
<head>
<title>财务管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<meta http-equiv="refresh" content="60*30">
<link rel="stylesheet" href="images/css.css" type="text/css" media="screen">
</head>
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
<%int id = Integer.parseInt(request.getParameter("id"));
		DispPay disppay = new DispPay();
		disppay.setId(id);
		Pay pay = disppay.idToPay();%>
<body text="#000000" topmargin=0>
<table cellpadding="2" cellspacing="1" border="0" width="95%" class="tableBorder" align=center>
  <form action="pay_editsave.jsp?id=<%=id%>" method=post id=form1 name=form1 onSubmit="return chk(this)">
    <tr bgcolor=ffffff> 
      <th height=25 colspan=5 align="center">修改费用记录
     </th>
    </tr>
    <tr bgcolor=ffffff> 
      <td width="26%" class=forumrow><div align="right">部门：</div></td>
      <td width="18%" class=forumrow><select name="classid">
        <%	Vector listclass = new Vector();
			DispClass dispclass = new DispClass();
			dispclass.setID(pay.getClassid());
			IClass classname=dispclass.idToClass();
			out.println("<option value=\""+classname.getID()+"\">"+classname.getName()+"</option>");
			listclass = dispclass.allClass();
			for(int i=0;i<listclass.size();i++) {
			IClass classinfo = (IClass)listclass.elementAt(i);%>
        <option value="<%=classinfo.getID()%>"><%=classinfo.getName()%></option>
        <%}%>
      </select></td>
      <td width="7%" class=forumrow><div align="right">日期：</div></td>
      <td colspan="2" class=forumrow><input name="addtime" type="text" id="addtime" value="<%=pay.getAddtime()%>" size="12" maxlength="12" readonly> 
        <input onclick="popUpCalendar(this, form1.addtime, 'yyyy-mm-dd')" type="button" value="请选择日期"></td>
    </tr>
    <tr  bgcolor=ffffff> 
      <td width="26%" class=forumrow><div align="right">报销人：</div></td>
      <td colspan="4" class=forumrow>
	  <select name="payerid" id="payerid">
	  	<%
	DispPayer disppayer = new DispPayer();
	Vector listpayer = new Vector();
	disppayer.setId(pay.getPayerid());
	Payer payer=disppayer.idToPayer();
	out.println("<option value=\""+payer.getId()+"\">"+payer.getName()+"</option>");	
	listpayer = disppayer.allPayer();
	for(int i=0;i<listpayer.size();i++) {
	Payer payerinfo = (Payer)listpayer.elementAt(i);
	out.println("<option value="+payerinfo.getId()+">"+payerinfo.getName()+"</option>");  
    }%>
        </select></td>
    </tr>
    <tr  bgcolor=ffffff> 
      <td class=forumrow><div align="right">费用类型：</div></td>
      <td colspan="4" class=forumrow><select name="paytypeid" id="paytypeid">
	  	<%
	DispPaytype dispaytype = new DispPaytype();
	Vector listpaytype = new Vector();
	dispaytype.setId(pay.getPaytypeid());
	Paytype paytype=dispaytype.idToPaytype();
	out.println("<option value=\""+paytype.getId()+"\">"+paytype.getName()+"</option>");	
	dispaytype.setInout(pay.getInout());
	listpaytype = dispaytype.inoutPaytype();
	for(int i=0;i<listpaytype.size();i++) {
	Paytype paytypeinfo = (Paytype)listpaytype.elementAt(i);
	out.println("<option value="+paytypeinfo.getId()+">"+paytypeinfo.getName()+"</option>");  
    }%>
        </select>
      (<%if(pay.getInout()==0){
	  out.println("<font class=blue>支出</font>");}
	  else{
	  out.println("<font class=red>收入</font>");
	  }%>)</td>
    </tr>
    <tr  bgcolor=ffffff> 
      <td class=forumrow><div align="right">金额：</div></td>
      <td colspan="4" class=forumrow>
	  <input name="money" type="text" id="money" size="10" maxlength="10" value="<%=pay.getMoney()%>">
	  (<span class="red">元</span>)</td>
    </tr>
    <tr  bgcolor=ffffff> 
      <td class=forumrow><div align="right">项目名称：</div></td>
      <td colspan="4" class=forumrow> <input name="project" type="text" id="project" size="50" maxlength="50" value="<%=pay.getProject()%>"></td>
    </tr>
    <tr  bgcolor=ffffff> 
      <td rowspan="2" class=forumrow><div align="right">说明：</div></td>
      <td colspan="4" class=forumrow><textarea name="message" cols="50" rows="8" id="message"><%=pay.getMessage()%></textarea> 
      </td>
    </tr>
    <tr  bgcolor=ffffff> 
      <td colspan="3" class=forumrow>&nbsp; </td>
      <td width="38%" class=forumrow><a href="javascript:change(document.all.message,-50)"><img src="images/minus.gif" alt="缩小文本框" width="20" height="20" border="0"></a> 
        <a href="javascript:change(document.all.message,50)"><img src="images/plus.gif" alt="放大文本框" width="20" height="20" border="0"></a></td>
    </tr>
    <tr  bgcolor=ffffff> 
      <td colspan="5" align="center" class=forumrow><input type="submit" name="edit" value="修 改"></td>
    </tr>
  </form>
</table>
</html>