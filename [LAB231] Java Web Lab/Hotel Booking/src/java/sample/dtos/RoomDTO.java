/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.dtos;

/**
 *
 * @author ASUS
 */
public class RoomDTO {
    private String id, hotelName, categoryName;
    private int quantity = 0;
    private double price;
    

    public RoomDTO() {
    }

    public RoomDTO(String id, String hotelName, String categoryName, double price) {
        this.id = id;
        this.hotelName = hotelName;
        this.categoryName = categoryName;
        this.price = price;
    }

    public RoomDTO(String id, String hotelName, String categoryName, double price, int amount) {
        this.id = id;
        this.hotelName = hotelName;
        this.categoryName = categoryName;
        this.price = price;
        this.quantity = amount;
    }
    
    
   
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    
}
