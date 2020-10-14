
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
public class Test {
    public int Menu()
    {
        System.out.println("----BEE----");
        System.out.println("1. Create new bee list.");
        System.out.println("2. Damage bee.");
        System.out.println("3. Quit.");
        System.out.print("Choose an option:");
        while(true){
            try {
                Scanner s=new Scanner(System.in);
                int choice=s.nextInt();
                if (choice>0 && choice<4) return choice;
                else System.out.println("Re-input");
            } catch (Exception e) {
                System.out.println("Re-input");
            }
        }
    }
    
      
    public static void main(String[] args) {
        Test test =new Test();
        BeeManagement listBee=new BeeManagement();
        int choice;
        do{    
            choice=test.Menu();
            switch(choice)
            {
                case 1: listBee.create();
                        listBee.displayAll();
                        break;
                case 2: listBee.damage();
                        listBee.displayAll();
                        break;  
            }
            
        }
        while (choice!=3);
    }
}
