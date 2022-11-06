/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ugovori;

import domen.OpstiDomenskiObjekat;
import domen.Prodavac;

/**
 *
 * @author PC
 */
public class ZapamtiProdavcaUgovor extends OpstaSistemskaOperacija{
    public ZapamtiProdavcaUgovor(OpstiDomenskiObjekat odo){
        this.odo = odo;
    }
    
    @Override
    protected void operacija() throws Exception {
        broker.DodajOdo((Prodavac) odo);
    }
}
