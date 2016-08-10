<%--
  Created by IntelliJ IDEA.
  User: zhangqiang
  Date: 2016/7/25
  Time: 16:44
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>list</title>
</head>
<body>
<h1>你好:${userInfo.username}</h1>
<p>
<h1>db data:</h1></p>
<c:forEach items="${testBoList}" var="item" varStatus="status">
    <p>
    <h1>${item.name} ${item.remark}</h1>
    </p>
</c:forEach>
<h1>redis data:</h1>
<p>
<h1>${redisData}</h1></p>
<p>
<h1>request avg time:${avgMap.avgTime}ms</h1></p>
<p>
<table style="border: solid 1px">
    <tr>
        <td>action</td>
        <td>avgTime/ms</td>
        <td>count</td>
    </tr>
    <c:forEach items="${avgMap.singleAction}" var="item" varStatus="status">
    <tr>
        <td>${item.action}</td>
        <td>${item.avgTime}</td>
        <td>${item.count}</td>
    </tr>
    </c:forEach>
</table>
</p>
</body>
</html>
