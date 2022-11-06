/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeli;

import domen.Camac;
import domen.Osobina;
import domen.StavkaUgovora;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author PC
 */
public class StavkaModel extends AbstractTableModel{
     private final List<StavkaUgovora> stavke;

    private final String[] vrednosti = new String[]{"Kolicina","Camac", "Ukupna naknada"};
    private final Class[] klase = new Class[]{ Integer.class, Camac.class, Double.class};

    public StavkaModel(List<StavkaUgovora> stavke) {
        this.stavke = stavke;
    }

    @Override
    public int getRowCount() {
        if (stavke == null) {
            return 0;
        }
        return stavke.size();
    }

    @Override
    public int getColumnCount() {
        return vrednosti.length;

    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        StavkaUgovora stavka = stavke.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return stavka.getKolicina();
            case 1:
                return stavka.getCamac();
            case 2:
                return stavka.getUkupnaNaknada();
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
