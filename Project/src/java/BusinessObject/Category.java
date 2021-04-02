/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessObject;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Utilisateur
 */
public class Category implements Serializable {
    
    private String name;
    private int id;
    private List<Document> documentList;

    public Category() {
    }
    

    public Category(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public Category(String name, List<Document> documentList) {
        this.name = name;
        this.documentList = documentList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Document> getDocumentList() {
        return documentList;
    }

    public void setDocumentList(List<Document> documentList) {
        this.documentList = documentList;
    }
    
}
