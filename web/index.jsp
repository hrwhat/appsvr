<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%--
  User: Ray
  Date: 14-5-16
  Time: 下午2:16
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Index</title>

    <link rel="stylesheet" href="resources/css/base.css">
    <style type="text/css">
        body {
            background: #0769AD;
        }
    </style>
    <script type="text/javascript" language="javascript">

        function iFrameHeight() {
            var ifm= document.getElementById("mainFrame");
            var bHeight = ifm.contentWindow.document.body.scrollHeight;
            var dHeight = ifm.contentWindow.document.documentElement.scrollHeight;
//            alert("bHeight:" + bHeight + ", dHeight:" + dHeight);
            ifm.height = Math.min(bHeight,dHeight) + 15;

        }

    </script>
</head>
<body>
<div>
    <div id="main" class="constrain clearfix">
        <div class="menu-top-container">
            <ul id="menu-top" class="menu">

                <li class="menu-item"><a href="students.jsp" target="mainFrame">学生信息</a> </li>
                <li class="menu-item"><a href="messages.jsp" target="mainFrame">消息日志</a> </li>
            </ul>
        </div>

       <%-- <form method="get" class="searchform" action="//jqueryui.com/">
            <button type="submit" class="icon-search"><span class="visuallyhidden">search</span></button>
            <label>
                <span class="visuallyhidden">Search jQuery UI</span>
                <input type="text" name="s" value=""
                       placeholder="Search">
            </label>
        </form>--%>
    </div>

</div>
<div class="constrain clearfix" style="padding: 0;background: #ffffee">
<iframe id="mainFrame" name="mainFrame" src="students.jsp" width="100%" scrolling="no"></iframe>
</div>
</body>
</html>