/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikaciona_logika;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PC
 */
public class ServerskaNit extends Thread{
    private final List<KlijentskaNit> klijenti;
    private final ServerSocket serverSoket;

    public ServerskaNit() throws IOException {
        this.klijenti = new ArrayList();
        this.serverSoket = new ServerSocket(9000);
    }

    @Override
    public void run() {
        while (serverSoket.isClosed() == false) {
            try {
                Socket soketKlijent = serverSoket.accept();
                KlijentskaNit nit = new KlijentskaNit(soketKlijent);
                klijenti.add(nit);
                nit.start();
            } catch (SocketException ex) {
                System.out.println("Doslo je do greske:\n " + ex.getMessage());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        for (KlijentskaNit klijent : klijenti) {
            try {
                klijent.getSoket().close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public ServerSocket getServerSoket() {
        return serverSoket;
    }

   

    public void zaustaviServer() throws IOException {
        serverSoket.close();
    }
}
