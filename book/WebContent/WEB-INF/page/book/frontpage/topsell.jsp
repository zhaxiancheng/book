<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/page/share/taglib.jsp" %>
<UL>
	<c:forEach items="${topsellbooks}" var="topsellbooks" varStatus="statu">
		<LI class="bx">${statu.count}.<a href="<html:rewrite action="/book/view"/>?booktid=${topsellbook.id}" target="_blank" >${topsellbooks.name}</a></LI></c:forEach>	
</UL>