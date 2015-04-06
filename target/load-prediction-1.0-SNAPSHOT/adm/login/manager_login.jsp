<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: LBC
  Date: 2015/1/16
  Time: 16:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<div class="container">
  <div class="headers-region">
    <div class="header_link">
      <a href="http://w.mail.qq.com/cgi-bin/loginpage?f=xhtml">基本版</a>&nbsp;|&nbsp;<a href="http://en.mail.qq.com">English</a>&nbsp;|&nbsp;<a href="http://app.mail.qq.com/" target="_blank">手机版</a>&nbsp;|&nbsp;<a href="http://exmail.qq.com?referrer=index_top" target="_blank">企业邮箱</a>
    </div>
  </div>
  <div  class="content">
    <s:form action="admLogin" namespace="/login" theme="xhtml">
      <s:textfield name="adm.username" label="用户名"></s:textfield>
      <s:textfield name="adm.password" label="密码"></s:textfield>
      <s:submit label="登录"></s:submit>
    </s:form>
  </div>
</div>

</body>
</html>
