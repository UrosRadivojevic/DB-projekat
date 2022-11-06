/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PC
 */
public class Osobina implements OpstiDomenskiObjekat, Serializable{
    private Long rbrOsobine; 
    private Camac camac;
    private String imeOsobine; 
    private String opisOsobine; 

    @Override
    public String toString() {
        return imeOsobine;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Osobina other = (Osobina) obj;
        if (!Objects.equals(this.imeOsobine, other.imeOsobine)) {
            return false;
        }
        if (!Objects.equals(this.opisOsobine, other.opisOsobine)) {
            return false;
        }
        if (!Objects.equals(this.rbrOsobine, other.rbrOsobine)) {
            return false;
        }
        if (!Objects.equals(this.camac, other.camac)) {
            return false;
        }
        return true;
    }

    public Osobina() {
    }

    public Osobina(Long rbrOsobine, Camac camac, String imeOsobine, String opisOsobine) {
        this.rbrOsobine = rbrOsobine;
        this.camac = camac;
        this.imeOsobine = imeOsobine;
        this.opisOsobine = opisOsobine;
    }

    public Long getRbrOsobine() {
        return rbrOsobine;
    }

    public void setRbrOsobine(Long rbrOsobine) {
        this.rbrOsobine = rbrOsobine;
    }

    public Camac getCamac() {
        return camac;
    }

    public void setCamac(Camac camac) {
        this.camac = camac;
    }

    public String getImeOsobine() {
        return imeOsobine;
    }

    public void setImeOsobine(String imeOsobine) {
        this.imeOsobine = imeOsobine;
    }

    public String getOpisOsobine() {
        return opisOsobine;
    }

    public void setOpisOsobine(String opisOsobine) {
        this.opisOsobine = opisOsobine;
    }
    
     @Override
    public String VrednostiUnosOdo() {
        return "" + camac.getCamacId() + ", '" + imeOsobine + "', '" + opisOsobine + "'";
    }

    @Override
    public String KoloneUnosOdo() {
        return "camacId, imeOsobine, opisOsobine";
    }

    @Override
    public String IzmenaUpitOdo() {
        return "camacId = " + camac.getCamacId() + ", imeOsobine= '" + imeOsobine + "', opisOsobine = '" + opisOsobine + "'";
    }

    @Override
    public String JoinUslovOdo() {
        return "join Camac c on (o.camacId = c.camacId)";
    }

    @Override
    public String WhereUpitOdo() {
        return "camacId = "+ camac.getCamacId();
    }

    @Override
    public List<OpstiDomenskiObjekat> ObjektiOdo(ResultSet rs) {
        List<OpstiDomenskiObjekat> lista = new ArrayList();
        try {
            while(rs.next()){
                Long camacId = rs.getLong("camacId");
                String nazivModela  = rs.getString("nazivModela");
                String opisCamca  = rs.getString("opisCamca");
                int godina = rs.getInt("godinaGarancije");
                int kolicina = rs.getInt("kolicinaNaStanju");
                double cena = rs.getDouble("cena");
                Camac camac = new Camac(camacId, nazivModela, opisCamca, godina, kolicina, cena, null);
                
                Long rbrOsobine = rs.getLong("rbrOsobine");
                String imeOsobine  = rs.getString("imeOsobine");
                String opisOsobine  = rs.getString("opisOsobine");
                Osobina osobina = new Osobina(rbrOsobine, camac, imeOsobine, opisOsobine);
                lista.add(osobina);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Prodavac.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    @Override
    public String TabeleOdo() {
        return "Osobina";
    }

    @Override
    public String TabelaNotacijaOdo() {
        return "Osobina o";
    }
    
}
