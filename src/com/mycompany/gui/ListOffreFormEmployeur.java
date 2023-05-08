/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gui;

import com.mycompany.services.OffreService;
import com.mycompany.entities.Offre;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;

import com.codename1.ui.FontImage;
import com.codename1.ui.Label;

import com.codename1.ui.layouts.GridLayout;
import com.mycompany.statics.Statics;

import com.codename1.ui.Form;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import java.util.ArrayList;


/**
 *
 * @author CYBERLAND
 */


public class ListOffreFormEmployeur extends Form {

    public ListOffreFormEmployeur(Form previous) {
        setTitle("Liste des Offres Employeur");
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));

        ArrayList<Offre> offre = OffreService.getInstance().getAllOffresEmplo();
        for (Offre o : offre) {
            addElement(o);
        }

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }




public void addElement(Offre offre) {
        int id_offre =offre.getId_offre();

    String titre = offre.getTitre();
    Label titreLabel = new Label("Titre : " + titre);
    
    String description = offre.getDescription();
    Label descriptionLabel = new Label("description : " + description);
    String etat = offre.getEtat();
    Label etatLabel = new Label("etat : " + etat);
    
   
         
    Button ModifierButton = new Button("Modifier");
  ModifierButton.addActionListener(
        e -> new ModifierOffreFromEmplo(this,offre).show()
    );    
   Button SupprimerButton = new Button("Supprimer");
SupprimerButton.addActionListener((evt) -> {
    boolean value = Dialog.show("Confirmation", "Voulez-vous vraiment supprimer cette offre?", "Oui", "Non");
    if (value == true) {
        String url = Statics.BASE_URL +"/offreApiRest/deleteApiRest/"+id_offre;
        ConnectionRequest request = new ConnectionRequest();
        request.setUrl(url);

        request.addResponseListener((NetworkEvent evtt) -> {
            String response = new String(request.getResponseData());
            System.out.println("Offre Supprimé: " + response);

            // Redémarre l'application
ListOffreFormEmployeur.this.show();
        });
        NetworkManager.getInstance().addToQueue(request);
    }
});




       

    Button DetailsButton = new Button("Details");
            DetailsButton.addActionListener(e-> new DetailsEmpForm(this,offre).show());

    
    Container container = new Container(new GridLayout(2, 1));
    container.add(titreLabel);
        container.add(descriptionLabel);
        container.add(etatLabel);
    container.add(BoxLayout.encloseX( DetailsButton,ModifierButton,SupprimerButton));
    
    addComponent(container);

}
    
} 
