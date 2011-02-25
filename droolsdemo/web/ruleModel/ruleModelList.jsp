<%@ page import="cn.lee.demo.drools.model.RuleModel" %>
<%@ page import="java.util.List" %>
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
<%
    List<RuleModel> rms = (List<RuleModel>) request.getAttribute("rms");
%>
<script type="text/javascript">
    function doPost(url) {
        document.form.action += url;
        document.form.submit();
    }
   
</script>

<div id="wrapper" class="centralPanel">
    <hr width="97.5%" align="left">
    <form name="form" action="./" method="post">

        <table border="1">
            <tr>
                <td></td>
                <td>规则名称</td>
                <td>规则类型</td>
                <td>自定义规则</td>
                <td>头文件</td>
                <td>是否启用</td>
            </tr>


            <%
                for (RuleModel rm : rms) {
            %>
            <tr>
                <td><input type="radio" name="id" value="<%=rm.getId()%>"></td>
                <td><%=rm.getRuleName() %>
                </td>
                <td><%=rm.getRuleType() %>
                </td>
                <td><%=rm.getCustomizeRule() %>
                </td>
                <td><%=rm.isFirstRole() %>
                </td>
                <td><%=rm.isActived() %>
                </td>
            </tr>
            <%
                }
            %>

        </table>
        <br>
        <button type="button" value="启用规则" class="sexybutton" onclick="doPost('enable')"><span><span><span
                class="save">启用规则</span></span></span>
        </button>

        <button type="button" value="禁用规则" class="sexybutton" onclick="doPost('disable')"><span><span><span
                class="save">禁用规则</span></span></span>
        </button>

        <button type="button" value="维护规则" class="sexybutton" onclick="doPost('edit')"><span><span><span
                class="save">维护规则</span></span></span>
        </button>
         <a href="<%=request.getContextPath()%>">返回</a>
    </form>
</div>
</body>
</html>