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
		<Th colspan=4 align=center><B class="whitetitle">费用类型管理</B></Th>
	</TR>
	<TR align=center bgcolor="#f1f3f5">
		<TD width=14%>编号</TD>
		<TD width=32%>费用类型名称</TD>
		<TD width=32%>所属类型(<span class="red">收入</span>/<span class="blue">支出</span>)</TD>
		<TD width=22%>操作</TD>
	</TR>

	<%
	DispPaytype disppaytype = new DispPaytype();
		Vector listpaytype = new Vector();
	listpaytype = disppaytype.allPaytype();
	for(int i=0;i<listpaytype.size();i++) {
	Paytype paytypeinfo = (Paytype)listpaytype.elementAt(i);%>
	<TR>
		<TD><div align="center"><%=paytypeinfo.getId()%></div></TD>
		<TD><div align="center"><A HREF="paytype_edit.jsp?id=<%=paytypeinfo.getId()%>"><%=paytypeinfo.getName()%></A></div></TD>
		<TD><div align="center">
	      <%//1为收入，0为支出，数据库字段默认为0
		if(paytypeinfo.getInout()==1)
		out.println ("<font class=red>收入<font>");
		else
		out.println ("<font class=blue>支出<font>");		
		%></div></TD>
		<TD align=center><A HREF="paytype_edit.jsp?id=<%=paytypeinfo.getId()%>">编辑</A>
		| <A HREF="paytype_del.jsp?id=<%=paytypeinfo.getId()%>" onClick="return delpay();">删除</A></TD>
	</TR>
	<%}%>
	  <TR>
	  <TD colspan="4"><div align="center"><span class="red">注意</span>：费用类型的所属类型只能在新添加时确定，不可以修改。</div></TD>
	  </TR>
	</TBODY>
</TABLE>
