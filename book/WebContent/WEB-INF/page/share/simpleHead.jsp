<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/share/taglib.jsp" %>
<div id="Head">
  <div id="HeadTop">
    <div id="Logo"><a href="#" target=_top><img alt="同大图书馆管理系统" src="/images/global/logo.gif" border=0 /></a> </div>
    <div id="HeadNavBar">
      <ul>
        <li class="NoSep"><a id="MyBuyCar"  href="/user/book/query.do?method=queryborrowBookUI" ><font color="blue"><Strong>借阅记录</Strong></font></a> </li>
        <c:if test="${empty user}"><li><a href="/user/reg.do?method=regUI" >新用户激活</a> </li>
        <li><a href="/user/logon.do" >读者登录</a> </li></c:if>
        <c:if test="${!empty user}"> <li><a href="#" >${user.username}</a></li> 
         <li><a href="/user/logout.do" >退出登录</a> </li></c:if>
        <li><a href="#" >帮助中心</a> </li>
        <li class="phone">服务热线：18703427011</li>
      </ul>
    </div>
  </div>
</div>
<!-- Head End -->