/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author CYBERLAND
 */
public class HomeForm extends Form{

    public HomeForm() {
        
        setTitle("Home");
        setLayout(BoxLayout.y());
        
        add(new Label("Choose an option"));
        Button btnAddOffre = new Button("Ajouter Offre");
        Button btnListeOffre = new Button("Liste Offre");
                Button btnListeOffreEmpl = new Button("Liste Employeur");
                Button btnListeOffreAdmin = new Button("Liste Admin");
                Button btnModifierOffreAdmin = new Button("Modifier Offre");
                Button btnMesCandidature = new Button("Mes Candidatures");
                Button btnLesCandidature = new Button("Les Candidatures");

        btnAddOffre.addActionListener(e-> new AddOffreForm(this).show());
        btnListeOffre.addActionListener(e-> new ListOffreForm(this).show());
        btnListeOffreEmpl.addActionListener(e-> new ListOffreFormEmployeur(this).show());
        btnListeOffreAdmin.addActionListener(e-> new ListOffreFormAdmin(this).show());
        btnMesCandidature.addActionListener(e-> new ListeMesCandidaturesForm(this).show());
        btnLesCandidature.addActionListener(e-> new lesCandidaturesFormEmployeur(this).show());

        addAll(btnAddOffre,btnListeOffre,btnListeOffreEmpl,btnListeOffreAdmin,btnModifierOffreAdmin,btnMesCandidature,btnLesCandidature);
        
        
    }
    
    
}
