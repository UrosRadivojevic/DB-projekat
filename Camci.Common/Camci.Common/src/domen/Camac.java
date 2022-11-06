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
public class Camac implements OpstiDomenskiObjekat, Serializable{
    private Long camacId; 
    private String nazivModela; 
    private String opisCamca; 
    private int godinaGarancije; 
    private int kolicinaNaStanju; 
    private double cena; 
    private MarkaCamca marka; 
    private List<Osobina> osobine;

    public List<Osobina> getOsobine() {
        return osobine;
    }

    public void setOsobine(List<Osobina> osobine) {
        this.osobine = osobine;
    }
    

    public Camac() {
    }

    @Override
    public String toString() {
        return nazivModela;
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
        final Camac other = (Camac) obj;
        if (this.godinaGarancije != other.godinaGarancije) {
            return false;
        }
        if (this.kolicinaNaStanju != other.kolicinaNaStanju) {
            return false;
        }
        if (Double.doubleToLongBits(this.cena) != Double.doubleToLongBits(other.cena)) {
            return false;
        }
        if (!Objects.equals(this.nazivModela, other.nazivModela)) {
            return false;
        }
        if (!Objects.equals(this.opisCamca, other.opisCamca)) {
            return false;
        }
        if (!Objects.equals(this.camacId, other.camacId)) {
            return false;
        }
        if (!Objects.equals(this.marka, other.marka)) {
            return false;
        }
        return true;
    }

    public Long getCamacId() {
        return camacId;
    }

    public void setCamacId(Long camacId) {
        this.camacId = camacId;
    }

    public String getNazivModela() {
        return nazivModela;
    }

    public void setNazivModela(String nazivModela) {
        this.nazivModela = nazivModela;
    }

    public String getOpisCamca() {
        return opisCamca;
    }

    public void setOpisCamca(String opisCamca) {
        this.opisCamca = opisCamca;
    }

    public int getGodinaGarancije() {
        return godinaGarancije;
    }

    public void setGodinaGarancije(int godinaGarancije) {
        this.godinaGarancije = godinaGarancije;
    }

    public int getKolicinaNaStanju() {
        return kolicinaNaStanju;
    }

    public void setKolicinaNaStanju(int kolicinaNaStanju) {
        this.kolicinaNaStanju = kolicinaNaStanju;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public MarkaCamca getMarka() {
        return marka;
    }

    public void setMarka(MarkaCamca marka) {
        this.marka = marka;
    }

    public Camac(Long camacId, String nazivModela, String opisCamca, int godinaGarancije, int kolicinaNaStanju, double cena, MarkaCamca marka) {
        this.camacId = camacId;
        this.nazivModela = nazivModela;
        this.opisCamca = opisCamca;
        this.godinaGarancije = godinaGarancije;
        this.kolicinaNaStanju = kolicinaNaStanju;
        this.cena = cena;
        this.marka = marka;
    }
    
    @Override
    public String VrednostiUnosOdo() {
        return "'" + nazivModela + "', '" + opisCamca + "', " + godinaGarancije + ", " + kolicinaNaStanju + ", " + cena + ", " + marka.getMarkaId();
    }

    @Override
    public String KoloneUnosOdo() {
        return "nazivModela, opisCamca, godinaGarancije, kolicinaNaStanju, cena, markaId";
    }

    @Override
    public String IzmenaUpitOdo() {
        return "nazivModela = '" + nazivModela + "',opisCamca = '" + opisCamca + "',godinaGarancije=  " + godinaGarancije + ", kolicinaNaStanju=" + kolicinaNaStanju + ", cena = " + cena + ",markaId= " + marka.getMarkaId();
    }

    @Override
    public String JoinUslovOdo() {
        return "join MarkaCamca mc on (mc.markaId = c.markaId)";
    }

    @Override
    public String WhereUpitOdo() {
        return "camacId = "+ camacId;
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
                
                 Long markaId = rs.getLong("markaId");
                String nazivMarke  = rs.getString("nazivMarke");
                MarkaCamca mc = new MarkaCamca(markaId, nazivMarke);
                
                Camac camac = new Camac(camacId, nazivModela, opisCamca, godina, kolicina, cena, mc);
                lista.add(camac);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Prodavac.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    @Override
    public String TabeleOdo() {
        return "Camac";
    }

    @Override
    public String TabelaNotacijaOdo() {
        return "Camac c";
    }
    
}
