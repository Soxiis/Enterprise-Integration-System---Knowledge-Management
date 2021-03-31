<%-- 
    Document   : AuthentificationView
    Created on : 24 mars 2021, 08:43:51
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
        <h1>Authentification</h1>
 
    <%! String s1 = ""; %>
    <% s1 = session.getAttribute("error") == null ? "" : (String) session.getAttribute("error"); %>
    <% if(s1.equals("emptyFiel")){ %>
        <p style="color : red">A field is empty.</div>
    <% }else if(s1.equals("user")){ %>
        <div class="besideemailbox" style="color : red">The informations is invalid.</div>
    <% } %>
 
      <form method="POST" action="${pageContext.request.contextPath}/AuthentificationServlet">
         <input type="hidden" name="redirectId"/>
         <table border="0">
            <tr>
               <td>User Name</td>
               <td><input type="text" name="userName" /> </td>
            </tr>
            <tr>
               <td>Password</td>
               <td><input type="password" name="password"/> </td>
            </tr>
          
            <tr>
               <td colspan ="2">
                  <input type="submit" value= "Submit" />
                  <a href="${pageContext.request.contextPath}/AuthenficationServlet">Cancel</a>
               </td>
            </tr>
         </table>
      </form>
    </body>
</html>
