package com.javaProjetFinal.commande;

import com.javaProjetFinal.brasserie.Boisson;
import com.javaProjetFinal.brasserie.Brasserie;

import java.io.*;
import java.util.List;

public class Commande {
    private Brasserie brasserie;
    private Client client;
    private List<Boisson> boissonList;
    private Integer NUMBER_OF_INVOICE;

    /**
     * @param brasserie   the brewery that has sold the beveries to the client
     * @param client      the client that has bought the beveries from the brewery
     * @param boissonList the list of the beveries bought by the client at the brewery
     */
    public Commande(Brasserie brasserie, Client client, List<Boisson> boissonList) {
        this.brasserie = brasserie;
        this.client = client;
        this.boissonList = boissonList;
        this.NUMBER_OF_INVOICE=0;
    }

    /**
     * this method create a HTML file to represent the command.
     */
    public void createInvoice() {
        Integer num = getNUMBER_OF_INVOICE();
        this.setNUMBER_OF_INVOICE(num+=1);
        try {
            File f = new File("out/invoices/" + this.brasserie.getNom() + "-" + NUMBER_OF_INVOICE + ".html");
            BufferedWriter bw = new BufferedWriter(new FileWriter(f));
            File invoiceTemplate = new File("src/main/resources/invoiceTemplate.html");
            BufferedReader br = new BufferedReader(new FileReader(invoiceTemplate));
            String page = "";
            String line;
            while ((line = br.readLine()) != null) {
                page += line + System.lineSeparator();
            }
            String beverages = "";
            for (Boisson boisson : this.boissonList) {
                beverages += "<p>" + boisson.getNom() + "(" + boisson.getType() + ")" + "</p>";

            }
            String invoiceName = this.brasserie.getNom() + "-" + NUMBER_OF_INVOICE;
            String newInvoice = page.replaceAll("INVOICE_NAME", invoiceName)
                    .replaceAll("INVOICE_BREWERY_NAME", this.brasserie.getNom())
                    .replaceAll("INVOICE_NUMBER", String.valueOf(this.NUMBER_OF_INVOICE))
                    .replaceAll("INVOICE_CLIENT_NAME", this.client.getNom())
                    .replaceAll("INVOICE_CLIENT_SURNAME", this.client.getPrenom())
                    .replaceAll("INVOICE_BEVERAGES", beverages);
            bw.write(newInvoice);
            br.close();
            bw.close();

        } catch (
                IOException e) {
            System.out.println("an error has occured");
            e.printStackTrace();
        }


    }


    /**
     * this method complete the file commandesRecap with some informations about the client
     */
    public void completeTheRecap() {
        try {
            File commandesRecap = new File("out/commandesRecap.txt");
            BufferedWriter bw = new BufferedWriter(new FileWriter(commandesRecap, true));
            String newInvoice = this.getClient().getNom() + "-" + this.getClient().getPrenom() + ":" + this.getBoissonList().get(0) + "," + this.getBoissonList().get(1);
            bw.write("\n" + newInvoice);
            bw.close();

        } catch (IOException e) {
            System.out.println("An error occured");
            e.printStackTrace();
        }
        ;
    }

    public Brasserie getBrasserie() {
        return brasserie;
    }

    public void setBrasserie(Brasserie brasserie) {
        this.brasserie = brasserie;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Boisson> getBoissonList() {
        return boissonList;
    }

    public void setBoissonList(List<Boisson> boissonList) {
        this.boissonList = boissonList;
    }

    public Integer getNUMBER_OF_INVOICE() {
        return NUMBER_OF_INVOICE;
    }

    public void setNUMBER_OF_INVOICE(Integer NUMBER_OF_INVOICE) {
        this.NUMBER_OF_INVOICE = NUMBER_OF_INVOICE;
    }
}
