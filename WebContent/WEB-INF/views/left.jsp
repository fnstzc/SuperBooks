<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<title>家庭财务管理系统</title>
<STYLE type=text/css>
BODY {
	SCROLLBAR-FACE-COLOR: #799ae1;
	BACKGROUND: #799ae1;
	MARGIN: 0px;
	FONT: 12px 宋体;
	SCROLLBAR-HIGHLIGHT-COLOR: #799ae1;
	SCROLLBAR-SHADOW-COLOR: #799ae1;
	SCROLLBAR-3DLIGHT-COLOR: #799ae1;
	SCROLLBAR-ARROW-COLOR: #ffffff;
	SCROLLBAR-TRACK-COLOR: #aabfec;
	SCROLLBAR-DARKSHADOW-COLOR: #799ae1
}

TABLE {
	BORDER-RIGHT: 0px;
	BORDER-TOP: 0px;
	BORDER-LEFT: 0px;
	BORDER-BOTTOM: 0px
}

TD {
	FONT: 12px 宋体
}

IMG {
	BORDER-RIGHT: 0px;
	BORDER-TOP: 0px;
	VERTICAL-ALIGN: bottom;
	BORDER-LEFT: 0px;
	BORDER-BOTTOM: 0px
}

A {
	FONT: 12px 宋体;
	COLOR: #000000;
	TEXT-DECORATION: none
}

A:hover {
	COLOR: #428eff;
	TEXT-DECORATION: none
}

.sec_menu {
	BORDER-RIGHT: white 1px solid;
	BACKGROUND: #d6dff7;
	OVERFLOW: hidden;
	BORDER-LEFT: white 1px solid;
	BORDER-BOTTOM: white 1px solid
}

.menu_title {
	
}

.menu_title SPAN {
	FONT-WEIGHT: bold;
	LEFT: 8px;
	COLOR: #215dc6;
	POSITION: relative;
	TOP: 2px
}

.menu_title2 {
	
}

.menu_title2 SPAN {
	FONT-WEIGHT: bold;
	LEFT: 8px;
	COLOR: #428eff;
	POSITION: relative;
	TOP: 2px
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

<META content="MSHTML 6.00.2900.2135" name=GENERATOR>
</HEAD>
<BODY leftMargin=0 topMargin=0 marginheight="0" marginwidth="0">
	<TABLE cellSpacing=0 cellPadding=0 width="100%" align=left border=0>
		<TBODY>
			<TR>
				<TD vAlign=top>
					<TABLE cellSpacing=0 cellPadding=0 width=158 align=center>
						<TBODY>
							<TR>
								<TD vAlign=bottom height=42><IMG height=38
									src="images/title.gif" width=158></TD>
							</TR>
						</TBODY>
					</TABLE>
					<TABLE cellSpacing=0 cellPadding=0 width=158 align=center>
						<TBODY>
							<TR>
								<TD class=menu_title onmouseover="this.className='menu_title2';"
									onmouseout="this.className='menu_title';"
									background=images/title_bg_quit.gif height=25><SPAN><A
										href="main.jsp" target=main><B>管理首页</B></A> | <A
										href="logout.jsp" target=_top><B>退出</B></A></SPAN></TD>
							</TR>
						</TBODY>
					</TABLE>&nbsp;
					<TABLE cellSpacing=0 cellPadding=0 width=158 align=center>
						<TBODY>
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
											<TBODY>
												<TR>
													<TD height=5></TD>
												</TR>
												<TR>
													<TD height=20><IMG height=20 alt=""
														src="images/bullet.gif" width=15 border=0><a
														href="pay_manage.jsp" target=main>个人资金</a></TD>
												</TR>
												<TR>
													<TD height=20><IMG height=20 alt=""
														src="images/bullet.gif" width=15 border=0><a
														href="pay_add.jsp?inout=1" target=main>新增支出</a></TD>
												</TR>
												<TR>
													<TD height=20><IMG height=20 alt=""
														src="images/bullet.gif" width=15 border=0><a
														href="pay_add.jsp?inout=-1" target=main>新增收入</a></TD>
												</TR>
												<TR>
													<TD height=20><IMG height=20 alt=""
														src="images/bullet.gif" width=15 border=0><a
														href="paytype_manage.jsp" target=main></a>资金分布</TD>
												</TR>
												<TR>
													<TD height=20><IMG height=20 alt=""
														src="images/bullet.gif" width=15 border=0><A
														href="paytype_add.jsp" target=main></A>家庭总资产</TD>
												</TR>
										</TABLE>
									</DIV>
									<DIV style="WIDTH: 158px">
										<TABLE cellSpacing=0 cellPadding=0 width=135 align=center>
											<TBODY>
												<TR>
													<TD height=20></TD>
												</TR>
											</TBODY>
										</TABLE>
									</DIV>
								</TD>
							</TR>
						</TBODY>
					</TABLE>
					<TABLE cellSpacing=0 cellPadding=0 width=158 align=center>
						<TBODY>
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
											<TBODY>
												<TR>
													<TD height=5></TD>
												</TR>
												<TR>
													<TD height=20><IMG height=20 alt=""
														src="images/bullet.gif" width=15 border=0><A
														href="pay_count.jsp" target=main>费用统计</A></TD>
												</TR>
											<TBODY>
											</TBODY>
										</TABLE>
									</DIV>
									<DIV style="WIDTH: 158px">
										<TABLE cellSpacing=0 cellPadding=0 width=135 align=center>
											<TBODY>
												<TR>
													<TD height=20></TD>
												</TR>
											</TBODY>
										</TABLE>
									</DIV>
								</TD>
							</TR>
						</TBODY>
					</TABLE>
					<TABLE cellSpacing=0 cellPadding=0 width=158 align=center>
						<TBODY>
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
											<TBODY>
												<TR>
													<TD height=5></TD>
												</TR>
												<TR>
													<TD height=20><IMG height=20 alt=""
														src="images/bullet.gif" width=15 border=0><A
														href="user_manage.jsp" target=main>成员信息</A></TD>
												</TR>
												<TR>
													<TD height=20><IMG height=20 alt=""
														src="images/bullet.gif" width=15 border=0><A
														href="user_add.jsp" target=main>添加成员</A></TD>
												</TR>
											<TBODY>
											</TBODY>
										</TABLE>
									</DIV>
									<DIV style="WIDTH: 158px">
										<TABLE cellSpacing=0 cellPadding=0 width=135 align=center>
											<TBODY>
												<TR>
													<TD height=20></TD>
												</TR>
											</TBODY>
										</TABLE>
									</DIV>
								</TD>
							</TR>
						</TBODY>
					</TABLE>
					<TABLE cellSpacing=0 cellPadding=0 width=158 align=center>
						<TBODY>
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
											<TBODY>
												<TR>
													<TD height=5></TD>
												</TR>
												<TR>
													<TD height=20><IMG height=20 alt=""
														src="images/bullet.gif" width=15 border=0><A
														href="class_manage.jsp" target=main>账目明细</A></TD>
												</TR>
												<TR>
													<TD height=20><IMG height=20 alt=""
														src="images/bullet.gif" width=15 border=0><A
														href="class_manage.jsp" target=main>支出分析</A></TD>
												</TR>
												<TR>
													<TD height=20><IMG height=20 alt=""
														src="images/bullet.gif" width=15 border=0><A
														href="class_add.jsp" target=main>收入分析</A></TD>
												</TR>
											<TBODY>
											</TBODY>
										</TABLE>
									</DIV>
									<DIV style="WIDTH: 158px">
										<TABLE cellSpacing=0 cellPadding=0 width=135 align=center>
											<TBODY>
												<TR>
													<TD height=20></TD>
												</TR>
											</TBODY>
										</TABLE>
									</DIV></TD>
							</TR>
						</TBODY>
					</TABLE>
				</TD>
			</TR>
		</TBODY>
	</TABLE>
</BODY>
</HTML>


