/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import BusinessObject.Category;
import Util.DAO;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author Utilisateur
 */
public class UploaderServlet extends HttpServlet {
    
    private static final String DOCUMENT_FOLDER = "/Documents";
    public static final String CHAMP_DESCRIPTION = "description";
public static final String CHAMP_FICHIER     = "fichier";
public static final String CHEMIN        = "chemin";
    public static final int TAILLE_TAMPON = 10240; 
    private DAO _dao;    
    private String uploadPath;
    private List<String> _categorieList = new LinkedList();
    
    
        @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        uploadPath = getServletContext().getRealPath(DOCUMENT_FOLDER);
        File uploadDir = new File(uploadPath);
        uploadDir.getParentFile().mkdirs(); // correct!
        if (!uploadDir.exists()) {
            try {
                uploadDir.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(UploaderServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
}

    } 

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
            out.println("<title>Servlet UploaderServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UploaderServlet at " + request.getContextPath() + "</h1>");
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
//        _dao = DAO.getInstance();
//        _categorieList =  _dao.getCategory();

        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/Views/UploaderView.jsp");
        dispatcher.forward(request, response);
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
            String button = request.getParameter("but");
        if (button != null && button.equals("add")) {
            String cat = request.getParameter("cat");
            if(cat.equals("process") && _categorieList.isEmpty()){
                _categorieList.add(cat);
                request.getSession().setAttribute("categoriesToString",cat);
                RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/Views/UploaderView.jsp");
                dispatcher.forward(request, response);
            }
        } else {
            String chemin = this.getServletConfig().getInitParameter(CHEMIN);

            /* Récupération du contenu du champ de description */
            String description = request.getParameter(CHAMP_DESCRIPTION);
            request.setAttribute(CHAMP_DESCRIPTION, description);

            /*
     * Les données reçues sont multipart, on doit donc utiliser la méthode
     * getPart() pour traiter le champ d'envoi de fichiers.
             */
            Part part = request.getPart(CHAMP_FICHIER);

            /*
     * Il faut déterminer s'il s'agit d'un champ classique 
     * ou d'un champ de type fichier : on délègue cette opération 
     * à la méthode utilitaire getNomFichier().
             */
            String nomFichier = getNomFichier(part);

            /*
     * Si la méthode a renvoyé quelque chose, il s'agit donc d'un champ
     * de type fichier (input type="file").
             */
            if (nomFichier != null && !nomFichier.isEmpty()) {
                String nomChamp = part.getName();
                /*
         * Antibug pour Internet Explorer, qui transmet pour une raison
         * mystique le chemin du fichier local à la machine du client...
         * 
         * Ex : C:/dossier/sous-dossier/fichier.ext
         * 
         * On doit donc faire en sorte de ne sélectionner que le nom et
         * l'extension du fichier, et de se débarrasser du superflu.
                 */
                nomFichier = nomFichier.substring(nomFichier.lastIndexOf('/') + 1)
                        .substring(nomFichier.lastIndexOf('\\') + 1);

                /* Écriture du fichier sur le disque */
                ecrireFichier(part, nomFichier, chemin);

                request.setAttribute(nomChamp, nomFichier);
                
                _dao = DAO.getInstance();
                _dao.saveDocument(nomFichier, chemin);
                
                response.sendRedirect(request.getContextPath()+"/HomeServlet");
            }
        }

    }

    
    private static String getNomFichier( Part part ) {
    /* Boucle sur chacun des paramètres de l'en-tête "content-disposition". */
    for ( String contentDisposition : part.getHeader( "content-disposition" ).split( ";" ) ) {
        /* Recherche de l'éventuelle présence du paramètre "filename". */
        if ( contentDisposition.trim().startsWith( "filename" ) ) {
            /*
             * Si "filename" est présent, alors renvoi de sa valeur,
             * c'est-à-dire du nom de fichier sans guillemets.
             */
            return contentDisposition.substring( contentDisposition.indexOf( '=' ) + 1 ).trim().replace( "\"", "" );
        }
    }
    /* Et pour terminer, si rien n'a été trouvé... */
    return null;
}
        
        /*
 * Méthode utilitaire qui a pour but d'écrire le fichier passé en paramètre
 * sur le disque, dans le répertoire donné et avec le nom donné.
 */
private void ecrireFichier( Part part, String nomFichier, String chemin ) throws IOException {
    /* Prépare les flux. */
    BufferedInputStream entree = null;
    BufferedOutputStream sortie = null;
    try {
        /* Ouvre les flux. */
        entree = new BufferedInputStream( part.getInputStream(), TAILLE_TAMPON );
        sortie = new BufferedOutputStream( new FileOutputStream( new File( chemin + nomFichier ) ),
                TAILLE_TAMPON );

        /*
         * Lit le fichier reçu et écrit son contenu dans un fichier sur le
         * disque.
         */
        byte[] tampon = new byte[TAILLE_TAMPON];
        int longueur;
        while ( ( longueur = entree.read( tampon ) ) > 0 ) {
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
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
