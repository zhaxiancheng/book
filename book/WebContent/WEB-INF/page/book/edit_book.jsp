<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/page/share/taglib.jsp" %>
<html>
<head>
<title>修改图书</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/css/vip.css" type="text/css">
<SCRIPT language=JavaScript src="/js/FoshanRen.js"></SCRIPT>
<script type="text/javascript" src="/js/jscripts/tiny_mce/tiny_mce.js"></script>
<script language="javascript" type="text/javascript">
tinyMCE.init({
	language : "zh_cn",
	mode : "textareas",
	theme : "advanced",
	//width : "500",
	plugins : "table,save,advhr,advimage,advlink,emotions,iespell,insertdatetime,preview,zoom,flash,searchreplace,print,contextmenu",
	theme_advanced_buttons1_add_before : "save,separator",
	theme_advanced_buttons1_add : "fontselect,fontsizeselect",
	theme_advanced_buttons2_add : "separator,insertdate,inserttime,preview,zoom,separator,forecolor,backcolor",
	theme_advanced_buttons2_add_before: "cut,copy,paste,separator,search,replace,separator",
	theme_advanced_buttons3_add_before : "tablecontrols,separator",
	theme_advanced_buttons3_add : "emotions,iespell,flash,advhr,separator,print",
	theme_advanced_toolbar_location : "top",
	theme_advanced_toolbar_align : "left",
	theme_advanced_path_location : "bottom",
	plugin_insertdate_dateFormat : "%Y-%m-%d",
	plugin_insertdate_timeFormat : "%H:%M:%S",
	extended_valid_elements : "a[name|href|target|title|onclick],img[class|src|border=0|alt|title|hspace|vspace|width|height|align|onmouseover|onmouseout|name],hr[class|width|size|noshade],font[face|size|color|style],span[class|align|style]",
	external_link_list_url : "example_data/example_link_list.js",
	external_image_list_url : "example_data/example_image_list.js",
	flash_external_list_url : "example_data/example_flash_list.js"
});

function Formfield(name, label){
	this.name=name;
	this.label=label;
}
function verifyForm(objForm){
	tinyMCE.triggerSave();//手动把iframe的值赋给textarea表单元素
	var list  = new Array(new Formfield("name", "图书名称"),new Formfield("typeid", "图书类型"),
	new Formfield("price", "图书价格"),new Formfield("barcode", "图书条形码"),new Formfield("sumcount", "图书总量"));
	for(var i=0;i<list.length;i++){
		var objfield = eval("objForm."+ list[i].name);
		if(trim(objfield.value)==""){
			alert(list[i].label+ "不能为空");
			if(objfield.type!="hidden" && objfield.focus()) objfield.focus();
			return false;
		}
	}
	var imagefile = objForm.imagefile.value;
	var ext = imagefile.substring(imagefile.length-3).toLowerCase();
	if (ext!="jpg" && ext!="gif" && ext!="bmp" && ext!="png"){
		alert("只允许上传gif、jpg、bmp、png！");
		return false; 
	}
    return true;
}
function SureSubmit(objForm){
	 objForm.submit();
} 
</script>
</head>

<body bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<html:form action="/control/book/manage" enctype="multipart/form-data" method="post">
<input type="hidden" name="method" value="edit">
<html:hidden property="typeid"/>
<html:hidden property="shelfid"/>
<html:hidden property="bookid"/>
  <table width="98%" border="0" cellspacing="1" cellpadding="3" align="center">
    <tr bgcolor="6f8ac4"> 
      <td colspan="2" ><font color="#FFFFFF">修改图书：</font></td>
    </tr>
    <tr bgcolor="f5f5f5"> 
      <td width="25%"> <div align="right">图书名称  ：</div></td>
      <td width="75%"> <html:text property="name" size="50" maxlength="40"/><font color="#FF0000">*</font></td>
    </tr>
    <tr bgcolor="f5f5f5"> 
      <td width="25%"> <div align="right">图书条形码 ：</div></td>
      <td width="75%"> <html:text property="barcode" size="35" maxlength="30"/><font color="#FF0000">*</font></td>
    </tr>
    <tr bgcolor="f5f5f5"> 
      <td width="25%"> <div align="right">图书类别  ：</div></td>
      <td width="75%"> <input type="text" name="v_type_name" disabled="true" size="30" value="${typename}"/> 
        <input type="button" name="select" value="选择..." onClick="javaScript:winOpen('<html:rewrite action="/control/book/manage"/>?method=selectUI','列表',600,400)">(<a href="<html:rewrite action='/control/book/type/manage'/>?method=addUI">添加图书类别</a>)
        <font color="#FF0000">*</font>
      </td>
    </tr>
	<tr bgcolor="f5f5f5"> 
      <td width="25%"> <div align="right">图书书架 ：</div></td>
      <td width="75%"> <html:select property="shelfid">
       <html:optionsCollection name="typeid" label="name" value="shelfid"/>
        </html:select>(<a href="<html:rewrite action='/control/book/manage'/>?method=addUI">添加书架</a>)<font color="#FF0000">*</font></td>
    </tr>
   <tr bgcolor="f5f5f5"> 
      <td width="25%"> <div align="right">图书作者：</div></td>
      <td width="75%"> <html:text property="author" size="35" maxlength="30"/></td>
    </tr>
    <tr bgcolor="f5f5f5"> 
      <td width="25%"> <div align="right">图书译者 ：</div></td>
      <td width="75%"> <html:text property="translator" size="35" maxlength="30"/></td>
    </tr>
    <tr bgcolor="f5f5f5"> 
      <td width="25%"> <div align="right">图书价格 ：</div></td>
      <td width="75%"> <html:text property="price" size="35" maxlength="30"/><font color="#FF0000">*</font></td>
    </tr>
    <tr bgcolor="f5f5f5"> 
      <td width="25%"> <div align="right">适用性别 ：</div></td>
      <td width="75%"><html:select property="sex">   
			<html:option value="NONE">男女不限</html:option>   
			<html:option value="MAN">男士</html:option>   
			<html:option value="WOMEN">女士</html:option>
		</html:select>
		</td>
    </tr>
    <tr bgcolor="f5f5f5"> 
      <td width="25%"> <div align="right">图书简介：</div></td>
      <td width="75%"> <html:text property="description" size="35" maxlength="30" /></td>
    </tr>
    <tr bgcolor="f5f5f5"> 
      <td width="25%"> <div align="right">图书总数量：</div></td>
      <td width="75%"> <html:text property="sumcount" size="35" maxlength="30"/><font color="#FF0000">*</font></td>
    </tr>
    <tr bgcolor="f5f5f5"> 
      <td colspan="2"> <div align="center"> 
          <input type="button" name="Add" value=" 确 认 " class="frm_btn" onClick="javascript:SureSubmit(this.form)">
          &nbsp;&nbsp;<input type="button" name="Button" value=" 返 回 " class="frm_btn" onclick="javascript:history.back()">
        </div></td>
    </tr>
  </table>
</html:form>
<br>
</body>
</html>