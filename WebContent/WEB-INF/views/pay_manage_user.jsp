<%@ page contentType="text/html;charset=gb2312" %>
<%@ include file="date.jsp"%>
<%@ include file="incdb.jsp"%>
<%@ include file="session.jsp"%>
<html>
<head>
<title>财务管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<link rel="stylesheet" href="images/css.css" type="text/css" media="screen">
</head>
<%
String action =(request.getParameter("action")==null?"":(request.getParameter("action")));
int spayerid =(request.getParameter("payerid")==null?0:(Integer.parseInt(request.getParameter("payerid"))));
int sinout =(request.getParameter("inout")==null?0:(Integer.parseInt(request.getParameter("inout"))));
int spaytypeid =(request.getParameter("paytypeid")==null?0:(Integer.parseInt(request.getParameter("paytypeid"))));
String saddtime =(request.getParameter("addtime")==null?"":(request.getParameter("addtime")));
String sproject =(request.getParameter("project")==null?"":(request.getParameter("project")));
		DispMaster dispmaster = new DispMaster();
		dispmaster.setUserName(userName);
		Master master = dispmaster.nameToMaster();
		int sclassid=master.getClassid();
%>
<body text="#000000" topmargin=0>
<form name="form1" method="post" action="pay_manage_user.jsp?action=search">
  <table width="98%" border="0" cellpadding="0" cellspacing="0" align="center" class=TableBorder>
    <tr height="22" valign="middle" align="center"> 
      <th width="11%" height="25">报销人</th>
      <th width="11%">所属类型 </th>
      <th width="11%">费用类型</th>
      <th width="24%">日期</th>
      <th width="16%">项目名称</th>
      <th width="13%">&nbsp;</th>
    </tr>
    <tr height="22" valign="middle" align="center"> 
      <td height="27"><select name="payerid" id="payerid">
        <option value="0">全部</option>
			  	<%
	DispPayer dispayer = new DispPayer();
		Vector listpayer = new Vector();
	listpayer = dispayer.allPayer();
	for(int i=0;i<listpayer.size();i++) {
	Payer payerinfo = (Payer)listpayer.elementAt(i);%>
	 <option value="<%=payerinfo.getId()%>" <%if (payerinfo.getId()==spayerid)
		{out.println("selected");}
		%>><%=payerinfo.getName()%></option>
        <%}%>
      </select></td>
      <td><select name="inout" id="inout">
        <option value="0">全部</option>
		<option value="1" <%if (sinout==1)
		{out.println("selected");}%>>收入</option>
		<option value="-1" <%if (sinout==-1)
		{out.println("selected");}%>>支出</option>
      </select></td>
      <td><select name="paytypeid" id="paytypeid">
        <option value="0">全部</option>
        <%
	DispPaytype dispaytype = new DispPaytype();
	Vector listpaytype = new Vector();
	listpaytype = dispaytype.allPaytype();
	for(int i=0;i<listpaytype.size();i++) {
	Paytype paytypeinfo = (Paytype)listpaytype.elementAt(i);%>
      <option value="<%=paytypeinfo.getId()%>" <%if (paytypeinfo.getId()==spaytypeid)
		{out.println("selected");}
		%>><%=paytypeinfo.getName()%></option>
        <%}%>
      </select></td>
      <td><input name="addtime" type="text" id="addtime" size="12" maxlength="12" readonly value="<%=saddtime%>"> 
        <input name="button" type="button" onclick="popUpCalendar(this, form1.addtime, 'yyyy-mm-dd')" value="请选择日期"></td>
      <td><input name="project" type="text" id="project" size="20" maxlength="50" value="<%=sproject%>"></td>
      <td><input type="submit" name="Submit" value="提交">
        <input type="reset" name="Submit2" value="重置"></td>
    </tr>
  </table>
</form>
<table width="98%" border="0" cellpadding="2" cellspacing="0" align="center" class=TableBorder> 
<tr height="22" valign="middle" align="center"> 
    <th height="25" colspan="9">费用清单搜索结果(<font class=red><%
	    DispClass sdispClass = new DispClass();
		sdispClass.setID(sclassid);
		IClass sclass = sdispClass.idToClass();
		out.println(sclass.getName());
        %></font>)</th>
  </tr>
  <tr> 
    <td width="4%" height="25" class=forumrow><div align="center">ID</div></td>
    <td width="15%" class=forumrow><div align="center">部门</div></td>
    <td width="6%" class=forumrow> <div align="center">报销人</div></td>
    <td width="8%" class=forumrow> <div align="center">所属类型</div></td>
    <td width="9%" class=forumrow><div align="center">费用类型</div></td>
    <td width="7%" class=forumrow> <div align="center">金额(元)</div></td>
    <td width="16%" class=forumrow> <div align="center">项目名称</div></td>
    <td width="17%" class=forumrow> <div align="center">说明</div></td>
    <td width="9%" class=forumrow><div align="center">日期</div></td>
  </tr>
  	<%
	DispPay dispay = new DispPay();
	Vector listpay = new Vector();
	    String sqlstr;
	    sqlstr="";
		int data_num;

	if (action.equals("search"))
	{
	    
		    sqlstr=sqlstr+"classid="+sclassid;
		
	    if (spayerid!=0){
		        if (!sqlstr.equals("")){sqlstr=sqlstr+" and payerid="+spayerid;}
				else{sqlstr=sqlstr+"payerid="+spayerid;}
			}
		
	    if (sinout!=0){
		        if (!sqlstr.equals("")){sqlstr=sqlstr+" and inout="+sinout;}
				else{sqlstr=sqlstr+"inout="+sinout;}
				}

	    if (spaytypeid!=0){
		        if (!sqlstr.equals("")){sqlstr=sqlstr+" and paytypeid="+spaytypeid;}
				else{sqlstr=sqlstr+"paytypeid="+spaytypeid;}
				}

	    if (!saddtime.equals("")){
		        if (!sqlstr.equals("")){sqlstr=sqlstr+" and addtime='"+saddtime+"'";}
				else{sqlstr=sqlstr+"addtime='"+saddtime+"'";}
				}

	    if (!sproject.equals("")){
		        if (!sqlstr.equals("")){sqlstr=sqlstr+" and project='"+sproject+"'";}
				else{sqlstr=sqlstr+"project like '%"+sproject+"%'";}
				}
		if (sqlstr.equals(""))
	            {
				   dispay.setClassid(master.getClassid());
	               listpay = dispay.classidtoallPay();
				   data_num=dispay.classidtopaynum();
	             }
	     else{
		 	    dispay.setSqlstr(sqlstr);
	            listpay = dispay.searchallPay();
				data_num=dispay.searchpaynum();
		 }
    }
	else
	{
	  dispay.setClassid(master.getClassid());
	  listpay = dispay.classidtoallPay();
	  data_num=dispay.classidtopaynum();
	}
//分页程序开始
//int data_num=dispay.paynum();
DisPage dispage=new DisPage();
int Current_Page = 0;
String currentpage=(String)request.getParameter("currentpage");
if (currentpage != null && !currentpage.equals(""))
{Current_Page = Integer.parseInt(request.getParameter("currentpage"));}

dispage.Init(Current_Page,data_num);

int l_start = dispage.getStart();
int l_end = dispage.getEnd();
	  
	//for(int i=0;i<listpay.size();i++) {
	int i=0;
	for(i=l_start;i<l_end;i++){
	Pay payinfo = (Pay)listpay.elementAt(i);%>
   <tr>
    <td height="25"><div align="center"><%=payinfo.getId()%></div></td>
    <td height="25"><div align="center">
	<%
	    int classid=payinfo.getClassid();
	    DispClass dispClass = new DispClass();
		dispClass.setID(classid);
		IClass iclass = dispClass.idToClass();
		out.println(iclass.getName());
	%></div></td>
    <td><div align="center"><%
	    int payerid=payinfo.getPayerid();
	    DispPayer disppayer = new DispPayer();
		disppayer.setId(payerid);
		Payer payer = disppayer.idToPayer();
		out.println(payer.getName());
	%></div></td>
    <td><div align="center"><%if (payinfo.getPaytypeid()==1)
	{
	out.println("<font class=red>收入</font>");
	}
	else
	{
	out.println("<font class=blue>支出</font>");
	}
	
	%></div></td>
    <td><div align="center"><%
	    int paytypeid=payinfo.getPaytypeid();
	    DispPaytype disppaytype = new DispPaytype();
		disppaytype.setId(paytypeid);
		Paytype paytype = disppaytype.idToPaytype();
		out.println(paytype.getName());
	%></div></td>
    <td><div align="right"><%=payinfo.getMoney()%></div></td>
    <td><div align="center"><%=payinfo.getProject()%></div></td>
    <td><div align="center"><%=payinfo.getMessage()%></div></td>
    <td><div align="center"><%=payinfo.getAddtime()%></div></td>
   </tr>
  <%}%>

<%
if (sqlstr.equals("")){
%>
   <tr>
     <td height="25" colspan="9"><div align="center">记录总条数：<%=data_num%> 当前页<%=Current_Page+1%>/<%=dispage.getTotalpage()+1%>总页数 <a href="pay_manage_user.jsp?currentpage=0" class="red">首页</a> <a href="pay_manage_user.jsp?currentpage=<%=dispage.getPrepage()%>" class="red">上一页</a> <a href="pay_manage_user.jsp?currentpage=<%=dispage.getNextpage()%>" class="red">下一页</a> <a href="pay_manage_user.jsp?currentpage=<%=dispage.getTotalpage()%>" class="red">末页</a> </div></td>
   </tr>
<%}
else{%>
   <tr>
     <td height="25" colspan="9"><div align="center">记录总条数：<%=data_num%> 当前页<%=Current_Page+1%>/<%=dispage.getTotalpage()+1%>总页数
	  <a href="pay_manage_user.jsp?currentpage=0&action=search&classid=<%=sclassid%>&payerid=<%=spayerid%>&inout=<%=sinout%>&paytypeid=<%=spaytypeid%>&addtime=<%=saddtime%>&project=<%=java.net.URLEncoder.encode(sproject)%>" class="red">首页</a>
	  <a href="pay_manage_user.jsp?currentpage=<%=dispage.getPrepage()%>&action=search&classid=<%=sclassid%>&payerid=<%=spayerid%>&inout=<%=sinout%>&paytypeid=<%=spaytypeid%>&addtime=<%=saddtime%>&project=<%=java.net.URLEncoder.encode(sproject)%>" class="red">上一页</a>
	   <a href="pay_manage_user.jsp?currentpage=<%=dispage.getNextpage()%>&action=search&classid=<%=sclassid%>&payerid=<%=spayerid%>&inout=<%=sinout%>&paytypeid=<%=spaytypeid%>&addtime=<%=saddtime%>&project=<%=java.net.URLEncoder.encode(sproject)%>" class="red">下一页</a>
	    <a href="pay_manage_user.jsp?currentpage=<%=dispage.getTotalpage()%>&action=search&classid=<%=sclassid%>&payerid=<%=spayerid%>&inout=<%=sinout%>&paytypeid=<%=spaytypeid%>&addtime=<%=saddtime%>&project=<%=java.net.URLEncoder.encode(sproject)%>" class="red">末页</a> </div></td>
   </tr>
<%}%>
</table>
