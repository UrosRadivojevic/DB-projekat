/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeli;

import domen.Kupac;
import domen.Prodavac;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author PC
 */
public class ProdavacModel extends AbstractTableModel{
    private final List<Prodavac> prodavci;

    private final String[] vrednosti = new String[]{"Ime","Prezime", "Datum rodjenja", "Broj telefona"};
    private final Class[] klase = new Class[]{ String.class, String.class, Date.class,String.class};

    public ProdavacModel(List<Prodavac> prodavci) {
        this.prodavci = prodavci;
    }

    @Override
    public int getRowCount() {
        if (prodavci == null) {
            return 0;
        }
        return prodavci.size();
    }

    @Override
    public int getColumnCount() {
        return vrednosti.length;

    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Prodavac prodavac = prodavci.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return prodavac.getIme();
            case 1:
                return prodavac.getPrezime();
            case 2:
                return prodavac.getDatumRodjenja();
            case 3:
                return prodavac.getBrojTelefona();
            default:
                return "n/a";
        }
    }
   
    @Override
    public String getColumnName(int column) {
        if (column > vrednosti.length) {
            return "n/a";
        }
        return vrednosti[column];
    }

    @Override
    public Class<?> getColumnClass(int column) {
        return klase[column];
    } 
}
