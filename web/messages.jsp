<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%--
  User: Ray
  Date: 14-5-16
  Time: 下午2:16
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>消息日志</title>
    <!-- DataTables CSS -->
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/resources/datatables/css/jquery.dataTables.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/resources/datatables/tableTools/css/dataTables.tableTools.css">

    <!-- jQuery -->
    <script type="text/javascript" charset="utf8"
            src="${pageContext.request.contextPath}/resources/js/jquery-1.11.1.js"></script>

    <!-- DataTables -->
    <script type="text/javascript" charset="utf8"
            src="${pageContext.request.contextPath}/resources/datatables/js/jquery.dataTables.js"></script>
    <script type="text/javascript" charset="utf8"
            src="${pageContext.request.contextPath}/resources/datatables/tableTools/js/dataTables.tableTools.js"></script>

    <style type="text/css">
        .center {
            text-align: center;
        }
    </style>
</head>
<body>

<table id="example" class="display" cellspacing="0" width="100%">
    <thead>
    <tr>
        <th>ID</th>
        <th>学号</th>
        <th>时间</th>
        <th>动作</th>
        <th>内容</th>
        <th>状态</th>
        <th>创建时间</th>
        <%--<th>最近更新时间</th>--%>
    </tr>
    </thead>

    <tfoot>
    <tr>
        <th>ID</th>
        <th>学号</th>
        <th>时间</th>
        <th>动作</th>
        <th>内容</th>
        <th>状态</th>
        <th>创建时间</th>
        <%--<th>最近更新时间</th>--%>
    </tr>
    </tfoot>
</table>
</body>

<script>

    var table;
    $(document).ready(function () {

        table = $('#example').DataTable({
            "processing": true,
            "serverSide": true,
            "ajax": {
                url:"msg/query",
                type:"POST",
                contentType:"application/json",
                data:function(d){
                    return JSON.stringify(d)
                }
            },
            "columns": [
                { "data": "MSG_ID", defaultContent: ""},
                { "data": "TO_USER", defaultContent: "", "class": "center"},
                { "data": "MSG_TIME", defaultContent: "", "class": "center"},
                { "data": "ACT", defaultContent: "", "class": "center"},
                { "data": "CONTENT", defaultContent: ""},
                { "data": "STATUS", defaultContent: "", "class": "center"},
                { "data": "CREATION_DATE", defaultContent: "", "class": "center"}
            ],
            language: {
                url: 'resources/datatables/json/dataTable.json'
            }
        });

    });

</script>
</html>