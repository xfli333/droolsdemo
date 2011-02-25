<%@ page import="cn.lee.demo.drools.model.RuleModel" %>
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
    <hr width="97.5%" align="left">
    <form name="form" action="./" method="post">
        <input type="hidden" name="id" value="${ruleModel.id}"/>
        <input type="hidden" name="version" value="${ruleModel.version}"/>
        <table>
            <tr>
                <td>ruleName:</td>
                <td>
                    <input type="text" id="ruleName" name="ruleName" lang="20" value="${ruleModel.ruleName}"/>
                </td>
                <td>ruleType:</td>
                <td>
                    <input type="text" id="ruleType" name="ruleType" lang="20" value="${ruleModel.ruleType}"/></td>
            </tr>

            <tr>
                <td>firstRole:</td>
                <%
                   RuleModel ruleModel=(RuleModel)request.getAttribute("ruleModel");
                    if(ruleModel.isFirstRole()){
                 %>
                <td><input type="checkbox" id="firstRole" name="firstRole" checked="true" lang="20"/></td>
                <%
                    }else{
                   %>
                <td><input type="checkbox" id="firstRole" name="firstRole"  lang="20"/></td>
                <%
                    }
                %>

                <td>actived:</td>
                <%
                    if(ruleModel.isActived()){
                 %>
                <td><input type="checkbox" id="actived" name="actived" checked="true" lang="20"/></td>
                <%
                    }else{
                   %>
                <td><input type="checkbox" id="actived" name="actived"  lang="20"/></td>
                <%
                    }
                %>
            </tr>
            <tr>

                <td>当前条件:</td>
                <td><%=ruleModel.getCustomizeRule()%></td>
                <td>自定义条件</td>
                <td><input type="text" id="customizeRule" name="customizeRule" lang="20" value="<%=ruleModel.getCustomizeRule()%>"/></td></td>
                </tr>
            <tr>
                <td>ruleContent:</td>
                <td colspan="3"><textarea rows="15" cols="120" id="ruleContent" name="ruleContent">
                    ${ruleModel.ruleContent}
                </textarea>

            </tr>
        </table>
        <br>
        <button type="button" value="保存" class="sexybutton" onclick="doPost('add')"><span><span><span
                class="save">保存</span></span></span>
        </button>

        <button type="button" value="新建" class="sexybutton" onclick="doPost('new')"><span><span><span
                class="save">新建</span></span></span>
        </button>

    </form>
</div>
</body>
</html>