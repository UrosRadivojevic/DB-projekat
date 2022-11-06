/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeli;

import domen.Kupac;
import domen.Osobina;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author PC
 */
public class OsobinaModel extends AbstractTableModel{
    private final List<Osobina> osobine;

    private final String[] vrednosti = new String[]{"Ime osobine","Opis osobine"};
    private final Class[] klase = new Class[]{ String.class, String.class};

    public OsobinaModel(List<Osobina> osobine) {
        this.osobine = osobine;
    }

    @Override
    public int getRowCount() {
        if (osobine == null) {
            return 0;
        }
        return osobine.size();
    }

    @Override
    public int getColumnCount() {
        return vrednosti.length;

    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Osobina osobina = osobine.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return osobina.getImeOsobine();
            case 1:
                return osobina.getOpisOsobine();
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
