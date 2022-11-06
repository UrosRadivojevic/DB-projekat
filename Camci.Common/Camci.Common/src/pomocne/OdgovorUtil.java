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
public class OdgovorUtil implements Serializable{
    private boolean znak;
    private Object objekat;

    public OdgovorUtil(boolean znak, Object objekat) {
        this.znak = znak;
        this.objekat = objekat;
    }

    public boolean isZnak() {
        return znak;
    }

    public void setZnak(boolean znak) {
        this.znak = znak;
    }

   
    public Object getObjekat() {
        return objekat;
    }

    public void setObjekat(Object objekat) {
        this.objekat = objekat;
    }
}
