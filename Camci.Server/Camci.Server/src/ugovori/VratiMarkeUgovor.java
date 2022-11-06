/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ugovori;

import domen.Kupac;
import domen.MarkaCamca;
import domen.OpstiDomenskiObjekat;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author PC
 */
public class VratiMarkeUgovor extends OpstaSistemskaOperacija{
    List<MarkaCamca> lista;
    public VratiMarkeUgovor(OpstiDomenskiObjekat odo,List<MarkaCamca> lista){
        this.odo = odo;
        this.lista = lista;
    }
    
    @Override
    protected void operacija() throws Exception {
        List<OpstiDomenskiObjekat> objekti = broker.VratiOdo(new MarkaCamca());
        lista.addAll(objekti.stream().map(MarkaCamca.class::cast).collect(Collectors.toList()));
    }
}
