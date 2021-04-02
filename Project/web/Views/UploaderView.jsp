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
        
        <header>
            <h1>Choose you file to import.</h1>
        </header>
        
        <form method="POST" action="${pageContext.request.contextPath}/UploaderServlet">
            <table>
                <tr>
                   <td>Categories</td>
                   <td><input type="text" name="cat" /> </td>
                   <td><input type="submit" name="but" value="add"/><td>
                    <%! String s1 = ""; %>
                    <% s1 = session.getAttribute("categoriesToString") == null ? "" : (String)session.getAttribute("categoriesToString"); %>
                           <% if(s1.equals("process")){ %>
                           <td><p style="color : blue">Category : Process</td></div>
                           <% } %>
                </tr>
            </table>
        </form>
            <form method="POST" action="${pageContext.request.contextPath}/UploaderServlet" enctype="multipart/form-data">
            Fichiers sélectionnés : 
            <input type="file" name="fichier" accept="application/*" multiple
                   onchange="readFilesAndDisplayPreview(this.files);" /> <br/>
            <input type="submit" name="button" value="Upload" /> <br/>        
            
            <div id="list"></div>   
        </form>
    </body>
</html>
