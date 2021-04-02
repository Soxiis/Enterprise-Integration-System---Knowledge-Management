/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import static Servlet.UploaderServlet.TAILLE_TAMPON;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Utilisateur
 */
public class Download extends HttpServlet {
    
    private static final int DEFAULT_BUFFER_SIZE = 10240; // 10 ko

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Download</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Download at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String chemin = this.getServletConfig().getInitParameter( "chemin" );
        /* Récupération du chemin du fichier demandé au sein de l'URL de la requête */
        String fichierRequis = request.getPathInfo();
        /* Vérifie qu'un fichier a bien été fourni */
        if ( fichierRequis == null || "/".equals( fichierRequis ) ) {
        /* Si non, alors on envoie une erreur 404, qui signifie que la ressource demandée n'existe pas */
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        
        /* Décode le nom de fichier récupéré, susceptible de contenir des espaces et autres caractères spéciaux, et prépare l'objet File */
        fichierRequis = URLDecoder.decode( fichierRequis, "UTF-8");
        File fichier = new File( chemin, fichierRequis );
        
    /* Vérifie que le fichier existe bien */
    if ( !fichier.exists() ) {
    /* Si non, alors on envoie une erreur 404, qui signifie que la ressource demandée n'existe pas */
    response.sendError(HttpServletResponse.SC_NOT_FOUND);
    return;
    }
    /* Récupère le type du fichier */
    String type = getServletContext().getMimeType( fichier.getName() );

    /* Si le type de fichier est inconnu, alors on initialise un type par défaut */
    if ( type == null ) {
        type = "application/octet-stream";
    }
    
    /* Initialise la réponse HTTP */
    response.reset();
    response.setBufferSize( DEFAULT_BUFFER_SIZE );
    response.setContentType( type );
    response.setHeader( "Content-Length", String.valueOf( fichier.length() ) );
    response.setHeader( "Content-Disposition", "attachment; filename=\"" + fichier.getName() + "\"" );
    
    /* Prépare les flux */
    BufferedInputStream entree = null;
    BufferedOutputStream sortie = null;
    try {
    /* Ouvre les flux */
    entree = new BufferedInputStream( new FileInputStream( fichier ), TAILLE_TAMPON );
    sortie = new BufferedOutputStream( response.getOutputStream(), TAILLE_TAMPON );
 
    /* Lit le fichier et écrit son contenu dans la réponse HTTP */
    byte[] tampon = new byte[TAILLE_TAMPON];
    int longueur;
    while ( ( longueur= entree.read( tampon ) ) > 0 ) {
    sortie.write( tampon, 0, longueur );
}
} finally {
    try {
        sortie.close();
    } catch ( IOException ignore ) {
    }
    try {
        entree.close();
    } catch ( IOException ignore ) {
    }
}
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
