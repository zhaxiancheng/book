<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/page/share/taglib.jsp" %>
	<c:forEach items="${viewHistory}" var="viewbook" varStatus="statu">
		<li class="bj_blue"><a href="<html:rewrite action="/book/view"/>?bookid=${viewbook.bookid}" target="_blank" title="${viewbook.name}">${viewbook.name}</a></li></c:forEach>			