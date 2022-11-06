/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ugovori;

import domen.MarkaCamca;
import domen.OpstiDomenskiObjekat;
import domen.Prodavac;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author PC
 */
public class VratiProdavceUgovor extends OpstaSistemskaOperacija{
    List<Prodavac> lista;
    public VratiProdavceUgovor(OpstiDomenskiObjekat odo,List<Prodavac> lista){
        this.odo = odo;
        this.lista = lista;
    }
    
    @Override
    protected void operacija() throws Exception {
        List<OpstiDomenskiObjekat> objekti = broker.VratiOdo(new Prodavac());
        lista.addAll(objekti.stream().map(Prodavac.class::cast).collect(Collectors.toList()));
    }
}
