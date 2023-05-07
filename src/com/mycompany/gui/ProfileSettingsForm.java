/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gui;

import com.codename1.components.FloatingActionButton;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.materialscreens.StatsForm;
import com.mycompany.entities.User;
import static com.mycompany.gui.ListUsers.isOnlyLetters;
import com.mycompany.services.UtilisateurService;

/**
 *
 * @author ASUS
 */
public class ProfileSettingsForm extends SideMenuBaseFormUsers {
    
        private UtilisateurService userService= new UtilisateurService();
        
    public ProfileSettingsForm(Resources res) {
        super(BoxLayout.y());
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        Image profilePic = res.getImage("img3.png");
        Image mask = res.getImage("round-mask.png");
        profilePic = profilePic.fill(mask.getWidth(), mask.getHeight());
        Label profilePicLabel = new Label(profilePic, "ProfilePicTitle");
        profilePicLabel.setMask(mask.createMask());

        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());

String nomCurrent=User.getCurrent_User().getNom();
String prenomCurrent=User.getCurrent_User().getPrenom();
String roleCurrent=User.getCurrent_User().getRole();
float soldeCurrent=User.getCurrent_User().getSolde();



        
if(roleCurrent.equals("Client")){
        Container titleCmp = BoxLayout.encloseY(
                        FlowLayout.encloseIn(menuButton),
                        BorderLayout.centerAbsolute(
                                BoxLayout.encloseY(
                                    new Label(nomCurrent+" "+prenomCurrent, "Title"),
                                    new Label(roleCurrent, "SubTitle"),
                                    new Label("Solde : "+soldeCurrent, "SubTitle")
                                )
                            )
                );
        
            FloatingActionButton fab = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);
                fab.getAllStyles().setMarginUnit(Style.UNIT_TYPE_PIXELS);
                fab.getAllStyles().setMargin(BOTTOM,2 );
                tb.setTitleComponent(titleCmp);
                Container img = new Container(new FlowLayout(CENTER, CENTER));
            img.add(profilePicLabel);
        titleCmp.add(img);
}else{
            Container titleCmp = BoxLayout.encloseY(
                        FlowLayout.encloseIn(menuButton),
                        BorderLayout.centerAbsolute(
                                BoxLayout.encloseY(
                                    new Label(nomCurrent+" "+prenomCurrent, "Title"),
                                    new Label(roleCurrent, "SubTitle") 
                                )
                            )
                );
            
            FloatingActionButton fab = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);
                fab.getAllStyles().setMarginUnit(Style.UNIT_TYPE_PIXELS);
                fab.getAllStyles().setMargin(BOTTOM,2 );
                tb.setTitleComponent(titleCmp);
Container img = new Container(new FlowLayout(CENTER, CENTER));
            img.add(profilePicLabel);
        titleCmp.add(img);
}

        
        User user=User.getCurrent_User();
//Form updateForm = new Form("Update User", BoxLayout.y());


    // create the text fields and set their values to the user's current data
    TextField nomField = new TextField(user.getNom());
    Container nomContainer = new Container(new BorderLayout());
    nomContainer.add(BorderLayout.WEST, new Label("Nom:"));
    nomContainer.add(BorderLayout.CENTER, nomField);

    TextField prenomField = new TextField(user.getPrenom());
    Container prenomContainer = new Container(new BorderLayout());
    prenomContainer.add(BorderLayout.WEST, new Label("Prenom:"));
    prenomContainer.add(BorderLayout.CENTER, prenomField);

    TextField mailField = new TextField(user.getMail(),"", CENTER, TextField.EMAILADDR);
    Container mailContainer = new Container(new BorderLayout());
    mailContainer.add(BorderLayout.WEST, new Label("Email:"));
    mailContainer.add(BorderLayout.CENTER, mailField);

    ComboBox<String> domaineField = new ComboBox<>("Web Programming", "Game Development", "Digital Marketing", "Video & Animation", "Data Engineering");
    Container domaineContainer = new Container(new BorderLayout());
    domaineContainer.add(BorderLayout.WEST, new Label("Domaine:"));
    domaineContainer.add(BorderLayout.CENTER, domaineField);

    
    // create the update button and add an action listener to handle the update
    Button updateButton = new Button("Modifier");
    
    TextField currentPasswordField = new TextField("", "", CENTER, TextField.PASSWORD);
    Container currentPasswordContainer = new Container(new BorderLayout());
    currentPasswordContainer.add(BorderLayout.WEST, new Label("Mot de passe actuel :"));
    currentPasswordContainer.add(BorderLayout.CENTER, currentPasswordField);
    
    TextField passwordField = new TextField("", "", CENTER, TextField.PASSWORD);
    Container passwordContainer = new Container(new BorderLayout());
    passwordContainer.add(BorderLayout.WEST, new Label("Nouveau mot de passe :"));
    passwordContainer.add(BorderLayout.CENTER, passwordField);

    TextField confirmpasswordField = new TextField("", "", CENTER, TextField.PASSWORD);
    Container confirmpasswordContainer = new Container(new BorderLayout());
    confirmpasswordContainer.add(BorderLayout.WEST, new Label("Confirmer mot de passe :"));
    confirmpasswordContainer.add(BorderLayout.CENTER, confirmpasswordField);
    
    
        // set the foreground color of the text in the text fields
    nomField.getAllStyles().setFgColor(0x000000);
    prenomField.getAllStyles().setFgColor(0x000000);
    mailField.getAllStyles().setFgColor(0x000000);
    domaineField.getAllStyles().setFgColor(0x000000);
    currentPasswordField.getAllStyles().setFgColor(0x000000);
    passwordField.getAllStyles().setFgColor(0x000000);
    confirmpasswordField.getAllStyles().setFgColor(0x000000);
    
    
    Button updatePassButton = new Button("Modifier mot de passe");


        // add the update button to the form
    Container buttonsContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
    buttonsContainer.add(updateButton);
    //updateForm.add(buttonsContainer);
    
    Container buttonsPassContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
    buttonsPassContainer.add(updatePassButton);
    
    



Container container = new Container(new BoxLayout(BoxLayout.Y_AXIS));
container.add(nomContainer);
container.add(prenomContainer);
container.add(mailContainer);
container.add(domaineContainer);
container.add(buttonsContainer);
container.add(currentPasswordContainer);
container.add(passwordContainer);
container.add(confirmpasswordContainer);
container.add(buttonsPassContainer);

  add(container);
      
        
    updatePassButton.addActionListener(e -> {
        // get the updated values from the text fields
        String currentPassword = currentPasswordField.getText().trim();
        String password = passwordField.getText().trim();
        String confirmpassword = confirmpasswordField.getText().trim();
               
         // validate input
        if (currentPassword.isEmpty() || password.isEmpty() || confirmpassword.isEmpty() ) {
            Dialog.show("Erreur", "Veuillez remplir tous les champs", "OK", null);
            return;
        }

        if (!password.equals(confirmpassword)) {
            Dialog.show("Erreur", "Le nouveau mot de passe et la confirmation du mot de passe ne correspondent pas.", "OK", null);
        }else{
             
        // create a new user object with the updated data
        User u = User.getCurrent_User();
       
        // call the userService to update the user
        boolean isPassUpdated = userService.updateUserPass(u,currentPassword,password);

        if (isPassUpdated) {
            Dialog.show("Succès", "Votre mot de passe est mis à jour avec succès", "OK", null);
            // refresh the user list after updating the user
            //new ListUsers(res).show();
        } else {
            Dialog.show("Erreur", "Échec de la mise à jour de votre mot de passe ", "OK", null);
        }
        }
    });
    
        updateButton.addActionListener(e -> {
        // get the updated values from the text fields
        String nom = nomField.getText().trim();
        String prenom = prenomField.getText().trim();
        String mail = mailField.getText().trim();
        int selectedIndex = domaineField.getSelectedIndex();
        String domaine = selectedIndex >= 0 ? domaineField.getModel().getItemAt(selectedIndex) : null;        
         // validate input
        if (nom.isEmpty() || prenom.isEmpty() || mail.isEmpty() ) {
            Dialog.show("Error", "Veuillez remplir tous les champs", "OK", null);
            return;
        }
        if (!mail.contains("@") || !mail.contains(".")) {
            Dialog.show("Error", "Veuillez entrer un email valide", "OK", null);
            return;
        }
        if (!isOnlyLetters(nom)) {
            Dialog.show("Error", "Veuillez entrer un nom valide", "OK", null);
            return;
        }
        if (!isOnlyLetters(prenom)) {
            Dialog.show("Error", "Veuillez entrer un prenom valide", "OK", null);
            return;
        }
        // create a new user object with the updated data
        User updatedUser = new User(user.getIdUser(), nom, prenom, mail, domaine);

        // call the userService to update the user
        boolean isUpdated = userService.updateUser(updatedUser);

        if (isUpdated) {
            Dialog.show("Succès", "Utilisateur mis à jour avec succès", "OK", null);
            // refresh the user list after updating the user
            //new ListUsers(res).show();
        } else {
            Dialog.show("Erreur", "Échec de la mise à jour de l'utilisateur", "OK", null);
        }

    });
        
        setupSideMenu(res);
    }
    @Override
    protected void showOtherForm(Resources res) {
        new StatsForm(res).show();
    }
}
