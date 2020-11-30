/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.dtos;

import java.sql.Date;
import java.util.HashMap;

/**
 *
 * @author ASUS
 */
public class CartDTO {

    private String id, userID;
    private HashMap<String, RoomDTO> cart;
    private double total, finalTotal;
    private String discountID;
    private Date arriveDate, returnDate;
    private int status;
    private Date bookingDate;

    public CartDTO() {
    }

    public CartDTO(String id, String userID, HashMap<String, RoomDTO> cart, double total, double finalTotal, String discountID, Date arriveDate, Date returnDate, int status) {
        this.id = id;
        this.userID = userID;
        this.cart = cart;
        this.total = total;
        this.finalTotal = finalTotal;
        this.discountID = discountID;
        this.arriveDate = arriveDate;
        this.returnDate = returnDate;
        this.status = status;
    }

    public CartDTO(String id, String userID, HashMap<String, RoomDTO> cart, double total, double finalTotal, String discountID, Date arriveDate, Date returnDate, int status, Date bookingDate) {
        this.id = id;
        this.userID = userID;
        this.cart = cart;
        this.total = total;
        this.finalTotal = finalTotal;
        this.discountID = discountID;
        this.arriveDate = arriveDate;
        this.returnDate = returnDate;
        this.status = status;
        this.bookingDate = bookingDate;
    }

    public void add(RoomDTO room, int _quantity) {
        if (cart == null) {
            cart = new HashMap<String, RoomDTO>();
        }
        String roomID = room.getId();
        int quantity = _quantity;
        if (cart.containsKey(roomID)) {
            quantity += cart.get(roomID).getQuantity();
        }
        this.total += room.getPrice() * _quantity;
        this.finalTotal = this.total;
        room.setQuantity(quantity);
        cart.put(roomID, room);
    }

    public void update(String roomID, int quantity) {
        if (cart.containsKey(roomID)) {
            this.total -= cart.get(roomID).getPrice() * cart.get(roomID).getQuantity();
            cart.get(roomID).setQuantity(quantity);
            this.total += cart.get(roomID).getPrice() * cart.get(roomID).getQuantity();
        }
        this.finalTotal = this.total;
    }

    public void remove(String roomID) {
        if (cart.containsKey(roomID)) {
            this.total -= cart.get(roomID).getPrice() * cart.get(roomID).getQuantity();
            cart.remove(roomID);
        }
        this.finalTotal = this.total;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public HashMap<String, RoomDTO> getCart() {
        return cart;
    }

    public void setCart(HashMap<String, RoomDTO> cart) {
        this.cart = cart;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getFinalTotal() {
        return finalTotal;
    }

    public void setFinalTotal(double finalTotal) {
        this.finalTotal = finalTotal;
    }

    public String getDiscountID() {
        return discountID;
    }

    public void setDiscountID(String discountID) {
        this.discountID = discountID;
    }

    public Date getArriveDate() {
        return arriveDate;
    }

    public void setArriveDate(Date arriveDate) {
        this.arriveDate = arriveDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

}
