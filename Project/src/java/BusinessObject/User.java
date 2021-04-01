/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessObject;

import Util.Role;
import java.util.UUID;

/**
 *
 * @author gabys
 */
public class User {

    private int _id;
    private String _userName = "";
    private String _password  = "";
    private String _lastName = "";
    private String _firstName = "";
    private Role _role;

    public String getUserName() {
        return _userName;
    }

    public void setUserName(String _userName) {
        this._userName = _userName;
    }

    public String getPassword() {
        return _password;
    }

    public void setPassword(String _password) {
        this._password = _password;
    }
    private String _adresse = "";
    private String _mail  = "";
    private String _phone  = "";

    public User() {
        
    }
    
        public User(String _userName, String _password) {
        this._userName = _userName;
        this._password = _password;
    }
    

    public User(int _id, String _lastName, String _firstName, Role _role, String _adresse, String _mail, String _phone) {
        this._id = _id;
        this._lastName = _lastName;
        this._firstName = _firstName;
        this._role = _role;
        this._adresse = _adresse;
        this._mail = _mail;
        this._phone = _phone;
    }
        public User(int _id, String _userName, String _password) {
        this._id = _id;
        this._userName = _userName;
        this._password = _password;
    }
    
    
    public String getLastName() {
        return _lastName;
    }

    public void setLastName(String _lastName) {
        this._lastName = _lastName;
    }

    public String getFirstName() {
        return _firstName;
    }

    public void setFirstName(String _firstName) {
        this._firstName = _firstName;
    }

    public String getAdresse() {
        return _adresse;
    }

    public void setAdresse(String _adresse) {
        this._adresse = _adresse;
    }

    public String getMail() {
        return _mail;
    }

    public void setMail(String _mail) {
        this._mail = _mail;
    }

    public String getPhone() {
        return _phone;
    }

    public void setPhone(String _phone) {
        this._phone = _phone;
    }

    public Role getRole() {
        return _role;
    }

    public void setRole(Role _role) {
        this._role = _role;
    }
    
    public int getId() {
        return _id;
    }

    public void setId(int _id) {
        this._id = _id;
    }
    
}
