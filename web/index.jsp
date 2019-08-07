<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %><%--
  Created by IntelliJ IDEA.
  User: GO
  Date: 2019/7/18
  Time: 14:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
  <%
    Date now = new Date();
    pageContext.setAttribute("now",now);
  %>
<fmt:formatDate value="${now}" pattern="yyyy-MM-dd HH:mm:ss"/><br>
${now}
