
import java.util.ArrayList;

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
        ManagerTask manager=new ManagerTask();
        ArrayList<Task> list=new ArrayList<>();
        while(true){
            int choice=manager.menu();
            switch(choice){
                case 1: manager.addTask(list);
                    break;
                case 2: manager.deleteTask(list);
                    break;
                case 3: manager.displayAll(list);
                    break;
                case 4: break;
            }
            if (choice==4) break;
        }
    }
}
