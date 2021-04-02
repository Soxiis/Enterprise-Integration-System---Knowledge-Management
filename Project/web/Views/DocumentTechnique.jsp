<%-- 
    Document   : DocumentTechnique
    Created on : 2 avr. 2021, 11:17:48
    Author     : Soxis
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
        
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js" integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0" crossorigin="anonymous"></script>
    </head>
    <header>Vous êtes connecter en tant que : ${user.userName}</header>
    <body>
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
        
        Select file to download :
        <br>
         <%@page import="java.io.*" %> 
        <%@page import="java.util.*" %> 
        <%!        public void GetDirectory(String a_Path, Vector a_files, Vector a_folders) {
                File l_Directory = new File(a_Path);
                File[] l_files = l_Directory.listFiles();

                for (int c = 0; c < l_files.length; c++) {
                    if (l_files[c].isDirectory()) {
                        a_folders.add(l_files[c].getName());
                    } else {
                        a_files.add(l_files[c].getName());
                    }
                }


            }
        %> 

        <%
            Vector l_Files = new Vector(), l_Folders = new Vector();
            GetDirectory("/home/ubuntu/Fichiers", l_Files, l_Folders);
            out.println("<music>");
            for (int a = 0; a < l_Files.size(); a++) {

                out.println("<a href=/Project/Fichiers/"+l_Files.elementAt(a).toString()+">"+"<file> " + l_Files.elementAt(a).toString() + "<br>");
            }
            out.println("</music>");
        %> 
    </body>
</html>
