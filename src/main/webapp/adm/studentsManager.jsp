<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: LBC
  Date: 2015/1/15
  Time: 14:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
  <s:form action="insert" name="insert" namespace="/db">
    <s:textfield name="info.id" label="学号"> </s:textfield>
    <s:textfield name="info.name" label="姓名"> </s:textfield>
    <s:textfield name="info.phoneNumber" label="手机"> </s:textfield>
    <s:textfield name="info.emailAddress" label="电邮"> </s:textfield>
    <s:submit > </s:submit>
  </s:form>
</body>
</html>
