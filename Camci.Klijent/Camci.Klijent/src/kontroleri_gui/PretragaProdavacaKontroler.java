/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroleri_gui;

import domen.Prodavac;
import gui.PretragaProdavacaGUI;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modeli.ProdavacModel;
import pomocne.OdgovorUtil;
import pomocne.OperacijaUtil;
import pomocne.PretragaUtil;
import zahtevi.Komunikacija;

/**
 *
 * @author PC
 */
public class PretragaProdavacaKontroler {
    private static PretragaProdavacaKontroler instance;
    private PretragaProdavacaGUI gui;
    private List<Prodavac> lista;
    private PretragaProdavacaKontroler() {
    }

    public static PretragaProdavacaKontroler getInstance() {
        if (instance == null) {
            instance = new PretragaProdavacaKontroler();
        }
        return instance;
    }

    public PretragaProdavacaGUI getGui() {
        gui = new PretragaProdavacaGUI();
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
            Komunikacija.getInstance().PosaljiZahtev(new PretragaUtil(gui.getTxtTekst().getText()), OperacijaUtil.PretraziProdavce);
            OdgovorUtil o = Komunikacija.getInstance().PrimiOdgovor();
            if(o.isZnak()){
                JOptionPane.showMessageDialog(gui, "Sistem je nasao prodavce po zadatoj vrednosti!", "", JOptionPane.INFORMATION_MESSAGE);
                lista = (List<Prodavac>) o.getObjekat();
                pripremaTabela();
            }else{
                JOptionPane.showMessageDialog(gui, "Sistem ne moze da pronadje prodavce po zadatoj vrednosti!", "", JOptionPane.ERROR_MESSAGE);

            }
        } catch (Exception ex) {
            Logger.getLogger(PretragaCamacaKontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void priprema() {
        try {
            Komunikacija.getInstance().PosaljiZahtev(null, OperacijaUtil.VratiProdavce);
            OdgovorUtil odgovor = Komunikacija.getInstance().PrimiOdgovor();
            if(odgovor.isZnak()){
                lista = (List<Prodavac>) odgovor.getObjekat();
                pripremaTabela();
            }
           
        } catch (Exception ex) {
            Logger.getLogger(PretragaCamacaKontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void pripremaTabela() {
       gui.getjTable1().setModel(new ProdavacModel(lista));
    }
}
