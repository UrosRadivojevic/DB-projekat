/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
public class Ugovor implements OpstiDomenskiObjekat, Serializable{
    private Long ugovorId; 
    private String opisUgovora; 
    private Date datumKreiranja; 
    private double ukupanDug; 
    private Kupac kupac; 
    private Prodavac prodavac; 
    private List<StavkaUgovora> stavke;

    public List<StavkaUgovora> getStavke() {
        return stavke;
    }

    public void setStavke(List<StavkaUgovora> stavke) {
        this.stavke = stavke;
    }
    

    public Ugovor() {
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public String toString() {
        return "Ugovor{" + "ugovorId=" + ugovorId + ", opisUgovora=" + opisUgovora + ", datumKreiranja=" + datumKreiranja + ", ukupanDug=" + ukupanDug + ", kupac=" + kupac + ", prodavac=" + prodavac + '}';
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
        final Ugovor other = (Ugovor) obj;
        if (Double.doubleToLongBits(this.ukupanDug) != Double.doubleToLongBits(other.ukupanDug)) {
            return false;
        }
        if (!Objects.equals(this.opisUgovora, other.opisUgovora)) {
            return false;
        }
        if (!Objects.equals(this.ugovorId, other.ugovorId)) {
            return false;
        }
        if (!Objects.equals(this.datumKreiranja, other.datumKreiranja)) {
            return false;
        }
        if (!Objects.equals(this.kupac, other.kupac)) {
            return false;
        }
        if (!Objects.equals(this.prodavac, other.prodavac)) {
            return false;
        }
        return true;
    }

    public Long getUgovorId() {
        return ugovorId;
    }

    public void setUgovorId(Long ugovorId) {
        this.ugovorId = ugovorId;
    }

    public String getOpisUgovora() {
        return opisUgovora;
    }

    public void setOpisUgovora(String opisUgovora) {
        this.opisUgovora = opisUgovora;
    }

    public Date getDatumKreiranja() {
        return datumKreiranja;
    }

    public void setDatumKreiranja(Date datumKreiranja) {
        this.datumKreiranja = datumKreiranja;
    }

    public double getUkupanDug() {
        return ukupanDug;
    }

    public void setUkupanDug(double ukupanDug) {
        this.ukupanDug = ukupanDug;
    }

    public Kupac getKupac() {
        return kupac;
    }

    public void setKupac(Kupac kupac) {
        this.kupac = kupac;
    }

    public Prodavac getProdavac() {
        return prodavac;
    }

    public void setProdavac(Prodavac prodavac) {
        this.prodavac = prodavac;
    }

    public Ugovor(Long ugovorId, String opisUgovora, Date datumKreiranja, double ukupanDug, Kupac kupac, Prodavac prodavac) {
        this.ugovorId = ugovorId;
        this.opisUgovora = opisUgovora;
        this.datumKreiranja = datumKreiranja;
        this.ukupanDug = ukupanDug;
        this.kupac = kupac;
        this.prodavac = prodavac;
    }
    
    @Override
    public String VrednostiUnosOdo() {
        DateFormat d = new SimpleDateFormat("yyyy-MM-dd");
        return "'" + opisUgovora + "',' " + d.format(datumKreiranja) + "', " + ukupanDug + ", " + kupac.getKupacId() + ", " + prodavac.getProdavacId();
    }

    @Override
    public String KoloneUnosOdo() {
        return "opisUgovora, datumKreiranja, ukupanDug, kupacId, prodavacId";
    }

    @Override
    public String IzmenaUpitOdo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String JoinUslovOdo() {
        return "join Kupac k on (u.kupacId = k.kupacId) join Prodavac p on (p.prodavacId = u.prodavacId)";
    }

    @Override
    public String WhereUpitOdo() {
        return "ugovorId = "+ ugovorId;
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
                
                Long prodavacId = rs.getLong("prodavacId");
                String ime  = rs.getString("ime");
                String prezime  = rs.getString("prezime");
                Prodavac prodavac = new Prodavac(prodavacId, ime, prezime, null, "");
                
                //ovde videti jos sta raditi sa dva imena..
                Long kupacId = rs.getLong("kupacId");
                String imeK  = rs.getString("ime");
                String prezimeK  = rs.getString("prezime");
                String mail  = rs.getString("mail");
                Kupac kupac = new Kupac(kupacId, imeK, prezimeK, "", mail, "", "");
                
                Ugovor u = new Ugovor(ugovorId, opisUgovora, datum, ukupanDug, kupac, prodavac);
                lista.add(u);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Ugovor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    @Override
    public String TabeleOdo() {
        return "Ugovor";
    }

    @Override
    public String TabelaNotacijaOdo() {
        return "Ugovor u";
    }
    
}
