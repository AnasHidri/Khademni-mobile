/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gui;


import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;


import com.codename1.ui.Container;
import com.codename1.ui.Dialog;

import com.codename1.ui.Form;

import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.services.EvenementService;
import com.mycompany.statics.Statics;

import java.util.List;
import java.util.Map;
import com.codename1.charts.models.CategorySeries;
import java.util.ArrayList;


/**
 *
 * @author user
 */
public class StatLike {
    
public void showTopEventsByLikes(Label label) {
     
    // 1. Récupérer les données depuis la propriété "events" du label
    List<Map<String, Object>> events = (List<Map<String, Object>>) label.getClientProperty("events");
    
    if (events != null && !events.isEmpty()) {
        // 2. Créer un CategorySeries et un DefaultRenderer pour configurer le PieChart
        CategorySeries series = new CategorySeries("Top Events by Likes");
        DefaultRenderer renderer = new DefaultRenderer();
        
        // 3. Ajouter les événements à la série et au renderer
        for (Map<String, Object> event : events) {
            String title = (String) event.get("title");
            int likes = Integer.parseInt(String.valueOf(event.get("likes")));
            series.add(title, likes);
            SimpleSeriesRenderer r = new SimpleSeriesRenderer();
            r.setColor(0xFF3366CC); // Utiliser une couleur bleue pour ce rendu
            renderer.addSeriesRenderer(r);
        }
        
        // 4. Créer un ChartComponent pour afficher le PieChart
        ChartComponent chart = new ChartComponent(new com.codename1.charts.views.PieChart(series, renderer));
        Container chartContainer = new Container(new BorderLayout());
        chartContainer.addComponent(BorderLayout.CENTER, chart);
        
        // 5. Afficher le container contenant le PieChart
        Form myForm = new Form("Top Events by Likes");
        myForm.setLayout(new BorderLayout());
        myForm.addComponent(BorderLayout.CENTER, chartContainer);
        myForm.show();
    } else {
        // Afficher un message d'erreur si la liste des événements est vide
        Dialog.show("Erreur", "Aucun événement trouvé.", "OK", null);
    }
}
}
