<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<STYLE type=text/css>
BODY {
	SCROLLBAR-FACE-COLOR: #799ae1; BACKGROUND: #799ae1; MARGIN: 0px; FONT: 12px 宋体; SCROLLBAR-HIGHLIGHT-COLOR: #799ae1; SCROLLBAR-SHADOW-COLOR: #799ae1; SCROLLBAR-3DLIGHT-COLOR: #799ae1; SCROLLBAR-ARROW-COLOR: #ffffff; SCROLLBAR-TRACK-COLOR: #aabfec; SCROLLBAR-DARKSHADOW-COLOR: #799ae1
}
TABLE {
	BORDER-RIGHT: 0px; BORDER-TOP: 0px; BORDER-LEFT: 0px; BORDER-BOTTOM: 0px
}
TD {
	FONT: 12px 宋体
}
IMG {
	BORDER-RIGHT: 0px; BORDER-TOP: 0px; VERTICAL-ALIGN: bottom; BORDER-LEFT: 0px; BORDER-BOTTOM: 0px
}
A {
	FONT: 12px 宋体; COLOR: #000000; TEXT-DECORATION: none
}
A:hover {
	COLOR: #428eff; TEXT-DECORATION: none
}
.sec_menu {
	BORDER-RIGHT: white 1px solid; BACKGROUND: #d6dff7; OVERFLOW: hidden; BORDER-LEFT: white 1px solid; BORDER-BOTTOM: white 1px solid
}
.menu_title {
	
}
.menu_title SPAN {
	FONT-WEIGHT: bold; LEFT: 8px; COLOR: #215dc6; POSITION: relative; TOP: 2px
}
.menu_title2 {
	
}
.menu_title2 SPAN {
	FONT-WEIGHT: bold; LEFT: 8px; COLOR: #428eff; POSITION: relative; TOP: 2px
}
</STYLE>

<SCRIPT language=javascript1.2>
function showsubmenu(sid)
{
whichEl = eval("submenu" + sid);
if (whichEl.style.display == "none")
{
eval("submenu" + sid + ".style.display=\"\";");
}
else
{
eval("submenu" + sid + ".style.display=\"none\";");
}
}
</SCRIPT>

</head>
<title>家庭财务管理系统</title>
<body>
	<table>
		<tr>
			<td>
				<TABLE cellSpacing=0 cellPadding=0 width="100%" align=left border=0>
					<TR>
						<TD vAlign=top>
							<TABLE cellSpacing=0 cellPadding=0 width=158 align=center>
								<TR>
									<TD vAlign=bottom height=42><IMG height=38
										src="images/title.gif" width=158></TD>
								</TR>
							</TABLE>
							<TABLE cellSpacing=0 cellPadding=0 width=158 align=center>
								<TR>
									<TD class=menu_title
										onmouseover="this.className='menu_title2';"
										onmouseout="this.className='menu_title';"
										background=images/title_bg_quit.gif height=25><SPAN><A
											href="main.jsp"><B>管理首页</B></A> | <A href="logout.jsp"
											target=_top><B>退出</B></A></SPAN></TD>
								</TR>
							</TABLE>&nbsp;
							<TABLE cellSpacing=0 cellPadding=0 width=158 align=center>
								<TR>
									<TD class=menu_title id=menuTitle1
										onmouseover="this.className='menu_title2';"
										style="CURSOR: hand" onclick=showsubmenu(0)
										onmouseout="this.className='menu_title';"
										background=images/admin_left_1.gif height=25><SPAN>资金管理</SPAN>
									</TD>
								</TR>
								<TR>
									<TD id=submenu0 style="DISPLAY: none">
										<DIV class=sec_menu style="WIDTH: 158px">
											<TABLE cellSpacing=0 cellPadding=0 width=150 align=center>
												<TR>
													<TD height=5></TD>
												</TR>
												<TR>
													<TD height=20><IMG height=20 alt=""
														src="images/bullet.gif" width=15 border=0><a
														href="pay_manage.jsp">个人资金</a></TD>
												</TR>
												<TR>
													<TD height=20><IMG height=20 alt=""
														src="images/bullet.gif" width=15 border=0><a
														href="pay_add.jsp">新增支出</a></TD>
												</TR>
												<TR>
													<TD height=20><IMG height=20 alt=""
														src="images/bullet.gif" width=15 border=0><a
														href="pay_add.jsp">新增收入</a></TD>
												</TR>
												<TR>
													<TD height=20><IMG height=20 alt=""
														src="images/bullet.gif" width=15 border=0><a
														href="paytype_manage.jsp"></a>资金分布</TD>
												</TR>
												<TR>
													<TD height=20><IMG height=20 alt=""
														src="images/bullet.gif" width=15 border=0><A
														href="paytype_add.jsp"></A>家庭总资产</TD>
												</TR>
											</TABLE>
										</DIV>
										<DIV style="WIDTH: 158px">
											<TABLE cellSpacing=0 cellPadding=0 width=135 align=center>
												<TR>
													<TD height=20></TD>
												</TR>
											</TABLE>
										</DIV>
									</TD>
								</TR>
							</TABLE>
							<TABLE cellSpacing=0 cellPadding=0 width=158 align=center>
								<TR>
									<TD class=menu_title id=menuTitle1
										onmouseover="this.className='menu_title2';"
										style="CURSOR: hand" onclick=showsubmenu(1)
										onmouseout="this.className='menu_title';"
										background=images/admin_left_2.gif height=25><SPAN>费用统计</SPAN>
									</TD>
								</TR>
								<TR>
									<TD id=submenu1 style="DISPLAY: none">
										<DIV class=sec_menu style="WIDTH: 158px">
											<TABLE cellSpacing=0 cellPadding=0 width=150 align=center>
												<TR>
													<TD height=5></TD>
												</TR>
												<TR>
													<TD height=20><IMG height=20 alt=""
														src="images/bullet.gif" width=15 border=0><A
														href="pay_count.jsp">费用统计</A></TD>
												</TR>
											</TABLE>
										</DIV>
										<DIV style="WIDTH: 158px">
											<TABLE cellSpacing=0 cellPadding=0 width=135 align=center>
												<TR>
													<TD height=20></TD>
												</TR>
											</TABLE>
										</DIV>
									</TD>
								</TR>
							</TABLE>
							<TABLE cellSpacing=0 cellPadding=0 width=158 align=center>
								<TR>
									<TD class=menu_title id=menuTitle1
										onmouseover="this.className='menu_title2';"
										style="CURSOR: hand" onclick=showsubmenu(2)
										onmouseout="this.className='menu_title';"
										background=images/admin_left_3.gif height=25><SPAN>成员管理</SPAN>
									</TD>
								</TR>
								<TR>
									<TD id=submenu2 style="DISPLAY: none">
										<DIV class=sec_menu style="WIDTH: 158px">
											<TABLE cellSpacing=0 cellPadding=0 width=150 align=center>
												<TR>
													<TD height=5></TD>
												</TR>
												<TR>
													<TD height=20><IMG height=20 alt=""
														src="images/bullet.gif" width=15 border=0><A
														href="user_manage.jsp">成员信息</A></TD>
												</TR>
												<TR>
													<TD height=20><IMG height=20 alt=""
														src="images/bullet.gif" width=15 border=0><A
														href="user_add.jsp">添加成员</A></TD>
												</TR>
											</TABLE>
										</DIV>
										<DIV style="WIDTH: 158px">
											<TABLE cellSpacing=0 cellPadding=0 width=135 align=center>
												<TR>
													<TD height=20></TD>
												</TR>
											</TABLE>
										</DIV>
									</TD>
								</TR>
							</TABLE>
							<TABLE cellSpacing=0 cellPadding=0 width=158 align=center>
								<TR>
									<TD class=menu_title id=menuTitle1
										onmouseover="this.className='menu_title2';"
										style="CURSOR: hand" onclick=showsubmenu(3)
										onmouseout="this.className='menu_title';"
										background=images/admin_left_5.gif height=25><SPAN>账目统计</SPAN>
									</TD>
								</TR>
								<TR>
									<TD id=submenu3 style="DISPLAY: none"><DIV class=sec_menu
											style="WIDTH: 158px">
											<TABLE cellSpacing=0 cellPadding=0 width=150 align=center>
												<TR>
													<TD height=5></TD>
												</TR>
												<TR>
													<TD height=20><IMG height=20 alt=""
														src="images/bullet.gif" width=15 border=0><A
														href="class_manage.jsp">账目明细</A></TD>
												</TR>
												<TR>
													<TD height=20><IMG height=20 alt=""
														src="images/bullet.gif" width=15 border=0><A
														href="class_manage.jsp">支出分析</A></TD>
												</TR>
												<TR>
													<TD height=20><IMG height=20 alt=""
														src="images/bullet.gif" width=15 border=0><A
														href="class_add.jsp">收入分析</A></TD>
												</TR>
											</TABLE>
										</DIV>
										<DIV style="WIDTH: 158px">
											<TABLE cellSpacing=0 cellPadding=0 width=135 align=center>
												<TR>
													<TD height=20></TD>
												</TR>
											</TABLE>
										</DIV></TD>
								</TR>
							</TABLE>
						</TD>
					</TR>
				</TABLE>
			</td>
			<td>
				<table>
					<tr><td>姓名</td><td>身份</td><td>日期</td><td>消费地点</td><td>消费方式</td><td>消费金额</td></tr>
					
				</table>
			</td>
		</tr>
	</table>
</body>
</html>
