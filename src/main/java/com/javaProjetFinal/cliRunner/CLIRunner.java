package com.javaProjetFinal.cliRunner;

import com.javaProjetFinal.brasserie.Boisson;
import com.javaProjetFinal.brasserie.Brasserie;
import com.javaProjetFinal.brasserie.Employe;
import com.javaProjetFinal.commande.Client;
import com.javaProjetFinal.commande.Commande;

import java.util.*;

public class CLIRunner {

    Brasserie brasserie;

    public CLIRunner() {
        brasserie = createBrasserieContext();
        this.run();
    }

    /** this method creates a brewery to be used
     * @return brewery Guinness
     */
    public Brasserie createBrasserieContext() {
        Brasserie brasserie = new Brasserie("Guinness", "1 rue des étoiles, 59000 Lille");
        brasserie.addEmploye(new Employe("Parker", "Peter", 23, "vendeur"));
        brasserie.addEmploye(new Employe("Stark", "Tony", 47, "patron"));
        brasserie.addEmploye(new Employe("Wayne", "Bruce", 23, "gestionnaire stock"));
        brasserie.receiveBoisson();
        return brasserie;
    }

    /**
     * this method is the main method
     */
    public void run() {
        System.out.println("Bienvenue dans la brasserie " + brasserie.getNom());
        System.out.println("La brasserie vous propose : " + brasserie.getBoissons());
        Client client = getClientFromCLI();
        List<Boisson> choosenBeveries = getChoosenBeveragesFromCLI();
        System.out.println("La brasserie " + brasserie.getNom() + "et ses employés " + brasserie.getEmployes() + "vous remercie d'avoir commandé : " + choosenBeveries);
        Commande commande = new Commande(brasserie,client,choosenBeveries);
        commande.completeTheRecap();
        commande.createInvoice();
    }

    /**
     * @return a client created from the input on the cli
     */
    public Client getClientFromCLI() {
        System.out.println("Veuillez saisir votre identité !");
        Scanner clientCLI = new Scanner(System.in);
        System.out.println("Nom : ");
        String name = clientCLI.nextLine();
        System.out.println("Prénom: ");
        String surname = clientCLI.nextLine();
        System.out.println("Email: ");
        String email = clientCLI.nextLine();
        while(!isAnEmail(email)){
            System.out.println("Veuillez renseigner un email valide");
            System.out.println("Email : ");
            email = clientCLI.nextLine();
        }
        return new Client(name, surname, email);
    }

    /**
     * @param email email to be verified
     * @return we just check that the email provided contains at least a @ and a dot
     */
    public boolean isAnEmail(String email){
        return email.contains("@") && email.contains(".");
    }

    /**
     * @return a list of two beverages from the input on the CLI
     */
    public List<Boisson> getChoosenBeveragesFromCLI() {
        System.out.println("Quelle boissons souhaitez vous acheter ?");
        System.out.println("Choix première boisson : ");
        Scanner boissonCLI = new Scanner(System.in);
        String beveryToBeChecked = boissonCLI.nextLine();
        while (!isBeveryAvailable(beveryToBeChecked)) {
            System.out.println("Boisson indisponible");
            System.out.println("Veuillez choisir parmi ces boissons :");
            System.out.println(brasserie.getBoissons());
            System.out.println("Choix : ");
            beveryToBeChecked = boissonCLI.nextLine();
        }
        Boisson boisson1 = createBoissonFromTheName(beveryToBeChecked);
        System.out.println("Choix deuxième boisson : ");
        String beveryToBeChecked2 = boissonCLI.nextLine();
        while (!isBeveryAvailable(beveryToBeChecked2)) {
            System.out.println("Boisson indisponible");
            System.out.println("Veuillez choisir parmi ces boissons :");
            System.out.println(brasserie.getBoissons());
            System.out.println("Choix : ");
            beveryToBeChecked2 = boissonCLI.nextLine();
        }
        Boisson boisson2 = createBoissonFromTheName(beveryToBeChecked2);
        return Arrays.asList(boisson1, boisson2);
    }

    /**
     * this method checks if the requested bevery is available in the brewery
     *
     * @param beveryToBeChecked the name of the drink to be checked
     * @return a boolean
     */
    public boolean isBeveryAvailable(String beveryToBeChecked) {
        for (Boisson boisson : this.brasserie.getBoissons()) {
            if (beveryToBeChecked.equals(boisson.getNom())) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param beveryName the name that the client has entered in the CLI
     * @return a Boisson instance
     */
    public Boisson createBoissonFromTheName(String beveryName) {
        List<Boisson> boissonList = brasserie.getBoissons();
        for (Boisson boisson : boissonList) {
            if (boisson.getNom().equals(beveryName)) {
                return boisson;
            }
        }
        return null;
    }



}
