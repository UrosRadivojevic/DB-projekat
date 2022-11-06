/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroleri_gui;

import domen.Prodavac;
import gui.KreirajProdavcaGUI;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class KreirajProdavcaKontroler {
    private static KreirajProdavcaKontroler instance;
    private KreirajProdavcaGUI gui;
    private KreirajProdavcaKontroler() {
    }

    public static KreirajProdavcaKontroler getInstance() {
        if (instance == null) {
            instance = new KreirajProdavcaKontroler();
        }
        return instance;
    }

    public KreirajProdavcaGUI getGui() {
        gui = new KreirajProdavcaGUI();
        dodajAkcije();
        
        gui.setVisible(true);
        return gui;
    }

    private void dodajAkcije() {
        this.gui.getjBtnSacuvaj().addActionListener(e -> sacuvajAction());
    }

    private void sacuvajAction() {
        try {
            Prodavac prodavac = formirajProdavca();
            Komunikacija.getInstance().PosaljiZahtev(prodavac, OperacijaUtil.ZapamtiProdavca);
            OdgovorUtil odgovor = Komunikacija.getInstance().PrimiOdgovor();
            if(odgovor.isZnak()) JOptionPane.showMessageDialog(gui, "Sistem je zapamtio prodavca!", "", JOptionPane.INFORMATION_MESSAGE);
            else JOptionPane.showMessageDialog(gui, "Sistem ne moze da zapamti prodavca!", "", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            Logger.getLogger(KreirajKupcaKontroler.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(gui, ex.getMessage(), "", JOptionPane.ERROR_MESSAGE);
        }
    }

    private Prodavac formirajProdavca() throws Exception {
        try{
            String ime = gui.getTxtIme().getText();
            String prezime = gui.getTxtPrezime().getText();
            String brojTelefona = gui.getTxtBroj().getText();
            if(ime.isEmpty() || prezime.isEmpty() || gui.getTxtDatum().getText().isEmpty() || brojTelefona.isEmpty() ){
                throw new Exception("Morate popuniti sva polja");
            }
            Date datum;
            try{
                datum = new SimpleDateFormat("yyyy-MM-dd").parse(gui.getTxtDatum().getText());
            }catch(Exception e){
                throw new Exception("Morate uneti datum u formatu yyyy-MM-dd" );
            }
            
            Prodavac prodavac = new Prodavac(new Long(0), ime, prezime, datum, brojTelefona);
            return prodavac;
        }catch(Exception ex){
            throw new Exception(ex.getMessage());
        }
    }
}
