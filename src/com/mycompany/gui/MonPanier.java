/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gui;

import com.mycompany.services.PanierService;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.uikit.materialscreens.SideMenuBaseForm;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.materialscreens.ProfileForm;
import entity.Ligne_commande;
import java.util.ArrayList;

/**
 *
 * @author mikea
 */
public class MonPanier extends SideMenuBaseForm {

    @Override
    protected void showOtherForm(Resources res) {
        new ProfileForm(res).show();
    }
    
 public MonPanier(Resources res) {
    super(BoxLayout.y());
  ArrayList<Ligne_commande> lignes = PanierService.getInstance().getAllLigne();
  Form myForm = new Form("MonPanier", new TableLayout(lignes.size() + 2, 4));
    // Create and add the header components
    TextField prixMinField = new TextField("", "Prix min", 20, TextField.ANY);
    prixMinField.getUnselectedStyle().setFgColor(0x000000);
    TextField prixMaxField = new TextField("", "Prix max", 20, TextField.ANY);
    prixMaxField.getUnselectedStyle().setFgColor(0x000000);
    Button filtrerButton = new Button("Filtrer");
    
    
     filtrerButton.addActionListener(evt -> {
         int prixMin = Integer.parseInt(prixMinField.getText());
        int prixMax = Integer.parseInt(prixMaxField.getText());
              ArrayList<Ligne_commande> lignes2 = PanierService.getInstance().FiltrerCommande(prixMin, prixMax);
              myForm.removeAll();
    
    // Add table header row
    myForm.add(new Label("Titre"));
    myForm.add(new Label("Prix"));
    myForm.add(new Label("Paiement"));
    myForm.add(new Label("Suppression"));

    // Add filtered data rows
    for (Ligne_commande ligne : lignes2) {
        myForm.add(new Label(ligne.getTitre()));
        myForm.add(new Label(String.valueOf(ligne.getPrix())));

        Button button = new Button("Payer");

        button.addActionListener(evt2 -> {
            int idLigneCommande = ligne.getId_ligne_commande();
            boolean test = PanierService.getInstance().PayerCommande(idLigneCommande);
            System.out.println(test);
        });

        Button button2 = new Button("Supprimer");

        button2.addActionListener(evt2 -> {
            int idLigneCommande = ligne.getId_ligne_commande();
            boolean test = PanierService.getInstance().deleteCommande(idLigneCommande);
            System.out.println(test);
        });

        myForm.add(button);
        myForm.add(button2);
    }
    
    // Revalidate the container to update the layout
    myForm.revalidate();
           
        });

    Container header = GridLayout.encloseIn(3, prixMinField, prixMaxField, filtrerButton);
    add(header);

  

    

    // Add table header row
    myForm.add(new Label("Titre"));
    myForm.add(new Label("Prix"));
    myForm.add(new Label("Paiement"));
    myForm.add(new Label("Suppression"));

    // Add table data rows
    for (Ligne_commande ligne : lignes) {
        myForm.add(new Label(ligne.getTitre()));
        myForm.add(new Label(String.valueOf(ligne.getPrix())));

        Button button = new Button("Payer");

        button.addActionListener(evt -> {
            int idLigneCommande = ligne.getId_ligne_commande();
            boolean test = PanierService.getInstance().PayerCommande(idLigneCommande);
            System.out.println(test);
        });

        Button button2 = new Button("Supprimer");

        button2.addActionListener(evt -> {
            int idLigneCommande = ligne.getId_ligne_commande();
            boolean test = PanierService.getInstance().deleteCommande(idLigneCommande);
            System.out.println(test);
        });

        myForm.add(button);
        myForm.add(button2);
    }

    // Add the table to the container
    add(myForm);
}


    
}
