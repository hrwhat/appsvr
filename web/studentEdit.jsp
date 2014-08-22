<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%--
  User: Administrator
  Date: 2014/8/22 0022
  Time: 下午 6:46
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学生信息</title>
    <script type="text/javascript" charset="utf8"
            src="${pageContext.request.contextPath}/resources/js/jquery-1.11.1.js"></script>
    <style type="text/css">
        table th {
            text-align: right;
        }
    </style>
</head>
<body>
<table align="center">
    <tr>
        <th>学号：</th>
        <td><input type="text" id="studentNo" value="${param.studentNo}"/></td>
    </tr>
    <tr>
        <th>姓名：</th>
        <td><input type="text" id="studentName" value="${param.studentName}"/></td>
    </tr>
    <tr>
        <th>OPEN_ID：</th>
        <td>${openId}</td>
    </tr>
    <tr>
        <td colspan="2" align="center"><input type="button" value="保存" class="DTTT_button" onclick="save()"/></td>
    </tr>
</table>
<input type="hidden" id="studentId" value="${param.studentId}"/>
</body>
<script type="text/javascript">
    function save() {
        $.post('${pageContext.request.contextPath}/student/save',
                {
                    studentNo: $("#studentNo").val(),
                    studentName: $("#studentName").val(),
                    studentId: $("#studentId").val()
                },
                function (res) {
                    window.returnValue = 1;
                    window.close()
                })
    }

    $(document).ready(function () {

    })
</script>
</html>