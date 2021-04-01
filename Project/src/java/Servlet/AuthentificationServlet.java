package Servlet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import BusinessObject.User;
import Util.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author gabys
 */
public class AuthentificationServlet extends HttpServlet {
    
    private DAO _dao;
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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AuthenficationServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AuthenficationServlet at " + request.getContextPath() + "</h1>");
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
 
      RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/Views/AuthentificationView.jsp");
 
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
            boolean isConnect = false;
            String userName = request.getParameter("userName");
            String password = request.getParameter("password");
            _dao = DAO.getInstance();
            if (userName.isEmpty() || password.isEmpty())
                request.getSession().setAttribute("error", "emptyFiel");
            else
                if (_dao.userExists(userName, password))
                    isConnect = connect(request, userName, password);
            if (isConnect)
                response.sendRedirect(request.getContextPath()+"/HomeServlet");
            else{
                request.getSession().setAttribute("error", "user");
                RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/Views/AuthentificationView.jsp");
                dispatcher.forward(request, response);
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

    private boolean connect(HttpServletRequest request, String username, String password ) {

        User user = _dao.GetUser(username, password);
        if(user != null && !user.getUserName().equals("") ){
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            return true;
        }
        else{
            
            return false;
        }
    }

}
