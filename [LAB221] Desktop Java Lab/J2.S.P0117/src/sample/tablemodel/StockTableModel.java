/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.tablemodel;

import sample.dtos.StockDTO;
import java.util.Stack;
import java.util.Vector;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Admin
 */
public class StockTableModel extends AbstractTableModel{
    private String[] header;
    private int[] indexes;
    private Vector<StockDTO> data;

    public StockTableModel(String[] header, int[] indexes) {
        this.header = new String[header.length];
        for(int i=0; i<header.length; i++){
            this.header[i]=header[i];
        }
        this.indexes = new int[indexes.length];
        for(int i=0; i<indexes.length; i++){
            this.indexes[i]=indexes[i];
        }
        this.data=new Vector<>();
    }

    public Vector<StockDTO> getData() {
        return data;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }
    
    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return header.length;
    }

    public String getColumnName(int column){
        return (column>=0 && column<header.length)?header[column]:"";
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        StockDTO stock=data.get(rowIndex);
        switch(indexes[columnIndex]){
            case 0: return stock.getId();
            case 1: return stock.getName();
            case 2: return stock.getAddress();
            case 3: return stock.getDataAvilable();
            case 4: return stock.getNote();
        }
        return null;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        StockDTO stock=data.get(rowIndex);
        switch(indexes[columnIndex]){
            case 0: stock.setId((String)aValue);    break;
            case 1: stock.setName((String)aValue);  break;
            case 2: stock.setAddress((String)aValue);   break;
            case 3: stock.setDataAvilable((String)aValue);  break;
            case 4: stock.setNote((String)aValue); break;
        }
                
    }
    
}
