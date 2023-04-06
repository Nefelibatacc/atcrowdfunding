<%--
  Created by IntelliJ IDEA.
  User: fengs
  Date: 2023/4/5
  Time: 20:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<script type="text/javascript">
    $(function () {
       $("#asyncBtn").click(function () {

           console.log("ajax函数之前");

           $.ajax({
              "url":"/test/ajax/async.html",
              "type":"post",
              "dataType":"text",
              "success":function (response) {
                  console.log("ajax函数内部的success函数"+response);
              }
           });

           console.log("ajax函数之后");
       });
    });
</script>

<body>

    <button id="asyncBtn">发送Ajax请求</button>

</body>
</html>
