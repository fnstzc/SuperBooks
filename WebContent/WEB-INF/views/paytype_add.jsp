<%@ page contentType="text/html;charset=gb2312" %>
<%@ include file="incdb.jsp"%>
<%@ include file="sessionadmin.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="0">
<title>�������ϵͳ</title>
<link rel="stylesheet" href="images/css.css" type="text/css" media="screen"><script Language="JavaScript">
<!--
function check_input(theForm)
{

   if (theForm.name.value == "")
  {
    alert("�����������������.");
    theForm.name.focus();
    return (false);
  }

  if (theForm.name.value.length > 20)
    {
    alert("�����������Ƴ���ӦС��10���ַ�������.");
    theForm.name.focus();
    return (false);
  }

}
//-->
</script>
</head>
<body topmargin=0>
<TABLE width=50% border="0" align=center cellpadding=4 cellspacing=1 class="tableBorder">
<form method="POST" action="paytype_save.jsp" onsubmit="return check_input(this)">
	<TR>
		<Th colspan=2><div align="center">���ӷ�������</div></th>
	</TR>
	<TR bgcolor="#FFFFFF">
	  <TD width="30%">��������</TD>
		<TD width="70%">
	  <input name=name TYPE="text" id="name" size=20 maxlength=20>&nbsp;**���ó��� 10 ������</TD>
	</TR>

	<TR bgcolor="#FFFFFF">
	  <TD height="24" align=center><div align="left">��������</div></TD>
      <TD height="24"><select name="inout" id="inout">
        <option value="-1" selected>֧��</option>
        <option value="1">����</option>
      </select>
      <div align="left"></div></TD>
	</TR>
	<TR bgcolor="#FFFFFF"><TD height="45" colspan=2 align=center>	  <FONT color=#000000>
		<INPUT name=Submit type=submit value="ȷ ��"> &nbsp;&nbsp;     
		<INPUT name=Submit2 type=reset value="�� ��"></FONT></TD>
	</TR>
  </form>
</TABLE>

