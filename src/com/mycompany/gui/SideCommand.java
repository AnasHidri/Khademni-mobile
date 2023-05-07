/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gui;

import com.codename1.ui.Command;
import com.codename1.ui.FontImage;
import com.codename1.ui.events.ActionListener;

/**
 *
 * @author ASUS
 */
public class SideCommand extends Command {
    private final ActionListener subMenuListener;
    private final FontImage subMenuIcon;
 
    public SideCommand(String name, ActionListener listener, FontImage icon) {
        super(name);
        subMenuListener = listener;
        subMenuIcon = icon;
    }
 
    public ActionListener getSubMenuListener() {
        return subMenuListener;
    }
 
    public FontImage getSubMenuIcon() {
        return subMenuIcon;
    }
}
