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
  <link type="text/css" rel="stylesheet" href="login/login.css">
  <title></title>
  <script src="login/mootools.js" type="text/javascript" charset="utf-8"></script>
  <script src="login/prototype-1.6.0.3.js" type="text/javascript" charset="utf-8"></script>
  <script src="login/json2.js" type="text/javascript" charset="utf-8"></script>
  <script type="text/javascript">
    function gotClick(){
      var url='/Test/Json/jsonT.action';
      var params=Form.serialize('form1');
      var myAjax=new Ajax.Request(url,
              {
                method:'post',
                parameters:params,
                onComplete:processResponse,
                asynchronous:true
              });
      // create a new Class instance
//    var myRequest = new Request({
//      url: '/Test/Json/jsonT.action',
//      method: 'post',
//      onRequest: function(){
//        myElement.set('text', 'loading...');
//      },
//      onSuccess: function(responseText){
//        myElement.set('text', responseText);
//      },
//      onFailure: function(){
//        myElement.set('text', 'Sorry, your request failed :(');
//      }
//    });
//
//     myRequest.send();
    }
    function processResponse(request){
//      var res=JSON.parse(request.responseText);
//      for(var propName in res){
//        $("show").innerHTML+=res[propName]+'<br/>';
//      }
      document.getElementById("show").innerHTML+="<p>";
      document.getElementById("show").innerHTML+=request.responseText;
      document.getElementById("show").innerHTML+="</p>";
    }
  </script>
</head>
<body>
<div>
  <form id="form1" name="form1" method="post">
    <input type="button" value="Test" onclick="gotClick();"/>
  </form>
</div>
<div id="show">
</div>
</body>
</html>
