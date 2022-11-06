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
public class MarkaCamca implements OpstiDomenskiObjekat, Serializable{
    private Long markaId; 
    private String nazivMarke; 

    public MarkaCamca() {
    }

    @Override
    public String toString() {
        return nazivMarke;
    }

    public MarkaCamca(Long markaId, String nazivMarke) {
        this.markaId = markaId;
        this.nazivMarke = nazivMarke;
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
        final MarkaCamca other = (MarkaCamca) obj;
        if (!Objects.equals(this.nazivMarke, other.nazivMarke)) {
            return false;
        }
        if (!Objects.equals(this.markaId, other.markaId)) {
            return false;
        }
        return true;
    }

    public Long getMarkaId() {
        return markaId;
    }

    public void setMarkaId(Long markaId) {
        this.markaId = markaId;
    }

    public String getNazivMarke() {
        return nazivMarke;
    }

    public void setNazivMarke(String nazivMarke) {
        this.nazivMarke = nazivMarke;
    }

    @Override
    public String VrednostiUnosOdo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String KoloneUnosOdo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        return "markaId = " + markaId;
    }

    @Override
    public List<OpstiDomenskiObjekat> ObjektiOdo(ResultSet rs) {
        List<OpstiDomenskiObjekat> lista = new ArrayList();
        try {
            while(rs.next()){
                Long markaId = rs.getLong("markaId");
                String nazivMarke  = rs.getString("nazivMarke");
                MarkaCamca mc = new MarkaCamca(markaId, nazivMarke);
                lista.add(mc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MarkaCamca.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    @Override
    public String TabeleOdo() {
        return "MarkaCamca";
    }

    @Override
    public String TabelaNotacijaOdo() {
        return "MarkaCamca mc";
    }
    
}
