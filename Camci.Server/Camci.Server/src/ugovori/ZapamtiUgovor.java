/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ugovori;

import domen.OpstiDomenskiObjekat;
import domen.StavkaUgovora;
import domen.Ugovor;

/**
 *
 * @author PC
 */
public class ZapamtiUgovor extends OpstaSistemskaOperacija{
    public ZapamtiUgovor(OpstiDomenskiObjekat odo){
        this.odo = odo;
    }
    
    @Override
    protected void operacija() throws Exception {
        Ugovor ugovor = (Ugovor) odo;
        Long id = broker.DodajOdo(ugovor);
        ugovor.setUgovorId(id);
        if(ugovor.getStavke().size() > 0) {
            for (StavkaUgovora stavke : ugovor.getStavke()) {
                stavke.setUgovor(ugovor);
                broker.DodajOdo(stavke);
                broker.IzmeniOdo(stavke.getCamac());
            }
        }
    }
}
