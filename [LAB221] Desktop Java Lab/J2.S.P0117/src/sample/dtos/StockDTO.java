package sample.dtos;


import java.sql.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Admin
 */
public class StockDTO{
    private String id;
    private String name;
    private String address;
    private String dataAvilable;
    private String note;

    public StockDTO(String id, String name, String address, String dataAvilable, String note) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.dataAvilable = dataAvilable;
        this.note = note;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDataAvilable() {
        return dataAvilable;
    }

    public void setDataAvilable(String dataAvilable) {
        this.dataAvilable = dataAvilable;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
    
    
}
