
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
    public static int checkInput()
    {
        int choice;
        while(true){
            try {
                Scanner s=new Scanner(System.in);
                System.out.println("Enter your choice:");
                choice = s.nextInt();
                if (choice>0 && choice<4) break;
                else System.out.println("input is not valid");
            } catch (Exception e) {
                System.out.println("input is not valid");
            }
        }
        return choice;
    }
    
    public static double checkNumber()
    {
        double x;
        while(true){
            try {
                 Scanner s=new Scanner(System.in);
                 x=s.nextDouble();
                 return x;
            } catch (Exception e) {
                System.out.println("Please input number");
            }
        }
    }
}
