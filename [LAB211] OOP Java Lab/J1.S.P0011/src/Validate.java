
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Admin
 */
public class Validate {
    public static int checkBaseNumber()
    {
        int choice;
        Scanner s=new Scanner(System.in);
        while(true){
            try {
                System.out.println("Choose the base number:");
                choice=s.nextInt();
                if(choice>0 && choice<4) break;
                else  System.out.println("input is not valid");
            } catch (Exception e) {
                System.out.println("input is not valid");
            }
        }
        return choice;
       
    }
    
    public static String checkBinary()
    {
        Scanner s=new Scanner(System.in);
        String binary;
        while(true){
            System.out.println("Enter binary number:");
            binary=s.nextLine();
            if (binary.matches("[0|1]+")) break;
            else System.out.println("input is not valid");
        }
        return binary;
    }
    
    public static String checkDecimal()
    {
        Scanner s=new Scanner(System.in);
        String decimal;
        while(true){
            System.out.println("Enter decimal number:");
            decimal=s.nextLine();
            if (decimal.matches("[0-9]+")) break;
            else System.out.println("input is not valid");
        }
        return decimal;
    }
    
    public static String checkHexadecimal()
    {
        Scanner s=new Scanner(System.in);
        String hexa;
        while(true){
            System.out.println("Enter binary number:");
            hexa=s.nextLine();
            if (hexa.matches("[0-9A-Fa-f]+")) break;
            else System.out.println("input is not valid");
        }
        return hexa;
    }
}
