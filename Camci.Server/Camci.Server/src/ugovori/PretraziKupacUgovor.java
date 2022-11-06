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
import pomocne.PretragaUtil;

/**
 *
 * @author PC
 */
public class PretraziKupacUgovor extends OpstaSistemskaOperacija{
    List<Kupac> lista;
    public PretraziKupacUgovor(OpstiDomenskiObjekat odo,List<Kupac> lista){
        this.odo = odo;
        this.lista = lista;
    }
    
    @Override
    protected void operacija() throws Exception {
        List<Kupac> objekti = broker.VratiOdo(new Kupac()).stream().map(Kupac.class::cast).collect(Collectors.toList());
        PretragaUtil pu = (PretragaUtil) odo;
        for (Kupac kupac : objekti) {
            if(kupac.getIme().toLowerCase().contains(pu.getTekst().toLowerCase()) || kupac.getPrezime().toLowerCase().contains(pu.getTekst().toLowerCase()) || 
                    kupac.getAdresa().toLowerCase().contains(pu.getTekst().toLowerCase()) || kupac.getBrojTelefona().toLowerCase().contains(pu.getTekst().toLowerCase()) ||
                    kupac.getMail().toLowerCase().contains(pu.getTekst().toLowerCase()) ){
                lista.add(kupac);
            }
        }
        if(lista.size() == 0) {
            throw new Exception("Nije nista pronadjeno!");
        }
        
    }
}
