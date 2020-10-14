
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Admin
 */
public class Validation {
    public static int checkChoice()
    {
        while(true){
            try {
                Scanner s=new Scanner(System.in);
                int choice=s.nextInt();
                if (choice>0 && choice <5) return choice;
                else{
                    System.out.println("Input is not valid!");
                    System.out.println("Re-input");
                }
            } catch (Exception e) {
                System.out.println("Choice is a integer number");
                System.out.println("Re-input");
            }
        }
    }
    
    public static String checkString(String object)
    {
        String str;
        while(true){
            Scanner s=new Scanner(System.in);
            System.out.print(object+":");
            str=s.nextLine();
            if (!str.equals("")) break;
            else System.out.println("Re-input:");
        }
        return str;
    }
    
    public static double checkPrice()
    {
       double price;
       while(true){
           try {
                 Scanner s=new Scanner(System.in);
                 System.out.print("Price:");
                 price=s.nextDouble();
                 if (price<=0) System.out.println("Re-input");
                 else break;
           } catch (Exception e) {
               System.out.println("price is a number");
               System.out.println("Re-input");
           }
       }
       return price;
    }
    
    public static int checkQuantity()
    {
       int quatity;
       while(true){
           try {
                 Scanner s=new Scanner(System.in);
                 System.out.print("Quatity:");
                 quatity=s.nextInt();
                 if (quatity<0) System.out.println("Re-input");
                 else break;
           } catch (Exception e) {
               System.out.println("quatity is an integer number");
               System.out.println("Re-input");
           }
       }
       return quatity;
    }
    
    public static boolean checkYN()
    {
        while(true){
            
            Scanner s=new Scanner(System.in);
            String str=s.nextLine();
            if (str.trim().length()==1){
                if (str.toUpperCase().charAt(0)=='Y')
                    return true;
                if (str.toUpperCase().charAt(0)=='N')
                    return false;
            }
            System.out.println("Input is not valid");
            System.out.print("Re-input:");
        }
    }
    
    public static int checkFruitSelect(int max)
    {
        while(true){
            try {
                Scanner s=new Scanner(System.in);
                System.out.print("Your selected item:");
                int choice=s.nextInt();
                if (choice >0 && choice <=max) return choice;
                else{
                    System.out.println("choice must be greater than one and less than "+max);
                    System.out.println("Re-input");
                }
                
            } catch (Exception e) {
                System.out.println("Must be a number");
                System.out.println("Re-input");
            }
        }
    }
    
    public static int checkQuantitySelected(int max)
    {
        while(true){
            try {
                Scanner s=new Scanner(System.in);
                System.out.print("Please input quantity:");
                int quantity=s.nextInt();
                if (quantity >0 && quantity <=max) return quantity;
                else{
                    System.out.println("choice must be greater than one and less than "+max+"(because there are/is "+max+" product(s) in shop)");
                    System.out.println("Re-input");
                }
                
            } catch (Exception e) {
                System.out.println("Must be a number");
                System.out.println("Re-input");
            }
        }
    }
    
    public static String checkName()
    {
        while(true){
            Scanner s=new Scanner(System.in);
            System.out.println("Input your name:");
            String name=s.nextLine();
            if (name.equals("")) System.out.println("Name not null");
            else return name;
        }
    }
}
