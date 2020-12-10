<%--
  Created by IntelliJ IDEA.
  User: 可爱的小栩
  Date: 2020/12/4
  Time: 21:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%response.setHeader("X-Frame-Options", "SAMEORIGIN");%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table border="1" cellspacing="0" cellpadding="3">
    <tr>
        <th>清单Id</th>
        <th>状态</th>
        <th>查看货物</th>
        <th>生成发货单</th>
        <th>生成进货单</th>
        <th>修改</th>
    </tr>
   <%-- <c:forEach items="${requestScope.allUsers }" var="g">
    <tr>
        <td>${g.id}</td>
        <td>${g.username}</td>
        <td>${g.password}</td>
        <td><a href="${pageContext.request.contextPath }/goods/edit/${g.id}">update</a></td>
        <td><a href="${pageContext.request.contextPath }/goods/delete/${g.id} ">delete</a></td>
    </tr>
    </c:forEach>--%>
</body>
</html>
