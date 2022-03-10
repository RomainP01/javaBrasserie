package com.javaProjetFinal.brasserie;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Brasserie {
    private String nom;
    private String adresse;
    private ArrayList<Employe> listEmploye = new ArrayList<>();
    private ArrayList<Boisson> listBoissons = new ArrayList<>();

    /**
     * @param nom     name of the brewery
     * @param adresse address of the brewery
     */
    public Brasserie(String nom, String adresse) {
        this.nom = nom;
        this.adresse = adresse;
    }


    /**
     * this method create a list of beveries from a file boissons.txt
     */
    public void receiveBoisson() {
        try{
            File recu = new File("src/main/resources/boissons.txt");
            BufferedReader bufferedReader = new BufferedReader(new FileReader(recu));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] words = line.split(" ");
                this.listBoissons.add(new Boisson(words[0],words[1]));
            }
        }catch (IOException e ){
            System.out.println("An error occured");
            e.printStackTrace();
        };

    }

    /**
     * @param employe add an employee to the brewery
     */
    public void addEmploye(Employe employe) {
        listEmploye.add(employe);
    }

    public ArrayList<Employe> getEmployes() {
        return listEmploye;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public List<Boisson> getBoissons(){
        return listBoissons;
    }

}
