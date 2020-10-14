
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
public class ManagerTask {
    public int menu()
    {
        System.out.println("======== Task program ===========");
        System.out.println("1. Add task");
        System.out.println("2. Delete task");
        System.out.println("3. Display task");
        System.out.println("4. Exit");
        return Validation.checkChoice();
    }
    
    public void addTask(ArrayList<Task> list)
    {
        System.out.println("---------- Add Task -----------");
        
        String name=Validation.checkString("Name");
        int type=Validation.checkType();
        String date=Validation.checkDate();
        
        String from, to;
        
        while(true){
            System.out.print("From:");
            from=Validation.checkFromAndTo();
            System.out.print("To:");
            to=Validation.checkFromAndTo();
            if (Double.parseDouble(from) < Double.parseDouble(to)) break; 
            else System.out.println("Re-input");
        }
        String assignee=Validation.checkString("Assignee");
        String expert=Validation.checkString("Expert");

        int id;
        if (list.isEmpty())
               id=1;
        else{
            id=list.get(list.size()-1).getId()+1;
        }
        Task task=new Task(id, name,type, date, from, to, assignee, expert);

        list.add(task);  
    }
    
    public void deleteTask(ArrayList<Task> list)
    {
        System.out.println("----------- Del Task ------------");
        int index=Validation.checkID(list);
        list.remove(index);
        System.out.println("Delete successfully!");
    }
    
    public String typeTask(int type)
    {
        if(type==1)
            return "Code";
        else if (type==2)
            return "Test";
        else if (type==3)
            return "Design";
        else if (type==4)
            return "Review";
        else return "";
    }
    
    public void displayAll(ArrayList<Task> list)
    {
        System.out.println("---------------------------------------- Task ----------------------------------------");
        System.out.printf("%-5s%-15s%-15s%-15s%-15s%-15s%-15s\n","ID", "Name", "Task Type", "Date", "Time", "Assignee", "Reviewer");

        for (Task task : list) {
            System.out.printf("%-5d%-15s%-15s%-15s%-15.1f%-15s%-15s\n",task.getId(), task.getName(), typeTask(task.getTypeID()), task.getDate(),
                    Double.parseDouble(task.getTo())-Double.parseDouble(task.getFrom()), task.getAssignee(), task.getExpert());
        }
    }
}
