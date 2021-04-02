<%-- 
    Document   : HomeView
    Created on : 24 mars 2021, 10:01:40
    Author     : gabys
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js" integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0" crossorigin="anonymous"></script>
        <title>JSP Page</title>
    </head>
    <body>
        <header>
            Vous êtes connecter en tant que : ${user.userName}
        </header>
        
        
            
            <div class="container-fluid">
            <div class="row">
                <div class="col-md-12">
                    <h3 class="text-center">
                        Knowledge Management
                    </h3>
                    <nav class="navbar navbar-expand-lg navbar-light bg-light navbar-dark bg-dark">
                        <div class="navbar-collapse collapse w-100 order-1 order-md-0 dual-collapse2" id="bs-example-navbar-collapse-1">
                            <ul class="navbar-nav">
                                <li class="nav-item active">
                                    <a class="nav-link" href="/Project/AuthentificationServlet">Authentification</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="/Project/DocumentTechnique">Visualiser les documents Techniques</a>
                                </li>
                            </ul>
                        </div>
                        
                        
                        <div class="collapse navbar-collapse"  id="navbarNav">
                            <form class="form-inline navbar-nav mr-auto">
                                <input class="form-control mr-sm-2 " type="text" /> 
                                <button class="btn btn-primary my-2 my-sm-0" type="submit">
                                    Search
                                </button>
                            </form>
                        </div>
                    </nav>
                </div>
            </div>
        </div>
            
        <table align="center">
            <thead>
                <tr>
                    <th colspan="2" style="font-family: Arial Narrow; font-size: 32pt; font-weight: bold" >Catégories :</th>
                    
                    Click on the button to add documents.
                    
                    <form action="${pageContext.request.contextPath}/HomeServlet" method="POST">
    <input type="submit" name="button" value= "Add" />
</form>
                </tr>
            </thead>
            <tbody>
                <tr>
            <p> <c:forEach items="${_categories}" var="category">
                <td> <a href="${category.name}"> ${category.name} </a></td>
            </c:forEach>
        </p>
        <td></td>
    </tr>
</tbody>
</table>    
        
            
            
    </body>
</html>
