<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/share/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="/css/global/header01.css" rel="stylesheet" type="text/css">
	<link href="/css/book/book.css" rel="stylesheet" type="text/css">
		<link href="/css/global/topcommend.css" rel="stylesheet"
			type="text/css">
			<SCRIPT language=JavaScript src="/js/FoshanRen.js"></SCRIPT>
			<SCRIPT language=JavaScript src="/js/xmlhttp.js"></SCRIPT>
			<title>${book.name}--河南文理学院图书馆</title>
			<meta name="Keywords" content="${book.name}">
				<META name="description" content="">
					<SCRIPT LANGUAGE="JavaScript">
					<!--
						function styleEvent(styleid) {
							var bookImage = document
									.getElementById('bookImage_' + styleid);
							if (bookImage) {
								var main_image = document
										.getElementById("main_image");
								main_image.style.background = "url("
										+ bookImage.value
										+ ") center center no-repeat";
							}
						}

						function bigImageBrowse() {
							var form = document.forms["cart"];
							var stypeid = form.styleid.value;
							var bookPrototypeImage = document
									.getElementById('bookPrototypeImage_'
											+ stypeid);
							if (bookPrototypeImage) {
								var path = "<html:rewrite action="/book/switch"/>?method=showimage&path="
										+ bookPrototypeImage.value;
								window.open(path, "显示图片");
							}
						}
					//-->
					</SCRIPT>
</head>

<body>
	<jsp:include page="/WEB-INF/page/share/Head.jsp" />
	<div id="ContentBody">
		<!-- 页面主体 -->
		<c:set var="out" value="&gt;&gt; <em>${book.name}</em>" />
		<c:forEach items="${stypes}" var="type" varStatus="statu">
			<c:set var="out"
				value=" &gt;&gt; <a href='/book/list.do?typeid=${type.typeid}'>${type.name}</a> ${out}" />
		</c:forEach>
		<div id="position">
			您现在的位置：<a href="/" name="linkHome">图书首页</a> <span id="uc_cat_spnPath"><c:out
					value="${out}" escapeXml="false"></c:out> </span>
		</div>
		<div class="browse_left">
			<!-- 页面主体 左边 -->
			<!-- 浏览过的图书 -->
			<div class="browse">
				<div class="browse_t">您浏览过的图书</div>
				<ul>
					<div id="historyaccess"></div>
				</ul>
			</div>
			<!--精品推荐 start -->
			<DIV id="topcommend" align="left">
				<DIV id="newtop">
					<IMG height=13 src="/images/global/sy2.gif" width=192>
				</DIV>
				<DIV id="newlist">
					<DIV id="newmore">
						<DIV class="title">图书推荐</DIV>
					</DIV>
					<span id="commenddetail"> </span>
				</DIV>
			</DIV>
		</div>
		<!-- 页面主体 左边end -->

		<div id="Right">
			<!-- 页面主体 右边 -->
			<form action="<html:rewrite action="/shopping/cart"/>" method="post"
				name="cart">
				<INPUT TYPE="hidden" NAME="bookid" value="${book.bookid}">
					<div id="browse_left">
						<c:set var="currentimage" />
						<c:set var="imagecount" value="0" />
						<c:forEach items="${book.styles}" var="style">
							<c:if test="${style.visible}">
								<c:set var="currentimage" value="${style}" />
								<c:set var="imagecount" value="${imagecount+1}" />
							</c:if>
						</c:forEach>
						<div class="right_left">
							<div id="main_image" onclick="JavaScript:bigImageBrowse()"
								style="cursor:hand;background:url(${currentimage.image140FullPath}) center center no-repeat">
								<img src="/images/global/book_blank.gif" WIDTH="200"
									HEIGHT="240" />
							</div>
							<img src="/images/global/zoom+.gif"
								onclick="JavaScript:bigImageBrowse()" style="cursor:hand;" />
						</div>

						<div class="right_right">
							<div class="right_title">
								<b>图书名称： ${book.name}</b>
							</div>
							<div class="right_desc">
								<ul>
								    <li class="li2">图书作者： ${book.author}</li>
									<li >图书条形码： ${book.barcode}</li>
									<c:if test="${!empty book.type}">
										<li class="li2">图书类别：${book.type.name}</li>
									</c:if>
								</ul>
							</div>
							<div class="right_desc">
							   <c:if test="${imagecount==1}">
											<INPUT TYPE="hidden" NAME="styleid" value="${currentimage.styleid }">
											<li class="guopiprice">出版社：${currentimage.ISBN}  版次：${currentimage.revision}</li>
											<INPUT TYPE="hidden" id="bookImage_${currentimage.styleid }" value="${currentimage.imagename}">
											<INPUT TYPE="hidden" id="bookPrototypeImage_${currentimage.styleid }" value="${currentimage.imageFullPath}">
											</c:if>
								<c:if test="${imagecount>1}">
									<img src="/images/global/init.gif" width="0" height="0">
										<li class="guopiprice">可选版次：<SELECT name="styleid"
											onchange="javascript:styleEvent(this.value)">
												<c:forEach items="${book.styles}" var="style">
													<c:if test="${style.visible}">
														<option value="${style.styleid }"
															<c:if test="${style.styleid==currentimage.styleid}">selected </c:if>>出版社：${style.ISBN}  版次：${style.revision}</option>
													</c:if>
												</c:forEach>
										</SELECT>
									</li><c:forEach items="${book.styles}" var="style">
											<c:if test="${style.visible}">
												<INPUT TYPE="hidden" id="bookImage_${style.styleid}"
													value="${style.image140FullPath}"> <INPUT
													TYPE="hidden" id="bookPrototypeImage_${style.styleid}"
													value="${style.imageFullPath}">
											</c:if>
										</c:forEach>
								</c:if>
							    <li class="guopiprice">书架位置:${book.shelf.location}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>
								<li class="guopiprice">书架名称:${book.shelf.name}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>
								<li class="guopiprice">上架时间:${book.intime}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>
								<li class="guopiprice">图书价格:${book.price}</li>
							</div>
							
						</div>
						<div class='right_title1'>图书说明</div>
			            <div class='right_content'>${book.description}</div>
					</div>
			</form>
			<div class='right_blank'></div>
			
		</div>
		<!-- 页面主体 右边 end -->
	</div>
	<!-- 页面主体 end -->
	<jsp:include page="/WEB-INF/page/share/Foot.jsp" />
</body>
</html>