<%@ page import="cn.lee.demo.drools.model.SecondDiagnosis" %>
<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 10-12-8
  Time: 下午8:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Simple jsp page</title>
</head>
<body>

<script type="text/javascript">
    function doPost(url) {
        document.form.action += url;
        document.form.submit();
    }
</script>

<div id="wrapper" class="centralPanel">
    <h3>上一张单数据(parts需一致)</h3>

    <p>identityCard:${secondDiagnosis.fd.identityCard}</p>

    <p>name:${secondDiagnosis.fd.name}</p>

    <p>parts:${secondDiagnosis.fd.parts}</p>

    <h1>${secondDiagnosis.ruleMsg}</h1>
    <font color="red">${secondDiagnosis.wrongMsg}</font>
    <hr width="97.5%" align="left">
    <form name="form" action="./" method="post">
        <input type="hidden" name="id" value="${secondDiagnosis.id}"/>
        <input type="hidden" name="version" value="${secondDiagnosis.version}"/>
        <table>
            <tr>
                <td>identityCard:</td>
                <td>
                    <input type="text" id="identityCard" name="identityCard" lang="20"
                           value="${secondDiagnosis.identityCard}"/>
                </td>
                <td>name:</td>
                <td>
                    <input type="text" id="name" name="name" lang="20" value="${secondDiagnosis.name}"/></td>
            </tr>

            <tr>
                <td>parts:</td>
                <td><input type="text" id="parts" name="parts" value="${secondDiagnosis.parts}" lang="20"/></td>
            </tr>

        </table>
        <br>
        <button type="button" value="保存" class="sexybutton" onclick="doPost('add')"><span><span><span
                class="save">保存</span></span></span>
        </button>
        <a href="<%=request.getContextPath()%>">返回</a>
    </form>
</div>
</body>
</html>