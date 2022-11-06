/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeli;

import domen.Camac;
import domen.MarkaCamca;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author PC
 */
public class CamacModel extends AbstractTableModel{
    private final List<Camac> camci;

    private final String[] vrednosti = new String[]{"Naziv modela","Opis", "Godina garancije", "Kolicina", "Cena", "Marka camca"};
    private final Class[] klase = new Class[]{ String.class, String.class, Integer.class,Integer.class,Double.class, MarkaCamca.class};

    public CamacModel(List<Camac> camci) {
        this.camci = camci;
    }

    @Override
    public int getRowCount() {
        if (camci == null) {
            return 0;
        }
        return camci.size();
    }

    @Override
    public int getColumnCount() {
        return vrednosti.length;

    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Camac camac = camci.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return camac.getNazivModela();
            case 1:
                return camac.getOpisCamca();
            case 2:
                return camac.getGodinaGarancije();
            case 3:
                return camac.getKolicinaNaStanju();
            case 4:
                return camac.getCena();
            case 5:
                return camac.getMarka();
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
