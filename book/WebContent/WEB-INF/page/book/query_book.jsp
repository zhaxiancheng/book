<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/page/share/taglib.jsp" %>
<html>
<head>
<title>图书查询</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/css/vip.css" type="text/css">
<SCRIPT language=JavaScript src="/js/FoshanRen.js"></SCRIPT>
</head>

<body bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<html:form action="/control/book/list" method="post">
<html:hidden property="typeid"/>
<input name="query" value="true" type="hidden">
  <table width="98%" border="0" cellspacing="1" cellpadding="3" align="center">
    <tr bgcolor="6f8ac4"> 
      <td colspan="2" ><font color="#FFFFFF">查询图书：</font></td>
    </tr>
    <tr bgcolor="f5f5f5"> 
      <td width="25%"> <div align="right">图书名称  ：</div></td>
      <td width="75%"> <html:text property="name" size="50" maxlength="40"/></td>
    </tr>
    <tr bgcolor="f5f5f5"> 
      <td width="25%"> <div align="right">图书类别  ：</div></td>
      <td width="75%"> <input type="text" name="v_type_name" disabled="true" size="30" value="${typename}"/> 
        <input type="button" name="select" value="选择..." onClick="javaScript:winOpen('<html:rewrite action="/control/book/manage"/>?method=selectUI','列表',600,400)">
      </td>
    </tr>
	<tr bgcolor="f5f5f5"> 
      <td width="25%"> <div align="right">图书价格 ：</div></td>
      <td width="75%"><html:text property="price" size="20" maxlength="30"/></td>
    </tr>
    <tr bgcolor="f5f5f5"> 
      <td width="25%"> <div align="right">图书条形码 ：</div></td>
      <td width="75%"> <html:text property="barcode" size="20" maxlength="30"/></td>
    </tr>
	<tr bgcolor="f5f5f5"> 
      <td width="25%"> <div align="right">书架 ：</div></td>
      <td width="75%"> <html:select property="shelfid">
          <html:option value="">***无***</html:option>
          <html:optionsCollection name="shelfids" label="name" value="shelfid"/>
        </html:select></td>
    </tr>
    <tr bgcolor="f5f5f5"> 
      <td colspan="2"> <div align="center"> 
          <input type="submit" name="edit" value=" 确 认 " class="frm_btn">
          &nbsp;&nbsp;<input type="button" name="Button" value=" 返 回 " class="frm_btn" onclick="javascript:history.back()">
        </div></td>
    </tr>
  </table>
</html:form>
<br>
</body>
</html>