<%--
  Created by IntelliJ IDEA.
  User: LBC
  Date: 2015/1/18
  Time: 22:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <link href="login.css">
<title>管理员登录</title>
  <script src="mootools.js" type="text/javascript" charset="utf-8"></script>
  <script src="prototype-1.6.0.3.js" type="text/javascript" charset="utf-8"></script>
  <script src="json2.js" type="text/javascript" charset="utf-8"></script>
  <script type="text/javascript">
    function onUserNameChange(){
      var params=Form.serialize('login');
      if(document.getElementById("rdo_user").checked==true){
        var url='/Test/login/userCheck.action';
      }else if(document.getElementById("rdo_admin").checked==true){
        var url='/Test/login/admCheck.action';
      }

      var myAjax=new Ajax.Request(url,
              {
                method:'post',
                parameters:params,
                onComplete:processResponse,
                asynchronous:true
              });
    }
    function onRdoReadOnly(){
      document.getElementById("login").action="/Test/login/userLogin.action";
      onUserNameChange();
      //$("adm.login").action="userLogin";
    }
    function onRdoComplete(){
      document.getElementById("login").action="/Test/login/admLogin.action";
      onUserNameChange();
      //$("adm.login").action="admLogin";
    }

    function processResponse(request){
      var res=JSON.parse(request.responseText);
	  var check=document.getElementById("user_check");
	  check.innerHTML="";
      if(res["userOrAdminExists"]==true){
//        check.fontcolor=;
        check.innerHTML+="用户名可用。";
      }else{
//        check.fontcolor="red";
        check.innerHTML+="用户不存在。";
      }
	  
    }
  </script>
</head>
<body>
<form id="login" name="login" method="post" action="/Test/login/userLogin.action" >
  <p><img src="user.jpg" >
    <input type="text" name="adm.username" id="username" oninput="onUserNameChange();" maxlength="18"/><label  id="user_check"></label>
  </p>
  <p>密码
    <input type="password"  name="adm.password" id="password" maxlength="18" />
  </p>
  <p >
    <label><input type="radio" name="rdo" value="checked" checked="true" id="rdo_user" onClick="onRdoReadOnly();"/>只读权限</label>
    <br />
    <label><input type="radio" name="rdo" value="单选" id="rdo_admin" onClick="onRdoComplete();"/>完全权限</label>
    <br />
  </p>
  <p>
    <input type="submit" name="submit" id="submit" value="登录"/>
    <input type="reset" name="reset" id="reset" value="重置"/>
  </p>
</form>
</body>
</html>
