/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroleri_gui;

import domen.Camac;
import domen.Kupac;
import domen.Prodavac;
import domen.StavkaUgovora;
import domen.Ugovor;
import gui.KreirajUgovorGUI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modeli.StavkaModel;
import pomocne.OdgovorUtil;
import pomocne.OperacijaUtil;
import zahtevi.Komunikacija;

/**
 *
 * @author PC
 */
public class KreirajUgovorKontroler {
    private static KreirajUgovorKontroler instance;
    private KreirajUgovorGUI gui;
    private List<StavkaUgovora> lista;
    private KreirajUgovorKontroler() {
    }

    public static KreirajUgovorKontroler getInstance() {
        if (instance == null) {
            instance = new KreirajUgovorKontroler();
        }
        return instance;
    }

    public KreirajUgovorGUI getGui() {
        gui = new KreirajUgovorGUI();
        dodajAkcije();
        gui.getTxtUkupanDug().setEnabled(false);
        priprema();
        gui.setVisible(true);
        return gui;
    }

    private void dodajAkcije() {
        this.gui.getjBtnSacuvaj().addActionListener(e -> sacuvajAction());
        this.gui.getBtnDodaj().addActionListener(e -> dodajAction());
        this.gui.getBtnObrisi().addActionListener(e -> obrisiAction());
    }

    private void sacuvajAction() {
         try {
            Ugovor ugovor = formirajUgovor();
            Komunikacija.getInstance().PosaljiZahtev(ugovor, OperacijaUtil.ZapamtiUgovor);
            OdgovorUtil odgovor = Komunikacija.getInstance().PrimiOdgovor();
            if(odgovor.isZnak()) JOptionPane.showMessageDialog(gui, "Sistem je zapamtio ugovor!", "", JOptionPane.INFORMATION_MESSAGE);
            else JOptionPane.showMessageDialog(gui, "Sistem ne moze da zapamti ugovor!", "", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            Logger.getLogger(KreirajUgovorKontroler.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(gui, ex.getMessage(), "", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void dodajAction() {
        try {
            StavkaUgovora stavka = formirajStavku();
            lista.add(stavka);
            pripremaTabela();
        } catch (Exception ex) {
            Logger.getLogger(KreirajUgovorKontroler.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(gui, ex.getMessage(), "", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void obrisiAction() {
        int red = gui.getjTable1().getSelectedRow();
        if (red == -1) {
            JOptionPane.showMessageDialog(gui, "Niste selektovali stavku", "", JOptionPane.ERROR_MESSAGE);
            return;
        }
        StavkaUgovora stavka = lista.get(red);
        stavka.getCamac().setKolicinaNaStanju(stavka.getCamac().getKolicinaNaStanju() + stavka.getKolicina());
            
        double ukupanDug =  Double.parseDouble(gui.getTxtUkupanDug().getText()) - stavka.getUkupnaNaknada() ;
        gui.getTxtUkupanDug().setText(ukupanDug + "");
        lista.remove(red);
        
        pripremaTabela();
    }

    private void priprema() {
        try {
            Komunikacija.getInstance().PosaljiZahtev(null, OperacijaUtil.VratiKupce);
            OdgovorUtil odgovor = Komunikacija.getInstance().PrimiOdgovor();
            if(odgovor.isZnak()){
                List<Kupac> kupci = (List<Kupac>) odgovor.getObjekat();
                for (Kupac kupac : kupci) {
                    gui.getCmbKupac().addItem(kupac);
                }
            }
            
            Komunikacija.getInstance().PosaljiZahtev(null, OperacijaUtil.VratiProdavce);
            odgovor = Komunikacija.getInstance().PrimiOdgovor();
            if(odgovor.isZnak()){
                List<Prodavac> prodavci = (List<Prodavac>) odgovor.getObjekat();
                for (Prodavac prodavac : prodavci) {
                    gui.getCmbProdavac().addItem(prodavac);
                }
            }
            
            Komunikacija.getInstance().PosaljiZahtev(null, OperacijaUtil.VratiCamce);
            odgovor = Komunikacija.getInstance().PrimiOdgovor();
            if(odgovor.isZnak()){
                List<Camac> camci = (List<Camac>) odgovor.getObjekat();
                for (Camac camac : camci) {
                    gui.getCmbCamac().addItem(camac);
                }
            }
            lista = new ArrayList<>();
            pripremaTabela();
        } catch (Exception ex) {
            Logger.getLogger(KreirajUgovorKontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void pripremaTabela() {
        gui.getjTable1().setModel(new StavkaModel(lista));
    }

    private StavkaUgovora formirajStavku() throws Exception {
        try{
            if(gui.getTxtKolicina().getText().isEmpty()){
                throw new Exception("Morate popuniti sva polja");
            }
            Camac camac = (Camac) gui.getCmbCamac().getSelectedItem();
            int kolicina = Integer.parseInt(gui.getTxtKolicina().getText());
            if(kolicina < 0){
                throw new Exception("Kolicina mora biti veca od 0");
            }
            if(camac.getKolicinaNaStanju() < kolicina) {
                throw new Exception("Nema toliko camaca koliko ste zahtevali u kolicini. Trenutan broj na stanju je "+camac.getKolicinaNaStanju());
            }else{
                camac.setKolicinaNaStanju(camac.getKolicinaNaStanju() - kolicina);
            }
            double ukupnaNaknada = kolicina * camac.getCena();
            
            if(gui.getTxtUkupanDug().getText().isEmpty()){
                gui.getTxtUkupanDug().setText("0");
            }
            double ukupanDug = ukupnaNaknada + Double.parseDouble(gui.getTxtUkupanDug().getText());
            gui.getTxtUkupanDug().setText(ukupanDug + "");
            
            if(proveraPostoji(camac)){
                throw new Exception("Vec ste uneli stavku za taj camac");
            }
            StavkaUgovora  stavka = new StavkaUgovora(new Long(0), null, kolicina, ukupnaNaknada, camac);
            return stavka;
        }catch(Exception ex){
            throw new Exception(ex.getMessage());
        }
    }

    private Ugovor formirajUgovor() throws Exception {
        try{
            String opis = gui.getTxtOpis().getText();
            
            if(opis.isEmpty() || gui.getTxtUkupanDug().getText().isEmpty()){
                throw new Exception("Morate popuniti sva polja");
            }
            
            double ukupanDug = Double.parseDouble(gui.getTxtUkupanDug().getText());
            Kupac kupac = (Kupac) gui.getCmbKupac().getSelectedItem();
            Date datum = new Date();
            Prodavac prodavac = (Prodavac) gui.getCmbProdavac().getSelectedItem();
            
            Ugovor u = new Ugovor(new Long(0), opis, datum, ukupanDug, kupac, prodavac);
            if(lista.size() == 0){
                throw new Exception("Morate uneti makar jednu stavku!");
            }
            u.setStavke(lista);
            return u;
        }catch(Exception ex){
            throw new Exception("Pogresan unos");
        }
    }

    private boolean proveraPostoji(Camac camac) {
        for (StavkaUgovora stavkaUgovora : lista) {
            if(stavkaUgovora.getCamac().getCamacId().equals(camac.getCamacId())) 
            {
                return true;
            }
        }
        return false;
    }
}
