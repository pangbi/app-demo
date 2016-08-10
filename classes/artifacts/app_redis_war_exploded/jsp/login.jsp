<%--
  Created by IntelliJ IDEA.
  User: zhangqiang
  Date: 2016/7/25
  Time: 16:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login</title>
</head>
<body>
<h1>login..</h1>
<br/>
<form action="/doLogin" method="post">
    <input type="text" name="username"/>
    <input type="password" name="password"/>
    <input type="submit" value="登录" />
</form>
</body>
</html>
