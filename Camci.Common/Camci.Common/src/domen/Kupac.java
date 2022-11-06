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
public class Kupac implements OpstiDomenskiObjekat, Serializable{
    private Long kupacId; 
    private String ime; 
    private String prezime; 
    private String adresa;
    private String mail;
    private String brojTelefona;
    private String napomena;

    public Kupac() {
    }

    @Override
    public String toString() {
        return mail;
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final Kupac other = (Kupac) obj;
        if (!Objects.equals(this.ime, other.ime)) {
            return false;
        }
        if (!Objects.equals(this.prezime, other.prezime)) {
            return false;
        }
        if (!Objects.equals(this.adresa, other.adresa)) {
            return false;
        }
        if (!Objects.equals(this.mail, other.mail)) {
            return false;
        }
        if (!Objects.equals(this.brojTelefona, other.brojTelefona)) {
            return false;
        }
        if (!Objects.equals(this.napomena, other.napomena)) {
            return false;
        }
        if (!Objects.equals(this.kupacId, other.kupacId)) {
            return false;
        }
        return true;
    }

    public Long getKupacId() {
        return kupacId;
    }

    public void setKupacId(Long kupacId) {
        this.kupacId = kupacId;
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

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getBrojTelefona() {
        return brojTelefona;
    }

    public void setBrojTelefona(String brojTelefona) {
        this.brojTelefona = brojTelefona;
    }

    public String getNapomena() {
        return napomena;
    }

    public void setNapomena(String napomena) {
        this.napomena = napomena;
    }

    public Kupac(Long kupacId, String ime, String prezime, String adresa, String mail, String brojTelefona, String napomena) {
        this.kupacId = kupacId;
        this.ime = ime;
        this.prezime = prezime;
        this.adresa = adresa;
        this.mail = mail;
        this.brojTelefona = brojTelefona;
        this.napomena = napomena;
    }
    
    @Override
    public String VrednostiUnosOdo() {
        return "'" + ime + "', '" + prezime + "', '" + adresa + "', '" + mail + "', '" + brojTelefona + "', '" + napomena + "'" ;
    }

    @Override
    public String KoloneUnosOdo() {
        return "ime,prezime,adresa,mail,brojTelefona, napomena";
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
        return "kupacId = "+ kupacId;
    }

    @Override
    public List<OpstiDomenskiObjekat> ObjektiOdo(ResultSet rs) {
        List<OpstiDomenskiObjekat> lista = new ArrayList();
        try {
            while(rs.next()){
                Long kupacId = rs.getLong("kupacId");
                String ime  = rs.getString("ime");
                String prezime  = rs.getString("prezime");
                String adresa  = rs.getString("adresa");
                String mail  = rs.getString("mail");
                String brojTelefona  = rs.getString("brojTelefona");
                String napomena  = rs.getString("napomena");
                Kupac kupac = new Kupac(kupacId, ime, prezime, adresa, mail, brojTelefona, napomena);
                lista.add(kupac);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Kupac.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    @Override
    public String TabeleOdo() {
        return "Kupac";
    }

    @Override
    public String TabelaNotacijaOdo() {
        return "Kupac k";
    }
    

}
