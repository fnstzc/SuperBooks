<%@ page contentType="text/html;charset=gb2312" %>
<%@ include file="incdb.jsp"%>
<%@ include file="sessionadmin.jsp"%>
<%try{
    //获取数据
    int classid = Integer.parseInt(request.getParameter("classid"));
	int payerid = Integer.parseInt(request.getParameter("payerid"));
	String addtime = request.getParameter("addtime");
	int paytypeid = Integer.parseInt(request.getParameter("paytypeid"));
	int money = Integer.parseInt(request.getParameter("money"));
	int inout = Integer.parseInt(request.getParameter("inout"));
	String project = request.getParameter("project");
	String message = request.getParameter("message");
	//添加数据
	PayControl paycontrol= new PayControl();
	paycontrol.setClassid(classid);
	paycontrol.setPayerid(payerid);
	paycontrol.setPaytypeid(paytypeid);
	paycontrol.setMessage(message);
	paycontrol.setMoney(money);
	paycontrol.setAddtime(addtime);
	paycontrol.setInout(inout);
	paycontrol.setProject(project);
	paycontrol.addpay();
    %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="0">
<meta HTTP-EQUIV=REFRESH CONTENT='4; URL=pay_manage.jsp'>
<title>财务管理系统</title>
<link rel="stylesheet" href="images/css.css" type="text/css" media="screen">
</head>
<body topmargin=0>
<TABLE width=400 border="0" align=center cellpadding=4 cellspacing=1 class="tableBorder">
	<TR>
	  <Th align=center class="whitetitle"><strong>费用添加成功</strong></Th>
	</TR>
	<TR><TD align=center><BR>
	  本页面将在<b><span id=yu>3</span><a href=javascript:countDown></a></b>秒后自动返回费用管理页面，您可以选择以下操作：<BR>
	  <BR>
	<li><a href="main.jsp">返回首页</a><br>
	</li>
	<br>
	<li><a href='pay_add.jsp?inout=<%=inout%>'>继续添加费用</a><br>
	</li>
	<br>
	<li><a href="pay_manage.jsp">返回费用管理页面</a><br>
	  <br>
	</li>
	</TD></TR>
</TABLE>

	<script>
	function 
	countDown(secs){yu.innerText=secs;if(--secs>0)setTimeout("countDown("+secs+")",1000);}countDown(3);
	</script>
	<%
	}
	catch(Exception e){
	out.println("错误信息:"+e.getMessage());
	}%>