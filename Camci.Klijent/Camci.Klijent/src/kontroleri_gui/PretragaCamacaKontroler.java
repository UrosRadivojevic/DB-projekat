/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroleri_gui;

import domen.Camac;
import gui.PretragaCamacaGUI;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modeli.CamacModel;
import pomocne.OdgovorUtil;
import pomocne.OperacijaUtil;
import pomocne.PretragaUtil;
import zahtevi.Komunikacija;

/**
 *
 * @author PC
 */
public class PretragaCamacaKontroler {
    private static PretragaCamacaKontroler instance;
    private PretragaCamacaGUI gui;
    private List<Camac> lista;
    private PretragaCamacaKontroler() {
    }

    public static PretragaCamacaKontroler getInstance() {
        if (instance == null) {
            instance = new PretragaCamacaKontroler();
        }
        return instance;
    }

    public PretragaCamacaGUI getGui() {
        gui = new PretragaCamacaGUI();
        dodajAkcije();
        priprema();
        gui.setVisible(true);
        return gui;
    }

    private void dodajAkcije() {
        this.gui.getBtnPretraga().addActionListener(e -> pretragaAction());
        this.gui.getBtnIzmeni().addActionListener(e -> izmeniAction());
        this.gui.getBtnPocetak().addActionListener(e -> priprema());
    }

    private void pretragaAction() {
        if(gui.getTxtTekst().getText().equals("")){
            JOptionPane.showMessageDialog(gui, "Molim vas unesite neki tekst", "", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            Komunikacija.getInstance().PosaljiZahtev(new PretragaUtil(gui.getTxtTekst().getText()), OperacijaUtil.PretraziCamce);
            OdgovorUtil o = Komunikacija.getInstance().PrimiOdgovor();
            if(o.isZnak()){
                JOptionPane.showMessageDialog(gui, "Sistem je nasao camce po zadatoj vrednosti!", "", JOptionPane.INFORMATION_MESSAGE);
                lista = (List<Camac>) o.getObjekat();
                pripremaTabela();
            }else{
                JOptionPane.showMessageDialog(gui, "Sistem ne moze da nadje camce po zadatoj vrednosti!", "", JOptionPane.ERROR_MESSAGE);

            }
        } catch (Exception ex) {
            Logger.getLogger(PretragaCamacaKontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }

    private void izmeniAction() {
        int red = gui.getjTable1().getSelectedRow();
        if (red == -1) {
            JOptionPane.showMessageDialog(gui, "Niste selektovali camac", "", JOptionPane.ERROR_MESSAGE);
            return;
        }
        MeniKontroler meni = new MeniKontroler();
        meni.guiKreirajCamac(lista.get(red));
        priprema();
    }


    private void priprema() {
        try {
            Komunikacija.getInstance().PosaljiZahtev(null, OperacijaUtil.VratiCamce);
            OdgovorUtil odgovor = Komunikacija.getInstance().PrimiOdgovor();
            if(odgovor.isZnak()){
                lista = (List<Camac>) odgovor.getObjekat();
                pripremaTabela();
            }
           
        } catch (Exception ex) {
            Logger.getLogger(PretragaCamacaKontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void pripremaTabela() {
       gui.getjTable1().setModel(new CamacModel(lista));
    }
}
