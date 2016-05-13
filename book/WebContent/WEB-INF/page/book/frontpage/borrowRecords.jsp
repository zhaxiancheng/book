<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/page/share/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<HEAD>
<TITLE>我的借阅记录</TITLE>
<META http-equiv=Content-Type content="text/html; charset=UTF-8">
<META http-equiv=Content-Language content=zh-CN>
<LINK href="/css/new_cart.css" rel="stylesheet" type="text/css">
<link href="/css/global/header01.css" rel="stylesheet" type="text/css">
<SCRIPT language=JavaScript src="/js/FoshanRen.js"></SCRIPT>
</HEAD>
<BODY>
<jsp:include page="/WEB-INF/page/share/Head.jsp"/>
<BR>
<TABLE cellSpacing=0 cellPadding=5 width="98%" border="0" align="center">
  <TR>
    <TD><TABLE cellSpacing=0 cellPadding=0 width="96%" border=0>
      <TBODY>
        <TR>
          <TD width="24%"><IMG height=31 src="/images/buy/shop-cart-header-blue.gif" width="218" border=0></TD>
        </TR>
      </TBODY>
    </TABLE></TD>
  </TR>

  <TR>
    <TD><FORM id="buycart" name="buycart" action="<html:rewrite action="/shopping/cart/manage" />" method="post">
    <INPUT TYPE="hidden" NAME="method" value="">
    <TABLE cellSpacing=0 cellPadding=6 width="100%" border=0> 
      <TR bgColor=#d7ebff>
        <TD width="66"><STRONG>图书条形码</STRONG></TD>
        <TD width=66><DIV align=center><STRONG>名称</STRONG></DIV></TD>
        <TD width=66><DIV align=center><STRONG>作者</STRONG></DIV></TD>
        <TD width=66><DIV align=center><STRONG>出版社</STRONG></DIV></TD>
        <TD width=66><DIV align=center><STRONG>版次</STRONG></DIV></TD>
        <TD width=66><DIV align=center><STRONG>借阅时间</STRONG></DIV></TD>
        <TD width=66><DIV align=center><STRONG>应归还时间</STRONG></DIV></TD>
        <TD width=66><DIV align=center><STRONG>借阅状态</STRONG></DIV></TD>
         <TD width=66><DIV align=center><STRONG>操作</STRONG></DIV></TD>
      </TR>
<!-- loop begin -->
   <c:if test="${empty user.username}">
   <tr><td colSpan="5" align="right" height="42"> <font size="12" color="red">${message}</font></td><td align="right" ><a href="/user/logon.do" >读者登录</a></td></tr>
   </c:if>
    <c:forEach items="${books}" var="borrow"> 
      <c:forEach items="${borrow.book.styles}" var="style">
			<c:if test="${style.visible}">
				<c:set var="currentbook" value="${style}" />
			</c:if>
	 </c:forEach>
       <TR vAlign="top">
        <TD width="66">${borrow.book.barcode}</TD>
        <TD width=66><DIV align=center>${borrow.book.name}</DIV></TD>
        <TD width=66><DIV align=center>${borrow.book.author}</DIV></TD>
        <TD width=66><DIV align=center>${currentbook.ISBN }</DIV></TD>
        <TD width=66><DIV align=center>${currentbook.revision}</DIV></TD>
        <TD width=66><DIV align=center>${borrow.borrowtime}</DIV></TD>
        <TD width=66><DIV align=center>${borrow.backTime}</DIV></TD>
        <TD width=66><DIV align=center>${borrow.state.name}</DIV></TD>
        <c:if test="${borrow.state.name=='正常'}"><TD width=66>
        <DIV align=center><a href="<html:rewrite action="/book/back"/>?method=renewBook&id=${borrow.id}"> 续借</a></DIV></TD></c:if>
        </TR>
      <tr> </tr>
      <TR vAlign="top">
         <TD colSpan="9"><IMG height=1 src="/images/buy/green-pixel.gif" width="100%" border="0"></TD>
      </TR>
</c:forEach>
<!-- loop end -->	  
    </TABLE></FORM>
     </TD>
  </TR>
</TABLE>
<br>
<jsp:include page="/WEB-INF/page/share/Foot.jsp" />
</BODY>
</HTML>
