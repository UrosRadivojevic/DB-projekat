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
public class VratiKupceUgovor extends OpstaSistemskaOperacija{
    List<Kupac> lista;
    public VratiKupceUgovor(OpstiDomenskiObjekat odo,List<Kupac> lista){
        this.odo = odo;
        this.lista = lista;
    }
    
    @Override
    protected void operacija() throws Exception {
        List<OpstiDomenskiObjekat> objekti = broker.VratiOdo(new Kupac());
        lista.addAll(objekti.stream().map(Kupac.class::cast).collect(Collectors.toList()));
    }
}
