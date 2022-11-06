/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeli;

import domen.Camac;
import domen.Kupac;
import domen.MarkaCamca;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author PC
 */
public class KupacModel extends AbstractTableModel{
    private final List<Kupac> kupci;

    private final String[] vrednosti = new String[]{"Ime","Prezime", "Adresa", "Mail", "Broj telefona", "Napomena"};
    private final Class[] klase = new Class[]{ String.class, String.class, String.class,String.class,String.class, String.class};

    public KupacModel(List<Kupac> kupci) {
        this.kupci = kupci;
    }

    @Override
    public int getRowCount() {
        if (kupci == null) {
            return 0;
        }
        return kupci.size();
    }

    @Override
    public int getColumnCount() {
        return vrednosti.length;

    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Kupac kupac = kupci.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return kupac.getIme();
            case 1:
                return kupac.getPrezime();
            case 2:
                return kupac.getAdresa();
            case 3:
                return kupac.getMail();
            case 4:
                return kupac.getBrojTelefona();
            case 5:
                return kupac.getNapomena();
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
