package com.javaProjetFinal.brasserie;

public class Boisson {
    private String nom;
    private String type;

    /**
     * @param nom name of the beverage
     * @param type type of the beverage
     */
    public Boisson(String nom, String type) {
        this.nom = nom;
        this.type = type;
    }



    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return nom+"("+type+")";
    }
}
