/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zahtevi;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import pomocne.OdgovorUtil;
import pomocne.ZahtevUtil;

/**
 *
 * @author PC
 */
public class Komunikacija {
     private static Komunikacija instance;

    private final Socket soket;
    private final ObjectOutputStream izlaz;
    private final ObjectInputStream ulaz;
    
    private Komunikacija() throws IOException {
        soket = new Socket("localhost", 9000);
        izlaz = new ObjectOutputStream(soket.getOutputStream());
        ulaz = new ObjectInputStream(soket.getInputStream());
    }
    public static Komunikacija getInstance() throws IOException {
        if (instance == null) {
            instance = new Komunikacija();
        }
        return instance;
    }

    public void PosaljiZahtev(Object objekat, int operacija) throws IOException {
        try {
            izlaz.writeObject(new ZahtevUtil(operacija,objekat));
        } catch (IOException ex) {
            Logger.getLogger(Komunikacija.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        } 
    }
    
    public OdgovorUtil PrimiOdgovor() throws IOException, ClassNotFoundException {
        try {
            return (OdgovorUtil) ulaz.readObject();
        } catch (IOException ex) {
            Logger.getLogger(Komunikacija.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Komunikacija.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }
    
    
}
