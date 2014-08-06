<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%--
  User: Ray
  Date: 14-5-16
  Time: 下午2:16
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学生信息</title>
    <!-- DataTables CSS -->
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/resources/datatables/css/jquery.dataTables.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/resources/datatables/tableTools/css/dataTables.tableTools.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/resources/datatables/editor/css/dataTables.editor.css">

    <!-- jQuery -->
    <script type="text/javascript" charset="utf8"
            src="${pageContext.request.contextPath}/resources/js/jquery-1.11.1.js"></script>

    <!-- DataTables -->
    <script type="text/javascript" charset="utf8"
            src="${pageContext.request.contextPath}/resources/datatables/js/jquery.dataTables.js"></script>
    <script type="text/javascript" charset="utf8"
            src="${pageContext.request.contextPath}/resources/datatables/tableTools/js/dataTables.tableTools.js"></script>
    <script type="text/javascript" charset="utf8"
            src="${pageContext.request.contextPath}/resources/datatables/editor/js/dataTables.editor.js"></script>
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
        <th>姓名</th>
        <th>学号</th>
        <th>OPEN_ID</th>
        <th>创建时间</th>
        <th>最近更新时间</th>
    </tr>
    </thead>

    <tfoot>
    <tr>
        <th>姓名</th>
        <th>学号</th>
        <th>OPEN_ID</th>
        <th>创建时间</th>
        <th>最近更新时间</th>
    </tr>
    </tfoot>
</table>
</body>

<script>

    var editor;
    var table;
    $(document).ready(function () {
//        $('#table_id').dataTable();
        createEditor();


        table = $('#example').DataTable({
            dom: "Tfrtip",
            sort: false,
            "processing": true,
            "serverSide": true,
            "ajax": "student/query",
            "columns": [
                { "data": "NAME", defaultContent: ""},
                { "data": "STUDENT_NO", defaultContent: "", "class": "center", editor: "text"},
                { "data": "OPEN_ID", defaultContent: "", "class": "center"},
                { "data": "CREATION_DATE", defaultContent: "", "class": "center"},
                { "data": "LAST_UPDATE_DATE", defaultContent: "", "class": "center"}
            ],
            tableTools: {
                sRowSelect: "os",
                aButtons: [
                    { sExtends: "editor_create", editor: editor, sButtonText: "新增" },
                    { sExtends: "editor_edit", editor: editor, sButtonText: "修改" },
                    { sExtends: "text", sButtonText: "删除", fnClick: function () {
                        remove()
                    } }
                ]
            }, language: {
                url: 'resources/datatables/json/dataTable.json'
            }
        });

    });


    function createEditor() {
        editor = new $.fn.dataTable.Editor({
            ajax: "student/edit",
            table: "#example",
            "idSrc": "STUDENT_ID",
            fields: [
                {
                    label: "ID:",
                    name: "STUDENT_ID",
                    type: "readonly"
                },
                {
                    label: "姓名:",
                    name: "NAME"
                },
                {
                    label: "学号 :",
                    name: "STUDENT_NO"
                },
                {
                    label: "OPEN_ID:",
                    name: "OPEN_ID"
                }
            ]
        });
    }
    function remove() {

        var rows = table.rows('.selected');
        var data = rows.data();
        if (data.length == 0) {
            alert("请先选择至少一条数据。")
        } else {
            if (confirm("确定删除选择的数据吗？")) {
                var ids = [];
                for (var i = 0; i < data.length; i++) {
                    ids.push(data[i].STUDENT_ID);
                }
                $.post("student/edit", {action: "remove", ids: ids.join(",")}, function (res) {
                    rows.remove().draw()
                })
            }
        }


    }
</script>
</html>