/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikaciona_logika;

import java.io.EOFException;
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
public class KlijentskaNit extends Thread{
    private final Socket soket;
    private final ObjectInputStream inputStream;
    private final ObjectOutputStream outpuntStream;

    public KlijentskaNit(Socket soket) throws IOException {
        this.soket = soket;
        this.inputStream = new ObjectInputStream(soket.getInputStream());
        this.outpuntStream = new ObjectOutputStream(soket.getOutputStream());
    }

    public Socket getSoket() {
        return soket;
    }

    @Override
    public void run() {
        while (soket.isClosed() == false) {
            try {
                ZahtevUtil zahtev = (ZahtevUtil) inputStream.readObject();
                OdgovorUtil odgovor = Kontroler.getInstance().ObradiZahtev(zahtev);
                this.outpuntStream.writeObject(odgovor);
            } catch (EOFException ex) {
                try {
                    soket.close();
                    break;
                } catch (IOException ex1) {
                    Logger.getLogger(KlijentskaNit.class.getName()).log(Level.SEVERE, null, ex1);
                }
            } catch (IOException | ClassNotFoundException ex) {
                try {
                    soket.close();
                } catch (IOException ex1) {
                    Logger.getLogger(KlijentskaNit.class.getName()).log(Level.SEVERE, null, ex1);
                }
            } catch (Exception ex) {
                Logger.getLogger(KlijentskaNit.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
   
}
