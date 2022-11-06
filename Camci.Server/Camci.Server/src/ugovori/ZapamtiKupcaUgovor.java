/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ugovori;

import domen.Kupac;
import domen.OpstiDomenskiObjekat;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author PC
 */
public class ZapamtiKupcaUgovor extends OpstaSistemskaOperacija{
    public ZapamtiKupcaUgovor(OpstiDomenskiObjekat odo){
        this.odo = odo;
    }
    
    @Override
    protected void operacija() throws Exception {
        proveraPostoji((Kupac)odo);
        broker.DodajOdo((Kupac) odo);
    }
    
    private void proveraPostoji(Kupac kupac) throws Exception {
        List<Kupac> objekti = broker.VratiOdo(new Kupac()).stream().map(Kupac.class::cast).collect(Collectors.toList());
        for (Kupac kupac1 : objekti) {
            if(kupac1.getMail().toLowerCase().equals(kupac.getMail().toLowerCase())){
                throw new Exception("Vec postoji kupac sa datim mailom");
            }
        }
        
    }
}
