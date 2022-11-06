/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author PC
 */
public interface OpstiDomenskiObjekat {
     //Unos u bazu
    public String VrednostiUnosOdo();
    public String KoloneUnosOdo();
    // Izmena
    public String IzmenaUpitOdo();
    public String JoinUslovOdo();
    //Brisanje
    public String WhereUpitOdo();
    //Vratiti sve 
    public List<OpstiDomenskiObjekat> ObjektiOdo(ResultSet rs);
    public String TabeleOdo();
    public String TabelaNotacijaOdo();

   
}
