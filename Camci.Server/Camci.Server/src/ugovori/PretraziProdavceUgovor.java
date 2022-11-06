/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ugovori;

import domen.Kupac;
import domen.OpstiDomenskiObjekat;
import domen.Prodavac;
import java.util.List;
import java.util.stream.Collectors;
import pomocne.PretragaUtil;

/**
 *
 * @author PC
 */
public class PretraziProdavceUgovor extends OpstaSistemskaOperacija{
    List<Prodavac> lista;
    public PretraziProdavceUgovor(OpstiDomenskiObjekat odo,List<Prodavac> lista){
        this.odo = odo;
        this.lista = lista;
    }
    
    @Override
    protected void operacija() throws Exception {
        List<Prodavac> objekti = broker.VratiOdo(new Prodavac()).stream().map(Prodavac.class::cast).collect(Collectors.toList());
        PretragaUtil pu = (PretragaUtil) odo;
        for (Prodavac prodavac : objekti) {
            if(prodavac.getIme().toLowerCase().contains(pu.getTekst().toLowerCase()) || prodavac.getPrezime().toLowerCase().contains(pu.getTekst().toLowerCase()) || 
                    prodavac.getBrojTelefona().toLowerCase().contains(pu.getTekst().toLowerCase()))
            {
                lista.add(prodavac);
            }
        }
        if(lista.size() == 0) {
            throw new Exception("Nije nista pronadjeno!");
        }
    }
}
