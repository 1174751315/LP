<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  ~ 版权所有 (c) 2015 。 李倍存 （iPso）。
  ~ 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
  ~ 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
  --%>
<%@ page contentType="text/html; ISO-8859-1;charset=UTF-8" language="java" %>
<<<<<<< HEAD
<html>
<head>
    <link href="CONFIG/favicon.ico" rel="shortcut icon" type="image/vnd.microsoft.icon" />
    <title>BETA 1.0</title>
    <script type="text/javascript">
        window.onload = function () {
//            window.location.href=window.location+"?now="+new Date().getTime();
            document.getElementById("dateEdit").onkeyup=function(e){
                e=e||window.event;
                var code= e.keyCode|| e.which|| e.charCode;
                if(code==13){
                    if(document.getElementById("dateEdit").value!=""){
                        doDetermineDateType();
                    }else{
                        alert("请首先选择一个有效的日期。");
                    }
                }
            }
            document.getElementById("sa_dateEdit").onkeyup=function(e){
                e=e||window.event;
                var code= e.keyCode|| e.which|| e.charCode;
                if(code==13){
                    if(document.getElementById("sa_dateEdit").value!=""){
                        doShowOneAccuracy();
                    }else{
=======

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>无标题文档</title>
    <style type="text/css">
        #apDiv1 {
            position: absolute;
            width: 200px;
            height: 115px;
            z-index: 1;
        }

        #apDiv2 {
            position: absolute;
            width: 200px;
            height: 115px;
            z-index: 1;
        }

        #apDiv3 {
            position: absolute;
            width: 200px;
            height: 115px;
            z-index: 2;
        }

        #apDiv4 {
            position: absolute;
            width: 200px;
            height: 115px;
            z-index: 3;
        }

        #apDiv1 #apDiv3 table {
            color: #000;
        }
    </style>
    <script src="SpryAccordion.js" type="text/javascript"></script>
    <script src="SpryCollapsiblePanel.js" type="text/javascript"></script>
    <script src="json2.js" type="text/javascript"></script>
    <script src="prototype-1.6.0.3.js" type="text/javascript"></script>
    <link href="SpryAccordion.css" rel="stylesheet" type="text/css"/>
    <style type="text/css">
        #apDiv5 {
            position: absolute;
            width: 200px;
            height: 115px;
            z-index: 1;
        }

        #apDiv6 {
            position: absolute;
            width: 200px;
            height: 115px;
            z-index: 2;
        }

        #apDiv7 {
            position: absolute;
            width: 200px;
            height: 115px;
            z-index: 4;
        }

        #apDiv8 {
            position: absolute;
            width: 200px;
            height: 115px;
            z-index: 4;
        }
    </style>
    <link href="SpryCollapsiblePanel.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript">
        window.onload = function () {
//            window.location.href=window.location+"?now="+new Date().getTime();
            document.getElementById("dateEdit").onkeyup = function (e) {
                e = e || window.event;
                var code = e.keyCode || e.which || e.charCode;
                if (code == 13) {
                    beginPredict();
                }
            }
            document.getElementById("sa_dateEdit").onkeyup = function (e) {
                e = e || window.event;
                var code = e.keyCode || e.which || e.charCode;
                if (code == 13) {
                    if (document.getElementById("sa_dateEdit").value != "") {
                        doShowOneAccuracy();
                    } else {
>>>>>>> origin/develop
                        alert("请首先选择一个有效的日期。");
                    }
                }
            }
            var getTypeElement = function (es, type) {
                        var esLen = es.length,
                                i = 0,
                                eArr = [],
                                esl = null;
                        for (; i < esLen; i++) {
                            esl = es[i];
                            if (esl.nodeName.replace("#", "").toLocaleLowerCase() == type) {
                                eArr.push(esl);
                            }
                        }
                        return eArr;
                    },
                    navs = getTypeElement(document.getElementById("pullDownNavigation").childNodes, "div"), i = 0, I = navs.length, targetID = null;
            for (; i < I; i++) {
                navs[i].onmousemove = function () {
                    targetID = this.getAttribute("data-targetID");
                    document.getElementById(targetID).style.display = "block";
                }
                navs[i].onmouseout = function () {
                    document.getElementById(targetID).style.display = "none";
                }
            }
        }

<<<<<<< HEAD
        function highLight(o) {
            o.style.borderColor="#0000FF";
            o.style.borderWidth="2pt";
        }
        function unLight(o) {
            o.style.borderColor="#FFFFFF";
            o.style.borderWidth="2pt";
=======
        function beginPredict() {
            if (document.getElementById("dateEdit").value != "") {
                doDetermineDateType();
            } else {
                alert("请首先选择一个有效的日期。");
            }
        }
        function highLight(o) {
            o.style.borderColor = "#0000FF";
            o.style.borderWidth = "2pt";
        }
        function unLight(o) {
            o.style.borderColor = "#FFFFFF";
            o.style.borderWidth = "2pt";
>>>>>>> origin/develop
        }
        function doIntelligentPrediction() {
            var url = '/ajax_use_json/IntelliPrediction.action';
//          var dateString=prompt("请输入形如2000-01-01格式的日期字符串。");

//          var dateString=input("","");
            var params = Form.serialize('input');
            var myAjax = new Ajax.Request(url,
                    {
                        method: 'post',
                        parameters: params,
                        onComplete: processResponseIntelligentPrediction,
                        asynchronous: true
                    });
        }
        function processResponseIntelligentPrediction(request) {
//            alert(request.responseText);
            var res = JSON.parse(request.responseText);

            if (res["warning"] == "OK") {
                alert("已完成预测。");
<<<<<<< HEAD
                document.getElementById("outputExcel").href = res["root"] + res["xlFileName"] +"?now="+new Date().getTime();
//                document.getElementById("outputImg").src = "javascript:void(0)";
                document.getElementById("outputImg").src = res["root"] + res["imgFileName"] + "?now="+new Date().getTime();
=======
                document.getElementById("outputExcel").href = res["root"] + res["xlFileName"] + "?now=" + new Date().getTime();
//                document.getElementById("outputImg").src = "javascript:void(0)";
                document.getElementById("outputImg").src = res["root"] + res["imgFileName"] + "?now=" + new Date().getTime();
>>>>>>> origin/develop
//              var url='/general/downloadOutputExcel.action';
//              var params=Form.serialize('input');
//              var myAjax=new Ajax.Request(url,
//                      {
//                          method:'post',
//                          parameters:params,
//                          onComplete:unnamed,
//                          asynchronous:true
//                      });
            } else {
                alert(res["warning"]);
            }


        }
        function unnamed(request) {
//          var res=JSON.parse(request.responseText);
//          window.location.href=
            alert("Completed");
<<<<<<< HEAD


=======
>>>>>>> origin/develop
        }
        function doDetermineDateType() {
            var url = '/ajax_use_json/getDateType.action';
            var params = Form.serialize('input');
            var myAjax = new Ajax.Request(url,
                    {
                        method: 'post',
                        parameters: params,
                        onComplete: processDetermineDateType,
                        asynchronous: true
                    });
        }
        function processDetermineDateType(request) {
            var res = JSON.parse(request.responseText);
            if (res["warning"] == "OK") {
                var con = confirm("已选择  " + res["dateType"] + "  预测。\n若您需要改变此日期的预测类别（响应），请联系开发者。\n是否继续？");
                if (con == true)
                    doIntelligentPrediction();
            }
            else {
                alert(res["warning"]);
            }
        }
        function doShowOneAccuracy() {
            var url = '/ajax_use_json/getOneAccuracy.action';
            var params = Form.serialize('sa_input');
            var myAjax = new Ajax.Request(url,
                    {
                        method: 'post',
                        parameters: params,
                        onComplete: processShowOneAccuracy,
                        asynchronous: true
                    });
        }
        function processShowOneAccuracy(request) {
            var res = JSON.parse(request.responseText);
<<<<<<< HEAD
            if(res["warning"]=="OK"){
                document.getElementById("sa_outputImg").src ="" + res["filename"] + "?now="+new Date().getTime();

                document.getElementById("sa_outputDate").innerText=res["dateString"];
                document.getElementById('sa_outputNumber').innerText=res["accuracy"];
                if(res["accuracy"]>=0.9500){
                    document.getElementById('sa_outputNumber').style.color="#00FF00";
                    alert("已完成计算。\n预测准确度达标。")
                }else{
                    document.getElementById('sa_outputNumber').style.color="#FF0000";
                    alert("已完成计算。\n预测准确度未达标。")
                }
            }
            else{
                alert("出现错误。\n"+res["warning"]);
=======
            if (res["warning"] == "OK") {
                document.getElementById("sa_outputImg").src = "" + res["filename"] + "?now=" + new Date().getTime();

                document.getElementById("sa_outputDate").innerText = res["dateString"];
                document.getElementById('sa_outputNumber').innerText = res["accuracy"];
                if (res["accuracy"] >= 0.9500) {
                    document.getElementById('sa_outputNumber').style.color = "#00FF00";
                    alert("已完成计算。\n预测准确度达标。")
                } else {
                    document.getElementById('sa_outputNumber').style.color = "#FF0000";
                    alert("已完成计算。\n预测准确度未达标。")
                }
            }
            else {
                alert("出现错误。\n" + res["warning"]);
>>>>>>> origin/develop
            }
        }
        function doShowAllAccuracies() {
            var url = '/ajax_use_json/showAccuracies.action';
            var params = Form.serialize('sas_input');
            var myAjax = new Ajax.Request(url,
                    {
                        method: 'post',
                        parameters: params,
                        onComplete: processShowAllAccuracies,
                        asynchronous: true
                    });
        }
        function processShowAllAccuracies(request) {
            var res = JSON.parse(request.responseText);
<<<<<<< HEAD
            if(res["warning"]=="OK"){
                alert("OK");
                document.getElementById("sas_outputImg").src ="" + res["filename"] + "?now="+new Date().getTime()+"";

            }
            else{
                alert("出现错误。\n"+res["warning"]);
            }
        }
        function setClient(id) {
            var root=document.getElementById("client");
            var elements=root.children;
            for(var i=0;i<root.children.length;i++){
                elements[i].style.visibility="hidden";
            }
            document.getElementById(id).style.visibility="visible";
=======
            if (res["warning"] == "OK") {
                alert("OK");
                document.getElementById("sas_outputImg").src = "" + res["filename"] + "?now=" + new Date().getTime() + "";

            }
            else {
                alert("出现错误。\n" + res["warning"]);
            }
        }
        function setClient(id) {
            var root = document.getElementById("panels");
            var elements = root.children;
            for (var i = 0; i < root.children.length; i++) {
                elements[i].style.visibility = "none";
            }
            document.getElementById(id).style.visibility = "block";
>>>>>>> origin/develop
        }
    </script>
</head>

<<<<<<< HEAD
<body id="me">
<div id="pullDownNavigation">
    <div class="navigation navigation1" data-targetID="pullDownNavigation1">
        <div class="nav">负荷预测</div>
        <div class="pullDownNavigationc" id="pullDownNavigation1">
            <div class="nav" align="center">
                <input type="button" onmouseover="highLight(this);" onmouseout="unLight(this);" onclick="setClient('smartPrediction');" value="智能预测"/>
            </div>
            <div class="nav" >
                <input type="button" onmouseover="highLight(this);" onmouseout="unLight(this);" onclick="setClient('customPrediction');" value="自定义预测"/>
            </div>
        </div>
    </div>
    <div class="navigation navigation1" data-targetID="pullDownNavigation2">
        <div class="nav">历史</div>
        <div class="pullDownNavigationc" id="pullDownNavigation2">
            <div class="nav" align="center">
                <input type="button" onmouseover="highLight(this);" onmouseout="unLight(this);" onclick="setClient('showAccuracy');" value="查看历史准确度"/>
            </div>
            <div class="nav" >
                <input type="button" onmouseover="highLight(this);" onmouseout="unLight(this);" onclick="setClient('showAccuracyStatics');" value="准确度统计"/>
            </div>
        </div>
    </div>
    <div class="navigation navigation1" data-targetID="pullDownNavigation3">
        <div class="nav">反馈</div>
        <div class="pullDownNavigationc" id="pullDownNavigation3">
            <div class="nav" align="center">
                <input type="button" onmouseover="highLight(this);" onmouseout="unLight(this);" onclick="setClient('sendEMailToDeveloper');" value="发送问题报告"/>
            </div>
        </div>
    </div>
</div>

<br>
<div id="client" class="div-relative">
    <div class="div-a" id='smartPrediction' style="visibility: hidden">

        <div class='nav'>
            <s:form class='nav' id='input' action='getDateType' namespace='/ajax_use_json'>
                <h4>选择一个日期。将自动根据您选择的日期执行适合的预测类型。</h4>
                <input type='date' name='dateString' id='dateEdit'
                       style='font-size: larger;font-weight: bold;text-align: center;color: #3F74C1;background-color: #FAFAFA;border-color: #0f0f0f;'/>
                <s:checkbox label="使用缓存" name="useCaches"/>
            </s:form>
        </div>
        <br>

        <div id='output'>
            <h4><a href="CONFIG/temp.xlsx" resource="" id='outputExcel'>点击</a>获取EXCEL输出。</h4>
            <img id='outputImg' >
        </div>
    </div>

    <div class="div-b" id='customPrediction' style="visibility: hidden">
    <h5>自定义预测</h5>

    <div class='nav'>
        <s:form class='nav' id='input' action='getDateType' namespace='/ajax_use_json'>
            <h4>选择一个日期。将自动根据您选择的日期执行适合的预测类型。</h4>
            <input type='date' name='dateString' id='dateEdit'
                   style='font-size: larger;font-weight: bold;text-align: center;color: #3F74C1;background-color: #FAFAFA;border-color: #0f0f0f;'/>
        </s:form>
    </div>
    <br>

    <div id='cp_output'>
        <h4><a href='' id='cp_outputExcel'>点击</a>获取EXCEL输出。</h4>
        <img id='cp_outputImg' src=''/>
    </div>
    </div>

    <div class="div-c" id='showAccuracy' style="visibility: hidden">
        <div class='nav'>
            <s:form class='nav' id='sa_input' action='getOneAccuracy' namespace='/ajax_use_json'>
                <h4>选择一个日期。将输出相应日期的准确度信息。</h4>
                <input type='date' name='dateString' id='sa_dateEdit'
                       style='font-size: larger;font-weight: bold;text-align: center;color: #3F74C1;background-color: #FAFAFA;border-color: #0f0f0f;'/>
            </s:form>
        </div>
        <br>
        <div id='sa_output'>
            <h4>所选日期：</h4><h4 style="color:#0000FF;" id="sa_outputDate"></h4>
            <br>
            <h4>准确度：</h4><h4 id='sa_outputNumber'></h4>
            <img id='sa_outputImg' src='' rel="icon">
        </div>
    </div>

    <div class="div-d" id='showAccuracyStatics' style="visibility: hidden">
        <div class='nav'>
            <input type="button" onclick="doShowAllAccuracies();" />
            <s:form class='nav' id='sas_input'>
                <h4>我要查看最近</h4><input type="text" name="number"/><h4>天的准确度统计。</h4>
                <h4>低于</h4><input type="text" name="threshold"/> <h4>%的准确度定义为不合格。</h4>
            </s:form>
        </div>
        <br>
        <div id='sas_output'>
            <%--<h4>输出过去</h4><h4 id='sas_outputNumber'></h4><h4>天的准确度统计。</h4>--%>
            <%--<h4>其中有</h4><h4 id='sas_outputUnSatiesfiedNumber'></h4><h4>天的准确度统计。</h4>--%>
            <img src=""  id='sas_outputImg' alt="">
            <%--<figure  content="/TEMP/AS97912132.jpg?KEY=VALUE">TEMP/AS97912132.jpg</figure>--%>
        </div>
    </div>

    <div class="div-d" id='sendEMailToDeveloper' style="visibility: hidden">
        <div class='nav'>
            <s:form class='nav' action="sendEMailToDeveloper" namespace="/general">
                <h4>向开发者发送一份电子邮件。服务器的日志将作为附件一并发送。</h4>
                <h4>请在此填写您对该问题的描述。</h4>
                <s:textarea name="details" value=""/>
                <s:submit value="提交问题报告"/>
            </s:form>
        </div>
    </div>
</div>
<br>







<script src="prototype-1.6.0.3.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript" src="json2.js" charset="utf-8"></script>
<link rel="stylesheet" href="index.css"/>
<style>
    .div-a{
        position: absolute;
        left: 0px;
        top:100px;
    }
    .div-b{
        position: absolute;
        left: 0px;
        top:100px;
    }
    .div-c{
        position: absolute;
        left: 0px;
        top:100px;
    }
    .div-d{
        position: absolute;
        left: 0px;
        top:100px;
    }
    .div-relative{
        position: relative;
    }
</style>
</body>

=======
<body>
<div id="appDiv1" style="width: 100%;height: 100%;">
    <div id="apDiv2" style="height:100%;width:20%;left:0%;top:0%;border:solid 1px #101010;background-color: #FFBB00;">
        <div id="panels" style="width:100%;height:80%;top:0%;left:0%;border :solid 1px #101010;">
            <div id="CollapsiblePanel1" class="CollapsiblePanel" onclick="setClient(this.id);" style="width:100%;">
                <div class="CollapsiblePanelTab" tabindex="0">智能预测</div>
                <div class="CollapsiblePanelContent" align="left">
                    <s:form id='input' action='getDateType' namespace='/ajax_use_json'>
                        <h5>选择一个日期。将自动根据您选择的日期执行适合的预测类型。</h5>
                        <input type='date' name='dateString' id='dateEdit'
                               style='width:100%; font-size: larger;font-weight: bold;text-align: center;color: #3F74C1;background-color: #FAFAFA;border-color: #0f0f0f;'/><br>
                        <s:checkbox label="使用缓存" name="useCaches" value="0"/><br>
                        <input name="" type="button" value="执行一次预测" onClick="beginPredict();"/>
                    </s:form>
                    <br>
                    <h5><a href="CONFIG/temp.xlsx" resource="" id='outputExcel'>点击</a>获取EXCEL输出。</h5>
                </div>
            </div>
            <div id="CollapsiblePanel2" class="CollapsiblePanel" onclick="setClient('CollapsiblePanel2');">
                <div class="CollapsiblePanelTab" tabindex="0">自定义预测</div>
                <div class="CollapsiblePanelContent">未实现此功能</div>
            </div>
            <div id="CollapsiblePanel3" class="CollapsiblePanel">
                <div class="CollapsiblePanelTab" tabindex="0">查看准确度</div>
                <div class="CollapsiblePanelContent">
                    <div class='nav'>
                        <s:form class='nav' id='sa_input' action='getOneAccuracy' namespace='/ajax_use_json'>
                            <h5>选择一个日期。将输出相应日期的准确度信息。</h5>
                            <input type='date' name='dateString' id='sa_dateEdit'
                                   style='font-size: larger;font-weight: bold;text-align: center;color: #3F74C1;background-color: #FAFAFA;border-color: #0f0f0f;width:100%;'/>
                        </s:form>
                    </div>
                    <br>

                    <div id='sa_output'>
                        <h5>所选日期：</h5><h5 style="color:#0000FF;" id="sa_outputDate"></h5>
                        <br>
                        <h5>准确度：</h5><h5 id='sa_outputNumber'></h5>
                        <img id='sa_outputImg' src='' rel="icon">
                    </div>
                </div>
            </div>
            <div id="CollapsiblePanel4" class="CollapsiblePanel">
                <div class="CollapsiblePanelTab" tabindex="0">准确度统计</div>
                <div class="CollapsiblePanelContent">内容</div>
            </div>
            <div id="CollapsiblePanel5" class="CollapsiblePanel">
                <div class="CollapsiblePanelTab" tabindex="0">报告问题</div>
                <div class="CollapsiblePanelContent">
                    <div class='nav'>
                        <s:form class='nav' action="sendEMailToDeveloper" namespace="/general">
                            <h5>向开发者发送一份电子邮件。服务器的日志将作为附件一并发送。 请在此填写您对该问题的描述。</h5>
                            <textarea name="details" style="width: 100%;height: 40%;top: 60%;left:0%"></textarea>
                            <s:submit value="提交问题报告"/>
                        </s:form>
                    </div>
                </div>
            </div>
        </div>
        <div id="apDiv8" style="width:100%;height:20%;left:0%;top:80%;border :solid 1px #101010;">
            <h5>测试版本 BETA 1.0</h5>
        </div>
    </div>
    <div id="apDiv3" style="width:80%;height:20%;left:20%;top:0%;border:2px;background-color:#7CBB00;">
        <div id="apDiv5" style="width:20%;height:100%;left:0%;top:0%;border:solid 1px #101010;"></div>
        <div id="apDiv6" style="width:80%;left:20%;height:100%;top:0%;border:solid 1px #101010;">
            <table style="width: 100%;height: 100%;text-align: center;font-size: 100%;" border="1" cellspacing="0">
                <tr>
                    <th style="font-size: 100%" scope="col">日期</th>
                    <th scope="col">最大负荷</th>
                    <th scope="col">最大负荷时刻</th>
                    <th scope="col">最小负荷</th>
                    <th scope="col">最小负荷时刻</th>
                    <th scope="col">平均负荷</th>
                    <th scope="col">日电量</th>
                    <th scope="col">峰谷差</th>
                </tr>
                <tr>
                    <th scope="row">预测日1</th>
                    <td>0.0000</td>
                    <td>0.0000</td>
                    <td>0.0000</td>
                    <td>0.0000</td>
                    <td>0.0000</td>
                    <td>0.0000</td>
                    <td>0.0000</td>
                </tr>
                <tr>
                    <th scope="row">相似日1</th>
                    <td>0.0000</td>
                    <td>0.0000</td>
                    <td>0.0000</td>
                    <td>0.0000</td>
                    <td>0.0000</td>
                    <td>0.0000</td>
                    <td>0.0000</td>
                </tr>
                <tr>
                    <th scope="row">备用</th>
                    <td>0.0000</td>
                    <td>0.0000</td>
                    <td>0.0000</td>
                    <td>0.0000</td>
                    <td>0.0000</td>
                    <td>0.0000</td>
                    <td>0.0000</td>
                </tr>
            </table>
        </div>
    </div>
    <div id="apDiv4" style="width:80%;height:80%;left:20%;top:20%;border:2px #000000;background-color:#0072C6 ;">
        <img src="" id="outputImg" style="width:100%;height: 100%;border : 2px #101010;"/>
    </div>
</div>
<script type="text/javascript">
    var CollapsiblePanel1 = new Spry.Widget.CollapsiblePanel("CollapsiblePanel1");
    var CollapsiblePanel2 = new Spry.Widget.CollapsiblePanel("CollapsiblePanel2");
    var CollapsiblePanel3 = new Spry.Widget.CollapsiblePanel("CollapsiblePanel3");
    var CollapsiblePanel4 = new Spry.Widget.CollapsiblePanel("CollapsiblePanel4");
    var CollapsiblePanel5 = new Spry.Widget.CollapsiblePanel("CollapsiblePanel5");
</script>
</body>
>>>>>>> origin/develop
</html>
