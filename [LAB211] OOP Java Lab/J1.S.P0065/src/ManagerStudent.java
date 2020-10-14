
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
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
public class ManagerStudent {
    List<Student> list;

    public ManagerStudent()
    {
        list=new ArrayList<>();
    }
    
    public ManagerStudent(List<Student> list) {
        this.list = list;
    }
    
    public Student createStudent()
    {
        Student student=new Student();

        student.name=Validation.checkString("Name");
        
        student.classes=Validation.checkString("Classes");
        
        student.math=Validation.checkMark("Maths");
        
        student.chemistry=Validation.checkMark("Chemistry");
        
        student.physical=Validation.checkMark("Physics");
        
        averageMark(student);

        classify(student);
        return student;
    }
    
    public void addStudent(Student student)
    {
        list.add(student);
    }
    
     public void averageMark(Student student)
    {
        student.average=(student.math + student.physical + student.chemistry)*1.0/3;
    }
    
     
    public List<Student> averageStudent()
    {
        for(int i=0; i<list.size(); i++){
            if (list.get(i).getType().equals("")){
                classify(list.get(i));
            }
        }
        return list;
    }
    
    
    public void classify(Student student)
    {
        if (student.average>7.5)
            student.type= "A";
        else if (student.average>=6)
                student.type= "B";
        else if (student.average >=4)
                student.type="C";
        else student.type="D";
    }
    
    public void displayInfoStudent()
    {
        for (int i=0; i<list.size(); i++)
        {
            System.out.println("-------- Student"+(i+1)+" Info ---------");
            System.out.println("Name: "+list.get(i).getName());
            System.out.println("Classes: "+list.get(i).getClasses());
            System.out.println("AVG: "+list.get(i).getAverage());
            System.out.println("Type: "+list.get(i).getType());
        }
    }
    
    public HashMap<String, Double> getPercentTypeStudent()
    {
        HashMap<String, Double> getPercentTypeStudent=new HashMap<>();
        int typeA=0; 
        int typeB=0;
        int typeC=0;
        int typeD=0;
        
        for (int i=0; i<list.size(); i++){
            if (list.get(i).getType().equals("A"))
                typeA++;
            if (list.get(i).getType().equals("B"))
                typeB++;
            if (list.get(i).getType().equals("C"))
                typeC++;
            if (list.get(i).getType().equals("D"))
                typeD++;
        }
        
        getPercentTypeStudent.put("A", typeA*1.0/list.size()*100);
        getPercentTypeStudent.put("B", typeB*1.0/list.size()*100);
        getPercentTypeStudent.put("D", typeD*1.0/list.size()*100);
        getPercentTypeStudent.put("C", typeC*1.0/list.size()*100);
        
        
        return getPercentTypeStudent;     
    }
    
    public void displayClassfication()
    {
        System.out.println("-------- Classification Info ---------");
        Iterator it=getPercentTypeStudent().keySet().iterator();
        while(it.hasNext()){
            Object type=it.next();
            System.out.println(type+": "+getPercentTypeStudent().get(type)+"%");
        }
    }
    
    public static void main(String[] args) {
        Validation.checkMark("Maths");
    }
}
