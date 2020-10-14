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
        Matrix calculator =new Matrix();
        while(true){
            int choice=calculator.menu();
            switch(choice){
                case 1: calculator.addition();
                    break;
                case 2: calculator.substraction();
                    break;
                case 3: calculator.multiplication();
                    break;
            }
            if (choice==4) break;
            
        }
    }
}
