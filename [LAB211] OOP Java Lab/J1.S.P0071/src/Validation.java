
import java.awt.BorderLayout;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    public static String checkString(String object)
    {
        while(true){
            Scanner s=new Scanner(System.in);
            System.out.print(object+":");
            String name=s.nextLine();
            if (name.equals("")){
                System.out.println(object+" is not null");
                System.out.println("Re-input");
            }
            else return name;
        }
    }
    
    public static int checkType()
    {
        while(true){
            try {
                 Scanner s=new Scanner(System.in);
                 System.out.print("Task type:");
                 int type=s.nextInt();
                 if (type>0 && type<5) return type;
                 else{
                     System.out.println("type must be less than 5 and geater than 0");
                       System.out.println("re-input");
                 }
            } catch (Exception e) {
                System.out.println("Type is a integer number");
                System.out.println("re-input");
            }
        }
    }
    
    public static String checkDate()
    {
        while(true){
            try{
              Scanner s=new Scanner(System.in);
              System.out.print("Date:");
              String stringDate=s.nextLine().trim();
              
              SimpleDateFormat format=new SimpleDateFormat("dd-MM-yyyy");
              Date date=format.parse(stringDate);
              
              if (stringDate.equals(format.format(date)) && date.before(new Date()))
                      return stringDate;
              else{
                  System.out.println("Re-input");
              }
            } catch (ParseException ex) {
                System.out.println("Re-input");
            }
            
        }
    }
    
    public static String checkFromAndTo()
    {
        String regex="^[0-9]{1,2}\\.5$|^[0-9]{1,2}\\.0$";
        while(true){
            Scanner s=new Scanner(System.in);
            String plan=s.nextLine().trim();
            if (plan.matches(regex) && Double.parseDouble(plan) >=8.0 && Double.parseDouble(plan) <=17.5){
                return plan;
            }
            else System.out.println("Re-input");
        }
    }
    
    public static int checkID(ArrayList<Task> list)
    {
        while(true){
            try {
                Scanner s=new Scanner(System.in);
                System.out.print("Enter ID deleted:");
                int id=s.nextInt();
                for (int i=0; i<list.size(); i++) {
                    if (list.get(i).getId()==id)
                        return i;
                }
                        System.out.println("ID is not exist");
                        System.out.println("Re-input");
                
            } catch (Exception e) {
                System.out.println("ID is a number");
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
