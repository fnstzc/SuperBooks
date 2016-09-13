<%@ page contentType="text/html;charset=gb2312" %>
<%@ include file="incdb.jsp"%>
<%@ include file="sessionadmin.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<meta http-equiv="refresh" content="60*30">
<title>财务管理系统</title>
<link rel="stylesheet" href="images/css.css" type="text/css" media="screen">
<script language="JavaScript" type="text/JavaScript">
function delpay()
{
   if(confirm("确定要删除此吗？"))
     return true;
   else
     return false;	 
}
</script>
</head>
<body topmargin=0>
<TABLE width=90% border=0 align=center cellPadding=5 cellSpacing=0 class="tableBorder">
	<TBODY>
	<TR>
		<Th colspan=4 align=center><B class="whitetitle">报销人管理</B></Th>
	</TR>
	<TR align=center bgcolor="#f1f3f5">
		<TD width=13%>id</TD>
		<TD width=33%>报销人</TD>
		<TD width=32%>报销数量</TD>
		<TD width=22%>操作</TD>
	</TR>

	<%
	DispPayer dispayer = new DispPayer();
		Vector listpayer = new Vector();
	listpayer = dispayer.allPayer();
	for(int i=0;i<listpayer.size();i++) {
	Payer payerinfo = (Payer)listpayer.elementAt(i);%>
	<TR>
		<TD><div align="center"><%=payerinfo.getId()%></div></TD>
		<TD><div align="center"><a href="payer_edit.jsp?id=<%=payerinfo.getId()%>"><%=payerinfo.getName()%></a></div></TD>
		<TD>&nbsp;</TD>
		<TD align=center><A HREF="payer_edit.jsp?id=<%=payerinfo.getId()%>">编辑</A>
		&nbsp;<A HREF="payer_del.jsp?id=<%=payerinfo.getId()%>" onClick="return delpay();">删除</A></TD>
	</TR>
	<%}%>
	</TBODY>
</TABLE>
