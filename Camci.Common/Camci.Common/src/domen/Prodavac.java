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
public class Prodavac implements OpstiDomenskiObjekat, Serializable{
    private Long prodavacId; 
    private String ime; 
    private String prezime; 
    private Date datumRodjenja; 
    private String brojTelefona;

    public Prodavac() {
    }

    @Override
    public String toString() {
        return ime + " " + prezime;
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
        final Prodavac other = (Prodavac) obj;
        if (!Objects.equals(this.ime, other.ime)) {
            return false;
        }
        if (!Objects.equals(this.prezime, other.prezime)) {
            return false;
        }
        if (!Objects.equals(this.brojTelefona, other.brojTelefona)) {
            return false;
        }
        if (!Objects.equals(this.prodavacId, other.prodavacId)) {
            return false;
        }
        if (!Objects.equals(this.datumRodjenja, other.datumRodjenja)) {
            return false;
        }
        return true;
    }

    public Long getProdavacId() {
        return prodavacId;
    }

    public void setProdavacId(Long prodavacId) {
        this.prodavacId = prodavacId;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public Date getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(Date datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    public String getBrojTelefona() {
        return brojTelefona;
    }

    public void setBrojTelefona(String brojTelefona) {
        this.brojTelefona = brojTelefona;
    }

    public Prodavac(Long prodavacId, String ime, String prezime, Date datumRodjenja, String brojTelefona) {
        this.prodavacId = prodavacId;
        this.ime = ime;
        this.prezime = prezime;
        this.datumRodjenja = datumRodjenja;
        this.brojTelefona = brojTelefona;
    }

    @Override
    public String VrednostiUnosOdo() {
        DateFormat d = new SimpleDateFormat("yyyy-MM-dd");
        return "'" + ime + "', '" + prezime + "', '" + d.format(datumRodjenja) + "', '" + brojTelefona + "'";
    }

    @Override
    public String KoloneUnosOdo() {
        return "ime,prezime,datumRodjenja,brojTelefona";
    }

    @Override
    public String IzmenaUpitOdo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String JoinUslovOdo() {
        return "";
    }

    @Override
    public String WhereUpitOdo() {
        return "prodavacId = "+ prodavacId;
    }

    @Override
    public List<OpstiDomenskiObjekat> ObjektiOdo(ResultSet rs) {
       List<OpstiDomenskiObjekat> lista = new ArrayList();
        try {
            while(rs.next()){
                Long prodavacId = rs.getLong("prodavacId");
                String ime  = rs.getString("ime");
                String prezime  = rs.getString("prezime");
                String brojTelefona  = rs.getString("brojTelefona");
                Date datum = rs.getDate("datumRodjenja");
                Prodavac prodavac = new Prodavac(prodavacId, ime, prezime, datum, brojTelefona);
                lista.add(prodavac);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Prodavac.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    @Override
    public String TabeleOdo() {
        return "Prodavac";
    }

    @Override
    public String TabelaNotacijaOdo() {
        return "Prodavac p";
    }
    
}
