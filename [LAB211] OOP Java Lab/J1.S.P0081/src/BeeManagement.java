
import java.util.ArrayList;
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Admin
 */
public class BeeManagement {
    ArrayList<Bees> list;
    
    public BeeManagement()
    {
        list=new ArrayList<>();
    }
    
    public void create()
    {
        list.removeAll(list);
        for (int i=0; i<10; i++)
        {
            list.add(new Bees());
        }
    }
    
    public void damage()
    {   if (list.isEmpty()){
                System.out.println("list of bee is not created!");
                return;
        }
        for (int i=0; i<10; i++)
        {
            Random rd=new Random();
            list.get(i).damage(rd.nextInt(81));
        }
    }
    
    public void displayAll()
    {
        for (int i=0; i<list.size(); i++)
        {
            System.out.println(list.get(i));
        }
    }
  
}
