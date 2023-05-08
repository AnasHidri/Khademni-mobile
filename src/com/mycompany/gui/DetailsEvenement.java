/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Evenement;
import com.mycompany.services.ParticipationService;

/**
 *
 * @author user
 */
public class DetailsEvenement extends Form {

    public DetailsEvenement(Resources res, Evenement evenement) {
        super("Détails de l'événement", new BoxLayout(BoxLayout.Y_AXIS));
        
        // Créer les composants graphiques pour afficher les détails de l'événement
        Label titreLabel = new Label("Titre : " + evenement.getTitre());
        Label descriptionLabel = new Label("Description : " + evenement.getDescription());
        Label nomSocieteLabel = new Label("Société organisatrice : " + evenement.getNom_societe());
        Label lieuLabel = new Label("Lieu : " + evenement.getLieu());
        
        // Ajouter les composants graphiques à la page
        this.add(titreLabel);
        this.add(descriptionLabel);
        this.add(nomSocieteLabel);
        this.add(lieuLabel);
        
        // Créer un bouton pour fermer la page
        Button closeButton = new Button("Retour a la liste");
        closeButton.addActionListener(e -> {
            new ListeEvenement(res).show();
        });
        
        // Ajouter le bouton à la page
        this.add(closeButton);
        
        // Ajouter un bouton pour permettre la modification de l'événement
        Button editButton = new Button("Modifier");
        editButton.addActionListener(e -> {
            new ModifierEvenement(res, evenement).show();
        });
        
        // Ajouter le bouton à la page
        this.add(editButton);
        
        Button participationButton = new Button("Participer");
participationButton.addActionListener(e -> {
    Dialog dig = new Dialog("Participation");
            
            if(dig.show("Participation","Vous voulez participer a cet evenement ?","Annuler","Oui")) {
                dig.dispose();
            }
            else {
                dig.dispose();
                 }
    ParticipationService.getInstance().ajoutParticipation(evenement.getId_evenement());
    new MesParticipations(res).show();
});
this.add(participationButton);

 Button annulationButton = new Button("Annuler la participation");
annulationButton.addActionListener(e -> {
    Dialog dig = new Dialog("Annulation");
            
            if(dig.show("Annulation","Vous voulez annuler votre participation ?","Annuler","Oui")) {
                dig.dispose();
            }
            else {
                dig.dispose();
                 }
    ParticipationService.getInstance().annulerParticipation(evenement.getId_evenement());
});
this.add(annulationButton);


}
    
}
