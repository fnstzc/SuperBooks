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
<SCRIPT language=javascript>
function Chk(theForm)
{

if (theForm.classid.value == "0"){
                alert("��������Ҫѡ��Ĳ���!");
                theForm.classid.focus();
                return (false);
    }else{
	updated.style.display = '';
}

if (theForm.addtime_year.value == "0"){
                alert("��������Ҫͳ�Ƶ����!");
                theForm.addtime_year.focus();
                return (false);
    }else{
	updated.style.display = '';
}

}
</SCRIPT>
<script language="javascript">
function printsub()
{
if (confirm('�뵥����ȷ������ť��ʼ��ӡ��������ȡ������ť����ӡ��'))
{
	window.print();
}
}
</script>
<link rel="stylesheet" href="images/css.css" type="text/css" media="screen">
<%String action =(request.getParameter("action")==null?"":(request.getParameter("action")));
int sclassid =(request.getParameter("classid")==null?0:(Integer.parseInt(request.getParameter("classid"))));
int saddtime_year =(request.getParameter("addtime_year")==null?0:(Integer.parseInt(request.getParameter("addtime_year"))));
//String saddtime_year =(request.getParameter("addtime_year")==null?"":(request.getParameter("addtime_year")));
%>
</head>
<BODY text="#000000" topmargin=0>
<DIV id=overDiv style="Z-INDEX: 1; POSITION: absolute"></DIV>
<TABLE cellSpacing=1 cellPadding=0 width="98%" align=center border=0 class="tableBorder">
  <TBODY>
    <TR> 
      <FORM name="Form1" action="pay_count.jsp?action=search" method="post" onSubmit="return Chk(this);">
        <th align=middle colSpan=6 height=22>
		  ���ţ�
		    <SELECT name="classid">
            <option value="0">--��ѡ����--</option>
			<%	Vector listclass = new Vector();
			DispClass dispclass = new DispClass();
			listclass = dispclass.allClass();
			for(int i=0;i<listclass.size();i++) {
			IClass classinfo = (IClass)listclass.elementAt(i);%>
        <option value="<%=classinfo.getID()%>" <%if (classinfo.getID()==sclassid)
		{out.println("selected");}
		%>><%=classinfo.getName()%></option>
        <%}%>
      </select>
          </SELECT>
		  ��ȣ�
		  <SELECT name="addtime_year">
            <option value="0">--��ѡ�����--</option>
			<%	Vector listpay = new Vector();
			DispPay disppay = new DispPay();
			listpay = disppay.allAddtime_year();
			for(int i=0;i<listpay.size();i++) {
			Pay payinfo = (Pay)listpay.elementAt(i);%>
        <option value="<%=payinfo.getAddtime_year()%>" <%if (payinfo.getAddtime_year()==saddtime_year)
		{out.println("selected");}
		%>><%=payinfo.getAddtime_year()%></option>
        <%}%>
          </SELECT>
          <input type="submit" name="Submit" value=" �� �� ͳ �� ">
        ���ͳ�Ʊ�</th>
      </FORM>
    </TR>
    <TR>
      <td align=middle colSpan=6> 
<DIV id="updated" style="DISPLAY: none" align="center">
          <table width="100%" border="0" align="center" cellpadding="4" cellspacing="4">
            <tr>
              <td height="50"> 
                <div align="center"><b>����ͳ�ƣ����Ժ�...<b> <img height="15" alt="Laoding..." src="images/loading.gif" width="150"></b></b></div></td>
            </tr>
          </table>
        </DIV>
<%
if (action.equals("search")){
%>
<table width="100%" border="0" align="center" cellpadding="2" cellspacing="1" bgcolor="#CCCCCC">
          <tr> 
            <td width="9%" height="25" class=forumrow>��������</td>
            <td width="5%" class=forumrow><div align="center">1��</div></td>
            <td width="5%" class=forumrow><div align="center">2��</div></td>
            <td width="5%" class=forumrow><div align="center">3��</div></td>
            <td width="5%" class=forumrow><div align="center">4��</div></td>
            <td width="5%" class=forumrow><div align="center">5��</div></td>
            <td width="5%" class=forumrow><div align="center">6��</div></td>
            <td width="5%" class=forumrow><div align="center">7��</div></td>
            <td width="5%" class=forumrow><div align="center">8��</div></td>
            <td width="5%" class=forumrow><div align="center">9��</div></td>
            <td width="5%" class=forumrow><div align="center">10��</div></td>
            <td width="5%" class=forumrow><div align="center">11��</div></td>
            <td width="5%" class=forumrow><div align="center">12��</div></td>
            <td width="9%" class=forumrow><div align="center"><b>�ϼ�</b></div></td>
          </tr>
 <%
    int totle_month;
	int totle_year;
	int totle_in[]=new int[13];
	int totle_out[]=new int[13];
	DispPaytype dispaytype = new DispPaytype();
	Vector listpaytype = new Vector();
	listpaytype = dispaytype.allPaytype();
	for(int i=0;i<listpaytype.size();i++) {
	Paytype paytypeinfo = (Paytype)listpaytype.elementAt(i);
 %>
          <tr bgcolor="#FFFFFF">
            <td height="25"><%=paytypeinfo.getName()%>(<%if (paytypeinfo.getInout()==1)
			{out.println("<font class=red>����</font>");}
			else{out.println("<font class=blue>֧��</font>");}
			%>)</td>
 			<%for (int j=1;j<13;j++)
			{%>
            <td>
			<%
			disppay.setClassid(sclassid);
			disppay.setPaytypeid(paytypeinfo.getId());
			disppay.setAddtime_year(saddtime_year);
			disppay.setAddtime_month(j);
			totle_month = disppay.totlemoney_month();
			if (paytypeinfo.getInout()==1)
			{out.println("<font class=red>"+totle_month+"</font>");}
			else{out.println("<font class=blue>"+(0-totle_month)+"</font>");}
			%>
			&nbsp;</td>
		    <%}%>
		  <td>
		  <%
		    disppay.setClassid(sclassid);
			disppay.setPaytypeid(paytypeinfo.getId());
			disppay.setAddtime_year(saddtime_year);
			totle_year = disppay.totlemoney_year();
			if (paytypeinfo.getInout()==1)
			{out.println("<font class=red>"+totle_year+"</font>");}
			else{out.println("<font class=blue>"+(0-totle_year)+"</font>");}
		  %>
		  &nbsp;</td>
          </tr>
   <%}%>
          <tr>
            <td height="25" class=forumrow><b>֧���ϼ�</b></td>
 			<%for (int j=1;j<13;j++)
			{%>
            <td class=forumrow>
			<%
			disppay.setClassid(sclassid);
			disppay.setInout(-1);
			disppay.setAddtime_year(saddtime_year);
			disppay.setAddtime_month(j);
			totle_month = disppay.totleinout_month();
			out.println("<font class=blue>"+(0-totle_month)+"</font>");
			totle_out[j-1]=totle_month;
			%>
			&nbsp;</td>
		    <%}%>
		  <td class=forumrow>
		  <%
		    disppay.setClassid(sclassid);
			disppay.setInout(-1);
			disppay.setAddtime_year(saddtime_year);
			totle_year = disppay.totleinout_year();
			out.println("<font class=blue>"+(0-totle_year)+"</font>");
			totle_out[12]=totle_year;
		  %>
		  &nbsp;</td>
          </tr>

          <tr>
            <td height="25" class=forumrow><b>����ϼ�</b></td>
 			<%for (int j=1;j<13;j++)
			{%>
            <td class=forumrow>
			<%
			disppay.setClassid(sclassid);
			disppay.setInout(1);
			disppay.setAddtime_year(saddtime_year);
			disppay.setAddtime_month(j);
			totle_month = disppay.totleinout_month();
			out.println("<font class=red>"+totle_month+"</font>");
			totle_in[j-1]=totle_month;
			%>
			&nbsp;</td>
		    <%}%>
		  <td class=forumrow>
		  <%
		    disppay.setClassid(sclassid);
			disppay.setInout(1);
			disppay.setAddtime_year(saddtime_year);
			totle_year = disppay.totleinout_year();
			out.println("<font class=red>"+totle_year+"</font>");
			totle_in[12]=totle_year;
		  %>
		  &nbsp;</td>
          </tr>
          <tr>
            <td height="25" class=forumrow><b>��֧ƽ��</b></td>
			<%for (int j=1;j<13;j++){%>
            <td class=forumrow><%=totle_in[j-1]-totle_out[j-1]%>&nbsp;</td>
            <%}%>
            <td class=forumrow><%=totle_in[12]-totle_out[12]%>&nbsp;</td>
          </tr>
        </table>
    <%}%>
      </td>
    </TR>
  </TBODY>
</TABLE>
<DIV id=data align=center></TABLE></TR></TABLE> 
  <CENTER>
    <p><INPUT onclick="printsub();" type=button value='��ӡ���ͳ�Ʊ�' name=out_excel></SPAN></p>
  </CENTER>
</BODY>
</HTML>
