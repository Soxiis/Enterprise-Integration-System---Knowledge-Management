/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import BusinessObject.User;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author gabys
 */
public class DAO {
    
    private final String _url="jdbc:mysql://51.83.42.138:3306/database?user=root&password=password";
    private Connection _connection;
    
    //Singleton
    private static final DAO instance = new DAO();
    
    public static DAO getInstance(){
        return instance;
    }
    
    private void connectDataBase(){
        try {
             Class.forName("com.mysql.jdbc.Driver");
            _connection = DriverManager.getConnection("jdbc:mysql://51.83.42.138:3306/database?user=root&password=password");
            System.out.println("Connection to data base");
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
        public User GetUser(String username, String password){
        connectDataBase();
        ResultSet result = null;
        User user = new User();
        String request = "SELECT TOP(1) * FROM users where userName='"+username+"' AND password='"+password+"'";
        try {
            Statement stmt = _connection.createStatement();
            result = stmt.executeQuery(request);
            user = resultatSetToUser(result);
            } catch (SQLException e) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, e);
            }
        return user;
    }
    
    
    public boolean userExists(String username, String password){
        connectDataBase();
        ResultSet result = null;
        User user = new User();
        String request = "SELECT TOP(1) * FROM users where userName='"+username+"' AND password='"+password+"'";
        try {
            _connection = DriverManager.getConnection("jdbc:mysql://51.83.42.138:3306/database?user=root&password=password");
            Statement stmt = _connection.createStatement();
            result = stmt.executeQuery(request);
            int i = result.getRow();
            if (i >= i)
                return true;
            else
                return false;
            } catch (SQLException e) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, e);
            }
        return false;
    }

    private User resultatSetToUser(ResultSet result) throws SQLException{
        User user = new User(result.getInt("id"),result.getString("userName"), result.getString("password"));
        return user;
    }    
    
 
}
