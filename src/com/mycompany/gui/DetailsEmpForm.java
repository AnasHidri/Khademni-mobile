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
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import java.util.ArrayList;
import com.mycompany.statics.Statics;

/**
 *
 * @author CYBERLAND
 */

 
    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */





/**
 *
 * @author CYBERLAND
 */


public class DetailsEmpForm extends Form {
    public DetailsEmpForm(Form previous,Offre offre) {
        setTitle("Liste des Offres Détaillées");
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));


    String titre = offre.getTitre();
    Label titreLabel = new Label("Titre : " + titre);
    
    String description = offre.getDescription();
    Label descriptionLabel = new Label("Description : " + description);
    
    String etat = offre.getEtat();
    Label etatLabel = new Label("Etat : " + etat);
    
      String Adresse = offre.getAdresse_societe();
    Label adresseLabel = new Label("Adresse  : " + Adresse);
     
    String domaine = offre.getDomaine_offre();
    Label domaineLabel = new Label("Domaine Offre : " + domaine);
         
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
            Container container = new Container(new GridLayout(2, 1));
 
        container.add(titreLabel);
        container.add(descriptionLabel);
        container.add(adresseLabel);
         container.add(domaineLabel);
        container.add(etatLabel);
            addComponent(container);

    }


}
