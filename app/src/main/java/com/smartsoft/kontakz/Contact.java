package com.smartsoft.kontakz;

import java.io.Serializable;

public class Contact implements Serializable {
    private String prenom;
    private String nom;
    private String phone;

    public Contact(String prenom, String nom, String phone) {
        this.prenom = prenom;
        this.nom = nom;
        this.phone = phone;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
