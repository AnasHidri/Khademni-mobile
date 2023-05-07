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
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Style;

import com.codename1.ui.util.Resources;
import com.codename1.uikit.materialscreens.ProfileForm;
import com.codename1.uikit.materialscreens.SideMenuBaseForm;
import com.mycompany.entities.User;
import com.mycompany.services.UtilisateurService;
import java.util.ArrayList;


/**
 *
 * @author ASUS
 */
public class ListUsers extends SideMenuBaseForm{

    private UtilisateurService userService= new UtilisateurService();
   public ListUsers(Resources res) {
    super(BoxLayout.y());
            TextField searchField = new TextField("", "Search");

            
    Toolbar tb = getToolbar();
    tb.setTitleCentered(false);

    Button menuButton = new Button("");
    menuButton.setUIID("Title");
    FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
    menuButton.addActionListener(e -> getToolbar().openSideMenu());

  
    Container titleCmp = BoxLayout.encloseY(
            FlowLayout.encloseIn(menuButton),
            BorderLayout.centerAbsolute(
                    BoxLayout.encloseY(
                            new Label("Liste des utilisateurs", "Title"),
                            searchField
                    )
            )
    );

    FloatingActionButton fab = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);
    fab.getAllStyles().setMarginUnit(Style.UNIT_TYPE_PIXELS);
    fab.getAllStyles().setMargin(BOTTOM,2 );
    tb.setTitleComponent(titleCmp);
    
    
    ArrayList<User> users = userService.getAllUsers();
        for (User user : users) {
                   
            addElement(user,res);
        }
        
        searchField.setShouldCalcPreferredSize(true);
        searchField.addDataChangedListener((i1, i2) -> {
    String filter = searchField.getText();
    if (filter.isEmpty()) {
        // If search field is empty, show all users
        removeAll();
        ArrayList<User> allUsers = userService.getAllUsers();
        for (User user : allUsers) {
            addElement(user, res);
        }
    } else {
        // If search field has text, filter users based on the search text
        ArrayList<User> filteredUsers = userService.filterUsers(filter);
        removeAll();
        for (User user : filteredUsers) {
            addElement(user, res);
        }
    }
    revalidate();
});



    setupSideMenu(res);
}


    @Override
    protected void showOtherForm(Resources res) {
        new ProfileForm(res).show();
    }
    
   
 public void addElement(User user,Resources res) {
 int id = user.getIdUser();
   // System.out.println("user id: " + id);
    String fullName = user.getNom() + " " + user.getPrenom();
    String role = user.getRole();
    String mail = user.getMail();
    String domaine = user.getDomaine();


    // create labels for each field
    Label fullNameLabel = new Label(fullName);
    Label roleLabel = new Label(role);
    Label mailLabel = new Label(mail);
    Label domaineLabel = new Label(domaine);
    //Label idLabel = new Label(String.valueOf(id));
    // create update and delete buttons
    Button updateButton = new Button("Update");
    Button deleteButton = new Button("Delete");

    // create a container to hold the labels and buttons
    Container container = new Container(new BoxLayout(BoxLayout.Y_AXIS));
    container.add(fullNameLabel);
    container.add(roleLabel);
    container.add(mailLabel);
    container.add(domaineLabel);
    //    container.add(idLabel);

Container buttonsContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
buttonsContainer.add(updateButton);
buttonsContainer.add(deleteButton);
container.add(buttonsContainer);
    // add the container to the form
    addComponent(container);

    // add action listeners to the buttons
   updateButton.addActionListener(e -> {
    // show the update form with the current user's data
    showUpdateForm(user, res);
});

deleteButton.addActionListener(e -> {
    boolean isDeleted = userService.deleteUser(user.getIdUser());
    if (isDeleted) {
        Dialog.show("Succès", "Utilisateur supprimé avec succé", "OK", null);
        new ListUsers(res).show();
        // call a method to refresh the user list
    } else {
        Dialog.show("Erreur", "Échec de la suppression de l'utilisateur", "OK", null);
    }
});
    
}
 public void showUpdateForm(User user, Resources res) {
    // create the update form
    Form updateForm = new Form("Update User", BoxLayout.y());

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

    // set the foreground color of the text in the text fields
    nomField.getAllStyles().setFgColor(0x000000);
    prenomField.getAllStyles().setFgColor(0x000000);
    mailField.getAllStyles().setFgColor(0x000000);
    domaineField.getAllStyles().setFgColor(0x000000);

    // add the text fields to the form
    updateForm.add(nomContainer);
    updateForm.add(prenomContainer);
    updateForm.add(mailContainer);
    updateForm.add(domaineContainer);

    // create the update button and add an action listener to handle the update
    Button updateButton = new Button("Modifier");
    Button retourButton = new Button("Annuler");
    
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
            new ListUsers(res).show();
        } else {
            Dialog.show("Erreur", "Échec de la mise à jour de l'utilisateur", "OK", null);
        }

        // close the update form after handling the update
        updateForm.showBack();
    });
    
retourButton.addActionListener(e -> {
    ListUsers listUsers = new ListUsers(res);
    listUsers.show();
    
});
    // add the update button to the form
    Container buttonsContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
    buttonsContainer.add(updateButton);
    buttonsContainer.add(retourButton);
    updateForm.add(buttonsContainer);
    

    // show the update form
    updateForm.show();
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
