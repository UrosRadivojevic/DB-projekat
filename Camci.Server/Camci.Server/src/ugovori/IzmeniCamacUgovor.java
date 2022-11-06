/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ugovori;

import domen.Camac;
import domen.OpstiDomenskiObjekat;
import domen.Osobina;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author PC
 */
public class IzmeniCamacUgovor extends OpstaSistemskaOperacija{

    public IzmeniCamacUgovor(OpstiDomenskiObjekat odo){
        this.odo = odo;
    }
    
    @Override
    protected void operacija() throws Exception {
        Camac camac = (Camac) odo;
        proveraPostoji(camac);
        broker.IzmeniOdo(camac);
            
        Osobina o = new Osobina();
        o.setCamac(camac);
        broker.ObrisiOdo(o);

        if(camac.getOsobine().size() > 0) {
            for (Osobina osobina : camac.getOsobine()) {
                osobina.setCamac(camac);
                broker.DodajOdo(osobina);
            }
        }
    }
    
    private void proveraPostoji(Camac camac) throws Exception {
        List<Camac> objekti = broker.VratiOdo(new Camac()).stream().map(Camac.class::cast).collect(Collectors.toList());
        for (Camac camac1 : objekti) {
            if(camac1.getNazivModela().toLowerCase().equals(camac.getNazivModela().toLowerCase()) 
                    && camac1.getMarka().getMarkaId().equals(camac.getMarka().getMarkaId()) && !camac1.getCamacId().equals(camac.getCamacId())){
                throw new Exception("Vec postoji camac sa datim nazivom i istom markom");
            }
        }
    }
    
}
