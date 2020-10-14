
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
    public static double checkMark(String subject)
    {
        double mark;
        while(true){
            try {
                Scanner s=new Scanner(System.in);
                System.out.print(subject+":");
                String str=s.nextLine();
                if (str.equals(""))
                    System.out.println(subject+" is digit");
                else{
                    mark=Double.parseDouble(str);
                    if (mark<0) System.out.println(subject+" is greater than equal zero");
                    else if (mark>10)   System.out.println(subject+" is less than equal ten");
                    else break;
                }
            } catch (Exception e) {
                System.out.println(subject+" is a number");
            }
        }
        return mark;
    }
    
    public static String checkString(String object)
    {
        String str;
        while(true){
            Scanner s=new Scanner(System.in);
            System.out.print(object+":");
            str=s.nextLine();
            if (!str.isEmpty()) break;
            else{
                System.out.println("Re-input!");
            }
        }
        return str;
    }
    public static boolean checkYN()
    {
        while(true){
            System.out.print("Do you want to enter more student information?(Y/N):");
            Scanner s=new Scanner(System.in);
            String str=s.nextLine();
            if (str.trim().length()==1){
                if (str.toUpperCase().charAt(0)=='Y')
                    return true;
                if (str.toUpperCase().charAt(0)=='N')
                    return false;
            }
            System.out.println("input is not valid");
            System.out.print("Re-input:");
        }
    }
}
