/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gui;

import com.mycompany.services.CandidatureService;
import com.mycompany.services.OffreService;
import com.mycompany.entities.Candidature;
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
import com.codename1.ui.layouts.BoxLayout;
import java.util.ArrayList;
/**
 *
 * @author CYBERLAND
 */
public class ListeMesCandidaturesForm extends Form {
    
      public ListeMesCandidaturesForm(Form previous) {
        setTitle("Liste Mes Candidatures");
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));

        ArrayList<Candidature> candidature = CandidatureService.getInstance().getMesCandidatures();
        for (Candidature c : candidature) {
            addElement(c);
        }

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }




public void addElement(Candidature candidature) {

    int id_candidature = candidature.getId_candidature();
    Label id_candidatureLabel = new Label("Id_candidatutre : " + id_candidature);

    String etat = candidature.getEtat();
    Label etatLabel = new Label("Etat : " + etat);

    
      
    Container container = new Container(new GridLayout(3, 1));
    container.add(id_candidatureLabel);
    container.add(etatLabel);

    addComponent(container);
}
}