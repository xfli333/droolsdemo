<%@ page import="cn.lee.demo.drools.model.RuleModel" %>
<%@ page import="cn.lee.demo.drools.rule.DroolsUtils" %>
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
    <script type="text/javascript" src="<%=request.getContextPath()%>/ruleModel/map.js"></script>
</head>
<body>

<script type="text/javascript">
        function doPost(url) {
            document.form.action += url;
            document.form.submit();
        }
   </script>

<%
    RuleModel rm=(RuleModel)request.getAttribute("ruleModel");
    String[] customizeRules= DroolsUtils.splitCustomizeRuleToArray(rm.getCustomizeRule());
   
%>

<div id="wrapper" class="centralPanel">
    <hr width="97.5%" align="left">
    <form name="form" action="./" method="post">
        <input type="hidden" name="id" value="${ruleModel.id}"/>
        <input type="hidden" name="version" value="${ruleModel.version}"/>
        <table>
            <tr>
                <td>ruleName:</td>
                <td>
                    ${ruleModel.ruleName}
                </td>
                <td>ruleType:</td>
                <td>
                   ${ruleModel.ruleType}
            </tr>
            <tr>

                <td>当前条件:</td>
                <td><%=customizeRules[0]+"  "+DroolsUtils.symbolMap.get(customizeRules[1])+"  "+customizeRules[2]%></td>
                <td>自定义条件</td>

                <td>
                    <input type="text" id="ruleField" name="ruleField" value="<%=customizeRules[0]%>" readonly="true"/>
                    <select name="symbol">
                        <option value="!=" <%if("!=".equals(customizeRules[1])){%>selected="true"<%}%>>不等于</option>
                        <option value="==" <%if("==".equals(customizeRules[1])){%>selected="true"<%}%>>等于</option>
                        <option value=">" <%if(">".equals(customizeRules[1])){%>selected="true"<%}%>>大于</option>
                        <option value="<" <%if("<".equals(customizeRules[1])){%>selected="true"<%}%>>小于</option>
                    </select>
                    <input type="text" id="ruleValue" name="ruleValue" value='<%=customizeRules[2]%>' />
                    </td>
                    <%--<input type="text" id="customizeRule" name="customizeRule" lang="20" value='${ruleModel.customizeRule}'/></td></td>--%>
                </tr>
            <tr>
            
            <tr>
                <td>ruleContent:</td>
                <td colspan="3"><textarea rows="15" cols="120" id="ruleContent" name="ruleContent" readonly="true">
                    ${ruleModel.ruleContent}
                </textarea>

            </tr>
        </table>
        <br>
        <button type="button" value="更新" class="sexybutton" onclick="doPost('update')"><span><span><span
                class="save">更新</span></span></span>
        </button>



    </form>
</div>
</body>
</html>