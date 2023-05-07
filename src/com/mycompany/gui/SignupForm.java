/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gui;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.List;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.list.DefaultListCellRenderer;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.materialscreens.LoginForm;
import com.mycompany.entities.User;
import static com.mycompany.gui.ListUsers.isOnlyLetters;
import com.mycompany.services.UtilisateurService;
import com.mycompany.statics.Statics;

/**
 *
 * @author ASUS
 */
public class SignupForm extends Form {
        private UtilisateurService userService= new UtilisateurService();

    public SignupForm(Resources theme) {
        super(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
        setUIID("LoginForm");
        Container welcome = FlowLayout.encloseCenter(
                new Label("Bienvenu à , ", "WelcomeWhite"),
                new Label("Khademni", "WelcomeBlue")
        );
        
        getTitleArea().setUIID("Container");
        
        
        
      /*  TextField login = new TextField("", "Identifiant", 20, CENTER) ;
        TextField password = new TextField("", "Mot de passe", 20, TextField.PASSWORD) ;
        /*
        
        
        */
         // create the text fields and set their values to the user's current data
         
         /*               Role     */
         ComboBox<String> roleField = new ComboBox<>("Client", "Formateur", "Employeur");
        Container roleContainer = new Container(new BorderLayout());
        Label roleLabel = new Label("Choisir role");
        roleLabel.setUIID("Label"); // Set UIID to access theme constants
        roleLabel.getAllStyles().setFgColor(0xffffff); // Set label color to white
        roleContainer.add(BorderLayout.WEST, roleLabel);
        roleContainer.add(BorderLayout.CENTER, roleField);

int fontSize = Display.getInstance().convertToPixels(1.2f);
Font font = Font.createTrueTypeFont("native:MainThin", "native:MainThin.ttf").derive(fontSize, Font.STYLE_PLAIN);
roleField.getAllStyles().setFont(font);
roleField.setRenderer(new DefaultListCellRenderer() {
    public Component getListCellRendererComponent(Component list, Object value, int index, boolean isSelected) {
        Label item = (Label) super.getListCellRendererComponent((List) list, value, index, isSelected);
        item.getUnselectedStyle().setFont(font);
        return item;
    }
});

    TextField nomField = new TextField("", "Nom", 20, CENTER);
    nomField.getHintLabel().getAllStyles().setFgColor(0xffffff); // Set hint color to white
    Container nomContainer = new Container(new BorderLayout());
    nomContainer.add(BorderLayout.CENTER, nomField);

    TextField prenomField = new TextField("", "Prénom", 20, CENTER);
    prenomField.getHintLabel().getAllStyles().setFgColor(0xffffff); // Set hint color to white
    Container prenomContainer = new Container(new BorderLayout());
    prenomContainer.add(BorderLayout.CENTER, prenomField);

    TextField mailField = new TextField("", "Email", 20, CENTER);
    mailField.getHintLabel().getAllStyles().setFgColor(0xffffff); // Set hint color to white
    Container mailContainer = new Container(new BorderLayout());
    mailContainer.add(BorderLayout.CENTER, mailField);

ComboBox<String> domaineField = new ComboBox<>("Web Programming", "Game Development", "Digital Marketing", "Data Engineering");
Container domaineContainer = new Container(new BorderLayout());
Label domaineLabel = new Label("Choisir domaine");
domaineLabel.setUIID("Label"); // Set UIID to access theme constants
domaineLabel.getAllStyles().setFgColor(0xffffff); // Set label color to white
domaineContainer.add(BorderLayout.WEST, domaineLabel);
domaineContainer.add(BorderLayout.CENTER, domaineField);
domaineField.getAllStyles().setFont(font);
domaineField.setRenderer(new DefaultListCellRenderer() {
    public Component getListCellRendererComponent(Component list, Object value, int index, boolean isSelected) {
        Label item = (Label) super.getListCellRendererComponent((List) list, value, index, isSelected);
        item.getUnselectedStyle().setFont(font);
        return item;
    }
});



    TextField loginField = new TextField("", "Identifiant", 20, CENTER);
    loginField.getHintLabel().getAllStyles().setFgColor(0xffffff); // Set hint color to white
    Container loginContainer = new Container(new BorderLayout());
    loginContainer.add(BorderLayout.CENTER, loginField);
    
    TextField passwordField = new TextField("", "Mot de passe", 20, TextField.PASSWORD);
    passwordField.getHintLabel().getAllStyles().setFgColor(0xffffff); // Set hint color to white
    Container passwordContainer = new Container(new BorderLayout());
    passwordContainer.add(BorderLayout.CENTER, passwordField);
    
    TextField confirmpasswordField = new TextField("", "Confirmer mot de passe", 20, TextField.PASSWORD);
    confirmpasswordField.getHintLabel().getAllStyles().setFgColor(0xffffff); // Set hint color to white
    Container confirmpasswordContainer = new Container(new BorderLayout());
    confirmpasswordContainer.add(BorderLayout.CENTER, confirmpasswordField);
    
    



        
        // ************************************* Button login ***************************************
        Button signupButton = new Button("Signup");
        signupButton.setUIID("LoginButton");
        
            signupButton.addActionListener(e -> {
        // get the updated values from the text fields
        String nom = nomField.getText().trim();
        String prenom = prenomField.getText().trim();
        String mail = mailField.getText().trim();
        String login = loginField.getText().trim();
        String password = passwordField.getText().trim();
        String confirmpassword = confirmpasswordField.getText().trim();
        int selectedIndexDom = domaineField.getSelectedIndex();
        int selectedIndexrole = roleField.getSelectedIndex();
        String domaine = selectedIndexDom >= 0 ? domaineField.getModel().getItemAt(selectedIndexDom) : null;
        String role = selectedIndexrole >= 0 ? roleField.getModel().getItemAt(selectedIndexrole) : null;        
        
         // validate input
        if (role.isEmpty() || nom.isEmpty() || prenom.isEmpty() || mail.isEmpty() || login.isEmpty() || password.isEmpty() || domaine.isEmpty() ) {
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
        if (password.length() < 8) {
            Dialog.show("Error", "Le mot de passe doit comporter au moins 8 caractères", "OK", null);
            return;
        }
        if (!password.equals(confirmpassword)) {
            Dialog.show("Error", "Les mots de passe ne correspondent pas", "OK", null);
            return;
        }
        // check if the login is already used
        
        boolean isLoginNotUsed = userService.checkLogin(login);
        boolean isEmailNotUsed = userService.checkEmail(mail);
    if (!isLoginNotUsed) {
        Dialog.show("Erreur", "Le login est déjà utilisé", "OK", null);
        return;
    }
    
 if (!isEmailNotUsed) {
        Dialog.show("Erreur", "Le email est déjà utilisé", "OK", null);
        return;
    }
        // call the userService to signup the user
boolean isAdded = userService.signUp(role, nom, prenom, mail, domaine, login, password);
    
        if (isAdded) {
            Dialog.show("Succès", "Inscription fait avec succès et un e-mail contenant les détails de connexion vous a été envoyé.", "OK", null);
            new LoginForm(theme).show();
        } else {
            Dialog.show("Erreur", "Échec de l'inscription", "OK", null);
        }
    });

        Button loginbtn = new Button("S'identifier");
        loginbtn.addActionListener( x-> {
            //Toolbar.setGlobalToolbar(false);
            //new WalkthruForm(theme).show();
            //new ProfileForm(theme).show();
            new LoginForm(theme).show();
            
            Toolbar.setGlobalToolbar(true);
        });
        
        
        
        // We remove the extra space for low resolution devices so things fit better
        Label spaceLabel;
        if(!Display.getInstance().isTablet() && Display.getInstance().getDeviceDensity() < Display.DENSITY_VERY_HIGH) {
            spaceLabel = new Label();
        } else {
            spaceLabel = new Label(" ");
        }
        
        
        Container by = BoxLayout.encloseY(
                welcome,
                spaceLabel,
                roleContainer,
                nomContainer,
                prenomContainer,
                mailContainer,
                domaineContainer,
                loginContainer,
                passwordContainer,
                confirmpasswordContainer,
                signupButton,
                loginbtn
        );
        add(BorderLayout.CENTER, by);
        
        // for low res and landscape devices
        by.setScrollableY(true);
        by.setScrollVisible(false);
    }
    
      public static boolean isOnlyLetters(String input) {
    for (int i = 0; i < input.length(); i++) {
        if (!(input.charAt(i) >= 'a' && input.charAt(i) <= 'z' || input.charAt(i) >= 'A' && input.charAt(i) <= 'Z')) {
            return false;
        }
    }
    return true;
}
      
      
}
