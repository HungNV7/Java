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
        Calculation calculator=new Calculation();
        
        while(true){
            int choice=calculator.menu();
            switch(choice){
                case 1: calculator.calculateEquatation();
                    break;
                case 2: calculator.calculateQuadraticEquation();
                    break;
                case 3: break; 
            }
            if (choice==3) break;
        }
    }
    
}
