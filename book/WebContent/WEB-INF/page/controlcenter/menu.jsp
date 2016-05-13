<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/page/share/taglib.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<base target="mainframe">
<Script language="javaScript">
function getTablesByStart(name){
	var inputs = document.getElementsByTagName("table");
	var files = new Array();
	var y = 0;
	for (var i=0; i<inputs.length; i++) {		
	  if (inputs[i].id !=null && inputs[i].id.length>name.length && inputs[i].id.substring(0, name.length)==name){
		 files[y] = inputs[i];
		 y++;
	  }
	}
	return files;
}

function HideAll(){
	var tables = getTablesByStart("menu_");
    for (var i=0; i<tables.length; i++) {
		tables[i].style.display = "none";
		var id = tables[i].id.substring("menu_".length);
		var imgId = document.getElementById("Img"+ id);
		var imgId2 = document.getElementById("Img"+ id + "_0");
		if(imgId) imgId.src="/images/midclosedfolder.gif";
		if(imgId2) imgId2.src="/images/clsfld.gif";
	}
}

function turnit(id) {
	var menu = document.getElementById("menu_"+ id);
	var imgId = document.getElementById("Img"+ id);
	var imgId2 = document.getElementById("Img"+ id + "_0");
	if (menu.style.display=="none"){
		HideAll();
		menu.style.display = "";
		if(imgId) imgId.src="/images/midopenedfolder.gif";
		if(imgId2) imgId2.src="/images/openfld.gif";
	}else{
		menu.style.display = "none";
		if(imgId) imgId.src="/images/midclosedfolder.gif";
		if(imgId2) imgId2.src="/images/clsfld.gif";
	}
}
</Script>
<style type="text/css">
<!--
td {  font-size: 13px; color:#000000; font-weight: none}
a:active {  color:#FF6600; text-decoration: none}
a:hover {  color:#FF6600; text-decoration: none}
a:link {  color: #FF6600; text-decoration: none}
a:visited {  color:#FF6600; text-decoration: none}
-->
</style>
</head>
<body leftmargin="0" topmargin="0" bgcolor="#F1F1F1"><br>
<!-------------------------图书借阅管理START------------------------------->
<table border=0 width="98%" align="center" cellspacing="0" cellpadding="0">
  <tr> 
    <td colspan="3" language="JavaScript" onMouseUp="turnit('order')" style="CURSOR: hand"> 
      <img id="Imgorder" src="/images/midclosedfolder.gif" align="absMiddle" border="0" width="16"><img name="Imgorder_0" src="/images/clsfld.gif" align="absMiddle" border="0"> 
        <font face=宋体><b>图书借阅管理</b></font> 
    </td>
  </tr>
</table>
<table id="menu_order" border=0 width="98%" align="center" cellspacing="0" cellpadding="0" style="display:none">
  <tr> 
    <td width="50"><img src="/images/vertline.gif" border=0><img src="/images/lastnodeline.gif" align="absMiddle" border=0 width="16" height="22"><img src="/images/doctemp.gif" align="absMiddle" border="0" width="16" height="16"></td>
    <td width="123"><a href="<html:rewrite action='/control/book/borrow'/>?method=borrowBookUI">借阅图书</a> </td>
  </tr> 
  <tr> 
    <td width="50"><img src="/images/vertline.gif" border=0><img src="/images/lastnodeline.gif" align="absMiddle" border=0 width="16" height="22"><img src="/images/doctemp.gif" align="absMiddle" border="0" width="16" height="16"></td>
    <td width="123"> <a href="<html:rewrite action='/control/book/back'/>?method=backBookUI">归还图书</a> </td>
  </tr>
   <tr> 
    <td width="50"><img src="/images/vertline.gif" border=0><img src="/images/lastnodeline.gif" align="absMiddle" border=0 width="16" height="22"><img src="/images/doctemp.gif" align="absMiddle" border="0" width="16" height="16"></td>
    <td width="123"> <a href="<html:rewrite action='/control/book/back'/>?method=renewBookUI">图书续借</a> </td>
  </tr> 
   <tr> 
    <td width="50"><img src="/images/vertline.gif" border=0><img src="/images/lastnodeline.gif" align="absMiddle" border=0 width="16" height="22"><img src="/images/doctemp.gif" align="absMiddle" border="0" width="16" height="16"></td>
    <td width="123"> <a href="<html:rewrite action='/control/book/query'/>?method=queryborrowBookUI">借阅查询</a> </td>
  </tr> 
  </table>
<!-------------------------图书借阅管理END------------------------------->
<!-------------------------图书管理START------------------------------->
<table border=0 width="98%" align="center" cellspacing="0" cellpadding="0">
  <tr> 
    <td colspan="3" language="JavaScript" onMouseUp="turnit('Product')" style="CURSOR: hand"> 
      <img id="ImgProduct" src="/images/midclosedfolder.gif" align="absMiddle" border="0" width="16"><img name="ImgProduct_0" src="/images/clsfld.gif" align="absMiddle" border="0"> 
        <font face=宋体><b>图书管理</b></font> 
    </td>
  </tr>
</table>
<table id="menu_Product" border=0 width="98%" align="center" cellspacing="0" cellpadding="0" style="display:none">
  <tr> 
    <td width="50"><img src="/images/vertline.gif" border=0><img src="/images/lastnodeline.gif" align="absMiddle" border=0 width="16" height="22"><img src="/images/doctemp.gif" align="absMiddle" border="0" width="16" height="16"></td>
    <td width="123"> <a href="<html:rewrite action='/control/book/type/list'/>">图书类别管理</a> </td>
  </tr>
  <tr> 
    <td width="50"><img src="/images/vertline.gif" border=0><img src="/images/lastnodeline.gif" align="absMiddle" border=0 width="16" height="22"><img src="/images/doctemp.gif" align="absMiddle" border="0" width="16" height="16"></td>
    <td width="123"> <a href="<html:rewrite action='/control/shelf/list'/>">书架管理</a> </td>
  </tr>
  <tr> 
    <td width="50"><img src="/images/vertline.gif" border=0><img src="/images/lastnodeline.gif" align="absMiddle" border=0 width="16" height="22"><img src="/images/doctemp.gif" align="absMiddle" border="0" width="16" height="16"></td>
    <td width="123"> <a href="<html:rewrite action='/control/book/list'/>">图书管理</a> </td>
  </tr>
</table>
<!-------------------------图书管理END------------------------------->

<!-------------------------文件管理START------------------------------->
<table border=0 width="98%" align="center" cellspacing="0" cellpadding="0">
  <tr> 
    <td colspan="3" language="JavaScript" onMouseUp="turnit('file')" style="CURSOR: hand"> 
      <img id="Imgfile" src="/images/midclosedfolder.gif" align="absMiddle" border="0" width="16"><img name="Imgfile_0" src="/images/clsfld.gif" align="absMiddle" border="0"> 
        <font face=宋体><b>文件管理</b></font> 
    </td>
  </tr>
</table>
<table id="menu_file" border=0 width="98%" align="center" cellspacing="0" cellpadding="0" style="display:none">
  <tr> 
    <td width="50"><img src="/images/vertline.gif" border=0><img src="/images/lastnodeline.gif" align="absMiddle" border=0 width="16" height="22"><img src="/images/doctemp.gif" align="absMiddle" border="0" width="16" height="16"></td>
    <td width="123"> <a href="<html:rewrite action='/control/uploadfile/list'/>">上传文件管理</a> </td>
  </tr>
</table>
<!-------------------------文件管理END------------------------------->

<!-------------------------读者管理START------------------------------->
<table border=0 width="98%" align="center" cellspacing="0" cellpadding="0">
  <tr> 
    <td colspan="3" language="JavaScript" onMouseUp="turnit('user')" style="CURSOR: hand"> 
      <img id="Imguser" src="/images/midclosedfolder.gif" align="absMiddle" border="0" width="16"><img name="Imguser_0" src="/images/clsfld.gif" align="absMiddle" border="0"> 
        <font face=宋体><b>读者管理</b></font> 
    </td>
  </tr>
</table>
<table id="menu_user" border=0 width="98%" align="center" cellspacing="0" cellpadding="0" style="display:none">
  <tr> 
    <td width="50"><img src="/images/vertline.gif" border=0><img src="/images/lastnodeline.gif" align="absMiddle" border=0 width="16" height="22"><img src="/images/doctemp.gif" align="absMiddle" border="0" width="16" height="16"></td>
    <td width="123"> <a href="<html:rewrite action='/control/user/list'/>">读者管理</a> </td>
  </tr>
  <tr> 
    <td width="50"><img src="/images/vertline.gif" border=0><img src="/images/lastnodeline.gif" align="absMiddle" border=0 width="16" height="22"><img src="/images/doctemp.gif" align="absMiddle" border="0" width="16" height="16"></td>
    <td width="123"> <a href="<html:rewrite action="/control/user/query"/>">读者查询</a> </td>
  </tr>
</table>
<!-------------------------网站用户管理END------------------------------->

<!-------------------------部门员工管理START------------------------------->
<table border=0 width="98%" align="center" cellspacing="0" cellpadding="0">
  <tr> 
    <td colspan="3" language="JavaScript" onMouseUp="turnit('department')" style="CURSOR: hand"> 
      <img id="Imgdepartment" src="/images/midclosedfolder.gif" align="absMiddle" border="0" width="16"><img name="Imgdepartment_0" src="/images/clsfld.gif" align="absMiddle" border="0"> 
        <font face=宋体><b>管理员管理</b></font> 
    </td>
  </tr>
</table>
<table id="menu_department" border=0 width="98%" align="center" cellspacing="0" cellpadding="0" style="display:none">
  <tr> 
    <td width="50"><img src="/images/vertline.gif" border=0><img src="/images/lastnodeline.gif" align="absMiddle" border=0 width="16" height="22"><img src="/images/doctemp.gif" align="absMiddle" border="0" width="16" height="16"></td>
    <td width="123"> <a href="<html:rewrite action='/control/department/list'/>">部门管理</a> </td>
  </tr>
  <tr> 
    <td width="50"><img src="/images/vertline.gif" border=0><img src="/images/lastnodeline.gif" align="absMiddle" border=0 width="16" height="22"><img src="/images/doctemp.gif" align="absMiddle" border="0" width="16" height="16"></td>
    <td width="123"> <a href="<html:rewrite action='/control/employee/list'/>">员工管理</a> </td>
  </tr>
  <tr> 
    <td width="50"><img src="/images/vertline.gif" border=0><img src="/images/lastnodeline.gif" align="absMiddle" border=0 width="16" height="22"><img src="/images/doctemp.gif" align="absMiddle" border="0" width="16" height="16"></td>
    <td width="123"> <a href="<html:rewrite action='/control/employee/manage'/>?method=query">员工查询</a> </td>
  </tr>  
  <tr> 
    <td width="50"><img src="/images/vertline.gif" border=0><img src="/images/lastnodeline.gif" align="absMiddle" border=0 width="16" height="22"><img src="/images/doctemp.gif" align="absMiddle" border="0" width="16" height="16"></td>
    <td width="123"> <a href="<html:rewrite action='/control/privilegegroup/list'/>">权限组管理</a> </td>
  </tr>  
  
</table>
<!-------------------------部门员工管理END------------------------------->
<table border="0" width="98%" align="center" cellspacing="0" cellpadding="0">
  <tr> 
    <td width="20"><img src="/images/lastnodeline.gif" border="0"></td>
    <td>
      <a href="<html:rewrite action='/employee/logout'/>" target="_parent">退出系统</a>
    </td>
  </tr>
</table>
</body>
</html>
