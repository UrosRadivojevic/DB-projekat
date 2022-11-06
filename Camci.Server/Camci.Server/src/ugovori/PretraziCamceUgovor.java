/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ugovori;

import domen.Camac;
import domen.Kupac;
import domen.OpstiDomenskiObjekat;
import domen.Osobina;
import domen.Prodavac;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import pomocne.PretragaUtil;

/**
 *
 * @author PC
 */
public class PretraziCamceUgovor extends OpstaSistemskaOperacija{
    
    List<Camac> lista;
    
    public PretraziCamceUgovor(OpstiDomenskiObjekat odo,List<Camac> lista){
        this.odo = odo;
        this.lista = lista;
    }
    
    @Override
    protected void operacija() throws Exception {
        List<Camac> objekti = broker.VratiOdo(new Camac()).stream().map(Camac.class::cast).collect(Collectors.toList());
        PretragaUtil pu = (PretragaUtil) odo;
        for (Camac camac : objekti) {
            if(camac.getNazivModela().toLowerCase().contains(pu.getTekst().toLowerCase()) || camac.getMarka().getNazivMarke().toLowerCase().contains(pu.getTekst().toLowerCase()) || 
                    camac.getOpisCamca().toLowerCase().contains(pu.getTekst().toLowerCase()))
            {
                lista.add(camac);
            }
        }
        
        if(lista.size() == 0) {
            throw new Exception("Nije nista pronadjeno!");
        }
        List<Osobina> osobine = broker.VratiOdo(new Osobina()).stream().map(Osobina.class::cast).collect(Collectors.toList());
        
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
