
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
    public static int checkRowColumn()
    {
        while(true){
            try {
                Scanner s=new Scanner(System.in);
                int number=s.nextInt();
                if (number>0)
                    return number;
                else System.out.println("Re-input");
            } catch (Exception e) {
                System.out.println("Value must be the number");
                System.out.println("Re-input");
            }
        }
    }
    
    public static int checkValue()
    {
        while(true){
            try {
                Scanner s=new Scanner(System.in);
                int number=s.nextInt();
                return number;
            } catch (Exception e) {
                System.out.println("Value must be the number");
                System.out.println("Re-input");
            }
        }
    }
    
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
    
}
