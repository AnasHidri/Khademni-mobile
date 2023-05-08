package com.mycompany.gui;

import com.mycompany.services.OffreService;
import com.mycompany.entities.Offre;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import java.util.ArrayList;
import com.mycompany.statics.Statics;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author CYBERLAND
 */
public class DetailsClientForm extends Form {
      public DetailsClientForm(Form previous) {
        setTitle("Liste des Offres ");
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));

        ArrayList<Offre> offre = OffreService.getInstance().getAllOffres();
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
    Label descriptionLabel = new Label("Description : " + description);
    

      String Adresse = offre.getAdresse_societe();
    Label adresseLabel = new Label("Adresse  : " + Adresse);
     
    String domaine = offre.getDomaine_offre();
    Label domaineLabel = new Label("Domaine Offre : " + domaine);
         
   Button PostulerButton = new Button("Postuler");
PostulerButton.addActionListener((evt) -> {
    System.out.println("testinh"+id_offre);
    String url = Statics.BASE_URL + "/candidatureApiRest/" + id_offre + "/postulerApiRest";

    ConnectionRequest request = new ConnectionRequest();
    request.setUrl(url);
    
    request.addResponseListener((NetworkEvent evtt) -> {
        String response = new String(request.getResponseData());
        System.out.println("Offre Postuler " + response);
        Dialog.show("Succès", "Vous avez postulé à l'offre avec succès.", "OK", null);
    });
    NetworkManager.getInstance().addToQueue(request);
});


     Button AnnulerPostulerButton = new Button("Annuler la Postulation");
AnnulerPostulerButton.addActionListener((evt) -> {
    System.out.println("testinh"+id_offre);
    String url = Statics.BASE_URL + "/candidatureApiRest/" + id_offre + "/deleteApiRest";

    ConnectionRequest request = new ConnectionRequest();
    request.setUrl(url);

    request.addResponseListener((NetworkEvent evtt) -> {
        String response = new String(request.getResponseData());
        System.out.println("Offre Postuler " + response);

        // Display an alert to inform the user that their application has been canceled
        Dialog.show("Annulation de postulation", "Votre postulation a été annulée.", "OK", null);
    });

    NetworkManager.getInstance().addToQueue(request);
});

    Container container = new Container(new GridLayout(4, 1));

 container.add(titreLabel);
       
        container.add(domaineLabel);
 container.add(descriptionLabel); container.add(adresseLabel);

    container.add(BoxLayout.encloseX( PostulerButton,AnnulerPostulerButton));
    
    addComponent(container);
}    
}
