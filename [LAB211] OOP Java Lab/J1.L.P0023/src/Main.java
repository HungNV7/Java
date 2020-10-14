
import java.util.ArrayList;
import java.util.Hashtable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Admin
 */
public class Main {
    
    public static void main(String[] args) {
        Manager manager=new Manager();
        ArrayList<Fruit> list=new ArrayList<>();
        Hashtable<String, ArrayList<Fruit>> listOrder=new Hashtable<>();
                
        while(true)
        {
            int choice=manager.menu();
            switch(choice){
                case 1: manager.createFruit(list);
                        break;
                case 2: manager.viewOrder(listOrder);
                        break;
                case 3: manager.shopping(list, listOrder);
                    break;
                case 4:break;  
            }
            if (choice==4) break;
        }
    }
}
