/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Admin
 */
public class Fruit {
    String fruitID;
    String name;
    double price;
    int quatity;
    String origin;

    public Fruit(){
    }
    
    public Fruit(String fruitID, String name, double price, int quatity, String origin) {
        this.fruitID = fruitID;
        this.name = name;
        this.price = price;
        this.quatity = quatity;
        this.origin = origin;
    }

    public String getFruitID() {
        return fruitID;
    }

    public void setFruitID(String fruitID) {
        this.fruitID = fruitID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuatity() {
        return quatity;
    }

    public void setQuatity(int quatity) {
        this.quatity = quatity;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    @Override
    public String toString() {
        return   name + "    " + origin + "     " + price+ "$";
    }
    
    
}
