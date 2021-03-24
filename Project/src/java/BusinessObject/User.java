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

    private UUID _id;
    private String _lastName;
    private String _firstName;
    private Role _role;
    private String _adresse;
    private String _mail;
    private String _phone;

    public User(UUID _id, String _lastName, String _firstName, Role _role, String _adresse, String _mail, String _phone) {
        this._id = _id;
        this._lastName = _lastName;
        this._firstName = _firstName;
        this._role = _role;
        this._adresse = _adresse;
        this._mail = _mail;
        this._phone = _phone;
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
    
    public UUID getId() {
        return _id;
    }

    public void setId(UUID _id) {
        this._id = _id;
    }
    
}