/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pomocne;

import java.io.Serializable;

/**
 *
 * @author PC
 */
public class ZahtevUtil implements Serializable{
    private int so;
    private Object objekat;

    public int getSo() {
        return so;
    }

    public void setSo(int so) {
        this.so = so;
    }

   

   
    public Object getObjekat() {
        return objekat;
    }

    public void setObjekat(Object objekat) {
        this.objekat = objekat;
    }

    public ZahtevUtil(int operacija, Object objekat) {
        this.so = operacija;
        this.objekat = objekat;
    }
}
