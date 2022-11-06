/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroleri_gui;

import domen.Kupac;
import gui.KreirajKupcaGUI;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import pomocne.OdgovorUtil;
import pomocne.OperacijaUtil;
import zahtevi.Komunikacija;

/**
 *
 * @author PC
 */
public class KreirajKupcaKontroler {
    private static KreirajKupcaKontroler instance;
    private KreirajKupcaGUI gui;
    private KreirajKupcaKontroler() {
    }

    public static KreirajKupcaKontroler getInstance() {
        if (instance == null) {
            instance = new KreirajKupcaKontroler();
        }
        return instance;
    }

    public KreirajKupcaGUI getGui() {
        gui = new KreirajKupcaGUI();
        dodajAkcije();
        
        gui.setVisible(true);
        return gui;
    }

    private void dodajAkcije() {
        this.gui.getjBtnSacuvaj().addActionListener(e -> sacuvajAction());
    }

    private void sacuvajAction() {
        try {
            Kupac kupac = formirajKupca();
            Komunikacija.getInstance().PosaljiZahtev(kupac, OperacijaUtil.ZapamtiKupca);
            OdgovorUtil odgovor = Komunikacija.getInstance().PrimiOdgovor();
            if(odgovor.isZnak()) JOptionPane.showMessageDialog(gui, "Sistem je zapamtio kupca!", "", JOptionPane.INFORMATION_MESSAGE);
            else JOptionPane.showMessageDialog(gui, "Sistem ne moze da zapamti kupca!", "", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            Logger.getLogger(KreirajKupcaKontroler.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(gui, ex.getMessage(), "", JOptionPane.ERROR_MESSAGE);
        }
    }

    private Kupac formirajKupca() throws Exception {
        try{
            String ime = gui.getTxtIme().getText();
            String prezime = gui.getTxtPrezime().getText();
            String mail = gui.getTxtMail().getText();
            String brojTelefona = gui.getTxtBroj().getText();
            String adresa = gui.getTxtAdresa().getText();
            String napomena = gui.getTxtNapomena().getText();
            if(ime.isEmpty() || prezime.isEmpty() || mail.isEmpty() || brojTelefona.isEmpty() || adresa.isEmpty() || napomena.isEmpty()){
                throw new Exception("Morate popuniti sva polja");
            }
            if(!mail.endsWith("@gmail.com") && !mail.endsWith("@hotmail.com") && !mail.endsWith("@outlook.com")){
                throw new Exception("Morate uneti mail u ispravnom formatu");
            }
            Kupac kupac = new Kupac(new Long(0), ime, prezime, adresa, mail, brojTelefona, napomena);
            return kupac;
        }catch(Exception ex){
            throw new Exception(ex.getMessage());
        }
    }
}
