/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ugovori;

import bbp.Broker;
import domen.OpstiDomenskiObjekat;

/**
 *
 * @author PC
 */
public abstract class OpstaSistemskaOperacija {
    protected Broker broker;
    protected OpstiDomenskiObjekat odo;

    public OpstaSistemskaOperacija() {
        broker = new Broker();
    }
    protected abstract void operacija() throws Exception;

    public void izvrsiSO() throws Exception {
        broker.connection();
        try {
            operacija();
            broker.commit();
        } catch (Exception ex) {
            broker.rollback();
            ex.printStackTrace();
            throw ex;
        } finally {
            broker.disconnection();
        }
    }
    public OpstiDomenskiObjekat getOdo(){
        return this.odo;
    }
}
