/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroleri_gui;

import domen.Kupac;
import gui.PretragaKupacaGUI;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modeli.KupacModel;
import pomocne.OdgovorUtil;
import pomocne.OperacijaUtil;
import pomocne.PretragaUtil;
import zahtevi.Komunikacija;

/**
 *
 * @author PC
 */
public class PretragaKupacaKontroler {
    private static PretragaKupacaKontroler instance;
    private PretragaKupacaGUI gui;
    private List<Kupac> lista;
    private PretragaKupacaKontroler() {
    }

    public static PretragaKupacaKontroler getInstance() {
        if (instance == null) {
            instance = new PretragaKupacaKontroler();
        }
        return instance;
    }

    public PretragaKupacaGUI getGui() {
        gui = new PretragaKupacaGUI();
        dodajAkcije();
        priprema();
        gui.setVisible(true);
        return gui;
    }

    private void dodajAkcije() {
        this.gui.getBtnPretraga().addActionListener(e -> pretragaAction());
        this.gui.getBtnPocetak().addActionListener(e -> priprema());
    }

    private void pretragaAction() {
        if(gui.getTxtTekst().getText().equals("")){
            JOptionPane.showMessageDialog(gui, "Molim vas unesite neki tekst", "", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            Komunikacija.getInstance().PosaljiZahtev(new PretragaUtil(gui.getTxtTekst().getText()), OperacijaUtil.PretraziKupac);
            OdgovorUtil o = Komunikacija.getInstance().PrimiOdgovor();
            if(o.isZnak()){
                JOptionPane.showMessageDialog(gui, "Sistem je nasao kupce po zadatoj vrednosti!", "", JOptionPane.INFORMATION_MESSAGE);
                lista = (List<Kupac>) o.getObjekat();
                pripremaTabela();
            }else{
                JOptionPane.showMessageDialog(gui, "Sistem ne moze da nadje kupce po zadatoj vrednosti!", "", JOptionPane.ERROR_MESSAGE);

            }
        } catch (Exception ex) {
            Logger.getLogger(PretragaCamacaKontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void priprema() {
        try {
            Komunikacija.getInstance().PosaljiZahtev(null, OperacijaUtil.VratiKupce);
            OdgovorUtil odgovor = Komunikacija.getInstance().PrimiOdgovor();
            if(odgovor.isZnak()){
                lista = (List<Kupac>) odgovor.getObjekat();
                pripremaTabela();
            }
           
        } catch (Exception ex) {
            Logger.getLogger(PretragaCamacaKontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void pripremaTabela() {
       gui.getjTable1().setModel(new KupacModel(lista));
    }
}
