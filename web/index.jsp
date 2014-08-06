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

    <link rel="stylesheet" href="//jquery.com/jquery-wp-content/themes/jquery/css/base.css?v=1">
    <style type="text/css">
        body {
            background: #0769AD;
        }
    </style>
    <script type="text/javascript" language="javascript">

        function iFrameHeight() {
                alert(1)
            var ifm= document.getElementById("mainFrame");

            var bHeight = ifm.contentWindow.document.body.scrollHeight;
            var dHeight = ifm.contentWindow.document.documentElement.scrollHeight;
            alert("bHeight:" + bHeight + ", dHeight:" + dHeight);

            var subWeb = document.frames ? document.frames["mainFrame"].document :

                    ifm.contentDocument;


            if (ifm.contentDocument && ifm.contentDocument.body.offsetHeight) {

                ifm.height = ifm.contentDocument.body.offsetHeight;

            } //ie

            else if (ifm.Document && ifm.Document.body.scrollHeight) {

                ifm.height = ifm.Document.body.scrollHeight;

            }
        }

    </script>
</head>
<body>
<div>
    <nav id="main" class="constrain clearfix">
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
    </nav>

</div>
<div class="constrain clearfix" style="padding: 0;background: #ffffee">
<iframe id="mainFrame" name="mainFrame" src="" width="100%" height="550"></iframe>
</div>
</body>
</html>