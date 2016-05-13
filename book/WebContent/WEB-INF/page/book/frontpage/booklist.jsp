<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/WEB-INF/page/share/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head><title>${booktype.name} 图书查阅</title>    
	<link href="/css/global/header01.css" rel="stylesheet" type="text/css">
	<link href="/css/book/list.css" rel="stylesheet" type="text/css" />	
	<link href="/css/global/topsell.css" rel="stylesheet" type="text/css">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta name="Keywords" content="${booktype.name}">
	<META name="description" content="${booktype.note}">
<SCRIPT language=JavaScript src="/js/xmlhttp.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript">
	function getTopSell(typeid){
		var salespromotion = document.getElementById('salespromotion');		
		if(salespromotion && typeid!=""){
			salespromotion.innerHTML= "数据正在加载...";
			send_request(function(value){salespromotion.innerHTML=value},
					 "<html:rewrite action="/book/switch"/>?method=topsell&typeid="+ typeid, true);
		}
	}
	function getViewHistory(){
		var viewHistoryUI = document.getElementById('viewHistory');
		if(viewHistoryUI){
			viewHistoryUI.innerHTML= "数据正在加载...";
			send_request(function(value){viewHistoryUI.innerHTML=value},
					 "<html:rewrite action="/book/switch"/>?method=getViewHistory", true);
		}
	}
	function pageInit(){
		getTopSell("${booktype.typeid}");
		getViewHistory();
	}
	function topage(pagenum){
		window.location.href='<html:rewrite action="/book/list/display"/>?sort=selldesc&style=${param.style}&brandid=${param.brandid}&sex=${param.sex}&typeid=${param.typeid}&page='+ pagenum;
	}
</SCRIPT>
</head>

<body class="ProducTypeHome2" onload="javascript:pageInit()">
	<jsp:include page="/WEB-INF/page/share/Head.jsp"/>
	<c:set var="out" value=""/><c:forEach items="${types}" var="type" varStatus="statu">
		<c:if test="${statu.count==1}"><c:set var="out" value=" &gt;&gt; <em>${type.name}</em> ${out}"/></c:if>
		<c:if test="${statu.count>1}"><c:set var="out" value=" &gt;&gt; <a href='/book/list/display?typeid=${type.typeid}'>${type.name}</a> ${out}"/></c:if>
	</c:forEach>
    <div id="position">您现在的位置: <a href="/" name="linkHome">图书馆首页</a> 
    <c:out value="${out}" escapeXml="false"></c:out>（${pageView.totalrecord}个）
	</div>

    <!--页面左侧分类浏览部分-->
    <div class="browse_left">
         <div class="browse">
            <div class="browse_t">${booktype.name}</div>
			
				<h2><span class="gray">浏览下级分类</span></h2>
				<ul><c:forEach items="${booktype.childtypes}" var="childtype">						
				<li class='bj_blue'><a href="<html:rewrite action="/book/list/display"/>?typeid=${childtype.typeid}">${childtype.name}</a></li></c:forEach>		    
			</ul>
	     </div>
<DIV id="sy_biankuang">
        <DIV class="lanmu_font">借阅量最多${booktype.name}</DIV>
        <DIV style="PADDING-LEFT: 10px; COLOR: #333333" id="salespromotion">
			
        </DIV>
</DIV>
		 <br/>
		 <div class="browse">
	          <div class="browse_t">您浏览过的图书</div>
			  <ul id="viewHistory"></ul>
	     </div>
    </div>
    <!--页面右侧分类列表部分开始-->
    <div class="browse_right">
         <div class="select_reorder">
              <div class="reorder_l">请选择排序方式： 
              <c:if test="${'borrowcountdesc'==param.sort}"><strong><em>借阅量由多到少</em></strong></c:if>
              <c:if test="${'borrowcountdesc'!=param.sort}"><a title='借阅量由多到少' href="<html:rewrite action="/book/list/display"/>?sort=borrowcountdesc&typeid=${param.typeid}&style=${param.style}">借阅量由多到少</a></c:if>
			  | <c:if test="${'borrowcountasc'==param.sort}"><strong><em>借阅量由少到多</em></strong></c:if>
			  <c:if test="${'borrowcountasc'!=param.sort}"><a title='借阅量由少到多' href="<html:rewrite action="/book/list/display"/>?sort=borrowcountasc&typeid=${param.typeid}&style=${param.style}">借阅量由少到多</a></c:if>
			  | <c:if test="${'intimeasc'==param.sort}"><strong><em>最早上架时间</em></strong></c:if>
			  <c:if test="${'intimeasc'!=param.sort}"> <a title='最早上架时间' href="<html:rewrite action="/book/list/display"/>?sort=intimeasc&typeid=${param.typeid}&style=${param.style}">最早上架时间</a></c:if>
			  | <c:if test="${empty param.sort}"><strong><em>最近上架时间</em></strong></c:if>
			  <c:if test="${!empty param.sort}"><a title='最近上架时间' href="<html:rewrite action="/book/list/display"/>?sort=&typeid=${param.typeid}&style=${param.style}">最近上架时间</a></c:if>
		     </div>
              
			<div class="emptybox"></div>
			 <div class="brand">
				<div class="FindByHint">按<strong>类别</strong>选择：</div>
				<ul class="CategoryListTableLevel1"><c:forEach items="${bookmap}" var="type">
				<li><a href="<html:rewrite action="/book/list/display"/>?sort=${param.sort}&typeid=${type.key}&brandid=${brand.code}&sex=${param.sex}">${type.value}</a></li></c:forEach>
				</ul>
			 </div>
			 <div class="SubCategoryBox">
				<div class="FindByHint">按<strong>性别</strong>选择：</div>
				<ul class="CategoryListTableLevel1">
				<li><a  href="<html:rewrite action="/book/list/display"/>?sort=sellpriceasc&typeid=${param.typeid}&sex=MAN&brandid=${param.brandid}&style=${param.style}">男性书籍</a></li>
				<li><a  href="<html:rewrite action="/book/list/display"/>?sort=sellpriceasc&typeid=${param.typeid}&sex=WOMEN&brandid=${param.brandid}&style=${param.style}">女性书籍</a></li>
				<li><a  href="<html:rewrite action="/book/list/display"/>?sort=sellpriceasc&typeid=${param.typeid}&sex=NONE&brandid=${param.brandid}&style=${param.style}">无性别要求</a></li>
				<li><a class="red" href="<html:rewrite action="/book/list/display"/>?sort=sellpriceasc&typeid=${param.typeid}">全部</a></li>
				</ul>
			 </div>
		</div>
	     <div id="divNaviTop" class="number">
	          <div class="number_l">以下<span class='number_white'>${pageView.totalrecord}</span>条结果按<span class="number_white">
				<c:choose>
				  <c:when test="${'borrowcountdesc'==param.sort}">借阅量由多到少</c:when>
				  <c:when test="${'borrowcountasc'==param.sort}">借阅量由少到多</c:when>
				  <c:when test="${'intimeasc'==param.sort}">最早上架时间</c:when>
				  <c:otherwise>最近上架时间</c:otherwise>
				</c:choose>
			  </span>排列　 每页显示<span class="number_white">${pageView.maxresult}</span>条记录</div>
		      <div class="turnpage">
                <div><em>第${pageView.currentpage}页</em></div>
		      </div>
	     </div>

	<div class='goods_pic'>
<!---------------------------LOOP START------------------------------>
<c:forEach items="${pageView.records}" var="entry">	
        <div class="detail">
           <div class="goods" style="cursor:hand;background:url(<c:forEach items="${entry.styles}" var="pic">${pic.image140FullPath}</c:forEach>) center center no-repeat"><a href="<html:rewrite action="/book/view"/>?bookid=${entry.bookid}" target="_blank">
            <img src="/images/global/book_blank.gif" alt="${entry.name}" width="140" height="168"  border="0"/></a></div>
          <%-- <h2><a href="/html/book/${entry.type.typeid}/${entry.bookid}.shtml" target="_blank" title="${entry.name}">${entry.name}</a></h2>--%>
           <h2><a href="<html:rewrite action="/book/view"/>?bookid=${entry.bookid}" target="_blank" title="${entry.name}">${entry.name}</a></h2>
           <div class="save_number" >作者 : <c:if test="${empty entry.author}"><strong><em>无</em></strong></c:if><c:if test="${!empty entry.author}">${entry.author}</c:if>&nbsp;  借阅量：${entry.borrowcount}　</div>
           <div class="an_img" align="center"><a href="<html:rewrite action="/book/view"/>?bookid=${entry.bookid}" target="_blank" title="${entry.name}"><img src='/images/look.gif' width='84' height='24' border='0' class='a_1' /></a></div>
        </div>
</c:forEach>
<!----------------------LOOP END------------------------------->
		<div class='emptybox'></div>
	</div>
	     <div id="divNaviBottom" class="page_number">
	     <div class="turnpage turnpage_bottom">	
	     <c:forEach begin="${pageView.pageindex.startindex}" end="${pageView.pageindex.endindex}" var="wp">
		    <c:if test="${pageView.currentpage==wp}"><div class='red'>${wp}</div></c:if>
		    <c:if test="${pageView.currentpage!=wp}"><div class="page"><a href="<html:rewrite action="/book/list/display"/>?page=${wp}&brandid=${param.brandid}&sex=${param.sex}&sort=selldesc&style=${param.style}&typeid=${param.typeid}">[${wp}]</a></div></c:if>
		</c:forEach>
		<div>&nbsp;&nbsp;</div>跳转到第
		<select name="selectPage" class="kuang" onChange="javaScript:topage(this.value)">
				<c:forEach begin="1" end="${pageView.totalpage}" var="wp">
				<option value="${wp}" <c:if test="${pageView.currentpage==wp}">selected</c:if>> ${wp} </option></c:forEach>
		</Select>页
		</div>
	    </div>
    </div>
	<jsp:include page="/WEB-INF/page/share/Foot.jsp" />
</body>
</html>