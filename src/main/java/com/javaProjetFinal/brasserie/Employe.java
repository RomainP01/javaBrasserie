package com.javaProjetFinal.brasserie;

import java.util.Arrays;
import java.util.List;

/**
 * An employe that works in a brewery
 * He can be a boss, the seller, the inventory manager
 */
public class Employe {
    private String nom;
    private String prenom;
    private Integer age;
    private String poste;

    /**
     * @param nom name of the employee
     * @param prenom surname of the employee
     * @param age age of the employee
     * @param poste the job of the employee, it can only be a boss, a seller or a inventory manager
     *              else he is set as undefined
     */
    public Employe(String nom, String prenom, Integer age, String poste) {
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        List<String> posteDisponible = Arrays.asList("patron", "vendeur", "gestionnaire stock");
        if (posteDisponible.contains(poste)){
            this.poste = poste;
        }else{
            this.poste = "inconnue";
        }
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    @Override
    public String toString() {
        return prenom;
    }
}
