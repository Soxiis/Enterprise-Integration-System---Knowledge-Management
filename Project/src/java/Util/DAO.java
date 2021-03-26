/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author gabys
 */
public class DAO {
    
    private final String _url="dbc:mysql://51.83.42.138:3306/database?user=root&password=root";
    private Connection _connection;
    
    public Connection connect(){
        try {
            _connection = DriverManager.getConnection("jdbc:mysql://51.83.42.138:3306/database?" + "user=root&password=root");
            System.out.println("Bienvenue sur internet");
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return _connection;
    }
    
    public void GetUser(){
        
    }    
    
 
}
