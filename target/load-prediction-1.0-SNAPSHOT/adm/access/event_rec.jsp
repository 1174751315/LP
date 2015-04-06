<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: LBC
  Date: 2015/1/20
  Time: 15:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>管理员</title>
  <script src="json2.js" type="text/javascript" charset="utf-8"></script>
  <script src="prototype-1.6.0.3.js" type="text/javascript" charset="utf-8"></script>
  <script type="text/javascript">
    function deleteRec(o){
      var id= o.id;
      var params=Form.serialize('show');
      var url='/Test/access/deleteRec.action';
      document.getElementById('sub'+id).name='idBy';

//      if(document.getElementById("rdo_user").checked==true){
//        var url='/Test/login/userCheck.action';
//      }else if(document.getElementById("rdo_admin").checked==true){
//        var url='/Test/login/admCheck.action';
//      }
//
      var myAjax=new Ajax.Request(url,
              {
                method:'post',
                parameters:params,
                onComplete:onResponseOfDeleteRec,
                asynchronous:true
              });
    }
    function onResponseOfDeleteRec(){
      var a=1;
      var b=a;
    }
  </script>
</head>
<body>
<form id="show">
  <s:iterator value="events" status="it">
<table border="1" style="background-color: cadetblue">
    <tr>
      <th>顺序号</th><th>类型</th><th>操作员</th>
        <%--<th>--%>
            <%--<s:submit  id="%{it.index}"  onclick="deleteRec(this);"></s:submit> --%>
        <%--</th>--%>
    </tr>
    <tr>
    <%--<td><s:label id="sub#it.index" ><s:property value="id"/></s:label></td>--%>
        <%--<td><s:label value=<s:property value="#it.index"> ></s:label></td>--%>
        <td><s:property value="id"/></td>
        <td><s:property value="type"/></td>
        <td><s:property value="operator"/></td>
    </tr>
    <table border="1" style="background-color: azure">
      <tr>
        <th>时间</th><th>事件</th><th>数据1</th><th>数据2</th>
      </tr>
      <s:iterator value="eventDatas">
        <tr>
        <td> <s:property value="time"/></td>
        <td> <s:property value="eventId"/></td>
        <td> <s:property value="data1"/></td>
        <td> <s:property value="data2"/></td>
      </tr>
      </s:iterator>
    </table>
</table>
    </p>
  </s:iterator>
</form>
</body>
</html>
