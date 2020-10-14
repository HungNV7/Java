
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
public class ManagerNumberSystem {
    public static void main(String[] args) {
        NumberSystem test=new NumberSystem();
        while(true)
        {
            int choice=test.menu();
            switch(choice){
                case 1: String binary=Validate.checkBinary();
                    test.convertFromBinary(binary);
                    break;
                case 2: String decimal=Validate.checkDecimal();
                    test.convertFromDecimal(decimal);
                    break;
                case 3: String hexa=Validate.checkHexadecimal();
                    test.convertFromHexa(hexa);
                    break;
                default: break;
            }
            Scanner s=new Scanner(System.in);
            System.out.println("Do you want to continue?(N to exit)");
            String c=s.nextLine();
            if (c.trim().toUpperCase().equals("N")) break;
        }
    }
    
}
