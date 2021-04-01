<%-- 
    Document   : HomeView
    Created on : 24 mars 2021, 10:01:40
    Author     : gabys
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <header>
            Home Page
        </header>
        <ul>
            <c:forEach items="${_categories}" var="category">
                <li>${category.id}<li>
            </c:forEach>
        </ul>
        <form action="${pageContext.request.contextPath}/HomeServlet" method="POST">
            <input type="submit" name="button" value= "Add" />
        </form>

    </body>
</html>
