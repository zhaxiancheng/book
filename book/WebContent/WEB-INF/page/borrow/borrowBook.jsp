<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/page/share/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>图书借阅</title>
<SCRIPT language=JavaScript src="/js/FoshanRen.js"></SCRIPT>
<link rel="stylesheet" href="/css/book/borrow.css" type="text/css">

<SCRIPT LANGUAGE="JavaScript">
<!--
function actionEvent(methodName ,query){
	var form = document.forms[0];
	form.action='<html:rewrite action="/control/book/borrow"/>?method='+methodName+'&query='+query;
	form.submit();
	
}
//-->
</SCRIPT>
</head>
<body><br>
<html:form action="/control/book/borrow" method="post">
<table width="90%" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#333333">
  <tr><td width="100%" bgcolor="6f8ac4" height="40"> <div align="center"><font color="#FFFFFF">读者信息</font></div></td>
   </tr>
  <tr><td width="100%" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">${message}</font></div></td>
   </tr>
  <tr>
    <td>
    <table width="100%" height="26" border="0" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
    <tr style="height: 50px; background:#FFFFCC;">
        <td width="20%"></td>
        <td width="40%">借阅卡号:<html:text property="librarycard"  size="50" maxlength="40" /></td>
        <td width="38%" ><input type="button" value="查询读者" onclick="JavaScript:actionEvent('findUser','')"/>&nbsp;</td>
    </tr>
    </table>
    <table width="100%" border="0" align="center" cellpadding="3" cellspacing="2">
        <tr>
          <td colspan="6" bgcolor="#FFFFFF" align="center"><strong>读者信息 </strong></td>
        </tr>
        <tr>
          <td width="13%" align="center" bgcolor="#FFFFFF">读者姓名</td>
          <td width="24%" bgcolor="#FFFFFF">${user.username}</td>
          <td width="12%" align="center" bgcolor="#FFFFFF">借阅卡号</td>
          <td width="18%" bgcolor="#FFFFFF">${user.librarycard}</td>
          <td width="12%" align="center" bgcolor="#FFFFFF">联系电话</td>
          <td colspan="2" bgcolor="#FFFFFF">${user.contactInfo.mobile}</td>
        </tr>
        <tr>
          <td align="center" bgcolor="#FFFFFF">欠费记录</td>
          <td colspan="3" bgcolor="#FFFFFF">${user.fine}</td>
          <td align="center" bgcolor="#FFFFFF">借书数量</td>
          <td colspan="2" bgcolor="#FFFFFF">${user.totalamount}</td>
        </tr>
        <tr>
          <td align="center" bgcolor="#FFFFFF">其他要求</td>
          <td colspan="6" bgcolor="#FFFFFF">无</td>
        </tr>
        </table>
        <table width="100%" border="0" align="center" cellpadding="2" cellspacing="7"  bgcolor="#FFFFFf">
          <tr>
            <td colspan="5"  bgcolor="6f8ac4" align="center" height="38"><FONT COLOR="#FFFFFF" >图书搜索</FONT> &nbsp; </td>
          </tr>
         <tr style="height: 50px; background:#FFFFCC;">
          <td width="10%"></td>
          <td width="70%">图书条形码:<html:text property="barcode" size="35" maxlength="30"/>&nbsp;&nbsp;图书名称:<html:text property="name" size="35" maxlength="30"/>&nbsp;&nbsp;<input type="button" value="查找图书" onclick="JavaScript:actionEvent('findBook','query')"/></td>
          <td width="10%"></td>
         </tr>
        </table>
        <table width="100%" border="0" cellspacing="1" cellpadding="2" align="center" bgcolor="#FFFFFF">
       <tr ><td colspan="10"  bgcolor="6f8ac4" align="right"></td></tr>
       <tr>
	      <td width="8%" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">图书ID</font></div></td>
	      <td width="12%" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">条形码</font></div></td>
	      <td width="26%" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">图书名称</font></div></td>
		  <td width="12%" nowrap bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">作者</font></div></td>
		  <td width="12%" bgcolor="6f8ac4"><div align="center"><font color="#FFFFFF">出版社</font></div></td>
		  <td width="8%" bgcolor="6f8ac4"><div align="center"><font color="#FFFFFF">版次</font></div></td>
		  <td width="6%" bgcolor="6f8ac4"><div align="center"><font color="#FFFFFF">图书类型</font></div></td>
		  <td width="6%" bgcolor="6f8ac4"><div align="center"><font color="#FFFFFF">图书总量</font></div></td>
		  <td width="10%" bgcolor="6f8ac4"><div align="center"><font color="#FFFFFF">借出</font></div></td>
       </tr>
<!---------------------------LOOP START------------------------------>
      <c:forEach items="${bookInfo.styles}" var="style">
			<c:if test="${style.visible}">
				<c:set var="currentbook" value="${style}" />
			</c:if>
	</c:forEach>
    <tr>
      <input type="hidden" name="bookid" value="${bookInfo.bookid }">
      <td bgcolor="f5f5f5"> <div align="center">${bookInfo.bookid }</div></td>
      <td bgcolor="f5f5f5"> <div align="center">${bookInfo.barcode }</div></td>
      <td bgcolor="f5f5f5"> <div align="center">${bookInfo.name }</div></td>
	  <td bgcolor="f5f5f5"> <div align="center">${bookInfo.author}</div></td>
	  <td bgcolor="f5f5f5"> <div align="center">${currentbook.ISBN}</div></td>
	  <td bgcolor="f5f5f5"> <div align="center">${currentbook.revision }</div></td>
	  <td bgcolor="f5f5f5"> <div align="center">${bookInfo.type.name}</div></td>
	  <td bgcolor="f5f5f5"> <div align="center">${bookInfo.sumcount}</div></td>
	  <td bgcolor="f5f5f5"> <div align="center"><input type="button" value="借出" onclick="JavaScript:actionEvent('borrowBook','')"/></div></td>
	</tr>

    </table></td>
    </tr>
  </table>
        </td>
  </tr>
</table>
</html:form>
</body>
</html>