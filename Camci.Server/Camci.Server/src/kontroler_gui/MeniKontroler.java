/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroler_gui;

import gui.MenuGUI;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author PC
 */
public class MeniKontroler {
    private MenuGUI gui;
    public MenuGUI getGui() {
        gui = new MenuGUI();
        setActionListeners();
        gui.setVisible(true);
        gui.setSize(250,250);
        return gui;
    }
      
    private void setActionListeners() {
        this.gui.getjMenuItem1().addActionListener(e -> serverPanelObrada(true));
        this.gui.getjMenuItem2().addActionListener(e -> serverPanelObrada(false));
    }


    private void serverPanelObrada(boolean isStart) {
        if(isStart){
            try {
                PoveziSe.getInstance().serverObrada(true);
                JOptionPane.showMessageDialog(gui, "Uspesno ste pokrenuli server!", "", JOptionPane.INFORMATION_MESSAGE);

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(gui, "Greska! Server trenutno ne moze da se pokrene!", "", JOptionPane.ERROR_MESSAGE);
            }
        }else{
            try {
                PoveziSe.getInstance().serverObrada(false);
                JOptionPane.showMessageDialog(gui, "Uspesno ste zaustavili server!", "", JOptionPane.INFORMATION_MESSAGE);
            }catch (IOException ex) {
               JOptionPane.showMessageDialog(gui, "Greska! Server trenutno ne moze da se zaustavi!", "", JOptionPane.ERROR_MESSAGE);

            }
        }
       
    }
    
}
