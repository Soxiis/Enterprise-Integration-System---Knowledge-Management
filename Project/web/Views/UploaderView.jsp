<%-- 
    Document   : UploaderView
    Created on : 1 avr. 2021, 18:00:19
    Author     : Utilisateur
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
         <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js" integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0" crossorigin="anonymous"></script>
   <header>Vous êtes connecter en tant que : ${user.userName}</header>
        <script>
            /* Cette fonction permet d'afficher une vignette pour chaque image sélectionnée */
            function readFilesAndDisplayPreview(files) {
                let imageList = document.querySelector('#list'); 
                imageList.innerHTML = "";
                
                for ( let file of files ) {
                    let reader = new FileReader();
                    
                    reader.addEventListener( "load", function( event ) {
                        let span = document.createElement('span');
                        span.innerHTML = '<img height="60" src="' + event.target.result + '" />';
                        imageList.appendChild( span );
                    });

                    reader.readAsDataURL( file );
                }
            }
        </script>
    </head>
    <body style="text-align: center">
        
          
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
            
        
        
        <header>
            <h1>Choose you file to import. : </h1>
        </header>
        
        
            <form method="POST" action="${pageContext.request.contextPath}/UploaderServlet" enctype="multipart/form-data">
            Fichiers sélectionnés : 
            <input type="file" name="fichier" accept="application/*" multiple
                   onchange="readFilesAndDisplayPreview(this.files);" /> <br/>
            <input type="submit" name="button" value="Upload" /> <br/>        
            
            <div id="list"></div>   
        </form>
    </body>
</html>
