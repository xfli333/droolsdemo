<%@ page import="cn.lee.demo.drools.model.HospitalRecords" %>
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
    <h2>${hospitalRecords.ruleMsg}</h2>
    <font color="red">${hospitalRecords.wrongMsg}</font>
    <hr width="97.5%" align="left">
    <form name="form" action="./" method="post">
        <input type="hidden" name="id" value="${hospitalRecords.id}"/>
        <input type="hidden" name="version" value="${hospitalRecords.version}"/>
        <table>
            <tr>
                <td>identityCard:</td>
                <td>
                    <input type="text" id="identityCard" name="identityCard" lang="20" value="${hospitalRecords.identityCard}"/>
                </td>
                <td>name:</td>
                <td>
                    <input type="text" id="name" name="name" lang="20" value="${hospitalRecords.name}"/></td>
            </tr>

            <tr>
                <td>sex:</td>
                <td><input type="text" id="sex" name="sex" value="${hospitalRecords.sex}" lang="20"/></td>
                <td>menstrual:</td>
                <td><input type="text" id="menstrual" name="menstrual" value="${hospitalRecords.menstrual}" lang="20"/></td>
            </tr>
            <tr>
                <td>fertility:</td>
                <td><input type="text" id="fertility" name="fertility" value="${hospitalRecords.fertility}" lang="20"/></td>
                <td>others:</td>
                <td><input type="text" id="others" name="others" value="${hospitalRecords.others}" lang="20"/></td>
            </tr>
        </table>
        <br>
        <button type="button" value="保存" class="sexybutton" onclick="doPost('add')"><span><span><span
                class="save">保存</span></span></span>
        </button>
        
    </form>
</div>
</body>
</html>