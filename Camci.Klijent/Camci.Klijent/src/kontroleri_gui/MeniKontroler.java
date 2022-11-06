/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroleri_gui;

import domen.Camac;
import gui.KreirajCamacGUI;
import gui.KreirajKupcaGUI;
import gui.KreirajProdavcaGUI;
import gui.KreirajUgovorGUI;
import gui.MenuGUI;
import gui.PretragaCamacaGUI;
import gui.PretragaKupacaGUI;
import gui.PretragaProdavacaGUI;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author PC
 */
public class MeniKontroler {
    private MenuGUI gui;

    public void getGui() {
        if (gui == null) {
            gui = new MenuGUI();
            this.gui.getjMenuItem1().addActionListener(e -> guiKreirajCamac(null));
            this.gui.getjMenuItem2().addActionListener(e -> guiPretragaCamac());
            this.gui.getjMenuItem3().addActionListener(e -> guiPretragaCamac());
            this.gui.getjMenuItem4().addActionListener(e -> guiUnosKupca());
            this.gui.getjMenuItem5().addActionListener(e -> guiPretragaKupca());
            this.gui.getjMenuItem6().addActionListener(e -> guiUnosProdavca());
            this.gui.getjMenuItem7().addActionListener(e -> guiPretragaProdavca());
            this.gui.getjMenuItem8().addActionListener(e -> guiUnosUgovora());
            gui.setSize(300, 300);
            gui.setLocationRelativeTo(null);
        }
        gui.setVisible(true);
        this.gui.getContentPane().removeAll();

    }

    public void postavi(JPanel panel) {
        JDialog dialog = new JDialog(new JFrame(), true);
        dialog.add(panel);
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    
    public void guiPretragaCamac() {
        PretragaCamacaGUI forma = PretragaCamacaKontroler.getInstance().getGui();
        postavi(forma);
         
    }

    private void guiUnosKupca() {
        KreirajKupcaGUI forma = KreirajKupcaKontroler.getInstance().getGui();
        postavi(forma);
    }

    public void guiKreirajCamac(Camac camac) {
        KreirajCamacGUI forma = KreirajCamacKontroler.getInstance().getGui(camac);
        postavi(forma);
    }

    private void guiPretragaKupca() {
        PretragaKupacaGUI forma = PretragaKupacaKontroler.getInstance().getGui();
        postavi(forma);
    }
   
    private void guiPretragaProdavca() {
        PretragaProdavacaGUI forma = PretragaProdavacaKontroler.getInstance().getGui();
        postavi(forma);
    }

    private void guiUnosUgovora() {
        KreirajUgovorGUI forma = KreirajUgovorKontroler.getInstance().getGui();
        postavi(forma);
    }

    private void guiUnosProdavca() {
        KreirajProdavcaGUI forma = KreirajProdavcaKontroler.getInstance().getGui();
        postavi(forma);
    }
}
