<%@ page contentType="text/html;charset=gb2312" %>
<%@ include file="incdb.jsp"%>
<%@ include file="sessionadmin.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<meta http-equiv="refresh" content="60*30">
<title>�������ϵͳ</title>
<link rel="stylesheet" href="images/css.css" type="text/css" media="screen">
<script language="JavaScript" type="text/JavaScript">
function delpay()
{
   if(confirm("ȷ��Ҫɾ������"))
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
		<Th colspan=4 align=center><B class="whitetitle">�������͹���</B></Th>
	</TR>
	<TR align=center bgcolor="#f1f3f5">
		<TD width=14%>���</TD>
		<TD width=32%>������������</TD>
		<TD width=32%>��������(<span class="red">����</span>/<span class="blue">֧��</span>)</TD>
		<TD width=22%>����</TD>
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
	      <%//1Ϊ���룬0Ϊ֧�������ݿ��ֶ�Ĭ��Ϊ0
		if(paytypeinfo.getInout()==1)
		out.println ("<font class=red>����<font>");
		else
		out.println ("<font class=blue>֧��<font>");		
		%></div></TD>
		<TD align=center><A HREF="paytype_edit.jsp?id=<%=paytypeinfo.getId()%>">�༭</A>
		| <A HREF="paytype_del.jsp?id=<%=paytypeinfo.getId()%>" onClick="return delpay();">ɾ��</A></TD>
	</TR>
	<%}%>
	  <TR>
	  <TD colspan="4"><div align="center"><span class="red">ע��</span>���������͵���������ֻ���������ʱȷ�����������޸ġ�</div></TD>
	  </TR>
	</TBODY>
</TABLE>
