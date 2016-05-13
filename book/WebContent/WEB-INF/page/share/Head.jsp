<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/WEB-INF/page/share/taglib.jsp" %>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%
Date dNow = new Date(); 
SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日");
%>
<div id="Head">
  <div id="HeadTop">
    <div id="Logo"><a href="" target=_top><img alt=图书管理系统前台展示！ src="/images/global/logo.gif" border=0 /></a> </div>
    <div id="HeadNavBar">
      <ul>
        <li class="NoSep"><a id="MyBuyCar"  href="/user/book/query.do?method=queryborrowBookUI" ><font color="blue"><Strong>借阅记录</Strong></font></a> </li>
        <c:if test="${empty user}"><li><a href="/user/reg.do?method=regUI" >新用户激活</a> </li>
        <li><a href="/user/logon.do" >读者登录</a> </li></c:if>
        <c:if test="${!empty user}"><li><a href="#" >${user.username}</a> </li>
        <li><a href="/user/logout.do" >退出登录</a> </li></c:if>
        <li><a href="#" >帮助中心</a> </li>
        <li class="phone">服务热线：18703427011</li>
      </ul>
    </div>
  </div>
  <div id="ChannelMenu">
	<UL id="ChannelMenuItems">
		<!--  
		<LI id="MenuHome"><a href="/"><span>首页</span></a></LI>
		<LI id="ProducType1Home"><a href="/book/list/display.do?typeid=1"><span>经典著作 </span></a></LI>
		<LI id="ProducType2Home"><a href="/book/list/display.do?typeid=1"><span>社会科学</span></a></LI>
		<LI id="ProducType3Home"><a href="/book/list/display.do?typeid=1"><span>计算机技术 </span></a></LI>
		<LI id="ProducType8Home"><a href="/book/list/display.do?typeid=1"><span>政治/军事 </span></a></LI>
		<LI id="MyAccountHome"><a href="/"><span>数理化学</span></a></LI>
		-->
	</UL>
<!--  SearchBox -->
<div id="SearchBox">
	  <div id="SearchBoxTop">
		  <div id="SearchForm">
			<form action="/book/query.do" method="post" name="search" id="search">

			 <span class="name">图书搜索: </span><input id="word" name="word" accesskey="s" size="100" maxlength="100" value="${param.word }"/>

			  <input type="submit" value="搜 索" id="DoSearch"/>
			</form>
		  </div>
	  </div>
      <div id="HotKeywords">
			<ul>
				<li><span>
					<%=formatter.format(dNow) %>&nbsp;&nbsp;
您好<SCRIPT language=JavaScript src="/js/welcome.js"></SCRIPT>，欢迎来到图书馆！</span></li>
				<li>热门搜索：</li>
				
<li><a href="/q?word=%E5%B8%90%E7%AF%B7">考研</a></li>
<li><a href="/q?word=%E7%91%9C%E4%BC%BD%E7%90%83">励志</a></li>
<li><a href="/q?word=%E8%BF%9C%E9%98%B3%E7%91%9C%E4%BC%BD%E6%9C%8D">平凡的世界</a></li>

			</ul>
      </div>
   </div>
</div><!-- End SearchBox -->
</div>
<!-- Head End -->