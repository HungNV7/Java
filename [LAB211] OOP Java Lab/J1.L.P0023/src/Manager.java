
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Admin
 */
public class Manager {
   public int menu()
    {
        System.out.println("FRUIT SHOP SYSTEM");
        System.out.println("1. Create Fruit");
        System.out.println("2. View orders");
        System.out.println("3. Shopping (for buyer)");
        System.out.println("4. Exit");
        return Validation.checkChoice();
    }
    
    public void createFruit(ArrayList<Fruit> list)
    {
        while(true){
            String id=Validation.checkString("Fruit ID");
            String name=Validation.checkString("Name");
            double price=Validation.checkPrice();
            int quatity=Validation.checkQuantity();
            String origin=Validation.checkString("Origin");
        
            Fruit fruit=new Fruit(id, name, price, quatity, origin);
            
            list.add(fruit);
            
            System.out.print("Do you want to continue(Y/N)?:");
            if (!Validation.checkYN()) break;
        }
        displayFruit(list);
    }
    
    public void displayFruit(ArrayList<Fruit> list)
    {
        System.out.println("| Item | Fruit Name | Origin | Price |");
        for(int i=0; i<list.size(); i++){
            System.out.print("    "+(i+1)+"       ");
            System.out.println(list.get(i));
        }
    }
    
    public void shopping(ArrayList<Fruit> list, Hashtable<String, ArrayList<Fruit>> listOrder)
    {
        int fruitSelected, quatitySelected;
        
        if(list.isEmpty()){
            System.out.println("No have item");
            return;
        }
        
        ArrayList<Fruit> order=new ArrayList<>();
        while(true){
            if (list.isEmpty()){
                System.out.println("No have any item in shop!");
                break;
            }
            displayFruit(list);
            
            fruitSelected=Validation.checkFruitSelect(list.size());
            Fruit fruit=list.get(fruitSelected-1);
            System.out.println("You selected: "+fruit.getName());
            
            quatitySelected =Validation.checkQuantitySelected(fruit.getQuatity());
            
            int remain=fruit.getQuatity()-quatitySelected;
            fruit.setQuatity(remain);
            if (remain==0)
                list.remove(fruit);
            
            order.add(new Fruit(fruit.getFruitID(), fruit.getName(), fruit.getPrice(), quatitySelected, fruit.getOrigin()));
            
            System.out.println("Do you want to order now (Y/N)?");
            if(Validation.checkYN()) break;
        }      
        displayOrder(order);
        String name=Validation.checkName();
        listOrder.put(name, order);
    }
    
    public void displayOrder(ArrayList<Fruit> order)
    {
        int total=0;
        System.out.println(" Product | Quantity | Price | Amount");
        for (int i=0; i<order.size(); i++){
            Fruit fruit=order.get(i);
            System.out.println(fruit.getName()+"    "+fruit.getQuatity()+"   "+fruit.getPrice()+"$   "+fruit.getQuatity()*fruit.getPrice()+"$");
            total+=fruit.getQuatity()*fruit.getPrice();
        }
        System.out.println("Total:"+total+"$");
    }
    
    public void viewOrder(Hashtable<String, ArrayList<Fruit>> listOrder)
    {
        if (listOrder.isEmpty()){
            System.out.println("No have any order");
            return;
        }
        for (String name : listOrder.keySet()) {
            System.out.println("Customer: "+name);
            ArrayList<Fruit> order=listOrder.get(name);
            displayOrder(order);
        }
    }

}
