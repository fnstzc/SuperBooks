<%@ page contentType="text/html;charset=gb2312" %>
<%@ include file="incdb.jsp"%>
<%@ include file="sessionadmin.jsp"%>
<%try{
	int id = Integer.parseInt(request.getParameter("id"));
    int classid = Integer.parseInt(request.getParameter("classid"));
	int payerid = Integer.parseInt(request.getParameter("payerid"));
	String addtime = request.getParameter("addtime");
	int paytypeid = Integer.parseInt(request.getParameter("paytypeid"));
	int money = Integer.parseInt(request.getParameter("money"));
	String project = request.getParameter("project");
	String message = request.getParameter("message");
	PayControl paycontrol= new PayControl();
	paycontrol.setId(id);
	paycontrol.setClassid(classid);
	paycontrol.setPayerid(payerid);
	paycontrol.setPaytypeid(paytypeid);
	paycontrol.setMessage(message);
	paycontrol.setMoney(money);
	paycontrol.setAddtime(addtime);
	paycontrol.setProject(project);
	paycontrol.editpay();%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="0">
<meta HTTP-EQUIV=REFRESH CONTENT='4; URL=pay_manage.jsp'>
<title>�������ϵͳ</title>
<link rel="stylesheet" href="images/css.css" type="text/css" media="screen">
</head>
<body topmargin=0>

<TABLE width=400 border="0" align=center cellpadding=4 cellspacing=1 class="tableBorder">
	<TR height=25>
	  <Th align=center><B class="whitetitle">���ü�¼�༭�ɹ�</B></Th>
	</TR>
	<TR><TD align=center><BR>
	  ��ҳ�潫��<b><span id=yu>3</span><a href=javascript:countDown></a></b>����Զ����ط��ü�¼����ҳ�棬������ѡ�����²�����<BR>
	  <BR>
	<li><a href="main.jsp">������ҳ</a><br>
	</li>
	<br>
	<li><a href='pay_edit.jsp?id=<%=id%>'>�����༭���ü�¼</a><br>
	</li>
	<br>
	<li><a href="pay_manage.jsp">���ط��ü�¼����ҳ��</a><br>
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
	out.println("������Ϣ:"+e.getMessage());
	}%>