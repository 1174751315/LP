<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  ~ 版权所有 (c) 2015 。 李倍存 （iPso）。
  ~ 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
  ~ 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
  --%>
<%@ page contentType="text/html; ISO-8859-1;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="CONFIG/favicon.ico" rel="shortcut icon" type="image/vnd.microsoft.icon" />
    <title>MAVEN-MAILLING</title>
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

        function highLight(o) {
            o.style.borderColor="#0000FF";
            o.style.borderWidth="2pt";
        }
        function unLight(o) {
            o.style.borderColor="#FFFFFF";
            o.style.borderWidth="2pt";
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
                document.getElementById("outputExcel").href = res["root"] + res["xlFileName"] +"?now="+new Date().getTime();
//                document.getElementById("outputImg").src = "javascript:void(0)";
                document.getElementById("outputImg").src = res["root"] + res["imgFileName"] + "?now="+new Date().getTime();
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
        }
    </script>
</head>

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
</div>

<br>
<div id="client" class="div-relative">
    <div class="div-a" id='smartPrediction' style="visibility: hidden">

        <div class='nav'>
            <s:form class='nav' id='input' action='getDateType' namespace='/ajax_use_json'>
                <h4>选择一个日期。将自动根据您选择的日期执行适合的预测类型。</h4>
                <input type='date' name='dateString' id='dateEdit'
                       style='font-size: larger;font-weight: bold;text-align: center;color: #3F74C1;background-color: #FAFAFA;border-color: #0f0f0f;'/>
                <s:checkbox label="使用缓存" name="useCaches" value="true"/>
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

</html>
