<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/share/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>新用户登录</title>
<link href="/css/global/header.css" rel="stylesheet" type="text/css">
<link href="/css/global/reg.css" rel="stylesheet" type="text/css">
<SCRIPT language=JavaScript src="/js/FoshanRen.js"></SCRIPT>
<SCRIPT language=JavaScript src="/js/xmlhttp.js"></SCRIPT>
  <SCRIPT LANGUAGE="JavaScript">
  <!--
	function checkForm(){
		var form = document.forms[0];
		if(form.username.value=="" || form.username.value.length<3){
			alert("请输入长度3位以上的用户名");
			return false;
		}
		if(form.password.value=="" || form.password.value.length<6){
			alert("请输入长度6位以上的密码");
			return false;
		}
		if(form.password.value!=form.repassword.value){
			alert("两次输入的密码不正确");
			return false;
		}
		if(/[\W]/g.test(form.username.value)){
			alert("用户名中不能含有中文");
			return false;
		};
		return true;
	}
	function checkUsername(){
		var form = document.forms[0];
		var librarycard = form.librarycard.value;
		var username = form.username.value;
		var viewobj = document.getElementById("checkResult");
		viewobj.innerHTML = "正在检测中...";
		send_request(function(value){viewobj.innerHTML=value},
		'<html:rewrite action="/user/reg"/>?method=isUserExsit&username='+ username+'&librarycard='+librarycard, true);
	}
  //-->
  </SCRIPT>
</head>

<body>
<jsp:include page="/WEB-INF/page/share/simpleHead.jsp"/>
<!-- 检证用户是否存在表单 end -->
<TABLE cellSpacing=0 cellPadding=0 width=770 align=center border=0>
  <TBODY>
  <TR><TD background="/images/login/login_03.jpg">&nbsp;</TD></TR>
  <TR>
    <TD height=15>&nbsp;</TD>
  </TR>
  <TR>
    <TD height=30><img src="/images/login/zc.gif" width="128" height="31" /></TD>
  </TR>
 </TBODY>
</TABLE>

<DIV id="errorinfo" style="DISPLAY:none">
<TABLE cellSpacing=1 cellPadding=8 width="78%" align="center" bgColor="#dd9988" border=0>
  <TBODY>
  <TR>
    <TD bgColor="#ffffd5" align="left"><IMG height="17" src="/images/buy/exclamation-error-red.gif" width="17"> <FONT color="#990000"><STRONG><SPAN class="font14">错误提示<BR></SPAN></STRONG></FONT>
      <DIV id="errorMessage"></DIV>
	</TD>
  </TR>
  </TBODY>
</TABLE>
</DIV>

<div id="Content">
<html:form action="/user/reg" method="post" onsubmit="javascript:return checkForm()">
<INPUT TYPE="hidden" NAME="method" value="reg">
<input type="hidden" name="directUrl" value="${param.directUrl}">
<div id="Main">
  <ul id="FormRegStep1_Account" class="RegForm">
        <li class="Title">以下<span class="STYLE1">均为</span>必填项　</li>
		
		<li>
            <div class="Hint">借阅卡号：</div>
            <div class="Input">
              <input name="librarycard" value="" id="librarycard" type="text" size="24" maxlength="16" tabindex="2"/>
            </div>
            <div class="Info">
                <div id="librarycard_info">借阅证的卡号</div><br>
            </div>
            <div class="HackBox"></div>
         </li>
          
		<li>
		   <div class="Hint">用户名：</div>
        <div class="Input">
		  	<input name="username" type="text" size="24" value="" tabindex="1"  maxLength="20" onkeyup="value=value.replace(/[\W]/g,'')"/> <br />
			<input type="button" name="check_username" value="验证借阅卡号和用户名" onClick="checkUsername()" onMouseOver="this.style.cursor='hand';"/>
  		  </div>
          <div class="Info">
        		<div id="username_info">5-20个字符(包括小写字母、数字、下划线),一旦激活成功后不能修改。</div><br>
			    <div id="checkResult"></div><br>
          </div>
        <div class="HackBox"></div>
		</li>
        
        <li>
            <div class="Hint">密码：</div>
            <div class="Input">
              <input name="password" value="" id="password" type="password" size="24" maxlength="16" tabindex="2"/>
            </div>
            <div class="Info">
                <div id="password_info">密码由6-16个字符组成,请使用英文字母加数字或符号的组合密码</div><br>
            </div>
            <div class="HackBox"></div>
          </li>
    
          <li>
            <div class="Hint">再输入一遍密码：</div>
            <div class="Input">
              <input name="repassword" value="" id="repassword" type="password" size="24" maxlength="16" tabindex="3"/>
            </div>
            <div class="Info">
                 <div id="confirm_password_info">请再输入一遍您上面输入的密码。</div><br>
            </div>
            <div class="HackBox"></div>
          </li>
		  <li>
            <div class="Hint">电子邮件：</div>
            <div class="Input">
              <input name="email" value="" id="email" type="text" size="28" maxlength="45" tabindex="3"/>
            </div>
            <div class="Info"><div id="email_info">建议使用常用的邮箱,找回密码是可以使用</div>
            </div>
            <div class="HackBox"></div>
          </li>
	</ul><p class="SubmitBox">
	<input type="submit" name="Submit" value="提交激活信息" tabindex="8" />
	</p></div>
	</html:form>
</div>
<jsp:include page="/WEB-INF/page/share/Foot.jsp" />
</html>