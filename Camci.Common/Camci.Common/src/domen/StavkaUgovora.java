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
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PC
 */
public class StavkaUgovora implements OpstiDomenskiObjekat, Serializable{
    private Long rbrStavke; 
    private Ugovor ugovor; 
    private int kolicina; 
    private double ukupnaNaknada; 
    private Camac camac; 

    public StavkaUgovora() {
    }

    public Long getRbrStavke() {
        return rbrStavke;
    }

    @Override
    public String toString() {
        return "StavkaUgovora{" + "rbrStavke=" + rbrStavke + ", ugovor=" + ugovor + ", kolicina=" + kolicina + ", ukupnaNaknada=" + ukupnaNaknada + ", camac=" + camac + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final StavkaUgovora other = (StavkaUgovora) obj;
        if (this.kolicina != other.kolicina) {
            return false;
        }
        if (Double.doubleToLongBits(this.ukupnaNaknada) != Double.doubleToLongBits(other.ukupnaNaknada)) {
            return false;
        }
        if (!Objects.equals(this.rbrStavke, other.rbrStavke)) {
            return false;
        }
        if (!Objects.equals(this.ugovor, other.ugovor)) {
            return false;
        }
        if (!Objects.equals(this.camac, other.camac)) {
            return false;
        }
        return true;
    }

    public void setRbrStavke(Long rbrStavke) {
        this.rbrStavke = rbrStavke;
    }

    public Ugovor getUgovor() {
        return ugovor;
    }

    public void setUgovor(Ugovor ugovor) {
        this.ugovor = ugovor;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public double getUkupnaNaknada() {
        return ukupnaNaknada;
    }

    public void setUkupnaNaknada(double ukupnaNaknada) {
        this.ukupnaNaknada = ukupnaNaknada;
    }

    public Camac getCamac() {
        return camac;
    }

    public void setCamac(Camac camac) {
        this.camac = camac;
    }

    public StavkaUgovora(Long rbrStavke, Ugovor ugovor, int kolicina, double ukupnaNaknada, Camac camac) {
        this.rbrStavke = rbrStavke;
        this.ugovor = ugovor;
        this.kolicina = kolicina;
        this.ukupnaNaknada = ukupnaNaknada;
        this.camac = camac;
    }
    
    @Override
    public String VrednostiUnosOdo() {
        return "" + ugovor.getUgovorId() + ", " + kolicina + ", " + ukupnaNaknada + ", " + camac.getCamacId();
    }

    @Override
    public String KoloneUnosOdo() {
        return "ugovorId, kolicina, ukupnaNaknada, camacId";
    }

    @Override
    public String IzmenaUpitOdo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String JoinUslovOdo() {
        return "join Ugovor u on (u.ugovorId = su.ugovorId) join Camac c on (c.camacId = su.camacId) join MarkaCamca mc on (mc.markaId = c.markaId)";
    }

    @Override
    public String WhereUpitOdo() {
        return "ugovorId = "+ ugovor.getUgovorId();
    }

    @Override
    public List<OpstiDomenskiObjekat> ObjektiOdo(ResultSet rs) {
        List<OpstiDomenskiObjekat> lista = new ArrayList();
        try {
            while(rs.next()){
                Long ugovorId = rs.getLong("ugovorId");
                Date datum  = rs.getDate("datumKreiranja");
                String opisUgovora  = rs.getString("opisUgovora");
                double ukupanDug = rs.getDouble("ukupanDug");
                
                Ugovor u = new Ugovor(ugovorId, opisUgovora, datum, ukupanDug, null, null);
                
                Long camacId = rs.getLong("camacId");
                String nazivModela  = rs.getString("nazivModela");
                String opisCamca  = rs.getString("opisCamca");
                int godina = rs.getInt("godinaGarancije");
                int kolicina = rs.getInt("kolicinaNaStanju");
                double cena = rs.getDouble("cena");
                
                 Long markaId = rs.getLong("markaId");
                String nazivMarke  = rs.getString("nazivMarke");
                MarkaCamca mc = new MarkaCamca(markaId, nazivMarke);
                
                Camac camac = new Camac(camacId, nazivModela, opisCamca, godina, kolicina, cena, mc);
                
                
                Long rbrStavke = rs.getLong("rbrStavke");
                 int kolicinaStavka = rs.getInt("kolicina");
                double ukupnaNaknada = rs.getDouble("ukupnaNaknada");
                
                StavkaUgovora su = new StavkaUgovora(rbrStavke, u, kolicinaStavka, ukupnaNaknada, camac);
                lista.add(su);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Ugovor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    @Override
    public String TabeleOdo() {
        return "StavkaUgovora";
    }

    @Override
    public String TabelaNotacijaOdo() {
        return "StavkaUgovora su";
    }
    
}
