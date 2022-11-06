/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroleri_gui;

import domen.Camac;
import domen.MarkaCamca;
import domen.Osobina;
import gui.KreirajCamacGUI;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modeli.OsobinaModel;
import pomocne.OdgovorUtil;
import pomocne.OperacijaUtil;
import zahtevi.Komunikacija;

/**
 *
 * @author PC
 */
public class KreirajCamacKontroler {
    private static KreirajCamacKontroler instance;
    private KreirajCamacGUI gui;
    private List<Osobina> lista;
    private Camac camacIzmena;
    private KreirajCamacKontroler() {
    }

    public static KreirajCamacKontroler getInstance() {
        if (instance == null) {
            instance = new KreirajCamacKontroler();
        }
        return instance;
    }

    public KreirajCamacGUI getGui(Camac camac) {
        gui = new KreirajCamacGUI();
        dodajAkcije();
        priprema();
        if(camac == null){
            gui.getjBtnSacuvaj().setText("Sacuvaj");
        }else{
            gui.getjBtnSacuvaj().setText("Izmeni");
            this.camacIzmena = camac; 
            if(camac.getOsobine() != null){
                lista = camac.getOsobine();
                pripremaTabela();
            }
            gui.getTxtNaziv().setText(camac.getNazivModela());
            gui.getTxtOpis().setText(camac.getOpisCamca());
            gui.getTxtGodina().setText(String.valueOf(camac.getGodinaGarancije()));
            gui.getTxtKolicina().setText(String.valueOf(camac.getKolicinaNaStanju()));
            gui.getTxtCena().setText(String.valueOf(camac.getCena()));
            gui.getCmbMarka().setSelectedItem(camac.getMarka());
        }
        gui.setVisible(true);
        return gui;
    }

    private void dodajAkcije() {
        this.gui.getjBtnSacuvaj().addActionListener(e -> proveraIzmena());
        this.gui.getBtnDodaj().addActionListener(e -> dodajAction());
        this.gui.getBtnObrisi().addActionListener(e -> obrisiAction());
    }

    private void sacuvajAction() {
        try {
            Camac camac = formirajCamac();
            Komunikacija.getInstance().PosaljiZahtev(camac, OperacijaUtil.ZapamtiCamac);
            OdgovorUtil odgovor = Komunikacija.getInstance().PrimiOdgovor();
            if(odgovor.isZnak()) JOptionPane.showMessageDialog(gui, "Sistem je zapamtio camac!", "", JOptionPane.INFORMATION_MESSAGE);
            else JOptionPane.showMessageDialog(gui, "Sistem ne moze da zapamti camac!", "", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            Logger.getLogger(KreirajCamacKontroler.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(gui, ex.getMessage(), "", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void dodajAction() {
        try {
            Osobina osobina = formirajOsobinu();
            lista.add(osobina);
            pripremaTabela();
        } catch (Exception ex) {
            Logger.getLogger(KreirajCamacKontroler.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(gui, ex.getMessage(), "", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void obrisiAction() {
        int red = gui.getjTable1().getSelectedRow();
        if (red == -1) {
            JOptionPane.showMessageDialog(gui, "Niste selektovali osobinu", "", JOptionPane.ERROR_MESSAGE);
            return;
        }
        lista.remove(red);
        pripremaTabela();
    }

    private void priprema() {
        try {
            Komunikacija.getInstance().PosaljiZahtev(null, OperacijaUtil.VratiMarke);
            OdgovorUtil odgovor = Komunikacija.getInstance().PrimiOdgovor();
            if(odgovor.isZnak()){
                List<MarkaCamca> marka = (List<MarkaCamca>) odgovor.getObjekat();
                for (MarkaCamca markaCamca : marka) {
                    gui.getCmbMarka().addItem(markaCamca);
                }
            }
            lista = new ArrayList<>();
            pripremaTabela();
        } catch (Exception ex) {
            Logger.getLogger(KreirajCamacKontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void pripremaTabela() {
        gui.getjTable1().setModel(new OsobinaModel(lista));
    }

    private Camac formirajCamac() throws Exception {
        try{
            String ime = gui.getTxtNaziv().getText();
            String opis = gui.getTxtOpis().getText();
            
            if(ime.isEmpty() || opis.isEmpty() || gui.getTxtGodina().getText().isEmpty() || gui.getTxtKolicina().getText().isEmpty()  
                    || gui.getTxtCena().getText().isEmpty() ){
                throw new Exception("Morate popuniti sva polja");
            }
            int godina = Integer.parseInt(gui.getTxtGodina().getText());
            if(godina < 0) {
                throw new Exception("Godina mora biti veca ili jednaka 0");
            }
            int kolicina = Integer.parseInt(gui.getTxtKolicina().getText());
            if(kolicina < 0) {
                throw new Exception("Kolicina mora biti veca od 0");
            }
            double cena = Double.parseDouble(gui.getTxtCena().getText());
            if(cena < 0) {
                throw new Exception("Cena mora biti veca od 0");
            }
            MarkaCamca marka = (MarkaCamca) gui.getCmbMarka().getSelectedItem();
            Camac camac = new Camac(new Long(0), ime, opis, godina, kolicina, cena, marka);
            if(lista.size() == 0){
                camac.setOsobine(new ArrayList());
            }else{
                camac.setOsobine(lista);
            }
            return camac;
        }catch(Exception ex){
            throw new Exception(ex.getMessage());
        }
    }

    private Osobina formirajOsobinu() throws Exception {
        try{
            String ime = gui.getTxtNazivOsobine().getText();
            String opis = gui.getTxtOpisOsobine().getText();
            if(ime.isEmpty() || opis.isEmpty()){
                throw new Exception("Morate popuniti sva polja");
            }
            if(postojiOsobina(ime)){
                throw new Exception("Vec ste uneli osobinu sa tim imenom!");
            }
            Osobina osobina = new Osobina(new Long(0), null, ime, opis);
            return osobina;
        }catch(Exception ex){
            throw new Exception(ex.getMessage());
        }
    }
    private boolean postojiOsobina(String ime){
        for (Osobina osobina : lista) {
            if(osobina.getImeOsobine().toLowerCase().equals(ime.toLowerCase())){
                return true;
            }
        }
        return false;
    }

    private void proveraIzmena() {
        if(camacIzmena == null) {
            sacuvajAction();
        }
        else {
            izmenaAction();
        }
    }

    private void izmenaAction() {
        try {
            Camac camac = formirajCamac();
            camac.setCamacId(camacIzmena.getCamacId());
            Komunikacija.getInstance().PosaljiZahtev(camac, OperacijaUtil.IzmeniCamac);
            OdgovorUtil odgovor = Komunikacija.getInstance().PrimiOdgovor();
            if(odgovor.isZnak()) JOptionPane.showMessageDialog(gui, "Sistem je zapamtio camac!", "", JOptionPane.INFORMATION_MESSAGE);
            else JOptionPane.showMessageDialog(gui, "Sistem ne moze da izmeni camac!", "", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            Logger.getLogger(KreirajCamacKontroler.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(gui, ex.getMessage(), "", JOptionPane.ERROR_MESSAGE);
        }
    }
}
