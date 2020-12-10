<%--
  Created by IntelliJ IDEA.
  User: 可爱的小栩
  Date: 2020/11/28
  Time: 23:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security"  %>
<html>
<head>
    <title>Title</title>
</head>
<body>
        <h1>销售管理系统</h1>
<form action="${pageContext.request.contextPath}/login" method="post">
            <security:csrfInput/>
            账户:<input type="text" name="username"/><br>
            密码:<input type="password" name="password"/><br>
            <input type="submit" value="submit"/>
        </form>
</body>
</html>
