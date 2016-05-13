<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/page/share/taglib.jsp" %>
<html>
<head>
<title>修改图书</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/css/vip.css" type="text/css">
<SCRIPT language=JavaScript src="/js/FoshanRen.js"></SCRIPT>
<script type="text/javascript" src="/js/jscripts/tiny_mce/tiny_mce.js"></script>
<script language="javascript" type="text/javascript">
function SureSubmit(objForm){
	 objForm.submit();
} 
</script>
</head>

<body bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<html:form action="/control/user/manage"  method="post">
<input type="hidden" name="method" value="add">
<table width="98%" border="0" cellspacing="1" cellpadding="3" align="center">
    <tr bgcolor="6f8ac4"> 
      <td width="40%" ><font color="#FFFFFF">添加读者：</font></td>
      <td width="60%"> ${message}</td> 
    </tr>
    <tr bgcolor="f5f5f5"> 
      <td width="40%"> <div align="right">借阅卡号  ：</div></td>
      <td width="60%"> <html:text property="librarycard" size="35" maxlength="30" value="${user.librarycard }"/><font color="#FF0000">*</font></td>
    </tr>
    <tr bgcolor="f5f5f5"> 
      <td width="40%"> <div align="right">读者姓名 ：</div></td>
      <td width="60%"> <html:text property="username" size="35" maxlength="30" value="${user.username}"/><font color="#FF0000">*</font></td>
    </tr>
    <tr bgcolor="f5f5f5"> 
      <td width="40%"> <div align="right">性别 ：</div></td>
      <td width="60%"><html:select property="gender">   
			<html:option value="MAN">男</html:option>   
			<html:option value="WOMEN">女</html:option>
		</html:select>
		</td>
    </tr>
    <tr bgcolor="f5f5f5"> 
      <td colspan="2"> <div align="center"> 
          <input type="button" name="Add" value=" 确 认 " class="frm_btn" onClick="javascript:SureSubmit(this.form)">
          &nbsp;&nbsp;<input type="button" name="Button" value=" 返 回 " class="frm_btn" onclick="javascript:history.back()">
        </div></td>
    </tr>
  </table>
</html:form>
<br>
</body>
</html>