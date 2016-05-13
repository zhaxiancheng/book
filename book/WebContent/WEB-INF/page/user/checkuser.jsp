<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/share/taglib.jsp" %>
<% 
response.setHeader("Cache-Control", "no-store"); //HTTP1.1
response.setHeader("Pragma", "no-cache"); //HTTP1.0
response.setDateHeader("Expires", 0);
%>
<c:if test="${exsit}">
	<font color="red">可以使用</font>
</c:if>
<c:if test="${!exsit}">
	<font color="green">不存在的用户名和借阅卡号</font>
</c:if>