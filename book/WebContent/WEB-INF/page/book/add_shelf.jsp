<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/page/share/taglib.jsp" %>
<html>
<head>
<title>添加书架</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/css/vip.css" type="text/css">
<SCRIPT language=JavaScript src="/js/FoshanRen.js"></SCRIPT>
<script language="JavaScript">
function checkfm(form){
	if (trim(form.name.value)==""){
		alert("书架名称不能为空！");
		form.name.focus();
		return false;
	}
	return true;
}
</script>
</head>
<body bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<html:form action="/control/shelf/manage" method="post" enctype="multipart/form-data" onsubmit="return checkfm(this)">
<input type="hidden" name="method" value="add">
<html:hidden property="typeid"/>
  <table width="90%" border="0" cellspacing="2" cellpadding="3" align="center">
    <tr bgcolor="6f8ac4"><td colspan="2"  > <font color="#FFFFFF">添加书架：</font></td>
    </tr>
    <tr bgcolor="f5f5f5"> 
      <td width="22%" > <div align="right">书架名称：</div></td>
      <td width="78%"> <html:text property="name" size="50" maxlength="40"/>
        <font color="#FF0000">*</font></td>
    </tr>
    <tr bgcolor="f5f5f5"> 
      <td width="22%" > <div align="right">书架位置：</div></td>
      <td width="78%"> <html:text property="location" size="50" maxlength="40"/>
        <font color="#FF0000">*</font></td>
    </tr>
     <tr bgcolor="f5f5f5"> 
      <td width="22%"> <div align="right">图书类别：</div></td>
      <td width="78%"> <input type="text" name="v_type_name" disabled="true" size="30"/> 
        <input type="button" name="select" value="选择..." onClick="javaScript:winOpen('<html:rewrite action="/control/book/manage"/>?method=selectUI','列表',600,400)">(<a href="<html:rewrite action='/control/book/type/manage'/>?method=addUI">添加图书类别</a>)
      </td>
    </tr>
	
    <tr bgcolor="f5f5f5"> 
      <td colspan="2"> <div align="center"> 
          <input type="submit" name="SYS_SET" value=" 确 定 " class="frm_btn">
        </div></td>
    </tr>
  </table>
</html:form>
<br>
</body>
</html>