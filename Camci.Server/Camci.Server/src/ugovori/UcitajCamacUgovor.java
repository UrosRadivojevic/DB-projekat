/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ugovori;

import domen.Camac;
import domen.OpstiDomenskiObjekat;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author PC
 */
public class UcitajCamacUgovor extends OpstaSistemskaOperacija{
    public UcitajCamacUgovor(OpstiDomenskiObjekat odo){
        this.odo = odo;
    }
    
    @Override
    protected void operacija() throws Exception {
        List<Camac> objekti = broker.VratiOdo(new Camac()).stream().map(Camac.class::cast).collect(Collectors.toList());
        Camac c = (Camac) odo;
        for (Camac camac : objekti) {
            if(camac.getCamacId().equals(c.getCamacId()))
            {
                odo = camac;
                return;
            }
        }
        throw new Exception("Ne postoji ovaj camac");
    }
}
