/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ugovori;

import domen.Camac;
import domen.OpstiDomenskiObjekat;
import domen.Osobina;
import domen.Prodavac;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author PC
 */
public class VratiCamceUgovor extends OpstaSistemskaOperacija{
    List<Camac> lista;
    public VratiCamceUgovor(OpstiDomenskiObjekat odo,List<Camac> lista){
        this.odo = odo;
        this.lista = lista;
    }
    
    @Override
    protected void operacija() throws Exception {
        List<OpstiDomenskiObjekat> objekti = broker.VratiOdo(new Camac());
        lista.addAll(objekti.stream().map(Camac.class::cast).collect(Collectors.toList()));
        
        List<OpstiDomenskiObjekat> objekti2 = broker.VratiOdo(new Osobina());
        List<Osobina> osobine = objekti2.stream().map(Osobina.class::cast).collect(Collectors.toList());
        
        for (Camac camac : lista) {
            camac.setOsobine(new ArrayList());
            for (Osobina osobina : osobine) {
                if(osobina.getCamac().getCamacId().equals(camac.getCamacId())){
                    camac.getOsobine().add(osobina);
                }
            }
        }
    }
}
